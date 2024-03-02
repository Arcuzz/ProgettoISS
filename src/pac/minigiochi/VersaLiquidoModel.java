package pac.minigiochi;

import java.io.Serializable;

public class VersaLiquidoModel extends MinigiocoModel implements Serializable {

    private Serbatoio[] bicchieri = new Serbatoio[3];
    private int obiettivo, mosse=0;

    public VersaLiquidoModel(String diff){
        this.difficolta = diff;
    }

    public Serbatoio getBicchieri(int i){
        return this.bicchieri[i];
    }

    public int getObiettivo(){
        return this.obiettivo;
    }

    public int getMosse(){
        return this.mosse;
    }

    public void setMosse(int val){
        this.mosse = val;
    }
    
    public void setBicchieri(int index, int val){
        this.bicchieri[index].setLivello(val);;
    }

    public void travaso(int index1, int index2){
        this.bicchieri[index1].travasa(this.bicchieri[index2],this.bicchieri[index1].livello);
        this.mosse++;
    }

    public void updateScore(){
        this.punti += this.punti;
    }

    @Override
    public void inizializza(){
        switch (this.rank) {
            case 1 -> {
                this.bicchieri[1] = new Serbatoio(5, 0);
                this.bicchieri[2] = new Serbatoio(3, 0);
                this.punti = 100;
            }
            case 2 -> {
                this.bicchieri[1] = new Serbatoio(7, 0);
                this.bicchieri[2] = new Serbatoio(3, 0);
                this.punti = 150;
            }
            case 3 -> {
                this.bicchieri[1] = new Serbatoio(9, 0);
                this.bicchieri[2] = new Serbatoio(7, 0);
                this.punti = 200;
            }
        }
        this.bicchieri[0] = new Serbatoio(this.bicchieri[1].CAPACITA+this.bicchieri[2].CAPACITA,this.bicchieri[1].CAPACITA+this.bicchieri[2].CAPACITA);
        this.obiettivo = this.bicchieri[0].CAPACITA/2;
    }

    public void reset(){
        this.bicchieri[0].setLivello(this.bicchieri[0].CAPACITA);
        this.bicchieri[1].setLivello(0);
        this.bicchieri[2].setLivello(0);
        this.mosse = 0;
    }
}
