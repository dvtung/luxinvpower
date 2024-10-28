package com.nfcx.luxinvpower.view.overview.dongle;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Version;
import com.nfcx.luxinvpower.view.overview.dongle.DongleManageAdapter;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class DongleManageAdapter extends BaseAdapter {
    private Context context;
    private JSONArray datalogArray;
    private LayoutInflater inflater;

    public DongleManageAdapter(Context context, JSONArray jSONArray) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.datalogArray = jSONArray;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.datalogArray.length();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        try {
            return this.datalogArray.getJSONObject(i);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        try {
            JSONObject jSONObject = (JSONObject) getItem(i);
            if (jSONObject != null) {
                return jSONObject.getLong("datalogSn");
            }
            return -1L;
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.inflater.inflate(R.layout.activity_datalog_manage_list_item, (ViewGroup) null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        JSONObject jSONObject = (JSONObject) getItem(i);
        try {
            viewHolder.datalogSnTextView.setText(jSONObject.getString("datalogSn"));
            viewHolder.datalogTypeTextView.setText(jSONObject.getString("datalogTypeText"));
            if (jSONObject.getBoolean("lost")) {
                viewHolder.datalogStatusTextView.setTextColor(this.context.getResources().getColor(R.color.mainGray));
                viewHolder.datalogStatusTextView.setText("Offline");
            } else {
                viewHolder.datalogStatusTextView.setTextColor(this.context.getResources().getColor(R.color.colorNormal));
                viewHolder.datalogStatusTextView.setText("Online");
            }
            viewHolder.lastUpdateTimeTextView.setText(jSONObject.getString("lastUpdateTime"));
            viewHolder.refreshDatalogButton.setOnClickListener(new refreshInverterListener(jSONObject.getString("datalogSn")));
            viewHolder.removeDatalogButton.setOnClickListener(new removeListener(jSONObject.getString("datalogSn")));
        } catch (Exception e) {
            Log.e(Version.TAG, e.getMessage(), e);
        }
        return view;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        TextView datalogSnTextView;
        TextView datalogStatusTextView;
        TextView datalogTypeTextView;
        TextView lastUpdateTimeTextView;
        Button refreshDatalogButton;
        Button removeDatalogButton;

        public ViewHolder(View view) {
            this.datalogSnTextView = (TextView) view.findViewById(R.id.datalog_manage_list_item_datalogSn);
            this.datalogStatusTextView = (TextView) view.findViewById(R.id.datalog_manage_list_item_plantStatus);
            this.lastUpdateTimeTextView = (TextView) view.findViewById(R.id.datalog_manage_list_item_lastUpdateTime);
            this.datalogTypeTextView = (TextView) view.findViewById(R.id.datalog_manage_list_item_datalogType);
            this.refreshDatalogButton = (Button) view.findViewById(R.id.datalog_manage_list_item_refresh);
            this.removeDatalogButton = (Button) view.findViewById(R.id.datalog_manage_list_item_remove);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class refreshInverterListener implements View.OnClickListener {
        private String datalogSn;

        public refreshInverterListener(String str) {
            this.datalogSn = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageAdapter$refreshInverterListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DongleManageAdapter.refreshInverterListener.this.m379x4870aaab();
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$2$com-nfcx-luxinvpower-view-overview-dongle-DongleManageAdapter$refreshInverterListener, reason: not valid java name */
        public /* synthetic */ void m379x4870aaab() {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("serialNum", String.valueOf(this.datalogSn));
                JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/config/datalog/readInvInfo", hashMap);
                if (postJson != null) {
                    final String string = postJson.getString("msg");
                    if (Tool.isEmpty(string)) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageAdapter$refreshInverterListener$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                DongleManageAdapter.refreshInverterListener.this.m377x6b628829();
                            }
                        });
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageAdapter$refreshInverterListener$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            DongleManageAdapter.refreshInverterListener.this.m378xd9e9996a(string);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-nfcx-luxinvpower-view-overview-dongle-DongleManageAdapter$refreshInverterListener, reason: not valid java name */
        public /* synthetic */ void m377x6b628829() {
            Toast.makeText(DongleManageAdapter.this.context, "Finished", 1).show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$1$com-nfcx-luxinvpower-view-overview-dongle-DongleManageAdapter$refreshInverterListener, reason: not valid java name */
        public /* synthetic */ void m378xd9e9996a(String str) {
            Toast.makeText(DongleManageAdapter.this.context, str, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class removeListener implements View.OnClickListener {
        private String datalogSn;

        public removeListener(String str) {
            this.datalogSn = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Tool.alert(DongleManageAdapter.this.context, DongleManageAdapter.this.context.getString(R.string.phrase_remove_dongle_text, this.datalogSn), new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageAdapter.removeListener.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    removeListener.this.removeDongle();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeDongle() {
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageAdapter$removeListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DongleManageAdapter.removeListener.this.m382x72b795cd();
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$removeDongle$2$com-nfcx-luxinvpower-view-overview-dongle-DongleManageAdapter$removeListener, reason: not valid java name */
        public /* synthetic */ void m382x72b795cd() {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("serialNum", String.valueOf(this.datalogSn));
                JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getBaseUrl() + "web/config/datalog/remove", hashMap);
                if (postJson != null) {
                    if (postJson.getBoolean("success")) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageAdapter$removeListener$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                DongleManageAdapter.removeListener.this.m380x40b4f28f();
                            }
                        });
                    } else {
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.nfcx.luxinvpower.view.overview.dongle.DongleManageAdapter$removeListener$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                DongleManageAdapter.removeListener.this.m381x59b6442e();
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$removeDongle$0$com-nfcx-luxinvpower-view-overview-dongle-DongleManageAdapter$removeListener, reason: not valid java name */
        public /* synthetic */ void m380x40b4f28f() {
            ((DongleManageActivity) DongleManageAdapter.this.context).m376x81717856();
            Toast.makeText(DongleManageAdapter.this.context, "success", 0).show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$removeDongle$1$com-nfcx-luxinvpower-view-overview-dongle-DongleManageAdapter$removeListener, reason: not valid java name */
        public /* synthetic */ void m381x59b6442e() {
            Toast.makeText(DongleManageAdapter.this.context, "fail", 0).show();
        }
    }
}
