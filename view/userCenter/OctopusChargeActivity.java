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
import com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class OctopusChargeActivity extends Activity {
    private JSONObject octopusChargeFromRemote;
    private WebView webView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_octopus_charge);
        initUI();
    }

    private void initUI() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OctopusChargeActivity.this.m443xbab9eb56(view);
            }
        });
        ((TextView) findViewById(R.id.titleTextView)).setText(getString(R.string.octopus_charge));
        setupWebView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initUI$0$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m443xbab9eb56(View view) {
        handleBack();
    }

    private void setupWebView() {
        WebView webView = (WebView) findViewById(R.id.webView);
        this.webView = webView;
        webView.setWebViewClient(new WebViewClient() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                if ("file:///android_asset/module/octopusCharge/index.html".equals(str)) {
                    OctopusChargeActivity.this.updateWebContent();
                }
            }
        });
        this.webView.addJavascriptInterface(new WebAppInterfaceOctopusCharge(), "WebAppInterfaceOctopusCharge");
        configureWebSettings(this.webView.getSettings());
        this.webView.loadUrl("file:///android_asset/module/octopusCharge/index.html");
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
    public void updateWebContent() {
        JSONObject jSONObject = this.octopusChargeFromRemote;
        if (jSONObject != null) {
            final String jSONObject2 = jSONObject.toString();
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OctopusChargeActivity.this.m447x7b9a96ab(jSONObject2);
                }
            });
        } else {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    OctopusChargeActivity.this.m448x819e620a();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateWebContent$1$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m447x7b9a96ab(String str) {
        this.webView.loadUrl("javascript:getList(" + str + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateWebContent$2$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m448x819e620a() {
        this.webView.loadUrl("javascript:getList(null)");
    }

    private void handleBack() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        fetchOctopusChargeData(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                OctopusChargeActivity.this.updateWebContent();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchOctopusChargeData(final Runnable runnable) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                OctopusChargeActivity.this.m440x53053292(runnable);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$fetchOctopusChargeData$5$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m440x53053292(final Runnable runnable) {
        HashMap hashMap = new HashMap();
        hashMap.put("page", "1");
        hashMap.put("rows", "6");
        try {
            this.octopusChargeFromRemote = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/octopusCharge/inverter/list", hashMap);
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda13
                @Override // java.lang.Runnable
                public final void run() {
                    OctopusChargeActivity.lambda$fetchOctopusChargeData$3(runnable);
                }
            });
        } catch (Exception e) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda14
                @Override // java.lang.Runnable
                public final void run() {
                    OctopusChargeActivity.this.m439x4d016733();
                }
            });
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$fetchOctopusChargeData$3(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$fetchOctopusChargeData$4$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m439x4d016733() {
        Tool.alert(this, getString(R.string.error_fetching_data));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class WebAppInterfaceOctopusCharge {
        private WebAppInterfaceOctopusCharge() {
        }

        @JavascriptInterface
        public String getList() throws JSONException {
            if (OctopusChargeActivity.this.octopusChargeFromRemote != null) {
                return OctopusChargeActivity.this.octopusChargeFromRemote.toString();
            }
            return null;
        }

        @JavascriptInterface
        public void saveForm(String str) {
            OctopusChargeActivity.this.saveData(str, new Consumer() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda5
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    OctopusChargeActivity.WebAppInterfaceOctopusCharge.this.m455xc9e49d33((Boolean) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$saveForm$1$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity$WebAppInterfaceOctopusCharge, reason: not valid java name */
        public /* synthetic */ void m455xc9e49d33(Boolean bool) {
            if (bool.booleanValue()) {
                final OctopusChargeActivity octopusChargeActivity = OctopusChargeActivity.this;
                octopusChargeActivity.fetchOctopusChargeData(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        OctopusChargeActivity.this.updateWebContent();
                    }
                });
            }
        }

        @JavascriptInterface
        public void getForm(String str) {
            OctopusChargeActivity.this.getOptimalSet(str, new ValueCallback() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda1
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    OctopusChargeActivity.WebAppInterfaceOctopusCharge.this.m451x2694e0b5((JSONObject) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getForm$2$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity$WebAppInterfaceOctopusCharge, reason: not valid java name */
        public /* synthetic */ void m449x548556b3(JSONObject jSONObject) {
            OctopusChargeActivity.this.webView.evaluateJavascript("handleFormResult('" + jSONObject + "')", null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getForm$4$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity$WebAppInterfaceOctopusCharge, reason: not valid java name */
        public /* synthetic */ void m451x2694e0b5(final JSONObject jSONObject) {
            if (jSONObject != null) {
                OctopusChargeActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        OctopusChargeActivity.WebAppInterfaceOctopusCharge.this.m449x548556b3(jSONObject);
                    }
                });
            } else {
                OctopusChargeActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        OctopusChargeActivity.WebAppInterfaceOctopusCharge.this.m450x3d8d1bb4();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getForm$3$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity$WebAppInterfaceOctopusCharge, reason: not valid java name */
        public /* synthetic */ void m450x3d8d1bb4() {
            OctopusChargeActivity.this.webView.evaluateJavascript("handleFormResult(null)", null);
        }

        @JavascriptInterface
        public void getTableList(String str, String str2) {
            OctopusChargeActivity.this.getDetails(str, str2, new ValueCallback() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda6
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    OctopusChargeActivity.WebAppInterfaceOctopusCharge.this.m454xd2f8536e((JSONObject) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getTableList$5$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity$WebAppInterfaceOctopusCharge, reason: not valid java name */
        public /* synthetic */ void m452xe8c96c(JSONObject jSONObject) {
            OctopusChargeActivity.this.webView.evaluateJavascript("handleFormResult('" + jSONObject + "')", null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getTableList$7$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity$WebAppInterfaceOctopusCharge, reason: not valid java name */
        public /* synthetic */ void m454xd2f8536e(final JSONObject jSONObject) {
            if (jSONObject != null) {
                OctopusChargeActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        OctopusChargeActivity.WebAppInterfaceOctopusCharge.this.m452xe8c96c(jSONObject);
                    }
                });
            } else {
                OctopusChargeActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        OctopusChargeActivity.WebAppInterfaceOctopusCharge.this.m453xe9f08e6d();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getTableList$6$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity$WebAppInterfaceOctopusCharge, reason: not valid java name */
        public /* synthetic */ void m453xe9f08e6d() {
            OctopusChargeActivity.this.webView.evaluateJavascript("handleFormResult(null)", null);
        }

        @JavascriptInterface
        public void setEnableStatus(String str, String str2) {
            OctopusChargeActivity.this.enableDevice(str, str2, new Consumer() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda0
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    OctopusChargeActivity.WebAppInterfaceOctopusCharge.this.m456x892f5017((Boolean) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$setEnableStatus$9$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity$WebAppInterfaceOctopusCharge, reason: not valid java name */
        public /* synthetic */ void m456x892f5017(Boolean bool) {
            if (bool.booleanValue()) {
                final OctopusChargeActivity octopusChargeActivity = OctopusChargeActivity.this;
                octopusChargeActivity.fetchOctopusChargeData(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$WebAppInterfaceOctopusCharge$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        OctopusChargeActivity.this.updateWebContent();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveData(final String str, final Consumer<Boolean> consumer) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                OctopusChargeActivity.this.m446x4aad94b(str, consumer);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveData$8$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m446x4aad94b(String str, final Consumer consumer) {
        try {
            final boolean z = HttpTool.multiPartPostJson("http://as.luxpowertek.com/WManage/web/maintain/octopusDeviceAdvancedSet/saveOrUpdate", str).getBoolean("success");
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OctopusChargeActivity.this.m444xf8a3428d(z, consumer);
                }
            });
        } catch (Exception e) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    OctopusChargeActivity.this.m445xfea70dec(consumer);
                }
            });
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveData$6$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m444xf8a3428d(boolean z, Consumer consumer) {
        if (z) {
            Tool.alert(this, getString(R.string.saved));
        } else {
            Tool.alert(this, getString(R.string.save_failed));
        }
        consumer.accept(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$saveData$7$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m445xfea70dec(Consumer consumer) {
        Tool.alert(this, getString(R.string.error_processing_request));
        consumer.accept(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getOptimalSet(final String str, final ValueCallback<JSONObject> valueCallback) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                OctopusChargeActivity.this.m442x8c0fa759(str, valueCallback);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getOptimalSet$10$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m442x8c0fa759(String str, final ValueCallback valueCallback) {
        final JSONObject jSONObject;
        HashMap hashMap = new HashMap();
        hashMap.put("serialNum", str);
        try {
            jSONObject = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "/web/maintain/octopusDeviceAdvancedSet/getSet", hashMap);
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject = null;
        }
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                OctopusChargeActivity.lambda$getOptimalSet$9(valueCallback, jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getOptimalSet$9(ValueCallback valueCallback, JSONObject jSONObject) {
        if (valueCallback != null) {
            valueCallback.onReceiveValue(jSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDetails(final String str, final String str2, final ValueCallback<JSONObject> valueCallback) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                OctopusChargeActivity.this.m441xc4bf97c7(str, str2, valueCallback);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getDetails$12$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m441xc4bf97c7(String str, String str2, final ValueCallback valueCallback) {
        final JSONObject jSONObject;
        HashMap hashMap = new HashMap();
        hashMap.put("serialNum", str);
        hashMap.put("dateText", str2);
        try {
            jSONObject = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/octopusCharge/setDetail/list", hashMap);
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject = null;
        }
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                OctopusChargeActivity.lambda$getDetails$11(valueCallback, jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getDetails$11(ValueCallback valueCallback, JSONObject jSONObject) {
        if (valueCallback != null) {
            valueCallback.onReceiveValue(jSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableDevice(final String str, final String str2, final Consumer<Boolean> consumer) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                OctopusChargeActivity.this.m438x70c9191(str, str2, consumer);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$enableDevice$15$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m438x70c9191(String str, String str2, final Consumer consumer) {
        JSONObject postJson;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("serialNum", str);
            if (str2.equals("enable")) {
                postJson = HttpTool.postJson("http://as.luxpowertek.com/WManage/web/maintain/optimalSet/enableDevice", hashMap);
            } else {
                postJson = HttpTool.postJson("http://as.luxpowertek.com/WManage/web/maintain/optimalSet/disableDevice", hashMap);
            }
            final boolean z = postJson.getBoolean("success");
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    OctopusChargeActivity.this.m436xfb04fad3(z, consumer);
                }
            });
        } catch (Exception e) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.userCenter.OctopusChargeActivity$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    OctopusChargeActivity.this.m437x108c632(consumer);
                }
            });
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$enableDevice$13$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m436xfb04fad3(boolean z, Consumer consumer) {
        if (z) {
            Tool.alert(this, getString(R.string.saved));
        } else {
            Tool.alert(this, getString(R.string.save_failed));
        }
        consumer.accept(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$enableDevice$14$com-nfcx-luxinvpower-view-userCenter-OctopusChargeActivity, reason: not valid java name */
    public /* synthetic */ void m437x108c632(Consumer consumer) {
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
