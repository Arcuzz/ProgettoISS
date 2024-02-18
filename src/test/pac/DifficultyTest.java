package test.pac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import pac.Difficulty;
import pac.Grafica;

public class DifficultyTest {
    Difficulty difficulty; 

    @Before 
    public void initClass() {
        var initValues = 
            "1" + System.lineSeparator() +
            "S" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "S" + System.lineSeparator() +
            "S" + System.lineSeparator() +
            "S" + System.lineSeparator();
        
        InputStream in = new ByteArrayInputStream(initValues.getBytes());
        System.setIn(in);
        difficulty = new Difficulty(new Scanner(System.in));
    } 


    @Test
    public void testAiutoFacile() {
        var initAiutante = 
            "S" + System.lineSeparator() +
            "S" + System.lineSeparator();
        
        InputStream in = new ByteArrayInputStream(initAiutante.getBytes());
        System.setIn(in);
        difficulty.aiutoFacile(new Scanner(in));

        assertEquals("Galgith", difficulty.aiutante.nome);
        assertEquals(" il Saggio", difficulty.aiutante.titolo);

    }

    @Test
    public void testPrintData() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);
        System.setOut(print);
            
        difficulty.printData();

        var output =
            "Statica: " + difficulty.statica + "\n" +
            "difficolta: " + difficulty.difficolta + "\n" +
            "Numero piani: " + difficulty.numPiani + "\n";

        assertEquals(output, os.toString());
    }

    @Test
    public void testSceltaAiutanteAlone() {
        var initAiutante = 
            "0" + System.lineSeparator() +
            "S" + System.lineSeparator();
        
        InputStream in = new ByteArrayInputStream(initAiutante.getBytes());
        System.setIn(in);
        difficulty.sceltaAiutante(new Scanner(in));

        assertEquals("Galgith", difficulty.aiutante.nome);
        assertEquals(" il Saggio", difficulty.aiutante.titolo);
    }

    @Test
    public void testSceltaAiutanteBoris() {
        var initAiutante = 
            "1" + System.lineSeparator() +
            "S" + System.lineSeparator();
        
        InputStream in = new ByteArrayInputStream(initAiutante.getBytes());
        System.setIn(in);
        difficulty.sceltaAiutante(new Scanner(in));

        assertEquals("Boris", difficulty.aiutante.nome);
        assertEquals(" il Cronistorico", difficulty.aiutante.titolo);
    }

    @Test
    public void testSceltaAiutanteRedar() {
        var initAiutante = 
            "2" + System.lineSeparator() +
            "S" + System.lineSeparator();
        
        InputStream in = new ByteArrayInputStream(initAiutante.getBytes());
        System.setIn(in);
        difficulty.sceltaAiutante(new Scanner(in));

        assertEquals("Rendar", difficulty.aiutante.nome);
        assertEquals(" il Linguista", difficulty.aiutante.titolo);
    }

    @Test
    public void testSceltaAiutanteSanga() {
        var initAiutante = 
            "3" + System.lineSeparator() +
            "S" + System.lineSeparator();
        
        InputStream in = new ByteArrayInputStream(initAiutante.getBytes());
        System.setIn(in);
        difficulty.sceltaAiutante(new Scanner(in));

        assertEquals("Sanga", difficulty.aiutante.nome);
        assertEquals(" il Matematico", difficulty.aiutante.titolo);
    }

    @Test
    public void testSceltaAiutanteWrong() {
        OutputStream os = null;

        try {
            InputStream in = new ByteArrayInputStream("5".getBytes());
            System.setIn(in);
            
            os = new ByteArrayOutputStream();
            PrintStream print = new PrintStream(os);
            System.setOut(print);
            
            difficulty.sceltaAiutante(new Scanner(in));
        }
        catch (Exception ex){
            var wrongInput = "Input sbagliato! Riprova:";
            assertTrue(os.toString().contains(wrongInput));
        }
    }

    @Test
    public void testSceltaSicuraTrue() {
        InputStream in = new ByteArrayInputStream("S".getBytes());
        System.setIn(in);
        assertTrue(difficulty.sceltaSicura(new Scanner(in)));
    }

    @Test
    public void testSceltaSicuraFalse() {
        InputStream in = new ByteArrayInputStream("N".getBytes());
        System.setIn(in);
        assertFalse(difficulty.sceltaSicura(new Scanner(in)));
    }

    @Test
    public void testSceltaSicuraWrong() {
        OutputStream os = null;

        try {
            InputStream in = new ByteArrayInputStream("Yes".getBytes());
            System.setIn(in);
            
            os = new ByteArrayOutputStream();
            PrintStream print = new PrintStream(os);
            System.setOut(print);
            
            difficulty.sceltaSicura(new Scanner(in));
        }
        catch (Exception ex){
            var wrongInput = "Input sbagliato! Riprova:";
            assertTrue(os.toString().contains(wrongInput));
        }
    }

    @Test
    public void testSetDiff() {
        difficulty.setDiff("Facile", 3);
        assertEquals("Facile", difficulty.difficolta);
        assertEquals(3, difficulty.numPiani);
    }

    @Test
    public void testStaticaLivello() {
        InputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "S").getBytes());
        System.setIn(in);
        assertEquals(1, difficulty.staticaLivello(new Scanner(in)));

        in = new ByteArrayInputStream(("2" + System.lineSeparator() + "S").getBytes());
        System.setIn(in);
        assertEquals(2, difficulty.staticaLivello(new Scanner(in)));

        in = new ByteArrayInputStream(("3" + System.lineSeparator() + "S").getBytes());
        System.setIn(in);
        assertEquals(3, difficulty.staticaLivello(new Scanner(in)));
    }

    @Test
    public void testStaticaLivelloWrong() {
        OutputStream os = null;

        try {
            InputStream in = new ByteArrayInputStream("5".getBytes());
            System.setIn(in);
            
            os = new ByteArrayOutputStream();
            PrintStream print = new PrintStream(os);
            System.setOut(print);
            
            difficulty.staticaLivello(new Scanner(in));
        }
        catch (Exception ex){
            var wrongInput = "Input sbagliato! Riprova:";
            assertTrue(os.toString().contains(wrongInput));
        }
    }

    @Test
    public void testStaticaLivelloNoConfirm() {
        OutputStream os = null;

        try {
            InputStream in = new ByteArrayInputStream(("3" + System.lineSeparator() + "N").getBytes());
            System.setIn(in);
            
            os = new ByteArrayOutputStream();
            PrintStream print = new PrintStream(os);
            System.setOut(print);
            
            difficulty.staticaLivello(new Scanner(in));
        }
        catch (Exception ex){
            var repeat = "\n"+ Grafica.sep + "Scegli 1 per facile, 2 media, 3 difficile:";
            assertTrue(os.toString().lastIndexOf(repeat) > 0);
        }
    }

    @Test
    public void testStaticaScelta() {
        InputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "S").getBytes());
        System.setIn(in);
        assertEquals(1, difficulty.staticaScelta(new Scanner(in)));

        in = new ByteArrayInputStream(("2" + System.lineSeparator() + "S").getBytes());
        System.setIn(in);
        assertEquals(2, difficulty.staticaScelta(new Scanner(in)));
    }

    @Test
    public void testStaticaSceltaWrong() {
        OutputStream os = null;

        try {
            InputStream in = new ByteArrayInputStream("3".getBytes());
            System.setIn(in);
            
            os = new ByteArrayOutputStream();
            PrintStream print = new PrintStream(os);
            System.setOut(print);
            
            difficulty.staticaScelta(new Scanner(in));
        }
        catch (Exception ex){
            var wrongInput = "Input sbagliato! Riprova:";
            assertTrue(os.toString().contains(wrongInput));
        }
    }

    @Test
    public void testStaticaSceltaNoConfirm() {
        OutputStream os = null;

        try {
            InputStream in = new ByteArrayInputStream(("2" + System.lineSeparator() + "N").getBytes());
            System.setIn(in);
            
            os = new ByteArrayOutputStream();
            PrintStream print = new PrintStream(os);
            System.setOut(print);
            
            difficulty.staticaScelta(new Scanner(in));
        }
        catch (Exception ex){
            var repeat = "\n"+ Grafica.sep+"Scegli 1 per difficolta statica, 2 per crescente:";
            assertTrue(os.toString().lastIndexOf(repeat) > 0);
        }
    }        

}
