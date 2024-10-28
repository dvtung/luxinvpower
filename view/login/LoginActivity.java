package com.nfcx.luxinvpower.view.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.net.HttpHeaders;
import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.tool.DisplayUtil;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.LocalConnectTool;
import com.nfcx.luxinvpower.tool.LogUtils;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Custom;
import com.nfcx.luxinvpower.version.Version;
import com.nfcx.luxinvpower.view.forgetPassword.ForgetPasswordActivity;
import com.nfcx.luxinvpower.view.login.LoginActivity;
import com.nfcx.luxinvpower.view.register.RegisterActivity;
import com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity;
import com.nfcx.luxinvpower.view.warranty.WarrantyActivity;
import com.nfcx.luxinvpower.view.wifi.WifiConnectActivity;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LoginActivity extends Activity {
    private static final String AUTO_LOGIN_CHECKED = "autoLoginChecked";
    public static final String CLUSTER_ID = "clusterId";
    public static final String IGNORE_VERSION = "ignoredVersion";
    private static final String PASSWORD = "password";
    private static final String REMEMBER_USERNAME_CHECKED = "rememberUsernameChecked";
    private static final int REQUEST_CODE = 2;
    private static final String USERNAME = "username";
    public static final String USER_INFO = "userInfo";
    public static final String isNotification = "isNotification";
    public static boolean isUserLoggedIn = false;
    public static boolean obtainDeviceToken = false;
    public static String passwordForLogin;
    public static String usernameForLogin;
    private EditText accountEditText;
    private String appVersion;
    private String fcmToken;
    private boolean fromLogout;
    private long loginSuccessTime;
    private String newVersion;
    private long obtainTokenTime;
    private String packageName;
    private EditText passwordEditText;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private boolean triedUpdate = false;
    JSONObject userInfoMap = new JSONObject();

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if ((getIntent().getFlags() & 4194304) != 0) {
            finish();
            return;
        }
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(getResources().getColor(R.color.theme_green));
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", 0);
        String country = Locale.getDefault().getCountry();
        System.out.println("LuxPower - Country 1: " + country);
        getApplicationContext();
        String networkCountryIso = ((TelephonyManager) getSystemService("phone")).getNetworkCountryIso();
        System.out.println("LuxPower - Country 2: " + networkCountryIso);
        if (Constants.INDIA_COUNTRY_CODE.equalsIgnoreCase(country) || Constants.INDIA_COUNTRY_CODE.equalsIgnoreCase(networkCountryIso)) {
            GlobalInfo.getInstance().setCurrentClusterGroup(Constants.CLUSTER_GROUP_INDIA);
        } else {
            GlobalInfo.getInstance().setCurrentClusterGroup(Constants.CLUSTER_GROUP_FIRST);
        }
        GlobalInfo.getInstance().getUserData().setClusterId(sharedPreferences.getLong(CLUSTER_ID, GlobalInfo.getDefaultClusterId()));
        GlobalInfo.getInstance().initializeGlobalInfo(this, getResources().getConfiguration().locale);
        if (ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.POST_NOTIFICATIONS"}, 1);
        }
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        System.out.println("LuxPowerdisplayWidth = " + width + ", displayHeight = " + height);
        float f = width;
        float f2 = height;
        System.out.println("LuxPowerdpWidth = " + DisplayUtil.px2dip(this, f) + ", dpHeight = " + DisplayUtil.px2dip(this, f2));
        System.out.println("LuxPowerspWidth = " + DisplayUtil.px2sp(this, f) + ", spHeight = " + DisplayUtil.px2sp(this, f2));
        getAllFirmwares(sharedPreferences);
        this.accountEditText = (EditText) findViewById(R.id.login_accountEditText);
        EditText editText = (EditText) findViewById(R.id.login_passwordEditText);
        this.passwordEditText = editText;
        editText.setTypeface(Typeface.DEFAULT);
        this.progressBar = (ProgressBar) findViewById(R.id.login_progressBar);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
            TextView textView = (TextView) findViewById(R.id.login_versionTextView);
            textView.setText(getString(R.string.phrase_version) + " " + packageInfo.versionName + " - " + getString(R.string.phrase_privacy_policy));
            this.appVersion = packageInfo.versionName;
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LoginActivity.this.m345lambda$onCreate$0$comnfcxluxinvpowerviewloginLoginActivity(view);
                }
            });
            this.packageName = packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        findNewVersion();
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                LoginActivity.this.m346lambda$onCreate$1$comnfcxluxinvpowerviewloginLoginActivity(task);
            }
        });
        final CheckBox checkBox = (CheckBox) findViewById(R.id.login_rememberAccountCheckBox);
        boolean z = sharedPreferences.getBoolean(REMEMBER_USERNAME_CHECKED, true);
        checkBox.setChecked(z);
        if (z) {
            this.accountEditText.setText(sharedPreferences.getString(USERNAME, ""));
        }
        handleIntent(getIntent());
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.login_autoLoginCheckBox);
        boolean z2 = sharedPreferences.getBoolean(AUTO_LOGIN_CHECKED, false);
        checkBox2.setChecked(z2);
        if (z2) {
            this.passwordEditText.setText(sharedPreferences.getString(PASSWORD, ""));
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
                if (z3) {
                    return;
                }
                checkBox2.setChecked(false);
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
                if (z3) {
                    checkBox.setChecked(true);
                }
            }
        });
        Button button = (Button) findViewById(R.id.login_loginButton);
        button.setOnClickListener(new AnonymousClass3(sharedPreferences, checkBox, checkBox2, button));
        boolean z3 = sharedPreferences.getBoolean(isNotification, true);
        if (z2 && !this.fromLogout && z3) {
            button.performClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-nfcx-luxinvpower-view-login-LoginActivity, reason: not valid java name */
    public /* synthetic */ void m345lambda$onCreate$0$comnfcxluxinvpowerviewloginLoginActivity(View view) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(GlobalInfo.getInstance().getCustomDoname() + Custom.PRIVACY_POLICY_URL_SUFFIX));
        intent.setAction("android.intent.action.VIEW");
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-nfcx-luxinvpower-view-login-LoginActivity, reason: not valid java name */
    public /* synthetic */ void m346lambda$onCreate$1$comnfcxluxinvpowerviewloginLoginActivity(Task task) {
        if (!task.isSuccessful()) {
            Log.w(Version.TAG, "Fetching FCM registration token failed", task.getException());
            return;
        }
        String str = (String) task.getResult();
        this.obtainTokenTime = System.currentTimeMillis();
        if (isUserLoggedIn) {
            sendTokenToServer();
        } else {
            this.fcmToken = str;
        }
    }

    /* renamed from: com.nfcx.luxinvpower.view.login.LoginActivity$3, reason: invalid class name */
    /* loaded from: classes2.dex */
    class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ CheckBox val$autoLoginCheckBox;
        final /* synthetic */ Button val$loginButton;
        final /* synthetic */ CheckBox val$rememberUsernameCheckBox;
        final /* synthetic */ SharedPreferences val$sharedPreferences;

        AnonymousClass3(SharedPreferences sharedPreferences, CheckBox checkBox, CheckBox checkBox2, Button button) {
            this.val$sharedPreferences = sharedPreferences;
            this.val$rememberUsernameCheckBox = checkBox;
            this.val$autoLoginCheckBox = checkBox2;
            this.val$loginButton = button;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String trim = LoginActivity.this.accountEditText.getText().toString().trim();
            String trim2 = LoginActivity.this.passwordEditText.getText().toString().trim();
            SharedPreferences.Editor edit = this.val$sharedPreferences.edit();
            edit.putString(LoginActivity.USERNAME, this.val$rememberUsernameCheckBox.isChecked() ? trim : "");
            edit.putString(LoginActivity.PASSWORD, this.val$autoLoginCheckBox.isChecked() ? trim2 : "");
            edit.putBoolean(LoginActivity.REMEMBER_USERNAME_CHECKED, this.val$rememberUsernameCheckBox.isChecked());
            edit.putBoolean(LoginActivity.AUTO_LOGIN_CHECKED, this.val$autoLoginCheckBox.isChecked());
            edit.commit();
            if (Tool.isEmpty(trim)) {
                Tool.alert(LoginActivity.this, R.string.login_toast_account_empty);
            } else {
                if (!Tool.isEmpty(trim2)) {
                    LoginActivity.this.progressBar.setVisibility(0);
                    this.val$loginButton.setEnabled(false);
                    new Thread(new AnonymousClass1(trim, trim2)).start();
                    return;
                }
                Tool.alert(LoginActivity.this, R.string.login_toast_password_empty);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.nfcx.luxinvpower.view.login.LoginActivity$3$1, reason: invalid class name */
        /* loaded from: classes2.dex */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ String val$accountValue;
            final /* synthetic */ String val$passwordValue;

            AnonymousClass1(String str, String str2) {
                this.val$accountValue = str;
                this.val$passwordValue = str2;
            }

            @Override // java.lang.Runnable
            public void run() {
                final boolean z;
                GlobalInfo.getInstance().getUserData().clear();
                LogUtils.writeLog("Do login for user: " + this.val$accountValue);
                HashMap hashMap = new HashMap();
                hashMap.put("account", this.val$accountValue);
                hashMap.put(LoginActivity.PASSWORD, this.val$passwordValue);
                hashMap.put("language", GlobalInfo.getInstance().getLanguage());
                hashMap.put("userPlatForm", Custom.APP_USER_PLATFORM);
                final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/login", hashMap);
                LogUtils.writeLog("resultFromRemote for login " + this.val$accountValue + ": " + (postJson != null ? postJson.toString() : "null..."));
                boolean z2 = true;
                if (postJson != null) {
                    z = false;
                    try {
                        try {
                            if (postJson.getBoolean("success")) {
                                LoginActivity.usernameForLogin = this.val$accountValue;
                                LoginActivity.passwordForLogin = this.val$passwordValue;
                                if (postJson.has("needReloginCluster") && postJson.getBoolean("needReloginCluster")) {
                                    String string = postJson.getString("reloginClusterUrl");
                                    if (!Tool.isEmpty(string)) {
                                        long j = postJson.getLong("reloginClusterId");
                                        SharedPreferences.Editor edit = AnonymousClass3.this.val$sharedPreferences.edit();
                                        edit.putLong(LoginActivity.CLUSTER_ID, j);
                                        edit.commit();
                                        postJson = HttpTool.postJson(string, hashMap);
                                    } else {
                                        Tool.alertNotInUiThread(LoginActivity.this, "Cluster exception");
                                        z2 = false;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            Tool.alertNotInUiThread(LoginActivity.this, R.string.phrase_toast_response_error);
                            e.printStackTrace();
                            LoginActivity loginActivity = LoginActivity.this;
                            final Button button = AnonymousClass3.this.val$loginButton;
                            loginActivity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$3$1$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    LoginActivity.AnonymousClass3.AnonymousClass1.this.m348lambda$run$0$comnfcxluxinvpowerviewloginLoginActivity$3$1(button);
                                }
                            });
                        }
                    } catch (Throwable th) {
                        LoginActivity loginActivity2 = LoginActivity.this;
                        final Button button2 = AnonymousClass3.this.val$loginButton;
                        loginActivity2.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$3$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                LoginActivity.AnonymousClass3.AnonymousClass1.this.m348lambda$run$0$comnfcxluxinvpowerviewloginLoginActivity$3$1(button2);
                            }
                        });
                        throw th;
                    }
                }
                LoginActivity loginActivity3 = LoginActivity.this;
                final Button button3 = AnonymousClass3.this.val$loginButton;
                loginActivity3.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$3$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        LoginActivity.AnonymousClass3.AnonymousClass1.this.m348lambda$run$0$comnfcxluxinvpowerviewloginLoginActivity$3$1(button3);
                    }
                });
                z = z2;
                LoginActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity.3.1.1
                    /* JADX WARN: Removed duplicated region for block: B:37:0x0240 A[Catch: Exception -> 0x033f, TryCatch #0 {Exception -> 0x033f, blocks: (B:12:0x00b2, B:14:0x00c3, B:15:0x00d0, B:17:0x0129, B:18:0x0132, B:31:0x01d0, B:32:0x01d3, B:35:0x0238, B:37:0x0240, B:38:0x0249, B:41:0x0253, B:43:0x0265, B:44:0x026f, B:46:0x0278, B:47:0x027c, B:49:0x0282, B:50:0x02b2, B:52:0x02b8, B:53:0x02f2, B:55:0x02f8, B:57:0x0309, B:59:0x0318, B:60:0x031d, B:62:0x0323, B:81:0x0235, B:34:0x0226), top: B:11:0x00b2, outer: #3, inners: #4 }] */
                    /* JADX WARN: Removed duplicated region for block: B:41:0x0253 A[Catch: Exception -> 0x033f, TRY_ENTER, TryCatch #0 {Exception -> 0x033f, blocks: (B:12:0x00b2, B:14:0x00c3, B:15:0x00d0, B:17:0x0129, B:18:0x0132, B:31:0x01d0, B:32:0x01d3, B:35:0x0238, B:37:0x0240, B:38:0x0249, B:41:0x0253, B:43:0x0265, B:44:0x026f, B:46:0x0278, B:47:0x027c, B:49:0x0282, B:50:0x02b2, B:52:0x02b8, B:53:0x02f2, B:55:0x02f8, B:57:0x0309, B:59:0x0318, B:60:0x031d, B:62:0x0323, B:81:0x0235, B:34:0x0226), top: B:11:0x00b2, outer: #3, inners: #4 }] */
                    /* JADX WARN: Removed duplicated region for block: B:52:0x02b8 A[Catch: Exception -> 0x033f, TryCatch #0 {Exception -> 0x033f, blocks: (B:12:0x00b2, B:14:0x00c3, B:15:0x00d0, B:17:0x0129, B:18:0x0132, B:31:0x01d0, B:32:0x01d3, B:35:0x0238, B:37:0x0240, B:38:0x0249, B:41:0x0253, B:43:0x0265, B:44:0x026f, B:46:0x0278, B:47:0x027c, B:49:0x0282, B:50:0x02b2, B:52:0x02b8, B:53:0x02f2, B:55:0x02f8, B:57:0x0309, B:59:0x0318, B:60:0x031d, B:62:0x0323, B:81:0x0235, B:34:0x0226), top: B:11:0x00b2, outer: #3, inners: #4 }] */
                    /* JADX WARN: Removed duplicated region for block: B:70:0x0356 A[Catch: Exception -> 0x03da, TryCatch #3 {Exception -> 0x03da, blocks: (B:6:0x001d, B:8:0x0021, B:10:0x0029, B:68:0x0343, B:70:0x0356, B:72:0x0383, B:75:0x039c, B:96:0x0340, B:97:0x03b2, B:99:0x03b6, B:101:0x03c3, B:103:0x03cd, B:12:0x00b2, B:14:0x00c3, B:15:0x00d0, B:17:0x0129, B:18:0x0132, B:31:0x01d0, B:32:0x01d3, B:35:0x0238, B:37:0x0240, B:38:0x0249, B:41:0x0253, B:43:0x0265, B:44:0x026f, B:46:0x0278, B:47:0x027c, B:49:0x0282, B:50:0x02b2, B:52:0x02b8, B:53:0x02f2, B:55:0x02f8, B:57:0x0309, B:59:0x0318, B:60:0x031d, B:62:0x0323, B:81:0x0235, B:34:0x0226), top: B:5:0x001d, inners: #0 }] */
                    /* JADX WARN: Removed duplicated region for block: B:72:0x0383 A[Catch: Exception -> 0x03da, TryCatch #3 {Exception -> 0x03da, blocks: (B:6:0x001d, B:8:0x0021, B:10:0x0029, B:68:0x0343, B:70:0x0356, B:72:0x0383, B:75:0x039c, B:96:0x0340, B:97:0x03b2, B:99:0x03b6, B:101:0x03c3, B:103:0x03cd, B:12:0x00b2, B:14:0x00c3, B:15:0x00d0, B:17:0x0129, B:18:0x0132, B:31:0x01d0, B:32:0x01d3, B:35:0x0238, B:37:0x0240, B:38:0x0249, B:41:0x0253, B:43:0x0265, B:44:0x026f, B:46:0x0278, B:47:0x027c, B:49:0x0282, B:50:0x02b2, B:52:0x02b8, B:53:0x02f2, B:55:0x02f8, B:57:0x0309, B:59:0x0318, B:60:0x031d, B:62:0x0323, B:81:0x0235, B:34:0x0226), top: B:5:0x001d, inners: #0 }] */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void run() {
                        /*
                            Method dump skipped, instructions count: 1003
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.login.LoginActivity.AnonymousClass3.AnonymousClass1.RunnableC00351.run():void");
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: lambda$run$0$com-nfcx-luxinvpower-view-login-LoginActivity$3$1, reason: not valid java name */
            public /* synthetic */ void m348lambda$run$0$comnfcxluxinvpowerviewloginLoginActivity$3$1(Button button) {
                LoginActivity.this.progressBar.setVisibility(4);
                button.setEnabled(true);
            }
        }
    }

    private void getAddressFromLocation(double d, double d2) {
        try {
            List<Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(d, d2, 1);
            if (fromLocation.isEmpty()) {
                return;
            }
            Address address = fromLocation.get(0);
            String str = "Country: " + address.getCountryName() + " (" + address.getCountryCode() + ")\nAdmin Area: " + address.getAdminArea() + "\nSub Admin Area: " + address.getSubAdminArea() + "\nLocality: " + address.getLocality() + "\nSub Locality: " + address.getSubLocality() + "\nThoroughfare: " + address.getThoroughfare() + "\nSub Thoroughfare: " + address.getSubThoroughfare();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(HttpHeaders.LOCATION);
            builder.setMessage(str).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Unable to get street address", 0).show();
        }
    }

    private void getAllFirmwares(final SharedPreferences sharedPreferences) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                LoginActivity.lambda$getAllFirmwares$2(sharedPreferences);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getAllFirmwares$2(SharedPreferences sharedPreferences) {
        try {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("firmwares", HttpTool.postJson("http://res.solarcloudsystem.com:8083/resource/getAllFirmware", null).toString());
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTokenToServer() {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                LoginActivity.this.m347x2e612e27();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendTokenToServer$3$com-nfcx-luxinvpower-view-login-LoginActivity, reason: not valid java name */
    public /* synthetic */ void m347x2e612e27() {
        try {
            if (this.fcmToken != null) {
                long j = this.loginSuccessTime;
                if (j == -1 || j - this.obtainTokenTime < 60000) {
                    System.out.println("fcmToken == " + this.fcmToken);
                    this.userInfoMap.put("fcmToken", this.fcmToken);
                }
            }
            if (Tool.isEmpty(this.userInfoMap.toString())) {
                return;
            }
            System.out.println("userInfoMap.toString() == " + this.userInfoMap.toString());
            HttpTool.multiPartPostJson("http://monitor.solarcloudsystem.com:8084/device/updateToken", this.userInfoMap.toString());
            this.userInfoMap = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findNewVersion() {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                LoginActivity.this.m344xd6fd8e65();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$findNewVersion$5$com-nfcx-luxinvpower-view-login-LoginActivity, reason: not valid java name */
    public /* synthetic */ void m344xd6fd8e65() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("envPlatform", "ANDROID");
            hashMap.put("versionCode", this.appVersion);
            hashMap.put("appType", "MONITOR");
            hashMap.put("userPlatform", Custom.APP_USER_PLATFORM);
            JSONObject postJson = HttpTool.postJson("http://res.solarcloudsystem.com:8083/resource/appVersion/findNew", hashMap);
            if (postJson != null) {
                this.newVersion = postJson.getString("msg");
                if (postJson.getBoolean(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)) {
                    runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            LoginActivity.this.m343x61836824();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$findNewVersion$4$com-nfcx-luxinvpower-view-login-LoginActivity, reason: not valid java name */
    public /* synthetic */ void m343x61836824() {
        checkNewVersion(this.newVersion);
    }

    private void checkNewVersion(final String str) {
        final SharedPreferences preferences = getPreferences(0);
        String string = preferences.getString(IGNORE_VERSION, "");
        if (Tool.isEmpty(string) || !string.equals(str)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.new_app_version_released));
            builder.setMessage(getString(R.string.new_app_version_released_message, new Object[]{str, getString(R.string.app_name)}));
            builder.setPositiveButton(getString(R.string.update), new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity$$ExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    LoginActivity.this.m342x4051db(dialogInterface, i);
                }
            });
            builder.setNegativeButton(getString(R.string.skip), new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString(LoginActivity.IGNORE_VERSION, str);
                    edit.apply();
                }
            });
            builder.setNeutralButton(getString(R.string.later), new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.login.LoginActivity.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog create = builder.create();
            create.show();
            create.getButton(-1).setTextColor(ContextCompat.getColor(this, R.color.main_green));
            create.getButton(-2).setTextColor(-7829368);
            create.getButton(-3).setTextColor(-7829368);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$checkNewVersion$6$com-nfcx-luxinvpower-view-login-LoginActivity, reason: not valid java name */
    public /* synthetic */ void m342x4051db(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + this.packageName));
        intent.setAction("android.intent.action.VIEW");
        startActivity(intent);
    }

    public void clickForgetPasswordButton(View view) {
        startActivity(new Intent(this, (Class<?>) ForgetPasswordActivity.class));
    }

    public void clickRegisterButton(View view) {
        if (GlobalInfo.getInstance().isInited()) {
            startActivity(new Intent(this, (Class<?>) RegisterActivity.class));
        } else {
            Tool.alert(this, R.string.phrase_please_wait_seconds);
            GlobalInfo.getInstance().isIniting();
        }
    }

    public void clickWifiModuleConnectButton(View view) {
        Intent intent = new Intent(this, (Class<?>) WifiConnectActivity.class);
        intent.putExtra("newVersionCode", this.newVersion);
        intent.putExtra("fromLogin", true);
        startActivity(intent);
    }

    public void clickWarrantyButton(View view) {
        startActivity(new Intent(this, (Class<?>) WarrantyActivity.class));
    }

    public void clickLocalConnectButton(View view) {
        LocalConnectTool.go2LocalActivity(this);
    }

    public void clickDownloadFirmwareButton(View view) {
        startActivity(new Intent(this, (Class<?>) DownloadFirmwareActivity.class));
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 1 || iArr.length <= 0) {
            return;
        }
        int i2 = iArr[0];
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.triedUpdate) {
            return;
        }
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        SharedPreferences preferences = getPreferences(0);
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("fromLogout", false);
            this.fromLogout = booleanExtra;
            if (booleanExtra) {
                GlobalInfo.getInstance().getUserData().setClusterId(0L);
                usernameForLogin = null;
                passwordForLogin = null;
            }
            if (intent.hasExtra("account")) {
                String stringExtra = intent.getStringExtra("account");
                String stringExtra2 = intent.getStringExtra(PASSWORD);
                this.accountEditText.setText(stringExtra);
                this.passwordEditText.setText(stringExtra2);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean(AUTO_LOGIN_CHECKED, false);
                edit.commit();
            }
            if (intent.getBooleanExtra("updateNewVersion", false)) {
                this.triedUpdate = true;
                ProgressDialog progressDialog = new ProgressDialog(this);
                this.progressDialog = progressDialog;
                progressDialog.setTitle(getString(R.string.login_download_dialog_title));
                this.progressDialog.setMessage(getString(R.string.login_download_dialog_text));
                this.progressDialog.setProgressStyle(0);
                this.progressDialog.show();
            }
        }
    }
}
