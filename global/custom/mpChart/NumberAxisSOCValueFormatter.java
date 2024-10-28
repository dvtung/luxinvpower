package com.nfcx.luxinvpower.global.custom.mpChart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/* loaded from: classes2.dex */
public class NumberAxisSOCValueFormatter implements IAxisValueFormatter {
    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return ((double) f) > 100.0d ? "" : String.valueOf(f);
    }
}
