package Model;

import Morse.Table;
import Morse.TableReader;



public class Main {

	public static void main(String[] args) {

		Table table = TableReader.readFile("morse.txt");
		
		
		 System.out.println("END " + table.codeForKey('0'));
	}

}
