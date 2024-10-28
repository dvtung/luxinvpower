package com.nfcx.luxinvpower.view.main.fragment.lv2;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.param.BattChgPARAM;
import com.nfcx.luxinvpower.global.bean.param.PARAM;
import com.nfcx.luxinvpower.global.bean.set.REMOTE_WRITE_TYPE;
import com.nfcx.luxinvpower.global.bean.set.RemoteReadInfo;
import com.nfcx.luxinvpower.global.bean.set.RemoteWriteInfo;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.global.cache.RemoteSetCacheManager;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.InvTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.main.fragment.lv1.AbstractItemFragment;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.plant.PlantListActivity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Lv2RemoteSetFragment extends AbstractItemFragment {
    private boolean created;
    private Fragment fragment;
    private Inverter inverter;
    private List<Inverter> inverterList;
    private Spinner inverterSpinner;
    private Button readAllButton;
    private String titleText;
    private TextView titleTextView;
    private WebView webView;

    public Lv2RemoteSetFragment() {
        super(51L);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_lv2_remote_set, viewGroup, false);
        this.fragment = this;
        InitView(inflate);
        this.created = true;
        return inflate;
    }

    public boolean canGoBackInWebView() {
        WebView webView = this.webView;
        if (webView != null) {
            return webView.canGoBack();
        }
        return false;
    }

    public void goBackInWebView() {
        this.webView.goBack();
    }

    private void InitView(final View view) {
        final UserData userData = GlobalInfo.getInstance().getUserData();
        this.titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        this.inverterSpinner = (Spinner) view.findViewById(R.id.new_remote_set_inverter_spinner);
        this.readAllButton = (Button) view.findViewById(R.id.new_remote_set_readAllButton);
        this.webView = (WebView) view.findViewById(R.id.webView);
        ((ConstraintLayout) view.findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv2.Lv2RemoteSetFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (Lv2RemoteSetFragment.this.webView.canGoBack()) {
                    Lv2RemoteSetFragment.this.webView.goBack();
                } else {
                    Lv2RemoteSetFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) (ROLE.VIEWER.equals(userData.getRole()) ? PlantListActivity.class : PlantOverviewActivity.class)));
                    MainActivity.instance.finish();
                }
            }
        });
        WebSettings settings = this.webView.getSettings();
        settings.setCacheMode(2);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        this.webView.setWebChromeClient(new WebChromeClient());
        settings.setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv2.Lv2RemoteSetFragment.2
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                Lv2RemoteSetFragment.this.inverterSpinner.setClickable(false);
                Lv2RemoteSetFragment.this.inverterSpinner.setEnabled(false);
                Lv2RemoteSetFragment.this.readAllButton.setVisibility(4);
                Lv2RemoteSetFragment.this.titleTextView.setText(Lv2RemoteSetFragment.this.titleText);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (str.equals("file:///android_asset/module/settingContent/userCenterDetail.html")) {
                    Lv2RemoteSetFragment.this.inverterSpinner.setClickable(true);
                    Lv2RemoteSetFragment.this.inverterSpinner.setEnabled(true);
                    Lv2RemoteSetFragment.this.readAllButton.setVisibility(0);
                } else {
                    Lv2RemoteSetFragment.this.inverterSpinner.setClickable(false);
                    Lv2RemoteSetFragment.this.inverterSpinner.setEnabled(false);
                    Lv2RemoteSetFragment.this.readAllButton.setVisibility(8);
                }
                webView.setVisibility(0);
                Lv2RemoteSetFragment.this.titleTextView.setText(Lv2RemoteSetFragment.this.titleText);
            }
        });
        this.webView.loadUrl("file:///android_asset/module/settingContent/userCenterDetail.html");
        this.webView.addJavascriptInterface(new WebAppInterface(this), "WebAppInterface");
        if (userData.getCurrentPlant() != null) {
            this.inverterList = userData.getInvertersByPlant(userData.getCurrentPlant().getPlantId());
        }
        if (this.inverterList == null) {
            this.inverterList = new ArrayList();
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, this.inverterList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.inverterSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        this.readAllButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv2.Lv2RemoteSetFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                Lv2RemoteSetFragment.this.m375x4e716142(view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$InitView$0$com-nfcx-luxinvpower-view-main-fragment-lv2-Lv2RemoteSetFragment, reason: not valid java name */
    public /* synthetic */ void m375x4e716142(View view) {
        if (this.inverter != null) {
            JSONObject cache = RemoteSetCacheManager.getInstance().getCache(this.inverter.getSerialNum());
            if (cache != null && cache.length() > 100) {
                fillDataFromCache();
                return;
            }
            RemoteSetCacheManager.getInstance().clearCache(this.inverter.getSerialNum());
            this.readAllButton.setEnabled(false);
            this.webView.setOnTouchListener(new View.OnTouchListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv2.Lv2RemoteSetFragment.3
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    return true;
                }
            });
            if (this.inverter.supportRead127Register()) {
                new ReadMultiParamTask(this).execute(new RemoteReadInfo(this.inverter.getSerialNum(), 0, 127), new RemoteReadInfo(this.inverter.getSerialNum(), 127, 127));
                return;
            }
            new ReadMultiParamTask(this).execute(new RemoteReadInfo(this.inverter.getSerialNum(), 0, 40), new RemoteReadInfo(this.inverter.getSerialNum(), 40, 40), new RemoteReadInfo(this.inverter.getSerialNum(), 80, 40), new RemoteReadInfo(this.inverter.getSerialNum(), 120, 40), new RemoteReadInfo(this.inverter.getSerialNum(), Opcodes.IF_ICMPNE, 40), new RemoteReadInfo(this.inverter.getSerialNum(), ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 40));
        }
    }

    public JSONArray getParamInfos(List<PARAM> list) {
        JSONArray jSONArray = new JSONArray();
        UserData userData = GlobalInfo.getInstance().getUserData();
        if (list != null && !list.isEmpty()) {
            for (PARAM param : list) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("device", this.inverter.getDeviceType());
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("holdParam", param.name());
                    jSONObject2.put("paramName", getString(param.getResourceId(this.inverter)));
                    jSONObject2.put("visible", !param.checkInvisible(userData));
                    jSONObject2.put("deviceType", this.inverter.getDeviceType());
                    jSONArray.put(jSONObject2);
                    jSONArray.put(jSONObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    public JSONArray getBattChgParamInfos(List<BattChgPARAM> list, boolean z) {
        JSONArray jSONArray = new JSONArray();
        UserData userData = GlobalInfo.getInstance().getUserData();
        if (list != null && !list.isEmpty()) {
            for (BattChgPARAM battChgPARAM : list) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("holdParam", battChgPARAM.name());
                    jSONObject.put("paramName", getString(battChgPARAM.getResourceId(this.inverter)));
                    jSONObject.put("visible", !battChgPARAM.checkInvisible(userData));
                    jSONObject.put("enabled", battChgPARAM.checkEnabled(z));
                    jSONObject.put("deviceType", this.inverter.getDeviceType());
                    jSONArray.put(jSONObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    /* loaded from: classes2.dex */
    private static class ReadMultiParamTask extends AsyncTask<RemoteReadInfo, JSONObject, Void> {
        private Lv2RemoteSetFragment fragment;

        public ReadMultiParamTask(Lv2RemoteSetFragment lv2RemoteSetFragment) {
            this.fragment = lv2RemoteSetFragment;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(RemoteReadInfo... remoteReadInfoArr) {
            JSONObject postJson;
            for (RemoteReadInfo remoteReadInfo : remoteReadInfoArr) {
                HashMap hashMap = new HashMap();
                hashMap.put("inverterSn", remoteReadInfo.getSerialNum());
                hashMap.put("startRegister", String.valueOf(remoteReadInfo.getStartRegister()));
                hashMap.put("pointNumber", String.valueOf(remoteReadInfo.getPointNumber()));
                hashMap.put("autoRetry", String.valueOf(true));
                try {
                    postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteRead/read", hashMap);
                    publishProgress(postJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (postJson == null || !postJson.getBoolean("success")) {
                    return null;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(JSONObject... jSONObjectArr) {
            super.onProgressUpdate((Object[]) jSONObjectArr);
            for (JSONObject jSONObject : jSONObjectArr) {
                if (jSONObject != null) {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            Toast.makeText(this.fragment.getContext(), R.string.page_maintain_remote_set_result_unknown_error, 1).show();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (jSONObject.getBoolean("success")) {
                        if (this.fragment.inverter != null) {
                            RemoteSetCacheManager.getInstance().setCache(this.fragment.inverter.getSerialNum(), jSONObject);
                        }
                        this.fragment.fillDataFromCache();
                    }
                }
                this.fragment.toast(jSONObject);
                return;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            super.onPostExecute((ReadMultiParamTask) r2);
            this.fragment.readAllButton.setEnabled(true);
            this.fragment.webView.setOnTouchListener(null);
        }
    }

    /* loaded from: classes2.dex */
    private static class WriteParamTask extends AsyncTask<RemoteWriteInfo, Void, JSONObject> {
        private Lv2RemoteSetFragment fragment;
        private RemoteWriteInfo remoteWriteInfo;

        public WriteParamTask(Lv2RemoteSetFragment lv2RemoteSetFragment) {
            this.fragment = lv2RemoteSetFragment;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
            this.remoteWriteInfo.setEnabled(false);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(RemoteWriteInfo... remoteWriteInfoArr) {
            RemoteWriteInfo remoteWriteInfo = remoteWriteInfoArr[0];
            if (remoteWriteInfo != null && remoteWriteInfo.getRemoteWriteType() != null) {
                this.remoteWriteInfo = remoteWriteInfo;
                publishProgress(new Void[0]);
                switch (AnonymousClass5.$SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[remoteWriteInfo.getRemoteWriteType().ordinal()]) {
                    case 1:
                        if (Tool.isEmpty(remoteWriteInfo.getValueText())) {
                            return this.fragment.createFailureJSONObject("PARAM_EMPTY");
                        }
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put(remoteWriteInfo.getHoldParam(), remoteWriteInfo.getValueText());
                            RemoteSetCacheManager.getInstance().setCache(this.fragment.inverter.getSerialNum(), jSONObject);
                            return postWriteParam(remoteWriteInfo.getSerialNum(), remoteWriteInfo.getHoldParam(), remoteWriteInfo.getValueText());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    case 2:
                        String valueText = remoteWriteInfo.getValueText();
                        if (Tool.isEmpty(valueText)) {
                            return this.fragment.createFailureJSONObject("PARAM_EMPTY");
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put(remoteWriteInfo.getBitParam(), remoteWriteInfo.getValueText());
                            RemoteSetCacheManager.getInstance().setCache(this.fragment.inverter.getSerialNum(), jSONObject2);
                            return postWriteBitParam(remoteWriteInfo.getSerialNum(), remoteWriteInfo.getBitParam(), valueText);
                        } catch (JSONException e2) {
                            throw new RuntimeException(e2);
                        }
                    case 3:
                        String valueText2 = remoteWriteInfo.getValueText();
                        if (Tool.isEmpty(valueText2)) {
                            return this.fragment.createFailureJSONObject("PARAM_EMPTY");
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        try {
                            String modelParam = this.fragment.getModelParam(remoteWriteInfo.getHoldParam());
                            if (!Tool.isEmpty(modelParam)) {
                                jSONObject3.put(modelParam, remoteWriteInfo.getValueText());
                            }
                            RemoteSetCacheManager.getInstance().setCache(this.fragment.inverter.getSerialNum(), jSONObject3);
                            return postWriteBitModelParam(remoteWriteInfo.getSerialNum(), remoteWriteInfo.getHoldParam(), valueText2);
                        } catch (JSONException e3) {
                            throw new RuntimeException(e3);
                        }
                    case 4:
                        return postResetParam(remoteWriteInfo.getSerialNum(), remoteWriteInfo.getResetParam());
                    case 5:
                        String hourText = remoteWriteInfo.getHourText();
                        String minuteText = remoteWriteInfo.getMinuteText();
                        if (Tool.isEmpty(hourText) || Tool.isEmpty(minuteText)) {
                            return this.fragment.createFailureJSONObject("PARAM_EMPTY");
                        }
                        try {
                            int parseInt = Integer.parseInt(hourText);
                            int parseInt2 = Integer.parseInt(minuteText);
                            if (parseInt < 0 || parseInt > 23) {
                                return this.fragment.createOutRangeJSONObject("0", "23");
                            }
                            if (parseInt2 < 0 || parseInt2 > 59) {
                                return this.fragment.createOutRangeJSONObject("0", "59");
                            }
                            JSONObject jSONObject4 = new JSONObject();
                            try {
                                String timeParam = remoteWriteInfo.getTimeParam();
                                String readParams = this.fragment.getReadParams(timeParam);
                                String[] strArr = new String[0];
                                if (readParams != null) {
                                    strArr = readParams.split(",");
                                }
                                jSONObject4.put(strArr[0], remoteWriteInfo.getHourText());
                                jSONObject4.put(strArr[1], remoteWriteInfo.getMinuteText());
                                jSONObject4.put(timeParam, hourText + ":" + minuteText);
                                RemoteSetCacheManager.getInstance().setCache(this.fragment.inverter.getSerialNum(), jSONObject4);
                                return postWriteTimeParam(remoteWriteInfo.getSerialNum(), remoteWriteInfo.getTimeParam(), hourText, minuteText);
                            } catch (JSONException e4) {
                                throw new RuntimeException(e4);
                            }
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            return this.fragment.createFailureJSONObject("INTEGER_FORMAT_ERROR");
                        }
                    case 6:
                        HashMap hashMap = new HashMap();
                        hashMap.put("inverterSn", remoteWriteInfo.getSerialNum());
                        hashMap.put("functionParam", remoteWriteInfo.getFunctionParam());
                        hashMap.put("enable", String.valueOf(remoteWriteInfo.isFunctionToggleButtonChecked()));
                        hashMap.put("clientType", "APP");
                        hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
                        try {
                            JSONObject jSONObject5 = new JSONObject();
                            try {
                                jSONObject5.put(remoteWriteInfo.getFunctionParam(), remoteWriteInfo.isFunctionToggleButtonChecked());
                                RemoteSetCacheManager.getInstance().setCache(this.fragment.inverter.getSerialNum(), jSONObject5);
                                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/functionControl", hashMap);
                            } catch (JSONException e6) {
                                throw new RuntimeException(e6);
                            }
                        } catch (Exception e7) {
                            e7.printStackTrace();
                            break;
                        }
                }
            }
            return this.fragment.createFailureJSONObject("UNKNOWN_ERROR");
        }

        private JSONObject postWriteParam(String str, String str2, String str3) {
            HashMap hashMap = new HashMap();
            hashMap.put("inverterSn", str);
            hashMap.put("holdParam", str2);
            hashMap.put("valueText", str3);
            hashMap.put("clientType", "APP");
            hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/write", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private JSONObject postResetParam(String str, String str2) {
            HashMap hashMap = new HashMap();
            hashMap.put("inverterSn", str);
            hashMap.put("resetParam", str2);
            hashMap.put("clientType", "APP");
            hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/reset", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private JSONObject postWriteBitParam(String str, String str2, String str3) {
            HashMap hashMap = new HashMap();
            hashMap.put("inverterSn", str);
            hashMap.put("bitParam", str2);
            hashMap.put("value", str3);
            hashMap.put("clientType", "APP");
            hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/bitParamControl", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private JSONObject postWriteBitModelParam(String str, String str2, String str3) {
            HashMap hashMap = new HashMap();
            hashMap.put("inverterSn", str);
            hashMap.put("modelBitParam", str2);
            hashMap.put("value", str3);
            hashMap.put("clientType", "APP");
            hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/bitModelParamControl", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private JSONObject postWriteTimeParam(String str, String str2, String str3, String str4) {
            HashMap hashMap = new HashMap();
            hashMap.put("inverterSn", str);
            hashMap.put("timeParam", str2);
            hashMap.put("hour", str3);
            hashMap.put("minute", str4);
            hashMap.put("clientType", "APP");
            hashMap.put("remoteSetType", InvTool.STATUS_NORMAL);
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/maintain/remoteSet/writeTime", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((WriteParamTask) jSONObject);
            try {
                if (jSONObject != null) {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            Tool.alert(this.fragment.getActivity(), R.string.page_maintain_remote_set_result_unknown_error);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (jSONObject.getBoolean("success")) {
                        Tool.alert(this.fragment.getActivity(), R.string.page_maintain_remote_set_result_success);
                        if (this.remoteWriteInfo != null && REMOTE_WRITE_TYPE.CONTROL.equals(this.remoteWriteInfo.getRemoteWriteType()) && !"FUNC_BAT_CHARGE_CONTROL".equals(this.remoteWriteInfo.getFunctionParam())) {
                            "FUNC_BAT_DISCHARGE_CONTROL".equals(this.remoteWriteInfo.getFunctionParam());
                        }
                    }
                }
                if (this.remoteWriteInfo != null && REMOTE_WRITE_TYPE.CONTROL.equals(this.remoteWriteInfo.getRemoteWriteType()) && this.remoteWriteInfo.getFunctionToggleButton() != null) {
                    this.remoteWriteInfo.getFunctionToggleButton().setChecked(!this.remoteWriteInfo.getFunctionToggleButton().isChecked());
                }
                this.fragment.toast(jSONObject);
            } finally {
                this.remoteWriteInfo.setEnabled(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.main.fragment.lv2.Lv2RemoteSetFragment$5, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE;

        static {
            int[] iArr = new int[REMOTE_WRITE_TYPE.values().length];
            $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE = iArr;
            try {
                iArr[REMOTE_WRITE_TYPE.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[REMOTE_WRITE_TYPE.BIT_PARAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[REMOTE_WRITE_TYPE.BIT_MODEL_PARAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[REMOTE_WRITE_TYPE.RESET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[REMOTE_WRITE_TYPE.TIME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$nfcx$luxinvpower$global$bean$set$REMOTE_WRITE_TYPE[REMOTE_WRITE_TYPE.CONTROL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject createOutRangeJSONObject(String str, String str2) {
        try {
            JSONObject createFailureJSONObject = createFailureJSONObject("OUT_RANGE_ERROR");
            if (createFailureJSONObject == null) {
                return null;
            }
            createFailureJSONObject.put("minValue", str);
            createFailureJSONObject.put("maxValue", str2);
            return createFailureJSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject createFailureJSONObject(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("success", false);
            jSONObject.put("msg", str);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initInverterSpinnerOnItemSelectedListener() {
        if (this.inverterSpinner.getOnItemSelectedListener() == null) {
            System.out.println("LuxPower - Lv2RemoteSetFragment initInverterSpinnerOnItemSelectedListener...");
            this.inverterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv2.Lv2RemoteSetFragment.4
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    Lv2RemoteSetFragment.this.updateSelectInverter((Inverter) Lv2RemoteSetFragment.this.inverterSpinner.getSelectedItem());
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                    if (Lv2RemoteSetFragment.this.inverter != null) {
                        Lv2RemoteSetFragment.this.inverter = null;
                        GlobalInfo.getInstance().getUserData().setCurrentInverter(Lv2RemoteSetFragment.this.inverter, true);
                        Lv2RemoteSetFragment.this.readAllButton.performClick();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getModelParam(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -691573023:
                if (str.equals("MODEL_BIT_LEAD_ACID_TYPE")) {
                    c = 0;
                    break;
                }
                break;
            case 405733769:
                if (str.equals("MODEL_BIT_METER_BRAND")) {
                    c = 1;
                    break;
                }
                break;
            case 660469460:
                if (str.equals("MODEL_BIT_BATTERY_TYPE")) {
                    c = 2;
                    break;
                }
                break;
            case 1310128660:
                if (str.equals("MODEL_BIT_MEASUREMENT")) {
                    c = 3;
                    break;
                }
                break;
            case 1375472721:
                if (str.equals("MODEL_BIT_LITHIUM_TYPE")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "HOLD_MODEL_leadAcidType";
            case 1:
                return "HOLD_MODEL_meterBrand";
            case 2:
                return "HOLD_MODEL_batteryType";
            case 3:
                return "HOLD_MODEL_measurement";
            case 4:
                return "HOLD_MODEL_lithiumType";
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getReadParams(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2091729313:
                if (str.equals("HOLD_AC_CHARGE_END_TIME")) {
                    c = 0;
                    break;
                }
                break;
            case -1910210817:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_TIME_1")) {
                    c = 1;
                    break;
                }
                break;
            case -1910210816:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_TIME_2")) {
                    c = 2;
                    break;
                }
                break;
            case -1790606454:
                if (str.equals("HOLD_PEAK_SHAVING_END_TIME_1")) {
                    c = 3;
                    break;
                }
                break;
            case -1790606453:
                if (str.equals("HOLD_PEAK_SHAVING_END_TIME_2")) {
                    c = 4;
                    break;
                }
                break;
            case -1563549068:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_TIME")) {
                    c = 5;
                    break;
                }
                break;
            case -1551266129:
                if (str.equals("HOLD_FORCED_CHARGE_START_TIME")) {
                    c = 6;
                    break;
                }
                break;
            case -1274671800:
                if (str.equals("HOLD_FORCED_CHARGE_END_TIME_1")) {
                    c = 7;
                    break;
                }
                break;
            case -1274671799:
                if (str.equals("HOLD_FORCED_CHARGE_END_TIME_2")) {
                    c = '\b';
                    break;
                }
                break;
            case -783053080:
                if (str.equals("HOLD_AC_FIRST_START_TIME_1")) {
                    c = '\t';
                    break;
                }
                break;
            case -783053079:
                if (str.equals("HOLD_AC_FIRST_START_TIME_2")) {
                    c = '\n';
                    break;
                }
                break;
            case -413095263:
                if (str.equals("HOLD_FORCED_CHARGE_START_TIME_1")) {
                    c = 11;
                    break;
                }
                break;
            case -413095262:
                if (str.equals("HOLD_FORCED_CHARGE_START_TIME_2")) {
                    c = '\f';
                    break;
                }
                break;
            case -383766600:
                if (str.equals("HOLD_AC_CHARGE_START_TIME")) {
                    c = '\r';
                    break;
                }
                break;
            case -107172271:
                if (str.equals("HOLD_AC_CHARGE_END_TIME_1")) {
                    c = 14;
                    break;
                }
                break;
            case -107172270:
                if (str.equals("HOLD_AC_CHARGE_END_TIME_2")) {
                    c = 15;
                    break;
                }
                break;
            case 128282390:
                if (str.equals("HOLD_FORCED_CHARGE_END_TIME")) {
                    c = 16;
                    break;
                }
                break;
            case 391307917:
                if (str.equals("HOLD_FORCED_DISCHARGE_START_TIME")) {
                    c = 17;
                    break;
                }
                break;
            case 531028150:
                if (str.equals("HOLD_AC_FIRST_START_TIME")) {
                    c = 18;
                    break;
                }
                break;
            case 567487850:
                if (str.equals("HOLD_AC_CHARGE_START_TIME_1")) {
                    c = 19;
                    break;
                }
                break;
            case 567487851:
                if (str.equals("HOLD_AC_CHARGE_START_TIME_2")) {
                    c = 20;
                    break;
                }
                break;
            case 667902246:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_TIME_1")) {
                    c = 21;
                    break;
                }
                break;
            case 667902247:
                if (str.equals("HOLD_FORCED_DISCHARGE_END_TIME_2")) {
                    c = 22;
                    break;
                }
                break;
            case 807622479:
                if (str.equals("HOLD_AC_FIRST_END_TIME_1")) {
                    c = 23;
                    break;
                }
                break;
            case 807622480:
                if (str.equals("HOLD_AC_FIRST_END_TIME_2")) {
                    c = 24;
                    break;
                }
                break;
            case 885755613:
                if (str.equals("HOLD_AC_FIRST_END_TIME")) {
                    c = 25;
                    break;
                }
                break;
            case 1989908579:
                if (str.equals("HOLD_PEAK_SHAVING_START_TIME_1")) {
                    c = 26;
                    break;
                }
                break;
            case 1989908580:
                if (str.equals("HOLD_PEAK_SHAVING_START_TIME_2")) {
                    c = 27;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "HOLD_AC_CHARGE_END_HOUR,HOLD_AC_CHARGE_END_MINUTE";
            case 1:
                return "HOLD_FORCED_DISCHARGE_START_HOUR_1,HOLD_FORCED_DISCHARGE_START_MINUTE_1";
            case 2:
                return "HOLD_FORCED_DISCHARGE_START_HOUR_2,HOLD_FORCED_DISCHARGE_START_MINUTE_2";
            case 3:
                return "LSP_HOLD_DIS_CHG_POWER_TIME_39,LSP_HOLD_DIS_CHG_POWER_TIME_40";
            case 4:
                return "LSP_HOLD_DIS_CHG_POWER_TIME_43,LSP_HOLD_DIS_CHG_POWER_TIME_44";
            case 5:
                return "HOLD_FORCED_DISCHARGE_END_HOUR,HOLD_FORCED_DISCHARGE_END_MINUTE";
            case 6:
                return "HOLD_FORCED_CHARGE_START_HOUR,HOLD_FORCED_CHARGE_START_MINUTE";
            case 7:
                return "HOLD_FORCED_CHARGE_END_HOUR_1,HOLD_FORCED_CHARGE_END_MINUTE_1";
            case '\b':
                return "HOLD_FORCED_CHARGE_END_HOUR_2,HOLD_FORCED_CHARGE_END_MINUTE_2";
            case '\t':
                return "HOLD_AC_FIRST_START_HOUR_1,HOLD_AC_FIRST_START_MINUTE_1";
            case '\n':
                return "HOLD_AC_FIRST_START_HOUR_2,HOLD_AC_FIRST_START_MINUTE_2";
            case 11:
                return "HOLD_FORCED_CHARGE_START_HOUR_1,HOLD_FORCED_CHARGE_START_MINUTE_1";
            case '\f':
                return "HOLD_FORCED_CHARGE_START_HOUR_2,HOLD_FORCED_CHARGE_START_MINUTE_2";
            case '\r':
                return "HOLD_AC_CHARGE_START_HOUR,HOLD_AC_CHARGE_START_MINUTE";
            case 14:
                return "HOLD_AC_CHARGE_END_HOUR_1,HOLD_AC_CHARGE_END_MINUTE_1";
            case 15:
                return "HOLD_AC_CHARGE_END_HOUR_2,HOLD_AC_CHARGE_END_MINUTE_2";
            case 16:
                return "HOLD_FORCED_CHARGE_END_HOUR,HOLD_FORCED_CHARGE_END_MINUTE";
            case 17:
                return "HOLD_FORCED_DISCHARGE_START_HOUR,HOLD_FORCED_DISCHARGE_START_HOUR";
            case 18:
                return "HOLD_AC_FIRST_START_HOUR,HOLD_AC_FIRST_START_MINUTE";
            case 19:
                return "HOLD_AC_CHARGE_START_HOUR_1,HOLD_AC_CHARGE_START_MINUTE_1";
            case 20:
                return "HOLD_AC_CHARGE_START_HOUR_2,HOLD_AC_CHARGE_START_MINUTE_2";
            case 21:
                return "HOLD_FORCED_DISCHARGE_END_HOUR_1,HOLD_FORCED_DISCHARGE_END_MINUTE_1";
            case 22:
                return "HOLD_FORCED_DISCHARGE_END_HOUR_2,HOLD_FORCED_DISCHARGE_END_MINUTE_2";
            case 23:
                return "HOLD_AC_FIRST_START_END_HOUR_1,HOLD_AC_FIRST_START_END_MINUTE_1";
            case 24:
                return "HOLD_AC_FIRST_START_END_HOUR_2,HOLD_AC_FIRST_START_END_MINUTE_2";
            case 25:
                return "HOLD_AC_FIRST_START_END_HOUR,HOLD_AC_FIRST_START_END_MINUTE";
            case 26:
                return "LSP_HOLD_DIS_CHG_POWER_TIME_37,LSP_HOLD_DIS_CHG_POWER_TIME_38";
            case 27:
                return "LSP_HOLD_DIS_CHG_POWER_TIME_41,LSP_HOLD_DIS_CHG_POWER_TIME_42";
            default:
                return null;
        }
    }

    public void fillDataFromCache() {
        if (this.created) {
            UserData userData = GlobalInfo.getInstance().getUserData();
            if (userData.getCurrentInverter() != null) {
                JSONObject cache = RemoteSetCacheManager.getInstance().getCache(userData.getCurrentInverter().getSerialNum());
                if (cache != null) {
                    WebView webView = this.webView;
                    if (webView != null) {
                        webView.loadUrl("javascript:getData(" + cache + ")");
                        return;
                    }
                    return;
                }
                WebView webView2 = this.webView;
                if (webView2 != null) {
                    webView2.loadUrl("javascript:getData()");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSelectInverter(Inverter inverter) {
        System.out.println("LuxPower - Lv2RemoteSetFragment selectInverter = " + inverter.getSerialNum() + ", inverter = " + this.inverter);
        Inverter inverter2 = this.inverter;
        if (inverter2 == null || !inverter2.getSerialNum().equals(inverter.getSerialNum())) {
            this.inverter = inverter;
            this.readAllButton.performClick();
            GlobalInfo.getInstance().getUserData().setCurrentInverter(this.inverter, true);
        }
    }

    public void refreshFragmentParams() {
        Inverter inverter;
        if (this.created) {
            UserData userData = GlobalInfo.getInstance().getUserData();
            if (userData.getCurrentInverter() == null || this.inverterList == null) {
                return;
            }
            for (int i = 0; i < this.inverterList.size(); i++) {
                Inverter inverter2 = this.inverterList.get(i);
                if (inverter2.getSerialNum().equals(userData.getCurrentInverter().getSerialNum()) && ((inverter = this.inverter) == null || !inverter.getSerialNum().equals(userData.getCurrentInverter().getSerialNum()))) {
                    if (this.inverterSpinner.getSelectedItemPosition() != i) {
                        this.inverterSpinner.setSelection(i);
                    } else {
                        updateSelectInverter(inverter2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class WebAppInterface {
        private Lv2RemoteSetFragment fragment;

        WebAppInterface(Lv2RemoteSetFragment lv2RemoteSetFragment) {
            this.fragment = lv2RemoteSetFragment;
        }

        @JavascriptInterface
        public String getData() throws JSONException {
            JSONObject cache = RemoteSetCacheManager.getInstance().getCache(Lv2RemoteSetFragment.this.inverter.getSerialNum());
            if (cache == null) {
                cache = new JSONObject();
            }
            return cache.toString();
        }

        @JavascriptInterface
        public String getLocalLanguageResources() {
            JSONObject jSONObject = new JSONObject();
            Resources resources = this.fragment.getResources();
            for (Field field : R.string.class.getFields()) {
                try {
                    jSONObject.put(field.getName(), resources.getString(field.getInt(null)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return jSONObject.toString();
        }

        @JavascriptInterface
        public void adJustTitle(String str) {
            if (Lv2RemoteSetFragment.this.inverter != null) {
                this.fragment.titleText = str;
            }
        }

        @JavascriptInterface
        public String getParamVisible(String str, String str2) throws JSONException {
            if (Lv2RemoteSetFragment.this.inverter == null) {
                return null;
            }
            List list = (List) new Gson().fromJson(str, new TypeToken<List<String>>() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv2.Lv2RemoteSetFragment.WebAppInterface.1
            }.getType());
            if (!Tool.isEmpty(str2)) {
                ArrayList arrayList = new ArrayList();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(BattChgPARAM.valueOf((String) it.next()));
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("paramInfos", this.fragment.getBattChgParamInfos(arrayList, str2.equals("SOC")));
                return jSONObject.toString();
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add(PARAM.valueOf((String) it2.next()));
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("paramInfos", this.fragment.getParamInfos(arrayList2));
            return jSONObject2.toString();
        }

        @JavascriptInterface
        public void runNormalRemoteWrite(String str, String str2) {
            if (Lv2RemoteSetFragment.this.inverter != null) {
                RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
                remoteWriteInfo.setSerialNum(Lv2RemoteSetFragment.this.inverter.getSerialNum());
                remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.NORMAL);
                remoteWriteInfo.setHoldParam(str);
                remoteWriteInfo.setValueText(str2);
                new WriteParamTask(Lv2RemoteSetFragment.this).execute(remoteWriteInfo);
            }
        }

        @JavascriptInterface
        public void runResetRemoteWrite(String str) {
            if (Lv2RemoteSetFragment.this.inverter != null) {
                RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
                remoteWriteInfo.setSerialNum(Lv2RemoteSetFragment.this.inverter.getSerialNum());
                remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.RESET);
                remoteWriteInfo.setResetParam(str);
                new WriteParamTask(Lv2RemoteSetFragment.this).execute(remoteWriteInfo);
            }
        }

        @JavascriptInterface
        public void runBitRemoteWrite(String str, String str2) {
            if (Lv2RemoteSetFragment.this.inverter != null) {
                RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
                remoteWriteInfo.setSerialNum(Lv2RemoteSetFragment.this.inverter.getSerialNum());
                remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.BIT_PARAM);
                remoteWriteInfo.setBitParam(str);
                remoteWriteInfo.setValueText(str2);
                new WriteParamTask(Lv2RemoteSetFragment.this).execute(remoteWriteInfo);
            }
        }

        @JavascriptInterface
        public void runBitModelRemoteWrite(String str, String str2) {
            if (Lv2RemoteSetFragment.this.inverter != null) {
                RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
                remoteWriteInfo.setSerialNum(Lv2RemoteSetFragment.this.inverter.getSerialNum());
                remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.BIT_MODEL_PARAM);
                remoteWriteInfo.setHoldParam(str);
                remoteWriteInfo.setValueText(str2);
                new WriteParamTask(Lv2RemoteSetFragment.this).execute(remoteWriteInfo);
            }
        }

        @JavascriptInterface
        public void runTimeRemoteWrite(String str, String str2, String str3) {
            if (Lv2RemoteSetFragment.this.inverter != null) {
                RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
                remoteWriteInfo.setSerialNum(Lv2RemoteSetFragment.this.inverter.getSerialNum());
                remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.TIME);
                remoteWriteInfo.setTimeParam(str);
                remoteWriteInfo.setHourText(str2.trim());
                remoteWriteInfo.setMinuteText(str3.trim());
                new WriteParamTask(Lv2RemoteSetFragment.this).execute(remoteWriteInfo);
            }
        }

        @JavascriptInterface
        public void runControlRemoteWrite(String str, boolean z) {
            if (Lv2RemoteSetFragment.this.inverter != null) {
                RemoteWriteInfo remoteWriteInfo = new RemoteWriteInfo();
                remoteWriteInfo.setSerialNum(Lv2RemoteSetFragment.this.inverter.getSerialNum());
                remoteWriteInfo.setRemoteWriteType(REMOTE_WRITE_TYPE.CONTROL);
                remoteWriteInfo.setFunctionParam(str);
                remoteWriteInfo.setFunctionToggleButtonChecked(z);
                new WriteParamTask(Lv2RemoteSetFragment.this).execute(remoteWriteInfo);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - Lv2RemoteSetFragment onResume...");
        initInverterSpinnerOnItemSelectedListener();
        refreshFragmentParams();
        this.webView.setWebChromeClient(new WebChromeClient());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toast(JSONObject jSONObject) {
        char c;
        try {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                if (jSONObject == null) {
                    Toast.makeText(activity.getApplicationContext(), R.string.phrase_toast_network_error, 1).show();
                    return;
                }
                String string = jSONObject.getString("msg");
                switch (string.hashCode()) {
                    case -1814651686:
                        if (string.equals("DEVICE_OFFLINE")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -981622981:
                        if (string.equals("ACTION_ERROR_UNDONE")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -556338971:
                        if (string.equals("DATAFRAME_TIMEOUT")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case -553504479:
                        if (string.equals("INTEGER_FORMAT_ERROR")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -75347237:
                        if (string.equals("PARAM_EMPTY")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -75196522:
                        if (string.equals("PARAM_ERROR")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 154026365:
                        if (string.equals("DATAFRAME_UNSEND")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1027843992:
                        if (string.equals("REMOTE_READ_ERROR")) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1099156916:
                        if (string.equals("SERVER_HTTP_EXCEPTION")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1705806578:
                        if (string.equals("REMOTE_SET_ERROR")) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1939189941:
                        if (string.equals("OUT_RANGE_ERROR")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_alert_param_empty, 1).show();
                        return;
                    case 1:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_alert_param_should_int, 1).show();
                        return;
                    case 2:
                        Toast.makeText(activity.getApplicationContext(), getString(R.string.page_maintain_remote_set_alert_param_out_range) + ": [" + jSONObject.getString("minValue") + ", " + jSONObject.getString("maxValue") + "]", 1).show();
                        return;
                    case 3:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_param_error, 1).show();
                        return;
                    case 4:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_set_undo, 1).show();
                        return;
                    case 5:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_device_offline, 1).show();
                        return;
                    case 6:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_command_not_send, 1).show();
                        return;
                    case 7:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_timeout, 1).show();
                        return;
                    case '\b':
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_server_exception, 1).show();
                        return;
                    case '\t':
                    case '\n':
                        Toast.makeText(activity.getApplicationContext(), getString(R.string.page_maintain_remote_set_result_failed) + " " + jSONObject.getInt("errorCode"), 1).show();
                        return;
                    default:
                        Toast.makeText(activity.getApplicationContext(), R.string.page_maintain_remote_set_result_unknown_error, 1).show();
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
