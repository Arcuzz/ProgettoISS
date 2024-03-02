package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import pac.Aiutante;
import pac.Grafica;
import pac.Prova;

public class ProvaTest {
    Prova prova;
    OutputStream os;
    PrintStream print;
    
    
    @Before
    public void initClass() {
       prova = new Prova("Question", "Response", "Help", 3);

       os = new ByteArrayOutputStream();
       print = new PrintStream(os);
       System.setOut(print);
    }

    @Test
    public void testDaiIndizio() {
        prova.aiutante = new Aiutante(3);
        prova.aiutante.attivaAiuto("Italiano");
        prova.daiIndizio();
        
        String actualOutput = os.toString();
        String expected = Grafica.sep+"INDIZIO: "+ prova.indizioPrincipale;

        assertEquals(expected.trim(), actualOutput.trim());
    }

    @Test
    public void testFaiDomandaWrong() {
        try {
            InputStream in = new ByteArrayInputStream("ciao".getBytes());
            System.setIn(in);
            prova.faiDomanda(new Scanner(System.in));
        }
        catch (Exception ex){
            String expected = 
                Grafica.sep + prova.domanda + "\n" +
                "\n" + Grafica.sep + "Risposta: \n" +
                Grafica.sep + ">> " +
                Grafica.sep + "Risposta sbagliata! Riprova o scrivi \"exit\" per uscire\n\n" + 
                Grafica.sep + prova.domanda + "\n" +
                "\n" + Grafica.sep + "Risposta: \n" +
                Grafica.sep + ">> ";
            
            String actualOutput = os.toString();
            assertEquals(expected.trim(), actualOutput.trim());
        }
    }
    
    @Test
    public void testFaiDomandaHint() {
        try {
            InputStream in = new ByteArrayInputStream("help".getBytes());
            System.setIn(in);
            prova.faiDomanda(new Scanner(System.in));
        }
        catch (Exception ex){
            String expected = 
                Grafica.sep + prova.domanda + "\n" +
                "\n" + Grafica.sep + "Risposta: \n" +
                Grafica.sep + ">> " +
                Grafica.sep+"LA RISPOSTA E': " + prova.risposta + "\n" +
                Grafica.sep + prova.domanda + "\n" +
                "\n" + Grafica.sep + "Risposta: \n" +
                Grafica.sep + ">> ";
            
            String actualOutput = os.toString();
            assertEquals(expected.trim(), actualOutput.trim());
        }
    }

    @Test
    public void testFaiDomandaRight() {
        try {
            InputStream in = new ByteArrayInputStream(prova.risposta.getBytes());
            System.setIn(in);
            prova.faiDomanda(new Scanner(System.in));
            prova.contaErrori = 1;
        }
        catch (Exception ex){
            String actualOutput = os.toString();
            assertEquals(actualOutput, Grafica.sep+"Risposta esatta con 1 errori! Vai avanti");
        }
    }

    @Test
    public void testFaiDomandaExit() {
        try {
            InputStream in = new ByteArrayInputStream("exit".getBytes());
            System.setIn(in);
            prova.faiDomanda(new Scanner(System.in));
        }
        catch (Exception ex){
            String actualOutput = os.toString();
            assertEquals(actualOutput, Grafica.sep+"Sei uscito senza rispondere correttamente, ritorno al movimento");
        }
    }

    @Test
    public void testSummary() {
        System.setOut(print);
        prova.summary();
        
        String actualOutput = os.toString();
        String expected = 
        "Domanda: " + prova.domanda +
        "\nRisposta: " + prova.risposta +
        "\nDifficoltà: " + prova.ranking;

        assertEquals(expected.trim(), actualOutput.trim());
    }
}
