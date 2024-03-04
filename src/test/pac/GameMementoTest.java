package test.pac;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import pac.Aiutante;
import pac.DifficultyModel;
import pac.GameMemento;
import pac.Piano;
import pac.stanze.Npc;
import pac.stanze.Stanza;

public class GameMementoTest {
    String initValues = 
        "1" + System.lineSeparator() +
        "S" + System.lineSeparator() +
        "1" + System.lineSeparator() +
        "S" + System.lineSeparator() +
        "S" + System.lineSeparator() +
        "S" + System.lineSeparator();
        
    InputStream in = new ByteArrayInputStream(initValues.getBytes());
    
    DifficultyModel diff = new DifficultyModel();
    
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
    
    GameMemento memento;
    
    @Before
    public void InitClass() {
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
    }
    
    @Test
    public void testGetAiutante() {
        assertEquals(aiutante, memento.getAiutante());
    }

    @Test
    public void testGetDiff() {
        assertEquals(diff, memento.getDiff());
    }

    @Test
    public void testGetDom() {
        assertEquals(dom, memento.getDom());
    }

    @Test
    public void testGetMini() {
        assertEquals(mini, memento.getMini());
    }

    @Test
    public void testGetNome() {
        assertEquals(nome, memento.getNome());
    }

    @Test
    public void testGetPiano() {
        assertEquals(piano, memento.getPiano());
    }

    @Test
    public void testGetPos() {
        assertEquals(pos, memento.getPos());
    }

    @Test
    public void testGetTemi() {
        assertEquals(temi, memento.getTemi());
    }

    @Test
    public void testGetTime() {
        assertEquals(temi, memento.getTemi());
    }

    @Test
    public void testGetTotal_points() {
        assertEquals(total_points, memento.getTotal_points());
    }

    @Test
    public void testGetVisited() {
        assertArrayEquals(visited, memento.getVisited());
    }
}
