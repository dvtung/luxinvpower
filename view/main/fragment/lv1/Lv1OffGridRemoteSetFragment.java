package com.nfcx.luxinvpower.view.main.fragment.lv1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.bean.set.REMOTE_WRITE_TYPE;
import com.nfcx.luxinvpower.global.bean.set.RemoteReadInfo;
import com.nfcx.luxinvpower.global.bean.set.RemoteWriteInfo;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.global.cache.RemoteSetCacheManager;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.InvTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.plant.PlantListActivity;
import com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Lv1OffGridRemoteSetFragment extends AbstractItemFragment {
    private Button acChargeEndBatterySocButton;
    private EditText acChargeEndBatterySocEditText;
    private Button acChargeEndBatteryVoltageButton;
    private EditText acChargeEndBatteryVoltageEditText;
    private EditText acChargeEndHour1EditText;
    private EditText acChargeEndHour2EditText;
    private EditText acChargeEndHourEditText;
    private EditText acChargeEndMinute1EditText;
    private EditText acChargeEndMinute2EditText;
    private EditText acChargeEndMinuteEditText;
    private Button acChargeEndTime1Button;
    private Button acChargeEndTime2Button;
    private Button acChargeEndTimeButton;
    private Button acChargeStartBatterySocButton;
    private EditText acChargeStartBatterySocEditText;
    private Button acChargeStartBatteryVoltageButton;
    private EditText acChargeStartBatteryVoltageEditText;
    private EditText acChargeStartHour1EditText;
    private EditText acChargeStartHour2EditText;
    private EditText acChargeStartHourEditText;
    private EditText acChargeStartMinute1EditText;
    private EditText acChargeStartMinute2EditText;
    private EditText acChargeStartMinuteEditText;
    private Button acChargeStartTime1Button;
    private Button acChargeStartTime2Button;
    private Button acChargeStartTimeButton;
    private Button acChargeTypeButton;
    private Spinner acChargeTypeSpinner;
    private Button acChgBatCurrentButton;
    private EditText acChgBatCurrentEditText;
    private EditText acFirstEndHour1EditText;
    private ConstraintLayout acFirstEndHour1Layout;
    private EditText acFirstEndHour2EditText;
    private ConstraintLayout acFirstEndHour2Layout;
    private EditText acFirstEndHourEditText;
    private ConstraintLayout acFirstEndHourLayout;
    private EditText acFirstEndMinute1EditText;
    private EditText acFirstEndMinute2EditText;
    private EditText acFirstEndMinuteEditText;
    private Button acFirstEndTime1Button;
    private Button acFirstEndTime2Button;
    private Button acFirstEndTimeButton;
    private EditText acFirstStartHour1EditText;
    private ConstraintLayout acFirstStartHour1Layout;
    private EditText acFirstStartHour2EditText;
    private ConstraintLayout acFirstStartHour2Layout;
    private EditText acFirstStartHourEditText;
    private ConstraintLayout acFirstStartHourLayout;
    private EditText acFirstStartMinute1EditText;
    private EditText acFirstStartMinute2EditText;
    private EditText acFirstStartMinuteEditText;
    private Button acFirstStartTime1Button;
    private Button acFirstStartTime2Button;
    private Button acFirstStartTimeButton;
    private ConstraintLayout applicationSetActionLayout;
    private TextView applicationSetActionTextView;
    private ToggleButton applicationSetActionToggleButton;
    private LinearLayout applicationSetParamLayout;
    private ConstraintLayout applicationSetTitleLayout;
    private Button batteryCapacityButton;
    private EditText batteryCapacityEditText;
    private ConstraintLayout batteryCapacityLayout;
    private ToggleButton batterySharedFunctionButton;
    private ConstraintLayout batterySharedFunctionLayout;
    private Button batteryWarningSocButton;
    private EditText batteryWarningSocEditText;
    private ConstraintLayout batteryWarningSocLayout;
    private Button batteryWarningVoltageButton;
    private EditText batteryWarningVoltageEditText;
    private ConstraintLayout batteryWarningVoltageLayout;
    private ToggleButton buzzerFunctionButton;
    private Button chargeCurrentButton;
    private EditText chargeCurrentEditText;
    private ConstraintLayout chargeCurrentLayout;
    private ConstraintLayout chargeSetActionLayout;
    private TextView chargeSetActionTextView;
    private ToggleButton chargeSetActionToggleButton;
    private LinearLayout chargeSetParamLayout;
    private Button composedPhaseButton;
    private ConstraintLayout composedPhaseLayout;
    private boolean created;
    private Button disChgControlButton;
    private Spinner disChgControlSpinner;
    private ConstraintLayout dischargeSetActionLayout;
    private TextView dischargeSetActionTextView;
    private ToggleButton dischargeSetActionToggleButton;
    private LinearLayout dischargeSetParamLayout;
    private Button dischgCurrentButton;
    private EditText dischgCurrentEditText;
    private Button epsFrequencySetButton;
    private ConstraintLayout epsFrequencySetLayout;
    private Spinner epsFrequencySetSpinner;
    private Button epsVoltageSetButton;
    private ConstraintLayout epsVoltageSetLayout;
    private Spinner epsVoltageSetSpinner;
    private Button equalizationPeriodButton;
    private EditText equalizationPeriodEditText;
    private ConstraintLayout equalizationPeriodLayout;
    private Button equalizationTimeButton;
    private EditText equalizationTimeEditText;
    private ConstraintLayout equalizationTimeLayout;
    private Button equalizationVoltageButton;
    private EditText equalizationVoltageEditText;
    private ConstraintLayout equalizationVoltageLayout;
    private ToggleButton feedInGridFunctionButton;
    private ConstraintLayout feedInGridFunctionLayout;
    private Button feedInGridPowerPercentButton;
    private EditText feedInGridPowerPercentEditText;
    private ConstraintLayout feedInGridPowerPercentLayout;
    private Button floatingVoltageButton;
    private EditText floatingVoltageEditText;
    private ConstraintLayout floatingVoltageLayout;
    private Fragment fragment;
    private ToggleButton greenFunctionButton;
    private ConstraintLayout greenFunctionLayout;
    private Inverter inverter;
    private List<Inverter> inverterList;
    private Spinner inverterSpinner;
    private Button leadAcidChargeVoltRefButton;
    private EditText leadAcidChargeVoltRefEditText;
    private ConstraintLayout leadAcidChargeVoltRefLayout;
    private Button leadAcidDischargeCutOffVoltButton;
    private EditText leadAcidDischargeCutOffVoltEditText;
    private Button lineModeInputButton;
    private ConstraintLayout lineModeInputLayout;
    private Spinner lineModeInputSpinner;
    private Button masterOrSlaveButton;
    private ConstraintLayout masterOrSlaveLayout;
    private Spinner masterOrSlaveSpinner;
    private Button maxGeneratorInputPowerButton;
    private EditText maxGeneratorInputPowerEditText;
    private ConstraintLayout maxGeneratorInputPowerLayout;
    private Button norminalBatteryVoltageButton;
    private EditText norminalBatteryVoltageEditText;
    private ConstraintLayout norminalBatteryVoltageLayout;
    private Button onGridEodSocButton;
    private EditText onGridEodSocEditText;
    private Button onGridEodVoltageButton;
    private EditText onGridEodVoltageEditText;
    private Button pvInputModeButton;
    private ConstraintLayout pvInputModeLayout;
    private Spinner pvInputModeSpinner;
    private Button readAllButton;
    private Spinner readComposedPhaseSpinner;
    private Spinner setComposedPhaseSpinner;
    private Button setTimeButton;
    private ToggleButton setToStandbyFunctionButton;
    private ConstraintLayout setToStandbyFunctionLayout;
    private Button socLowLimitEpsDischgButton;
    private EditText socLowLimitEpsDischgEditText;
    private ToggleButton takeLoadTogetherFunctionButton;
    private ConstraintLayout takeLoadTogetherFunctionLayout;
    private EditText timeDateEditText;
    private ConstraintLayout timeLayout;
    private EditText timeTimeEditText;

    public Lv1OffGridRemoteSetFragment() {
        super(42L);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_lv1_off_grid_remote_set, viewGroup, false);
        this.fragment = this;
        final UserData userData = GlobalInfo.getInstance().getUserData();
        if (!PLATFORM.LUX_POWER.equals(userData.getPlatform())) {
            inflate.findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        ((ConstraintLayout) inflate.findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) (ROLE.VIEWER.equals(userData.getRole()) ? PlantListActivity.class : PlantOverviewActivity.class)));
                MainActivity.instance.finish();
            }
        });
        ((ImageView) inflate.findViewById(R.id.userCenterImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) NewUserCenterActivity.class));
            }
        });
        this.inverterSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_inverter_spinner);
        if (userData.getCurrentPlant() != null) {
            this.inverterList = userData.getInvertersByPlant(userData.getCurrentPlant().getPlantId());
        }
        if (this.inverterList == null) {
            this.inverterList = new ArrayList();
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, this.inverterList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.inverterSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        Button button = (Button) inflate.findViewById(R.id.fragment_remote_set_readAllButton);
        this.readAllButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Lv1OffGridRemoteSetFragment.this.m355x6a88d1ca(view);
            }
        });
        this.timeLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_timeLayout);
        EditText editText = (EditText) inflate.findViewById(R.id.fragment_remote_set_timeDateEditText);
        this.timeDateEditText = editText;
        editText.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = Lv1OffGridRemoteSetFragment.this.timeDateEditText.getText().toString();
                if (Tool.isEmpty(obj) || obj.length() != 10) {
                    Lv1OffGridRemoteSetFragment.this.timeDateEditText.setText(InvTool.formatDate(new Date()));
                }
                Lv1OffGridRemoteSetFragment.this.getActivity().showDialog(6);
            }
        });
        EditText editText2 = (EditText) inflate.findViewById(R.id.fragment_remote_set_timeTimeEditText);
        this.timeTimeEditText = editText2;
        editText2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = Lv1OffGridRemoteSetFragment.this.timeTimeEditText.getText().toString();
                if (Tool.isEmpty(obj) || obj.length() != 5) {
                    Lv1OffGridRemoteSetFragment.this.timeTimeEditText.setText(InvTool.formatTime(new Date()).substring(0, 5));
                }
                Lv1OffGridRemoteSetFragment.this.getActivity().showDialog(7);
            }
        });
        Button button2 = (Button) inflate.findViewById(R.id.fragment_remote_set_timeButton);
        this.setTimeButton = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                Lv1OffGridRemoteSetFragment.this.runNormalRemoteWrite("HOLD_TIME", Lv1OffGridRemoteSetFragment.this.timeDateEditText.getText().toString().trim() + " " + Lv1OffGridRemoteSetFragment.this.timeTimeEditText.getText().toString().trim() + ":" + String.valueOf(calendar.get(13)), Lv1OffGridRemoteSetFragment.this.setTimeButton);
            }
        });
        this.pvInputModeLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_pvInputModeLayout);
        this.pvInputModeSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_pvInputModeSpinner);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_pv_input_mode_empty)));
        arrayList.add(new Property(String.valueOf(3), getString(R.string.phrase_param_pv_input_mode_off_grid_3)));
        arrayList.add(new Property(String.valueOf(4), getString(R.string.phrase_param_pv_input_mode_off_grid_4)));
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.pvInputModeSpinner.setAdapter((SpinnerAdapter) arrayAdapter2);
        Button button3 = (Button) inflate.findViewById(R.id.fragment_remote_set_pvInputModeButton);
        this.pvInputModeButton = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Lv1OffGridRemoteSetFragment.this.pvInputModeSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Lv1OffGridRemoteSetFragment.this.runNormalRemoteWrite("HOLD_PV_INPUT_MODE", property.getName(), Lv1OffGridRemoteSetFragment.this.pvInputModeButton);
            }
        });
        this.setToStandbyFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_setToStandbyFunctionLayout);
        ToggleButton toggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_setToStandbyFunctionButton);
        this.setToStandbyFunctionButton = toggleButton;
        toggleButton.setOnClickListener(new AnonymousClass7());
        this.batteryCapacityLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_batteryCapacityLayout);
        this.batteryCapacityEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_batteryCapacityEditText);
        Button button4 = (Button) inflate.findViewById(R.id.fragment_remote_set_batteryCapacityButton);
        this.batteryCapacityButton = button4;
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_BATTERY_CAPACITY", lv1OffGridRemoteSetFragment.batteryCapacityEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.batteryCapacityButton);
            }
        });
        this.norminalBatteryVoltageLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_norminalBatteryVoltageLayout);
        this.norminalBatteryVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_norminalBatteryVoltageEditText);
        Button button5 = (Button) inflate.findViewById(R.id.fragment_remote_set_norminalBatteryVoltageButton);
        this.norminalBatteryVoltageButton = button5;
        button5.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_NOMINAL_BATTERY_VOLTAGE", lv1OffGridRemoteSetFragment.norminalBatteryVoltageEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.norminalBatteryVoltageButton);
            }
        });
        ToggleButton toggleButton2 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_buzzerFunctionButton);
        this.buzzerFunctionButton = toggleButton2;
        toggleButton2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runControlRemoteWrite("FUNC_BUZZER_EN", lv1OffGridRemoteSetFragment.buzzerFunctionButton);
            }
        });
        this.greenFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_greenFunctionLayout);
        ToggleButton toggleButton3 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_greenFunctionButton);
        this.greenFunctionButton = toggleButton3;
        toggleButton3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runControlRemoteWrite("FUNC_GREEN_EN", lv1OffGridRemoteSetFragment.greenFunctionButton);
            }
        });
        this.epsVoltageSetLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_epsVoltageSetLayout);
        this.epsVoltageSetSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_epsVoltageSetSpinner);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_eps_voltage_set_empty)));
        arrayList2.add(new Property(String.valueOf(208), getString(R.string.phrase_param_eps_voltage_set_208)));
        arrayList2.add(new Property(String.valueOf(220), getString(R.string.phrase_param_eps_voltage_set_220)));
        arrayList2.add(new Property(String.valueOf(230), getString(R.string.phrase_param_eps_voltage_set_230)));
        arrayList2.add(new Property(String.valueOf(240), getString(R.string.phrase_param_eps_voltage_set_240)));
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.epsVoltageSetSpinner.setAdapter((SpinnerAdapter) arrayAdapter3);
        Button button6 = (Button) inflate.findViewById(R.id.fragment_remote_set_epsVoltageSetButton);
        this.epsVoltageSetButton = button6;
        button6.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Lv1OffGridRemoteSetFragment.this.epsVoltageSetSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Lv1OffGridRemoteSetFragment.this.runNormalRemoteWrite("HOLD_EPS_VOLT_SET", property.getName(), Lv1OffGridRemoteSetFragment.this.epsVoltageSetButton);
            }
        });
        this.epsFrequencySetLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_epsFrequencySetLayout);
        this.epsFrequencySetSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_epsFrequencySetSpinner);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_eps_frequency_set_empty)));
        arrayList3.add(new Property(String.valueOf(50), getString(R.string.phrase_param_eps_frequency_set_50)));
        arrayList3.add(new Property(String.valueOf(60), getString(R.string.phrase_param_eps_frequency_set_60)));
        ArrayAdapter arrayAdapter4 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList3);
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.epsFrequencySetSpinner.setAdapter((SpinnerAdapter) arrayAdapter4);
        Button button7 = (Button) inflate.findViewById(R.id.fragment_remote_set_epsFrequencySetButton);
        this.epsFrequencySetButton = button7;
        button7.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Lv1OffGridRemoteSetFragment.this.epsFrequencySetSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Lv1OffGridRemoteSetFragment.this.runNormalRemoteWrite("HOLD_EPS_FREQ_SET", property.getName(), Lv1OffGridRemoteSetFragment.this.epsFrequencySetButton);
            }
        });
        this.lineModeInputLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_lineModeInputLayout);
        this.lineModeInputSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_lineModeInputSpinner);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_line_mode_input_empty)));
        arrayList4.add(new Property(String.valueOf(0), getString(R.string.phrase_param_line_mode_input_0)));
        arrayList4.add(new Property(String.valueOf(1), getString(R.string.phrase_param_line_mode_input_1)));
        ArrayAdapter arrayAdapter5 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList4);
        arrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.lineModeInputSpinner.setAdapter((SpinnerAdapter) arrayAdapter5);
        Button button8 = (Button) inflate.findViewById(R.id.fragment_remote_set_lineModeInputButton);
        this.lineModeInputButton = button8;
        button8.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Lv1OffGridRemoteSetFragment.this.lineModeInputSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Lv1OffGridRemoteSetFragment.this.runNormalRemoteWrite("HOLD_LINE_MODE_INPUT", property.getName(), Lv1OffGridRemoteSetFragment.this.lineModeInputButton);
            }
        });
        this.maxGeneratorInputPowerLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_maxGeneratorInputPowerLayout);
        this.maxGeneratorInputPowerEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_maxGeneratorInputPowerEditText);
        Button button9 = (Button) inflate.findViewById(R.id.fragment_remote_set_maxGeneratorInputPowerButton);
        this.maxGeneratorInputPowerButton = button9;
        button9.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_MAX_GENERATOR_INPUT_POWER", lv1OffGridRemoteSetFragment.maxGeneratorInputPowerEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.maxGeneratorInputPowerButton);
            }
        });
        this.acFirstStartHourLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_acFirstStartHourLayout);
        this.acFirstStartHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstStartHourEditText);
        this.acFirstStartMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstStartMinuteEditText);
        Button button10 = (Button) inflate.findViewById(R.id.fragment_remote_set_acFirstStartTimeButton);
        this.acFirstStartTimeButton = button10;
        button10.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_FIRST_START_TIME", lv1OffGridRemoteSetFragment.acFirstStartHourEditText, Lv1OffGridRemoteSetFragment.this.acFirstStartMinuteEditText, Lv1OffGridRemoteSetFragment.this.acFirstStartTimeButton);
            }
        });
        this.acFirstEndHourLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_acFirstEndHourLayout);
        this.acFirstEndHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstEndHourEditText);
        this.acFirstEndMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstEndMinuteEditText);
        Button button11 = (Button) inflate.findViewById(R.id.fragment_remote_set_acFirstEndTimeButton);
        this.acFirstEndTimeButton = button11;
        button11.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_FIRST_END_TIME", lv1OffGridRemoteSetFragment.acFirstEndHourEditText, Lv1OffGridRemoteSetFragment.this.acFirstEndMinuteEditText, Lv1OffGridRemoteSetFragment.this.acFirstEndTimeButton);
            }
        });
        this.acFirstStartHour1Layout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_acFirstStartHour1Layout);
        this.acFirstStartHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstStartHour1EditText);
        this.acFirstStartMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstStartMinute1EditText);
        Button button12 = (Button) inflate.findViewById(R.id.fragment_remote_set_acFirstStartTime1Button);
        this.acFirstStartTime1Button = button12;
        button12.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_FIRST_START_TIME_1", lv1OffGridRemoteSetFragment.acFirstStartHour1EditText, Lv1OffGridRemoteSetFragment.this.acFirstStartMinute1EditText, Lv1OffGridRemoteSetFragment.this.acFirstStartTime1Button);
            }
        });
        this.acFirstEndHour1Layout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_acFirstEndHour1Layout);
        this.acFirstEndHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstEndHour1EditText);
        this.acFirstEndMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstEndMinute1EditText);
        Button button13 = (Button) inflate.findViewById(R.id.fragment_remote_set_acFirstEndTime1Button);
        this.acFirstEndTime1Button = button13;
        button13.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_FIRST_END_TIME_1", lv1OffGridRemoteSetFragment.acFirstEndHour1EditText, Lv1OffGridRemoteSetFragment.this.acFirstEndMinute1EditText, Lv1OffGridRemoteSetFragment.this.acFirstEndTime1Button);
            }
        });
        this.acFirstStartHour2Layout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_acFirstStartHour2Layout);
        this.acFirstStartHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstStartHour2EditText);
        this.acFirstStartMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstStartMinute2EditText);
        Button button14 = (Button) inflate.findViewById(R.id.fragment_remote_set_acFirstStartTime2Button);
        this.acFirstStartTime2Button = button14;
        button14.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_FIRST_START_TIME_2", lv1OffGridRemoteSetFragment.acFirstStartHour2EditText, Lv1OffGridRemoteSetFragment.this.acFirstStartMinute2EditText, Lv1OffGridRemoteSetFragment.this.acFirstStartTime2Button);
            }
        });
        this.acFirstEndHour2Layout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_acFirstEndHour2Layout);
        this.acFirstEndHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstEndHour2EditText);
        this.acFirstEndMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acFirstEndMinute2EditText);
        Button button15 = (Button) inflate.findViewById(R.id.fragment_remote_set_acFirstEndTime2Button);
        this.acFirstEndTime2Button = button15;
        button15.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_FIRST_END_TIME_2", lv1OffGridRemoteSetFragment.acFirstEndHour2EditText, Lv1OffGridRemoteSetFragment.this.acFirstEndMinute2EditText, Lv1OffGridRemoteSetFragment.this.acFirstEndTime2Button);
            }
        });
        this.batterySharedFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_batterySharedFunctionLayout);
        ToggleButton toggleButton4 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_batterySharedFunctionButton);
        this.batterySharedFunctionButton = toggleButton4;
        toggleButton4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runControlRemoteWrite("FUNC_BAT_SHARED", lv1OffGridRemoteSetFragment.batterySharedFunctionButton);
            }
        });
        this.masterOrSlaveLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_masterOrSlaveLayout);
        this.masterOrSlaveSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_masterOrSlaveSpinner);
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_master_or_slave_empty)));
        arrayList5.add(new Property(String.valueOf(0), getString(R.string.phrase_param_master_or_slave_offgrid_0)));
        arrayList5.add(new Property(String.valueOf(1), getString(R.string.phrase_param_master_or_slave_offgrid_1)));
        arrayList5.add(new Property(String.valueOf(3), getString(R.string.phrase_param_master_or_slave_offgrid_3)));
        ArrayAdapter arrayAdapter6 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList5);
        arrayAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.masterOrSlaveSpinner.setAdapter((SpinnerAdapter) arrayAdapter6);
        Button button16 = (Button) inflate.findViewById(R.id.fragment_remote_set_masterOrSlaveButton);
        this.masterOrSlaveButton = button16;
        button16.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Lv1OffGridRemoteSetFragment.this.masterOrSlaveSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Lv1OffGridRemoteSetFragment.this.runNormalRemoteWrite("HOLD_SET_MASTER_OR_SLAVE", property.getName(), Lv1OffGridRemoteSetFragment.this.masterOrSlaveButton);
            }
        });
        this.composedPhaseLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_composedPhaseLayout);
        this.readComposedPhaseSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_readComposedPhaseSpinner);
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add(new Property(String.valueOf(-1), "--"));
        arrayList6.add(new Property(String.valueOf(1), getString(R.string.phrase_param_composed_phase_1)));
        arrayList6.add(new Property(String.valueOf(2), getString(R.string.phrase_param_composed_phase_2)));
        arrayList6.add(new Property(String.valueOf(3), getString(R.string.phrase_param_composed_phase_3)));
        ArrayAdapter arrayAdapter7 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList6);
        arrayAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.readComposedPhaseSpinner.setAdapter((SpinnerAdapter) arrayAdapter7);
        this.readComposedPhaseSpinner.setEnabled(false);
        this.setComposedPhaseSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_setComposedPhaseSpinner);
        ArrayList arrayList7 = new ArrayList();
        arrayList7.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_composed_phase_empty)));
        arrayList7.add(new Property(String.valueOf(1), getString(R.string.phrase_param_composed_phase_1)));
        arrayList7.add(new Property(String.valueOf(2), getString(R.string.phrase_param_composed_phase_2)));
        arrayList7.add(new Property(String.valueOf(3), getString(R.string.phrase_param_composed_phase_3)));
        ArrayAdapter arrayAdapter8 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList7);
        arrayAdapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setComposedPhaseSpinner.setAdapter((SpinnerAdapter) arrayAdapter8);
        Button button17 = (Button) inflate.findViewById(R.id.fragment_remote_set_composedPhaseButton);
        this.composedPhaseButton = button17;
        button17.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Lv1OffGridRemoteSetFragment.this.setComposedPhaseSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Lv1OffGridRemoteSetFragment.this.runNormalRemoteWrite("HOLD_SET_COMPOSED_PHASE", property.getName(), Lv1OffGridRemoteSetFragment.this.composedPhaseButton);
            }
        });
        this.takeLoadTogetherFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_takeLoadTogetherFunctionLayout);
        ToggleButton toggleButton5 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_takeLoadTogetherFunctionButton);
        this.takeLoadTogetherFunctionButton = toggleButton5;
        toggleButton5.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runControlRemoteWrite("FUNC_TAKE_LOAD_TOGETHER", lv1OffGridRemoteSetFragment.takeLoadTogetherFunctionButton);
            }
        });
        this.feedInGridFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_feedInGridFunctionLayout);
        ToggleButton toggleButton6 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_feedInGridFunctionButton);
        this.feedInGridFunctionButton = toggleButton6;
        toggleButton6.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runControlRemoteWrite("FUNC_FEED_IN_GRID_EN", lv1OffGridRemoteSetFragment.feedInGridFunctionButton);
            }
        });
        this.feedInGridPowerPercentLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_feedInGridPowerPercentLayout);
        this.feedInGridPowerPercentEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_feedInGridPowerPercentEditText);
        Button button18 = (Button) inflate.findViewById(R.id.fragment_remote_set_feedInGridPowerPercentButton);
        this.feedInGridPowerPercentButton = button18;
        button18.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_FEED_IN_GRID_POWER_PERCENT", lv1OffGridRemoteSetFragment.feedInGridPowerPercentEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.feedInGridPowerPercentButton);
            }
        });
        this.onGridEodVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_onGridEodVoltageEditText);
        Button button19 = (Button) inflate.findViewById(R.id.fragment_remote_set_onGridEodVoltageButton);
        this.onGridEodVoltageButton = button19;
        button19.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_ON_GRID_EOD_VOLTAGE", lv1OffGridRemoteSetFragment.onGridEodVoltageEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.onGridEodVoltageButton);
            }
        });
        this.onGridEodSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_onGridEodSocEditText);
        Button button20 = (Button) inflate.findViewById(R.id.fragment_remote_set_onGridEodSocButton);
        this.onGridEodSocButton = button20;
        button20.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_DISCHG_CUT_OFF_SOC_EOD", lv1OffGridRemoteSetFragment.onGridEodSocEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.onGridEodSocButton);
            }
        });
        this.chargeCurrentLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_chargeCurrentLayout);
        this.chargeCurrentEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_chargeCurrentEditText);
        Button button21 = (Button) inflate.findViewById(R.id.fragment_remote_set_chargeCurrentButton);
        this.chargeCurrentButton = button21;
        button21.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_CHARGE_RATE", lv1OffGridRemoteSetFragment.chargeCurrentEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.chargeCurrentButton);
            }
        });
        this.leadAcidChargeVoltRefLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_leadAcidChargeVoltRefLayout);
        this.leadAcidChargeVoltRefEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_leadAcidChargeVoltRefEditText);
        Button button22 = (Button) inflate.findViewById(R.id.fragment_remote_set_leadAcidChargeVoltRefButton);
        this.leadAcidChargeVoltRefButton = button22;
        button22.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_CHARGE_VOLT_REF", lv1OffGridRemoteSetFragment.leadAcidChargeVoltRefEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.leadAcidChargeVoltRefButton);
            }
        });
        this.floatingVoltageLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_floatingVoltageLayout);
        this.floatingVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_floatingVoltageEditText);
        Button button23 = (Button) inflate.findViewById(R.id.fragment_remote_set_floatingVoltageButton);
        this.floatingVoltageButton = button23;
        button23.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_FLOATING_VOLTAGE", lv1OffGridRemoteSetFragment.floatingVoltageEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.floatingVoltageButton);
            }
        });
        this.equalizationVoltageLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_equalizationVoltageLayout);
        this.equalizationVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_equalizationVoltageEditText);
        Button button24 = (Button) inflate.findViewById(R.id.fragment_remote_set_equalizationVoltageButton);
        this.equalizationVoltageButton = button24;
        button24.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_EQUALIZATION_VOLTAGE", lv1OffGridRemoteSetFragment.equalizationVoltageEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.equalizationVoltageButton);
            }
        });
        this.equalizationPeriodLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_equalizationPeriodLayout);
        this.equalizationPeriodEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_equalizationPeriodEditText);
        Button button25 = (Button) inflate.findViewById(R.id.fragment_remote_set_equalizationPeriodButton);
        this.equalizationPeriodButton = button25;
        button25.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_EQUALIZATION_PERIOD", lv1OffGridRemoteSetFragment.equalizationPeriodEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.equalizationPeriodButton);
            }
        });
        this.equalizationTimeLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_equalizationTimeLayout);
        this.equalizationTimeEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_equalizationTimeEditText);
        Button button26 = (Button) inflate.findViewById(R.id.fragment_remote_set_equalizationTimeButton);
        this.equalizationTimeButton = button26;
        button26.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_EQUALIZATION_TIME", lv1OffGridRemoteSetFragment.equalizationTimeEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.equalizationTimeButton);
            }
        });
        this.acChargeTypeSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_acChargeTypeSpinner);
        ArrayList arrayList8 = new ArrayList();
        arrayList8.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_ac_charge_type_empty)));
        arrayList8.add(new Property(String.valueOf(0), getString(R.string.phrase_param_ac_charge_type_0)));
        arrayList8.add(new Property(String.valueOf(1), getString(R.string.phrase_param_ac_charge_type_1)));
        arrayList8.add(new Property(String.valueOf(2), getString(R.string.phrase_param_ac_charge_type_2)));
        arrayList8.add(new Property(String.valueOf(3), getString(R.string.phrase_param_ac_charge_type_3)));
        arrayList8.add(new Property(String.valueOf(4), getString(R.string.phrase_param_ac_charge_type_4)));
        arrayList8.add(new Property(String.valueOf(5), getString(R.string.phrase_param_ac_charge_type_5)));
        ArrayAdapter arrayAdapter9 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList8);
        arrayAdapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.acChargeTypeSpinner.setAdapter((SpinnerAdapter) arrayAdapter9);
        Button button27 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeTypeButton);
        this.acChargeTypeButton = button27;
        button27.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Lv1OffGridRemoteSetFragment.this.acChargeTypeSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Lv1OffGridRemoteSetFragment.this.runBitRemoteWrite("BIT_AC_CHARGE_TYPE", property.getName(), Lv1OffGridRemoteSetFragment.this.acChargeTypeButton);
            }
        });
        this.acChgBatCurrentEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChgBatCurrentEditText);
        Button button28 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChgBatCurrentButton);
        this.acChgBatCurrentButton = button28;
        button28.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_BATTERY_CURRENT", lv1OffGridRemoteSetFragment.acChgBatCurrentEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.acChgBatCurrentButton);
            }
        });
        this.acChargeStartHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartHourEditText);
        this.acChargeStartMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartMinuteEditText);
        Button button29 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartTimeButton);
        this.acChargeStartTimeButton = button29;
        button29.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.38
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_START_TIME", lv1OffGridRemoteSetFragment.acChargeStartHourEditText, Lv1OffGridRemoteSetFragment.this.acChargeStartMinuteEditText, Lv1OffGridRemoteSetFragment.this.acChargeStartTimeButton);
            }
        });
        this.acChargeEndHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndHourEditText);
        this.acChargeEndMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndMinuteEditText);
        Button button30 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndTimeButton);
        this.acChargeEndTimeButton = button30;
        button30.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.39
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_END_TIME", lv1OffGridRemoteSetFragment.acChargeEndHourEditText, Lv1OffGridRemoteSetFragment.this.acChargeEndMinuteEditText, Lv1OffGridRemoteSetFragment.this.acChargeEndTimeButton);
            }
        });
        this.acChargeStartHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartHour1EditText);
        this.acChargeStartMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartMinute1EditText);
        Button button31 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartTime1Button);
        this.acChargeStartTime1Button = button31;
        button31.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_START_TIME_1", lv1OffGridRemoteSetFragment.acChargeStartHour1EditText, Lv1OffGridRemoteSetFragment.this.acChargeStartMinute1EditText, Lv1OffGridRemoteSetFragment.this.acChargeStartTime1Button);
            }
        });
        this.acChargeEndHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndHour1EditText);
        this.acChargeEndMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndMinute1EditText);
        Button button32 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndTime1Button);
        this.acChargeEndTime1Button = button32;
        button32.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_END_TIME_1", lv1OffGridRemoteSetFragment.acChargeEndHour1EditText, Lv1OffGridRemoteSetFragment.this.acChargeEndMinute1EditText, Lv1OffGridRemoteSetFragment.this.acChargeEndTime1Button);
            }
        });
        this.acChargeStartHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartHour2EditText);
        this.acChargeStartMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartMinute2EditText);
        Button button33 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartTime2Button);
        this.acChargeStartTime2Button = button33;
        button33.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.42
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_START_TIME_2", lv1OffGridRemoteSetFragment.acChargeStartHour2EditText, Lv1OffGridRemoteSetFragment.this.acChargeStartMinute2EditText, Lv1OffGridRemoteSetFragment.this.acChargeStartTime2Button);
            }
        });
        this.acChargeEndHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndHour2EditText);
        this.acChargeEndMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndMinute2EditText);
        Button button34 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndTime2Button);
        this.acChargeEndTime2Button = button34;
        button34.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.43
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_END_TIME_2", lv1OffGridRemoteSetFragment.acChargeEndHour2EditText, Lv1OffGridRemoteSetFragment.this.acChargeEndMinute2EditText, Lv1OffGridRemoteSetFragment.this.acChargeEndTime2Button);
            }
        });
        this.acChargeStartBatteryVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartBatteryVoltageEditText);
        Button button35 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartBatteryVoltageButton);
        this.acChargeStartBatteryVoltageButton = button35;
        button35.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.44
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_START_BATTERY_VOLTAGE", lv1OffGridRemoteSetFragment.acChargeStartBatteryVoltageEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.acChargeStartBatteryVoltageButton);
            }
        });
        this.acChargeEndBatteryVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndBatteryVoltageEditText);
        Button button36 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndBatteryVoltageButton);
        this.acChargeEndBatteryVoltageButton = button36;
        button36.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.45
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_END_BATTERY_VOLTAGE", lv1OffGridRemoteSetFragment.acChargeEndBatteryVoltageEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.acChargeEndBatteryVoltageButton);
            }
        });
        this.acChargeStartBatterySocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartBatterySocEditText);
        Button button37 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartBatterySocButton);
        this.acChargeStartBatterySocButton = button37;
        button37.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.46
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_START_BATTERY_SOC", lv1OffGridRemoteSetFragment.acChargeStartBatterySocEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.acChargeStartBatterySocButton);
            }
        });
        this.acChargeEndBatterySocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndBatterySocEditText);
        Button button38 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndBatterySocButton);
        this.acChargeEndBatterySocButton = button38;
        button38.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.47
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_END_BATTERY_SOC", lv1OffGridRemoteSetFragment.acChargeEndBatterySocEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.acChargeEndBatterySocButton);
            }
        });
        this.disChgControlSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_disChgControlSpinner);
        ArrayList arrayList9 = new ArrayList();
        arrayList9.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_discharge_control_empty)));
        arrayList9.add(new Property(String.valueOf(0), getString(R.string.phrase_param_discharge_control_0)));
        arrayList9.add(new Property(String.valueOf(1), getString(R.string.phrase_param_discharge_control_1)));
        ArrayAdapter arrayAdapter10 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList9);
        arrayAdapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.disChgControlSpinner.setAdapter((SpinnerAdapter) arrayAdapter10);
        Button button39 = (Button) inflate.findViewById(R.id.fragment_remote_set_disChgControlButton);
        this.disChgControlButton = button39;
        button39.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.48
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Lv1OffGridRemoteSetFragment.this.disChgControlSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Lv1OffGridRemoteSetFragment.this.runBitRemoteWrite("BIT_DISCHG_CONTROL_TYPE", property.getName(), Lv1OffGridRemoteSetFragment.this.disChgControlButton);
            }
        });
        this.dischgCurrentEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_dischgCurrentEditText);
        Button button40 = (Button) inflate.findViewById(R.id.fragment_remote_set_dischgCurrentButton);
        this.dischgCurrentButton = button40;
        button40.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.49
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_DISCHARGE_RATE", lv1OffGridRemoteSetFragment.dischgCurrentEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.dischgCurrentButton);
            }
        });
        this.batteryWarningVoltageLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_batteryWarningVoltageLayout);
        this.batteryWarningVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_batteryWarningVoltageEditText);
        Button button41 = (Button) inflate.findViewById(R.id.fragment_remote_set_batteryWarningVoltageButton);
        this.batteryWarningVoltageButton = button41;
        button41.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.50
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_BATTERY_WARNING_VOLTAGE", lv1OffGridRemoteSetFragment.batteryWarningVoltageEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.batteryWarningVoltageButton);
            }
        });
        this.batteryWarningSocLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_batteryWarningSocLayout);
        this.batteryWarningSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_batteryWarningSocEditText);
        Button button42 = (Button) inflate.findViewById(R.id.fragment_remote_set_batteryWarningSocButton);
        this.batteryWarningSocButton = button42;
        button42.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.51
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_BATTERY_WARNING_SOC", lv1OffGridRemoteSetFragment.batteryWarningSocEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.batteryWarningSocButton);
            }
        });
        this.leadAcidDischargeCutOffVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_leadAcidDischargeCutOffVoltEditText);
        Button button43 = (Button) inflate.findViewById(R.id.fragment_remote_set_leadAcidDischargeCutOffVoltButton);
        this.leadAcidDischargeCutOffVoltButton = button43;
        button43.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.52
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT", lv1OffGridRemoteSetFragment.leadAcidDischargeCutOffVoltEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.leadAcidDischargeCutOffVoltButton);
            }
        });
        this.socLowLimitEpsDischgEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_socLowLimitEpsDischgEditText);
        Button button44 = (Button) inflate.findViewById(R.id.fragment_remote_set_socLowLimitEpsDischgButton);
        this.socLowLimitEpsDischgButton = button44;
        button44.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.53
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
                lv1OffGridRemoteSetFragment.runNormalRemoteWrite("HOLD_SOC_LOW_LIMIT_EPS_DISCHG", lv1OffGridRemoteSetFragment.socLowLimitEpsDischgEditText.getText().toString().trim(), Lv1OffGridRemoteSetFragment.this.socLowLimitEpsDischgButton);
            }
        });
        this.applicationSetTitleLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_application_set_titleLayout);
        this.applicationSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_application_set_layout);
        this.applicationSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_application_set_textView);
        this.applicationSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_application_set_toggleButton);
        this.applicationSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_application_set_paramLayout);
        this.applicationSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.54
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OffGridRemoteSetFragment.this.applicationSetActionToggleButton.isChecked()) {
                    Lv1OffGridRemoteSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_collapse);
                    Lv1OffGridRemoteSetFragment.this.applicationSetParamLayout.setVisibility(0);
                } else {
                    Lv1OffGridRemoteSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_expand);
                    Lv1OffGridRemoteSetFragment.this.applicationSetParamLayout.setVisibility(8);
                }
            }
        });
        this.applicationSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.55
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OffGridRemoteSetFragment.this.applicationSetActionToggleButton.isChecked()) {
                    Lv1OffGridRemoteSetFragment.this.applicationSetActionToggleButton.setChecked(false);
                    Lv1OffGridRemoteSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_expand);
                    Lv1OffGridRemoteSetFragment.this.applicationSetParamLayout.setVisibility(8);
                } else {
                    Lv1OffGridRemoteSetFragment.this.applicationSetActionToggleButton.setChecked(true);
                    Lv1OffGridRemoteSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_collapse);
                    Lv1OffGridRemoteSetFragment.this.applicationSetParamLayout.setVisibility(0);
                }
            }
        });
        this.chargeSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_layout);
        this.chargeSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_textView);
        this.chargeSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_toggleButton);
        this.chargeSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_paramLayout);
        this.chargeSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.56
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OffGridRemoteSetFragment.this.chargeSetActionToggleButton.isChecked()) {
                    Lv1OffGridRemoteSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    Lv1OffGridRemoteSetFragment.this.chargeSetParamLayout.setVisibility(0);
                } else {
                    Lv1OffGridRemoteSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_expand);
                    Lv1OffGridRemoteSetFragment.this.chargeSetParamLayout.setVisibility(8);
                }
            }
        });
        this.chargeSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.57
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OffGridRemoteSetFragment.this.chargeSetActionToggleButton.isChecked()) {
                    Lv1OffGridRemoteSetFragment.this.chargeSetActionToggleButton.setChecked(false);
                    Lv1OffGridRemoteSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_expand);
                    Lv1OffGridRemoteSetFragment.this.chargeSetParamLayout.setVisibility(8);
                } else {
                    Lv1OffGridRemoteSetFragment.this.chargeSetActionToggleButton.setChecked(true);
                    Lv1OffGridRemoteSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    Lv1OffGridRemoteSetFragment.this.chargeSetParamLayout.setVisibility(0);
                }
            }
        });
        this.dischargeSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_layout);
        this.dischargeSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_textView);
        this.dischargeSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_toggleButton);
        this.dischargeSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_paramLayout);
        this.dischargeSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.58
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OffGridRemoteSetFragment.this.dischargeSetActionToggleButton.isChecked()) {
                    Lv1OffGridRemoteSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    Lv1OffGridRemoteSetFragment.this.dischargeSetParamLayout.setVisibility(0);
                } else {
                    Lv1OffGridRemoteSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_expand);
                    Lv1OffGridRemoteSetFragment.this.dischargeSetParamLayout.setVisibility(8);
                }
            }
        });
        this.dischargeSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.59
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OffGridRemoteSetFragment.this.dischargeSetActionToggleButton.isChecked()) {
                    Lv1OffGridRemoteSetFragment.this.dischargeSetActionToggleButton.setChecked(false);
                    Lv1OffGridRemoteSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_expand);
                    Lv1OffGridRemoteSetFragment.this.dischargeSetParamLayout.setVisibility(8);
                } else {
                    Lv1OffGridRemoteSetFragment.this.dischargeSetActionToggleButton.setChecked(true);
                    Lv1OffGridRemoteSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    Lv1OffGridRemoteSetFragment.this.dischargeSetParamLayout.setVisibility(0);
                }
            }
        });
        if (userData.isInstallerLevel() || ROLE.VIEWER.equals(userData.getRole())) {
            this.timeLayout.setVisibility(8);
            this.pvInputModeLayout.setVisibility(8);
            this.batteryCapacityLayout.setVisibility(8);
            this.norminalBatteryVoltageLayout.setVisibility(8);
            this.greenFunctionLayout.setVisibility(8);
        }
        if (ROLE.VIEWER.equals(userData.getRole())) {
            this.setToStandbyFunctionLayout.setVisibility(8);
            this.epsVoltageSetLayout.setVisibility(8);
            this.epsFrequencySetLayout.setVisibility(8);
            this.lineModeInputLayout.setVisibility(8);
            this.maxGeneratorInputPowerLayout.setVisibility(8);
            this.batterySharedFunctionLayout.setVisibility(8);
            this.masterOrSlaveLayout.setVisibility(8);
            this.composedPhaseLayout.setVisibility(8);
            this.equalizationVoltageLayout.setVisibility(8);
            this.equalizationPeriodLayout.setVisibility(8);
            this.equalizationTimeLayout.setVisibility(8);
        }
        this.created = true;
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$0$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OffGridRemoteSetFragment, reason: not valid java name */
    public /* synthetic */ void m355x6a88d1ca(View view) {
        if (this.inverter != null) {
            RemoteSetCacheManager.getInstance().clearCache(this.inverter.getSerialNum());
            clearFragmentData();
            this.readAllButton.setEnabled(false);
            if (this.inverter.supportRead127Register()) {
                new ReadMultiParamTask(this).execute(new RemoteReadInfo(this.inverter.getSerialNum(), 0, 127), new RemoteReadInfo(this.inverter.getSerialNum(), 127, 127));
            } else {
                new ReadMultiParamTask(this).execute(new RemoteReadInfo(this.inverter.getSerialNum(), 0, 40), new RemoteReadInfo(this.inverter.getSerialNum(), 40, 40), new RemoteReadInfo(this.inverter.getSerialNum(), 80, 40), new RemoteReadInfo(this.inverter.getSerialNum(), 120, 40), new RemoteReadInfo(this.inverter.getSerialNum(), Opcodes.IF_ICMPNE, 20));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment$7, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass7 implements View.OnClickListener {
        AnonymousClass7() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (Lv1OffGridRemoteSetFragment.this.inverter != null) {
                boolean isChecked = Lv1OffGridRemoteSetFragment.this.setToStandbyFunctionButton.isChecked();
                AlertDialog.Builder builder = new AlertDialog.Builder(Lv1OffGridRemoteSetFragment.this.fragment.getActivity());
                builder.setTitle(isChecked ? R.string.phrase_func_param_normaly : R.string.phrase_func_param_standby).setIcon(android.R.drawable.ic_dialog_info).setMessage(Lv1OffGridRemoteSetFragment.this.getString(isChecked ? R.string.phrase_func_text_normal : R.string.phrase_func_text_standby) + " " + Lv1OffGridRemoteSetFragment.this.inverter.getSerialNum()).setPositiveButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment$7$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        Lv1OffGridRemoteSetFragment.AnonymousClass7.this.m356xe4f019ac(dialogInterface, i);
                    }
                }).setNegativeButton(R.string.phrase_button_cancel, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment$7$$ExternalSyntheticLambda1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        Lv1OffGridRemoteSetFragment.AnonymousClass7.this.m357x189e446d(dialogInterface, i);
                    }
                });
                builder.show();
                return;
            }
            Lv1OffGridRemoteSetFragment.this.setToStandbyFunctionButton.setChecked(!Lv1OffGridRemoteSetFragment.this.setToStandbyFunctionButton.isChecked());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OffGridRemoteSetFragment$7, reason: not valid java name */
        public /* synthetic */ void m356xe4f019ac(DialogInterface dialogInterface, int i) {
            Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = Lv1OffGridRemoteSetFragment.this;
            lv1OffGridRemoteSetFragment.runControlRemoteWrite("FUNC_SET_TO_STANDBY", lv1OffGridRemoteSetFragment.setToStandbyFunctionButton);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$1$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OffGridRemoteSetFragment$7, reason: not valid java name */
        public /* synthetic */ void m357x189e446d(DialogInterface dialogInterface, int i) {
            Lv1OffGridRemoteSetFragment.this.setToStandbyFunctionButton.setChecked(!Lv1OffGridRemoteSetFragment.this.setToStandbyFunctionButton.isChecked());
        }
    }

    private void initInverterSpinnerOnItemSelectedListener() {
        if (this.inverterSpinner.getOnItemSelectedListener() == null) {
            System.out.println("LuxPower - Lv1OffGridRemoteSetFragment initInverterSpinnerOnItemSelectedListener...");
            this.inverterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment.60
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    Lv1OffGridRemoteSetFragment.this.updateSelectInverter((Inverter) Lv1OffGridRemoteSetFragment.this.inverterSpinner.getSelectedItem());
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                    if (Lv1OffGridRemoteSetFragment.this.inverter != null) {
                        Lv1OffGridRemoteSetFragment.this.inverter = null;
                        GlobalInfo.getInstance().getUserData().setCurrentInverter(Lv1OffGridRemoteSetFragment.this.inverter, true);
                        Lv1OffGridRemoteSetFragment.this.clearFragmentData();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSelectInverter(Inverter inverter) {
        System.out.println("LuxPower - Lv1OffGridRemoteSetFragment selectInverter = " + inverter.getSerialNum() + ", inverter = " + this.inverter);
        Inverter inverter2 = this.inverter;
        if (inverter2 == null || !inverter2.getSerialNum().equals(inverter.getSerialNum())) {
            this.inverter = inverter;
            GlobalInfo.getInstance().getUserData().setCurrentInverter(this.inverter, true);
            if (!this.inverter.isSnaSeries()) {
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

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - Lv1OffGridRemoteSetFragment onResume...");
        initInverterSpinnerOnItemSelectedListener();
        refreshFragmentParams();
    }

    public void refreshFragmentParams() {
        Inverter inverter;
        if (this.created) {
            UserData userData = GlobalInfo.getInstance().getUserData();
            if (userData.getCurrentInverter() == null || this.inverterList == null) {
                return;
            }
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

    /* JADX INFO: Access modifiers changed from: private */
    public void runNormalRemoteWrite(String str, String str2, Button button) {
        if (this.inverter != null) {
            RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
            remoteWriteInfo.setSerialNum(this.inverter.getSerialNum());
            remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.NORMAL);
            remoteWriteInfo.setHoldParam(str);
            remoteWriteInfo.setValueText(str2);
            remoteWriteInfo.setSetButton(button);
            new WriteParamTask(this).execute(remoteWriteInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runBitRemoteWrite(String str, String str2, Button button) {
        if (this.inverter != null) {
            RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
            remoteWriteInfo.setSerialNum(this.inverter.getSerialNum());
            remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.BIT_PARAM);
            remoteWriteInfo.setBitParam(str);
            remoteWriteInfo.setValueText(str2);
            remoteWriteInfo.setSetButton(button);
            new WriteParamTask(this).execute(remoteWriteInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runTimeRemoteWrite(String str, EditText editText, EditText editText2, Button button) {
        if (this.inverter != null) {
            RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
            remoteWriteInfo.setSerialNum(this.inverter.getSerialNum());
            remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.TIME);
            remoteWriteInfo.setTimeParam(str);
            remoteWriteInfo.setHourText(editText.getText().toString().trim());
            remoteWriteInfo.setMinuteText(editText2.getText().toString().trim());
            remoteWriteInfo.setSetButton(button);
            new WriteParamTask(this).execute(remoteWriteInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runControlRemoteWrite(String str, ToggleButton toggleButton) {
        if (this.inverter != null) {
            RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
            remoteWriteInfo.setSerialNum(this.inverter.getSerialNum());
            remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.CONTROL);
            remoteWriteInfo.setFunctionParam(str);
            remoteWriteInfo.setFunctionToggleButtonChecked(toggleButton.isChecked());
            remoteWriteInfo.setFunctionToggleButton(toggleButton);
            new WriteParamTask(this).execute(remoteWriteInfo);
        }
    }

    public void clearFragmentData() {
        if (this.created) {
            this.timeDateEditText.setText("");
            this.timeTimeEditText.setText("");
            this.pvInputModeSpinner.setSelection(0);
            this.setToStandbyFunctionButton.setChecked(false);
            this.batteryCapacityEditText.setText("");
            this.norminalBatteryVoltageEditText.setText("");
            this.buzzerFunctionButton.setChecked(false);
            this.greenFunctionButton.setChecked(false);
            this.epsVoltageSetSpinner.setSelection(0);
            this.epsFrequencySetSpinner.setSelection(0);
            this.lineModeInputSpinner.setSelection(0);
            this.maxGeneratorInputPowerEditText.setText("");
            this.acFirstStartHourEditText.setText("");
            this.acFirstStartMinuteEditText.setText("");
            this.acFirstEndHourEditText.setText("");
            this.acFirstEndMinuteEditText.setText("");
            this.acFirstStartHour1EditText.setText("");
            this.acFirstStartMinute1EditText.setText("");
            this.acFirstEndHour1EditText.setText("");
            this.acFirstEndMinute1EditText.setText("");
            this.acFirstStartHour2EditText.setText("");
            this.acFirstStartMinute2EditText.setText("");
            this.acFirstEndHour2EditText.setText("");
            this.acFirstEndMinute2EditText.setText("");
            this.batterySharedFunctionButton.setChecked(false);
            this.masterOrSlaveSpinner.setSelection(0);
            this.readComposedPhaseSpinner.setSelection(0);
            this.setComposedPhaseSpinner.setSelection(0);
            this.takeLoadTogetherFunctionButton.setChecked(false);
            this.feedInGridFunctionButton.setChecked(false);
            this.feedInGridPowerPercentEditText.setText("");
            this.onGridEodVoltageEditText.setText("");
            this.onGridEodSocEditText.setText("");
            this.chargeCurrentEditText.setText("");
            this.leadAcidChargeVoltRefEditText.setText("");
            this.floatingVoltageEditText.setText("");
            this.equalizationVoltageEditText.setText("");
            this.equalizationPeriodEditText.setText("");
            this.equalizationTimeEditText.setText("");
            this.acChargeTypeSpinner.setSelection(0);
            this.acChgBatCurrentEditText.setText("");
            this.acChargeStartHourEditText.setText("");
            this.acChargeStartMinuteEditText.setText("");
            this.acChargeEndHourEditText.setText("");
            this.acChargeEndMinuteEditText.setText("");
            this.acChargeStartHour1EditText.setText("");
            this.acChargeStartMinute1EditText.setText("");
            this.acChargeEndHour1EditText.setText("");
            this.acChargeEndMinute1EditText.setText("");
            this.acChargeStartHour2EditText.setText("");
            this.acChargeStartMinute2EditText.setText("");
            this.acChargeEndHour2EditText.setText("");
            this.acChargeEndMinute2EditText.setText("");
            this.acChargeStartBatteryVoltageEditText.setText("");
            this.acChargeEndBatteryVoltageEditText.setText("");
            this.acChargeStartBatterySocEditText.setText("");
            this.acChargeEndBatterySocEditText.setText("");
            this.disChgControlSpinner.setSelection(0);
            this.dischgCurrentEditText.setText("");
            this.batteryWarningVoltageEditText.setText("");
            this.batteryWarningSocEditText.setText("");
            this.leadAcidDischargeCutOffVoltEditText.setText("");
            this.socLowLimitEpsDischgEditText.setText("");
        }
    }

    public void fillDataFromCache() {
        if (this.created) {
            UserData userData = GlobalInfo.getInstance().getUserData();
            if (userData.getCurrentInverter() != null) {
                JSONObject cache = RemoteSetCacheManager.getInstance().getCache(userData.getCurrentInverter().getSerialNum());
                System.out.println("LuxPower - off grid remote set result from cache = " + cache);
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
        private Lv1OffGridRemoteSetFragment fragment;

        public ReadMultiParamTask(Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment) {
            this.fragment = lv1OffGridRemoteSetFragment;
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
                            Toast.makeText(this.fragment.getActivity().getApplicationContext(), R.string.page_maintain_remote_set_result_unknown_error, 1).show();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (jSONObject.getBoolean("success")) {
                        Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment = this.fragment;
                        if (lv1OffGridRemoteSetFragment != null && lv1OffGridRemoteSetFragment.inverter != null) {
                            RemoteSetCacheManager.getInstance().setCache(this.fragment.inverter.getSerialNum(), jSONObject);
                        }
                        this.fragment.analyzeResultToFragment(jSONObject);
                    }
                }
                this.fragment.toast(jSONObject);
                return;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            super.onPostExecute((ReadMultiParamTask) r2);
            this.fragment.readAllButton.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void analyzeResultToFragment(JSONObject jSONObject) throws Exception {
        if (jSONObject.has("HOLD_MODEL_batteryType")) {
            int i = jSONObject.getInt("HOLD_MODEL_batteryType") == 1 ? 0 : 8;
            this.floatingVoltageLayout.setVisibility(i);
            this.leadAcidChargeVoltRefLayout.setVisibility(i);
        }
        if (jSONObject.has("HOLD_TIME")) {
            String string = jSONObject.getString("HOLD_TIME");
            this.timeDateEditText.setText(string.substring(0, 10));
            this.timeTimeEditText.setText(string.substring(11, 16));
        }
        int i2 = 4;
        int i3 = 2;
        if (jSONObject.has("HOLD_PV_INPUT_MODE")) {
            int parseInt = Integer.parseInt(jSONObject.getString("HOLD_PV_INPUT_MODE"));
            this.pvInputModeSpinner.setSelection(parseInt == 3 ? 1 : parseInt == 4 ? 2 : 0);
        }
        if (jSONObject.has("FUNC_SET_TO_STANDBY")) {
            this.setToStandbyFunctionButton.setChecked(jSONObject.getBoolean("FUNC_SET_TO_STANDBY"));
        }
        if (jSONObject.has("HOLD_BATTERY_CAPACITY")) {
            this.batteryCapacityEditText.setText(jSONObject.getString("HOLD_BATTERY_CAPACITY"));
        }
        if (jSONObject.has("HOLD_NOMINAL_BATTERY_VOLTAGE")) {
            this.norminalBatteryVoltageEditText.setText(jSONObject.getString("HOLD_NOMINAL_BATTERY_VOLTAGE"));
        }
        if (jSONObject.has("FUNC_BUZZER_EN")) {
            this.buzzerFunctionButton.setChecked(jSONObject.getBoolean("FUNC_BUZZER_EN"));
        }
        if (jSONObject.has("FUNC_GREEN_EN")) {
            this.greenFunctionButton.setChecked(jSONObject.getBoolean("FUNC_GREEN_EN"));
        }
        if (jSONObject.has("HOLD_EPS_VOLT_SET")) {
            int parseInt2 = Integer.parseInt(jSONObject.getString("HOLD_EPS_VOLT_SET"));
            if (parseInt2 == 208) {
                i2 = 1;
            } else if (parseInt2 == 220) {
                i2 = 2;
            } else if (parseInt2 == 230) {
                i2 = 3;
            } else if (parseInt2 != 240) {
                i2 = 0;
            }
            this.epsVoltageSetSpinner.setSelection(i2);
        }
        if (jSONObject.has("HOLD_EPS_FREQ_SET")) {
            int parseInt3 = Integer.parseInt(jSONObject.getString("HOLD_EPS_FREQ_SET"));
            this.epsFrequencySetSpinner.setSelection(parseInt3 == 50 ? 1 : parseInt3 == 60 ? 2 : 0);
        }
        if (jSONObject.has("HOLD_LINE_MODE_INPUT")) {
            int parseInt4 = Integer.parseInt(jSONObject.getString("HOLD_LINE_MODE_INPUT"));
            if (parseInt4 >= 0 && parseInt4 <= 1) {
                this.lineModeInputSpinner.setSelection(parseInt4 + 1);
            } else {
                this.lineModeInputSpinner.setSelection(0);
            }
        }
        if (jSONObject.has("HOLD_MAX_GENERATOR_INPUT_POWER")) {
            this.maxGeneratorInputPowerEditText.setText(jSONObject.getString("HOLD_MAX_GENERATOR_INPUT_POWER"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_START_HOUR")) {
            this.acFirstStartHourEditText.setText(jSONObject.getString("HOLD_AC_FIRST_START_HOUR"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_START_MINUTE")) {
            this.acFirstStartMinuteEditText.setText(jSONObject.getString("HOLD_AC_FIRST_START_MINUTE"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_END_HOUR")) {
            this.acFirstEndHourEditText.setText(jSONObject.getString("HOLD_AC_FIRST_END_HOUR"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_END_MINUTE")) {
            this.acFirstEndMinuteEditText.setText(jSONObject.getString("HOLD_AC_FIRST_END_MINUTE"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_START_HOUR_1")) {
            this.acFirstStartHour1EditText.setText(jSONObject.getString("HOLD_AC_FIRST_START_HOUR_1"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_START_MINUTE_1")) {
            this.acFirstStartMinute1EditText.setText(jSONObject.getString("HOLD_AC_FIRST_START_MINUTE_1"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_END_HOUR_1")) {
            this.acFirstEndHour1EditText.setText(jSONObject.getString("HOLD_AC_FIRST_END_HOUR_1"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_END_MINUTE_1")) {
            this.acFirstEndMinute1EditText.setText(jSONObject.getString("HOLD_AC_FIRST_END_MINUTE_1"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_START_HOUR_2")) {
            this.acFirstStartHour2EditText.setText(jSONObject.getString("HOLD_AC_FIRST_START_HOUR_2"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_START_MINUTE_2")) {
            this.acFirstStartMinute2EditText.setText(jSONObject.getString("HOLD_AC_FIRST_START_MINUTE_2"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_END_HOUR_2")) {
            this.acFirstEndHour2EditText.setText(jSONObject.getString("HOLD_AC_FIRST_END_HOUR_2"));
        }
        if (jSONObject.has("HOLD_AC_FIRST_END_MINUTE_2")) {
            this.acFirstEndMinute2EditText.setText(jSONObject.getString("HOLD_AC_FIRST_END_MINUTE_2"));
        }
        if (jSONObject.has("FUNC_BAT_SHARED")) {
            this.batterySharedFunctionButton.setChecked(jSONObject.getBoolean("FUNC_BAT_SHARED"));
        }
        if (jSONObject.has("HOLD_SET_MASTER_OR_SLAVE")) {
            int parseInt5 = Integer.parseInt(jSONObject.getString("HOLD_SET_MASTER_OR_SLAVE"));
            if (parseInt5 == 0) {
                i3 = 1;
            } else if (parseInt5 != 1) {
                i3 = parseInt5 == 3 ? 3 : 0;
            }
            this.masterOrSlaveSpinner.setSelection(i3);
        }
        if (jSONObject.has("HOLD_SET_COMPOSED_PHASE")) {
            int parseInt6 = Integer.parseInt(jSONObject.getString("HOLD_SET_COMPOSED_PHASE"));
            int i4 = parseInt6 >> 8;
            if (i4 >= 1 && i4 <= 3) {
                this.readComposedPhaseSpinner.setSelection(i4);
            } else {
                this.readComposedPhaseSpinner.setSelection(0);
            }
            int i5 = parseInt6 & 255;
            if (i5 >= 0 && i5 <= 3) {
                this.setComposedPhaseSpinner.setSelection(i5);
            } else {
                this.setComposedPhaseSpinner.setSelection(0);
            }
        }
        if (jSONObject.has("FUNC_TAKE_LOAD_TOGETHER")) {
            this.takeLoadTogetherFunctionButton.setChecked(jSONObject.getBoolean("FUNC_TAKE_LOAD_TOGETHER"));
        }
        if (jSONObject.has("FUNC_FEED_IN_GRID_EN")) {
            this.feedInGridFunctionButton.setChecked(jSONObject.getBoolean("FUNC_FEED_IN_GRID_EN"));
        }
        if (jSONObject.has("HOLD_FEED_IN_GRID_POWER_PERCENT")) {
            this.feedInGridPowerPercentEditText.setText(jSONObject.getString("HOLD_FEED_IN_GRID_POWER_PERCENT"));
        }
        if (jSONObject.has("HOLD_ON_GRID_EOD_VOLTAGE")) {
            this.onGridEodVoltageEditText.setText(jSONObject.getString("HOLD_ON_GRID_EOD_VOLTAGE"));
        }
        if (jSONObject.has("HOLD_DISCHG_CUT_OFF_SOC_EOD")) {
            this.onGridEodSocEditText.setText(jSONObject.getString("HOLD_DISCHG_CUT_OFF_SOC_EOD"));
        }
        if (jSONObject.has("HOLD_LEAD_ACID_CHARGE_RATE")) {
            this.chargeCurrentEditText.setText(jSONObject.getString("HOLD_LEAD_ACID_CHARGE_RATE"));
        }
        if (jSONObject.has("HOLD_LEAD_ACID_CHARGE_VOLT_REF")) {
            this.leadAcidChargeVoltRefEditText.setText(jSONObject.getString("HOLD_LEAD_ACID_CHARGE_VOLT_REF"));
        }
        if (jSONObject.has("HOLD_FLOATING_VOLTAGE")) {
            this.floatingVoltageEditText.setText(jSONObject.getString("HOLD_FLOATING_VOLTAGE"));
        }
        if (jSONObject.has("HOLD_EQUALIZATION_VOLTAGE")) {
            this.equalizationVoltageEditText.setText(jSONObject.getString("HOLD_EQUALIZATION_VOLTAGE"));
        }
        if (jSONObject.has("HOLD_EQUALIZATION_PERIOD")) {
            this.equalizationPeriodEditText.setText(jSONObject.getString("HOLD_EQUALIZATION_PERIOD"));
        }
        if (jSONObject.has("HOLD_EQUALIZATION_TIME")) {
            this.equalizationTimeEditText.setText(jSONObject.getString("HOLD_EQUALIZATION_TIME"));
        }
        if (jSONObject.has("BIT_AC_CHARGE_TYPE")) {
            int parseInt7 = Integer.parseInt(jSONObject.getString("BIT_AC_CHARGE_TYPE"));
            if (parseInt7 >= 0 && parseInt7 <= 5) {
                this.acChargeTypeSpinner.setSelection(parseInt7 + 1);
            } else {
                this.acChargeTypeSpinner.setSelection(0);
            }
        }
        if (jSONObject.has("HOLD_AC_CHARGE_BATTERY_CURRENT")) {
            this.acChgBatCurrentEditText.setText(jSONObject.getString("HOLD_AC_CHARGE_BATTERY_CURRENT"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_START_HOUR")) {
            this.acChargeStartHourEditText.setText(jSONObject.getString("HOLD_AC_CHARGE_START_HOUR"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_START_MINUTE")) {
            this.acChargeStartMinuteEditText.setText(jSONObject.getString("HOLD_AC_CHARGE_START_MINUTE"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_END_HOUR")) {
            this.acChargeEndHourEditText.setText(jSONObject.getString("HOLD_AC_CHARGE_END_HOUR"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_END_MINUTE")) {
            this.acChargeEndMinuteEditText.setText(jSONObject.getString("HOLD_AC_CHARGE_END_MINUTE"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_START_HOUR_1")) {
            this.acChargeStartHour1EditText.setText(jSONObject.getString("HOLD_AC_CHARGE_START_HOUR_1"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_START_MINUTE_1")) {
            this.acChargeStartMinute1EditText.setText(jSONObject.getString("HOLD_AC_CHARGE_START_MINUTE_1"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_END_HOUR_1")) {
            this.acChargeEndHour1EditText.setText(jSONObject.getString("HOLD_AC_CHARGE_END_HOUR_1"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_END_MINUTE_1")) {
            this.acChargeEndMinute1EditText.setText(jSONObject.getString("HOLD_AC_CHARGE_END_MINUTE_1"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_START_HOUR_2")) {
            this.acChargeStartHour2EditText.setText(jSONObject.getString("HOLD_AC_CHARGE_START_HOUR_2"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_START_MINUTE_2")) {
            this.acChargeStartMinute2EditText.setText(jSONObject.getString("HOLD_AC_CHARGE_START_MINUTE_2"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_END_HOUR_2")) {
            this.acChargeEndHour2EditText.setText(jSONObject.getString("HOLD_AC_CHARGE_END_HOUR_2"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_END_MINUTE_2")) {
            this.acChargeEndMinute2EditText.setText(jSONObject.getString("HOLD_AC_CHARGE_END_MINUTE_2"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_START_BATTERY_VOLTAGE")) {
            this.acChargeStartBatteryVoltageEditText.setText(jSONObject.getString("HOLD_AC_CHARGE_START_BATTERY_VOLTAGE"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_END_BATTERY_VOLTAGE")) {
            this.acChargeEndBatteryVoltageEditText.setText(jSONObject.getString("HOLD_AC_CHARGE_END_BATTERY_VOLTAGE"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_START_BATTERY_SOC")) {
            this.acChargeStartBatterySocEditText.setText(jSONObject.getString("HOLD_AC_CHARGE_START_BATTERY_SOC"));
        }
        if (jSONObject.has("HOLD_AC_CHARGE_END_BATTERY_SOC")) {
            this.acChargeEndBatterySocEditText.setText(jSONObject.getString("HOLD_AC_CHARGE_END_BATTERY_SOC"));
        }
        if (jSONObject.has("BIT_DISCHG_CONTROL_TYPE")) {
            int parseInt8 = Integer.parseInt(jSONObject.getString("BIT_DISCHG_CONTROL_TYPE"));
            if (parseInt8 >= 0 && parseInt8 <= 1) {
                this.disChgControlSpinner.setSelection(parseInt8 + 1);
            } else {
                this.disChgControlSpinner.setSelection(0);
            }
        }
        if (jSONObject.has("HOLD_LEAD_ACID_DISCHARGE_RATE")) {
            this.dischgCurrentEditText.setText(jSONObject.getString("HOLD_LEAD_ACID_DISCHARGE_RATE"));
        }
        if (jSONObject.has("HOLD_BATTERY_WARNING_VOLTAGE")) {
            this.batteryWarningVoltageEditText.setText(jSONObject.getString("HOLD_BATTERY_WARNING_VOLTAGE"));
        }
        if (jSONObject.has("HOLD_BATTERY_WARNING_SOC")) {
            this.batteryWarningSocEditText.setText(jSONObject.getString("HOLD_BATTERY_WARNING_SOC"));
        }
        if (jSONObject.has("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT")) {
            this.leadAcidDischargeCutOffVoltEditText.setText(jSONObject.getString("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT"));
        }
        if (jSONObject.has("HOLD_SOC_LOW_LIMIT_EPS_DISCHG")) {
            this.socLowLimitEpsDischgEditText.setText(jSONObject.getString("HOLD_SOC_LOW_LIMIT_EPS_DISCHG"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class WriteParamTask extends AsyncTask<RemoteWriteInfo, Void, JSONObject> {
        private Lv1OffGridRemoteSetFragment fragment;
        private RemoteWriteInfo remoteWriteInfo;

        public WriteParamTask(Lv1OffGridRemoteSetFragment lv1OffGridRemoteSetFragment) {
            this.fragment = lv1OffGridRemoteSetFragment;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
            this.remoteWriteInfo.setEnabled(false);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(RemoteWriteInfo... remoteWriteInfoArr) {
            RemoteWriteInfo remoteWriteInfo = remoteWriteInfoArr[0];
            if (remoteWriteInfo != null && remoteWriteInfo.getRemoteWriteType() != null) {
                this.remoteWriteInfo = remoteWriteInfo;
                publishProgress(new Void[0]);
                int i = AnonymousClass61.$SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[remoteWriteInfo.getRemoteWriteType().ordinal()];
                if (i == 1) {
                    if (Tool.isEmpty(remoteWriteInfo.getValueText())) {
                        return this.fragment.createFailureJSONObject("PARAM_EMPTY");
                    }
                    return postWriteParam(remoteWriteInfo.getSerialNum(), remoteWriteInfo.getHoldParam(), remoteWriteInfo.getValueText());
                }
                if (i == 2) {
                    String valueText = remoteWriteInfo.getValueText();
                    if (Tool.isEmpty(valueText)) {
                        return this.fragment.createFailureJSONObject("PARAM_EMPTY");
                    }
                    return postWriteBitParam(remoteWriteInfo.getSerialNum(), remoteWriteInfo.getBitParam(), valueText);
                }
                if (i == 3) {
                    String hourText = remoteWriteInfo.getHourText();
                    String minuteText = remoteWriteInfo.getMinuteText();
                    if (Tool.isEmpty(hourText) || Tool.isEmpty(minuteText)) {
                        return this.fragment.createFailureJSONObject("PARAM_EMPTY");
                    }
                    try {
                        int parseInt = Integer.parseInt(hourText);
                        int parseInt2 = Integer.parseInt(minuteText);
                        if (parseInt < 0 || parseInt > 23) {
                            return this.fragment.createOutRangeJSONObject("0", "23");
                        }
                        if (parseInt2 < 0 || parseInt2 > 59) {
                            return this.fragment.createOutRangeJSONObject("0", "59");
                        }
                        return postWriteTimeParam(remoteWriteInfo.getSerialNum(), remoteWriteInfo.getTimeParam(), hourText, minuteText);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return this.fragment.createFailureJSONObject("INTEGER_FORMAT_ERROR");
                    }
                }
                if (i == 4) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("inverterSn", remoteWriteInfo.getSerialNum());
                    hashMap.put("functionParam", remoteWriteInfo.getFunctionParam());
                    hashMap.put("enable", String.valueOf(remoteWriteInfo.isFunctionToggleButtonChecked()));
                    hashMap.put("clientType", "APP");
                    hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
                    try {
                        return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/functionControl", hashMap);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            return this.fragment.createFailureJSONObject("UNKNOWN_ERROR");
        }

        private JSONObject postWriteParam(String str, String str2, String str3) {
            HashMap hashMap = new HashMap();
            hashMap.put("inverterSn", str);
            hashMap.put("holdParam", str2);
            hashMap.put("valueText", str3);
            hashMap.put("clientType", "APP");
            hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/write", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private JSONObject postWriteBitParam(String str, String str2, String str3) {
            HashMap hashMap = new HashMap();
            hashMap.put("inverterSn", str);
            hashMap.put("bitParam", str2);
            hashMap.put("value", str3);
            hashMap.put("clientType", "APP");
            hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/bitParamControl", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private JSONObject postWriteTimeParam(String str, String str2, String str3, String str4) {
            HashMap hashMap = new HashMap();
            hashMap.put("inverterSn", str);
            hashMap.put("timeParam", str2);
            hashMap.put("hour", str3);
            hashMap.put("minute", str4);
            hashMap.put("clientType", "APP");
            hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/writeTime", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
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
                            Tool.alert(this.fragment.getActivity(), R.string.page_maintain_remote_set_result_unknown_error);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (jSONObject.getBoolean("success")) {
                        RemoteSetCacheManager.getInstance().clearCache(this.fragment.inverter.getSerialNum());
                        Tool.alert(this.fragment.getActivity(), R.string.page_maintain_remote_set_result_success);
                    }
                }
                if (this.remoteWriteInfo != null && REMOTE_WRITE_TYPE.CONTROL.equals(this.remoteWriteInfo.getRemoteWriteType())) {
                    this.remoteWriteInfo.getFunctionToggleButton().setChecked(!this.remoteWriteInfo.getFunctionToggleButton().isChecked());
                }
                this.fragment.toast(jSONObject);
            } finally {
                this.remoteWriteInfo.setEnabled(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OffGridRemoteSetFragment$61, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass61 {
        static final /* synthetic */ int[] $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE;

        static {
            int[] iArr = new int[REMOTE_WRITE_TYPE.values().length];
            $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE = iArr;
            try {
                iArr[REMOTE_WRITE_TYPE.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[REMOTE_WRITE_TYPE.BIT_PARAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[REMOTE_WRITE_TYPE.TIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[REMOTE_WRITE_TYPE.CONTROL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject createOutRangeJSONObject(String str, String str2) {
        try {
            JSONObject createFailureJSONObject = createFailureJSONObject("OUT_RANGE_ERROR");
            if (createFailureJSONObject == null) {
                return null;
            }
            createFailureJSONObject.put("minValue", str);
            createFailureJSONObject.put("maxValue", str2);
            return createFailureJSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
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
    public void toast(JSONObject jSONObject) {
        char c;
        try {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                if (jSONObject == null) {
                    Toast.makeText(activity.getApplicationContext(), R.string.phrase_toast_network_error, 1).show();
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
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_alert_param_empty, 1).show();
                        return;
                    case 1:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_alert_param_should_int, 1).show();
                        return;
                    case 2:
                        Toast.makeText(activity.getApplicationContext(), getString(R.string.page_maintain_remote_set_alert_param_out_range) + ": [" + jSONObject.getString("minValue") + ", " + jSONObject.getString("maxValue") + "]", 1).show();
                        return;
                    case 3:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_param_error, 1).show();
                        return;
                    case 4:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_set_undo, 1).show();
                        return;
                    case 5:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_device_offline, 1).show();
                        return;
                    case 6:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_command_not_send, 1).show();
                        return;
                    case 7:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_timeout, 1).show();
                        return;
                    case '\b':
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_server_exception, 1).show();
                        return;
                    case '\t':
                    case '\n':
                        Toast.makeText(activity.getApplicationContext(), getString(R.string.page_maintain_remote_set_result_failed) + " " + jSONObject.getInt("errorCode"), 1).show();
                        return;
                    default:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_unknown_error, 1).show();
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EditText getTimeDateEditText() {
        return this.timeDateEditText;
    }

    public EditText getTimeTimeEditText() {
        return this.timeTimeEditText;
    }
}
