package Morse;

/**
 * Klasa przygotowuj�ca wiadomo�� do t�umaczenia (usuwanie zb�dnych znak�w, zmniejszanie, itp.)
 * 
 * @author Quetz
 * 
 */
public class MessagePreparator {

	public MessagePreparator(Table table) {
		this.morseTable = table;		
	}
	
	/**
	 * Message - you are not prepared! 
	 * ~by Illidan Stormrage
	 * 
	 * @param text
	 * @return
	 */
	public String prepareMessage(String text){
		
		StringBuilder preparedString = new StringBuilder();
		String tempText = text.toLowerCase();
		
		for (int i = 0; i<tempText.length(); i++){
			
			if(morseTable.isContains(tempText.charAt(i)) || tempText.charAt(i) == ' ')
				preparedString.append(tempText.charAt(i));
		}
			
		return preparedString.toString();
		
	}
	
	protected Table morseTable;
}
