package com.nfcx.luxinvpower.tcp;

import com.nfcx.luxinvpower.protocol.tcp.dataframe.DataFrame;

/* loaded from: classes2.dex */
public interface CommandSender {
    void close();

    String getCommandWaitResult(String str);

    void putCommandWaitMap(String str, String str2);

    String sendCommand(String str, DataFrame dataFrame);

    String sendCommand(String str, DataFrame dataFrame, int i);

    boolean sendPureCommand(DataFrame dataFrame);

    boolean startMonitor();
}
