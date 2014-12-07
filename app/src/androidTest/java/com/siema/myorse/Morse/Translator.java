package Morse;

import java.util.ArrayList;
import java.util.List;

import Model.Char;
import Model.DelayAfterChar;
import Model.DelayAfterWord;

/**
 * Klasa t³umacz¹ca wiadomoœci w String'u na jêzyk Morse'a
 * 
 * @author Quetz
 * 
 */
public class Translator {

	public Translator(Table table) {
		this.morseTable = table;		
	}
	
	public List<Char> translate(String text){
		List<Char> message = new ArrayList<Char>();
		
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
			if(!array.add(new DelayAfterChar()))
				return false;
			
		return true;	
	}
	
	private boolean addWordSpaceToArray(List<Char> array, Character c){
		if(!array.add(new DelayAfterWord()))
			return false;	
		
		return true;	
	}
	
	protected Table morseTable;
}
