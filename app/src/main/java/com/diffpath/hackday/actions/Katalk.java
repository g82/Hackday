package com.diffpath.hackday.actions;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Heedeok on 15. 4. 20..
 */
public class Katalk {
    public static void alert(final Context mContext) {

        Intent i = new Intent(mContext, KatalkActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);

    }
}
