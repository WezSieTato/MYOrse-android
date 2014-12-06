package Model;

/**
 * Klasa znaku
 * 
 * @author Quetz
 * 
 * 
 * 	@param emitSound okreœla czy ma byæ emitowany dŸwiêk
 *  @param time okreœla d³ugoœæ trwania znaku (sygna³u)
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
