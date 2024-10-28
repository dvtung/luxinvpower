package com.nfcx.luxinvpower.global.custom.android.dialog;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

/* loaded from: classes2.dex */
public class YearOnDateSetListener implements DatePickerDialog.OnDateSetListener {
    private EditText editText;

    public YearOnDateSetListener(EditText editText) {
        this.editText = editText;
    }

    @Override // android.app.DatePickerDialog.OnDateSetListener
    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        this.editText.setText(String.valueOf(i));
    }
}
