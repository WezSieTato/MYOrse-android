package com.siema.morse;


import com.siema.morse.model.Char;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Character charForCode(List<Char> code){
        for (Map.Entry<Character, List<Char>> entry : content.entrySet()) {
            if (code.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
	
	public int getSize(){
		return content.size();
	}
	
	public boolean isContains(Character character){
		return content.containsKey(character);
	}

}
