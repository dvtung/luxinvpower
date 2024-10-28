package com.nfcx.luxinvpower.view.local.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.google.common.base.Ascii;
import com.nfcx.luxinvpower.BuildConfig;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.connect.LocalConnect;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.bean.set.REMOTE_WRITE_TYPE;
import com.nfcx.luxinvpower.global.bean.set.RemoteReadInfo;
import com.nfcx.luxinvpower.global.bean.set.RemoteWriteInfo;
import com.nfcx.luxinvpower.protocol.tcp.DataFrameFactory;
import com.nfcx.luxinvpower.tool.InvTool;
import com.nfcx.luxinvpower.tool.PinTool;
import com.nfcx.luxinvpower.tool.ProTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.local.LocalActivity;
import com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment;
import com.nfcx.luxinvpower.view.local.ulCompliance.ULComplianceActivity;
import com.nfcx.luxinvpower.view.login.LoginActivity;
import com.nfcx.luxinvpower.view.main.fragment.lv1.AbstractItemFragment;
import com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Local12KSetFragment extends AbstractItemFragment {
    private String[] _110Functions;
    private String[] _179Functions;
    private String[] _21Functions;
    private String[] _226Functions;
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
    private ToggleButton acChargeFunctionButton;
    private Button acChargePowerButton;
    private EditText acChargePowerEditText;
    private Button acChargeSocLimitButton;
    private EditText acChargeSocLimitEditText;
    private EditText acChargeStartHour1EditText;
    private EditText acChargeStartHour2EditText;
    private EditText acChargeStartHourEditText;
    private EditText acChargeStartMinute1EditText;
    private EditText acChargeStartMinute2EditText;
    private EditText acChargeStartMinuteEditText;
    private Button acChargeStartTime1Button;
    private Button acChargeStartTime2Button;
    private Button acChargeStartTimeButton;
    private Button acCoupleEndSocButton;
    private EditText acCoupleEndSocEditText;
    private Button acCoupleEndVoltButton;
    private EditText acCoupleEndVoltEditText;
    private Button acCoupleStartSocButton;
    private EditText acCoupleStartSocEditText;
    private Button acCoupleStartVoltButton;
    private EditText acCoupleStartVoltEditText;
    private ConstraintLayout advancedSetActionLayout;
    private TextView advancedSetActionTextView;
    private ToggleButton advancedSetActionToggleButton;
    private LinearLayout advancedSetParamLayout;
    private Button all2DefaultButton;
    private ConstraintLayout applicationSetActionLayout;
    private TextView applicationSetActionTextView;
    private ToggleButton applicationSetActionToggleButton;
    private LinearLayout applicationSetParamLayout;
    private ConstraintLayout applicationSetTitleLayout;
    private Button batChargeControlButton;
    private Spinner batChargeControlSpinner;
    private Button batChargeCurrentLimitButton;
    private EditText batChargeCurrentLimitEditText;
    private Button batDischargeControlButton;
    private Spinner batDischargeControlSpinner;
    private ToggleButton batterySharedFunctionButton;
    private ConstraintLayout batterySharedFunctionLayout;
    private JSONObject cacheReadAllResultObj;
    private Button chargeCurrentButton;
    private EditText chargeCurrentEditText;
    private Button chargeEndSocButton;
    private EditText chargeEndSocEditText;
    private Button chargeEndVoltButton;
    private EditText chargeEndVoltEditText;
    private Button chargeFirstVoltButton;
    private EditText chargeFirstVoltEditText;
    private ConstraintLayout chargeSetActionLayout;
    private TextView chargeSetActionTextView;
    private ToggleButton chargeSetActionToggleButton;
    private LinearLayout chargeSetParamLayout;
    private Button chargeStartSocButton;
    private EditText chargeStartSocEditText;
    private Button chargeStartVoltButton;
    private EditText chargeStartVoltEditText;
    private Button composedPhaseButton;
    private ConstraintLayout composedPhaseLayout;
    private boolean created;
    private ToggleButton ctDirectionReversedFunctionButton;
    private ConstraintLayout ctDirectionReversedFunctionLayout;
    private Button ctSampleRatioButton;
    private ConstraintLayout ctSampleRatioLayout;
    private Spinner ctSampleRatioSpinner;
    private Button datalogSnButton;
    private EditText datalogSnEditText;
    private TextView datalogSnTextView;
    private ConstraintLayout dischargeAcCoupleSetActionLayout;
    private TextView dischargeAcCoupleSetActionTextView;
    private ToggleButton dischargeAcCoupleSetActionToggleButton;
    private LinearLayout dischargeAcCoupleSetParamLayout;
    private ConstraintLayout dischargeAcCoupleSetTitleLayout;
    private TextView dischargeAcCoupleSetTitleTextView;
    private ConstraintLayout dischargePeakShavingSetActionLayout;
    private TextView dischargePeakShavingSetActionTextView;
    private ToggleButton dischargePeakShavingSetActionToggleButton;
    private LinearLayout dischargePeakShavingSetParamLayout;
    private ConstraintLayout dischargePeakShavingSetTitleLayout;
    private TextView dischargePeakShavingSetTitleTextView;
    private ConstraintLayout dischargeSetActionLayout;
    private TextView dischargeSetActionTextView;
    private ToggleButton dischargeSetActionToggleButton;
    private LinearLayout dischargeSetParamLayout;
    private ConstraintLayout dischargeSmartLoadSetActionLayout;
    private TextView dischargeSmartLoadSetActionTextView;
    private ToggleButton dischargeSmartLoadSetActionToggleButton;
    private LinearLayout dischargeSmartLoadSetParamLayout;
    private ConstraintLayout dischargeSmartLoadSetTitleLayout;
    private TextView dischargeSmartLoadSetTitleTextView;
    private Button dischgCurrentButton;
    private EditText dischgCurrentEditText;
    private ToggleButton drmsFunctionButton;
    private Button epsFrequencySetButton;
    private Spinner epsFrequencySetSpinner;
    private ToggleButton epsFunctionButton;
    private Button exportULCompliancePDFButton;
    private ToggleButton fastZeroExportFunctionButton;
    private ConstraintLayout fastZeroExportFunctionLayout;
    private ToggleButton feedInGridFunctionButton;
    private ConstraintLayout feedInGridFunctionLayout;
    private Button feedInGridPowerPercentButton;
    private EditText feedInGridPowerPercentEditText;
    private ConstraintLayout feedInGridPowerPercentLayout;
    private Button floatingVoltageButton;
    private EditText floatingVoltageEditText;
    private EditText forcedChargeEndHour1EditText;
    private EditText forcedChargeEndHour2EditText;
    private EditText forcedChargeEndHourEditText;
    private EditText forcedChargeEndMinute1EditText;
    private EditText forcedChargeEndMinute2EditText;
    private EditText forcedChargeEndMinuteEditText;
    private Button forcedChargeEndTime1Button;
    private Button forcedChargeEndTime2Button;
    private Button forcedChargeEndTimeButton;
    private EditText forcedChargeStartHour1EditText;
    private EditText forcedChargeStartHour2EditText;
    private EditText forcedChargeStartHourEditText;
    private EditText forcedChargeStartMinute1EditText;
    private EditText forcedChargeStartMinute2EditText;
    private EditText forcedChargeStartMinuteEditText;
    private Button forcedChargeStartTime1Button;
    private Button forcedChargeStartTime2Button;
    private Button forcedChargeStartTimeButton;
    private ToggleButton forcedChgFunctionButton;
    private Button forcedChgPowerCmdButton;
    private EditText forcedChgPowerCmdEditText;
    private Button forcedChgSocLimitButton;
    private EditText forcedChgSocLimitEditText;
    private EditText forcedDisChargeEndHour1EditText;
    private EditText forcedDisChargeEndHour2EditText;
    private EditText forcedDisChargeEndHourEditText;
    private EditText forcedDisChargeEndMinute1EditText;
    private EditText forcedDisChargeEndMinute2EditText;
    private EditText forcedDisChargeEndMinuteEditText;
    private Button forcedDisChargeEndTime1Button;
    private Button forcedDisChargeEndTime2Button;
    private Button forcedDisChargeEndTimeButton;
    private EditText forcedDisChargeStartHour1EditText;
    private EditText forcedDisChargeStartHour2EditText;
    private EditText forcedDisChargeStartHourEditText;
    private EditText forcedDisChargeStartMinute1EditText;
    private EditText forcedDisChargeStartMinute2EditText;
    private EditText forcedDisChargeStartMinuteEditText;
    private Button forcedDisChargeStartTime1Button;
    private Button forcedDisChargeStartTime2Button;
    private Button forcedDisChargeStartTimeButton;
    private ToggleButton forcedDisChgFunctionButton;
    private Button forcedDisChgPowerCmdButton;
    private EditText forcedDisChgPowerCmdEditText;
    private Button forcedDisChgSocLimitButton;
    private EditText forcedDisChgSocLimitEditText;
    private Button genRatedPowerButton;
    private EditText genRatedPowerEditText;
    private ConstraintLayout generatorSetActionLayout;
    private TextView generatorSetActionTextView;
    private ToggleButton generatorSetActionToggleButton;
    private LinearLayout generatorSetParamLayout;
    private ConstraintLayout gridConnectSetActionLayout;
    private TextView gridConnectSetActionTextView;
    private ToggleButton gridConnectSetActionToggleButton;
    private LinearLayout gridConnectSetParamLayout;
    private ConstraintLayout gridConnectSetTitleLayout;
    private Button gridFreqConnHighButton;
    private EditText gridFreqConnHighEditText;
    private Button gridFreqConnLowButton;
    private EditText gridFreqConnLowEditText;
    private Button gridFreqLimit1HighButton;
    private EditText gridFreqLimit1HighEditText;
    private Button gridFreqLimit1LowButton;
    private EditText gridFreqLimit1LowEditText;
    private Button gridFreqLimit2HighButton;
    private EditText gridFreqLimit2HighEditText;
    private Button gridFreqLimit2LowButton;
    private EditText gridFreqLimit2LowEditText;
    private Button gridFreqLimit3HighButton;
    private EditText gridFreqLimit3HighEditText;
    private Button gridFreqLimit3LowButton;
    private EditText gridFreqLimit3LowEditText;
    private ToggleButton gridPeakShavingFunctionButton;
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
    private Button gridRegulationButton;
    private Spinner gridRegulationSpinner;
    private Button gridTypeButton;
    private Spinner gridTypeSpinner;
    private Button gridVoltConnHighButton;
    private EditText gridVoltConnHighEditText;
    private Button gridVoltConnLowButton;
    private EditText gridVoltConnLowEditText;
    private Button gridVoltLimit1HighButton;
    private EditText gridVoltLimit1HighEditText;
    private Button gridVoltLimit1LowButton;
    private EditText gridVoltLimit1LowEditText;
    private Button gridVoltLimit2HighButton;
    private EditText gridVoltLimit2HighEditText;
    private Button gridVoltLimit2LowButton;
    private EditText gridVoltLimit2LowEditText;
    private Button gridVoltLimit3HighButton;
    private EditText gridVoltLimit3HighEditText;
    private Button gridVoltLimit3LowButton;
    private EditText gridVoltLimit3LowEditText;
    private TextView inverterSnTextView;
    private Button leadAcidChargeVoltRefButton;
    private EditText leadAcidChargeVoltRefEditText;
    private Button leadAcidDischargeCutOffVoltButton;
    private EditText leadAcidDischargeCutOffVoltEditText;
    private LocalConnect localConnect;
    private Button masterOrSlaveButton;
    private ConstraintLayout masterOrSlaveLayout;
    private Spinner masterOrSlaveSpinner;
    private Button maxAcInputPowerButton;
    private EditText maxAcInputPowerEditText;
    private ConstraintLayout maxAcInputPowerLayout;
    private Button offGridDischargeCutoffSocButton;
    private EditText offGridDischargeCutoffSocEditText;
    private ToggleButton onGridAlwaysOnFunctionButton;
    private Button onGridDischargeCutoffSocButton;
    private EditText onGridDischargeCutoffSocEditText;
    private Button onGridEodVoltageButton;
    private EditText onGridEodVoltageEditText;
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
    private EditText pinEditText;
    private ToggleButton pvArcFunctionButton;
    private ConstraintLayout pvArcFunctionLayout;
    private Button readAllButton;
    private Spinner readComposedPhaseSpinner;
    private Button reconnectTimeButton;
    private EditText reconnectTimeEditText;
    private Button reset2FactoryButton;
    private ToggleButton runWithoutGridFunctionButton;
    private ConstraintLayout runWithoutGridFunctionLayout;
    private Spinner setComposedPhaseSpinner;
    private Button setTimeButton;
    private ToggleButton setToStandbyFunctionButton;
    private Button smartLoadEndSocButton;
    private EditText smartLoadEndSocEditText;
    private Button smartLoadEndVoltButton;
    private EditText smartLoadEndVoltEditText;
    private Button smartLoadStartSocButton;
    private EditText smartLoadStartSocEditText;
    private Button smartLoadStartVoltButton;
    private EditText smartLoadStartVoltEditText;
    private Button startAcChargeSOCButton;
    private EditText startAcChargeSOCEditText;
    private Button startAcChargeVoltButton;
    private EditText startAcChargeVoltEditText;
    private Button startPvPowerButton;
    private EditText startPvPowerEditText;
    private Button stopDischgVoltButton;
    private EditText stopDischgVoltEditText;
    private EditText timeDateEditText;
    private ConstraintLayout timeLayout;
    private EditText timeTimeEditText;
    private Button updateFirmwareButton;
    private ConstraintLayout wifiModuleSetActionLayout;
    private TextView wifiModuleSetActionTextView;
    private ToggleButton wifiModuleSetActionToggleButton;
    private LinearLayout wifiModuleSetParamLayout;

    private static boolean checkIfRangeValid(int i, int i2, int i3) {
        return i3 >= i && i3 <= i2;
    }

    public Local12KSetFragment(LocalConnect localConnect) {
        super(23L);
        this._21Functions = new String[]{"FUNC_DRMS_EN", "FUNC_AC_CHARGE", "FUNC_FORCED_CHG_EN", "FUNC_FORCED_DISCHG_EN", "FUNC_SET_TO_STANDBY", "FUNC_EPS_EN", "FUNC_FEED_IN_GRID_EN"};
        this._110Functions = new String[]{"FUNC_PV_GRID_OFF_EN", "FUNC_RUN_WITHOUT_GRID", "FUNC_MICRO_GRID_EN", "FUNC_BAT_SHARED", "FUNC_CHARGE_LAST", "FUNC_BUZZER_EN", "FUNC_TAKE_LOAD_TOGETHER", "FUNC_GREEN_EN"};
        this._179Functions = new String[]{"FUNC_CT_DIRECTION_REVERSED", "FUNC_PV_ARC_FAULT_CLEAR", "FUNC_PV_SELL_TO_GRID_EN", "FUNC_WATT_VOLT_EN", "FUNC_TRIP_TIME_UNIT", "FUNC_ACTIVE_POWER_LIMIT_MODE", "FUNC_GRID_PEAK_SHAVING", "FUNC_GEN_PEAK_SHAVING", "FUNC_BAT_CHARGE_CONTROL", "FUNC_BAT_DISCHARGE_CONTROL", "FUNC_PV_ARC", "FUNC_ON_GRID_ALWAYS_ON"};
        this._226Functions = new String[]{"FUNC_RUN_WITHOUT_GRID_12K"};
        this.localConnect = localConnect;
    }

    public synchronized void putReadAllResultObj(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (this.cacheReadAllResultObj == null) {
            this.cacheReadAllResultObj = new JSONObject();
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                this.cacheReadAllResultObj.put(next, jSONObject.get(next));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_local_12k_set, viewGroup, false);
        ((ConstraintLayout) inflate.findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) LoginActivity.class));
                LocalActivity.instance.finish();
            }
        });
        this.datalogSnTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_datalogSn_TextView);
        this.inverterSnTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_inverterSn_TextView);
        Button button = (Button) inflate.findViewById(R.id.fragment_remote_set_readAllButton);
        this.readAllButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Inverter inverter = Local12KSetFragment.this.localConnect.getInverter();
                if (inverter != null) {
                    Local12KSetFragment.this.cacheReadAllResultObj = null;
                    Local12KSetFragment.this.clearFragmentData();
                    Local12KSetFragment.this.readAllButton.setEnabled(false);
                    Local12KSetFragment.this.exportULCompliancePDFButton.setEnabled(false);
                    new ReadMultiParamTask(Local12KSetFragment.this).execute(new RemoteReadInfo(inverter.getSerialNum(), 0, 40), new RemoteReadInfo(inverter.getSerialNum(), 40, 40), new RemoteReadInfo(inverter.getSerialNum(), 80, 40), new RemoteReadInfo(inverter.getSerialNum(), 120, 40), new RemoteReadInfo(inverter.getSerialNum(), Opcodes.IF_ICMPNE, 40), new RemoteReadInfo(inverter.getSerialNum(), ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 40));
                    new ReadDatalogParamTask(Local12KSetFragment.this).execute(1);
                }
            }
        });
        this.timeLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_timeLayout);
        EditText editText = (EditText) inflate.findViewById(R.id.fragment_remote_set_timeDateEditText);
        this.timeDateEditText = editText;
        editText.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = Local12KSetFragment.this.timeDateEditText.getText().toString();
                if (Tool.isEmpty(obj) || obj.length() != 10) {
                    Local12KSetFragment.this.timeDateEditText.setText(InvTool.formatDate(new Date()));
                }
                Local12KSetFragment.this.getActivity().showDialog(6);
            }
        });
        EditText editText2 = (EditText) inflate.findViewById(R.id.fragment_remote_set_timeTimeEditText);
        this.timeTimeEditText = editText2;
        editText2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = Local12KSetFragment.this.timeTimeEditText.getText().toString();
                if (Tool.isEmpty(obj) || obj.length() != 5) {
                    Local12KSetFragment.this.timeTimeEditText.setText(InvTool.formatTime(new Date()).substring(0, 5));
                }
                Local12KSetFragment.this.getActivity().showDialog(7);
            }
        });
        Button button2 = (Button) inflate.findViewById(R.id.fragment_remote_set_timeButton);
        this.setTimeButton = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                Local12KSetFragment.this.runNormalRemoteWrite("HOLD_TIME", Local12KSetFragment.this.timeDateEditText.getText().toString().trim() + " " + Local12KSetFragment.this.timeTimeEditText.getText().toString().trim() + ":" + String.valueOf(calendar.get(13)), Local12KSetFragment.this.setTimeButton);
            }
        });
        this.ctSampleRatioLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_ctSampleRatioLayout);
        this.ctSampleRatioSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_ctSampleRatioSpinner);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Property(String.valueOf(-1), getString(R.string.phrase_bit_param_ct_sample_ratio_empty)));
        arrayList.add(new Property(String.valueOf(0), getString(R.string.phrase_bit_param_ct_sample_ratio_0)));
        arrayList.add(new Property(String.valueOf(1), getString(R.string.phrase_bit_param_ct_sample_ratio_1)));
        arrayList.add(new Property(String.valueOf(2), "1/2000"));
        ArrayAdapter arrayAdapter = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.ctSampleRatioSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        Button button3 = (Button) inflate.findViewById(R.id.fragment_remote_set_ctSampleRatioButton);
        this.ctSampleRatioButton = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Local12KSetFragment.this.ctSampleRatioSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Local12KSetFragment.this.runBitRemoteWrite("BIT_CT_SAMPLE_RATIO", property.getName(), Local12KSetFragment.this.ctSampleRatioButton);
            }
        });
        ToggleButton toggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_setToStandbyFunctionButton);
        this.setToStandbyFunctionButton = toggleButton;
        toggleButton.setOnClickListener(new AnonymousClass7());
        ToggleButton toggleButton2 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_epsFunctionButton);
        this.epsFunctionButton = toggleButton2;
        toggleButton2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_EPS_EN", local12KSetFragment.epsFunctionButton);
            }
        });
        this.epsFrequencySetSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_epsFrequencySetSpinner);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_eps_frequency_set_empty)));
        arrayList2.add(new Property(String.valueOf(50), getString(R.string.phrase_param_eps_frequency_set_50)));
        arrayList2.add(new Property(String.valueOf(60), getString(R.string.phrase_param_eps_frequency_set_60)));
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.epsFrequencySetSpinner.setAdapter((SpinnerAdapter) arrayAdapter2);
        Button button4 = (Button) inflate.findViewById(R.id.fragment_remote_set_epsFrequencySetButton);
        this.epsFrequencySetButton = button4;
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Local12KSetFragment.this.epsFrequencySetSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Local12KSetFragment.this.runNormalRemoteWrite("HOLD_EPS_FREQ_SET", property.getName(), Local12KSetFragment.this.epsFrequencySetButton);
            }
        });
        this.fastZeroExportFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_fastZeroExportFunctionLayout);
        ToggleButton toggleButton3 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_fastZeroExportFunctionButton);
        this.fastZeroExportFunctionButton = toggleButton3;
        toggleButton3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_RUN_WITHOUT_GRID", local12KSetFragment.fastZeroExportFunctionButton);
            }
        });
        this.runWithoutGridFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_runWithoutGridFunctionLayout);
        ToggleButton toggleButton4 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_runWithoutGridFunctionButton);
        this.runWithoutGridFunctionButton = toggleButton4;
        toggleButton4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_RUN_WITHOUT_GRID_12K", local12KSetFragment.runWithoutGridFunctionButton);
            }
        });
        this.feedInGridFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_feedInGridFunctionLayout);
        ToggleButton toggleButton5 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_feedInGridFunctionButton);
        this.feedInGridFunctionButton = toggleButton5;
        toggleButton5.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_FEED_IN_GRID_EN", local12KSetFragment.feedInGridFunctionButton);
            }
        });
        this.feedInGridPowerPercentLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_feedInGridPowerPercentLayout);
        this.feedInGridPowerPercentEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_feedInGridPowerPercentEditText);
        Button button5 = (Button) inflate.findViewById(R.id.fragment_remote_set_feedInGridPowerPercentButton);
        this.feedInGridPowerPercentButton = button5;
        button5.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_FEED_IN_GRID_POWER_PERCENT", local12KSetFragment.feedInGridPowerPercentEditText.getText().toString().trim(), Local12KSetFragment.this.feedInGridPowerPercentButton);
            }
        });
        this.masterOrSlaveLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_masterOrSlaveLayout);
        this.masterOrSlaveSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_masterOrSlaveSpinner);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_master_or_slave_empty)));
        arrayList3.add(new Property(String.valueOf(1), getString(R.string.phrase_param_master_or_slave_hybird_1)));
        arrayList3.add(new Property(String.valueOf(2), getString(R.string.phrase_param_master_or_slave_hybird_2)));
        arrayList3.add(new Property(String.valueOf(3), getString(R.string.phrase_param_master_or_slave_hybird_3)));
        arrayList3.add(new Property(String.valueOf(4), getString(R.string.phrase_param_master_or_slave_hybird_4)));
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList3);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.masterOrSlaveSpinner.setAdapter((SpinnerAdapter) arrayAdapter3);
        Button button6 = (Button) inflate.findViewById(R.id.fragment_remote_set_masterOrSlaveButton);
        this.masterOrSlaveButton = button6;
        button6.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Local12KSetFragment.this.masterOrSlaveSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Local12KSetFragment.this.runNormalRemoteWrite("HOLD_SET_MASTER_OR_SLAVE", property.getName(), Local12KSetFragment.this.masterOrSlaveButton);
            }
        });
        this.composedPhaseLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_composedPhaseLayout);
        this.readComposedPhaseSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_readComposedPhaseSpinner);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new Property(String.valueOf(-1), "--"));
        arrayList4.add(new Property(String.valueOf(1), getString(R.string.phrase_param_composed_phase_1)));
        arrayList4.add(new Property(String.valueOf(2), getString(R.string.phrase_param_composed_phase_2)));
        arrayList4.add(new Property(String.valueOf(3), getString(R.string.phrase_param_composed_phase_3)));
        ArrayAdapter arrayAdapter4 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList4);
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.readComposedPhaseSpinner.setAdapter((SpinnerAdapter) arrayAdapter4);
        this.readComposedPhaseSpinner.setEnabled(false);
        this.setComposedPhaseSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_setComposedPhaseSpinner);
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_composed_phase_empty)));
        arrayList5.add(new Property(String.valueOf(0), getString(R.string.phrase_param_composed_phase_0)));
        arrayList5.add(new Property(String.valueOf(1), getString(R.string.phrase_param_composed_phase_1)));
        arrayList5.add(new Property(String.valueOf(2), getString(R.string.phrase_param_composed_phase_2)));
        arrayList5.add(new Property(String.valueOf(3), getString(R.string.phrase_param_composed_phase_3)));
        ArrayAdapter arrayAdapter5 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList5);
        arrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setComposedPhaseSpinner.setAdapter((SpinnerAdapter) arrayAdapter5);
        Button button7 = (Button) inflate.findViewById(R.id.fragment_remote_set_composedPhaseButton);
        this.composedPhaseButton = button7;
        button7.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Local12KSetFragment.this.setComposedPhaseSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Local12KSetFragment.this.runNormalRemoteWrite("HOLD_SET_COMPOSED_PHASE", property.getName(), Local12KSetFragment.this.composedPhaseButton);
            }
        });
        this.batterySharedFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_batterySharedFunctionLayout);
        ToggleButton toggleButton6 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_batterySharedFunctionButton);
        this.batterySharedFunctionButton = toggleButton6;
        toggleButton6.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_BAT_SHARED", local12KSetFragment.batterySharedFunctionButton);
            }
        });
        this.maxAcInputPowerLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_maxAcInputPowerLayout);
        this.maxAcInputPowerEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_maxAcInputPowerEditText);
        Button button8 = (Button) inflate.findViewById(R.id.fragment_remote_set_maxAcInputPowerButton);
        this.maxAcInputPowerButton = button8;
        button8.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_MAX_AC_INPUT_POWER", local12KSetFragment.maxAcInputPowerEditText.getText().toString().trim(), Local12KSetFragment.this.maxAcInputPowerButton);
            }
        });
        this.gridRegulationSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_gridRegulationSpinner);
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add(new Property(String.valueOf(-1), getString(R.string.phrase_please_select)));
        arrayList6.add(new Property(String.valueOf(0), "USA"));
        arrayList6.add(new Property(String.valueOf(1), "Hawaii"));
        arrayList6.add(new Property(String.valueOf(2), "USA(rule21)"));
        arrayList6.add(new Property(String.valueOf(3), "South Africa"));
        arrayList6.add(new Property(String.valueOf(4), "General"));
        arrayList6.add(new Property(String.valueOf(5), "PR-LUMA"));
        ArrayAdapter arrayAdapter6 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList6);
        arrayAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.gridRegulationSpinner.setAdapter((SpinnerAdapter) arrayAdapter6);
        Button button9 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridRegulationButton);
        this.gridRegulationButton = button9;
        button9.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Local12KSetFragment.this.gridRegulationSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Local12KSetFragment.this.runNormalRemoteWrite("_12K_HOLD_GRID_REGULATION", property.getName(), Local12KSetFragment.this.gridRegulationButton);
            }
        });
        this.gridTypeSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_gridTypeSpinner);
        ArrayList arrayList7 = new ArrayList();
        arrayList7.add(new Property(String.valueOf(-1), getString(R.string.phrase_please_select)));
        arrayList7.add(new Property(String.valueOf(0), "240V/120V"));
        arrayList7.add(new Property(String.valueOf(1), "208V/120V"));
        arrayList7.add(new Property(String.valueOf(2), "240V"));
        arrayList7.add(new Property(String.valueOf(3), "230V"));
        arrayList7.add(new Property(String.valueOf(4), "200V/100V"));
        ArrayAdapter arrayAdapter7 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList7);
        arrayAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.gridTypeSpinner.setAdapter((SpinnerAdapter) arrayAdapter7);
        Button button10 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridTypeButton);
        this.gridTypeButton = button10;
        button10.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Local12KSetFragment.this.gridTypeSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Local12KSetFragment.this.runNormalRemoteWrite("_12K_HOLD_GRID_TYPE", property.getName(), Local12KSetFragment.this.gridTypeButton);
            }
        });
        ToggleButton toggleButton7 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_drmsFunctionButton);
        this.drmsFunctionButton = toggleButton7;
        toggleButton7.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_DRMS_EN", local12KSetFragment.drmsFunctionButton);
            }
        });
        this.reconnectTimeEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_reconnectTimeEditText);
        Button button11 = (Button) inflate.findViewById(R.id.fragment_remote_set_reconnectTimeButton);
        this.reconnectTimeButton = button11;
        button11.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_RECONNECT_TIME", local12KSetFragment.reconnectTimeEditText.getText().toString().trim(), Local12KSetFragment.this.reconnectTimeButton);
            }
        });
        this.gridVoltConnHighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltConnHighEditText);
        Button button12 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltConnHighButton);
        this.gridVoltConnHighButton = button12;
        button12.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_CONN_HIGH", local12KSetFragment.gridVoltConnHighEditText.getText().toString().trim(), Local12KSetFragment.this.gridVoltConnHighButton);
            }
        });
        this.gridVoltConnLowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltConnLowEditText);
        Button button13 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltConnLowButton);
        this.gridVoltConnLowButton = button13;
        button13.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_CONN_LOW", local12KSetFragment.gridVoltConnLowEditText.getText().toString().trim(), Local12KSetFragment.this.gridVoltConnLowButton);
            }
        });
        this.gridFreqConnHighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqConnHighEditText);
        Button button14 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqConnHighButton);
        this.gridFreqConnHighButton = button14;
        button14.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_CONN_HIGH", local12KSetFragment.gridFreqConnHighEditText.getText().toString().trim(), Local12KSetFragment.this.gridFreqConnHighButton);
            }
        });
        this.gridFreqConnLowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqConnLowEditText);
        Button button15 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqConnLowButton);
        this.gridFreqConnLowButton = button15;
        button15.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_CONN_LOW", local12KSetFragment.gridFreqConnLowEditText.getText().toString().trim(), Local12KSetFragment.this.gridFreqConnLowButton);
            }
        });
        this.gridVoltLimit1LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit1LowEditText);
        Button button16 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit1LowButton);
        this.gridVoltLimit1LowButton = button16;
        button16.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT1_LOW", local12KSetFragment.gridVoltLimit1LowEditText.getText().toString().trim(), Local12KSetFragment.this.gridVoltLimit1LowButton);
            }
        });
        this.gridVoltLimit1HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit1HighEditText);
        Button button17 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit1HighButton);
        this.gridVoltLimit1HighButton = button17;
        button17.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT1_HIGH", local12KSetFragment.gridVoltLimit1HighEditText.getText().toString().trim(), Local12KSetFragment.this.gridVoltLimit1HighButton);
            }
        });
        this.gridVoltLimit2LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit2LowEditText);
        Button button18 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit2LowButton);
        this.gridVoltLimit2LowButton = button18;
        button18.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT2_LOW", local12KSetFragment.gridVoltLimit2LowEditText.getText().toString().trim(), Local12KSetFragment.this.gridVoltLimit2LowButton);
            }
        });
        this.gridVoltLimit2HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit2HighEditText);
        Button button19 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit2HighButton);
        this.gridVoltLimit2HighButton = button19;
        button19.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT2_HIGH", local12KSetFragment.gridVoltLimit2HighEditText.getText().toString().trim(), Local12KSetFragment.this.gridVoltLimit2HighButton);
            }
        });
        this.gridVoltLimit3LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit3LowEditText);
        Button button20 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit3LowButton);
        this.gridVoltLimit3LowButton = button20;
        button20.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT3_LOW", local12KSetFragment.gridVoltLimit3LowEditText.getText().toString().trim(), Local12KSetFragment.this.gridVoltLimit3LowButton);
            }
        });
        this.gridVoltLimit3HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit3HighEditText);
        Button button21 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit3HighButton);
        this.gridVoltLimit3HighButton = button21;
        button21.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT3_HIGH", local12KSetFragment.gridVoltLimit3HighEditText.getText().toString().trim(), Local12KSetFragment.this.gridVoltLimit3HighButton);
            }
        });
        this.gridFreqLimit1LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit1LowEditText);
        Button button22 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit1LowButton);
        this.gridFreqLimit1LowButton = button22;
        button22.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT1_LOW", local12KSetFragment.gridFreqLimit1LowEditText.getText().toString().trim(), Local12KSetFragment.this.gridFreqLimit1LowButton);
            }
        });
        this.gridFreqLimit1HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit1HighEditText);
        Button button23 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit1HighButton);
        this.gridFreqLimit1HighButton = button23;
        button23.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT1_HIGH", local12KSetFragment.gridFreqLimit1HighEditText.getText().toString().trim(), Local12KSetFragment.this.gridFreqLimit1HighButton);
            }
        });
        this.gridFreqLimit2LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit2LowEditText);
        Button button24 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit2LowButton);
        this.gridFreqLimit2LowButton = button24;
        button24.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT2_LOW", local12KSetFragment.gridFreqLimit2LowEditText.getText().toString().trim(), Local12KSetFragment.this.gridFreqLimit2LowButton);
            }
        });
        this.gridFreqLimit2HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit2HighEditText);
        Button button25 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit2HighButton);
        this.gridFreqLimit2HighButton = button25;
        button25.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT2_HIGH", local12KSetFragment.gridFreqLimit2HighEditText.getText().toString().trim(), Local12KSetFragment.this.gridFreqLimit2HighButton);
            }
        });
        this.gridFreqLimit3LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit3LowEditText);
        Button button26 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit3LowButton);
        this.gridFreqLimit3LowButton = button26;
        button26.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT3_LOW", local12KSetFragment.gridFreqLimit3LowEditText.getText().toString().trim(), Local12KSetFragment.this.gridFreqLimit3LowButton);
            }
        });
        this.gridFreqLimit3HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit3HighEditText);
        Button button27 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit3HighButton);
        this.gridFreqLimit3HighButton = button27;
        button27.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT3_HIGH", local12KSetFragment.gridFreqLimit3HighEditText.getText().toString().trim(), Local12KSetFragment.this.gridFreqLimit3HighButton);
            }
        });
        ToggleButton toggleButton8 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_acChargeFunctionButton);
        this.acChargeFunctionButton = toggleButton8;
        toggleButton8.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.38
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_AC_CHARGE", local12KSetFragment.acChargeFunctionButton);
            }
        });
        this.acChargePowerEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargePowerCmdEditText);
        Button button28 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargePowerCmdButton);
        this.acChargePowerButton = button28;
        button28.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.39
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_POWER_CMD", local12KSetFragment.acChargePowerEditText.getText().toString().trim(), Local12KSetFragment.this.acChargePowerButton);
            }
        });
        this.startAcChargeSOCEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_Start_AC_Charge_SOCEditText);
        Button button29 = (Button) inflate.findViewById(R.id.fragment_remote_set_Start_AC_Charge_SOCButton);
        this.startAcChargeSOCButton = button29;
        button29.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_START_BATTERY_SOC", local12KSetFragment.startAcChargeSOCEditText.getText().toString().trim(), Local12KSetFragment.this.startAcChargeSOCButton);
            }
        });
        this.acChargeSocLimitEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeSocLimitEditText);
        Button button30 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeSocLimitButton);
        this.acChargeSocLimitButton = button30;
        button30.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_SOC_LIMIT", local12KSetFragment.acChargeSocLimitEditText.getText().toString().trim(), Local12KSetFragment.this.acChargeSocLimitButton);
            }
        });
        this.startAcChargeVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_Start_AC_Charge_VoltEditText);
        Button button31 = (Button) inflate.findViewById(R.id.fragment_remote_set_Start_AC_Charge_VoltButton);
        this.startAcChargeVoltButton = button31;
        button31.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.42
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_START_BATTERY_VOLTAGE", local12KSetFragment.startAcChargeVoltEditText.getText().toString().trim(), Local12KSetFragment.this.startAcChargeVoltButton);
            }
        });
        this.acChargeEndBatteryVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndBatteryVoltageEditText);
        Button button32 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndBatteryVoltageButton);
        this.acChargeEndBatteryVoltageButton = button32;
        button32.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.43
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_END_BATTERY_VOLTAGE", local12KSetFragment.acChargeEndBatteryVoltageEditText.getText().toString().trim(), Local12KSetFragment.this.acChargeEndBatteryVoltageButton);
            }
        });
        this.acChargeStartHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartHourEditText);
        this.acChargeStartMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartMinuteEditText);
        Button button33 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartTimeButton);
        this.acChargeStartTimeButton = button33;
        button33.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.44
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_START_TIME", local12KSetFragment.acChargeStartHourEditText, Local12KSetFragment.this.acChargeStartMinuteEditText, Local12KSetFragment.this.acChargeStartTimeButton);
            }
        });
        this.acChargeEndHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndHourEditText);
        this.acChargeEndMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndMinuteEditText);
        Button button34 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndTimeButton);
        this.acChargeEndTimeButton = button34;
        button34.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.45
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_END_TIME", local12KSetFragment.acChargeEndHourEditText, Local12KSetFragment.this.acChargeEndMinuteEditText, Local12KSetFragment.this.acChargeEndTimeButton);
            }
        });
        this.acChargeStartHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartHour1EditText);
        this.acChargeStartMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartMinute1EditText);
        Button button35 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartTime1Button);
        this.acChargeStartTime1Button = button35;
        button35.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.46
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_START_TIME_1", local12KSetFragment.acChargeStartHour1EditText, Local12KSetFragment.this.acChargeStartMinute1EditText, Local12KSetFragment.this.acChargeStartTime1Button);
            }
        });
        this.acChargeEndHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndHour1EditText);
        this.acChargeEndMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndMinute1EditText);
        Button button36 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndTime1Button);
        this.acChargeEndTime1Button = button36;
        button36.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.47
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_END_TIME_1", local12KSetFragment.acChargeEndHour1EditText, Local12KSetFragment.this.acChargeEndMinute1EditText, Local12KSetFragment.this.acChargeEndTime1Button);
            }
        });
        this.acChargeStartHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartHour2EditText);
        this.acChargeStartMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartMinute2EditText);
        Button button37 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartTime2Button);
        this.acChargeStartTime2Button = button37;
        button37.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.48
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_START_TIME_2", local12KSetFragment.acChargeStartHour2EditText, Local12KSetFragment.this.acChargeStartMinute2EditText, Local12KSetFragment.this.acChargeStartTime2Button);
            }
        });
        this.acChargeEndHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndHour2EditText);
        this.acChargeEndMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndMinute2EditText);
        Button button38 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndTime2Button);
        this.acChargeEndTime2Button = button38;
        button38.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.49
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_END_TIME_2", local12KSetFragment.acChargeEndHour2EditText, Local12KSetFragment.this.acChargeEndMinute2EditText, Local12KSetFragment.this.acChargeEndTime2Button);
            }
        });
        ToggleButton toggleButton9 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_forcedChgFunctionButton);
        this.forcedChgFunctionButton = toggleButton9;
        toggleButton9.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.50
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_FORCED_CHG_EN", local12KSetFragment.forcedChgFunctionButton);
            }
        });
        this.forcedChgPowerCmdEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChgPowerCmdEditText);
        Button button39 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChgPowerCmdButton);
        this.forcedChgPowerCmdButton = button39;
        button39.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.51
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_FORCED_CHG_POWER_CMD", local12KSetFragment.forcedChgPowerCmdEditText.getText().toString().trim(), Local12KSetFragment.this.forcedChgPowerCmdButton);
            }
        });
        this.forcedChgSocLimitEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChgSocLimitEditText);
        Button button40 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChgSocLimitButton);
        this.forcedChgSocLimitButton = button40;
        button40.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.52
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_FORCED_CHG_SOC_LIMIT", local12KSetFragment.forcedChgSocLimitEditText.getText().toString().trim(), Local12KSetFragment.this.forcedChgSocLimitButton);
            }
        });
        this.chargeFirstVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_chargeFirstVoltEditText);
        Button button41 = (Button) inflate.findViewById(R.id.fragment_remote_set_chargeFirstVoltButton);
        this.chargeFirstVoltButton = button41;
        button41.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.53
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_CHARGE_FIRST_VOLT", local12KSetFragment.chargeFirstVoltEditText.getText().toString().trim(), Local12KSetFragment.this.chargeFirstVoltButton);
            }
        });
        this.forcedChargeStartHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartHourEditText);
        this.forcedChargeStartMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartMinuteEditText);
        Button button42 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartTimeButton);
        this.forcedChargeStartTimeButton = button42;
        button42.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.54
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_START_TIME", local12KSetFragment.forcedChargeStartHourEditText, Local12KSetFragment.this.forcedChargeStartMinuteEditText, Local12KSetFragment.this.forcedChargeStartTimeButton);
            }
        });
        this.forcedChargeEndHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndHourEditText);
        this.forcedChargeEndMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndMinuteEditText);
        Button button43 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndTimeButton);
        this.forcedChargeEndTimeButton = button43;
        button43.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.55
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_END_TIME", local12KSetFragment.forcedChargeEndHourEditText, Local12KSetFragment.this.forcedChargeEndMinuteEditText, Local12KSetFragment.this.forcedChargeEndTimeButton);
            }
        });
        this.forcedChargeStartHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartHour1EditText);
        this.forcedChargeStartMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartMinute1EditText);
        Button button44 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartTime1Button);
        this.forcedChargeStartTime1Button = button44;
        button44.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.56
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_START_TIME_1", local12KSetFragment.forcedChargeStartHour1EditText, Local12KSetFragment.this.forcedChargeStartMinute1EditText, Local12KSetFragment.this.forcedChargeStartTime1Button);
            }
        });
        this.forcedChargeEndHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndHour1EditText);
        this.forcedChargeEndMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndMinute1EditText);
        Button button45 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndTime1Button);
        this.forcedChargeEndTime1Button = button45;
        button45.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.57
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_END_TIME_1", local12KSetFragment.forcedChargeEndHour1EditText, Local12KSetFragment.this.forcedChargeEndMinute1EditText, Local12KSetFragment.this.forcedChargeEndTime1Button);
            }
        });
        this.forcedChargeStartHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartHour2EditText);
        this.forcedChargeStartMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartMinute2EditText);
        Button button46 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartTime2Button);
        this.forcedChargeStartTime2Button = button46;
        button46.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.58
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_START_TIME_2", local12KSetFragment.forcedChargeStartHour2EditText, Local12KSetFragment.this.forcedChargeStartMinute2EditText, Local12KSetFragment.this.forcedChargeStartTime2Button);
            }
        });
        this.forcedChargeEndHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndHour2EditText);
        this.forcedChargeEndMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndMinute2EditText);
        Button button47 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndTime2Button);
        this.forcedChargeEndTime2Button = button47;
        button47.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.59
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_END_TIME_2", local12KSetFragment.forcedChargeEndHour2EditText, Local12KSetFragment.this.forcedChargeEndMinute2EditText, Local12KSetFragment.this.forcedChargeEndTime2Button);
            }
        });
        this.leadAcidChargeVoltRefEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_leadAcidChargeVoltRefEditText);
        Button button48 = (Button) inflate.findViewById(R.id.fragment_remote_set_leadAcidChargeVoltRefButton);
        this.leadAcidChargeVoltRefButton = button48;
        button48.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.60
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_CHARGE_VOLT_REF", local12KSetFragment.leadAcidChargeVoltRefEditText.getText().toString().trim(), Local12KSetFragment.this.leadAcidChargeVoltRefButton);
            }
        });
        this.floatingVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_floatingVoltageEditText);
        Button button49 = (Button) inflate.findViewById(R.id.fragment_remote_set_floatingVoltageButton);
        this.floatingVoltageButton = button49;
        button49.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.61
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_FLOATING_VOLTAGE", local12KSetFragment.floatingVoltageEditText.getText().toString().trim(), Local12KSetFragment.this.floatingVoltageButton);
            }
        });
        this.batChargeCurrentLimitEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_batChargeCurrentLimitEditText);
        Button button50 = (Button) inflate.findViewById(R.id.fragment_remote_set_batChargeCurrentLimitButton);
        this.batChargeCurrentLimitButton = button50;
        button50.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.62
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("OFF_GRID_HOLD_MAX_GEN_CHG_BAT_CURR", local12KSetFragment.batChargeCurrentLimitEditText.getText().toString().trim(), Local12KSetFragment.this.batChargeCurrentLimitButton);
            }
        });
        this.genRatedPowerEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_genRatedPowerEditText);
        Button button51 = (Button) inflate.findViewById(R.id.fragment_remote_set_genRatedPowerButton);
        this.genRatedPowerButton = button51;
        button51.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.63
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_MAX_GENERATOR_INPUT_POWER", local12KSetFragment.genRatedPowerEditText.getText().toString().trim(), Local12KSetFragment.this.genRatedPowerButton);
            }
        });
        this.chargeStartVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_chargeStartVoltEditText);
        Button button52 = (Button) inflate.findViewById(R.id.fragment_remote_set_chargeStartVoltButton);
        this.chargeStartVoltButton = button52;
        button52.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.64
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("OFF_GRID_HOLD_GEN_CHG_START_VOLT", local12KSetFragment.chargeStartVoltEditText.getText().toString().trim(), Local12KSetFragment.this.chargeStartVoltButton);
            }
        });
        this.chargeEndVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_chargeEndVoltEditText);
        Button button53 = (Button) inflate.findViewById(R.id.fragment_remote_set_chargeEndVoltButton);
        this.chargeEndVoltButton = button53;
        button53.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.65
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("OFF_GRID_HOLD_GEN_CHG_END_VOLT", local12KSetFragment.chargeEndVoltEditText.getText().toString().trim(), Local12KSetFragment.this.chargeEndVoltButton);
            }
        });
        this.chargeStartSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_chargeStartSocEditText);
        Button button54 = (Button) inflate.findViewById(R.id.fragment_remote_set_chargeStartSocButton);
        this.chargeStartSocButton = button54;
        button54.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.66
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("OFF_GRID_HOLD_GEN_CHG_START_SOC", local12KSetFragment.chargeStartSocEditText.getText().toString().trim(), Local12KSetFragment.this.chargeStartSocButton);
            }
        });
        this.chargeEndSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_chargeEndSocEditText);
        Button button55 = (Button) inflate.findViewById(R.id.fragment_remote_set_chargeEndSocButton);
        this.chargeEndSocButton = button55;
        button55.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.67
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("OFF_GRID_HOLD_GEN_CHG_END_SOC", local12KSetFragment.chargeEndSocEditText.getText().toString().trim(), Local12KSetFragment.this.chargeEndSocButton);
            }
        });
        this.chargeCurrentEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_chargeCurrentEditText);
        Button button56 = (Button) inflate.findViewById(R.id.fragment_remote_set_chargeCurrentButton);
        this.chargeCurrentButton = button56;
        button56.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.68
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_CHARGE_RATE", local12KSetFragment.chargeCurrentEditText.getText().toString().trim(), Local12KSetFragment.this.chargeCurrentButton);
            }
        });
        this.batChargeControlSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_batChargeControlSpinner);
        ArrayAdapter arrayAdapter8 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, GlobalInfo.getBatControlList());
        arrayAdapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.batChargeControlSpinner.setAdapter((SpinnerAdapter) arrayAdapter8);
        Button button57 = (Button) inflate.findViewById(R.id.fragment_remote_set_batChargeControlButton);
        this.batChargeControlButton = button57;
        button57.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.69
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Local12KSetFragment.this.batChargeControlSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Local12KSetFragment.this.runControlRemoteWrite("FUNC_BAT_CHARGE_CONTROL", Boolean.parseBoolean(property.getName()), Local12KSetFragment.this.batChargeControlButton);
            }
        });
        this.batDischargeControlSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_batDischargeControlSpinner);
        ArrayAdapter arrayAdapter9 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, GlobalInfo.getBatControlList());
        arrayAdapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.batDischargeControlSpinner.setAdapter((SpinnerAdapter) arrayAdapter9);
        Button button58 = (Button) inflate.findViewById(R.id.fragment_remote_set_batDischargeControlButton);
        this.batDischargeControlButton = button58;
        button58.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.70
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) Local12KSetFragment.this.batDischargeControlSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                Local12KSetFragment.this.runControlRemoteWrite("FUNC_BAT_DISCHARGE_CONTROL", Boolean.parseBoolean(property.getName()), Local12KSetFragment.this.batDischargeControlButton);
            }
        });
        ToggleButton toggleButton10 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgFunctionButton);
        this.forcedDisChgFunctionButton = toggleButton10;
        toggleButton10.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.71
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_FORCED_DISCHG_EN", local12KSetFragment.forcedDisChgFunctionButton);
            }
        });
        this.forcedDisChgPowerCmdEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgPowerCmdEditText);
        Button button59 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgPowerCmdButton);
        this.forcedDisChgPowerCmdButton = button59;
        button59.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.72
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_FORCED_DISCHG_POWER_CMD", local12KSetFragment.forcedDisChgPowerCmdEditText.getText().toString().trim(), Local12KSetFragment.this.forcedDisChgPowerCmdButton);
            }
        });
        this.forcedDisChgSocLimitEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgSocLimitEditText);
        Button button60 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgSocLimitButton);
        this.forcedDisChgSocLimitButton = button60;
        button60.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.73
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_FORCED_DISCHG_SOC_LIMIT", local12KSetFragment.forcedDisChgSocLimitEditText.getText().toString().trim(), Local12KSetFragment.this.forcedDisChgSocLimitButton);
            }
        });
        this.stopDischgVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_stopDischgVoltEditText);
        Button button61 = (Button) inflate.findViewById(R.id.fragment_remote_set_stopDischgVoltButton);
        this.stopDischgVoltButton = button61;
        button61.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.74
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_STOP_DISCHG_VOLT", local12KSetFragment.stopDischgVoltEditText.getText().toString().trim(), Local12KSetFragment.this.stopDischgVoltButton);
            }
        });
        this.forcedDisChargeStartHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartHourEditText);
        this.forcedDisChargeStartMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartMinuteEditText);
        Button button62 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartTimeButton);
        this.forcedDisChargeStartTimeButton = button62;
        button62.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.75
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_START_TIME", local12KSetFragment.forcedDisChargeStartHourEditText, Local12KSetFragment.this.forcedDisChargeStartMinuteEditText, Local12KSetFragment.this.forcedDisChargeStartTimeButton);
            }
        });
        this.forcedDisChargeEndHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndHourEditText);
        this.forcedDisChargeEndMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndMinuteEditText);
        Button button63 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndTimeButton);
        this.forcedDisChargeEndTimeButton = button63;
        button63.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.76
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_END_TIME", local12KSetFragment.forcedDisChargeEndHourEditText, Local12KSetFragment.this.forcedDisChargeEndMinuteEditText, Local12KSetFragment.this.forcedDisChargeEndTimeButton);
            }
        });
        this.forcedDisChargeStartHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartHour1EditText);
        this.forcedDisChargeStartMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartMinute1EditText);
        Button button64 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartTime1Button);
        this.forcedDisChargeStartTime1Button = button64;
        button64.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.77
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_START_TIME_1", local12KSetFragment.forcedDisChargeStartHour1EditText, Local12KSetFragment.this.forcedDisChargeStartMinute1EditText, Local12KSetFragment.this.forcedDisChargeStartTime1Button);
            }
        });
        this.forcedDisChargeEndHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndHour1EditText);
        this.forcedDisChargeEndMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndMinute1EditText);
        Button button65 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndTime1Button);
        this.forcedDisChargeEndTime1Button = button65;
        button65.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.78
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_END_TIME_1", local12KSetFragment.forcedDisChargeEndHour1EditText, Local12KSetFragment.this.forcedDisChargeEndMinute1EditText, Local12KSetFragment.this.forcedDisChargeEndTime1Button);
            }
        });
        this.forcedDisChargeStartHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartHour2EditText);
        this.forcedDisChargeStartMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartMinute2EditText);
        Button button66 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartTime2Button);
        this.forcedDisChargeStartTime2Button = button66;
        button66.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.79
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_START_TIME_2", local12KSetFragment.forcedDisChargeStartHour2EditText, Local12KSetFragment.this.forcedDisChargeStartMinute2EditText, Local12KSetFragment.this.forcedDisChargeStartTime2Button);
            }
        });
        this.forcedDisChargeEndHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndHour2EditText);
        this.forcedDisChargeEndMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndMinute2EditText);
        Button button67 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndTime2Button);
        this.forcedDisChargeEndTime2Button = button67;
        button67.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.80
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_END_TIME_2", local12KSetFragment.forcedDisChargeEndHour2EditText, Local12KSetFragment.this.forcedDisChargeEndMinute2EditText, Local12KSetFragment.this.forcedDisChargeEndTime2Button);
            }
        });
        this.leadAcidDischargeCutOffVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_leadAcidDischargeCutOffVoltEditText);
        Button button68 = (Button) inflate.findViewById(R.id.fragment_remote_set_leadAcidDischargeCutOffVoltButton);
        this.leadAcidDischargeCutOffVoltButton = button68;
        button68.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.81
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT", local12KSetFragment.leadAcidDischargeCutOffVoltEditText.getText().toString().trim(), Local12KSetFragment.this.leadAcidDischargeCutOffVoltButton);
            }
        });
        this.dischgCurrentEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_dischgCurrentEditText);
        Button button69 = (Button) inflate.findViewById(R.id.fragment_remote_set_dischgCurrentButton);
        this.dischgCurrentButton = button69;
        button69.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.82
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_DISCHARGE_RATE", local12KSetFragment.dischgCurrentEditText.getText().toString().trim(), Local12KSetFragment.this.dischgCurrentButton);
            }
        });
        this.onGridDischargeCutoffSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_onGridDischargeCutoffSocEditText);
        Button button70 = (Button) inflate.findViewById(R.id.fragment_remote_set_onGridDischargeCutoffSocButton);
        this.onGridDischargeCutoffSocButton = button70;
        button70.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.83
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_DISCHG_CUT_OFF_SOC_EOD", local12KSetFragment.onGridDischargeCutoffSocEditText.getText().toString().trim(), Local12KSetFragment.this.onGridDischargeCutoffSocButton);
            }
        });
        this.offGridDischargeCutoffSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_offGridDischargeCutoffSocEditText);
        Button button71 = (Button) inflate.findViewById(R.id.fragment_remote_set_offGridDischargeCutoffSocButton);
        this.offGridDischargeCutoffSocButton = button71;
        button71.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.84
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_SOC_LOW_LIMIT_EPS_DISCHG", local12KSetFragment.offGridDischargeCutoffSocEditText.getText().toString().trim(), Local12KSetFragment.this.offGridDischargeCutoffSocButton);
            }
        });
        this.onGridEodVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_onGridEodVoltageEditText);
        Button button72 = (Button) inflate.findViewById(R.id.fragment_remote_set_onGridEodVoltageButton);
        this.onGridEodVoltageButton = button72;
        button72.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.85
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("HOLD_ON_GRID_EOD_VOLTAGE", local12KSetFragment.onGridEodVoltageEditText.getText().toString().trim(), Local12KSetFragment.this.onGridEodVoltageButton);
            }
        });
        ToggleButton toggleButton11 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingFunctionButton);
        this.gridPeakShavingFunctionButton = toggleButton11;
        toggleButton11.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.86
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_GRID_PEAK_SHAVING", local12KSetFragment.gridPeakShavingFunctionButton);
            }
        });
        this.gridPeakShavingPowerEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingPowerEditText);
        Button button73 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingPowerButton);
        this.gridPeakShavingPowerButton = button73;
        button73.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.87
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_GRID_PEAK_SHAVING_POWER", local12KSetFragment.gridPeakShavingPowerEditText.getText().toString().trim(), Local12KSetFragment.this.gridPeakShavingPowerButton);
            }
        });
        this.gridPeakShavingVolt1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingVolt1EditText);
        Button button74 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingVolt1Button);
        this.gridPeakShavingVolt1Button = button74;
        button74.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.88
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_GRID_PEAK_SHAVING_VOLT", local12KSetFragment.gridPeakShavingVolt1EditText.getText().toString().trim(), Local12KSetFragment.this.gridPeakShavingVolt1Button);
            }
        });
        this.gridPeakShavingSoc1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingSoc1EditText);
        Button button75 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingSoc1Button);
        this.gridPeakShavingSoc1Button = button75;
        button75.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.89
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_GRID_PEAK_SHAVING_SOC", local12KSetFragment.gridPeakShavingSoc1EditText.getText().toString().trim(), Local12KSetFragment.this.gridPeakShavingSoc1Button);
            }
        });
        this.gridPeakShavingVolt2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingVolt2EditText);
        Button button76 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingVolt2Button);
        this.gridPeakShavingVolt2Button = button76;
        button76.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.90
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_GRID_PEAK_SHAVING_VOLT_2", local12KSetFragment.gridPeakShavingVolt2EditText.getText().toString().trim(), Local12KSetFragment.this.gridPeakShavingVolt2Button);
            }
        });
        this.gridPeakShavingSoc2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingSoc2EditText);
        Button button77 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridPeakShavingSoc2Button);
        this.gridPeakShavingSoc2Button = button77;
        button77.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.91
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_GRID_PEAK_SHAVING_SOC_2", local12KSetFragment.gridPeakShavingSoc2EditText.getText().toString().trim(), Local12KSetFragment.this.gridPeakShavingSoc2Button);
            }
        });
        this.peakShavingStartHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_peakShavingStartHour1EditText);
        this.peakShavingStartMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_peakShavingStartMinute1EditText);
        Button button78 = (Button) inflate.findViewById(R.id.fragment_remote_set_peakShavingStartTime1Button);
        this.peakShavingStartTime1Button = button78;
        button78.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.92
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_PEAK_SHAVING_START_TIME_1", local12KSetFragment.peakShavingStartHour1EditText, Local12KSetFragment.this.peakShavingStartMinute1EditText, Local12KSetFragment.this.peakShavingStartTime1Button);
            }
        });
        this.peakShavingEndHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_peakShavingEndHour1EditText);
        this.peakShavingEndMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_peakShavingEndMinute1EditText);
        Button button79 = (Button) inflate.findViewById(R.id.fragment_remote_set_peakShavingEndTime1Button);
        this.peakShavingEndTime1Button = button79;
        button79.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.93
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_PEAK_SHAVING_END_TIME_1", local12KSetFragment.peakShavingEndHour1EditText, Local12KSetFragment.this.peakShavingEndMinute1EditText, Local12KSetFragment.this.peakShavingEndTime1Button);
            }
        });
        this.peakShavingStartHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_peakShavingStartHour2EditText);
        this.peakShavingStartMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_peakShavingStartMinute2EditText);
        Button button80 = (Button) inflate.findViewById(R.id.fragment_remote_set_peakShavingStartTime2Button);
        this.peakShavingStartTime2Button = button80;
        button80.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.94
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_PEAK_SHAVING_START_TIME_2", local12KSetFragment.peakShavingStartHour2EditText, Local12KSetFragment.this.peakShavingStartMinute2EditText, Local12KSetFragment.this.peakShavingStartTime2Button);
            }
        });
        this.peakShavingEndHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_peakShavingEndHour2EditText);
        this.peakShavingEndMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_peakShavingEndMinute2EditText);
        Button button81 = (Button) inflate.findViewById(R.id.fragment_remote_set_peakShavingEndTime2Button);
        this.peakShavingEndTime2Button = button81;
        button81.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.95
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runTimeRemoteWrite("HOLD_PEAK_SHAVING_END_TIME_2", local12KSetFragment.peakShavingEndHour2EditText, Local12KSetFragment.this.peakShavingEndMinute2EditText, Local12KSetFragment.this.peakShavingEndTime2Button);
            }
        });
        ToggleButton toggleButton12 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_onGridAlwaysOnFunctionButton);
        this.onGridAlwaysOnFunctionButton = toggleButton12;
        toggleButton12.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.96
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_ON_GRID_ALWAYS_ON", local12KSetFragment.onGridAlwaysOnFunctionButton);
            }
        });
        this.startPvPowerEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_startPvPowerEditText);
        Button button82 = (Button) inflate.findViewById(R.id.fragment_remote_set_startPvPowerButton);
        this.startPvPowerButton = button82;
        button82.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.97
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_START_PV_POWER", local12KSetFragment.startPvPowerEditText.getText().toString().trim(), Local12KSetFragment.this.startPvPowerButton);
            }
        });
        this.smartLoadStartVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_smartLoadStartVoltEditText);
        Button button83 = (Button) inflate.findViewById(R.id.fragment_remote_set_smartLoadStartVoltButton);
        this.smartLoadStartVoltButton = button83;
        button83.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.98
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_SMART_LOAD_START_VOLT", local12KSetFragment.smartLoadStartVoltEditText.getText().toString().trim(), Local12KSetFragment.this.smartLoadStartVoltButton);
            }
        });
        this.smartLoadEndVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_smartLoadEndVoltEditText);
        Button button84 = (Button) inflate.findViewById(R.id.fragment_remote_set_smartLoadEndVoltButton);
        this.smartLoadEndVoltButton = button84;
        button84.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.99
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_SMART_LOAD_END_VOLT", local12KSetFragment.smartLoadEndVoltEditText.getText().toString().trim(), Local12KSetFragment.this.smartLoadEndVoltButton);
            }
        });
        this.smartLoadStartSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_smartLoadStartSocEditText);
        Button button85 = (Button) inflate.findViewById(R.id.fragment_remote_set_smartLoadStartSocButton);
        this.smartLoadStartSocButton = button85;
        button85.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.100
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_SMART_LOAD_START_SOC", local12KSetFragment.smartLoadStartSocEditText.getText().toString().trim(), Local12KSetFragment.this.smartLoadStartSocButton);
            }
        });
        this.smartLoadEndSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_smartLoadEndSocEditText);
        Button button86 = (Button) inflate.findViewById(R.id.fragment_remote_set_smartLoadEndSocButton);
        this.smartLoadEndSocButton = button86;
        button86.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.101
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_SMART_LOAD_END_SOC", local12KSetFragment.smartLoadEndSocEditText.getText().toString().trim(), Local12KSetFragment.this.smartLoadEndSocButton);
            }
        });
        this.acCoupleStartVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acCoupleStartVoltEditText);
        Button button87 = (Button) inflate.findViewById(R.id.fragment_remote_set_acCoupleStartVoltButton);
        this.acCoupleStartVoltButton = button87;
        button87.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.102
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_AC_COUPLE_START_VOLT", local12KSetFragment.acCoupleStartVoltEditText.getText().toString().trim(), Local12KSetFragment.this.acCoupleStartVoltButton);
            }
        });
        this.acCoupleEndVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acCoupleEndVoltEditText);
        Button button88 = (Button) inflate.findViewById(R.id.fragment_remote_set_acCoupleEndVoltButton);
        this.acCoupleEndVoltButton = button88;
        button88.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.103
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_AC_COUPLE_END_VOLT", local12KSetFragment.acCoupleEndVoltEditText.getText().toString().trim(), Local12KSetFragment.this.acCoupleEndVoltButton);
            }
        });
        this.acCoupleStartSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acCoupleStartSocEditText);
        Button button89 = (Button) inflate.findViewById(R.id.fragment_remote_set_acCoupleStartSocButton);
        this.acCoupleStartSocButton = button89;
        button89.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.104
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_AC_COUPLE_START_SOC", local12KSetFragment.acCoupleStartSocEditText.getText().toString().trim(), Local12KSetFragment.this.acCoupleStartSocButton);
            }
        });
        this.acCoupleEndSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acCoupleEndSocEditText);
        Button button90 = (Button) inflate.findViewById(R.id.fragment_remote_set_acCoupleEndSocButton);
        this.acCoupleEndSocButton = button90;
        button90.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.105
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runNormalRemoteWrite("_12K_HOLD_AC_COUPLE_END_SOC", local12KSetFragment.acCoupleEndSocEditText.getText().toString().trim(), Local12KSetFragment.this.acCoupleEndSocButton);
            }
        });
        ToggleButton toggleButton13 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_ctDirectionReversedFunctionButton);
        this.ctDirectionReversedFunctionButton = toggleButton13;
        toggleButton13.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.106
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_CT_DIRECTION_REVERSED", local12KSetFragment.ctDirectionReversedFunctionButton);
            }
        });
        ToggleButton toggleButton14 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_pvArcFunctionButton);
        this.pvArcFunctionButton = toggleButton14;
        toggleButton14.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.107
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runControlRemoteWrite("FUNC_PV_ARC", local12KSetFragment.pvArcFunctionButton);
            }
        });
        Button button91 = (Button) inflate.findViewById(R.id.fragment_local_set_all2DefaultButton);
        this.all2DefaultButton = button91;
        button91.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.108
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment.this.runNormalRemoteWrite("ALL_TO_DEFAULT", String.valueOf(2), Local12KSetFragment.this.all2DefaultButton);
            }
        });
        this.datalogSnEditText = (EditText) inflate.findViewById(R.id.fragment_local_set_datalogSnEditText);
        this.pinEditText = (EditText) inflate.findViewById(R.id.fragment_local_set_pinEditText);
        Button button92 = (Button) inflate.findViewById(R.id.fragment_local_set_datalogSnButton);
        this.datalogSnButton = button92;
        button92.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.109
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = Local12KSetFragment.this.datalogSnEditText.getText().toString();
                String obj2 = Local12KSetFragment.this.pinEditText.getText().toString();
                if (Tool.isEmpty(obj) || Tool.isEmpty(obj2)) {
                    Toast.makeText(Local12KSetFragment.this.getActivity().getApplicationContext(), R.string.page_maintain_remote_set_alert_param_empty, 1).show();
                    return;
                }
                if (obj.length() != 10) {
                    Toast.makeText(Local12KSetFragment.this.getActivity().getApplicationContext(), R.string.page_register_error_datalogSn_length, 1).show();
                    return;
                }
                if (PinTool.verifyDatalog(obj, obj2)) {
                    try {
                        Local12KSetFragment.this.runDatalogParamWrite(1, obj.getBytes("ISO-8859-1"), Local12KSetFragment.this.datalogSnButton);
                        return;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Toast.makeText(Local12KSetFragment.this.getActivity().getApplicationContext(), R.string.page_register_error_check_code_not_match, 1).show();
            }
        });
        Button button93 = (Button) inflate.findViewById(R.id.fragment_local_set_reset2FactoryButton);
        this.reset2FactoryButton = button93;
        button93.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.110
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
                local12KSetFragment.runDatalogParamWrite(3, new byte[]{JSONB.Constants.BC_OBJECT_END}, local12KSetFragment.reset2FactoryButton);
            }
        });
        this.applicationSetTitleLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_application_set_titleLayout);
        this.applicationSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_application_set_layout);
        this.applicationSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_application_set_textView);
        this.applicationSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_application_set_toggleButton);
        this.applicationSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_application_set_paramLayout);
        this.applicationSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.111
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.applicationSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.applicationSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.applicationSetParamLayout.setVisibility(8);
                }
            }
        });
        this.applicationSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.112
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.applicationSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.applicationSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.applicationSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.applicationSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.applicationSetParamLayout.setVisibility(0);
                }
            }
        });
        this.gridConnectSetTitleLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_grid_connect_set_titleLayout);
        this.gridConnectSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_grid_connect_set_layout);
        this.gridConnectSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_grid_connect_set_textView);
        this.gridConnectSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_grid_connect_set_toggleButton);
        this.gridConnectSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_grid_connect_set_paramLayout);
        this.gridConnectSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.113
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.gridConnectSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.gridConnectSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.gridConnectSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.gridConnectSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.gridConnectSetParamLayout.setVisibility(8);
                }
            }
        });
        this.gridConnectSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.114
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.gridConnectSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.gridConnectSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.gridConnectSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.gridConnectSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.gridConnectSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.gridConnectSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.gridConnectSetParamLayout.setVisibility(0);
                }
            }
        });
        this.generatorSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_generator_set_layout);
        this.generatorSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_generator_set_textView);
        this.generatorSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_generator_set_toggleButton);
        this.generatorSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_generator_set_paramLayout);
        this.generatorSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.115
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.generatorSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.generatorSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.generatorSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.generatorSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.generatorSetParamLayout.setVisibility(8);
                }
            }
        });
        this.generatorSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.116
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.generatorSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.generatorSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.generatorSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.generatorSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.generatorSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.generatorSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.generatorSetParamLayout.setVisibility(0);
                }
            }
        });
        this.chargeSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_layout);
        this.chargeSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_textView);
        this.chargeSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_toggleButton);
        this.chargeSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_paramLayout);
        this.chargeSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.117
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.chargeSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.chargeSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.chargeSetParamLayout.setVisibility(8);
                }
            }
        });
        this.chargeSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.118
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.chargeSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.chargeSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.chargeSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.chargeSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.chargeSetParamLayout.setVisibility(0);
                }
            }
        });
        this.dischargeSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_layout);
        this.dischargeSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_textView);
        this.dischargeSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_toggleButton);
        this.dischargeSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_paramLayout);
        this.dischargeSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.119
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.dischargeSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.dischargeSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.dischargeSetParamLayout.setVisibility(8);
                }
            }
        });
        this.dischargeSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.120
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.dischargeSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.dischargeSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.dischargeSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.dischargeSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.dischargeSetParamLayout.setVisibility(0);
                }
            }
        });
        this.dischargePeakShavingSetTitleLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_dischg_peak_shaving_set_titleLayout);
        this.dischargePeakShavingSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_dischg_peak_shaving_set_layout);
        TextView textView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_dischg_peak_shaving_set);
        this.dischargePeakShavingSetTitleTextView = textView;
        textView.setText(getString(R.string.page_maintain_remote_set_label_discharge_set) + " - " + getString(R.string.phrase_param_peak_shaving));
        this.dischargePeakShavingSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_dischg_peak_shaving_set_textView);
        this.dischargePeakShavingSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_dischg_peak_shaving_set_toggleButton);
        this.dischargePeakShavingSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_dischg_peak_shaving_set_paramLayout);
        this.dischargePeakShavingSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.121
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.dischargePeakShavingSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.dischargePeakShavingSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.dischargePeakShavingSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.dischargePeakShavingSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.dischargePeakShavingSetParamLayout.setVisibility(8);
                }
            }
        });
        this.dischargePeakShavingSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.122
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.dischargePeakShavingSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.dischargePeakShavingSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.dischargePeakShavingSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.dischargePeakShavingSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.dischargePeakShavingSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.dischargePeakShavingSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.dischargePeakShavingSetParamLayout.setVisibility(0);
                }
            }
        });
        this.dischargeSmartLoadSetTitleLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_dischg_smart_load_set_titleLayout);
        this.dischargeSmartLoadSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_dischg_smart_load_set_layout);
        TextView textView2 = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_dischg_smart_load_set);
        this.dischargeSmartLoadSetTitleTextView = textView2;
        textView2.setText(getString(R.string.page_maintain_remote_set_label_discharge_set) + " - " + getString(R.string.phrase_param_smart_load));
        this.dischargeSmartLoadSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_dischg_smart_load_set_textView);
        this.dischargeSmartLoadSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_dischg_smart_load_set_toggleButton);
        this.dischargeSmartLoadSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_dischg_smart_load_set_paramLayout);
        this.dischargeSmartLoadSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.123
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.dischargeSmartLoadSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.dischargeSmartLoadSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.dischargeSmartLoadSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.dischargeSmartLoadSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.dischargeSmartLoadSetParamLayout.setVisibility(8);
                }
            }
        });
        this.dischargeSmartLoadSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.124
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.dischargeSmartLoadSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.dischargeSmartLoadSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.dischargeSmartLoadSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.dischargeSmartLoadSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.dischargeSmartLoadSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.dischargeSmartLoadSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.dischargeSmartLoadSetParamLayout.setVisibility(0);
                }
            }
        });
        this.dischargeAcCoupleSetTitleLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_dischg_ac_couple_set_titleLayout);
        this.dischargeAcCoupleSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_dischg_ac_couple_set_layout);
        TextView textView3 = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_dischg_ac_couple_set);
        this.dischargeAcCoupleSetTitleTextView = textView3;
        textView3.setText(getString(R.string.page_maintain_remote_set_label_discharge_set) + " - " + getString(R.string.phrase_param_ac_couple));
        this.dischargeAcCoupleSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_dischg_ac_couple_set_textView);
        this.dischargeAcCoupleSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_dischg_ac_couple_set_toggleButton);
        this.dischargeAcCoupleSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_dischg_ac_couple_set_paramLayout);
        this.dischargeAcCoupleSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.125
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.dischargeAcCoupleSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.dischargeAcCoupleSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.dischargeAcCoupleSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.dischargeAcCoupleSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.dischargeAcCoupleSetParamLayout.setVisibility(8);
                }
            }
        });
        this.dischargeAcCoupleSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.126
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.dischargeAcCoupleSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.dischargeAcCoupleSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.dischargeAcCoupleSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.dischargeAcCoupleSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.dischargeAcCoupleSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.dischargeAcCoupleSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.dischargeAcCoupleSetParamLayout.setVisibility(0);
                }
            }
        });
        this.advancedSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_advanced_set_layout);
        this.advancedSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_advanced_set_textView);
        this.advancedSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_advanced_set_toggleButton);
        this.advancedSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_advanced_set_paramLayout);
        this.advancedSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.127
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.advancedSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.advancedSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.advancedSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.advancedSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.advancedSetParamLayout.setVisibility(8);
                }
            }
        });
        this.advancedSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.128
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.advancedSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.advancedSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.advancedSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.advancedSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.advancedSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.advancedSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.advancedSetParamLayout.setVisibility(0);
                }
            }
        });
        this.wifiModuleSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_wifi_module_set_layout);
        this.wifiModuleSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_wifi_module_set_textView);
        this.wifiModuleSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_wifi_module_set_toggleButton);
        this.wifiModuleSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_wifi_module_set_paramLayout);
        this.wifiModuleSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.129
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.wifiModuleSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.wifiModuleSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.wifiModuleSetParamLayout.setVisibility(0);
                } else {
                    Local12KSetFragment.this.wifiModuleSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.wifiModuleSetParamLayout.setVisibility(8);
                }
            }
        });
        this.wifiModuleSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.130
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Local12KSetFragment.this.wifiModuleSetActionToggleButton.isChecked()) {
                    Local12KSetFragment.this.wifiModuleSetActionToggleButton.setChecked(false);
                    Local12KSetFragment.this.wifiModuleSetActionTextView.setText(R.string.phrase_button_expand);
                    Local12KSetFragment.this.wifiModuleSetParamLayout.setVisibility(8);
                } else {
                    Local12KSetFragment.this.wifiModuleSetActionToggleButton.setChecked(true);
                    Local12KSetFragment.this.wifiModuleSetActionTextView.setText(R.string.phrase_button_collapse);
                    Local12KSetFragment.this.wifiModuleSetParamLayout.setVisibility(0);
                }
            }
        });
        Button button94 = (Button) inflate.findViewById(R.id.fragment_local_set_updateFirmwareButton);
        this.updateFirmwareButton = button94;
        button94.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Local12KSetFragment.this.m322x8bc6755d(view);
            }
        });
        Button button95 = (Button) inflate.findViewById(R.id.fragment_local_set_export_ul_compliance_pdf);
        this.exportULCompliancePDFButton = button95;
        button95.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Local12KSetFragment.this.m323x2004e4fc(view);
            }
        });
        this.created = true;
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment$7, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass7 implements View.OnClickListener {
        AnonymousClass7() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean isChecked = Local12KSetFragment.this.setToStandbyFunctionButton.isChecked();
            AlertDialog.Builder builder = new AlertDialog.Builder(LocalActivity.instance);
            builder.setTitle(isChecked ? R.string.phrase_func_param_normaly : R.string.phrase_func_param_standby).setIcon(android.R.drawable.ic_dialog_info).setMessage(Local12KSetFragment.this.getString(isChecked ? R.string.phrase_func_text_normal : R.string.phrase_func_text_standby) + " " + ((Object) Local12KSetFragment.this.inverterSnTextView.getText())).setPositiveButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment$7$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Local12KSetFragment.AnonymousClass7.this.m324x2d189441(dialogInterface, i);
                }
            }).setNegativeButton(R.string.phrase_button_cancel, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment$7$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Local12KSetFragment.AnonymousClass7.this.m325xab799820(dialogInterface, i);
                }
            });
            builder.show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-nfcx-luxinvpower-view-local-fragment-Local12KSetFragment$7, reason: not valid java name */
        public /* synthetic */ void m324x2d189441(DialogInterface dialogInterface, int i) {
            Local12KSetFragment local12KSetFragment = Local12KSetFragment.this;
            local12KSetFragment.runControlRemoteWrite("FUNC_SET_TO_STANDBY", local12KSetFragment.setToStandbyFunctionButton);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$1$com-nfcx-luxinvpower-view-local-fragment-Local12KSetFragment$7, reason: not valid java name */
        public /* synthetic */ void m325xab799820(DialogInterface dialogInterface, int i) {
            Local12KSetFragment.this.setToStandbyFunctionButton.setChecked(!Local12KSetFragment.this.setToStandbyFunctionButton.isChecked());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$0$com-nfcx-luxinvpower-view-local-fragment-Local12KSetFragment, reason: not valid java name */
    public /* synthetic */ void m322x8bc6755d(View view) {
        Intent intent = new Intent(view.getContext(), (Class<?>) UpdateFirmwareActivity.class);
        intent.putExtra(Constants.LOCAL_CONNECT_TYPE, this.localConnect.getConnectType());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$1$com-nfcx-luxinvpower-view-local-fragment-Local12KSetFragment, reason: not valid java name */
    public /* synthetic */ void m323x2004e4fc(View view) {
        if (this.cacheReadAllResultObj != null) {
            Intent intent = new Intent(view.getContext(), (Class<?>) ULComplianceActivity.class);
            intent.putExtra("cacheReadAllResultText", this.cacheReadAllResultObj.toString());
            startActivity(intent);
        }
    }

    public void refreshFragmentParams() {
        if (this.created) {
            Inverter inverter = this.localConnect.getInverter();
            this.datalogSnTextView.setText(inverter != null ? inverter.getDatalogSn() : "");
            this.inverterSnTextView.setText(inverter != null ? inverter.getSerialNum() : "");
            this.updateFirmwareButton.setEnabled((this.localConnect.getInverter() == null || Tool.isEmpty(this.localConnect.getInverter().getFwCode())) ? false : true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - Local12KFragment onResume...");
        refreshFragmentParams();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        System.out.println("LuxPower - Local12KSetFragment onDestroy...");
    }

    public void clearFragmentData() {
        if (this.created) {
            this.timeDateEditText.setText("");
            this.timeTimeEditText.setText("");
            this.ctSampleRatioSpinner.setSelection(0);
            this.setToStandbyFunctionButton.setChecked(false);
            this.epsFunctionButton.setChecked(false);
            this.epsFrequencySetSpinner.setSelection(0);
            this.fastZeroExportFunctionButton.setChecked(false);
            this.runWithoutGridFunctionButton.setChecked(false);
            this.feedInGridFunctionButton.setChecked(false);
            this.feedInGridPowerPercentEditText.setText("");
            this.masterOrSlaveSpinner.setSelection(0);
            this.readComposedPhaseSpinner.setSelection(0);
            this.setComposedPhaseSpinner.setSelection(0);
            this.batterySharedFunctionButton.setChecked(false);
            this.maxAcInputPowerEditText.setText("");
            this.gridRegulationSpinner.setSelection(0);
            this.gridTypeSpinner.setSelection(0);
            this.drmsFunctionButton.setChecked(false);
            this.reconnectTimeEditText.setText("");
            this.gridVoltConnLowEditText.setText("");
            this.gridVoltConnHighEditText.setText("");
            this.gridFreqConnLowEditText.setText("");
            this.gridFreqConnHighEditText.setText("");
            this.gridVoltLimit1LowEditText.setText("");
            this.gridVoltLimit1HighEditText.setText("");
            this.gridVoltLimit2LowEditText.setText("");
            this.gridVoltLimit2HighEditText.setText("");
            this.gridVoltLimit3LowEditText.setText("");
            this.gridVoltLimit3HighEditText.setText("");
            this.gridFreqLimit1LowEditText.setText("");
            this.gridFreqLimit1HighEditText.setText("");
            this.gridFreqLimit2LowEditText.setText("");
            this.gridFreqLimit2HighEditText.setText("");
            this.gridFreqLimit3LowEditText.setText("");
            this.gridFreqLimit3HighEditText.setText("");
            this.batChargeCurrentLimitEditText.setText("");
            this.genRatedPowerEditText.setText("");
            this.chargeStartVoltEditText.setText("");
            this.chargeEndVoltEditText.setText("");
            this.chargeStartSocEditText.setText("");
            this.chargeEndSocEditText.setText("");
            this.batChargeControlSpinner.setSelection(0);
            this.acChargeFunctionButton.setChecked(false);
            this.acChargePowerEditText.setText("");
            this.startAcChargeSOCEditText.setText("");
            this.acChargeSocLimitEditText.setText("");
            this.startAcChargeVoltEditText.setText("");
            this.acChargeEndBatteryVoltageEditText.setText("");
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
            this.forcedChgFunctionButton.setChecked(false);
            this.forcedChgPowerCmdEditText.setText("");
            this.forcedChgSocLimitEditText.setText("");
            this.chargeFirstVoltEditText.setText("");
            this.forcedChargeStartHourEditText.setText("");
            this.forcedChargeStartMinuteEditText.setText("");
            this.forcedChargeEndHourEditText.setText("");
            this.forcedChargeEndMinuteEditText.setText("");
            this.forcedChargeStartHour1EditText.setText("");
            this.forcedChargeStartMinute1EditText.setText("");
            this.forcedChargeEndHour1EditText.setText("");
            this.forcedChargeEndMinute1EditText.setText("");
            this.forcedChargeStartHour2EditText.setText("");
            this.forcedChargeStartMinute2EditText.setText("");
            this.forcedChargeEndHour2EditText.setText("");
            this.forcedChargeEndMinute2EditText.setText("");
            this.leadAcidChargeVoltRefEditText.setText("");
            this.floatingVoltageEditText.setText("");
            this.chargeCurrentEditText.setText("");
            this.batDischargeControlSpinner.setSelection(0);
            this.forcedDisChgFunctionButton.setChecked(false);
            this.forcedDisChgPowerCmdEditText.setText("");
            this.forcedDisChgSocLimitEditText.setText("");
            this.forcedDisChargeStartHourEditText.setText("");
            this.forcedDisChargeStartMinuteEditText.setText("");
            this.forcedDisChargeEndHourEditText.setText("");
            this.forcedDisChargeEndMinuteEditText.setText("");
            this.forcedDisChargeStartHour1EditText.setText("");
            this.forcedDisChargeStartMinute1EditText.setText("");
            this.forcedDisChargeEndHour1EditText.setText("");
            this.forcedDisChargeEndMinute1EditText.setText("");
            this.forcedDisChargeStartHour2EditText.setText("");
            this.forcedDisChargeStartMinute2EditText.setText("");
            this.forcedDisChargeEndHour2EditText.setText("");
            this.forcedDisChargeEndMinute2EditText.setText("");
            this.onGridEodVoltageEditText.setText("");
            this.leadAcidDischargeCutOffVoltEditText.setText("");
            this.onGridDischargeCutoffSocEditText.setText("");
            this.offGridDischargeCutoffSocEditText.setText("");
            this.dischgCurrentEditText.setText("");
            this.stopDischgVoltEditText.setText("");
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
            this.onGridAlwaysOnFunctionButton.setChecked(false);
            this.startPvPowerEditText.setText("");
            this.smartLoadStartVoltEditText.setText("");
            this.smartLoadEndVoltEditText.setText("");
            this.smartLoadStartSocEditText.setText("");
            this.smartLoadEndSocEditText.setText("");
            this.acCoupleStartVoltEditText.setText("");
            this.acCoupleEndVoltEditText.setText("");
            this.acCoupleStartSocEditText.setText("");
            this.acCoupleEndSocEditText.setText("");
            this.ctDirectionReversedFunctionButton.setChecked(false);
            this.pvArcFunctionButton.setChecked(false);
            this.datalogSnEditText.setText("");
            this.pinEditText.setText("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runNormalRemoteWrite(String str, String str2, Button button) {
        RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
        remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.NORMAL);
        remoteWriteInfo.setHoldParam(str);
        remoteWriteInfo.setValueText(str2);
        remoteWriteInfo.setSetButton(button);
        new WriteParamTask(this).execute(remoteWriteInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runBitRemoteWrite(String str, String str2, Button button) {
        RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
        remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.BIT_PARAM);
        remoteWriteInfo.setBitParam(str);
        remoteWriteInfo.setValueText(str2);
        remoteWriteInfo.setSetButton(button);
        new WriteParamTask(this).execute(remoteWriteInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runTimeRemoteWrite(String str, EditText editText, EditText editText2, Button button) {
        RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
        remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.TIME);
        remoteWriteInfo.setTimeParam(str);
        remoteWriteInfo.setHourText(editText.getText().toString().trim());
        remoteWriteInfo.setMinuteText(editText2.getText().toString().trim());
        remoteWriteInfo.setSetButton(button);
        new WriteParamTask(this).execute(remoteWriteInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runControlRemoteWrite(String str, boolean z, Button button) {
        RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
        remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.CONTROL);
        remoteWriteInfo.setFunctionParam(str);
        remoteWriteInfo.setFunctionToggleButtonChecked(z);
        remoteWriteInfo.setSetButton(button);
        new WriteParamTask(this).execute(remoteWriteInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runControlRemoteWrite(String str, ToggleButton toggleButton) {
        RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
        remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.CONTROL);
        remoteWriteInfo.setFunctionParam(str);
        if (toggleButton != null) {
            remoteWriteInfo.setFunctionToggleButtonChecked(toggleButton.isChecked());
            remoteWriteInfo.setFunctionToggleButton(toggleButton);
        }
        new WriteParamTask(this).execute(remoteWriteInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runDatalogParamWrite(int i, byte[] bArr, Button button) {
        RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
        remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.DATALOG_PARAM);
        remoteWriteInfo.setDatalogParamIndex(Integer.valueOf(i));
        remoteWriteInfo.setDatalogParamValues(bArr);
        remoteWriteInfo.setSetButton(button);
        new WriteParamTask(this).execute(remoteWriteInfo);
    }

    /* loaded from: classes2.dex */
    private static class ReadDatalogParamTask extends AsyncTask<Integer, JSONObject, Void> {
        private Local12KSetFragment fragment;

        public ReadDatalogParamTask(Local12KSetFragment local12KSetFragment) {
            this.fragment = local12KSetFragment;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Integer... numArr) {
            for (Integer num : numArr) {
                String readDatalogParam = this.fragment.localConnect.readDatalogParam(num.intValue());
                if (!Tool.isEmpty(readDatalogParam)) {
                    try {
                        JSONObject createSuccessJSONObject = this.fragment.createSuccessJSONObject();
                        createSuccessJSONObject.put(String.valueOf(num), readDatalogParam);
                        publishProgress(createSuccessJSONObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
                        if (jSONObject.has(String.valueOf(1))) {
                            this.fragment.datalogSnEditText.setText(jSONObject.getString(String.valueOf(1)));
                            this.fragment.pinEditText.setText("");
                        }
                    }
                }
                this.fragment.toast(jSONObject);
                return;
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class ReadMultiParamTask extends AsyncTask<RemoteReadInfo, JSONObject, Void> {
        private Local12KSetFragment fragment;

        public ReadMultiParamTask(Local12KSetFragment local12KSetFragment) {
            this.fragment = local12KSetFragment;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(RemoteReadInfo... remoteReadInfoArr) {
            RemoteReadInfo[] remoteReadInfoArr2 = remoteReadInfoArr;
            int length = remoteReadInfoArr2.length;
            int i = 0;
            while (i < length) {
                RemoteReadInfo remoteReadInfo = remoteReadInfoArr2[i];
                int startRegister = remoteReadInfo.getStartRegister();
                int pointNumber = remoteReadInfo.getPointNumber();
                String sendCommand = this.fragment.localConnect.sendCommand("read_multi_03_" + startRegister + "_" + (pointNumber * 2), DataFrameFactory.createReadMultiHoldDataFrame(this.fragment.localConnect.getTcpProtocol(), this.fragment.localConnect.getDatalogSn(), startRegister, pointNumber));
                JSONObject createSuccessJSONObject = this.fragment.createSuccessJSONObject();
                try {
                    if (!Tool.isEmpty(sendCommand) && sendCommand.length() > 36) {
                        int i2 = startRegister;
                        while (i2 < startRegister + pointNumber) {
                            String[] holdParamsByStartRegister = this.fragment.getHoldParamsByStartRegister(i2);
                            if (holdParamsByStartRegister != null) {
                                int length2 = holdParamsByStartRegister.length;
                                int i3 = 0;
                                while (i3 < length2) {
                                    int i4 = pointNumber;
                                    String str = holdParamsByStartRegister[i3];
                                    if (this.fragment.getStartRegisterByParam(str) != null) {
                                        createSuccessJSONObject.put(str, this.fragment.getValueShowText(str, startRegister, sendCommand));
                                    }
                                    i3++;
                                    pointNumber = i4;
                                }
                            }
                            int i5 = pointNumber;
                            if (i2 == 21) {
                                int i6 = ((21 - startRegister) * 2) + 35;
                                int count = ProTool.count(sendCommand.charAt(i6 + 1), sendCommand.charAt(i6));
                                String[] strArr = this.fragment._21Functions;
                                int length3 = strArr.length;
                                int i7 = 0;
                                while (i7 < length3) {
                                    String str2 = strArr[i7];
                                    String[] strArr2 = strArr;
                                    createSuccessJSONObject.put(str2, ((1 << this.fragment.getBitByFunction(str2).intValue()) & count) > 0);
                                    i7++;
                                    strArr = strArr2;
                                }
                            }
                            if (i2 == 110) {
                                int i8 = ((110 - startRegister) * 2) + 35;
                                int count2 = ProTool.count(sendCommand.charAt(i8 + 1), sendCommand.charAt(i8));
                                String[] strArr3 = this.fragment._110Functions;
                                int length4 = strArr3.length;
                                int i9 = 0;
                                while (i9 < length4) {
                                    String str3 = strArr3[i9];
                                    String[] strArr4 = strArr3;
                                    createSuccessJSONObject.put(str3, ((1 << this.fragment.getBitByFunction(str3).intValue()) & count2) > 0);
                                    i9++;
                                    strArr3 = strArr4;
                                }
                                createSuccessJSONObject.put("BIT_WORKING_MODE", (count2 >> this.fragment.getBitByBitParam("BIT_WORKING_MODE").intValue()) & 1);
                                createSuccessJSONObject.put("BIT_CT_SAMPLE_RATIO", (count2 >> this.fragment.getBitByBitParam("BIT_CT_SAMPLE_RATIO").intValue()) & 3);
                                createSuccessJSONObject.put("BIT_PVCT_SAMPLE_TYPE", (count2 >> this.fragment.getBitByBitParam("BIT_PVCT_SAMPLE_TYPE").intValue()) & 3);
                                createSuccessJSONObject.put("BIT_PVCT_SAMPLE_RATIO", (count2 >> this.fragment.getBitByBitParam("BIT_PVCT_SAMPLE_RATIO").intValue()) & 3);
                            }
                            if (i2 == 179) {
                                int i10 = ((179 - startRegister) * 2) + 35;
                                int count3 = ProTool.count(sendCommand.charAt(i10 + 1), sendCommand.charAt(i10));
                                String[] strArr5 = this.fragment._179Functions;
                                int length5 = strArr5.length;
                                int i11 = 0;
                                while (i11 < length5) {
                                    String str4 = strArr5[i11];
                                    String[] strArr6 = strArr5;
                                    createSuccessJSONObject.put(str4, ((1 << this.fragment.getBitByFunction(str4).intValue()) & count3) > 0);
                                    i11++;
                                    strArr5 = strArr6;
                                }
                            }
                            if (i2 == 226) {
                                int i12 = ((226 - startRegister) * 2) + 35;
                                int count4 = ProTool.count(sendCommand.charAt(i12 + 1), sendCommand.charAt(i12));
                                String[] strArr7 = this.fragment._226Functions;
                                int length6 = strArr7.length;
                                int i13 = 0;
                                while (i13 < length6) {
                                    String str5 = strArr7[i13];
                                    String[] strArr8 = strArr7;
                                    createSuccessJSONObject.put(str5, ((1 << this.fragment.getBitByFunction(str5).intValue()) & count4) > 0);
                                    i13++;
                                    strArr7 = strArr8;
                                }
                            }
                            i2++;
                            pointNumber = i5;
                        }
                    }
                    JSONObject[] jSONObjectArr = new JSONObject[1];
                    try {
                        jSONObjectArr[0] = createSuccessJSONObject;
                        publishProgress(jSONObjectArr);
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        i++;
                        remoteReadInfoArr2 = remoteReadInfoArr;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
                i++;
                remoteReadInfoArr2 = remoteReadInfoArr;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't wrap try/catch for region: R(261:24|(8:554|555|556|557|558|559|560|561)(1:26)|27|28|29|(2:31|(1:35)(1:34))|36|(1:38)|39|(1:41)|42|(3:44|(1:46)(2:48|(1:50)(1:51))|47)|52|(1:54)|55|(1:57)|58|(1:60)|61|(1:63)|64|65|(2:67|(1:71)(1:70))|72|73|74|(6:529|530|531|(1:544)(2:534|535)|536|(1:540)(1:539))(1:76)|77|78|79|(2:520|521)|81|(5:82|83|(2:512|513)|85|(6:86|87|(4:495|496|497|(1:505)(2:501|502))(1:89)|90|91|92))|(4:472|473|474|(230:479|480|481|482|96|97|98|(4:100|101|102|103)(1:468)|104|105|106|(5:451|452|453|454|455)(1:108)|109|110|(1:112)|113|(1:115)|116|(1:118)|119|(1:121)|122|(1:124)|125|(1:127)|128|(1:130)|131|(1:133)|134|(1:136)|137|(1:139)|140|(1:142)|143|(1:145)|146|(1:148)|149|(1:151)|152|(1:154)|155|(1:157)|158|(1:160)|161|(1:163)|164|(1:166)|167|(1:169)|170|(1:172)|173|(1:175)|176|(1:178)|179|(1:181)|182|(1:184)|185|(1:187)|188|(1:190)|191|(1:193)|194|(1:196)|197|(1:199)|200|(1:202)|203|(1:205)|206|(1:208)|209|(1:211)|212|(1:214)|215|(1:217)|218|(1:220)|221|(1:223)|224|(1:226)|227|(1:229)|230|(1:232)|233|(1:235)|236|(1:238)|239|(1:241)|242|(1:244)|245|(1:247)|248|(1:250)|251|(1:253)|254|(1:256)|257|(1:259)|260|(1:262)|263|(1:265)|266|(5:268|269|(1:271)(1:445)|272|273)(1:446)|274|(1:276)|277|(1:279)|280|(1:282)|283|(1:285)|286|(1:288)|289|(1:291)|292|(1:294)|295|(3:297|(1:299)(1:301)|300)|302|(1:304)|305|(1:307)|308|(1:310)|311|(1:313)|314|(1:316)|317|(1:319)|320|(1:322)|323|(1:325)|326|(1:328)|329|(1:331)|332|(1:334)|335|(1:337)|338|(1:340)|341|(1:343)|344|(1:346)|347|(1:349)|350|(1:352)|353|(1:355)|356|(1:358)|359|(1:361)|362|(1:364)|365|(1:367)|368|(1:370)|371|(1:373)|374|(1:376)|377|(1:379)|380|(1:382)|383|(1:385)|386|(1:388)|389|(1:391)|392|(1:394)|395|(1:397)|398|(1:400)|401|(1:403)|404|(1:406)|407|(1:409)|410|(1:412)|413|(1:415)|416|(1:418)|419|(1:421)|422|(1:424)|425|(1:427)|428|(1:430)|431|(1:433)|434|(1:436)|437|(1:439)|440|(2:442|443)(1:444)|14)(1:478))(1:94)|95|96|97|98|(0)(0)|104|105|106|(0)(0)|109|110|(0)|113|(0)|116|(0)|119|(0)|122|(0)|125|(0)|128|(0)|131|(0)|134|(0)|137|(0)|140|(0)|143|(0)|146|(0)|149|(0)|152|(0)|155|(0)|158|(0)|161|(0)|164|(0)|167|(0)|170|(0)|173|(0)|176|(0)|179|(0)|182|(0)|185|(0)|188|(0)|191|(0)|194|(0)|197|(0)|200|(0)|203|(0)|206|(0)|209|(0)|212|(0)|215|(0)|218|(0)|221|(0)|224|(0)|227|(0)|230|(0)|233|(0)|236|(0)|239|(0)|242|(0)|245|(0)|248|(0)|251|(0)|254|(0)|257|(0)|260|(0)|263|(0)|266|(0)(0)|274|(0)|277|(0)|280|(0)|283|(0)|286|(0)|289|(0)|292|(0)|295|(0)|302|(0)|305|(0)|308|(0)|311|(0)|314|(0)|317|(0)|320|(0)|323|(0)|326|(0)|329|(0)|332|(0)|335|(0)|338|(0)|341|(0)|344|(0)|347|(0)|350|(0)|353|(0)|356|(0)|359|(0)|362|(0)|365|(0)|368|(0)|371|(0)|374|(0)|377|(0)|380|(0)|383|(0)|386|(0)|389|(0)|392|(0)|395|(0)|398|(0)|401|(0)|404|(0)|407|(0)|410|(0)|413|(0)|416|(0)|419|(0)|422|(0)|425|(0)|428|(0)|431|(0)|434|(0)|437|(0)|440|(0)(0)|14) */
        /* JADX WARN: Code restructure failed: missing block: B:448:0x0c9b, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:450:0x0ca0, code lost:
        
            r19 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:463:0x0c9d, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:464:0x0c9e, code lost:
        
            r16 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:470:0x0ca4, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:471:0x0ca5, code lost:
        
            r17 = r3;
         */
        /* JADX WARN: Removed duplicated region for block: B:100:0x0292 A[Catch: Exception -> 0x0ca4, TRY_LEAVE, TryCatch #7 {Exception -> 0x0ca4, blocks: (B:98:0x028c, B:100:0x0292), top: B:97:0x028c }] */
        /* JADX WARN: Removed duplicated region for block: B:108:0x02c7  */
        /* JADX WARN: Removed duplicated region for block: B:112:0x02d1 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:115:0x02eb A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:118:0x0302 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:121:0x0319 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:124:0x0330 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:127:0x0347 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:130:0x035e A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:133:0x0375 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:136:0x038c A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:139:0x03a3 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:142:0x03ba A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:145:0x03d1 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:148:0x03e8 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:151:0x03ff A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:154:0x0416 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:157:0x042d A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:160:0x0444 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:163:0x045b A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:166:0x0472 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:169:0x0489 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:172:0x04a0 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:175:0x04b7 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:178:0x04ce A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:181:0x04e5 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:184:0x04fc A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:187:0x0513 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:190:0x052a A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:193:0x0541 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:196:0x0558 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:199:0x056f A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:202:0x0586 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:205:0x059d A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:208:0x05b4 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:211:0x05cb A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:214:0x05e2 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:217:0x05f9 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:220:0x0610 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:223:0x0627 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:226:0x063e A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:229:0x0655 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:232:0x066c A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:235:0x0683 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:238:0x069a A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:241:0x06b1 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:244:0x06c8 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:247:0x06df A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:250:0x06f6 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:253:0x070d A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:256:0x0724 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:259:0x073b A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:262:0x0752 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:265:0x0769 A[Catch: Exception -> 0x02e1, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02e1, blocks: (B:455:0x02b7, B:112:0x02d1, B:115:0x02eb, B:118:0x0302, B:121:0x0319, B:124:0x0330, B:127:0x0347, B:130:0x035e, B:133:0x0375, B:136:0x038c, B:139:0x03a3, B:142:0x03ba, B:145:0x03d1, B:148:0x03e8, B:151:0x03ff, B:154:0x0416, B:157:0x042d, B:160:0x0444, B:163:0x045b, B:166:0x0472, B:169:0x0489, B:172:0x04a0, B:175:0x04b7, B:178:0x04ce, B:181:0x04e5, B:184:0x04fc, B:187:0x0513, B:190:0x052a, B:193:0x0541, B:196:0x0558, B:199:0x056f, B:202:0x0586, B:205:0x059d, B:208:0x05b4, B:211:0x05cb, B:214:0x05e2, B:217:0x05f9, B:220:0x0610, B:223:0x0627, B:226:0x063e, B:229:0x0655, B:232:0x066c, B:235:0x0683, B:238:0x069a, B:241:0x06b1, B:244:0x06c8, B:247:0x06df, B:250:0x06f6, B:253:0x070d, B:256:0x0724, B:259:0x073b, B:262:0x0752, B:265:0x0769), top: B:454:0x02b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:268:0x0780 A[Catch: Exception -> 0x0c9b, TRY_LEAVE, TryCatch #6 {Exception -> 0x0c9b, blocks: (B:110:0x02c9, B:113:0x02e3, B:116:0x02fa, B:119:0x0311, B:122:0x0328, B:125:0x033f, B:128:0x0356, B:131:0x036d, B:134:0x0384, B:137:0x039b, B:140:0x03b2, B:143:0x03c9, B:146:0x03e0, B:149:0x03f7, B:152:0x040e, B:155:0x0425, B:158:0x043c, B:161:0x0453, B:164:0x046a, B:167:0x0481, B:170:0x0498, B:173:0x04af, B:176:0x04c6, B:179:0x04dd, B:182:0x04f4, B:185:0x050b, B:188:0x0522, B:191:0x0539, B:194:0x0550, B:197:0x0567, B:200:0x057e, B:203:0x0595, B:206:0x05ac, B:209:0x05c3, B:212:0x05da, B:215:0x05f1, B:218:0x0608, B:221:0x061f, B:224:0x0636, B:227:0x064d, B:230:0x0664, B:233:0x067b, B:236:0x0692, B:239:0x06a9, B:242:0x06c0, B:245:0x06d7, B:248:0x06ee, B:251:0x0705, B:254:0x071c, B:257:0x0733, B:260:0x074a, B:263:0x0761, B:266:0x0778, B:268:0x0780), top: B:109:0x02c9 }] */
        /* JADX WARN: Removed duplicated region for block: B:276:0x07a6 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:279:0x07bd A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:282:0x07d4 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:285:0x07eb A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:288:0x0802 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:291:0x0819 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:294:0x0830 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:297:0x0847 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:304:0x0868 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:307:0x087f A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:310:0x0896 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:313:0x08ad A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:316:0x08c4 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:319:0x08db A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:322:0x08f2 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:325:0x0909 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:328:0x0920 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:331:0x0937 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:334:0x094e A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:337:0x0965 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:340:0x097c A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:343:0x0993 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:346:0x09aa A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:349:0x09c1 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:352:0x09d8 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:355:0x09ef A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:358:0x0a06 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:361:0x0a1d A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:364:0x0a34 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:367:0x0a4b A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:370:0x0a62 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:373:0x0a79 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:376:0x0a90 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:379:0x0aa7 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:382:0x0abe A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:385:0x0ad5 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:388:0x0aec A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:391:0x0b03 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:394:0x0b1a A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:397:0x0b31 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:400:0x0b48 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:403:0x0b5f A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:406:0x0b76 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:409:0x0b8d A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:412:0x0ba4 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:415:0x0bbb A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:418:0x0bd2 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:421:0x0be9 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:424:0x0c00 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:427:0x0c17 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:430:0x0c2e A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:433:0x0c45 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:436:0x0c5c A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:439:0x0c73 A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:442:0x0c8a A[Catch: Exception -> 0x0cf2, TryCatch #4 {Exception -> 0x0cf2, blocks: (B:273:0x0793, B:274:0x079e, B:276:0x07a6, B:277:0x07b5, B:279:0x07bd, B:280:0x07cc, B:282:0x07d4, B:283:0x07e3, B:285:0x07eb, B:286:0x07fa, B:288:0x0802, B:289:0x0811, B:291:0x0819, B:292:0x0828, B:294:0x0830, B:295:0x083f, B:297:0x0847, B:300:0x0858, B:302:0x0860, B:304:0x0868, B:305:0x0877, B:307:0x087f, B:308:0x088e, B:310:0x0896, B:311:0x08a5, B:313:0x08ad, B:314:0x08bc, B:316:0x08c4, B:317:0x08d3, B:319:0x08db, B:320:0x08ea, B:322:0x08f2, B:323:0x0901, B:325:0x0909, B:326:0x0918, B:328:0x0920, B:329:0x092f, B:331:0x0937, B:332:0x0946, B:334:0x094e, B:335:0x095d, B:337:0x0965, B:338:0x0974, B:340:0x097c, B:341:0x098b, B:343:0x0993, B:344:0x09a2, B:346:0x09aa, B:347:0x09b9, B:349:0x09c1, B:350:0x09d0, B:352:0x09d8, B:353:0x09e7, B:355:0x09ef, B:356:0x09fe, B:358:0x0a06, B:359:0x0a15, B:361:0x0a1d, B:362:0x0a2c, B:364:0x0a34, B:365:0x0a43, B:367:0x0a4b, B:368:0x0a5a, B:370:0x0a62, B:371:0x0a71, B:373:0x0a79, B:374:0x0a88, B:376:0x0a90, B:377:0x0a9f, B:379:0x0aa7, B:380:0x0ab6, B:382:0x0abe, B:383:0x0acd, B:385:0x0ad5, B:386:0x0ae4, B:388:0x0aec, B:389:0x0afb, B:391:0x0b03, B:392:0x0b12, B:394:0x0b1a, B:395:0x0b29, B:397:0x0b31, B:398:0x0b40, B:400:0x0b48, B:401:0x0b57, B:403:0x0b5f, B:404:0x0b6e, B:406:0x0b76, B:407:0x0b85, B:409:0x0b8d, B:410:0x0b9c, B:412:0x0ba4, B:413:0x0bb3, B:415:0x0bbb, B:416:0x0bca, B:418:0x0bd2, B:419:0x0be1, B:421:0x0be9, B:422:0x0bf8, B:424:0x0c00, B:425:0x0c0f, B:427:0x0c17, B:428:0x0c26, B:430:0x0c2e, B:431:0x0c3d, B:433:0x0c45, B:434:0x0c54, B:436:0x0c5c, B:437:0x0c6b, B:439:0x0c73, B:440:0x0c82, B:442:0x0c8a, B:5:0x0cdc), top: B:272:0x0793 }] */
        /* JADX WARN: Removed duplicated region for block: B:444:0x0d12 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:446:0x079c  */
        /* JADX WARN: Removed duplicated region for block: B:451:0x02af A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:468:0x02a5  */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onProgressUpdate(org.json.JSONObject... r28) {
            /*
                Method dump skipped, instructions count: 3370
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.ReadMultiParamTask.onProgressUpdate(org.json.JSONObject[]):void");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            super.onPostExecute((ReadMultiParamTask) r2);
            this.fragment.readAllButton.setEnabled(true);
            this.fragment.exportULCompliancePDFButton.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class WriteParamTask extends AsyncTask<RemoteWriteInfo, Void, JSONObject> {
        private Local12KSetFragment fragment;
        private RemoteWriteInfo remoteWriteInfo;

        public WriteParamTask(Local12KSetFragment local12KSetFragment) {
            this.fragment = local12KSetFragment;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
            this.remoteWriteInfo.setEnabled(false);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(RemoteWriteInfo... remoteWriteInfoArr) {
            Integer num;
            Integer bitByFunction;
            Integer valueOf;
            RemoteWriteInfo remoteWriteInfo = remoteWriteInfoArr[0];
            if (remoteWriteInfo != null && remoteWriteInfo.getRemoteWriteType() != null) {
                this.remoteWriteInfo = remoteWriteInfo;
                publishProgress(new Void[0]);
                int i = AnonymousClass131.$SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[remoteWriteInfo.getRemoteWriteType().ordinal()];
                if (i == 1) {
                    String valueText = remoteWriteInfo.getValueText();
                    if (Tool.isEmpty(valueText)) {
                        return this.fragment.createFailureJSONObject("PARAM_EMPTY");
                    }
                    String holdParam = remoteWriteInfo.getHoldParam();
                    Integer startRegisterByParam = this.fragment.getStartRegisterByParam(holdParam);
                    if (!"HOLD_TIME".equals(holdParam)) {
                        Integer valueByParam = this.fragment.getValueByParam(holdParam, valueText);
                        if (valueByParam == null) {
                            return this.fragment.createFailureJSONObject("PARAM_ERROR");
                        }
                        return this.fragment.localConnect.writeSingle(startRegisterByParam.intValue(), valueByParam.intValue()) ? this.fragment.createSuccessJSONObject() : this.fragment.createFailureJSONObject("FAILED");
                    }
                    Date parseDateTime = InvTool.parseDateTime(valueText);
                    if (parseDateTime == null) {
                        return this.fragment.createFailureJSONObject("PARAM_ERROR");
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(parseDateTime);
                    return this.fragment.localConnect.writeMulti(startRegisterByParam.intValue(), 3, new byte[]{(byte) (calendar.get(1) + (-2000)), (byte) (calendar.get(2) + 1), (byte) calendar.get(5), (byte) calendar.get(11), (byte) calendar.get(12), (byte) calendar.get(13)}) ? this.fragment.createSuccessJSONObject() : this.fragment.createFailureJSONObject("FAILED");
                }
                if (i == 2) {
                    Integer registerByBitParam = this.fragment.getRegisterByBitParam(remoteWriteInfo.getBitParam());
                    Integer readSingle03 = this.fragment.localConnect.readSingle03(registerByBitParam.intValue());
                    if (readSingle03 == null) {
                        return this.fragment.createFailureJSONObject("FAILED");
                    }
                    Integer bitByBitParam = this.fragment.getBitByBitParam(remoteWriteInfo.getBitParam());
                    Integer bitSizeByBitParam = this.fragment.getBitSizeByBitParam(remoteWriteInfo.getBitParam());
                    try {
                        num = Integer.valueOf(Integer.parseInt(remoteWriteInfo.getValueText()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        num = null;
                    }
                    if (bitByBitParam == null || bitSizeByBitParam == null || num == null) {
                        return this.fragment.createFailureJSONObject("FAILED");
                    }
                    int intValue = bitByBitParam.intValue() + bitSizeByBitParam.intValue();
                    return this.fragment.localConnect.writeSingle(registerByBitParam.intValue(), ((((readSingle03.intValue() >> intValue) << intValue) + (num.intValue() << bitByBitParam.intValue())) + (readSingle03.intValue() & ((1 << bitByBitParam.intValue()) - 1))) & SupportMenu.USER_MASK) ? this.fragment.createSuccessJSONObject() : this.fragment.createFailureJSONObject("FAILED");
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
                            return this.fragment.createFailureJSONObject("OUT_RANGE_ERROR");
                        }
                        if (parseInt2 < 0 || parseInt2 > 59) {
                            return this.fragment.createFailureJSONObject("OUT_RANGE_ERROR");
                        }
                        return this.fragment.localConnect.writeSingle(this.fragment.getStartRegisterByParam(remoteWriteInfo.getTimeParam()).intValue(), ProTool.count(parseInt2, parseInt)) ? this.fragment.createSuccessJSONObject() : this.fragment.createFailureJSONObject("FAILED");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return this.fragment.createFailureJSONObject("INTEGER_FORMAT_ERROR");
                    }
                }
                if (i == 4) {
                    Integer registerByFunction = this.fragment.getRegisterByFunction(remoteWriteInfo.getFunctionParam());
                    Integer readSingle032 = this.fragment.localConnect.readSingle03(registerByFunction.intValue());
                    if (readSingle032 != null && (bitByFunction = this.fragment.getBitByFunction(remoteWriteInfo.getFunctionParam())) != null) {
                        if (remoteWriteInfo.isFunctionToggleButtonChecked()) {
                            valueOf = Integer.valueOf(readSingle032.intValue() | (1 << bitByFunction.intValue()));
                        } else {
                            valueOf = Integer.valueOf(readSingle032.intValue() & (~(1 << bitByFunction.intValue())) & SupportMenu.USER_MASK);
                        }
                        return this.fragment.localConnect.writeSingle(registerByFunction.intValue(), Integer.valueOf(valueOf.intValue() & SupportMenu.USER_MASK).intValue()) ? this.fragment.createSuccessJSONObject() : this.fragment.createFailureJSONObject("FAILED");
                    }
                    return this.fragment.createFailureJSONObject("FAILED");
                }
                if (i == 5) {
                    if (remoteWriteInfo.getDatalogParamIndex() == null || remoteWriteInfo.getDatalogParamValues() == null) {
                        return this.fragment.createFailureJSONObject("PARAM_EMPTY");
                    }
                    return this.fragment.localConnect.writeDatalogParam(remoteWriteInfo.getDatalogParamIndex().intValue(), remoteWriteInfo.getDatalogParamValues()) ? this.fragment.createSuccessJSONObject() : this.fragment.createFailureJSONObject("FAILED");
                }
            }
            return this.fragment.createFailureJSONObject("UNKNOWN_ERROR");
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
                        Tool.alert(this.fragment.getActivity(), R.string.local_set_result_success);
                        if (this.remoteWriteInfo != null && REMOTE_WRITE_TYPE.CONTROL.equals(this.remoteWriteInfo.getRemoteWriteType())) {
                            if ("FUNC_BAT_CHARGE_CONTROL".equals(this.remoteWriteInfo.getFunctionParam())) {
                                this.fragment.refreshBattChgState(this.remoteWriteInfo.isFunctionToggleButtonChecked());
                            } else if ("FUNC_BAT_DISCHARGE_CONTROL".equals(this.remoteWriteInfo.getFunctionParam())) {
                                this.fragment.refreshBattDisChgState(this.remoteWriteInfo.isFunctionToggleButtonChecked());
                            }
                        }
                    }
                }
                if (this.remoteWriteInfo != null && REMOTE_WRITE_TYPE.CONTROL.equals(this.remoteWriteInfo.getRemoteWriteType()) && this.remoteWriteInfo.getFunctionToggleButton() != null) {
                    this.remoteWriteInfo.getFunctionToggleButton().setChecked(!this.remoteWriteInfo.getFunctionToggleButton().isChecked());
                }
                this.fragment.toast(jSONObject);
            } finally {
                this.remoteWriteInfo.setEnabled(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment$131, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass131 {
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
            try {
                $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[REMOTE_WRITE_TYPE.DATALOG_PARAM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBattChgState(boolean z) {
        this.acChargeEndBatteryVoltageEditText.setEnabled(z);
        this.acChargeEndBatteryVoltageButton.setEnabled(z);
        this.startAcChargeVoltEditText.setEnabled(z);
        this.startAcChargeVoltButton.setEnabled(z);
        this.chargeFirstVoltEditText.setEnabled(z);
        this.chargeFirstVoltButton.setEnabled(z);
        this.chargeStartVoltEditText.setEnabled(z);
        this.chargeStartVoltButton.setEnabled(z);
        this.chargeEndVoltEditText.setEnabled(z);
        this.chargeEndVoltButton.setEnabled(z);
        this.startAcChargeSOCEditText.setEnabled(!z);
        this.startAcChargeSOCButton.setEnabled(!z);
        this.acChargeSocLimitEditText.setEnabled(!z);
        this.acChargeSocLimitButton.setEnabled(!z);
        this.forcedChgSocLimitEditText.setEnabled(!z);
        this.forcedChgSocLimitButton.setEnabled(!z);
        this.chargeStartSocEditText.setEnabled(!z);
        this.chargeStartSocButton.setEnabled(!z);
        this.chargeEndSocEditText.setEnabled(!z);
        this.chargeEndSocButton.setEnabled(!z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBattDisChgState(boolean z) {
        this.stopDischgVoltEditText.setEnabled(z);
        this.stopDischgVoltButton.setEnabled(z);
        this.onGridEodVoltageEditText.setEnabled(z);
        this.onGridEodVoltageButton.setEnabled(z);
        this.leadAcidDischargeCutOffVoltEditText.setEnabled(z);
        this.leadAcidDischargeCutOffVoltButton.setEnabled(z);
        this.gridPeakShavingVolt1EditText.setEnabled(z);
        this.gridPeakShavingVolt1Button.setEnabled(z);
        this.gridPeakShavingVolt2EditText.setEnabled(z);
        this.gridPeakShavingVolt2Button.setEnabled(z);
        this.smartLoadStartVoltEditText.setEnabled(z);
        this.smartLoadStartVoltButton.setEnabled(z);
        this.smartLoadEndVoltEditText.setEnabled(z);
        this.smartLoadEndVoltButton.setEnabled(z);
        this.acCoupleStartVoltEditText.setEnabled(z);
        this.acCoupleStartVoltButton.setEnabled(z);
        this.acCoupleEndVoltEditText.setEnabled(z);
        this.acCoupleEndVoltButton.setEnabled(z);
        this.forcedDisChgSocLimitEditText.setEnabled(!z);
        this.forcedDisChgSocLimitButton.setEnabled(!z);
        this.onGridDischargeCutoffSocEditText.setEnabled(!z);
        this.onGridDischargeCutoffSocButton.setEnabled(!z);
        this.offGridDischargeCutoffSocEditText.setEnabled(!z);
        this.offGridDischargeCutoffSocButton.setEnabled(!z);
        this.gridPeakShavingSoc1EditText.setEnabled(!z);
        this.gridPeakShavingSoc1Button.setEnabled(!z);
        this.gridPeakShavingSoc2EditText.setEnabled(!z);
        this.gridPeakShavingSoc2Button.setEnabled(!z);
        this.smartLoadStartSocEditText.setEnabled(!z);
        this.smartLoadStartSocButton.setEnabled(!z);
        this.smartLoadEndSocEditText.setEnabled(!z);
        this.smartLoadEndSocButton.setEnabled(!z);
        this.acCoupleStartSocEditText.setEnabled(!z);
        this.acCoupleStartSocButton.setEnabled(!z);
        this.acCoupleEndSocEditText.setEnabled(!z);
        this.acCoupleEndSocButton.setEnabled(!z);
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
            Toast.makeText(getActivity().getApplicationContext(), R.string.phrase_toast_network_error, 1).show();
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
                Toast.makeText(getActivity().getApplicationContext(), R.string.page_maintain_remote_set_result_device_offline, 1).show();
                return;
            case 1:
                Toast.makeText(getActivity().getApplicationContext(), R.string.page_maintain_remote_set_result_set_undo, 1).show();
                return;
            case 2:
                Toast.makeText(getActivity().getApplicationContext(), R.string.page_maintain_remote_set_result_timeout, 1).show();
                return;
            case 3:
                Toast.makeText(getActivity().getApplicationContext(), R.string.page_maintain_remote_set_alert_param_should_int, 1).show();
                return;
            case 4:
                Toast.makeText(getActivity().getApplicationContext(), R.string.page_maintain_remote_set_alert_param_empty, 1).show();
                return;
            case 5:
                Toast.makeText(getActivity().getApplicationContext(), R.string.page_maintain_remote_set_result_param_error, 1).show();
                return;
            case 6:
                Toast.makeText(getActivity().getApplicationContext(), R.string.page_maintain_remote_set_result_command_not_send, 1).show();
                return;
            case 7:
            case '\t':
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.page_maintain_remote_set_result_failed) + " " + jSONObject.getInt("errorCode"), 1).show();
                return;
            case '\b':
                Toast.makeText(getActivity().getApplicationContext(), R.string.page_maintain_remote_set_result_server_exception, 1).show();
                return;
            case '\n':
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.page_maintain_remote_set_alert_param_out_range), 1).show();
                return;
            default:
                Toast.makeText(getActivity().getApplicationContext(), R.string.local_set_result_failed, 1).show();
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getRegisterByBitParam(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1236111220:
                if (str.equals("BIT_PVCT_SAMPLE_RATIO")) {
                    c = 0;
                    break;
                }
                break;
            case -503707613:
                if (str.equals("BIT_WORKING_MODE")) {
                    c = 1;
                    break;
                }
                break;
            case 189011893:
                if (str.equals("BIT_DISCHG_CONTROL_TYPE")) {
                    c = 2;
                    break;
                }
                break;
            case 1068586617:
                if (str.equals("BIT_PVCT_SAMPLE_TYPE")) {
                    c = 3;
                    break;
                }
                break;
            case 1073806034:
                if (str.equals("BIT_CT_SAMPLE_RATIO")) {
                    c = 4;
                    break;
                }
                break;
            case 1642564362:
                if (str.equals("BIT_ON_GRID_EOD_TYPE")) {
                    c = 5;
                    break;
                }
                break;
            case 1775981274:
                if (str.equals("BIT_AC_CHARGE_TYPE")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 3:
            case 4:
                return 110;
            case 2:
            case 5:
            case 6:
                return 120;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getBitByBitParam(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1236111220:
                if (str.equals("BIT_PVCT_SAMPLE_RATIO")) {
                    c = 0;
                    break;
                }
                break;
            case -503707613:
                if (str.equals("BIT_WORKING_MODE")) {
                    c = 1;
                    break;
                }
                break;
            case 189011893:
                if (str.equals("BIT_DISCHG_CONTROL_TYPE")) {
                    c = 2;
                    break;
                }
                break;
            case 1068586617:
                if (str.equals("BIT_PVCT_SAMPLE_TYPE")) {
                    c = 3;
                    break;
                }
                break;
            case 1073806034:
                if (str.equals("BIT_CT_SAMPLE_RATIO")) {
                    c = 4;
                    break;
                }
                break;
            case 1642564362:
                if (str.equals("BIT_ON_GRID_EOD_TYPE")) {
                    c = 5;
                    break;
                }
                break;
            case 1775981274:
                if (str.equals("BIT_AC_CHARGE_TYPE")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 12;
            case 1:
                return 11;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 1;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
    
        if (r8.equals("BIT_WORKING_MODE") == false) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Integer getBitSizeByBitParam(java.lang.String r8) {
        /*
            r7 = this;
            r8.hashCode()
            int r0 = r8.hashCode()
            r1 = 3
            r2 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r4 = 2
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            r6 = -1
            switch(r0) {
                case -1236111220: goto L58;
                case -503707613: goto L4f;
                case 189011893: goto L44;
                case 1068586617: goto L39;
                case 1073806034: goto L2e;
                case 1642564362: goto L23;
                case 1775981274: goto L18;
                default: goto L16;
            }
        L16:
            r2 = r6
            goto L62
        L18:
            java.lang.String r0 = "BIT_AC_CHARGE_TYPE"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L21
            goto L16
        L21:
            r2 = 6
            goto L62
        L23:
            java.lang.String r0 = "BIT_ON_GRID_EOD_TYPE"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L2c
            goto L16
        L2c:
            r2 = 5
            goto L62
        L2e:
            java.lang.String r0 = "BIT_CT_SAMPLE_RATIO"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L37
            goto L16
        L37:
            r2 = 4
            goto L62
        L39:
            java.lang.String r0 = "BIT_PVCT_SAMPLE_TYPE"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L42
            goto L16
        L42:
            r2 = r1
            goto L62
        L44:
            java.lang.String r0 = "BIT_DISCHG_CONTROL_TYPE"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L4d
            goto L16
        L4d:
            r2 = r4
            goto L62
        L4f:
            java.lang.String r0 = "BIT_WORKING_MODE"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L62
            goto L16
        L58:
            java.lang.String r0 = "BIT_PVCT_SAMPLE_RATIO"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L61
            goto L16
        L61:
            r2 = 0
        L62:
            switch(r2) {
                case 0: goto L6f;
                case 1: goto L6e;
                case 2: goto L6d;
                case 3: goto L6d;
                case 4: goto L6d;
                case 5: goto L6c;
                case 6: goto L67;
                default: goto L65;
            }
        L65:
            r8 = 0
            return r8
        L67:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r1)
            return r8
        L6c:
            return r3
        L6d:
            return r5
        L6e:
            return r3
        L6f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.getBitSizeByBitParam(java.lang.String):java.lang.Integer");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getStartRegisterByParam(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2146680827:
                if (str.equals("HOLD_UVF_DROOP_KUF")) {
                    c = 0;
                    break;
                }
                break;
            case -2110570028:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE")) {
                    c = 1;
                    break;
                }
                break;
            case -2106433585:
                if (str.equals("HOLD_GRID_FREQ_CONN_LOW")) {
                    c = 2;
                    break;
                }
                break;
            case -2092080778:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR")) {
                    c = 3;
                    break;
                }
                break;
            case -2091729313:
                if (str.equals("HOLD_AC_CHARGE_END_TIME")) {
                    c = 4;
                    break;
                }
                break;
            case -2070600516:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_LOW_TIME")) {
                    c = 5;
                    break;
                }
                break;
            case -2064663285:
                if (str.equals("HOLD_SET_COMPOSED_PHASE")) {
                    c = 6;
                    break;
                }
                break;
            case -2051169280:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_CHG")) {
                    c = 7;
                    break;
                }
                break;
            case -2012582261:
                if (str.equals("HOLD_FEED_IN_GRID_POWER_PERCENT")) {
                    c = '\b';
                    break;
                }
                break;
            case -1922746271:
                if (str.equals("HOLD_LEAD_ACID_CHARGE_VOLT_REF")) {
                    c = '\t';
                    break;
                }
                break;
            case -1917271147:
                if (str.equals("_12K_HOLD_START_PV_POWER")) {
                    c = '\n';
                    break;
                }
                break;
            case -1910210817:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_TIME_1")) {
                    c = 11;
                    break;
                }
                break;
            case -1910210816:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_TIME_2")) {
                    c = '\f';
                    break;
                }
                break;
            case -1907123448:
                if (str.equals("OFF_GRID_HOLD_GEN_CHG_END_VOLT")) {
                    c = '\r';
                    break;
                }
                break;
            case -1905186084:
                if (str.equals("_12K_HOLD_SMART_LOAD_END_VOLT")) {
                    c = 14;
                    break;
                }
                break;
            case -1876698434:
                if (str.equals("HOLD_FORCED_DISCHG_SOC_LIMIT")) {
                    c = 15;
                    break;
                }
                break;
            case -1862575828:
                if (str.equals("_12K_HOLD_SMART_LOAD_END_SOC")) {
                    c = 16;
                    break;
                }
                break;
            case -1854323434:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_DISCHG")) {
                    c = 17;
                    break;
                }
                break;
            case -1790606454:
                if (str.equals("HOLD_PEAK_SHAVING_END_TIME_1")) {
                    c = 18;
                    break;
                }
                break;
            case -1790606453:
                if (str.equals("HOLD_PEAK_SHAVING_END_TIME_2")) {
                    c = 19;
                    break;
                }
                break;
            case -1784648912:
                if (str.equals("HOLD_P_TO_USER_START_DISCHG")) {
                    c = 20;
                    break;
                }
                break;
            case -1774171999:
                if (str.equals("_12K_HOLD_OVF_DERATE_START_POINT")) {
                    c = 21;
                    break;
                }
                break;
            case -1738912721:
                if (str.equals("HOLD_EQUALIZATION_VOLTAGE")) {
                    c = 22;
                    break;
                }
                break;
            case -1724289777:
                if (str.equals("HOLD_UVF_DERATE_START_POINT")) {
                    c = 23;
                    break;
                }
                break;
            case -1670871462:
                if (str.equals("OFF_GRID_HOLD_MAX_GEN_CHG_BAT_CURR")) {
                    c = 24;
                    break;
                }
                break;
            case -1662706451:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE_1")) {
                    c = 25;
                    break;
                }
                break;
            case -1662706450:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE_2")) {
                    c = 26;
                    break;
                }
                break;
            case -1657690225:
                if (str.equals("HOLD_COM_ADDR")) {
                    c = 27;
                    break;
                }
                break;
            case -1612429665:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR_1")) {
                    c = 28;
                    break;
                }
                break;
            case -1612429664:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR_2")) {
                    c = 29;
                    break;
                }
                break;
            case -1605958379:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_DISCHG")) {
                    c = 30;
                    break;
                }
                break;
            case -1567068683:
                if (str.equals("_12K_HOLD_SMART_LOAD_START_VOLT")) {
                    c = 31;
                    break;
                }
                break;
            case -1563900533:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR")) {
                    c = ' ';
                    break;
                }
                break;
            case -1563549068:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_TIME")) {
                    c = '!';
                    break;
                }
                break;
            case -1551617594:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR")) {
                    c = '\"';
                    break;
                }
                break;
            case -1551266129:
                if (str.equals("HOLD_FORCED_CHARGE_START_TIME")) {
                    c = '#';
                    break;
                }
                break;
            case -1536953061:
                if (str.equals("_12K_HOLD_STOP_DISCHG_VOLT")) {
                    c = '$';
                    break;
                }
                break;
            case -1524255375:
                if (str.equals("HOLD_PV_INPUT_MODE")) {
                    c = '%';
                    break;
                }
                break;
            case -1475031011:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE")) {
                    c = '&';
                    break;
                }
                break;
            case -1474656009:
                if (str.equals("_12K_HOLD_GRID_REGULATION")) {
                    c = '\'';
                    break;
                }
                break;
            case -1448232788:
                if (str.equals("_12K_HOLD_AC_COUPLE_START_VOLT")) {
                    c = '(';
                    break;
                }
                break;
            case -1433471711:
                if (str.equals("HOLD_AC_CHARGE_START_BATTERY_VOLTAGE")) {
                    c = ')';
                    break;
                }
                break;
            case -1413838822:
                if (str.equals("HOLD_AC_CHARGE_END_BATTERY_VOLTAGE")) {
                    c = '*';
                    break;
                }
                break;
            case -1376342106:
                if (str.equals("HOLD_VREF_ADJUSTMENT_TIME_CONSTANT")) {
                    c = '+';
                    break;
                }
                break;
            case -1367869989:
                if (str.equals("ALL_TO_DEFAULT")) {
                    c = ',';
                    break;
                }
                break;
            case -1352201891:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_HIGH")) {
                    c = '-';
                    break;
                }
                break;
            case -1345314128:
                if (str.equals("HOLD_EQUALIZATION_PERIOD")) {
                    c = '.';
                    break;
                }
                break;
            case -1323572740:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_HIGH")) {
                    c = '/';
                    break;
                }
                break;
            case -1294943589:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_HIGH")) {
                    c = '0';
                    break;
                }
                break;
            case -1288210873:
                if (str.equals("_12K_HOLD_GRID_TYPE")) {
                    c = '1';
                    break;
                }
                break;
            case -1282654362:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_HIGH")) {
                    c = '2';
                    break;
                }
                break;
            case -1274671800:
                if (str.equals("HOLD_FORCED_CHARGE_END_TIME_1")) {
                    c = '3';
                    break;
                }
                break;
            case -1274671799:
                if (str.equals("HOLD_FORCED_CHARGE_END_TIME_2")) {
                    c = '4';
                    break;
                }
                break;
            case -1254025211:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_HIGH")) {
                    c = '5';
                    break;
                }
                break;
            case -1225396060:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_HIGH")) {
                    c = '6';
                    break;
                }
                break;
            case -1119226968:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE_1")) {
                    c = '7';
                    break;
                }
                break;
            case -1119226967:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE_2")) {
                    c = '8';
                    break;
                }
                break;
            case -1033230202:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE_1")) {
                    c = '9';
                    break;
                }
                break;
            case -1033230201:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE_2")) {
                    c = ':';
                    break;
                }
                break;
            case -1028461968:
                if (str.equals("HOLD_MIN_Q_PERCENT_FOR_QV")) {
                    c = ';';
                    break;
                }
                break;
            case -917268805:
                if (str.equals("HOLD_FORCED_DISCHG_POWER_CMD")) {
                    c = Typography.less;
                    break;
                }
                break;
            case -875057049:
                if (str.equals("HOLD_GRID_FREQ_CONN_HIGH")) {
                    c = '=';
                    break;
                }
                break;
            case -823881906:
                if (str.equals("HOLD_MAX_AC_INPUT_POWER")) {
                    c = Typography.greater;
                    break;
                }
                break;
            case -750853128:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR_1")) {
                    c = '?';
                    break;
                }
                break;
            case -750853127:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR_2")) {
                    c = '@';
                    break;
                }
                break;
            case -659862417:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_HIGH_TIME")) {
                    c = 'A';
                    break;
                }
                break;
            case -659327994:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_HIGH_TIME")) {
                    c = 'B';
                    break;
                }
                break;
            case -625075712:
                if (str.equals("HOLD_VOLT_WATT_DELAY_TIME")) {
                    c = 'C';
                    break;
                }
                break;
            case -613454474:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE")) {
                    c = 'D';
                    break;
                }
                break;
            case -595561232:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_LOW")) {
                    c = 'E';
                    break;
                }
                break;
            case -594637711:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_LOW")) {
                    c = 'F';
                    break;
                }
                break;
            case -593714190:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_LOW")) {
                    c = 'G';
                    break;
                }
                break;
            case -573009974:
                if (str.equals("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT")) {
                    c = 'H';
                    break;
                }
                break;
            case -550997124:
                if (str.equals("HOLD_EQUALIZATION_TIME")) {
                    c = 'I';
                    break;
                }
                break;
            case -535537138:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_VOLT_2")) {
                    c = 'J';
                    break;
                }
                break;
            case -444930136:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR_1")) {
                    c = 'K';
                    break;
                }
                break;
            case -444930135:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR_2")) {
                    c = 'L';
                    break;
                }
                break;
            case -431364128:
                if (str.equals("HOLD_FORCED_CHG_SOC_LIMIT")) {
                    c = 'M';
                    break;
                }
                break;
            case -413095263:
                if (str.equals("HOLD_FORCED_CHARGE_START_TIME_1")) {
                    c = 'N';
                    break;
                }
                break;
            case -413095262:
                if (str.equals("HOLD_FORCED_CHARGE_START_TIME_2")) {
                    c = 'O';
                    break;
                }
                break;
            case -386262971:
                if (str.equals("HOLD_OVF_DROOP_KOF")) {
                    c = 'P';
                    break;
                }
                break;
            case -384118065:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR")) {
                    c = 'Q';
                    break;
                }
                break;
            case -383766600:
                if (str.equals("HOLD_AC_CHARGE_START_TIME")) {
                    c = 'R';
                    break;
                }
                break;
            case -307531482:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE")) {
                    c = 'S';
                    break;
                }
                break;
            case -276744627:
                if (str.equals("HOLD_TIME")) {
                    c = 'T';
                    break;
                }
                break;
            case -276676643:
                if (str.equals("HOLD_VREF")) {
                    c = 'U';
                    break;
                }
                break;
            case -233655236:
                if (str.equals("HOLD_LEAD_ACID_DISCHARGE_RATE")) {
                    c = 'V';
                    break;
                }
                break;
            case -214155815:
                if (str.equals("HOLD_DELAY_TIME_FOR_OVER_F_DERATE")) {
                    c = 'W';
                    break;
                }
                break;
            case -213248243:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_SOC")) {
                    c = 'X';
                    break;
                }
                break;
            case -165590897:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE_1")) {
                    c = 'Y';
                    break;
                }
                break;
            case -165590896:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE_2")) {
                    c = 'Z';
                    break;
                }
                break;
            case -135057669:
                if (str.equals("HOLD_DISCHG_POWER_PERCENT_CMD")) {
                    c = '[';
                    break;
                }
                break;
            case -107172271:
                if (str.equals("HOLD_AC_CHARGE_END_TIME_1")) {
                    c = '\\';
                    break;
                }
                break;
            case -107172270:
                if (str.equals("HOLD_AC_CHARGE_END_TIME_2")) {
                    c = ']';
                    break;
                }
                break;
            case -26098721:
                if (str.equals("HOLD_REACTIVE_POWER_CMD_TYPE")) {
                    c = '^';
                    break;
                }
                break;
            case 41202161:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_HIGH_TIME")) {
                    c = '_';
                    break;
                }
                break;
            case 41736584:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_HIGH_TIME")) {
                    c = '`';
                    break;
                }
                break;
            case 51556766:
                if (str.equals("HOLD_MAX_Q_PERCENT_FOR_QV")) {
                    c = 'a';
                    break;
                }
                break;
            case 106114747:
                if (str.equals("HOLD_CT_POWER_OFFSET")) {
                    c = 'b';
                    break;
                }
                break;
            case 124739648:
                if (str.equals("HOLD_ON_GRID_EOD_VOLTAGE")) {
                    c = 'c';
                    break;
                }
                break;
            case 127930925:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR")) {
                    c = 'd';
                    break;
                }
                break;
            case 128282390:
                if (str.equals("HOLD_FORCED_CHARGE_END_TIME")) {
                    c = 'e';
                    break;
                }
                break;
            case 159565091:
                if (str.equals("HOLD_MAINTENANCE_COUNT")) {
                    c = 'f';
                    break;
                }
                break;
            case 168754545:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_LOW_TIME")) {
                    c = 'g';
                    break;
                }
                break;
            case 180157537:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_CHG")) {
                    c = 'h';
                    break;
                }
                break;
            case 219635409:
                if (str.equals("HOLD_PF_CMD")) {
                    c = 'i';
                    break;
                }
                break;
            case 221415899:
                if (str.equals("HOLD_EPS_VOLT_SET")) {
                    c = 'j';
                    break;
                }
                break;
            case 229729985:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR_1")) {
                    c = 'k';
                    break;
                }
                break;
            case 229729986:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR_2")) {
                    c = 'l';
                    break;
                }
                break;
            case 265183773:
                if (str.equals("HOLD_START_PV_VOLT")) {
                    c = 'm';
                    break;
                }
                break;
            case 268168589:
                if (str.equals("HOLD_V1H")) {
                    c = 'n';
                    break;
                }
                break;
            case 268168593:
                if (str.equals("HOLD_V1L")) {
                    c = 'o';
                    break;
                }
                break;
            case 268168620:
                if (str.equals("HOLD_V2H")) {
                    c = 'p';
                    break;
                }
                break;
            case 268168624:
                if (str.equals("HOLD_V2L")) {
                    c = 'q';
                    break;
                }
                break;
            case 305030215:
                if (str.equals("OFF_GRID_HOLD_GEN_CHG_START_SOC")) {
                    c = 'r';
                    break;
                }
                break;
            case 313840816:
                if (str.equals("HOLD_GRID_VOLT_CONN_HIGH")) {
                    c = 's';
                    break;
                }
                break;
            case 330144381:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR_1")) {
                    c = 't';
                    break;
                }
                break;
            case 330144382:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR_2")) {
                    c = 'u';
                    break;
                }
                break;
            case 365088499:
                if (str.equals("_12K_HOLD_SMART_LOAD_START_SOC")) {
                    c = 'v';
                    break;
                }
                break;
            case 365268050:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_LOW_TIME")) {
                    c = 'w';
                    break;
                }
                break;
            case 367128639:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE")) {
                    c = 'x';
                    break;
                }
                break;
            case 390956452:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR")) {
                    c = 'y';
                    break;
                }
                break;
            case 391307917:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_TIME")) {
                    c = 'z';
                    break;
                }
                break;
            case 467543035:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE")) {
                    c = '{';
                    break;
                }
                break;
            case 485996371:
                if (str.equals("_12K_HOLD_AC_COUPLE_END_VOLT")) {
                    c = '|';
                    break;
                }
                break;
            case 528065501:
                if (str.equals("HOLD_FORCED_CHG_POWER_CMD")) {
                    c = '}';
                    break;
                }
                break;
            case 561781555:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_LOW_TIME")) {
                    c = '~';
                    break;
                }
                break;
            case 565590738:
                if (str.equals("HOLD_EPS_FREQ_SET")) {
                    c = Ascii.MAX;
                    break;
                }
                break;
            case 567487850:
                if (str.equals("HOLD_AC_CHARGE_START_TIME_1")) {
                    c = 128;
                    break;
                }
                break;
            case 567487851:
                if (str.equals("HOLD_AC_CHARGE_START_TIME_2")) {
                    c = 129;
                    break;
                }
                break;
            case 574683163:
                if (str.equals("HOLD_SET_MASTER_OR_SLAVE")) {
                    c = 130;
                    break;
                }
                break;
            case 614838895:
                if (str.equals("_12K_HOLD_CHARGE_FIRST_VOLT")) {
                    c = 131;
                    break;
                }
                break;
            case 623306801:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE_1")) {
                    c = 132;
                    break;
                }
                break;
            case 623306802:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE_2")) {
                    c = 133;
                    break;
                }
                break;
            case 667902246:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_TIME_1")) {
                    c = 134;
                    break;
                }
                break;
            case 667902247:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_TIME_2")) {
                    c = 135;
                    break;
                }
                break;
            case 677731373:
                if (str.equals("HOLD_ACTIVE_POWER_PERCENT_CMD")) {
                    c = 136;
                    break;
                }
                break;
            case 695812793:
                if (str.equals("HOLD_VOLT_WATT_P2")) {
                    c = 137;
                    break;
                }
                break;
            case 695812978:
                if (str.equals("HOLD_VOLT_WATT_V1")) {
                    c = 138;
                    break;
                }
                break;
            case 695812979:
                if (str.equals("HOLD_VOLT_WATT_V2")) {
                    c = 139;
                    break;
                }
                break;
            case 767099658:
                if (str.equals("HOLD_DISCHG_CUT_OFF_SOC_EOD")) {
                    c = 140;
                    break;
                }
                break;
            case 814992216:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE_1")) {
                    c = 141;
                    break;
                }
                break;
            case 814992217:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE_2")) {
                    c = 142;
                    break;
                }
                break;
            case 866091809:
                if (str.equals("OFF_GRID_HOLD_GEN_CHG_START_VOLT")) {
                    c = 143;
                    break;
                }
                break;
            case 894260725:
                if (str.equals("HOLD_AC_CHARGE_SOC_LIMIT")) {
                    c = 144;
                    break;
                }
                break;
            case 946846866:
                if (str.equals("HOLD_SOC_LOW_LIMIT_EPS_DISCHG")) {
                    c = 145;
                    break;
                }
                break;
            case 1039780741:
                if (str.equals("HOLD_FLOATING_VOLTAGE")) {
                    c = 146;
                    break;
                }
                break;
            case 1118506598:
                if (str.equals("HOLD_GRID_VOLT_CONN_LOW")) {
                    c = 147;
                    break;
                }
                break;
            case 1203310617:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_LOW")) {
                    c = 148;
                    break;
                }
                break;
            case 1204234138:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_LOW")) {
                    c = 149;
                    break;
                }
                break;
            case 1205157659:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_LOW")) {
                    c = 150;
                    break;
                }
                break;
            case 1224119563:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_POWER")) {
                    c = 151;
                    break;
                }
                break;
            case 1226871680:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_SOC_2")) {
                    c = 152;
                    break;
                }
                break;
            case 1457278549:
                if (str.equals("HOLD_RECONNECT_TIME")) {
                    c = 153;
                    break;
                }
                break;
            case 1477300572:
                if (str.equals("_12K_HOLD_AC_COUPLE_START_SOC")) {
                    c = 154;
                    break;
                }
                break;
            case 1504508315:
                if (str.equals("HOLD_VBAT_START_DERATING")) {
                    c = 155;
                    break;
                }
                break;
            case 1527620681:
                if (str.equals("HOLD_MAX_GENERATOR_INPUT_POWER")) {
                    c = 156;
                    break;
                }
                break;
            case 1539695061:
                if (str.equals("_12K_HOLD_AC_COUPLE_END_SOC")) {
                    c = 157;
                    break;
                }
                break;
            case 1600103487:
                if (str.equals("HOLD_GRID_VOLT_MOV_AVG_HIGH")) {
                    c = 158;
                    break;
                }
                break;
            case 1613415905:
                if (str.equals("HOLD_DELAY_TIME_FOR_QV_CURVE")) {
                    c = 159;
                    break;
                }
                break;
            case 1809765729:
                if (str.equals("HOLD_P1")) {
                    c = Typography.nbsp;
                    break;
                }
                break;
            case 1809765730:
                if (str.equals("HOLD_P2")) {
                    c = 161;
                    break;
                }
                break;
            case 1809765731:
                if (str.equals("HOLD_P3")) {
                    c = Typography.cent;
                    break;
                }
                break;
            case 1809765762:
                if (str.equals("HOLD_Q3")) {
                    c = Typography.pound;
                    break;
                }
                break;
            case 1809765763:
                if (str.equals("HOLD_Q4")) {
                    c = 164;
                    break;
                }
                break;
            case 1831339770:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_LOW_TIME")) {
                    c = 165;
                    break;
                }
                break;
            case 1838153520:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_HIGH_TIME")) {
                    c = 166;
                    break;
                }
                break;
            case 1838687943:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_HIGH_TIME")) {
                    c = Typography.section;
                    break;
                }
                break;
            case 1853690354:
                if (str.equals("HOLD_AC_CHARGE_POWER_CMD")) {
                    c = 168;
                    break;
                }
                break;
            case 1890826442:
                if (str.equals("HOLD_AC_CHARGE_START_BATTERY_SOC")) {
                    c = Typography.copyright;
                    break;
                }
                break;
            case 1910924292:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_37")) {
                    c = 170;
                    break;
                }
                break;
            case 1910924293:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_38")) {
                    c = 171;
                    break;
                }
                break;
            case 1910924294:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_39")) {
                    c = 172;
                    break;
                }
                break;
            case 1910924316:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_40")) {
                    c = 173;
                    break;
                }
                break;
            case 1910924317:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_41")) {
                    c = Typography.registered;
                    break;
                }
                break;
            case 1910924318:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_42")) {
                    c = 175;
                    break;
                }
                break;
            case 1910924319:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_43")) {
                    c = Typography.degree;
                    break;
                }
                break;
            case 1910924320:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_44")) {
                    c = Typography.plusMinus;
                    break;
                }
                break;
            case 1925850636:
                if (str.equals("HOLD_LEAD_ACID_CHARGE_RATE")) {
                    c = 178;
                    break;
                }
                break;
            case 1979328795:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_VOLT")) {
                    c = 179;
                    break;
                }
                break;
            case 1985523803:
                if (str.equals("HOLD_CHARGE_POWER_PERCENT_CMD")) {
                    c = 180;
                    break;
                }
                break;
            case 1989908579:
                if (str.equals("HOLD_PEAK_SHAVING_START_TIME_1")) {
                    c = 181;
                    break;
                }
                break;
            case 1989908580:
                if (str.equals("HOLD_PEAK_SHAVING_START_TIME_2")) {
                    c = Typography.paragraph;
                    break;
                }
                break;
            case 2016686976:
                if (str.equals("OFF_GRID_HOLD_GEN_CHG_END_SOC")) {
                    c = Typography.middleDot;
                    break;
                }
                break;
            case 2020464802:
                if (str.equals("HOLD_CONNECT_TIME")) {
                    c = 184;
                    break;
                }
                break;
            case 2027853275:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_LOW_TIME")) {
                    c = 185;
                    break;
                }
                break;
            case 2036981088:
                if (str.equals("HOLD_REACTIVE_POWER_PERCENT_CMD")) {
                    c = 186;
                    break;
                }
                break;
            case 2046998614:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR_1")) {
                    c = 187;
                    break;
                }
                break;
            case 2046998615:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR_2")) {
                    c = 188;
                    break;
                }
                break;
            case 2069975635:
                if (str.equals("HOLD_POWER_SOFT_START_SLOPE")) {
                    c = Typography.half;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return Integer.valueOf(Opcodes.INSTANCEOF);
            case 1:
            case 'y':
            case 'z':
                return 84;
            case 2:
                return 27;
            case 3:
            case 4:
            case 'S':
                return 69;
            case 5:
                return 31;
            case 6:
                return 113;
            case 7:
                return 108;
            case '\b':
                return 103;
            case '\t':
                return 99;
            case '\n':
                return 217;
            case 11:
            case '9':
            case Opcodes.NEW /* 187 */:
                return 86;
            case '\f':
            case ':':
            case 188:
                return 88;
            case '\r':
                return Integer.valueOf(Opcodes.MONITOREXIT);
            case 14:
                return 214;
            case 15:
                return 83;
            case 16:
                return 216;
            case 17:
                return 106;
            case 18:
            case Opcodes.IRETURN /* 172 */:
            case Opcodes.LRETURN /* 173 */:
                return 210;
            case 19:
            case Opcodes.ARETURN /* 176 */:
            case Opcodes.RETURN /* 177 */:
                return 212;
            case 20:
                return 116;
            case 21:
                return 115;
            case 22:
                return Integer.valueOf(Opcodes.FCMPL);
            case 23:
                return 134;
            case 24:
                return Integer.valueOf(Opcodes.IFNULL);
            case 25:
            case 't':
            case 134:
                return 87;
            case 26:
            case 'u':
            case 135:
                return 89;
            case 27:
                return 15;
            case 28:
            case '3':
            case 'Y':
                return 79;
            case 29:
            case '4':
            case 'Z':
                return 81;
            case 30:
                return 107;
            case 31:
                return 213;
            case ' ':
            case '!':
            case '{':
                return 85;
            case '\"':
            case '#':
            case 'D':
                return 76;
            case '$':
                return 202;
            case '%':
                return 20;
            case '&':
            case 'd':
            case 'e':
                return 77;
            case '\'':
                return 203;
            case '(':
                return 222;
            case ')':
                return Integer.valueOf(Opcodes.IFLE);
            case '*':
                return Integer.valueOf(Opcodes.IF_ICMPEQ);
            case '+':
                return 186;
            case ',':
                return 11;
            case '-':
                return 43;
            case '.':
                return Integer.valueOf(Opcodes.FCMPG);
            case '/':
                return 47;
            case '0':
                return 51;
            case '1':
                return 205;
            case '2':
                return 30;
            case '5':
                return 34;
            case '6':
                return 38;
            case '7':
            case '?':
            case 'N':
                return 78;
            case '8':
            case '@':
            case 'O':
                return 80;
            case ';':
                return 121;
            case '<':
                return 82;
            case '=':
                return 28;
            case '>':
                return Integer.valueOf(Opcodes.ARETURN);
            case 'A':
                return 45;
            case 'B':
                return 32;
            case 'C':
                return Integer.valueOf(Opcodes.INVOKESPECIAL);
            case 'E':
                return 29;
            case 'F':
                return 33;
            case 'G':
                return 37;
            case 'H':
                return 100;
            case 'I':
                return Integer.valueOf(Opcodes.DCMPL);
            case 'J':
                return 219;
            case 'K':
            case '\\':
            case 141:
                return 71;
            case 'L':
            case ']':
            case Opcodes.D2I /* 142 */:
                return 73;
            case 'M':
                return 75;
            case 'P':
                return Integer.valueOf(Opcodes.L2I);
            case 'Q':
            case 'R':
            case 'x':
                return 68;
            case 'T':
                return 12;
            case 'U':
                return Integer.valueOf(Opcodes.INVOKEINTERFACE);
            case 'V':
                return 102;
            case 'W':
                return 97;
            case 'X':
                return 207;
            case '[':
                return 65;
            case '^':
                return 59;
            case '_':
                return 53;
            case '`':
                return 40;
            case 'a':
                return 54;
            case 'b':
                return 119;
            case 'c':
                return Integer.valueOf(Opcodes.RET);
            case 'f':
                return 122;
            case 'g':
                return 52;
            case 'h':
                return 109;
            case 'i':
                return 62;
            case 'j':
                return 90;
            case 'k':
            case 128:
            case Opcodes.IINC /* 132 */:
                return 70;
            case 'l':
            case Opcodes.LOR /* 129 */:
            case 133:
                return 72;
            case 'm':
                return 22;
            case 'n':
                return 57;
            case 'o':
                return 55;
            case 'p':
                return 58;
            case 'q':
                return 56;
            case 'r':
                return 196;
            case 's':
                return 26;
            case 'v':
                return 215;
            case 'w':
                return 48;
            case '|':
                return 223;
            case '}':
                return 74;
            case '~':
                return 44;
            case 127:
                return 91;
            case Opcodes.IXOR /* 130 */:
                return 112;
            case 131:
                return 201;
            case Opcodes.L2I /* 136 */:
                return 60;
            case 137:
                return Integer.valueOf(Opcodes.INVOKESTATIC);
            case 138:
                return Integer.valueOf(Opcodes.PUTFIELD);
            case Opcodes.F2I /* 139 */:
                return Integer.valueOf(Opcodes.INVOKEVIRTUAL);
            case 140:
                return 105;
            case Opcodes.D2L /* 143 */:
                return Integer.valueOf(Opcodes.MONITORENTER);
            case 144:
                return 67;
            case Opcodes.I2B /* 145 */:
                return 125;
            case Opcodes.I2C /* 146 */:
                return 144;
            case Opcodes.I2S /* 147 */:
                return 25;
            case Opcodes.LCMP /* 148 */:
                return 42;
            case Opcodes.FCMPL /* 149 */:
                return 46;
            case Opcodes.FCMPG /* 150 */:
                return 50;
            case Opcodes.DCMPL /* 151 */:
                return Integer.valueOf(BuildConfig.VERSION_CODE);
            case Opcodes.DCMPG /* 152 */:
                return 218;
            case Opcodes.IFEQ /* 153 */:
                return 24;
            case Opcodes.IFNE /* 154 */:
                return 220;
            case Opcodes.IFLT /* 155 */:
                return 118;
            case Opcodes.IFGE /* 156 */:
                return Integer.valueOf(Opcodes.RETURN);
            case Opcodes.IFGT /* 157 */:
                return 221;
            case Opcodes.IFLE /* 158 */:
                return 41;
            case Opcodes.IF_ICMPEQ /* 159 */:
                return 96;
            case Opcodes.IF_ICMPNE /* 160 */:
                return 189;
            case 161:
                return Integer.valueOf(Opcodes.ARRAYLENGTH);
            case 162:
                return Integer.valueOf(Opcodes.ATHROW);
            case 163:
                return Integer.valueOf(Opcodes.NEW);
            case 164:
                return 188;
            case 165:
                return 39;
            case Opcodes.IF_ACMPNE /* 166 */:
                return 49;
            case Opcodes.GOTO /* 167 */:
                return 36;
            case 168:
                return 66;
            case Opcodes.RET /* 169 */:
                return Integer.valueOf(Opcodes.IF_ICMPNE);
            case Opcodes.TABLESWITCH /* 170 */:
            case Opcodes.LOOKUPSWITCH /* 171 */:
            case Opcodes.PUTFIELD /* 181 */:
                return 209;
            case Opcodes.FRETURN /* 174 */:
            case Opcodes.DRETURN /* 175 */:
            case Opcodes.INVOKEVIRTUAL /* 182 */:
                return 211;
            case Opcodes.GETSTATIC /* 178 */:
                return 101;
            case Opcodes.PUTSTATIC /* 179 */:
                return 208;
            case Opcodes.GETFIELD /* 180 */:
                return 64;
            case Opcodes.INVOKESPECIAL /* 183 */:
                return 197;
            case Opcodes.INVOKESTATIC /* 184 */:
                return 23;
            case Opcodes.INVOKEINTERFACE /* 185 */:
                return 35;
            case 186:
                return 61;
            case 189:
                return 63;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getValueByParam(String str, String str2) {
        Integer valueOf;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2146680827:
                if (str.equals("HOLD_UVF_DROOP_KUF")) {
                    c = 0;
                    break;
                }
                break;
            case -2106433585:
                if (str.equals("HOLD_GRID_FREQ_CONN_LOW")) {
                    c = 1;
                    break;
                }
                break;
            case -2051169280:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_CHG")) {
                    c = 2;
                    break;
                }
                break;
            case -2012582261:
                if (str.equals("HOLD_FEED_IN_GRID_POWER_PERCENT")) {
                    c = 3;
                    break;
                }
                break;
            case -1922746271:
                if (str.equals("HOLD_LEAD_ACID_CHARGE_VOLT_REF")) {
                    c = 4;
                    break;
                }
                break;
            case -1917271147:
                if (str.equals("_12K_HOLD_START_PV_POWER")) {
                    c = 5;
                    break;
                }
                break;
            case -1907123448:
                if (str.equals("OFF_GRID_HOLD_GEN_CHG_END_VOLT")) {
                    c = 6;
                    break;
                }
                break;
            case -1905186084:
                if (str.equals("_12K_HOLD_SMART_LOAD_END_VOLT")) {
                    c = 7;
                    break;
                }
                break;
            case -1854323434:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_DISCHG")) {
                    c = '\b';
                    break;
                }
                break;
            case -1774171999:
                if (str.equals("_12K_HOLD_OVF_DERATE_START_POINT")) {
                    c = '\t';
                    break;
                }
                break;
            case -1738912721:
                if (str.equals("HOLD_EQUALIZATION_VOLTAGE")) {
                    c = '\n';
                    break;
                }
                break;
            case -1724289777:
                if (str.equals("HOLD_UVF_DERATE_START_POINT")) {
                    c = 11;
                    break;
                }
                break;
            case -1605958379:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_DISCHG")) {
                    c = '\f';
                    break;
                }
                break;
            case -1567068683:
                if (str.equals("_12K_HOLD_SMART_LOAD_START_VOLT")) {
                    c = '\r';
                    break;
                }
                break;
            case -1536953061:
                if (str.equals("_12K_HOLD_STOP_DISCHG_VOLT")) {
                    c = 14;
                    break;
                }
                break;
            case -1448232788:
                if (str.equals("_12K_HOLD_AC_COUPLE_START_VOLT")) {
                    c = 15;
                    break;
                }
                break;
            case -1433471711:
                if (str.equals("HOLD_AC_CHARGE_START_BATTERY_VOLTAGE")) {
                    c = 16;
                    break;
                }
                break;
            case -1422719063:
                if (str.equals("HOLD_BATTERY_WARNING_VOLTAGE")) {
                    c = 17;
                    break;
                }
                break;
            case -1413838822:
                if (str.equals("HOLD_AC_CHARGE_END_BATTERY_VOLTAGE")) {
                    c = 18;
                    break;
                }
                break;
            case -1352201891:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_HIGH")) {
                    c = 19;
                    break;
                }
                break;
            case -1323572740:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_HIGH")) {
                    c = 20;
                    break;
                }
                break;
            case -1294943589:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_HIGH")) {
                    c = 21;
                    break;
                }
                break;
            case -1282654362:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_HIGH")) {
                    c = 22;
                    break;
                }
                break;
            case -1254025211:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_HIGH")) {
                    c = 23;
                    break;
                }
                break;
            case -1225396060:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_HIGH")) {
                    c = 24;
                    break;
                }
                break;
            case -917268805:
                if (str.equals("HOLD_FORCED_DISCHG_POWER_CMD")) {
                    c = 25;
                    break;
                }
                break;
            case -915649916:
                if (str.equals("HOLD_BATTERY_LOW_TO_UTILITY_VOLTAGE")) {
                    c = 26;
                    break;
                }
                break;
            case -875057049:
                if (str.equals("HOLD_GRID_FREQ_CONN_HIGH")) {
                    c = 27;
                    break;
                }
                break;
            case -823881906:
                if (str.equals("HOLD_MAX_AC_INPUT_POWER")) {
                    c = 28;
                    break;
                }
                break;
            case -595561232:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_LOW")) {
                    c = 29;
                    break;
                }
                break;
            case -594637711:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_LOW")) {
                    c = 30;
                    break;
                }
                break;
            case -593714190:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_LOW")) {
                    c = 31;
                    break;
                }
                break;
            case -573009974:
                if (str.equals("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT")) {
                    c = ' ';
                    break;
                }
                break;
            case -537681431:
                if (str.equals("HOLD_NOMINAL_BATTERY_VOLTAGE")) {
                    c = '!';
                    break;
                }
                break;
            case -535537138:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_VOLT_2")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case -386262971:
                if (str.equals("HOLD_OVF_DROOP_KOF")) {
                    c = '#';
                    break;
                }
                break;
            case -123360169:
                if (str.equals("INPUT_BATTERY_VOLTAGE")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case 106114747:
                if (str.equals("HOLD_CT_POWER_OFFSET")) {
                    c = '%';
                    break;
                }
                break;
            case 124739648:
                if (str.equals("HOLD_ON_GRID_EOD_VOLTAGE")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 180157537:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_CHG")) {
                    c = '\'';
                    break;
                }
                break;
            case 265183773:
                if (str.equals("HOLD_START_PV_VOLT")) {
                    c = '(';
                    break;
                }
                break;
            case 268168589:
                if (str.equals("HOLD_V1H")) {
                    c = ')';
                    break;
                }
                break;
            case 268168593:
                if (str.equals("HOLD_V1L")) {
                    c = '*';
                    break;
                }
                break;
            case 268168620:
                if (str.equals("HOLD_V2H")) {
                    c = '+';
                    break;
                }
                break;
            case 268168624:
                if (str.equals("HOLD_V2L")) {
                    c = ',';
                    break;
                }
                break;
            case 313840816:
                if (str.equals("HOLD_GRID_VOLT_CONN_HIGH")) {
                    c = '-';
                    break;
                }
                break;
            case 485996371:
                if (str.equals("_12K_HOLD_AC_COUPLE_END_VOLT")) {
                    c = '.';
                    break;
                }
                break;
            case 528065501:
                if (str.equals("HOLD_FORCED_CHG_POWER_CMD")) {
                    c = '/';
                    break;
                }
                break;
            case 614838895:
                if (str.equals("_12K_HOLD_CHARGE_FIRST_VOLT")) {
                    c = '0';
                    break;
                }
                break;
            case 719765641:
                if (str.equals("HOLD_BATTERY_WARNING_RECOVERY_VOLTAGE")) {
                    c = '1';
                    break;
                }
                break;
            case 866091809:
                if (str.equals("OFF_GRID_HOLD_GEN_CHG_START_VOLT")) {
                    c = '2';
                    break;
                }
                break;
            case 1039780741:
                if (str.equals("HOLD_FLOATING_VOLTAGE")) {
                    c = '3';
                    break;
                }
                break;
            case 1118506598:
                if (str.equals("HOLD_GRID_VOLT_CONN_LOW")) {
                    c = '4';
                    break;
                }
                break;
            case 1203310617:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_LOW")) {
                    c = '5';
                    break;
                }
                break;
            case 1204234138:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_LOW")) {
                    c = '6';
                    break;
                }
                break;
            case 1205157659:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_LOW")) {
                    c = '7';
                    break;
                }
                break;
            case 1224119563:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_POWER")) {
                    c = '8';
                    break;
                }
                break;
            case 1504508315:
                if (str.equals("HOLD_VBAT_START_DERATING")) {
                    c = '9';
                    break;
                }
                break;
            case 1527620681:
                if (str.equals("HOLD_MAX_GENERATOR_INPUT_POWER")) {
                    c = ':';
                    break;
                }
                break;
            case 1600103487:
                if (str.equals("HOLD_GRID_VOLT_MOV_AVG_HIGH")) {
                    c = ';';
                    break;
                }
                break;
            case 1853690354:
                if (str.equals("HOLD_AC_CHARGE_POWER_CMD")) {
                    c = Typography.less;
                    break;
                }
                break;
            case 1979328795:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_VOLT")) {
                    c = '=';
                    break;
                }
                break;
            case 2069975635:
                if (str.equals("HOLD_POWER_SOFT_START_SLOPE")) {
                    c = Typography.greater;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case '#':
                valueOf = Integer.valueOf((int) Math.round(1.0d / toDouble(str2).doubleValue()));
                break;
            case 1:
            case '\t':
            case 11:
            case 19:
            case 20:
            case 21:
            case 27:
            case '5':
            case '6':
            case '7':
                Double d = toDouble(str2);
                if (d != null) {
                    valueOf = Integer.valueOf((int) Double.valueOf(d.doubleValue() * 100.0d).doubleValue());
                    break;
                }
                valueOf = null;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\n':
            case '\f':
            case '\r':
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 28:
            case 29:
            case 30:
            case 31:
            case ' ':
            case '!':
            case '\"':
            case '$':
            case '%':
            case '&':
            case '\'':
            case '(':
            case ')':
            case '*':
            case '+':
            case ',':
            case '-':
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '8':
            case '9':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
                Double d2 = toDouble(str2);
                if (d2 != null) {
                    valueOf = Integer.valueOf((int) Double.valueOf(d2.doubleValue() * 10.0d).doubleValue());
                    break;
                }
                valueOf = null;
                break;
            default:
                valueOf = toInt(str2);
                break;
        }
        return Integer.valueOf(checkNegativeValue(str, valueOf.intValue()));
    }

    private static int checkNegativeValue(String str, int i) {
        str.hashCode();
        return (str.equals("HOLD_CT_POWER_OFFSET") && i < 0) ? 65535 & i : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getRegisterByFunction(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1658624300:
                if (str.equals("FUNC_PV_GRID_OFF_EN")) {
                    c = 0;
                    break;
                }
                break;
            case -1576051434:
                if (str.equals("FUNC_TAKE_LOAD_TOGETHER")) {
                    c = 1;
                    break;
                }
                break;
            case -1572766213:
                if (str.equals("FUNC_ON_GRID_ALWAYS_ON")) {
                    c = 2;
                    break;
                }
                break;
            case -1289503838:
                if (str.equals("FUNC_GEN_PEAK_SHAVING")) {
                    c = 3;
                    break;
                }
                break;
            case -1085100649:
                if (str.equals("FUNC_BAT_CHARGE_CONTROL")) {
                    c = 4;
                    break;
                }
                break;
            case -661950026:
                if (str.equals("FUNC_AC_CHARGE")) {
                    c = 5;
                    break;
                }
                break;
            case -649921843:
                if (str.equals("FUNC_BUZZER_EN")) {
                    c = 6;
                    break;
                }
                break;
            case -354288139:
                if (str.equals("FUNC_CT_DIRECTION_REVERSED")) {
                    c = 7;
                    break;
                }
                break;
            case -254470247:
                if (str.equals("FUNC_DRMS_EN")) {
                    c = '\b';
                    break;
                }
                break;
            case -247500863:
                if (str.equals("FUNC_SET_TO_STANDBY")) {
                    c = '\t';
                    break;
                }
                break;
            case -234409492:
                if (str.equals("FUNC_RUN_WITHOUT_GRID")) {
                    c = '\n';
                    break;
                }
                break;
            case -62297874:
                if (str.equals("FUNC_FEED_IN_GRID_EN")) {
                    c = 11;
                    break;
                }
                break;
            case 59516663:
                if (str.equals("FUNC_TRIP_TIME_UNIT")) {
                    c = '\f';
                    break;
                }
                break;
            case 84912813:
                if (str.equals("FUNC_PV_SELL_TO_GRID_EN")) {
                    c = '\r';
                    break;
                }
                break;
            case 192023757:
                if (str.equals("FUNC_WATT_VOLT_EN")) {
                    c = 14;
                    break;
                }
                break;
            case 958635077:
                if (str.equals("FUNC_PV_ARC_FAULT_CLEAR")) {
                    c = 15;
                    break;
                }
                break;
            case 1123832438:
                if (str.equals("FUNC_GRID_PEAK_SHAVING")) {
                    c = 16;
                    break;
                }
                break;
            case 1216588652:
                if (str.equals("FUNC_MICRO_GRID_EN")) {
                    c = 17;
                    break;
                }
                break;
            case 1265688859:
                if (str.equals("FUNC_EPS_EN")) {
                    c = 18;
                    break;
                }
                break;
            case 1392376480:
                if (str.equals("FUNC_GREEN_EN")) {
                    c = 19;
                    break;
                }
                break;
            case 1446005111:
                if (str.equals("FUNC_RUN_WITHOUT_GRID_12K")) {
                    c = 20;
                    break;
                }
                break;
            case 1535225926:
                if (str.equals("FUNC_CHARGE_LAST")) {
                    c = 21;
                    break;
                }
                break;
            case 1586479700:
                if (str.equals("FUNC_PV_ARC")) {
                    c = 22;
                    break;
                }
                break;
            case 1646023067:
                if (str.equals("FUNC_BAT_DISCHARGE_CONTROL")) {
                    c = 23;
                    break;
                }
                break;
            case 1782925386:
                if (str.equals("FUNC_BAT_SHARED")) {
                    c = 24;
                    break;
                }
                break;
            case 1991591793:
                if (str.equals("FUNC_FORCED_CHG_EN")) {
                    c = 25;
                    break;
                }
                break;
            case 2029383081:
                if (str.equals("FUNC_FORCED_DISCHG_EN")) {
                    c = 26;
                    break;
                }
                break;
            case 2100429567:
                if (str.equals("FUNC_ACTIVE_POWER_LIMIT_MODE")) {
                    c = 27;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 6:
            case '\n':
            case 17:
            case 19:
            case 21:
            case 24:
                return 110;
            case 2:
            case 3:
            case 4:
            case 7:
            case '\f':
            case '\r':
            case 14:
            case 15:
            case 16:
            case 22:
            case 23:
            case 27:
                return Integer.valueOf(Opcodes.PUTSTATIC);
            case 5:
            case '\b':
            case '\t':
            case 11:
            case 18:
            case 25:
            case 26:
                return 21;
            case 20:
                return 226;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00f1, code lost:
    
        if (r27.equals("FUNC_PV_ARC_FAULT_CLEAR") == false) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Integer getBitByFunction(java.lang.String r27) {
        /*
            Method dump skipped, instructions count: 670
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.local.fragment.Local12KSetFragment.getBitByFunction(java.lang.String):java.lang.Integer");
    }

    private static Integer toInt(String str) {
        try {
            return Integer.valueOf(str.toLowerCase().contains("0x") ? Integer.parseInt(str.substring(2), 16) : Integer.parseInt(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Double toDouble(String str) {
        try {
            return Double.valueOf(str.toLowerCase().contains("0x") ? Integer.parseInt(str.substring(2), 16) : Double.parseDouble(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] getHoldParamsByStartRegister(int i) {
        switch (i) {
            case 0:
                return new String[]{"HOLD_MODEL"};
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 17:
            case 18:
            case 19:
            case 21:
            case 92:
            case 93:
            case 94:
            case 95:
            case 98:
            case 104:
            case 110:
            case 111:
            case 114:
            case 117:
            case 120:
            case 123:
            case 124:
            case 126:
            case 127:
            case 128:
            case Opcodes.LOR /* 129 */:
            case Opcodes.IXOR /* 130 */:
            case 131:
            case Opcodes.IINC /* 132 */:
            case 133:
            case 135:
            case 137:
            case 138:
            case Opcodes.F2I /* 139 */:
            case 140:
            case 141:
            case Opcodes.D2I /* 142 */:
            case Opcodes.D2L /* 143 */:
            case Opcodes.I2B /* 145 */:
            case Opcodes.I2C /* 146 */:
            case Opcodes.I2S /* 147 */:
            case Opcodes.LCMP /* 148 */:
            case Opcodes.DCMPG /* 152 */:
            case Opcodes.IFEQ /* 153 */:
            case Opcodes.IFNE /* 154 */:
            case Opcodes.IFLT /* 155 */:
            case Opcodes.IFGE /* 156 */:
            case Opcodes.IFGT /* 157 */:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case Opcodes.IF_ACMPNE /* 166 */:
            case Opcodes.GOTO /* 167 */:
            case 168:
            case Opcodes.TABLESWITCH /* 170 */:
            case Opcodes.LOOKUPSWITCH /* 171 */:
            case Opcodes.IRETURN /* 172 */:
            case Opcodes.LRETURN /* 173 */:
            case Opcodes.FRETURN /* 174 */:
            case Opcodes.DRETURN /* 175 */:
            case Opcodes.GETSTATIC /* 178 */:
            case Opcodes.PUTSTATIC /* 179 */:
            case Opcodes.GETFIELD /* 180 */:
            case Opcodes.CHECKCAST /* 192 */:
            case Opcodes.IFNONNULL /* 199 */:
            case ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION /* 200 */:
            case 204:
            default:
                return null;
            case 2:
                return new String[]{"HOLD_SERIAL_NUM"};
            case 7:
                return new String[]{"HOLD_FW_CODE"};
            case 12:
                return new String[]{"HOLD_TIME"};
            case 15:
                return new String[]{"HOLD_COM_ADDR"};
            case 16:
                return new String[]{"HOLD_LANGUAGE"};
            case 20:
                return new String[]{"HOLD_PV_INPUT_MODE"};
            case 22:
                return new String[]{"HOLD_START_PV_VOLT"};
            case 23:
                return new String[]{"HOLD_CONNECT_TIME"};
            case 24:
                return new String[]{"HOLD_RECONNECT_TIME"};
            case 25:
                return new String[]{"HOLD_GRID_VOLT_CONN_LOW"};
            case 26:
                return new String[]{"HOLD_GRID_VOLT_CONN_HIGH"};
            case 27:
                return new String[]{"HOLD_GRID_FREQ_CONN_LOW"};
            case 28:
                return new String[]{"HOLD_GRID_FREQ_CONN_HIGH"};
            case 29:
                return new String[]{"HOLD_GRID_VOLT_LIMIT1_LOW"};
            case 30:
                return new String[]{"HOLD_GRID_VOLT_LIMIT1_HIGH"};
            case 31:
                return new String[]{"HOLD_GRID_VOLT_LIMIT1_LOW_TIME"};
            case 32:
                return new String[]{"HOLD_GRID_VOLT_LIMIT1_HIGH_TIME"};
            case 33:
                return new String[]{"HOLD_GRID_VOLT_LIMIT2_LOW"};
            case 34:
                return new String[]{"HOLD_GRID_VOLT_LIMIT2_HIGH"};
            case 35:
                return new String[]{"HOLD_GRID_VOLT_LIMIT2_LOW_TIME"};
            case 36:
                return new String[]{"HOLD_GRID_VOLT_LIMIT2_HIGH_TIME"};
            case 37:
                return new String[]{"HOLD_GRID_VOLT_LIMIT3_LOW"};
            case 38:
                return new String[]{"HOLD_GRID_VOLT_LIMIT3_HIGH"};
            case 39:
                return new String[]{"HOLD_GRID_VOLT_LIMIT3_LOW_TIME"};
            case 40:
                return new String[]{"HOLD_GRID_VOLT_LIMIT3_HIGH_TIME"};
            case 41:
                return new String[]{"HOLD_GRID_VOLT_MOV_AVG_HIGH"};
            case 42:
                return new String[]{"HOLD_GRID_FREQ_LIMIT1_LOW"};
            case 43:
                return new String[]{"HOLD_GRID_FREQ_LIMIT1_HIGH"};
            case 44:
                return new String[]{"HOLD_GRID_FREQ_LIMIT1_LOW_TIME"};
            case 45:
                return new String[]{"HOLD_GRID_FREQ_LIMIT1_HIGH_TIME"};
            case 46:
                return new String[]{"HOLD_GRID_FREQ_LIMIT2_LOW"};
            case 47:
                return new String[]{"HOLD_GRID_FREQ_LIMIT2_HIGH"};
            case 48:
                return new String[]{"HOLD_GRID_FREQ_LIMIT2_LOW_TIME"};
            case 49:
                return new String[]{"HOLD_GRID_FREQ_LIMIT2_HIGH_TIME"};
            case 50:
                return new String[]{"HOLD_GRID_FREQ_LIMIT3_LOW"};
            case 51:
                return new String[]{"HOLD_GRID_FREQ_LIMIT3_HIGH"};
            case 52:
                return new String[]{"HOLD_GRID_FREQ_LIMIT3_LOW_TIME"};
            case 53:
                return new String[]{"HOLD_GRID_FREQ_LIMIT3_HIGH_TIME"};
            case 54:
                return new String[]{"HOLD_MAX_Q_PERCENT_FOR_QV"};
            case 55:
                return new String[]{"HOLD_V1L"};
            case 56:
                return new String[]{"HOLD_V2L"};
            case 57:
                return new String[]{"HOLD_V1H"};
            case 58:
                return new String[]{"HOLD_V2H"};
            case 59:
                return new String[]{"HOLD_REACTIVE_POWER_CMD_TYPE"};
            case 60:
                return new String[]{"HOLD_ACTIVE_POWER_PERCENT_CMD"};
            case 61:
                return new String[]{"HOLD_REACTIVE_POWER_PERCENT_CMD"};
            case 62:
                return new String[]{"HOLD_PF_CMD"};
            case 63:
                return new String[]{"HOLD_POWER_SOFT_START_SLOPE"};
            case 64:
                return new String[]{"HOLD_CHARGE_POWER_PERCENT_CMD"};
            case 65:
                return new String[]{"HOLD_DISCHG_POWER_PERCENT_CMD"};
            case 66:
                return new String[]{"HOLD_AC_CHARGE_POWER_CMD"};
            case 67:
                return new String[]{"HOLD_AC_CHARGE_SOC_LIMIT"};
            case 68:
                return new String[]{"HOLD_AC_CHARGE_START_HOUR", "HOLD_AC_CHARGE_START_MINUTE"};
            case 69:
                return new String[]{"HOLD_AC_CHARGE_END_HOUR", "HOLD_AC_CHARGE_END_MINUTE"};
            case 70:
                return new String[]{"HOLD_AC_CHARGE_START_HOUR_1", "HOLD_AC_CHARGE_START_MINUTE_1"};
            case 71:
                return new String[]{"HOLD_AC_CHARGE_END_HOUR_1", "HOLD_AC_CHARGE_END_MINUTE_1"};
            case 72:
                return new String[]{"HOLD_AC_CHARGE_START_HOUR_2", "HOLD_AC_CHARGE_START_MINUTE_2"};
            case 73:
                return new String[]{"HOLD_AC_CHARGE_END_HOUR_2", "HOLD_AC_CHARGE_END_MINUTE_2"};
            case 74:
                return new String[]{"HOLD_FORCED_CHG_POWER_CMD"};
            case 75:
                return new String[]{"HOLD_FORCED_CHG_SOC_LIMIT"};
            case 76:
                return new String[]{"HOLD_FORCED_CHARGE_START_HOUR", "HOLD_FORCED_CHARGE_START_MINUTE"};
            case 77:
                return new String[]{"HOLD_FORCED_CHARGE_END_HOUR", "HOLD_FORCED_CHARGE_END_MINUTE"};
            case 78:
                return new String[]{"HOLD_FORCED_CHARGE_START_HOUR_1", "HOLD_FORCED_CHARGE_START_MINUTE_1"};
            case 79:
                return new String[]{"HOLD_FORCED_CHARGE_END_HOUR_1", "HOLD_FORCED_CHARGE_END_MINUTE_1"};
            case 80:
                return new String[]{"HOLD_FORCED_CHARGE_START_HOUR_2", "HOLD_FORCED_CHARGE_START_MINUTE_2"};
            case 81:
                return new String[]{"HOLD_FORCED_CHARGE_END_HOUR_2", "HOLD_FORCED_CHARGE_END_MINUTE_2"};
            case 82:
                return new String[]{"HOLD_FORCED_DISCHG_POWER_CMD"};
            case 83:
                return new String[]{"HOLD_FORCED_DISCHG_SOC_LIMIT"};
            case 84:
                return new String[]{"HOLD_FORCED_DISCHARGE_START_HOUR", "HOLD_FORCED_DISCHARGE_START_MINUTE"};
            case 85:
                return new String[]{"HOLD_FORCED_DISCHARGE_END_HOUR", "HOLD_FORCED_DISCHARGE_END_MINUTE"};
            case 86:
                return new String[]{"HOLD_FORCED_DISCHARGE_START_HOUR_1", "HOLD_FORCED_DISCHARGE_START_MINUTE_1"};
            case 87:
                return new String[]{"HOLD_FORCED_DISCHARGE_END_HOUR_1", "HOLD_FORCED_DISCHARGE_END_MINUTE_1"};
            case 88:
                return new String[]{"HOLD_FORCED_DISCHARGE_START_HOUR_2", "HOLD_FORCED_DISCHARGE_START_MINUTE_2"};
            case 89:
                return new String[]{"HOLD_FORCED_DISCHARGE_END_HOUR_2", "HOLD_FORCED_DISCHARGE_END_MINUTE_2"};
            case 90:
                return new String[]{"HOLD_EPS_VOLT_SET"};
            case 91:
                return new String[]{"HOLD_EPS_FREQ_SET"};
            case 96:
                return new String[]{"HOLD_DELAY_TIME_FOR_QV_CURVE"};
            case 97:
                return new String[]{"HOLD_DELAY_TIME_FOR_OVER_F_DERATE"};
            case 99:
                return new String[]{"HOLD_LEAD_ACID_CHARGE_VOLT_REF"};
            case 100:
                return new String[]{"HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT"};
            case 101:
                return new String[]{"HOLD_LEAD_ACID_CHARGE_RATE"};
            case 102:
                return new String[]{"HOLD_LEAD_ACID_DISCHARGE_RATE"};
            case 103:
                return new String[]{"HOLD_FEED_IN_GRID_POWER_PERCENT"};
            case 105:
                return new String[]{"HOLD_DISCHG_CUT_OFF_SOC_EOD"};
            case 106:
                return new String[]{"HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_DISCHG"};
            case 107:
                return new String[]{"HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_DISCHG"};
            case 108:
                return new String[]{"HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_CHG"};
            case 109:
                return new String[]{"HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_CHG"};
            case 112:
                return new String[]{"HOLD_SET_MASTER_OR_SLAVE"};
            case 113:
                return new String[]{"HOLD_SET_COMPOSED_PHASE"};
            case 115:
                return new String[]{"_12K_HOLD_OVF_DERATE_START_POINT"};
            case 116:
                return new String[]{"HOLD_P_TO_USER_START_DISCHG"};
            case 118:
                return new String[]{"HOLD_VBAT_START_DERATING"};
            case 119:
                return new String[]{"HOLD_CT_POWER_OFFSET"};
            case 121:
                return new String[]{"HOLD_MIN_Q_PERCENT_FOR_QV"};
            case 122:
                return new String[]{"HOLD_MAINTENANCE_COUNT"};
            case 125:
                return new String[]{"HOLD_SOC_LOW_LIMIT_EPS_DISCHG"};
            case 134:
                return new String[]{"HOLD_UVF_DERATE_START_POINT"};
            case Opcodes.L2I /* 136 */:
                return new String[]{"HOLD_OVF_DROOP_KOF"};
            case 144:
                return new String[]{"HOLD_FLOATING_VOLTAGE"};
            case Opcodes.FCMPL /* 149 */:
                return new String[]{"HOLD_EQUALIZATION_VOLTAGE"};
            case Opcodes.FCMPG /* 150 */:
                return new String[]{"HOLD_EQUALIZATION_PERIOD"};
            case Opcodes.DCMPL /* 151 */:
                return new String[]{"HOLD_EQUALIZATION_TIME"};
            case Opcodes.IFLE /* 158 */:
                return new String[]{"HOLD_AC_CHARGE_START_BATTERY_VOLTAGE"};
            case Opcodes.IF_ICMPEQ /* 159 */:
                return new String[]{"HOLD_AC_CHARGE_END_BATTERY_VOLTAGE"};
            case Opcodes.IF_ICMPNE /* 160 */:
                return new String[]{"HOLD_AC_CHARGE_START_BATTERY_SOC"};
            case Opcodes.RET /* 169 */:
                return new String[]{"HOLD_ON_GRID_EOD_VOLTAGE"};
            case Opcodes.ARETURN /* 176 */:
                return new String[]{"HOLD_MAX_AC_INPUT_POWER"};
            case Opcodes.RETURN /* 177 */:
                return new String[]{"HOLD_MAX_GENERATOR_INPUT_POWER"};
            case Opcodes.PUTFIELD /* 181 */:
                return new String[]{"HOLD_VOLT_WATT_V1"};
            case Opcodes.INVOKEVIRTUAL /* 182 */:
                return new String[]{"HOLD_VOLT_WATT_V2"};
            case Opcodes.INVOKESPECIAL /* 183 */:
                return new String[]{"HOLD_VOLT_WATT_DELAY_TIME"};
            case Opcodes.INVOKESTATIC /* 184 */:
                return new String[]{"HOLD_VOLT_WATT_P2"};
            case Opcodes.INVOKEINTERFACE /* 185 */:
                return new String[]{"HOLD_VREF"};
            case 186:
                return new String[]{"HOLD_VREF_ADJUSTMENT_TIME_CONSTANT"};
            case Opcodes.NEW /* 187 */:
                return new String[]{"HOLD_Q3"};
            case 188:
                return new String[]{"HOLD_Q4"};
            case 189:
                return new String[]{"HOLD_P1"};
            case Opcodes.ARRAYLENGTH /* 190 */:
                return new String[]{"HOLD_P2"};
            case Opcodes.ATHROW /* 191 */:
                return new String[]{"HOLD_P3"};
            case Opcodes.INSTANCEOF /* 193 */:
                return new String[]{"HOLD_UVF_DROOP_KUF"};
            case Opcodes.MONITORENTER /* 194 */:
                return new String[]{"OFF_GRID_HOLD_GEN_CHG_START_VOLT"};
            case Opcodes.MONITOREXIT /* 195 */:
                return new String[]{"OFF_GRID_HOLD_GEN_CHG_END_VOLT"};
            case 196:
                return new String[]{"OFF_GRID_HOLD_GEN_CHG_START_SOC"};
            case 197:
                return new String[]{"OFF_GRID_HOLD_GEN_CHG_END_SOC"};
            case Opcodes.IFNULL /* 198 */:
                return new String[]{"OFF_GRID_HOLD_MAX_GEN_CHG_BAT_CURR"};
            case 201:
                return new String[]{"_12K_HOLD_CHARGE_FIRST_VOLT"};
            case 202:
                return new String[]{"_12K_HOLD_STOP_DISCHG_VOLT"};
            case 203:
                return new String[]{"_12K_HOLD_GRID_REGULATION"};
            case 205:
                return new String[]{"_12K_HOLD_GRID_TYPE"};
            case BuildConfig.VERSION_CODE /* 206 */:
                return new String[]{"_12K_HOLD_GRID_PEAK_SHAVING_POWER"};
            case 207:
                return new String[]{"_12K_HOLD_GRID_PEAK_SHAVING_SOC"};
            case 208:
                return new String[]{"_12K_HOLD_GRID_PEAK_SHAVING_VOLT"};
            case 209:
                return new String[]{"LSP_HOLD_DIS_CHG_POWER_TIME_37", "LSP_HOLD_DIS_CHG_POWER_TIME_38"};
            case 210:
                return new String[]{"LSP_HOLD_DIS_CHG_POWER_TIME_39", "LSP_HOLD_DIS_CHG_POWER_TIME_40"};
            case 211:
                return new String[]{"LSP_HOLD_DIS_CHG_POWER_TIME_41", "LSP_HOLD_DIS_CHG_POWER_TIME_42"};
            case 212:
                return new String[]{"LSP_HOLD_DIS_CHG_POWER_TIME_43", "LSP_HOLD_DIS_CHG_POWER_TIME_44"};
            case 213:
                return new String[]{"_12K_HOLD_SMART_LOAD_START_VOLT"};
            case 214:
                return new String[]{"_12K_HOLD_SMART_LOAD_END_VOLT"};
            case 215:
                return new String[]{"_12K_HOLD_SMART_LOAD_START_SOC"};
            case 216:
                return new String[]{"_12K_HOLD_SMART_LOAD_END_SOC"};
            case 217:
                return new String[]{"_12K_HOLD_START_PV_POWER"};
            case 218:
                return new String[]{"_12K_HOLD_GRID_PEAK_SHAVING_SOC_2"};
            case 219:
                return new String[]{"_12K_HOLD_GRID_PEAK_SHAVING_VOLT_2"};
            case 220:
                return new String[]{"_12K_HOLD_AC_COUPLE_START_SOC"};
            case 221:
                return new String[]{"_12K_HOLD_AC_COUPLE_END_SOC"};
            case 222:
                return new String[]{"_12K_HOLD_AC_COUPLE_START_VOLT"};
            case 223:
                return new String[]{"_12K_HOLD_AC_COUPLE_END_VOLT"};
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getValueShowText(String str, int i, String str2) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2146680827:
                if (str.equals("HOLD_UVF_DROOP_KUF")) {
                    c = 0;
                    break;
                }
                break;
            case -2110570028:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE")) {
                    c = 1;
                    break;
                }
                break;
            case -2106433585:
                if (str.equals("HOLD_GRID_FREQ_CONN_LOW")) {
                    c = 2;
                    break;
                }
                break;
            case -2092080778:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR")) {
                    c = 3;
                    break;
                }
                break;
            case -2051169280:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_CHG")) {
                    c = 4;
                    break;
                }
                break;
            case -2012582261:
                if (str.equals("HOLD_FEED_IN_GRID_POWER_PERCENT")) {
                    c = 5;
                    break;
                }
                break;
            case -1922746271:
                if (str.equals("HOLD_LEAD_ACID_CHARGE_VOLT_REF")) {
                    c = 6;
                    break;
                }
                break;
            case -1917271147:
                if (str.equals("_12K_HOLD_START_PV_POWER")) {
                    c = 7;
                    break;
                }
                break;
            case -1907123448:
                if (str.equals("OFF_GRID_HOLD_GEN_CHG_END_VOLT")) {
                    c = '\b';
                    break;
                }
                break;
            case -1905186084:
                if (str.equals("_12K_HOLD_SMART_LOAD_END_VOLT")) {
                    c = '\t';
                    break;
                }
                break;
            case -1854323434:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_DISCHG")) {
                    c = '\n';
                    break;
                }
                break;
            case -1774171999:
                if (str.equals("_12K_HOLD_OVF_DERATE_START_POINT")) {
                    c = 11;
                    break;
                }
                break;
            case -1738912721:
                if (str.equals("HOLD_EQUALIZATION_VOLTAGE")) {
                    c = '\f';
                    break;
                }
                break;
            case -1724289777:
                if (str.equals("HOLD_UVF_DERATE_START_POINT")) {
                    c = '\r';
                    break;
                }
                break;
            case -1662706451:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE_1")) {
                    c = 14;
                    break;
                }
                break;
            case -1662706450:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE_2")) {
                    c = 15;
                    break;
                }
                break;
            case -1612429665:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR_1")) {
                    c = 16;
                    break;
                }
                break;
            case -1612429664:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR_2")) {
                    c = 17;
                    break;
                }
                break;
            case -1605958379:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_DISCHG")) {
                    c = 18;
                    break;
                }
                break;
            case -1579641573:
                if (str.equals("HOLD_FW_CODE")) {
                    c = 19;
                    break;
                }
                break;
            case -1567068683:
                if (str.equals("_12K_HOLD_SMART_LOAD_START_VOLT")) {
                    c = 20;
                    break;
                }
                break;
            case -1563900533:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR")) {
                    c = 21;
                    break;
                }
                break;
            case -1551617594:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR")) {
                    c = 22;
                    break;
                }
                break;
            case -1536953061:
                if (str.equals("_12K_HOLD_STOP_DISCHG_VOLT")) {
                    c = 23;
                    break;
                }
                break;
            case -1475031011:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE")) {
                    c = 24;
                    break;
                }
                break;
            case -1448232788:
                if (str.equals("_12K_HOLD_AC_COUPLE_START_VOLT")) {
                    c = 25;
                    break;
                }
                break;
            case -1433471711:
                if (str.equals("HOLD_AC_CHARGE_START_BATTERY_VOLTAGE")) {
                    c = 26;
                    break;
                }
                break;
            case -1422719063:
                if (str.equals("HOLD_BATTERY_WARNING_VOLTAGE")) {
                    c = 27;
                    break;
                }
                break;
            case -1413838822:
                if (str.equals("HOLD_AC_CHARGE_END_BATTERY_VOLTAGE")) {
                    c = 28;
                    break;
                }
                break;
            case -1352201891:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_HIGH")) {
                    c = 29;
                    break;
                }
                break;
            case -1323572740:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_HIGH")) {
                    c = 30;
                    break;
                }
                break;
            case -1294943589:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_HIGH")) {
                    c = 31;
                    break;
                }
                break;
            case -1282654362:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_HIGH")) {
                    c = ' ';
                    break;
                }
                break;
            case -1254025211:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_HIGH")) {
                    c = '!';
                    break;
                }
                break;
            case -1225396060:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_HIGH")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case -1119226968:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE_1")) {
                    c = '#';
                    break;
                }
                break;
            case -1119226967:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE_2")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case -1033230202:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE_1")) {
                    c = '%';
                    break;
                }
                break;
            case -1033230201:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE_2")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case -917268805:
                if (str.equals("HOLD_FORCED_DISCHG_POWER_CMD")) {
                    c = '\'';
                    break;
                }
                break;
            case -915649916:
                if (str.equals("HOLD_BATTERY_LOW_TO_UTILITY_VOLTAGE")) {
                    c = '(';
                    break;
                }
                break;
            case -875057049:
                if (str.equals("HOLD_GRID_FREQ_CONN_HIGH")) {
                    c = ')';
                    break;
                }
                break;
            case -823881906:
                if (str.equals("HOLD_MAX_AC_INPUT_POWER")) {
                    c = '*';
                    break;
                }
                break;
            case -750853128:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR_1")) {
                    c = '+';
                    break;
                }
                break;
            case -750853127:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR_2")) {
                    c = ',';
                    break;
                }
                break;
            case -613454474:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE")) {
                    c = '-';
                    break;
                }
                break;
            case -595561232:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_LOW")) {
                    c = '.';
                    break;
                }
                break;
            case -594637711:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_LOW")) {
                    c = '/';
                    break;
                }
                break;
            case -593714190:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_LOW")) {
                    c = '0';
                    break;
                }
                break;
            case -573009974:
                if (str.equals("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT")) {
                    c = '1';
                    break;
                }
                break;
            case -537681431:
                if (str.equals("HOLD_NOMINAL_BATTERY_VOLTAGE")) {
                    c = '2';
                    break;
                }
                break;
            case -535537138:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_VOLT_2")) {
                    c = '3';
                    break;
                }
                break;
            case -444930136:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR_1")) {
                    c = '4';
                    break;
                }
                break;
            case -444930135:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR_2")) {
                    c = '5';
                    break;
                }
                break;
            case -386262971:
                if (str.equals("HOLD_OVF_DROOP_KOF")) {
                    c = '6';
                    break;
                }
                break;
            case -384118065:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR")) {
                    c = '7';
                    break;
                }
                break;
            case -307531482:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE")) {
                    c = '8';
                    break;
                }
                break;
            case -276744627:
                if (str.equals("HOLD_TIME")) {
                    c = '9';
                    break;
                }
                break;
            case -165590897:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE_1")) {
                    c = ':';
                    break;
                }
                break;
            case -165590896:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE_2")) {
                    c = ';';
                    break;
                }
                break;
            case -123360169:
                if (str.equals("INPUT_BATTERY_VOLTAGE")) {
                    c = Typography.less;
                    break;
                }
                break;
            case 4556681:
                if (str.equals("HOLD_MODEL")) {
                    c = '=';
                    break;
                }
                break;
            case 106114747:
                if (str.equals("HOLD_CT_POWER_OFFSET")) {
                    c = Typography.greater;
                    break;
                }
                break;
            case 124739648:
                if (str.equals("HOLD_ON_GRID_EOD_VOLTAGE")) {
                    c = '?';
                    break;
                }
                break;
            case 127930925:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR")) {
                    c = '@';
                    break;
                }
                break;
            case 180157537:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_CHG")) {
                    c = 'A';
                    break;
                }
                break;
            case 225083931:
                if (str.equals("HOLD_SERIAL_NUM")) {
                    c = 'B';
                    break;
                }
                break;
            case 229729985:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR_1")) {
                    c = 'C';
                    break;
                }
                break;
            case 229729986:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR_2")) {
                    c = 'D';
                    break;
                }
                break;
            case 265183773:
                if (str.equals("HOLD_START_PV_VOLT")) {
                    c = 'E';
                    break;
                }
                break;
            case 268168589:
                if (str.equals("HOLD_V1H")) {
                    c = 'F';
                    break;
                }
                break;
            case 268168593:
                if (str.equals("HOLD_V1L")) {
                    c = 'G';
                    break;
                }
                break;
            case 268168620:
                if (str.equals("HOLD_V2H")) {
                    c = 'H';
                    break;
                }
                break;
            case 268168624:
                if (str.equals("HOLD_V2L")) {
                    c = 'I';
                    break;
                }
                break;
            case 313840816:
                if (str.equals("HOLD_GRID_VOLT_CONN_HIGH")) {
                    c = 'J';
                    break;
                }
                break;
            case 330144381:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR_1")) {
                    c = 'K';
                    break;
                }
                break;
            case 330144382:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR_2")) {
                    c = 'L';
                    break;
                }
                break;
            case 367128639:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE")) {
                    c = 'M';
                    break;
                }
                break;
            case 390956452:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR")) {
                    c = 'N';
                    break;
                }
                break;
            case 467543035:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE")) {
                    c = 'O';
                    break;
                }
                break;
            case 485996371:
                if (str.equals("_12K_HOLD_AC_COUPLE_END_VOLT")) {
                    c = 'P';
                    break;
                }
                break;
            case 528065501:
                if (str.equals("HOLD_FORCED_CHG_POWER_CMD")) {
                    c = 'Q';
                    break;
                }
                break;
            case 614838895:
                if (str.equals("_12K_HOLD_CHARGE_FIRST_VOLT")) {
                    c = 'R';
                    break;
                }
                break;
            case 623306801:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE_1")) {
                    c = 'S';
                    break;
                }
                break;
            case 623306802:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE_2")) {
                    c = 'T';
                    break;
                }
                break;
            case 719765641:
                if (str.equals("HOLD_BATTERY_WARNING_RECOVERY_VOLTAGE")) {
                    c = 'U';
                    break;
                }
                break;
            case 814992216:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE_1")) {
                    c = 'V';
                    break;
                }
                break;
            case 814992217:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE_2")) {
                    c = 'W';
                    break;
                }
                break;
            case 866091809:
                if (str.equals("OFF_GRID_HOLD_GEN_CHG_START_VOLT")) {
                    c = 'X';
                    break;
                }
                break;
            case 1039780741:
                if (str.equals("HOLD_FLOATING_VOLTAGE")) {
                    c = 'Y';
                    break;
                }
                break;
            case 1118506598:
                if (str.equals("HOLD_GRID_VOLT_CONN_LOW")) {
                    c = 'Z';
                    break;
                }
                break;
            case 1203310617:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_LOW")) {
                    c = '[';
                    break;
                }
                break;
            case 1204234138:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_LOW")) {
                    c = '\\';
                    break;
                }
                break;
            case 1205157659:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_LOW")) {
                    c = ']';
                    break;
                }
                break;
            case 1224119563:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_POWER")) {
                    c = '^';
                    break;
                }
                break;
            case 1504508315:
                if (str.equals("HOLD_VBAT_START_DERATING")) {
                    c = '_';
                    break;
                }
                break;
            case 1527620681:
                if (str.equals("HOLD_MAX_GENERATOR_INPUT_POWER")) {
                    c = '`';
                    break;
                }
                break;
            case 1600103487:
                if (str.equals("HOLD_GRID_VOLT_MOV_AVG_HIGH")) {
                    c = 'a';
                    break;
                }
                break;
            case 1853690354:
                if (str.equals("HOLD_AC_CHARGE_POWER_CMD")) {
                    c = 'b';
                    break;
                }
                break;
            case 1910924292:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_37")) {
                    c = 'c';
                    break;
                }
                break;
            case 1910924293:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_38")) {
                    c = 'd';
                    break;
                }
                break;
            case 1910924294:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_39")) {
                    c = 'e';
                    break;
                }
                break;
            case 1910924316:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_40")) {
                    c = 'f';
                    break;
                }
                break;
            case 1910924317:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_41")) {
                    c = 'g';
                    break;
                }
                break;
            case 1910924318:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_42")) {
                    c = 'h';
                    break;
                }
                break;
            case 1910924319:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_43")) {
                    c = 'i';
                    break;
                }
                break;
            case 1910924320:
                if (str.equals("LSP_HOLD_DIS_CHG_POWER_TIME_44")) {
                    c = 'j';
                    break;
                }
                break;
            case 1979328795:
                if (str.equals("_12K_HOLD_GRID_PEAK_SHAVING_VOLT")) {
                    c = 'k';
                    break;
                }
                break;
            case 2046998614:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR_1")) {
                    c = 'l';
                    break;
                }
                break;
            case 2046998615:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR_2")) {
                    c = 'm';
                    break;
                }
                break;
            case 2069975635:
                if (str.equals("HOLD_POWER_SOFT_START_SLOPE")) {
                    c = 'n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case '6':
                return InvTool.formatData(1.0d / getInt2ParamValue(str, i, str2), 2);
            case 1:
            case 14:
            case 15:
            case 24:
            case '#':
            case '$':
            case '%':
            case '&':
            case '-':
            case '8':
            case ':':
            case ';':
            case 'M':
            case 'O':
            case 'S':
            case 'T':
            case 'V':
            case 'W':
            case 'd':
            case 'f':
            case 'h':
            case 'j':
                return ProTool.fillZeros(String.valueOf(getInt2HighParamValue(str, i, str2)), 2);
            case 2:
            case 11:
            case '\r':
            case 29:
            case 30:
            case 31:
            case ')':
            case '[':
            case '\\':
            case ']':
                return InvTool.formatData(getInt2ParamValue(str, i, str2) / 100.0d);
            case 3:
            case 16:
            case 17:
            case 21:
            case 22:
            case '+':
            case ',':
            case '4':
            case '5':
            case '7':
            case '@':
            case 'C':
            case 'D':
            case 'K':
            case 'L':
            case 'N':
            case 'c':
            case 'e':
            case 'g':
            case 'i':
            case 'l':
            case 'm':
                return ProTool.fillZeros(String.valueOf(getInt2LowParamValue(str, i, str2)), 2);
            case 4:
            case '\n':
            case 18:
            case 'A':
                return InvTool.formatData(getInt2ParamValueN(str, i, str2) / 10.0d);
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\f':
            case 20:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case ' ':
            case '!':
            case '\"':
            case '\'':
            case '(':
            case '*':
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '<':
            case '>':
            case '?':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'P':
            case 'Q':
            case 'R':
            case 'U':
            case 'X':
            case 'Y':
            case 'Z':
            case '^':
            case '_':
            case '`':
            case 'a':
            case 'b':
            case 'k':
            case 'n':
                return InvTool.formatData(getInt2ParamValue(str, i, str2) / 10.0d);
            case 19:
                int valueStartIndex = getValueStartIndex(str, i);
                return str2.substring(valueStartIndex, 4) + "-" + ProTool.showHex(str2.charAt(valueStartIndex + 5)) + ProTool.showHex(str2.charAt(valueStartIndex + 6));
            case '9':
                int valueStartIndex2 = getValueStartIndex(str, i);
                char charAt = str2.charAt(valueStartIndex2);
                char charAt2 = str2.charAt(valueStartIndex2 + 1);
                char charAt3 = str2.charAt(valueStartIndex2 + 2);
                char charAt4 = str2.charAt(valueStartIndex2 + 3);
                char charAt5 = str2.charAt(valueStartIndex2 + 4);
                char charAt6 = str2.charAt(valueStartIndex2 + 5);
                Calendar calendar = Calendar.getInstance();
                calendar.set(charAt + 2000, charAt2 - 1, charAt3, charAt4, charAt5, charAt6);
                return InvTool.formatDateTime(calendar.getTime());
            case '=':
                return "0x" + Long.toHexString(getLong4ParamValue(str, i, str2)).toUpperCase();
            case 'B':
                return str2.substring(getValueStartIndex(str, i), 10);
            default:
                return String.valueOf(getInt2ParamValue(str, i, str2));
        }
    }

    private long getLong4ParamValue(String str, int i, String str2) {
        int valueStartIndex = getValueStartIndex(str, i);
        return ProTool.count(str2.charAt(valueStartIndex + 3), str2.charAt(valueStartIndex + 2), str2.charAt(valueStartIndex + 1), str2.charAt(valueStartIndex));
    }

    private int getInt2ParamValue(String str, int i, String str2) {
        int valueStartIndex = getValueStartIndex(str, i);
        return ProTool.count(str2.charAt(valueStartIndex + 1), str2.charAt(valueStartIndex));
    }

    private int getInt2ParamValueN(String str, int i, String str2) {
        int valueStartIndex = getValueStartIndex(str, i);
        return ProTool.countN(str2.charAt(valueStartIndex + 1), str2.charAt(valueStartIndex));
    }

    private int getInt2LowParamValue(String str, int i, String str2) {
        return str2.charAt(getValueStartIndex(str, i));
    }

    private int getInt2HighParamValue(String str, int i, String str2) {
        return str2.charAt(getValueStartIndex(str, i) + 1);
    }

    private int getValueStartIndex(String str, int i) {
        return ((getStartRegisterByParam(str).intValue() - i) * 2) + 35;
    }

    public EditText getTimeDateEditText() {
        return this.timeDateEditText;
    }

    public EditText getTimeTimeEditText() {
        return this.timeTimeEditText;
    }
}
