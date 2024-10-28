package com.nfcx.luxinvpower.view.overview.inverter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.tool.API;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class InverterOverviewActivity extends Activity {
    public static InverterOverviewActivity instance;
    private ListView inverterListView;
    private long mExitTime;
    private SwipeRefreshLayout swipeRefreshLayout;

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshInverterList() {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_inverter_overview);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        if (!PLATFORM.LUX_POWER.equals(GlobalInfo.getInstance().getUserData().getPlatform())) {
            findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        ((ImageView) findViewById(R.id.userCenterImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.inverter.InverterOverviewActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InverterOverviewActivity.this.startActivity(new Intent(view.getContext(), (Class<?>) NewUserCenterActivity.class));
            }
        });
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.inverter_overview_swipe_refresh_layout);
        ListView listView = (ListView) findViewById(R.id.inverter_overview_inverterList);
        this.inverterListView = listView;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.nfcx.luxinvpower.view.overview.inverter.InverterOverviewActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(InverterOverviewActivity.this, (Class<?>) MainActivity.class);
                GlobalInfo.getInstance().getUserData().setCurrentPlant(GlobalInfo.getInstance().getUserData().getPlant(j));
                InverterOverviewActivity.this.startActivity(intent);
                InverterOverviewActivity.this.finish();
            }
        });
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.nfcx.luxinvpower.view.overview.inverter.InverterOverviewActivity.3
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                InverterOverviewActivity.this.refreshInverterList();
            }
        });
        refreshInverterList();
    }

    /* loaded from: classes2.dex */
    private static class RefreshInverterListTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private RefreshInverterListTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/inverterMonitor/list", mapArr[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((RefreshInverterListTask) jSONObject);
            InverterOverviewActivity.instance.swipeRefreshLayout.setRefreshing(false);
            if (jSONObject != null) {
                try {
                    if (jSONObject.getBoolean("success")) {
                        InverterOverviewActivity.instance.inverterListView.setAdapter((ListAdapter) new InverterOverviewAdapter(InverterOverviewActivity.instance, jSONObject.getJSONArray("rows")));
                    }
                } catch (JSONException e) {
                    Toast.makeText(InverterOverviewActivity.instance, R.string.phrase_toast_response_error, 1).show();
                    e.printStackTrace();
                    return;
                }
            }
            if (jSONObject == null) {
                Toast.makeText(InverterOverviewActivity.instance, R.string.phrase_toast_network_error, 1).show();
            } else if (jSONObject.getInt(API.MSG_CODE) == 102) {
                Toast.makeText(InverterOverviewActivity.instance, R.string.phrase_toast_unlogin_error, 1).show();
            }
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            startActivity(new Intent(this, (Class<?>) PlantOverviewActivity.class));
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }
}
