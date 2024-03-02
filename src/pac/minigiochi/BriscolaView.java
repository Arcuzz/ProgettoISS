package pac.minigiochi;

import java.io.Serializable;
import java.util.ArrayList;

import pac.Grafica;

public class BriscolaView extends MinigiocoView implements Serializable {

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
        System.out.println("\n\n" + Grafica.sep+"--------BRISCOLA--------");
        System.out.println(Grafica.sep+"Da un mazzo di 40 carte e 4 semi viene estratta una prima carta il cui seme è la briscola");
        System.out.println(Grafica.sep+"La mano del giocatore è composta da tre carte, e si estrae una nuova carta dal mazzo dopo ogni turno");
        System.out.println(Grafica.sep+"A ogni turno si sceglie una delle 3 carte, e chi ha la carta dal valore maggiore vince il turno e somma il valore delle due al proprio punteggio totale");
        System.out.println(Grafica.sep+"L'asso, il 3, l'8, il 9 e il 10 valgono rispettivamente 11, 10, 2, 3, 4 punti, tutte le altre 0");
        System.out.println(Grafica.sep+"Se uno dei due giocatori sceglie una briscola vince in automatico");
        System.out.println(Grafica.sep+"Se entrambi i giocatori scelgono una briscola, il turno procede normalmente");
        System.out.println(Grafica.sep+"Quando il mazzo viene esaurito vince chi ha totalizzato più di 60 punti\n");;
    }
    

    public String printCarta(String carta){
        String out="";
        switch (carta.charAt(0)) {
            case '0' -> out = "_Coppe";
            case '1' -> out = "_Denari";
            case '2' -> out = "_Mazze";
            case '3' -> out = "_Spade";
            default -> System.out.println("Errore carta!");
        }
        return (((int)carta.charAt(1)-'0')+1)+out;
    }

    public void playerCard(String carta){
        System.out.println(Grafica.sep+"Hai scelto: " + printCarta(carta));
    }

    public void pcCard(String carta){
        System.out.println("\n"+Grafica.sep+"Pc ha scelto: " + printCarta(carta));
    }

    public void printMano(ArrayList<String> mano){
        System.out.print("\n"+Grafica.sep+"----------------------------------------------\n");
        System.out.print(Grafica.sep+"---> Mano: ");
        for(int i=0;i<mano.size();i++) System.out.print(" | "+printCarta(mano.get(i)));
        System.out.print(" |\n"+Grafica.sep+"----------------------------------------------");
    }

    public void startBriscola(String briscola){
        System.out.println(Grafica.sep+"***************************************");
        System.out.println(Grafica.sep+"*******\tBriscola: "+printCarta(briscola)+"\t*******");
        System.out.println(Grafica.sep+"***************************************");
        System.out.println(Grafica.sep+"Lanciando una moneta: testa parte l'utente, croce parte il pc");
    }

    public void testa(){
        System.out.println(Grafica.sep+"Esce testa, inizia l'utente");
        System.out.println(Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
    }

    public void croce(){
        System.out.println(Grafica.sep+"Esce croce, parte il pc");
        System.out.println(Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
    }

    public void turno(int n_turno){
        System.out.println(Grafica.sep+"Lanciando una moneta: testa parte l'utente, croce parte il pc");
        System.out.println("\n"+ Grafica.sep+"Turno numero " + n_turno + ". Rimangono " + (20-n_turno) +" turni.");
    }

    public void printBirscola(String briscola){
        System.out.println(Grafica.sep+"BRISCOLA: " + printCarta(briscola)+"\n");
    }

    public void exit(){
        System.out.println(Grafica.sep+"Sei uscito senza completare il minigioco, ritorno al movimento");
        System.out.print(Grafica.sep+"#: ");
    }

    public void continua(){
        System.out.println("\n"+ Grafica.sep+"Premi invio per continuare");
        System.out.print(Grafica.sep+"#: ");
    }

    public void chooseCard(){
        System.out.println("\n"+Grafica.sep+"Scegli la tua carta (1,2,3 rispettivamente) o 0 per uscire dal gioco:");
    }

    public void chooseCardError(){
        System.out.println(Grafica.sep+"Errore! Digita 1,2,3 per le carte o 0 per uscire dal gioco");
        System.out.print(Grafica.sep+"#: ");
    }

    public boolean esitoPartita(int score){
        if(score==0){
            System.out.println(Grafica.sep+"Sei uscito senza completare la partita! Ritorno al movimento");
            return false;
        }
        if(score>60){
            System.out.print("\n"+ Grafica.sep+" -=-= Complimenti! Hai vinto con "+score+" Punti! =-=-\n");
            return true;
        }
        else{
            if(score<60){
                System.out.print(Grafica.sep+" -=-= Hai perso! Hai ottenuto "+score+" Punti! =-=-\n");
            }
            else{
                System.out.print(Grafica.sep+" -=-= Pareggio! Hai ottenuto "+score+" Punti! =-=-\n");
            }
            return false;
        }
    }
}
