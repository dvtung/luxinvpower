package com.nfcx.luxinvpower.view.main.fragment.lv1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.messaging.Constants;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.global.custom.mpChart.CustomLargeValueFormatter;
import com.nfcx.luxinvpower.global.custom.mpChart.marker.CustomBarMarkerView;
import com.nfcx.luxinvpower.global.custom.mpChart.marker.CustomLineMarkerView;
import com.nfcx.luxinvpower.tool.API;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.InvTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.plant.PlantListActivity;
import com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Lv1DataFragment extends AbstractItemFragment {
    private static final int ENERGY_TYPE_MONTH = 0;
    private static final int ENERGY_TYPE_TOTAL = 2;
    private static final int ENERGY_TYPE_YEAR = 1;
    private LineDataSet batteryDataSet;
    private BarDataSet batteryDischargingBarDataSet;
    private BarDataSet consumptionBarDataSet;
    private LineDataSet consumptionDataSet;
    private boolean created;
    private int currentEnergyType;
    private BarChart energyBarChart;
    private ToggleButton energyChartMonthButton;
    private ConstraintLayout energyChartNextButton;
    private ConstraintLayout energyChartPreviousButton;
    private EditText energyChartTimeEditText;
    private RelativeLayout energyChartTimeLayout;
    private ToggleButton energyChartTotalButton;
    private ToggleButton energyChartYearButton;
    private BarDataSet export2GridBarDataSet;
    private Fragment fragment;
    private LineDataSet gridDataSet;
    private BarDataSet importOffgridBarDataSet;
    private boolean inited;
    private Inverter inverter;
    private List<Inverter> inverterList;
    private Spinner inverterSpinner;
    private ConstraintLayout powerChartNextButton;
    private ConstraintLayout powerChartPreviousButton;
    private LineChart powerLineChart;
    private EditText powerLineChartTimeEditText;
    private LineDataSet socDataSet;
    private BarDataSet solarProductionBarDataSet;
    private LineDataSet solarPvDataSet;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int waitRequestCount;

    static /* synthetic */ int access$1306(Lv1DataFragment lv1DataFragment) {
        int i = lv1DataFragment.waitRequestCount - 1;
        lv1DataFragment.waitRequestCount = i;
        return i;
    }

    public Lv1DataFragment() {
        super(2L);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        List<Inverter> invertersByPlant;
        View inflate = layoutInflater.inflate(R.layout.fragment_lv1_data, viewGroup, false);
        this.fragment = this;
        final UserData userData = GlobalInfo.getInstance().getUserData();
        if (!PLATFORM.LUX_POWER.equals(userData.getPlatform())) {
            inflate.findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        ((ConstraintLayout) inflate.findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1DataFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) (ROLE.VIEWER.equals(userData.getRole()) ? PlantListActivity.class : PlantOverviewActivity.class)));
                MainActivity.instance.finish();
            }
        });
        ((ImageView) inflate.findViewById(R.id.userCenterImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1DataFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) NewUserCenterActivity.class));
            }
        });
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
                inverter.setParallelFirstDeviceSn(parallelGroups.get(str));
                this.inverterList.add(i, inverter);
                i++;
            }
        }
        this.inverterSpinner = (Spinner) inflate.findViewById(R.id.fragment_data_inverter_spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, this.inverterList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.inverterSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        LineChart lineChart = (LineChart) inflate.findViewById(R.id.fragment_overview_powerChart);
        this.powerLineChart = lineChart;
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        this.powerLineChart.getXAxis().setGranularity(60.0f);
        this.powerLineChart.getXAxis().setValueFormatter(GlobalInfo.getInstance().getTimeAxisValueFormatter());
        this.powerLineChart.getAxisLeft().setGranularity(100.0f);
        this.powerLineChart.getAxisLeft().enableGridDashedLine(10.0f, 10.0f, 0.0f);
        this.powerLineChart.getAxisLeft().setValueFormatter(new CustomLargeValueFormatter());
        this.powerLineChart.getAxisRight().setAxisMinimum(0.0f);
        this.powerLineChart.getAxisRight().setAxisMaximum(110.0f);
        this.powerLineChart.getAxisRight().setGranularity(20.0f);
        this.powerLineChart.getAxisRight().setValueFormatter(GlobalInfo.getInstance().getNumberAxisSOCValueFormatter());
        this.powerLineChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        this.powerLineChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        Description description = new Description();
        description.setText("");
        this.powerLineChart.setDescription(description);
        this.powerLineChart.setPinchZoom(false);
        this.powerLineChart.setDoubleTapToZoomEnabled(false);
        this.powerLineChart.setTouchEnabled(true);
        this.powerLineChart.setMarker(new CustomLineMarkerView(getActivity(), this.powerLineChart, R.layout.custom_line_marker_view_layout));
        BarChart barChart = (BarChart) inflate.findViewById(R.id.fragment_overview_energyChart);
        this.energyBarChart = barChart;
        barChart.getAxisRight().setEnabled(false);
        this.energyBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        this.energyBarChart.getXAxis().setGranularity(1.0f);
        this.energyBarChart.getXAxis().setValueFormatter(GlobalInfo.getInstance().getNumberAxisValueFormatter());
        this.energyBarChart.getAxisLeft().enableGridDashedLine(10.0f, 10.0f, 0.0f);
        this.energyBarChart.getAxisLeft().setValueFormatter(new CustomLargeValueFormatter());
        this.energyBarChart.getAxisLeft().setGranularity(1.0f);
        this.energyBarChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        this.energyBarChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        Description description2 = new Description();
        description2.setEnabled(false);
        this.energyBarChart.setDescription(description2);
        this.energyBarChart.setPinchZoom(false);
        this.energyBarChart.setDoubleTapToZoomEnabled(false);
        this.energyBarChart.setTouchEnabled(true);
        this.energyBarChart.setMarker(new CustomBarMarkerView(getActivity(), this.energyBarChart, R.layout.custom_bar_marker_view_layout, this.currentEnergyType));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.fragment_data_swipe_refresh_layout);
        this.swipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment$$ExternalSyntheticLambda2
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                Lv1DataFragment.this.m352x59dfd28d();
            }
        });
        this.created = true;
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$0$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1DataFragment, reason: not valid java name */
    public /* synthetic */ void m352x59dfd28d() {
        this.waitRequestCount = 2;
        reloadFragmentData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ConstraintLayout constraintLayout = (ConstraintLayout) getView().findViewById(R.id.fragment_overview_powerChart_previousButtonLayout);
        this.powerChartPreviousButton = constraintLayout;
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1DataFragment.this.changePowerLineChartTimeEditTextByButton(-1);
            }
        });
        ConstraintLayout constraintLayout2 = (ConstraintLayout) getView().findViewById(R.id.fragment_overview_powerChart_nextButtonLayout);
        this.powerChartNextButton = constraintLayout2;
        constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1DataFragment.this.changePowerLineChartTimeEditTextByButton(1);
            }
        });
        EditText editText = (EditText) getView().findViewById(R.id.fragment_overview_powerChart_timeEditText);
        this.powerLineChartTimeEditText = editText;
        editText.setText(InvTool.formatDate(new Date()));
        this.powerLineChartTimeEditText.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1DataFragment.this.getActivity().showDialog(0);
            }
        });
        this.powerLineChartTimeEditText.addTextChangedListener(new TextWatcher() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.6
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Lv1DataFragment.this.refreshPowerLineChart();
            }
        });
        ConstraintLayout constraintLayout3 = (ConstraintLayout) getView().findViewById(R.id.fragment_overview_energyChart_previousButtonLayout);
        this.energyChartPreviousButton = constraintLayout3;
        constraintLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1DataFragment.this.changeEnergyChartTimeEditTextByButton(-1);
            }
        });
        ConstraintLayout constraintLayout4 = (ConstraintLayout) getView().findViewById(R.id.fragment_overview_energyChart_nextButtonLayout);
        this.energyChartNextButton = constraintLayout4;
        constraintLayout4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1DataFragment.this.changeEnergyChartTimeEditTextByButton(1);
            }
        });
        ToggleButton toggleButton = (ToggleButton) getView().findViewById(R.id.fragment_overview_energyChart_monthButton);
        this.energyChartMonthButton = toggleButton;
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z || Lv1DataFragment.this.currentEnergyType == 0) {
                    if (Lv1DataFragment.this.currentEnergyType == 0) {
                        compoundButton.setChecked(true);
                    }
                } else {
                    Lv1DataFragment.this.currentEnergyType = 0;
                    Lv1DataFragment.this.energyChartYearButton.setChecked(false);
                    Lv1DataFragment.this.energyChartTotalButton.setChecked(false);
                    Lv1DataFragment.this.initEnergyChartTimeEditText();
                    Lv1DataFragment.this.energyChartTimeLayout.setVisibility(0);
                }
            }
        });
        ToggleButton toggleButton2 = (ToggleButton) getView().findViewById(R.id.fragment_overview_energyChart_yearButton);
        this.energyChartYearButton = toggleButton2;
        toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z || Lv1DataFragment.this.currentEnergyType == 1) {
                    if (Lv1DataFragment.this.currentEnergyType == 1) {
                        compoundButton.setChecked(true);
                    }
                } else {
                    Lv1DataFragment.this.currentEnergyType = 1;
                    Lv1DataFragment.this.energyChartMonthButton.setChecked(false);
                    Lv1DataFragment.this.energyChartTotalButton.setChecked(false);
                    Lv1DataFragment.this.initEnergyChartTimeEditText();
                    Lv1DataFragment.this.energyChartTimeLayout.setVisibility(0);
                }
            }
        });
        ToggleButton toggleButton3 = (ToggleButton) getView().findViewById(R.id.fragment_overview_energyChart_totalButton);
        this.energyChartTotalButton = toggleButton3;
        toggleButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.11
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z || Lv1DataFragment.this.currentEnergyType == 2) {
                    if (Lv1DataFragment.this.currentEnergyType == 2) {
                        compoundButton.setChecked(true);
                    }
                } else {
                    Lv1DataFragment.this.currentEnergyType = 2;
                    Lv1DataFragment.this.energyChartMonthButton.setChecked(false);
                    Lv1DataFragment.this.energyChartYearButton.setChecked(false);
                    Lv1DataFragment.this.energyChartTimeLayout.setVisibility(4);
                    Lv1DataFragment.this.initEnergyChartTimeEditText();
                    Lv1DataFragment.this.refreshEnergyBarChart();
                }
            }
        });
        EditText editText2 = (EditText) getView().findViewById(R.id.fragment_overview_energyChart_timeEditText);
        this.energyChartTimeEditText = editText2;
        editText2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Lv1DataFragment.this.currentEnergyType != 0) {
                    if (Lv1DataFragment.this.currentEnergyType == 1) {
                        Lv1DataFragment.this.getActivity().showDialog(2);
                        return;
                    }
                    return;
                }
                Lv1DataFragment.this.getActivity().showDialog(1);
            }
        });
        this.energyChartTimeEditText.addTextChangedListener(new TextWatcher() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.13
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Lv1DataFragment.this.refreshEnergyBarChart();
            }
        });
        this.currentEnergyType = 0;
        initEnergyChartTimeEditText();
        this.energyChartTimeLayout = (RelativeLayout) getView().findViewById(R.id.fragment_overview_energyChart_timeLayout);
    }

    private void initFirstTimeVisible() {
        System.out.println("LuxPower - DataFragment initFirstTimeVisible...");
        this.inverterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.14
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Lv1DataFragment.this.updateSelectInverter((Inverter) Lv1DataFragment.this.inverterSpinner.getSelectedItem());
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (Lv1DataFragment.this.inverter != null) {
                    UserData userData = GlobalInfo.getInstance().getUserData();
                    if (Lv1DataFragment.this.inverter.isParallelGroup()) {
                        userData.setCurrentParallelGroup(null);
                    } else {
                        userData.setCurrentInverter(null, true);
                    }
                    Lv1DataFragment.this.inverter = null;
                    Lv1DataFragment.this.reloadFragmentData();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSelectInverter(Inverter inverter) {
        System.out.println("LuxPower - DataFragment selectInverter = " + inverter.getSerialNum() + ", inverter = " + this.inverter);
        Inverter inverter2 = this.inverter;
        if (inverter2 == null || !inverter2.getSerialNum().equals(inverter.getSerialNum())) {
            this.inverter = inverter;
            UserData userData = GlobalInfo.getInstance().getUserData();
            if (this.inverter.isParallelGroup()) {
                userData.setCurrentParallelGroup(this.inverter);
            } else {
                userData.setCurrentInverter(this.inverter, true);
            }
            reloadFragmentData();
            MainActivity mainActivity = (MainActivity) this.fragment.getActivity();
            if (mainActivity != null) {
                mainActivity.switchRemoteSetFragment(this.inverter.getDeviceTypeValue());
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - DataFragment onResume...");
        if (!this.inited) {
            this.inited = true;
            initFirstTimeVisible();
        }
        refreshFragmentParams();
    }

    public void refreshFragmentParams() {
        Inverter inverter;
        if (this.created) {
            System.out.println("LuxPower - DATA refreshFragmentParams...");
            UserData userData = GlobalInfo.getInstance().getUserData();
            Inverter currentParallelGroup = userData.getCurrentParallelGroup();
            if (currentParallelGroup == null) {
                currentParallelGroup = userData.getCurrentInverter();
            }
            if (currentParallelGroup == null || this.inverterList == null) {
                return;
            }
            for (int i = 0; i < this.inverterList.size(); i++) {
                Inverter inverter2 = this.inverterList.get(i);
                if (inverter2.getSerialNum().equals(currentParallelGroup.getSerialNum()) && ((inverter = this.inverter) == null || !inverter.getSerialNum().equals(currentParallelGroup.getSerialNum()))) {
                    System.out.println("LuxPower - inverterSpinner.setSelection(" + i + ")");
                    if (this.inverterSpinner.getSelectedItemPosition() != i) {
                        this.inverterSpinner.setSelection(i);
                    } else {
                        updateSelectInverter(inverter2);
                    }
                }
            }
        }
    }

    public synchronized void reloadFragmentData() {
        if (this.created) {
            refreshPowerLineChart();
            refreshEnergyBarChart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changePowerLineChartTimeEditTextByButton(int i) {
        Calendar calendar = Calendar.getInstance();
        String obj = this.powerLineChartTimeEditText.getText().toString();
        calendar.set(1, Integer.parseInt(obj.substring(0, 4)));
        calendar.set(2, Integer.parseInt(obj.substring(5, 7)) - 1);
        calendar.set(5, Integer.parseInt(obj.substring(8, 10)));
        calendar.add(5, i);
        this.powerLineChartTimeEditText.setText(InvTool.formatDate(calendar.getTime()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshPowerLineChart() {
        LineChart lineChart = this.powerLineChart;
        if (lineChart != null) {
            if (this.inverter != null) {
                new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lv1DataFragment.this.m354x484e6534();
                    }
                }).start();
                return;
            }
            lineChart.clear();
            this.powerLineChart.animateX(2000, Easing.EasingOption.EaseInCubic);
            this.powerLineChart.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$refreshPowerLineChart$1$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1DataFragment, reason: not valid java name */
    public /* synthetic */ void m354x484e6534() {
        final UserData userData = GlobalInfo.getInstance().getUserData();
        HashMap hashMap = new HashMap();
        hashMap.put("serialNum", this.inverter.isParallelGroup() ? this.inverter.getParallelFirstDeviceSn() : this.inverter.getSerialNum());
        hashMap.put("dateText", this.powerLineChartTimeEditText.getText().toString());
        System.out.println("LuxPower - Query serialNum = " + ((String) hashMap.get("serialNum")));
        final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/analyze/chart/dayMultiLine" + (this.inverter.isParallelGroup() ? "Parallel" : ""), hashMap);
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.15
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (Lv1DataFragment.this.waitRequestCount > 0 && Lv1DataFragment.access$1306(Lv1DataFragment.this) == 0) {
                            Lv1DataFragment.this.swipeRefreshLayout.setRefreshing(false);
                        }
                        JSONObject jSONObject = postJson;
                        if (jSONObject == null || !jSONObject.getBoolean("success")) {
                            Lv1DataFragment.this.toastByResult(activity, postJson);
                            return;
                        }
                        JSONObject chartColors = userData.getChartColor().getChartColors();
                        JSONArray jSONArray = postJson.getJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                        if (jSONArray.length() <= 0) {
                            Lv1DataFragment.this.powerLineChart.clear();
                        } else {
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            ArrayList arrayList3 = new ArrayList();
                            ArrayList arrayList4 = new ArrayList();
                            ArrayList arrayList5 = new ArrayList();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                float f = (jSONObject2.getInt("hour") * 3600) + (jSONObject2.getInt("minute") * 60) + jSONObject2.getInt("second");
                                arrayList.add(new Entry(f, jSONObject2.getInt("solarPv")));
                                arrayList2.add(new Entry(f, jSONObject2.getInt("gridPower")));
                                arrayList3.add(new Entry(f, jSONObject2.getInt("batteryDischarging")));
                                arrayList4.add(new Entry(f, jSONObject2.getInt("consumption")));
                                if (jSONObject2.has("soc")) {
                                    arrayList5.add(new Entry(f, jSONObject2.getInt("soc")));
                                }
                            }
                            if (Lv1DataFragment.this.solarPvDataSet != null) {
                                Lv1DataFragment.this.solarPvDataSet.setValues(arrayList);
                            } else {
                                Lv1DataFragment lv1DataFragment = Lv1DataFragment.this;
                                lv1DataFragment.solarPvDataSet = lv1DataFragment.createLineDataSet(arrayList, lv1DataFragment.getString(R.string.main_overview_chart_solar_pv), Color.parseColor(chartColors.getString("Solar PV")));
                            }
                            if (Lv1DataFragment.this.batteryDataSet != null) {
                                Lv1DataFragment.this.batteryDataSet.setValues(arrayList3);
                            } else {
                                Lv1DataFragment lv1DataFragment2 = Lv1DataFragment.this;
                                lv1DataFragment2.batteryDataSet = lv1DataFragment2.createLineDataSet(arrayList3, lv1DataFragment2.getString(R.string.main_overview_chart_battery_discharging), Color.parseColor(chartColors.getString("Battery")));
                            }
                            if (Lv1DataFragment.this.gridDataSet != null) {
                                Lv1DataFragment.this.gridDataSet.setValues(arrayList2);
                            } else {
                                Lv1DataFragment lv1DataFragment3 = Lv1DataFragment.this;
                                lv1DataFragment3.gridDataSet = lv1DataFragment3.createLineDataSet(arrayList2, lv1DataFragment3.getString(R.string.main_overview_chart_grid_power), Color.parseColor(chartColors.getString("Grid")));
                            }
                            if (Lv1DataFragment.this.consumptionDataSet != null) {
                                Lv1DataFragment.this.consumptionDataSet.setValues(arrayList4);
                            } else {
                                Lv1DataFragment lv1DataFragment4 = Lv1DataFragment.this;
                                lv1DataFragment4.consumptionDataSet = lv1DataFragment4.createLineDataSet(arrayList4, lv1DataFragment4.getString(R.string.main_overview_chart_consumption), Color.parseColor(chartColors.getString("Consumption")));
                            }
                            if (Lv1DataFragment.this.socDataSet != null) {
                                Lv1DataFragment.this.socDataSet.setValues(arrayList5);
                            } else {
                                Lv1DataFragment lv1DataFragment5 = Lv1DataFragment.this;
                                lv1DataFragment5.socDataSet = lv1DataFragment5.createLineDataSet(arrayList5, "SOC", Color.parseColor(chartColors.getString("SOC")));
                                Lv1DataFragment.this.socDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
                            }
                            ArrayList arrayList6 = new ArrayList();
                            String userChartRecord = userData.getUserChartRecord();
                            if (Tool.isEmpty(userChartRecord)) {
                                arrayList6.add(Lv1DataFragment.this.solarPvDataSet);
                                arrayList6.add(Lv1DataFragment.this.batteryDataSet);
                                arrayList6.add(Lv1DataFragment.this.gridDataSet);
                                arrayList6.add(Lv1DataFragment.this.consumptionDataSet);
                                arrayList6.add(Lv1DataFragment.this.socDataSet);
                            } else {
                                JSONObject jSONObject3 = new JSONObject(userChartRecord);
                                String str = Lv1DataFragment.this.inverter.getDeviceTypeValue() + "_" + Lv1DataFragment.this.inverter.getSubDeviceTypeValue() + "_" + Lv1DataFragment.this.inverter.getPhaseValue() + "_" + Lv1DataFragment.this.inverter.getDtcValue();
                                if (!jSONObject3.has(str)) {
                                    arrayList6.add(Lv1DataFragment.this.solarPvDataSet);
                                    arrayList6.add(Lv1DataFragment.this.batteryDataSet);
                                    arrayList6.add(Lv1DataFragment.this.gridDataSet);
                                    arrayList6.add(Lv1DataFragment.this.consumptionDataSet);
                                    arrayList6.add(Lv1DataFragment.this.socDataSet);
                                } else {
                                    JSONObject jSONObject4 = jSONObject3.getJSONObject(str);
                                    if (jSONObject4.getBoolean("lineSolarPV")) {
                                        arrayList6.add(Lv1DataFragment.this.solarPvDataSet);
                                    }
                                    if (jSONObject4.getBoolean("lineBattery")) {
                                        arrayList6.add(Lv1DataFragment.this.batteryDataSet);
                                    }
                                    if (jSONObject4.getBoolean("lineGrid")) {
                                        arrayList6.add(Lv1DataFragment.this.gridDataSet);
                                    }
                                    if (jSONObject4.getBoolean("lineConsumption")) {
                                        arrayList6.add(Lv1DataFragment.this.consumptionDataSet);
                                    }
                                    if (jSONObject4.getBoolean("lineSOC")) {
                                        arrayList6.add(Lv1DataFragment.this.socDataSet);
                                    }
                                }
                            }
                            Lv1DataFragment.this.powerLineChart.setData(new LineData(arrayList6));
                        }
                        Lv1DataFragment.this.powerLineChart.animateX(2000, Easing.EasingOption.EaseInCubic);
                        Lv1DataFragment.this.powerLineChart.invalidate();
                    } catch (Exception e) {
                        Toast.makeText(activity.getApplicationContext(), R.string.phrase_toast_response_error, 1).show();
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LineDataSet createLineDataSet(List<Entry> list, String str, int i) {
        LineDataSet lineDataSet = new LineDataSet(list, str);
        lineDataSet.setDrawValues(false);
        lineDataSet.setColor(i);
        lineDataSet.setCircleColor(i);
        lineDataSet.setLineWidth(1.0f);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCubicIntensity(0.1f);
        lineDataSet.setCircleRadius(1.0f);
        return lineDataSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initEnergyChartTimeEditText() {
        int i = this.currentEnergyType;
        if (i == 0) {
            this.energyChartTimeEditText.setText(InvTool.formatDate(new Date()).substring(0, 7));
        } else if (i == 1) {
            this.energyChartTimeEditText.setText(InvTool.formatDate(new Date()).substring(0, 4));
        }
        this.energyBarChart.clear();
        this.energyBarChart.setMarker(new CustomBarMarkerView(getActivity(), this.energyBarChart, R.layout.custom_bar_marker_view_layout, this.currentEnergyType));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeEnergyChartTimeEditTextByButton(int i) {
        Calendar calendar = Calendar.getInstance();
        String obj = this.energyChartTimeEditText.getText().toString();
        calendar.set(1, Integer.parseInt(obj.substring(0, 4)));
        int i2 = this.currentEnergyType;
        if (i2 == 0) {
            calendar.set(2, Integer.parseInt(obj.substring(5, 7)) - 1);
            calendar.add(2, i);
            this.energyChartTimeEditText.setText(InvTool.formatDate(calendar.getTime()).substring(0, 7));
        } else if (i2 == 1) {
            calendar.add(1, i);
            this.energyChartTimeEditText.setText(InvTool.formatDate(calendar.getTime()).substring(0, 4));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshEnergyBarChart() {
        BarChart barChart = this.energyBarChart;
        if (barChart != null) {
            if (this.inverter != null) {
                new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lv1DataFragment.this.m353x937baaa3();
                    }
                }).start();
                return;
            }
            barChart.clear();
            this.energyBarChart.animateX(2000, Easing.EasingOption.EaseInCubic);
            this.energyBarChart.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$refreshEnergyBarChart$2$com-nfcx-luxinvpower-view-main-fragment-lv1-Lv1DataFragment, reason: not valid java name */
    public /* synthetic */ void m353x937baaa3() {
        String str;
        final UserData userData = GlobalInfo.getInstance().getUserData();
        HashMap hashMap = new HashMap();
        hashMap.put("serialNum", this.inverter.isParallelGroup() ? this.inverter.getParallelFirstDeviceSn() : this.inverter.getSerialNum());
        int i = this.currentEnergyType;
        if (i == 0 || i == 1) {
            hashMap.put("year", this.energyChartTimeEditText.getText().toString().substring(0, 4));
        }
        if (this.currentEnergyType == 0) {
            hashMap.put("month", this.energyChartTimeEditText.getText().toString().substring(5, 7));
        }
        int i2 = this.currentEnergyType;
        if (i2 == 0) {
            str = "monthColumn".concat(this.inverter.isParallelGroup() ? "Parallel" : "");
        } else if (i2 == 1) {
            str = "yearColumn".concat(this.inverter.isParallelGroup() ? "Parallel" : "");
        } else if (i2 == 2) {
            str = "totalColumn".concat(this.inverter.isParallelGroup() ? "Parallel" : "");
        } else {
            str = null;
        }
        if (Tool.isEmpty(str)) {
            return;
        }
        final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/inverterChart/" + str, hashMap);
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.16
                /* JADX WARN: Removed duplicated region for block: B:101:0x0292 A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:104:0x02d3 A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:107:0x0310 A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:110:0x034d A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:113:0x038a A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:116:0x03cd A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:120:0x03f8 A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:121:0x03a5 A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:122:0x0368 A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:123:0x032b A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:124:0x02ee A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:125:0x02af A[Catch: Exception -> 0x047e, TryCatch #1 {Exception -> 0x047e, blocks: (B:58:0x0277, B:83:0x0231, B:85:0x023d, B:86:0x0243, B:89:0x026b, B:99:0x0287, B:101:0x0292, B:102:0x02ba, B:104:0x02d3, B:105:0x02f7, B:107:0x0310, B:108:0x0334, B:110:0x034d, B:111:0x0371, B:113:0x038a, B:114:0x03ae, B:116:0x03cd, B:117:0x0422, B:118:0x045c, B:120:0x03f8, B:121:0x03a5, B:122:0x0368, B:123:0x032b, B:124:0x02ee, B:125:0x02af, B:133:0x0452, B:134:0x0473), top: B:9:0x0022 }] */
                /* JADX WARN: Removed duplicated region for block: B:127:0x00f8  */
                /* JADX WARN: Removed duplicated region for block: B:128:0x00ee  */
                /* JADX WARN: Removed duplicated region for block: B:129:0x00e4  */
                /* JADX WARN: Removed duplicated region for block: B:130:0x00da  */
                /* JADX WARN: Removed duplicated region for block: B:131:0x00d0  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x00c9 A[Catch: Exception -> 0x0480, TryCatch #0 {Exception -> 0x0480, blocks: (B:3:0x0006, B:5:0x000f, B:7:0x0017, B:8:0x0020, B:11:0x0024, B:13:0x002c, B:15:0x005d, B:17:0x0069, B:19:0x00c1, B:21:0x00c9, B:23:0x00d3, B:25:0x00dd, B:27:0x00e7, B:29:0x00f1, B:31:0x00fc, B:33:0x0104, B:35:0x0113, B:38:0x0120, B:97:0x011a), top: B:2:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:23:0x00d3 A[Catch: Exception -> 0x0480, TryCatch #0 {Exception -> 0x0480, blocks: (B:3:0x0006, B:5:0x000f, B:7:0x0017, B:8:0x0020, B:11:0x0024, B:13:0x002c, B:15:0x005d, B:17:0x0069, B:19:0x00c1, B:21:0x00c9, B:23:0x00d3, B:25:0x00dd, B:27:0x00e7, B:29:0x00f1, B:31:0x00fc, B:33:0x0104, B:35:0x0113, B:38:0x0120, B:97:0x011a), top: B:2:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:25:0x00dd A[Catch: Exception -> 0x0480, TryCatch #0 {Exception -> 0x0480, blocks: (B:3:0x0006, B:5:0x000f, B:7:0x0017, B:8:0x0020, B:11:0x0024, B:13:0x002c, B:15:0x005d, B:17:0x0069, B:19:0x00c1, B:21:0x00c9, B:23:0x00d3, B:25:0x00dd, B:27:0x00e7, B:29:0x00f1, B:31:0x00fc, B:33:0x0104, B:35:0x0113, B:38:0x0120, B:97:0x011a), top: B:2:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:27:0x00e7 A[Catch: Exception -> 0x0480, TryCatch #0 {Exception -> 0x0480, blocks: (B:3:0x0006, B:5:0x000f, B:7:0x0017, B:8:0x0020, B:11:0x0024, B:13:0x002c, B:15:0x005d, B:17:0x0069, B:19:0x00c1, B:21:0x00c9, B:23:0x00d3, B:25:0x00dd, B:27:0x00e7, B:29:0x00f1, B:31:0x00fc, B:33:0x0104, B:35:0x0113, B:38:0x0120, B:97:0x011a), top: B:2:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:29:0x00f1 A[Catch: Exception -> 0x0480, TryCatch #0 {Exception -> 0x0480, blocks: (B:3:0x0006, B:5:0x000f, B:7:0x0017, B:8:0x0020, B:11:0x0024, B:13:0x002c, B:15:0x005d, B:17:0x0069, B:19:0x00c1, B:21:0x00c9, B:23:0x00d3, B:25:0x00dd, B:27:0x00e7, B:29:0x00f1, B:31:0x00fc, B:33:0x0104, B:35:0x0113, B:38:0x0120, B:97:0x011a), top: B:2:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:33:0x0104 A[Catch: Exception -> 0x0480, TryCatch #0 {Exception -> 0x0480, blocks: (B:3:0x0006, B:5:0x000f, B:7:0x0017, B:8:0x0020, B:11:0x0024, B:13:0x002c, B:15:0x005d, B:17:0x0069, B:19:0x00c1, B:21:0x00c9, B:23:0x00d3, B:25:0x00dd, B:27:0x00e7, B:29:0x00f1, B:31:0x00fc, B:33:0x0104, B:35:0x0113, B:38:0x0120, B:97:0x011a), top: B:2:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:98:0x0287 A[EDGE_INSN: B:98:0x0287->B:99:0x0287 BREAK  A[LOOP:0: B:31:0x00fc->B:58:0x0277], SYNTHETIC] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        Method dump skipped, instructions count: 1175
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1DataFragment.AnonymousClass16.run():void");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BarDataSet createBarDataSet(List<BarEntry> list, String str, int i) {
        BarDataSet barDataSet = new BarDataSet(list, str);
        barDataSet.setColor(i);
        return barDataSet;
    }

    public EditText getPowerLineChartTimeEditText() {
        return this.powerLineChartTimeEditText;
    }

    public EditText getEnergyChartTimeEditText() {
        return this.energyChartTimeEditText;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toastByResult(FragmentActivity fragmentActivity, JSONObject jSONObject) throws JSONException {
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
}
