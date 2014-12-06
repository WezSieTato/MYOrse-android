package Morse;

public abstract class Char {
	
	boolean emitSound;
	double time;
	
	
	Char(boolean emitSound, double time){
		this.emitSound = emitSound;
		this.time = time;	
	}
	

}
