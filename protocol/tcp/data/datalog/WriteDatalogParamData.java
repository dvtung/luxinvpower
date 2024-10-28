package com.nfcx.luxinvpower.protocol.tcp.data.datalog;

import com.nfcx.luxinvpower.protocol.tcp.data.Data;
import com.nfcx.luxinvpower.tool.ProTool;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class WriteDatalogParamData implements Data {
    private String datalogSn;
    private int paramIndex;
    private byte[] values;

    public WriteDatalogParamData(String str, int i, byte[] bArr) {
        this.datalogSn = str;
        this.paramIndex = i;
        this.values = bArr;
    }

    @Override // com.nfcx.luxinvpower.protocol.tcp.data.Data
    public byte[] getFrame() {
        byte[] bArr = new byte[this.values.length + 14];
        try {
            System.arraycopy(this.datalogSn.getBytes("ISO-8859-1"), 0, bArr, 0, 10);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ProTool.convertLong2Byte2(bArr, 10, this.paramIndex, 0, true);
        ProTool.convertLong2Byte2(bArr, 12, this.values.length, 0, true);
        byte[] bArr2 = this.values;
        System.arraycopy(bArr2, 0, bArr, 14, bArr2.length);
        return bArr;
    }
}
