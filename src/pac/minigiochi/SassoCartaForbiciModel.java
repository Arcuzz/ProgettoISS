package pac.minigiochi;

import java.io.Serializable;
import java.util.*;

public class SassoCartaForbiciModel extends MinigiocoModel implements Serializable {

    public int obiettivo;
    public ArrayList<String> sceltaPC = new ArrayList<>();

    public SassoCartaForbiciModel(){
        Collections.addAll(this.sceltaPC,"S","C","F");
    }
    
    @Override
    public void inizializza(){
        this.obiettivo = this.rank+1;
        this.punti = this.rank*50;
        Collections.shuffle(this.sceltaPC);
    }

}