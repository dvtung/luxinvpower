package com.nfcx.luxinvpower.protocol.tcp.dataframe;

import com.nfcx.luxinvpower.tool.ProTool;

/* loaded from: classes2.dex */
public abstract class AbstractDataFrame implements DataFrame {
    protected byte[] frame;

    @Override // com.nfcx.luxinvpower.protocol.tcp.dataframe.DataFrame
    public String show() {
        return ProTool.showData(getFrame());
    }
}
