package pac.minigiochi;
import pac.Grafica;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SassoCartaForbici extends Minigiochi{
    public int obiettivo;
    public ArrayList<String> sceltaPC = new ArrayList<>();
    public SassoCartaForbici(){
        Collections.addAll(this.sceltaPC,"S","C","F");
    }
    @Override
    public void startGame(){
        Grafica.clearConsole();
        System.out.println(Grafica.Minigame);
        System.out.println("\n\n"+Grafica.sep+"--- Sasso Carta Forbici ---");
        System.out.println(Grafica.sep+"Sicuramente sai già come funziona:");
        System.out.println(Grafica.sep+"Il Sasso batte le Forbici, le Forbici battono la Carta, la Carta batte il Sasso");
        System.out.println(Grafica.sep+"Fai la tua scelta ad ogni round e vinci");
        System.out.println(Grafica.sep+"Il primo che vince "+this.obiettivo+" round vince la partita!\n");
    }
    @Override
    public void inizializza(){
        this.obiettivo = this.rank+1;
        this.punti = this.rank*50;
        Collections.shuffle(this.sceltaPC);
    }

    @Override
    public boolean play(Scanner scan) {
        System.out.println("\n"+ Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
        scan.nextLine();

        int vittorieUser=0, vittoriePC=0;
        while(vittorieUser<this.obiettivo && vittoriePC<this.obiettivo){

            startGame();

            System.out.println("\n"+ Grafica.sep+"Giocatore: "+vittorieUser+"  |  PC: "+vittoriePC);
            System.out.println(Grafica.sep+"Scrivi una lettera per scegliere! [S|C|F] (o \"exit\" per uscire)");
            System.out.print(Grafica.sep+"#: ");
            String input = scan.nextLine();
            if(input.equalsIgnoreCase("exit")){
                System.out.println(Grafica.sep+"Sei uscito senza completare il minigioco, ritorno al movimento");
                return false;
            }
            System.out.println("\n"+Grafica.sep+"Hai scelto "+printWord(input)+" !");
            attesa(4);
            System.out.println(Grafica.sep+"Il PC ha scelto "+printWord(this.sceltaPC.getFirst())+" !");
            int esito = esitoTurno(input,this.sceltaPC.getFirst());
            if(esito==1){
                System.out.println(Grafica.sep+"Hai vinto questo round!!!");
                vittorieUser++;
            }
            else if(esito==2){
                System.out.println(Grafica.sep+"Hai perso questo round!!!");
                vittoriePC++;
            }
            Collections.shuffle(this.sceltaPC);
            System.out.println("\n"+ Grafica.sep+"Premi invio per continuare");
            System.out.print(Grafica.sep+"#: ");
            scan.nextLine();
        }
        System.out.println(Grafica.sep+"Punteggio finale: ");
        System.out.println("\n"+Grafica.sep+"Giocatore: "+vittorieUser+"  |  PC: "+vittoriePC);
        if(vittoriePC==0){
            this.punti += this.punti;
            System.out.println(Grafica.sep+"Congratulazioni, hai vinto senza nessun errore!!!");
            return true;
        }
        else if(vittorieUser>vittoriePC){
            System.out.println(Grafica.sep+"Congratulazioni, hai vinto!!!");
            return true;
        }
        else{
            System.out.println(Grafica.sep+"Hai perso!!!");
            return false;
        }
    }
    public int esitoTurno(String sceltaUser, String sceltaPC){
        //ritorna true in caso di vittoria
        if(sceltaUser.equalsIgnoreCase(sceltaPC)){
            System.out.println(Grafica.sep+"Pareggio!!!");
            return 0;
        }
        else switch (sceltaUser){
            case "s","S": if(sceltaPC.equalsIgnoreCase("f")) return 1; else return 2;
            case "c","C": if(sceltaPC.equalsIgnoreCase("s")) return 1; else return 2;
            case "f","F": if(sceltaPC.equalsIgnoreCase("c")) return 1; else return 2;
        }
        return 0;
    }
    public String printWord(String s){
        switch (s){
            case "s","S": s = "Sasso"; break;
            case "c","C": s = "Carta"; break;
            case "f","F": s = "Forbici"; break;
        }
        return s;
    }
    private void attesa(int t){
        System.out.print(Grafica.sep);
        for(;t>0;t--){
            System.out.print(".  ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }
}