package test.pac;

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
import pac.ProvaController;
import pac.ProvaModel;
import pac.ProvaView;

public class ProvaTest {
    ProvaController prova;
    ProvaModel provaModel;
    ProvaView provaView = new ProvaView();
    OutputStream os;
    PrintStream print;
    
    
    @Before
    public void initClass() {
       provaModel = new ProvaModel("Question", "Response", "Help");
       prova = new ProvaController(provaModel, provaView);

       os = new ByteArrayOutputStream();
       print = new PrintStream(os);
       System.setOut(print);
    }

    @Test
    public void testDaiIndizio() {
        provaModel.setAiutante(new Aiutante(3));
        provaModel.getAiutante().attivaAiuto("Italiano");
        prova.daiIndizio();
        
        String actualOutput = os.toString();
        String expected = Grafica.sep+"INDIZIO: "+ provaModel.getIndizioPrincipale();

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
                Grafica.sep + provaModel.getDomanda() + "\n" +
                "\n" + Grafica.sep + "Risposta: \n" +
                Grafica.sep + ">> " +
                Grafica.sep + "Risposta sbagliata! Riprova o scrivi \"exit\" per uscire\n\n" + 
                Grafica.sep + provaModel.getDomanda() + "\n" +
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
                Grafica.sep + provaModel.getDomanda() + "\n" +
                "\n" + Grafica.sep + "Risposta: \n" +
                Grafica.sep + ">> " +
                Grafica.sep+"LA RISPOSTA E': " + provaModel.getRisposta() + "\n" +
                Grafica.sep + provaModel.getDomanda() + "\n" +
                "\n" + Grafica.sep + "Risposta: \n" +
                Grafica.sep + ">> ";
            
            String actualOutput = os.toString();
            assertEquals(expected.trim(), actualOutput.trim());
        }
    }

    @Test
    public void testFaiDomandaRight() {
        try {
            InputStream in = new ByteArrayInputStream(provaModel.getRisposta().getBytes());
            System.setIn(in);
            prova.faiDomanda(new Scanner(System.in));
            provaModel.setContaErrori(1);
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
        provaView.summary(provaModel.getDomanda(),provaModel.getRisposta(),2);
        
        String actualOutput = os.toString();
        String expected = 
        "Domanda: " + provaModel.getDomanda() +
        "\nRisposta: " + provaModel.getRisposta() +
        "\nDifficoltà: 2";

        assertEquals(expected.trim(), actualOutput.trim());
    }
}
