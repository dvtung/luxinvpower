package com.nfcx.luxinvpower.tcp;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class TcpReadSerial extends ReadSerial {
    public TcpReadSerial(SerialBuffer serialBuffer, InputStream inputStream) {
        super(serialBuffer, inputStream);
    }

    @Override // com.nfcx.luxinvpower.tcp.ReadSerial, java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                int read = this.ComPort.read();
                if (read < 0) {
                    return;
                } else {
                    this.ComBuffer.putChar(read);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
