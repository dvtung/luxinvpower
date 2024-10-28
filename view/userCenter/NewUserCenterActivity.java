package com.nfcx.luxinvpower.view.userCenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.user.ROLE;

/* loaded from: classes2.dex */
public class NewUserCenterActivity extends Activity {
    public static NewUserCenterActivity instance;
    private TextView activityOnlineDays;
    private ConstraintLayout newSettingPageLayout;
    private ConstraintLayout notificationLayout;
    private ConstraintLayout octopusChargePageLayout;
    private ConstraintLayout settingsLayout;
    private ConstraintLayout userAccountLayout;
    private TextView userName;
    private ConstraintLayout weatherOptimizePageLayout;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_new_user_center);
        instance = this;
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewUserCenterActivity.instance.finish();
            }
        });
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.activity_userAccount_Layout);
        this.userAccountLayout = constraintLayout;
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewUserCenterActivity.this.startActivity(new Intent(NewUserCenterActivity.instance, (Class<?>) UserCenterActivity.class));
            }
        });
        ConstraintLayout constraintLayout2 = (ConstraintLayout) findViewById(R.id.activity_setting_layout);
        this.settingsLayout = constraintLayout2;
        constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewUserCenterActivity.this.startActivity(new Intent(NewUserCenterActivity.instance, (Class<?>) UserCenterActivity.class));
            }
        });
        ConstraintLayout constraintLayout3 = (ConstraintLayout) findViewById(R.id.activity_notification_layout);
        this.notificationLayout = constraintLayout3;
        constraintLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewUserCenterActivity.this.startActivity(new Intent(NewUserCenterActivity.instance, (Class<?>) ManageNotificationActivity.class));
            }
        });
        ConstraintLayout constraintLayout4 = (ConstraintLayout) findViewById(R.id.use_newSettingPage_layout);
        this.newSettingPageLayout = constraintLayout4;
        constraintLayout4.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewUserCenterActivity.this.startActivity(new Intent(NewUserCenterActivity.instance, (Class<?>) NormalSettingActivity.class));
            }
        });
        ConstraintLayout constraintLayout5 = (ConstraintLayout) findViewById(R.id.weather_optimizePage_layout);
        this.weatherOptimizePageLayout = constraintLayout5;
        constraintLayout5.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewUserCenterActivity.this.startActivity(new Intent(NewUserCenterActivity.instance, (Class<?>) WeatherOptimizeActivity.class));
            }
        });
        ConstraintLayout constraintLayout6 = (ConstraintLayout) findViewById(R.id.octopus_chargePage_layout);
        this.octopusChargePageLayout = constraintLayout6;
        constraintLayout6.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewUserCenterActivity.this.startActivity(new Intent(NewUserCenterActivity.instance, (Class<?>) OctopusChargeActivity.class));
            }
        });
    }

    private void updateVisibilityBasedOnRole(UserData userData) {
        if (userData != null && ROLE.VIEWER.equals(userData.getRole())) {
            this.notificationLayout.setVisibility(0);
            if (!userData.isAllowViewerVisitOptimalSet()) {
                this.octopusChargePageLayout.setVisibility(8);
            }
            System.out.println("LuxPower - userData.isAllowViewerVisitWeatherSet() = " + userData.isAllowViewerVisitWeatherSet() + ", userData.getClusterId() = " + userData.getClusterId());
            if (userData.isAllowViewerVisitWeatherSet() || userData.getClusterId() == 4) {
                return;
            }
            this.weatherOptimizePageLayout.setVisibility(8);
            return;
        }
        this.notificationLayout.setVisibility(8);
        this.octopusChargePageLayout.setVisibility(8);
        this.weatherOptimizePageLayout.setVisibility(8);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        UserData userData = GlobalInfo.getInstance().getUserData();
        TextView textView = (TextView) findViewById(R.id.activity_userName);
        this.userName = textView;
        textView.setText(userData.getUsername());
        this.activityOnlineDays = (TextView) findViewById(R.id.activity_onlineDays);
        this.activityOnlineDays.setText(instance.getString(R.string.online_for_days, new Object[]{String.valueOf(userData.getUserCreatedDays())}));
        updateVisibilityBasedOnRole(userData);
    }
}
