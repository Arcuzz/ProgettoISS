package pac.minigiochi;

import pac.Grafica;

import java.io.Serializable;

public class MemoryView extends MinigiocoView implements Serializable {

    @Override
    public void initGraphic(String nome){
        System.out.println(Grafica.Minigame);
        System.out.println("\n"+Grafica.sep+ "Sono "+nome+" risolvi il mio minigioco");
        System.out.println(Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
    }

    @Override
    public void startGame(int... val)  {
        Grafica.clearConsole();
        System.out.println(Grafica.Minigame);
        System.out.println("\n\n"+Grafica.sep+"--- Gioco del Memory ---");
        System.out.println(Grafica.sep+"Ti verranno mostrate "+val[0]/2+" coppie di carte numerate da 1 a "+val[1]*5);
        System.out.println(Grafica.sep+"Verranno mischiate e coperte dopo qualche secondo");
        System.out.println(Grafica.sep+"Il tuo obiettivo è scoprirle tutte, due alla volta, accoppiandole correttamente");
        System.out.println(Grafica.sep+"Se sbagli, quelle rimanenti verranno scoperte per qualche secondo e mischiate di nuovo");
        System.out.println(Grafica.sep+"Quando sarai pronto, avrai "+5*val[2]+" secondi per memorizzare le carte");
        System.out.println(Grafica.sep+"Buona fortuna e divertiti!");
        System.out.println("\n"+Grafica.sep+"Pronto? [s/n]");
        System.out.print(Grafica.sep+"#: ");
    }

    public void direction(){
        System.out.println(Grafica.sep+"In che direzione vuoi andare?");
        System.out.println(Grafica.sep+"d per destra, a per sinistra, w per sopra, s per sotto, x per selezionare, r per resettare, exit per uscire: ");
        System.out.print(Grafica.sep+"#: ");
    }

    public void invalidDirection(){
        System.out.println(Grafica.sep+"Direzione non valida");
    }

    public void giaScoperta(){
        System.out.println(Grafica.sep+"Hai già scoperto questa carta! Riprova");
    }

    public void vicotory(){
        System.out.println(Grafica.sep+"Congratulazioni, hai trovato tutte le coppie di carte!");
    }

    public void pressToStart(){
        System.out.println("\n"+ Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
    }

    public void exit(){
        System.out.println(Grafica.sep+"Sei uscito senza risolvere il minigioco, ritorno al movimento");
    }

    public void perfectVictory(){
        System.out.println(Grafica.sep+"Complimenti! Hai trovato tutte le coppie di carte senza errori!");
    }

    public void tentVictory(int tent){
        System.out.println(Grafica.sep+"Complimenti! Hai trovato tutte le coppie di carte con "+tent+" tentativi");
    }

    public void indovinato(){
        System.out.println(Grafica.sep+"Indovinato");
    }

    public void differentCard(){
        System.out.println(Grafica.sep+"Errore! Le due carte sono diverse");
        System.out.println(Grafica.sep+"Le carte coperte verranno mischiate!");
    }

    public void memorize(){
        System.out.println(Grafica.sep+"Memorizza in fretta!!!");
    }

    public void newLine(){
        System.out.println();
    }

    public void doubleNewLine(){
        System.out.println("\n");
    }

    public void waiting(int t){
        System.out.print(Grafica.sep+t+"  ");
    }

    public void printNum1(){
        System.out.print("\n" + Grafica.sep);
    }

    public void printNum2(){
        System.out.print("0");
    }

    public void printNum3(int i){
        System.out.print(i + "* ");
    }

    public void printNum4(int i){
        System.out.print(i + "  ");
    }
}
