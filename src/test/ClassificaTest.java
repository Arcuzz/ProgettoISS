package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;

import pac.Classifica;
import pac.Grafica;
import pac.RecordPersona;


public class ClassificaTest {
    Classifica leaderboard;
    
    @Before
    public void initClass() throws FileNotFoundException {
        leaderboard = new Classifica();
    }
    
    @Test
    public void testAggiornaRecord() throws FileNotFoundException  {
        leaderboard.rp.add(new RecordPersona("Andrea", 15));
        leaderboard.rp.add(new RecordPersona("Giovanni", 13));
        leaderboard.rp.add(new RecordPersona("Gloria", 10));

        leaderboard.aggiornaRecord("Gloria", 14);
        assertEquals("Gloria", leaderboard.rp.get(1).nome);
    }

    @Test
    public void testAggiornaRecordBalance() throws FileNotFoundException  {
        leaderboard.rp.add(new RecordPersona("Andrea", 15));
        leaderboard.rp.add(new RecordPersona("Giovanni", 13));
        leaderboard.rp.add(new RecordPersona("Gloria", 10));

        leaderboard.aggiornaRecord("Gloria", 15);
        assertEquals("Andrea, Gloria", leaderboard.rp.get(0).nome);
    }


    @Test
    public void testScriviClassifica() throws IOException {
        leaderboard.file = File.createTempFile("Classifica", "tmp");
        leaderboard.file.deleteOnExit();

        leaderboard.rp.add(new RecordPersona("Andrea", 15));
        leaderboard.rp.add(new RecordPersona("Giovanni", 13));
        leaderboard.rp.add(new RecordPersona("Gloria", 10));
        
        var output = "Andrea\n15\nGiovanni\n13\nGloria\n10\n";

        leaderboard.scriviClassifica();
        assertEquals(output, new String(Files.readAllBytes(leaderboard.file.toPath())));
        leaderboard.file.delete();
    }

    @Test
    public void testStampaClassifica() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);
        System.setOut(print);

        leaderboard.rp.add(new RecordPersona("Andrea", 15));
        leaderboard.rp.add(new RecordPersona("Giovanni", 13));
        leaderboard.rp.add(new RecordPersona("Gloria", 10));

        var output = "\n" + Grafica.sep+"Classifica: \n";
        
        for(int i=0; i<leaderboard.rp.size(); i++)
            output += 
                Grafica.sep + (i+1) + "o: " + leaderboard.rp.get(i).nome + 
                " Punti: "+ this.leaderboard.rp.get(i).punteggio + "\n";

        leaderboard.stampaClassifica();
        assertEquals(output, os.toString());
    }

    @Test
    public void testRecordPersona() {
        RecordPersona rp = new RecordPersona("Gloria", 0);
        assertEquals("Gloria", rp.nome);
        assertEquals(0, rp.punteggio);
    }
}
