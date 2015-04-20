package com.diffpath.hackday.actions;

import android.content.Context;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by gamepari on 4/20/15.
 */
public class Findme {

    public static void alert(Context mContext) {

        Vibrator vibe = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(5000);

        Toast.makeText(mContext, "나를 찾아줘", Toast.LENGTH_LONG).show();

    }



}
