package com.nfcx.luxinvpower.tool;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class InvTool {
    public static final String DEVICE_TYPE_DATALOG = "DATALOG";
    public static final String DEVICE_TYPE_INVERTER = "INVERTER";
    public static final String STATUS_ERROR = "ERROR";
    public static final String STATUS_LOST = "LOST";
    public static final String STATUS_NORMAL = "NORMAL";
    public static final String STATUS_UNKNOWN = "UNKNOWN";
    public static final String STATUS_WAITING = "WAITING";
    public static final String STATUS_WARNING = "WARNING";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat shortDateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static synchronized Date parseDateTime(String str) {
        Date date;
        synchronized (InvTool.class) {
            date = null;
            try {
                if (!Tool.isEmpty(str)) {
                    date = dateTimeFormat.parse(str);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return date;
    }

    public static synchronized String formatDate(Date date) {
        String format;
        synchronized (InvTool.class) {
            format = dateFormat.format(date);
        }
        return format;
    }

    public static synchronized String formatTime(Date date) {
        String format;
        synchronized (InvTool.class) {
            format = timeFormat.format(date);
        }
        return format;
    }

    public static synchronized String formatDateTime(Date date) {
        String format;
        synchronized (InvTool.class) {
            format = dateTimeFormat.format(date);
        }
        return format;
    }

    public static synchronized String formatShortDateTime(Date date) {
        String format;
        synchronized (InvTool.class) {
            format = shortDateTimeFormat.format(date);
        }
        return format;
    }

    public static synchronized float formatFloat(float f, int i) {
        float floatValue;
        synchronized (InvTool.class) {
            floatValue = Float.valueOf(formatData(f, i)).floatValue();
        }
        return floatValue;
    }

    public static String formatData(double d) {
        return formatData(d, 1);
    }

    public static String formatData(double d, int i) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.CHINA);
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumFractionDigits(i);
        return numberFormat.format(d);
    }

    public static String fillZero(int i) {
        if (i >= 0 && i <= 9) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

    public static String formatDataText10(long j) {
        return formatData(j / 10.0d);
    }
}
