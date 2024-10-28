package com.nfcx.luxinvpower.view.userCenter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alibaba.fastjson2.JSONObject;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.user.CHART_COLOR;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.plant.PlantListActivity;
import java.util.HashMap;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class NormalSettingActivity extends Activity {
    public static final String USER_INFO = "userInfo";
    public static NormalSettingActivity instance;
    private JSONObject jsonFromRemote;
    private WebView webView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_new_page_setting);
        instance = this;
        setupListeners();
    }

    private void setupListeners() {
        findViewById(R.id.backImageViewLayout).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity.instance != null) {
                    MainActivity.instance.finish();
                }
                NormalSettingActivity.instance.finish();
            }
        });
        WebView webView = (WebView) findViewById(R.id.chart_Color_setting);
        this.webView = webView;
        webView.setWebChromeClient(new WebChromeClient());
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity.2
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                super.onPageStarted(webView2, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                super.onPageFinished(webView2, str);
                if (str.equals("file:///android_asset/module/normalSetting/index.html")) {
                    NormalSettingActivity.this.updateWebContent();
                    webView2.loadUrl("javascript:initUseNewStatus()");
                }
            }
        });
        this.webView.addJavascriptInterface(new WebAppInterfaceNormalSetting(), "WebAppInterfaceNormalSetting");
        configureWebSettings(this.webView.getSettings());
        this.webView.loadUrl("file:///android_asset/module/normalSetting/index.html");
    }

    private void configureWebSettings(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(2);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class WebAppInterfaceNormalSetting {
        private WebAppInterfaceNormalSetting() {
        }

        @JavascriptInterface
        public String initColorData() throws JSONException {
            if (NormalSettingActivity.this.jsonFromRemote != null) {
                return NormalSettingActivity.this.jsonFromRemote.toString();
            }
            return null;
        }

        @JavascriptInterface
        public void saveForm(String str) {
            NormalSettingActivity.this.saveData(str);
        }

        @JavascriptInterface
        public boolean getUseNewSettingPage() {
            return NormalSettingActivity.this.adaptUseSetting();
        }

        @JavascriptInterface
        public void changeUseNewStatus(boolean z) {
            NormalSettingActivity.this.saveNewPageSetting(Boolean.valueOf(z));
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        createColorJson();
    }

    private void createColorJson() {
        UserData userData = GlobalInfo.getInstance().getUserData();
        if (this.jsonFromRemote == null) {
            this.jsonFromRemote = new JSONObject();
        }
        if (!Tool.isEmpty(userData.getChartColor().toString())) {
            this.jsonFromRemote.put("chartColor", userData.getChartColor().getName());
        } else {
            this.jsonFromRemote.put("chartColor", CHART_COLOR.STANDARD.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWebContent() {
        JSONObject jSONObject = this.jsonFromRemote;
        if (jSONObject != null) {
            final String jSONObject2 = jSONObject.toString();
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    NormalSettingActivity.this.m433x96e24bc2(jSONObject2);
                }
            });
        } else {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    NormalSettingActivity.this.m434x9ce61721();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateWebContent$0$com-nfcx-luxinvpower-view-userCenter-NormalSettingActivity, reason: not valid java name */
    public /* synthetic */ void m433x96e24bc2(String str) {
        this.webView.loadUrl("javascript:getForm(" + str + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateWebContent$1$com-nfcx-luxinvpower-view-userCenter-NormalSettingActivity, reason: not valid java name */
    public /* synthetic */ void m434x9ce61721() {
        this.webView.loadUrl("javascript:getForm(null)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveData(final String str) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                NormalSettingActivity.this.m430xde72c45(str);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveData$4$com-nfcx-luxinvpower-view-userCenter-NormalSettingActivity, reason: not valid java name */
    public /* synthetic */ void m430xde72c45(final String str) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("chartColor", str);
            org.json.JSONObject postJson = HttpTool.postJson("http://as.luxpowertek.com/WManage/api/userChartRecord/saveOrUpdateChartColor", hashMap);
            System.out.println("saveOrUpdateChartColor == " + postJson);
            final boolean z = postJson.getBoolean("success");
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    NormalSettingActivity.this.m428x1df9587(z, str);
                }
            });
        } catch (Exception e) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    NormalSettingActivity.this.m429x7e360e6();
                }
            });
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveData$2$com-nfcx-luxinvpower-view-userCenter-NormalSettingActivity, reason: not valid java name */
    public /* synthetic */ void m428x1df9587(boolean z, String str) {
        if (z) {
            GlobalInfo.getInstance().getUserData().setChartColor(CHART_COLOR.valueOf(str));
            Tool.alert(this, getString(R.string.saved), new DialogInterface.OnDismissListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity.3
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    NormalSettingActivity.this.startActivity(new Intent(NormalSettingActivity.instance, (Class<?>) (ROLE.VIEWER.equals(GlobalInfo.getInstance().getUserData().getRole()) ? PlantListActivity.class : PlantOverviewActivity.class)));
                    if (MainActivity.instance != null) {
                        MainActivity.instance.finish();
                    }
                }
            });
        } else {
            Tool.alert(this, getString(R.string.save_failed));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveData$3$com-nfcx-luxinvpower-view-userCenter-NormalSettingActivity, reason: not valid java name */
    public /* synthetic */ void m429x7e360e6() {
        Tool.alert(this, getString(R.string.error_processing_request));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveNewPageSetting(final Boolean bool) {
        final UserData userData = GlobalInfo.getInstance().getUserData();
        final SharedPreferences sharedPreferences = getSharedPreferences("userInfo", 0);
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                NormalSettingActivity.this.m432x3d824b8c(sharedPreferences, bool, userData);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveNewPageSetting$6$com-nfcx-luxinvpower-view-userCenter-NormalSettingActivity, reason: not valid java name */
    public /* synthetic */ void m432x3d824b8c(SharedPreferences sharedPreferences, Boolean bool, final UserData userData) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(Constants.useNewSettingPage, bool.booleanValue());
        edit.apply();
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NormalSettingActivity.this.m431x377e802d(userData);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveNewPageSetting$5$com-nfcx-luxinvpower-view-userCenter-NormalSettingActivity, reason: not valid java name */
    public /* synthetic */ void m431x377e802d(final UserData userData) {
        Tool.alert(instance, getString(R.string.wifi_connect_tcp_set_success), new DialogInterface.OnDismissListener() { // from class: com.nfcx.luxinvpower.view.userCenter.NormalSettingActivity.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (MainActivity.instance != null) {
                    MainActivity.instance.finish();
                }
                Intent intent = new Intent(NormalSettingActivity.instance, (Class<?>) (ROLE.VIEWER.equals(userData.getRole()) ? PlantListActivity.class : PlantOverviewActivity.class));
                intent.setFlags(335544320);
                NormalSettingActivity.this.startActivity(intent);
                NormalSettingActivity.instance.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean adaptUseSetting() {
        return getSharedPreferences("userInfo", 0).getBoolean(Constants.useNewSettingPage, false);
    }
}
