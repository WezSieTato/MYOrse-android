package com.siema.morse.model;

/**
 * Klasa op�nienia
 * 
 * @author Quetz
 * 
 *
 * 
 */
public abstract class Delay extends Char {
	protected Delay(long time){
		super(false, time);
	}

    protected Delay(){
		super(false);
	}
}
