package com.nfcx.luxinvpower.view.wifi;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.messaging.Constants;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.connect.LocalConnect;
import com.nfcx.luxinvpower.connect.LocalConnectManager;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.bean.set.REMOTE_WRITE_TYPE;
import com.nfcx.luxinvpower.global.bean.set.RemoteWriteInfo;
import com.nfcx.luxinvpower.tool.ProTool;
import com.nfcx.luxinvpower.tool.Tool;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EWifiRemoteUpdateActivity extends Activity {
    public static EWifiRemoteUpdateActivity instance;
    private static String selectedFirmware;
    private Button eWifiRemoteUpdateButton;
    private Spinner eWifiRemoteUpdateSpinner;
    private LocalConnect localConnect;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ewifi_remote_update);
        instance = this;
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.EWifiRemoteUpdateActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EWifiRemoteUpdateActivity.instance.finish();
            }
        });
        this.localConnect = LocalConnectManager.getLocalConnect(getIntent().getStringExtra(Constants.LOCAL_CONNECT_TYPE));
        selectedFirmware = null;
        Spinner spinner = (Spinner) findViewById(R.id.eWifi_remoteUpdateSpinner);
        this.eWifiRemoteUpdateSpinner = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.wifi.EWifiRemoteUpdateActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                String unused = EWifiRemoteUpdateActivity.selectedFirmware = (String) EWifiRemoteUpdateActivity.this.eWifiRemoteUpdateSpinner.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                String unused = EWifiRemoteUpdateActivity.selectedFirmware = null;
            }
        });
        this.eWifiRemoteUpdateButton = (Button) findViewById(R.id.eWifi_remoteUpdateButton);
    }

    private void getAllFirmwares(final JSONArray jSONArray, final String str) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.EWifiRemoteUpdateActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EWifiRemoteUpdateActivity.this.m488x99d0c592(str, jSONArray);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getAllFirmwares$0$com-nfcx-luxinvpower-view-wifi-EWifiRemoteUpdateActivity, reason: not valid java name */
    public /* synthetic */ void m488x99d0c592(String str, JSONArray jSONArray) {
        try {
            ArrayList arrayList = new ArrayList();
            if (!Tool.isEmpty(ProTool.showData(str))) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    addFirmwareNameIfMatches(jSONArray.getJSONObject(i), str, arrayList);
                }
            }
            updateUI(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFirmwareNameIfMatches(JSONObject jSONObject, String str, List<String> list) throws JSONException {
        char charAt = str.charAt(0);
        String string = jSONObject.getString("datalogType");
        if ((charAt == 5 && "ESP_WIFI".equals(string)) || (charAt == 6 && "ESP_WIFI6".equals(string))) {
            list.add(jSONObject.getString("sourceName"));
        }
    }

    private void updateUI(final List<String> list) {
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.EWifiRemoteUpdateActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                EWifiRemoteUpdateActivity.this.m489x191a6e27(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateUI$1$com-nfcx-luxinvpower-view-wifi-EWifiRemoteUpdateActivity, reason: not valid java name */
    public /* synthetic */ void m489x191a6e27(List list) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.eWifiRemoteUpdateSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
    }

    public void clickUpdateFirmwareButton(View view) {
        if (selectedFirmware != null) {
            try {
                getVersionNumber(getSharedPreferences("userInfo", 0).getString("firmwareVersion", " "));
                getSelectedFirmwareVersionNumber(selectedFirmware);
                this.eWifiRemoteUpdateButton.setEnabled(false);
                runDatalogParamWrite(9, ("http://47.254.33.206:8083/resource/firmware/" + selectedFirmware).getBytes("ISO-8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private double getVersionNumber(String str) {
        try {
            return Double.parseDouble(str.replaceAll("[^0-9.]", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Utils.DOUBLE_EPSILON;
        }
    }

    private double getSelectedFirmwareVersionNumber(String str) {
        try {
            Matcher matcher = Pattern.compile("V(\\d+_\\d+)\\.bin").matcher(str);
            return matcher.find() ? Double.parseDouble(matcher.group(1).replace("_", ".")) : Utils.DOUBLE_EPSILON;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Utils.DOUBLE_EPSILON;
        }
    }

    private void runDatalogParamWrite(int i, byte[] bArr) {
        RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
        remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.DATALOG_PARAM);
        remoteWriteInfo.setDatalogParamIndex(Integer.valueOf(i));
        remoteWriteInfo.setDatalogParamValues(bArr);
        new WriteParamTask(this).execute(remoteWriteInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class WriteParamTask extends AsyncTask<RemoteWriteInfo, Void, JSONObject> {
        private EWifiRemoteUpdateActivity instance;
        private RemoteWriteInfo remoteWriteInfo;

        public WriteParamTask(EWifiRemoteUpdateActivity eWifiRemoteUpdateActivity) {
            this.instance = eWifiRemoteUpdateActivity;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(RemoteWriteInfo... remoteWriteInfoArr) {
            RemoteWriteInfo remoteWriteInfo = remoteWriteInfoArr[0];
            if (remoteWriteInfo != null && remoteWriteInfo.getRemoteWriteType() != null) {
                this.remoteWriteInfo = remoteWriteInfo;
                if (AnonymousClass3.$SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[remoteWriteInfo.getRemoteWriteType().ordinal()] == 1) {
                    if (remoteWriteInfo.getDatalogParamIndex() == null || remoteWriteInfo.getDatalogParamValues() == null) {
                        return this.instance.createFailureJSONObject("PARAM_EMPTY");
                    }
                    return this.instance.localConnect.writeDatalogParam(remoteWriteInfo.getDatalogParamIndex().intValue(), remoteWriteInfo.getDatalogParamValues()) ? this.instance.createSuccessJSONObject() : this.instance.createFailureJSONObject("FAILED");
                }
            }
            return this.instance.createFailureJSONObject("UNKNOWN_ERROR");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((WriteParamTask) jSONObject);
            try {
                if (jSONObject != null) {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            Tool.alert(this.instance, R.string.page_maintain_remote_set_result_unknown_error);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (jSONObject.getBoolean("success")) {
                        Tool.alert(this.instance, R.string.upgrade_succeed);
                    }
                }
                if (this.remoteWriteInfo != null && REMOTE_WRITE_TYPE.CONTROL.equals(this.remoteWriteInfo.getRemoteWriteType())) {
                    this.remoteWriteInfo.getFunctionToggleButton().setChecked(!this.remoteWriteInfo.getFunctionToggleButton().isChecked());
                }
                this.instance.toast(jSONObject);
            } finally {
                this.instance.eWifiRemoteUpdateButton.setEnabled(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.wifi.EWifiRemoteUpdateActivity$3, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE;

        static {
            int[] iArr = new int[REMOTE_WRITE_TYPE.values().length];
            $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE = iArr;
            try {
                iArr[REMOTE_WRITE_TYPE.DATALOG_PARAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject createFailureJSONObject(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("success", false);
            jSONObject.put("msg", str);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject createSuccessJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("success", true);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toast(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            Toast.makeText(this, R.string.phrase_toast_network_error, 1).show();
            return;
        }
        String string = jSONObject.getString("msg");
        string.hashCode();
        char c = 65535;
        switch (string.hashCode()) {
            case -1814651686:
                if (string.equals("DEVICE_OFFLINE")) {
                    c = 0;
                    break;
                }
                break;
            case -981622981:
                if (string.equals("ACTION_ERROR_UNDONE")) {
                    c = 1;
                    break;
                }
                break;
            case -556338971:
                if (string.equals("DATAFRAME_TIMEOUT")) {
                    c = 2;
                    break;
                }
                break;
            case -553504479:
                if (string.equals("INTEGER_FORMAT_ERROR")) {
                    c = 3;
                    break;
                }
                break;
            case -75347237:
                if (string.equals("PARAM_EMPTY")) {
                    c = 4;
                    break;
                }
                break;
            case -75196522:
                if (string.equals("PARAM_ERROR")) {
                    c = 5;
                    break;
                }
                break;
            case 154026365:
                if (string.equals("DATAFRAME_UNSEND")) {
                    c = 6;
                    break;
                }
                break;
            case 1027843992:
                if (string.equals("REMOTE_READ_ERROR")) {
                    c = 7;
                    break;
                }
                break;
            case 1099156916:
                if (string.equals("SERVER_HTTP_EXCEPTION")) {
                    c = '\b';
                    break;
                }
                break;
            case 1705806578:
                if (string.equals("REMOTE_SET_ERROR")) {
                    c = '\t';
                    break;
                }
                break;
            case 1939189941:
                if (string.equals("OUT_RANGE_ERROR")) {
                    c = '\n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Toast.makeText(this, R.string.page_maintain_remote_set_result_device_offline, 1).show();
                return;
            case 1:
                Toast.makeText(this, R.string.page_maintain_remote_set_result_set_undo, 1).show();
                return;
            case 2:
                Toast.makeText(this, R.string.page_maintain_remote_set_result_timeout, 1).show();
                return;
            case 3:
                Toast.makeText(this, R.string.page_maintain_remote_set_alert_param_should_int, 1).show();
                return;
            case 4:
                Toast.makeText(this, R.string.page_maintain_remote_set_alert_param_empty, 1).show();
                return;
            case 5:
                Toast.makeText(this, R.string.page_maintain_remote_set_result_param_error, 1).show();
                return;
            case 6:
                Toast.makeText(this, R.string.page_maintain_remote_set_result_command_not_send, 1).show();
                return;
            case 7:
            case '\t':
                Toast.makeText(this, getString(R.string.page_maintain_remote_set_result_failed) + " " + jSONObject.getInt("errorCode"), 1).show();
                return;
            case '\b':
                Toast.makeText(this, R.string.page_maintain_remote_set_result_server_exception, 1).show();
                return;
            case '\n':
                Toast.makeText(this, getString(R.string.page_maintain_remote_set_alert_param_out_range), 1).show();
                return;
            default:
                Toast.makeText(this, R.string.local_set_result_failed, 1).show();
                return;
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        handleIntent(getIntent(), getSharedPreferences("userInfo", 0).getString("firmwares", " "));
    }

    private void handleIntent(Intent intent, String str) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("datalogParam");
            this.eWifiRemoteUpdateButton.setText(instance.getString(R.string._1_s_update_firmware, new Object[]{intent.getStringExtra("firmwareVersion") + " - "}));
            try {
                instance.getAllFirmwares(new JSONObject(str).getJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE), stringExtra);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.eWifiRemoteUpdateSpinner.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_item, new ArrayList()));
    }
}
