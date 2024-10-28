package com.nfcx.luxinvpower.global.custom.mpChart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;

/* loaded from: classes2.dex */
public class CustomLargeValueFormatter implements IValueFormatter, IAxisValueFormatter {
    private static final int MAX_LENGTH = 5;
    private static String[] SUFFIX = {"", "k", "m", "b", "t"};
    private DecimalFormat mFormat;
    private String mText;

    public int getDecimalDigits() {
        return 0;
    }

    public CustomLargeValueFormatter() {
        this.mText = "";
        this.mFormat = new DecimalFormat("###E00");
    }

    public CustomLargeValueFormatter(String str) {
        this();
        this.mText = str;
    }

    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return makePretty(f) + this.mText;
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return makePretty(f) + this.mText;
    }

    public void setAppendix(String str) {
        this.mText = str;
    }

    public void setSuffix(String[] strArr) {
        SUFFIX = strArr;
    }

    private String makePretty(double d) {
        String format = this.mFormat.format(d);
        String str = format.substring(0, format.length() - 3) + SUFFIX[Integer.valueOf(Character.getNumericValue(format.charAt(format.length() - 2)) + "" + Character.getNumericValue(format.charAt(format.length() - 1))).intValue() / 3];
        while (true) {
            if (str.length() <= 5 && !str.matches("[0-9]+\\.[a-z]")) {
                return str;
            }
            str = str.substring(0, str.length() - 2) + str.substring(str.length() - 1);
        }
    }
}
