package pac;
import java.util.*;

public class Difficulty {
    public String difficolta;
    public int numPiani;
    public boolean statica;

    public Difficulty(Scanner scan){
        int stat = staticaScelta(scan);
        if(stat==1){
            this.statica = true;
            int level = staticaLivello(scan);
            switch (level) {
                case 1:
                    setDiff("facile", 3);
                    break;
                case 2:
                    setDiff("media", 4);
                    break;
                case 3:
                    setDiff("difficile", 5);
                    break;
                default:
                    break;
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
    public int staticaScelta(Scanner scan){
        int scelta;
        do{
            System.out.println("Scegli 1 per difficolta statica, 2 per crescente:");
            scelta = scan.nextInt();
            while(scelta!=1 && scelta!=2){
                System.out.println("Input sbagliato! Riprova:");
                scelta = scan.nextInt();
            }
        }while(!sceltaSicura(scan));
        return scelta;
    }

    public int staticaLivello(Scanner scan){
        int scelta;
        do{
            System.out.println("Scegli 1 per facile, 2 media, 3 difficile:");
            scelta = scan.nextInt();
            while(scelta!=1 && scelta!=2 && scelta!=3){
                System.out.println("Input sbagliato! Riprova:");
                scelta = scan.nextInt();
            }
        }while(!sceltaSicura(scan));
        return scelta;
    }
    public boolean sceltaSicura(Scanner scan){
        System.out.println("Sei sicuro della tua scelta? (scrivi si/no):");
        scan.nextLine();   //non so perché senza questo nextline in più non funziona
        String choice = scan.nextLine();
        while(!choice.equalsIgnoreCase("si") && !choice.equalsIgnoreCase("no")){
            System.out.println("Input sbagliato! Riprova:");
            choice = scan.nextLine();
        }
        return choice.equalsIgnoreCase("si") || !choice.equalsIgnoreCase("no");
    }
    public void printData(){
        System.out.println("Statica: " + this.statica);
        System.out.println("difficolta: " + this.difficolta);
        System.out.println("Numero piani: " + this.numPiani);
    }
}
