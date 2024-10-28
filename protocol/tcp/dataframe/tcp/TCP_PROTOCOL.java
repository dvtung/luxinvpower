package com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp;

/* loaded from: classes2.dex */
public enum TCP_PROTOCOL {
    _01 { // from class: com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL.1
        @Override // com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL
        public int getPrefixData() {
            return 1;
        }
    },
    _02 { // from class: com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL.2
        @Override // com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL
        public int getPrefixData() {
            return 2;
        }
    };

    public abstract int getPrefixData();
}
