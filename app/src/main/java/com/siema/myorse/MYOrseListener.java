package com.siema.myorse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by marcin on 08/12/14.
 */
public class MYOrseListener extends BroadcastReceiver {

    private boolean enabled;
    private boolean enabledSMSSending;
    private ContactInfo username;
    private String phoneNumber;

    public Context getContex() {
        return contex;
    }

    public void setContex(Context contex) {
        this.contex = contex;
    }

    private Context contex;

    public MYOrseListener() {
        super();
        username = null;
        enabled = false;
        phoneNumber = null;
    }

    public boolean isEnabledSMSSending() {
        return enabledSMSSending;
    }

    public void setEnabledSMSSending(boolean enabledSMSSending) {
        this.enabledSMSSending = enabledSMSSending;
    }

    public ContactInfo getUsername() {
        return username;
    }

    public void setUsername(ContactInfo username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;


        if (enabledSMSSending) {
            String msg = contex.getString(enabled ? R.string.START_MYORSE_MESSAGE : R.string.STOP_MYORSE_MESSAGE);

            for (String number : username.getPhoneNumbers()) {
            Toast.makeText(contex, "Wysylam SMSa powiadamiającego: " + number, 5 * (Toast.LENGTH_LONG)).show();
                SMSSender.sendMessage(number, msg);
            }
        }
    }

    public boolean isTransmitting() {
        //TO DO!
        return false;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!enabled && username == null)
            return;

        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String messageReceived = "";
        if (bundle != null) {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++)

            {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                messageReceived += msgs[i].getMessageBody().toString();
                messageReceived += "\n";
            }

            String senderPhoneNumber = msgs[0].getOriginatingAddress();


            if (username.hasThisPhoneNumber(senderPhoneNumber)) {
                Toast.makeText(context, senderPhoneNumber + messageReceived, Toast.LENGTH_LONG).show();
                startTransmission(senderPhoneNumber);


            } else {
                Toast.makeText(context, "Nie zalapalo sendera", 5 * (Toast.LENGTH_LONG)).show();
            }
        }
    }

    public void startTransmission(String phoneNumber) {
        this.phoneNumber = phoneNumber;

        if (enabledSMSSending) {
            SMSSender.sendMessage(phoneNumber, contex.getString(R.string.TRANSMITTION_MORSE_STARTED));
        }
    }

}
