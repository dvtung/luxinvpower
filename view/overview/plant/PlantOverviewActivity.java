package com.nfcx.luxinvpower.view.overview.plant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.cluster.Cluster;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.plant.Plant;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.tool.API;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Version;
import com.nfcx.luxinvpower.view.login.LoginActivity;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.overview.dongle.DongleManageAdapter;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.plant.AddPlantActivity;
import com.nfcx.luxinvpower.view.plant.PlantListActivity;
import com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity;
import com.nfcx.luxinvpower.view.userCenter.UserCenterActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PlantOverviewActivity extends Activity {
    public static int currentPage = 1;
    public static PlantOverviewActivity instance;
    private Spinner clusterSpinner;
    private ImageView datalogOverviewUserFavImg;
    private long mExitTime;
    private ConstraintLayout nextButton;
    private EditText pageEditText;
    private ListView plantListView;
    private EditText plantTitleEditText;
    private ConstraintLayout previousButton;
    private ImageView searchPlantImageView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EditText userVisitRecordEditText;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_plant_overview_list);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        if (!PLATFORM.LUX_POWER.equals(GlobalInfo.getInstance().getUserData().getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        ((ImageView) findViewById(R.id.userCenterImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlantOverviewActivity.this.startActivity(new Intent(view.getContext(), (Class<?>) NewUserCenterActivity.class));
            }
        });
        this.clusterSpinner = (Spinner) findViewById(R.id.clusterSpinner);
        if (Constants.CLUSTER_GROUP_FIRST.equals(GlobalInfo.getInstance().getCurrentClusterGroup())) {
            this.clusterSpinner.setVisibility(0);
            ArrayList arrayList = new ArrayList();
            Map<Long, Cluster> clusterMap = GlobalInfo.getInstance().getClusterMap();
            Iterator<Long> it = clusterMap.keySet().iterator();
            while (it.hasNext()) {
                long longValue = it.next().longValue();
                arrayList.add(new Property(String.valueOf(longValue), getString(R.string.phrase_cluster) + ": " + clusterMap.get(Long.valueOf(longValue)).getClusterName()));
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.custom_spinner_text_view, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            this.clusterSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            int clusterId = (int) GlobalInfo.getInstance().getUserData().getClusterId();
            this.clusterSpinner.setSelection((clusterId <= 0 || clusterId > arrayList.size()) ? 0 : clusterId - 1);
            this.clusterSpinner.setOnItemSelectedListener(new AnonymousClass2());
        }
        EditText editText = (EditText) findViewById(R.id.plant_overview_list_item_plantTitleEditText);
        this.plantTitleEditText = editText;
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 3 && (keyEvent == null || keyEvent.getKeyCode() != 66)) {
                    return false;
                }
                PlantOverviewActivity.this.m385x593025c7();
                return true;
            }
        });
        EditText editText2 = (EditText) findViewById(R.id.plant_overview_list_item_userVisitRecord);
        this.userVisitRecordEditText = editText2;
        editText2.setFocusable(false);
        this.userVisitRecordEditText.setClickable(true);
        this.userVisitRecordEditText.setEnabled(true);
        this.userVisitRecordEditText.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlantOverviewActivity.this.plantTitleEditText.setText(PlantOverviewActivity.this.userVisitRecordEditText.getText());
                PlantOverviewActivity.this.m385x593025c7();
            }
        });
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.plant_overview_list_swipe_refresh_layout);
        ListView listView = (ListView) findViewById(R.id.plant_overview_list_plantList);
        this.plantListView = listView;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(PlantOverviewActivity.this, (Class<?>) MainActivity.class);
                GlobalInfo.getInstance().getUserData().setCurrentPlant(GlobalInfo.getInstance().getUserData().getPlant(j));
                PlantOverviewActivity.this.startActivity(intent);
                PlantOverviewActivity.this.finish();
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.datalog_overview_userFavImg);
        this.datalogOverviewUserFavImg = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlantOverviewActivity.this.plantTitleEditText.setText("");
                PlantOverviewActivity.this.plantListView.setAdapter((ListAdapter) new DongleManageAdapter(PlantOverviewActivity.this, new JSONArray()));
                PlantOverviewActivity.this.userFavList();
            }
        });
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                PlantOverviewActivity.this.m385x593025c7();
            }
        });
        ImageView imageView2 = (ImageView) findViewById(R.id.searchPlantImageView);
        this.searchPlantImageView = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlantOverviewActivity.this.m385x593025c7();
            }
        });
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.plant_overview_previousButtonLayout);
        this.previousButton = constraintLayout;
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlantOverviewActivity.this.changePageEditTextByButton(-1);
            }
        });
        ConstraintLayout constraintLayout2 = (ConstraintLayout) findViewById(R.id.plant_overview_nextButtonLayout);
        this.nextButton = constraintLayout2;
        constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlantOverviewActivity.this.changePageEditTextByButton(1);
            }
        });
        EditText editText3 = (EditText) findViewById(R.id.plant_overview_pageEditText);
        this.pageEditText = editText3;
        editText3.setText(String.valueOf(currentPage));
        m385x593025c7();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 implements AdapterView.OnItemSelectedListener {
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        AnonymousClass2() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            long parseLong = Long.parseLong(((Property) PlantOverviewActivity.this.clusterSpinner.getSelectedItem()).getName());
            if (parseLong == GlobalInfo.getInstance().getUserData().getClusterId() || GlobalInfo.getInstance().getClusterMap().get(Long.valueOf(parseLong)) == null) {
                return;
            }
            PlantOverviewActivity.instance.swipeRefreshLayout.setRefreshing(true);
            PlantOverviewActivity.this.plantListView.setAdapter((ListAdapter) new PlantOverviewAdapter(PlantOverviewActivity.this, new JSONArray()));
            GlobalInfo.getInstance().getUserData().setClusterId(parseLong);
            SharedPreferences.Editor edit = PlantOverviewActivity.this.getSharedPreferences("userInfo", 0).edit();
            edit.putLong(LoginActivity.CLUSTER_ID, parseLong);
            edit.commit();
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity$2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PlantOverviewActivity.AnonymousClass2.this.m387x573122a2();
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onItemSelected$1$com-nfcx-luxinvpower-view-overview-plant-PlantOverviewActivity$2, reason: not valid java name */
        public /* synthetic */ void m387x573122a2() {
            final boolean z = false;
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("account", LoginActivity.usernameForLogin);
                hashMap.put("password", LoginActivity.passwordForLogin);
                hashMap.put("language", GlobalInfo.getInstance().getLanguage());
                hashMap.put("changeCluster", Boolean.TRUE.toString());
                JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/login", hashMap);
                if (postJson != null) {
                    if (postJson.getBoolean("success")) {
                        z = true;
                    }
                }
            } catch (Exception unused) {
            }
            PlantOverviewActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PlantOverviewActivity.AnonymousClass2.this.m386xed019a83(z);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onItemSelected$0$com-nfcx-luxinvpower-view-overview-plant-PlantOverviewActivity$2, reason: not valid java name */
        public /* synthetic */ void m386xed019a83(boolean z) {
            PlantOverviewActivity.instance.swipeRefreshLayout.setRefreshing(false);
            if (z) {
                PlantOverviewActivity.this.m385x593025c7();
                return;
            }
            Intent intent = new Intent(PlantOverviewActivity.this, (Class<?>) LoginActivity.class);
            intent.putExtra("fromLogout", true);
            PlantOverviewActivity.this.startActivity(intent);
            PlantOverviewActivity.this.finish();
            if (PlantListActivity.instance != null) {
                PlantListActivity.instance.finish();
            }
            if (MainActivity.instance != null) {
                MainActivity.instance.finish();
            }
            if (UserCenterActivity.instance != null) {
                UserCenterActivity.instance.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changePageEditTextByButton(int i) {
        int currentPage2 = getCurrentPage() + i;
        currentPage = currentPage2;
        if (currentPage2 < 1) {
            currentPage = 1;
        }
        this.pageEditText.setText(String.valueOf(currentPage));
        m385x593025c7();
    }

    private int getCurrentPage() {
        String obj = this.pageEditText.getText().toString();
        try {
            if (Tool.isEmpty(obj)) {
                return 1;
            }
            return Integer.parseInt(obj);
        } catch (Exception e) {
            Log.e(e.getMessage(), Version.TAG, e);
            return 1;
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        m385x593025c7();
        UserData userData = GlobalInfo.getInstance().getUserData();
        if (userData.getUserVisitRecord() != null) {
            instance.userVisitRecordEditText.setText(userData.getUserVisitRecord().getSerialNum());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: refreshPlantList, reason: merged with bridge method [inline-methods] */
    public void m385x593025c7() {
        HashMap hashMap = new HashMap();
        String obj = this.plantTitleEditText.getText().toString();
        if (!Tool.isEmpty(obj)) {
            hashMap.put("searchText", obj);
        }
        hashMap.put("page", String.valueOf(getCurrentPage()));
        hashMap.put("rows", "30");
        hashMap.put("language", GlobalInfo.getInstance().getLanguage());
        instance.swipeRefreshLayout.setRefreshing(true);
        this.plantListView.setAdapter((ListAdapter) new PlantOverviewAdapter(this, new JSONArray()));
        new RefreshPlantListTask().execute(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class RefreshPlantListTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private RefreshPlantListTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/plantOverview/list", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((RefreshPlantListTask) jSONObject);
            PlantOverviewActivity.instance.swipeRefreshLayout.setRefreshing(false);
            if (jSONObject != null) {
                try {
                    if (jSONObject.getBoolean("success")) {
                        JSONArray jSONArray = jSONObject.getJSONArray("rows");
                        PlantOverviewActivity.instance.plantListView.setAdapter((ListAdapter) new PlantOverviewAdapter(PlantOverviewActivity.instance, jSONArray));
                        UserData userData = GlobalInfo.getInstance().getUserData();
                        if (ROLE.VIEWER.equals(userData.getRole())) {
                            return;
                        }
                        userData.clearPlantsAndInverters();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            Plant plant = new Plant();
                            plant.setPlantId(jSONObject2.getLong("plantId"));
                            plant.setName(jSONObject2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                            plant.setTimezoneHourOffset(jSONObject2.getInt("timezoneHourOffset"));
                            plant.setTimezoneMinuteOffset(jSONObject2.getInt("timezoneMinuteOffset"));
                            userData.addPlant(plant);
                            ArrayList arrayList = new ArrayList();
                            JSONArray jSONArray2 = jSONObject2.getJSONArray("inverters");
                            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                Inverter inverterByJsonObj = Tool.getInverterByJsonObj(jSONArray2.getJSONObject(i2), plant);
                                arrayList.add(inverterByJsonObj);
                                userData.addInverter(inverterByJsonObj);
                            }
                            userData.put(plant.getPlantId(), arrayList);
                            if (jSONObject2.has("parallelGroups")) {
                                JSONArray jSONArray3 = jSONObject2.getJSONArray("parallelGroups");
                                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                                    JSONObject jSONObject3 = jSONArray3.getJSONObject(i3);
                                    plant.addParallelGroup(jSONObject3.getString("parallelGroup"), jSONObject3.getString("parallelFirstDeviceSn"));
                                }
                            }
                        }
                        return;
                    }
                } catch (JSONException e) {
                    Toast.makeText(PlantOverviewActivity.instance, R.string.phrase_toast_response_error, 1).show();
                    e.printStackTrace();
                    return;
                }
            }
            if (jSONObject == null) {
                Toast.makeText(PlantOverviewActivity.instance, R.string.phrase_toast_network_error, 1).show();
            } else if (jSONObject.getInt(API.MSG_CODE) == 102) {
                Toast.makeText(PlantOverviewActivity.instance, R.string.phrase_toast_unlogin_error, 1).show();
            }
        }
    }

    public void userFavList() {
        instance.swipeRefreshLayout.setRefreshing(true);
        this.plantListView.setAdapter((ListAdapter) new DongleManageAdapter(this, new JSONArray()));
        HashMap hashMap = new HashMap();
        hashMap.put("clientType", "APP");
        new userFavPlantListTask().execute(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class userFavPlantListTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private userFavPlantListTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/userFav/getUserFavPlantRecordList", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((userFavPlantListTask) jSONObject);
            PlantOverviewActivity.instance.swipeRefreshLayout.setRefreshing(false);
            try {
                if (jSONObject != null) {
                    PlantOverviewActivity.instance.plantListView.setAdapter((ListAdapter) new PlantOverviewAdapter(PlantOverviewActivity.instance, jSONObject.getJSONArray("rows")));
                } else {
                    Toast.makeText(PlantOverviewActivity.instance, R.string.phrase_toast_network_error, 1).show();
                }
            } catch (JSONException e) {
                Toast.makeText(PlantOverviewActivity.instance, R.string.phrase_toast_response_error, 1).show();
                e.printStackTrace();
            }
        }
    }

    public void clickAddPlantButton(View view) {
        startActivity(new Intent(this, (Class<?>) AddPlantActivity.class));
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (System.currentTimeMillis() - this.mExitTime > 2000) {
            Toast.makeText(this, R.string.phrase_toast_double_click_exit, 0).show();
            this.mExitTime = System.currentTimeMillis();
            return true;
        }
        finish();
        return true;
    }
}
