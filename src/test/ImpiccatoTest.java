package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import java.io.InputStream;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import pac.minigiochi.Impiccato;


public class ImpiccatoTest {
    
    private Impiccato impiccato;

    @Before
    public void initClass() {
        impiccato = new Impiccato("EasyTest",1,"1");
    }

    @Test
    public void RankOneShouldDispayFirstAndLastChars() {
        impiccato.rank = 1;
        impiccato.inizializza();
        assertEquals("E______t", impiccato.guessed.toString());
    }
    @Test
    public void RankTwoShouldDispayFirstChar() {
        impiccato.rank = 2;
        impiccato.inizializza();
        assertEquals("E_______", impiccato.guessed.toString());
    }
    @Test
    public void OtherRankShouldDispayanyChars() {
        impiccato.rank = 0;
        impiccato.inizializza();
        assertEquals("________", impiccato.guessed.toString());
    }
    
    @Test
    public void CorrectAnswerShouldStopPlayWithTrue() {
        InputStream in = new ByteArrayInputStream(impiccato.secret.getBytes());
        System.setIn(in);
        assertTrue(impiccato.play(new Scanner(System.in)));
    }

    @Test
    public void wrongAnswerShouldStopPlayWithFalse() {
        impiccato.remainingAttempts = 9;
        InputStream in = new ByteArrayInputStream("Hello Guys".getBytes());
        System.setIn(in);
        assertFalse(impiccato.play(new Scanner(System.in)));
    }

    @Test
    public void wordExitShouldStopPlayWithFalse() {
        impiccato.remainingAttempts = 9;
        InputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);
        assertFalse(impiccato.play(new Scanner(System.in)));
    }

    @Test
    public void singleWrongCharShouldDecreaseAttemps() {
        impiccato.remainingAttempts = 10;
        String inputs = "m" + System.lineSeparator() + 
        "a" + System.lineSeparator() +
        "r" + System.lineSeparator() +
        impiccato.secret + System.lineSeparator(); 

        InputStream in = new ByteArrayInputStream(inputs.getBytes());
        System.setIn(in);
        assertTrue(impiccato.play(new Scanner(System.in)));
        assertEquals(8, impiccato.remainingAttempts);
    }


    
}
