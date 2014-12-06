package Model;

/**
 * Klasa opóŸnienia
 * 
 * @author Quetz
 * 
 *
 * 
 */
public abstract class Delay extends Char {
	Delay(double time){
		super(false, time);
	}
	
	Delay(){
		super(false);
	}
}
