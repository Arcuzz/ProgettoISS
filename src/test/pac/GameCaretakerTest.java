package test.pac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import pac.Aiutante;
import pac.Difficulty;
import pac.GameCaretaker;
import pac.GameMemento;
import pac.Grafica;
import pac.Piano;
import pac.stanze.Npc;
import pac.stanze.Stanza;

public class GameCaretakerTest {
    String initValues = 
        "1" + System.lineSeparator() +
        "S" + System.lineSeparator() +
        "1" + System.lineSeparator() +
        "S" + System.lineSeparator() +
        "S" + System.lineSeparator() +
        "S" + System.lineSeparator();
        
    InputStream in = new ByteArrayInputStream(initValues.getBytes());
    
    Difficulty diff = new Difficulty(new Scanner(in));
    ArrayList<String> temi = new ArrayList<String>();
    Piano piano = new Piano(1, "1", "Italiano", new ArrayList<Npc>());
    String nome = "Glo";
    int[] pos = new int[]{1,2};
    Stanza[][] visited = null;
    Aiutante aiutante = new Aiutante(1);
    int[] dom = new int[]{0, 2, 0};
    int[] mini = new int[]{0, 0};
    long time = Calendar.getInstance().getTimeInMillis();
    int total_points = 10;
    
    GameCaretaker gamec;
    GameMemento memento;

    @Before 
    public void initClass() {
        gamec = new GameCaretaker();
        memento = new GameMemento(
            diff,
            temi,
            piano,
            nome,
            pos,
            visited,
            aiutante,
            dom,
            mini,
            total_points,
            time
        );

        gamec.addSnapshot(memento);
    }


    @Test
    public void testAddSnapshot() {
        assertEquals(1, gamec.getSnapshots().size());
    }

    @Test
    public void testCheck_duplicate_name() {
        assertTrue(gamec.check_duplicate_name("Glo"));
    }

    @Test
    public void testCheck_save() {
        assertTrue(gamec.check_save("Glo"));
        assertFalse(gamec.check_save("Giovanni"));
    }

    @Test
    public void testGetSnapshot() {
        gamec.addSnapshot(memento);
        assertEquals("Glo", gamec.getSnapshot("Glo", 1).getNome());
        assertNull(gamec.getSnapshot("Glo", 3));
    }

    @Test
    public void testLoadGameFile() throws IOException {
        File gameFile = File.createTempFile( "Snapshots", "txt"); 
        gameFile.deleteOnExit();

        Writer out = new FileWriter(gameFile.getAbsolutePath());
        PrintWriter printF = new PrintWriter(out);
        
        printF.print(gamec.getSnapshots());
        printF.close();
        
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);
        System.setOut(print);
          
        gamec.removeSnapshot("Glo");
        assertEquals(0, gamec.getSnapshots().size());

        gamec.loadGameFile("Games.txt");
        assertEquals(1, gamec.getSnapshots().size());
        
        var output = Grafica.sep + "Game file loaded\n";
        assertTrue(os.toString().contains(output)); 
    }

    @Test
    public void testPrintSnapshots_present() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);
        System.setOut(print);
            
        gamec.printSnapshots("Glo");

        var output = "sono presenti i seguenti salvataggi";
        assertTrue(os.toString().contains(output));    
    }

    @Test
    public void testPrintSnapshots_dont_present() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);
        System.setOut(print);
            
        gamec.printSnapshots("Andrea");
        var output = Grafica.sep+"Non è presente alcun salavataggio\n";
        assertEquals(output, os.toString());  
    }

    @Test
    public void testPrintPerSnapshots() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);
        System.setOut(print);
            
        gamec.printPerSnapshots();
        var output = "Sono presenti salvataggi per i seguenti personaggi";
        assertTrue(os.toString().contains(output)); 

        output = "# " + memento.getNome();
        assertTrue(os.toString().contains(output)); 
    }

    @Test
    public void testRemoveSnapshot() {
        gamec.removeSnapshot("Glo");
        assertEquals(0, gamec.getSnapshots().size());
    }

    @Test
    public void testSaveGame() throws IOException {
        File gameFile = File.createTempFile( "Snapshots", "txt"); 
        gameFile.deleteOnExit();

        OutputStream os = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(os);
        System.setOut(print);
            
        gamec.saveGame("Games.txt");
        var output = Grafica.sep + "Game saved.\n";
        assertTrue(os.toString().contains(output)); 
        gameFile.delete();
    }
}
