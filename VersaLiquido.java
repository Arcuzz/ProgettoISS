package pac;
import java.util.*;
public class VersaLiquido extends Minigiochi {
    public Serbatoio[] bicchieri = new Serbatoio[3];
    public int obiettivo, mosse=0;
    public VersaLiquido(String diff){
        this.difficolta = diff;
        inizializza();
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
        System.out.println("Davanti a te vedi tre bicchieri di "+this.bicchieri[0].CAPACITA+", "+this.bicchieri[1].CAPACITA+", "+this.bicchieri[2].CAPACITA+" litri rispettivamente");
        System.out.println("Il primo è pieno d'acqua e gli altri due sono vuoti");
        System.out.println("Il tuo obiettivo è spostare l'acqua tra i bicchieri in modo che il primo e il secondo contengano "+this.obiettivo+" litri");
        System.out.println("Versare acqua più del limite di un bicchiere semplicemente lo riempie fino all'orlo, senza alcuna perdita");
        System.out.println("Puoi fare tutti gli spostamenti che vuoi, ma per la soluzione più rapida ne servono solo "+(this.bicchieri[0].CAPACITA-1));
        System.out.println("Buona fortuna e divertiti!");
    }

    @Override
    public int play(Scanner sca){
        startGame();
        int scelta, dest;
        while(!(this.bicchieri[0].livello==this.obiettivo && this.bicchieri[0].livello==this.bicchieri[1].livello)){
            stampaBicchieri();
            System.out.println("\nCosa vuoi fare? Digita 1,2,3 per travasare dal rispettivo bicchiere o 4 per resettare: ");
            scelta = sca.nextInt();
            while(scelta>4 || scelta<1){
                System.out.println("Input sbagliato! Riprova");
                scelta = sca.nextInt();
            }
            if(scelta==4){
                reset();
                continue;
            }
            if(this.bicchieri[scelta-1].livello==0){
                System.out.println("Il bicchiere che hai scelto è vuoto! Riprova");
                continue;
            }
            System.out.println("Scegli il bicchiere da riempire con 1,2,3:");
            dest = sca.nextInt();
            while(dest>3 || dest<1){
                System.out.println("Input sbagliato! Riprova");
                dest = sca.nextInt();
            }
            if(this.bicchieri[dest-1].livello==this.bicchieri[dest-1].CAPACITA){
                System.out.println("Il bicchiere che hai scelto è pieno! Riprova");
                continue;
            }
            if(scelta!=dest){
                this.bicchieri[scelta-1].travasa(this.bicchieri[dest-1],this.bicchieri[scelta-1].livello);
                this.mosse++;
            }
            else System.out.println("Hai scelto il bicchiere di partenza! Riprova");
        }
        if(this.mosse==this.bicchieri[0].CAPACITA-1){
            System.out.println("Complimenti! Hai finito nel numero minimo di spostamenti possibili!");
            this.punti += this.punti;
        }
        else System.out.println("\nComplimenti! Hai finito correttamente con "+this.mosse+" spostamenti!");
        System.out.println("Hai ottenuto "+this.punti+" punti!");
        return this.punti;
    }
    public void stampaBicchieri(){
        System.out.println("\n|\t\t|");
        System.out.println("|\t\t|\t|\t\t|");
        System.out.println("|   "+this.bicchieri[0].livello+"\t|\t|\t\t|\t|\t\t|");
        System.out.println("|\t\t|\t|   "+this.bicchieri[1].livello+"\t|\t|\t\t|");
        System.out.println("|\t\t|\t|\t\t|\t|   "+this.bicchieri[2].livello+"\t|");
        System.out.println("|-------|\t|-------|\t|-------|");
        System.out.println("    "+this.bicchieri[0].CAPACITA+"L\t\t    "+this.bicchieri[1].CAPACITA+"L\t\t    "+this.bicchieri[2].CAPACITA+"L");
        System.out.println("Spostamenti fatti: "+this.mosse);
    }
    public void reset(){
        this.bicchieri[0].setLivello(this.bicchieri[0].CAPACITA);
        this.bicchieri[1].setLivello(0);
        this.bicchieri[2].setLivello(0);
        this.mosse = 0;
    }
}