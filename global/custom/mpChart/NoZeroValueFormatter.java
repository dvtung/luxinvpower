package com.nfcx.luxinvpower.global.custom.mpChart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes2.dex */
public class NoZeroValueFormatter implements IValueFormatter {
    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return f > 0.0f ? String.valueOf(f) : "";
    }
}
