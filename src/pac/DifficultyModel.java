package pac;
import java.io.Serializable;

public class DifficultyModel implements Serializable {
    private String difficolta;
    private int numPiani;
    private boolean statica;
    private Aiutante aiutante;

    public DifficultyModel(){}

    public void setStatica(boolean statica){
        this.statica = statica;
    }

    public void setDiff(String diff, int num){
        this.difficolta = diff;
        this.numPiani = num;
    }

    public int getNumPiani(){
        return this.numPiani;
    }

    public String getDifficolta(){
        return this.difficolta;
    }

    public void setAiutante(Aiutante aiutante){
        this.aiutante = aiutante;
    }

    public Aiutante getAiutante(){
        return this.aiutante;
    }
}
