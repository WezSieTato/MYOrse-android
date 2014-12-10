package com.siema.morse;

import java.util.ArrayList;
import java.util.List;

import com.siema.morse.model.*;


/**
 * Klasa t�umacz�ca wiadomo�ci w String'u na j�zyk Morse'a
 * 
 * @author Quetz
 * 
 */
public class Translator {

    private Table morseTable;
    private DelayAfterChar delayChar;
    private DelayAfterWord delayWord;
    private MessagePreparator preparator;

	public Translator(Table table) {
		this.morseTable = table;
        delayChar = new DelayAfterChar();
        delayWord = new DelayAfterWord();
        preparator = new MessagePreparator(table);
	}
	
	public List<Char> translate(String text){
		List<Char> message = new ArrayList<Char>();

        text = preparator.prepareMessage(text);
		
		for(int i = 0; i < text.length(); i++) {
			Character c = text.charAt(i);
			
			if(c == ' ')
			{
				if(!addWordSpaceToArray(message, c))
					System.out.println("TRANSLATOR ERROR!");
			}
			else
			{
				if(i != text.length() - 1 && text.charAt(i+1) != ' ') {
					if(!addMorseCharToArray(message, c, true))
						System.out.println("TRANSLATOR ERROR!");
				}
				else {
					if(!addMorseCharToArray(message, c, false))
						System.out.println("TRANSLATOR ERROR!");
				}
			}
		}
	
		return message;
	}
	
	private boolean addMorseCharToArray(List<Char> array, char c, boolean delay){
		List<Char> list = morseTable.codeForKey(c);
		
		for(Char e : list){
			if(!array.add(e))
				return false;	
		}
		
		if(delay)
			if(!array.add(delayChar))
				return false;
			
		return true;	
	}
	
	private boolean addWordSpaceToArray(List<Char> array, Character c){
		if(!array.add(delayWord))
			return false;	
		
		return true;	
	}
	

}
