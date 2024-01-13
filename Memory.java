package pac;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Memory extends Minigiochi{
    public ArrayList<Integer> numeri = new ArrayList<>();
    public ArrayList<Integer> numeriVisibili = new ArrayList<>();
    public ArrayList<Integer> coppia = new ArrayList<>();
    public ArrayList<Boolean> giaVisto = new ArrayList<>();
    public int pos=0, coppieScop=0, posPrimaCarta, tentativi=0;
    public String input;
    public Memory(){}
    @Override
    public void inizializza(){
        for(int i=0;i<this.rank*5;i++){
            this.numeri.add(i+1);
            this.numeri.add(i+1);
            this.numeriVisibili.add(0);
            this.numeriVisibili.add(0);
            this.giaVisto.add(false);
        }
        this.giaVisto.add(false);
        Collections.shuffle(this.numeri);
        this.punti = this.rank*100;
    }
    @Override
    public void startGame()  {
        System.out.println("--- Gioco del Memory ---");
        System.out.println("Davanti a te vedi "+this.numeri.size()/2+" coppie di carte numerate da 1 a "+this.rank*5);
        System.out.println("Verranno mischiate e coperte dopo qualche secondo");
        System.out.println("Il tuo obiettivo è scoprirle tutte, due alla volta, accoppiandole correttamente");
        System.out.println("Se sbagli quelle rimanenti verrano scoperte per qualche secondo e mischiate di nuovo");
        System.out.println("Buona fortuna e divertiti!");
        attesa(15);
    }
    @Override
    public boolean play(Scanner sca){
        printNumeri(this.numeri);
        attesa(5*this.rank);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        this.move(sca);
        if(this.input.equalsIgnoreCase("exit")) return false;
        if(this.tentativi==this.coppieScop){
            System.out.println("Complimenti! Hai trovato tutte le coppie di carte senza errori!");
            this.punti += this.punti;
        }
        else System.out.println("Complimenti! Hai trovato tutte le coppie di carte con "+this.tentativi+" tentativi");
        return true;
    }
    public void move(Scanner sca){
        this.input = "";
        while(!this.input.equalsIgnoreCase("exit") && this.coppieScop!=this.numeri.size()/2){
            printNumeri(this.numeriVisibili);
            System.out.println("In che direzione vuoi andare?");
            System.out.println("d per destra, a per sinistra, w per sopra, s per sotto, x per selezionare, r per resettare, exit per uscire: ");
            this.input = sca.nextLine();
            switch (this.input) {
                case "d","D" -> {
                    if (legalCoord(this.pos + 1) && this.numeri.get(this.pos + 1) != null) this.pos++;
                    else System.out.println("Direzione non valida");
                }
                case "a","A" -> {
                    if (legalCoord(this.pos - 1) && this.numeri.get(this.pos - 1) != null) this.pos--;
                    else System.out.println("Direzione non valida");
                }
                case "w","W" -> {
                    if (legalCoord(this.pos - 5) && this.numeri.get(this.pos - 5) != null) this.pos -= 5;
                    else System.out.println("Direzione non valida");
                }
                case "s","S" -> {
                    if (legalCoord(this.pos + 5) && this.numeri.get(this.pos + 5) != null) this.pos += 5;
                    else System.out.println("Direzione non valida");
                }
                case "r","R" -> reset();
                case "h" -> printNumeri(this.numeri);
                case "x","X" -> {
                    if(this.numeriVisibili.get(this.pos)!=0){
                        System.out.println("Hai già scoperto questa carta! Riprova");
                        continue;
                    }
                    selezioneCarta(checkCoppia(this.numeri.get(this.pos)));
                    if(this.coppieScop==this.numeri.size()/2){
                        System.out.println("Congratulazioni, hai trovato tutte le coppie di carte!");
                    }
                }
            }
        }
    }
    public void selezioneCarta(int check){
        //è stata scelta solo la prima carta
        if(check==0){
            this.numeriVisibili.set(this.pos,this.coppia.get(0));
            this.posPrimaCarta = this.pos;
        }
        //le due carte scelte sono uguali
        else if(check==1){
            this.numeriVisibili.set(this.numeri.indexOf(this.coppia.get(0)),this.coppia.get(0));
            this.numeriVisibili.set(this.numeri.lastIndexOf(this.coppia.get(0)),this.coppia.get(0));
            this.coppia.clear();
            this.coppieScop++;
            this.tentativi++;
            System.out.println("Indovinato!");
        }
        //le due carte scelte sono diverse
        else if(check==2){
            this.numeriVisibili.set(this.pos,this.coppia.get(1));
            printNumeri(this.numeriVisibili);
            System.out.println("Errore! Le due carte sono diverse");
            System.out.println("Le carte coperte verranno mischiate!");
            this.numeriVisibili.set(this.posPrimaCarta,0);
            this.numeriVisibili.set(this.pos,0);
            Collections.shuffle(this.numeri);
            for(int i=0;i<this.numeriVisibili.size();i++){
                int tmp = this.numeriVisibili.get(i);
                if(tmp!=0 && !this.giaVisto.get(tmp)){
                    Collections.swap(this.numeri,this.numeriVisibili.indexOf(tmp),this.numeri.indexOf(tmp));
                    Collections.swap(this.numeri,this.numeriVisibili.lastIndexOf(tmp),this.numeri.lastIndexOf(tmp));
                    this.giaVisto.set(tmp,true);
                }
            }
            printNumeri(this.numeri);
            attesa(5*this.rank);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            this.giaVisto.replaceAll(ignored -> false);
            this.coppia.clear();
            this.tentativi++;
        }
    }
    public boolean legalCoord(int x){
        return x >= 0 && x < this.numeri.size();
    }
    public int checkCoppia(int num){
        //aggiunge la prima carta nella coppia da confrontare
        if(this.coppia.size()<2) this.coppia.add(num);
        //se è stata scelta la seconda carta le confronta
        if(this.coppia.size()==2){
            if(this.coppia.get(0)==this.coppia.get(1)) return 1;
            else return 2;
        }
        return 0;
        //serve per il metodo selezioneCarta
    }
    public void printNumeri(ArrayList<Integer> arr){
        System.out.print("\n");
        for (int i = 0; i < arr.size(); i++) {
            if(i%5==0)
                System.out.print("\n");
            if(arr.get(i)<10)
                System.out.print("0");
            if(i==this.pos){
                System.out.print(arr.get(i) + "* ");
            }
            else System.out.print(arr.get(i) + "  ");
        }
        System.out.println("\n");
    }
    public void reset(){
        this.pos = this.coppieScop = this.posPrimaCarta = this.tentativi = 0;
        this.numeri.clear();
        this.numeriVisibili.clear();
        this.coppia.clear();
        this.giaVisto.clear();
    }
    private void attesa(int t){
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}