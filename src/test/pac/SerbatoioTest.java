package test.pac;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pac.Serbatoio;

public class SerbatoioTest {
    Serbatoio serbatoio;

    @Before
    public void initClass() {
       serbatoio = new Serbatoio(10, 5);
    }

    @Test
    public void consumeLevelShouldDecrease() {
        serbatoio.consuma(1);
        assertEquals(4, serbatoio.livello);
    }
    @Test
    public void consumeLevelShouldReset() {
        serbatoio.consuma(11);
        assertEquals(0, serbatoio.livello);
    }
    @Test
    public void consumeLevelShouldMaintains() {
        serbatoio.consuma(0);
        assertEquals(5, serbatoio.livello);
    }
    @Test
    public void consumeLevelWithoutLevelShouldMantainsEqual() {
        Serbatoio serbatoio2 = new Serbatoio();
        serbatoio2.consuma(10);
        assertEquals(0, serbatoio2.livello);
    }
    @Test
    public void supplyLevelShouldIncrease() {
        serbatoio.rifornisci(1);
        assertEquals(6, serbatoio.livello);
    }
    @Test
    public void supplyLevelShouldEqualCapacity() {
        serbatoio.rifornisci(11);
        assertEquals(serbatoio.CAPACITA, serbatoio.livello);
    }
    @Test
    public void supplyLevelShouldMaintains() {
        serbatoio.rifornisci(0);
        assertEquals(5, serbatoio.livello);
    }
    @Test
    public void supplyLevelWithoutCapacityShouldMantainsEqual() {
        Serbatoio serbatoio2 = new Serbatoio();
        serbatoio2.rifornisci(10);
        assertEquals(0, serbatoio2.livello);
    }

    @Test
    public void setAndGetLevel() {
       serbatoio.livello = 10;
       assertEquals(10, serbatoio.getLivello());
    }

    @Test
    public void pourOffShouldDecreaseAndIncrease() {
        Serbatoio serbatoio2 = new Serbatoio(6,0);
        serbatoio.travasa(serbatoio2, 2);
        assertEquals(2,serbatoio2.livello);
        assertEquals(3,serbatoio.livello);
    }
    @Test
    public void pourOffAllShouldBeEmpty() {
        Serbatoio serbatoio2 = new Serbatoio(6,0);
        serbatoio.travasa(serbatoio2, 8);
        assertEquals(5,serbatoio2.livello);
        assertEquals(0,serbatoio.livello);
    }
    @Test
    public void pourOffAPartShouldBeFull() {
        Serbatoio serbatoio2 = new Serbatoio(6,4);
        serbatoio.travasa(serbatoio2, 3);
        assertEquals(3,serbatoio.livello);
        assertEquals(serbatoio2.CAPACITA,serbatoio2.livello);
    }
    @Test
    public void pourOffNullShouldBeEqual() {
        Serbatoio serbatoio2 = new Serbatoio();
        serbatoio.travasa(serbatoio2, 0);
        assertEquals(5,serbatoio.livello);
        assertEquals(0,serbatoio2.livello);
    }
    
}
