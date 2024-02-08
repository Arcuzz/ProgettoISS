package pac;

import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import pac.minigiochi.*;
import pac.stanze.Npc;

public class Torre implements Serializable {

    public int livello;
    public ArrayList<String> temi;
    public Difficulty diff;
    public Piano pianoCurr;
    public Protagonista pro;
    public ArrayList<Npc> npc = new ArrayList<>();
    private static Torre instance = null;
    private GameCaretaker caretaker;
    private long time;

    private Torre(ArrayList<String> temi, Difficulty diff, Protagonista pro, Piano piano, int livello, GameCaretaker caretaker, long time){
        this.temi = temi;
        this.diff = diff;
        this.livello = livello;
        this.pianoCurr = piano;
        this.pro = pro;
        this.caretaker = caretaker;
        this.time = time;
        initNPC();
    }
    public static Torre getDefaultInstance(ArrayList<String> temi, Difficulty diff, Protagonista pro, GameCaretaker caretaker){
        if(instance==null){
            instance = new Torre(temi, diff, pro, null, 1, caretaker, 0);
            Collections.shuffle(instance.temi);
        }
        return instance;
    }

    public static Torre getSavedInstance(ArrayList<String> temi, Difficulty diff, Protagonista pro, Piano piano, GameCaretaker caretaker, long time){
        if(instance==null){
            instance = new Torre(temi, diff, pro, piano, piano.livello, caretaker, time);
            Collections.shuffle(instance.temi);
        }
        return instance;
    }

    public void setupTorre(){
        ArrayList<Npc> floorNPC = new ArrayList<>(this.npc.subList(this.livello-1,this.livello));
        this.pianoCurr = new Piano(this.livello, this.diff.difficolta, this.temi.get(this.livello), floorNPC);
        this.pro.aiutante.attivaAiuto(this.pianoCurr.tema);
        System.out.println(this.time);
        pro.start(this.pianoCurr);
    }
    public void game(Scanner scan) throws IOException{
        Instant start;
        long penalty;
        //intro(scan);
        //pro.piano.stampaMatrice();
        while (this.livello <= this.diff.numPiani) {
            start = Instant.now();
            int out = 0;
            while (out != 1 && out != 3){
                out = pro.move(scan);
                if (out == 2){
                    System.out.println("Punteggio prima :" + this.pro.punteggio_totale);
                    penalty = time_penalty(start, Instant.now());
                    System.out.println(penalty + "s Dopo " + this.pro.punteggio_totale);
                    GameMemento memento = new GameMemento(this.diff, this.temi, this.pianoCurr,
                            pro.nome, new int[]{pro.x, pro.y}, pro.visited, pro.aiutante,
                            new int[]{pro.n_dom_risposte, pro.n_errori_dom, pro.punti_dom},
                            new int[]{pro.n_mini_risolti, pro.punti_mini}, pro.punteggio_totale, penalty);
                    this.caretaker.addSnapshot(memento);
                    this.caretaker.saveGame("../../../saves/save.ser");
                    System.out.println("Salvataggio avvenuto con successo");
                    System.out.println("Premi invio per riprendere il gioco");
                    scan.nextLine();
                }
            }
            if(out == 1 && this.livello+1 <= this.diff.numPiani){
                System.out.println("Punteggio prima :" + this.pro.punteggio_totale);
                penalty = time_penalty(start, Instant.now());
                System.out.println(penalty + "s Dopo " + this.pro.punteggio_totale);
                System.out.println("Hai finito il livello "+this.livello+" con "+this.pro.punteggio_totale +" punti!");
                this.livello ++;
                this.time = 0;
                System.out.println("Premi invio per riprendere il gioco");
                scan.nextLine();
                setupTorre();
            }else{
                time_penalty(start, Instant.now());
                System.out.println("Sei uscito dal gioco con un punteggio di "+this.pro.punteggio_totale);
                System.out.println("Premi invio per riprendere il gioco");
                scan.nextLine();
                break;
            }
        }
        classifica();
        crediti();
        System.out.println("Premi invio per riprendere il gioco");
        scan.nextLine();
    }

    public long time_penalty(Instant start, Instant end){
        long tot = Duration.between(start, end).toSeconds() + this.time;
        pro.punteggio_totale -= (int) (tot/2);
        return tot;
    }
    public void initNPC(){
        this.npc.add(new Npc("Aldo", new Impiccato("Giovanni", 10, this.diff.difficolta)));
        this.npc.add(new Npc("Giovanni", new VersaLiquido(this.diff.difficolta)));
        this.npc.add(new Npc("Giacomo", new Briscola()));
        this.npc.add(new Npc("Memy", new Memory()));
        this.npc.add(new Npc("SCF",new SassoCartaForbici()));
        Collections.shuffle(this.npc);
    }
    public void crediti(){
        System.out.println("\nGrazie per aver giocato a Tower of Trials!");
        System.out.println("Sviluppato da: Arcuri Andrea, Palazzolo Gloria, Palmeri Giovanni");
    }
    public void classifica() throws IOException {
        Classifica c = new Classifica();
        c.aggiornaRecord(this.pro.nome,this.pro.punteggio_totale);
        c.stampaClassifica();
        c.scriviClassifica();
    }
    public void intro(Scanner scan){
        System.out.println("Una mattina come tante. Una sveglia suona. "+this.pro.nome+", uno studente di informatica apre gli occhi.\n" +
                           "Per lui oggi è un giorno importante: deve sostenere il suo ultimo esame.\n" +
                           "Apre la bocca per sbadigliare ma si accorge di non potere emettere alcun suono.\n" +
                           "Spaventato viene preso dal panico e per alcuni minuti prova in tutti i modi a parlare, a gridare, cantare ma senza risultato.\n" +
                           "Poi torna lucido: si ricordò di aver sentito parlare di una \"Torre della Conoscenza\", in cima alla quale vi era un artefatto sacro in grado di esaudire un desiderio.\n" +
                           "Con la speranza nel cuore e un aiutante al suo fianco, si mette alla ricerca della Torre e appena la trova, comincia la sua ascesa.\n" +
                           "Inserisci un carattere qualunque per andare avanti: ");
        scan.nextLine();
    }
}
