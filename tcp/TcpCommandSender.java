package com.nfcx.luxinvpower.tcp;

import java.net.Socket;

/* loaded from: classes2.dex */
public class TcpCommandSender extends AbstractCommandSender {
    public TcpCommandSender(Socket socket) {
        this.portBean = new TcpBean(socket);
    }

    @Override // com.nfcx.luxinvpower.tcp.CommandSender
    public boolean startMonitor() {
        return this.portBean.initialize(this);
    }
}
