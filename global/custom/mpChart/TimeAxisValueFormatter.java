package com.nfcx.luxinvpower.global.custom.mpChart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.nfcx.luxinvpower.tool.InvTool;

/* loaded from: classes2.dex */
public class TimeAxisValueFormatter implements IAxisValueFormatter {
    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        int i = (int) f;
        return InvTool.fillZero(i / 3600) + ":" + InvTool.fillZero((i % 3600) / 60);
    }
}
