package com.siema.morse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.siema.morse.model.*;

/**
 * Klasa posiadaj�ca statyczn� funkcj� zwracaj�c� tablic� z wczytanymi parametrami z pliku
 *
 * @author Quetz
 */
public class TableReader {

    public static Table readFile(String path) {
        File file = new File(path);
        Table table = new Table();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text = null;
            Dot dot = new Dot();
            Dash dash = new Dash();
            DelayAfterMorseChar delay = new DelayAfterMorseChar();

            while ((text = reader.readLine()) != null) {

                List<Char> array = new ArrayList<Char>();

                for (int i = 1; i < text.length(); i++) {

                    if (text.charAt(i) == '.') {
                        array.add(dot);
                    } else if (text.charAt(i) == '-') {
                        array.add(dash);
                    }
                    if (i != text.length() - 1)
                        array.add(delay);
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
