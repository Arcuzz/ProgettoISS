package pac.minigiochi;
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
        System.out.println("--- Sasso Carta Forbici ---");
        System.out.println("Sicuramente sai già come funziona:");
        System.out.println("Il Sasso batte le Forbici, le Forbici battono la Carta, la Carta batte il Sasso");
        System.out.println("Fai la tua scelta ad ogni round e vinci");
        System.out.println("Il primo che vince "+this.obiettivo+" round vince la partita!\n");
    }
    @Override
    public void inizializza(){
        this.obiettivo = this.rank+1;
        this.punti = this.rank*50;
        Collections.shuffle(this.sceltaPC);
    }

    @Override
    public boolean play(Scanner scan) {
        int vittorieUser=0, vittoriePC=0;
        while(vittorieUser<this.obiettivo && vittoriePC<this.obiettivo){
            System.out.println("\nGiocatore: "+vittorieUser+"  |  PC: "+vittoriePC);
            System.out.println("Scrivi una lettera per scegliere! [S|C|F] (o \"exit\" per uscire)");
            String input = scan.nextLine();
            if(input.equalsIgnoreCase("exit")){
                System.out.println("Sei uscito senza completare il minigioco, ritorno al movimento");
                return false;
            }
            System.out.println("\nHai scelto "+printWord(input)+" !");
            attesa(4);
            System.out.println("Il PC ha scelto "+printWord(this.sceltaPC.get(0))+" !");
            int esito = esitoTurno(input,this.sceltaPC.get(0));
            if(esito==1){
                System.out.println("Hai vinto questo round!!!");
                vittorieUser++;
            }
            else if(esito==2){
                System.out.println("Hai perso questo round!!!");
                vittoriePC++;
            }
            Collections.shuffle(this.sceltaPC);
        }
        System.out.println("Punteggio finale: ");
        System.out.println("\nGiocatore: "+vittorieUser+"  |  PC: "+vittoriePC);
        if(vittoriePC==0){
            this.punti += this.punti;
            System.out.println("Congratulazioni, hai vinto senza nessun errore!!!");
            return true;
        }
        else if(vittorieUser>vittoriePC){
            System.out.println("Congratulazioni, hai vinto!!!");
            return true;
        }
        else{
            System.out.println("Hai perso!!!");
            return false;
        }
    }
    public int esitoTurno(String sceltaUser, String sceltaPC){
        //ritorna true in caso di vittoria
        if(sceltaUser.equalsIgnoreCase(sceltaPC)){
            System.out.println("Pareggio!!!");
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