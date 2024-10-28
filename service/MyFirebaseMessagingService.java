package com.nfcx.luxinvpower.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nfcx.luxinvpower.R;

/* loaded from: classes2.dex */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private int requestCode = 0;

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String str = remoteMessage.getData().get("title");
        String str2 = remoteMessage.getData().get("body");
        if (str == null || str2 == null) {
            return;
        }
        sendNotification(str, str2);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        super.onNewToken(str);
        SharedPreferences.Editor edit = getSharedPreferences("userInfo", 0).edit();
        edit.putString("fcmToken", str);
        edit.apply();
    }

    private void sendNotification(String str, String str2) {
        Log.d("MyFirebaseMessagingService", "sendNotification is called");
        this.requestCode++;
        String string = getString(R.string.event_notification_channel);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        NotificationChannel notificationChannel = new NotificationChannel("my_channel_id", string, 4);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationChannel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, new AudioAttributes.Builder().setContentType(4).setUsage(6).build());
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        notificationManager.notify(this.requestCode, new NotificationCompat.Builder(this, "my_channel_id").setContentTitle(str).setContentText(str2).setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true).setPriority(1).setDefaults(-1).setSound(Settings.System.DEFAULT_NOTIFICATION_URI, 5).setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build());
    }
}
