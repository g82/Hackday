package com.diffpath.hackday.actions;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

import com.diffpath.hackday.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by gamepari on 4/20/15.
 */
public class PlaySuzyActivity extends YouTubeBaseActivity {

    PowerManager.WakeLock sCpuWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_suzy);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        sCpuWakeLock = pm.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE, "hello");

        sCpuWakeLock.acquire();

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.yv_player);

        youTubePlayerView.initialize("AIzaSyBlVNUw1SxA0j0RvggCy8qy-CjuuWhu95M", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("zO9RzrhYR-I");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }


    @Override
    public void finish() {
        sCpuWakeLock.release();
        super.finish();
    }
}
