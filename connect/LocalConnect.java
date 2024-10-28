package com.nfcx.luxinvpower.connect;

import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.DataFrame;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL;

/* loaded from: classes2.dex */
public interface LocalConnect {
    void close();

    String getCommandWaitResult(String str);

    String getConnectType();

    String getDatalogSn();

    Inverter getInverter();

    TCP_PROTOCOL getTcpProtocol();

    boolean initialize(boolean z);

    boolean read03AndInitDevice();

    String readDatalogParam(int i);

    Integer readSingle03(int i);

    String sendCommand(String str, DataFrame dataFrame);

    String sendCommand(String str, DataFrame dataFrame, int i);

    boolean sendPureCommand(DataFrame dataFrame);

    void setDatalogSn(String str);

    void setInverter(Inverter inverter);

    void setTcpProtocol(TCP_PROTOCOL tcp_protocol);

    boolean writeDatalogParam(int i, String str);

    boolean writeDatalogParam(int i, byte[] bArr);

    boolean writeMulti(int i, int i2, byte[] bArr);

    boolean writeSingle(int i, int i2);
}
