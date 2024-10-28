package com.nfcx.luxinvpower.view.plant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.global.bean.plant.Plant;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.tool.API;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PlantListActivity extends Activity {
    public static PlantListActivity instance;
    private long mExitTime;
    private ListView plantListView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_plant_list);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        if (!PLATFORM.LUX_POWER.equals(GlobalInfo.getInstance().getUserData().getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        ((ImageView) findViewById(R.id.userCenterImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.plant.PlantListActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlantListActivity.this.m392x5ef58eac(view);
            }
        });
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.plant_list_swipe_refresh_layout);
        ListView listView = (ListView) findViewById(R.id.plant_list_plantList);
        this.plantListView = listView;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.nfcx.luxinvpower.view.plant.PlantListActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(PlantListActivity.this, (Class<?>) MainActivity.class);
                GlobalInfo.getInstance().getUserData().setCurrentPlant(GlobalInfo.getInstance().getUserData().getPlant(j));
                PlantListActivity.this.startActivity(intent);
                PlantListActivity.this.finish();
            }
        });
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.nfcx.luxinvpower.view.plant.PlantListActivity$$ExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                PlantListActivity.this.m393xa280ac6d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-nfcx-luxinvpower-view-plant-PlantListActivity, reason: not valid java name */
    public /* synthetic */ void m392x5ef58eac(View view) {
        startActivity(new Intent(view.getContext(), (Class<?>) NewUserCenterActivity.class));
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        m393xa280ac6d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: refreshPlantList, reason: merged with bridge method [inline-methods] */
    public void m393xa280ac6d() {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.plant.PlantListActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PlantListActivity.this.m395x5349d4c2();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$refreshPlantList$3$com-nfcx-luxinvpower-view-plant-PlantListActivity, reason: not valid java name */
    public /* synthetic */ void m395x5349d4c2() {
        HashMap hashMap = new HashMap();
        hashMap.put("showPlantImage", String.valueOf(true));
        hashMap.put("showParallelGroups", String.valueOf(true));
        final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/plant/getPlantList", hashMap);
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.plant.PlantListActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                PlantListActivity.this.m394xfbeb701(postJson);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$refreshPlantList$2$com-nfcx-luxinvpower-view-plant-PlantListActivity, reason: not valid java name */
    public /* synthetic */ void m394xfbeb701(JSONObject jSONObject) {
        this.swipeRefreshLayout.setRefreshing(false);
        if (jSONObject != null) {
            try {
                if (jSONObject.getBoolean("success")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("rows");
                    this.plantListView.setAdapter((ListAdapter) new PlantListAdapter(this, jSONArray));
                    UserData userData = GlobalInfo.getInstance().getUserData();
                    if (ROLE.VIEWER.equals(userData.getRole())) {
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
                            System.out.println("LuxPower - Plant List inverters = " + arrayList.size());
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
                    return;
                }
            } catch (JSONException e) {
                Toast.makeText(this, R.string.phrase_toast_response_error, 1).show();
                e.printStackTrace();
                return;
            }
        }
        if (jSONObject == null) {
            Toast.makeText(this, R.string.phrase_toast_network_error, 1).show();
            return;
        }
        if (jSONObject.getInt(API.MSG_CODE) == 110) {
            this.plantListView.setAdapter((ListAdapter) new PlantListAdapter(this, new JSONArray()));
            Toast.makeText(this, R.string.plant_toast_no_plant, 1).show();
        } else if (jSONObject.getInt(API.MSG_CODE) == 102) {
            Toast.makeText(this, R.string.phrase_toast_unlogin_error, 1).show();
        }
    }

    public void clickAddPlantButton(View view) {
        if (GlobalInfo.getInstance().isInited()) {
            startActivity(new Intent(this, (Class<?>) AddPlantActivity.class));
        } else {
            Toast.makeText(this, R.string.phrase_please_wait_seconds, 1).show();
            GlobalInfo.getInstance().isIniting();
        }
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
