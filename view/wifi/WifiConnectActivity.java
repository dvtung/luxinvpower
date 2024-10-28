package com.nfcx.luxinvpower.view.wifi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.github.mikephil.charting.utils.Utils;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.bean.set.REMOTE_WRITE_TYPE;
import com.nfcx.luxinvpower.global.bean.set.RemoteWriteInfo;
import com.nfcx.luxinvpower.protocol.tcp.DataFrameFactory;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.DataFrame;
import com.nfcx.luxinvpower.tcp.TcpManager;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.ble.BleConnectActivity;
import com.nfcx.luxinvpower.view.wifi.WifiConnectActivity;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class WifiConnectActivity extends Activity {
    public static WifiConnectActivity instance;
    String datalogParamData;
    private Button dongleConnectParamsButton;
    private Button eWifiRemoteUpdateButton;
    String firmwareVersion;
    private boolean fromLogin;
    private String newVersionCode;
    private EditText passwordEditView;
    private ConstraintLayout serverIpLayout;
    private Spinner serverIpSpinner;
    private Button setServerIpButton;
    private EditText ssidEditView;
    private Spinner ssidSpinner;
    private Button tcpActionButton;
    private TcpManager tcpManager = TcpManager.getInstance();
    private TextView tcpResultTextView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wifi_connect);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WifiConnectActivity.instance.finish();
            }
        });
        this.ssidEditView = (EditText) findViewById(R.id.wifi_connect_ssid_editView);
        this.ssidSpinner = (Spinner) findViewById(R.id.wifi_connect_ssid_spinner);
        this.passwordEditView = (EditText) findViewById(R.id.wifi_connect_password_editView);
        this.tcpResultTextView = (TextView) findViewById(R.id.wifi_tcp_result_textView);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.wifi_connect_serverIpLayout);
        this.serverIpLayout = constraintLayout;
        constraintLayout.setVisibility(8);
        this.serverIpSpinner = (Spinner) findViewById(R.id.wifi_connect_serverIpSpinner);
        this.serverIpSpinner.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_item, new ArrayList()));
        Button button = (Button) findViewById(R.id.wifi_connect_setServerIpButton);
        this.setServerIpButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) WifiConnectActivity.this.serverIpSpinner.getSelectedItem();
                if (property != null) {
                    try {
                        WifiConnectActivity.this.setServerIpButton.setEnabled(false);
                        WifiConnectActivity.this.runDatalogParamWrite(6, property.getName().getBytes("ISO-8859-1"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Button button2 = (Button) findViewById(R.id.eWifi_remoteUpdate_button);
        this.eWifiRemoteUpdateButton = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(WifiConnectActivity.instance, (Class<?>) EWifiRemoteUpdateActivity.class);
                intent.putExtra("firmwareVersion", WifiConnectActivity.this.firmwareVersion);
                intent.putExtra("datalogParam", WifiConnectActivity.this.datalogParamData);
                WifiConnectActivity.this.startActivity(intent);
            }
        });
        Button button3 = (Button) findViewById(R.id.Dongle_Connect_Params_button);
        this.dongleConnectParamsButton = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(WifiConnectActivity.instance, (Class<?>) DongleConnectActivity.class);
                intent.putExtra(Constants.LOCAL_CONNECT_TYPE, Constants.LOCAL_CONNECT_TYPE_TCP);
                WifiConnectActivity.this.startActivity(intent);
            }
        });
        Button button4 = (Button) findViewById(R.id.wifi_tcp_action_button);
        this.tcpActionButton = button4;
        button4.setOnClickListener(new AnonymousClass5());
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                WifiConnectActivity.this.m493x8d118b73();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity$5, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass5 implements View.OnClickListener {
        AnonymousClass5() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WifiConnectActivity.this.tcpResultTextView.setVisibility(4);
            final String ssid = WifiConnectActivity.this.getSsid();
            if (Tool.isEmpty(ssid) || "unknown ssid".equals(ssid)) {
                WifiConnectActivity.this.tcpResultTextView.setVisibility(0);
                WifiConnectActivity.this.tcpResultTextView.setText(R.string.wifi_connect_ssid_invalid);
                return;
            }
            final String trim = WifiConnectActivity.this.passwordEditView.getText().toString().trim();
            if (Tool.isEmpty(trim)) {
                trim = "";
            }
            WifiConnectActivity.this.tcpActionButton.setText(R.string.wifi_connect_btn_sending);
            WifiConnectActivity.this.tcpActionButton.setEnabled(false);
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WifiConnectActivity.AnonymousClass5.this.m494xc39d25d3(ssid, trim);
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-nfcx-luxinvpower-view-wifi-WifiConnectActivity$5, reason: not valid java name */
        public /* synthetic */ void m494xc39d25d3(String str, String str2) {
            if (!WifiConnectActivity.this.tcpManager.initialize(true)) {
                WifiConnectActivity.this.closeTcpActionAtUiThread(R.string.phrase_toast_local_connect_error);
                return;
            }
            WifiInfo connectionInfo = ((WifiManager) WifiConnectActivity.this.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            DataFrame dataFrame = null;
            String ssid = connectionInfo != null ? connectionInfo.getSSID() : null;
            if (ssid == null || ssid.length() != 12) {
                WifiConnectActivity.this.tcpManager.setDatalogSn(Constants.DEFAULT_DATALOG_SN);
            } else {
                WifiConnectActivity.this.tcpManager.setDatalogSn(ssid.substring(1, 11));
            }
            try {
                dataFrame = DataFrameFactory.createWriteDatalogParamDataFrame(WifiConnectActivity.this.tcpManager.getTcpProtocol(), WifiConnectActivity.this.tcpManager.getDatalogSn(), 4, (str + "," + str2).getBytes("ISO-8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String sendCommand = WifiConnectActivity.this.tcpManager.sendCommand("write_datalog_4", dataFrame);
            if (!Tool.isEmpty(sendCommand) && sendCommand.length() == 21 && sendCommand.charAt(20) == 0) {
                WifiConnectActivity.this.closeTcpActionAtUiThread(R.string.wifi_connect_tcp_set_success_reboot);
            } else {
                WifiConnectActivity.this.closeTcpActionAtUiThread(R.string.wifi_connect_write_tcp_failed);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$3$com-nfcx-luxinvpower-view-wifi-WifiConnectActivity, reason: not valid java name */
    public /* synthetic */ void m493x8d118b73() {
        if (this.tcpManager.initialize(true)) {
            try {
                try {
                    this.tcpManager.setDatalogSn(Constants.DEFAULT_DATALOG_SN);
                    String readDatalogParam = readDatalogParam(5);
                    if (!Tool.isEmpty(readDatalogParam)) {
                        final ArrayList arrayList = new ArrayList();
                        for (String str : readDatalogParam.split(";")) {
                            if (!Tool.isEmpty(str)) {
                                arrayList.add(new Property(str, str));
                            }
                        }
                        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                WifiConnectActivity.this.m490x387bc56(arrayList);
                            }
                        });
                    }
                    if (!Constants.CLUSTER_GROUP_INDIA.equals(GlobalInfo.getInstance().getCurrentClusterGroup())) {
                        final String readDatalogParam2 = readDatalogParam(6);
                        if (!Tool.isEmpty(readDatalogParam2) && Constants.validServerIndexMap.containsKey(readDatalogParam2)) {
                            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    WifiConnectActivity.this.m491x316056b5(readDatalogParam2);
                                }
                            });
                        }
                    }
                    this.firmwareVersion = readDatalogParam(7);
                    this.datalogParamData = readDatalogParam(11);
                    System.out.println("datalogParamData == " + this.datalogParamData.charAt(0));
                    if (!Tool.isEmptyNoTrim(this.datalogParamData) && (this.datalogParamData.charAt(0) == 5 || this.datalogParamData.charAt(0) == 6)) {
                        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                WifiConnectActivity.this.m492x5f38f114();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                this.tcpManager.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-nfcx-luxinvpower-view-wifi-WifiConnectActivity, reason: not valid java name */
    public /* synthetic */ void m490x387bc56(List list) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.ssidSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        if (list.size() > 0) {
            this.ssidEditView.setVisibility(8);
            this.ssidSpinner.setVisibility(0);
        } else {
            this.ssidSpinner.setVisibility(8);
            this.ssidEditView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-nfcx-luxinvpower-view-wifi-WifiConnectActivity, reason: not valid java name */
    public /* synthetic */ void m491x316056b5(String str) {
        this.serverIpLayout.setVisibility(0);
        ArrayAdapter arrayAdapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getFirstClusterServers(this));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.serverIpSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        Integer num = Constants.validServerIndexMap.get(str);
        if (num != null) {
            this.serverIpSpinner.setSelection(num.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$2$com-nfcx-luxinvpower-view-wifi-WifiConnectActivity, reason: not valid java name */
    public /* synthetic */ void m492x5f38f114() {
        this.dongleConnectParamsButton.setVisibility(0);
        this.eWifiRemoteUpdateButton.setVisibility(0);
        this.eWifiRemoteUpdateButton.setText(instance.getString(R.string._1_s_update_firmware, new Object[]{this.firmwareVersion + " - "}));
    }

    private double getVersionNumber(String str) {
        try {
            return Double.parseDouble(str.replaceAll("[^0-9.]", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Utils.DOUBLE_EPSILON;
        }
    }

    public String readDatalogParam(int i) {
        if (!this.tcpManager.initialize(true) || Tool.isEmpty(this.tcpManager.getDatalogSn())) {
            return null;
        }
        String sendCommand = this.tcpManager.sendCommand("read_datalog_" + i, DataFrameFactory.createReadDatalogParamDataFrame(this.tcpManager.getTcpProtocol(), this.tcpManager.getDatalogSn(), i), 10);
        if (Tool.isEmpty(sendCommand) || sendCommand.length() <= 22) {
            return null;
        }
        return sendCommand.substring(22);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSsid() {
        if (this.ssidEditView.getVisibility() == 0) {
            return this.ssidEditView.getText().toString().trim();
        }
        Property property = (Property) this.ssidSpinner.getSelectedItem();
        return property != null ? property.getName() : "unknown ssid";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeTcpActionAtUiThread(final int i) {
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity.6
            @Override // java.lang.Runnable
            public void run() {
                WifiConnectActivity.this.tcpResultTextView.setVisibility(0);
                WifiConnectActivity.this.tcpResultTextView.setText(i);
                WifiConnectActivity.this.tcpActionButton.setText(R.string.wifi_tcp_btn_connect);
                WifiConnectActivity.this.tcpActionButton.setEnabled(true);
                WifiConnectActivity.this.tcpManager.close();
            }
        });
    }

    public void clickConnectFromBrowserButton(View view) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("http://10.10.10.1"));
        intent.setAction("android.intent.action.VIEW");
        startActivity(intent);
    }

    public void clickSwitchSsidModeButton(View view) {
        if (this.ssidSpinner.getVisibility() == 8) {
            this.ssidEditView.setVisibility(8);
            this.ssidSpinner.setVisibility(0);
        } else {
            this.ssidSpinner.setVisibility(8);
            this.ssidEditView.setVisibility(0);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.tcpManager.close();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("fromLogin", false);
            this.fromLogin = booleanExtra;
            if (booleanExtra) {
                this.newVersionCode = intent.getStringExtra("newVersionCode");
                System.out.println("newVersionCode == " + this.newVersionCode);
            }
        }
    }

    public void clickBleConnectButton(View view) {
        Intent intent = new Intent(this, (Class<?>) BleConnectActivity.class);
        intent.putExtra(TypedValues.AttributesType.S_TARGET, Constants.BLUETOOTH_TARGET_WIFI_CONFIG);
        intent.putExtra("ssid", getSsid());
        intent.putExtra("password", this.passwordEditView.getText());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runDatalogParamWrite(int i, byte[] bArr) {
        RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
        remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.DATALOG_PARAM);
        remoteWriteInfo.setDatalogParamIndex(Integer.valueOf(i));
        remoteWriteInfo.setDatalogParamValues(bArr);
        new WriteParamTask(this).execute(remoteWriteInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class WriteParamTask extends AsyncTask<RemoteWriteInfo, Void, JSONObject> {
        private WifiConnectActivity instance;
        private RemoteWriteInfo remoteWriteInfo;

        public WriteParamTask(WifiConnectActivity wifiConnectActivity) {
            this.instance = wifiConnectActivity;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(RemoteWriteInfo... remoteWriteInfoArr) {
            RemoteWriteInfo remoteWriteInfo = remoteWriteInfoArr[0];
            if (remoteWriteInfo != null && remoteWriteInfo.getRemoteWriteType() != null) {
                this.remoteWriteInfo = remoteWriteInfo;
                if (AnonymousClass7.$SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[remoteWriteInfo.getRemoteWriteType().ordinal()] == 1) {
                    if (remoteWriteInfo.getDatalogParamIndex() == null || remoteWriteInfo.getDatalogParamValues() == null) {
                        return this.instance.createFailureJSONObject("PARAM_EMPTY");
                    }
                    return this.instance.writeDatalogParam(remoteWriteInfo.getDatalogParamIndex().intValue(), remoteWriteInfo.getDatalogParamValues()) ? this.instance.createSuccessJSONObject() : this.instance.createFailureJSONObject("FAILED");
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
                        Tool.alert(this.instance, R.string.local_set_result_success);
                    }
                }
                if (this.remoteWriteInfo != null && REMOTE_WRITE_TYPE.CONTROL.equals(this.remoteWriteInfo.getRemoteWriteType())) {
                    this.remoteWriteInfo.getFunctionToggleButton().setChecked(!this.remoteWriteInfo.getFunctionToggleButton().isChecked());
                }
                this.instance.toast(jSONObject);
            } finally {
                this.instance.setServerIpButton.setEnabled(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.wifi.WifiConnectActivity$7, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass7 {
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

    public boolean writeDatalogParam(int i, byte[] bArr) {
        if (this.tcpManager.initialize(true) && !Tool.isEmpty(this.tcpManager.getDatalogSn())) {
            String sendCommand = this.tcpManager.sendCommand("write_datalog_" + i, DataFrameFactory.createWriteDatalogParamDataFrame(this.tcpManager.getTcpProtocol(), this.tcpManager.getDatalogSn(), i, bArr));
            return !Tool.isEmpty(sendCommand) && sendCommand.length() == 21 && sendCommand.charAt(20) == 0;
        }
        return false;
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
}
