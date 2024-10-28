package com.nfcx.luxinvpower.view.local.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.connect.LocalConnect;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.bean.inverter.BATTERY_TYPE;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.custom.view.GifView;
import com.nfcx.luxinvpower.protocol.tcp.DataFrameFactory;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.tcp.TCP_PROTOCOL;
import com.nfcx.luxinvpower.tool.FrameTool;
import com.nfcx.luxinvpower.tool.InvTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.local.LocalActivity;
import com.nfcx.luxinvpower.view.login.LoginActivity;
import com.nfcx.luxinvpower.view.main.fragment.lv1.AbstractItemFragment;
import java.util.Date;

/* loaded from: classes2.dex */
public class LocalOverviewFragment extends AbstractItemFragment {
    private TextView battCapacityLabel;
    private TextView battCapacityValueLabel;
    private TextView battParallelNumLabel;
    private TextView battParallelNumValueLabel;
    private TextView bmsLimitChargeLabel;
    private TextView bmsLimitChargeValueLabel;
    private TextView bmsLimitDischargeLabel;
    private TextView bmsLimitDischargeValueLabel;
    private TextView datalogSnTextView;
    private boolean firstRead04;
    private GifView flowAcPvPowerGifView1;
    private GifView flowAcPvPowerGifView2;
    private ImageView flowAcPvPowerImageView;
    private TextView flowAcPvPowerLabelTextView;
    private TextView flowAcPvPowerTextView;
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
    private TextView inverterSnTextView;
    private LocalConnect localConnect;
    private TextView localTimeTextView;
    private boolean paused;
    private boolean read03Success;
    private boolean read04_3_Success;
    private boolean readData;
    private ImageView statusImageView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView todayDischargingTextView;
    private TextView todayExportTextView;
    private TextView todayUsageTextView;
    private TextView todayYieldingTextView;
    private TextView totalDischargingTextView;
    private TextView totalExportTextView;
    private TextView totalUsageTextView;
    private TextView totalYieldingTextView;

    public LocalOverviewFragment(LocalConnect localConnect) {
        super(1L);
        this.read03Success = false;
        this.firstRead04 = true;
        this.read04_3_Success = false;
        this.localConnect = localConnect;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.readData = true;
        this.paused = false;
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                LocalOverviewFragment.this.m329xe94a6523();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-nfcx-luxinvpower-view-local-fragment-LocalOverviewFragment, reason: not valid java name */
    public /* synthetic */ void m329xe94a6523() {
        this.localConnect.initialize(true);
        while (this.readData) {
            if (!this.paused) {
                if (!this.read03Success) {
                    sendRead03Command1();
                } else {
                    sendRead04Command1();
                }
            }
            int i = 0;
            if (this.read03Success) {
                if (this.firstRead04) {
                    Tool.sleep(1000L);
                    this.firstRead04 = false;
                } else {
                    while (i < 20 && this.readData) {
                        Tool.sleep(500L);
                        i++;
                    }
                }
            } else {
                while (i < 6 && this.readData) {
                    Tool.sleep(500L);
                    i++;
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_local_overview, viewGroup, false);
        ((ConstraintLayout) inflate.findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocalOverviewFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) LoginActivity.class));
                LocalActivity.instance.finish();
            }
        });
        this.datalogSnTextView = (TextView) inflate.findViewById(R.id.fragment_overview_datalogSn_TextView);
        this.inverterSnTextView = (TextView) inflate.findViewById(R.id.fragment_overview_inverterSn_TextView);
        this.todayYieldingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_yielding);
        this.totalYieldingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_yielding);
        this.todayDischargingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_discharging);
        this.totalDischargingTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_discharging);
        this.todayExportTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_export);
        this.totalExportTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_export);
        this.todayUsageTextView = (TextView) inflate.findViewById(R.id.main_overview_info_today_usage);
        this.totalUsageTextView = (TextView) inflate.findViewById(R.id.main_overview_info_total_usage);
        this.statusImageView = (ImageView) inflate.findViewById(R.id.fragment_flow_statusImageView);
        this.flowBatteryPowerTextView = (TextView) inflate.findViewById(R.id.fragment_flow_batteryPower_textView);
        this.flowBatteryPowerLabelTextView = (TextView) inflate.findViewById(R.id.fragment_flow_batteryPowerLabel_textView);
        this.flowBatteryPowerGifView1 = (GifView) inflate.findViewById(R.id.fragment_flow_batteryPower_gifView1);
        this.flowBatteryPowerGifView2 = (GifView) inflate.findViewById(R.id.fragment_flow_batteryPower_gifView2);
        this.flowSocPowerTextView = (TextView) inflate.findViewById(R.id.fragment_flow_socPower_textView);
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
        ImageView imageView = (ImageView) inflate.findViewById(R.id.fragment_flow_inverter_imageView);
        this.flowInverterImageView = imageView;
        imageView.setBackgroundResource(R.drawable.icon_dt_inverter_mid);
        this.localTimeTextView = (TextView) inflate.findViewById(R.id.fragment_flow_localTime_textView);
        this.battParallelNumLabel = (TextView) inflate.findViewById(R.id.fragment_flow_battParallelNumLabel);
        this.battParallelNumValueLabel = (TextView) inflate.findViewById(R.id.fragment_flow_battParallelNumValueLabel);
        this.battCapacityLabel = (TextView) inflate.findViewById(R.id.fragment_flow_battCapacityLabel);
        this.battCapacityValueLabel = (TextView) inflate.findViewById(R.id.fragment_flow_battCapacityValueLabel);
        this.bmsLimitChargeLabel = (TextView) inflate.findViewById(R.id.fragment_flow_bmsLimitChargeLabel);
        this.bmsLimitChargeValueLabel = (TextView) inflate.findViewById(R.id.fragment_flow_bmsLimitChargeValueLabel);
        this.bmsLimitDischargeLabel = (TextView) inflate.findViewById(R.id.fragment_flow_bmsLimitDischargeLabel);
        this.bmsLimitDischargeValueLabel = (TextView) inflate.findViewById(R.id.fragment_flow_bmsLimitDischargeValueLabel);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.fragment_overview_swipe_refresh_layout);
        this.swipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setEnabled(false);
        initFlowChartByDeviceType();
        return inflate;
    }

    private void initFlowChartByDeviceType() {
        Inverter inverter = this.localConnect.getInverter();
        if (inverter != null && inverter.isAcCharger()) {
            this.flowPvPowerImageView.setVisibility(4);
            this.flowPvPowerTextView.setVisibility(4);
            this.flowPvPowerLabelTextView.setVisibility(4);
            this.flowPvPowerGifView.setVisibility(4);
            this.flowAcPvPowerImageView.setVisibility(0);
            this.flowAcPvPowerTextView.setVisibility(0);
            this.flowAcPvPowerLabelTextView.setVisibility(0);
            this.flowInverterImageView.setBackgroundResource(R.drawable.icon_dt_ac_charger_mid);
            return;
        }
        this.flowAcPvPowerImageView.setVisibility(4);
        this.flowAcPvPowerTextView.setVisibility(4);
        this.flowAcPvPowerLabelTextView.setVisibility(4);
        this.flowAcPvPowerGifView1.setVisibility(4);
        this.flowAcPvPowerGifView2.setVisibility(4);
        this.flowPvPowerImageView.setVisibility(0);
        this.flowPvPowerTextView.setVisibility(0);
        this.flowPvPowerLabelTextView.setVisibility(0);
        if (inverter != null && inverter.isAcCharger()) {
            this.flowInverterImageView.setBackgroundResource(R.drawable.icon_dt_ac_charger_mid);
        } else if (inverter != null && inverter.isSnaSeries()) {
            if (inverter.getSubDeviceTypeValue() == 131) {
                this.flowInverterImageView.setBackgroundResource(R.drawable.icon_dt_sna_us_6k_mid);
            } else if (inverter.getSubDeviceTypeValue() == 1111) {
                this.flowInverterImageView.setBackgroundResource(R.drawable.icon_sna_12k_us_mid);
            } else if (inverter.getSubDeviceTypeValue() == 1110) {
                this.flowInverterImageView.setBackgroundResource(R.drawable.icon_sna_12k_eu_mid);
            } else {
                this.flowInverterImageView.setBackgroundResource(R.drawable.icon_dt_inverter_off_grid_mid);
            }
        } else if (inverter != null && inverter.isType6()) {
            if (inverter.getSubDeviceTypeValue() == 161) {
                this.flowInverterImageView.setBackgroundResource(R.drawable.icon_dt_type6_8_10k_eu_mid);
            } else if (inverter.getSubDeviceTypeValue() == 162) {
                this.flowInverterImageView.setBackgroundResource(R.drawable.icon_dt_type6_12k_eu_mid);
            } else if (inverter.getSubDeviceTypeValue() == 163) {
                this.flowInverterImageView.setBackgroundResource(R.drawable.icon_dt_type6_8_10k_us_mid);
            } else if (inverter.getSubDeviceTypeValue() == 164) {
                this.flowInverterImageView.setBackgroundResource(R.drawable.icon_dt_type6_12k_us_mid);
            }
        }
        if (inverter == null) {
            this.flowInverterImageView.setBackgroundResource(R.drawable.icon_dt_inverter_mid);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        System.out.println("LuxPowerLocal Overview Fragment onStart...");
        Inverter inverter = this.localConnect.getInverter();
        this.datalogSnTextView.setText(inverter != null ? inverter.getDatalogSn() : "");
        this.inverterSnTextView.setText(inverter != null ? inverter.getSerialNum() : "");
        initFlowChartByDeviceType();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - ble - onResume >>>>>>>>>>>");
        this.paused = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        System.out.println("LuxPower - ble - onPause >>>>>>>>>>>");
        this.readData = false;
        this.paused = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        System.out.println("LuxPowerLocalOverviewFragment onDestroy...");
        this.readData = false;
    }

    public void sendRead03Command1() {
        try {
            if (this.localConnect.initialize(true)) {
                if (Tool.isEmpty(this.localConnect.getDatalogSn())) {
                    return;
                }
                if (Constants.DEFAULT_DATALOG_SN.equals(this.localConnect.getDatalogSn())) {
                    String commandWaitResult = this.localConnect.getCommandWaitResult("heart_beat");
                    if (!Tool.isEmpty(commandWaitResult) && commandWaitResult.length() == 19) {
                        this.localConnect.setTcpProtocol(commandWaitResult.charAt(2) == 2 ? TCP_PROTOCOL._02 : TCP_PROTOCOL._01);
                        this.localConnect.setDatalogSn(commandWaitResult.substring(8, 18));
                    }
                }
                if (this.localConnect.read03AndInitDevice()) {
                    this.read03Success = true;
                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                LocalOverviewFragment.this.m330x75ffe956();
                            }
                        });
                        return;
                    }
                    return;
                }
                this.localConnect.setTcpProtocol(TCP_PROTOCOL._01.equals(this.localConnect.getTcpProtocol()) ? TCP_PROTOCOL._02 : TCP_PROTOCOL._01);
                return;
            }
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                activity2.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocalOverviewFragment.this.m331xf460ed35();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendRead03Command1$1$com-nfcx-luxinvpower-view-local-fragment-LocalOverviewFragment, reason: not valid java name */
    public /* synthetic */ void m330x75ffe956() {
        initFlowChartByDeviceType();
        this.datalogSnTextView.setText(this.localConnect.getInverter().getDatalogSn());
        this.inverterSnTextView.setText(this.localConnect.getInverter().getSerialNum());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendRead03Command1$2$com-nfcx-luxinvpower-view-local-fragment-LocalOverviewFragment, reason: not valid java name */
    public /* synthetic */ void m331xf460ed35() {
        clearFragmentData();
        Toast.makeText(getActivity().getApplicationContext(), R.string.local_regular_set_toast_tcp_init_fail, 1).show();
    }

    public void sendRead04Command1() {
        try {
            if (this.localConnect.initialize(true)) {
                if (Tool.isEmpty(this.localConnect.getDatalogSn())) {
                    return;
                }
                final String sendCommand = this.localConnect.sendCommand("read_04_1", DataFrameFactory.createReadMultiInputDataFrame(this.localConnect.getTcpProtocol(), this.localConnect.getDatalogSn(), 0, 40));
                if (Tool.isEmpty(sendCommand) || sendCommand.length() <= 60) {
                    return;
                }
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocalOverviewFragment.this.m332x8d32ea33(sendCommand);
                        }
                    });
                }
                sendRead04Command2();
                return;
            }
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                activity2.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocalOverviewFragment.this.m333xb93ee12();
                    }
                });
            }
            this.read03Success = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendRead04Command1$3$com-nfcx-luxinvpower-view-local-fragment-LocalOverviewFragment, reason: not valid java name */
    public /* synthetic */ void m332x8d32ea33(String str) {
        Inverter inverter = this.localConnect.getInverter();
        if (inverter == null) {
            this.read03Success = false;
            return;
        }
        int register2 = FrameTool.getRegister2(str, 28);
        int i = register2 + 0;
        if (!inverter.isAcCharger()) {
            i = i + FrameTool.getRegister2(str, 29) + FrameTool.getRegister2(str, 30);
        }
        this.todayYieldingTextView.setText(InvTool.formatDataText10(i) + " kWh");
        this.todayDischargingTextView.setText(InvTool.formatDataText10(FrameTool.getRegister2(str, 34)) + " kWh");
        int register22 = FrameTool.getRegister2(str, 36);
        this.todayExportTextView.setText(InvTool.formatDataText10(register22) + " kWh");
        int register23 = FrameTool.getRegister2(str, 35) + (FrameTool.getRegister2(str, 37) - register22) + (FrameTool.getRegister2(str, 31) - FrameTool.getRegister2(str, 32));
        if (!inverter.isAcCharger()) {
            register2 = 0;
        }
        this.todayUsageTextView.setText(InvTool.formatDataText10(register23 + register2) + " kWh");
        int register24 = FrameTool.getRegister2(str, 10);
        int register25 = FrameTool.getRegister2(str, 11);
        if (register24 > 0) {
            this.flowBatteryPowerLabelTextView.setText(R.string.main_flow_charge_power);
            this.flowBatteryPowerTextView.setText(register24 + "W");
            this.flowBatteryPowerGifView1.setMovieResource(R.drawable.arrow_left);
            this.flowBatteryPowerGifView2.setMovieResource(R.drawable.arrow_left);
            this.flowBatteryPowerGifView1.setVisibility(0);
            this.flowBatteryPowerGifView2.setVisibility(0);
        } else if (register25 > 0) {
            this.flowBatteryPowerLabelTextView.setText(R.string.main_flow_discharge_power);
            this.flowBatteryPowerTextView.setText(register25 + "W");
            this.flowBatteryPowerGifView1.setMovieResource(R.drawable.arrow_right);
            this.flowBatteryPowerGifView2.setMovieResource(R.drawable.arrow_right);
            this.flowBatteryPowerGifView1.setVisibility(0);
            this.flowBatteryPowerGifView2.setVisibility(0);
        } else {
            this.flowBatteryPowerLabelTextView.setText(R.string.main_flow_battery_power);
            this.flowBatteryPowerTextView.setText("0 W");
            this.flowBatteryPowerGifView1.setVisibility(4);
            this.flowBatteryPowerGifView2.setVisibility(4);
        }
        int regLowInt = FrameTool.getRegLowInt(str, 5);
        this.flowSocPowerTextView.setText(regLowInt + "%");
        if (regLowInt >= 90) {
            this.flowBatteryImageView.setBackgroundResource(R.drawable.flow_icon_battery_5_green);
        } else if (regLowInt >= 70) {
            this.flowBatteryImageView.setBackgroundResource(R.drawable.flow_icon_battery_4_green);
        } else if (regLowInt >= 50) {
            this.flowBatteryImageView.setBackgroundResource(R.drawable.flow_icon_battery_3_green);
        } else if (regLowInt >= 30) {
            this.flowBatteryImageView.setBackgroundResource(R.drawable.flow_icon_battery_2_green);
        } else if (regLowInt >= 10) {
            this.flowBatteryImageView.setBackgroundResource(R.drawable.flow_icon_battery_1_green);
        } else {
            this.flowBatteryImageView.setBackgroundResource(R.drawable.flow_icon_battery_0_green);
        }
        int register26 = FrameTool.getRegister2(str, 7);
        if (inverter.isAcCharger()) {
            this.flowPvPowerGifView.setVisibility(4);
            this.flowAcPvPowerTextView.setText(register26 + "W");
            if (register26 < 30) {
                this.flowAcPvPowerGifView1.setVisibility(4);
                this.flowAcPvPowerGifView2.setVisibility(4);
            } else {
                this.flowAcPvPowerGifView1.setVisibility(0);
                this.flowAcPvPowerGifView2.setVisibility(0);
            }
        } else {
            this.flowAcPvPowerGifView1.setVisibility(4);
            this.flowAcPvPowerGifView2.setVisibility(4);
            int register27 = FrameTool.getRegister2(str, 8) + register26 + FrameTool.getRegister2(str, 9);
            this.flowPvPowerTextView.setText(register27 + "W");
            if (register27 > 0) {
                this.flowPvPowerGifView.setVisibility(0);
            } else {
                this.flowPvPowerGifView.setVisibility(4);
            }
        }
        int register28 = FrameTool.getRegister2(str, 26);
        int register29 = FrameTool.getRegister2(str, 27);
        int i2 = register28 - register29;
        this.flowGridPowerTextView.setText(Math.abs(i2) + "W");
        if (i2 > 0) {
            this.flowGridPowerLabelTextView.setText(R.string.main_flow_export_power);
            this.flowGridPowerGifView1.setMovieResource(R.drawable.arrow_right);
            this.flowGridPowerGifView2.setMovieResource(R.drawable.arrow_right);
            this.flowGridPowerGifView1.setVisibility(0);
            this.flowGridPowerGifView2.setVisibility(0);
        } else if (i2 < 0) {
            this.flowGridPowerLabelTextView.setText(R.string.main_flow_import_power);
            this.flowGridPowerGifView1.setMovieResource(R.drawable.arrow_left);
            this.flowGridPowerGifView2.setMovieResource(R.drawable.arrow_left);
            this.flowGridPowerGifView1.setVisibility(0);
            this.flowGridPowerGifView2.setVisibility(0);
        } else {
            this.flowGridPowerLabelTextView.setText("");
            this.flowGridPowerGifView1.setVisibility(4);
            this.flowGridPowerGifView2.setVisibility(4);
        }
        int register210 = FrameTool.getRegister2(str, 16);
        int register211 = FrameTool.getRegister2(str, 17);
        if (register210 > register211) {
            this.flowInverterArrowGifView1.setMovieResource(R.drawable.arrow_right);
            this.flowInverterArrowGifView2.setMovieResource(R.drawable.arrow_right);
            this.flowInverterArrowGifView3.setMovieResource(R.drawable.arrow_right);
            this.flowInverterArrowGifView1.setVisibility(0);
            this.flowInverterArrowGifView2.setVisibility(0);
            this.flowInverterArrowGifView3.setVisibility(0);
        } else if (register210 < register211) {
            this.flowInverterArrowGifView1.setMovieResource(R.drawable.arrow_left);
            this.flowInverterArrowGifView2.setMovieResource(R.drawable.arrow_left);
            this.flowInverterArrowGifView3.setMovieResource(R.drawable.arrow_left);
            this.flowInverterArrowGifView1.setVisibility(0);
            this.flowInverterArrowGifView2.setVisibility(0);
            this.flowInverterArrowGifView3.setVisibility(0);
        } else {
            this.flowInverterArrowGifView1.setVisibility(4);
            this.flowInverterArrowGifView2.setVisibility(4);
            this.flowInverterArrowGifView3.setVisibility(4);
        }
        if (!inverter.isAcCharger()) {
            register26 = 0;
        }
        int i3 = register26 + (register210 - register211) + (register29 - register28);
        if (i3 < 0) {
            i3 = 0;
        }
        this.flowConsumptionPowerTextView.setText(i3 + "W");
        if (i3 > 0) {
            this.flowConsumptionPowerGifView1.setVisibility(0);
            this.flowConsumptionPowerGifView2.setVisibility(0);
        } else {
            this.flowConsumptionPowerGifView1.setVisibility(4);
            this.flowConsumptionPowerGifView2.setVisibility(4);
        }
        if (FrameTool.getRegister2(str, 0) >= 64) {
            int register212 = FrameTool.getRegister2(str, 24);
            this.flowEpsPowerTextView.setText(register212 + "W");
            if (register212 > 0) {
                this.flowEpsPowerGifView.setVisibility(0);
                return;
            } else {
                this.flowEpsPowerGifView.setVisibility(4);
                return;
            }
        }
        this.flowEpsPowerTextView.setText(R.string.main_flow_standby);
        this.flowEpsPowerGifView.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendRead04Command1$4$com-nfcx-luxinvpower-view-local-fragment-LocalOverviewFragment, reason: not valid java name */
    public /* synthetic */ void m333xb93ee12() {
        clearFragmentData();
        Toast.makeText(getActivity().getApplicationContext(), R.string.local_regular_set_toast_tcp_init_fail, 1).show();
    }

    public void sendRead04Command2() {
        try {
            if (this.localConnect.initialize(true)) {
                if (Tool.isEmpty(this.localConnect.getDatalogSn())) {
                    return;
                }
                final String sendCommand = this.localConnect.sendCommand("read_04_2", DataFrameFactory.createReadMultiInputDataFrame(this.localConnect.getTcpProtocol(), this.localConnect.getDatalogSn(), 40, 40));
                if (Tool.isEmpty(sendCommand) || sendCommand.length() <= 60) {
                    return;
                }
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment$$ExternalSyntheticLambda7
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocalOverviewFragment.this.m334xf4247a10(sendCommand);
                        }
                    });
                }
                if (this.read04_3_Success) {
                    return;
                }
                sendRead04Command3();
                return;
            }
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                activity2.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocalOverviewFragment.this.m335x72857def();
                    }
                });
            }
            this.read03Success = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendRead04Command2$5$com-nfcx-luxinvpower-view-local-fragment-LocalOverviewFragment, reason: not valid java name */
    public /* synthetic */ void m334xf4247a10(String str) {
        Inverter inverter = this.localConnect.getInverter();
        if (inverter == null) {
            this.read03Success = false;
            return;
        }
        long register4 = FrameTool.getRegister4(str, 0);
        long j = register4 + 0;
        if (!inverter.isAcCharger()) {
            j = j + FrameTool.getRegister2(str, 2) + FrameTool.getRegister2(str, 4);
        }
        this.totalYieldingTextView.setText(InvTool.formatDataText10(j) + " kWh");
        this.totalDischargingTextView.setText(InvTool.formatDataText10(FrameTool.getRegister4(str, 12)) + " kWh");
        long register42 = FrameTool.getRegister4(str, 16);
        this.totalExportTextView.setText(InvTool.formatDataText10(register42) + " kWh");
        long register43 = FrameTool.getRegister4(str, 14) + (FrameTool.getRegister4(str, 18) - register42) + (FrameTool.getRegister4(str, 6) - FrameTool.getRegister4(str, 8));
        if (!inverter.isAcCharger()) {
            register4 = 0;
        }
        this.totalUsageTextView.setText(InvTool.formatDataText10(register43 + register4) + " kWh");
        this.statusImageView.setImageResource(getStatusResourceId(FrameTool.getRegister4(str, 20) > 0 ? com.google.firebase.messaging.Constants.IPC_BUNDLE_KEY_SEND_ERROR : FrameTool.getRegister4(str, 22) > 0 ? "warning" : "normal"));
        this.localTimeTextView.setText(InvTool.formatDateTime(new Date()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendRead04Command2$6$com-nfcx-luxinvpower-view-local-fragment-LocalOverviewFragment, reason: not valid java name */
    public /* synthetic */ void m335x72857def() {
        clearFragmentData();
        Toast.makeText(getActivity().getApplicationContext(), R.string.local_regular_set_toast_tcp_init_fail, 1).show();
    }

    public void sendRead04Command3() {
        FragmentActivity activity;
        try {
            if (this.localConnect.initialize(true)) {
                if (Tool.isEmpty(this.localConnect.getDatalogSn())) {
                    return;
                }
                final String sendCommand = this.localConnect.sendCommand("read_04_3", DataFrameFactory.createReadMultiInputDataFrame(this.localConnect.getTcpProtocol(), this.localConnect.getDatalogSn(), 80, 40));
                if (Tool.isEmpty(sendCommand) || sendCommand.length() <= 60 || (activity = getActivity()) == null) {
                    return;
                }
                activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocalOverviewFragment.this.m336x5b1609ed(sendCommand);
                    }
                });
                return;
            }
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                activity2.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.fragment.LocalOverviewFragment$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocalOverviewFragment.this.m337xd9770dcc();
                    }
                });
            }
            this.read03Success = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendRead04Command3$7$com-nfcx-luxinvpower-view-local-fragment-LocalOverviewFragment, reason: not valid java name */
    public /* synthetic */ void m336x5b1609ed(String str) {
        Integer valueOf;
        Integer valueOf2;
        Integer valueOf3;
        Integer valueOf4;
        Inverter inverter = this.localConnect.getInverter();
        if (inverter == null || inverter.getModel() == null) {
            this.read03Success = false;
            return;
        }
        BATTERY_TYPE batteryTypeFromModel = inverter.getBatteryTypeFromModel();
        int register2 = FrameTool.getRegister2(str, 0);
        inverter.setBatCurrUnit(Integer.valueOf((register2 >> 10) & 1));
        Integer num = null;
        if (BATTERY_TYPE.LITHIUM.equals(BATTERY_TYPE.getBatteryTypeByCode(register2 & 3)) || register2 == 0) {
            valueOf = Integer.valueOf(FrameTool.getRegister2(str, 1));
            valueOf2 = Integer.valueOf(FrameTool.getRegister2(str, 2));
            valueOf3 = Integer.valueOf(FrameTool.getRegister2(str, 16));
            valueOf4 = Integer.valueOf(FrameTool.getRegister2(str, 17));
        } else {
            valueOf4 = null;
            valueOf = null;
            valueOf2 = null;
            valueOf3 = null;
        }
        if (BATTERY_TYPE.LITHIUM.equals(batteryTypeFromModel)) {
            if (valueOf == null) {
                valueOf = Integer.valueOf(inverter.isBatCurrUnit10() ? 660 : 6600);
            }
            if (valueOf2 == null) {
                valueOf2 = Integer.valueOf(inverter.isBatCurrUnit10() ? 660 : 6600);
            }
        }
        if (BATTERY_TYPE.LITHIUM.equals(batteryTypeFromModel) && valueOf4 != null) {
            num = valueOf4;
        } else if (inverter.getLeadAcidCapacity() != null) {
            num = inverter.getLeadAcidCapacity();
        }
        if (valueOf3 != null) {
            this.battParallelNumLabel.setVisibility(0);
            this.battParallelNumValueLabel.setVisibility(0);
            this.battParallelNumValueLabel.setText(String.valueOf(valueOf3));
        }
        if (num != null) {
            this.battCapacityLabel.setVisibility(0);
            this.battCapacityValueLabel.setVisibility(0);
            this.battCapacityValueLabel.setText(num + " Ah");
        }
        if (BATTERY_TYPE.LITHIUM.equals(batteryTypeFromModel)) {
            if (valueOf != null) {
                this.bmsLimitChargeLabel.setVisibility(0);
                this.bmsLimitChargeValueLabel.setVisibility(0);
                this.bmsLimitChargeValueLabel.setText((valueOf.intValue() / (inverter.isBatCurrUnit10() ? 10 : 100)) + " A");
            }
            if (valueOf2 != null) {
                this.bmsLimitDischargeLabel.setVisibility(0);
                this.bmsLimitDischargeValueLabel.setVisibility(0);
                this.bmsLimitDischargeValueLabel.setText((valueOf2.intValue() / (inverter.isBatCurrUnit10() ? 10 : 100)) + " A");
            }
        }
        this.read04_3_Success = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendRead04Command3$8$com-nfcx-luxinvpower-view-local-fragment-LocalOverviewFragment, reason: not valid java name */
    public /* synthetic */ void m337xd9770dcc() {
        clearFragmentData();
        Toast.makeText(getActivity().getApplicationContext(), R.string.local_regular_set_toast_tcp_init_fail, 1).show();
    }

    private void clearFragmentData() {
        this.todayYieldingTextView.setText("-- kWh");
        this.totalYieldingTextView.setText("-- kWh");
        this.todayDischargingTextView.setText("-- kWh");
        this.totalDischargingTextView.setText("-- kWh");
        this.todayExportTextView.setText("-- kWh");
        this.totalExportTextView.setText("-- kWh");
        this.todayUsageTextView.setText("-- kWh");
        this.totalUsageTextView.setText("-- kWh");
        this.statusImageView.setImageResource(R.drawable.status_offline);
        this.flowBatteryPowerLabelTextView.setText(R.string.main_flow_battery_power);
        this.flowBatteryPowerTextView.setText("");
        this.flowBatteryImageView.setBackgroundResource(R.drawable.flow_icon_battery_0_green);
        this.flowSocPowerTextView.setText("");
        this.flowPvPowerTextView.setText("");
        this.flowAcPvPowerTextView.setText("");
        this.flowGridPowerTextView.setText("");
        this.flowGridPowerLabelTextView.setText("");
        this.flowConsumptionPowerTextView.setText("");
        this.flowEpsPowerTextView.setText("");
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
                if (str.equals(com.google.firebase.messaging.Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
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
}
