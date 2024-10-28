package com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.datalog;

import com.nfcx.luxinvpower.protocol.tcp.TCP_FUNCTION;
import com.nfcx.luxinvpower.protocol.tcp.data.Data;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.AbstractTcpDataFrame;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL;

/* loaded from: classes2.dex */
public class WriteDatalogParamDataFrame extends AbstractTcpDataFrame {
    public WriteDatalogParamDataFrame(TCP_PROTOCOL tcp_protocol, Data data) {
        super(TCP_FUNCTION.W_PARAM, tcp_protocol);
        setData(data);
    }
}
