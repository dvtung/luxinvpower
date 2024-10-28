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
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.connect.LocalConnect;
import com.nfcx.luxinvpower.global.Constants;
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
import com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment;
import com.nfcx.luxinvpower.view.login.LoginActivity;
import com.nfcx.luxinvpower.view.main.fragment.lv1.AbstractItemFragment;
import com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LocalSetFragment extends AbstractItemFragment {
    private String[] _110Functions;
    private String[] _21Functions;
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
    private Button acChargePowerCmdButton;
    private EditText acChargePowerCmdEditText;
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
    private Button all2DefaultButton;
    private ConstraintLayout applicationSetActionLayout;
    private TextView applicationSetActionTextView;
    private ToggleButton applicationSetActionToggleButton;
    private LinearLayout applicationSetParamLayout;
    private ToggleButton batterySharedFunctionButton;
    private Button chargeCurrentButton;
    private EditText chargeCurrentEditText;
    private Button chargePowerPercentCmdButton;
    private EditText chargePowerPercentCmdEditText;
    private ConstraintLayout chargeSetActionLayout;
    private TextView chargeSetActionTextView;
    private ToggleButton chargeSetActionToggleButton;
    private LinearLayout chargeSetParamLayout;
    private EditText comAddrEditText;
    private Button composedPhaseButton;
    private Button connectTimeButton;
    private EditText connectTimeEditText;
    private boolean created;
    private Button ctSampleRatioButton;
    private Spinner ctSampleRatioSpinner;
    private Button datalogSnButton;
    private EditText datalogSnEditText;
    private TextView datalogSnTextView;
    private Button dischargeCurrentLimitButton;
    private EditText dischargeCurrentLimitEditText;
    private ConstraintLayout dischargeSetActionLayout;
    private TextView dischargeSetActionTextView;
    private ToggleButton dischargeSetActionToggleButton;
    private LinearLayout dischargeSetParamLayout;
    private Button eodButton;
    private EditText eodEditText;
    private ToggleButton epsFunctionButton;
    private Button equalizationPeriodButton;
    private EditText equalizationPeriodEditText;
    private Button equalizationTimeButton;
    private EditText equalizationTimeEditText;
    private Button equalizationVoltageButton;
    private EditText equalizationVoltageEditText;
    private ToggleButton feedInGridFunctionButton;
    private Button feedInGridPowerPercentButton;
    private EditText feedInGridPowerPercentEditText;
    private Button floatingVoltageButton;
    private EditText floatingVoltageEditText;
    private EditText forcedChargeEndHour1EditText;
    private EditText forcedChargeEndHour2EditText;
    private EditText forcedChargeEndHourEditText;
    private EditText forcedChargeEndMinute1EditText;
    private EditText forcedChargeEndMinute2EditText;
    private EditText forcedChargeEndMinuteEditText;
    private Button forcedChargeEndTime1Button;
    private ConstraintLayout forcedChargeEndTime1Layout;
    private Button forcedChargeEndTime2Button;
    private ConstraintLayout forcedChargeEndTime2Layout;
    private Button forcedChargeEndTimeButton;
    private ConstraintLayout forcedChargeEndTimeLayout;
    private EditText forcedChargeStartHour1EditText;
    private EditText forcedChargeStartHour2EditText;
    private EditText forcedChargeStartHourEditText;
    private EditText forcedChargeStartMinute1EditText;
    private EditText forcedChargeStartMinute2EditText;
    private EditText forcedChargeStartMinuteEditText;
    private Button forcedChargeStartTime1Button;
    private ConstraintLayout forcedChargeStartTime1Layout;
    private Button forcedChargeStartTime2Button;
    private ConstraintLayout forcedChargeStartTime2Layout;
    private Button forcedChargeStartTimeButton;
    private ConstraintLayout forcedChargeStartTimeLayout;
    private ToggleButton forcedChgFunctionButton;
    private ConstraintLayout forcedChgFunctionLayout;
    private Button forcedChgPowerCmdButton;
    private EditText forcedChgPowerCmdEditText;
    private ConstraintLayout forcedChgPowerCmdLayout;
    private Button forcedChgSocLimitButton;
    private EditText forcedChgSocLimitEditText;
    private ConstraintLayout forcedChgSocLimitLayout;
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
    private Button forcedDisChgPowerPercentCmdButton;
    private EditText forcedDisChgPowerPercentCmdEditText;
    private Button forcedDisChgSocLimitButton;
    private EditText forcedDisChgSocLimitEditText;
    private ConstraintLayout gridConnectSetActionLayout;
    private TextView gridConnectSetActionTextView;
    private ToggleButton gridConnectSetActionToggleButton;
    private LinearLayout gridConnectSetParamLayout;
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
    private Button gridVoltMovAvgHighButton;
    private EditText gridVoltMovAvgHighEditText;
    private TextView inverterSnTextView;
    private Button leadAcidChargeVoltRefButton;
    private EditText leadAcidChargeVoltRefEditText;
    private Button leadAcidDischargeCutOffVoltButton;
    private EditText leadAcidDischargeCutOffVoltEditText;
    private LocalConnect localConnect;
    private Button masterOrSlaveButton;
    private Spinner masterOrSlaveSpinner;
    private Button maxAcInputPowerButton;
    private EditText maxAcInputPowerEditText;
    private ToggleButton microGridFunctionButton;
    private Button offGridDischargeCutoffSocButton;
    private EditText offGridDischargeCutoffSocEditText;
    private EditText pinEditText;
    private ToggleButton pvGridOffFunctionButton;
    private ConstraintLayout pvGridOffFunctionLayout;
    private Button pvInputModeButton;
    private ConstraintLayout pvInputModeLayout;
    private Spinner pvInputModeSpinner;
    private Button pvctSampleRatioButton;
    private Spinner pvctSampleRatioSpinner;
    private Button pvctSampleTypeButton;
    private Spinner pvctSampleTypeSpinner;
    private Button readAllButton;
    private Spinner readComposedPhaseSpinner;
    private Button reconnectTimeButton;
    private EditText reconnectTimeEditText;
    private Button reset2FactoryButton;
    private ConstraintLayout resetSetActionLayout;
    private TextView resetSetActionTextView;
    private ToggleButton resetSetActionToggleButton;
    private LinearLayout resetSetParamLayout;
    private ToggleButton runWithoutGridFunctionButton;
    private Button setComAddrButton;
    private Spinner setComposedPhaseSpinner;
    private Button setTimeButton;
    private ToggleButton setToStandbyFunctionButton;
    private Button startPvVoltButton;
    private EditText startPvVoltEditText;
    private ConstraintLayout startPvVoltLayout;
    private ToggleButton swSeamlesslyEnButton;
    private EditText timeDateEditText;
    private EditText timeTimeEditText;
    private Button updateFirmwareButton;
    private ConstraintLayout wifiModuleSetActionLayout;
    private TextView wifiModuleSetActionTextView;
    private ToggleButton wifiModuleSetActionToggleButton;
    private LinearLayout wifiModuleSetParamLayout;

    private static boolean checkIfRangeValid(int i, int i2, int i3) {
        return i3 >= i && i3 <= i2;
    }

    public LocalSetFragment(LocalConnect localConnect) {
        super(21L);
        this._21Functions = new String[]{"FUNC_AC_CHARGE", "FUNC_FORCED_CHG_EN", "FUNC_FORCED_DISCHG_EN", "FUNC_SW_SEAMLESSLY_EN", "FUNC_SET_TO_STANDBY", "FUNC_EPS_EN", "FUNC_FEED_IN_GRID_EN"};
        this._110Functions = new String[]{"FUNC_PV_GRID_OFF_EN", "FUNC_RUN_WITHOUT_GRID", "FUNC_MICRO_GRID_EN", "FUNC_BAT_SHARED"};
        this.localConnect = localConnect;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        System.out.println("LuxPower - LocalSetFragment - onCreateView...");
        View inflate = layoutInflater.inflate(R.layout.fragment_local_set, viewGroup, false);
        ((ConstraintLayout) inflate.findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) LoginActivity.class));
                LocalActivity.instance.finish();
            }
        });
        this.datalogSnTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_datalogSn_TextView);
        this.inverterSnTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_inverterSn_TextView);
        Button button = (Button) inflate.findViewById(R.id.fragment_remote_set_readAllButton);
        this.readAllButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Inverter inverter = LocalSetFragment.this.localConnect.getInverter();
                if (inverter != null) {
                    LocalSetFragment.this.clearFragmentData();
                    LocalSetFragment.this.readAllButton.setEnabled(false);
                    new ReadMultiParamTask(LocalSetFragment.this).execute(new RemoteReadInfo(inverter.getSerialNum(), 0, 40), new RemoteReadInfo(inverter.getSerialNum(), 40, 40), new RemoteReadInfo(inverter.getSerialNum(), 80, 40), new RemoteReadInfo(inverter.getSerialNum(), 120, 40), new RemoteReadInfo(inverter.getSerialNum(), Opcodes.IF_ICMPNE, 20));
                    new ReadDatalogParamTask(LocalSetFragment.this).execute(1);
                }
            }
        });
        EditText editText = (EditText) inflate.findViewById(R.id.fragment_remote_set_timeDateEditText);
        this.timeDateEditText = editText;
        editText.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = LocalSetFragment.this.timeDateEditText.getText().toString();
                if (Tool.isEmpty(obj) || obj.length() != 10) {
                    LocalSetFragment.this.timeDateEditText.setText(InvTool.formatDate(new Date()));
                }
                LocalSetFragment.this.getActivity().showDialog(0);
            }
        });
        EditText editText2 = (EditText) inflate.findViewById(R.id.fragment_remote_set_timeTimeEditText);
        this.timeTimeEditText = editText2;
        editText2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = LocalSetFragment.this.timeTimeEditText.getText().toString();
                if (Tool.isEmpty(obj) || obj.length() != 5) {
                    LocalSetFragment.this.timeTimeEditText.setText(InvTool.formatTime(new Date()).substring(0, 5));
                }
                LocalSetFragment.this.getActivity().showDialog(1);
            }
        });
        Button button2 = (Button) inflate.findViewById(R.id.fragment_remote_set_timeButton);
        this.setTimeButton = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                LocalSetFragment.this.runNormalRemoteWrite("HOLD_TIME", LocalSetFragment.this.timeDateEditText.getText().toString().trim() + " " + LocalSetFragment.this.timeTimeEditText.getText().toString().trim() + ":" + String.valueOf(calendar.get(13)), LocalSetFragment.this.setTimeButton);
            }
        });
        this.comAddrEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_comAddrEditText);
        Button button3 = (Button) inflate.findViewById(R.id.fragment_remote_set_comAddrButton);
        this.setComAddrButton = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_COM_ADDR", localSetFragment.comAddrEditText.getText().toString().trim(), LocalSetFragment.this.setComAddrButton);
            }
        });
        this.pvInputModeLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_pvInputModeLayout);
        this.pvInputModeSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_pvInputModeSpinner);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_pv_input_mode_empty)));
        arrayList.add(new Property(String.valueOf(0), getString(R.string.phrase_param_pv_input_mode_0)));
        arrayList.add(new Property(String.valueOf(1), getString(R.string.phrase_param_pv_input_mode_1)));
        arrayList.add(new Property(String.valueOf(2), getString(R.string.phrase_param_pv_input_mode_2)));
        arrayList.add(new Property(String.valueOf(3), getString(R.string.phrase_param_pv_input_mode_3)));
        arrayList.add(new Property(String.valueOf(4), getString(R.string.phrase_param_pv_input_mode_4)));
        ArrayAdapter arrayAdapter = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.pvInputModeSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        Button button4 = (Button) inflate.findViewById(R.id.fragment_remote_set_pvInputModeButton);
        this.pvInputModeButton = button4;
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) LocalSetFragment.this.pvInputModeSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                LocalSetFragment.this.runNormalRemoteWrite("HOLD_PV_INPUT_MODE", property.getName(), LocalSetFragment.this.pvInputModeButton);
            }
        });
        this.startPvVoltLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_startPvVoltLayout);
        this.startPvVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_startPvVoltEditText);
        Button button5 = (Button) inflate.findViewById(R.id.fragment_remote_set_startPvVoltButton);
        this.startPvVoltButton = button5;
        button5.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_START_PV_VOLT", localSetFragment.startPvVoltEditText.getText().toString().trim(), LocalSetFragment.this.startPvVoltButton);
            }
        });
        this.ctSampleRatioSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_ctSampleRatioSpinner);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Property(String.valueOf(-1), getString(R.string.phrase_bit_param_ct_sample_ratio_empty)));
        arrayList2.add(new Property(String.valueOf(0), getString(R.string.phrase_bit_param_ct_sample_ratio_0)));
        arrayList2.add(new Property(String.valueOf(1), getString(R.string.phrase_bit_param_ct_sample_ratio_1)));
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.ctSampleRatioSpinner.setAdapter((SpinnerAdapter) arrayAdapter2);
        Button button6 = (Button) inflate.findViewById(R.id.fragment_remote_set_ctSampleRatioButton);
        this.ctSampleRatioButton = button6;
        button6.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) LocalSetFragment.this.ctSampleRatioSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                LocalSetFragment.this.runBitRemoteWrite("BIT_CT_SAMPLE_RATIO", property.getName(), LocalSetFragment.this.ctSampleRatioButton);
            }
        });
        this.pvctSampleTypeSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_pvctSampleTypeSpinner);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new Property(String.valueOf(-1), getString(R.string.phrase_bit_param_pvct_sample_type_empty)));
        arrayList3.add(new Property(String.valueOf(0), getString(R.string.phrase_bit_param_pvct_sample_type_0)));
        arrayList3.add(new Property(String.valueOf(1), getString(R.string.phrase_bit_param_pvct_sample_type_1)));
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList3);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.pvctSampleTypeSpinner.setAdapter((SpinnerAdapter) arrayAdapter3);
        Button button7 = (Button) inflate.findViewById(R.id.fragment_remote_set_pvctSampleTypeButton);
        this.pvctSampleTypeButton = button7;
        button7.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) LocalSetFragment.this.pvctSampleTypeSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                LocalSetFragment.this.runBitRemoteWrite("BIT_PVCT_SAMPLE_TYPE", property.getName(), LocalSetFragment.this.pvctSampleTypeButton);
            }
        });
        this.pvctSampleRatioSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_pvctSampleRatioSpinner);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new Property(String.valueOf(-1), getString(R.string.phrase_bit_param_pvct_sample_ratio_empty)));
        arrayList4.add(new Property(String.valueOf(0), getString(R.string.phrase_bit_param_pvct_sample_ratio_0)));
        arrayList4.add(new Property(String.valueOf(1), getString(R.string.phrase_bit_param_pvct_sample_ratio_1)));
        ArrayAdapter arrayAdapter4 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList4);
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.pvctSampleRatioSpinner.setAdapter((SpinnerAdapter) arrayAdapter4);
        Button button8 = (Button) inflate.findViewById(R.id.fragment_remote_set_pvctSampleRatioButton);
        this.pvctSampleRatioButton = button8;
        button8.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) LocalSetFragment.this.pvctSampleRatioSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                LocalSetFragment.this.runBitRemoteWrite("BIT_PVCT_SAMPLE_RATIO", property.getName(), LocalSetFragment.this.pvctSampleRatioButton);
            }
        });
        ToggleButton toggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_setToStandbyFunctionButton);
        this.setToStandbyFunctionButton = toggleButton;
        toggleButton.setOnClickListener(new AnonymousClass12());
        ToggleButton toggleButton2 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_epsFunctionButton);
        this.epsFunctionButton = toggleButton2;
        toggleButton2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_EPS_EN", localSetFragment.epsFunctionButton);
            }
        });
        ToggleButton toggleButton3 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_swSeamlesslyEnButton);
        this.swSeamlesslyEnButton = toggleButton3;
        toggleButton3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_SW_SEAMLESSLY_EN", localSetFragment.swSeamlesslyEnButton);
            }
        });
        ToggleButton toggleButton4 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_runWithoutGridFunctionButton);
        this.runWithoutGridFunctionButton = toggleButton4;
        toggleButton4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_RUN_WITHOUT_GRID", localSetFragment.runWithoutGridFunctionButton);
            }
        });
        this.pvGridOffFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_pvGridOffFunctionLayout);
        ToggleButton toggleButton5 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_pvGridOffFunctionButton);
        this.pvGridOffFunctionButton = toggleButton5;
        toggleButton5.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_PV_GRID_OFF_EN", localSetFragment.pvGridOffFunctionButton);
            }
        });
        ToggleButton toggleButton6 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_feedInGridFunctionButton);
        this.feedInGridFunctionButton = toggleButton6;
        toggleButton6.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_FEED_IN_GRID_EN", localSetFragment.feedInGridFunctionButton);
            }
        });
        this.feedInGridPowerPercentEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_feedInGridPowerPercentEditText);
        Button button9 = (Button) inflate.findViewById(R.id.fragment_remote_set_feedInGridPowerPercentButton);
        this.feedInGridPowerPercentButton = button9;
        button9.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_FEED_IN_GRID_POWER_PERCENT", localSetFragment.feedInGridPowerPercentEditText.getText().toString().trim(), LocalSetFragment.this.feedInGridPowerPercentButton);
            }
        });
        this.masterOrSlaveSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_masterOrSlaveSpinner);
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_master_or_slave_empty)));
        arrayList5.add(new Property(String.valueOf(1), getString(R.string.phrase_param_master_or_slave_hybird_1)));
        arrayList5.add(new Property(String.valueOf(2), getString(R.string.phrase_param_master_or_slave_hybird_2)));
        arrayList5.add(new Property(String.valueOf(3), getString(R.string.phrase_param_master_or_slave_hybird_3)));
        ArrayAdapter arrayAdapter5 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList5);
        arrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.masterOrSlaveSpinner.setAdapter((SpinnerAdapter) arrayAdapter5);
        Button button10 = (Button) inflate.findViewById(R.id.fragment_remote_set_masterOrSlaveButton);
        this.masterOrSlaveButton = button10;
        button10.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) LocalSetFragment.this.masterOrSlaveSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                LocalSetFragment.this.runNormalRemoteWrite("HOLD_SET_MASTER_OR_SLAVE", property.getName(), LocalSetFragment.this.masterOrSlaveButton);
            }
        });
        this.readComposedPhaseSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_readComposedPhaseSpinner);
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add(new Property(String.valueOf(-1), "--"));
        arrayList6.add(new Property(String.valueOf(1), getString(R.string.phrase_param_composed_phase_1)));
        arrayList6.add(new Property(String.valueOf(2), getString(R.string.phrase_param_composed_phase_2)));
        arrayList6.add(new Property(String.valueOf(3), getString(R.string.phrase_param_composed_phase_3)));
        ArrayAdapter arrayAdapter6 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList6);
        arrayAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.readComposedPhaseSpinner.setAdapter((SpinnerAdapter) arrayAdapter6);
        this.readComposedPhaseSpinner.setEnabled(false);
        this.setComposedPhaseSpinner = (Spinner) inflate.findViewById(R.id.fragment_remote_set_setComposedPhaseSpinner);
        ArrayList arrayList7 = new ArrayList();
        arrayList7.add(new Property(String.valueOf(-1), getString(R.string.phrase_param_composed_phase_empty)));
        arrayList7.add(new Property(String.valueOf(0), getString(R.string.phrase_param_composed_phase_0)));
        arrayList7.add(new Property(String.valueOf(1), getString(R.string.phrase_param_composed_phase_1)));
        arrayList7.add(new Property(String.valueOf(2), getString(R.string.phrase_param_composed_phase_2)));
        arrayList7.add(new Property(String.valueOf(3), getString(R.string.phrase_param_composed_phase_3)));
        ArrayAdapter arrayAdapter7 = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, arrayList7);
        arrayAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setComposedPhaseSpinner.setAdapter((SpinnerAdapter) arrayAdapter7);
        Button button11 = (Button) inflate.findViewById(R.id.fragment_remote_set_composedPhaseButton);
        this.composedPhaseButton = button11;
        button11.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Property property = (Property) LocalSetFragment.this.setComposedPhaseSpinner.getSelectedItem();
                if ("-1".equals(property.getName())) {
                    return;
                }
                LocalSetFragment.this.runNormalRemoteWrite("HOLD_SET_COMPOSED_PHASE", property.getName(), LocalSetFragment.this.composedPhaseButton);
            }
        });
        ToggleButton toggleButton7 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_microGridFunctionButton);
        this.microGridFunctionButton = toggleButton7;
        toggleButton7.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_MICRO_GRID_EN", localSetFragment.microGridFunctionButton);
            }
        });
        ToggleButton toggleButton8 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_batterySharedFunctionButton);
        this.batterySharedFunctionButton = toggleButton8;
        toggleButton8.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_BAT_SHARED", localSetFragment.batterySharedFunctionButton);
            }
        });
        this.maxAcInputPowerEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_maxAcInputPowerEditText);
        Button button12 = (Button) inflate.findViewById(R.id.fragment_remote_set_maxAcInputPowerButton);
        this.maxAcInputPowerButton = button12;
        button12.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_MAX_AC_INPUT_POWER", localSetFragment.maxAcInputPowerEditText.getText().toString().trim(), LocalSetFragment.this.maxAcInputPowerButton);
            }
        });
        this.connectTimeEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_connectTimeEditText);
        Button button13 = (Button) inflate.findViewById(R.id.fragment_remote_set_connectTimeButton);
        this.connectTimeButton = button13;
        button13.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_CONNECT_TIME", localSetFragment.connectTimeEditText.getText().toString().trim(), LocalSetFragment.this.connectTimeButton);
            }
        });
        this.reconnectTimeEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_reconnectTimeEditText);
        Button button14 = (Button) inflate.findViewById(R.id.fragment_remote_set_reconnectTimeButton);
        this.reconnectTimeButton = button14;
        button14.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_RECONNECT_TIME", localSetFragment.reconnectTimeEditText.getText().toString().trim(), LocalSetFragment.this.reconnectTimeButton);
            }
        });
        this.gridVoltConnHighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltConnHighEditText);
        Button button15 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltConnHighButton);
        this.gridVoltConnHighButton = button15;
        button15.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_CONN_HIGH", localSetFragment.gridVoltConnHighEditText.getText().toString().trim(), LocalSetFragment.this.gridVoltConnHighButton);
            }
        });
        this.gridVoltConnLowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltConnLowEditText);
        Button button16 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltConnLowButton);
        this.gridVoltConnLowButton = button16;
        button16.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_CONN_LOW", localSetFragment.gridVoltConnLowEditText.getText().toString().trim(), LocalSetFragment.this.gridVoltConnLowButton);
            }
        });
        this.gridFreqConnHighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqConnHighEditText);
        Button button17 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqConnHighButton);
        this.gridFreqConnHighButton = button17;
        button17.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_CONN_HIGH", localSetFragment.gridFreqConnHighEditText.getText().toString().trim(), LocalSetFragment.this.gridFreqConnHighButton);
            }
        });
        this.gridFreqConnLowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqConnLowEditText);
        Button button18 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqConnLowButton);
        this.gridFreqConnLowButton = button18;
        button18.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_CONN_LOW", localSetFragment.gridFreqConnLowEditText.getText().toString().trim(), LocalSetFragment.this.gridFreqConnLowButton);
            }
        });
        this.gridVoltLimit1LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit1LowEditText);
        Button button19 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit1LowButton);
        this.gridVoltLimit1LowButton = button19;
        button19.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT1_LOW", localSetFragment.gridVoltLimit1LowEditText.getText().toString().trim(), LocalSetFragment.this.gridVoltLimit1LowButton);
            }
        });
        this.gridVoltLimit1HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit1HighEditText);
        Button button20 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit1HighButton);
        this.gridVoltLimit1HighButton = button20;
        button20.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT1_HIGH", localSetFragment.gridVoltLimit1HighEditText.getText().toString().trim(), LocalSetFragment.this.gridVoltLimit1HighButton);
            }
        });
        this.gridVoltLimit2LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit2LowEditText);
        Button button21 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit2LowButton);
        this.gridVoltLimit2LowButton = button21;
        button21.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT2_LOW", localSetFragment.gridVoltLimit2LowEditText.getText().toString().trim(), LocalSetFragment.this.gridVoltLimit2LowButton);
            }
        });
        this.gridVoltLimit2HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit2HighEditText);
        Button button22 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit2HighButton);
        this.gridVoltLimit2HighButton = button22;
        button22.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT2_HIGH", localSetFragment.gridVoltLimit2HighEditText.getText().toString().trim(), LocalSetFragment.this.gridVoltLimit2HighButton);
            }
        });
        this.gridVoltLimit3LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit3LowEditText);
        Button button23 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit3LowButton);
        this.gridVoltLimit3LowButton = button23;
        button23.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT3_LOW", localSetFragment.gridVoltLimit3LowEditText.getText().toString().trim(), LocalSetFragment.this.gridVoltLimit3LowButton);
            }
        });
        this.gridVoltLimit3HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit3HighEditText);
        Button button24 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltLimit3HighButton);
        this.gridVoltLimit3HighButton = button24;
        button24.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_LIMIT3_HIGH", localSetFragment.gridVoltLimit3HighEditText.getText().toString().trim(), LocalSetFragment.this.gridVoltLimit3HighButton);
            }
        });
        this.gridVoltMovAvgHighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridVoltMovAvgHighEditText);
        Button button25 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridVoltMovAvgHighButton);
        this.gridVoltMovAvgHighButton = button25;
        button25.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_VOLT_MOV_AVG_HIGH", localSetFragment.gridVoltMovAvgHighEditText.getText().toString().trim(), LocalSetFragment.this.gridVoltMovAvgHighButton);
            }
        });
        this.gridFreqLimit1LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit1LowEditText);
        Button button26 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit1LowButton);
        this.gridFreqLimit1LowButton = button26;
        button26.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT1_LOW", localSetFragment.gridFreqLimit1LowEditText.getText().toString().trim(), LocalSetFragment.this.gridFreqLimit1LowButton);
            }
        });
        this.gridFreqLimit1HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit1HighEditText);
        Button button27 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit1HighButton);
        this.gridFreqLimit1HighButton = button27;
        button27.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.38
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT1_HIGH", localSetFragment.gridFreqLimit1HighEditText.getText().toString().trim(), LocalSetFragment.this.gridFreqLimit1HighButton);
            }
        });
        this.gridFreqLimit2LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit2LowEditText);
        Button button28 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit2LowButton);
        this.gridFreqLimit2LowButton = button28;
        button28.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.39
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT2_LOW", localSetFragment.gridFreqLimit2LowEditText.getText().toString().trim(), LocalSetFragment.this.gridFreqLimit2LowButton);
            }
        });
        this.gridFreqLimit2HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit2HighEditText);
        Button button29 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit2HighButton);
        this.gridFreqLimit2HighButton = button29;
        button29.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT2_HIGH", localSetFragment.gridFreqLimit2HighEditText.getText().toString().trim(), LocalSetFragment.this.gridFreqLimit2HighButton);
            }
        });
        this.gridFreqLimit3LowEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit3LowEditText);
        Button button30 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit3LowButton);
        this.gridFreqLimit3LowButton = button30;
        button30.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT3_LOW", localSetFragment.gridFreqLimit3LowEditText.getText().toString().trim(), LocalSetFragment.this.gridFreqLimit3LowButton);
            }
        });
        this.gridFreqLimit3HighEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit3HighEditText);
        Button button31 = (Button) inflate.findViewById(R.id.fragment_remote_set_gridFreqLimit3HighButton);
        this.gridFreqLimit3HighButton = button31;
        button31.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.42
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_GRID_FREQ_LIMIT3_HIGH", localSetFragment.gridFreqLimit3HighEditText.getText().toString().trim(), LocalSetFragment.this.gridFreqLimit3HighButton);
            }
        });
        this.chargePowerPercentCmdEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_chargePowerPercentCmdEditText);
        Button button32 = (Button) inflate.findViewById(R.id.fragment_remote_set_chargePowerPercentCmdButton);
        this.chargePowerPercentCmdButton = button32;
        button32.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.43
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_CHARGE_POWER_PERCENT_CMD", localSetFragment.chargePowerPercentCmdEditText.getText().toString().trim(), LocalSetFragment.this.chargePowerPercentCmdButton);
            }
        });
        this.equalizationVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_equalizationVoltageEditText);
        Button button33 = (Button) inflate.findViewById(R.id.fragment_remote_set_equalizationVoltageButton);
        this.equalizationVoltageButton = button33;
        button33.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.44
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_EQUALIZATION_VOLTAGE", localSetFragment.equalizationVoltageEditText.getText().toString().trim(), LocalSetFragment.this.equalizationVoltageButton);
            }
        });
        this.equalizationPeriodEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_equalizationPeriodEditText);
        Button button34 = (Button) inflate.findViewById(R.id.fragment_remote_set_equalizationPeriodButton);
        this.equalizationPeriodButton = button34;
        button34.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.45
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_EQUALIZATION_PERIOD", localSetFragment.equalizationPeriodEditText.getText().toString().trim(), LocalSetFragment.this.equalizationPeriodButton);
            }
        });
        this.equalizationTimeEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_equalizationTimeEditText);
        Button button35 = (Button) inflate.findViewById(R.id.fragment_remote_set_equalizationTimeButton);
        this.equalizationTimeButton = button35;
        button35.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.46
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_EQUALIZATION_TIME", localSetFragment.equalizationTimeEditText.getText().toString().trim(), LocalSetFragment.this.equalizationTimeButton);
            }
        });
        ToggleButton toggleButton9 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_acChargeFunctionButton);
        this.acChargeFunctionButton = toggleButton9;
        toggleButton9.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.47
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_AC_CHARGE", localSetFragment.acChargeFunctionButton);
            }
        });
        this.acChargePowerCmdEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargePowerCmdEditText);
        Button button36 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargePowerCmdButton);
        this.acChargePowerCmdButton = button36;
        button36.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.48
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_POWER_CMD", localSetFragment.acChargePowerCmdEditText.getText().toString().trim(), LocalSetFragment.this.acChargePowerCmdButton);
            }
        });
        this.acChargeSocLimitEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeSocLimitEditText);
        Button button37 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeSocLimitButton);
        this.acChargeSocLimitButton = button37;
        button37.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.49
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_AC_CHARGE_SOC_LIMIT", localSetFragment.acChargeSocLimitEditText.getText().toString().trim(), LocalSetFragment.this.acChargeSocLimitButton);
            }
        });
        this.acChargeStartHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartHourEditText);
        this.acChargeStartMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartMinuteEditText);
        Button button38 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartTimeButton);
        this.acChargeStartTimeButton = button38;
        button38.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.50
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_START_TIME", localSetFragment.acChargeStartHourEditText, LocalSetFragment.this.acChargeStartMinuteEditText, LocalSetFragment.this.acChargeStartTimeButton);
            }
        });
        this.acChargeEndHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndHourEditText);
        this.acChargeEndMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndMinuteEditText);
        Button button39 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndTimeButton);
        this.acChargeEndTimeButton = button39;
        button39.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.51
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_END_TIME", localSetFragment.acChargeEndHourEditText, LocalSetFragment.this.acChargeEndMinuteEditText, LocalSetFragment.this.acChargeEndTimeButton);
            }
        });
        this.acChargeStartHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartHour1EditText);
        this.acChargeStartMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartMinute1EditText);
        Button button40 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartTime1Button);
        this.acChargeStartTime1Button = button40;
        button40.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.52
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_START_TIME_1", localSetFragment.acChargeStartHour1EditText, LocalSetFragment.this.acChargeStartMinute1EditText, LocalSetFragment.this.acChargeStartTime1Button);
            }
        });
        this.acChargeEndHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndHour1EditText);
        this.acChargeEndMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndMinute1EditText);
        Button button41 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndTime1Button);
        this.acChargeEndTime1Button = button41;
        button41.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.53
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_END_TIME_1", localSetFragment.acChargeEndHour1EditText, LocalSetFragment.this.acChargeEndMinute1EditText, LocalSetFragment.this.acChargeEndTime1Button);
            }
        });
        this.acChargeStartHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartHour2EditText);
        this.acChargeStartMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeStartMinute2EditText);
        Button button42 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeStartTime2Button);
        this.acChargeStartTime2Button = button42;
        button42.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.54
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_START_TIME_2", localSetFragment.acChargeStartHour2EditText, LocalSetFragment.this.acChargeStartMinute2EditText, LocalSetFragment.this.acChargeStartTime2Button);
            }
        });
        this.acChargeEndHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndHour2EditText);
        this.acChargeEndMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_acChargeEndMinute2EditText);
        Button button43 = (Button) inflate.findViewById(R.id.fragment_remote_set_acChargeEndTime2Button);
        this.acChargeEndTime2Button = button43;
        button43.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.55
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_AC_CHARGE_END_TIME_2", localSetFragment.acChargeEndHour2EditText, LocalSetFragment.this.acChargeEndMinute2EditText, LocalSetFragment.this.acChargeEndTime2Button);
            }
        });
        this.forcedChgFunctionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_forcedChgFunctionLayout);
        ToggleButton toggleButton10 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_forcedChgFunctionButton);
        this.forcedChgFunctionButton = toggleButton10;
        toggleButton10.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.56
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_FORCED_CHG_EN", localSetFragment.forcedChgFunctionButton);
            }
        });
        this.forcedChgPowerCmdLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_forcedChgPowerCmdLayout);
        this.forcedChgPowerCmdEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChgPowerCmdEditText);
        Button button44 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChgPowerCmdButton);
        this.forcedChgPowerCmdButton = button44;
        button44.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.57
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_FORCED_CHG_POWER_CMD", localSetFragment.forcedChgPowerCmdEditText.getText().toString().trim(), LocalSetFragment.this.forcedChgPowerCmdButton);
            }
        });
        this.forcedChgSocLimitLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_forcedChgSocLimitLayout);
        this.forcedChgSocLimitEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChgSocLimitEditText);
        Button button45 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChgSocLimitButton);
        this.forcedChgSocLimitButton = button45;
        button45.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.58
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_FORCED_CHG_SOC_LIMIT", localSetFragment.forcedChgSocLimitEditText.getText().toString().trim(), LocalSetFragment.this.forcedChgSocLimitButton);
            }
        });
        this.forcedChargeStartTimeLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartTimeLayout);
        this.forcedChargeStartHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartHourEditText);
        this.forcedChargeStartMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartMinuteEditText);
        Button button46 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartTimeButton);
        this.forcedChargeStartTimeButton = button46;
        button46.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.59
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_START_TIME", localSetFragment.forcedChargeStartHourEditText, LocalSetFragment.this.forcedChargeStartMinuteEditText, LocalSetFragment.this.forcedChargeStartTimeButton);
            }
        });
        this.forcedChargeEndTimeLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndTimeLayout);
        this.forcedChargeEndHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndHourEditText);
        this.forcedChargeEndMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndMinuteEditText);
        Button button47 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndTimeButton);
        this.forcedChargeEndTimeButton = button47;
        button47.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.60
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_END_TIME", localSetFragment.forcedChargeEndHourEditText, LocalSetFragment.this.forcedChargeEndMinuteEditText, LocalSetFragment.this.forcedChargeEndTimeButton);
            }
        });
        this.forcedChargeStartTime1Layout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartTime1Layout);
        this.forcedChargeStartHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartHour1EditText);
        this.forcedChargeStartMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartMinute1EditText);
        Button button48 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartTime1Button);
        this.forcedChargeStartTime1Button = button48;
        button48.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.61
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_START_TIME_1", localSetFragment.forcedChargeStartHour1EditText, LocalSetFragment.this.forcedChargeStartMinute1EditText, LocalSetFragment.this.forcedChargeStartTime1Button);
            }
        });
        this.forcedChargeEndTime1Layout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndTime1Layout);
        this.forcedChargeEndHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndHour1EditText);
        this.forcedChargeEndMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndMinute1EditText);
        Button button49 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndTime1Button);
        this.forcedChargeEndTime1Button = button49;
        button49.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.62
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_END_TIME_1", localSetFragment.forcedChargeEndHour1EditText, LocalSetFragment.this.forcedChargeEndMinute1EditText, LocalSetFragment.this.forcedChargeEndTime1Button);
            }
        });
        this.forcedChargeStartTime2Layout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartTime2Layout);
        this.forcedChargeStartHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartHour2EditText);
        this.forcedChargeStartMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartMinute2EditText);
        Button button50 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeStartTime2Button);
        this.forcedChargeStartTime2Button = button50;
        button50.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.63
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_START_TIME_2", localSetFragment.forcedChargeStartHour2EditText, LocalSetFragment.this.forcedChargeStartMinute2EditText, LocalSetFragment.this.forcedChargeStartTime2Button);
            }
        });
        this.forcedChargeEndTime2Layout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndTime2Layout);
        this.forcedChargeEndHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndHour2EditText);
        this.forcedChargeEndMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndMinute2EditText);
        Button button51 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedChargeEndTime2Button);
        this.forcedChargeEndTime2Button = button51;
        button51.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.64
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_CHARGE_END_TIME_2", localSetFragment.forcedChargeEndHour2EditText, LocalSetFragment.this.forcedChargeEndMinute2EditText, LocalSetFragment.this.forcedChargeEndTime2Button);
            }
        });
        this.leadAcidChargeVoltRefEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_leadAcidChargeVoltRefEditText);
        Button button52 = (Button) inflate.findViewById(R.id.fragment_remote_set_leadAcidChargeVoltRefButton);
        this.leadAcidChargeVoltRefButton = button52;
        button52.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.65
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_CHARGE_VOLT_REF", localSetFragment.leadAcidChargeVoltRefEditText.getText().toString().trim(), LocalSetFragment.this.leadAcidChargeVoltRefButton);
            }
        });
        this.floatingVoltageEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_floatingVoltageEditText);
        Button button53 = (Button) inflate.findViewById(R.id.fragment_remote_set_floatingVoltageButton);
        this.floatingVoltageButton = button53;
        button53.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.66
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_FLOATING_VOLTAGE", localSetFragment.floatingVoltageEditText.getText().toString().trim(), LocalSetFragment.this.floatingVoltageButton);
            }
        });
        this.chargeCurrentEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_chargeCurrentEditText);
        Button button54 = (Button) inflate.findViewById(R.id.fragment_remote_set_chargeCurrentButton);
        this.chargeCurrentButton = button54;
        button54.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.67
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_CHARGE_RATE", localSetFragment.chargeCurrentEditText.getText().toString().trim(), LocalSetFragment.this.chargeCurrentButton);
            }
        });
        this.forcedDisChgPowerPercentCmdEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgPowerPercentCmdEditText);
        Button button55 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgPowerPercentCmdButton);
        this.forcedDisChgPowerPercentCmdButton = button55;
        button55.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.68
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_DISCHG_POWER_PERCENT_CMD", localSetFragment.forcedDisChgPowerPercentCmdEditText.getText().toString().trim(), LocalSetFragment.this.forcedDisChgPowerPercentCmdButton);
            }
        });
        ToggleButton toggleButton11 = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgFunctionButton);
        this.forcedDisChgFunctionButton = toggleButton11;
        toggleButton11.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.69
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runControlRemoteWrite("FUNC_FORCED_DISCHG_EN", localSetFragment.forcedDisChgFunctionButton);
            }
        });
        this.forcedDisChgPowerCmdEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgPowerCmdEditText);
        Button button56 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgPowerCmdButton);
        this.forcedDisChgPowerCmdButton = button56;
        button56.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.70
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_FORCED_DISCHG_POWER_CMD", localSetFragment.forcedDisChgPowerCmdEditText.getText().toString().trim(), LocalSetFragment.this.forcedDisChgPowerCmdButton);
            }
        });
        this.forcedDisChgSocLimitEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgSocLimitEditText);
        Button button57 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChgSocLimitButton);
        this.forcedDisChgSocLimitButton = button57;
        button57.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.71
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_FORCED_DISCHG_SOC_LIMIT", localSetFragment.forcedDisChgSocLimitEditText.getText().toString().trim(), LocalSetFragment.this.forcedDisChgSocLimitButton);
            }
        });
        this.forcedDisChargeStartHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartHourEditText);
        this.forcedDisChargeStartMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartMinuteEditText);
        Button button58 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartTimeButton);
        this.forcedDisChargeStartTimeButton = button58;
        button58.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.72
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_START_TIME", localSetFragment.forcedDisChargeStartHourEditText, LocalSetFragment.this.forcedDisChargeStartMinuteEditText, LocalSetFragment.this.forcedDisChargeStartTimeButton);
            }
        });
        this.forcedDisChargeEndHourEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndHourEditText);
        this.forcedDisChargeEndMinuteEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndMinuteEditText);
        Button button59 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndTimeButton);
        this.forcedDisChargeEndTimeButton = button59;
        button59.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.73
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_END_TIME", localSetFragment.forcedDisChargeEndHourEditText, LocalSetFragment.this.forcedDisChargeEndMinuteEditText, LocalSetFragment.this.forcedDisChargeEndTimeButton);
            }
        });
        this.forcedDisChargeStartHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartHour1EditText);
        this.forcedDisChargeStartMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartMinute1EditText);
        Button button60 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartTime1Button);
        this.forcedDisChargeStartTime1Button = button60;
        button60.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.74
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_START_TIME_1", localSetFragment.forcedDisChargeStartHour1EditText, LocalSetFragment.this.forcedDisChargeStartMinute1EditText, LocalSetFragment.this.forcedDisChargeStartTime1Button);
            }
        });
        this.forcedDisChargeEndHour1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndHour1EditText);
        this.forcedDisChargeEndMinute1EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndMinute1EditText);
        Button button61 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndTime1Button);
        this.forcedDisChargeEndTime1Button = button61;
        button61.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.75
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_END_TIME_1", localSetFragment.forcedDisChargeEndHour1EditText, LocalSetFragment.this.forcedDisChargeEndMinute1EditText, LocalSetFragment.this.forcedDisChargeEndTime1Button);
            }
        });
        this.forcedDisChargeStartHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartHour2EditText);
        this.forcedDisChargeStartMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartMinute2EditText);
        Button button62 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeStartTime2Button);
        this.forcedDisChargeStartTime2Button = button62;
        button62.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.76
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_START_TIME_2", localSetFragment.forcedDisChargeStartHour2EditText, LocalSetFragment.this.forcedDisChargeStartMinute2EditText, LocalSetFragment.this.forcedDisChargeStartTime2Button);
            }
        });
        this.forcedDisChargeEndHour2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndHour2EditText);
        this.forcedDisChargeEndMinute2EditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndMinute2EditText);
        Button button63 = (Button) inflate.findViewById(R.id.fragment_remote_set_forcedDisChargeEndTime2Button);
        this.forcedDisChargeEndTime2Button = button63;
        button63.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.77
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runTimeRemoteWrite("HOLD_FORCED_DISCHARGE_END_TIME_2", localSetFragment.forcedDisChargeEndHour2EditText, LocalSetFragment.this.forcedDisChargeEndMinute2EditText, LocalSetFragment.this.forcedDisChargeEndTime2Button);
            }
        });
        this.leadAcidDischargeCutOffVoltEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_leadAcidDischargeCutOffVoltEditText);
        Button button64 = (Button) inflate.findViewById(R.id.fragment_remote_set_leadAcidDischargeCutOffVoltButton);
        this.leadAcidDischargeCutOffVoltButton = button64;
        button64.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.78
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT", localSetFragment.leadAcidDischargeCutOffVoltEditText.getText().toString().trim(), LocalSetFragment.this.leadAcidDischargeCutOffVoltButton);
            }
        });
        this.eodEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_eodEditText);
        Button button65 = (Button) inflate.findViewById(R.id.fragment_remote_set_eodButton);
        this.eodButton = button65;
        button65.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.79
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_DISCHG_CUT_OFF_SOC_EOD", localSetFragment.eodEditText.getText().toString().trim(), LocalSetFragment.this.eodButton);
            }
        });
        this.offGridDischargeCutoffSocEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_offGridDischargeCutoffSocEditText);
        Button button66 = (Button) inflate.findViewById(R.id.fragment_remote_set_offGridDischargeCutoffSocButton);
        this.offGridDischargeCutoffSocButton = button66;
        button66.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.80
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_SOC_LOW_LIMIT_EPS_DISCHG", localSetFragment.offGridDischargeCutoffSocEditText.getText().toString().trim(), LocalSetFragment.this.offGridDischargeCutoffSocButton);
            }
        });
        this.dischargeCurrentLimitEditText = (EditText) inflate.findViewById(R.id.fragment_remote_set_dischargeCurrentLimitEditText);
        Button button67 = (Button) inflate.findViewById(R.id.fragment_remote_set_dischargeCurrentLimitButton);
        this.dischargeCurrentLimitButton = button67;
        button67.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.81
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runNormalRemoteWrite("HOLD_LEAD_ACID_DISCHARGE_RATE", localSetFragment.dischargeCurrentLimitEditText.getText().toString().trim(), LocalSetFragment.this.dischargeCurrentLimitButton);
            }
        });
        this.datalogSnEditText = (EditText) inflate.findViewById(R.id.fragment_local_set_datalogSnEditText);
        this.pinEditText = (EditText) inflate.findViewById(R.id.fragment_local_set_pinEditText);
        Button button68 = (Button) inflate.findViewById(R.id.fragment_local_set_datalogSnButton);
        this.datalogSnButton = button68;
        button68.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.82
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = LocalSetFragment.this.datalogSnEditText.getText().toString();
                String obj2 = LocalSetFragment.this.pinEditText.getText().toString();
                if (Tool.isEmpty(obj) || Tool.isEmpty(obj2)) {
                    Toast.makeText(LocalSetFragment.this.getActivity().getApplicationContext(), R.string.page_maintain_remote_set_alert_param_empty, 1).show();
                    return;
                }
                if (obj.length() != 10) {
                    Toast.makeText(LocalSetFragment.this.getActivity().getApplicationContext(), R.string.page_register_error_datalogSn_length, 1).show();
                    return;
                }
                if (PinTool.verifyDatalog(obj, obj2)) {
                    try {
                        LocalSetFragment.this.runDatalogParamWrite(1, obj.getBytes("ISO-8859-1"), LocalSetFragment.this.datalogSnButton);
                        return;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Toast.makeText(LocalSetFragment.this.getActivity().getApplicationContext(), R.string.page_register_error_check_code_not_match, 1).show();
            }
        });
        Button button69 = (Button) inflate.findViewById(R.id.fragment_local_set_all2DefaultButton);
        this.all2DefaultButton = button69;
        button69.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.83
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment.this.runNormalRemoteWrite("ALL_TO_DEFAULT", String.valueOf(2), LocalSetFragment.this.all2DefaultButton);
            }
        });
        Button button70 = (Button) inflate.findViewById(R.id.fragment_local_set_reset2FactoryButton);
        this.reset2FactoryButton = button70;
        button70.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.84
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalSetFragment localSetFragment = LocalSetFragment.this;
                localSetFragment.runDatalogParamWrite(3, new byte[]{JSONB.Constants.BC_OBJECT_END}, localSetFragment.reset2FactoryButton);
            }
        });
        this.applicationSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_application_set_layout);
        this.applicationSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_application_set_textView);
        this.applicationSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_application_set_toggleButton);
        this.applicationSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_application_set_paramLayout);
        this.applicationSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.85
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.applicationSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.applicationSetParamLayout.setVisibility(0);
                } else {
                    LocalSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.applicationSetParamLayout.setVisibility(8);
                }
            }
        });
        this.applicationSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.86
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.applicationSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.applicationSetActionToggleButton.setChecked(false);
                    LocalSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.applicationSetParamLayout.setVisibility(8);
                } else {
                    LocalSetFragment.this.applicationSetActionToggleButton.setChecked(true);
                    LocalSetFragment.this.applicationSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.applicationSetParamLayout.setVisibility(0);
                }
            }
        });
        this.gridConnectSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_grid_connect_set_layout);
        this.gridConnectSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_grid_connect_set_textView);
        this.gridConnectSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_grid_connect_set_toggleButton);
        this.gridConnectSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_grid_connect_set_paramLayout);
        this.gridConnectSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.87
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.gridConnectSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.gridConnectSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.gridConnectSetParamLayout.setVisibility(0);
                } else {
                    LocalSetFragment.this.gridConnectSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.gridConnectSetParamLayout.setVisibility(8);
                }
            }
        });
        this.gridConnectSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.88
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.gridConnectSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.gridConnectSetActionToggleButton.setChecked(false);
                    LocalSetFragment.this.gridConnectSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.gridConnectSetParamLayout.setVisibility(8);
                } else {
                    LocalSetFragment.this.gridConnectSetActionToggleButton.setChecked(true);
                    LocalSetFragment.this.gridConnectSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.gridConnectSetParamLayout.setVisibility(0);
                }
            }
        });
        this.chargeSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_layout);
        this.chargeSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_textView);
        this.chargeSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_toggleButton);
        this.chargeSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_charge_set_paramLayout);
        this.chargeSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.89
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.chargeSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.chargeSetParamLayout.setVisibility(0);
                } else {
                    LocalSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.chargeSetParamLayout.setVisibility(8);
                }
            }
        });
        this.chargeSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.90
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.chargeSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.chargeSetActionToggleButton.setChecked(false);
                    LocalSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.chargeSetParamLayout.setVisibility(8);
                } else {
                    LocalSetFragment.this.chargeSetActionToggleButton.setChecked(true);
                    LocalSetFragment.this.chargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.chargeSetParamLayout.setVisibility(0);
                }
            }
        });
        this.dischargeSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_layout);
        this.dischargeSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_textView);
        this.dischargeSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_toggleButton);
        this.dischargeSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_discharge_set_paramLayout);
        this.dischargeSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.91
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.dischargeSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.dischargeSetParamLayout.setVisibility(0);
                } else {
                    LocalSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.dischargeSetParamLayout.setVisibility(8);
                }
            }
        });
        this.dischargeSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.92
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.dischargeSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.dischargeSetActionToggleButton.setChecked(false);
                    LocalSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.dischargeSetParamLayout.setVisibility(8);
                } else {
                    LocalSetFragment.this.dischargeSetActionToggleButton.setChecked(true);
                    LocalSetFragment.this.dischargeSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.dischargeSetParamLayout.setVisibility(0);
                }
            }
        });
        this.resetSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_reset_set_layout);
        this.resetSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_reset_set_textView);
        this.resetSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_reset_set_toggleButton);
        this.resetSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_reset_set_paramLayout);
        this.resetSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.93
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.resetSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.resetSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.resetSetParamLayout.setVisibility(0);
                } else {
                    LocalSetFragment.this.resetSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.resetSetParamLayout.setVisibility(8);
                }
            }
        });
        this.resetSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.94
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.resetSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.resetSetActionToggleButton.setChecked(false);
                    LocalSetFragment.this.resetSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.resetSetParamLayout.setVisibility(8);
                } else {
                    LocalSetFragment.this.resetSetActionToggleButton.setChecked(true);
                    LocalSetFragment.this.resetSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.resetSetParamLayout.setVisibility(0);
                }
            }
        });
        this.wifiModuleSetActionLayout = (ConstraintLayout) inflate.findViewById(R.id.fragment_remote_set_label_wifi_module_set_layout);
        this.wifiModuleSetActionTextView = (TextView) inflate.findViewById(R.id.fragment_remote_set_label_wifi_module_set_textView);
        this.wifiModuleSetActionToggleButton = (ToggleButton) inflate.findViewById(R.id.fragment_remote_set_label_wifi_module_set_toggleButton);
        this.wifiModuleSetParamLayout = (LinearLayout) inflate.findViewById(R.id.fragment_remote_set_label_wifi_module_set_paramLayout);
        this.wifiModuleSetActionToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.95
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.wifiModuleSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.wifiModuleSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.wifiModuleSetParamLayout.setVisibility(0);
                } else {
                    LocalSetFragment.this.wifiModuleSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.wifiModuleSetParamLayout.setVisibility(8);
                }
            }
        });
        this.wifiModuleSetActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.96
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LocalSetFragment.this.wifiModuleSetActionToggleButton.isChecked()) {
                    LocalSetFragment.this.wifiModuleSetActionToggleButton.setChecked(false);
                    LocalSetFragment.this.wifiModuleSetActionTextView.setText(R.string.phrase_button_expand);
                    LocalSetFragment.this.wifiModuleSetParamLayout.setVisibility(8);
                } else {
                    LocalSetFragment.this.wifiModuleSetActionToggleButton.setChecked(true);
                    LocalSetFragment.this.wifiModuleSetActionTextView.setText(R.string.phrase_button_collapse);
                    LocalSetFragment.this.wifiModuleSetParamLayout.setVisibility(0);
                }
            }
        });
        Button button71 = (Button) inflate.findViewById(R.id.fragment_local_set_updateFirmwareButton);
        this.updateFirmwareButton = button71;
        button71.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocalSetFragment.this.m338x11b93f9d(view);
            }
        });
        this.created = true;
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment$12, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass12 implements View.OnClickListener {
        AnonymousClass12() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean isChecked = LocalSetFragment.this.setToStandbyFunctionButton.isChecked();
            AlertDialog.Builder builder = new AlertDialog.Builder(LocalActivity.instance);
            builder.setTitle(isChecked ? R.string.phrase_func_param_normaly : R.string.phrase_func_param_standby).setIcon(android.R.drawable.ic_dialog_info).setMessage(LocalSetFragment.this.getString(isChecked ? R.string.phrase_func_text_normal : R.string.phrase_func_text_standby) + " " + ((Object) LocalSetFragment.this.inverterSnTextView.getText())).setPositiveButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment$12$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    LocalSetFragment.AnonymousClass12.this.m339x40807a59(dialogInterface, i);
                }
            }).setNegativeButton(R.string.phrase_button_cancel, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment$12$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    LocalSetFragment.AnonymousClass12.this.m340xd4bee9f8(dialogInterface, i);
                }
            });
            builder.show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-nfcx-luxinvpower-view-local-fragment-LocalSetFragment$12, reason: not valid java name */
        public /* synthetic */ void m339x40807a59(DialogInterface dialogInterface, int i) {
            LocalSetFragment localSetFragment = LocalSetFragment.this;
            localSetFragment.runControlRemoteWrite("FUNC_SET_TO_STANDBY", localSetFragment.setToStandbyFunctionButton);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$1$com-nfcx-luxinvpower-view-local-fragment-LocalSetFragment$12, reason: not valid java name */
        public /* synthetic */ void m340xd4bee9f8(DialogInterface dialogInterface, int i) {
            LocalSetFragment.this.setToStandbyFunctionButton.setChecked(!LocalSetFragment.this.setToStandbyFunctionButton.isChecked());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$0$com-nfcx-luxinvpower-view-local-fragment-LocalSetFragment, reason: not valid java name */
    public /* synthetic */ void m338x11b93f9d(View view) {
        Intent intent = new Intent(view.getContext(), (Class<?>) UpdateFirmwareActivity.class);
        intent.putExtra(Constants.LOCAL_CONNECT_TYPE, this.localConnect.getConnectType());
        startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        refreshFragmentParams();
    }

    private void initFlowChartByDeviceType() {
        Inverter inverter = this.localConnect.getInverter();
        System.out.println("LuxPower - inverter = " + inverter);
        if (inverter == null || inverter.getDeviceType() == null) {
            return;
        }
        System.out.println("LuxPower - inverter.deviceType = " + inverter.getDeviceType());
        if (inverter.isSnaSeries() || inverter.isType6()) {
            ((LocalActivity) getActivity()).switchLocalSetFragment(inverter.getDeviceTypeValue());
            return;
        }
        if (inverter.isAcCharger()) {
            this.pvInputModeLayout.setVisibility(8);
            this.startPvVoltLayout.setVisibility(8);
            this.pvGridOffFunctionLayout.setVisibility(8);
            this.forcedChgFunctionLayout.setVisibility(8);
            this.forcedChgPowerCmdLayout.setVisibility(8);
            this.forcedChgSocLimitLayout.setVisibility(8);
            this.forcedChargeStartTimeLayout.setVisibility(8);
            this.forcedChargeEndTimeLayout.setVisibility(8);
            this.forcedChargeStartTime1Layout.setVisibility(8);
            this.forcedChargeEndTime1Layout.setVisibility(8);
            this.forcedChargeStartTime2Layout.setVisibility(8);
            this.forcedChargeEndTime2Layout.setVisibility(8);
            return;
        }
        this.pvInputModeLayout.setVisibility(0);
        this.startPvVoltLayout.setVisibility(0);
        this.pvGridOffFunctionLayout.setVisibility(0);
        this.forcedChgFunctionLayout.setVisibility(0);
        this.forcedChgPowerCmdLayout.setVisibility(0);
        this.forcedChgSocLimitLayout.setVisibility(0);
        this.forcedChargeStartTimeLayout.setVisibility(0);
        this.forcedChargeEndTimeLayout.setVisibility(0);
        this.forcedChargeStartTime1Layout.setVisibility(0);
        this.forcedChargeEndTime1Layout.setVisibility(0);
        this.forcedChargeStartTime2Layout.setVisibility(0);
        this.forcedChargeEndTime2Layout.setVisibility(0);
    }

    public void refreshFragmentParams() {
        if (this.created) {
            Inverter inverter = this.localConnect.getInverter();
            this.datalogSnTextView.setText(inverter != null ? inverter.getDatalogSn() : "");
            this.inverterSnTextView.setText(inverter != null ? inverter.getSerialNum() : "");
            initFlowChartByDeviceType();
            this.updateFirmwareButton.setEnabled((this.localConnect.getInverter() == null || Tool.isEmpty(this.localConnect.getInverter().getFwCode())) ? false : true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - LocalSetFragment onResume...");
        refreshFragmentParams();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        System.out.println("LuxPower - LocalSetFragment onDestroy...");
    }

    public void clearFragmentData() {
        if (this.created) {
            this.timeDateEditText.setText("");
            this.timeTimeEditText.setText("");
            this.comAddrEditText.setText("");
            this.pvInputModeSpinner.setSelection(0);
            this.startPvVoltEditText.setText("");
            this.ctSampleRatioSpinner.setSelection(0);
            this.pvctSampleTypeSpinner.setSelection(0);
            this.pvctSampleRatioSpinner.setSelection(0);
            this.setToStandbyFunctionButton.setChecked(false);
            this.epsFunctionButton.setChecked(false);
            this.swSeamlesslyEnButton.setChecked(false);
            this.runWithoutGridFunctionButton.setChecked(false);
            this.pvGridOffFunctionButton.setChecked(false);
            this.feedInGridFunctionButton.setChecked(false);
            this.feedInGridPowerPercentEditText.setText("");
            this.masterOrSlaveSpinner.setSelection(0);
            this.readComposedPhaseSpinner.setSelection(0);
            this.setComposedPhaseSpinner.setSelection(0);
            this.microGridFunctionButton.setChecked(false);
            this.batterySharedFunctionButton.setChecked(false);
            this.maxAcInputPowerEditText.setText("");
            this.connectTimeEditText.setText("");
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
            this.gridVoltMovAvgHighEditText.setText("");
            this.gridFreqLimit1LowEditText.setText("");
            this.gridFreqLimit1HighEditText.setText("");
            this.gridFreqLimit2LowEditText.setText("");
            this.gridFreqLimit2HighEditText.setText("");
            this.gridFreqLimit3LowEditText.setText("");
            this.gridFreqLimit3HighEditText.setText("");
            this.chargePowerPercentCmdEditText.setText("");
            this.equalizationVoltageEditText.setText("");
            this.equalizationPeriodEditText.setText("");
            this.equalizationTimeEditText.setText("");
            this.acChargeFunctionButton.setChecked(false);
            this.acChargePowerCmdEditText.setText("");
            this.acChargeSocLimitEditText.setText("");
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
            this.forcedDisChgPowerPercentCmdEditText.setText("");
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
            this.leadAcidDischargeCutOffVoltEditText.setText("");
            this.eodEditText.setText("");
            this.offGridDischargeCutoffSocEditText.setText("");
            this.dischargeCurrentLimitEditText.setText("");
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
    public void runControlRemoteWrite(String str, ToggleButton toggleButton) {
        RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
        remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.CONTROL);
        remoteWriteInfo.setFunctionParam(str);
        remoteWriteInfo.setFunctionToggleButtonChecked(toggleButton.isChecked());
        remoteWriteInfo.setFunctionToggleButton(toggleButton);
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
        private LocalSetFragment fragment;

        public ReadDatalogParamTask(LocalSetFragment localSetFragment) {
            this.fragment = localSetFragment;
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
        private LocalSetFragment fragment;

        public ReadMultiParamTask(LocalSetFragment localSetFragment) {
            this.fragment = localSetFragment;
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
        /* JADX WARN: Can't wrap try/catch for region: R(170:24|(8:467|468|469|470|471|472|473|474)(1:26)|(37:27|28|(1:30)|31|(2:33|(1:38)(1:37))|39|(1:41)|42|(2:44|(1:49)(1:48))|50|(2:52|(1:57)(1:56))|58|(2:60|(1:65)(1:64))|66|(1:68)|69|(1:71)|72|(1:74)|75|76|77|(2:455|456)|79|80|81|(2:447|448)|83|84|85|(5:429|430|431|432|433)(1:87)|88|89|90|(5:415|416|417|418|419)(1:92)|93|(6:94|95|(5:401|402|403|404|405)(1:97)|98|99|100))|(6:364|365|366|(4:386|387|388|389)(2:369|370)|371|(169:375|376|377|378|104|105|106|(1:108)|109|110|111|(1:113)|114|115|116|(4:118|119|120|121)(1:352)|122|(1:124)|125|(1:127)|128|(1:130)|131|(1:133)|134|(1:136)|137|(1:139)|140|(1:142)|143|(1:145)|146|(1:148)|149|(1:151)|152|(1:154)|155|(1:157)|158|(1:160)|161|(1:163)|164|(1:166)|167|(1:169)|170|(1:172)|173|(1:175)|176|(1:178)|179|(1:181)|182|(1:184)|185|(1:187)|188|(1:190)|191|(1:193)|194|(1:196)|197|(1:199)|200|(1:202)|203|(1:205)|206|(1:208)|209|(1:211)|212|(1:214)|215|(1:217)|218|(1:220)|221|(1:223)|224|(1:226)|227|(1:229)|230|(1:232)|233|(1:235)|236|(1:238)|239|(1:241)|242|(1:244)|245|(1:247)|248|(1:250)|251|(1:253)|254|(1:256)|257|(1:259)|260|(1:262)|263|(1:265)|266|(1:268)|269|(1:271)|272|(1:274)|275|(1:277)|278|(1:280)|281|(1:283)|284|(1:286)|287|(1:289)|290|(1:292)|293|(1:295)|296|(1:298)|299|(1:301)|302|(1:304)|305|(1:307)|308|(1:310)|311|(1:313)|314|(1:316)|317|(1:319)|320|(1:322)|323|(1:325)|326|(1:328)|329|(1:331)|332|(1:334)|335|(1:337)|338|(1:340)|341|(1:343)|344|(1:346)|347|(2:349|350)(1:351)|14)(1:374))(1:102)|103|104|105|106|(0)|109|110|111|(0)|114|115|116|(0)(0)|122|(0)|125|(0)|128|(0)|131|(0)|134|(0)|137|(0)|140|(0)|143|(0)|146|(0)|149|(0)|152|(0)|155|(0)|158|(0)|161|(0)|164|(0)|167|(0)|170|(0)|173|(0)|176|(0)|179|(0)|182|(0)|185|(0)|188|(0)|191|(0)|194|(0)|197|(0)|200|(0)|203|(0)|206|(0)|209|(0)|212|(0)|215|(0)|218|(0)|221|(0)|224|(0)|227|(0)|230|(0)|233|(0)|236|(0)|239|(0)|242|(0)|245|(0)|248|(0)|251|(0)|254|(0)|257|(0)|260|(0)|263|(0)|266|(0)|269|(0)|272|(0)|275|(0)|278|(0)|281|(0)|284|(0)|287|(0)|290|(0)|293|(0)|296|(0)|299|(0)|302|(0)|305|(0)|308|(0)|311|(0)|314|(0)|317|(0)|320|(0)|323|(0)|326|(0)|329|(0)|332|(0)|335|(0)|338|(0)|341|(0)|344|(0)|347|(0)(0)|14) */
        /* JADX WARN: Can't wrap try/catch for region: R(211:24|(8:467|468|469|470|471|472|473|474)(1:26)|27|28|(1:30)|31|(2:33|(1:38)(1:37))|39|(1:41)|42|(2:44|(1:49)(1:48))|50|(2:52|(1:57)(1:56))|58|(2:60|(1:65)(1:64))|66|(1:68)|69|(1:71)|72|(1:74)|75|76|77|(2:455|456)|79|80|81|(2:447|448)|83|84|85|(5:429|430|431|432|433)(1:87)|88|89|90|(5:415|416|417|418|419)(1:92)|93|94|95|(5:401|402|403|404|405)(1:97)|98|99|100|(6:364|365|366|(4:386|387|388|389)(2:369|370)|371|(169:375|376|377|378|104|105|106|(1:108)|109|110|111|(1:113)|114|115|116|(4:118|119|120|121)(1:352)|122|(1:124)|125|(1:127)|128|(1:130)|131|(1:133)|134|(1:136)|137|(1:139)|140|(1:142)|143|(1:145)|146|(1:148)|149|(1:151)|152|(1:154)|155|(1:157)|158|(1:160)|161|(1:163)|164|(1:166)|167|(1:169)|170|(1:172)|173|(1:175)|176|(1:178)|179|(1:181)|182|(1:184)|185|(1:187)|188|(1:190)|191|(1:193)|194|(1:196)|197|(1:199)|200|(1:202)|203|(1:205)|206|(1:208)|209|(1:211)|212|(1:214)|215|(1:217)|218|(1:220)|221|(1:223)|224|(1:226)|227|(1:229)|230|(1:232)|233|(1:235)|236|(1:238)|239|(1:241)|242|(1:244)|245|(1:247)|248|(1:250)|251|(1:253)|254|(1:256)|257|(1:259)|260|(1:262)|263|(1:265)|266|(1:268)|269|(1:271)|272|(1:274)|275|(1:277)|278|(1:280)|281|(1:283)|284|(1:286)|287|(1:289)|290|(1:292)|293|(1:295)|296|(1:298)|299|(1:301)|302|(1:304)|305|(1:307)|308|(1:310)|311|(1:313)|314|(1:316)|317|(1:319)|320|(1:322)|323|(1:325)|326|(1:328)|329|(1:331)|332|(1:334)|335|(1:337)|338|(1:340)|341|(1:343)|344|(1:346)|347|(2:349|350)(1:351)|14)(1:374))(1:102)|103|104|105|106|(0)|109|110|111|(0)|114|115|116|(0)(0)|122|(0)|125|(0)|128|(0)|131|(0)|134|(0)|137|(0)|140|(0)|143|(0)|146|(0)|149|(0)|152|(0)|155|(0)|158|(0)|161|(0)|164|(0)|167|(0)|170|(0)|173|(0)|176|(0)|179|(0)|182|(0)|185|(0)|188|(0)|191|(0)|194|(0)|197|(0)|200|(0)|203|(0)|206|(0)|209|(0)|212|(0)|215|(0)|218|(0)|221|(0)|224|(0)|227|(0)|230|(0)|233|(0)|236|(0)|239|(0)|242|(0)|245|(0)|248|(0)|251|(0)|254|(0)|257|(0)|260|(0)|263|(0)|266|(0)|269|(0)|272|(0)|275|(0)|278|(0)|281|(0)|284|(0)|287|(0)|290|(0)|293|(0)|296|(0)|299|(0)|302|(0)|305|(0)|308|(0)|311|(0)|314|(0)|317|(0)|320|(0)|323|(0)|326|(0)|329|(0)|332|(0)|335|(0)|338|(0)|341|(0)|344|(0)|347|(0)(0)|14) */
        /* JADX WARN: Code restructure failed: missing block: B:354:0x09e6, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:355:0x09e7, code lost:
        
            r18 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:357:0x09eb, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:358:0x09ec, code lost:
        
            r18 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:361:0x09ef, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:362:0x09f0, code lost:
        
            r18 = r2;
         */
        /* JADX WARN: Removed duplicated region for block: B:108:0x02d4 A[Catch: Exception -> 0x09ef, TRY_LEAVE, TryCatch #0 {Exception -> 0x09ef, blocks: (B:106:0x02ce, B:108:0x02d4), top: B:105:0x02ce }] */
        /* JADX WARN: Removed duplicated region for block: B:113:0x02e9 A[Catch: Exception -> 0x09eb, TRY_LEAVE, TryCatch #3 {Exception -> 0x09eb, blocks: (B:111:0x02e3, B:113:0x02e9), top: B:110:0x02e3 }] */
        /* JADX WARN: Removed duplicated region for block: B:118:0x02fe A[Catch: Exception -> 0x09e6, TRY_LEAVE, TryCatch #12 {Exception -> 0x09e6, blocks: (B:116:0x02f8, B:118:0x02fe), top: B:115:0x02f8 }] */
        /* JADX WARN: Removed duplicated region for block: B:124:0x0318 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:127:0x032f A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:130:0x0346 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:133:0x035d A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:136:0x0374 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:139:0x038b A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:142:0x03a2 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:145:0x03b9 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:148:0x03d0 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:151:0x03e7 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:154:0x03fe A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:157:0x0415 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:160:0x042c A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:163:0x0443 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:166:0x045a A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:169:0x0471 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:172:0x0488 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:175:0x049f A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:178:0x04b6 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:181:0x04cd A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:184:0x04e4 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:187:0x04fb A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:190:0x0512 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:193:0x0529 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:196:0x0540 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:199:0x0557 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:202:0x056e A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:205:0x0585 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:208:0x059c A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:211:0x05b3 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:214:0x05ca A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:217:0x05e1 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:220:0x05f8 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:223:0x060f A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:226:0x0626 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:229:0x063d A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:232:0x0654 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:235:0x066b A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:238:0x0682 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:241:0x0699 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:244:0x06b0 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:247:0x06c7 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:250:0x06de A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:253:0x06f5 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:256:0x070c A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:259:0x0723 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:262:0x073a A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:265:0x0751 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:268:0x0768 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:271:0x077f A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:274:0x0796 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:277:0x07ad A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:280:0x07c4 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:283:0x07db A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:286:0x07f2 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:289:0x0809 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:292:0x0820 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:295:0x0837 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:298:0x084e A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:301:0x0865 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:304:0x087c A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:307:0x0893 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:310:0x08aa A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:313:0x08c1 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:316:0x08d8 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:319:0x08ef A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:322:0x0906 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:325:0x091d A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:328:0x0934 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:331:0x094b A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:334:0x0962 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:337:0x0979 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:340:0x0990 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:343:0x09a7 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:346:0x09be A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:349:0x09d5 A[Catch: Exception -> 0x0a4d, TryCatch #14 {Exception -> 0x0a4d, blocks: (B:121:0x0306, B:122:0x0310, B:124:0x0318, B:125:0x0327, B:127:0x032f, B:128:0x033e, B:130:0x0346, B:131:0x0355, B:133:0x035d, B:134:0x036c, B:136:0x0374, B:137:0x0383, B:139:0x038b, B:140:0x039a, B:142:0x03a2, B:143:0x03b1, B:145:0x03b9, B:146:0x03c8, B:148:0x03d0, B:149:0x03df, B:151:0x03e7, B:152:0x03f6, B:154:0x03fe, B:155:0x040d, B:157:0x0415, B:158:0x0424, B:160:0x042c, B:161:0x043b, B:163:0x0443, B:164:0x0452, B:166:0x045a, B:167:0x0469, B:169:0x0471, B:170:0x0480, B:172:0x0488, B:173:0x0497, B:175:0x049f, B:176:0x04ae, B:178:0x04b6, B:179:0x04c5, B:181:0x04cd, B:182:0x04dc, B:184:0x04e4, B:185:0x04f3, B:187:0x04fb, B:188:0x050a, B:190:0x0512, B:191:0x0521, B:193:0x0529, B:194:0x0538, B:196:0x0540, B:197:0x054f, B:199:0x0557, B:200:0x0566, B:202:0x056e, B:203:0x057d, B:205:0x0585, B:206:0x0594, B:208:0x059c, B:209:0x05ab, B:211:0x05b3, B:212:0x05c2, B:214:0x05ca, B:215:0x05d9, B:217:0x05e1, B:218:0x05f0, B:220:0x05f8, B:221:0x0607, B:223:0x060f, B:224:0x061e, B:226:0x0626, B:227:0x0635, B:229:0x063d, B:230:0x064c, B:232:0x0654, B:233:0x0663, B:235:0x066b, B:236:0x067a, B:238:0x0682, B:239:0x0691, B:241:0x0699, B:242:0x06a8, B:244:0x06b0, B:245:0x06bf, B:247:0x06c7, B:248:0x06d6, B:250:0x06de, B:251:0x06ed, B:253:0x06f5, B:254:0x0704, B:256:0x070c, B:257:0x071b, B:259:0x0723, B:260:0x0732, B:262:0x073a, B:263:0x0749, B:265:0x0751, B:266:0x0760, B:268:0x0768, B:269:0x0777, B:271:0x077f, B:272:0x078e, B:274:0x0796, B:275:0x07a5, B:277:0x07ad, B:278:0x07bc, B:280:0x07c4, B:281:0x07d3, B:283:0x07db, B:284:0x07ea, B:286:0x07f2, B:287:0x0801, B:289:0x0809, B:290:0x0818, B:292:0x0820, B:293:0x082f, B:295:0x0837, B:296:0x0846, B:298:0x084e, B:299:0x085d, B:301:0x0865, B:302:0x0874, B:304:0x087c, B:305:0x088b, B:307:0x0893, B:308:0x08a2, B:310:0x08aa, B:311:0x08b9, B:313:0x08c1, B:314:0x08d0, B:316:0x08d8, B:317:0x08e7, B:319:0x08ef, B:320:0x08fe, B:322:0x0906, B:323:0x0915, B:325:0x091d, B:326:0x092c, B:328:0x0934, B:329:0x0943, B:331:0x094b, B:332:0x095a, B:334:0x0962, B:335:0x0971, B:337:0x0979, B:338:0x0988, B:340:0x0990, B:341:0x099f, B:343:0x09a7, B:344:0x09b6, B:346:0x09be, B:347:0x09cd, B:349:0x09d5, B:5:0x0a33), top: B:120:0x0306 }] */
        /* JADX WARN: Removed duplicated region for block: B:351:0x0a6d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:352:0x030e  */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onProgressUpdate(org.json.JSONObject... r29) {
            /*
                Method dump skipped, instructions count: 2697
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.ReadMultiParamTask.onProgressUpdate(org.json.JSONObject[]):void");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            super.onPostExecute((ReadMultiParamTask) r2);
            this.fragment.readAllButton.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class WriteParamTask extends AsyncTask<RemoteWriteInfo, Void, JSONObject> {
        private LocalSetFragment fragment;
        private RemoteWriteInfo remoteWriteInfo;

        public WriteParamTask(LocalSetFragment localSetFragment) {
            this.fragment = localSetFragment;
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
                int i = AnonymousClass97.$SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[remoteWriteInfo.getRemoteWriteType().ordinal()];
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
    /* renamed from: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment$97, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass97 {
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
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.getBitSizeByBitParam(java.lang.String):java.lang.Integer");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getStartRegisterByParam(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2110570028:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE")) {
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
            case -2092080778:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR")) {
                    c = 2;
                    break;
                }
                break;
            case -2091729313:
                if (str.equals("HOLD_AC_CHARGE_END_TIME")) {
                    c = 3;
                    break;
                }
                break;
            case -2064663285:
                if (str.equals("HOLD_SET_COMPOSED_PHASE")) {
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
            case -1910210817:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_TIME_1")) {
                    c = 7;
                    break;
                }
                break;
            case -1910210816:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_TIME_2")) {
                    c = '\b';
                    break;
                }
                break;
            case -1876698434:
                if (str.equals("HOLD_FORCED_DISCHG_SOC_LIMIT")) {
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
            case -1662706451:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE_1")) {
                    c = 11;
                    break;
                }
                break;
            case -1662706450:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE_2")) {
                    c = '\f';
                    break;
                }
                break;
            case -1657690225:
                if (str.equals("HOLD_COM_ADDR")) {
                    c = '\r';
                    break;
                }
                break;
            case -1612429665:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR_1")) {
                    c = 14;
                    break;
                }
                break;
            case -1612429664:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR_2")) {
                    c = 15;
                    break;
                }
                break;
            case -1563900533:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR")) {
                    c = 16;
                    break;
                }
                break;
            case -1563549068:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_TIME")) {
                    c = 17;
                    break;
                }
                break;
            case -1551617594:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR")) {
                    c = 18;
                    break;
                }
                break;
            case -1551266129:
                if (str.equals("HOLD_FORCED_CHARGE_START_TIME")) {
                    c = 19;
                    break;
                }
                break;
            case -1524255375:
                if (str.equals("HOLD_PV_INPUT_MODE")) {
                    c = 20;
                    break;
                }
                break;
            case -1475031011:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE")) {
                    c = 21;
                    break;
                }
                break;
            case -1367869989:
                if (str.equals("ALL_TO_DEFAULT")) {
                    c = 22;
                    break;
                }
                break;
            case -1352201891:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_HIGH")) {
                    c = 23;
                    break;
                }
                break;
            case -1345314128:
                if (str.equals("HOLD_EQUALIZATION_PERIOD")) {
                    c = 24;
                    break;
                }
                break;
            case -1323572740:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_HIGH")) {
                    c = 25;
                    break;
                }
                break;
            case -1294943589:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_HIGH")) {
                    c = 26;
                    break;
                }
                break;
            case -1282654362:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_HIGH")) {
                    c = 27;
                    break;
                }
                break;
            case -1274671800:
                if (str.equals("HOLD_FORCED_CHARGE_END_TIME_1")) {
                    c = 28;
                    break;
                }
                break;
            case -1274671799:
                if (str.equals("HOLD_FORCED_CHARGE_END_TIME_2")) {
                    c = 29;
                    break;
                }
                break;
            case -1254025211:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_HIGH")) {
                    c = 30;
                    break;
                }
                break;
            case -1225396060:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_HIGH")) {
                    c = 31;
                    break;
                }
                break;
            case -1119226968:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE_1")) {
                    c = ' ';
                    break;
                }
                break;
            case -1119226967:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE_2")) {
                    c = '!';
                    break;
                }
                break;
            case -1033230202:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE_1")) {
                    c = '\"';
                    break;
                }
                break;
            case -1033230201:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE_2")) {
                    c = '#';
                    break;
                }
                break;
            case -917268805:
                if (str.equals("HOLD_FORCED_DISCHG_POWER_CMD")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case -875057049:
                if (str.equals("HOLD_GRID_FREQ_CONN_HIGH")) {
                    c = '%';
                    break;
                }
                break;
            case -823881906:
                if (str.equals("HOLD_MAX_AC_INPUT_POWER")) {
                    c = '&';
                    break;
                }
                break;
            case -750853128:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR_1")) {
                    c = '\'';
                    break;
                }
                break;
            case -750853127:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR_2")) {
                    c = '(';
                    break;
                }
                break;
            case -613454474:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE")) {
                    c = ')';
                    break;
                }
                break;
            case -595561232:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_LOW")) {
                    c = '*';
                    break;
                }
                break;
            case -594637711:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_LOW")) {
                    c = '+';
                    break;
                }
                break;
            case -593714190:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_LOW")) {
                    c = ',';
                    break;
                }
                break;
            case -573009974:
                if (str.equals("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT")) {
                    c = '-';
                    break;
                }
                break;
            case -550997124:
                if (str.equals("HOLD_EQUALIZATION_TIME")) {
                    c = '.';
                    break;
                }
                break;
            case -444930136:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR_1")) {
                    c = '/';
                    break;
                }
                break;
            case -444930135:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR_2")) {
                    c = '0';
                    break;
                }
                break;
            case -431364128:
                if (str.equals("HOLD_FORCED_CHG_SOC_LIMIT")) {
                    c = '1';
                    break;
                }
                break;
            case -413095263:
                if (str.equals("HOLD_FORCED_CHARGE_START_TIME_1")) {
                    c = '2';
                    break;
                }
                break;
            case -413095262:
                if (str.equals("HOLD_FORCED_CHARGE_START_TIME_2")) {
                    c = '3';
                    break;
                }
                break;
            case -384118065:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR")) {
                    c = '4';
                    break;
                }
                break;
            case -383766600:
                if (str.equals("HOLD_AC_CHARGE_START_TIME")) {
                    c = '5';
                    break;
                }
                break;
            case -307531482:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE")) {
                    c = '6';
                    break;
                }
                break;
            case -276744627:
                if (str.equals("HOLD_TIME")) {
                    c = '7';
                    break;
                }
                break;
            case -233655236:
                if (str.equals("HOLD_LEAD_ACID_DISCHARGE_RATE")) {
                    c = '8';
                    break;
                }
                break;
            case -165590897:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE_1")) {
                    c = '9';
                    break;
                }
                break;
            case -165590896:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE_2")) {
                    c = ':';
                    break;
                }
                break;
            case -135057669:
                if (str.equals("HOLD_DISCHG_POWER_PERCENT_CMD")) {
                    c = ';';
                    break;
                }
                break;
            case -107172271:
                if (str.equals("HOLD_AC_CHARGE_END_TIME_1")) {
                    c = Typography.less;
                    break;
                }
                break;
            case -107172270:
                if (str.equals("HOLD_AC_CHARGE_END_TIME_2")) {
                    c = '=';
                    break;
                }
                break;
            case 127930925:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR")) {
                    c = Typography.greater;
                    break;
                }
                break;
            case 128282390:
                if (str.equals("HOLD_FORCED_CHARGE_END_TIME")) {
                    c = '?';
                    break;
                }
                break;
            case 229729985:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR_1")) {
                    c = '@';
                    break;
                }
                break;
            case 229729986:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR_2")) {
                    c = 'A';
                    break;
                }
                break;
            case 265183773:
                if (str.equals("HOLD_START_PV_VOLT")) {
                    c = 'B';
                    break;
                }
                break;
            case 313840816:
                if (str.equals("HOLD_GRID_VOLT_CONN_HIGH")) {
                    c = 'C';
                    break;
                }
                break;
            case 330144381:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR_1")) {
                    c = 'D';
                    break;
                }
                break;
            case 330144382:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR_2")) {
                    c = 'E';
                    break;
                }
                break;
            case 367128639:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE")) {
                    c = 'F';
                    break;
                }
                break;
            case 390956452:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR")) {
                    c = 'G';
                    break;
                }
                break;
            case 391307917:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_TIME")) {
                    c = 'H';
                    break;
                }
                break;
            case 467543035:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE")) {
                    c = 'I';
                    break;
                }
                break;
            case 528065501:
                if (str.equals("HOLD_FORCED_CHG_POWER_CMD")) {
                    c = 'J';
                    break;
                }
                break;
            case 567487850:
                if (str.equals("HOLD_AC_CHARGE_START_TIME_1")) {
                    c = 'K';
                    break;
                }
                break;
            case 567487851:
                if (str.equals("HOLD_AC_CHARGE_START_TIME_2")) {
                    c = 'L';
                    break;
                }
                break;
            case 574683163:
                if (str.equals("HOLD_SET_MASTER_OR_SLAVE")) {
                    c = 'M';
                    break;
                }
                break;
            case 623306801:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE_1")) {
                    c = 'N';
                    break;
                }
                break;
            case 623306802:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE_2")) {
                    c = 'O';
                    break;
                }
                break;
            case 667902246:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_TIME_1")) {
                    c = 'P';
                    break;
                }
                break;
            case 667902247:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_TIME_2")) {
                    c = 'Q';
                    break;
                }
                break;
            case 767099658:
                if (str.equals("HOLD_DISCHG_CUT_OFF_SOC_EOD")) {
                    c = 'R';
                    break;
                }
                break;
            case 814992216:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE_1")) {
                    c = 'S';
                    break;
                }
                break;
            case 814992217:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE_2")) {
                    c = 'T';
                    break;
                }
                break;
            case 894260725:
                if (str.equals("HOLD_AC_CHARGE_SOC_LIMIT")) {
                    c = 'U';
                    break;
                }
                break;
            case 946846866:
                if (str.equals("HOLD_SOC_LOW_LIMIT_EPS_DISCHG")) {
                    c = 'V';
                    break;
                }
                break;
            case 1039780741:
                if (str.equals("HOLD_FLOATING_VOLTAGE")) {
                    c = 'W';
                    break;
                }
                break;
            case 1118506598:
                if (str.equals("HOLD_GRID_VOLT_CONN_LOW")) {
                    c = 'X';
                    break;
                }
                break;
            case 1203310617:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_LOW")) {
                    c = 'Y';
                    break;
                }
                break;
            case 1204234138:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_LOW")) {
                    c = 'Z';
                    break;
                }
                break;
            case 1205157659:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_LOW")) {
                    c = '[';
                    break;
                }
                break;
            case 1457278549:
                if (str.equals("HOLD_RECONNECT_TIME")) {
                    c = '\\';
                    break;
                }
                break;
            case 1600103487:
                if (str.equals("HOLD_GRID_VOLT_MOV_AVG_HIGH")) {
                    c = ']';
                    break;
                }
                break;
            case 1853690354:
                if (str.equals("HOLD_AC_CHARGE_POWER_CMD")) {
                    c = '^';
                    break;
                }
                break;
            case 1925850636:
                if (str.equals("HOLD_LEAD_ACID_CHARGE_RATE")) {
                    c = '_';
                    break;
                }
                break;
            case 1985523803:
                if (str.equals("HOLD_CHARGE_POWER_PERCENT_CMD")) {
                    c = '`';
                    break;
                }
                break;
            case 2020464802:
                if (str.equals("HOLD_CONNECT_TIME")) {
                    c = 'a';
                    break;
                }
                break;
            case 2046998614:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR_1")) {
                    c = 'b';
                    break;
                }
                break;
            case 2046998615:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR_2")) {
                    c = 'c';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 'G':
            case 'H':
                return 84;
            case 1:
                return 27;
            case 2:
            case 3:
            case '6':
                return 69;
            case 4:
                return 113;
            case 5:
                return 103;
            case 6:
                return 99;
            case 7:
            case '\"':
            case 'b':
                return 86;
            case '\b':
            case '#':
            case 'c':
                return 88;
            case '\t':
                return 83;
            case '\n':
                return Integer.valueOf(Opcodes.FCMPL);
            case 11:
            case 'D':
            case 'P':
                return 87;
            case '\f':
            case 'E':
            case 'Q':
                return 89;
            case '\r':
                return 15;
            case 14:
            case 28:
            case '9':
                return 79;
            case 15:
            case 29:
            case ':':
                return 81;
            case 16:
            case 17:
            case 'I':
                return 85;
            case 18:
            case 19:
            case ')':
                return 76;
            case 20:
                return 20;
            case 21:
            case '>':
            case '?':
                return 77;
            case 22:
                return 11;
            case 23:
                return 43;
            case 24:
                return Integer.valueOf(Opcodes.FCMPG);
            case 25:
                return 47;
            case 26:
                return 51;
            case 27:
                return 30;
            case 30:
                return 34;
            case 31:
                return 38;
            case ' ':
            case '\'':
            case '2':
                return 78;
            case '!':
            case '(':
            case '3':
                return 80;
            case '$':
                return 82;
            case '%':
                return 28;
            case '&':
                return Integer.valueOf(Opcodes.ARETURN);
            case '*':
                return 29;
            case '+':
                return 33;
            case ',':
                return 37;
            case '-':
                return 100;
            case '.':
                return Integer.valueOf(Opcodes.DCMPL);
            case '/':
            case '<':
            case 'S':
                return 71;
            case '0':
            case '=':
            case 'T':
                return 73;
            case '1':
                return 75;
            case '4':
            case '5':
            case 'F':
                return 68;
            case '7':
                return 12;
            case '8':
                return 102;
            case ';':
                return 65;
            case '@':
            case 'K':
            case 'N':
                return 70;
            case 'A':
            case 'L':
            case 'O':
                return 72;
            case 'B':
                return 22;
            case 'C':
                return 26;
            case 'J':
                return 74;
            case 'M':
                return 112;
            case 'R':
                return 105;
            case 'U':
                return 67;
            case 'V':
                return 125;
            case 'W':
                return 144;
            case 'X':
                return 25;
            case 'Y':
                return 42;
            case 'Z':
                return 46;
            case '[':
                return 50;
            case '\\':
                return 24;
            case ']':
                return 41;
            case '^':
                return 66;
            case '_':
                return 101;
            case '`':
                return 64;
            case 'a':
                return 23;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getValueByParam(String str, String str2) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2106433585:
                if (str.equals("HOLD_GRID_FREQ_CONN_LOW")) {
                    c = 0;
                    break;
                }
                break;
            case -2051169280:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_CHG")) {
                    c = 1;
                    break;
                }
                break;
            case -1922746271:
                if (str.equals("HOLD_LEAD_ACID_CHARGE_VOLT_REF")) {
                    c = 2;
                    break;
                }
                break;
            case -1854323434:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_DISCHG")) {
                    c = 3;
                    break;
                }
                break;
            case -1738912721:
                if (str.equals("HOLD_EQUALIZATION_VOLTAGE")) {
                    c = 4;
                    break;
                }
                break;
            case -1605958379:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_DISCHG")) {
                    c = 5;
                    break;
                }
                break;
            case -1352201891:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_HIGH")) {
                    c = 6;
                    break;
                }
                break;
            case -1323572740:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_HIGH")) {
                    c = 7;
                    break;
                }
                break;
            case -1294943589:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_HIGH")) {
                    c = '\b';
                    break;
                }
                break;
            case -1282654362:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_HIGH")) {
                    c = '\t';
                    break;
                }
                break;
            case -1254025211:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_HIGH")) {
                    c = '\n';
                    break;
                }
                break;
            case -1225396060:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_HIGH")) {
                    c = 11;
                    break;
                }
                break;
            case -875057049:
                if (str.equals("HOLD_GRID_FREQ_CONN_HIGH")) {
                    c = '\f';
                    break;
                }
                break;
            case -595561232:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_LOW")) {
                    c = '\r';
                    break;
                }
                break;
            case -594637711:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_LOW")) {
                    c = 14;
                    break;
                }
                break;
            case -593714190:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_LOW")) {
                    c = 15;
                    break;
                }
                break;
            case -573009974:
                if (str.equals("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT")) {
                    c = 16;
                    break;
                }
                break;
            case 180157537:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_CHG")) {
                    c = 17;
                    break;
                }
                break;
            case 265183773:
                if (str.equals("HOLD_START_PV_VOLT")) {
                    c = 18;
                    break;
                }
                break;
            case 268168589:
                if (str.equals("HOLD_V1H")) {
                    c = 19;
                    break;
                }
                break;
            case 268168593:
                if (str.equals("HOLD_V1L")) {
                    c = 20;
                    break;
                }
                break;
            case 268168620:
                if (str.equals("HOLD_V2H")) {
                    c = 21;
                    break;
                }
                break;
            case 268168624:
                if (str.equals("HOLD_V2L")) {
                    c = 22;
                    break;
                }
                break;
            case 313840816:
                if (str.equals("HOLD_GRID_VOLT_CONN_HIGH")) {
                    c = 23;
                    break;
                }
                break;
            case 1039780741:
                if (str.equals("HOLD_FLOATING_VOLTAGE")) {
                    c = 24;
                    break;
                }
                break;
            case 1118506598:
                if (str.equals("HOLD_GRID_VOLT_CONN_LOW")) {
                    c = 25;
                    break;
                }
                break;
            case 1203310617:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_LOW")) {
                    c = 26;
                    break;
                }
                break;
            case 1204234138:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_LOW")) {
                    c = 27;
                    break;
                }
                break;
            case 1205157659:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_LOW")) {
                    c = 28;
                    break;
                }
                break;
            case 1600103487:
                if (str.equals("HOLD_GRID_VOLT_MOV_AVG_HIGH")) {
                    c = 29;
                    break;
                }
                break;
            case 2069975635:
                if (str.equals("HOLD_POWER_SOFT_START_SLOPE")) {
                    c = 30;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 6:
            case 7:
            case '\b':
            case '\f':
            case 26:
            case 27:
            case 28:
                Double d = toDouble(str2);
                if (d != null) {
                    return Integer.valueOf((int) Double.valueOf(d.doubleValue() * 100.0d).doubleValue());
                }
                return null;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case '\t':
            case '\n':
            case 11:
            case '\r':
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 29:
            case 30:
                Double d2 = toDouble(str2);
                if (d2 != null) {
                    return Integer.valueOf((int) Double.valueOf(d2.doubleValue() * 10.0d).doubleValue());
                }
                return null;
            default:
                return toInt(str2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02d5 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean checkValueValid(java.lang.String r16, int r17) {
        /*
            Method dump skipped, instructions count: 1090
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.checkValueValid(java.lang.String, int):boolean");
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
            case -661950026:
                if (str.equals("FUNC_AC_CHARGE")) {
                    c = 1;
                    break;
                }
                break;
            case -247500863:
                if (str.equals("FUNC_SET_TO_STANDBY")) {
                    c = 2;
                    break;
                }
                break;
            case -234409492:
                if (str.equals("FUNC_RUN_WITHOUT_GRID")) {
                    c = 3;
                    break;
                }
                break;
            case -62297874:
                if (str.equals("FUNC_FEED_IN_GRID_EN")) {
                    c = 4;
                    break;
                }
                break;
            case 1216588652:
                if (str.equals("FUNC_MICRO_GRID_EN")) {
                    c = 5;
                    break;
                }
                break;
            case 1265688859:
                if (str.equals("FUNC_EPS_EN")) {
                    c = 6;
                    break;
                }
                break;
            case 1325529060:
                if (str.equals("FUNC_SW_SEAMLESSLY_EN")) {
                    c = 7;
                    break;
                }
                break;
            case 1782925386:
                if (str.equals("FUNC_BAT_SHARED")) {
                    c = '\b';
                    break;
                }
                break;
            case 1991591793:
                if (str.equals("FUNC_FORCED_CHG_EN")) {
                    c = '\t';
                    break;
                }
                break;
            case 2029383081:
                if (str.equals("FUNC_FORCED_DISCHG_EN")) {
                    c = '\n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 3:
            case 5:
            case '\b':
                return 110;
            case 1:
            case 2:
            case 4:
            case 6:
            case 7:
            case '\t':
            case '\n':
                return 21;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0094, code lost:
    
        if (r12.equals("FUNC_PV_GRID_OFF_EN") == false) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Integer getBitByFunction(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.local.fragment.LocalSetFragment.getBitByFunction(java.lang.String):java.lang.Integer");
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
        if (i == 0) {
            return new String[]{"HOLD_MODEL"};
        }
        if (i == 2) {
            return new String[]{"HOLD_SERIAL_NUM"};
        }
        if (i == 7) {
            return new String[]{"HOLD_FW_CODE"};
        }
        if (i == 12) {
            return new String[]{"HOLD_TIME"};
        }
        if (i == 20) {
            return new String[]{"HOLD_PV_INPUT_MODE"};
        }
        if (i == 125) {
            return new String[]{"HOLD_SOC_LOW_LIMIT_EPS_DISCHG"};
        }
        if (i == 144) {
            return new String[]{"HOLD_FLOATING_VOLTAGE"};
        }
        if (i == 176) {
            return new String[]{"HOLD_MAX_AC_INPUT_POWER"};
        }
        if (i == 15) {
            return new String[]{"HOLD_COM_ADDR"};
        }
        if (i == 16) {
            return new String[]{"HOLD_LANGUAGE"};
        }
        if (i == 112) {
            return new String[]{"HOLD_SET_MASTER_OR_SLAVE"};
        }
        if (i != 113) {
            switch (i) {
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
                default:
                    switch (i) {
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
                        default:
                            switch (i) {
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
                                default:
                                    switch (i) {
                                        case Opcodes.FCMPL /* 149 */:
                                            return new String[]{"HOLD_EQUALIZATION_VOLTAGE"};
                                        case Opcodes.FCMPG /* 150 */:
                                            return new String[]{"HOLD_EQUALIZATION_PERIOD"};
                                        case Opcodes.DCMPL /* 151 */:
                                            return new String[]{"HOLD_EQUALIZATION_TIME"};
                                        default:
                                            return null;
                                    }
                            }
                    }
            }
        }
        return new String[]{"HOLD_SET_COMPOSED_PHASE"};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getValueShowText(String str, int i, String str2) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2110570028:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE")) {
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
            case -2092080778:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR")) {
                    c = 2;
                    break;
                }
                break;
            case -2070600516:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_LOW_TIME")) {
                    c = 3;
                    break;
                }
                break;
            case -2064663285:
                if (str.equals("HOLD_SET_COMPOSED_PHASE")) {
                    c = 4;
                    break;
                }
                break;
            case -2051169280:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_CHG")) {
                    c = 5;
                    break;
                }
                break;
            case -2012582261:
                if (str.equals("HOLD_FEED_IN_GRID_POWER_PERCENT")) {
                    c = 6;
                    break;
                }
                break;
            case -1922746271:
                if (str.equals("HOLD_LEAD_ACID_CHARGE_VOLT_REF")) {
                    c = 7;
                    break;
                }
                break;
            case -1876698434:
                if (str.equals("HOLD_FORCED_DISCHG_SOC_LIMIT")) {
                    c = '\b';
                    break;
                }
                break;
            case -1854323434:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_LOWER_LIMIT_DISCHG")) {
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
            case -1662706451:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE_1")) {
                    c = 11;
                    break;
                }
                break;
            case -1662706450:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE_2")) {
                    c = '\f';
                    break;
                }
                break;
            case -1657690225:
                if (str.equals("HOLD_COM_ADDR")) {
                    c = '\r';
                    break;
                }
                break;
            case -1612429665:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR_1")) {
                    c = 14;
                    break;
                }
                break;
            case -1612429664:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR_2")) {
                    c = 15;
                    break;
                }
                break;
            case -1605958379:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_DISCHG")) {
                    c = 16;
                    break;
                }
                break;
            case -1579641573:
                if (str.equals("HOLD_FW_CODE")) {
                    c = 17;
                    break;
                }
                break;
            case -1563900533:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR")) {
                    c = 18;
                    break;
                }
                break;
            case -1551617594:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR")) {
                    c = 19;
                    break;
                }
                break;
            case -1524255375:
                if (str.equals("HOLD_PV_INPUT_MODE")) {
                    c = 20;
                    break;
                }
                break;
            case -1475031011:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE")) {
                    c = 21;
                    break;
                }
                break;
            case -1352201891:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_HIGH")) {
                    c = 22;
                    break;
                }
                break;
            case -1345314128:
                if (str.equals("HOLD_EQUALIZATION_PERIOD")) {
                    c = 23;
                    break;
                }
                break;
            case -1323572740:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_HIGH")) {
                    c = 24;
                    break;
                }
                break;
            case -1294943589:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_HIGH")) {
                    c = 25;
                    break;
                }
                break;
            case -1282654362:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_HIGH")) {
                    c = 26;
                    break;
                }
                break;
            case -1254025211:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_HIGH")) {
                    c = 27;
                    break;
                }
                break;
            case -1225396060:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_HIGH")) {
                    c = 28;
                    break;
                }
                break;
            case -1119226968:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE_1")) {
                    c = 29;
                    break;
                }
                break;
            case -1119226967:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE_2")) {
                    c = 30;
                    break;
                }
                break;
            case -1033230202:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE_1")) {
                    c = 31;
                    break;
                }
                break;
            case -1033230201:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_MINUTE_2")) {
                    c = ' ';
                    break;
                }
                break;
            case -917268805:
                if (str.equals("HOLD_FORCED_DISCHG_POWER_CMD")) {
                    c = '!';
                    break;
                }
                break;
            case -875057049:
                if (str.equals("HOLD_GRID_FREQ_CONN_HIGH")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case -823881906:
                if (str.equals("HOLD_MAX_AC_INPUT_POWER")) {
                    c = '#';
                    break;
                }
                break;
            case -750853128:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR_1")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case -750853127:
                if (str.equals("HOLD_FORCED_CHARGE_START_HOUR_2")) {
                    c = '%';
                    break;
                }
                break;
            case -659862417:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_HIGH_TIME")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case -659327994:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_HIGH_TIME")) {
                    c = '\'';
                    break;
                }
                break;
            case -613454474:
                if (str.equals("HOLD_FORCED_CHARGE_START_MINUTE")) {
                    c = '(';
                    break;
                }
                break;
            case -595561232:
                if (str.equals("HOLD_GRID_VOLT_LIMIT1_LOW")) {
                    c = ')';
                    break;
                }
                break;
            case -594637711:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_LOW")) {
                    c = '*';
                    break;
                }
                break;
            case -593714190:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_LOW")) {
                    c = '+';
                    break;
                }
                break;
            case -573009974:
                if (str.equals("HOLD_LEAD_ACID_DISCHARGE_CUT_OFF_VOLT")) {
                    c = ',';
                    break;
                }
                break;
            case -550997124:
                if (str.equals("HOLD_EQUALIZATION_TIME")) {
                    c = '-';
                    break;
                }
                break;
            case -444930136:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR_1")) {
                    c = '.';
                    break;
                }
                break;
            case -444930135:
                if (str.equals("HOLD_AC_CHARGE_END_HOUR_2")) {
                    c = '/';
                    break;
                }
                break;
            case -431364128:
                if (str.equals("HOLD_FORCED_CHG_SOC_LIMIT")) {
                    c = '0';
                    break;
                }
                break;
            case -384118065:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR")) {
                    c = '1';
                    break;
                }
                break;
            case -307531482:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE")) {
                    c = '2';
                    break;
                }
                break;
            case -276744627:
                if (str.equals("HOLD_TIME")) {
                    c = '3';
                    break;
                }
                break;
            case -233655236:
                if (str.equals("HOLD_LEAD_ACID_DISCHARGE_RATE")) {
                    c = '4';
                    break;
                }
                break;
            case -165590897:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE_1")) {
                    c = '5';
                    break;
                }
                break;
            case -165590896:
                if (str.equals("HOLD_FORCED_CHARGE_END_MINUTE_2")) {
                    c = '6';
                    break;
                }
                break;
            case -135057669:
                if (str.equals("HOLD_DISCHG_POWER_PERCENT_CMD")) {
                    c = '7';
                    break;
                }
                break;
            case -26098721:
                if (str.equals("HOLD_REACTIVE_POWER_CMD_TYPE")) {
                    c = '8';
                    break;
                }
                break;
            case 4556681:
                if (str.equals("HOLD_MODEL")) {
                    c = '9';
                    break;
                }
                break;
            case 41202161:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_HIGH_TIME")) {
                    c = ':';
                    break;
                }
                break;
            case 41736584:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_HIGH_TIME")) {
                    c = ';';
                    break;
                }
                break;
            case 127930925:
                if (str.equals("HOLD_FORCED_CHARGE_END_HOUR")) {
                    c = Typography.less;
                    break;
                }
                break;
            case 168754545:
                if (str.equals("HOLD_GRID_FREQ_LIMIT3_LOW_TIME")) {
                    c = '=';
                    break;
                }
                break;
            case 180157537:
                if (str.equals("HOLD_LEAD_ACID_TEMPR_UPPER_LIMIT_CHG")) {
                    c = Typography.greater;
                    break;
                }
                break;
            case 225083931:
                if (str.equals("HOLD_SERIAL_NUM")) {
                    c = '?';
                    break;
                }
                break;
            case 229729985:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR_1")) {
                    c = '@';
                    break;
                }
                break;
            case 229729986:
                if (str.equals("HOLD_AC_CHARGE_START_HOUR_2")) {
                    c = 'A';
                    break;
                }
                break;
            case 265183773:
                if (str.equals("HOLD_START_PV_VOLT")) {
                    c = 'B';
                    break;
                }
                break;
            case 268168589:
                if (str.equals("HOLD_V1H")) {
                    c = 'C';
                    break;
                }
                break;
            case 268168593:
                if (str.equals("HOLD_V1L")) {
                    c = 'D';
                    break;
                }
                break;
            case 268168620:
                if (str.equals("HOLD_V2H")) {
                    c = 'E';
                    break;
                }
                break;
            case 268168624:
                if (str.equals("HOLD_V2L")) {
                    c = 'F';
                    break;
                }
                break;
            case 313840816:
                if (str.equals("HOLD_GRID_VOLT_CONN_HIGH")) {
                    c = 'G';
                    break;
                }
                break;
            case 330144381:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR_1")) {
                    c = 'H';
                    break;
                }
                break;
            case 330144382:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_HOUR_2")) {
                    c = 'I';
                    break;
                }
                break;
            case 365268050:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_LOW_TIME")) {
                    c = 'J';
                    break;
                }
                break;
            case 367128639:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE")) {
                    c = 'K';
                    break;
                }
                break;
            case 390956452:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR")) {
                    c = 'L';
                    break;
                }
                break;
            case 467543035:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_MINUTE")) {
                    c = 'M';
                    break;
                }
                break;
            case 528065501:
                if (str.equals("HOLD_FORCED_CHG_POWER_CMD")) {
                    c = 'N';
                    break;
                }
                break;
            case 561781555:
                if (str.equals("HOLD_GRID_FREQ_LIMIT1_LOW_TIME")) {
                    c = 'O';
                    break;
                }
                break;
            case 574683163:
                if (str.equals("HOLD_SET_MASTER_OR_SLAVE")) {
                    c = 'P';
                    break;
                }
                break;
            case 623306801:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE_1")) {
                    c = 'Q';
                    break;
                }
                break;
            case 623306802:
                if (str.equals("HOLD_AC_CHARGE_START_MINUTE_2")) {
                    c = 'R';
                    break;
                }
                break;
            case 677731373:
                if (str.equals("HOLD_ACTIVE_POWER_PERCENT_CMD")) {
                    c = 'S';
                    break;
                }
                break;
            case 767099658:
                if (str.equals("HOLD_DISCHG_CUT_OFF_SOC_EOD")) {
                    c = 'T';
                    break;
                }
                break;
            case 814992216:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE_1")) {
                    c = 'U';
                    break;
                }
                break;
            case 814992217:
                if (str.equals("HOLD_AC_CHARGE_END_MINUTE_2")) {
                    c = 'V';
                    break;
                }
                break;
            case 894260725:
                if (str.equals("HOLD_AC_CHARGE_SOC_LIMIT")) {
                    c = 'W';
                    break;
                }
                break;
            case 946846866:
                if (str.equals("HOLD_SOC_LOW_LIMIT_EPS_DISCHG")) {
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
            case 1457278549:
                if (str.equals("HOLD_RECONNECT_TIME")) {
                    c = '^';
                    break;
                }
                break;
            case 1600103487:
                if (str.equals("HOLD_GRID_VOLT_MOV_AVG_HIGH")) {
                    c = '_';
                    break;
                }
                break;
            case 1831339770:
                if (str.equals("HOLD_GRID_VOLT_LIMIT3_LOW_TIME")) {
                    c = '`';
                    break;
                }
                break;
            case 1838153520:
                if (str.equals("HOLD_GRID_FREQ_LIMIT2_HIGH_TIME")) {
                    c = 'a';
                    break;
                }
                break;
            case 1838687943:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_HIGH_TIME")) {
                    c = 'b';
                    break;
                }
                break;
            case 1853690354:
                if (str.equals("HOLD_AC_CHARGE_POWER_CMD")) {
                    c = 'c';
                    break;
                }
                break;
            case 1925850636:
                if (str.equals("HOLD_LEAD_ACID_CHARGE_RATE")) {
                    c = 'd';
                    break;
                }
                break;
            case 1985523803:
                if (str.equals("HOLD_CHARGE_POWER_PERCENT_CMD")) {
                    c = 'e';
                    break;
                }
                break;
            case 2020464802:
                if (str.equals("HOLD_CONNECT_TIME")) {
                    c = 'f';
                    break;
                }
                break;
            case 2027853275:
                if (str.equals("HOLD_GRID_VOLT_LIMIT2_LOW_TIME")) {
                    c = 'g';
                    break;
                }
                break;
            case 2036981088:
                if (str.equals("HOLD_REACTIVE_POWER_PERCENT_CMD")) {
                    c = 'h';
                    break;
                }
                break;
            case 2046998614:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR_1")) {
                    c = 'i';
                    break;
                }
                break;
            case 2046998615:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_HOUR_2")) {
                    c = 'j';
                    break;
                }
                break;
            case 2069975635:
                if (str.equals("HOLD_POWER_SOFT_START_SLOPE")) {
                    c = 'k';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 11:
            case '\f':
            case 21:
            case 29:
            case 30:
            case 31:
            case ' ':
            case '(':
            case '2':
            case '5':
            case '6':
            case 'K':
            case 'M':
            case 'Q':
            case 'R':
            case 'U':
            case 'V':
                return ProTool.fillZeros(String.valueOf(getInt2HighParamValue(str, i, str2)), 2);
            case 1:
            case 22:
            case 24:
            case 25:
            case '\"':
            case '[':
            case '\\':
            case ']':
                return InvTool.formatData(getInt2ParamValue(str, i, str2) / 100.0d);
            case 2:
            case 14:
            case 15:
            case 18:
            case 19:
            case '$':
            case '%':
            case '.':
            case '/':
            case '1':
            case '<':
            case '@':
            case 'A':
            case 'H':
            case 'I':
            case 'L':
            case 'i':
            case 'j':
                return ProTool.fillZeros(String.valueOf(getInt2LowParamValue(str, i, str2)), 2);
            case 3:
            case 4:
            case 6:
            case '\b':
            case '\r':
            case 20:
            case 23:
            case '!':
            case '#':
            case '&':
            case '\'':
            case '-':
            case '0':
            case '4':
            case '7':
            case '8':
            case ':':
            case ';':
            case '=':
            case 'J':
            case 'N':
            case 'O':
            case 'P':
            case 'S':
            case 'T':
            case 'W':
            case 'X':
            case '^':
            case '`':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
                return String.valueOf(getInt2ParamValue(str, i, str2));
            case 5:
            case '\t':
            case 16:
            case '>':
                return InvTool.formatData(getInt2ParamValueN(str, i, str2) / 10.0d);
            case 7:
            case '\n':
            case 26:
            case 27:
            case 28:
            case ')':
            case '*':
            case '+':
            case ',':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'Y':
            case 'Z':
            case '_':
            case 'k':
                return InvTool.formatData(getInt2ParamValue(str, i, str2) / 10.0d);
            case 17:
                int valueStartIndex = getValueStartIndex(str, i);
                return str2.substring(valueStartIndex, 4) + "-" + ProTool.showHex(str2.charAt(valueStartIndex + 5)) + ProTool.showHex(str2.charAt(valueStartIndex + 6));
            case '3':
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
            case '9':
                return "0x" + Long.toHexString(getLong4ParamValue(str, i, str2)).toUpperCase();
            case '?':
                return str2.substring(getValueStartIndex(str, i), 10);
            default:
                return "";
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
