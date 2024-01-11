package pac;
import java.util.*;
public class Prova {
    public String domanda, risposta, indizioPrincipale;
    public int ranking, contaErrori;

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
    public boolean faiDomanda(Scanner scan){
        String answer = "";
        while(!answer.equalsIgnoreCase(this.risposta)){
            printDomanda();
            if(contaErrori>2) System.out.println("Indizio: "+this.indizioPrincipale);
            System.out.println("Risposta: ");
            answer = scan.nextLine();
            if(answer.equalsIgnoreCase(this.risposta)){
                System.out.println("Risposta esatta con "+this.contaErrori+" errori! Vai avanti");
                return true;
            }
            else if(answer.equalsIgnoreCase("exit")){
                System.out.println("Sei uscito senza rispondere correttamente, ritorno al movimento");
                break;
            }
            contaErrori++;
            System.out.println("Risposta sbagliata! Riprova o scrivi \"exit\" per uscire");
        }
        return false;
    }

    public void summary(){
        System.out.println( "\nDomanda: " + this.domanda +
                            "\nRisposta: " + this.risposta +
                            "\nDifficoltà: " + this.ranking);
    }
}
