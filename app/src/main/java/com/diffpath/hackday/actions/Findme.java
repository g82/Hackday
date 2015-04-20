package com.diffpath.hackday.actions;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

/**
 * Created by gamepari on 4/20/15.
 */
public class Findme {

    public static void alert(final Context mContext) {

//        PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock sCpuWakeLock = pm.newWakeLock(
//                PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
//                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
//                        PowerManager.ON_AFTER_RELEASE, "hello");
//
//        sCpuWakeLock.acquire();
//
//        Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
//        vibe.vibrate(2500);
//
//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//        Ringtone r = RingtoneManager.getRingtone(mContext, notification);
//        r.play();

        Intent i = new Intent(mContext, FindmeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);

    }

}
