package pac;

import java.io.Serializable;

public class Serbatoio implements Serializable{
    public int livello;
    public int CAPACITA;
    public Serbatoio(){};
    public Serbatoio(int cap, int l){
        this.CAPACITA = cap;
        this.livello = l;
    }
    public void travasa(Serbatoio other, int j){
        if(this.livello<j){
            other.rifornisci(this.livello);
            this.livello = 0;
        }
        else if(other.livello+j>other.CAPACITA){
            this.consuma(other.CAPACITA - other.livello);
            other.livello = other.CAPACITA;
        }
        else{
            this.consuma(j);
            other.rifornisci(j);
        }
    }
    public void rifornisci (int j){
        if(livello+j < CAPACITA) {
            this.livello += j;
        }
        else{
            this.livello = this.CAPACITA;
        }
    }
    public void consuma(int j){
        if(livello > j){
            this.livello -= j;
        }
        else{
            this.livello = 0;
        }
    }
    public int getLivello(){
        return this.livello;
    }
    public void setLivello(int l){this.livello = l;}
}
