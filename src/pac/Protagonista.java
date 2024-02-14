package pac;

import java.io.Serializable;
import java.util.Scanner;

import pac.stanze.*;

public class Protagonista implements Serializable {
    public String nome;
    public int x, y, last_d;
    public Piano piano;
    public Stanza[][] visited;
    public Aiutante aiutante;
    public int punteggio_totale,n_dom_risposte,n_errori_dom,n_mini_risolti,punti_dom,punti_mini;
    public static final int[][] direzioni = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static Protagonista instance = null;

    private Protagonista(String nome, Piano piano, int[] pos, Stanza[][] visited, Aiutante aiutante, int[] dom, int[] mini, int tot){
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
        this.punteggio_totale = tot;
    }
    public static Protagonista getDefaultInstance(String nome){
        if(instance==null){
            instance = new Protagonista(nome, null, new int[]{0,0}, null, null, new int[]{0,0,0}, new int[]{0,0}, 0);
        }
        return instance;
    }

    public static Protagonista getSavedInstance(String nome, Piano piano, int[] pos, Stanza[][] visited, Aiutante aiutante, int[] dom, int[] mini, int tot){
        if(instance==null){
            instance = new Protagonista(nome, piano, pos, visited, aiutante, dom, mini, tot);
        }
        return instance;
    }

    public static void resetInstance(){
        instance = null;
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
        String in;
        vision();
        //System.out.println("\nRisolte: "+this.piano.dom_sup + " | Totali: " + this.piano.n_dom);
        System.out.println("\n"+ Grafica.sep+"In che direzione vuoi andare?");
        System.out.println("\n"+ Grafica.sep+ Grafica.bold +"d"+ Grafica.resetText+" per destra, "+ Grafica.bold + "a"+ Grafica.resetText+" per sinistra, "+ Grafica.bold+"w"+ Grafica.resetText+" per sopra, "+ Grafica.bold+"s"+ Grafica.resetText+" per sotto, "+ Grafica.bold+"r"+ Grafica.resetText+" per riepilogo: ");
        System.out.print("\n"+ Grafica.sep+">> ");
        in = scan.nextLine();
        switch (in) {
            case "d","D":
                if(legal_coord(this.x, this.y+1) && legal_cell(this.x, this.y+1)) this.y = this.y + 1;
                else System.out.println("\n"+ Grafica.sep+"Direzione non valida");
                break;
            case "a","A":
                if(legal_coord(this.x, this.y-1) && legal_cell(this.x, this.y-1)) this.y = this.y - 1;
                else System.out.println("\n"+ Grafica.sep+"Direzione non valida");
                break;
            case "w","W":
                if(legal_coord(this.x-1, this.y) && legal_cell(this.x-1, this.y)) this.x = this.x - 1;
                else System.out.println("\n"+ Grafica.sep+"Direzione non valida");
                break;
            case "s","S":
                if(legal_coord(this.x+1, this.y) && legal_cell(this.x+1, this.y)) this.x = this.x + 1;
                else System.out.println("\n"+ Grafica.sep+"Direzione non valida");
                break;
            case "r","R":
                riepilogo();
                System.out.println("\n"+ Grafica.sep+"Premi invio per riprendere il gioco");
                System.out.print(Grafica.sep+"+ ");
                scan.nextLine();
                break;
            case "h","H":
                help(scan);
                System.out.println("\n"+ Grafica.sep+"Premi invio per riprendere il gioco");
                System.out.print(Grafica.sep+"+ ");
                scan.nextLine();
                break;
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
            Grafica.clearConsole();
            System.out.println(Grafica.Dom);
            d.idle(scan,this.aiutante);

            if(d.risposta){
                int p = this.piano.livello * (10-d.prova.contaErrori);
                if(d.prova.contaErrori>2) p = 5*this.piano.livello;
                this.punteggio_totale += p;
                this.punti_dom += p;
                this.n_dom_risposte++;
                this.n_errori_dom += d.prova.contaErrori;
                System.out.println(Grafica.sep+"Hai ottenuto "+p+" punti!");
                this.piano.dom_sup++;
                System.out.println(Grafica.sep+"Premi invio per riprendere il gioco");
                System.out.print(Grafica.sep+"+ ");
                scan.nextLine();
            }
            return 'D';
        }
        else if (this.piano.mat[this.x][this.y].id == 'N' && !((Npc)this.piano.mat[this.x][this.y]).res){
            Npc n = (Npc)this.piano.mat[this.x][this.y];
            Grafica.clearConsole();

            n.idle(scan);
            if(n.res){
                this.punteggio_totale += n.mini.punti;
                this.n_mini_risolti++;
                this.punti_mini += n.mini.punti;
                System.out.println(Grafica.sep+"Hai ottenuto "+n.mini.punti+" punti!");
                System.out.println(Grafica.sep+"Premi invio per riprendere il gioco");
                System.out.print(Grafica.sep+"+ ");
                scan.nextLine();
            }
            return 'N';
        }
        else if (this.piano.mat[this.x][this.y].id == '+'){
            System.out.println(Grafica.sep+"Desideri salvare la partita? [s/n]");
            System.out.print("\n"+ Grafica.sep+">> ");
            if(scan.nextLine().equalsIgnoreCase("s")){
                return '+';
            }
        }
        return 'v';
    }
    public boolean nextFloor(Scanner scan){
        String in;
        if (this.piano.dom_sup == this.piano.n_dom && !last()){
            System.out.println("\n"+ Grafica.sep+"Hai finito le domande del piano, vuoi andare al succesivo? [s/n]");
            System.out.print("\n"+ Grafica.sep+">> ");
            in = scan.nextLine();
            if(in.equalsIgnoreCase("s")) return true;
            this.last_d++;
        }
        else if (this.piano.mat[this.x][this.y].id == 'S' && this.piano.dom_sup == this.piano.n_dom){
            System.out.println("\n"+ Grafica.sep+"Hai finito le domande del piano, vuoi andare al succesivo? [s/n]");
            System.out.print("\n"+ Grafica.sep+">> ");
            in = scan.nextLine();
            return in.equalsIgnoreCase("s");
        }
        return false;
    }
    public void riepilogo(){
        System.out.println("\n"+ Grafica.sep+"Menù riepilogo");
        System.out.println(Grafica.sep+"Nome: "+this.nome);
        System.out.println(Grafica.sep+"Punteggio: "+this.punteggio_totale);
        System.out.println(Grafica.sep+"Difficoltà: "+this.piano.difficolta);
        System.out.println(Grafica.sep+"Piano corrente: "+this.piano.livello);
        System.out.println(Grafica.sep+"Tema corrente: "+this.piano.tema);
        System.out.println(Grafica.sep+"Domande risposte correttamente: "+this.n_dom_risposte);
        System.out.println(Grafica.sep+"Errori commessi: "+this.n_errori_dom);
        System.out.println(Grafica.sep+"Punti ottenuti dalle domande: "+this.punti_dom);
        System.out.println(Grafica.sep+"Minigiochi risolti: "+this.n_mini_risolti);
        System.out.println(Grafica.sep+"Punti ottenuti dai minigiochi: "+this.punti_mini);
    }
    public boolean last(){
        return this.last_d > 0;
    }
    
    public void help(Scanner scan){
        System.out.println(Grafica.sep+"--- SEZIONE DI AIUTO ---");
        System.out.println(Grafica.sep+"Digita 1 per una spiegazione delle meccaniche di gioco");
        System.out.println(Grafica.sep+"Digita 2 per andare immediatamente al prossimo piano");
        System.out.println(Grafica.sep+">> ");
        switch(Integer.parseInt(scan.nextLine())){
            case 1: System.out.println("""
                    Lo scopo del gioco è completare diversi livelli (da 3 a 5)
                    Ogni livello contiene un certo numero di domande a cui rispondere
                    Alcune domande sono a risposta chiusa, e dovrai digitare solo la lettera della risposta che credi corretta
                    Altre domande sono a risposta aperta, e ti verrà suggerito il formato della risposta se è più di una parola o un numero
                    Rispondere "exit" ti fa tornare al movimento senza rispondere alla domanda, che ti verrà riproposta al rientro in quella stanza
                    IMPORTANTE: rispondere "help" a una domanda ti darà la risposta esatta da inserire (viene detto solo qui)
                    Dopo aver risposto a tutte le domande ti verrà chiesto se vuoi andare al piano successivo
                    Se rispondi no, puoi comunque avanzare tornando alla stanza di partenza ("S")
                    Ogni livello contiene anche un minigioco, sfide più complesse e danno più punti, ma totalmente opzionali
                    I punti dati da domande e minigiochi non sono necessari, esistono per pura competizione (alla fine del gioco viene stampata una classifica)
                    Durante il movimento muoverti digitando W|A|S|D, stampare un riepilogo delle statistiche con R o questa sezione di aiuto con H
                    Quando vuoi andare avanti, inserisci un carattere qualunque:""");
                    scan.nextLine(); break;
            case 2: this.piano.dom_sup = this.piano.n_dom; break;
        }
    }

    public void vision() {
        Grafica.clearConsole();

        System.out.println(Grafica.ASCII_lvl(this.piano.livello));
        int rows = this.visited.length;
        int cols = this.visited[0].length;

        // Prima riga

        System.out.println("\n");
        System.out.print(Grafica.sep+"\t╔═══");

        for (int i = 1; i < cols; i++) {
            System.out.print("╦═══");
        }

        System.out.println("╗");

        // Contenuto della mappa
        for (int j = 0; j < rows; j++) {
            System.out.print(Grafica.sep+"\t");
            for (int i = 0; i < cols; i++) {
                if (this.visited[j][i] != null) {
                    if (this.x == j && this.y == i)
                        System.out.print("║" + " " + Grafica.green + this.visited[j][i].id + " " + Grafica.resetText);
                    else
                        System.out.print("║" + " " + this.visited[j][i].id + " ");
                } else
                    System.out.print("║   ");
            }
            System.out.println("║");
            if (j < rows - 1) {
                System.out.print(Grafica.sep+"\t╠═══");
                for (int i = 0; i < cols - 1; i++) {
                    System.out.print("╬═══");
                }
                System.out.println("╣");
            }
        }

        // Ultima riga
        System.out.print(Grafica.sep+"\t╚═══");
        for (int i = 0; i < cols - 1; i++) {
            System.out.print("╩═══");
        }
        System.out.println("╝");
    }
}
