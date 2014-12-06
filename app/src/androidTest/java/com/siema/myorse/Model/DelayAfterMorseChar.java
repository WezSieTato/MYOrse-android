package Model;

/**
 * Klasa opóŸnienia miêdzy znakami Morse'a (kreski/kropki)
 * 
 * @author Quetz
 * 
 * 
 * 
 */
public class DelayAfterMorseChar extends Delay {
	public DelayAfterMorseChar(double time) {
		super(3*time);
	}

	public DelayAfterMorseChar() {
	}
}
