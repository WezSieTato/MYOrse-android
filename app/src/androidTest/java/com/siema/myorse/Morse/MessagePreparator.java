package Morse;

/**
 * Klasa przygotowuj¹ca wiadomoœæ do t³umaczenia (usuwanie zbêdnych znaków, zmniejszanie, itp.)
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
