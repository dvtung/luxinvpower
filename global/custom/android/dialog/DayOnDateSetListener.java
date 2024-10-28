package com.nfcx.luxinvpower.global.custom.android.dialog;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import com.nfcx.luxinvpower.tool.InvTool;

/* loaded from: classes2.dex */
public class DayOnDateSetListener implements DatePickerDialog.OnDateSetListener {
    private EditText editText;

    public DayOnDateSetListener(EditText editText) {
        this.editText = editText;
    }

    @Override // android.app.DatePickerDialog.OnDateSetListener
    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        this.editText.setText(i + "-" + InvTool.fillZero(i2 + 1) + "-" + InvTool.fillZero(i3));
    }
}
