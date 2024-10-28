package com.nfcx.luxinvpower.protocol.lux.command.read;

import com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION;
import com.nfcx.luxinvpower.protocol.lux.command.Command;
import com.nfcx.luxinvpower.tool.ProTool;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class AbstractQueryCommand implements Command {
    private int address;
    private DEVICE_FUNCTION deviceFunction;
    private int pointNumber;
    private String serialNum;
    private int startAddress;

    public AbstractQueryCommand(int i, DEVICE_FUNCTION device_function, String str, int i2, int i3) {
        this.address = i;
        this.deviceFunction = device_function;
        this.serialNum = str;
        this.startAddress = i2;
        this.pointNumber = i3;
    }

    @Override // com.nfcx.luxinvpower.protocol.lux.command.Command
    public byte[] getFrame() {
        byte[] bArr = new byte[18];
        bArr[0] = (byte) this.address;
        bArr[1] = (byte) this.deviceFunction.getFunctionCode();
        try {
            System.arraycopy(this.serialNum.getBytes("ISO-8859-1"), 0, bArr, 2, this.serialNum.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ProTool.convertLong2Byte2(bArr, 12, this.startAddress, 0, true);
        ProTool.convertLong2Byte2(bArr, 14, this.pointNumber, 0, true);
        ProTool.convertLong2Byte2(bArr, 16, ProTool.modbus_Caluation_CRC16(bArr, 16), 0, true);
        return bArr;
    }
}
