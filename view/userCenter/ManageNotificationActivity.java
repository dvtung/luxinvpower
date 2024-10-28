package com.nfcx.luxinvpower.view.userCenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ManageNotificationActivity extends Activity {
    private EditText BattLowNoticeSocEditText;
    private ConstraintLayout BattLowNoticeSocNotificationLayout;
    private ConstraintLayout BattSocLowNotificationLayout;
    private ConstraintLayout allowNotificationsLayout;
    private ToggleButton allowNotificationsToggleButton;
    private Button battLowNoticeSocButton;
    private ToggleButton battSocLowNotificationsToggleButton;
    private ConstraintLayout gridOutageNotificationLayout;
    private ToggleButton gridOutageNotificationToggleButton;
    private TextView notificationTypeText;
    private ConstraintLayout octopusTOUNotificationsLayout;
    private ToggleButton octopusTOUNotificationsToggleButton;
    private ConstraintLayout updatesNotificationLayout;
    private ToggleButton updatesNotificationToggleButton;
    private ConstraintLayout weatherUpdatesNotificationLayout;
    private ToggleButton weatherUpdatesNotificationToggleButton;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_manage_notifications);
        initializeViews();
        setupListeners();
        updateVisibilityBasedOnRole();
    }

    private void initializeViews() {
        this.allowNotificationsLayout = (ConstraintLayout) findViewById(R.id.activity_Allow_NotificationsLayout);
        this.notificationTypeText = (TextView) findViewById(R.id.notification_type);
        this.octopusTOUNotificationsLayout = (ConstraintLayout) findViewById(R.id.activity_Octopus_TOU_NotificationsLayout);
        this.weatherUpdatesNotificationLayout = (ConstraintLayout) findViewById(R.id.activity_Weather_Updates_NotificationLayout);
        this.gridOutageNotificationLayout = (ConstraintLayout) findViewById(R.id.activity_Grid_Outage_NotificationLayout);
        this.BattSocLowNotificationLayout = (ConstraintLayout) findViewById(R.id.activity_BattSocLow_NotificationLayout);
        this.BattLowNoticeSocNotificationLayout = (ConstraintLayout) findViewById(R.id.activity_BattLowNoticeSoc_NotificationLayout);
        this.updatesNotificationLayout = (ConstraintLayout) findViewById(R.id.activity_Updates_NotificationLayout);
        this.allowNotificationsToggleButton = (ToggleButton) findViewById(R.id.activity_Allow_Notifications_toggleButton);
        this.octopusTOUNotificationsToggleButton = (ToggleButton) findViewById(R.id.activity_Octopus_TOU_Notifications_toggleButton);
        this.weatherUpdatesNotificationToggleButton = (ToggleButton) findViewById(R.id.activity_Weather_Updates_Notification_toggleButton);
        this.gridOutageNotificationToggleButton = (ToggleButton) findViewById(R.id.activity_Grid_Outage_Notification_toggleButton);
        this.battSocLowNotificationsToggleButton = (ToggleButton) findViewById(R.id.activity_NoticeBattSocLow_toggleButton);
        this.updatesNotificationToggleButton = (ToggleButton) findViewById(R.id.activity_Updates_NotificationLayout_toggleButton);
        this.BattLowNoticeSocEditText = (EditText) findViewById(R.id.activity_BattLowNoticeSoc_NotificationEditText);
        Button button = (Button) findViewById(R.id.activity_BattLowNoticeSoc_Notification_Button);
        this.battLowNoticeSocButton = button;
        button.setEnabled(false);
    }

    private void setupListeners() {
        findViewById(R.id.backImageViewLayout).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageNotificationActivity.this.m418xb118a889(view);
            }
        });
        this.allowNotificationsLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageNotificationActivity.this.openNotificationSettings(view);
            }
        });
        this.allowNotificationsToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageNotificationActivity.this.openNotificationSettings(view);
            }
        });
        this.BattLowNoticeSocEditText.addTextChangedListener(new AnonymousClass1());
        this.battSocLowNotificationsToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageNotificationActivity.this.m419xfed8208a(view);
            }
        });
        this.battLowNoticeSocButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageNotificationActivity.this.m420x4c97988b(view);
            }
        });
        this.gridOutageNotificationToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageNotificationActivity.this.m421x9a57108c(view);
            }
        });
        this.octopusTOUNotificationsToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageNotificationActivity.this.m422xe816888d(view);
            }
        });
        this.weatherUpdatesNotificationToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageNotificationActivity.this.m423x35d6008e(view);
            }
        });
        this.updatesNotificationToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageNotificationActivity.this.m424x8395788f(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupListeners$0$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m418xb118a889(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onTextChanged$0$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity$1, reason: not valid java name */
        public /* synthetic */ void m427xb0eec562(CharSequence charSequence) {
            ManageNotificationActivity.this.battLowNoticeSocButton.setEnabled(charSequence.length() > 0);
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(final CharSequence charSequence, int i, int i2, int i3) {
            ManageNotificationActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ManageNotificationActivity.AnonymousClass1.this.m427xb0eec562(charSequence);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupListeners$1$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m419xfed8208a(View view) {
        saveNotificationSettings();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupListeners$2$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m420x4c97988b(View view) {
        saveNotificationSettings();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupListeners$3$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m421x9a57108c(View view) {
        saveNotificationSettings();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupListeners$4$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m422xe816888d(View view) {
        saveNotificationSettings();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupListeners$5$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m423x35d6008e(View view) {
        saveNotificationSettings();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupListeners$6$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m424x8395788f(View view) {
        Tool.alert(this, "UpdatesNotificationToggleButton");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openNotificationSettings(View view) {
        Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        startActivity(intent);
    }

    private void updateVisibilityBasedOnRole() {
        UserData userData = GlobalInfo.getInstance().getUserData();
        if (!ROLE.VIEWER.equals(userData.getRole())) {
            this.notificationTypeText.setVisibility(8);
            this.BattSocLowNotificationLayout.setVisibility(8);
            this.BattLowNoticeSocNotificationLayout.setVisibility(8);
            this.octopusTOUNotificationsLayout.setVisibility(8);
            this.weatherUpdatesNotificationLayout.setVisibility(8);
            this.gridOutageNotificationLayout.setVisibility(8);
            return;
        }
        if (!userData.isAllowViewerVisitOptimalSet()) {
            this.octopusTOUNotificationsLayout.setVisibility(8);
        }
        if (userData.isAllowViewerVisitWeatherSet() || userData.getClusterId() == 4) {
            return;
        }
        this.weatherUpdatesNotificationLayout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchNotificationSettings() {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ManageNotificationActivity.this.m413x53c91c3c();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$fetchNotificationSettings$7$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m413x53c91c3c() {
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                ManageNotificationActivity.this.updateNotificationToggleButtons();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNotificationToggleButtons() {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                ManageNotificationActivity.this.m426x94fb934f();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateNotificationToggleButtons$9$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m426x94fb934f() {
        UserData userData = GlobalInfo.getInstance().getUserData();
        HashMap hashMap = new HashMap();
        hashMap.put("userId", String.valueOf(userData.getUserId()));
        JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/user/getUserAppNoticeInfo", hashMap);
        if (postJson != null) {
            try {
                if (postJson.getBoolean("success")) {
                    boolean z = postJson.getBoolean("noticeAppWarning");
                    boolean z2 = postJson.getBoolean("noticeOctopusSet");
                    boolean z3 = postJson.getBoolean("noticeWeatherSet");
                    boolean z4 = postJson.getBoolean("noticeBattSocLow");
                    int i = postJson.getInt("battLowNoticeSoc");
                    this.battSocLowNotificationsToggleButton.setChecked(z4);
                    this.gridOutageNotificationToggleButton.setChecked(z);
                    this.octopusTOUNotificationsToggleButton.setChecked(z2);
                    this.weatherUpdatesNotificationToggleButton.setChecked(z3);
                    this.BattLowNoticeSocEditText.setText(i + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                ManageNotificationActivity.this.m425x473c1b4e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateNotificationToggleButtons$8$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m425x473c1b4e() {
        this.battLowNoticeSocButton.setEnabled(false);
    }

    private void saveNotificationSettings() {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                ManageNotificationActivity.this.m417xc4734bb9();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveNotificationSettings$10$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m417xc4734bb9() {
        UserData userData = GlobalInfo.getInstance().getUserData();
        HashMap hashMap = new HashMap();
        hashMap.put("userId", String.valueOf(userData.getUserId()));
        hashMap.put("noticeAppFault", "false");
        hashMap.put("noticeBattSocLow", String.valueOf(this.battSocLowNotificationsToggleButton.isChecked()));
        hashMap.put("battLowNoticeSoc", this.BattLowNoticeSocEditText.getText().toString().trim());
        hashMap.put("noticeAppWarning", String.valueOf(this.gridOutageNotificationToggleButton.isChecked()));
        hashMap.put("noticeOctopusSet", String.valueOf(this.octopusTOUNotificationsToggleButton.isChecked()));
        hashMap.put("noticeWeatherSet", String.valueOf(this.weatherUpdatesNotificationToggleButton.isChecked()));
        handleSaveResponse(HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/user/saveUserAppNoticeInfo", hashMap));
    }

    private void handleSaveResponse(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.getBoolean("success")) {
                    runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            ManageNotificationActivity.this.m414x4f7ff03f();
                        }
                    });
                }
            } catch (JSONException unused) {
                runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        ManageNotificationActivity.this.m416xeafee041();
                    }
                });
                return;
            }
        }
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ManageNotificationActivity.this.fetchNotificationSettings();
            }
        });
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.ManageNotificationActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                ManageNotificationActivity.this.m415x9d3f6840();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$handleSaveResponse$11$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m414x4f7ff03f() {
        Tool.alert(this, getString(R.string.local_set_result_success));
        this.battLowNoticeSocButton.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$handleSaveResponse$12$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m415x9d3f6840() {
        Tool.alert(this, getString(R.string.local_set_result_failed));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$handleSaveResponse$13$com-nfcx-luxinvpower-view-userCenter-ManageNotificationActivity, reason: not valid java name */
    public /* synthetic */ void m416xeafee041() {
        Toast.makeText(this, R.string.phrase_toast_network_error, 0).show();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        fetchNotificationSettings();
    }
}
