package pac;

import java.io.Serializable;

import pac.stanze.*;

public class ProtagonistaModel implements Serializable {
    private String nome;
    private int x, y, last_d;
    private Piano piano;
    private Stanza[][] visited;
    private Aiutante aiutante;
    private int punteggio_totale,n_dom_risposte,n_errori_dom,n_mini_risolti,punti_dom,punti_mini;
    private static ProtagonistaModel instance = null;

    public void setNome(String nome){this.nome = nome;}
    public String getNome(){return this.nome;}
    public void setX(int x){this.x = x;}
    public int getX(){return this.x;}
    public void setY(int y){this.y = y;}
    public int getY() {return this.y;}
    public void setLast_d(int last_d){this.last_d = last_d;}
    public int getLast_d(){return this.last_d;}
    public void setPunteggio_totale(int punteggioTotale){this.punteggio_totale = punteggioTotale;}
    public int getPunteggio_totale(){return this.punteggio_totale;}
    public void setN_dom_risposte(int nDomRisposte){this.n_dom_risposte = nDomRisposte;}
    public int getN_dom_risposte(){return this.n_dom_risposte;}
    public void setN_errori_dom(int n_errori_dom){this.n_errori_dom = n_errori_dom;}
    public int getN_errori_dom(){return  this.n_errori_dom;}
    public void setN_mini_risolti(int nMiniRisolti){this.n_mini_risolti = nMiniRisolti;}
    public int getN_mini_risolti(){return this.n_mini_risolti;}
    public void setPunti_dom(int punti_dom){this.punti_dom = punti_dom;}
    public int getPunti_dom(){return this.punti_dom;}
    public void setPunti_mini(int punti_mini){this.punti_mini = punti_mini;}
    public int getPunti_mini(){return this.punti_mini; }
    public void setPiano(Piano piano){this.piano = piano;}
    public void attivaAiutante(String tema){this.aiutante.attivaAiuto(tema);}
    public void setAiutante(Aiutante aiutante){this.aiutante = aiutante;}
    public void setVisited(Stanza[][] visited){this.visited = visited;}
    public Stanza[][] getVisited(){return this.visited;}
    public Piano getPiano(){return this.piano;}
    public Aiutante getAiutante(){return this.aiutante;}

    private ProtagonistaModel(String nome, Piano piano, int[] pos, Stanza[][] visited, Aiutante aiutante, int[] dom, int[] mini, int tot){
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
    public static ProtagonistaModel getDefaultInstance(String nome){
        if(instance==null){
            instance = new ProtagonistaModel(nome, null, new int[]{0,0}, null, null, new int[]{0,0,0}, new int[]{0,0}, 0);
        }
        return instance;
    }

    public static ProtagonistaModel getSavedInstance(String nome, Piano piano, int[] pos, Stanza[][] visited, Aiutante aiutante, int[] dom, int[] mini, int tot){
        if(instance==null){
            instance = new ProtagonistaModel(nome, piano, pos, visited, aiutante, dom, mini, tot);
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
    }
}
