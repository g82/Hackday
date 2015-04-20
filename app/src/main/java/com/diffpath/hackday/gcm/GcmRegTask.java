package com.diffpath.hackday.gcm;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

/**
 * Created by gamepari on 4/20/15.
 */
public class GcmRegTask extends AsyncTask<String, Void, String> {

    public interface GcmTaskListener {
        void onRegistered(String regId);
    }

    private static final String TAG = "GcmRegTask";

    private Context mContext;
    private GcmTaskListener mGcmTaskListener;

    public GcmRegTask(Context mContext) {
        this.mContext = mContext;
        this.mGcmTaskListener = (GcmTaskListener) mContext;
    }

    @Override
    protected String doInBackground(String... strings) {

        String senderId = strings[0];

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(mContext);

        String regId = "";

        try {
            regId = gcm.register(senderId);
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        return regId;
    }

    @Override
    protected void onPostExecute(String s) {
        mGcmTaskListener.onRegistered(s);
    }
}
