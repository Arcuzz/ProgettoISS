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
    private String secret;

    @Before
    public void initClass() {
        impiccatoModel = new ImpiccatoModel(1,"1");
        secret = impiccatoModel.getSecret();
        impiccato = new ImpiccatoController(impiccatoModel, new ImpiccattoView());
    }

    @Test
    public void RankOneShouldDispayFirstAndLastChars() {
        impiccatoModel.rank = 1;
        impiccatoModel.inizializza();

        String str = "" + secret.charAt(0);
        for (var i = 1; i <= secret.length() - 2; i++) {
            str += "_";
        }
        str += secret.charAt(secret.length() - 1);
        assertEquals(str, impiccatoModel.guessed.toString());
    }
    @Test
    public void RankTwoShouldDispayFirstChar() {
        impiccatoModel.rank = 2;
        impiccatoModel.inizializza();

        String str = "" + secret.charAt(0);
        for (var i = 1; i <= secret.length() - 1; i++) {
            str += "_";
        }

        assertEquals(str, impiccatoModel.guessed.toString());
    }
    @Test
    public void OtherRankShouldDispayanyChars() {
        impiccatoModel.rank = 0;
        impiccatoModel.inizializza();

        String str = "";
        for (var i = 0; i <= secret.length() - 1; i++) {
            str += "_";
        }
        assertEquals(str, impiccatoModel.guessed.toString());
    }
    
    @Test
    public void CorrectAnswerShouldStopPlayWithTrue() {
        String inputs =  
            "s" + System.lineSeparator() + System.lineSeparator() +
            impiccatoModel.getSecret() + System.lineSeparator() + System.lineSeparator();
    
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
        int attemps = 10;
        String inputs = System.lineSeparator() + "m" + System.lineSeparator() + 
        impiccatoModel.getSecret() + System.lineSeparator() + System.lineSeparator();

        InputStream in = new ByteArrayInputStream(inputs.getBytes());
        System.setIn(in);
        assertTrue(impiccato.play(new Scanner(System.in), "Gloria"));
        assertEquals(attemps, impiccatoModel.remainingAttempts);
    }
}
