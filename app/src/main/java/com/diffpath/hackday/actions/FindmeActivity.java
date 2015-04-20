package com.diffpath.hackday.actions;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;

import com.diffpath.hackday.R;

import java.io.IOException;

/**
 * Created by gamepari on 4/20/15.
 */
public class FindmeActivity extends ActionBarActivity {

    PowerManager.WakeLock sCpuWakeLock;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_findme);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        sCpuWakeLock = pm.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE, "hello");

        sCpuWakeLock.acquire();

        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(5000);

        try {
            AssetFileDescriptor afd =  getAssets().openFd("remix.mp3");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void finish() {
        sCpuWakeLock.release();
        mediaPlayer.stop();
        mediaPlayer.release();
        super.finish();
    }
}
