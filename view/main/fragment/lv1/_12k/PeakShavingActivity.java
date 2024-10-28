package com.nfcx.luxinvpower.view.main.fragment.lv1._12k;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.set.RemoteReadInfo;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.cache.RemoteSetCacheManager;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.view.main.MainActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PeakShavingActivity extends Activity {
    public static PeakShavingActivity instance;
    private boolean created;
    private Fragment fragment;
    private ToggleButton gridPeakShavingFunctionButton;
    private ConstraintLayout gridPeakShavingLayout;
    private Button gridPeakShavingPowerButton;
    private EditText gridPeakShavingPowerEditText;
    private Button gridPeakShavingSoc1Button;
    private EditText gridPeakShavingSoc1EditText;
    private Button gridPeakShavingSoc2Button;
    private EditText gridPeakShavingSoc2EditText;
    private Button gridPeakShavingVolt1Button;
    private EditText gridPeakShavingVolt1EditText;
    private Button gridPeakShavingVolt2Button;
    private EditText gridPeakShavingVolt2EditText;
    private Inverter inverter;
    private List<Inverter> inverterList;
    private Spinner inverterSpinner;
    private EditText peakShavingEndHour1EditText;
    private EditText peakShavingEndHour2EditText;
    private EditText peakShavingEndMinute1EditText;
    private EditText peakShavingEndMinute2EditText;
    private Button peakShavingEndTime1Button;
    private Button peakShavingEndTime2Button;
    private EditText peakShavingStartHour1EditText;
    private EditText peakShavingStartHour2EditText;
    private EditText peakShavingStartMinute1EditText;
    private EditText peakShavingStartMinute2EditText;
    private Button peakShavingStartTime1Button;
    private Button peakShavingStartTime2Button;
    private Button readAllButton;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_local_12k_peak_shaving_set);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        this.gridPeakShavingPowerEditText = (EditText) findViewById(R.id.fragment_remote_set_gridPeakShavingPowerEditText);
        Button button = (Button) findViewById(R.id.fragment_remote_set_gridPeakShavingPowerButton);
        this.gridPeakShavingPowerButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(PeakShavingActivity.instance, "dasadaw", 0).show();
            }
        });
        this.gridPeakShavingVolt1EditText = (EditText) findViewById(R.id.fragment_remote_set_gridPeakShavingVolt1EditText);
        Button button2 = (Button) findViewById(R.id.fragment_remote_set_gridPeakShavingVolt1Button);
        this.gridPeakShavingVolt1Button = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(PeakShavingActivity.instance, "dasadaw", 0).show();
            }
        });
        this.gridPeakShavingSoc1EditText = (EditText) findViewById(R.id.fragment_remote_set_gridPeakShavingSoc1EditText);
        Button button3 = (Button) findViewById(R.id.fragment_remote_set_gridPeakShavingSoc1Button);
        this.gridPeakShavingSoc1Button = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(PeakShavingActivity.instance, "dasadaw", 0).show();
            }
        });
        this.gridPeakShavingVolt2EditText = (EditText) findViewById(R.id.fragment_remote_set_gridPeakShavingVolt2EditText);
        Button button4 = (Button) findViewById(R.id.fragment_remote_set_gridPeakShavingVolt2Button);
        this.gridPeakShavingVolt2Button = button4;
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(PeakShavingActivity.instance, "dasadaw", 0).show();
            }
        });
        this.gridPeakShavingSoc2EditText = (EditText) findViewById(R.id.fragment_remote_set_gridPeakShavingSoc2EditText);
        Button button5 = (Button) findViewById(R.id.fragment_remote_set_gridPeakShavingSoc2Button);
        this.gridPeakShavingSoc2Button = button5;
        button5.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(PeakShavingActivity.instance, "dasadaw", 0).show();
            }
        });
        this.peakShavingStartHour1EditText = (EditText) findViewById(R.id.fragment_remote_set_peakShavingStartHour1EditText);
        this.peakShavingStartMinute1EditText = (EditText) findViewById(R.id.fragment_remote_set_peakShavingStartMinute1EditText);
        Button button6 = (Button) findViewById(R.id.fragment_remote_set_peakShavingStartTime1Button);
        this.peakShavingStartTime1Button = button6;
        button6.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(PeakShavingActivity.instance, "dasadaw", 0).show();
            }
        });
        this.peakShavingEndHour1EditText = (EditText) findViewById(R.id.fragment_remote_set_peakShavingEndHour1EditText);
        this.peakShavingEndMinute1EditText = (EditText) findViewById(R.id.fragment_remote_set_peakShavingEndMinute1EditText);
        Button button7 = (Button) findViewById(R.id.fragment_remote_set_peakShavingEndTime1Button);
        this.peakShavingEndTime1Button = button7;
        button7.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(PeakShavingActivity.instance, "dasadaw", 0).show();
            }
        });
        this.peakShavingStartHour2EditText = (EditText) findViewById(R.id.fragment_remote_set_peakShavingStartHour2EditText);
        this.peakShavingStartMinute2EditText = (EditText) findViewById(R.id.fragment_remote_set_peakShavingStartMinute2EditText);
        Button button8 = (Button) findViewById(R.id.fragment_remote_set_peakShavingStartTime2Button);
        this.peakShavingStartTime2Button = button8;
        button8.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(PeakShavingActivity.instance, "dasadaw", 0).show();
            }
        });
        this.peakShavingEndHour2EditText = (EditText) findViewById(R.id.fragment_remote_set_peakShavingEndHour2EditText);
        this.peakShavingEndMinute2EditText = (EditText) findViewById(R.id.fragment_remote_set_peakShavingEndMinute2EditText);
        Button button9 = (Button) findViewById(R.id.fragment_remote_set_peakShavingEndTime2Button);
        this.peakShavingEndTime2Button = button9;
        button9.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(PeakShavingActivity.instance, "dasadaw", 0).show();
            }
        });
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeakShavingActivity.instance.finish();
            }
        });
        UserData userData = GlobalInfo.getInstance().getUserData();
        if (!PLATFORM.LUX_POWER.equals(userData.getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        if (userData.getCurrentPlant() != null) {
            this.inverterList = userData.getInvertersByPlant(userData.getCurrentPlant().getPlantId());
        }
        if (this.inverterList == null) {
            this.inverterList = new ArrayList();
        }
        this.inverterSpinner = (Spinner) findViewById(R.id.fragment_remote_set_inverter_spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.inverterList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.inverterSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        Button button10 = (Button) findViewById(R.id.fragment_remote_set_readAllButton);
        this.readAllButton = button10;
        button10.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeakShavingActivity.this.m374x4c2da6de(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-nfcx-luxinvpower-view-main-fragment-lv1-_12k-PeakShavingActivity, reason: not valid java name */
    public /* synthetic */ void m374x4c2da6de(View view) {
        if (this.inverter != null) {
            RemoteSetCacheManager.getInstance().clearCache(this.inverter.getSerialNum());
            clearFragmentData();
            this.readAllButton.setEnabled(false);
            new ReadMultiParamTask(this).execute(new RemoteReadInfo(this.inverter.getSerialNum(), 0, 40), new RemoteReadInfo(this.inverter.getSerialNum(), 40, 40), new RemoteReadInfo(this.inverter.getSerialNum(), 80, 40), new RemoteReadInfo(this.inverter.getSerialNum(), 120, 40), new RemoteReadInfo(this.inverter.getSerialNum(), Opcodes.IF_ICMPNE, 40), new RemoteReadInfo(this.inverter.getSerialNum(), ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 40));
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - Lv112KRemoteSetFragment onResume...");
        initInverterSpinnerOnItemSelectedListener();
        refreshFragmentParams();
    }

    public void refreshFragmentParams() {
        Inverter inverter;
        System.out.println("LuxPower - 12K Set refreshFragmentParams created = " + this.created);
        if (this.created) {
            UserData userData = GlobalInfo.getInstance().getUserData();
            if (userData.getCurrentInverter() == null || this.inverterList == null) {
                return;
            }
            System.out.println("LuxPower - 12K Set refreshFragmentParams userData.getCurrentInverter() = " + userData.getCurrentInverter().getSerialNum());
            for (int i = 0; i < this.inverterList.size(); i++) {
                Inverter inverter2 = this.inverterList.get(i);
                if (inverter2.getSerialNum().equals(userData.getCurrentInverter().getSerialNum()) && ((inverter = this.inverter) == null || !inverter.getSerialNum().equals(userData.getCurrentInverter().getSerialNum()))) {
                    if (this.inverterSpinner.getSelectedItemPosition() != i) {
                        this.inverterSpinner.setSelection(i);
                    } else {
                        updateSelectInverter(inverter2);
                    }
                }
            }
        }
    }

    private void initInverterSpinnerOnItemSelectedListener() {
        if (this.inverterSpinner.getOnItemSelectedListener() == null) {
            System.out.println("LuxPower - Lv112KRemoteSetFragment initInverterSpinnerOnItemSelectedListener...");
            this.inverterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1._12k.PeakShavingActivity.10
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    PeakShavingActivity.this.updateSelectInverter((Inverter) PeakShavingActivity.this.inverterSpinner.getSelectedItem());
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                    if (PeakShavingActivity.this.inverter != null) {
                        PeakShavingActivity.this.inverter = null;
                        GlobalInfo.getInstance().getUserData().setCurrentInverter(PeakShavingActivity.this.inverter, true);
                        PeakShavingActivity.this.clearFragmentData();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSelectInverter(Inverter inverter) {
        System.out.println("LuxPower - Lv112KRemoteSetFragment selectInverter = " + inverter.getSerialNum() + ", inverter = " + this.inverter);
        Inverter inverter2 = this.inverter;
        if (inverter2 == null || !inverter2.getSerialNum().equals(inverter.getSerialNum())) {
            this.inverter = inverter;
            GlobalInfo.getInstance().getUserData().setCurrentInverter(this.inverter, true);
            if (!this.inverter.isType6()) {
                MainActivity mainActivity = (MainActivity) this.fragment.getActivity();
                if (mainActivity != null) {
                    mainActivity.switchRemoteSetFragment(this.inverter.getDeviceTypeValue());
                    return;
                }
                return;
            }
            clearFragmentData();
            fillDataFromCache();
        }
    }

    public void fillDataFromCache() {
        if (this.created) {
            UserData userData = GlobalInfo.getInstance().getUserData();
            if (userData.getCurrentInverter() != null) {
                JSONObject cache = RemoteSetCacheManager.getInstance().getCache(userData.getCurrentInverter().getSerialNum());
                System.out.println("LuxPower - 12k remote set result from cache = " + cache);
                if (cache != null) {
                    try {
                        analyzeResultToFragment(cache);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.readAllButton.performClick();
        }
    }

    /* loaded from: classes2.dex */
    private static class ReadMultiParamTask extends AsyncTask<RemoteReadInfo, JSONObject, Void> {
        private PeakShavingActivity activity;

        public ReadMultiParamTask(PeakShavingActivity peakShavingActivity) {
            this.activity = peakShavingActivity;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(RemoteReadInfo... remoteReadInfoArr) {
            JSONObject postJson;
            for (RemoteReadInfo remoteReadInfo : remoteReadInfoArr) {
                HashMap hashMap = new HashMap();
                hashMap.put("inverterSn", remoteReadInfo.getSerialNum());
                hashMap.put("startRegister", String.valueOf(remoteReadInfo.getStartRegister()));
                hashMap.put("pointNumber", String.valueOf(remoteReadInfo.getPointNumber()));
                hashMap.put("autoRetry", String.valueOf(true));
                try {
                    postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteRead/read", hashMap);
                    publishProgress(postJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (postJson == null || !postJson.getBoolean("success")) {
                    return null;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(JSONObject... jSONObjectArr) {
            super.onProgressUpdate((Object[]) jSONObjectArr);
            for (JSONObject jSONObject : jSONObjectArr) {
                if (jSONObject != null) {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            Toast.makeText(this.activity.getApplicationContext(), R.string.page_maintain_remote_set_result_unknown_error, 1).show();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (jSONObject.getBoolean("success")) {
                        PeakShavingActivity peakShavingActivity = this.activity;
                        if (peakShavingActivity != null && peakShavingActivity.inverter != null) {
                            RemoteSetCacheManager.getInstance().setCache(this.activity.inverter.getSerialNum(), jSONObject);
                        }
                        this.activity.analyzeResultToFragment(jSONObject);
                    }
                }
                this.activity.toast(jSONObject);
                return;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            super.onPostExecute((ReadMultiParamTask) r2);
            PeakShavingActivity.instance.readAllButton.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void analyzeResultToFragment(JSONObject jSONObject) throws Exception {
        if (jSONObject.has("_12K_HOLD_GRID_PEAK_SHAVING_POWER")) {
            this.gridPeakShavingPowerEditText.setText(jSONObject.getString("_12K_HOLD_GRID_PEAK_SHAVING_POWER"));
        }
        if (jSONObject.has("_12K_HOLD_GRID_PEAK_SHAVING_VOLT")) {
            this.gridPeakShavingVolt1EditText.setText(jSONObject.getString("_12K_HOLD_GRID_PEAK_SHAVING_VOLT"));
        }
        if (jSONObject.has("_12K_HOLD_GRID_PEAK_SHAVING_SOC")) {
            this.gridPeakShavingSoc1EditText.setText(jSONObject.getString("_12K_HOLD_GRID_PEAK_SHAVING_SOC"));
        }
        if (jSONObject.has("_12K_HOLD_GRID_PEAK_SHAVING_VOLT_2")) {
            this.gridPeakShavingVolt2EditText.setText(jSONObject.getString("_12K_HOLD_GRID_PEAK_SHAVING_VOLT_2"));
        }
        if (jSONObject.has("_12K_HOLD_GRID_PEAK_SHAVING_SOC_2")) {
            this.gridPeakShavingSoc2EditText.setText(jSONObject.getString("_12K_HOLD_GRID_PEAK_SHAVING_SOC_2"));
        }
        if (jSONObject.has("LSP_HOLD_DIS_CHG_POWER_TIME_37")) {
            this.peakShavingStartHour1EditText.setText(jSONObject.getString("LSP_HOLD_DIS_CHG_POWER_TIME_37"));
        }
        if (jSONObject.has("LSP_HOLD_DIS_CHG_POWER_TIME_38")) {
            this.peakShavingStartMinute1EditText.setText(jSONObject.getString("LSP_HOLD_DIS_CHG_POWER_TIME_38"));
        }
        if (jSONObject.has("LSP_HOLD_DIS_CHG_POWER_TIME_39")) {
            this.peakShavingEndHour1EditText.setText(jSONObject.getString("LSP_HOLD_DIS_CHG_POWER_TIME_39"));
        }
        if (jSONObject.has("LSP_HOLD_DIS_CHG_POWER_TIME_40")) {
            this.peakShavingEndMinute1EditText.setText(jSONObject.getString("LSP_HOLD_DIS_CHG_POWER_TIME_40"));
        }
        if (jSONObject.has("LSP_HOLD_DIS_CHG_POWER_TIME_41")) {
            this.peakShavingStartHour2EditText.setText(jSONObject.getString("LSP_HOLD_DIS_CHG_POWER_TIME_41"));
        }
        if (jSONObject.has("LSP_HOLD_DIS_CHG_POWER_TIME_42")) {
            this.peakShavingStartMinute2EditText.setText(jSONObject.getString("LSP_HOLD_DIS_CHG_POWER_TIME_42"));
        }
        if (jSONObject.has("LSP_HOLD_DIS_CHG_POWER_TIME_43")) {
            this.peakShavingEndHour2EditText.setText(jSONObject.getString("LSP_HOLD_DIS_CHG_POWER_TIME_43"));
        }
        if (jSONObject.has("LSP_HOLD_DIS_CHG_POWER_TIME_44")) {
            this.peakShavingEndMinute2EditText.setText(jSONObject.getString("LSP_HOLD_DIS_CHG_POWER_TIME_44"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toast(JSONObject jSONObject) {
        char c;
        try {
            PeakShavingActivity peakShavingActivity = instance;
            if (peakShavingActivity != null) {
                if (jSONObject == null) {
                    Toast.makeText(peakShavingActivity.getApplicationContext(), R.string.phrase_toast_network_error, 1).show();
                    return;
                }
                String string = jSONObject.getString("msg");
                switch (string.hashCode()) {
                    case -1814651686:
                        if (string.equals("DEVICE_OFFLINE")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -981622981:
                        if (string.equals("ACTION_ERROR_UNDONE")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -556338971:
                        if (string.equals("DATAFRAME_TIMEOUT")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case -553504479:
                        if (string.equals("INTEGER_FORMAT_ERROR")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -75347237:
                        if (string.equals("PARAM_EMPTY")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -75196522:
                        if (string.equals("PARAM_ERROR")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 154026365:
                        if (string.equals("DATAFRAME_UNSEND")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1027843992:
                        if (string.equals("REMOTE_READ_ERROR")) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1099156916:
                        if (string.equals("SERVER_HTTP_EXCEPTION")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1705806578:
                        if (string.equals("REMOTE_SET_ERROR")) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1939189941:
                        if (string.equals("OUT_RANGE_ERROR")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        Toast.makeText(instance.getApplicationContext(), R.string.page_maintain_remote_set_alert_param_empty, 1).show();
                        return;
                    case 1:
                        Toast.makeText(instance.getApplicationContext(), R.string.page_maintain_remote_set_alert_param_should_int, 1).show();
                        return;
                    case 2:
                        Toast.makeText(instance.getApplicationContext(), getString(R.string.page_maintain_remote_set_alert_param_out_range) + ": [" + jSONObject.getString("minValue") + ", " + jSONObject.getString("maxValue") + "]", 1).show();
                        return;
                    case 3:
                        Toast.makeText(instance.getApplicationContext(), R.string.page_maintain_remote_set_result_param_error, 1).show();
                        return;
                    case 4:
                        Toast.makeText(instance.getApplicationContext(), R.string.page_maintain_remote_set_result_set_undo, 1).show();
                        return;
                    case 5:
                        Toast.makeText(instance.getApplicationContext(), R.string.page_maintain_remote_set_result_device_offline, 1).show();
                        return;
                    case 6:
                        Toast.makeText(instance.getApplicationContext(), R.string.page_maintain_remote_set_result_command_not_send, 1).show();
                        return;
                    case 7:
                        Toast.makeText(instance.getApplicationContext(), R.string.page_maintain_remote_set_result_timeout, 1).show();
                        return;
                    case '\b':
                        Toast.makeText(instance.getApplicationContext(), R.string.page_maintain_remote_set_result_server_exception, 1).show();
                        return;
                    case '\t':
                    case '\n':
                        Toast.makeText(instance.getApplicationContext(), getString(R.string.page_maintain_remote_set_result_failed) + " " + jSONObject.getInt("errorCode"), 1).show();
                        return;
                    default:
                        Toast.makeText(instance.getApplicationContext(), R.string.page_maintain_remote_set_result_unknown_error, 1).show();
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearFragmentData() {
        if (this.created) {
            this.gridPeakShavingFunctionButton.setChecked(false);
            this.gridPeakShavingPowerEditText.setText("");
            this.gridPeakShavingVolt1EditText.setText("");
            this.gridPeakShavingSoc1EditText.setText("");
            this.gridPeakShavingVolt2EditText.setText("");
            this.gridPeakShavingSoc2EditText.setText("");
            this.peakShavingStartHour1EditText.setText("");
            this.peakShavingStartMinute1EditText.setText("");
            this.peakShavingEndHour1EditText.setText("");
            this.peakShavingEndMinute1EditText.setText("");
            this.peakShavingStartHour2EditText.setText("");
            this.peakShavingStartMinute2EditText.setText("");
            this.peakShavingEndHour2EditText.setText("");
            this.peakShavingEndMinute2EditText.setText("");
        }
    }
}
