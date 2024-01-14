package pac;

import java.util.*;

public class Torre {

    public int livello = 1;
    public ArrayList<String> temi = new ArrayList<>();
    public Difficulty diff;
    public Piano pianoCurr;
    public Protagonista pro;
    public ArrayList<Npc> npc = new ArrayList<>();
    private static Torre instance = null;

    private Torre(String[] temi, Difficulty diff, Protagonista pro){
        Collections.addAll(this.temi,temi);
        Collections.shuffle(this.temi);
        this.diff = diff;
        this.pro = pro;
        initNPC();
    }
    public static Torre getInstance(String[] temi, Difficulty diff, Protagonista pro){
        if(instance==null){
            instance = new Torre(temi, diff, pro);
        }
        return instance;
    }

    public void game(Scanner scan){
        ArrayList<Npc> floorNPC = new ArrayList<>(this.npc.subList(this.livello-1,this.livello));
        this.pianoCurr = new Piano(this.livello, this.diff.difficolta, this.temi.get(this.livello), floorNPC);
        this.pro.aiutante.attivaAiuto(this.pianoCurr.tema);
        pro.start(this.pianoCurr);
        while (this.livello <= this.diff.numPiani) {
            pro.move(scan);
            if(this.pianoCurr.dom_sup == this.pianoCurr.n_dom && this.livello+1 <= this.diff.numPiani){
                System.out.println("Hai finito il livello "+this.livello+" con "+this.pro.punteggio_totale +" punti!");
                this.livello ++;
                floorNPC = new ArrayList<>(this.npc.subList(this.livello-1,this.livello));
                this.pianoCurr = new Piano(this.livello, this.diff.difficolta, this.temi.get(this.livello),floorNPC);
                this.pro.aiutante.attivaAiuto(this.pianoCurr.tema);
                pro.start(this.pianoCurr);
            }else{
                System.out.println("Sei uscito dal gioco con un punteggio di "+this.pro.punteggio_totale);
                break;
            }
        }
    }
    public void initNPC(){
        this.npc.add(new Npc("Aldo", new Impiccato("Giovanni", 10, this.diff.difficolta)));
        this.npc.add(new Npc("Giovanni", new VersaLiquido(this.diff.difficolta)));
        this.npc.add(new Npc("Giacomo", new Briscola()));
        this.npc.add(new Npc("Memy", new Memory()));
        //da sostituire
        this.npc.add(new Npc("TEMPORANEO",new VersaLiquido(this.diff.difficolta)));
        Collections.shuffle(this.npc);
    }
}
