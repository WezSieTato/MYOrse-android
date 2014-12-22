package com.siema.morse;

public interface Transmitter {
    /**
     *
     * @param time
     * @return true if function really transmit signal
     */
    public boolean transmit(double time);

    public void transmitShort();
    public void transmitLong();
}