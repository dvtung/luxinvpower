package com.nfcx.luxinvpower.connect;

import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.bean.inverter.BATTERY_TYPE;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.protocol.tcp.DataFrameFactory;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL;
import com.nfcx.luxinvpower.tool.FrameTool;
import com.nfcx.luxinvpower.tool.ProTool;
import com.nfcx.luxinvpower.tool.Tool;
import java.nio.charset.StandardCharsets;

/* loaded from: classes2.dex */
public abstract class AbstractLocalConnect implements LocalConnect {
    protected String connectType;
    protected boolean connected;
    protected String datalogSn;
    protected Inverter inverter;
    protected TCP_PROTOCOL tcpProtocol = TCP_PROTOCOL._02;

    public AbstractLocalConnect(String str) {
        this.connectType = str;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public boolean read03AndInitDevice() {
        String sendCommand = sendCommand("read_03_1", DataFrameFactory.createReadMultiHoldDataFrame(getTcpProtocol(), getDatalogSn(), 0, 40));
        if (Tool.isEmpty(sendCommand) || sendCommand.length() <= 60) {
            return false;
        }
        Inverter inverter = new Inverter();
        inverter.setDatalogSn(FrameTool.getDatalogSn(sendCommand));
        inverter.setSerialNum(FrameTool.getInverterSn(sendCommand));
        long register4 = FrameTool.getRegister4(sendCommand, 0);
        inverter.setModel(Long.valueOf(register4));
        inverter.setBatteryTypeFromModel(BATTERY_TYPE.getBatteryTypeByCode((int) ((register4 >> 8) & 3)));
        if (BATTERY_TYPE.LEAD_ACID.equals(inverter.getBatteryTypeFromModel())) {
            inverter.setLeadAcidType(Integer.valueOf((int) ((register4 >> 10) & 31)));
        }
        int register2 = FrameTool.getRegister2(sendCommand, 19);
        inverter.setDeviceType(Integer.valueOf((register2 >> 1) & 15));
        inverter.setDtc(Integer.valueOf((register2 >> 5) & 7));
        inverter.setUsVersion(Integer.valueOf((int) ((register4 >> 19) & 1)));
        inverter.setPowerRating(Integer.valueOf((int) ((register4 >> 5) & 7)));
        int regHighInt = FrameTool.getRegHighInt(sendCommand, 9);
        int regLowInt = FrameTool.getRegLowInt(sendCommand, 9);
        int regLowInt2 = FrameTool.getRegLowInt(sendCommand, 10);
        if (inverter.isSnaSeries() || inverter.isLsp()) {
            inverter.setFwCode(FrameTool.getRegisterText(sendCommand, 7, 2) + "-" + ProTool.showHex(regLowInt) + ProTool.showHex(regHighInt) + ProTool.showHex(regLowInt2));
        } else {
            inverter.setFwCode(FrameTool.getRegisterText(sendCommand, 7, 2) + "-" + ProTool.showHex(regHighInt) + ProTool.showHex(regLowInt2));
        }
        if (inverter.isSnaSeries()) {
            inverter.setSubDeviceType(inverter.isDtcAmerica() ? 131 : null);
        } else if (inverter.isSna12K()) {
            inverter.setSubDeviceType(Integer.valueOf(inverter.isDtcAmerica() ? Constants.SUB_DEVICE_TYPE_SNA_12K_US : Constants.SUB_DEVICE_TYPE_SNA_12K_EU));
        } else if (inverter.isType6() && inverter.getPowerRating() != null) {
            if (inverter.is12KUsVersion()) {
                if (inverter.getPowerRating().intValue() == 3 || inverter.getPowerRating().intValue() == 6) {
                    inverter.setSubDeviceType(164);
                } else {
                    inverter.setSubDeviceType(163);
                }
            } else if (inverter.getPowerRating().intValue() == 3 || inverter.getPowerRating().intValue() == 6) {
                inverter.setSubDeviceType(162);
            } else {
                inverter.setSubDeviceType(161);
            }
        }
        setInverter(inverter);
        return true;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public Integer readSingle03(int i) {
        if (!initialize(true) || Tool.isEmpty(this.datalogSn)) {
            return null;
        }
        String sendCommand = sendCommand("read_single_03_" + i, DataFrameFactory.createReadSingleHoldDataFrame(this.tcpProtocol, this.datalogSn, i));
        if (Tool.isEmpty(sendCommand) || sendCommand.length() <= 36) {
            return null;
        }
        return Integer.valueOf(ProTool.count(sendCommand.charAt(36), sendCommand.charAt(35)));
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public boolean writeSingle(int i, int i2) {
        if (initialize(true) && !Tool.isEmpty(this.datalogSn)) {
            String sendCommand = sendCommand("write_single_06", DataFrameFactory.createWriteSingleDataFrame(this.tcpProtocol, this.datalogSn, i, i2));
            return !Tool.isEmpty(sendCommand) && sendCommand.length() > 33 && i == ProTool.count(sendCommand.charAt(33), sendCommand.charAt(32));
        }
        return false;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public boolean writeMulti(int i, int i2, byte[] bArr) {
        if (initialize(true) && !Tool.isEmpty(this.datalogSn)) {
            String sendCommand = sendCommand("write_multi_06_" + i + "_" + i2, DataFrameFactory.createWriteMultiDataFrame(this.tcpProtocol, this.datalogSn, i, i2, bArr));
            if (!Tool.isEmpty(sendCommand) && sendCommand.length() > 33) {
                return i == ProTool.count(sendCommand.charAt(33), sendCommand.charAt(32)) && i2 == ProTool.count(sendCommand.charAt(35), sendCommand.charAt(34));
            }
        }
        return false;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public String readDatalogParam(int i) {
        if (!initialize(true) || Tool.isEmpty(this.datalogSn)) {
            return null;
        }
        String sendCommand = sendCommand("read_datalog_" + i, DataFrameFactory.createReadDatalogParamDataFrame(this.tcpProtocol, this.datalogSn, i), 5);
        if (Tool.isEmpty(sendCommand) || sendCommand.length() <= 22) {
            return null;
        }
        return sendCommand.substring(22);
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public boolean writeDatalogParam(int i, String str) {
        if (Tool.isEmpty(str)) {
            return false;
        }
        return writeDatalogParam(i, str.getBytes(StandardCharsets.ISO_8859_1));
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public boolean writeDatalogParam(int i, byte[] bArr) {
        if (initialize(true) && !Tool.isEmpty(this.datalogSn)) {
            String sendCommand = sendCommand("write_datalog_" + i, DataFrameFactory.createWriteDatalogParamDataFrame(this.tcpProtocol, this.datalogSn, i, bArr));
            return !Tool.isEmpty(sendCommand) && sendCommand.length() == 21 && sendCommand.charAt(20) == 0;
        }
        return false;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public String getConnectType() {
        return this.connectType;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public Inverter getInverter() {
        return this.inverter;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public void setInverter(Inverter inverter) {
        this.inverter = inverter;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public String getDatalogSn() {
        return this.datalogSn;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public void setDatalogSn(String str) {
        this.datalogSn = str;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public TCP_PROTOCOL getTcpProtocol() {
        return this.tcpProtocol;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public void setTcpProtocol(TCP_PROTOCOL tcp_protocol) {
        this.tcpProtocol = tcp_protocol;
    }
}
