package pac;

import java.io.Serializable;
import java.util.Scanner;

public class Protagonista implements Serializable {
    public String nome;
    public int x, y, last_d;
    public Piano piano;
    public Stanza[][] visited;
    public Aiutante aiutante;
    public int punteggio_totale,n_dom_risposte,n_errori_dom,n_mini_risolti,punti_dom,punti_mini;
    public static final int[][] direzioni = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static Protagonista instance = null;

    private Protagonista(String nome, Piano piano, int[] pos, Stanza[][] visited, Aiutante aiutante, int[] dom, int[] mini){
        this.nome = nome;
        this.piano = piano;
        this.x = pos[0];
        this.y = pos[1];
        this.visited = visited;
        this.aiutante = aiutante;
        this.n_dom_risposte = dom[0];
        this.n_errori_dom = dom[1];
        this.punti_dom = dom[2];
        this.n_mini_risolti = mini[0];
        this.punti_mini = mini[1];
        this.punteggio_totale = this.punti_dom + this.punti_mini;
    }
    public static Protagonista getDefaultInstance(String nome){
        if(instance==null){
            instance = new Protagonista(nome, null, new int[]{0,0}, null, null, new int[]{0,0,0}, new int[]{0,0});
        }
        return instance;
    }

    public static Protagonista getSavedInstance(String nome, Piano piano, int[] pos, Stanza[][] visited, Aiutante aiutante, int[] dom, int[] mini){
        if(instance==null){
            instance = new Protagonista(nome, piano, pos, visited, aiutante, dom, mini);
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

    public boolean legal_cell(int x, int y){
        return (this.piano.mat[x][y] != null && this.piano.mat[x][y].id != '#');
    }

    public int move(Scanner scan){
        String in = "";
        System.out.println("\nRisolte: "+this.piano.dom_sup + " | Totali: " + this.piano.n_dom);
        vision();
        System.out.println("In che direzione vuoi andare?");
        System.out.println("d per destra, a per sinistra, w per sopra, s per sotto, r per riepilogo: ");
        in = scan.nextLine();
        switch (in) {
            case "d","D":
                if(legal_coord(this.x, this.y+1) && legal_cell(this.x, this.y+1)) this.y = this.y + 1;
                else System.out.println("Direzione non valida");
                break;
            case "a","A":
                if(legal_coord(this.x, this.y-1) && legal_cell(this.x, this.y-1)) this.y = this.y - 1;
                else System.out.println("Direzione non valida");
                break;
            case "w","W":
                if(legal_coord(this.x-1, this.y) && legal_cell(this.x-1, this.y)) this.x = this.x - 1;
                else System.out.println("Direzione non valida");
                break;
            case "s","S":
                if(legal_coord(this.x+1, this.y) && legal_cell(this.x+1, this.y)) this.x = this.x + 1;
                else System.out.println("Direzione non valida");
                break;
            case "r","R": riepilogo(); break;
            case "exit": return 3;
        }
        if(checkRoom(scan) == '+') return 2;
        if(nextFloor(scan)) return 1; // si continua al prossimo piano
        aggiungi_adj();
        return 0;
    }

    public char checkRoom(Scanner scan){
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
            return 'D';
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
            return 'N';
        }
        else if (this.piano.mat[this.x][this.y].id == '+'){
            System.out.println("Desideri salvare la partita? [s/n]");
            if(scan.nextLine().equalsIgnoreCase("s")){
                return '+';
            }
        }
        return 'v';
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
            return in.equalsIgnoreCase("s");
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
