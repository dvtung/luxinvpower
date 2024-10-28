package com.nfcx.luxinvpower.view.updateFirmware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.messaging.Constants;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Custom;
import com.nfcx.luxinvpower.view.updateFirmware.adapter.ChangelogAdapter;
import com.nfcx.luxinvpower.view.updateFirmware.item.ChangelogItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FirmwareChangeLogActivity extends Activity {
    List<ChangelogItem> changelogItems = new ArrayList();
    private RecyclerView changelogRecyclerView;
    private Spinner firmwareTypeSpinner;
    private Property selectedFileTypeProperty;
    private int selectedItemPosition;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_firmware_changelog_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        Intent intent = getIntent();
        if (intent != null) {
            this.selectedItemPosition = intent.getIntExtra("selectedItemPosition", 0);
        }
        ChangelogAdapter changelogAdapter = new ChangelogAdapter(this.changelogItems);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.firmware_changelogRecyclerView);
        this.changelogRecyclerView = recyclerView;
        recyclerView.setAdapter(changelogAdapter);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Constants.firmwareTypeProperties);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.firmware_firmwareTypeSpinner);
        this.firmwareTypeSpinner = spinner;
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        this.firmwareTypeSpinner.setSelection(this.selectedItemPosition);
        this.firmwareTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.updateFirmware.FirmwareChangeLogActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                FirmwareChangeLogActivity firmwareChangeLogActivity = FirmwareChangeLogActivity.this;
                firmwareChangeLogActivity.selectedFileTypeProperty = (Property) firmwareChangeLogActivity.firmwareTypeSpinner.getSelectedItem();
                FirmwareChangeLogActivity firmwareChangeLogActivity2 = FirmwareChangeLogActivity.this;
                firmwareChangeLogActivity2.loadFirmwareChangeLog(firmwareChangeLogActivity2.selectedFileTypeProperty.toString());
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                FirmwareChangeLogActivity.this.selectedFileTypeProperty = null;
            }
        });
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.updateFirmware.FirmwareChangeLogActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FirmwareChangeLogActivity.this.startActivity(new Intent(FirmwareChangeLogActivity.this, (Class<?>) DownloadFirmwareActivity.class));
                FirmwareChangeLogActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadFirmwareChangeLog(final String str) {
        this.changelogItems.clear();
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.FirmwareChangeLogActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FirmwareChangeLogActivity.this.m403xa1b21c2b(str);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$loadFirmwareChangeLog$0$com-nfcx-luxinvpower-view-updateFirmware-FirmwareChangeLogActivity, reason: not valid java name */
    public /* synthetic */ void m403xa1b21c2b(String str) {
        Runnable runnable;
        try {
            try {
                HashMap hashMap = new HashMap();
                System.out.println(FIRMWARE_DEVICE_TYPE.getEnumByText(str));
                hashMap.put("platform", Custom.APP_USER_PLATFORM);
                hashMap.put("firmwareDeviceType", FIRMWARE_DEVICE_TYPE.getEnumByText(str));
                JSONObject postJson = HttpTool.postJson("http://res.solarcloudsystem.com:8083/resource/findAllTypeInfo", hashMap);
                if (postJson != null && postJson.getInt("code") == 200) {
                    JSONArray jSONArray = postJson.getJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        ChangelogItem changelogItem = new ChangelogItem();
                        changelogItem.setFwCode(jSONObject.getString("fwCode"));
                        changelogItem.setCreateTime(jSONObject.getString("createTime").substring(0, 10));
                        String string = jSONObject.getString("description");
                        changelogItem.setDescription(!Tool.isEmpty(string) ? (string.trim() + "\n").replace("\n", "\n\n") : "");
                        this.changelogItems.add(changelogItem);
                    }
                }
                runnable = new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.FirmwareChangeLogActivity.3
                    @Override // java.lang.Runnable
                    public void run() {
                        FirmwareChangeLogActivity.this.changelogRecyclerView.setLayoutManager(new LinearLayoutManager(FirmwareChangeLogActivity.this.changelogRecyclerView.getContext()));
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
                runnable = new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.FirmwareChangeLogActivity.3
                    @Override // java.lang.Runnable
                    public void run() {
                        FirmwareChangeLogActivity.this.changelogRecyclerView.setLayoutManager(new LinearLayoutManager(FirmwareChangeLogActivity.this.changelogRecyclerView.getContext()));
                    }
                };
            }
            runOnUiThread(runnable);
        } catch (Throwable th) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.FirmwareChangeLogActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    FirmwareChangeLogActivity.this.changelogRecyclerView.setLayoutManager(new LinearLayoutManager(FirmwareChangeLogActivity.this.changelogRecyclerView.getContext()));
                }
            });
            throw th;
        }
    }
}
