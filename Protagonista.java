package pac;

import java.util.Scanner;

import pac.Piano;
import pac.Stanza;

public class Protagonista {
    public String nome;
    public int livello;
    public int x;
    public int y;
    public Piano piano;
    public Stanza visited[][];

    public static final int[][] direzioni = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public Protagonista(String nome){
        this.nome = nome;
        this.livello = 0;
    }

    public void creaPiano(String difficoltà, int livello){
        this.piano = new Piano(livello, difficoltà);
        this.x = posizioneIniziale()[0];
        this.y = posizioneIniziale()[1];
        this.visited = new Stanza[this.piano.mat[0].length][this.piano.mat.length];
        this.visited[this.x][this.y] = this.piano.mat[this.x][this.y];
        aggiungi_adj();
    }

    public int[] posizioneIniziale(){
        int[] pos = new int[2];
        for(int i = 0; i < this.piano.mat.length; i++){
            for(int j = 0; j < this.piano.mat[i].length; j++){
                if(this.piano.mat[i][j] != null && this.piano.mat[i][j].id == 'S'){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }  
            }
        }
        return null;
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

    public void move(){
        String in = "";
        Scanner sca = new Scanner(System.in);

        while (!in.equals("exit")) {
            vision();
            System.out.println("In che direzione vuoi andare?");
            System.out.println("d per destra, a per sinitra, w per sopra, s per sotto: ");
            if(sca.hasNextLine()) in = sca.nextLine();
            switch (in) {
                case "d":
                    if(legal_coord(this.x, this.y+1) && this.piano.mat[this.x][this.y+1] != null) this.y = this.y + 1;
                    else System.out.println("Direzione non valida");
                    break;
                case "a":
                    if(legal_coord(this.x, this.y-1) && this.piano.mat[this.x][this.y-1] != null) this.y = this.y - 1;
                    else System.out.println("Direzione non valida");
                    break;
                case "w":
                    if(legal_coord(this.x-1, this.y) &&this.piano.mat[this.x-1][this.y] != null) this.x = this.x - 1;
                    else System.out.println("Direzione non valida");
                    break;
                case "s":
                    if(legal_coord(this.x+1, this.y) && this.piano.mat[this.x+1][this.y] != null) this.x = this.x + 1;
                    else System.out.println("Direzione non valida, riprova");
                    break;
                default:
                    break;
            }
            aggiungi_adj();
        }
        sca.close();
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
