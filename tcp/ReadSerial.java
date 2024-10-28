package com.nfcx.luxinvpower.tcp;

import com.nfcx.luxinvpower.tool.Tool;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class ReadSerial extends Thread {
    protected SerialBuffer ComBuffer;
    protected InputStream ComPort;

    public ReadSerial(SerialBuffer serialBuffer, InputStream inputStream) {
        this.ComBuffer = serialBuffer;
        this.ComPort = inputStream;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                int read = this.ComPort.read();
                if (read < 0) {
                    Tool.sleep(50L);
                } else {
                    this.ComBuffer.putChar(read);
                }
            } catch (IOException e) {
                TcpManager.getInstance().setConnected(false);
                e.printStackTrace();
                return;
            }
        }
    }
}
