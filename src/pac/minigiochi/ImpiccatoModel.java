package pac.minigiochi;

import java.io.Serializable;
import java.util.Random;

public class ImpiccatoModel extends MinigiocoModel implements Serializable {

    private static final String[] dictionary = new String[] {"Andrea", "Gloria", "Giovanni", "Corallo", "Globalizzazione", "Transoceanico", "Almanacco", "Aracnofobia", "Onomatopea", "Spinterogeno", "Sillogismo", "Poliptoto", "Trabiccolo", "Idilliaco", "Pernicioso"};
    private String secret;
    public StringBuilder guessed;
    public int remainingAttempts;

    public String getSecret(){
        return this.secret;
    }

    public ImpiccatoModel(int maxAttempts, String difficolta){
        Random rand = new Random();
        int sec = rand.nextInt(15);
        this.secret = ImpiccatoModel.dictionary[sec];
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
