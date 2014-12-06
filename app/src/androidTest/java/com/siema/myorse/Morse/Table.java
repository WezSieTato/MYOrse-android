package Morse;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import Model.Char;

/**
 * Klasa tablicy zawieraj¹cej mapê znaków i ich zapisu za pomoc¹ Morse'a
 * 
 * @author Quetz
 * 
 */
public class Table{
	
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
	
	protected Map<Character, List<Char> > content;

}
