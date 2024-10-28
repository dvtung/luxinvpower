package com.nfcx.luxinvpower.tool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.plant.Plant;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Tool {
    public static void alert(Activity activity, int i) {
        if (activity.isFinishing()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.phrase_message).setIcon(android.R.drawable.ic_dialog_info).setMessage(i).setNegativeButton(R.string.phrase_button_ok, (DialogInterface.OnClickListener) null);
        builder.show();
    }

    public static void alert(Activity activity, String str) {
        if (activity.isFinishing()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.phrase_message).setIcon(android.R.drawable.ic_dialog_info).setMessage(str).setNegativeButton(R.string.phrase_button_ok, (DialogInterface.OnClickListener) null);
        builder.show();
    }

    public static void alert(Activity activity, String str, DialogInterface.OnDismissListener onDismissListener) {
        if (activity.isFinishing()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.phrase_message).setIcon(android.R.drawable.ic_dialog_info).setMessage(str).setNegativeButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.tool.Tool$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog create = builder.create();
        create.setOnDismissListener(onDismissListener);
        create.show();
    }

    public static void alert(Activity activity, int i, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(activity).setMessage(i).setPositiveButton(R.string.phrase_button_ok, onClickListener).create().show();
    }

    public static void alert(Context context, String str, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context).setMessage(str).setPositiveButton(R.string.phrase_button_delete, onClickListener).setNegativeButton(R.string.phrase_button_cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    public static int versionToInt(String str) {
        String[] split = str.split("\\.");
        return (Integer.parseInt(split[0]) * 10000) + ((split.length > 1 ? Integer.parseInt(split[1]) : 0) * 100) + (split.length > 2 ? Integer.parseInt(split[2]) : 0);
    }

    public static boolean isValidIP(String str) {
        return str.matches("^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    }

    public static boolean isValidSubnetMask(String str) {
        try {
            String[] split = str.split("\\.");
            if (split.length != 4) {
                return false;
            }
            int i = 0;
            for (String str2 : split) {
                int parseInt = Integer.parseInt(str2);
                if (parseInt < 0 || parseInt > 255) {
                    return false;
                }
                i = (i << 8) + parseInt;
            }
            boolean z = false;
            for (int i2 = 31; i2 >= 0; i2--) {
                if (((1 << i2) & i) == 0) {
                    z = true;
                } else if (z) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static void alertNotInUiThread(final Activity activity, final String str) {
        if (activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.tool.Tool$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Tool.alert(activity, str);
            }
        });
    }

    public static void alertNotInUiThread(final Activity activity, final int i) {
        if (activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.tool.Tool$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                Tool.alert(activity, i);
            }
        });
    }

    public static long mac2Long(String str) {
        try {
            return Long.parseLong(str.replaceAll(":", ""), 16);
        } catch (Exception unused) {
            return -1L;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isEmptyNoTrim(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isEmail(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("^([a-z0-9A-Z]+[_|-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(str).matches();
    }

    public static boolean containInvalidCharacter(String str) {
        return str.indexOf(38) >= 0 || str.indexOf(39) >= 0 || str.indexOf(34) >= 0 || str.indexOf(60) >= 0 || str.indexOf(62) >= 0 || str.indexOf(44) >= 0 || str.indexOf(92) >= 0;
    }

    public static void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getTechSupportPrefix(Activity activity, JSONObject jSONObject, int i) throws JSONException {
        String string = jSONObject.getString("techInfoType" + i);
        return "WHATSAPP_ID".equals(string) ? "Whatsapp ID: " : "FACEBOOK_ID".equals(string) ? "Facebook ID: " : "PHONE_NUMBER".equals(string) ? activity.getString(R.string.page_register_text_tel_number) + ": " : "";
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0048: MOVE (r4 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:40:0x0048 */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Long getCRC32(java.io.File r9) {
        /*
            java.lang.String r0 = " error"
            java.lang.String r1 = "Get CRC32 for file "
            java.lang.String r2 = "LuxPower"
            java.util.zip.CRC32 r3 = new java.util.zip.CRC32
            r3.<init>()
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 java.io.FileNotFoundException -> L7c
            r5.<init>(r9)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51 java.io.FileNotFoundException -> L7c
            r6 = 8192(0x2000, float:1.148E-41)
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4b java.io.FileNotFoundException -> L4d
        L15:
            int r7 = r5.read(r6)     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4b java.io.FileNotFoundException -> L4d
            r8 = -1
            if (r7 == r8) goto L21
            r8 = 0
            r3.update(r6, r8, r7)     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4b java.io.FileNotFoundException -> L4d
            goto L15
        L21:
            long r6 = r3.getValue()     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4b java.io.FileNotFoundException -> L4d
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4b java.io.FileNotFoundException -> L4d
            r5.close()     // Catch: java.io.IOException -> L2d
            goto L46
        L2d:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            java.lang.String r9 = r9.getAbsolutePath()
            java.lang.StringBuilder r9 = r5.append(r9)
            java.lang.StringBuilder r9 = r9.append(r0)
            java.lang.String r9 = r9.toString()
            android.util.Log.e(r2, r9, r4)
        L46:
            return r3
        L47:
            r3 = move-exception
            r4 = r5
            goto Lba
        L4b:
            r3 = move-exception
            goto L53
        L4d:
            r3 = move-exception
            goto L7e
        L4f:
            r3 = move-exception
            goto Lba
        L51:
            r3 = move-exception
            r5 = r4
        L53:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L47
            r6.<init>()     // Catch: java.lang.Throwable -> L47
            java.lang.StringBuilder r6 = r6.append(r1)     // Catch: java.lang.Throwable -> L47
            java.lang.String r7 = r9.getAbsolutePath()     // Catch: java.lang.Throwable -> L47
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: java.lang.Throwable -> L47
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch: java.lang.Throwable -> L47
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L47
            android.util.Log.e(r2, r6, r3)     // Catch: java.lang.Throwable -> L47
            if (r5 == 0) goto Lb9
            r5.close()     // Catch: java.io.IOException -> L75
            goto Lb9
        L75:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            goto La6
        L7c:
            r3 = move-exception
            r5 = r4
        L7e:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L47
            r6.<init>()     // Catch: java.lang.Throwable -> L47
            java.lang.StringBuilder r6 = r6.append(r1)     // Catch: java.lang.Throwable -> L47
            java.lang.String r7 = r9.getAbsolutePath()     // Catch: java.lang.Throwable -> L47
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: java.lang.Throwable -> L47
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch: java.lang.Throwable -> L47
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L47
            android.util.Log.e(r2, r6, r3)     // Catch: java.lang.Throwable -> L47
            if (r5 == 0) goto Lb9
            r5.close()     // Catch: java.io.IOException -> La0
            goto Lb9
        La0:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
        La6:
            java.lang.String r9 = r9.getAbsolutePath()
            java.lang.StringBuilder r9 = r5.append(r9)
            java.lang.StringBuilder r9 = r9.append(r0)
            java.lang.String r9 = r9.toString()
            android.util.Log.e(r2, r9, r3)
        Lb9:
            return r4
        Lba:
            if (r4 == 0) goto Ld9
            r4.close()     // Catch: java.io.IOException -> Lc0
            goto Ld9
        Lc0:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            java.lang.String r9 = r9.getAbsolutePath()
            java.lang.StringBuilder r9 = r5.append(r9)
            java.lang.StringBuilder r9 = r9.append(r0)
            java.lang.String r9 = r9.toString()
            android.util.Log.e(r2, r9, r4)
        Ld9:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.tool.Tool.getCRC32(java.io.File):java.lang.Long");
    }

    public static String getTimezoneText(String str) {
        if (isEmpty(str)) {
            return "";
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1737953266:
                if (str.equals("WEST10")) {
                    c = 0;
                    break;
                }
                break;
            case -1737953265:
                if (str.equals("WEST11")) {
                    c = 1;
                    break;
                }
                break;
            case -1737953264:
                if (str.equals("WEST12")) {
                    c = 2;
                    break;
                }
                break;
            case 2750120:
                if (str.equals("ZERO")) {
                    c = 3;
                    break;
                }
                break;
            case 65741780:
                if (str.equals("EAST1")) {
                    c = 4;
                    break;
                }
                break;
            case 65741781:
                if (str.equals("EAST2")) {
                    c = 5;
                    break;
                }
                break;
            case 65741782:
                if (str.equals("EAST3")) {
                    c = 6;
                    break;
                }
                break;
            case 65741783:
                if (str.equals("EAST4")) {
                    c = 7;
                    break;
                }
                break;
            case 65741784:
                if (str.equals("EAST5")) {
                    c = '\b';
                    break;
                }
                break;
            case 65741785:
                if (str.equals("EAST6")) {
                    c = '\t';
                    break;
                }
                break;
            case 65741786:
                if (str.equals("EAST7")) {
                    c = '\n';
                    break;
                }
                break;
            case 65741787:
                if (str.equals("EAST8")) {
                    c = 11;
                    break;
                }
                break;
            case 65741788:
                if (str.equals("EAST9")) {
                    c = '\f';
                    break;
                }
                break;
            case 82484322:
                if (str.equals("WEST1")) {
                    c = '\r';
                    break;
                }
                break;
            case 82484323:
                if (str.equals("WEST2")) {
                    c = 14;
                    break;
                }
                break;
            case 82484324:
                if (str.equals("WEST3")) {
                    c = 15;
                    break;
                }
                break;
            case 82484325:
                if (str.equals("WEST4")) {
                    c = 16;
                    break;
                }
                break;
            case 82484326:
                if (str.equals("WEST5")) {
                    c = 17;
                    break;
                }
                break;
            case 82484327:
                if (str.equals("WEST6")) {
                    c = 18;
                    break;
                }
                break;
            case 82484328:
                if (str.equals("WEST7")) {
                    c = 19;
                    break;
                }
                break;
            case 82484329:
                if (str.equals("WEST8")) {
                    c = 20;
                    break;
                }
                break;
            case 82484330:
                if (str.equals("WEST9")) {
                    c = 21;
                    break;
                }
                break;
            case 2037995228:
                if (str.equals("EAST10")) {
                    c = 22;
                    break;
                }
                break;
            case 2037995229:
                if (str.equals("EAST11")) {
                    c = 23;
                    break;
                }
                break;
            case 2037995230:
                if (str.equals("EAST12")) {
                    c = 24;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "GMT -10";
            case 1:
                return "GMT -11";
            case 2:
                return "GMT -12";
            case 3:
                return "GMT 0";
            case 4:
                return "GMT +1";
            case 5:
                return "GMT +2";
            case 6:
                return "GMT +3";
            case 7:
                return "GMT +4";
            case '\b':
                return "GMT +5";
            case '\t':
                return "GMT +6";
            case '\n':
                return "GMT +7";
            case 11:
                return "GMT +8";
            case '\f':
                return "GMT +9";
            case '\r':
                return "GMT -1";
            case 14:
                return "GMT -2";
            case 15:
                return "GMT -3";
            case 16:
                return "GMT -4";
            case 17:
                return "GMT -5";
            case 18:
                return "GMT -6";
            case 19:
                return "GMT -7";
            case 20:
                return "GMT -8";
            case 21:
                return "GMT -9";
            case 22:
                return "GMT +10";
            case 23:
                return "GMT +11";
            case 24:
                return "GMT +12";
            default:
                return "";
        }
    }

    public static Inverter getInverterByJsonObj(JSONObject jSONObject, Plant plant) throws JSONException {
        Inverter inverter = new Inverter();
        inverter.setSerialNum(jSONObject.getString("serialNum"));
        inverter.setPlantId(Long.valueOf(plant.getPlantId()));
        inverter.setPhase(Integer.valueOf(jSONObject.getInt(TypedValues.CycleType.S_WAVE_PHASE)));
        inverter.setDeviceType(Integer.valueOf(jSONObject.getInt("deviceType")));
        inverter.setDtc(Integer.valueOf(jSONObject.getInt("dtc")));
        inverter.setSubDeviceType(Integer.valueOf(jSONObject.getInt("subDeviceType")));
        inverter.setAllowGenExercise(jSONObject.getBoolean("allowGenExercise"));
        inverter.setAllowExport2Grid(jSONObject.getBoolean("allowExport2Grid"));
        inverter.setWithbatteryData(Boolean.valueOf(jSONObject.getBoolean("withbatteryData")));
        inverter.setHardwareVersion(jSONObject.getInt("hardwareVersion"));
        inverter.setMachineType(jSONObject.getInt("machineType"));
        inverter.setProtocolVersion(Integer.valueOf(jSONObject.getInt("protocolVersion")));
        return inverter;
    }
}
