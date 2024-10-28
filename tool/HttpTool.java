package com.nfcx.luxinvpower.tool;

import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.common.net.HttpHeaders;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.view.login.LoginActivity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes2.dex */
public class HttpTool {
    private static String COOKIE_SESSION_NAME = "JSESSIONID";
    private static String JSESSIONID;
    public static final Map<String, String> JSON_CONTENT_TYPE_HEADERS;
    private static long lastTimeGetSession;

    static {
        HashMap hashMap = new HashMap();
        JSON_CONTENT_TYPE_HEADERS = hashMap;
        hashMap.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
    }

    private static synchronized boolean reLoginWhenSessionLost() {
        synchronized (HttpTool.class) {
            if (System.currentTimeMillis() - lastTimeGetSession < DeviceOrientationRequest.OUTPUT_PERIOD_FAST) {
                return true;
            }
            if (!Tool.isEmpty(LoginActivity.usernameForLogin) && !Tool.isEmpty(LoginActivity.passwordForLogin)) {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("account", LoginActivity.usernameForLogin);
                    hashMap.put("password", LoginActivity.passwordForLogin);
                    hashMap.put("language", GlobalInfo.getInstance().getLanguage());
                    JSONObject postJson = postJson(GlobalInfo.getInstance().getBaseUrl() + "api/login", hashMap);
                    if (postJson != null && postJson.getBoolean("success")) {
                        lastTimeGetSession = System.currentTimeMillis();
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    public static final String postString(String str, Map<String, String> map, Map<String, String> map2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (map != null && !map.isEmpty()) {
            for (String str2 : map.keySet()) {
                if (stringBuffer.length() > 0) {
                    stringBuffer.append("&");
                }
                stringBuffer.append(str2).append("=").append(map.get(str2));
            }
        }
        return doPost(str, stringBuffer, map2);
    }

    public static final String postString(String str, Map<String, String> map, String str2) throws Exception {
        return new String(postString(str, map, (Map<String, String>) null).getBytes(str2));
    }

    public static final JSONObject postJson(String str, Map<String, String> map) {
        return postJson(str, map, null);
    }

    public static final JSONObject postJson(String str, Map<String, String> map, Map<String, String> map2) {
        try {
            String postString = postString(str, map, map2);
            LogUtils.writeLog("LuxPower - postJson responseText = " + postString);
            if (Tool.isEmpty(postString)) {
                return null;
            }
            return (JSONObject) new JSONTokener(postString).nextValue();
        } catch (Exception e) {
            LogUtils.writeLog(e);
            return null;
        }
    }

    public static final JSONArray postJsonArray(String str, Map<String, String> map) {
        return postJsonArray(str, map, null);
    }

    public static final JSONArray postJsonArray(String str, Map<String, String> map, Map<String, String> map2) {
        try {
            String postString = postString(str, map, map2);
            System.out.println("LuxPowerresponseText = " + postString);
            if (Tool.isEmpty(postString)) {
                return null;
            }
            return (JSONArray) new JSONTokener(postString).nextValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final JSONObject multiPartPostJson(String str, String str2) throws Exception {
        return multiPartPostJson(str, str2, JSON_CONTENT_TYPE_HEADERS);
    }

    public static final JSONObject multiPartPostJson(String str, String str2, Map<String, String> map) throws Exception {
        return (JSONObject) new JSONTokener(multiPartPostString(str, str2, map)).nextValue();
    }

    public static final String multiPartPostString(String str, String str2, Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        return doPost(str, stringBuffer, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0201 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x022d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0223 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0219 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01b0 A[Catch: all -> 0x01e9, Exception -> 0x01ed, LOOP:3: B:89:0x01aa->B:91:0x01b0, LOOP_END, TryCatch #23 {Exception -> 0x01ed, all -> 0x01e9, blocks: (B:88:0x01a5, B:89:0x01aa, B:91:0x01b0, B:93:0x01b4), top: B:87:0x01a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01b4 A[EDGE_INSN: B:92:0x01b4->B:93:0x01b4 BREAK  A[LOOP:3: B:89:0x01aa->B:91:0x01b0], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x020b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v16, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String doPost(java.lang.String r8, java.lang.StringBuffer r9, java.util.Map<java.lang.String, java.lang.String> r10) {
        /*
            Method dump skipped, instructions count: 566
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.tool.HttpTool.doPost(java.lang.String, java.lang.StringBuffer, java.util.Map):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0142 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0138 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String getString(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.tool.HttpTool.getString(java.lang.String):java.lang.String");
    }

    public static final JSONObject getJson(String str) throws Exception {
        try {
            String string = getString(str);
            System.out.println("responseText = " + string);
            if (Tool.isEmpty(string)) {
                return null;
            }
            return (JSONObject) new JSONTokener(string).nextValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final JSONObject getJson(String str, String str2) throws Exception {
        return (JSONObject) new JSONTokener(new String(getString(str).getBytes(str2))).nextValue();
    }

    public static String getCurrentSessionId() {
        return JSESSIONID;
    }
}
