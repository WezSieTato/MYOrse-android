package Model;

/**
 * Klasa znaku w³aœciwego (sygnalizowanego dŸwiêkiem/wibracj¹)
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
