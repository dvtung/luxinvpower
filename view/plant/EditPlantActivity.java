package com.nfcx.luxinvpower.view.plant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.overview.dongle.DongleManageActivity;
import com.nfcx.luxinvpower.view.plant.EditPlantActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EditPlantActivity extends Activity {
    public static EditPlantActivity instance;
    private Spinner continentSpinner;
    private Spinner countrySpinner;
    private long currentPlantId;
    private ToggleButton daylightSavingTimeToggleButton;
    private Button editPlantButton;
    private ConstraintLayout favoritePlantLayout;
    private ToggleButton favoritePlantToggleButton;
    private boolean firstTimeLoadCountryBySelect;
    private boolean firstTimeLoadRegionBySelect;
    private EditText plantNameEditText;
    private Spinner regionSpinner;
    private Button removeStationButton;
    private Spinner timezoneSpinner;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit_plant);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        this.firstTimeLoadRegionBySelect = true;
        this.firstTimeLoadCountryBySelect = true;
        this.currentPlantId = getIntent().getLongExtra("plantId", 0L);
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.plant.EditPlantActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditPlantActivity.instance.finish();
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
        this.regionSpinner = (Spinner) findViewById(R.id.register_regionSpinner);
        this.countrySpinner = (Spinner) findViewById(R.id.register_countrySpinner);
        this.timezoneSpinner = (Spinner) findViewById(R.id.register_timezoneSpinner);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, GlobalInfo.getInstance().getTimezones());
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.timezoneSpinner.setAdapter((SpinnerAdapter) arrayAdapter2);
        this.daylightSavingTimeToggleButton = (ToggleButton) findViewById(R.id.register_daylightSavingTimeToggleButton);
        this.editPlantButton = (Button) findViewById(R.id.register_editPlantButton);
        this.removeStationButton = (Button) findViewById(R.id.removeStationButton);
        this.favoritePlantLayout = (ConstraintLayout) findViewById(R.id.register_favoritePlantLayout);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.register_favoritePlantToggleButton);
        this.favoritePlantToggleButton = toggleButton;
        toggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.plant.EditPlantActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EditPlantActivity.this.favoritePlantToggleButton.isChecked()) {
                    EditPlantActivity.this.favoritePlantButton();
                } else {
                    EditPlantActivity.this.unFavoritePlantButton();
                }
            }
        });
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        new GetPlantInfoTask().execute(Long.valueOf(this.currentPlantId));
        if (GlobalInfo.getInstance().getUserData().getRole().equals(ROLE.VIEWER)) {
            this.favoritePlantLayout.setVisibility(8);
        }
        updateFavoritePlantButtons();
    }

    public void clickEditPlantButton(View view) {
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
        hashMap.put("plantId", String.valueOf(this.currentPlantId));
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
        this.editPlantButton.setEnabled(false);
        new EditPlantTask().execute(hashMap);
    }

    public void clickManagePlantButton(View view) {
        Intent intent = new Intent(this, (Class<?>) DongleManageActivity.class);
        intent.putExtra("stationName", this.plantNameEditText.getText().toString());
        intent.putExtra("plantId", this.currentPlantId);
        startActivity(intent);
    }

    public void favoritePlantButton() {
        HashMap hashMap = new HashMap();
        hashMap.put("plantId", String.valueOf(this.currentPlantId));
        new FavoritePlantTask().execute(hashMap);
    }

    public void unFavoritePlantButton() {
        HashMap hashMap = new HashMap();
        hashMap.put("plantId", String.valueOf(this.currentPlantId));
        new unFavoritePlantTask().execute(hashMap);
    }

    private void updateFavoritePlantButtons() {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.plant.EditPlantActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EditPlantActivity.this.m389x3e3086c6();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateFavoritePlantButtons$2$com-nfcx-luxinvpower-view-plant-EditPlantActivity, reason: not valid java name */
    public /* synthetic */ void m389x3e3086c6() {
        HashMap hashMap = new HashMap();
        hashMap.put("plantId", String.valueOf(this.currentPlantId));
        JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/userFav/getUserFavPlantRecord", hashMap);
        if (postJson != null) {
            try {
                if (postJson.getBoolean("success")) {
                    final boolean z = postJson.getBoolean(NotificationCompat.CATEGORY_STATUS);
                    runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.plant.EditPlantActivity$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            EditPlantActivity.this.m388xfaa56905(z);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateFavoritePlantButtons$1$com-nfcx-luxinvpower-view-plant-EditPlantActivity, reason: not valid java name */
    public /* synthetic */ void m388xfaa56905(boolean z) {
        this.favoritePlantToggleButton.setChecked(z);
    }

    /* loaded from: classes2.dex */
    private static class GetPlantInfoTask extends AsyncTask<Long, Object, JSONObject> {
        private GetPlantInfoTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Long... lArr) {
            HashMap hashMap = new HashMap();
            hashMap.put("plantId", String.valueOf(lArr[0]));
            hashMap.put("language", GlobalInfo.getInstance().getLanguage());
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/plant/getPlantInfo", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((GetPlantInfoTask) jSONObject);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (jSONObject != null) {
                try {
                    EditPlantActivity.instance.plantNameEditText.setText(jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                    String string = jSONObject.getString("continent");
                    List<Property> continents = GlobalInfo.getInstance().getContinents(EditPlantActivity.instance);
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= continents.size()) {
                            i2 = 0;
                            break;
                        } else if (continents.get(i2).getName().equals(string)) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    EditPlantActivity.instance.continentSpinner.setSelection(i2);
                    EditPlantActivity.instance.continentSpinner.invalidate();
                    JSONArray jSONArray = jSONObject.getJSONArray("regions");
                    for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                        arrayList.add(new Property(jSONObject2.getString("value"), jSONObject2.getString("text")));
                    }
                    JSONArray jSONArray2 = jSONObject.getJSONArray("countrys");
                    for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                        JSONObject jSONObject3 = jSONArray2.getJSONObject(i4);
                        arrayList2.add(new Property(jSONObject3.getString("value"), jSONObject3.getString("text")));
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(EditPlantActivity.instance, android.R.layout.simple_spinner_item, arrayList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    EditPlantActivity.instance.regionSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
                    int i5 = jSONObject.getInt("currentRegionIndex");
                    Spinner spinner = EditPlantActivity.instance.regionSpinner;
                    if (i5 <= 0) {
                        i5 = 0;
                    }
                    spinner.setSelection(i5);
                    EditPlantActivity.instance.regionSpinner.invalidate();
                    ArrayAdapter arrayAdapter2 = new ArrayAdapter(EditPlantActivity.instance, android.R.layout.simple_spinner_item, arrayList2);
                    arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    EditPlantActivity.instance.countrySpinner.setAdapter((SpinnerAdapter) arrayAdapter2);
                    int i6 = jSONObject.getInt("currentCountryIndex");
                    Spinner spinner2 = EditPlantActivity.instance.countrySpinner;
                    if (i6 <= 0) {
                        i6 = 0;
                    }
                    spinner2.setSelection(i6);
                    EditPlantActivity.instance.countrySpinner.invalidate();
                    String string2 = jSONObject.getString("timezone");
                    List<Property> timezones = GlobalInfo.getInstance().getTimezones();
                    int i7 = 0;
                    while (true) {
                        if (i7 >= timezones.size()) {
                            break;
                        }
                        if (timezones.get(i7).getName().equals(string2)) {
                            i = i7;
                            break;
                        }
                        i7++;
                    }
                    EditPlantActivity.instance.timezoneSpinner.setSelection(i);
                    EditPlantActivity.instance.timezoneSpinner.invalidate();
                    EditPlantActivity.instance.daylightSavingTimeToggleButton.setChecked(jSONObject.getBoolean("daylightSavingTime"));
                    EditPlantActivity.instance.continentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.plant.EditPlantActivity.GetPlantInfoTask.1
                        @Override // android.widget.AdapterView.OnItemSelectedListener
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }

                        @Override // android.widget.AdapterView.OnItemSelectedListener
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i8, long j) {
                            if (!EditPlantActivity.instance.firstTimeLoadRegionBySelect) {
                                new ReloadRegionSpinnerDataTask().execute(((Property) EditPlantActivity.instance.continentSpinner.getSelectedItem()).getName());
                            } else {
                                EditPlantActivity.instance.firstTimeLoadRegionBySelect = false;
                            }
                        }
                    });
                    EditPlantActivity.instance.regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.plant.EditPlantActivity.GetPlantInfoTask.2
                        @Override // android.widget.AdapterView.OnItemSelectedListener
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }

                        @Override // android.widget.AdapterView.OnItemSelectedListener
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i8, long j) {
                            if (!EditPlantActivity.instance.firstTimeLoadCountryBySelect) {
                                new ReloadCountrySpinnerDataTask().execute(((Property) EditPlantActivity.instance.regionSpinner.getSelectedItem()).getName());
                            } else {
                                EditPlantActivity.instance.firstTimeLoadCountryBySelect = false;
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FavoritePlantTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private FavoritePlantTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/userFav/saveUserFavPlantRecord", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((FavoritePlantTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (jSONObject.getBoolean("success")) {
                            Tool.alert(EditPlantActivity.instance, R.string.page_favorite_plant_success);
                        } else {
                            Tool.alert(EditPlantActivity.instance, R.string.phrase_toast_unknown_error);
                        }
                    } else {
                        Tool.alert(EditPlantActivity.instance, R.string.phrase_toast_network_error);
                    }
                    if (EditPlantActivity.instance == null) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (EditPlantActivity.instance == null) {
                        return;
                    }
                }
                EditPlantActivity.instance.isDestroyed();
            } catch (Throwable th) {
                if (EditPlantActivity.instance != null) {
                    EditPlantActivity.instance.isDestroyed();
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class unFavoritePlantTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private unFavoritePlantTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/userFav/removeUserFavPlantRecord", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((unFavoritePlantTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (jSONObject.getBoolean("success")) {
                            Tool.alert(EditPlantActivity.instance, R.string.page_unfavorite_plant_success);
                        } else {
                            Tool.alert(EditPlantActivity.instance, R.string.phrase_toast_unknown_error);
                        }
                    } else {
                        Tool.alert(EditPlantActivity.instance, R.string.phrase_toast_network_error);
                    }
                    if (EditPlantActivity.instance == null) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (EditPlantActivity.instance == null) {
                        return;
                    }
                }
                EditPlantActivity.instance.isDestroyed();
            } catch (Throwable th) {
                if (EditPlantActivity.instance != null) {
                    EditPlantActivity.instance.isDestroyed();
                }
                throw th;
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class EditPlantTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private EditPlantTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/config/plant/edit", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((EditPlantTask) jSONObject);
            try {
                try {
                    if (jSONObject != null) {
                        if (jSONObject.getBoolean("success")) {
                            Tool.alert(EditPlantActivity.instance, R.string.page_edit_plant_success);
                        } else {
                            Tool.alert(EditPlantActivity.instance, R.string.phrase_toast_unknown_error);
                        }
                    } else {
                        Tool.alert(EditPlantActivity.instance, R.string.phrase_toast_network_error);
                    }
                    if (EditPlantActivity.instance == null || EditPlantActivity.instance.isDestroyed()) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (EditPlantActivity.instance == null || EditPlantActivity.instance.isDestroyed()) {
                        return;
                    }
                }
                EditPlantActivity.instance.editPlantButton.setEnabled(true);
            } catch (Throwable th) {
                if (EditPlantActivity.instance != null && !EditPlantActivity.instance.isDestroyed()) {
                    EditPlantActivity.instance.editPlantButton.setEnabled(true);
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
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        arrayList.add(new Property(jSONObject.getString("value"), jSONObject.getString("text")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (EditPlantActivity.instance == null || EditPlantActivity.instance.isDestroyed()) {
                return;
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(EditPlantActivity.instance, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            EditPlantActivity.instance.regionSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
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
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        arrayList.add(new Property(jSONObject.getString("value"), jSONObject.getString("text")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (EditPlantActivity.instance == null || EditPlantActivity.instance.isDestroyed()) {
                return;
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(EditPlantActivity.instance, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            EditPlantActivity.instance.countrySpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        }
    }

    public void clickRemoveStationButton(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.phrase_remove_station).setIcon(android.R.drawable.ic_dialog_info).setMessage(R.string.phrase_remove_station_text).setPositiveButton(R.string.phrase_button_ok, new AnonymousClass2()).setNegativeButton(R.string.phrase_button_cancel, (DialogInterface.OnClickListener) null);
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.plant.EditPlantActivity$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 implements DialogInterface.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            EditPlantActivity.this.removeStationButton.setEnabled(false);
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.plant.EditPlantActivity$2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    EditPlantActivity.AnonymousClass2.this.m391x1536dca7();
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$1$com-nfcx-luxinvpower-view-plant-EditPlantActivity$2, reason: not valid java name */
        public /* synthetic */ void m391x1536dca7() {
            HashMap hashMap = new HashMap();
            hashMap.put("plantId", String.valueOf(EditPlantActivity.this.currentPlantId));
            final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/config/plant/remove", hashMap);
            EditPlantActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.plant.EditPlantActivity$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EditPlantActivity.AnonymousClass2.this.m390x87fc2b26(postJson);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-nfcx-luxinvpower-view-plant-EditPlantActivity$2, reason: not valid java name */
        public /* synthetic */ void m390x87fc2b26(JSONObject jSONObject) {
            try {
                if (jSONObject != null) {
                    try {
                    } catch (Exception e) {
                        Tool.alert(EditPlantActivity.this, R.string.phrase_toast_response_error);
                        e.printStackTrace();
                        if (EditPlantActivity.instance == null || EditPlantActivity.instance.isDestroyed()) {
                            return;
                        }
                    }
                    if (jSONObject.getBoolean("success")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditPlantActivity.this);
                        builder.setTitle(R.string.phrase_message).setIcon(android.R.drawable.ic_dialog_info).setMessage(R.string.phrase_remove_success).setNegativeButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.plant.EditPlantActivity.2.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditPlantActivity.instance.finish();
                            }
                        });
                        builder.show();
                        if (EditPlantActivity.instance != null || EditPlantActivity.instance.isDestroyed()) {
                        }
                        EditPlantActivity.instance.removeStationButton.setEnabled(true);
                        return;
                    }
                }
                if ("stillHasDatalog".equals(jSONObject.getString("msg"))) {
                    Tool.alert(EditPlantActivity.this, R.string.page_setting_plant_error_still_has_datalog);
                } else {
                    Tool.alert(EditPlantActivity.this, R.string.phrase_toast_unknown_error);
                }
                if (EditPlantActivity.instance != null) {
                }
            } catch (Throwable th) {
                if (EditPlantActivity.instance != null && !EditPlantActivity.instance.isDestroyed()) {
                    EditPlantActivity.instance.removeStationButton.setEnabled(true);
                }
                throw th;
            }
        }
    }
}
