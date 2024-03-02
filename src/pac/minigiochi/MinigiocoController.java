package pac.minigiochi;

import java.io.Serializable;
import java.util.Scanner;

public abstract class MinigiocoController implements Serializable {

    public abstract boolean play(Scanner sca, String nome);
}
