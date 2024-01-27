package test.pac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pac.Aiutante;

public class AiutanteTest {
    String helperName[] = {"Galgith", "Boris", "Rendar", "Sanga"};
    String themes[] = {"Storia", "Geografia", "Matematica", "Informatica", "Italiano", "Inglese"};

    @Test
    public void WithIdOneShouldGalgith() {
        var aiutante = new Aiutante(1);
        assertEquals(helperName[0], aiutante.nome);
    }
    @Test
    public void WithIdTwoShouldBoris() {
        var aiutante = new Aiutante(2);
        assertEquals(helperName[1], aiutante.nome);
    }
    @Test
    public void WithIdThreeShouldRendal() {
        var aiutante = new Aiutante(3);
        assertEquals(helperName[2], aiutante.nome);
    }
    @Test
    public void WithIdFourShouldSanga() {
        var aiutante = new Aiutante(4);
        assertEquals(helperName[3], aiutante.nome);
    }

    @Test
    public void WithIdZeroShouldNull() {
        var aiutante = new Aiutante(0);
        assertEquals(null, aiutante.nome);
    }

    @Test
    public void ThemeEmptyShoudAiutoFalse() {
        var aiutante = new Aiutante(1);
        aiutante.attivaAiuto("");
        assertFalse(aiutante.aiuto);
    }

    @Test
    public void ThemeLowerCaseShoudAiutoFalse() {
        var aiutante = new Aiutante(1);
        aiutante.attivaAiuto("storia");
        assertFalse(aiutante.aiuto);
    }
    @Test
    public void IdOneShouldAiutoAlwaysTrue() {
        var aiutante = new Aiutante(1);
        for(var i=0;i<themes.length;i++){
            aiutante.aiuto = false;
            aiutante.attivaAiuto(themes[i]);
            assertTrue(aiutante.aiuto);
        }
    }
    @Test
    public void IdZeroShouldAiutoAlwaysTrue() {
        var aiutante = new Aiutante(0);
        for(var i=0;i<themes.length;i++){
            aiutante.aiuto = false;
            aiutante.attivaAiuto(themes[i]);
            assertFalse(aiutante.aiuto);
        }
    }
    @Test
    public void IdTwoShoudAiutoOnlyGeographyAndHistory() {
        var aiutante = new Aiutante(2);
        for(var i=0;i<themes.length;i++){
            aiutante.aiuto = false;
            aiutante.attivaAiuto(themes[i]);
            if (i < 2) assertTrue(aiutante.aiuto); else assertFalse(aiutante.aiuto);
        }
    }
    @Test
    public void IdThreeShouldAiutoOnlyEnglishAndItalian() {
        var aiutante = new Aiutante(3);
        for(var i=0;i<themes.length;i++){
            aiutante.aiuto = false;
            aiutante.attivaAiuto(themes[i]);
            if (i > 3) assertTrue(aiutante.aiuto); else assertFalse(aiutante.aiuto);
        }
    }
    @Test
    public void IdFourShouldAiutoOnlyMathsAndComputerScience() {
        var aiutante = new Aiutante(4);
        for(var i=0;i<themes.length;i++){
            aiutante.aiuto = false;
            aiutante.attivaAiuto(themes[i]);
            if (i >= 2 && i <= 3) assertTrue(aiutante.aiuto); else assertFalse(aiutante.aiuto);
        }
    }

}
