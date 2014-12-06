package Model;

/**
 * Klasa op�nienia mi�dzy znakami Morse'a (kreski/kropki)
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
