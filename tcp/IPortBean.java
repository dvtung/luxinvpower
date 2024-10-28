package com.nfcx.luxinvpower.tcp;

/* loaded from: classes2.dex */
public interface IPortBean {
    String ReadPort(int i);

    void WritePort(char[] cArr, int i);

    boolean WritePort(byte[] bArr);

    void closePort();

    boolean initialize(CommandSender commandSender);
}
