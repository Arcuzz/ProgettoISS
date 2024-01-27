package pac;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Minigiochi implements Serializable{
    public abstract void inizializza();
    public abstract void startGame();
    public abstract boolean play(Scanner sca);
    public int punti, rank; //rank è 1,2,3 in base alla difficolta
    public String difficolta;
}