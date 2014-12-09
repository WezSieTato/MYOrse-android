package com.siema.morse.model;

/**
 * Klasa op�nienia mi�dzy s�owami
 * 
 * @author Quetz
 * 
 * 
 */
public class DelayAfterWord extends Delay {
	public DelayAfterWord(double time) {
		super(9*time);
	}

	public DelayAfterWord() {
        super(defaultTime * 9);
	}
}
