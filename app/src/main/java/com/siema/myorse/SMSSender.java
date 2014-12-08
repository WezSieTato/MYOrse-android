package com.siema.myorse;

import android.telephony.SmsManager;

/**
 * Created by marcin on 08/12/14.
 */
public class SMSSender {
    static void sendMessage(String phoneNumber, String body){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, body, null, null);
    }
}
