package com.nfcx.luxinvpower.view.userCenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ModifyPasswordActivity extends Activity {
    public static ModifyPasswordActivity instance;
    private Button modifyPasswordButton;
    private EditText newPasswordEditText;
    private EditText oldPasswordEditText;
    private EditText repeatPasswordEditText;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_modify_password);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ModifyPasswordActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ModifyPasswordActivity.instance.finish();
            }
        });
        if (!PLATFORM.LUX_POWER.equals(GlobalInfo.getInstance().getUserData().getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        this.oldPasswordEditText = (EditText) findViewById(R.id.oldPasswordEditText);
        this.newPasswordEditText = (EditText) findViewById(R.id.newPasswordEditText);
        this.repeatPasswordEditText = (EditText) findViewById(R.id.repeatPasswordEditText);
        this.modifyPasswordButton = (Button) findViewById(R.id.modifyPasswordButton);
    }

    public void clickModifyPasswordButton(View view) {
        String obj = this.oldPasswordEditText.getText().toString();
        if (Tool.isEmpty(obj)) {
            Tool.alert(this, R.string.page_register_error_old_password_empty);
            this.oldPasswordEditText.requestFocus();
            return;
        }
        String obj2 = this.newPasswordEditText.getText().toString();
        if (Tool.isEmpty(obj2)) {
            Tool.alert(this, R.string.page_register_error_new_password_empty);
            this.newPasswordEditText.requestFocus();
            return;
        }
        if (obj2.length() < 6) {
            Tool.alert(this, R.string.page_register_error_new_password_minLength);
            this.newPasswordEditText.requestFocus();
            return;
        }
        if (Tool.containInvalidCharacter(obj2)) {
            Tool.alert(this, R.string.phrase_password_error_char_invalid);
            this.newPasswordEditText.requestFocus();
            return;
        }
        if (!obj2.equals(this.repeatPasswordEditText.getText().toString())) {
            Tool.alert(this, R.string.page_register_error_repeat_password_different);
            this.repeatPasswordEditText.requestFocus();
            return;
        }
        UserData userData = GlobalInfo.getInstance().getUserData();
        HashMap hashMap = new HashMap();
        hashMap.put("userId", String.valueOf(userData.getUserId()));
        hashMap.put("oldPassword", obj);
        hashMap.put("newPassword", obj2);
        this.modifyPasswordButton.setEnabled(false);
        new ModifyPasswordTask().execute(hashMap);
    }

    /* loaded from: classes2.dex */
    private static class ModifyPasswordTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private ModifyPasswordTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/system/user/modifyPasswordJson", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((ModifyPasswordTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (jSONObject.getBoolean("success")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ModifyPasswordActivity.instance);
                            builder.setTitle(R.string.phrase_message).setIcon(android.R.drawable.ic_dialog_info).setMessage(R.string.page_modify_password_success).setNegativeButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.ModifyPasswordActivity.ModifyPasswordTask.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ModifyPasswordActivity.instance.finish();
                                }
                            });
                            builder.show();
                        } else {
                            Tool.alert(ModifyPasswordActivity.instance, R.string.page_modify_password_failed);
                        }
                    } else {
                        Tool.alert(ModifyPasswordActivity.instance, R.string.phrase_toast_network_error);
                    }
                    if (ModifyPasswordActivity.instance == null || ModifyPasswordActivity.instance.isDestroyed()) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (ModifyPasswordActivity.instance == null || ModifyPasswordActivity.instance.isDestroyed()) {
                        return;
                    }
                }
                ModifyPasswordActivity.instance.modifyPasswordButton.setEnabled(true);
            } catch (Throwable th) {
                if (ModifyPasswordActivity.instance != null && !ModifyPasswordActivity.instance.isDestroyed()) {
                    ModifyPasswordActivity.instance.modifyPasswordButton.setEnabled(true);
                }
                throw th;
            }
        }
    }
}
