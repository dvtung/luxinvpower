package com.nfcx.luxinvpower.tool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.nfcx.luxinvpower.R;
import com.nfcx.luxinvpower.global.Constants;
import com.nfcx.luxinvpower.tcp.TcpManager;
import com.nfcx.luxinvpower.view.ble.BleConnectActivity;
import com.nfcx.luxinvpower.view.local.LocalActivity;

/* loaded from: classes2.dex */
public class LocalConnectTool {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$go2LocalActivity$1(DialogInterface dialogInterface, int i) {
    }

    public static void go2LocalActivity(final Activity activity) {
        new AlertDialog.Builder(activity).setTitle(R.string.phrase_local_connect_title).setIcon(R.drawable.ic_launcher).setItems(new String[]{activity.getString(R.string.phrase_tcp_connect), activity.getString(R.string.phrase_bluetooth_connect)}, new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.tool.LocalConnectTool$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LocalConnectTool.lambda$go2LocalActivity$0(activity, dialogInterface, i);
            }
        }).setNegativeButton(activity.getString(R.string.phrase_button_cancel), new DialogInterface.OnClickListener() { // from class: com.nfcx.luxinvpower.tool.LocalConnectTool$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LocalConnectTool.lambda$go2LocalActivity$1(dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$go2LocalActivity$0(Activity activity, DialogInterface dialogInterface, int i) {
        if (i == 1) {
            Intent intent = new Intent(activity, (Class<?>) BleConnectActivity.class);
            intent.putExtra(TypedValues.AttributesType.S_TARGET, Constants.BLUETOOTH_TARGET_LOCAL_CONNECT);
            activity.startActivity(intent);
            return;
        }
        connectTcpLocal(activity);
    }

    public static void connectTcpLocal(final Activity activity) {
        new Thread(new Runnable() { // from class: com.nfcx.luxinvpower.tool.LocalConnectTool$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                LocalConnectTool.lambda$connectTcpLocal$3(activity);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$connectTcpLocal$3(final Activity activity) {
        final boolean initialize = TcpManager.getInstance().initialize(true);
        activity.runOnUiThread(new Runnable() { // from class: com.nfcx.luxinvpower.tool.LocalConnectTool$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LocalConnectTool.lambda$connectTcpLocal$2(initialize, activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$connectTcpLocal$2(boolean z, Activity activity) {
        if (z) {
            Intent intent = new Intent(activity, (Class<?>) LocalActivity.class);
            intent.putExtra(Constants.LOCAL_CONNECT_TYPE, Constants.LOCAL_CONNECT_TYPE_TCP);
            activity.startActivity(intent);
            return;
        }
        Tool.alert(activity, R.string.phrase_toast_local_connect_error);
    }
}
