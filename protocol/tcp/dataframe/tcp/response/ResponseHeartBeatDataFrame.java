package com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.response;

import com.nfcx.luxinvpower.protocol.tcp.TCP_FUNCTION;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.AbstractTcpDataFrame;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL;

/* loaded from: classes2.dex */
public class ResponseHeartBeatDataFrame extends AbstractTcpDataFrame {
    public ResponseHeartBeatDataFrame(TCP_PROTOCOL tcp_protocol) {
        super(TCP_FUNCTION.HEART_BEAT, tcp_protocol);
    }
}
