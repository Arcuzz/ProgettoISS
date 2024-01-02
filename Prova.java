package pac;
import java.util.*;
public class Prova {
    public String domanda;
    public String risposta;
    public String indizioPrincipale;
    public int ranking;
    public int contaErrori;
    public Prova(String dom, String ris, String hint, int rank){
        this.domanda = dom;
        this.risposta = ris;
        this.indizioPrincipale = hint;
        this.ranking = rank;
        this.contaErrori = 0;
    }
    public void printDomanda(){
        System.out.println(this.domanda);
    }
    public boolean faiDomanda(){
        Scanner input = new Scanner(System.in);
        String answer = "";
        while(!answer.equalsIgnoreCase(this.risposta)){
            printDomanda();
            if(contaErrori>2) System.out.println("Indizio: "+this.indizioPrincipale);
            System.out.println("Risposta: ");
            answer = input.nextLine();
            if(answer.equalsIgnoreCase(this.risposta)){
                System.out.println("Risposta esatta con "+this.contaErrori+" tentativi! Vai avanti");
                return true;
            }
            contaErrori++;
            System.out.println("Risposta sbagliata! Riprova o scrivi \"stop\" per uscire");
            if(answer.equalsIgnoreCase("stop")){
                System.out.println("Sei uscito senza rispondere correttamente, ritorno al movimento");
                break;
            }
        }
        return false;
    }

    public void summary(){
        System.out.println( "\nDomanda: " + this.domanda +
                            "\nRisposta: " + this.risposta +
                            "\nDifficoltà: " + this.ranking);
    }
}
