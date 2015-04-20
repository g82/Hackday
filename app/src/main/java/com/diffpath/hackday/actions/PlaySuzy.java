package com.diffpath.hackday.actions;

import android.content.Context;
import android.content.Intent;

/**
 * Created by gamepari on 4/20/15.
 */
public class PlaySuzy {


    public static final void playit(Context mContext) {

        Intent i = new Intent(mContext, PlaySuzyActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);

    }


}
