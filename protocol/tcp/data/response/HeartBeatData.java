package com.nfcx.luxinvpower.protocol.tcp.data.response;

import com.nfcx.luxinvpower.protocol.tcp.data.Data;
import com.nfcx.luxinvpower.tool.ProTool;

/* loaded from: classes2.dex */
public class HeartBeatData implements Data {
    private int datalogType;
    private String serialNum;

    public HeartBeatData(String str, int i) {
        this.serialNum = str;
        this.datalogType = i;
    }

    @Override // com.nfcx.luxinvpower.protocol.tcp.data.Data
    public byte[] getFrame() {
        byte[] bArr = new byte[11];
        ProTool.convertAsciiString2Byte(bArr, 0, this.serialNum);
        bArr[10] = (byte) this.datalogType;
        return bArr;
    }
}
