package pac;
import java.io.Serializable;
import java.util.*;

public class Difficulty implements Serializable {
    public String difficolta;
    public int numPiani;
    public boolean statica;
    public Aiutante aiutante;

    public Difficulty(Scanner scan){
        int stat = staticaScelta(scan);
        if(stat==1){
            this.statica = true;
            int level = staticaLivello(scan);
            switch (level) {
                case 1:
                    setDiff("facile", 3);
                    aiutoFacile(scan);

                    break;
                case 2:
                    setDiff("media", 4);
                    sceltaAiutante(scan);
                    break;
                case 3:
                    setDiff("difficile", 5);
                    this.aiutante = new Aiutante(5);
                    break;
                default:
                    break;
            }
        }
        else{
            this.statica = false;
            setDiff("crescente", 3);
            sceltaAiutante(scan);
        }
    }

    public void setDiff(String diff, int num){
        this.difficolta = diff;
        this.numPiani = num;
    }
    public void sceltaAiutante(Scanner scan){
        int scelta;
        do{
            System.out.println("\n"+ Grafica.sep+"Scegli un aiutante per facilitare il tuo viaggio:");
            System.out.println(Grafica.sep+"Scrivi 0 per andare avanti da solo");
            System.out.println(Grafica.sep+"Scrivi 1 per Boris il Cronistorico");
            System.out.println(Grafica.sep+"Scrivi 2 per Rendar il Linguista");
            System.out.println(Grafica.sep+"Scrivi 3 per Sanga il Matematico");
            System.out.print(Grafica.sep+">> ");
            scelta = Integer.parseInt(scan.nextLine());
            while(scelta<0 || scelta>3){
                System.out.println("\n"+ Grafica.sep+"Input sbagliato! Riprova:");
                System.out.print(Grafica.sep+">> ");
                scelta = Integer.parseInt(scan.nextLine());
            }
        }while(!sceltaSicura(scan));
        this.aiutante = new Aiutante(scelta+1);
    }
    public void aiutoFacile(Scanner scan){
        String choice;
        do{
            System.out.println("\n"+ Grafica.sep+"Galgith il Saggio chiede di accompagnarti. Accetti la sua proposta? [s/n]");
            System.out.print(Grafica.sep+">> ");
            choice = scan.nextLine();
            while(!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n")){
                System.out.println("\n"+ Grafica.sep+"Input sbagliato! Riprova:");
                System.out.print(Grafica.sep+">> ");
                choice = scan.nextLine();
            }
        }while(!sceltaSicura(scan));
        if(choice.equalsIgnoreCase("s")) this.aiutante = new Aiutante(1);
        else if(choice.equalsIgnoreCase("n")) this.aiutante = new Aiutante(5);
    }
    public int staticaScelta(Scanner scan){
        int scelta;
        do{
            System.out.println("\n"+ Grafica.sep+"Scegli 1 per difficolta statica, 2 per crescente:");
            System.out.print(Grafica.sep+">> ");
            scelta = Integer.parseInt(scan.nextLine());
            while(scelta!=1 && scelta!=2){
                System.out.println("\n"+ Grafica.sep+"Input sbagliato! Riprova:");
                System.out.print(Grafica.sep+">> ");
                scelta = Integer.parseInt(scan.nextLine());
            }
        }while(!sceltaSicura(scan));
        return scelta;
    }

    public int staticaLivello(Scanner scan){
        int scelta;
        do{
            System.out.println("\n"+ Grafica.sep+"Scegli 1 per facile, 2 media, 3 difficile:");
            System.out.print(Grafica.sep+">> ");
            scelta = Integer.parseInt(scan.nextLine());
            while(scelta!=1 && scelta!=2 && scelta!=3){
                System.out.println("\n"+ Grafica.sep+"Input sbagliato! Riprova:");
                System.out.print(Grafica.sep+">> ");
                scelta = Integer.parseInt(scan.nextLine());
            }
        }while(!sceltaSicura(scan));
        return scelta;
    }
    public boolean sceltaSicura(Scanner scan){
        System.out.println("\n"+ Grafica.sep+"Sei sicuro della tua scelta? [s/n]:");
        System.out.print(Grafica.sep+">> ");
        String choice = scan.nextLine();
        while(!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n")){
            System.out.println("\n"+ Grafica.sep+"Input sbagliato! Riprova:");
            System.out.print(Grafica.sep+">> ");
            choice = scan.nextLine();
        }
        return choice.equalsIgnoreCase("s") || !choice.equalsIgnoreCase("n");
    }
    public void printData(){
        System.out.println("Statica: " + this.statica);
        System.out.println("difficolta: " + this.difficolta);
        System.out.println("Numero piani: " + this.numPiani);
    }
}
