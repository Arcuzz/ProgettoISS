package pac.minigiochi;
import pac.Grafica;

import java.util.*;
public class VersaLiquido extends Minigiochi {
    public Serbatoio[] bicchieri = new Serbatoio[3];
    public int obiettivo, mosse=0;
    public VersaLiquido(String diff){
        this.difficolta = diff;
    }
    @Override
    public void inizializza(){
        switch (this.rank) {
            case 1 -> {
                this.bicchieri[1] = new Serbatoio(5, 0);
                this.bicchieri[2] = new Serbatoio(3, 0);
                this.punti = 100;
            }
            case 2 -> {
                this.bicchieri[1] = new Serbatoio(7, 0);
                this.bicchieri[2] = new Serbatoio(3, 0);
                this.punti = 150;
            }
            case 3 -> {
                this.bicchieri[1] = new Serbatoio(9, 0);
                this.bicchieri[2] = new Serbatoio(7, 0);
                this.punti = 200;
            }
        }
        this.bicchieri[0] = new Serbatoio(this.bicchieri[1].CAPACITA+this.bicchieri[2].CAPACITA,this.bicchieri[1].CAPACITA+this.bicchieri[2].CAPACITA);
        this.obiettivo = this.bicchieri[0].CAPACITA/2;
    }

    @Override
    public void startGame() {
        Grafica.clearConsole();
        System.out.println(Grafica.Minigame);
        System.out.println("\n\n" + Grafica.sep+"--- Dosa i bicchieri ---");
        System.out.println(Grafica.sep+"Davanti a te vedi tre bicchieri di "+this.bicchieri[0].CAPACITA+", "+this.bicchieri[1].CAPACITA+", "+this.bicchieri[2].CAPACITA+" litri rispettivamente");
        System.out.println(Grafica.sep+"Il primo è pieno d'acqua e gli altri due sono vuoti");
        System.out.println(Grafica.sep+"Il tuo obiettivo è spostare l'acqua tra i bicchieri in modo che il primo e il secondo contengano "+this.obiettivo+" litri");
        System.out.println(Grafica.sep+"Versare acqua più del limite di un bicchiere semplicemente lo riempie fino all'orlo, senza alcuna perdita");
        System.out.println(Grafica.sep+"Puoi fare tutti gli spostamenti che vuoi, ma per la soluzione più rapida ne servono solo "+(this.bicchieri[0].CAPACITA-1));
        System.out.println(Grafica.sep+"Buona fortuna e divertiti!");
    }

    @Override
    public boolean play(Scanner sca){
        System.out.println("\n"+ Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
        sca.nextLine();

        String scelta, dest;    //in realtà sono numeri

        while(!(this.bicchieri[0].livello==this.obiettivo && this.bicchieri[0].livello==this.bicchieri[1].livello)){
            startGame();

            stampaBicchieri();
            System.out.println("\n"+Grafica.sep+"Cosa vuoi fare? Digita 1,2,3 per travasare dal rispettivo bicchiere, 4 per resettare o 5 per uscire: ");
            System.out.print(Grafica.sep+"#: ");
            scelta = sca.nextLine();
            while(!scelta.matches("[1-5]")){
                System.out.println(Grafica.sep+"Input sbagliato! Riprova");
                System.out.print(Grafica.sep+"#: ");
                scelta = sca.nextLine();
            }
            int sceltaPos = Integer.parseInt(scelta)-1;
            if(scelta.equals("4")){
                reset();
                continue;
            }
            if(scelta.equals("5")){
                System.out.println("\n" + Grafica.sep+"Sei uscito senza risolvere il minigioco, ritorno al movimento");
                reset();
                return false;
            }
            if(this.bicchieri[sceltaPos].livello==0){
                System.out.println(Grafica.sep+"Il bicchiere che hai scelto è vuoto! Riprova");
                continue;
            }
            System.out.println(Grafica.sep+"Scegli il bicchiere da riempire con 1,2,3:");
            System.out.print(Grafica.sep+"#: ");
            dest = sca.nextLine();
            while(!dest.matches("[1-3]")){
                System.out.println(Grafica.sep+"Input sbagliato! Riprova");
                System.out.print(Grafica.sep+"#: ");
                dest = sca.nextLine();
            }
            int destPos = Integer.parseInt(dest)-1;
            if(this.bicchieri[destPos].livello==this.bicchieri[destPos].CAPACITA){
                System.out.println(Grafica.sep+"Il bicchiere che hai scelto è pieno! Riprova");
                continue;
            }
            if(!scelta.equals(dest)){
                this.bicchieri[sceltaPos].travasa(this.bicchieri[destPos],this.bicchieri[sceltaPos].livello);
                this.mosse++;
            }
            else System.out.println(Grafica.sep+"Hai scelto il bicchiere di partenza! Riprova");
        }
        if(this.mosse==this.bicchieri[0].CAPACITA-1){
            System.out.println(Grafica.sep+"Complimenti! Hai finito nel numero minimo di spostamenti possibili!");
            this.punti += this.punti;
            System.out.println("\n"+ Grafica.sep+"Premi invio per iniziare");
            System.out.print(Grafica.sep+"#: ");
            sca.nextLine();
            return true;
        }
        else if(this.mosse>this.bicchieri[0].CAPACITA-1){
            System.out.println("\n"+Grafica.sep+"Complimenti! Hai finito correttamente con "+this.mosse+" spostamenti!");
            System.out.println("\n"+ Grafica.sep+"Premi invio per iniziare");
            System.out.print(Grafica.sep+"#: ");
            sca.nextLine();
            return true;
        }
        return false;
    }
    public void stampaBicchieri(){
        System.out.println("\n"+Grafica.sep+"|       |");
        System.out.println(Grafica.sep+"|       |       |       |");
        System.out.println(Grafica.sep+"|   "+this.bicchieri[0].livello+"\t|       |       |       |       |");
        System.out.println(Grafica.sep+"|       |       |   "+this.bicchieri[1].livello+"   |       |       |");
        System.out.println(Grafica.sep+"|       |       |       |       |   "+this.bicchieri[2].livello+"   |");
        System.out.println(Grafica.sep+"|-------|       |-------|       |-------|");
        System.out.println(Grafica.sep+"    "+this.bicchieri[0].CAPACITA+"L              "+this.bicchieri[1].CAPACITA+"L              "+this.bicchieri[2].CAPACITA+"L");
        System.out.println(Grafica.sep+"Spostamenti fatti: "+this.mosse);
    }
    public void reset(){
        this.bicchieri[0].setLivello(this.bicchieri[0].CAPACITA);
        this.bicchieri[1].setLivello(0);
        this.bicchieri[2].setLivello(0);
        this.mosse = 0;
    }
}
