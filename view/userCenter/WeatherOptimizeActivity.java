package com.nfcx.luxinvpower.view.userCenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Consumer;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class WeatherOptimizeActivity extends Activity {
    private JSONObject weatherOptimizeFromRemote;
    private WebView webView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_weather_optimize);
        setupUI();
    }

    private void setupUI() {
        setTitleText(getString(R.string.weather_optimize));
        setupWebView();
        setupBackButton();
        loadWebContent();
    }

    private void setTitleText(String str) {
        ((TextView) findViewById(R.id.titleTextView)).setText(str);
    }

    private void setupWebView() {
        WebView webView = (WebView) findViewById(R.id.webView);
        this.webView = webView;
        webView.setWebViewClient(new WebViewClient() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                if ("file:///android_asset/module/weatherOptimize/index.html".equals(str)) {
                    WeatherOptimizeActivity.this.updateWebContent();
                }
            }
        });
        configureWebSettings(this.webView.getSettings());
        this.webView.addJavascriptInterface(new WebAppInterfaceWeatherOptimize(), "WebAppInterfaceWeatherOptimize");
    }

    private void configureWebSettings(WebSettings webSettings) {
        webSettings.setCacheMode(2);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);
    }

    private void setupBackButton() {
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeatherOptimizeActivity.this.m468xd38a57fc(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupBackButton$0$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m468xd38a57fc(View view) {
        handleBackPress();
    }

    private void handleBackPress() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            finish();
        }
    }

    private void loadWebContent() {
        this.webView.loadUrl("file:///android_asset/module/weatherOptimize/index.html");
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        fetchWeatherData(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                WeatherOptimizeActivity.this.updateWebContent();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchWeatherData(final Runnable runnable) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WeatherOptimizeActivity.this.m463xccae4a71(runnable);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$fetchWeatherData$3$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m463xccae4a71(final Runnable runnable) {
        HashMap hashMap = new HashMap();
        hashMap.put("page", "1");
        hashMap.put("rows", "6");
        try {
            this.weatherOptimizeFromRemote = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/weatherSet/inverter/list", hashMap);
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherOptimizeActivity.lambda$fetchWeatherData$1(runnable);
                }
            });
        } catch (Exception e) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherOptimizeActivity.this.m462x386fdad2();
                }
            });
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$fetchWeatherData$1(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$fetchWeatherData$2$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m462x386fdad2() {
        Tool.alert(this, getString(R.string.error_fetching_data));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWebContent() {
        JSONObject jSONObject = this.weatherOptimizeFromRemote;
        if (jSONObject != null) {
            final String jSONObject2 = jSONObject.toString();
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherOptimizeActivity.this.m469x88d8f346(jSONObject2);
                }
            });
        } else {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherOptimizeActivity.this.m470x1d1762e5();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateWebContent$4$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m469x88d8f346(String str) {
        this.webView.loadUrl("javascript:getList(" + str + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateWebContent$5$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m470x1d1762e5() {
        this.webView.loadUrl("javascript:getList(null)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class WebAppInterfaceWeatherOptimize {
        private WebAppInterfaceWeatherOptimize() {
        }

        @JavascriptInterface
        public String getList() throws JSONException {
            if (WeatherOptimizeActivity.this.weatherOptimizeFromRemote != null) {
                return WeatherOptimizeActivity.this.weatherOptimizeFromRemote.toString();
            }
            return null;
        }

        @JavascriptInterface
        public void getTableList(String str, String str2) throws JSONException {
            WeatherOptimizeActivity.this.getSetDetailList(str, str2, new ValueCallback() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize$$ExternalSyntheticLambda6
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    WeatherOptimizeActivity.WebAppInterfaceWeatherOptimize.this.m473x781e0329((JSONObject) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getTableList$0$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize, reason: not valid java name */
        public /* synthetic */ void m471xacc74a27(JSONObject jSONObject) {
            WeatherOptimizeActivity.this.webView.evaluateJavascript("handleFormResult('" + jSONObject + "')", null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getTableList$2$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize, reason: not valid java name */
        public /* synthetic */ void m473x781e0329(final JSONObject jSONObject) {
            if (jSONObject != null) {
                WeatherOptimizeActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        WeatherOptimizeActivity.WebAppInterfaceWeatherOptimize.this.m471xacc74a27(jSONObject);
                    }
                });
            } else {
                WeatherOptimizeActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        WeatherOptimizeActivity.WebAppInterfaceWeatherOptimize.this.m472x9272a6a8();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getTableList$1$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize, reason: not valid java name */
        public /* synthetic */ void m472x9272a6a8() {
            WeatherOptimizeActivity.this.webView.evaluateJavascript("handleFormResult(null)", null);
        }

        @JavascriptInterface
        public void saveForm(String str) throws JSONException {
            WeatherOptimizeActivity.this.saveWeatherSet(WeatherOptimizeActivity.jsonStringToMap(str), new Consumer() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize$$ExternalSyntheticLambda5
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    WeatherOptimizeActivity.WebAppInterfaceWeatherOptimize.this.m474x55f0c676((Boolean) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$saveForm$4$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize, reason: not valid java name */
        public /* synthetic */ void m474x55f0c676(Boolean bool) {
            if (bool.booleanValue()) {
                final WeatherOptimizeActivity weatherOptimizeActivity = WeatherOptimizeActivity.this;
                weatherOptimizeActivity.fetchWeatherData(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        WeatherOptimizeActivity.this.updateWebContent();
                    }
                });
            }
        }

        @JavascriptInterface
        public void setEnableStatus(String str, String str2) {
            WeatherOptimizeActivity.this.enableDevice(str, str2, new Consumer() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize$$ExternalSyntheticLambda2
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    WeatherOptimizeActivity.WebAppInterfaceWeatherOptimize.this.m475xd71b3c54((Boolean) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$setEnableStatus$6$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize, reason: not valid java name */
        public /* synthetic */ void m475xd71b3c54(Boolean bool) {
            if (bool.booleanValue()) {
                final WeatherOptimizeActivity weatherOptimizeActivity = WeatherOptimizeActivity.this;
                weatherOptimizeActivity.fetchWeatherData(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$WebAppInterfaceWeatherOptimize$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        WeatherOptimizeActivity.this.updateWebContent();
                    }
                });
            }
        }
    }

    public static Map<String, String> jsonStringToMap(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSetDetailList(final String str, final String str2, final ValueCallback<JSONObject> valueCallback) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                WeatherOptimizeActivity.this.m464x974d79d6(str, str2, valueCallback);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getSetDetailList$7$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m464x974d79d6(String str, String str2, final ValueCallback valueCallback) {
        final JSONObject jSONObject;
        HashMap hashMap = new HashMap();
        hashMap.put("serialNum", str);
        hashMap.put("dateText", str2);
        try {
            jSONObject = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/weatherSet/setDetail/list", hashMap);
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject = null;
        }
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                WeatherOptimizeActivity.lambda$getSetDetailList$6(valueCallback, jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getSetDetailList$6(ValueCallback valueCallback, JSONObject jSONObject) {
        if (valueCallback != null) {
            valueCallback.onReceiveValue(jSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveWeatherSet(final Map<String, String> map, final Consumer<Boolean> consumer) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                WeatherOptimizeActivity.this.m465x1d98bfe6(map, consumer);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveWeatherSet$10$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m465x1d98bfe6(Map map, final Consumer consumer) {
        try {
            final boolean z = HttpTool.postJson("http://as.luxpowertek.com/WManage/web/maintain/weatherSet/addDevice", map).getBoolean("success");
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherOptimizeActivity.this.m466x4f417de5(z, consumer);
                }
            });
        } catch (Exception e) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherOptimizeActivity.this.m467xe37fed84(consumer);
                }
            });
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveWeatherSet$8$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m466x4f417de5(boolean z, Consumer consumer) {
        if (z) {
            Tool.alert(this, getString(R.string.saved));
        } else {
            Tool.alert(this, getString(R.string.save_failed));
        }
        consumer.accept(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveWeatherSet$9$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m467xe37fed84(Consumer consumer) {
        Tool.alert(this, getString(R.string.error_processing_request));
        consumer.accept(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableDevice(final String str, final String str2, final Consumer<Boolean> consumer) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                WeatherOptimizeActivity.this.m461x1a7f9e91(str, str2, consumer);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$enableDevice$13$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m461x1a7f9e91(String str, String str2, final Consumer consumer) {
        JSONObject postJson;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("serialNum", str);
            if (str2.equals("enable")) {
                postJson = HttpTool.postJson("http://as.luxpowertek.com/WManage/web/maintain/weatherSet/enableDevice", hashMap);
            } else {
                postJson = HttpTool.postJson("http://as.luxpowertek.com/WManage/web/maintain/weatherSet/disableDevice", hashMap);
            }
            final boolean z = postJson.getBoolean("success");
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherOptimizeActivity.this.m459xf202bf53(z, consumer);
                }
            });
        } catch (Exception e) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.WeatherOptimizeActivity$$ExternalSyntheticLambda13
                @Override // java.lang.Runnable
                public final void run() {
                    WeatherOptimizeActivity.this.m460x86412ef2(consumer);
                }
            });
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$enableDevice$11$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m459xf202bf53(boolean z, Consumer consumer) {
        if (z) {
            Tool.alert(this, getString(R.string.saved));
        } else {
            Tool.alert(this, getString(R.string.save_failed));
        }
        consumer.accept(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$enableDevice$12$com-nfcx-luxinvpower-view-userCenter-WeatherOptimizeActivity, reason: not valid java name */
    public /* synthetic */ void m460x86412ef2(Consumer consumer) {
        Tool.alert(this, getString(R.string.error_processing_request));
        consumer.accept(false);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            startActivity(new Intent(this, (Class<?>) NewUserCenterActivity.class));
            finish();
        }
    }
}
