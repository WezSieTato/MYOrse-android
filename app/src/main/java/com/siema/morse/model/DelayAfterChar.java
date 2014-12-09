package com.siema.morse.model;

/**
 * Klasa op�nienia mi�dzy znakami (literami, interpunkcj�, liczbami)
 * 
 * @author Quetz
 * 
 * 
 * 
 */
public class DelayAfterChar extends Delay {
	public DelayAfterChar(double time) {
		super(time);
	}

	public DelayAfterChar() {
        super(defaultTime * 3);
	}
}
