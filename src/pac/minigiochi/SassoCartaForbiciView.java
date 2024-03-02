package pac.minigiochi;

import pac.Grafica;

import java.io.Serializable;

public class SassoCartaForbiciView extends MinigiocoView implements Serializable {

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
        System.out.println("\n\n"+Grafica.sep+"--- Sasso Carta Forbici ---");
        System.out.println(Grafica.sep+"Sicuramente sai già come funziona:");
        System.out.println(Grafica.sep+"Il Sasso batte le Forbici, le Forbici battono la Carta, la Carta batte il Sasso");
        System.out.println(Grafica.sep+"Fai la tua scelta ad ogni round e vinci");
        System.out.println(Grafica.sep+"Il primo che vince "+val[0]+" round vince la partita!\n");
        System.out.println("\n"+Grafica.sep+"Pronto? [s/n]");
        System.out.print(Grafica.sep+"#: ");
    }

    public void mainGame(int vittoriePC, int vittorieUser){
        System.out.println("\n"+ Grafica.sep+"Giocatore: "+vittorieUser+"  |  PC: "+vittoriePC);
        System.out.println(Grafica.sep+"Scrivi una lettera per scegliere! [S|C|F] (o \"exit\" per uscire)");
        System.out.print(Grafica.sep+"#: ");
    }

    public void sceltaUser(String input){
        System.out.println("\n"+Grafica.sep+"Hai scelto "+printWord(input)+" !");
    }

    public void sceltaPC(String sceltaPC){
        System.out.println(Grafica.sep+"Il PC ha scelto "+printWord(sceltaPC)+" !");
    }

    public void exit(){
        System.out.println(Grafica.sep+"Sei uscito senza completare il minigioco, ritorno al movimento");
    }

    public void roundVinto(){
        System.out.println(Grafica.sep+"Hai vinto questo round!!!");
    }

    public void roundPerso(){
        System.out.println(Grafica.sep+"Hai perso questo round!!!");
    }

    public void resumeGame(){
        System.out.println("\n"+ Grafica.sep+"Premi invio per continuare");
        System.out.print(Grafica.sep+"#: ");
    }

    public void victory(){
        System.out.println(Grafica.sep+"Congratulazioni, hai vinto!!!");
    }

    public void perfectVictory(){
        System.out.println(Grafica.sep+"Congratulazioni, hai vinto senza nessun errore!!!");
    }

    public void finalScore(int vittorieUser, int vittoriePC){
        System.out.println(Grafica.sep+"Punteggio finale: ");
        System.out.println("\n"+Grafica.sep+"Giocatore: "+vittorieUser+"  |  PC: "+vittoriePC);
    }

    public void lose(){
        System.out.println(Grafica.sep+"Hai perso!!!");
    }

    public void draw(){
        System.out.println(Grafica.sep+"Pareggio!!!");
    }

    public String printWord(String s){
        switch (s){
            case "s","S": s = "Sasso"; break;
            case "c","C": s = "Carta"; break;
            case "f","F": s = "Forbici"; break;
        }
        return s;
    }
}