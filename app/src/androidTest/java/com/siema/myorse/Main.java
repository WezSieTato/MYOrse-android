

import Morse.MessagePreparator;
import Morse.Table;
import Morse.TableReader;
import Morse.Translator;



public class Main {

	public static void main(String[] args) {

		Table table = TableReader.readFile("morse.txt");
		MessagePreparator mp = new MessagePreparator(table);
		Translator translator = new Translator(table);
		
		String newmsg = mp.prepareMessage("Siema! Co tam?!!!! x x x");
		
		
		 System.out.println(translator.translate(newmsg));
	}

}
