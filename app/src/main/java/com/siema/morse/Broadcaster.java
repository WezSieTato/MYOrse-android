package com.siema.morse;

/**
 * Klasa odpowiedzialna za transmisj� informacji Morse'm
 * 
 * @author Quetz
 * 
 */
public class Broadcaster {
	public Broadcaster(Translator translator){
		this.translator = translator;		
	}

	private BroadcasterDelegate broadcasterDelegate;
	private Transmitter transmitter;
	
	public void sendMessage(String message){
		// TODO doko�czy� funkcj�
	}
	
	String message;
	boolean transmitting;
	Translator translator;
}
