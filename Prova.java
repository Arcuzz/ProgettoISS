import java.util.*;
public class Prova {
    public String domanda;
    public String risposta;
    public String indizioPrincipale;
    public ArrayList<String> indiziGenerici = new ArrayList<>();
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
    public void faiDomanda(){
        Scanner input = new Scanner(System.in);
        String answer = "";
        while(!answer.equalsIgnoreCase(this.risposta)){
            printDomanda();
            System.out.println("Risposta: ");
            answer = input.nextLine();
            if(answer.equalsIgnoreCase(this.risposta)){
                System.out.println("Risposta esatta! Vai avanti");
                break;
            }
            System.out.println("Risposta sbagliata! Riprova");
            contaErrori++;
        }
    }

    public void summary(){
        System.out.println( "\nDomanda: " + this.domanda +
                            "\nRisposta: " + this.risposta +
                            "\nDifficoltà: " + this.ranking);
    }
}
