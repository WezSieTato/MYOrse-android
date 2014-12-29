package com.siema.myorse;

import com.siema.morse.Receiver;
import com.siema.morse.Table;
import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;

/**
 * Created by marcin on 27/12/14.
 */
public class MYOReceiver extends AbstractDeviceListener {

    private Receiver morseReceiver;
    private String phoneNumber;
    private MYOReceiverDelegate delegate;
    private boolean firstPose;

    public MYOReceiver(Table morseTable) {
        this.morseReceiver = new Receiver(morseTable);

    }

    public void start(String phoneNumber){
        this.phoneNumber = phoneNumber;
        firstPose = true;
        Hub.getInstance().addListener(this);
        Hub.getInstance().setLockingPolicy(Hub.LockingPolicy.NONE);
        for (Myo myo : Hub.getInstance().getConnectedDevices()){
            myo.unlock(Myo.UnlockType.HOLD);
        }
    }

    public void stop(){
        Hub.getInstance().removeListener(this);
        morseReceiver.clear();
        for (Myo myo : Hub.getInstance().getConnectedDevices()){
            myo.lock();
        }
    }

    @Override
    public void onPose(Myo myo, long l, Pose pose) {
        if(firstPose){
            firstPose = false;
//            return;
        }

        switch (pose) {
            case FIST:
                morseReceiver.putDot();
                myo.vibrate(Myo.VibrationType.SHORT);
                break;

            case FINGERS_SPREAD:
                morseReceiver.putDash();
                myo.vibrate(Myo.VibrationType.LONG);
                break;

            case WAVE_IN:
            case WAVE_OUT:
                if(morseReceiver.putDelay())
                   sendMessage();
                myo.notifyUserAction();
                break;


            case REST:
            case UNKNOWN:
            case DOUBLE_TAP:


        }
    }

    @Override
    public void onConnect(Myo myo, long timestamp) {
        super.onConnect(myo, timestamp);
    }

    @Override
    public void onDisconnect(Myo myo, long timestamp) {
        super.onDisconnect(myo, timestamp);
    }

    private void sendMessage(){
        String message = morseReceiver.getMessage();
        SMSSender.sendMessage(phoneNumber, message);
        if(delegate != null)
            delegate.messageSended(message);
        stop();
    }

    public MYOReceiverDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(MYOReceiverDelegate delegate) {
        this.delegate = delegate;
    }
}
