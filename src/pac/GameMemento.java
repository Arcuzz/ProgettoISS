package pac;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GameMemento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private LocalDateTime date;
    private Difficulty diff;
    private ArrayList<String> temi;
    private Piano piano;
    private String nome;
    private int[] pos;
    private Stanza[][] visited;
    private Aiutante aiutante;
    private int[] dom;
    private int[] mini;

    public GameMemento(Difficulty diff, ArrayList<String> temi, Piano piano, String nome, int[] pos, Stanza[][] visited, Aiutante aiutante, int[] dom, int[] mini){
        this.diff = diff;
        this.temi = temi;
        this.piano = piano;
        this.nome = nome;
        this.pos = pos;
        this.visited = visited;
        this.aiutante = aiutante;
        this.dom = dom;
        this.mini = mini;
        this.date = LocalDateTime.now();
    }

    public Difficulty getDiff() {
        return diff;
    }

    public ArrayList<String> getTemi() {
        return temi;
    }

    public Piano getPiano() {
        return piano;
    }

    public String getNome() {
        return nome;
    }

    public int[] getPos() {
        return pos;
    }

    public Stanza[][] getVisited() {
        return visited;
    }

    public Aiutante getAiutante() {
        return aiutante;
    }

    public int[] getDom() {
        return dom;
    }

    public int[] getMini() {
        return mini;
    }

    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(this.date);
    }
}
