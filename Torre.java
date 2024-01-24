package pac;

import java.io.Serializable;
import java.util.*;

public class Torre implements Serializable {

    public int livello;
    public ArrayList<String> temi = new ArrayList<>();
    public Difficulty diff;
    public Piano pianoCurr;
    public Protagonista pro;
    public ArrayList<Npc> npc = new ArrayList<>();
    private static Torre instance = null;
    private GameCaretaker caretaker;

    private Torre(ArrayList<String> temi, Difficulty diff, Protagonista pro, Piano piano, int livello, GameCaretaker caretaker){
        this.temi = temi;
        this.diff = diff;
        this.livello = livello;
        this.pianoCurr = piano;
        this.pro = pro;
        this.caretaker = caretaker;
        initNPC();
    }
    public static Torre getDefaultInstance(ArrayList<String> temi, Difficulty diff, Protagonista pro, GameCaretaker caretaker){
        if(instance==null){
            instance = new Torre(temi, diff, pro, null, 1, caretaker);
            Collections.shuffle(instance.temi);
        }
        return instance;
    }

    public static Torre getSavedInstance(ArrayList<String> temi, Difficulty diff, Protagonista pro, Piano piano, GameCaretaker caretaker){
        if(instance==null){
            instance = new Torre(temi, diff, pro, piano, piano.livello, caretaker);
            Collections.shuffle(instance.temi);
        }
        return instance;
    }

    public void setupTorre(){
        ArrayList<Npc> floorNPC = new ArrayList<>(this.npc.subList(this.livello-1,this.livello));
        this.pianoCurr = new Piano(this.livello, this.diff.difficolta, this.temi.get(this.livello), floorNPC);
        this.pro.aiutante.attivaAiuto(this.pianoCurr.tema);
        pro.start(this.pianoCurr);
    }
    public void game(Scanner scan){
        pro.piano.stampaMatrice();
        while (this.livello <= this.diff.numPiani) {
            int out = 0;
            while (out != 1 && out != 3){
                out = pro.move(scan);
                if (out == 2){
                    GameMemento memento = new GameMemento(this.diff, this.temi, this.pianoCurr,
                            pro.nome, new int[]{pro.x, pro.y}, pro.visited, pro.aiutante,
                            new int[]{pro.n_dom_risposte, pro.n_errori_dom, pro.punti_dom},
                            new int[]{pro.n_mini_risolti, pro.punti_mini});
                    this.caretaker.addSnapshot(memento);
                    this.caretaker.saveGame("saves/save.ser");
                    System.out.println("Salvataggio avvenuto con successo");
                }
            }
            if(out == 1 && this.livello+1 <= this.diff.numPiani){
                System.out.println("Hai finito il livello "+this.livello+" con "+this.pro.punteggio_totale +" punti!");
                this.livello ++;
                setupTorre();
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
