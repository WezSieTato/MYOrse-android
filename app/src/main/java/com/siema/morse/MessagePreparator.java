package com.siema.morse;

/**
 * Klasa przygotowuj�ca wiadomo�� do t�umaczenia (usuwanie zb�dnych znak�w, zmniejszanie, itp.)
 * 
 * @author Quetz
 * 
 */
public class MessagePreparator {
    private Table morseTable;

	public MessagePreparator(Table table) {
		this.morseTable = table;		
	}
	
	public String prepareMessage(String text){
		
		StringBuilder preparedString = new StringBuilder();
		String tempText = text.toLowerCase();

        tempText = tempText.replace('ą', 'a');
        tempText = tempText.replace('ć', 'c');
        tempText = tempText.replace('ę', 'e');
        tempText = tempText.replace('ł', 'l');
        tempText = tempText.replace('ń', 'n');
        tempText = tempText.replace('ó', 'o');
        tempText = tempText.replace('ś', 's');
        tempText = tempText.replace('ź', 'z');
        tempText = tempText.replace('ż', 'z');

        tempText = tempText.replaceAll("\\s+"," ").trim();

        for (int i = 0; i<tempText.length(); i++){
			
			if(morseTable.isContains(tempText.charAt(i)) || tempText.charAt(i) == ' ')
				preparedString.append(tempText.charAt(i));
		}
			
		return preparedString.toString();
		
	}
	
}
