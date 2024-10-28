package com.nfcx.luxinvpower.view.overview.dongle;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Version;
import com.nfcx.luxinvpower.view.plant.AddDatalogActivity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class DongleManageActivity extends Activity {
    public static int currentPage = 1;
    public static DongleManageActivity instance;
    private ListView datalogListView;
    private ConstraintLayout nextButton;
    private EditText pageEditText;
    private long plantId;
    private ConstraintLayout previousButton;
    private ImageView searchDongleImageView;
    private EditText searchEditText;
    private String stationName;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_datalog_manage_overview_list);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        Intent intent = getIntent();
        if (intent != null) {
            this.stationName = intent.getStringExtra("stationName");
            this.plantId = intent.getLongExtra("plantId", -1L);
        }
        ((TextView) findViewById(R.id.stationNameText)).setText(this.stationName);
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DongleManageActivity.instance.finish();
            }
        });
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.datalog_manage_list_swipe_refresh_layout);
        ImageView imageView = (ImageView) findViewById(R.id.searchDongleImageView);
        this.searchDongleImageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DongleManageActivity.instance.swipeRefreshLayout.setRefreshing(true);
                DongleManageActivity.this.datalogListView.setAdapter((ListAdapter) new DongleManageAdapter(DongleManageActivity.this, new JSONArray()));
                DongleManageActivity.this.m376x81717856();
            }
        });
        this.datalogListView = (ListView) findViewById(R.id.datalog_manage_list_plantList);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageActivity$$ExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                DongleManageActivity.this.m376x81717856();
            }
        });
        this.searchEditText = (EditText) findViewById(R.id.datalog_overview_list_item_searchEditText);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.datalog_overview_previousButtonLayout);
        this.previousButton = constraintLayout;
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DongleManageActivity.this.changePageEditTextByButton(-1);
            }
        });
        ConstraintLayout constraintLayout2 = (ConstraintLayout) findViewById(R.id.datalog_overview_nextButtonLayout);
        this.nextButton = constraintLayout2;
        constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DongleManageActivity.this.changePageEditTextByButton(1);
            }
        });
        EditText editText = (EditText) findViewById(R.id.datalog_overview_pageEditText);
        this.pageEditText = editText;
        editText.setText(String.valueOf(currentPage));
        m376x81717856();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changePageEditTextByButton(int i) {
        int currentPage2 = getCurrentPage() + i;
        currentPage = currentPage2;
        if (currentPage2 < 1) {
            currentPage = 1;
        }
        this.pageEditText.setText(String.valueOf(currentPage));
        m376x81717856();
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

    /* renamed from: refreshDongleList, reason: merged with bridge method [inline-methods] */
    public void m376x81717856() {
        HashMap hashMap = new HashMap();
        String obj = this.searchEditText.getText().toString();
        if (!Tool.isEmpty(obj)) {
            hashMap.put("searchType", "serialNum");
            hashMap.put("searchText", obj);
        }
        hashMap.put("plantId", String.valueOf(this.plantId));
        hashMap.put("page", String.valueOf(getCurrentPage()));
        hashMap.put("rows", "10");
        instance.swipeRefreshLayout.setRefreshing(true);
        this.datalogListView.setAdapter((ListAdapter) new DongleManageAdapter(this, new JSONArray()));
        new RefreshDongleListTask().execute(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class RefreshDongleListTask extends AsyncTask<Map<String, String>, Object, JSONObject> {
        private RefreshDongleListTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Map<String, String>[] mapArr) {
            try {
                return HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/config/datalog/list", null);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute((RefreshDongleListTask) jSONObject);
            DongleManageActivity.instance.swipeRefreshLayout.setRefreshing(false);
            try {
                if (jSONObject != null) {
                    DongleManageActivity.instance.datalogListView.setAdapter((ListAdapter) new DongleManageAdapter(DongleManageActivity.instance, jSONObject.getJSONArray("rows")));
                } else {
                    Toast.makeText(DongleManageActivity.instance, R.string.phrase_toast_network_error, 1).show();
                }
            } catch (JSONException e) {
                Toast.makeText(DongleManageActivity.instance, R.string.phrase_toast_response_error, 1).show();
                e.printStackTrace();
            }
        }
    }

    public void clickAddDongleButton(View view) {
        startActivity(new Intent(this, (Class<?>) AddDatalogActivity.class));
    }
}
