package pac;

import java.util.Scanner;

public class Torre {

    public int livello = 1;
    public String[] temi;
    public Difficulty diff;
    public Piano pianoCurr;
    public Protagonista pro;

    public Torre(String[] temi, Difficulty diff, Protagonista pro){
        this.temi = temi;
        this.diff = diff;
        this.pro = pro;
    }

    public void game(Scanner scan){
        this.pianoCurr = new Piano(this.livello, this.diff.difficoltà, "Matematica");
        pro.start(this.pianoCurr);
        while (this.livello <= this.diff.numPiani) {
            pro.move(scan);
            if(this.pianoCurr.dom_sup == this.pianoCurr.n_dom && this.livello+1 <= this.diff.numPiani){
                this.livello ++;
                this.pianoCurr = new Piano(this.livello, this.diff.difficoltà, "Matematica");
                pro.start(this.pianoCurr);
            }else{
                System.out.println("Sei uscito dal gioco");
                break;
            }
        }
    }
}
