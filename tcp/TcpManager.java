package com.nfcx.luxinvpower.tcp;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.nfcx.luxinvpower.connect.AbstractLocalConnect;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.DataFrame;
import com.nfcx.luxinvpower.version.Version;
import java.net.InetSocketAddress;
import java.net.Socket;

/* loaded from: classes2.dex */
public class TcpManager extends AbstractLocalConnect {
    public static final int SERVER_PORT = 8000;
    private static final TcpManager tcpManager = new TcpManager();
    private CommandSender commandSender;

    private TcpManager() {
        super(Constants.LOCAL_CONNECT_TYPE_TCP);
    }

    public static TcpManager getInstance() {
        return tcpManager;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect
    public boolean initialize(boolean z) {
        try {
            if (!this.connected) {
                System.out.println("LuxPower, TcpManager initialize...");
                this.inverter = null;
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(Version.LOCAL_TCP_IP, SERVER_PORT), PathInterpolatorCompat.MAX_NUM_POINTS);
                TcpCommandSender tcpCommandSender = new TcpCommandSender(socket);
                this.commandSender = tcpCommandSender;
                if (tcpCommandSender.startMonitor()) {
                    this.connected = true;
                }
            }
            return this.connected;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect, com.nfcx.luxinvpower.tcp.CommandSender
    public String sendCommand(String str, DataFrame dataFrame) {
        CommandSender commandSender = this.commandSender;
        if (commandSender != null) {
            return commandSender.sendCommand(str, dataFrame);
        }
        return null;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect, com.nfcx.luxinvpower.tcp.CommandSender
    public String sendCommand(String str, DataFrame dataFrame, int i) {
        CommandSender commandSender = this.commandSender;
        if (commandSender != null) {
            return commandSender.sendCommand(str, dataFrame, i);
        }
        return null;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect, com.nfcx.luxinvpower.tcp.CommandSender
    public boolean sendPureCommand(DataFrame dataFrame) {
        CommandSender commandSender = this.commandSender;
        if (commandSender != null) {
            return commandSender.sendPureCommand(dataFrame);
        }
        return false;
    }

    public void setConnected(boolean z) {
        System.out.println("LuxPowerTcpManager set connected = " + z);
        this.connected = z;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect, com.nfcx.luxinvpower.tcp.CommandSender
    public String getCommandWaitResult(String str) {
        CommandSender commandSender = this.commandSender;
        if (commandSender != null) {
            return commandSender.getCommandWaitResult(str);
        }
        return null;
    }

    @Override // com.nfcx.luxinvpower.connect.LocalConnect, com.nfcx.luxinvpower.tcp.CommandSender
    public void close() {
        System.out.println("LuxPowerTcpManager close...");
        this.connected = false;
        CommandSender commandSender = this.commandSender;
        if (commandSender != null) {
            commandSender.close();
        }
    }
}
