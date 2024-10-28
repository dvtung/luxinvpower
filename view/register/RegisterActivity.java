package com.nfcx.luxinvpower.view.register;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Custom;
import com.nfcx.luxinvpower.view.login.LoginActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class RegisterActivity extends Activity {
    public static RegisterActivity instance;
    private static String scannedCountry;
    private static String scannedRegion;
    private EditText addressEditText;
    private ConstraintLayout clusterConstraintLayout;
    private Spinner clusterSpinner;
    private TextView clusterTextView;
    private Spinner continentSpinner;
    private ArrayAdapter<Property> continentSpinnerAdapter;
    private Spinner countrySpinner;
    private EditText customerCodeEditText;
    private EditText datalogCheckCodeEditText;
    private EditText datalogSnEditText;
    private ToggleButton daylightSavingTimeToggleButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText plantNameEditText;
    private EditText realNameEditText;
    private Spinner regionSpinner;
    private Button registerButton;
    private EditText repeatPasswordEditText;
    private EditText telNumberEditText;
    private Spinner timezoneSpinner;
    private ArrayAdapter<Property> timezoneSpinnerAdapter;
    private EditText usernameEditText;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_register);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.register.RegisterActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegisterActivity.instance.finish();
            }
        });
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.clusterConstraintLayout);
        this.clusterConstraintLayout = constraintLayout;
        constraintLayout.setVisibility(GlobalInfo.getHideClusterAtRegisterPage() ? 8 : 0);
        TextView textView = (TextView) findViewById(R.id.clusterTextView);
        this.clusterTextView = textView;
        textView.setText("* " + getString(R.string.phrase_cluster));
        this.clusterSpinner = (Spinner) findViewById(R.id.register_clusterSpinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getFirstClusterServerIds(this));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.clusterSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        this.clusterSpinner.setSelection(GlobalInfo.getDefaultClusterIdIndex());
        this.usernameEditText = (EditText) findViewById(R.id.register_usernameEditText);
        this.passwordEditText = (EditText) findViewById(R.id.register_passwordEditText);
        this.repeatPasswordEditText = (EditText) findViewById(R.id.register_repeatPasswordEditText);
        this.realNameEditText = (EditText) findViewById(R.id.register_realNameEditText);
        this.emailEditText = (EditText) findViewById(R.id.register_emailEditText);
        this.telNumberEditText = (EditText) findViewById(R.id.register_telNumberEditText);
        this.plantNameEditText = (EditText) findViewById(R.id.register_plantNameEditText);
        this.daylightSavingTimeToggleButton = (ToggleButton) findViewById(R.id.register_daylightSavingTimeToggleButton);
        this.continentSpinner = (Spinner) findViewById(R.id.register_continentSpinner);
        ArrayAdapter<Property> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getContinents(this));
        this.continentSpinnerAdapter = arrayAdapter2;
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.continentSpinner.setAdapter((SpinnerAdapter) this.continentSpinnerAdapter);
        this.continentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.register.RegisterActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                new ReloadRegionSpinnerDataTask().execute(((Property) RegisterActivity.this.continentSpinner.getSelectedItem()).getName());
            }
        });
        this.continentSpinner.setSelection(Custom.DEFAULT_CONTINENT.ordinal());
        Spinner spinner = (Spinner) findViewById(R.id.register_regionSpinner);
        this.regionSpinner = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.register.RegisterActivity.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                new ReloadCountrySpinnerDataTask().execute(((Property) RegisterActivity.this.regionSpinner.getSelectedItem()).getName());
            }
        });
        this.countrySpinner = (Spinner) findViewById(R.id.register_countrySpinner);
        this.timezoneSpinner = (Spinner) findViewById(R.id.register_timezoneSpinner);
        ArrayAdapter<Property> arrayAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getTimezones());
        this.timezoneSpinnerAdapter = arrayAdapter3;
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.timezoneSpinner.setAdapter((SpinnerAdapter) this.timezoneSpinnerAdapter);
        this.timezoneSpinner.setSelection(GlobalInfo.getInstance().getDefaultTimezoneIndex().intValue());
        this.addressEditText = (EditText) findViewById(R.id.register_addressEditText);
        this.customerCodeEditText = (EditText) findViewById(R.id.register_customerCodeEditText);
        ((ImageView) findViewById(R.id.register_customerCodescanImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.register.RegisterActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegisterActivity.this.customerCodeEditText.setText("");
                new IntentIntegrator(RegisterActivity.this).setDesiredBarcodeFormats(IntentIntegrator.QR_CODE).setPrompt(RegisterActivity.this.getString(R.string.warranty_scan_tip_text)).setCameraId(0).setBeepEnabled(true).setBarcodeImageEnabled(false).initiateScan();
            }
        });
        this.datalogSnEditText = (EditText) findViewById(R.id.register_datalogSnEditText);
        this.datalogCheckCodeEditText = (EditText) findViewById(R.id.register_datalogCheckCodeEditText);
        this.registerButton = (Button) findViewById(R.id.register_registerButton);
        ((ImageView) findViewById(R.id.scanImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.register.RegisterActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegisterActivity.this.datalogSnEditText.setText("");
                new IntentIntegrator(RegisterActivity.this).setDesiredBarcodeFormats(IntentIntegrator.QR_CODE).setPrompt(RegisterActivity.this.getString(R.string.warranty_scan_tip_text)).setCameraId(0).setBeepEnabled(true).setBarcodeImageEnabled(false).initiateScan();
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
            try {
                JSONObject jSONObject = new JSONObject(parseActivityResult.getContents());
                if (jSONObject.has("customerCode")) {
                    boolean z = false;
                    if (jSONObject.has("continent")) {
                        String string = jSONObject.getString("continent");
                        scannedRegion = jSONObject.getString("region");
                        scannedCountry = jSONObject.getString("country");
                        long selectedItemId = this.continentSpinner.getSelectedItemId();
                        int i3 = 0;
                        while (true) {
                            if (i3 >= this.continentSpinnerAdapter.getCount()) {
                                i3 = 0;
                                break;
                            }
                            Property item = this.continentSpinnerAdapter.getItem(i3);
                            if (item != null && item.getName().equals(string)) {
                                System.out.println("LuxPower - continentSpinner select: " + i3);
                                break;
                            }
                            i3++;
                        }
                        if (i3 != selectedItemId) {
                            this.continentSpinner.setSelection(0);
                        } else {
                            new ReloadRegionSpinnerDataTask().execute(string);
                        }
                    }
                    if (jSONObject.has("timezone")) {
                        String string2 = jSONObject.getString("timezone");
                        int i4 = 0;
                        while (true) {
                            if (i4 < this.timezoneSpinnerAdapter.getCount()) {
                                Property item2 = this.timezoneSpinnerAdapter.getItem(i4);
                                if (item2 != null && item2.getName().equals(string2)) {
                                    this.timezoneSpinner.setSelection(i4);
                                    z = true;
                                    break;
                                }
                                i4++;
                            } else {
                                break;
                            }
                        }
                        if (!z) {
                            this.timezoneSpinner.setSelection(GlobalInfo.getInstance().getDefaultTimezoneIndex().intValue());
                        }
                    }
                    this.customerCodeEditText.setText(jSONObject.getString("customerCode"));
                    return;
                }
                this.datalogSnEditText.setText(parseActivityResult.getContents());
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void clickRegisterButton(View view) {
        Property property;
        String valueOf = String.valueOf(GlobalInfo.getDefaultClusterId());
        if (!Constants.CLUSTER_GROUP_INDIA.equals(GlobalInfo.getInstance().getCurrentClusterGroup()) && (property = (Property) this.clusterSpinner.getSelectedItem()) != null) {
            valueOf = property.getName();
        }
        String obj = this.usernameEditText.getText().toString();
        if (Tool.isEmpty(obj)) {
            Tool.alert(this, R.string.page_register_error_account_empty);
            this.usernameEditText.requestFocus();
            return;
        }
        if (obj.length() < 4) {
            Tool.alert(this, R.string.page_register_error_account_minLength);
            this.usernameEditText.requestFocus();
            return;
        }
        if (Tool.containInvalidCharacter(obj)) {
            Tool.alert(this, R.string.phrase_username_error_char_invalid);
            this.usernameEditText.requestFocus();
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
        String obj3 = this.realNameEditText.getText().toString();
        String obj4 = this.emailEditText.getText().toString();
        if (Tool.isEmpty(obj4)) {
            Tool.alert(this, R.string.page_register_error_email_empty);
            this.emailEditText.requestFocus();
            return;
        }
        if (!Tool.isEmail(obj4)) {
            Tool.alert(this, R.string.page_register_error_email_format);
            this.emailEditText.requestFocus();
            return;
        }
        String obj5 = this.telNumberEditText.getText().toString();
        String obj6 = this.plantNameEditText.getText().toString();
        if (Tool.isEmpty(obj6)) {
            Tool.alert(this, R.string.page_setting_plant_error_name_empty);
            this.plantNameEditText.requestFocus();
            return;
        }
        if (obj6.length() < 2) {
            Tool.alert(this, R.string.page_setting_plant_error_name_minLength);
            this.plantNameEditText.requestFocus();
            return;
        }
        String obj7 = this.addressEditText.getText().toString();
        String obj8 = this.customerCodeEditText.getText().toString();
        if (Tool.isEmpty(obj8)) {
            Tool.alert(this, R.string.page_register_error_customer_code_empty);
            this.customerCodeEditText.requestFocus();
            return;
        }
        String obj9 = this.datalogSnEditText.getText().toString();
        if (Tool.isEmpty(obj9)) {
            Tool.alert(this, R.string.page_register_error_datalogSn_empty);
            this.datalogSnEditText.requestFocus();
            return;
        }
        if (obj9.length() != 10) {
            Tool.alert(this, R.string.page_register_error_datalogSn_length);
            this.datalogSnEditText.requestFocus();
            return;
        }
        String obj10 = this.datalogCheckCodeEditText.getText().toString();
        if (Tool.isEmpty(obj10)) {
            Tool.alert(this, R.string.page_register_error_check_code_empty);
            this.datalogCheckCodeEditText.requestFocus();
            return;
        }
        if (obj10.length() != 5) {
            Tool.alert(this, R.string.page_register_error_check_code_length);
            this.datalogCheckCodeEditText.requestFocus();
            return;
        }
        HashMap hashMap = new HashMap();
        if (!Tool.isEmpty(Custom.TARGET_PLATFORM)) {
            hashMap.put("targetPlatform", Custom.TARGET_PLATFORM);
        }
        hashMap.put("targetClusterId", valueOf);
        hashMap.put("account", obj);
        hashMap.put("password", obj2);
        hashMap.put("realName", obj3);
        hashMap.put("email", obj4);
        hashMap.put("language", GlobalInfo.getInstance().getLanguageEnumName());
        hashMap.put("telNumber", obj5);
        hashMap.put(AppMeasurementSdk.ConditionalUserProperty.NAME, obj6);
        hashMap.put("daylightSavingTime", String.valueOf(this.daylightSavingTimeToggleButton.isChecked()));
        Property property2 = (Property) this.countrySpinner.getSelectedItem();
        if (property2 == null) {
            return;
        }
        hashMap.put("country", property2.getName());
        Property property3 = (Property) this.timezoneSpinner.getSelectedItem();
        if (property3 == null) {
            return;
        }
        hashMap.put("timezone", property3.getName());
        hashMap.put("address", obj7);
        hashMap.put("customerCode", obj8);
        hashMap.put("datalogSn", obj9);
        hashMap.put("checkCode", obj10);
        this.registerButton.setEnabled(false);
        new RegisterTask().execute(hashMap);
    }

    public void clickButton() {
        Intent intent = new Intent(instance, (Class<?>) LoginActivity.class);
        intent.putExtra("account", this.usernameEditText.getText().toString());
        intent.putExtra("password", this.passwordEditText.getText().toString());
        startActivity(intent);
        finish();
    }

    /* loaded from: classes2.dex */
    private static class RegisterTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private RegisterTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/register/viewer", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            char c;
            super.onPostExecute((RegisterTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (jSONObject.getBoolean("success")) {
                            Tool.alert(RegisterActivity.instance, R.string.page_register_success, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.register.RegisterActivity.RegisterTask.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    RegisterActivity.instance.clickButton();
                                }
                            });
                        } else {
                            String string = jSONObject.getString("msg");
                            switch (string.hashCode()) {
                                case -1944596357:
                                    if (string.equals("datalogNotRegistered")) {
                                        c = 3;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case -1266199319:
                                    if (string.equals("accountExists")) {
                                        c = 0;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case -549673078:
                                    if (string.equals("platformDismatch")) {
                                        c = 6;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case -261232953:
                                    if (string.equals("noCustomerCodeOwner")) {
                                        c = 5;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case -171736047:
                                    if (string.equals("datalogSnExists")) {
                                        c = 2;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 317971141:
                                    if (string.equals("noCustomerCodeRecordFound")) {
                                        c = 4;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 480508044:
                                    if (string.equals("customerCodeInvalid")) {
                                        c = 7;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 1109868007:
                                    if (string.equals("verifyDatalogError")) {
                                        c = 1;
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
                                    Tool.alert(RegisterActivity.instance, R.string.page_register_error_account_exists);
                                    break;
                                case 1:
                                    Tool.alert(RegisterActivity.instance, R.string.page_register_error_check_code_not_match);
                                    break;
                                case 2:
                                    Tool.alert(RegisterActivity.instance, R.string.page_register_error_datalogSn_exists);
                                    break;
                                case 3:
                                    Tool.alert(RegisterActivity.instance, R.string.page_register_error_datalogSn_not_registered);
                                    break;
                                case 4:
                                    Tool.alert(RegisterActivity.instance, R.string.page_register_error_no_customer_code_record_found);
                                    break;
                                case 5:
                                    Tool.alert(RegisterActivity.instance, R.string.page_register_error_no_customer_code_owner);
                                    break;
                                case 6:
                                    Tool.alert(RegisterActivity.instance, R.string.page_register_error_platform_dismatch);
                                    break;
                                case 7:
                                    Tool.alert(RegisterActivity.instance, R.string.page_register_error_customer_code_invalid);
                                    break;
                                default:
                                    Tool.alert(RegisterActivity.instance, R.string.phrase_toast_unknown_error);
                                    break;
                            }
                        }
                    } else {
                        Tool.alert(RegisterActivity.instance, R.string.phrase_toast_network_error);
                    }
                    if (RegisterActivity.instance == null || RegisterActivity.instance.isDestroyed()) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (RegisterActivity.instance == null || RegisterActivity.instance.isDestroyed()) {
                        return;
                    }
                }
                RegisterActivity.instance.registerButton.setEnabled(true);
            } catch (Throwable th) {
                if (RegisterActivity.instance != null && !RegisterActivity.instance.isDestroyed()) {
                    RegisterActivity.instance.registerButton.setEnabled(true);
                }
                throw th;
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class ReloadRegionSpinnerDataTask extends AsyncTask<String, Object, JSONArray> {
        private ReloadRegionSpinnerDataTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONArray doInBackground(String... strArr) {
            HashMap hashMap = new HashMap();
            hashMap.put("continent", String.valueOf(strArr[0]));
            hashMap.put("language", GlobalInfo.getInstance().getLanguage());
            System.out.println("LuxPower - param = " + hashMap);
            try {
                return HttpTool.postJsonArray(GlobalInfo.getInstance().getBaseUrl() + "locale/region", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONArray jSONArray) {
            Integer num;
            super.onPostExecute((ReloadRegionSpinnerDataTask) jSONArray);
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                num = null;
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        String string = jSONObject.getString("value");
                        arrayList.add(new Property(string, jSONObject.getString("text")));
                        if (Custom.DEFAULT_REGION.equals(string)) {
                            num = Integer.valueOf(i);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                num = null;
            }
            if (RegisterActivity.instance == null || RegisterActivity.instance.isDestroyed()) {
                return;
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(RegisterActivity.instance, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            RegisterActivity.instance.regionSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            if (RegisterActivity.scannedRegion == null) {
                if (num != null) {
                    RegisterActivity.instance.regionSpinner.setSelection(num.intValue());
                }
            } else {
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (((Property) arrayList.get(i2)).getName().equals(RegisterActivity.scannedRegion)) {
                        RegisterActivity.instance.regionSpinner.setSelection(i2);
                        String unused = RegisterActivity.scannedRegion = null;
                        return;
                    }
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class ReloadCountrySpinnerDataTask extends AsyncTask<String, Object, JSONArray> {
        private ReloadCountrySpinnerDataTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONArray doInBackground(String... strArr) {
            HashMap hashMap = new HashMap();
            hashMap.put("region", String.valueOf(strArr[0]));
            hashMap.put("language", GlobalInfo.getInstance().getLanguage());
            try {
                return HttpTool.postJsonArray(GlobalInfo.getInstance().getBaseUrl() + "locale/country", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONArray jSONArray) {
            Integer num;
            super.onPostExecute((ReloadCountrySpinnerDataTask) jSONArray);
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                num = null;
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        String string = jSONObject.getString("value");
                        arrayList.add(new Property(string, jSONObject.getString("text")));
                        if (Custom.DEFAULT_COUNTRY.equals(string)) {
                            num = Integer.valueOf(i);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                num = null;
            }
            if (RegisterActivity.instance == null || RegisterActivity.instance.isDestroyed()) {
                return;
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(RegisterActivity.instance, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            RegisterActivity.instance.countrySpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            if (RegisterActivity.scannedCountry == null) {
                if (num != null) {
                    RegisterActivity.instance.countrySpinner.setSelection(num.intValue());
                }
            } else {
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (((Property) arrayList.get(i2)).getName().equals(RegisterActivity.scannedCountry)) {
                        RegisterActivity.instance.countrySpinner.setSelection(i2);
                        String unused = RegisterActivity.scannedCountry = null;
                        return;
                    }
                }
            }
        }
    }
}
