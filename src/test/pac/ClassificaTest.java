package test.pac;

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

import pac.ClassificaController;
import pac.ClassificaModel;
import pac.ClassificaView;
import pac.Grafica;
import pac.RecordPersona;


public class ClassificaTest {
    ClassificaController leaderboard;
    ClassificaModel model; 
    
    @Before
    public void initClass() throws FileNotFoundException {
        model = new ClassificaModel();
        model.rp.add(new RecordPersona("Andrea", 15));
        model.rp.add(new RecordPersona("Giovanni", 13));
        model.rp.add(new RecordPersona("Gloria", 10));
        
        leaderboard = new ClassificaController(model, new ClassificaView());
    }
    
    @Test
    public void testAggiornaRecord() throws FileNotFoundException  {
        leaderboard.aggiornaRecord("Gloria", 14);
        assertEquals("Gloria", model.rp.get(1).nome);
    }

    @Test
    public void testAggiornaRecordBalance() throws FileNotFoundException  {
        leaderboard.aggiornaRecord("Gloria", 15);
        assertEquals("Andrea, Gloria", model.rp.get(0).nome);
    }

    @Test
    public void testStampaClassifica() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);
        System.setOut(print);

        var output = "\n" + Grafica.sep+"Classifica: \n";
        
        for(int i=0; i<model.rp.size(); i++)
            output += 
                Grafica.sep + (i+1) + "o: " + model.rp.get(i).nome + 
                " Punti: "+ model.rp.get(i).punteggio + "\n";

        leaderboard.stampaClassifica();
        assertEquals(output, os.toString());
    }

    @Test
    public void testScriviClassifica() throws IOException {
        model.file = File.createTempFile("Classifica", "tmp");
        model.file.delete();

        var output = "Andrea\n15\nGiovanni\n13\nGloria\n10\n";

        model.scriviClassifica();
        assertEquals(output, new String(Files.readAllBytes(model.file.toPath())));
        model.file.delete();
    }

    @Test
    public void testRecordPersona() {
        RecordPersona rp = new RecordPersona("Gloria", 0);
        assertEquals("Gloria", rp.nome);
        assertEquals(0, rp.punteggio);
    }
}
