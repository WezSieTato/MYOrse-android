package com.siema.morse;

public interface BroadcasterDelegate {

	public void broadcasterDidEndTransmition(Broadcaster morseTransmitter);
    public void broadcasterDidInterruptTransmition(Broadcaster morseTransmitter);
}
