package com.nfcx.luxinvpower.view.wifi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.connect.LocalConnect;
import com.nfcx.luxinvpower.connect.LocalConnectManager;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.tool.ProTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.wifi.DongleConnectActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DongleConnectActivity extends Activity {
    public static DongleConnectActivity instance;
    private EditText apParamAPPasswordEditText;
    private ToggleButton apParamEncryptionModeToggleButton;
    private Button apParamQueryAPPasswordButton;
    private Button apParamRestartDongleButton;
    private TextView apParamSSIDText;
    private String apPassword;
    private TextView apStateIPText;
    private TextView apStateNetmaskText;
    private TextView apStateText;
    private Context context;
    private ImageButton eyeImageButton;
    private ImageButton eyeToggleButton4APPassword;
    private Button ipModeButton;
    private List<String> ipModeList;
    private Spinner ipModeSpinner;
    private boolean isOn = false;
    private LocalConnect localConnect;
    private TextView network1StateProtocolText;
    private TextView network1StateRemotePortText;
    private TextView network1StateServerAddressText;
    private TextView network1StateTCPClientStateText;
    private TextView network2StateLocalPortText;
    private TextView network2StateProtocolText;
    private ConstraintLayout signalStrengthLayout;
    private TextView signalStrengthText;
    private ConstraintLayout staIPModeLayout;
    private EditText staStateGatewayText;
    private EditText staStateIPText;
    private EditText staStateNetmaskText;
    private TextView stationConnectionStateText;
    private TextView stationParamPasswordText;
    private TextView stationParamSSIDText;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dongle_connect);
        instance = this;
        this.context = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DongleConnectActivity.instance.finish();
                DongleConnectActivity.this.clearFlowChart();
            }
        });
        this.localConnect = LocalConnectManager.getLocalConnect(getIntent().getStringExtra(Constants.LOCAL_CONNECT_TYPE));
        this.apStateText = (TextView) findViewById(R.id.activity_dongle_connect_ap_stateText);
        this.apStateIPText = (TextView) findViewById(R.id.activity_dongle_connect_ap_stateIPText);
        this.apStateNetmaskText = (TextView) findViewById(R.id.activity_dongle_connect_ap_state_NetmaskText);
        this.staIPModeLayout = (ConstraintLayout) findViewById(R.id.activity_dongle_connect_sta_ipModeLayout);
        this.ipModeSpinner = (Spinner) findViewById(R.id.activity_dongle_connect_sta_ipModeSpinner);
        ArrayList arrayList = new ArrayList();
        this.ipModeList = arrayList;
        arrayList.add(getString(R.string.dongle_connect_ip_mode_dhcp_disable));
        this.ipModeList.add(getString(R.string.dongle_connect_ip_mode_dhcp_client));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.context, android.R.layout.simple_spinner_item, this.ipModeList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.ipModeSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        this.ipModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                System.out.println("LuxPower - onItemSelected selectIpMode = " + ((String) DongleConnectActivity.this.ipModeSpinner.getSelectedItem()));
                if (DongleConnectActivity.this.ipModeSpinner.getSelectedItemId() == 0) {
                    DongleConnectActivity.this.staStateIPText.setEnabled(true);
                    DongleConnectActivity.this.staStateNetmaskText.setEnabled(true);
                    DongleConnectActivity.this.staStateGatewayText.setEnabled(true);
                } else {
                    DongleConnectActivity.this.staStateIPText.setEnabled(false);
                    DongleConnectActivity.this.staStateNetmaskText.setEnabled(false);
                    DongleConnectActivity.this.staStateGatewayText.setEnabled(false);
                }
            }
        });
        this.ipModeButton = (Button) findViewById(R.id.activity_dongle_connect_sta_changeIPModeButton);
        this.staStateIPText = (EditText) findViewById(R.id.activity_dongle_connect_sta_stateIPText);
        this.staStateNetmaskText = (EditText) findViewById(R.id.activity_dongle_connect_sta_state_NetmaskText);
        this.staStateGatewayText = (EditText) findViewById(R.id.activity_dongle_connect_sta_stateGatewayText);
        this.apParamSSIDText = (TextView) findViewById(R.id.activity_dongle_connect_ap_param_SSIDText);
        this.apParamEncryptionModeToggleButton = (ToggleButton) findViewById(R.id.activity_dongle_connect_ap_param_Encryption_ModeToggleButton);
        this.apParamAPPasswordEditText = (EditText) findViewById(R.id.activity_dongle_connect_AP_PasswordEditText);
        this.eyeToggleButton4APPassword = (ImageButton) findViewById(R.id.eyeToggleButton4APPassword);
        Button button = (Button) findViewById(R.id.activity_dongle_connect_ap_param_query_AP_PasswordButton);
        this.apParamQueryAPPasswordButton = button;
        button.setEnabled(false);
        this.apParamQueryAPPasswordButton.setOnClickListener(new AnonymousClass3());
        this.eyeToggleButton4APPassword.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DongleConnectActivity.this.isOn = !r3.isOn;
                if (DongleConnectActivity.this.isOn) {
                    DongleConnectActivity dongleConnectActivity = DongleConnectActivity.this;
                    dongleConnectActivity.showAPPasswordInputDialog(dongleConnectActivity.apPassword);
                } else {
                    DongleConnectActivity.this.apParamAPPasswordEditText.setText(new String(new char[DongleConnectActivity.this.apPassword.length()]).replace((char) 0, '*'));
                    DongleConnectActivity.this.eyeToggleButton4APPassword.setBackgroundResource(R.drawable.icon_eye_close);
                    DongleConnectActivity.this.apParamQueryAPPasswordButton.setEnabled(false);
                }
            }
        });
        Button button2 = (Button) findViewById(R.id.activity_dongle_connect_ap_param_restart_dongleButton);
        this.apParamRestartDongleButton = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DongleConnectActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DongleConnectActivity.this.apParamRestartDongleButton.setEnabled(false);
                    }
                });
                DongleConnectActivity.this.restartDongle();
            }
        });
        this.eyeImageButton = (ImageButton) findViewById(R.id.eyeToggleButton);
        this.stationParamSSIDText = (TextView) findViewById(R.id.activity_dongle_connect_station_param_SSIDText);
        this.stationParamPasswordText = (TextView) findViewById(R.id.activity_dongle_connect_station_param_PasswordText);
        this.stationConnectionStateText = (TextView) findViewById(R.id.activity_dongle_connect_station_param_ConnectionStateText);
        this.signalStrengthLayout = (ConstraintLayout) findViewById(R.id.activity_dongle_connect_station_param_Signal_StrengthLayout);
        this.signalStrengthText = (TextView) findViewById(R.id.activity_dongle_connect_station_param_Signal_StrengthText);
        this.network1StateProtocolText = (TextView) findViewById(R.id.activity_dongle_connect_network1_state_ProtocolText);
        this.network1StateRemotePortText = (TextView) findViewById(R.id.activity_dongle_connect_network1_state_Remote_PortText);
        this.network1StateServerAddressText = (TextView) findViewById(R.id.activity_dongle_connect_network1_state_Server_AddressText);
        this.network1StateTCPClientStateText = (TextView) findViewById(R.id.activity_dongle_connect_network1_state_TCP_Client_StateText);
        this.network2StateProtocolText = (TextView) findViewById(R.id.activity_dongle_connect_network2_state_ProtocolText);
        this.network2StateLocalPortText = (TextView) findViewById(R.id.activity_dongle_connect_network2_state_Local_PortText);
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DongleConnectActivity.this.m482xf843608d();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$3, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$3$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DongleConnectActivity.AnonymousClass3.this.m485xffc1ad48();
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$1$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity$3, reason: not valid java name */
        public /* synthetic */ void m485xffc1ad48() {
            String obj = DongleConnectActivity.this.apParamAPPasswordEditText.getText().toString();
            if (!Tool.isEmpty(obj)) {
                if (DongleConnectActivity.this.apParamEncryptionModeToggleButton.isChecked()) {
                    DongleConnectActivity.this.dongleSetApPasswordStatus(4, obj);
                    return;
                } else {
                    DongleConnectActivity.this.dongleSetApPasswordStatus(0, obj);
                    return;
                }
            }
            DongleConnectActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Toast.makeText(DongleConnectActivity.instance, R.string.page_maintain_remote_set_alert_param_empty, 0).show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity, reason: not valid java name */
    public /* synthetic */ void m482xf843608d() {
        try {
            try {
                this.apPassword = this.localConnect.readDatalogParam(14);
                System.out.println("LuxPower - apPassword = " + this.apPassword);
                if (!Tool.isEmpty(this.apPassword)) {
                    final String replace = new String(new char[this.apPassword.length()]).replace((char) 0, '*');
                    runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            DongleConnectActivity.this.m481xde27e1ee(replace);
                        }
                    });
                }
                String showData = ProTool.showData(this.localConnect.readDatalogParam(15));
                System.out.println("LuxPower - data = " + showData);
                if (!Tool.isEmpty(showData)) {
                    final String trim = showData.substring(showData.length() - 3).trim();
                    runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.6
                        @Override // java.lang.Runnable
                        public void run() {
                            if ("04".equals(trim)) {
                                DongleConnectActivity.this.apParamEncryptionModeToggleButton.setChecked(true);
                            } else if ("00".equals(trim)) {
                                DongleConnectActivity.this.apParamEncryptionModeToggleButton.setChecked(false);
                            }
                        }
                    });
                }
                String readDatalogParam = this.localConnect.readDatalogParam(16);
                System.out.println("LuxPower - result = " + readDatalogParam);
                if (!Tool.isEmpty(readDatalogParam)) {
                    final String[] split = readDatalogParam.split(";");
                    runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.7
                        @Override // java.lang.Runnable
                        public void run() {
                            if (split.length >= 13) {
                                DongleConnectActivity.this.apStateText.setText(R.string.phrase_button_enable);
                                DongleConnectActivity.this.apStateIPText.setText(split[0]);
                                DongleConnectActivity.this.apStateNetmaskText.setText(split[1]);
                                DongleConnectActivity.this.ipModeButton.setText(R.string.phrase_button_set);
                                DongleConnectActivity.this.staStateIPText.setText(split[2]);
                                DongleConnectActivity.this.staStateNetmaskText.setText(split[3]);
                                DongleConnectActivity.this.staStateGatewayText.setText(split[4]);
                                DongleConnectActivity.this.apParamSSIDText.setText(split[5]);
                                DongleConnectActivity.this.stationParamSSIDText.setText(split[6]);
                                DongleConnectActivity.this.eyeImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.7.1
                                    boolean isOn = false;

                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view) {
                                        boolean z = !this.isOn;
                                        this.isOn = z;
                                        if (z) {
                                            DongleConnectActivity.this.showPasswordInputDialog(split[7]);
                                        } else {
                                            DongleConnectActivity.this.stationParamPasswordText.setText("XXX");
                                            DongleConnectActivity.this.eyeImageButton.setBackgroundResource(R.drawable.icon_eye_close);
                                        }
                                    }
                                });
                                if (split[8].length() <= 15) {
                                    DongleConnectActivity.this.stationConnectionStateText.setText(split[8]);
                                } else {
                                    DongleConnectActivity.this.stationConnectionStateText.setText(split[8]);
                                    DongleConnectActivity.this.stationConnectionStateText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                                    DongleConnectActivity.this.stationConnectionStateText.setMarqueeRepeatLimit(-1);
                                    DongleConnectActivity.this.stationConnectionStateText.setFocusable(true);
                                    DongleConnectActivity.this.stationConnectionStateText.setFocusableInTouchMode(true);
                                    DongleConnectActivity.this.stationConnectionStateText.setSingleLine(true);
                                    DongleConnectActivity.this.stationConnectionStateText.setSelected(true);
                                }
                                DongleConnectActivity.this.network1StateProtocolText.setText("TCPClient");
                                String str = split[9];
                                if (str.length() <= 15) {
                                    DongleConnectActivity.this.network1StateServerAddressText.setText(str);
                                } else {
                                    DongleConnectActivity.this.network1StateServerAddressText.setText(str);
                                    DongleConnectActivity.this.network1StateServerAddressText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                                    DongleConnectActivity.this.network1StateServerAddressText.setMarqueeRepeatLimit(-1);
                                    DongleConnectActivity.this.network1StateServerAddressText.setFocusable(true);
                                    DongleConnectActivity.this.network1StateServerAddressText.setFocusableInTouchMode(true);
                                    DongleConnectActivity.this.network1StateServerAddressText.setSingleLine(true);
                                    DongleConnectActivity.this.network1StateServerAddressText.setSelected(true);
                                }
                                DongleConnectActivity.this.network1StateRemotePortText.setText(split[10]);
                                if (Integer.parseInt(split[11]) == 1) {
                                    DongleConnectActivity.this.network1StateTCPClientStateText.setText("Connected");
                                } else {
                                    DongleConnectActivity.this.network1StateTCPClientStateText.setText("DisConnected");
                                }
                                DongleConnectActivity.this.network2StateProtocolText.setText("TCPServer");
                                DongleConnectActivity.this.network2StateLocalPortText.setText(split[12]);
                                DongleConnectActivity.this.staStateIPText.setEnabled(false);
                                DongleConnectActivity.this.staStateNetmaskText.setEnabled(false);
                                DongleConnectActivity.this.staStateGatewayText.setEnabled(false);
                            }
                            String[] strArr = split;
                            if (strArr.length >= 15) {
                                int parseInt = Integer.parseInt(strArr[13]);
                                if (parseInt == 0) {
                                    DongleConnectActivity.this.ipModeSpinner.setSelection(0);
                                } else if (parseInt == 1) {
                                    DongleConnectActivity.this.ipModeSpinner.setSelection(1);
                                }
                                DongleConnectActivity.this.signalStrengthText.setText(split[14]);
                                DongleConnectActivity.this.staIPModeLayout.setVisibility(0);
                                DongleConnectActivity.this.signalStrengthLayout.setVisibility(0);
                            }
                        }
                    });
                    this.ipModeButton.setOnClickListener(new AnonymousClass8());
                }
                if (Constants.LOCAL_CONNECT_TYPE_BLUETOOTH.equals(this.localConnect.getConnectType())) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (Constants.LOCAL_CONNECT_TYPE_BLUETOOTH.equals(this.localConnect.getConnectType())) {
                    return;
                }
            }
            this.localConnect.close();
        } catch (Throwable th) {
            if (!Constants.LOCAL_CONNECT_TYPE_BLUETOOTH.equals(this.localConnect.getConnectType())) {
                this.localConnect.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity, reason: not valid java name */
    public /* synthetic */ void m481xde27e1ee(String str) {
        this.apParamAPPasswordEditText.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$8, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass8 implements View.OnClickListener {
        AnonymousClass8() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DongleConnectActivity.this.ipModeButton.setEnabled(false);
            DongleConnectActivity.this.apParamRestartDongleButton.setEnabled(false);
            if (DongleConnectActivity.this.ipModeSpinner.getSelectedItemId() == 0) {
                DongleConnectActivity.this.dongleChangeIpModeStatus(DongleConnectActivity.this.staStateIPText.getText().toString(), DongleConnectActivity.this.staStateNetmaskText.getText().toString(), DongleConnectActivity.this.staStateGatewayText.getText().toString());
                return;
            }
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$8$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    DongleConnectActivity.AnonymousClass8.this.m487xf8002c();
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$2$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity$8, reason: not valid java name */
        public /* synthetic */ void m487xf8002c() {
            try {
                final boolean writeDatalogParam = DongleConnectActivity.this.localConnect.writeDatalogParam(18, new byte[]{JSONB.Constants.BC_OBJECT_END});
                DongleConnectActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$8$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DongleConnectActivity.AnonymousClass8.this.m486xfe8b5a6e(writeDatalogParam);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                DongleConnectActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$8$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Toast.makeText(DongleConnectActivity.instance, R.string.page_maintain_remote_set_result_command_not_send, 0).show();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity$8, reason: not valid java name */
        public /* synthetic */ void m486xfe8b5a6e(boolean z) {
            if (z) {
                Toast.makeText(DongleConnectActivity.instance, R.string.local_set_result_success, 0).show();
                DongleConnectActivity.this.restartDongle();
            } else {
                Toast.makeText(DongleConnectActivity.instance, R.string.page_maintain_remote_set_result_command_not_send, 0).show();
            }
            DongleConnectActivity.this.ipModeButton.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dongleSetApPasswordStatus(final int i, final String str) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                DongleConnectActivity.this.m480x9596fbe(i, str);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$dongleSetApPasswordStatus$4$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity, reason: not valid java name */
    public /* synthetic */ void m480x9596fbe(int i, String str) {
        try {
            final boolean writeDatalogParam = this.localConnect.writeDatalogParam(12, (i + "," + str).getBytes("ISO-8859-1"));
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    DongleConnectActivity.this.m479xd5227280(writeDatalogParam);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    Toast.makeText(DongleConnectActivity.instance, R.string.page_maintain_remote_set_result_command_not_send, 0).show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$dongleSetApPasswordStatus$2$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity, reason: not valid java name */
    public /* synthetic */ void m479xd5227280(boolean z) {
        if (z) {
            this.apParamRestartDongleButton.setEnabled(false);
            Toast.makeText(instance, R.string.local_set_result_success, 0).show();
            restartDongle();
            return;
        }
        Toast.makeText(instance, R.string.page_maintain_remote_set_result_command_not_send, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dongleChangeIpModeStatus(final String str, final String str2, final String str3) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DongleConnectActivity.this.m478x2766c8d7(str, str3, str2);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$dongleChangeIpModeStatus$9$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity, reason: not valid java name */
    public /* synthetic */ void m478x2766c8d7(String str, String str2, String str3) {
        Runnable runnable;
        if (!Tool.isValidIP(str) || !Tool.isValidSubnetMask(str2) || !Tool.isValidIP(str3)) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    Toast.makeText(DongleConnectActivity.instance, R.string.invalid_ip_address_or_subnet_mask, 0).show();
                }
            });
            return;
        }
        try {
            try {
                final boolean writeDatalogParam = this.localConnect.writeDatalogParam(17, (str + "," + str3 + "," + str2).getBytes("ISO-8859-1"));
                runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        DongleConnectActivity.this.m476xd9144cfa(writeDatalogParam);
                    }
                });
                runnable = new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        DongleConnectActivity.this.m477xd4b4a38();
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        Toast.makeText(DongleConnectActivity.instance, R.string.page_maintain_remote_set_result_command_not_send, 0).show();
                    }
                });
                runnable = new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        DongleConnectActivity.this.m477xd4b4a38();
                    }
                };
            }
            runOnUiThread(runnable);
        } catch (Throwable th) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    DongleConnectActivity.this.m477xd4b4a38();
                }
            });
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$dongleChangeIpModeStatus$6$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity, reason: not valid java name */
    public /* synthetic */ void m476xd9144cfa(boolean z) {
        if (z) {
            this.ipModeButton.setEnabled(false);
            Toast.makeText(instance, R.string.local_set_result_success, 0).show();
            restartDongle();
            return;
        }
        Toast.makeText(instance, R.string.page_maintain_remote_set_result_command_not_send, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$dongleChangeIpModeStatus$8$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity, reason: not valid java name */
    public /* synthetic */ void m477xd4b4a38() {
        this.ipModeButton.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartDongle() {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DongleConnectActivity.this.m484xe6d332fd();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$restartDongle$11$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity, reason: not valid java name */
    public /* synthetic */ void m484xe6d332fd() {
        this.localConnect.writeDatalogParam(13, new byte[]{JSONB.Constants.BC_OBJECT_END});
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DongleConnectActivity.this.m483xccb7b45e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$restartDongle$10$com-nfcx-luxinvpower-view-wifi-DongleConnectActivity, reason: not valid java name */
    public /* synthetic */ void m483xccb7b45e() {
        this.apParamRestartDongleButton.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAPPasswordInputDialog(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dongle_connect_verification_code);
        final EditText editText = new EditText(this);
        editText.setInputType(Opcodes.LOR);
        builder.setView(editText);
        builder.setPositiveButton(getString(R.string.phrase_button_ok), new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText.getText().toString().equals("123123")) {
                    DongleConnectActivity.this.apParamAPPasswordEditText.setText(str);
                    DongleConnectActivity.this.eyeToggleButton4APPassword.setBackgroundResource(R.drawable.icon_eye_open);
                    DongleConnectActivity.this.apParamQueryAPPasswordButton.setEnabled(true);
                } else {
                    DongleConnectActivity dongleConnectActivity = DongleConnectActivity.this;
                    Toast.makeText(dongleConnectActivity, dongleConnectActivity.getString(R.string.verification_code_mismatch), 0).show();
                    DongleConnectActivity.this.apParamQueryAPPasswordButton.setEnabled(false);
                }
            }
        });
        builder.setNegativeButton(getString(R.string.phrase_button_cancel), new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                DongleConnectActivity.this.eyeToggleButton4APPassword.setBackgroundResource(R.drawable.icon_eye_close);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.11
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                DongleConnectActivity.this.isOn = !r2.isOn;
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPasswordInputDialog(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dongle_connect_verification_code);
        final EditText editText = new EditText(this);
        editText.setInputType(Opcodes.LOR);
        builder.setView(editText);
        builder.setPositiveButton(getString(R.string.phrase_button_ok), new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText.getText().toString().equals("123123")) {
                    DongleConnectActivity.this.stationParamPasswordText.setText(str);
                    DongleConnectActivity.this.eyeImageButton.setBackgroundResource(R.drawable.icon_eye_open);
                } else {
                    DongleConnectActivity dongleConnectActivity = DongleConnectActivity.this;
                    Toast.makeText(dongleConnectActivity, dongleConnectActivity.getString(R.string.verification_code_mismatch), 0).show();
                }
            }
        });
        builder.setNegativeButton(getString(R.string.phrase_button_cancel), new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.wifi.DongleConnectActivity.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                DongleConnectActivity.this.eyeImageButton.setBackgroundResource(R.drawable.icon_eye_close);
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFlowChart() {
        this.apPassword = "";
        this.apStateText.setText(R.string.phrase_button_disable);
        this.apStateIPText.setText("");
        this.apStateNetmaskText.setText("");
        this.apStateText.setText(R.string.phrase_button_disable);
        this.staStateIPText.setText("");
        this.staStateNetmaskText.setText("");
        this.staStateGatewayText.setText("");
        this.signalStrengthText.setText("");
        this.apParamSSIDText.setText("");
        this.apParamEncryptionModeToggleButton.setChecked(false);
        this.stationParamSSIDText.setText("");
        this.stationParamPasswordText.setText("");
        this.network1StateProtocolText.setText("");
        this.network1StateRemotePortText.setText("");
        this.network1StateServerAddressText.setText("");
        this.network1StateTCPClientStateText.setText("");
        this.network2StateProtocolText.setText("");
        this.network2StateLocalPortText.setText("");
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.staIPModeLayout.setVisibility(8);
        this.signalStrengthLayout.setVisibility(8);
    }
}
