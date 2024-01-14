package pac;

import java.util.Scanner;

public class Protagonista {
    public String nome;
    public int livello = 1;
    public int x, y, last_d;
    public Piano piano;
    public Stanza[][] visited;
    public Aiutante aiutante;
    public int punteggio_totale=0,n_dom_risposte=0,n_errori_dom=0,n_mini_risolti=0,punti_dom=0,punti_mini=0;
    public static final int[][] direzioni = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static Protagonista instance = null;

    private Protagonista(String nome){
        this.nome = nome;
    }
    public static Protagonista getInstance(String nome){
        if(instance==null){
            instance = new Protagonista(nome);
        }
        return instance;
    }
    public void start(Piano piano){
        this.last_d = 0;
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
        return (x >= 0 && x < this.piano.mat.length && y >= 0 && y < this.piano.mat.length);
    }

    public void move(Scanner scan){
        String in = "";
        while (!in.equals("exit")) {
            System.out.println("\nRisolte: "+this.piano.dom_sup + " | Totali: " + this.piano.n_dom);
            vision();
            System.out.println("In che direzione vuoi andare?");
            System.out.println("d per destra, a per sinistra, w per sopra, s per sotto, r per riepilogo: ");
            in = scan.nextLine();
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
                case "r","R": riepilogo(); break;
                default: continue;
            }
            checkRoom(scan);
            if(nextFloor(scan)) break;
            aggiungi_adj();
        }
    }
    public void checkRoom(Scanner scan){
        if (this.piano.mat[this.x][this.y].id == 'D' && !((Domanda)this.piano.mat[this.x][this.y]).risposta){
            Domanda d = (Domanda)this.piano.mat[this.x][this.y];
            d.idle(scan,this.aiutante);
            if(d.risposta){
                int p = this.piano.livello * (10-d.prova.contaErrori);
                if(d.prova.contaErrori>2) p = 5*this.piano.livello;
                this.punteggio_totale += p;
                this.punti_dom += p;
                this.n_dom_risposte++;
                this.n_errori_dom += d.prova.contaErrori;
                System.out.println("Hai ottenuto "+p+" punti!");
                this.piano.dom_sup++;
            }
        }
        else if (this.piano.mat[this.x][this.y].id == 'N' && !((Npc)this.piano.mat[this.x][this.y]).res){
            Npc n = (Npc)this.piano.mat[this.x][this.y];
            n.idle(scan);
            if(n.res){
                this.punteggio_totale += n.mini.punti;
                this.n_mini_risolti++;
                this.punti_mini += n.mini.punti;
                System.out.println("Hai ottenuto "+n.mini.punti+" punti!");
            }
        }
    }
    public boolean nextFloor(Scanner scan){
        String in;
        if (this.piano.dom_sup == this.piano.n_dom && !last()){
            System.out.println("Hai finito le domande del piano, vuoi andare al succesivo? [s/n]");
            in = scan.nextLine();
            if(in.equalsIgnoreCase("s")) return true;
            this.last_d++;
        }
        else if (this.piano.mat[this.x][this.y].id == 'S' && this.piano.dom_sup == this.piano.n_dom){
            System.out.println("Hai finito le domande del piano, vuoi andare al succesivo? [s/n]");
            in = scan.nextLine();
            if(in.equalsIgnoreCase("s")) return true;
        }
        return false;
    }
    public void riepilogo(){
        System.out.println("\nMenù riepilogo");
        System.out.println("Nome: "+this.nome);
        System.out.println("Punteggio: "+this.punteggio_totale);
        System.out.println("Difficoltà: "+this.piano.difficolta);
        System.out.println("Piano corrente: "+this.piano.livello);
        System.out.println("Tema corrente: "+this.piano.tema);
        System.out.println("Domande risposte correttamente: "+this.n_dom_risposte);
        System.out.println("Errori commessi: "+this.n_errori_dom);
        System.out.println("Punti ottenuti dalle domande: "+this.punti_dom);
        System.out.println("Minigiochi risolti: "+this.n_mini_risolti);
        System.out.println("Punti ottenuti dai minigiochi: "+this.punti_mini);
    }
    public boolean last(){
        return this.last_d > 0;
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
