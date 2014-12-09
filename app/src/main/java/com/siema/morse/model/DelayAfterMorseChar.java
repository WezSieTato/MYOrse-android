package com.siema.morse.model;

/**
 * Klasa op�nienia mi�dzy znakami Morse'a (kreski/kropki)
 * 
 * @author Quetz
 * 
 * 
 * 
 */
public class DelayAfterMorseChar extends Delay {
	public DelayAfterMorseChar(long time) {
		super(time);
	}

	public DelayAfterMorseChar() {
        super(defaultTime);
	}
}
