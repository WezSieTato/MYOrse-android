package com.siema.morse.model;

import android.test.InstrumentationTestCase;

/**
 * Created by marcin on 09/12/14.
 */
public class ModelTest extends InstrumentationTestCase {

    public void testDot() throws Exception {
        Char morse = new Dot();
        assertTrue(morse.isEmitSound());
        assertEquals(Char.defaultTime * 1, morse.getTime());
    }

    public void testDash() throws Exception {
        Char morse = new Dash();
        assertTrue(morse.isEmitSound());
        assertEquals(Char.defaultTime * 3, morse.getTime());
    }

    public void testDelayAfterMorseChar() throws Exception {
        Char morse = new DelayAfterMorseChar();
        assertFalse(morse.isEmitSound());
        assertEquals(Char.defaultTime * 1, morse.getTime());
    }

    public void testDelayAfterChar() throws Exception {
        Char morse = new DelayAfterChar();
        assertFalse(morse.isEmitSound());
        assertEquals(Char.defaultTime * 3, morse.getTime());
    }

    public void testDelayAfterWord() throws Exception {
        Char morse = new DelayAfterWord();
        assertFalse(morse.isEmitSound());
        assertEquals(Char.defaultTime * 9, morse.getTime());
    }

}
