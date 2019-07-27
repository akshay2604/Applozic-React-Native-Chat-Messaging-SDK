package com.applozic;

import android.util.Log;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.applozic.mobicomkit.api.notification.MobiComPushReceiver;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FcmListenerService extends FirebaseMessagingService {

    public static final String REMOTE_NOTIFICATION_EVENT = "notifications-remote-notification";
    public static final String MESSAGE_EVENT = "messaging-message";

    private static final String TAG = "ApplozicGcmListener";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.i(TAG, "Message data:" + remoteMessage.getData());

        if (remoteMessage.getData().size() > 0) {
            if (MobiComPushReceiver.isMobiComPushNotification(remoteMessage.getData())) {
                Log.i(TAG, "Applozic notification processing...");
                MobiComPushReceiver.processMessageAsync(this, remoteMessage.getData());
                Intent messagingEvent = new Intent(MESSAGE_EVENT);
                messagingEvent.putExtra("message", remoteMessage);
        

                // Broadcast it to the (foreground) RN Application
                LocalBroadcastManager
                    .getInstance(this)
                    .sendBroadcast(messagingEvent);
                return;
            }

        }
    }
}