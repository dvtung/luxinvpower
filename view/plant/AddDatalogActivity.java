package com.nfcx.luxinvpower.view.plant;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class AddDatalogActivity extends Activity {
    public static AddDatalogActivity instance;
    private Button addDatalogButton;
    private long currentPlantId;
    private EditText datalogCheckCodeEditText;
    private EditText datalogSnEditText;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_datalog);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.plant.AddDatalogActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddDatalogActivity.instance.finish();
            }
        });
        if (!PLATFORM.LUX_POWER.equals(GlobalInfo.getInstance().getUserData().getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        this.currentPlantId = getIntent().getLongExtra("plantId", 0L);
        this.datalogSnEditText = (EditText) findViewById(R.id.register_datalogSnEditText);
        this.datalogCheckCodeEditText = (EditText) findViewById(R.id.register_datalogCheckCodeEditText);
        this.addDatalogButton = (Button) findViewById(R.id.register_addDatalogButton);
        ((ImageView) findViewById(R.id.scanImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.plant.AddDatalogActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddDatalogActivity.this.datalogSnEditText.setText("");
                new IntentIntegrator(AddDatalogActivity.this).setDesiredBarcodeFormats(IntentIntegrator.QR_CODE).setPrompt(AddDatalogActivity.this.getString(R.string.warranty_scan_tip_text)).setCameraId(0).setBeepEnabled(true).setBarcodeImageEnabled(false).initiateScan();
            }
        });
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult != null) {
            if (parseActivityResult.getContents() == null) {
                return;
            }
            this.datalogSnEditText.setText(parseActivityResult.getContents());
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    public void clickAddDatalogButton(View view) {
        String obj = this.datalogSnEditText.getText().toString();
        if (Tool.isEmpty(obj)) {
            Tool.alert(this, R.string.page_register_error_datalogSn_empty);
            this.datalogSnEditText.requestFocus();
            return;
        }
        if (obj.length() != 10) {
            Tool.alert(this, R.string.page_register_error_datalogSn_length);
            this.datalogSnEditText.requestFocus();
            return;
        }
        String obj2 = this.datalogCheckCodeEditText.getText().toString();
        if (Tool.isEmpty(obj2)) {
            Tool.alert(this, R.string.page_register_error_check_code_empty);
            this.datalogCheckCodeEditText.requestFocus();
        } else {
            if (obj2.length() != 5) {
                Tool.alert(this, R.string.page_register_error_check_code_length);
                this.datalogCheckCodeEditText.requestFocus();
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("serialNum", obj);
            hashMap.put("verifyCode", obj2);
            hashMap.put("plantId", String.valueOf(this.currentPlantId));
            this.addDatalogButton.setEnabled(false);
            new AddDatalogTask().execute(hashMap);
        }
    }

    /* loaded from: classes2.dex */
    private static class AddDatalogTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private AddDatalogTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/config/datalog/add", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0073 A[Catch: all -> 0x009a, Exception -> 0x009c, TryCatch #0 {Exception -> 0x009c, blocks: (B:4:0x0006, B:6:0x000e, B:16:0x0018, B:27:0x0058, B:28:0x0061, B:29:0x006a, B:30:0x0073, B:31:0x0033, B:34:0x003d, B:37:0x0047, B:40:0x007c), top: B:2:0x0004, outer: #1 }] */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onPostExecute(org.json.JSONObject r5) {
            /*
                r4 = this;
                super.onPostExecute(r5)
                r0 = 1
                if (r5 == 0) goto L7c
                java.lang.String r1 = "success"
                boolean r1 = r5.getBoolean(r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                if (r1 == 0) goto L18
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                r1 = 2131886465(0x7f120181, float:1.940751E38)
                com.nfcx.luxinvpower.tool.Tool.alert(r5, r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                goto L84
            L18:
                java.lang.String r1 = "msg"
                java.lang.String r5 = r5.getString(r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                int r1 = r5.hashCode()     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                r2 = -1334946316(0xffffffffb06e55f4, float:-8.670604E-10)
                r3 = 2
                if (r1 == r2) goto L47
                r2 = -79698962(0xfffffffffb3fe3ee, float:-9.963517E35)
                if (r1 == r2) goto L3d
                r2 = 254378608(0xf298270, float:8.357465E-30)
                if (r1 == r2) goto L33
                goto L51
            L33:
                java.lang.String r1 = "serialNumLengthError"
                boolean r5 = r5.equals(r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                if (r5 == 0) goto L51
                r5 = 0
                goto L52
            L3d:
                java.lang.String r1 = "serialNumExists"
                boolean r5 = r5.equals(r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                if (r5 == 0) goto L51
                r5 = r3
                goto L52
            L47:
                java.lang.String r1 = "verifyCodeMismatch"
                boolean r5 = r5.equals(r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                if (r5 == 0) goto L51
                r5 = r0
                goto L52
            L51:
                r5 = -1
            L52:
                if (r5 == 0) goto L73
                if (r5 == r0) goto L6a
                if (r5 == r3) goto L61
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                r1 = 2131886910(0x7f12033e, float:1.9408412E38)
                com.nfcx.luxinvpower.tool.Tool.alert(r5, r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                goto L84
            L61:
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                r1 = 2131886526(0x7f1201be, float:1.9407633E38)
                com.nfcx.luxinvpower.tool.Tool.alert(r5, r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                goto L84
            L6a:
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                r1 = 2131886520(0x7f1201b8, float:1.9407621E38)
                com.nfcx.luxinvpower.tool.Tool.alert(r5, r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                goto L84
            L73:
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                r1 = 2131886527(0x7f1201bf, float:1.9407635E38)
                com.nfcx.luxinvpower.tool.Tool.alert(r5, r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                goto L84
            L7c:
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
                r1 = 2131886906(0x7f12033a, float:1.9408404E38)
                com.nfcx.luxinvpower.tool.Tool.alert(r5, r1)     // Catch: java.lang.Throwable -> L9a java.lang.Exception -> L9c
            L84:
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance
                if (r5 == 0) goto Lad
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance
                boolean r5 = r5.isDestroyed()
                if (r5 != 0) goto Lad
            L90:
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance
                android.widget.Button r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.access$200(r5)
                r5.setEnabled(r0)
                goto Lad
            L9a:
                r5 = move-exception
                goto Lae
            L9c:
                r5 = move-exception
                r5.printStackTrace()     // Catch: java.lang.Throwable -> L9a
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance
                if (r5 == 0) goto Lad
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r5 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance
                boolean r5 = r5.isDestroyed()
                if (r5 != 0) goto Lad
                goto L90
            Lad:
                return
            Lae:
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r1 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance
                if (r1 == 0) goto Lc3
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r1 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance
                boolean r1 = r1.isDestroyed()
                if (r1 != 0) goto Lc3
                com.nfcx.luxinvpower.view.plant.AddDatalogActivity r1 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.instance
                android.widget.Button r1 = com.nfcx.luxinvpower.view.plant.AddDatalogActivity.access$200(r1)
                r1.setEnabled(r0)
            Lc3:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.plant.AddDatalogActivity.AddDatalogTask.onPostExecute(org.json.JSONObject):void");
        }
    }
}
