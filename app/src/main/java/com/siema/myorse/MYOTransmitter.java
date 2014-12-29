package com.siema.myorse;

import com.siema.morse.Transmitter;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

import java.util.ArrayList;

/**
 * Created by marcin on 10/12/14.
 */
public class MYOTransmitter implements Transmitter {

    @Override
    public boolean transmit(double time) {
        return false;
    }

    @Override
    public void transmitShort() {
        vibrate(true);
    }

    @Override
    public void transmitLong() {
        vibrate(false);
    }

    private void vibrate(boolean isShort){
        Hub hub = Hub.getInstance();
        ArrayList< Myo > devices = hub.getConnectedDevices();
        Myo myo = devices.get(0);
        myo.vibrate(isShort ? Myo.VibrationType.SHORT : Myo.VibrationType.LONG);
    }
}
