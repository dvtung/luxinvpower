package com.nfcx.luxinvpower.view.local.ulCompliance;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes2.dex */
public class ULComplianceActivity extends Activity {
    public static ULComplianceActivity instance;
    private JSONObject cacheReadAllResult;
    private TextView complianceModelText;
    private TextView complianceParallelNumberText;
    private TextView rcpNumberText;
    private TextView usernameText;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ul_compliance);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        String stringExtra = getIntent().getStringExtra("cacheReadAllResultText");
        if (!Tool.isEmpty(stringExtra)) {
            try {
                this.cacheReadAllResult = (JSONObject) new JSONTokener(stringExtra).nextValue();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.local.ulCompliance.ULComplianceActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ULComplianceActivity.instance.finish();
            }
        });
        this.usernameText = (TextView) findViewById(R.id.activity_ul_compliance_user_nameEditText);
        this.rcpNumberText = (TextView) findViewById(R.id.activity_ul_compliance_rcp_numberEditText);
        this.complianceModelText = (TextView) findViewById(R.id.activity_ul_compliance_modelEditText);
        this.complianceParallelNumberText = (TextView) findViewById(R.id.activity_ul_compliance_compliance_parallel_numberEditText);
    }

    public void clickExportPdfButton(View view) {
        if (this.cacheReadAllResult == null) {
            Tool.alert(this, R.string.ul_toast_local_data_empty);
            return;
        }
        final String obj = this.usernameText.getText().toString();
        if (Tool.isEmpty(obj)) {
            Tool.alert(this, R.string.login_toast_account_empty);
            return;
        }
        final String obj2 = this.rcpNumberText.getText().toString();
        if (Tool.isEmpty(obj2)) {
            Tool.alert(this, R.string.ul_toast_rcp_number_empty);
            return;
        }
        final String obj3 = this.complianceModelText.getText().toString();
        if (Tool.isEmpty(obj3)) {
            Tool.alert(this, R.string.ul_toast_model_empty);
            return;
        }
        final String obj4 = this.complianceParallelNumberText.getText().toString();
        if (Tool.isEmpty(obj4)) {
            Tool.alert(this, R.string.ul_toast_parallel_number_empty);
        } else {
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.local.ulCompliance.ULComplianceActivity$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ULComplianceActivity.this.m341x800cecde(obj, obj2, obj3, obj4);
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$clickExportPdfButton$1$com-nfcx-luxinvpower-view-local-ulCompliance-ULComplianceActivity, reason: not valid java name */
    public /* synthetic */ void m341x800cecde(String str, String str2, String str3, String str4) {
        try {
            String uuid = UUID.randomUUID().toString();
            this.cacheReadAllResult.put("exportId", uuid);
            this.cacheReadAllResult.put("username", str);
            this.cacheReadAllResult.put("rpcNumber", str2);
            this.cacheReadAllResult.put("model", str3);
            this.cacheReadAllResult.put("parallelNumber", str4);
            JSONObject multiPartPostJson = HttpTool.multiPartPostJson(GlobalInfo.getInstance().getCustomDoname() + "web/maintain/local/ulCompliance/postExportData", this.cacheReadAllResult.toString());
            if (multiPartPostJson == null || !multiPartPostJson.getBoolean("success")) {
                return;
            }
            Intent intent = new Intent();
            intent.setData(Uri.parse(GlobalInfo.getInstance().getCustomDoname() + "web/maintain/local/ulCompliance/" + uuid));
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        clearActivityData();
    }

    private void clearActivityData() {
        this.cacheReadAllResult = null;
        this.usernameText.setText("");
        this.rcpNumberText.setText("");
        this.complianceModelText.setText("");
        this.complianceParallelNumberText.setText("");
    }
}
