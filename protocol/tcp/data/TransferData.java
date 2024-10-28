package com.nfcx.luxinvpower.protocol.tcp.data;

import com.nfcx.luxinvpower.tool.ProTool;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class TransferData implements Data {
    protected byte[] command;
    protected String datalogSn;

    public TransferData(String str) {
        this.datalogSn = str;
    }

    public void setCommand(byte[] bArr) {
        this.command = bArr;
    }

    @Override // com.nfcx.luxinvpower.protocol.tcp.data.Data
    public byte[] getFrame() {
        byte[] bArr = this.command;
        byte[] bArr2 = new byte[(bArr != null ? bArr.length : 0) + 12];
        try {
            System.arraycopy(this.datalogSn.getBytes("ISO-8859-1"), 0, bArr2, 0, 10);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ProTool.convertLong2Byte2(bArr2, 10, this.command != null ? r3.length : 0, 0, true);
        byte[] bArr3 = this.command;
        if (bArr3 != null) {
            System.arraycopy(bArr3, 0, bArr2, 12, bArr3.length);
        }
        return bArr2;
    }
}
