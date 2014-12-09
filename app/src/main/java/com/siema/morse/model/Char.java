package com.siema.morse.model;

/**
 * Klasa znaku
 * 
 * @author Quetz
 * 
 * 
 * 	@param emitSound okre�la czy ma by� emitowany d�wi�k
 *  @param time okre�la d�ugo�� trwania znaku (sygna�u)
 *  @param defaultTime defaultowy czas w konstruktorze bezargumentowym
 */
public abstract class Char {
	public final static double defaultTime = 1.0;
	
	protected boolean emitSound;
	protected double time;
	
	Char(boolean emitSound, double time){
		this.emitSound = emitSound;
		this.time = time;	
	}
	
	Char(boolean emitSound){
		this.emitSound = emitSound;
		this.time = defaultTime;	
	}

	boolean getEmitSound() {
		return emitSound;
	}
	
	double getTime(){
		return time;
	}

}
