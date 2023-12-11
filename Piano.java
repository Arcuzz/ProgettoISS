package pac;

import pac.Domanda;
import pac.Stanza;
import pac.Npc;
import pac.Start;
import pac.Vuota;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class Piano{
    public String difficoltà;
    public int livello;
    public Stanza[][] mat;
    
    public Piano(int livello, String difficoltà){
        this.livello = livello;
        this.difficoltà = difficoltà;

        switch (livello) {
            case 1:
            this.mat = new Stanza[5][5];
                switch (difficoltà) {
                    case "facile":
                        inizializzaMatrice(5, creaDomande(3), creaNpcs(3));
                        break;
                    case "media":
                        inizializzaMatrice(5, creaDomande(3), creaNpcs(2));
                        break;
                    case "difficile":
                        inizializzaMatrice(5, creaDomande(3), creaNpcs(1));
                        break;
                    default:
                        break;
                }
                break;

            case 2:
                this.mat = new Stanza[7][7];
                switch (difficoltà) {
                    case "facile":
                        inizializzaMatrice(7, creaDomande(5), creaNpcs(5));
                        break;
                    case "media":
                        inizializzaMatrice(7, creaDomande(5), creaNpcs(3));
                        break;
                    case "difficile":
                        inizializzaMatrice(7, creaDomande(5), creaNpcs(1));
                        break;
                    default:
                        break;
                }
                break;

            case 3:
                this.mat = new Stanza[9][9];
                switch (difficoltà) {
                    case "facile":
                        inizializzaMatrice(9, creaDomande(8), creaNpcs(8));
                        break;
                    case "media":
                        inizializzaMatrice(9, creaDomande(8), creaNpcs(5));
                        break;
                    case "difficile":
                        inizializzaMatrice(9, creaDomande(8), creaNpcs(3));
                        break;
                    default:
                        break;
                }
                break;

            case 4:
                this.mat = new Stanza[9][9];
                switch (difficoltà) {
                    case "media":
                        inizializzaMatrice(9, creaDomande(11), creaNpcs(7));
                        break;
                    case "difficile":
                        inizializzaMatrice(9, creaDomande(11), creaNpcs(5));
                        break;
                    default:
                        break;
                }
                break;

            case 5:
                this.mat = new Stanza[9][9];
                inizializzaMatrice(9, creaDomande(13), creaNpcs(5));
                break;
            default:
                break;
        }
    }

    public ArrayList<Domanda> creaDomande(int num){
        ArrayList<Domanda> domande = new ArrayList<>();
        for (int i = 0; i < num; i++) domande.add(i, new Domanda(i, i));
        return domande;
    }

    public ArrayList<Npc> creaNpcs(int num){
        ArrayList<Npc> npc = new ArrayList<>();
        for (int i = 0; i < num; i++) npc.add(i, new Npc("Aldo")); 
        return npc;
    }

    private void inizializzaMatrice(int index, ArrayList<Domanda> dom, ArrayList<Npc> npc){
        // stanza_inizio = -1, muro = 0, stanza_domanda = 1, stanza_npc = 2, stanza_vuota = 3
        Random rand = new Random();
        int x = rand.nextInt(index);
        int y = rand.nextInt(index);
        this.mat[x][y] = new Start();
        riempiMatrice(x, y, rand, dom, npc, index);
    }

    private void riempiMatrice(int i, int j, Random rand, ArrayList<Domanda> dom, ArrayList<Npc> npc, int index){
        if(dom.size() == 0 && npc.size() == 0) return;
        boolean notzero = false;
        int x = 0;
        int y = 0;

        // sotto=(i+1, j) sopra=(i-1, j) destra=(i, j+1) sinistra(1, j-1)
        int[][] direzioni = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for(int[] direzione: direzioni){
            int n_rig = i + direzione[0];
            int n_col = j + direzione[1];

            if(n_rig >= 0 && n_rig < index && n_col >=0 && n_col < index && mat[n_rig][n_col] == null){
                int val = rand.nextInt(4);
                x = n_rig;
                y = n_col;

                if(val != 0){
                    notzero = true;
                    if(val == 3){
                        this.mat[n_rig][n_col] = new Vuota();
                        riempiMatrice(n_rig, n_col, rand, dom, npc, index);
                    }else{
                        route(n_rig, n_col, val, dom, npc, rand);
                        riempiMatrice(n_rig, n_col, rand, dom, npc, index);
                    }
                }
            }
        }

        if (!notzero && check(x, y, index)) {
            int val2 = rand.nextInt(3)+1;
            route(x, y, val2, dom, npc, rand);
        }
    }

    public void route(int x, int y, int val, ArrayList<Domanda> dom, ArrayList<Npc> npc, Random rand){
        if (val == 1) {
            if(dom.size() > 0){
                int ind = rand.nextInt(dom.size());
                this.mat[x][y] = dom.get(ind);
                dom.remove(ind);
            }else if(dom.size() == 0 && npc.size() > 0){
                int ind = rand.nextInt(npc.size());
                this.mat[x][y] = npc.get(ind);
                npc.remove(ind);
            }
        }else{
            if(npc.size() > 0){
                int ind = rand.nextInt(npc.size());
                this.mat[x][y] = npc.get(ind);
                npc.remove(ind);
            }else if(npc.size() == 0 && dom.size() > 0){
                int ind = rand.nextInt(dom.size());
                this.mat[x][y] = dom.get(ind);
                dom.remove(ind);
            }
        }
    }

    public boolean check(int riga, int colonna, int index){
        if (colonna + 1 < index && this.mat[riga][colonna + 1] == null) return false;
        if (colonna - 1 >= 0 && this.mat[riga][colonna - 1] == null) return false;
        if (riga - 1 >= 0 && this.mat[riga - 1][colonna] == null) return false;
        if (riga + 1 < index && this.mat[riga + 1][colonna] == null) return false;
        return true;
    }

    public void stampaMatrice() {
        int c_dom = 0;
        int c_npc = 0;
        for(int i = 0; i < this.mat.length; i++){
            for(int j = 0; j < this.mat[i].length; j++){
                if(this.mat[i][j] == null) System.out.print("-|");
                else{
                    System.out.print(this.mat[i][j].id + "|");
                    if(this.mat[i][j].id == 'N') c_npc++;
                    if(this.mat[i][j].id == 'D') c_dom++;
                }
            }
            System.out.println();
        }
        System.out.println("Ci sono " + c_dom + " domande e " + c_npc + " npc");
    }
}

