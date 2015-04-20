package com.diffpath.hackday.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.diffpath.hackday.MainActivity;
import com.diffpath.hackday.R;
import com.diffpath.hackday.actions.Action;
import com.diffpath.hackday.actions.Findme;
import com.diffpath.hackday.actions.Loveyou;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class GcmIntentService extends IntentService {

    private static final String TAG = "GcmIntentService";
    private final static AtomicInteger c = new AtomicInteger(0);

    public GcmIntentService() {
        super("GcmIntentService");
    }

    private static int getID() {
        return c.incrementAndGet();
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void doAction(Intent data) {

        Log.d(TAG, data.getExtras().toString());

        String actionStr = data.getStringExtra("default");

        int actionType = Integer.valueOf(actionStr);


        switch (actionType) {
            case Action.FINDME:
                //나를 찾아줘
                //휴대전화 찾기

                Findme.alert(this);

                break;

            case Action.LOVEYOU:
                Loveyou.sendMessage();
                break;

            case Action.PLAYSUZY:

                break;

            default:
                showNotification(data);
                break;
        }

    }

    private void showNotification(Intent data) {


        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent i = new Intent(this, MainActivity.class);
//        i.putExtra(MainActivity.PARAM_PUSH_URL, link);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentTitle()
//                        .setContentText(message)
                        .setAutoCancel(true);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(GcmIntentService.getID(), mBuilder.build());
    }


    @Override
    protected void onHandleIntent(Intent intent) {


        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM
             * will be extended in the future with new message types, just ignore
             * any message types you're not interested in, or that you don't
             * recognize.
             */
            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {

            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {

                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {

                doAction(intent);

            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);

    }

}
