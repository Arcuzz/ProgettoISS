package pac.minigiochi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BriscolaModel extends MinigiocoModel implements Serializable {

    private static final String[] mazzoFisso={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39"};
    private static final int[] value={11,0,10,0,0,0,0,2,3,4};
    private static int ind = 0; //indice scorrimento mazzo
    private static String briscola = "";
    private static String[] mazzo;

    public BriscolaModel(){}

    public String getBriscola(){
        return BriscolaModel.briscola;
    }

    public int getRank(){
        return this.rank;
    }

    public void updateScore(int esito){
        this.punti *= esito;
    }

    public int getIndice(){
        return BriscolaModel.ind;
    }

    public int getValue(int index){
        return BriscolaModel.value[index];
    }

    public ArrayList<String> riempiMano(ArrayList<String> mano){
        while(mano.size()<3){
            mano.add(mazzo[ind++]);
        }
        return mano;
    }

    @Override
    public void inizializza(){
        this.punti = this.rank;
        mazzo = mescolaMazzo(mazzoFisso);
        briscola = mazzo[39];
    }

    public String[] mescolaMazzo(String[] mazzo){
        ArrayList<String> mazzo1 = new ArrayList<>(Arrays.asList(mazzo));
        Collections.shuffle(mazzo1);
        for(int i=0;i< mazzo.length;i++) mazzo[i] = mazzo1.get(i);
        return mazzo;
    }

    public void reset(){
        briscola = "";
        ind = 0;
        this.punti = this.rank;
    }

}
