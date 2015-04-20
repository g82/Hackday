package com.diffpath.hackday.actions;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.diffpath.hackday.R;

import java.io.IOException;

/**
 * Created by Heedeok on 15. 4. 20..
 */
public class KatalkActivity extends ActionBarActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findme);


        try {
            AssetFileDescriptor afd =  getAssets().openFd("katalk.wmv");
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
        mediaPlayer.stop();
        mediaPlayer.release();
        super.finish();
    }
}