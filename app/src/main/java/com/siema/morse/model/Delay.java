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
	Delay(long time){
		super(false, time);
	}
	
	Delay(){
		super(false);
	}
}
