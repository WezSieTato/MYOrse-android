package com.siema.morse;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.siema.morse.model.Char;

/**
 * Klasa tablicy zawieraj�cej map� znak�w i ich zapisu za pomoc� Morse'a
 * 
 * @author Quetz
 * 
 */
public class Table{
    private Map<Character, List<Char> > content;

    public Table(){
		content = new HashMap<Character, List<Char>>();
	}
	
	public void addCode(List<Char> array, Character character){
		content.put(character, array);
	}
	
	public List<Char> codeForKey(Character character){
		return content.get(character);
	}
	
	public int getSize(){
		return content.size();
	}
	
	public boolean isContains(Character character){
		return content.containsKey(character);
	}

}
