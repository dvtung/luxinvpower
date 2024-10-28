package com.nfcx.luxinvpower.view.plant;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Custom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class AddPlantActivity extends Activity {
    public static AddPlantActivity instance;
    private Button addPlantButton;
    private Spinner continentSpinner;
    private Spinner countrySpinner;
    private ToggleButton daylightSavingTimeToggleButton;
    private EditText plantNameEditText;
    private Spinner regionSpinner;
    private Spinner timezoneSpinner;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_plant);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.plant.AddPlantActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddPlantActivity.instance.finish();
            }
        });
        if (!PLATFORM.LUX_POWER.equals(GlobalInfo.getInstance().getUserData().getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        this.plantNameEditText = (EditText) findViewById(R.id.register_plantNameEditText);
        this.continentSpinner = (Spinner) findViewById(R.id.register_continentSpinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getContinents(this));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.continentSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        this.continentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.plant.AddPlantActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                new ReloadRegionSpinnerDataTask().execute(((Property) AddPlantActivity.this.continentSpinner.getSelectedItem()).getName());
            }
        });
        this.continentSpinner.setSelection(Custom.DEFAULT_CONTINENT.ordinal());
        Spinner spinner = (Spinner) findViewById(R.id.register_regionSpinner);
        this.regionSpinner = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.plant.AddPlantActivity.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                new ReloadCountrySpinnerDataTask().execute(((Property) AddPlantActivity.this.regionSpinner.getSelectedItem()).getName());
            }
        });
        this.countrySpinner = (Spinner) findViewById(R.id.register_countrySpinner);
        this.timezoneSpinner = (Spinner) findViewById(R.id.register_timezoneSpinner);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getTimezones());
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.timezoneSpinner.setAdapter((SpinnerAdapter) arrayAdapter2);
        this.timezoneSpinner.setSelection(GlobalInfo.getInstance().getDefaultTimezoneIndex().intValue());
        this.daylightSavingTimeToggleButton = (ToggleButton) findViewById(R.id.register_daylightSavingTimeToggleButton);
        this.addPlantButton = (Button) findViewById(R.id.register_addPlantButton);
    }

    public void clickAddPlantButton(View view) {
        String obj = this.plantNameEditText.getText().toString();
        if (Tool.isEmpty(obj)) {
            Tool.alert(this, R.string.page_setting_plant_error_name_empty);
            this.plantNameEditText.requestFocus();
            return;
        }
        if (obj.length() < 2) {
            Tool.alert(this, R.string.page_setting_plant_error_name_minLength);
            this.plantNameEditText.requestFocus();
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(AppMeasurementSdk.ConditionalUserProperty.NAME, obj);
        hashMap.put("daylightSavingTime", String.valueOf(this.daylightSavingTimeToggleButton.isChecked()));
        Property property = (Property) this.countrySpinner.getSelectedItem();
        if (property == null) {
            return;
        }
        hashMap.put("country", property.getName());
        Property property2 = (Property) this.timezoneSpinner.getSelectedItem();
        if (property2 == null) {
            return;
        }
        hashMap.put("timezone", property2.getName());
        this.addPlantButton.setEnabled(false);
        new AddPlantTask().execute(hashMap);
    }

    /* loaded from: classes2.dex */
    private static class AddPlantTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private AddPlantTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/config/plant/add", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((AddPlantTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (jSONObject.getBoolean("success")) {
                            Tool.alert(AddPlantActivity.instance, R.string.page_add_plant_success);
                        } else {
                            Tool.alert(AddPlantActivity.instance, R.string.phrase_toast_unknown_error);
                        }
                    } else {
                        Tool.alert(AddPlantActivity.instance, R.string.phrase_toast_network_error);
                    }
                    if (AddPlantActivity.instance == null || AddPlantActivity.instance.isDestroyed()) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (AddPlantActivity.instance == null || AddPlantActivity.instance.isDestroyed()) {
                        return;
                    }
                }
                AddPlantActivity.instance.addPlantButton.setEnabled(true);
            } catch (Throwable th) {
                if (AddPlantActivity.instance != null && !AddPlantActivity.instance.isDestroyed()) {
                    AddPlantActivity.instance.addPlantButton.setEnabled(true);
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
            super.onPostExecute((ReloadRegionSpinnerDataTask) jSONArray);
            ArrayList arrayList = new ArrayList();
            Integer num = null;
            if (jSONArray != null) {
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
            }
            if (AddPlantActivity.instance == null || AddPlantActivity.instance.isDestroyed()) {
                return;
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(AddPlantActivity.instance, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            AddPlantActivity.instance.regionSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            if (num != null) {
                AddPlantActivity.instance.regionSpinner.setSelection(num.intValue());
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
            super.onPostExecute((ReloadCountrySpinnerDataTask) jSONArray);
            ArrayList arrayList = new ArrayList();
            Integer num = null;
            if (jSONArray != null) {
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
            }
            if (AddPlantActivity.instance == null || AddPlantActivity.instance.isDestroyed()) {
                return;
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(AddPlantActivity.instance, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            AddPlantActivity.instance.countrySpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            if (num != null) {
                AddPlantActivity.instance.countrySpinner.setSelection(num.intValue());
            }
        }
    }
}
