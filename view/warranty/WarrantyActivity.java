package com.nfcx.luxinvpower.view.warranty;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.tool.API;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class WarrantyActivity extends Activity {
    public static WarrantyActivity instance;
    private TextView resultTextView;
    private EditText serialNumEditText;
    private TextView techSupport1Label;
    private ConstraintLayout techSupport1Layout;
    private TextView techSupport1Value;
    private TextView techSupport2Label;
    private ConstraintLayout techSupport2Layout;
    private TextView techSupport2Value;
    private ImageView warrantyCheckImageView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_warranty);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.warranty.WarrantyActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WarrantyActivity.instance.finish();
            }
        });
        this.serialNumEditText = (EditText) findViewById(R.id.serialNumEditText);
        this.resultTextView = (TextView) findViewById(R.id.resultTextView);
        ((ImageView) findViewById(R.id.scanImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.warranty.WarrantyActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WarrantyActivity.this.serialNumEditText.setText("");
                new IntentIntegrator(WarrantyActivity.this).setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES).setPrompt(WarrantyActivity.this.getString(R.string.warranty_scan_tip_text)).setCameraId(0).setBeepEnabled(true).setBarcodeImageEnabled(false).initiateScan();
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.warrantyCheckImageView);
        this.warrantyCheckImageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.warranty.WarrantyActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WarrantyActivity.this.resultTextView.setText("");
                WarrantyActivity.this.techSupport1Value.setText("");
                WarrantyActivity.this.techSupport1Layout.setVisibility(4);
                WarrantyActivity.this.techSupport2Value.setText("");
                WarrantyActivity.this.techSupport2Layout.setVisibility(4);
                new GetInverterWarrantyInfoTask().execute(WarrantyActivity.this.serialNumEditText.getText().toString());
            }
        });
        this.techSupport1Label = (TextView) findViewById(R.id.techSupport1Label);
        this.techSupport1Value = (TextView) findViewById(R.id.techSupport1Value);
        this.techSupport1Layout = (ConstraintLayout) findViewById(R.id.techSupport1Layout);
        TextView textView = (TextView) findViewById(R.id.techSupport2Label);
        this.techSupport2Label = textView;
        textView.setText(getString(R.string.phrase_tech_support) + " 2");
        this.techSupport2Value = (TextView) findViewById(R.id.techSupport2Value);
        this.techSupport2Layout = (ConstraintLayout) findViewById(R.id.techSupport2Layout);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult != null) {
            if (parseActivityResult.getContents() == null) {
                return;
            }
            this.serialNumEditText.setText(parseActivityResult.getContents());
            this.warrantyCheckImageView.performClick();
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    /* loaded from: classes2.dex */
    private static class GetInverterWarrantyInfoTask extends AsyncTask<String, Object, JSONObject> {
        private GetInverterWarrantyInfoTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(String... strArr) {
            HashMap hashMap = new HashMap();
            hashMap.put("serialNum", strArr[0]);
            hashMap.put("needTechInfo", String.valueOf(true));
            try {
                return HttpTool.postJson("http://as.luxpowertek.com:8081/WManage/api/inverter/getInverterWarrantyInfo", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((GetInverterWarrantyInfoTask) jSONObject);
            if (jSONObject != null) {
                try {
                    if (jSONObject.getBoolean("success")) {
                        if (jSONObject.getBoolean("hasWarrantyInfo")) {
                            WarrantyActivity.instance.resultTextView.setText(WarrantyActivity.instance.getString(R.string.warranty_result_warranty_time) + ": " + jSONObject.getString("commissionDate") + " - " + jSONObject.getString("warrantyExpireDate"));
                        } else {
                            WarrantyActivity.instance.resultTextView.setText(R.string.warranty_result_no_warranty_info);
                        }
                        if (jSONObject.has("techInfo")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("techInfo");
                            try {
                                int i = jSONObject2.getInt("techInfoCount");
                                if (i > 0) {
                                    WarrantyActivity.instance.techSupport1Layout.setVisibility(0);
                                    WarrantyActivity.instance.techSupport1Value.setText(Tool.getTechSupportPrefix(WarrantyActivity.instance, jSONObject2, 1) + jSONObject2.getString("techInfo1"));
                                    if (i == 1) {
                                        WarrantyActivity.instance.techSupport1Label.setText(WarrantyActivity.instance.getString(R.string.phrase_tech_support));
                                    } else {
                                        WarrantyActivity.instance.techSupport1Label.setText(WarrantyActivity.instance.getString(R.string.phrase_tech_support) + " 1");
                                        WarrantyActivity.instance.techSupport2Layout.setVisibility(0);
                                        WarrantyActivity.instance.techSupport2Value.setText(Tool.getTechSupportPrefix(WarrantyActivity.instance, jSONObject2, 2) + jSONObject2.getString("techInfo2"));
                                    }
                                }
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if (jSONObject == null) {
                WarrantyActivity.instance.resultTextView.setText(R.string.phrase_toast_network_error);
            } else if (jSONObject.getInt(API.MSG_CODE) == 150) {
                WarrantyActivity.instance.resultTextView.setText(R.string.plant_toast_not_find_device);
            }
        }
    }
}
