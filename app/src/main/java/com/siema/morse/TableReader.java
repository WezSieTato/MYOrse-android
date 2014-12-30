package com.siema.morse;

import com.siema.morse.model.Char;
import com.siema.morse.model.Dash;
import com.siema.morse.model.Dot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa posiadająca statyczną funkcję zwracającą tablicę z wczytanymi parametrami z pliku
 *
 * @author Quetz
 */
public class TableReader {

    public static Table readFile(InputStream is) {
        Table table = new Table();

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String text;
            Dot dot = new Dot();
            Dash dash = new Dash();

            while ((text = reader.readLine()) != null) {

                List<Char> array = new ArrayList<Char>();

                for (int i = 1; i < text.length(); i++) {

                    if (text.charAt(i) == '.') {
                        array.add(dot);
                    } else if (text.charAt(i) == '-') {
                        array.add(dash);
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
