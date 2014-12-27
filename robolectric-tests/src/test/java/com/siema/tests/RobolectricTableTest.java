package com.siema.tests;

import com.siema.RobolectricGradleTestRunner;
import com.siema.morse.MessagePreparator;
import com.siema.morse.Table;
import com.siema.morse.TableReader;
import com.siema.morse.Translator;
import com.siema.morse.model.*;
import com.siema.myorse.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by marcin on 10/12/14.
 */

@RunWith(RobolectricGradleTestRunner.class)
public class RobolectricTableTest {

    @Test
    public void testTable() throws Exception {
        Table morseTable = TableReader.readFile(Robolectric.application.getResources().openRawResource(R.raw.morse_table));
        assertEquals(36, morseTable.getSize());

        List< Char > code = morseTable.codeForKey('c');

        assertTrue(code.get(0) instanceof Dash );
//        assertTrue(code.get(1) instanceof DelayAfterMorseChar );
        assertTrue(code.get(1) instanceof Dot );
//        assertTrue(code.get(3) instanceof DelayAfterMorseChar );
        assertTrue(code.get(2) instanceof Dash );
//        assertTrue(code.get(5) instanceof DelayAfterMorseChar );
        assertTrue(code.get(3) instanceof Dot );

        assertEquals(4, code.size());
    }

    @Test
    public void testMessagePreparator() throws Exception {
        Table morseTable = TableReader.readFile(Robolectric.application.getResources().openRawResource(R.raw.morse_table));
        MessagePreparator preparator = new MessagePreparator(morseTable);
        String message;

        message = "ABCD efgh IjKm";
        message = preparator.prepareMessage(message);
        assertEquals("abcd efgh ijkm", message);

        message = "zazółć gęślą jaźń";
        message = preparator.prepareMessage(message);
        assertEquals("zazolc gesla jazn", message);

        message = "uwaga! kropka. przecinek, wszystko naraz !@#!@$$%^$#%^!$^%$^";
        message = preparator.prepareMessage(message);
        assertEquals("uwaga kropka przecinek wszystko naraz ", message);

        message = "siem\ta teraz\nbedzie ty";
        message = preparator.prepareMessage(message);
        assertEquals("siem a teraz bedzie ty", message);
    }

    @Test
    public  void testMorseTlanslation() throws Exception {
        Table morseTable = TableReader.readFile(Robolectric.application.getResources().openRawResource(R.raw.morse_table));
        Translator translator = new Translator(morseTable);
        String message = "a, bo!";

        List< Char > code = translator.translate(message);

        //a
        assertTrue(code.get(0) instanceof Dot );
//        assertTrue(code.get(1) instanceof DelayAfterMorseChar );
        assertTrue(code.get(1) instanceof Dash );
        assertTrue(code.get(2) instanceof DelayAfterWord );

        //b
        assertTrue(code.get(3) instanceof Dash );
        assertTrue(code.get(4) instanceof Dot );
        assertTrue(code.get(5) instanceof Dot );
        assertTrue(code.get(6) instanceof Dot );
        assertTrue(code.get(7) instanceof DelayAfterChar );

        //c
        assertTrue(code.get(8) instanceof Dash );
//        assertTrue(code.get(7) instanceof DelayAfterMorseChar );
        assertTrue(code.get(9) instanceof Dash );
//        assertTrue(code.get(9) instanceof DelayAfterMorseChar );
        assertTrue(code.get(10) instanceof Dash );

        assertEquals(11, code.size());

    }

}
