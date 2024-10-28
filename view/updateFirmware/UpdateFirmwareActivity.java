package com.nfcx.luxinvpower.view.updateFirmware;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.connect.LocalConnect;
import com.nfcx.luxinvpower.connect.LocalConnectManager;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.global.bean.inverter.Inverter;
import com.nfcx.luxinvpower.protocol.tcp.DataFrameFactory;
import com.nfcx.luxinvpower.protocol.tcp.dataframe.DataFrame;
import com.nfcx.luxinvpower.tool.ProTool;
import com.nfcx.luxinvpower.tool.Tool;
import com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity;
import com.nfcx.luxinvpower.view.updateFirmware.bean.StandardUploadCheck;
import com.nfcx.luxinvpower.view.updateFirmware.bean.UPDATE_STATUS;
import com.nfcx.luxinvpower.view.updateFirmware.bean.UpdateFileCache;
import com.nfcx.luxinvpower.view.updateFirmware.bean.UpdateProgressDetail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class UpdateFirmwareActivity extends Activity {
    public static final int MAX_WAIT_MILLS_SECONDS = 600000;
    public static final int MIN_WAIT_MILLS_SECONDS = 360000;
    public static final int _12K_NO_POWER_OFF_MIN_WAIT_MILLS_SECONDS = 30000;
    public static UpdateFirmwareActivity instance;
    private static final Map<String, UpdateFileCache> updateFileCacheMap = new HashMap();
    private LocalConnect localConnect;
    private List<UpdateFileCache> matchedUpdateFileCaches;
    private boolean progressing;
    private StandardUploadCheck standardUploadCheck;
    private List<UpdateFileCache> updateFileCaches;
    private TextView updateFirmwareText;
    private TextView updateFirmwareTextView1;
    private TextView updateFirmwareTextView2;
    private TextView updateFirmwareTextView3;
    private Button updateNormalFileButton;
    private ProgressBar updateProgressBar1;
    private ProgressBar updateProgressBar2;
    private ProgressBar updateProgressBar3;
    private TextView waitTimeTextView2;
    private TextView waitTimeTextView3;
    private final ProgressBar[] updateProgressArray = new ProgressBar[3];
    private int currentIndex = -1;
    private int lastUpdateIndex = -1;
    private final Runnable sendPackageRunnable = new AnonymousClass1();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showResultDialog$1(DialogInterface dialogInterface, int i) {
    }

    static /* synthetic */ int access$108(UpdateFirmwareActivity updateFirmwareActivity) {
        int i = updateFirmwareActivity.currentIndex;
        updateFirmwareActivity.currentIndex = i + 1;
        return i;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_update_firmware);
        instance = this;
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_green));
        getWindow().addFlags(128);
        this.localConnect = LocalConnectManager.getLocalConnect(getIntent().getStringExtra(Constants.LOCAL_CONNECT_TYPE));
        ((ConstraintLayout) findViewById(R.id.backImageViewLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpdateFirmwareActivity.this.m405x2803f292(view);
            }
        });
        this.currentIndex = 0;
        Inverter inverter = this.localConnect.getInverter();
        System.out.println("LuxPower - fwCode = " + (inverter != null ? inverter.getFwCode() : null));
        this.updateFirmwareText = (TextView) findViewById(R.id.update_firmware);
        this.updateFirmwareTextView1 = (TextView) findViewById(R.id.update_firmware_textView1);
        this.waitTimeTextView2 = (TextView) findViewById(R.id.update_firmware_waitTime_textView2);
        this.updateFirmwareTextView2 = (TextView) findViewById(R.id.update_firmware_textView2);
        this.waitTimeTextView3 = (TextView) findViewById(R.id.update_firmware_waitTime_textView3);
        this.updateFirmwareTextView3 = (TextView) findViewById(R.id.update_firmware_textView3);
        this.updateProgressBar1 = (ProgressBar) findViewById(R.id.update_firmware1_updateProgressBar);
        this.updateProgressBar2 = (ProgressBar) findViewById(R.id.update_firmware2_updateProgressBar);
        this.updateProgressBar3 = (ProgressBar) findViewById(R.id.update_firmware3_updateProgressBar);
        this.updateNormalFileButton = (Button) findViewById(R.id.update_firmware_updateNormalFileButton);
        updateFileCacheMap.clear();
        this.updateFileCaches = DownloadFirmwareActivity.restoreUpdateFileCachesFromLocal(getExternalFilesDir("firmware"));
        for (int i = 0; i < this.updateFileCaches.size(); i++) {
            UpdateFileCache updateFileCache = this.updateFileCaches.get(i);
            updateFileCacheMap.put(updateFileCache.getRecordId(), updateFileCache);
        }
        ProgressBar[] progressBarArr = this.updateProgressArray;
        progressBarArr[0] = this.updateProgressBar1;
        progressBarArr[1] = this.updateProgressBar2;
        progressBarArr[2] = this.updateProgressBar3;
        String checkFwCodeValid = checkFwCodeValid(inverter);
        if (!Tool.isEmpty(checkFwCodeValid)) {
            Tool.alert(this, getString(R.string.update_firmware_invalid) + ": " + checkFwCodeValid);
            return;
        }
        StandardUploadCheck standardUploadCheck = new StandardUploadCheck();
        this.standardUploadCheck = standardUploadCheck;
        standardUploadCheck.fillByInverter(inverter);
        this.matchedUpdateFileCaches = new ArrayList();
        UpdateFileCache tryMatchV1StandardUploadRecord = tryMatchV1StandardUploadRecord(inverter);
        if (tryMatchV1StandardUploadRecord != null) {
            this.matchedUpdateFileCaches.add(tryMatchV1StandardUploadRecord);
        }
        UpdateFileCache tryMatchV2StandardUploadRecord = tryMatchV2StandardUploadRecord(inverter);
        if (tryMatchV2StandardUploadRecord != null) {
            this.matchedUpdateFileCaches.add(tryMatchV2StandardUploadRecord);
        }
        UpdateFileCache tryMatchV3StandardUploadRecord = tryMatchV3StandardUploadRecord(inverter);
        if (tryMatchV3StandardUploadRecord != null) {
            this.matchedUpdateFileCaches.add(tryMatchV3StandardUploadRecord);
        }
        this.updateFirmwareText.setText(inverter.getSerialNum() + ":" + inverter.getFwCode().toUpperCase());
        if (this.matchedUpdateFileCaches.size() >= 1) {
            this.updateFirmwareTextView1.setText(this.matchedUpdateFileCaches.get(0).getFileName());
            this.updateFirmwareTextView1.setVisibility(0);
            this.updateProgressBar1.setVisibility(0);
        }
        if (this.matchedUpdateFileCaches.size() >= 2) {
            this.updateFirmwareTextView2.setText(this.matchedUpdateFileCaches.get(1).getFileName());
            this.updateFirmwareTextView2.setVisibility(0);
            this.waitTimeTextView2.setVisibility(0);
            this.updateProgressBar2.setVisibility(0);
        }
        if (this.matchedUpdateFileCaches.size() >= 3) {
            this.updateFirmwareTextView3.setText(this.matchedUpdateFileCaches.get(2).getFileName());
            this.updateFirmwareTextView3.setVisibility(0);
            this.waitTimeTextView3.setVisibility(0);
            this.updateProgressBar3.setVisibility(0);
        }
        List<UpdateFileCache> list = this.matchedUpdateFileCaches;
        if (list == null || list.size() < 1) {
            Tool.alert(this, R.string.update_firmware_no_match_standard_firmware);
            this.updateNormalFileButton.setEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-nfcx-luxinvpower-view-updateFirmware-UpdateFirmwareActivity, reason: not valid java name */
    public /* synthetic */ void m405x2803f292(View view) {
        if (this.progressing) {
            this.progressing = false;
        }
        instance.finish();
    }

    private UpdateFileCache tryMatchV1StandardUploadRecord(Inverter inverter) {
        String standard = this.standardUploadCheck.getStandard();
        if (Tool.isEmpty(standard) || this.standardUploadCheck.getV1() == null) {
            return null;
        }
        if (inverter.isSnaSeries()) {
            return tryStrictMatchV2StandardUploadRecord(standard, this.standardUploadCheck.getV1().intValue());
        }
        if (inverter.isType6()) {
            if ("EAAB".equals(standard) || "FAAA".equals(standard)) {
                standard = "FAAB";
            } else if ("eAAB".equals(standard) || "fAAA".equals(standard)) {
                standard = "fAAB";
            }
            if (this.standardUploadCheck.check12KNoPowerOffUpdateAllowed()) {
                return tryMatchV1StandardUploadRecord(standard, this.standardUploadCheck.getV1().intValue());
            }
            return tryStrictMatchV2StandardUploadRecord(standard, this.standardUploadCheck.getV1().intValue());
        }
        return tryMatchV1StandardUploadRecord(standard, this.standardUploadCheck.getV1().intValue());
    }

    private UpdateFileCache tryMatchV2StandardUploadRecord(Inverter inverter) {
        String standard = this.standardUploadCheck.getStandard();
        if (Tool.isEmpty(standard) || this.standardUploadCheck.getV2() == null) {
            return null;
        }
        if (inverter.isLsp()) {
            return tryMatchV2StandardUploadRecord(standard, this.standardUploadCheck.getV2().intValue());
        }
        if (inverter.isSnaSeries()) {
            return tryMatchV1StandardUploadRecord(standard, this.standardUploadCheck.getV2().intValue());
        }
        if (inverter.isType6()) {
            if ("EAAB".equals(standard) || "FAAA".equals(standard)) {
                standard = "FAAB";
            } else if ("eAAB".equals(standard) || "fAAA".equals(standard)) {
                standard = "fAAB";
            }
            if (this.standardUploadCheck.check12KNoPowerOffUpdateAllowed()) {
                return tryStrictMatchV2StandardUploadRecord(standard, this.standardUploadCheck.getV2().intValue());
            }
            return tryMatchV1StandardUploadRecord(standard, this.standardUploadCheck.getV2().intValue());
        }
        return tryStrictMatchV2StandardUploadRecord(standard, this.standardUploadCheck.getV2().intValue());
    }

    private UpdateFileCache tryMatchV3StandardUploadRecord(Inverter inverter) {
        String standard = this.standardUploadCheck.getStandard();
        if (Tool.isEmpty(standard) || this.standardUploadCheck.getV3() == null) {
            return null;
        }
        return tryMatchV3StandardUploadRecord(standard, this.standardUploadCheck.getV3().intValue());
    }

    private UpdateFileCache tryMatchV1StandardUploadRecord(String str, int i) {
        return tryMatchStandardUploadRecord(str, i, 1, false);
    }

    private UpdateFileCache tryMatchV2StandardUploadRecord(String str, int i) {
        return tryMatchStandardUploadRecord(str, i, 2, false);
    }

    private UpdateFileCache tryStrictMatchV2StandardUploadRecord(String str, int i) {
        return tryMatchStandardUploadRecord(str, i, 2, true);
    }

    public UpdateFileCache tryMatchV3StandardUploadRecord(String str, int i) {
        return tryMatchStandardUploadRecord(str, i, 3, false);
    }

    private UpdateFileCache tryMatchStandardUploadRecord(String str, int i, int i2, boolean z) {
        UpdateFileCache updateFileCache = null;
        for (UpdateFileCache updateFileCache2 : this.updateFileCaches) {
            if (str.equals(updateFileCache2.getStandard()) || (!z && str.equalsIgnoreCase(updateFileCache2.getStandard()))) {
                if (updateFileCache2.getV(i2) != null && updateFileCache2.getV(i2).intValue() > i && (updateFileCache == null || updateFileCache.getV(i2) == null || updateFileCache.getV(i2).intValue() < updateFileCache2.getV(i2).intValue())) {
                    updateFileCache = updateFileCache2;
                }
            }
        }
        return updateFileCache;
    }

    private String checkFwCodeValid(Inverter inverter) {
        String fwCode = inverter.getFwCode();
        if (Tool.isEmpty(fwCode) || fwCode.indexOf("-") != 4) {
            return "inverterFwCodeInvalid";
        }
        if (!inverter.isSnaSeries() && fwCode.length() != 9) {
            return "inverterFwCodeInvalid";
        }
        if (inverter.isSnaSeries() && fwCode.length() != 11) {
            return "inverterFwCodeInvalid";
        }
        if (!inverter.isType6()) {
            return null;
        }
        if (fwCode.startsWith("fAAA") || fwCode.startsWith("FAAA")) {
            int parseInt = Integer.parseInt(fwCode.substring(5, 7), 16);
            int parseInt2 = Integer.parseInt(fwCode.substring(7, 9), 16);
            if (parseInt < 7 || parseInt2 < 7) {
                return "inverter_8_12K_FwCodeInvalid";
            }
            return null;
        }
        if (fwCode.startsWith("EAAB") || fwCode.startsWith("FAAB") || fwCode.startsWith("eAAB") || fwCode.startsWith("fAAB")) {
            return null;
        }
        return "inverter_8_12K_FwCodeInvalid";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showResultDialog(boolean z) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.local_button_update_firmware);
        builder.setMessage(z ? R.string.local_page_update_result_success : R.string.local_page_update_result_failed);
        builder.setPositiveButton(R.string.phrase_button_ok, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdateFirmwareActivity.lambda$showResultDialog$1(dialogInterface, i);
            }
        });
        builder.show();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.progressing = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getMinWaitMillsSeconds() {
        return this.standardUploadCheck.check12KNoPowerOffUpdateAllowed() ? _12K_NO_POWER_OFF_MIN_WAIT_MILLS_SECONDS : MIN_WAIT_MILLS_SECONDS;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            boolean z2;
            boolean z3 = true;
            UpdateFirmwareActivity.this.progressing = true;
            if (UpdateFirmwareActivity.this.currentIndex > 0 && UpdateFirmwareActivity.this.lastUpdateIndex < UpdateFirmwareActivity.this.currentIndex) {
                long currentTimeMillis = System.currentTimeMillis();
                boolean z4 = false;
                int i = 0;
                for (long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis; currentTimeMillis2 < 600000 && !z4 && UpdateFirmwareActivity.this.progressing; currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis) {
                    Tool.sleep(1000L);
                    int i2 = (int) ((600000 - currentTimeMillis2) / 1000);
                    final String str = "00:" + ProTool.fillZeros(String.valueOf(i2 / 60), 2) + ":" + ProTool.fillZeros(String.valueOf(i2 % 60), 2);
                    UpdateFirmwareActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            UpdateFirmwareActivity.AnonymousClass1.this.m407xc0e6ffd5(str);
                        }
                    });
                    if (currentTimeMillis2 > UpdateFirmwareActivity.this.getMinWaitMillsSeconds()) {
                        if (i == 0) {
                            String sendCommand = UpdateFirmwareActivity.this.localConnect.sendCommand("read_03_1", DataFrameFactory.createReadMultiHoldDataFrame(UpdateFirmwareActivity.this.localConnect.getTcpProtocol(), UpdateFirmwareActivity.this.localConnect.getDatalogSn(), 0, 40));
                            if (!Tool.isEmpty(sendCommand) && sendCommand.length() > 60) {
                                z4 = true;
                            }
                        }
                        i++;
                        if (i > 10) {
                            i = 0;
                        }
                    }
                }
                UpdateFirmwareActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpdateFirmwareActivity.AnonymousClass1.this.m408x9ca87b96();
                    }
                });
            }
            UpdateFirmwareActivity updateFirmwareActivity = UpdateFirmwareActivity.this;
            updateFirmwareActivity.lastUpdateIndex = updateFirmwareActivity.currentIndex;
            final UpdateProgressDetail createUpdateProgressDetail = UpdateFirmwareActivity.this.createUpdateProgressDetail();
            boolean z5 = true;
            boolean z6 = false;
            while (UpdateFirmwareActivity.this.progressing && z5) {
                final Map<Integer, String> firmware = ((UpdateFileCache) UpdateFirmwareActivity.this.matchedUpdateFileCaches.get(UpdateFirmwareActivity.this.currentIndex)).getFirmware();
                int i3 = AnonymousClass2.$SwitchMap$com$nfcx$luxinvpower$view$updateFirmware$bean$UPDATE_STATUS[createUpdateProgressDetail.getUpdateStatus().ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        if (createUpdateProgressDetail.getLastTimeSendPackage() > 1) {
                            if (System.currentTimeMillis() - createUpdateProgressDetail.getLastTimeSendPackage() < DeviceOrientationRequest.OUTPUT_PERIOD_FAST) {
                                Tool.sleep(500L);
                                z5 = true;
                            } else {
                                createUpdateProgressDetail.setErrorCount(createUpdateProgressDetail.getErrorCount() + 1);
                            }
                        }
                        if ((!createUpdateProgressDetail.isSendUpdateStart_0x21() && createUpdateProgressDetail.getErrorCount() >= 4) || createUpdateProgressDetail.getErrorCount() >= 10) {
                            createUpdateProgressDetail.setUpdateStatus(UPDATE_STATUS.FAILURE);
                            UpdateFirmwareActivity.this.localConnect.close();
                            UpdateFirmwareActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$1$$ExternalSyntheticLambda2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    UpdateFirmwareActivity.AnonymousClass1.this.m409x7869f757();
                                }
                            });
                        } else {
                            createUpdateProgressDetail.setLastTimeSendPackage(System.currentTimeMillis());
                            UpdateFirmwareActivity.this.sendUpdateDataFrame(createUpdateProgressDetail);
                            z = z6;
                            z2 = true;
                        }
                    }
                    z = z6;
                    z2 = false;
                } else if (createUpdateProgressDetail.getPackageIndex() > firmware.size() && createUpdateProgressDetail.isSendUpdateReset_0x23()) {
                    createUpdateProgressDetail.setUpdateStatus(UPDATE_STATUS.SUCCESS);
                    System.out.println("LuxPower升级成功");
                    z = true;
                    z2 = false;
                } else {
                    if (!createUpdateProgressDetail.isSendUpdateStart_0x21()) {
                        createUpdateProgressDetail.setSendUpdateStart_0x21(true);
                    } else if (createUpdateProgressDetail.getPackageIndex() <= firmware.size()) {
                        createUpdateProgressDetail.setPackageIndex(createUpdateProgressDetail.getPackageIndex() + 1);
                    }
                    createUpdateProgressDetail.setErrorCount(0);
                    createUpdateProgressDetail.setUpdateStatus(UPDATE_STATUS.READY);
                    createUpdateProgressDetail.setLastTimeSendPackage(System.currentTimeMillis());
                    UpdateFirmwareActivity.this.sendUpdateDataFrame(createUpdateProgressDetail);
                    z = z6;
                    z2 = true;
                }
                UpdateFirmwareActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$1$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpdateFirmwareActivity.AnonymousClass1.this.m410x542b7318(createUpdateProgressDetail, firmware);
                    }
                });
                Tool.sleep(100L);
                z5 = z2;
                z6 = z;
            }
            if (z6) {
                if (UpdateFirmwareActivity.this.currentIndex < UpdateFirmwareActivity.this.matchedUpdateFileCaches.size() - 1) {
                    UpdateFirmwareActivity.access$108(UpdateFirmwareActivity.this);
                    new Thread(UpdateFirmwareActivity.this.sendPackageRunnable).start();
                    z3 = false;
                } else {
                    UpdateFirmwareActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$1$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            UpdateFirmwareActivity.AnonymousClass1.this.m411x2feceed9();
                        }
                    });
                }
            }
            if (!z6 || z3) {
                UpdateFirmwareActivity.this.progressing = false;
                UpdateFirmwareActivity.this.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$1$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpdateFirmwareActivity.AnonymousClass1.this.m412xbae6a9a();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$run$0$com-nfcx-luxinvpower-view-updateFirmware-UpdateFirmwareActivity$1, reason: not valid java name */
        public /* synthetic */ void m407xc0e6ffd5(String str) {
            if (UpdateFirmwareActivity.this.currentIndex == 1) {
                UpdateFirmwareActivity.this.waitTimeTextView2.setText(UpdateFirmwareActivity.this.getString(R.string.login_download_dialog_text) + " " + str);
            } else if (UpdateFirmwareActivity.this.currentIndex == 2) {
                UpdateFirmwareActivity.this.waitTimeTextView3.setText(UpdateFirmwareActivity.this.getString(R.string.login_download_dialog_text) + " " + str);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$run$1$com-nfcx-luxinvpower-view-updateFirmware-UpdateFirmwareActivity$1, reason: not valid java name */
        public /* synthetic */ void m408x9ca87b96() {
            UpdateFirmwareActivity.this.waitTimeTextView2.setVisibility(4);
            UpdateFirmwareActivity.this.waitTimeTextView3.setVisibility(4);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$run$2$com-nfcx-luxinvpower-view-updateFirmware-UpdateFirmwareActivity$1, reason: not valid java name */
        public /* synthetic */ void m409x7869f757() {
            UpdateFirmwareActivity.this.showResultDialog(false);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$run$3$com-nfcx-luxinvpower-view-updateFirmware-UpdateFirmwareActivity$1, reason: not valid java name */
        public /* synthetic */ void m410x542b7318(UpdateProgressDetail updateProgressDetail, Map map) {
            UpdateFirmwareActivity.this.updateProgressArray[UpdateFirmwareActivity.this.currentIndex].setProgress((updateProgressDetail.getCurrentProgress() * 100) / (map.size() + 2));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$run$4$com-nfcx-luxinvpower-view-updateFirmware-UpdateFirmwareActivity$1, reason: not valid java name */
        public /* synthetic */ void m411x2feceed9() {
            UpdateFirmwareActivity.this.showResultDialog(true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$run$5$com-nfcx-luxinvpower-view-updateFirmware-UpdateFirmwareActivity$1, reason: not valid java name */
        public /* synthetic */ void m412xbae6a9a() {
            UpdateFirmwareActivity.this.updateNormalFileButton.setEnabled(true);
        }
    }

    /* renamed from: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$nfcx$luxinvpower$view$updateFirmware$bean$UPDATE_STATUS;

        static {
            int[] iArr = new int[UPDATE_STATUS.values().length];
            $SwitchMap$com$nfcx$luxinvpower$view$updateFirmware$bean$UPDATE_STATUS = iArr;
            try {
                iArr[UPDATE_STATUS.WAITING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$nfcx$luxinvpower$view$updateFirmware$bean$UPDATE_STATUS[UPDATE_STATUS.READY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUpdateDataFrame(UpdateProgressDetail updateProgressDetail) {
        DataFrame createUpdateResetDataFrame;
        UpdateFileCache updateFileCache = this.matchedUpdateFileCaches.get(this.currentIndex);
        Map<Integer, String> firmware = updateFileCache.getFirmware();
        int size = firmware.size();
        int i = 1;
        if (!updateProgressDetail.isSendUpdateStart_0x21()) {
            DataFrame createUpdatePrepareDataFrame = DataFrameFactory.createUpdatePrepareDataFrame(this.localConnect.getTcpProtocol(), updateProgressDetail.getDatalogSn(), updateProgressDetail.getInverterSn(), updateFileCache.getTailEncoded(), size, updateFileCache.getCrc32());
            if (createUpdatePrepareDataFrame != null) {
                String sendCommand = this.localConnect.sendCommand("tcpUpdate_Prepare", createUpdatePrepareDataFrame);
                if (Tool.isEmpty(sendCommand)) {
                    return;
                }
                int count = ProTool.count(sendCommand.charAt(34), sendCommand.charAt(33));
                updateProgressDetail.setUpdateStatus(UPDATE_STATUS.WAITING);
                if (count > 0 && count < size) {
                    i = count;
                }
                updateProgressDetail.setPackageIndex(i);
                return;
            }
            return;
        }
        if (updateProgressDetail.isSendUpdateStart_0x21() && updateProgressDetail.getPackageIndex() <= size) {
            int packageIndex = updateProgressDetail.getPackageIndex();
            System.out.println("LuxPower - ble - SEND 0x22 - " + packageIndex);
            DataFrame createUpdateSendDataDataFrame = DataFrameFactory.createUpdateSendDataDataFrame(this.localConnect.getTcpProtocol(), updateProgressDetail.getDatalogSn(), updateProgressDetail.getInverterSn(), packageIndex, updateFileCache.getFileType(), updateFileCache.getPhysicalAddr().get(Integer.valueOf(packageIndex)).intValue(), firmware.get(Integer.valueOf(packageIndex)));
            if (createUpdateSendDataDataFrame == null || Tool.isEmpty(this.localConnect.sendCommand("tcpUpdate_Send_" + packageIndex, createUpdateSendDataDataFrame))) {
                return;
            }
            updateProgressDetail.setUpdateStatus(UPDATE_STATUS.WAITING);
            return;
        }
        if (updateProgressDetail.getPackageIndex() <= size || updateProgressDetail.isSendUpdateReset_0x23() || (createUpdateResetDataFrame = DataFrameFactory.createUpdateResetDataFrame(this.localConnect.getTcpProtocol(), updateProgressDetail.getDatalogSn(), updateProgressDetail.getInverterSn(), updateFileCache.getFileType(), size, updateFileCache.getCrc32())) == null) {
            return;
        }
        this.localConnect.sendCommand("tcpUpdate_Reset", createUpdateResetDataFrame);
        updateProgressDetail.setSendUpdateReset_0x23(true);
        updateProgressDetail.setUpdateStatus(UPDATE_STATUS.WAITING);
    }

    private String generateFirmwareUpdateVerifyCode(long j) {
        String valueOf = String.valueOf(j);
        return valueOf.length() > 6 ? valueOf.substring(valueOf.length() - 6) : valueOf;
    }

    public void clickStartUpdateButton(View view) {
        List<UpdateFileCache> list = this.matchedUpdateFileCaches;
        if (list == null || list.size() < 1) {
            Tool.alert(this, R.string.update_firmware_no_match_standard_firmware);
        } else {
            new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateFirmwareActivity.this.m404xf20b87ce();
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$clickStartUpdateButton$2$com-nfcx-luxinvpower-view-updateFirmware-UpdateFirmwareActivity, reason: not valid java name */
    public /* synthetic */ void m404xf20b87ce() {
        if (startSendFirmwarePackage() == 14) {
            Tool.alertNotInUiThread(this, R.string.local_regular_set_toast_tcp_init_fail);
        }
    }

    private synchronized int startSendFirmwarePackage() {
        if (!this.localConnect.initialize(true)) {
            return 14;
        }
        if (Tool.isEmpty(this.localConnect.getDatalogSn())) {
            this.localConnect.setDatalogSn(Constants.DEFAULT_DATALOG_SN);
        }
        if (this.localConnect.getInverter() == null && !this.localConnect.read03AndInitDevice()) {
            return 16;
        }
        if (!this.progressing) {
            runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.view.updateFirmware.UpdateFirmwareActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateFirmwareActivity.this.m406xd404ecfb();
                }
            });
            new Thread(this.sendPackageRunnable).start();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startSendFirmwarePackage$3$com-nfcx-luxinvpower-view-updateFirmware-UpdateFirmwareActivity, reason: not valid java name */
    public /* synthetic */ void m406xd404ecfb() {
        this.updateNormalFileButton.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UpdateProgressDetail createUpdateProgressDetail() {
        UpdateProgressDetail updateProgressDetail = new UpdateProgressDetail();
        updateProgressDetail.setInverterSn(this.localConnect.getInverter().getSerialNum());
        updateProgressDetail.setDatalogSn(this.localConnect.getDatalogSn());
        updateProgressDetail.setPackageIndex(1);
        updateProgressDetail.setUpdateStatus(UPDATE_STATUS.READY);
        return updateProgressDetail;
    }
}
