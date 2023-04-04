package com.alatheer.noamany.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.alatheer.noamany.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class FCMMessagingService extends FirebaseMessagingService {
    String type = "";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            type = "json";
            sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }
        Log.e("message",remoteMessage.getData().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotification(String title2, String messageBody) {
        String id = "", message = "", title = "";
        if (type.equals("json")) {
            try {
                JSONObject jsonObject = new JSONObject(messageBody);
                id = jsonObject.getString("id");
                messageBody = jsonObject.getString("message");
                title2 = jsonObject.getString("title");
            } catch (JSONException e) {
                //
            }
        }
        String id1 = "my_channel_01";
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel mChannel = new NotificationChannel(id1, name, importance);
        mChannel.setDescription(description);
        mChannel.setShowBadge(true);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(mChannel);
        Intent intent = new Intent("com.alatheer.noamany_FCM-MESSAGE");
        intent.putExtra("title",title2);
        intent.putExtra("message",messageBody);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(intent);
    }
}
