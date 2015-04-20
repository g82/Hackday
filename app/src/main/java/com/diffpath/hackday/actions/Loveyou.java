package com.diffpath.hackday.actions;

import android.telephony.SmsManager;

/**
 * Created by gamepari on 4/20/15.
 */
public class Loveyou {

    public static final void sendMessage() {

        SmsManager smsManager = SmsManager.getDefault();
        String sendTo = "01083293124";
        String myMessage = "피카피카피";
        smsManager.sendTextMessage(sendTo, null, myMessage, null, null);
    }




}
