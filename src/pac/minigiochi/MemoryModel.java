package pac.minigiochi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryModel extends MinigiocoModel implements Serializable {

    private ArrayList<Integer> numeri = new ArrayList<>();
    private ArrayList<Integer> numeriVisibili = new ArrayList<>();
    private ArrayList<Integer> coppia = new ArrayList<>();
    private ArrayList<Boolean> giaVisto = new ArrayList<>();
    private int pos=0, coppieScop=0, posPrimaCarta, tentativi=0;
    private String input;
    private boolean inizializzato;

    public MemoryModel(){
        this.inizializzato = false;
    }

    @Override
    public void inizializza(){
        if(!this.inizializzato){
            for(int i=0;i<this.rank*5;i++){
                this.numeri.add(i+1);
                this.numeri.add(i+1);
                this.numeriVisibili.add(0);
                this.numeriVisibili.add(0);
                this.giaVisto.add(false);
            }
            this.giaVisto.add(false);
            this.inizializzato = true;
        }
        Collections.shuffle(this.numeri);
        this.punti = this.rank*100;
    }

    public void setTentativi(int tentativi){
        this.tentativi = tentativi;
    }

    public int getPosPrimaCarta(){
        return this.posPrimaCarta;
    }

    public void setPosPrimaCarta(int posPrimaCarta){
        this.posPrimaCarta = posPrimaCarta;
    }

    public ArrayList<Boolean> getGiaVisto(){
        return this.giaVisto;
    }

    public void setGiaVisto(int index, boolean el){
        this.giaVisto.set(index, el);
    }

    public void setInput(String input){
        this.input = input;
    }

    public String getInput(){
        return this.input;
    }

    public void setPunti(int punti){
        this.punti += punti;
    }

    public int getCoppieScop(){
        return this.coppieScop;
    }

    public void setCoppieScop(int coppieScop){
        this.coppieScop = coppieScop;
    }
    
    public int getPunti(){
        return this.punti;
    }

    public int getTentativi(){
        return this.tentativi;
    }

    public ArrayList<Integer> getNumeri(){
        return this.numeri;
    }

    public ArrayList<Integer> getNumeriVisibili(){
        return this.numeriVisibili;
    }

    public int getPos(){
        return pos;
    }

    public void resetNumeriVisibili(){
        this.numeriVisibili.replaceAll(ignored -> 0);
    }

    public void resetCoppia(){
        this.coppia.clear();
    }

    public void resetGiaVisto(){
        this.giaVisto.replaceAll(ignored -> false);
    }

    public void setPos(int pos){
        this.pos += pos;
    }

    public int getRank(){
        return this.rank;
    }

    public ArrayList<Integer> getCoppia(){
        return this.coppia;
    }

    public void setCoppia(int num){
        this.coppia.add(num);
    }

    public void setNumeriVisibili(int pos, int coppia){
        this.numeriVisibili.set(pos, coppia);
    }
}
