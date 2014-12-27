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
	public final static long defaultTime = 750;
	
	private boolean emitSound;
    private long time;

    protected Char(boolean emitSound, long time){
		this.emitSound = emitSound;
		this.time = time;	
	}

    protected Char(boolean emitSound){
		this.emitSound = emitSound;
		this.time = defaultTime;	
	}

	public boolean isEmitSound() {
		return emitSound;
	}
	
	public long getTime(){
		return time;
	}

}
