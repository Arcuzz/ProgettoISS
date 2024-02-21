package test.pac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pac.Grafica;

public class GraficaTest {
    String sep = "\t\t";
    
    String levelOne = """
        \n \n
        """ + sep + """
        ██████╗ ██╗ █████╗ ███╗  ██╗ █████╗           ███╗
        """ + sep + """
        ██╔══██╗██║██╔══██╗████╗ ██║██╔══██╗         ████║
        """ + sep + """
        ██████╔╝██║███████║██╔██╗██║██║  ██║        ██╔██║
        """ + sep + """
        ██╔═══╝ ██║██╔══██║██║╚████║██║  ██║        ╚═╝██║
        """ + sep + """
        ██║     ██║██║  ██║██║ ╚███║╚█████╔╝        ███████╗
        """ + sep + """
        ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚══╝ ╚════╝         ╚══════╝
        """;

    PrintStream originalOut = System.out;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    
        @Test
    public void testASCII_lvl() {
        assertNull(Grafica.ASCII_lvl(0));

        assertEquals(levelOne, Grafica.ASCII_lvl(1));

        assertTrue(Grafica.ASCII_lvl(2).contains(
            """                  
            ██████╗ ██╗ █████╗ ███╗  ██╗ █████╗         ██████╗
            """
        ));
        assertTrue(Grafica.ASCII_lvl(3).contains(
            """
            ██╔══██╗██║██╔══██╗████╗░██║██╔══██╗        ╚════██╗
            """
        ));

        assertTrue(Grafica.ASCII_lvl(4).contains(
            """
            ██████╔╝██║███████║██╔██╗██║██║  ██║        ██╔╝ ██║
            """
        ));

        assertTrue(Grafica.ASCII_lvl(5).contains(
            """
            ██╔══██╗██║██╔══██╗████╗ ██║██╔══██╗        ██╔════╝
            """
        ));

        assertFalse(Grafica.ASCII_lvl(4).contains(
            """
            ██╔══██╗██║██╔══██╗████╗ ██║██╔══██╗        ██╔════╝
            """
        ));

        assertFalse(Grafica.ASCII_lvl(1).contains(
            """
            ██╔══██╗██║██╔══██╗████╗░██║██╔══██╗        ╚════██╗
            """
        ));
    }

    @Test
    public void testClearConsole() {
        Grafica.clearConsole();
        if (System.getProperty("os.name").contains("Windows")) {
            assertEquals("", outContent.toString());
        }
        else {
            assertEquals("c", outContent.toString());
        }
        
    }
}

    

   
