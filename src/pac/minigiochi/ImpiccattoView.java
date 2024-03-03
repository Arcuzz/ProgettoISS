package pac.minigiochi;

import pac.Grafica;

import java.io.Serializable;

public class ImpiccattoView extends MinigiocoView implements Serializable {

    @Override
    public void initGraphic(String nome){
        System.out.println(Grafica.Minigame);
        System.out.println("\n"+Grafica.sep+ "Sono "+nome+" risolvi il mio minigioco");
        System.out.println(Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
    }

    @Override
    public void startGame(int ...val){
        Grafica.clearConsole();
        System.out.println(Grafica.Minigame);
        System.out.println("\n\n" + Grafica.sep+"--- Gioco dell'impiccato ---");
        System.out.println("\n"+Grafica.sep+"Pronto? [s/n]");
        System.out.print(Grafica.sep+"#: ");

    }

    public void showGame(StringBuilder guessed, int remainingAttempts){
        Grafica.clearConsole();
        System.out.println(Grafica.Minigame);
        System.out.println("\n"+Grafica.sep+"Parola da indovinare:");
        System.out.println(Grafica.sep+guessed);
        System.out.println("\n" + Grafica.sep+"Hai a disposizione " + remainingAttempts + " tentativi");
        System.out.println();
        System.out.print(Grafica.sep+"#: ");
    }

    public void resumeGame(){
        System.out.println(Grafica.sep+"Premi invio per riprendere il gioco");
        System.out.print(Grafica.sep+"+ ");
    }

    public void completedGame(){
        System.out.println(Grafica.sep+"PAROLA INDOVINATA !!!!");
    }
}
