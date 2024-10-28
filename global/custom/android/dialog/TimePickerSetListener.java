package com.nfcx.luxinvpower.global.custom.android.dialog;

import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TimePicker;
import com.nfcx.luxinvpower.tool.InvTool;

/* loaded from: classes2.dex */
public class TimePickerSetListener implements TimePickerDialog.OnTimeSetListener {
    private EditText editText;

    public TimePickerSetListener(EditText editText) {
        this.editText = editText;
    }

    @Override // android.app.TimePickerDialog.OnTimeSetListener
    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        this.editText.setText(InvTool.fillZero(i) + ":" + InvTool.fillZero(i2));
    }
}
