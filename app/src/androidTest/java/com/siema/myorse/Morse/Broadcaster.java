package Morse;

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
	
	

	// + delegate;
	// + transmitter;
	
	public void sendMessage(String message){
		// TODO doko�czy� funkcj�
	}
	
	String message;
	boolean transmitting;
	Translator translator;
}
