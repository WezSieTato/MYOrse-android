package com.siema.morse;

public interface BroadcasterDelegate {

	public void broadcasterDidEndTransmission(Broadcaster morseTransmitter);
    public void broadcasterDidInterruptTransmission(Broadcaster morseTransmitter);
}
