package com.nfcx.luxinvpower.protocol.tcp.data.datalog;

import com.nfcx.luxinvpower.protocol.tcp.data.Data;
import com.nfcx.luxinvpower.tool.ProTool;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class ReadDatalogParamData implements Data {
    private String datalogSn;
    private int paramIndex;

    public ReadDatalogParamData(String str, int i) {
        this.datalogSn = str;
        this.paramIndex = i;
    }

    @Override // com.nfcx.luxinvpower.protocol.tcp.data.Data
    public byte[] getFrame() {
        byte[] bArr = new byte[12];
        try {
            System.arraycopy(this.datalogSn.getBytes("ISO-8859-1"), 0, bArr, 0, 10);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ProTool.convertLong2Byte2(bArr, 10, this.paramIndex, 0, true);
        return bArr;
    }
}
