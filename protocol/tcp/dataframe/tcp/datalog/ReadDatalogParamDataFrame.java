package com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.datalog;

import com.nfcx.luxinvpower.protocol.tcp.TCP_FUNCTION;
import com.nfcx.luxinvpower.protocol.tcp.data.datalog.ReadDatalogParamData;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.AbstractTcpDataFrame;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL;

/* loaded from: classes2.dex */
public class ReadDatalogParamDataFrame extends AbstractTcpDataFrame {
    public ReadDatalogParamDataFrame(TCP_PROTOCOL tcp_protocol, String str, int i) {
        super(TCP_FUNCTION.R_PARAM, tcp_protocol);
        setData(new ReadDatalogParamData(str, i));
    }
}
