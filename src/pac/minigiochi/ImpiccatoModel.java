package pac.minigiochi;

import java.io.Serializable;

public class ImpiccatoModel extends MinigiocoModel implements Serializable {
    public String secret;
    public StringBuilder guessed;
    public int remainingAttempts;

    public ImpiccatoModel(String secret, int maxAttempts, String difficolta){
        this.secret = secret;
        this.remainingAttempts = maxAttempts;
        this.difficolta = difficolta;
        inizializza();
    }

    @Override
    public void inizializza(){
        this.guessed = new StringBuilder(this.secret.length());
        if (this.rank==1){
            this.guessed.insert(0, this.secret.charAt(0));
            for (int i = 1; i < this.secret.length()-1; i++) this.guessed.append('_');
            this.guessed.insert(this.guessed.length(), this.secret.charAt(this.secret.length()-1));
        }else if(this.rank==2){
            this.guessed.insert(0, this.secret.charAt(0));
            for (int i = 1; i < this.secret.length(); i++) this.guessed.append('_');
        }else for (int i = 0; i < this.secret.length(); i++) this.guessed.append('_');
        this.punti = this.rank*100;
    }
}