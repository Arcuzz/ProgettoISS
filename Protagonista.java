package pac;

import java.util.Scanner;

public class Protagonista {
    public String nome;
    public int livello = 1;
    public int x;
    public int y;
    public Piano piano;
    public Stanza visited[][];
    public int punteggio = 0;
    public int last_d = 0;

    public static final int[][] direzioni = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static Protagonista instance = null;

    private Protagonista(String nome) {
        this.nome = nome;
    }
    public static Protagonista getIstance(String nome){
        if (instance == null) {
            instance = new Protagonista(nome);
        }
        return instance;
    }

    public void start(Piano piano){
        this.piano = piano;
        this.x = this.piano.start[0];
        this.y = this.piano.start[1];
        this.visited = new Stanza[this.piano.mat[0].length][this.piano.mat.length];
        this.visited[this.x][this.y] = this.piano.mat[this.x][this.y];
        aggiungi_adj();
    }

    public void aggiungi_adj(){
        for(int[] direzione: direzioni){ 
            int n_rig = this.x + direzione[0];
            int n_col = this.y + direzione[1];
            if(legal_coord(n_rig, n_col) && this.piano.mat[n_rig][n_col] != null && this.visited[n_rig][n_col] == null){
                this.visited[n_rig][n_col] = this.piano.mat[n_rig][n_col];
            }
        }
    }

    public boolean legal_coord(int x, int y){
        if(x >= 0 && x < this.piano.mat.length && y >=0 && y < this.piano.mat.length) return true;
        else return false;
    }

    public void move(Scanner scan){
        String in = "";

        while (!in.equals("exit")) {
            vision();
            System.out.println("In che direzione vuoi andare?");
            System.out.println("d per destra, a per sinistra, w per sopra, s per sotto: ");
            if(scan.hasNextLine()) in = scan.nextLine();
            switch (in) {
                case "d","D":
                    if(legal_coord(this.x, this.y+1) && this.piano.mat[this.x][this.y+1] != null) this.y = this.y + 1;
                    else System.out.println("Direzione non valida");
                    break;
                case "a","A":
                    if(legal_coord(this.x, this.y-1) && this.piano.mat[this.x][this.y-1] != null) this.y = this.y - 1;
                    else System.out.println("Direzione non valida");
                    break;
                case "w","W":
                    if(legal_coord(this.x-1, this.y) && this.piano.mat[this.x-1][this.y] != null) this.x = this.x - 1;
                    else System.out.println("Direzione non valida");
                    break;
                case "s","S":
                    if(legal_coord(this.x+1, this.y) && this.piano.mat[this.x+1][this.y] != null) this.x = this.x + 1;
                    else System.out.println("Direzione non valida");
                    break;
                default:
                    break;
            }
            if (this.piano.mat[this.x][this.y].id == 'D' && !((Domanda)this.piano.mat[this.x][this.y]).risposta){
                Domanda d = (Domanda)this.piano.mat[this.x][this.y];
                d.idle(scan);
                if(d.risposta){
                    int p = this.piano.livello * (10-d.prova.contaErrori);
                    if(d.prova.contaErrori>2) p = 5*this.piano.livello;
                    this.punteggio += p;
                    System.out.println("Hai ottenuto "+p+" punti!");
                    this.piano.dom_sup++;
                }
            }
            else if (this.piano.mat[this.x][this.y].id == 'N' && !((Npc)this.piano.mat[this.x][this.y]).res){
                Npc n = (Npc)this.piano.mat[this.x][this.y];
                n.idle(scan);
                if(n.res){
                    this.punteggio += n.mini.punti;
                    System.out.println("Hai ottenuto "+n.mini.punti+" punti!");
                }
            }
            System.out.println(this.piano.dom_sup + " " + this.piano.n_dom);
            if (this.piano.dom_sup == this.piano.n_dom && !last()){
                System.out.println("Hai finito le domande del piano, vuoi andare al succesivo? [S/n]");
                in = scan.nextLine();
                if(in.equalsIgnoreCase("s")) break;
                this.last_d++;
            }
            else if (this.piano.mat[this.x][this.y].id == 'S' && this.piano.dom_sup == this.piano.n_dom){
                System.out.println("Hai finito le domande del piano, vuoi andare al succesivo? [S/n]");
                in = scan.nextLine();
                if(in.equalsIgnoreCase("s")) break;                
            }
            aggiungi_adj();
        }
    }

    public boolean last(){
        if (this.last_d > 0) return true;
        else return false;
    }

    public void vision(){
        for(int i = 0; i < this.visited.length; i++){
            for(int j = 0; j < this.visited[i].length; j++){
                if(this.visited[i][j] == null) System.out.print("-|");
                else if(i == this.x && j == this.y) System.out.print(this.visited[i][j].id + "*|");
                else System.out.print(this.visited[i][j].id + "|");
            }System.out.println();
        }
    }
}
