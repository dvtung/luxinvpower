package com.nfcx.luxinvpower.view.ble;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.connect.LocalConnect;
import com.nfcx.luxinvpower.connect.LocalConnectManager;
import com.nfcx.luxinvpower.connect.ble.BleAction;
import com.nfcx.luxinvpower.connect.ble.BluetoothLocalConnect;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.protocol.tcp.DataFrameFactory;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL;
import com.nfcx.luxinvpower.tool.ProTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.ble.BlufiActivity;
import com.nfcx.luxinvpower.view.wifi.DongleConnectActivity;
import com.nfcx.luxinvpower.view.wifi.EWifiRemoteUpdateActivity;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class BlufiActivity extends Activity implements BleAction {
    public static BlufiActivity instance;
    private byte[] dataReceived;
    private String datalogParam;
    private String datalogSn;
    private Button dongleConnectParamsButton;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private String firmwareVersion;
    private LocalConnect localConnect;
    private EditText passwordEditView;
    private Spinner serverIpSpinner;
    private Button setServerIpButton;
    private EditText ssidEditView;
    private Button tcpActionButton;
    private TextView tcpResultTextView;
    private Button test04Button;
    private TextView titleTextView;
    private Button updateFirmwareButton;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ble_fi);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        this.titleTextView = (TextView) findViewById(R.id.titleTextView);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) getIntent().getParcelableExtra(Constants.KEY_BLE_DEVICE);
        this.titleTextView.setText(bluetoothDevice.getName() == null ? getString(R.string.string_unknown) : bluetoothDevice.getName());
        String trim = this.titleTextView.getText().toString().trim();
        this.datalogSn = trim;
        if (trim == null || trim.length() != 10) {
            this.datalogSn = Constants.DEFAULT_DATALOG_SN;
        }
        instance = this;
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BlufiActivity.instance.finish();
            }
        });
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("ssid");
        String stringExtra2 = intent.getStringExtra("password");
        EditText editText = (EditText) findViewById(R.id.wifi_connect_ssid_editView);
        this.ssidEditView = editText;
        if (Tool.isEmpty(stringExtra)) {
            stringExtra = "";
        }
        editText.setText(stringExtra);
        EditText editText2 = (EditText) findViewById(R.id.wifi_connect_password_editView);
        this.passwordEditView = editText2;
        if (Tool.isEmpty(stringExtra2)) {
            stringExtra2 = "";
        }
        editText2.setText(stringExtra2);
        this.tcpResultTextView = (TextView) findViewById(R.id.wifi_tcp_result_textView);
        Button button = (Button) findViewById(R.id.wifi_tcp_action_button);
        this.tcpActionButton = button;
        button.setEnabled(false);
        this.tcpActionButton.setOnClickListener(new AnonymousClass1());
        this.serverIpSpinner = (Spinner) findViewById(R.id.wifi_connect_serverIpSpinner);
        this.serverIpSpinner.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_item, new ArrayList()));
        Button button2 = (Button) findViewById(R.id.wifi_connect_setServerIpButton);
        this.setServerIpButton = button2;
        button2.setEnabled(false);
        this.setServerIpButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BlufiActivity.this.m316lambda$onCreate$3$comnfcxluxinvpowerviewbleBlufiActivity(view);
            }
        });
        Button button3 = (Button) findViewById(R.id.Dongle_Connect_Params_button);
        this.dongleConnectParamsButton = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent2 = new Intent(BlufiActivity.instance, (Class<?>) DongleConnectActivity.class);
                intent2.putExtra(Constants.LOCAL_CONNECT_TYPE, BlufiActivity.this.localConnect.getConnectType());
                BlufiActivity.this.startActivity(intent2);
            }
        });
        Button button4 = (Button) findViewById(R.id.ble_update_firmware_button);
        this.updateFirmwareButton = button4;
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BlufiActivity.this.m317lambda$onCreate$4$comnfcxluxinvpowerviewbleBlufiActivity(view);
            }
        });
        Button button5 = (Button) findViewById(R.id.ble_test_04_button);
        this.test04Button = button5;
        button5.setEnabled(false);
        this.test04Button.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BlufiActivity.this.m318lambda$onCreate$5$comnfcxluxinvpowerviewbleBlufiActivity(view);
            }
        });
        LocalConnectManager.updateBluetoothLocalConnect(new BluetoothLocalConnect(this, bluetoothDevice));
        LocalConnect bluetoothLocalConnect = LocalConnectManager.getBluetoothLocalConnect();
        this.localConnect = bluetoothLocalConnect;
        bluetoothLocalConnect.initialize(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.ble.BlufiActivity$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BlufiActivity.this.tcpResultTextView.setVisibility(4);
            final String trim = BlufiActivity.this.ssidEditView.getText().toString().trim();
            if (Tool.isEmpty(trim) || "unknown ssid".equals(trim)) {
                BlufiActivity.this.tcpResultTextView.setVisibility(0);
                BlufiActivity.this.tcpResultTextView.setText(R.string.wifi_connect_ssid_invalid);
                return;
            }
            final String trim2 = BlufiActivity.this.passwordEditView.getText().toString().trim();
            if (Tool.isEmpty(trim2)) {
                trim2 = "";
            }
            if (Tool.isEmpty(trim2) || trim2.length() < 8) {
                BlufiActivity.this.tcpResultTextView.setVisibility(0);
                BlufiActivity.this.tcpResultTextView.setText(R.string.page_register_error_wifi_password_minLength);
            } else {
                BlufiActivity.this.tcpActionButton.setText(R.string.wifi_connect_btn_sending);
                BlufiActivity.this.tcpActionButton.setEnabled(false);
                new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BlufiActivity.AnonymousClass1.this.m320lambda$onClick$0$comnfcxluxinvpowerviewbleBlufiActivity$1(trim, trim2);
                    }
                }).start();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-nfcx-luxinvpower-view-ble-BlufiActivity$1, reason: not valid java name */
        public /* synthetic */ void m320lambda$onClick$0$comnfcxluxinvpowerviewbleBlufiActivity$1(String str, String str2) {
            if (BlufiActivity.this.localConnect.writeDatalogParam(4, str + "," + str2)) {
                BlufiActivity.this.closeTcpActionAtUiThread(R.string.wifi_connect_tcp_set_success_reboot);
            } else {
                BlufiActivity.this.closeTcpActionAtUiThread(R.string.wifi_connect_write_tcp_failed);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$3$com-nfcx-luxinvpower-view-ble-BlufiActivity, reason: not valid java name */
    public /* synthetic */ void m316lambda$onCreate$3$comnfcxluxinvpowerviewbleBlufiActivity(View view) {
        final Property property = (Property) this.serverIpSpinner.getSelectedItem();
        if (property != null) {
            this.setServerIpButton.setEnabled(false);
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    BlufiActivity.this.m315lambda$onCreate$2$comnfcxluxinvpowerviewbleBlufiActivity(property);
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$2$com-nfcx-luxinvpower-view-ble-BlufiActivity, reason: not valid java name */
    public /* synthetic */ void m315lambda$onCreate$2$comnfcxluxinvpowerviewbleBlufiActivity(Property property) {
        final boolean writeDatalogParam = this.localConnect.writeDatalogParam(6, property.getName());
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                BlufiActivity.this.m314lambda$onCreate$1$comnfcxluxinvpowerviewbleBlufiActivity(writeDatalogParam);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-nfcx-luxinvpower-view-ble-BlufiActivity, reason: not valid java name */
    public /* synthetic */ void m314lambda$onCreate$1$comnfcxluxinvpowerviewbleBlufiActivity(boolean z) {
        if (z) {
            Tool.alert(this, R.string.local_set_result_success);
        } else {
            Tool.alert(this, R.string.local_set_result_failed);
        }
        this.setServerIpButton.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$4$com-nfcx-luxinvpower-view-ble-BlufiActivity, reason: not valid java name */
    public /* synthetic */ void m317lambda$onCreate$4$comnfcxluxinvpowerviewbleBlufiActivity(View view) {
        Intent intent = new Intent(instance, (Class<?>) EWifiRemoteUpdateActivity.class);
        intent.putExtra("datalogParam", this.datalogParam);
        intent.putExtra("firmwareVersion", this.firmwareVersion);
        intent.putExtra(Constants.LOCAL_CONNECT_TYPE, this.localConnect.getConnectType());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$5$com-nfcx-luxinvpower-view-ble-BlufiActivity, reason: not valid java name */
    public /* synthetic */ void m318lambda$onCreate$5$comnfcxluxinvpowerviewbleBlufiActivity(View view) {
        if (this.localConnect.initialize(true)) {
            this.localConnect.sendPureCommand(DataFrameFactory.createReadMultiInputDataFrame(TCP_PROTOCOL._02, this.datalogSn, 0, 40));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeTcpActionAtUiThread(final int i) {
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BlufiActivity.this.m312x26ef693c(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$closeTcpActionAtUiThread$6$com-nfcx-luxinvpower-view-ble-BlufiActivity, reason: not valid java name */
    public /* synthetic */ void m312x26ef693c(int i) {
        this.tcpResultTextView.setVisibility(0);
        this.tcpResultTextView.setText(i);
        this.tcpActionButton.setText(R.string.wifi_tcp_btn_connect);
        this.tcpActionButton.setEnabled(true);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("LuxPower - BlufiActivity onDestroy...");
        this.executor.shutdown();
        LocalConnectManager.updateBluetoothLocalConnect(null);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    /* loaded from: classes2.dex */
    private class GattCallback extends BluetoothGattCallback {
        private GattCallback() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i != 0) {
                bluetoothGatt.disconnect();
                System.out.println("ble - " + String.format(Locale.ENGLISH, "WriteChar error status %d", Integer.valueOf(i)));
            } else {
                System.out.println("ble - 发送成功");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            int count2Low;
            int count2Low2;
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (value != null) {
                if (BlufiActivity.this.dataReceived == null || BlufiActivity.this.dataReceived.length == 0 || (value.length >= 2 && value[0] == -95 && value[1] == 26)) {
                    BlufiActivity.this.dataReceived = new byte[value.length];
                    System.arraycopy(value, 0, BlufiActivity.this.dataReceived, 0, value.length);
                } else {
                    byte[] bArr = new byte[BlufiActivity.this.dataReceived.length + value.length];
                    System.arraycopy(BlufiActivity.this.dataReceived, 0, bArr, 0, BlufiActivity.this.dataReceived.length);
                    System.arraycopy(value, 0, bArr, BlufiActivity.this.dataReceived.length, value.length);
                    BlufiActivity.this.dataReceived = bArr;
                }
                if (BlufiActivity.this.dataReceived == null || BlufiActivity.this.dataReceived.length < 6 || BlufiActivity.this.dataReceived.length < (count2Low2 = (count2Low = ProTool.count2Low(BlufiActivity.this.dataReceived, 4)) + 6)) {
                    return;
                }
                int i = ProTool.getInt(BlufiActivity.this.dataReceived, 7);
                if (i != 195) {
                    if (i == 196 && BlufiActivity.this.dataReceived.length >= 21) {
                        int count2Low3 = ProTool.count2Low(BlufiActivity.this.dataReceived, 18);
                        byte b = BlufiActivity.this.dataReceived[20];
                        if (count2Low3 != 4) {
                            if (count2Low3 == 6) {
                                if (b == 0) {
                                    Tool.alertNotInUiThread(BlufiActivity.this, R.string.local_set_result_success);
                                } else {
                                    Tool.alertNotInUiThread(BlufiActivity.this, R.string.local_set_result_failed);
                                }
                            }
                        } else if (b == 0) {
                            BlufiActivity.this.closeTcpActionAtUiThread(R.string.wifi_connect_tcp_set_success_reboot);
                        } else {
                            BlufiActivity.this.closeTcpActionAtUiThread(R.string.wifi_connect_write_tcp_failed);
                        }
                    }
                } else if (BlufiActivity.this.dataReceived.length > 22) {
                    int count2Low4 = ProTool.count2Low(BlufiActivity.this.dataReceived, 18);
                    String substring = new String(BlufiActivity.this.dataReceived).substring(22);
                    if (count2Low4 == 6) {
                        BlufiActivity.this.handleReadServerIpAndPort(substring);
                    } else if (count2Low4 == 7) {
                        Tool.alertNotInUiThread(BlufiActivity.this, substring);
                    }
                }
                int i2 = count2Low + 7;
                if (BlufiActivity.this.dataReceived.length <= i2 || BlufiActivity.this.dataReceived[count2Low2] != -95 || BlufiActivity.this.dataReceived[i2] != 26) {
                    BlufiActivity.this.dataReceived = null;
                    return;
                }
                int length = (BlufiActivity.this.dataReceived.length - count2Low) - 6;
                byte[] bArr2 = new byte[length];
                System.arraycopy(BlufiActivity.this.dataReceived, count2Low2, bArr2, 0, length);
                BlufiActivity.this.dataReceived = bArr2;
            }
        }
    }

    private void setButtonStatusAtUiThread(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BlufiActivity.this.m319x5c14d04a(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setButtonStatusAtUiThread$7$com-nfcx-luxinvpower-view-ble-BlufiActivity, reason: not valid java name */
    public /* synthetic */ void m319x5c14d04a(boolean z) {
        this.tcpActionButton.setEnabled(z);
        this.setServerIpButton.setEnabled(z);
        this.test04Button.setEnabled(z);
    }

    @Override // com.nfcx.luxinvpower.connect.ble.BleAction
    public void bleConnected() {
        setButtonStatusAtUiThread(true);
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                BlufiActivity.this.m311x4882ecdd();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$bleConnected$9$com-nfcx-luxinvpower-view-ble-BlufiActivity, reason: not valid java name */
    public /* synthetic */ void m311x4882ecdd() {
        handleReadServerIpAndPort(this.localConnect.readDatalogParam(6));
        Tool.sleep(200L);
        final String readDatalogParam = this.localConnect.readDatalogParam(7);
        System.out.println("localFirmwareVersion == " + readDatalogParam);
        Tool.sleep(200L);
        final String readDatalogParam2 = this.localConnect.readDatalogParam(11);
        System.out.println("localDatalogParam == " + readDatalogParam2);
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BlufiActivity.this.m310xade22a5c(readDatalogParam, readDatalogParam2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateData, reason: merged with bridge method [inline-methods] */
    public void m310xade22a5c(String str, String str2) {
        this.firmwareVersion = str;
        this.datalogParam = str2;
        instance.dongleConnectParamsButton.setEnabled(true);
        instance.updateFirmwareButton.setText(instance.getString(R.string._1_s_update_firmware, new Object[]{this.firmwareVersion + " - "}));
        instance.updateFirmwareButton.setEnabled(true);
    }

    @Override // com.nfcx.luxinvpower.connect.ble.BleAction
    public void bleConnectClosed() {
        setButtonStatusAtUiThread(false);
    }

    public void handleReadServerIpAndPort(final String str) {
        System.out.println("ble - handleReadServerIpAndPort serverIpAndPort: " + str);
        if (Tool.isEmpty(str) || !Constants.validServerIndexMap.containsKey(str)) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.ble.BlufiActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                BlufiActivity.this.m313x1da9444d(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$handleReadServerIpAndPort$10$com-nfcx-luxinvpower-view-ble-BlufiActivity, reason: not valid java name */
    public /* synthetic */ void m313x1da9444d(String str) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getFirstClusterServers(this));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.serverIpSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        Integer num = Constants.validServerIndexMap.get(str);
        if (num != null) {
            this.serverIpSpinner.setSelection(num.intValue());
        }
        this.setServerIpButton.setEnabled(true);
    }
}
