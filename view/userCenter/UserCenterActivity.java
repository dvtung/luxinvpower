package com.nfcx.luxinvpower.view.userCenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Custom;
import com.nfcx.luxinvpower.view.logout.LogoutActivity;
import com.nfcx.luxinvpower.view.userCenter.UserCenterActivity;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class UserCenterActivity extends Activity {
    public static UserCenterActivity instance;
    private ConstraintLayout deleteUserActionLayout;
    private TextView deleteUserActionTextView;
    private ToggleButton deleteUserActionToggleButton;
    private Button deleteUserButton;
    private ConstraintLayout deleteUserCtrlLayout;
    private TextView techSupport1Label;
    private TextView techSupport1Value;
    private TextView techSupport2Label;
    private ConstraintLayout techSupport2Layout;
    private TextView techSupport2Value;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_center);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.UserCenterActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserCenterActivity.instance.finish();
            }
        });
        UserData userData = GlobalInfo.getInstance().getUserData();
        if (!PLATFORM.LUX_POWER.equals(userData.getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        this.deleteUserCtrlLayout = (ConstraintLayout) findViewById(R.id.deleteUserCtrlLayout);
        this.deleteUserButton = (Button) findViewById(R.id.deleteUserButton);
        this.deleteUserActionLayout = (ConstraintLayout) findViewById(R.id.label_deleteUser_set_layout);
        this.deleteUserActionTextView = (TextView) findViewById(R.id.label_deleteUser_set_textView);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.label_deleteUser_set_toggleButton);
        this.deleteUserActionToggleButton = toggleButton;
        toggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.UserCenterActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UserCenterActivity.this.deleteUserActionToggleButton.isChecked()) {
                    UserCenterActivity.this.deleteUserActionTextView.setText(R.string.phrase_button_collapse);
                    UserCenterActivity.this.deleteUserButton.setVisibility(0);
                } else {
                    UserCenterActivity.this.deleteUserActionTextView.setText(R.string.phrase_button_expand);
                    UserCenterActivity.this.deleteUserButton.setVisibility(8);
                }
            }
        });
        this.deleteUserActionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.UserCenterActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UserCenterActivity.this.deleteUserActionToggleButton.isChecked()) {
                    UserCenterActivity.this.deleteUserActionToggleButton.setChecked(false);
                    UserCenterActivity.this.deleteUserActionTextView.setText(R.string.phrase_button_expand);
                    UserCenterActivity.this.deleteUserButton.setVisibility(8);
                } else {
                    UserCenterActivity.this.deleteUserActionToggleButton.setChecked(true);
                    UserCenterActivity.this.deleteUserActionTextView.setText(R.string.phrase_button_collapse);
                    UserCenterActivity.this.deleteUserButton.setVisibility(0);
                }
            }
        });
        if (ROLE.VIEWER.equals(userData.getRole())) {
            this.deleteUserCtrlLayout.setVisibility(0);
        }
        initTechSupportInfo(userData.getTechInfo());
    }

    private void initTechSupportInfo(JSONObject jSONObject) {
        this.techSupport1Label = (TextView) findViewById(R.id.techSupport1Label);
        this.techSupport1Value = (TextView) findViewById(R.id.techSupport1Value);
        this.techSupport2Label = (TextView) findViewById(R.id.techSupport2Label);
        this.techSupport2Value = (TextView) findViewById(R.id.techSupport2Value);
        this.techSupport2Layout = (ConstraintLayout) findViewById(R.id.techSupport2Layout);
        if (jSONObject != null && jSONObject.has("techInfoCount")) {
            try {
                int i = jSONObject.getInt("techInfoCount");
                if (i > 0) {
                    this.techSupport2Label.setText(getString(R.string.phrase_tech_support) + " 2");
                    this.techSupport1Value.setText(Tool.getTechSupportPrefix(instance, jSONObject, 1) + jSONObject.getString("techInfo1"));
                    if (i == 1) {
                        this.techSupport1Label.setText(getString(R.string.phrase_tech_support));
                        this.techSupport2Value.setText("");
                        this.techSupport2Layout.setVisibility(4);
                        return;
                    } else {
                        this.techSupport1Label.setText(getString(R.string.phrase_tech_support) + " 1");
                        this.techSupport2Value.setText(Tool.getTechSupportPrefix(instance, jSONObject, 2) + jSONObject.getString("techInfo2"));
                        this.techSupport2Layout.setVisibility(0);
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.techSupport1Label.setText(R.string.user_center_manufacturer_email);
        this.techSupport1Value.setText(Custom.MANUFACTURER_EMAIL);
        this.techSupport2Label.setText(R.string.user_center_manufacturer_tel);
        this.techSupport2Value.setText(Custom.MANUFACTURER_TEL);
        this.techSupport2Layout.setVisibility(0);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        UserData userData = GlobalInfo.getInstance().getUserData();
        ((TextView) findViewById(R.id.user_center_username_textView)).setText(userData.getUsername());
        ((TextView) findViewById(R.id.user_center_realName_textView)).setText(userData.getRealName());
        ((TextView) findViewById(R.id.user_center_email_textView)).setText(userData.getEmail());
        ((TextView) findViewById(R.id.user_center_country_textView)).setText(userData.getCountryText());
        ((TextView) findViewById(R.id.user_center_timezone_textView)).setText(userData.getTimezoneText());
        ((TextView) findViewById(R.id.user_center_telNumber_textView)).setText(userData.getTelNumber());
        ((TextView) findViewById(R.id.user_center_address_textView)).setText(userData.getAddress());
    }

    public void clickEditUserButton(View view) {
        startActivity(new Intent(this, (Class<?>) EditUserActivity.class));
    }

    public void clickModifyPasswordButton(View view) {
        startActivity(new Intent(this, (Class<?>) ModifyPasswordActivity.class));
    }

    public void clickLogoutButton(View view) {
        startActivity(new Intent(this, (Class<?>) LogoutActivity.class));
    }

    public void clickDeleteUserButton(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.phrase_delete_user).setIcon(android.R.drawable.ic_dialog_info).setMessage(R.string.phrase_delete_user_text).setPositiveButton(R.string.phrase_button_delete, new AnonymousClass3()).setNegativeButton(R.string.phrase_button_cancel, (DialogInterface.OnClickListener) null);
        AlertDialog create = builder.create();
        create.show();
        Button button = create.getButton(-1);
        if (button != null) {
            button.setTextColor(getResources().getColor(R.color.mainGray));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.userCenter.UserCenterActivity$3, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass3 implements DialogInterface.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.UserCenterActivity$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    UserCenterActivity.AnonymousClass3.this.m458xeee9a976();
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$1$com-nfcx-luxinvpower-view-userCenter-UserCenterActivity$3, reason: not valid java name */
        public /* synthetic */ void m458xeee9a976() {
            HashMap hashMap = new HashMap();
            hashMap.put("userId", String.valueOf(GlobalInfo.getInstance().getUserData().getUserId()));
            final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/system/user/remove", hashMap);
            UserCenterActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.UserCenterActivity$3$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    UserCenterActivity.AnonymousClass3.this.m457xde33dcb5(postJson);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-nfcx-luxinvpower-view-userCenter-UserCenterActivity$3, reason: not valid java name */
        public /* synthetic */ void m457xde33dcb5(JSONObject jSONObject) {
            if (jSONObject != null) {
                try {
                    if (jSONObject.getBoolean("success")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserCenterActivity.this);
                        builder.setTitle(R.string.phrase_message).setIcon(android.R.drawable.ic_dialog_info).setMessage(R.string.phrase_delete_user_success).setNegativeButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.UserCenterActivity.3.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                LogoutActivity.doLogout(UserCenterActivity.this);
                            }
                        });
                        builder.show();
                    }
                } catch (Exception e) {
                    Tool.alert(UserCenterActivity.this, R.string.phrase_toast_response_error);
                    e.printStackTrace();
                    return;
                }
            }
            Tool.alert(UserCenterActivity.this, R.string.phrase_toast_unknown_error);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }
}
