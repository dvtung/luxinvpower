package com.nfcx.luxinvpower.view.updateFirmware;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.GlobalInfo;
import com.nfcx.luxinvpower.global.bean.property.Property;
import com.nfcx.luxinvpower.tool.HttpTool;
import com.nfcx.luxinvpower.tool.LocalConnectTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.version.Version;
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
public class DownloadFirmwareActivity_Backup extends Activity {
    public static DownloadFirmwareActivity_Backup instance;
    private UpdateFileCache currentFileCacheUpdate;
    private Button downloadFileButton;
    private Spinner fileSpinner;
    private File firmwareDir;
    private Button go2UpdateButton;
    private TextView resultTextView;
    private Property selectedUpdateFileProperty;
    public static final List<Property> updateFileProperties = new ArrayList();
    public static final Map<String, UpdateFileCache> updateFileCacheMap = new HashMap();

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_download_firmware);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        this.firmwareDir = getExternalFilesDir("firmware");
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity_Backup.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DownloadFirmwareActivity_Backup.instance.finish();
            }
        });
        this.selectedUpdateFileProperty = null;
        Spinner spinner = (Spinner) findViewById(R.id.download_firmware_firmwareTypeSpinner);
        this.fileSpinner = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity_Backup.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                DownloadFirmwareActivity_Backup downloadFirmwareActivity_Backup = DownloadFirmwareActivity_Backup.this;
                downloadFirmwareActivity_Backup.selectedUpdateFileProperty = (Property) downloadFirmwareActivity_Backup.fileSpinner.getSelectedItem();
                DownloadFirmwareActivity_Backup.this.updateFileSelectChange();
                System.out.println("....." + DownloadFirmwareActivity_Backup.this.selectedUpdateFileProperty.getValue());
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                DownloadFirmwareActivity_Backup.this.selectedUpdateFileProperty = null;
                DownloadFirmwareActivity_Backup.this.updateFileSelectChange();
            }
        });
        Button button = (Button) findViewById(R.id.download_firmware_downloadFileButton);
        this.downloadFileButton = button;
        button.setEnabled(false);
        this.resultTextView = (TextView) findViewById(R.id.download_firmware_resultTextView);
        Button button2 = (Button) findViewById(R.id.download_firmware_go2UpdateButton);
        this.go2UpdateButton = button2;
        button2.setEnabled(false);
        loadRemoteFiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFileSelectChange() {
        boolean z = false;
        SharedPreferences.Editor edit = getSharedPreferences("userInfo", 0).edit();
        Property property = this.selectedUpdateFileProperty;
        if (property != null) {
            UpdateFileCache updateFileCache = updateFileCacheMap.get(property.getName());
            this.currentFileCacheUpdate = updateFileCache;
            if (updateFileCache != null && updateFileCache.isDoneDownload()) {
                z = true;
            }
            if (z) {
                this.resultTextView.setText(R.string.download_firmware_already_done);
            } else {
                this.resultTextView.setText(R.string.download_firmware_tip_2);
            }
            this.downloadFileButton.setEnabled(!z);
            edit.putString(Constants.SELECTED_FIRMWARE_ID, this.selectedUpdateFileProperty.getName());
        } else {
            this.downloadFileButton.setEnabled(false);
            edit.putString(Constants.SELECTED_FIRMWARE_ID, "");
        }
        edit.commit();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.nfcx.luxinvpower.view.updateFirmware.bean.UpdateFileCache> restoreUpdateFileCachesFromLocal(java.io.File r7) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.io.File[] r7 = r7.listFiles()
            int r1 = r7.length
            r2 = 0
        Lb:
            if (r2 >= r1) goto L3d
            r3 = r7[r2]
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L2a
            r5.<init>(r3)     // Catch: java.lang.Exception -> L2a
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch: java.lang.Exception -> L2a
            r3.<init>(r5)     // Catch: java.lang.Exception -> L2a
            java.lang.Object r6 = r3.readObject()     // Catch: java.lang.Exception -> L2a
            com.nfcx.luxinvpower.view.updateFirmware.bean.UpdateFileCache r6 = (com.nfcx.luxinvpower.view.updateFirmware.bean.UpdateFileCache) r6     // Catch: java.lang.Exception -> L2a
            r3.close()     // Catch: java.lang.Exception -> L27
            r5.close()     // Catch: java.lang.Exception -> L27
            goto L35
        L27:
            r3 = move-exception
            r4 = r6
            goto L2b
        L2a:
            r3 = move-exception
        L2b:
            java.lang.String r5 = "LuxPower"
            java.lang.String r6 = r3.getMessage()
            android.util.Log.e(r5, r6, r3)
            r6 = r4
        L35:
            if (r6 == 0) goto L3a
            r0.add(r6)
        L3a:
            int r2 = r2 + 1
            goto Lb
        L3d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity_Backup.restoreUpdateFileCachesFromLocal(java.io.File):java.util.List");
    }

    private void loadRemoteFiles() {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity_Backup$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DownloadFirmwareActivity_Backup.this.m402x7e32d679();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$loadRemoteFiles$1$com-nfcx-luxinvpower-view-updateFirmware-DownloadFirmwareActivity_Backup, reason: not valid java name */
    public /* synthetic */ void m402x7e32d679() {
        updateFileProperties.clear();
        final JSONObject postJson = HttpTool.postJson(GlobalInfo.getInstance().getMajorUrl() + "web/maintain/appLocalUpdate/listForApp", null);
        runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity_Backup$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DownloadFirmwareActivity_Backup.this.m401x63c1dd5a(postJson);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$loadRemoteFiles$0$com-nfcx-luxinvpower-view-updateFirmware-DownloadFirmwareActivity_Backup, reason: not valid java name */
    public /* synthetic */ void m401x63c1dd5a(JSONObject jSONObject) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", 0);
            String string = sharedPreferences.getString(Constants.SELECTED_FIRMWARE_ID, "");
            Integer num = null;
            int i = 0;
            for (UpdateFileCache updateFileCache : restoreUpdateFileCachesFromLocal(this.firmwareDir)) {
                if (!checkUpdateFilePropertiesContainRecordId(updateFileCache.getRecordId())) {
                    updateFileProperties.add(new Property(updateFileCache.getRecordId(), updateFileCache.getFileName()));
                    if (!Tool.isEmpty(string) && updateFileCache.getRecordId().equals(string)) {
                        num = Integer.valueOf(i);
                    }
                }
                Map<String, UpdateFileCache> map = updateFileCacheMap;
                if (!map.containsKey(updateFileCache.getRecordId())) {
                    map.put(updateFileCache.getRecordId(), updateFileCache);
                }
                this.go2UpdateButton.setEnabled(true);
                i++;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("rows");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                String string2 = jSONObject2.getString("recordId");
                if (!checkUpdateFilePropertiesContainRecordId(string2)) {
                    updateFileProperties.add(new Property(string2, jSONObject2.getString("fileName")));
                    if (!Tool.isEmpty(string) && string2.equals(string)) {
                        num = Integer.valueOf(i);
                    }
                }
                if (updateFileCacheMap.containsKey(string2)) {
                    this.go2UpdateButton.setEnabled(true);
                }
                i++;
            }
            DownloadFirmwareActivity_Backup downloadFirmwareActivity_Backup = instance;
            List<Property> list = updateFileProperties;
            ArrayAdapter arrayAdapter = new ArrayAdapter(downloadFirmwareActivity_Backup, R.layout.multiline_spinner_dropdown_item, list);
            arrayAdapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
            this.fileSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            if (num != null) {
                this.fileSpinner.setSelection(num.intValue());
            } else {
                if (list.isEmpty()) {
                    return;
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(Constants.SELECTED_FIRMWARE_ID, list.get(0).getName());
                edit.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkUpdateFilePropertiesContainRecordId(String str) {
        Iterator<Property> it = updateFileProperties.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void clickDownloadFileButton(View view) {
        if (this.selectedUpdateFileProperty != null) {
            this.downloadFileButton.setEnabled(false);
            loadFirmwareDataPackage(this.selectedUpdateFileProperty.getName(), 1);
        }
    }

    private void loadFirmwareDataPackage(final String str, final int i) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity_Backup$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DownloadFirmwareActivity_Backup.this.m400xe445b107(str, i);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$loadFirmwareDataPackage$3$com-nfcx-luxinvpower-view-updateFirmware-DownloadFirmwareActivity_Backup, reason: not valid java name */
    public /* synthetic */ void m400xe445b107(String str, int i) {
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
                this.currentFileCacheUpdate = updateFileCache;
                runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity_Backup$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DownloadFirmwareActivity_Backup.this.m399xc9d4b7e8();
                    }
                });
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
                    return;
                } catch (Exception e) {
                    Log.e(Version.TAG, e.getMessage(), e);
                    return;
                }
            }
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.DownloadFirmwareActivity_Backup.3
                @Override // java.lang.Runnable
                public void run() {
                    DownloadFirmwareActivity_Backup.this.downloadFileButton.setEnabled(true);
                    DownloadFirmwareActivity_Backup.this.updateFileSelectChange();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$loadFirmwareDataPackage$2$com-nfcx-luxinvpower-view-updateFirmware-DownloadFirmwareActivity_Backup, reason: not valid java name */
    public /* synthetic */ void m399xc9d4b7e8() {
        this.downloadFileButton.setEnabled(true);
        updateFileSelectChange();
        this.go2UpdateButton.setEnabled(true);
    }

    public void clickGo2UpdateButton(View view) {
        LocalConnectTool.go2LocalActivity(this);
    }
}
