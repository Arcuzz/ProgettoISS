package pac;

import java.io.Serializable;

public class RecordPersona implements Serializable {
    public String nome;
    public int punteggio;
    
    public RecordPersona(String n, int p){
        this.nome = n;
        this.punteggio = p;
    }
}
