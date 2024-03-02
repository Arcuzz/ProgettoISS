package pac.minigiochi;

import java.io.Serializable;

public abstract class MinigiocoModel implements Serializable {

    public int punti, rank; //rank è 1,2,3 in base alla difficolta
    public String difficolta;

    public abstract void inizializza();
}
