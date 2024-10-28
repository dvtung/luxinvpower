package com.nfcx.luxinvpower.view.updateFirmware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.global.firmware.FIRMWARE_DEVICE_TYPE;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.LocalConnectTool;
import com.nfcx.luxinvpower.version.Version;
import com.nfcx.luxinvpower.view.updateFirmware.adapter.DownloadFirmwareAdapter;
import com.nfcx.luxinvpower.view.updateFirmware.bean.UpdateFileCache;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class DownloadFirmwareActivity extends Activity {
    public static DownloadFirmwareActivity instance;
    public static final Map<String, UpdateFileCache> updateFileCacheMap = new HashMap();
    private Button downloadFileButton;
    private File firmwareDir;
    private ListView firmwareListView;
    private final Map<FIRMWARE_DEVICE_TYPE, List<String>> firmwareTypeRecordIdsMap = new HashMap();
    private Spinner firmwareTypeSpinner;
    private Button go2ChangeLog;
    private Button go2UpdateButton;
    private Property selectedFileTypeProperty;
    private int selectedItemPosition;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_download_firmware);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        this.firmwareDir = getExternalFilesDir("firmware");
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DownloadFirmwareActivity.instance.finish();
            }
        });
        this.selectedFileTypeProperty = null;
        Spinner spinner = (Spinner) findViewById(R.id.download_firmware_firmwareTypeSpinner);
        this.firmwareTypeSpinner = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                DownloadFirmwareActivity downloadFirmwareActivity = DownloadFirmwareActivity.this;
                downloadFirmwareActivity.selectedFileTypeProperty = (Property) downloadFirmwareActivity.firmwareTypeSpinner.getSelectedItem();
                DownloadFirmwareActivity downloadFirmwareActivity2 = DownloadFirmwareActivity.this;
                downloadFirmwareActivity2.loadRemoteFilesByType(FIRMWARE_DEVICE_TYPE.valueOf(downloadFirmwareActivity2.selectedFileTypeProperty.getName()));
                DownloadFirmwareActivity.this.updateFirmwareTypeSelectChange();
                DownloadFirmwareActivity downloadFirmwareActivity3 = DownloadFirmwareActivity.this;
                downloadFirmwareActivity3.selectedItemPosition = downloadFirmwareActivity3.firmwareTypeSpinner.getSelectedItemPosition();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                DownloadFirmwareActivity.this.selectedFileTypeProperty = null;
                DownloadFirmwareActivity.this.updateFirmwareTypeSelectChange();
            }
        });
        ArrayAdapter arrayAdapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_item, Constants.firmwareTypeProperties);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.firmwareTypeSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        this.firmwareTypeSpinner.setSelection(0);
        this.go2ChangeLog = (Button) findViewById(R.id.download_firmware_change_log);
        Button button = (Button) findViewById(R.id.download_firmware_downloadFileButton);
        this.downloadFileButton = button;
        button.setEnabled(false);
        this.firmwareListView = (ListView) findViewById(R.id.download_firmware_firmwareListView);
        Button button2 = (Button) findViewById(R.id.download_firmware_go2UpdateButton);
        this.go2UpdateButton = button2;
        button2.setEnabled(false);
        for (UpdateFileCache updateFileCache : restoreUpdateFileCachesFromLocal(this.firmwareDir)) {
            System.out.println("LuxPower - localUpdateFileCache = " + updateFileCache);
            updateFileCacheMap.put(updateFileCache.getRecordId(), updateFileCache);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFirmwareTypeSelectChange() {
        m398x318e2c67();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0055 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.nfcx.luxinvpower.view.updateFirmware.bean.UpdateFileCache> restoreUpdateFileCachesFromLocal(java.io.File r8) {
        /*
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "LuxPower - "
            r1.<init>(r2)
            java.lang.String r2 = r8.getAbsolutePath()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.io.File[] r8 = r8.listFiles()
            int r1 = r8.length
            r2 = 0
        L23:
            if (r2 >= r1) goto L58
            r3 = r8[r2]
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L41
            r5.<init>(r3)     // Catch: java.lang.Exception -> L41
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch: java.lang.Exception -> L41
            r6.<init>(r5)     // Catch: java.lang.Exception -> L41
            java.lang.Object r7 = r6.readObject()     // Catch: java.lang.Exception -> L41
            com.nfcx.luxinvpower.view.updateFirmware.bean.UpdateFileCache r7 = (com.nfcx.luxinvpower.view.updateFirmware.bean.UpdateFileCache) r7     // Catch: java.lang.Exception -> L41
            r6.close()     // Catch: java.lang.Exception -> L3f
            r5.close()     // Catch: java.lang.Exception -> L3f
            goto L50
        L3f:
            r4 = move-exception
            goto L44
        L41:
            r5 = move-exception
            r7 = r4
            r4 = r5
        L44:
            java.lang.String r5 = "LuxPower"
            java.lang.String r6 = r4.getMessage()
            android.util.Log.e(r5, r6, r4)
            r3.delete()
        L50:
            if (r7 == 0) goto L55
            r0.add(r7)
        L55:
            int r2 = r2 + 1
            goto L23
        L58:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity.restoreUpdateFileCachesFromLocal(java.io.File):java.util.List");
    }

    private FIRMWARE_DEVICE_TYPE getCurrentFirmwareDeviceType() {
        Property property = this.selectedFileTypeProperty;
        if (property != null) {
            return FIRMWARE_DEVICE_TYPE.getEnumByName(property.getName());
        }
        return null;
    }

    private List<String> getCurrentRecordIds() {
        FIRMWARE_DEVICE_TYPE currentFirmwareDeviceType = getCurrentFirmwareDeviceType();
        if (currentFirmwareDeviceType != null) {
            return this.firmwareTypeRecordIdsMap.get(currentFirmwareDeviceType);
        }
        return null;
    }

    public void clickDownloadFileButton(View view) {
        List<String> currentRecordIds = getCurrentRecordIds();
        if (currentRecordIds == null || currentRecordIds.isEmpty()) {
            return;
        }
        this.downloadFileButton.setEnabled(false);
        for (int i = 0; i < currentRecordIds.size(); i++) {
            loadFirmwareDataPackage(currentRecordIds.get(i), 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadRemoteFilesByType(final FIRMWARE_DEVICE_TYPE firmware_device_type) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DownloadFirmwareActivity.this.m397x1dfcc918(firmware_device_type);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$loadRemoteFilesByType$0$com-nfcx-luxinvpower-view-updateFirmware-DownloadFirmwareActivity, reason: not valid java name */
    public /* synthetic */ void m397x1dfcc918(FIRMWARE_DEVICE_TYPE firmware_device_type) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("firmwareDeviceType", firmware_device_type.toString());
            JSONArray jSONArray = HttpTool.postJson(GlobalInfo.getInstance().getMajorUrl() + "web/maintain/appLocalUpdate/listForAppByType", hashMap).getJSONArray("rows");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("recordId");
                arrayList.add(string);
                Map<String, UpdateFileCache> map = updateFileCacheMap;
                UpdateFileCache updateFileCache = map.get(string);
                if (updateFileCache == null) {
                    updateFileCache = new UpdateFileCache();
                }
                if (!updateFileCache.isDoneDownload()) {
                    updateFileCache.setRecordId(string);
                    updateFileCache.setFileName(jSONObject.getString("fileName"));
                    updateFileCache.setStandard(jSONObject.getString("standard"));
                    updateFileCache.setV1(Integer.valueOf(jSONObject.getInt("v1")));
                    updateFileCache.setV2(Integer.valueOf(jSONObject.getInt("v2")));
                    updateFileCache.setV3(Integer.valueOf(jSONObject.getInt("v3")));
                }
                map.put(string, updateFileCache);
            }
            this.firmwareTypeRecordIdsMap.put(firmware_device_type, arrayList);
            refreshFirmwareListViewAtUiThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshFirmwareListViewAtUiThread() {
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DownloadFirmwareActivity.this.m398x318e2c67();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: refreshFirmwareListView, reason: merged with bridge method [inline-methods] */
    public void m398x318e2c67() {
        ArrayList arrayList = new ArrayList();
        List<String> currentRecordIds = getCurrentRecordIds();
        boolean z = false;
        if (currentRecordIds != null && !currentRecordIds.isEmpty()) {
            Iterator<String> it = currentRecordIds.iterator();
            while (it.hasNext()) {
                UpdateFileCache updateFileCache = updateFileCacheMap.get(it.next());
                if (updateFileCache != null) {
                    arrayList.add(updateFileCache);
                    if (!updateFileCache.isDoneDownload()) {
                        z = true;
                    }
                }
            }
        }
        this.firmwareListView.setAdapter((ListAdapter) new DownloadFirmwareAdapter(arrayList));
        if (z) {
            return;
        }
        this.downloadFileButton.setEnabled(true);
        this.go2UpdateButton.setEnabled(true);
    }

    private void loadFirmwareDataPackage(final String str, final int i) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DownloadFirmwareActivity.this.m396x6da7ccf9(str, i);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$loadFirmwareDataPackage$2$com-nfcx-luxinvpower-view-updateFirmware-DownloadFirmwareActivity, reason: not valid java name */
    public /* synthetic */ void m396x6da7ccf9(String str, int i) {
        try {
            String str2 = GlobalInfo.getInstance().getMajorUrl() + "web/maintain/appLocalUpdate/getUploadFileAnalyzeInfo";
            HashMap hashMap = new HashMap();
            hashMap.put("recordId", str);
            hashMap.put("startIndex", String.valueOf(i));
            JSONObject postJson = HttpTool.postJson(str2, hashMap);
            if (postJson != null && postJson.getBoolean("success")) {
                UpdateFileCache updateFileCache = updateFileCacheMap.get(str);
                if (updateFileCache == null) {
                    updateFileCache = new UpdateFileCache();
                }
                if (i == 1) {
                    updateFileCache.setRecordId(str);
                    updateFileCache.setFileName(postJson.getString("fileName"));
                    updateFileCache.setFileType(postJson.getInt("fileType"));
                    updateFileCache.setCrc32(postJson.getLong("crc32"));
                    JSONArray jSONArray = postJson.getJSONArray("physicalAddrData");
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        updateFileCache.getPhysicalAddr().put(Integer.valueOf(jSONObject.getInt(FirebaseAnalytics.Param.INDEX)), Integer.valueOf(jSONObject.getInt("physicalAddr")));
                    }
                    updateFileCache.setTailEncoded(postJson.getString("tailEncoded"));
                }
                JSONArray jSONArray2 = postJson.getJSONArray("firmwareData");
                for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                    updateFileCache.getFirmware().put(Integer.valueOf(jSONObject2.getInt(FirebaseAnalytics.Param.INDEX)), jSONObject2.getString(Constants.ScionAnalytics.MessageType.DATA_MESSAGE));
                }
                Map<String, UpdateFileCache> map = updateFileCacheMap;
                map.put(str, updateFileCache);
                if (postJson.getBoolean("hasNext")) {
                    loadFirmwareDataPackage(str, i + jSONArray2.length());
                    return;
                }
                updateFileCache.setDoneDownload(true);
                map.put(str, updateFileCache);
                File file = new File(this.firmwareDir.getAbsolutePath() + File.separator + str);
                if (file.exists()) {
                    file.delete();
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(updateFileCache);
                    objectOutputStream.close();
                    fileOutputStream.close();
                } catch (Exception e) {
                    Log.e(Version.TAG, e.getMessage(), e);
                }
                Looper.prepare();
                refreshFirmwareListViewAtUiThread();
                Looper.loop();
                return;
            }
            refreshFirmwareListViewAtUiThread();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void clickGo2UpdateButton(View view) {
        LocalConnectTool.go2LocalActivity(this);
    }

    public void clickGo2ChangeLog(View view) {
        Intent intent = new Intent(this, (Class<?>) FirmwareChangeLogActivity.class);
        intent.putExtra("selectedItemPosition", this.selectedItemPosition);
        startActivity(intent);
        finish();
    }
}
