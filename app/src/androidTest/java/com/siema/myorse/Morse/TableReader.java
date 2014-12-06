package Morse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Char;
import Model.DelayAfterMorseChar;
import Model.Dot;
import Model.Dash;

/**
 * Klasa posiadaj¹ca statyczn¹ funkcjê zwracaj¹c¹ tablicê z wczytanymi parametrami z pliku
 * 
 * @author Quetz
 * 
 */
public class TableReader {
	private static BufferedReader reader;

	public static Table readFile(String path){
		File file = new File(path);
		Table table = new Table();
		
		try {
			
			reader = new BufferedReader(new FileReader(file));
			String text = null;
	
			while ((text = reader.readLine()) != null) {
				
				List<Char> array = new ArrayList<Char>();
				
				for(int i = 1; i<text.length(); i++) {
					
					if(text.charAt(i) == '.') {
						array.add(new Dot());
						if(i != text.length()-1)
							array.add(new DelayAfterMorseChar());
					}
					
					else if (text.charAt(i) == '-') {
						array.add(new Dash());
						if(i != text.length()-1)
							array.add(new DelayAfterMorseChar());						
					}
				}
				
			table.addCode(array, text.charAt(0));
					
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}

}
