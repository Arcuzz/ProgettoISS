import java.util.*;
public class Difficulty {
    public String difficolta;
    public int numPiani;
    public boolean statica;
    Scanner input = new Scanner(System.in);
    public Difficulty(){
        int stat = staticaScelta();
        if(stat==1){
            this.statica = true;
            int level = staticaLivello();
            switch (level) {
                case 1 -> setDiff("facile", 3);
                case 2 -> setDiff("media", 4);
                case 3 -> setDiff("difficile", 5);
            }
        }
        else{
            this.statica = false;
            setDiff("crescente", 3);
        }
    }

    public void setDiff(String diff, int num){
        this.difficolta = diff;
        this.numPiani = num;
    }
    public int staticaScelta(){
        int scelta;
        do{
            System.out.println("Scegli 1 per difficoltà statica, 2 per crescente:");
            scelta = input.nextInt();
            while(scelta!=1 && scelta!=2){
                System.out.println("Input sbagliato 1! Riprova:");
                scelta = input.nextInt();
            }
        }while(!sceltaSicura());
        return scelta;
    }
    public int staticaLivello(){
        int scelta;
        do{
            System.out.println("Scegli 1 per facile, 2 media, 3 difficile:");
            scelta = input.nextInt();
            while(scelta!=1 && scelta!=2 && scelta!=3){
                System.out.println("Input sbagliato 2! Riprova:");
                scelta = input.nextInt();
            }
        }while(!sceltaSicura());
        return scelta;
    }
    public boolean sceltaSicura(){
        System.out.println("Sei sicuro della tua scelta? (scrivi si/no):");
        input.nextLine();   //non so perché senza questo nextline in più non funziona
        String choice = input.nextLine();
        while(!choice.equalsIgnoreCase("si") && !choice.equalsIgnoreCase("no")){
            System.out.println("Input sbagliato 3! Riprova:");
            choice = input.nextLine();
        }
        return choice.equalsIgnoreCase("si") || !choice.equalsIgnoreCase("no");
    }
    public void printData(){
        System.out.println("Statica: " + this.statica);
        System.out.println("Difficolta: " + this.difficolta);
        System.out.println("Numero piani: " + this.numPiani);
    }
}
