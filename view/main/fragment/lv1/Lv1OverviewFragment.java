package com.nfcx.luxinvpower.view.main.fragment.lv1;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.firebase.messaging.Constants;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.global.custom.view.CircleView;
import com.nfcx.luxinvpower.global.custom.view.GifView;
import com.nfcx.luxinvpower.tool.API;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment;
import com.nfcx.luxinvpower.view.overview.inverter.InverterBatteryParamsActivity;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.plant.PlantListActivity;
import com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Lv1OverviewFragment extends AbstractItemFragment {
    private static List<Integer> pvPieColors;
    private Button battCapacityButton;
    private Button battParallelNumButton;
    private Button batteryDataButton;
    private TextView batteryEnergyTitleLabel;
    private ConstraintLayout batteryEnergyViewLayout;
    private LinearLayout batteryEnergyViewLayout1;
    private LinearLayout batteryEnergyViewLayout2;
    private ImageView batteryParamImageView;
    private Button bmsLimitChargeButton;
    private Button bmsLimitDischargeButton;
    private ConstraintLayout centered1Layout;
    private ConstraintLayout centered2Layout;
    private ConstraintLayout centeredLayout;
    private TextView consumptionPowerValue1TextView;
    private TextView consumptionPowerValue2TextView;
    private CountDownTimer countDownTimer;
    private boolean created;
    private int currentBatteryEnergyViewIndex;
    private int currentFeedinEnergyViewIndex;
    private int currentPvEnergyViewIndex;
    private Button epsL1nButton;
    private Button epsL2nButton;
    private ConstraintLayout epsLayout;
    private ImageView epsParamImageView;
    private TextView epsValue1TextView;
    private TextView epsValue2TextView;
    private TextView feedinEnergyTitleLabel;
    private ConstraintLayout feedinEnergyViewLayout;
    private LinearLayout feedinEnergyViewLayout1;
    private LinearLayout feedinEnergyViewLayout2;
    private Button firmwareButton;
    private GifView flowAcPvPowerGifView1;
    private GifView flowAcPvPowerGifView2;
    private ImageView flowAcPvPowerImageView;
    private TextView flowAcPvPowerLabelTextView;
    private TextView flowAcPvPowerTextView;
    private TextView flowBatVoltTextView;
    private ImageView flowBatteryImageView;
    private GifView flowBatteryPowerGifView1;
    private GifView flowBatteryPowerGifView2;
    private TextView flowBatteryPowerLabelTextView;
    private TextView flowBatteryPowerTextView;
    private GifView flowConsumptionPowerGifView1;
    private GifView flowConsumptionPowerGifView2;
    private TextView flowConsumptionPowerTextView;
    private GifView flowEpsPowerGifView;
    private TextView flowEpsPowerTextView;
    private TextView flowFacTextView;
    private GifView flowGridPowerGifView1;
    private GifView flowGridPowerGifView2;
    private TextView flowGridPowerLabelTextView;
    private TextView flowGridPowerTextView;
    private GifView flowInverterArrowGifView1;
    private GifView flowInverterArrowGifView2;
    private GifView flowInverterArrowGifView3;
    private ImageView flowInverterImageView;
    private GifView flowPvPowerGifView;
    private ImageView flowPvPowerImageView;
    private TextView flowPvPowerLabelTextView;
    private TextView flowPvPowerTextView;
    private TextView flowSocPowerTextView;
    private TextView flowVacTextView;
    private Fragment fragment;
    private Button genExerciseStartButton;
    private TextView generatorAutoStopTextView;
    private ImageView generatorImageView;
    private ImageView gridImageView;
    private TextView gridPowerValue1TextView;
    private Inverter inverter;
    private List<Inverter> inverterList;
    private Spinner inverterSpinner;
    private long lastRefreshDataTime;
    private Inverter lastReloadInverter;
    private long lastReloadTime;
    private TextView localTimeTextView;
    private ConstraintLayout newButton2Layout;
    private ConstraintLayout newButtonLayout;
    private Button powerRateButton;
    private TextView pvChargeToday;
    private TextView pvChargeTotal;
    private ConstraintLayout pvEnergyViewLayout;
    private LinearLayout pvEnergyViewLayout1;
    private ConstraintLayout pvEnergyViewLayout2;
    private ConstraintLayout pvEnergyViewLayout3;
    private TextView pvExportToday;
    private TextView pvExportTotal;
    private TextView pvLoadToday;
    private TextView pvLoadTotal;
    private Button quickGridChargeButton;
    private Button refreshButton;
    private boolean shouldShowTodayCircleView;
    private boolean shouldShowTotalCircleView;
    private String status;
    private ImageView statusImageView;
    private Button stopGenExerciseStartButton;
    private Button stopQuickGridChargeButton;
    private TextView todayAllPvEnergy;
    private TextView todayChargingTextView;
    private CircleView todayCircleView;
    private TextView todayDischargingTextView;
    private TextView todayExportTextView;
    private TextView todayImportTextView;
    private PieChart todayPvPieChart;
    private PieDataSet todayPvPieDataSet;
    private TextView todayUsageTextView;
    private TextView todayYieldingTextView;
    private TextView totalAllPvEnergy;
    private TextView totalChargingTextView;
    private CircleView totalCircleView;
    private TextView totalDischargingTextView;
    private TextView totalExportTextView;
    private TextView totalImportTextView;
    private PieChart totalPvPieChart;
    private PieDataSet totalPvPieDataSet;
    private TextView totalUsageTextView;
    private TextView totalYieldingTextView;
    private int waitRequestCount;

    public Lv1OverviewFragment() {
        super(1L);
        this.currentPvEnergyViewIndex = 1;
        this.currentBatteryEnergyViewIndex = 1;
        this.currentFeedinEnergyViewIndex = 1;
    }

    static {
        ArrayList arrayList = new ArrayList();
        pvPieColors = arrayList;
        arrayList.add(Integer.valueOf(Color.rgb(255, 113, Opcodes.D2L)));
        pvPieColors.add(Integer.valueOf(Color.rgb(92, 201, Opcodes.IF_ICMPNE)));
        pvPieColors.add(Integer.valueOf(Color.rgb(242, 164, 116)));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        List<Inverter> invertersByPlant;
        System.out.println("LuxPower - Lv1OverviewFragment.onCreateView...");
        View inflate = layoutInflater.inflate(R.layout.fragment_lv1_overview, viewGroup, false);
        this.fragment = this;
        final UserData userData = GlobalInfo.getInstance().getUserData();
        if (!PLATFORM.LUX_POWER.equals(userData.getPlatform())) {
            inflate.findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        ((ConstraintLayout) inflate.findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OverviewFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) (ROLE.VIEWER.equals(userData.getRole()) ? PlantListActivity.class : PlantOverviewActivity.class)));
                MainActivity.instance.finish();
            }
        });
        ((ImageView) inflate.findViewById(R.id.userCenterImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OverviewFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) NewUserCenterActivity.class));
            }
        });
        this.inverterSpinner = (Spinner) inflate.findViewById(R.id.fragment_overview_inverter_spinner);
        this.inverterList = new ArrayList();
        if (userData.getCurrentPlant() != null && (invertersByPlant = userData.getInvertersByPlant(userData.getCurrentPlant().getPlantId())) != null) {
            Iterator<Inverter> it = invertersByPlant.iterator();
            while (it.hasNext()) {
                this.inverterList.add(it.next());
            }
        }
        if (userData.getCurrentPlant() != null && !userData.getCurrentPlant().getParallelGroups().isEmpty()) {
            Map<String, String> parallelGroups = userData.getCurrentPlant().getParallelGroups();
            int i = 0;
            for (String str : parallelGroups.keySet()) {
                Inverter inverter = new Inverter();
                inverter.setSerialNum(getString(R.string.phrase_parallel) + "-" + str);
                inverter.setPlantId(Long.valueOf(userData.getCurrentPlant().getPlantId()));
                inverter.setParallelGroup(str);
                String str2 = parallelGroups.get(str);
                inverter.setParallelFirstDeviceSn(str2);
                Inverter inverter2 = userData.getInverter(str2);
                if (inverter2 != null) {
                    inverter.setDeviceType(inverter2.getDeviceType());
                }
                this.inverterList.add(i, inverter);
                i++;
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item, this.inverterList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.inverterSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        this.inverterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                Inverter inverter3 = (Inverter) Lv1OverviewFragment.this.inverterSpinner.getSelectedItem();
                System.out.println("LuxPower - onItemSelected selectInverter = " + inverter3);
                if (Lv1OverviewFragment.this.inverter == null || !Lv1OverviewFragment.this.inverter.getSerialNum().equals(inverter3.getSerialNum())) {
                    Lv1OverviewFragment.this.inverter = inverter3;
                    Lv1OverviewFragment.this.initFlowChartByDeviceType();
                    UserData userData2 = GlobalInfo.getInstance().getUserData();
                    if (Lv1OverviewFragment.this.inverter.isParallelGroup()) {
                        userData2.setCurrentParallelGroup(Lv1OverviewFragment.this.inverter);
                    } else {
                        userData2.setCurrentInverter(Lv1OverviewFragment.this.inverter, true);
                    }
                    if (Lv1OverviewFragment.this.inverter.getWithbatteryData() == null || !Lv1OverviewFragment.this.inverter.getWithbatteryData().booleanValue()) {
                        Lv1OverviewFragment.this.newButton2Layout.setVisibility(8);
                    } else {
                        Lv1OverviewFragment.this.newButton2Layout.setVisibility(0);
                    }
                    Lv1OverviewFragment.this.m360xd9585229();
                    MainActivity mainActivity = (MainActivity) Lv1OverviewFragment.this.fragment.getActivity();
                    if (mainActivity != null) {
                        mainActivity.switchRemoteSetFragment(Lv1OverviewFragment.this.inverter.getDeviceTypeValue());
                    }
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (Lv1OverviewFragment.this.inverter != null) {
                    UserData userData2 = GlobalInfo.getInstance().getUserData();
                    if (Lv1OverviewFragment.this.inverter.isParallelGroup()) {
                        userData2.setCurrentParallelGroup(null);
                    } else {
                        userData2.setCurrentInverter(null, true);
                    }
                    if (Lv1OverviewFragment.this.inverter != null && !Lv1OverviewFragment.this.inverter.isAllowGenExercise()) {
                        Lv1OverviewFragment.this.genExerciseStartButton.setVisibility(4);
                        Lv1OverviewFragment.this.stopGenExerciseStartButton.setVisibility(4);
                    }
                    if (Lv1OverviewFragment.this.inverter != null && Lv1OverviewFragment.this.inverter.isParallelGroup()) {
                        Lv1OverviewFragment.this.centeredLayout.setVisibility(0);
                        Lv1OverviewFragment.this.epsLayout.setVisibility(0);
                    }
                    Lv1OverviewFragment.this.inverter = null;
                    Lv1OverviewFragment.this.m360xd9585229();
                }
            }
        });
        this.pvEnergyViewLayout1 = (LinearLayout) inflate.findViewById(R.id.pvEnergyViewLayout1);
        this.pvEnergyViewLayout2 = (ConstraintLayout) inflate.findViewById(R.id.pvEnergyViewLayout2);
        this.pvEnergyViewLayout3 = (ConstraintLayout) inflate.findViewById(R.id.pvEnergyViewLayout3);
        ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.pvEnergyViewLayout);
        this.pvEnergyViewLayout = constraintLayout;
        constraintLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (Lv1OverviewFragment.this.currentPvEnergyViewIndex == 1) {
                    Lv1OverviewFragment.this.pvEnergyViewLayout1.setVisibility(8);
                    Lv1OverviewFragment.this.pvEnergyViewLayout3.setVisibility(8);
                    Lv1OverviewFragment.this.todayCircleView.setVisibility(Lv1OverviewFragment.this.shouldShowTodayCircleView ? 0 : 8);
                    Lv1OverviewFragment.this.totalCircleView.setVisibility(8);
                    Lv1OverviewFragment.this.pvEnergyViewLayout2.setVisibility(0);
                    Lv1OverviewFragment.this.currentPvEnergyViewIndex = 2;
                } else if (Lv1OverviewFragment.this.currentPvEnergyViewIndex == 2) {
                    Lv1OverviewFragment.this.pvEnergyViewLayout1.setVisibility(8);
                    Lv1OverviewFragment.this.pvEnergyViewLayout2.setVisibility(8);
                    Lv1OverviewFragment.this.todayCircleView.setVisibility(8);
                    Lv1OverviewFragment.this.totalCircleView.setVisibility(Lv1OverviewFragment.this.shouldShowTotalCircleView ? 0 : 8);
                    Lv1OverviewFragment.this.pvEnergyViewLayout3.setVisibility(0);
                    Lv1OverviewFragment.this.currentPvEnergyViewIndex = 3;
                } else {
                    Lv1OverviewFragment.this.pvEnergyViewLayout2.setVisibility(8);
                    Lv1OverviewFragment.this.pvEnergyViewLayout3.setVisibility(8);
                    Lv1OverviewFragment.this.todayCircleView.setVisibility(8);
                    Lv1OverviewFragment.this.totalCircleView.setVisibility(8);
                    Lv1OverviewFragment.this.pvEnergyViewLayout1.setVisibility(0);
                    Lv1OverviewFragment.this.currentPvEnergyViewIndex = 1;
                }
                return false;
            }
        });
        this.batteryEnergyTitleLabel = (TextView) inflate.findViewById(R.id.batteryEnergyTitleLabel);
        this.batteryEnergyViewLayout1 = (LinearLayout) inflate.findViewById(R.id.batteryEnergyViewLayout1);
        this.batteryEnergyViewLayout2 = (LinearLayout) inflate.findViewById(R.id.batteryEnergyViewLayout2);
        this.batteryEnergyViewLayout = (ConstraintLayout) inflate.findViewById(R.id.batteryEnergyViewLayout);
        if (userData.isGigabiz1User()) {
            this.batteryEnergyTitleLabel.setText(R.string.main_overview_info_box_battery_charging);
            this.batteryEnergyViewLayout1.setVisibility(8);
            this.batteryEnergyViewLayout2.setVisibility(0);
            this.currentBatteryEnergyViewIndex = 2;
        } else {
            this.batteryEnergyViewLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return Lv1OverviewFragment.this.m358xa4c41fdc(view, motionEvent);
                }
            });
        }
        this.feedinEnergyViewLayout1 = (LinearLayout) inflate.findViewById(R.id.feedinEnergyViewLayout1);
        this.feedinEnergyViewLayout2 = (LinearLayout) inflate.findViewById(R.id.feedinEnergyViewLayout2);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) inflate.findViewById(R.id.feedinEnergyViewLayout);
        this.feedinEnergyViewLayout = constraintLayout2;
        constraintLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return Lv1OverviewFragment.this.m359xf28397dd(view, motionEvent);
            }
        });
        this.pvLoadToday = (TextView) inflate.findViewById(R.id.main_overview_info_pvLoadToday);
        this.pvChargeToday = (TextView) inflate.findViewById(R.id.main_overview_info_pvChargeToday);
        this.pvExportToday = (TextView) inflate.findViewById(R.id.main_overview_info_pvExportToday);
        this.todayAllPvEnergy = (TextView) inflate.findViewById(R.id.main_overview_info_todayAllPvEnergy);
        PieChart pieChart = (PieChart) inflate.findViewById(R.id.main_overview_info_todayPvPieChart);
        this.todayPvPieChart = pieChart;
        pieChart.getLegend().setEnabled(false);
        this.todayPvPieChart.getDescription().setEnabled(false);
        this.todayPvPieChart.setDrawHoleEnabled(false);
        this.shouldShowTodayCircleView = this.inverterList.isEmpty();
        this.todayCircleView = (CircleView) inflate.findViewById(R.id.main_overview_info_todayPvPieChartMask);
        this.pvLoadTotal = (TextView) inflate.findViewById(R.id.main_overview_info_pvLoadTotal);
        this.pvChargeTotal = (TextView) inflate.findViewById(R.id.main_overview_info_pvChargeTotal);
        this.pvExportTotal = (TextView) inflate.findViewById(R.id.main_overview_info_pvExportTotal);
        this.totalAllPvEnergy = (TextView) inflate.findViewById(R.id.main_overview_info_totalAllPvEnergy);
        PieChart pieChart2 = (PieChart) inflate.findViewById(R.id.main_overview_info_totalPvPieChart);
        this.totalPvPieChart = pieChart2;
        pieChart2.getLegend().setEnabled(false);
        this.totalPvPieChart.getDescription().setEnabled(false);
        this.totalPvPieChart.setDrawHoleEnabled(false);
        this.shouldShowTotalCircleView = this.inverterList.isEmpty();
        this.totalCircleView = (CircleView) inflate.findViewById(R.id.main_overview_info_totalPvPieChartMask);
        this.todayYieldingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_yielding);
        this.totalYieldingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_yielding);
        this.todayDischargingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_discharging);
        this.totalDischargingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_discharging);
        this.todayChargingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_charging);
        this.totalChargingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_charging);
        this.feedinEnergyTitleLabel = (TextView) inflate.findViewById(R.id.feedinEnergyTitleLabel);
        this.todayExportTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_export);
        this.totalExportTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_export);
        this.todayImportTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_import);
        this.totalImportTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_import);
        this.todayUsageTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_usage);
        this.totalUsageTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_usage);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.fragment_flow_statusImageView);
        this.statusImageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Constants.IPC_BUNDLE_KEY_SEND_ERROR.equals(Lv1OverviewFragment.this.status)) {
                    return;
                }
                "warning".equals(Lv1OverviewFragment.this.status);
            }
        });
        this.flowBatteryPowerTextView = (TextView) inflate.findViewById(R.id.fragment_flow_batteryPower_textView);
        this.flowBatteryPowerLabelTextView = (TextView) inflate.findViewById(R.id.fragment_flow_batteryPowerLabel_textView);
        this.flowBatteryPowerGifView1 = (GifView) inflate.findViewById(R.id.fragment_flow_batteryPower_gifView1);
        this.flowBatteryPowerGifView2 = (GifView) inflate.findViewById(R.id.fragment_flow_batteryPower_gifView2);
        this.flowSocPowerTextView = (TextView) inflate.findViewById(R.id.fragment_flow_socPower_textView);
        this.flowBatVoltTextView = (TextView) inflate.findViewById(R.id.fragment_flow_batVoltTextView);
        this.flowPvPowerImageView = (ImageView) inflate.findViewById(R.id.fragment_flow_pvPower_imageView);
        this.flowPvPowerTextView = (TextView) inflate.findViewById(R.id.fragment_flow_pvPower_textView);
        this.flowPvPowerLabelTextView = (TextView) inflate.findViewById(R.id.fragment_flow_pvPowerLabel_textView);
        GifView gifView = (GifView) inflate.findViewById(R.id.fragment_flow_pvPower_gifView);
        this.flowPvPowerGifView = gifView;
        gifView.setMovieResource(R.drawable.arrow_down);
        this.flowPvPowerGifView.setVisibility(4);
        this.flowAcPvPowerImageView = (ImageView) inflate.findViewById(R.id.fragment_flow_acPvPower_imageView);
        this.flowAcPvPowerTextView = (TextView) inflate.findViewById(R.id.fragment_flow_acPvPower_textView);
        this.flowAcPvPowerLabelTextView = (TextView) inflate.findViewById(R.id.fragment_flow_acPvPowerLabel_textView);
        GifView gifView2 = (GifView) inflate.findViewById(R.id.fragment_flow_acPvPower_gifView1);
        this.flowAcPvPowerGifView1 = gifView2;
        gifView2.setMovieResource(R.drawable.arrow_down);
        this.flowAcPvPowerGifView1.setVisibility(4);
        GifView gifView3 = (GifView) inflate.findViewById(R.id.fragment_flow_acPvPower_gifView2);
        this.flowAcPvPowerGifView2 = gifView3;
        gifView3.setMovieResource(R.drawable.arrow_down);
        this.flowAcPvPowerGifView2.setVisibility(4);
        this.flowInverterArrowGifView1 = (GifView) inflate.findViewById(R.id.fragment_flow_inverterArrow_gifView1);
        this.flowInverterArrowGifView2 = (GifView) inflate.findViewById(R.id.fragment_flow_inverterArrow_gifView2);
        this.flowInverterArrowGifView3 = (GifView) inflate.findViewById(R.id.fragment_flow_inverterArrow_gifView3);
        this.flowGridPowerTextView = (TextView) inflate.findViewById(R.id.fragment_flow_gridPower_textView);
        this.flowGridPowerLabelTextView = (TextView) inflate.findViewById(R.id.fragment_flow_gridPowerLabel_textView);
        this.flowGridPowerGifView1 = (GifView) inflate.findViewById(R.id.fragment_flow_gridPower_gifView1);
        this.flowGridPowerGifView2 = (GifView) inflate.findViewById(R.id.fragment_flow_gridPower_gifView2);
        this.flowVacTextView = (TextView) inflate.findViewById(R.id.fragment_flow_vacLabel_textView);
        this.flowFacTextView = (TextView) inflate.findViewById(R.id.fragment_flow_facLabel_textView);
        this.flowConsumptionPowerTextView = (TextView) inflate.findViewById(R.id.fragment_flow_consumptionPower_textView);
        GifView gifView4 = (GifView) inflate.findViewById(R.id.fragment_flow_consumptionPower_gifView1);
        this.flowConsumptionPowerGifView1 = gifView4;
        gifView4.setMovieResource(R.drawable.arrow_down);
        this.flowConsumptionPowerGifView1.setVisibility(4);
        GifView gifView5 = (GifView) inflate.findViewById(R.id.fragment_flow_consumptionPower_gifView2);
        this.flowConsumptionPowerGifView2 = gifView5;
        gifView5.setMovieResource(R.drawable.arrow_down);
        this.flowConsumptionPowerGifView2.setVisibility(4);
        this.flowEpsPowerTextView = (TextView) inflate.findViewById(R.id.fragment_flow_epsPower_textView);
        GifView gifView6 = (GifView) inflate.findViewById(R.id.fragment_flow_epsPower_gifView);
        this.flowEpsPowerGifView = gifView6;
        gifView6.setMovieResource(R.drawable.arrow_down);
        this.flowEpsPowerGifView.setVisibility(4);
        this.flowBatteryImageView = (ImageView) inflate.findViewById(R.id.fragment_flow_battery_imageView);
        this.gridPowerValue1TextView = (TextView) inflate.findViewById(R.id.fragment_flow_gridPowerValue1_textView);
        this.epsValue1TextView = (TextView) inflate.findViewById(R.id.fragment_flow_epsValue1_textView);
        this.epsValue2TextView = (TextView) inflate.findViewById(R.id.fragment_flow_epsValue2_textView);
        this.consumptionPowerValue1TextView = (TextView) inflate.findViewById(R.id.fragment_flow_consumptionPowerValue1_textView);
        this.consumptionPowerValue2TextView = (TextView) inflate.findViewById(R.id.fragment_flow_consumptionPowerValue2_textView);
        this.gridImageView = (ImageView) inflate.findViewById(R.id.gridImageView);
        this.generatorImageView = (ImageView) inflate.findViewById(R.id.generatorImageView);
        this.flowInverterImageView = (ImageView) inflate.findViewById(R.id.fragment_flow_inverter_imageView);
        this.localTimeTextView = (TextView) inflate.findViewById(R.id.fragment_flow_localTime_textView);
        this.powerRateButton = (Button) inflate.findViewById(R.id.power_rate_button);
        this.firmwareButton = (Button) inflate.findViewById(R.id.firmware_button);
        this.battParallelNumButton = (Button) inflate.findViewById(R.id.batt_parallel_button);
        this.battCapacityButton = (Button) inflate.findViewById(R.id.batt_capacity_button);
        this.bmsLimitChargeButton = (Button) inflate.findViewById(R.id.bms_limit_charge_button);
        this.bmsLimitDischargeButton = (Button) inflate.findViewById(R.id.bms_limit_discharge_button);
        this.epsL1nButton = (Button) inflate.findViewById(R.id.EPS_L1N_BUTTON);
        this.epsL2nButton = (Button) inflate.findViewById(R.id.EPS_L2N_BUTTON);
        this.centeredLayout = (ConstraintLayout) inflate.findViewById(R.id.centered_Layout);
        this.centered1Layout = (ConstraintLayout) inflate.findViewById(R.id.centered1_Layout);
        this.centered2Layout = (ConstraintLayout) inflate.findViewById(R.id.centered2_Layout);
        this.generatorAutoStopTextView = (TextView) inflate.findViewById(R.id.generator_auto_stopTextView);
        this.newButtonLayout = (ConstraintLayout) inflate.findViewById(R.id.new_button_layout);
        this.quickGridChargeButton = (Button) inflate.findViewById(R.id.quick_Grid_Charge_Button);
        String string = getString(R.string.start_quick_charge);
        SpannableString spannableString = new SpannableString(string + " ↗");
        spannableString.setSpan(new StyleSpan(1), 0, string.length(), 33);
        spannableString.setSpan(new StyleSpan(1), string.length(), string.length() + 2, 33);
        spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString.length(), 18);
        this.quickGridChargeButton.setText(spannableString);
        this.quickGridChargeButton.setTextSize(1, 12.0f);
        this.quickGridChargeButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OverviewFragment.this.inverter != null) {
                    Lv1OverviewFragment.this.quickGridChargeButton.setEnabled(false);
                    Lv1OverviewFragment lv1OverviewFragment = Lv1OverviewFragment.this;
                    new QuickChargeCtrlTask(lv1OverviewFragment, lv1OverviewFragment.quickGridChargeButton).execute(true);
                }
            }
        });
        this.stopQuickGridChargeButton = (Button) inflate.findViewById(R.id.stop_quick_Grid_Charge_Button);
        String string2 = getString(R.string.stop_quick_charge);
        SpannableString spannableString2 = new SpannableString(string2 + " ↗");
        spannableString2.setSpan(new StyleSpan(1), 0, string2.length(), 33);
        spannableString2.setSpan(new StyleSpan(1), string2.length(), string2.length() + 2, 33);
        spannableString2.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString2.length(), 18);
        this.stopQuickGridChargeButton.setText(spannableString2);
        this.stopQuickGridChargeButton.setTextSize(1, 12.0f);
        this.stopQuickGridChargeButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OverviewFragment.this.inverter != null) {
                    Lv1OverviewFragment.this.stopQuickGridChargeButton.setEnabled(false);
                    Lv1OverviewFragment lv1OverviewFragment = Lv1OverviewFragment.this;
                    new QuickChargeCtrlTask(lv1OverviewFragment, lv1OverviewFragment.stopQuickGridChargeButton).execute(false);
                }
            }
        });
        this.genExerciseStartButton = (Button) inflate.findViewById(R.id.start_GEN_Exercise_Button);
        String string3 = getString(R.string.start_gen_exercise);
        SpannableString spannableString3 = new SpannableString(string3 + " ↗");
        spannableString3.setSpan(new StyleSpan(1), 0, string3.length(), 33);
        spannableString3.setSpan(new StyleSpan(1), string3.length(), string3.length() + 2, 33);
        spannableString3.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString3.length(), 18);
        this.genExerciseStartButton.setText(spannableString3);
        this.genExerciseStartButton.setTextSize(1, 12.0f);
        this.genExerciseStartButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OverviewFragment.this.inverter != null) {
                    Lv1OverviewFragment.this.genExerciseStartButton.setEnabled(false);
                    Lv1OverviewFragment lv1OverviewFragment = Lv1OverviewFragment.this;
                    new GenExerciseCtrlTask(lv1OverviewFragment, lv1OverviewFragment.genExerciseStartButton).execute(true);
                }
            }
        });
        this.stopGenExerciseStartButton = (Button) inflate.findViewById(R.id.stop_GEN_Exercise_Button);
        String string4 = getString(R.string.start_gen_exercise);
        SpannableString spannableString4 = new SpannableString(string4 + " ↗");
        spannableString4.setSpan(new StyleSpan(1), 0, string4.length(), 33);
        spannableString4.setSpan(new StyleSpan(1), string4.length(), string4.length() + 2, 33);
        spannableString4.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString4.length(), 18);
        this.stopGenExerciseStartButton.setText(spannableString4);
        this.stopGenExerciseStartButton.setTextSize(1, 12.0f);
        this.stopGenExerciseStartButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1OverviewFragment.this.inverter != null) {
                    Lv1OverviewFragment.this.stopGenExerciseStartButton.setEnabled(false);
                    Lv1OverviewFragment lv1OverviewFragment = Lv1OverviewFragment.this;
                    new GenExerciseCtrlTask(lv1OverviewFragment, lv1OverviewFragment.stopGenExerciseStartButton).execute(false);
                }
            }
        });
        this.newButton2Layout = (ConstraintLayout) inflate.findViewById(R.id.new_button2_layout);
        this.batteryDataButton = (Button) inflate.findViewById(R.id.with_battery_Data_Button);
        String string5 = getString(R.string.battery_params);
        SpannableString spannableString5 = new SpannableString(string5 + " ↗");
        spannableString5.setSpan(new StyleSpan(1), 0, string5.length(), 33);
        spannableString5.setSpan(new StyleSpan(1), string5.length(), string5.length() + 2, 33);
        spannableString5.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString5.length(), 18);
        this.batteryDataButton.setText(spannableString5);
        this.batteryDataButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OverviewFragment.this.startActivity(new Intent(Lv1OverviewFragment.this.getActivity(), (Class<?>) InverterBatteryParamsActivity.class));
            }
        });
        this.epsLayout = (ConstraintLayout) inflate.findViewById(R.id.eps_Layout);
        this.batteryParamImageView = (ImageView) inflate.findViewById(R.id.batteryParamImageView);
        this.epsParamImageView = (ImageView) inflate.findViewById(R.id.epsParamImageView);
        Button button = (Button) inflate.findViewById(R.id.fragment_flow_refreshButton);
        this.refreshButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1OverviewFragment.this.refreshData();
            }
        });
        this.created = true;
        if (this.inverterList.isEmpty()) {
            initFlowChartByDeviceType();
        }
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$0$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment, reason: not valid java name */
    public /* synthetic */ boolean m358xa4c41fdc(View view, MotionEvent motionEvent) {
        if (this.currentBatteryEnergyViewIndex == 1) {
            this.batteryEnergyTitleLabel.setText(R.string.main_overview_info_box_battery_charging);
            this.batteryEnergyViewLayout1.setVisibility(8);
            this.batteryEnergyViewLayout2.setVisibility(0);
            this.currentBatteryEnergyViewIndex = 2;
        } else {
            this.batteryEnergyTitleLabel.setText(R.string.main_overview_info_box_battery_discharging);
            this.batteryEnergyViewLayout2.setVisibility(8);
            this.batteryEnergyViewLayout1.setVisibility(0);
            this.currentBatteryEnergyViewIndex = 1;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$1$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment, reason: not valid java name */
    public /* synthetic */ boolean m359xf28397dd(View view, MotionEvent motionEvent) {
        if (this.currentFeedinEnergyViewIndex == 1) {
            this.feedinEnergyTitleLabel.setText(R.string.main_overview_info_box_import);
            this.feedinEnergyViewLayout1.setVisibility(8);
            this.feedinEnergyViewLayout2.setVisibility(0);
            this.currentFeedinEnergyViewIndex = 2;
        } else {
            this.feedinEnergyTitleLabel.setText(R.string.main_overview_info_box_feed_in_discharging);
            this.feedinEnergyViewLayout2.setVisibility(8);
            this.feedinEnergyViewLayout1.setVisibility(0);
            this.currentFeedinEnergyViewIndex = 1;
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - Lv1OverviewFragment.onResume...");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    public long getLastRefreshDataTime() {
        return this.lastRefreshDataTime;
    }

    public void refreshData() {
        if (this.created) {
            if (this.inverter != null) {
                this.lastRefreshDataTime = System.currentTimeMillis();
                this.refreshButton.setEnabled(false);
                CountDownTimer countDownTimer = this.countDownTimer;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lv1OverviewFragment.this.m361x2717ca2a();
                    }
                }).start();
                return;
            }
            this.waitRequestCount = 2;
            m360xd9585229();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$refreshData$3$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment, reason: not valid java name */
    public /* synthetic */ void m361x2717ca2a() {
        FragmentActivity activity;
        Runnable runnable;
        try {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("inverterSn", this.inverter.isParallelGroup() ? this.inverter.getParallelFirstDeviceSn() : this.inverter.getSerialNum());
                JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteTransfer/refreshInputData", hashMap);
                if (postJson != null && postJson.getBoolean("success")) {
                    Tool.sleep(3000L);
                }
                this.waitRequestCount = 2;
                activity = getActivity();
            } catch (JSONException e) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.phrase_toast_response_error, 1).show();
                e.printStackTrace();
                this.waitRequestCount = 2;
                activity = getActivity();
                if (activity == null) {
                    return;
                } else {
                    runnable = new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            Lv1OverviewFragment.this.m360xd9585229();
                        }
                    };
                }
            }
            if (activity != null) {
                runnable = new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lv1OverviewFragment.this.m360xd9585229();
                    }
                };
                activity.runOnUiThread(runnable);
            }
        } catch (Throwable th) {
            this.waitRequestCount = 2;
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                activity2.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lv1OverviewFragment.this.m360xd9585229();
                    }
                });
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFlowChartByDeviceType() {
        if (this.created) {
            ImageView imageView = this.generatorImageView;
            if (imageView != null) {
                imageView.setVisibility(4);
            }
            ImageView imageView2 = this.gridImageView;
            boolean z = false;
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
            clearBattInfo();
            clearEpsLnInfo();
            Button button = this.powerRateButton;
            if (button != null) {
                button.setText("");
            }
            Button button2 = this.firmwareButton;
            if (button2 != null) {
                button2.setText("");
            }
            Inverter inverter = this.inverter;
            if (inverter != null && inverter.isParallelGroup()) {
                this.centered1Layout.setVisibility(8);
                this.centered2Layout.setVisibility(8);
            } else {
                this.centeredLayout.setVisibility(0);
                this.centered1Layout.setVisibility(0);
                this.centered2Layout.setVisibility(0);
            }
            Inverter inverter2 = this.inverter;
            if (inverter2 != null && !inverter2.isAllowGenExercise()) {
                this.genExerciseStartButton.setVisibility(4);
                this.stopGenExerciseStartButton.setVisibility(4);
            }
            Inverter inverter3 = this.inverter;
            if (inverter3 != null && inverter3.isAcCharger()) {
                z = true;
            }
            initFlowChartByDeviceExceptDeviceImg(z);
            refreshDeviceImageSource();
        }
    }

    private void refreshDeviceImageSource() {
        System.out.println("LuxPower - refreshDeviceImageSource...");
        if (!this.created || this.flowInverterImageView == null) {
            return;
        }
        Inverter inverter = this.inverter;
        if (inverter != null && inverter.isParallelGroup()) {
            this.flowInverterImageView.setBackgroundResource(R.drawable.icon_inverter_parallel);
            return;
        }
        Integer num = null;
        if (PLATFORM.LUX_POWER.equals(GlobalInfo.getInstance().getUserData().getPlatform())) {
            Inverter inverter2 = this.inverter;
            if (inverter2 != null && inverter2.isAcCharger()) {
                num = Integer.valueOf(R.drawable.icon_dt_ac_charger);
            } else {
                Inverter inverter3 = this.inverter;
                if (inverter3 != null && inverter3.isSnaSeries()) {
                    if (this.inverter.getSubDeviceTypeValue() == 131) {
                        num = Integer.valueOf(R.drawable.icon_dt_sna_us_6k);
                    } else if (this.inverter.getSubDeviceTypeValue() == 1111) {
                        num = Integer.valueOf(R.drawable.icon_sna_12k_us);
                    } else if (this.inverter.getSubDeviceTypeValue() == 1110) {
                        num = Integer.valueOf(R.drawable.icon_sna_12k_eu);
                    } else {
                        num = Integer.valueOf(R.drawable.icon_dt_inverter_off_grid);
                    }
                } else {
                    Inverter inverter4 = this.inverter;
                    if (inverter4 != null && inverter4.isType6()) {
                        if (this.inverter.getSubDeviceTypeValue() == 161) {
                            num = Integer.valueOf(R.drawable.icon_dt_type6_8_10k_eu);
                        } else if (this.inverter.getSubDeviceTypeValue() == 162) {
                            num = Integer.valueOf(R.drawable.icon_dt_type6_12k_eu);
                        } else if (this.inverter.getSubDeviceTypeValue() == 163) {
                            num = Integer.valueOf(R.drawable.icon_dt_type6_8_10k_us);
                        } else if (this.inverter.getSubDeviceTypeValue() == 164) {
                            num = Integer.valueOf(R.drawable.icon_dt_type6_12k_us);
                        }
                    } else {
                        Inverter inverter5 = this.inverter;
                        if (inverter5 != null && inverter5.isAllInOne()) {
                            num = Integer.valueOf(R.drawable.icon_dt_all_in_one);
                        } else {
                            Inverter inverter6 = this.inverter;
                            if (inverter6 != null && inverter6.isTrip12K()) {
                                num = Integer.valueOf(R.drawable.icon_dt_trip);
                            } else {
                                Inverter inverter7 = this.inverter;
                                if (inverter7 != null && inverter7.is7_10KDevice()) {
                                    num = Integer.valueOf(R.drawable.icon_gen_7_10k);
                                } else {
                                    Inverter inverter8 = this.inverter;
                                    if (inverter8 != null && inverter8.isGen3_6K()) {
                                        num = Integer.valueOf(R.drawable.icon_gen_3_6k);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (num == null) {
                num = Integer.valueOf(R.drawable.icon_dt_inverter);
            }
        } else {
            Inverter inverter9 = this.inverter;
            if (inverter9 != null && inverter9.isAcCharger()) {
                num = Integer.valueOf(R.drawable.icon_dt_ac_charger_mid);
            } else {
                Inverter inverter10 = this.inverter;
                if (inverter10 != null && inverter10.isSnaSeries()) {
                    if (this.inverter.getSubDeviceTypeValue() == 131) {
                        num = Integer.valueOf(R.drawable.icon_dt_sna_us_6k_mid);
                    } else if (this.inverter.getSubDeviceTypeValue() == 1111) {
                        num = Integer.valueOf(R.drawable.icon_sna_12k_us_mid);
                    } else if (this.inverter.getSubDeviceTypeValue() == 1110) {
                        num = Integer.valueOf(R.drawable.icon_sna_12k_eu_mid);
                    } else {
                        num = Integer.valueOf(R.drawable.icon_dt_inverter_off_grid_mid);
                    }
                } else {
                    Inverter inverter11 = this.inverter;
                    if (inverter11 != null && inverter11.isType6()) {
                        if (this.inverter.getSubDeviceTypeValue() == 161) {
                            num = Integer.valueOf(R.drawable.icon_dt_type6_8_10k_eu_mid);
                        } else if (this.inverter.getSubDeviceTypeValue() == 162) {
                            num = Integer.valueOf(R.drawable.icon_dt_type6_12k_eu_mid);
                        } else if (this.inverter.getSubDeviceTypeValue() == 163) {
                            num = Integer.valueOf(R.drawable.icon_dt_type6_8_10k_us_mid);
                        } else if (this.inverter.getSubDeviceTypeValue() == 164) {
                            num = Integer.valueOf(R.drawable.icon_dt_type6_12k_us_mid);
                        }
                    } else {
                        Inverter inverter12 = this.inverter;
                        if (inverter12 != null && inverter12.isAllInOne()) {
                            num = Integer.valueOf(R.drawable.icon_dt_all_in_one_mid);
                        } else {
                            Inverter inverter13 = this.inverter;
                            if (inverter13 != null && inverter13.isTrip12K()) {
                                num = Integer.valueOf(R.drawable.icon_dt_trip_mid);
                            } else {
                                Inverter inverter14 = this.inverter;
                                if (inverter14 != null && inverter14.is7_10KDevice()) {
                                    num = Integer.valueOf(R.drawable.icon_gen_7_10k_mid);
                                } else {
                                    Inverter inverter15 = this.inverter;
                                    if (inverter15 != null && inverter15.isGen3_6K()) {
                                        num = Integer.valueOf(R.drawable.icon_gen_3_6k_mid);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (num == null) {
                num = Integer.valueOf(R.drawable.icon_dt_inverter_mid);
            }
        }
        this.flowInverterImageView.setBackgroundResource(num.intValue());
    }

    private void initFlowChartByDeviceExceptDeviceImg(boolean z) {
        if (z) {
            this.flowPvPowerImageView.setVisibility(4);
            this.flowPvPowerTextView.setVisibility(4);
            this.flowPvPowerLabelTextView.setVisibility(4);
            this.flowPvPowerGifView.setVisibility(4);
            this.flowAcPvPowerImageView.setVisibility(0);
            this.flowAcPvPowerTextView.setVisibility(0);
            this.flowAcPvPowerLabelTextView.setVisibility(0);
            return;
        }
        ImageView imageView = this.flowAcPvPowerImageView;
        if (imageView != null) {
            imageView.setVisibility(4);
        }
        TextView textView = this.flowAcPvPowerTextView;
        if (textView != null) {
            textView.setVisibility(4);
        }
        TextView textView2 = this.flowAcPvPowerLabelTextView;
        if (textView2 != null) {
            textView2.setVisibility(4);
        }
        GifView gifView = this.flowAcPvPowerGifView1;
        if (gifView != null) {
            gifView.setVisibility(4);
        }
        GifView gifView2 = this.flowAcPvPowerGifView2;
        if (gifView2 != null) {
            gifView2.setVisibility(4);
        }
        ImageView imageView2 = this.flowPvPowerImageView;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
        }
        TextView textView3 = this.flowPvPowerTextView;
        if (textView3 != null) {
            textView3.setVisibility(0);
        }
        TextView textView4 = this.flowPvPowerLabelTextView;
        if (textView4 != null) {
            textView4.setVisibility(0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        refreshFragmentParams();
    }

    public void refreshFragmentParams() {
        Inverter inverter;
        UserData userData = GlobalInfo.getInstance().getUserData();
        Inverter currentParallelGroup = userData.getCurrentParallelGroup();
        if (currentParallelGroup == null) {
            currentParallelGroup = userData.getCurrentInverter();
        }
        if (currentParallelGroup != null && this.inverterList != null) {
            for (int i = 0; i < this.inverterList.size(); i++) {
                if (this.inverterList.get(i).getSerialNum().equals(currentParallelGroup.getSerialNum()) && ((inverter = this.inverter) == null || !inverter.getSerialNum().equals(currentParallelGroup.getSerialNum()))) {
                    this.inverterSpinner.setSelection(i);
                }
            }
        }
        initFlowChartByDeviceType();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003d A[ADDED_TO_REGION] */
    /* renamed from: reloadFragmentData, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m360xd9585229() {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.created     // Catch: java.lang.Throwable -> L4d
            if (r0 != 0) goto L7
            monitor-exit(r8)
            return
        L7:
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L4d
            com.nfcx.luxinvpower.global.bean.inverter.Inverter r2 = r8.inverter     // Catch: java.lang.Throwable -> L4d
            r3 = 1
            if (r2 != 0) goto L15
            com.nfcx.luxinvpower.global.bean.inverter.Inverter r2 = r8.lastReloadInverter     // Catch: java.lang.Throwable -> L4d
            if (r2 == 0) goto L32
            goto L33
        L15:
            com.nfcx.luxinvpower.global.bean.inverter.Inverter r4 = r8.lastReloadInverter     // Catch: java.lang.Throwable -> L4d
            if (r4 == 0) goto L33
            java.lang.String r2 = r2.getSerialNum()     // Catch: java.lang.Throwable -> L4d
            if (r2 == 0) goto L32
            com.nfcx.luxinvpower.global.bean.inverter.Inverter r2 = r8.inverter     // Catch: java.lang.Throwable -> L4d
            java.lang.String r2 = r2.getSerialNum()     // Catch: java.lang.Throwable -> L4d
            com.nfcx.luxinvpower.global.bean.inverter.Inverter r4 = r8.lastReloadInverter     // Catch: java.lang.Throwable -> L4d
            java.lang.String r4 = r4.getSerialNum()     // Catch: java.lang.Throwable -> L4d
            boolean r2 = r2.equals(r4)     // Catch: java.lang.Throwable -> L4d
            if (r2 != 0) goto L32
            goto L33
        L32:
            r3 = 0
        L33:
            long r4 = r8.lastReloadTime     // Catch: java.lang.Throwable -> L4d
            long r4 = r0 - r4
            r6 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 > 0) goto L3f
            if (r3 == 0) goto L4b
        L3f:
            com.nfcx.luxinvpower.global.bean.inverter.Inverter r2 = r8.inverter     // Catch: java.lang.Throwable -> L4d
            r8.lastReloadInverter = r2     // Catch: java.lang.Throwable -> L4d
            r8.lastReloadTime = r0     // Catch: java.lang.Throwable -> L4d
            r8.refreshEnergyInfo()     // Catch: java.lang.Throwable -> L4d
            r8.refreshFlowChart()     // Catch: java.lang.Throwable -> L4d
        L4b:
            monitor-exit(r8)
            return
        L4d:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.m360xd9585229():void");
    }

    private void refreshEnergyInfo() {
        if (this.inverter != null) {
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    Lv1OverviewFragment.this.m363x84284858();
                }
            }).start();
        } else {
            clearEnergyInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$refreshEnergyInfo$5$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment, reason: not valid java name */
    public /* synthetic */ void m363x84284858() {
        HashMap hashMap = new HashMap();
        hashMap.put("serialNum", this.inverter.isParallelGroup() ? this.inverter.getParallelFirstDeviceSn() : this.inverter.getSerialNum());
        final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/inverter/getInverterEnergyInfo" + (this.inverter.isParallelGroup() ? "Parallel" : ""), hashMap);
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    Lv1OverviewFragment.this.m362x3668d057(postJson, activity);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$refreshEnergyInfo$4$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment, reason: not valid java name */
    public /* synthetic */ void m362x3668d057(JSONObject jSONObject, FragmentActivity fragmentActivity) {
        try {
            int i = this.waitRequestCount;
            if (i > 0) {
                int i2 = i - 1;
                this.waitRequestCount = i2;
                if (i2 == 0) {
                    this.refreshButton.setEnabled(true);
                }
            }
            if (jSONObject != null && jSONObject.getBoolean("success")) {
                if (jSONObject.getBoolean("hasRuntimeData")) {
                    this.todayYieldingTextView.setText(jSONObject.getString("todayYieldingText") + " kWh");
                    this.totalYieldingTextView.setText(jSONObject.getString("totalYieldingText") + " kWh");
                    this.todayDischargingTextView.setText(jSONObject.getString("todayDischargingText") + " kWh");
                    this.totalDischargingTextView.setText(jSONObject.getString("totalDischargingText") + " kWh");
                    this.todayChargingTextView.setText(jSONObject.getString("todayChargingText") + " kWh");
                    this.totalChargingTextView.setText(jSONObject.getString("totalChargingText") + " kWh");
                    this.todayExportTextView.setText(jSONObject.getString("todayExportText") + " kWh");
                    this.totalExportTextView.setText(jSONObject.getString("totalExportText") + " kWh");
                    this.todayImportTextView.setText(jSONObject.getString("todayImportText") + " kWh");
                    this.totalImportTextView.setText(jSONObject.getString("totalImportText") + " kWh");
                    this.todayUsageTextView.setText(jSONObject.getString("todayUsageText") + " kWh");
                    this.totalUsageTextView.setText(jSONObject.getString("totalUsageText") + " kWh");
                    this.pvLoadToday.setText((jSONObject.getInt("pvPieUsageTodayRate") / 10.0d) + "%");
                    this.pvChargeToday.setText((jSONObject.getInt("pvPieChargeTodayRate") / 10.0d) + "%");
                    this.pvExportToday.setText((jSONObject.getInt("pvPieExportTodayRate") / 10.0d) + "%");
                    this.todayAllPvEnergy.setText(jSONObject.getString("todayYieldingText") + " kWh");
                    this.pvLoadTotal.setText((jSONObject.getInt("pvPieUsageTotalRate") / 10.0d) + "%");
                    this.pvChargeTotal.setText((jSONObject.getInt("pvPieChargeTotalRate") / 10.0d) + "%");
                    this.pvExportTotal.setText((jSONObject.getInt("pvPieExportTotalRate") / 10.0d) + "%");
                    this.totalAllPvEnergy.setText(jSONObject.getString("totalYieldingText") + " kWh");
                    int i3 = jSONObject.getInt("pvPieUsageTodayRate");
                    int i4 = jSONObject.getInt("pvPieChargeTodayRate");
                    int i5 = jSONObject.getInt("pvPieExportTodayRate");
                    if (i3 == 0 && i4 == 0 && i5 == 0) {
                        this.shouldShowTodayCircleView = true;
                        if (this.currentPvEnergyViewIndex == 2) {
                            this.todayCircleView.setVisibility(0);
                        }
                    } else {
                        this.shouldShowTodayCircleView = false;
                        this.todayCircleView.setVisibility(8);
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new PieEntry((float) (i3 / 10.0d)));
                    arrayList.add(new PieEntry((float) (i4 / 10.0d)));
                    arrayList.add(new PieEntry((float) (i5 / 10.0d)));
                    PieDataSet pieDataSet = this.todayPvPieDataSet;
                    if (pieDataSet == null) {
                        PieDataSet pieDataSet2 = new PieDataSet(arrayList, "Today Pv Pie");
                        this.todayPvPieDataSet = pieDataSet2;
                        pieDataSet2.setColors(pvPieColors);
                    } else {
                        pieDataSet.setValues(arrayList);
                    }
                    this.todayPvPieChart.setData(new PieData(this.todayPvPieDataSet));
                    this.todayPvPieChart.invalidate();
                    int i6 = jSONObject.getInt("pvPieUsageTotalRate");
                    int i7 = jSONObject.getInt("pvPieChargeTotalRate");
                    int i8 = jSONObject.getInt("pvPieExportTotalRate");
                    if (i6 == 0 && i7 == 0 && i8 == 0) {
                        this.shouldShowTotalCircleView = true;
                        if (this.currentPvEnergyViewIndex == 3) {
                            this.totalCircleView.setVisibility(0);
                        }
                    } else {
                        this.shouldShowTotalCircleView = false;
                        this.totalCircleView.setVisibility(8);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new PieEntry((float) (i6 / 10.0d)));
                    arrayList2.add(new PieEntry((float) (i7 / 10.0d)));
                    arrayList2.add(new PieEntry((float) (i8 / 10.0d)));
                    PieDataSet pieDataSet3 = this.totalPvPieDataSet;
                    if (pieDataSet3 == null) {
                        PieDataSet pieDataSet4 = new PieDataSet(arrayList2, "Total Pv Pie");
                        this.totalPvPieDataSet = pieDataSet4;
                        pieDataSet4.setColors(pvPieColors);
                    } else {
                        pieDataSet3.setValues(arrayList2);
                    }
                    this.totalPvPieChart.setData(new PieData(this.totalPvPieDataSet));
                    this.totalPvPieChart.invalidate();
                    return;
                }
                clearEnergyInfo();
                return;
            }
            try {
                toastByResult(fragmentActivity, jSONObject);
            } catch (JSONException e) {
                e = e;
                Toast.makeText(fragmentActivity.getApplicationContext(), R.string.phrase_toast_response_error, 1).show();
                e.printStackTrace();
            }
        } catch (JSONException e2) {
            e = e2;
        }
    }

    private void clearEnergyInfo() {
        this.todayYieldingTextView.setText("-- kWh");
        this.totalYieldingTextView.setText("-- kWh");
        this.todayDischargingTextView.setText("-- kWh");
        this.totalDischargingTextView.setText("-- kWh");
        this.todayChargingTextView.setText("-- kWh");
        this.totalChargingTextView.setText("-- kWh");
        this.todayExportTextView.setText("-- kWh");
        this.totalExportTextView.setText("-- kWh");
        this.todayImportTextView.setText("-- kWh");
        this.totalImportTextView.setText("-- kWh");
        this.todayUsageTextView.setText("-- kWh");
        this.totalUsageTextView.setText("-- kWh");
        this.pvLoadToday.setText("--%");
        this.pvChargeToday.setText("--%");
        this.pvExportToday.setText("--%");
        this.todayAllPvEnergy.setText("-- kWh");
        this.todayPvPieChart.clear();
        this.pvLoadTotal.setText("--%");
        this.pvChargeTotal.setText("--%");
        this.pvExportTotal.setText("--%");
        this.totalAllPvEnergy.setText("-- kWh");
        this.totalPvPieChart.clear();
        this.shouldShowTodayCircleView = true;
        this.shouldShowTotalCircleView = true;
        int i = this.currentPvEnergyViewIndex;
        if (i == 2) {
            this.todayCircleView.setVisibility(0);
            this.totalCircleView.setVisibility(8);
        } else if (i == 3) {
            this.todayCircleView.setVisibility(8);
            this.totalCircleView.setVisibility(0);
        } else {
            this.todayCircleView.setVisibility(8);
            this.totalCircleView.setVisibility(8);
        }
    }

    private void refreshFlowChart() {
        if (this.inverter != null) {
            UserData userData = GlobalInfo.getInstance().getUserData();
            if (userData.getCurrentPlant() != null && !userData.getCurrentPlant().getParallelGroups().isEmpty()) {
                refreshDeviceImageSource();
                if (this.inverter.isParallelGroup()) {
                    this.flowBatVoltTextView.setVisibility(8);
                } else {
                    this.flowBatVoltTextView.setVisibility(0);
                }
            }
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    Lv1OverviewFragment.this.m364x101bddd7();
                }
            }).start();
            return;
        }
        clearFlowChart();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$refreshFlowChart$6$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment, reason: not valid java name */
    public /* synthetic */ void m364x101bddd7() {
        if (this.inverter.isParallelGroup()) {
            refreshFlowChartForParallelAtThread();
        } else {
            refreshFlowChartForSingleAtThread();
        }
    }

    private void refreshFlowChartForParallelAtThread() {
        HashMap hashMap = new HashMap();
        hashMap.put("serialNum", this.inverter.getParallelFirstDeviceSn());
        final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/inverter/getInverterRuntimeParallel", hashMap);
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    Lv1OverviewFragment.this.m365x2d3f3081(postJson, activity);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:106:0x08e6 A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x094b A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x096f A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0909  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x08b2  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x044e A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0341 A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0274 A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x019d A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0165 A[Catch: all -> 0x0987, JSONException -> 0x098a, TRY_ENTER, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0305 A[Catch: all -> 0x0987, JSONException -> 0x098a, TRY_ENTER, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x03fb A[Catch: all -> 0x0987, JSONException -> 0x098a, TRY_ENTER, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x041d A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x042f A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0439 A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x045c A[Catch: all -> 0x0987, JSONException -> 0x098a, TRY_LEAVE, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x088a A[Catch: all -> 0x0987, JSONException -> 0x098a, TryCatch #1 {all -> 0x0987, blocks: (B:3:0x0015, B:5:0x0019, B:7:0x001e, B:8:0x0023, B:10:0x0078, B:12:0x0080, B:14:0x00a0, B:16:0x00a8, B:18:0x00ae, B:19:0x00b7, B:21:0x00bf, B:23:0x00c9, B:26:0x00d3, B:28:0x00db, B:29:0x00ec, B:31:0x00f4, B:33:0x00fa, B:35:0x0100, B:36:0x0157, B:39:0x0165, B:40:0x01f8, B:43:0x0230, B:44:0x02fd, B:47:0x0305, B:49:0x0328, B:51:0x03f1, B:54:0x03fb, B:56:0x0401, B:60:0x0411, B:62:0x041d, B:63:0x0427, B:65:0x042f, B:67:0x0439, B:71:0x045c, B:76:0x046e, B:79:0x0479, B:82:0x04c1, B:83:0x04ce, B:85:0x04ea, B:87:0x0508, B:88:0x055c, B:90:0x0564, B:92:0x05cc, B:94:0x05d4, B:97:0x088a, B:100:0x0895, B:103:0x08a3, B:104:0x08d8, B:106:0x08e6, B:107:0x0943, B:109:0x094b, B:111:0x0953, B:114:0x0961, B:115:0x096f, B:120:0x0910, B:121:0x0921, B:125:0x08b4, B:126:0x08cd, B:127:0x05dd, B:128:0x050f, B:129:0x0516, B:132:0x052d, B:134:0x0545, B:135:0x0551, B:136:0x04c8, B:139:0x05e7, B:141:0x05ee, B:144:0x05f8, B:147:0x06c8, B:152:0x0723, B:153:0x07d5, B:155:0x07ef, B:157:0x086e, B:159:0x0876, B:160:0x087f, B:161:0x072b, B:162:0x0733, B:165:0x0754, B:168:0x0773, B:171:0x07a4, B:173:0x07be, B:174:0x07ca, B:175:0x065f, B:178:0x044e, B:181:0x0333, B:182:0x0341, B:184:0x0349, B:185:0x0351, B:188:0x035b, B:189:0x0371, B:191:0x0379, B:192:0x0381, B:195:0x038b, B:196:0x03c2, B:198:0x03e5, B:199:0x03ec, B:200:0x03b3, B:202:0x0367, B:205:0x023c, B:207:0x0248, B:209:0x0254, B:211:0x0260, B:212:0x026a, B:213:0x0274, B:216:0x027e, B:218:0x028a, B:220:0x0296, B:222:0x02a1, B:224:0x02ac, B:225:0x02b5, B:227:0x02c0, B:229:0x02cb, B:231:0x02d6, B:233:0x02e1, B:235:0x02ec, B:236:0x02f5, B:237:0x019d, B:239:0x01a4, B:240:0x01df, B:241:0x0127, B:243:0x012c, B:244:0x012f, B:245:0x0145, B:247:0x097b, B:250:0x0981, B:254:0x098e), top: B:2:0x0015 }] */
    /* JADX WARN: Type inference failed for: r16v0, types: [com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$12] */
    /* renamed from: lambda$refreshFlowChartForParallelAtThread$7$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void m365x2d3f3081(org.json.JSONObject r22, androidx.fragment.app.FragmentActivity r23) {
        /*
            Method dump skipped, instructions count: 2472
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.m365x2d3f3081(org.json.JSONObject, androidx.fragment.app.FragmentActivity):void");
    }

    private void refreshFlowChartForSingleAtThread() {
        HashMap hashMap = new HashMap();
        hashMap.put("serialNum", this.inverter.getSerialNum());
        final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/inverter/getInverterRuntime", hashMap);
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    Lv1OverviewFragment.this.m366x4214e343(postJson, activity);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0876 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0898 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0860 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x082d A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x079d A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0734  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x06ca  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0167 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01a6 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01e7 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0694 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0711 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0778  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0791 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x07b0 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0834 A[Catch: all -> 0x08b0, JSONException -> 0x08b3, TryCatch #1 {all -> 0x08b0, blocks: (B:3:0x001c, B:5:0x0053, B:7:0x0058, B:8:0x005d, B:10:0x006b, B:12:0x0073, B:14:0x0079, B:15:0x008f, B:17:0x0095, B:18:0x00ab, B:204:0x00b3, B:206:0x00bd, B:209:0x00c7, B:211:0x00cf, B:212:0x00e0, B:214:0x00e8, B:216:0x00ee, B:218:0x00f4, B:22:0x0161, B:24:0x0167, B:26:0x0171, B:27:0x019e, B:29:0x01a6, B:31:0x01b2, B:32:0x01da, B:33:0x01df, B:35:0x01e7, B:37:0x01ef, B:39:0x0219, B:41:0x0221, B:42:0x0254, B:44:0x025c, B:45:0x029c, B:48:0x02ac, B:49:0x033f, B:52:0x039c, B:53:0x0469, B:55:0x0471, B:57:0x0494, B:59:0x0559, B:61:0x056e, B:62:0x0576, B:66:0x0586, B:68:0x0678, B:70:0x0694, B:73:0x069f, B:76:0x06ad, B:79:0x06bb, B:80:0x06fd, B:82:0x0711, B:83:0x0770, B:86:0x0779, B:88:0x0791, B:89:0x07a8, B:91:0x07b0, B:93:0x081c, B:95:0x0824, B:98:0x0834, B:100:0x0852, B:101:0x086e, B:103:0x0876, B:105:0x087e, B:109:0x088b, B:110:0x0898, B:111:0x0859, B:112:0x0860, B:113:0x082d, B:114:0x079d, B:120:0x073d, B:121:0x074e, B:126:0x06cc, B:127:0x06ed, B:128:0x05d1, B:131:0x05dd, B:132:0x062e, B:134:0x049f, B:135:0x04ac, B:137:0x04b4, B:138:0x04bc, B:141:0x04c6, B:142:0x04db, B:144:0x04e3, B:145:0x04eb, B:148:0x04f5, B:149:0x052b, B:151:0x054e, B:152:0x0554, B:153:0x051c, B:155:0x04d1, B:158:0x03a8, B:160:0x03b4, B:162:0x03c0, B:164:0x03cc, B:165:0x03d6, B:166:0x03e0, B:169:0x03ea, B:171:0x03f6, B:173:0x0402, B:175:0x040d, B:177:0x0418, B:178:0x0421, B:180:0x042c, B:182:0x0437, B:184:0x0442, B:186:0x044d, B:188:0x0458, B:189:0x0461, B:190:0x02e5, B:192:0x02ed, B:193:0x0326, B:194:0x028a, B:195:0x024f, B:196:0x0290, B:199:0x0299, B:200:0x08a4, B:201:0x0196, B:222:0x0123, B:224:0x0127, B:225:0x012a, B:227:0x013d, B:233:0x08b7, B:235:0x0145, B:238:0x00a6, B:239:0x008a, B:242:0x08aa), top: B:2:0x001c }] */
    /* JADX WARN: Type inference failed for: r16v0, types: [com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$13] */
    /* JADX WARN: Type inference failed for: r1v141, types: [com.nfcx.luxinvpower.global.custom.view.GifView] */
    /* JADX WARN: Type inference failed for: r1v142, types: [com.nfcx.luxinvpower.global.custom.view.GifView] */
    /* JADX WARN: Type inference failed for: r1v155, types: [com.nfcx.luxinvpower.global.custom.view.GifView] */
    /* JADX WARN: Type inference failed for: r1v156, types: [com.nfcx.luxinvpower.global.custom.view.GifView] */
    /* JADX WARN: Type inference failed for: r1v53, types: [com.nfcx.luxinvpower.global.custom.view.GifView] */
    /* JADX WARN: Type inference failed for: r1v54, types: [com.nfcx.luxinvpower.global.custom.view.GifView] */
    /* JADX WARN: Type inference failed for: r1v88, types: [com.nfcx.luxinvpower.global.custom.view.GifView] */
    /* JADX WARN: Type inference failed for: r1v89, types: [android.widget.ImageView] */
    /* JADX WARN: Type inference failed for: r1v90, types: [android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r1v94, types: [com.nfcx.luxinvpower.global.custom.view.GifView] */
    /* JADX WARN: Type inference failed for: r1v95, types: [com.nfcx.luxinvpower.global.custom.view.GifView] */
    /* JADX WARN: Type inference failed for: r4v38, types: [android.widget.ImageView] */
    /* JADX WARN: Type inference failed for: r4v45, types: [androidx.constraintlayout.widget.ConstraintLayout] */
    /* JADX WARN: Type inference failed for: r4v46, types: [android.widget.ImageView] */
    /* JADX WARN: Type inference failed for: r4v47, types: [android.widget.Button] */
    /* JADX WARN: Type inference failed for: r4v49, types: [androidx.constraintlayout.widget.ConstraintLayout] */
    /* JADX WARN: Type inference failed for: r4v50, types: [android.widget.ImageView] */
    /* JADX WARN: Type inference failed for: r4v51, types: [android.widget.Button] */
    /* JADX WARN: Type inference failed for: r4v54, types: [androidx.constraintlayout.widget.ConstraintLayout] */
    /* JADX WARN: Type inference failed for: r4v55, types: [android.widget.ImageView] */
    /* JADX WARN: Type inference failed for: r4v56, types: [android.widget.Button] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12, types: [int] */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* renamed from: lambda$refreshFlowChartForSingleAtThread$8$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void m366x4214e343(org.json.JSONObject r26, androidx.fragment.app.FragmentActivity r27) {
        /*
            Method dump skipped, instructions count: 2257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment.m366x4214e343(org.json.JSONObject, androidx.fragment.app.FragmentActivity):void");
    }

    public void adJustHideButton() {
        boolean z = this.quickGridChargeButton.getVisibility() == 4;
        boolean z2 = this.stopQuickGridChargeButton.getVisibility() == 4;
        boolean z3 = this.genExerciseStartButton.getVisibility() == 4;
        boolean z4 = this.stopGenExerciseStartButton.getVisibility() == 4;
        if (z && z2 && z3 && z4) {
            this.newButtonLayout.setVisibility(8);
        } else {
            this.newButtonLayout.setVisibility(0);
        }
    }

    private int getStatusResourceId(String str) {
        if (Tool.isEmpty(str)) {
            return R.drawable.status_offline;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1039745817:
                if (str.equals("normal")) {
                    c = 0;
                    break;
                }
                break;
            case 96784904:
                if (str.equals(Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
                    c = 1;
                    break;
                }
                break;
            case 1124446108:
                if (str.equals("warning")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.drawable.status_normal;
            case 1:
                return R.drawable.status_error;
            case 2:
                return R.drawable.status_warning;
            default:
                return R.drawable.status_offline;
        }
    }

    private void clearFlowChart() {
        this.statusImageView.setImageResource(R.drawable.status_offline);
        this.flowBatteryPowerLabelTextView.setText(R.string.main_flow_battery_power);
        this.flowBatteryPowerTextView.setText("");
        this.flowBatteryImageView.setBackgroundResource(R.drawable.flow_icon_battery_0_green);
        this.flowSocPowerTextView.setText("");
        this.flowBatVoltTextView.setText("");
        this.flowPvPowerTextView.setText("");
        this.flowAcPvPowerTextView.setText("");
        this.flowGridPowerTextView.setText("");
        this.flowGridPowerLabelTextView.setText("");
        this.flowConsumptionPowerTextView.setText("");
        this.flowEpsPowerTextView.setText("");
        this.flowVacTextView.setText("");
        this.flowFacTextView.setText("");
        this.gridPowerValue1TextView.setText("");
        this.epsValue1TextView.setText("");
        this.epsValue2TextView.setText("");
        this.consumptionPowerValue1TextView.setText("");
        this.consumptionPowerValue2TextView.setText("");
        this.localTimeTextView.setText("");
        this.flowBatteryPowerGifView1.setVisibility(4);
        this.flowBatteryPowerGifView2.setVisibility(4);
        this.flowPvPowerGifView.setVisibility(4);
        this.flowAcPvPowerGifView1.setVisibility(4);
        this.flowAcPvPowerGifView2.setVisibility(4);
        this.flowInverterArrowGifView1.setVisibility(4);
        this.flowInverterArrowGifView2.setVisibility(4);
        this.flowInverterArrowGifView3.setVisibility(4);
        this.flowGridPowerGifView1.setVisibility(4);
        this.flowGridPowerGifView2.setVisibility(4);
        this.flowConsumptionPowerGifView1.setVisibility(4);
        this.flowConsumptionPowerGifView2.setVisibility(4);
        this.flowEpsPowerGifView.setVisibility(4);
        this.quickGridChargeButton.setVisibility(4);
        this.stopQuickGridChargeButton.setVisibility(4);
        this.genExerciseStartButton.setVisibility(4);
        this.stopGenExerciseStartButton.setVisibility(4);
    }

    private void clearBattInfo() {
        Button button = this.battParallelNumButton;
        if (button != null) {
            button.setVisibility(8);
            this.battParallelNumButton.setText("");
        }
        Button button2 = this.battCapacityButton;
        if (button2 != null) {
            button2.setVisibility(8);
            this.battCapacityButton.setText("");
        }
        Button button3 = this.bmsLimitChargeButton;
        if (button3 != null) {
            button3.setVisibility(8);
            this.bmsLimitChargeButton.setText("");
        }
        Button button4 = this.bmsLimitDischargeButton;
        if (button4 != null) {
            button4.setVisibility(8);
            this.bmsLimitDischargeButton.setText("");
        }
        this.batteryParamImageView.setVisibility(8);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.centered2Layout.getLayoutParams();
        layoutParams.bottomMargin = 8;
        this.centered2Layout.setLayoutParams(layoutParams);
        this.centered2Layout.setVisibility(8);
    }

    private void clearEpsLnInfo() {
        Button button = this.epsL1nButton;
        if (button != null) {
            button.setText("");
        }
        Button button2 = this.epsL2nButton;
        if (button2 != null) {
            button2.setText("");
        }
        ConstraintLayout constraintLayout = this.epsLayout;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(8);
        }
        this.epsParamImageView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class QuickChargeCtrlTask extends AsyncTask<Boolean, Object, JSONObject> {
        private Button ctrlButton;
        private Lv1OverviewFragment fragment;

        public QuickChargeCtrlTask(Lv1OverviewFragment lv1OverviewFragment, Button button) {
            this.fragment = lv1OverviewFragment;
            this.ctrlButton = button;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Boolean[] boolArr) {
            try {
                String str = boolArr[0].booleanValue() ? "start" : "stop";
                HashMap hashMap = new HashMap();
                if (this.fragment.inverter.isParallelGroup()) {
                    hashMap.put("inverterSn", this.fragment.inverter.getParallelFirstDeviceSn());
                } else {
                    hashMap.put("inverterSn", this.fragment.inverter.getSerialNum());
                }
                hashMap.put("clientType", "APP");
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/config/quickCharge/" + str, hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0 */
        /* JADX WARN: Type inference failed for: r0v8, types: [com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$QuickChargeCtrlTask$$ExternalSyntheticLambda1, java.lang.Runnable] */
        /* JADX WARN: Type inference failed for: r0v9 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v4, types: [boolean] */
        /* JADX WARN: Type inference failed for: r1v6, types: [com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment] */
        /* JADX WARN: Type inference failed for: r1v7 */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            Thread thread;
            super.onPostExecute((QuickChargeCtrlTask) jSONObject);
            int i = 0;
            i = 0;
            boolean z = 1;
            z = 1;
            try {
                try {
                    if (jSONObject != null) {
                        if (!jSONObject.getBoolean("success")) {
                            Tool.alert(this.fragment.getActivity(), R.string.phrase_toast_unknown_error);
                        }
                    } else {
                        Tool.alert(this.fragment.getActivity(), R.string.phrase_toast_network_error);
                    }
                    this.ctrlButton.setVisibility(8);
                    this.ctrlButton.setEnabled(true);
                    z = this.fragment;
                    new ReadQuickChargeTask(z).execute(new Void[0]);
                    i = new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$QuickChargeCtrlTask$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Lv1OverviewFragment.QuickChargeCtrlTask.this.m370xc276bec6();
                        }
                    };
                    thread = new Thread((Runnable) i);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.ctrlButton.setVisibility(8);
                    this.ctrlButton.setEnabled(z);
                    new ReadQuickChargeTask(this.fragment).execute(new Void[i]);
                    thread = new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$QuickChargeCtrlTask$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Lv1OverviewFragment.QuickChargeCtrlTask.this.m370xc276bec6();
                        }
                    });
                }
                thread.start();
            } catch (Throwable th) {
                this.ctrlButton.setVisibility(8);
                this.ctrlButton.setEnabled(z);
                new ReadQuickChargeTask(this.fragment).execute(new Void[i]);
                new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$QuickChargeCtrlTask$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lv1OverviewFragment.QuickChargeCtrlTask.this.m370xc276bec6();
                    }
                }).start();
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onPostExecute$1$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment$QuickChargeCtrlTask, reason: not valid java name */
        public /* synthetic */ void m370xc276bec6() {
            Tool.sleep(DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
            FragmentActivity activity = this.fragment.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$QuickChargeCtrlTask$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lv1OverviewFragment.QuickChargeCtrlTask.this.m369xa8a5145();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onPostExecute$0$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment$QuickChargeCtrlTask, reason: not valid java name */
        public /* synthetic */ void m369xa8a5145() {
            this.fragment.refreshData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class GenExerciseCtrlTask extends AsyncTask<Boolean, Object, JSONObject> {
        private Button ctrlButton;
        private Lv1OverviewFragment fragment;

        public GenExerciseCtrlTask(Lv1OverviewFragment lv1OverviewFragment, Button button) {
            this.fragment = lv1OverviewFragment;
            this.ctrlButton = button;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Boolean[] boolArr) {
            try {
                boolean booleanValue = boolArr[0].booleanValue();
                HashMap hashMap = new HashMap();
                hashMap.put("inverterSn", this.fragment.inverter.isParallelGroup() ? this.fragment.inverter.getParallelFirstDeviceSn() : this.fragment.inverter.getSerialNum());
                hashMap.put("enable", String.valueOf(booleanValue));
                hashMap.put("clientType", "APP");
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/inverter/ctrlGenExercise", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            Thread thread;
            super.onPostExecute((GenExerciseCtrlTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (!jSONObject.getBoolean("success")) {
                            Tool.alert(this.fragment.getActivity(), R.string.phrase_toast_unknown_error);
                        }
                    } else {
                        Tool.alert(this.fragment.getActivity(), R.string.phrase_toast_network_error);
                    }
                    thread = new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$GenExerciseCtrlTask$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Lv1OverviewFragment.GenExerciseCtrlTask.this.m368xbc6bde0d();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    thread = new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$GenExerciseCtrlTask$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Lv1OverviewFragment.GenExerciseCtrlTask.this.m368xbc6bde0d();
                        }
                    });
                }
                thread.start();
            } catch (Throwable th) {
                new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$GenExerciseCtrlTask$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lv1OverviewFragment.GenExerciseCtrlTask.this.m368xbc6bde0d();
                    }
                }).start();
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onPostExecute$1$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment$GenExerciseCtrlTask, reason: not valid java name */
        public /* synthetic */ void m368xbc6bde0d() {
            Tool.sleep(3000L);
            FragmentActivity activity = this.fragment.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1OverviewFragment$GenExerciseCtrlTask$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lv1OverviewFragment.GenExerciseCtrlTask.this.m367x47f708c();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onPostExecute$0$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1OverviewFragment$GenExerciseCtrlTask, reason: not valid java name */
        public /* synthetic */ void m367x47f708c() {
            this.fragment.refreshData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ReadQuickChargeTask extends AsyncTask<Void, Object, JSONObject> {
        private Lv1OverviewFragment fragment;

        public ReadQuickChargeTask(Lv1OverviewFragment lv1OverviewFragment) {
            this.fragment = lv1OverviewFragment;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Void[] voidArr) {
            try {
                HashMap hashMap = new HashMap();
                if (this.fragment.inverter.isParallelGroup()) {
                    hashMap.put("inverterSn", this.fragment.inverter.getParallelFirstDeviceSn());
                } else {
                    hashMap.put("inverterSn", this.fragment.inverter.getSerialNum());
                }
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "/web/config/quickCharge/getStatusInfo", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            int i;
            super.onPostExecute((ReadQuickChargeTask) jSONObject);
            int i2 = 4;
            try {
                if (jSONObject != null) {
                    try {
                        if (jSONObject.getBoolean("success") && jSONObject.has("hasUnclosedQuickChargeTask")) {
                            i = 0;
                            if (!jSONObject.getBoolean("hasUnclosedQuickChargeTask")) {
                                i2 = 0;
                                i = 4;
                            }
                            this.fragment.quickGridChargeButton.setVisibility(i2);
                            this.fragment.stopQuickGridChargeButton.setVisibility(i);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.fragment.quickGridChargeButton.setVisibility(4);
                        this.fragment.stopQuickGridChargeButton.setVisibility(4);
                        return;
                    }
                }
                i = 4;
                this.fragment.quickGridChargeButton.setVisibility(i2);
                this.fragment.stopQuickGridChargeButton.setVisibility(i);
            } catch (Throwable th) {
                this.fragment.quickGridChargeButton.setVisibility(4);
                this.fragment.stopQuickGridChargeButton.setVisibility(4);
                throw th;
            }
        }
    }

    private void toastByResult(FragmentActivity fragmentActivity, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            Toast.makeText(fragmentActivity.getApplicationContext(), R.string.phrase_toast_network_error, 1).show();
            return;
        }
        if (jSONObject.getInt(API.MSG_CODE) == 150) {
            Toast.makeText(fragmentActivity.getApplicationContext(), R.string.plant_toast_not_find_device, 1).show();
            return;
        }
        if (jSONObject.getInt(API.MSG_CODE) == 151) {
            Toast.makeText(fragmentActivity.getApplicationContext(), R.string.plant_toast_device_user_dismatch, 1).show();
            return;
        }
        if (jSONObject.getInt(API.MSG_CODE) == 102) {
            Toast.makeText(fragmentActivity.getApplicationContext(), R.string.phrase_toast_unlogin_error, 1).show();
        } else if (jSONObject.getInt(API.MSG_CODE) == 103) {
            Toast.makeText(fragmentActivity.getApplicationContext(), String.format(getString(R.string.phrase_toast_param_error), jSONObject.getString("errorParamIndex")), 1).show();
        }
    }

    private SpannableStringBuilder buildSpannableString(int i, String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) str);
        spannableStringBuilder.setSpan(new RelativeSizeSpan(1.4f), 0, str.length(), 33);
        spannableStringBuilder.append((CharSequence) "\n ");
        spannableStringBuilder.append((CharSequence) getString(i));
        spannableStringBuilder.setSpan(new RelativeSizeSpan(0.8f), spannableStringBuilder.length() - getString(i).length(), spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }
}
