package com.siema.tests;

/**
 * Created by marcin on 09/12/14.
 */

import com.siema.RobolectricGradleTestRunner;
import com.siema.morse.model.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
public class RobolectricModelTest {

    @Test
    public void testDot() throws Exception {
        Char morse = new Dot();
        assertTrue(morse.isEmitSound());
        assertEquals(Char.defaultTime * 1, morse.getTime());
    }

    @Test
    public void testDash() throws Exception {
        Char morse = new Dash();
        assertTrue(morse.isEmitSound());
        assertEquals(Char.defaultTime * 3, morse.getTime());
    }

    @Test
    public void testDelayAfterMorseChar() throws Exception {
        Char morse = new DelayAfterMorseChar();
        assertFalse(morse.isEmitSound());
        assertEquals(Char.defaultTime * 1, morse.getTime());
    }

    @Test
    public void testDelayAfterChar() throws Exception {
        Char morse = new DelayAfterChar();
        assertFalse(morse.isEmitSound());
        assertEquals(Char.defaultTime * 3, morse.getTime());
    }

    @Test
    public void testDelayAfterWord() throws Exception {
        Char morse = new DelayAfterWord();
        assertFalse(morse.isEmitSound());
        assertEquals(Char.defaultTime * 9, morse.getTime());
    }

}
