package com.nfcx.luxinvpower.global.custom.android.dialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

/* loaded from: classes2.dex */
public class CustomTimePickerDialog extends TimePickerDialog {
    public CustomTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener, String str) {
        super(context, onTimeSetListener, Integer.parseInt(str.substring(0, 2)), Integer.parseInt(str.substring(3, 5)), true);
    }

    @Override // android.app.TimePickerDialog, android.widget.TimePicker.OnTimeChangedListener
    public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        super.onTimeChanged(timePicker, i, i2);
    }
}
