package pac;
import java.io.Serializable;
import java.util.*;
public class Prova implements Serializable{
    public String domanda, risposta, indizioPrincipale;
    public int ranking, contaErrori=0;
    public Aiutante aiutante;

    public Prova(String dom, String ris, String hint, int rank){
        this.domanda = dom;
        this.risposta = ris;
        this.indizioPrincipale = hint;
        this.ranking = rank;
    }

    public boolean faiDomanda(Scanner scan){
        String answer = "";
        while(!answer.equalsIgnoreCase(this.risposta)){
            System.out.println(this.domanda);
            if(this.contaErrori>2) daiIndizio();
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
            else if(answer.equalsIgnoreCase("help")){
                System.out.println("LA RISPOSTA E': "+this.risposta);
                continue;
            }
            this.contaErrori++;
            System.out.println("Risposta sbagliata! Riprova o scrivi \"exit\" per uscire\n");
        }
        return false;
    }

    public void daiIndizio(){
        if(this.aiutante.aiuto){
            System.out.println("INDIZIO: "+this.indizioPrincipale);
        }
    }

    public void summary(){
        System.out.println( "\nDomanda: " + this.domanda +
                            "\nRisposta: " + this.risposta +
                            "\nDifficoltà: " + this.ranking);
    }
}
