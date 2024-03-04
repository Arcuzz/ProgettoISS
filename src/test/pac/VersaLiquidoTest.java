package test.pac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import pac.minigiochi.VersaLiquidoController;
import pac.minigiochi.VersaLiquidoModel;
import pac.minigiochi.VersaLiquidoView;


public class VersaLiquidoTest {
    VersaLiquidoController ver;
    VersaLiquidoModel verModel;
    
    @Before 
    public void initClass() {
        verModel = new VersaLiquidoModel("1");
        verModel.rank = 1;
        verModel.inizializza();

        ver = new VersaLiquidoController(verModel, new VersaLiquidoView());
    }

    
    @Test
    public void MoreRankShouldMorePoints() {
        for (var i = 1; i < 4; i++) {
            verModel.rank = i;
            verModel.inizializza();
            assertEquals(50 * (i+1), verModel.punti);
        }
    }
    @Test
    public void RankOutRageShouldImpossible() {
        verModel = new VersaLiquidoModel("1");
        assertThrows(NullPointerException.class,
            ()->{
                verModel.inizializza();
            });
        
    }
    
    @Test
    public void FiveShouldExitPlay() {
        String input = 
            System.lineSeparator() + 
            "5" + 
            System.lineSeparator() + 
            System.lineSeparator();
        
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertFalse(ver.play(new Scanner(System.in), "gloria"));
    }

    @Test
    public void FourShouldResetPlay() {
        try {
            String input = 
            System.lineSeparator() + "4";
            
            
            verModel.setMosse(100);
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            ver.play(new Scanner(System.in), "Giovanni");
        }
        catch (Exception ex) {
            assertEquals(0, verModel.getMosse());
        }
    }
    @Test
    public void RigthMoveShouldIncreaseDecreaseLevel() {
        try {
            var move = 
                System.lineSeparator() +
                "1" + System.lineSeparator() +
                "2" + System.lineSeparator();

            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            ver.play(new Scanner(System.in), "andrea");
        }
        catch (Exception ex) {
            assertEquals(3, verModel.getBicchieri(0).getLivello());
            assertEquals(5, verModel.getBicchieri(1).getLivello());
        }
    }

    @Test
    public void FullGlassShouldGiveWarning() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);

        try {
            var move = 
                System.lineSeparator() +
                "1" + System.lineSeparator() +
                "1" + System.lineSeparator();

            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            System.setOut(print);
            ver.play(new Scanner(System.in), "gloria");
        }
        catch (Exception ex) {
            String actualOutput = os.toString();
            assertTrue(actualOutput.contains("Il bicchiere che hai scelto è pieno! Riprova"));
        }
    }
    @Test
    public void EmptyGlassShouldGiveWarning() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);

        try {
            var move = 
                System.lineSeparator() +
                "2" + System.lineSeparator() +
                "1" + System.lineSeparator();

            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            System.setOut(print);
            ver.play(new Scanner(System.in), "giovanni");
        }
        catch (Exception ex) {
            String actualOutput = os.toString();
            assertTrue(actualOutput.contains("Il bicchiere che hai scelto è vuoto! Riprova"));
        }
    }

    @Test
    public void SameGlassShouldGiveWarning() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);

        try {
            var move = 
                System.lineSeparator() +
                "1" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "1" + System.lineSeparator();

            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            System.setOut(print);
            ver.play(new Scanner(System.in), "andrea");
        }
        catch (Exception ex) {
            String actualOutput = os.toString();
            assertTrue(actualOutput.contains("Hai scelto il bicchiere di partenza! Riprova"));
        }
    }
    @Test
    public void WringDestinationShouldGiveWarning() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);

        try {
            var move = 
                System.lineSeparator() +
                "1" + System.lineSeparator() +
                "5" + System.lineSeparator();
            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            System.setOut(print);
            ver.play(new Scanner(System.in), "gloria");
        }
        catch (Exception ex) {
            String actualOutput = os.toString();
            assertTrue(actualOutput.contains("Input sbagliato! Riprova"));
        }
    }

    @Test
    public void resetShouldSetZeroToMosse() {
        verModel.setMosse(100);
        verModel.reset();
        assertEquals(0, verModel.getMosse());
    }
}
