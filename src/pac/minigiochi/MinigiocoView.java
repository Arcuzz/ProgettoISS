package pac.minigiochi;

import java.io.Serializable;

public abstract class MinigiocoView implements Serializable {

    public abstract void startGame(int ...val);
    public abstract void initGraphic(String nome);
}
