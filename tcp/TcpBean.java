package com.nfcx.luxinvpower.tcp;

import java.io.IOException;
import java.net.Socket;

/* loaded from: classes2.dex */
public class TcpBean extends AbstractPortBean {
    private Socket socket;

    public TcpBean(Socket socket) {
        this.socket = socket;
    }

    @Override // com.nfcx.luxinvpower.tcp.IPortBean
    public boolean initialize(CommandSender commandSender) {
        Socket socket = this.socket;
        if (socket == null) {
            return false;
        }
        try {
            this.in = socket.getInputStream();
            this.out = this.socket.getOutputStream();
            this.SB = new TcpAnalyzer(commandSender);
            this.RT = new TcpReadSerial(this.SB, this.in);
            this.RT.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.nfcx.luxinvpower.tcp.IPortBean
    public void closePort() {
        try {
            if (this.in != null) {
                this.in.close();
            }
            if (this.out != null) {
                this.out.close();
            }
            Socket socket = this.socket;
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
