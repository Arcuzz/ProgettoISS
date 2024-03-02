package test;

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

import pac.minigiochi.VersaLiquido;

public class VersaLiquidoTest {
    VersaLiquido ver;
    
    @Before 
    public void initClass() {
        ver = new VersaLiquido("1");
        ver.rank = 1;
        ver.inizializza();
    }

    
    @Test
    public void MoreRankShouldMorePoints() {
        for (var i = 1; i < 4; i++) {
            ver.rank = i;
            ver.inizializza();
            assertEquals(50 * (i+1), ver.punti);
        }
    }
    @Test
    public void RankOutRageShouldImpossible() {
        ver = new VersaLiquido("1");
        assertThrows(NullPointerException.class,
            ()->{
                ver.inizializza();
            });
        
    }
    
    @Test
    public void FiveShouldExitPlay() {
        InputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        assertFalse(ver.play(new Scanner(System.in)));
    }

    @Test
    public void FourShouldResetPlay() {
        try {
            ver.mosse = 100;
            InputStream in = new ByteArrayInputStream("4".getBytes());
            System.setIn(in);
            ver.play(new Scanner(System.in));
        }
        catch (Exception ex) {
            assertEquals(0, ver.mosse);
        }
    }
    @Test
    public void RigthMoveShouldIncreaseDecreaseLevel() {
        try {
            var move = "1" + System.lineSeparator() +
            "2" + System.lineSeparator();

            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            ver.play(new Scanner(System.in));
        }
        catch (Exception ex) {
            assertEquals(3, ver.bicchieri[0].getLivello());
            assertEquals(5, ver.bicchieri[1].getLivello());
        }
    }

    @Test
    public void FullGlassShouldGiveWarning() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);

        try {
            var move = "1" + System.lineSeparator() +
            "1" + System.lineSeparator();

            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            System.setOut(print);
            ver.play(new Scanner(System.in));
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
            var move = "2" + System.lineSeparator() +
            "1" + System.lineSeparator();

            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            System.setOut(print);
            ver.play(new Scanner(System.in));
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
            var move = "1" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "1" + System.lineSeparator();

            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            System.setOut(print);
            ver.play(new Scanner(System.in));
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
            var move = "1" + System.lineSeparator() +
            "5" + System.lineSeparator();
            InputStream in = new ByteArrayInputStream(move.getBytes());
            System.setIn(in);
            System.setOut(print);
            ver.play(new Scanner(System.in));
        }
        catch (Exception ex) {
            String actualOutput = os.toString();
            assertTrue(actualOutput.contains("Input sbagliato! Riprova"));
        }
    }

    @Test
    public void resetShouldSetZeroToMosse() {
        ver.mosse = 100;
        ver.reset();
        assertEquals(0, ver.mosse);
    }
}
