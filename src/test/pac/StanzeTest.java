package test.pac;

import pac.ProvaModel;
import pac.stanze.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

public class StanzeTest {
    @Test
    public void EmptyRoomShouldBeCreated() {
        Vuota v = new Vuota();
        assertEquals('V', v.id);
    }
    @Test
    public void StartRoomShouldBeCreated() {
        Start s = new Start();
        assertEquals('S', s.id);
    }
    @Test
    public void WallShouldBeCreated() {
        Wall w = new Wall();
        assertEquals('#', w.id);
    }
    @Test
    public void SaveRoomShouldBeCreated() {
        Save s = new Save();
        assertEquals('+', s.id);
    }
    
    @Test
    public void QuestionRoomWithoutTestShouldBeCreated() {
        Domanda q = new Domanda(null);
        assertEquals(null, q.prova);
        assertEquals('D', q.id);
    }
    @Test
    public void QuestionRoomWithoutTestShouldGetWarning() {
        Domanda q = new Domanda(null);
        InputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        assertThrows(NullPointerException.class,
            ()->{
                q.idle(new Scanner(System.in), null);
            });
    }
    @Test
    public void QuestionRoomWithTestShouldBeCreated() {
        Domanda q = new Domanda(new ProvaModel("Come ti chiami?", "Gloria", "Tu"));
        assertEquals("Come ti chiami?", q.prova.getDomanda());
        assertEquals("Gloria", q.prova.getRisposta());
        assertEquals("Tu", q.prova.getIndizioPrincipale());
        assertEquals('D', q.id);
    }
    @Test
    public void QuestionRoomShouldResponseRight() {
        Domanda q = new Domanda(new ProvaModel("Come ti chiami?", "Gloria", "Tu"));
        InputStream in = new ByteArrayInputStream("Gloria".getBytes());
        System.setIn(in);
        q.idle(new Scanner(System.in), null);
        assertTrue(q.risposta);
    }
}
    





    
    
