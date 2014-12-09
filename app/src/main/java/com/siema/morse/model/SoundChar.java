package com.siema.morse.model;

/**
 * Klasa znaku w�a�ciwego (sygnalizowanego d�wi�kiem/wibracj�)
 * 
 * @author Quetz
 * 
 */
public abstract class SoundChar extends Char {
    protected SoundChar(long time) {
		super(true, time);	
	}

    protected SoundChar() {
		super(true);
	}
}
