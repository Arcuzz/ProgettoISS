package pac;
import java.io.Serializable;

public class ProvaModel implements Serializable{
    private String domanda, risposta, indizioPrincipale;
    private Aiutante aiutante;
    private int contaErrori;

    public ProvaModel(String dom, String ris, String hint){
        this.domanda = dom;
        this.risposta = ris;
        this.indizioPrincipale = hint;
        this.contaErrori = 0;
    }

    public String getDomanda() {
        return domanda;
    }

    public String getRisposta() {
        return risposta;
    }

    public String getIndizioPrincipale() {
        return indizioPrincipale;
    }

    public Aiutante getAiutante(){
        return aiutante;
    }

    public void setAiutante(Aiutante aiutante) {
        this.aiutante = aiutante;
    }
    public int getContaErrori(){return this.contaErrori;}
    public void setContaErrori(int contaErrori){this.contaErrori = contaErrori;}

}
