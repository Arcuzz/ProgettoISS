import java.util.Random;
import java.util.HashMap;

public class Piano{
    private String difficoltà;
    private int livello;
    private int[][] mat;
    
    public Piano(int livello, String difficoltà){
        this.livello = livello;
        this.difficoltà = difficoltà;

        switch (livello) {
            case 1:
            this.mat = new int[5][5];
                switch (difficoltà) {
                    case "facile":
                        inizializzaMatrice(5, 3, 3);
                        break;
                    case "media":
                        inizializzaMatrice(5, 3, 2);
                        break;
                    case "difficile":
                        inizializzaMatrice(5, 3, 1);
                        break;
                    default:
                        break;
                }
                break;

            case 2:
                this.mat = new int[7][7];
                switch (difficoltà) {
                    case "facile":
                        inizializzaMatrice(7, 5, 5);
                        break;
                    case "media":
                        inizializzaMatrice(7, 5, 3);
                        break;
                    case "difficile":
                        inizializzaMatrice(7, 5, 1);
                        break;
                    default:
                        break;
                }
                break;

            case 3:
                this.mat = new int[9][9];
                switch (difficoltà) {
                    case "facile":
                        inizializzaMatrice(9, 8, 8);
                        break;
                    case "media":
                        inizializzaMatrice(9, 8, 5);
                        break;
                    case "difficile":
                        inizializzaMatrice(9, 8, 3);
                        break;
                    default:
                        break;
                }
                break;

            case 4:
                this.mat = new int[9][9];
                switch (difficoltà) {
                    case "facile":
                        inizializzaMatrice(9, 11, 11);
                        break;
                    case "media":
                        inizializzaMatrice(9, 11, 7);
                        break;
                    case "difficile":
                        inizializzaMatrice(9, 11, 5);
                        break;
                    default:
                        break;
                }
                break;

            case 5:
                this.mat = new int[9][9];
                inizializzaMatrice(9, 13, 5);
        
            default:
                break;
        }
    }

    private void inizializzaMatrice(int index, int domande, int npc){
        // stanza_inizio = -1, muro = 0, stanza_domanda = 1, stanza_npc = 2, stanza_vuota = 3
        Random rand = new Random();
        int x = rand.nextInt(index);
        int y = rand.nextInt(index);
        this.mat[x][y] = -1;
        HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();
        counter.put(1, domande);
        counter.put(2, npc);
        counter.put(3, -1);
        riempiMatrice(x, y, rand, counter, index);
    }

    private void riempiMatrice(int i, int j, Random rand, HashMap<Integer, Integer> counter, int index){
        if(counter.get(1) == 0 && counter.get(2) == 0) return;

        // sotto=(i+1, j) sopra=(i-1, j) destra=(i, j+1) sinistra(1, j-1)
        int[][] direzioni = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for(int[] direzione: direzioni){
            int n_rig = i + direzione[0];
            int n_col = j + direzione[1];

            if(n_rig >= 0 && n_rig < index && n_col >=0 && n_col < index && mat[n_rig][n_col] == 0){
                int val = rand.nextInt(4);

                if(val != 0){
                    if(val == 3){
                        this.mat[n_rig][n_col] = val;
                        riempiMatrice(n_rig, n_col, rand, counter, index);
                    }
                    else if (val == 1 || val == 2) {
                        int altroVal = (val == 1) ? 2 : 1;
                        if (counter.get(val) > 0) {
                            this.mat[n_rig][n_col] = val;
                            counter.put(val, counter.get(val) - 1);
                            riempiMatrice(n_rig, n_col, rand, counter, index);
                        } else if (counter.get(val) == 0 && counter.get(altroVal) > 0) {
                            this.mat[n_rig][n_col] = altroVal;
                            counter.put(altroVal, counter.get(altroVal) - 1);
                            riempiMatrice(n_rig, n_col, rand, counter, index);
                        }
                    }
                }
            }
        }
    }

    public void stampaMatrice() {
        int c_dom = 0;
        int c_npc = 0;
        for (int[] riga : mat) {
            for (int cella : riga) {
                System.out.print(cella + " ");
                if(cella==1) c_dom+=1;
                if(cella==2) c_npc+=1;
            }
            System.out.println();
        }
        System.out.println("Ci sono " + c_dom + " domande e " + c_npc + " npc");
    }
    
    
    public static void main(String[] args){
        Piano pi = new Piano(4, "media");
        pi.stampaMatrice();
    }
}
