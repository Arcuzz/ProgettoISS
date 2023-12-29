package pac;

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

    public void game(){a
        this.pianoCurr = new Piano(this.livello);
        pro.start(this.pianoCurr);
        while (this.livello <= this.diff.numPiani) {
            
            pro.move();
            if(this.pianoCurr.dom_sup == this.pianoCurr.n_dom){
                this.livello ++;
                this.pianoCurr = new Piano(this.livello);
                if(this.pianoCurr.n_npc != 0) pro.start(this.pianoCurr);
                else break;
            }
        }
        System.out.println("HAI VINTO !!!!");
    }
}
