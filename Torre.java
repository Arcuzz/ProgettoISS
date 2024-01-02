package pac;

import java.util.*;

public class Torre {

    public int livello = 1;
    public ArrayList<String> temi = new ArrayList<>();
    public Difficulty diff;
    public Piano pianoCurr;
    public Protagonista pro;

    public Torre(String[] temi, Difficulty diff, Protagonista pro){
        Collections.addAll(this.temi,temi);
        Collections.shuffle(this.temi);
        this.diff = diff;
        this.pro = pro;
    }

    public void game(Scanner scan){
        this.pianoCurr = new Piano(this.livello, this.diff.difficolta, this.temi.get(this.livello));
        pro.start(this.pianoCurr);
        while (this.livello <= this.diff.numPiani) {
            pro.move(scan);
            if(this.pianoCurr.dom_sup == this.pianoCurr.n_dom && this.livello+1 <= this.diff.numPiani){
                System.out.println("Hai finito il livello "+this.livello+" con "+this.pro.punteggio+" punti!");
                this.livello ++;
                this.pianoCurr = new Piano(this.livello, this.diff.difficolta, this.temi.get(this.livello));
                pro.start(this.pianoCurr);
            }else{
                System.out.println("Sei uscito dal gioco con un punteggio di "+this.pro.punteggio);
                break;
            }
        }
    }
}
