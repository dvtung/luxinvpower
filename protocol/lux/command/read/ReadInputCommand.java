package com.nfcx.luxinvpower.protocol.lux.command.read;

import com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION;

/* loaded from: classes2.dex */
public class ReadInputCommand extends AbstractQueryCommand {
    public ReadInputCommand(String str, int i, int i2) {
        super(0, DEVICE_FUNCTION.R_INPUT, str, i, i2);
    }
}
