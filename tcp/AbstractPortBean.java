package com.nfcx.luxinvpower.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public abstract class AbstractPortBean implements IPortBean {
    protected ReadSerial RT;
    protected SerialBuffer SB;
    protected InputStream in;
    protected OutputStream out;

    @Override // com.nfcx.luxinvpower.tcp.IPortBean
    public String ReadPort(int i) {
        return this.SB.getMsg(i);
    }

    @Override // com.nfcx.luxinvpower.tcp.IPortBean
    public boolean WritePort(byte[] bArr) {
        try {
            this.SB.clearContent();
            this.out.write(bArr);
            this.out.flush();
            return true;
        } catch (IOException e) {
            TcpManager.getInstance().setConnected(false);
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.nfcx.luxinvpower.tcp.IPortBean
    public void WritePort(char[] cArr, int i) {
        try {
            this.SB.clearContent();
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) cArr[i2];
            }
            this.out.write(bArr);
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
