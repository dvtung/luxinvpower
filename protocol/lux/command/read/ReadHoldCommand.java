package com.nfcx.luxinvpower.protocol.lux.command.read;

import com.nfcx.luxinvpower.protocol.lux.DEVICE_FUNCTION;

/* loaded from: classes2.dex */
public class ReadHoldCommand extends AbstractQueryCommand {
    public ReadHoldCommand(String str, int i, int i2) {
        super(0, DEVICE_FUNCTION.R_HOLD, str, i, i2);
    }
}
