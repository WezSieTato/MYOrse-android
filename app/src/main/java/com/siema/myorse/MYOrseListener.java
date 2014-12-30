package com.siema.myorse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.siema.morse.Broadcaster;
import com.siema.morse.BroadcasterDelegate;
import com.siema.morse.Table;
import com.siema.morse.TableReader;
import com.siema.morse.Translator;
import com.thalmic.myo.Hub;

/**
 * Created by marcin on 08/12/14.
 */
public class MYOrseListener extends BroadcastReceiver implements BroadcasterDelegate, MYOReceiverDelegate {

    private boolean enabled;
    private boolean transmitting;
    private boolean enabledSMSSending;
    private ContactInfo username;
    private String phoneNumber;
    private Context context;
    private Broadcaster broadcaster;
    private MYOReceiver receiver;

    public MYOrseListener(Context context) {
        super();
        username = null;
        enabled = false;
        phoneNumber = null;
        this.context = context;

        Table morseTable = TableReader.readFile(context.getResources().openRawResource(R.raw.morse_table));
        Translator translator = new Translator(morseTable);
        broadcaster = new Broadcaster(translator);
        broadcaster.setBroadcasterDelegate(this);
        broadcaster.setTransmitter(new MYOTransmitter());
        receiver = new MYOReceiver(morseTable);
        receiver.setDelegate(this);
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

    public void start(){
        setEnabled(true);
    }

    public void stop(){
        setEnabled(false);
    }
    private void setEnabled(boolean enabled) {

        if(this.enabled == enabled)
            return;

        if(this.enabled && !enabled && isTransmitting()){
            broadcaster.stopTransmission();
            receiver.stop();
        }

        if (enabledSMSSending) {
            String msg = context.getString(enabled ? R.string.START_MYORSE_MESSAGE : R.string.STOP_MYORSE_MESSAGE);

            for (String number : username.getPhoneNumbers()) {
            Toast.makeText(context, "Wysylam SMSa powiadamiającego: " + number, 5 * (Toast.LENGTH_LONG)).show();
                SMSSender.sendMessage(number, msg);
            }
        }
        this.enabled = enabled;
    }

    public boolean isTransmitting() {
        return transmitting;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!enabled || username == null || isTransmitting())
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
                startTransmission(senderPhoneNumber, messageReceived);

            } else {
                Toast.makeText(context, "Nie zalapalo sendera", 5 * (Toast.LENGTH_LONG)).show();
            }
        }
    }

    public void startTransmission(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        transmitting = true;
        if (enabledSMSSending) {
            SMSSender.sendMessage(phoneNumber, context.getString(R.string.TRANSMITTION_MORSE_STARTED));
        }

        broadcaster.sendMessage(message);

    }

    @Override
    public void broadcasterDidEndTransmission(Broadcaster morseTransmitter) {

        Runnable task = new Runnable() {
            private byte count = 0;
            private final Handler handler = new Handler();
            @Override
            public void run() {
                Hub.getInstance().getConnectedDevices().get(0).notifyUserAction();
                ++count;
                if(count < 3)
                    handler.postDelayed(this, 200);
                else
                  receiver.start(phoneNumber);
            }
        };
        task.run();

    }

    @Override
    public void broadcasterDidInterruptTransmission(Broadcaster morseTransmitter) {
        if(enabledSMSSending)
            SMSSender.sendMessage(phoneNumber, context.getString(R.string.INTERRUPT_MYORSE_MESSAGE));
        transmitting = false;
        phoneNumber = null;
    }

    @Override
    public void messageSended(String message) {
        if((message == null || message.length() == 0) && enabledSMSSending)
                SMSSender.sendMessage(phoneNumber, context.getString(R.string.TRANSMITTION_MORSE_ENDED));
        transmitting = false;
        phoneNumber = null;
    }
}
