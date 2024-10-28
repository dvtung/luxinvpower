package com.nfcx.luxinvpower.global.custom.android.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import com.nfcx.luxinvpower.tool.InvTool;

/* loaded from: classes2.dex */
public class DayDatePickerDialog extends AbstractDatePickerDialog {
    public DayDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener, String str) {
        super(context, onDateSetListener, Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(5, 7)) - 1, Integer.parseInt(str.substring(8, 10)));
        setTitle(str);
    }

    @Override // android.app.DatePickerDialog, android.widget.DatePicker.OnDateChangedListener
    public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
        super.onDateChanged(datePicker, i, i2, i3);
        setTitle(i + "-" + InvTool.fillZero(i2 + 1) + "-" + InvTool.fillZero(i3));
    }
}
