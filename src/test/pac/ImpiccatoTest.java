package test.pac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import java.io.InputStream;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import pac.minigiochi.ImpiccatoController;
import pac.minigiochi.ImpiccatoModel;
import pac.minigiochi.ImpiccattoView;


public class ImpiccatoTest {
    
    private ImpiccatoController impiccato;
    private ImpiccatoModel impiccatoModel;

    @Before
    public void initClass() {
        impiccatoModel = new ImpiccatoModel("EasyTest",1,"1");
        impiccato = new ImpiccatoController(impiccatoModel, new ImpiccattoView());
    }

    @Test
    public void RankOneShouldDispayFirstAndLastChars() {
        impiccatoModel.rank = 1;
        impiccatoModel.inizializza();
        assertEquals("E______t", impiccatoModel.guessed.toString());
    }
    @Test
    public void RankTwoShouldDispayFirstChar() {
        impiccatoModel.rank = 2;
        impiccatoModel.inizializza();
        assertEquals("E_______", impiccatoModel.guessed.toString());
    }
    @Test
    public void OtherRankShouldDispayanyChars() {
        impiccatoModel.rank = 0;
        impiccatoModel.inizializza();
        assertEquals("________", impiccatoModel.guessed.toString());
    }
    
    @Test
    public void CorrectAnswerShouldStopPlayWithTrue() {
        String inputs =  
            "s" + System.lineSeparator() + System.lineSeparator() +
            impiccatoModel.secret + System.lineSeparator() + System.lineSeparator();
    
        InputStream in = new ByteArrayInputStream(inputs.getBytes());
        System.setIn(in);
        assertTrue(impiccato.play(new Scanner(System.in), "Gloria"));
    }

    @Test
    public void wrongAnswerShouldStopPlayWithFalse() {
        impiccatoModel.remainingAttempts = 9;
        String inputs =  
            "s" + System.lineSeparator() + System.lineSeparator() +
            "Hello Guys" + System.lineSeparator() + System.lineSeparator();

        InputStream in = new ByteArrayInputStream(inputs.getBytes() );
        System.setIn(in);
        assertFalse(impiccato.play(new Scanner(System.in), "Giovanni"));
    }

    @Test
    public void wordExitShouldStopPlayWithFalse() {
        impiccatoModel.remainingAttempts = 9;
        String inputs =  
        "s" + System.lineSeparator() + System.lineSeparator() +
        "exit" + System.lineSeparator() + System.lineSeparator();

        InputStream in = new ByteArrayInputStream(inputs.getBytes());
        System.setIn(in);
        assertFalse(impiccato.play(new Scanner(System.in), "Andrea"));
    }

    @Test
    public void singleWrongCharShouldDecreaseAttemps() {
        impiccatoModel.remainingAttempts = 10;
        String inputs = "m" + System.lineSeparator() + 
        "a" + System.lineSeparator() +
        "r" + System.lineSeparator() +
        impiccatoModel.secret + System.lineSeparator() + System.lineSeparator();

        InputStream in = new ByteArrayInputStream(inputs.getBytes());
        System.setIn(in);
        assertTrue(impiccato.play(new Scanner(System.in), "Gloria"));
        assertEquals(9, impiccatoModel.remainingAttempts);
    }


    
}
