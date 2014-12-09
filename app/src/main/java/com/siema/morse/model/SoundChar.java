package com.siema.morse.model;

/**
 * Klasa znaku w�a�ciwego (sygnalizowanego d�wi�kiem/wibracj�)
 * 
 * @author Quetz
 * 
 */
public abstract class SoundChar extends Char {
	SoundChar(double time) {
		super(true, time);	
	}

	SoundChar() {
		super(true);
	}
}
