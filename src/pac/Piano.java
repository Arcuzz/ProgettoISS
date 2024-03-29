package pac;

import java.io.Serializable;
import java.util.*;

import pac.stanze.*;

import java.io.FileNotFoundException;

public class Piano implements Serializable {
    public int livello, n_dom, dom_sup=0;
    public String tema, difficolta;
    public Stanza[][] mat;
    public int[] start;
    public ArrayList<Npc> npc;

    private static final int threshold = 3;


    public Piano(int livello, String difficolta, String tema, ArrayList<Npc> npc){
        this.livello = livello;
        this.difficolta = difficolta;
        this.tema = tema;
        this.npc = npc;
        if(this.livello < 3){
            this.mat = new Stanza[this.livello*2+3][this.livello*2+3];
            inizializzaMatrice(this.livello*2+3);
        }else{
            this.mat = new Stanza[9][9];
            inizializzaMatrice(9);
        }
    }

    public ArrayList<Domanda> creaDomande(){
        ArrayList<Domanda> domande = new ArrayList<>();
        try {
            Questions q = new Questions(this.livello, this.tema, this.difficolta);
            for (int i = 0; i < q.domande.size(); i++) domande.add(i, new Domanda(q.domande.get(i)));
            this.n_dom = domande.size();
            return domande;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public ArrayList<Npc> creaNpcs(){
        switch (this.difficolta) {
            case "facile" -> this.npc.getFirst().model.rank = 1;
            case "media" -> this.npc.getFirst().model.rank = 2;
            case "difficile" -> this.npc.getFirst().model.rank = 3;
            case "crescente" -> this.npc.getFirst().model.rank = this.livello;
        }
        return this.npc;
    }

    private void inizializzaMatrice(int index){
        // stanza_inizio = -1, muro = 0, stanza_domanda = 1, stanza_npc = 2, stanza_vuota = 3
        Random rand = new Random();
        this.start = new int[2];
        this.start[0] = rand.nextInt(index);
        this.start[1] = rand.nextInt(index);
        this.mat[this.start[0]][this.start[1]] = new Start();
        Queue<int[]> adj = new LinkedList<>();
        adj.add(this.start);
        riempiMatrice(adj, rand, inizializzaStanze(3), index);
    }

    private ArrayList<? extends Stanza>[] inizializzaStanze(int special_room){
        ArrayList<? extends Stanza>[] stanze = new ArrayList[special_room];
        stanze[0] = creaDomande();
        stanze[1] = creaNpcs();
        ArrayList<Save> save = new ArrayList<>();
        if (this.livello <= 3) save.add(new Save());
        else{
            save.add(new Save());
            save.add(new Save());
        }
        stanze[2] = save;
        return stanze;
    }

    public void printStanze(ArrayList<? extends Stanza>[] stanze){
        for (int i = 0; i < stanze.length; i++) {
            for (Stanza st: stanze[i]){
                System.out.println(st.id);
            }
        }
    }
    private void riempiMatrice(Queue<int[]> adj, Random rand, ArrayList<? extends Stanza>[] stanze, int index) {
        boolean notzero;
        Queue<int[]> walls = new LinkedList<>();
        //printIntArray(adj.peek());
        while (!check_st(stanze)) {
            notzero = false;

            // sotto=(i+1, j) sopra=(i-1, j) destra=(i, j+1) sinistra(1, j-1)
            int[][] direzioni = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            int[] coord = adj.poll();

            for (int[] direzione : direzioni) {
                assert coord != null;
                int n_rig = coord[0] + direzione[0];
                int n_col = coord[1] + direzione[1];

                if (n_rig >= 0 && n_rig < index && n_col >= 0 && n_col < index && mat[n_rig][n_col] == null) {
                    int val = rand.nextInt(stanze.length+2);

                    if (val != 0) {
                        notzero = true;
                        if (val == 1) {
                            this.mat[n_rig][n_col] = new Vuota();
                        } else {
                            this.mat[n_rig][n_col] = route(val-2, stanze);
                        }
                        adj.add(new int[]{n_rig, n_col});
                    }else{
                        this.mat[n_rig][n_col] = new Wall();
                        walls.add(new int[]{n_rig, n_col});
                    }
                }
            }
            if (!notzero && adj.size()<threshold) {
                int val2 = rand.nextInt(stanze.length);
                int[] zero = walls.poll();
                assert zero != null;
                this.mat[zero[0]][zero[1]] = route(val2, stanze);
                adj.add(zero);
            }
        }
        adj.clear();
    }

    private boolean check_st (ArrayList < ? extends Stanza >[]stanze){
        for (ArrayList<? extends Stanza> stanzas : stanze) if (!stanzas.isEmpty()) return false;
        return true;
    }


    public Stanza route(int val, ArrayList<? extends Stanza>[] stanze){
        // controlla per gli indici prima di lui e dopo di lui se ne è qualcuno non vuoto
        Stanza out;
        if (!stanze[val].isEmpty()){
            out = stanze[val].getFirst();
            stanze[val].removeFirst();
            return out;
        }else{
            // check left-side
            for (int i = val-1; i >= 0; i--) {
                if (!stanze[i].isEmpty()){
                    out = stanze[i].getFirst();
                    stanze[i].removeFirst();
                    return out;
                }
            }
            for (int j = val+1; j < stanze.length; j++) {
                if (!stanze[j].isEmpty()){
                    out = stanze[j].getFirst();
                    stanze[j].removeFirst();
                    return out;
                }
            }
        }
        return null;
    }

    public boolean check(int[] cell, int index){
        if (cell[1] + 1 < index && this.mat[cell[0]][cell[1] + 1] == null) return false;
        if (cell[1] - 1 >= 0 && this.mat[cell[0]][cell[1] - 1] == null) return false;
        if (cell[0] - 1 >= 0 && this.mat[cell[0] - 1][cell[1]] == null) return false;
        return cell[0] + 1 >= index || this.mat[cell[0] + 1][cell[1]] != null;
    }

    public void stampaMatrice() {
        int c_dom = 0;
        int c_npc = 0;
        for (Stanza[] stanzas : this.mat) {
            for (Stanza stanza : stanzas) {
                if (stanza == null) System.out.print("-|");
                else {
                    System.out.print(stanza.id + "|");
                    if (stanza.id == 'N') c_npc++;
                    if (stanza.id == 'D') c_dom++;
                }
            }
            System.out.println();
        }
        System.out.println("Ci sono " + c_dom + " domande e " + c_npc + " npc");
    }
}
