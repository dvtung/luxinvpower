package com.nfcx.luxinvpower.view.main.fragment.lv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.UserData;
import com.nfcx.luxinvpower.global.bean.event.EventRecord;
import com.nfcx.luxinvpower.global.bean.user.PLATFORM;
import com.nfcx.luxinvpower.global.bean.user.ROLE;
import com.nfcx.luxinvpower.tool.API;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.view.main.MainActivity;
import com.nfcx.luxinvpower.view.main.fragment.adapter.EventRecordAdapter;
import com.nfcx.luxinvpower.view.overview.plant.PlantOverviewActivity;
import com.nfcx.luxinvpower.view.plant.PlantListActivity;
import com.nfcx.luxinvpower.view.userCenter.NewUserCenterActivity;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Lv1EventFragment extends AbstractItemFragment {
    private ListView eventListView;
    private boolean inited;
    private SwipeRefreshLayout swipeRefreshLayout;

    public Lv1EventFragment() {
        super(3L);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_lv1_event, viewGroup, false);
        final UserData userData = GlobalInfo.getInstance().getUserData();
        if (!PLATFORM.LUX_POWER.equals(userData.getPlatform())) {
            inflate.findViewById(R.id.companyLogoImageView).setVisibility(4);
        }
        ((ConstraintLayout) inflate.findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1EventFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1EventFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) (ROLE.VIEWER.equals(userData.getRole()) ? PlantListActivity.class : PlantOverviewActivity.class)));
                MainActivity.instance.finish();
            }
        });
        ((ImageView) inflate.findViewById(R.id.userCenterImageView)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1EventFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Lv1EventFragment.this.startActivity(new Intent(view.getContext(), (Class<?>) NewUserCenterActivity.class));
            }
        });
        this.swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.fragment_event_swipe_refresh_layout);
        this.eventListView = (ListView) inflate.findViewById(R.id.fragment_event_eventList);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1EventFragment.3
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                Lv1EventFragment.this.refreshEventList();
            }
        });
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        System.out.println("LuxPower - EventFragment onResume...");
        if (this.inited) {
            return;
        }
        this.inited = true;
        refreshEventList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshEventList() {
        if (GlobalInfo.getInstance().getUserData().getCurrentPlant() != null) {
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1EventFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("plantId", String.valueOf(GlobalInfo.getInstance().getUserData().getCurrentPlant().getPlantId()));
                    hashMap.put("page", "1");
                    hashMap.put("rows", "20");
                    final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "api/analyze/event/list", hashMap);
                    final FragmentActivity activity = Lv1EventFragment.this.getActivity();
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.main.fragment.lv1.Lv1EventFragment.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Lv1EventFragment.this.swipeRefreshLayout.setRefreshing(false);
                                try {
                                    JSONObject jSONObject = postJson;
                                    if (jSONObject != null && jSONObject.getBoolean("success")) {
                                        JSONArray jSONArray = postJson.getJSONArray("rows");
                                        ArrayList arrayList = new ArrayList();
                                        for (int i = 0; i < jSONArray.length(); i++) {
                                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                            EventRecord eventRecord = new EventRecord();
                                            eventRecord.setRecordId(jSONObject2.getLong("recordId"));
                                            eventRecord.setPlantName(jSONObject2.getString("plantName"));
                                            eventRecord.setSerialNum(jSONObject2.getString("serialNum"));
                                            eventRecord.setEventTypeText(jSONObject2.getString("eventTypeText"));
                                            eventRecord.setEventText(jSONObject2.getString("eventText"));
                                            eventRecord.setStartTime(jSONObject2.getString("startSlashTime"));
                                            eventRecord.setRenormalTime(jSONObject2.getString("renormalSlashTime"));
                                            arrayList.add(eventRecord);
                                        }
                                        Lv1EventFragment.this.eventListView.setAdapter((ListAdapter) new EventRecordAdapter(arrayList));
                                        return;
                                    }
                                    JSONObject jSONObject3 = postJson;
                                    if (jSONObject3 == null) {
                                        Toast.makeText(activity, R.string.phrase_toast_network_error, 1).show();
                                    } else if (jSONObject3.getInt(API.MSG_CODE) == 102) {
                                        Toast.makeText(activity, R.string.phrase_toast_unlogin_error, 1).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(activity, R.string.phrase_toast_response_error, 1).show();
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }).start();
        }
    }
}
