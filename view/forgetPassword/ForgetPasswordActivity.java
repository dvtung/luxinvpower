package com.nfcx.luxinvpower.view.forgetPassword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ForgetPasswordActivity extends Activity {
    public static ForgetPasswordActivity instance;
    private EditText datalogSnEditText;
    private ConstraintLayout datalogSnLayout;
    private EditText emailEditText;
    private ToggleButton forgetUsernameButton;
    private Button getVerifyCodeButton;
    private EditText passwordEditText;
    private ConstraintLayout passwordLayout;
    private EditText repeatPasswordEditText;
    private ConstraintLayout repeatPasswordLayout;
    private Button resetPasswordButton;
    private EditText usernameEditText;
    private EditText verifyCodeEditText;
    private ConstraintLayout verifyCodeLayout;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_forget_password);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.forgetPassword.ForgetPasswordActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ForgetPasswordActivity.instance.finish();
            }
        });
        this.datalogSnLayout = (ConstraintLayout) findViewById(R.id.datalogSnLayout);
        this.datalogSnEditText = (EditText) findViewById(R.id.datalogSnEditText);
        this.usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.forgetUsernameButton);
        this.forgetUsernameButton = toggleButton;
        toggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.forgetPassword.ForgetPasswordActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ForgetPasswordActivity.this.datalogSnLayout.setVisibility(ForgetPasswordActivity.this.forgetUsernameButton.isChecked() ? 0 : 8);
                ForgetPasswordActivity.this.usernameEditText.setEnabled(!ForgetPasswordActivity.this.forgetUsernameButton.isChecked());
            }
        });
        this.emailEditText = (EditText) findViewById(R.id.emailEditText);
        this.getVerifyCodeButton = (Button) findViewById(R.id.getVerifyCodeButton);
        this.verifyCodeLayout = (ConstraintLayout) findViewById(R.id.verifyCodeLayout);
        this.verifyCodeEditText = (EditText) findViewById(R.id.verifyCodeEditText);
        this.passwordLayout = (ConstraintLayout) findViewById(R.id.passwordLayout);
        this.passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        this.repeatPasswordLayout = (ConstraintLayout) findViewById(R.id.repeatPasswordLayout);
        this.repeatPasswordEditText = (EditText) findViewById(R.id.repeatPasswordEditText);
        this.resetPasswordButton = (Button) findViewById(R.id.resetPasswordButton);
    }

    private Map<String, String> buildParamMapForRequest() {
        boolean isChecked = this.forgetUsernameButton.isChecked();
        String obj = this.usernameEditText.getText().toString();
        String obj2 = this.datalogSnEditText.getText().toString();
        HashMap hashMap = new HashMap();
        hashMap.put("useDatalogSn", String.valueOf(isChecked));
        if (isChecked) {
            if (Tool.isEmpty(obj2)) {
                Tool.alert(this, R.string.page_register_error_datalogSn_empty);
                this.datalogSnEditText.requestFocus();
                return null;
            }
            if (obj2.length() != 10) {
                Tool.alert(this, R.string.page_register_error_datalogSn_length);
                this.datalogSnEditText.requestFocus();
                return null;
            }
            hashMap.put("datalogSn", obj2);
        } else {
            if (Tool.isEmpty(obj)) {
                Tool.alert(this, R.string.page_register_error_account_empty);
                this.usernameEditText.requestFocus();
                return null;
            }
            hashMap.put("username", obj);
        }
        return hashMap;
    }

    public void clickGetVerifyCodeButton(View view) {
        Map<String, String> buildParamMapForRequest = buildParamMapForRequest();
        if (buildParamMapForRequest != null) {
            this.forgetUsernameButton.setEnabled(false);
            this.getVerifyCodeButton.setEnabled(false);
            new GetVerifyCodeTask().execute(buildParamMapForRequest);
        }
    }

    /* loaded from: classes2.dex */
    private static class GetVerifyCodeTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private GetVerifyCodeTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/forgetPassword/find", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((GetVerifyCodeTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (jSONObject.has("email")) {
                            ForgetPasswordActivity.instance.emailEditText.setText(jSONObject.getString("email"));
                        } else {
                            ForgetPasswordActivity.instance.emailEditText.setText("");
                        }
                        if (ForgetPasswordActivity.instance.forgetUsernameButton.isChecked()) {
                            if (jSONObject.has("username")) {
                                ForgetPasswordActivity.instance.usernameEditText.setText(jSONObject.getString("username"));
                            } else {
                                ForgetPasswordActivity.instance.usernameEditText.setText("");
                            }
                        }
                        char c = 0;
                        if (jSONObject.getBoolean("success")) {
                            ForgetPasswordActivity.instance.datalogSnEditText.setEnabled(false);
                            ForgetPasswordActivity.instance.usernameEditText.setEnabled(false);
                            ForgetPasswordActivity.instance.getVerifyCodeButton.setVisibility(4);
                            ForgetPasswordActivity.instance.verifyCodeLayout.setVisibility(0);
                            ForgetPasswordActivity.instance.passwordLayout.setVisibility(0);
                            ForgetPasswordActivity.instance.repeatPasswordLayout.setVisibility(0);
                            ForgetPasswordActivity.instance.resetPasswordButton.setVisibility(0);
                            Tool.alert(ForgetPasswordActivity.instance, R.string.page_find_password_send_mail_success);
                        } else {
                            String string = jSONObject.getString("msg");
                            switch (string.hashCode()) {
                                case -1961423567:
                                    if (string.equals("accountNotExist")) {
                                        c = 2;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case -362745952:
                                    if (string.equals("emailNotExist")) {
                                        c = 3;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 929433229:
                                    if (string.equals("datalogSnNotBindViewer")) {
                                        c = 1;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 1845760601:
                                    if (string.equals("datalogSnNotExist")) {
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                default:
                                    c = 65535;
                                    break;
                            }
                            if (c == 0) {
                                Tool.alert(ForgetPasswordActivity.instance, R.string.page_find_password_error_datalogsn_not_exist);
                            } else if (c == 1) {
                                Tool.alert(ForgetPasswordActivity.instance, R.string.page_find_password_error_datalogsn_not_bind_viewer);
                            } else if (c == 2) {
                                Tool.alert(ForgetPasswordActivity.instance, R.string.page_find_password_error_account_not_exist);
                            } else if (c == 3) {
                                Tool.alert(ForgetPasswordActivity.instance, R.string.page_find_password_error_user_not_exists);
                            } else {
                                Tool.alert(ForgetPasswordActivity.instance, R.string.phrase_toast_unknown_error);
                            }
                        }
                    } else {
                        Tool.alert(ForgetPasswordActivity.instance, R.string.phrase_toast_network_error);
                    }
                    if (ForgetPasswordActivity.instance == null || ForgetPasswordActivity.instance.isDestroyed()) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (ForgetPasswordActivity.instance == null || ForgetPasswordActivity.instance.isDestroyed()) {
                        return;
                    }
                }
                ForgetPasswordActivity.instance.forgetUsernameButton.setEnabled(true);
                ForgetPasswordActivity.instance.getVerifyCodeButton.setEnabled(true);
            } catch (Throwable th) {
                if (ForgetPasswordActivity.instance != null && !ForgetPasswordActivity.instance.isDestroyed()) {
                    ForgetPasswordActivity.instance.forgetUsernameButton.setEnabled(true);
                    ForgetPasswordActivity.instance.getVerifyCodeButton.setEnabled(true);
                }
                throw th;
            }
        }
    }

    public void clickResetPasswordButton(View view) {
        String obj = this.verifyCodeEditText.getText().toString();
        if (Tool.isEmpty(obj)) {
            Tool.alert(this, R.string.page_register_error_verify_code_empty);
            this.verifyCodeEditText.requestFocus();
            return;
        }
        String obj2 = this.passwordEditText.getText().toString();
        if (Tool.isEmpty(obj2)) {
            Tool.alert(this, R.string.page_register_error_password_empty);
            this.passwordEditText.requestFocus();
            return;
        }
        if (obj2.length() < 6) {
            Tool.alert(this, R.string.page_register_error_password_minLength);
            this.passwordEditText.requestFocus();
            return;
        }
        if (Tool.containInvalidCharacter(obj2)) {
            Tool.alert(this, R.string.phrase_password_error_char_invalid);
            this.passwordEditText.requestFocus();
            return;
        }
        if (!obj2.equals(this.repeatPasswordEditText.getText().toString())) {
            Tool.alert(this, R.string.page_register_error_repeat_password_different);
            this.repeatPasswordEditText.requestFocus();
            return;
        }
        Map<String, String> buildParamMapForRequest = buildParamMapForRequest();
        if (buildParamMapForRequest != null) {
            buildParamMapForRequest.put("verifyCode", obj);
            buildParamMapForRequest.put("password", obj2);
            this.resetPasswordButton.setEnabled(false);
            new ResetPasswordTask().execute(buildParamMapForRequest);
        }
    }

    /* loaded from: classes2.dex */
    private static class ResetPasswordTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private ResetPasswordTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/forgetPassword/reset", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((ResetPasswordTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (jSONObject.getBoolean("success")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPasswordActivity.instance);
                            builder.setTitle(R.string.phrase_message).setIcon(android.R.drawable.ic_dialog_info).setMessage(R.string.page_find_password_reset_success).setNegativeButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.forgetPassword.ForgetPasswordActivity.ResetPasswordTask.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ForgetPasswordActivity.instance.finish();
                                }
                            });
                            builder.show();
                        } else {
                            Tool.alert(ForgetPasswordActivity.instance, R.string.page_find_password_reset_failure);
                        }
                    } else {
                        Tool.alert(ForgetPasswordActivity.instance, R.string.phrase_toast_network_error);
                    }
                    if (ForgetPasswordActivity.instance == null || ForgetPasswordActivity.instance.isDestroyed()) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (ForgetPasswordActivity.instance == null || ForgetPasswordActivity.instance.isDestroyed()) {
                        return;
                    }
                }
                ForgetPasswordActivity.instance.resetPasswordButton.setEnabled(true);
            } catch (Throwable th) {
                if (ForgetPasswordActivity.instance != null && !ForgetPasswordActivity.instance.isDestroyed()) {
                    ForgetPasswordActivity.instance.resetPasswordButton.setEnabled(true);
                }
                throw th;
            }
        }
    }
}
