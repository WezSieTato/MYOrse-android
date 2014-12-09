package com.siema.morse.model;

/**
 * Klasa kreski
 * 
 * @author Quetz
 * 
 * 
 */
public class Dash extends SoundChar {
	public Dash(long time){
		super(3*time);	
	}

	public Dash() {
        super(3 * defaultTime);
	}
}
