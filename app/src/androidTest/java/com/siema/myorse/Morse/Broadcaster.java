package Morse;

/**
 * Klasa odpowiedzialna za transmisjê informacji Morse'm
 * 
 * @author Quetz
 * 
 */
public class Broadcaster {
	public Broadcaster(Translator translator){
		this.translator = translator;		
	}
	
	

	BroadcasterDelegate broadcasterDelegate;
	Transmitter transmitter;
	
	public void sendMessage(String message){
		// TODO dokoñczyæ funkcjê
	}
	
	String message;
	boolean transmitting;
	Translator translator;
}
