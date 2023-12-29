package pac;

public class Stanza {
    public char id;

    public Stanza(char id){
        this.id = id;
    }
}

class Domanda extends Stanza{
    public Prova prova;
    public boolean risposta;

    public Domanda(Prova prova){
        super('D');
        this.prova = prova;
        this.risposta = false;
    }
}

class Npc extends Stanza{
    // public Personaggio npc;
    public String nome;
    public Minigiochi mini;

    public Npc(String nome, Minigiochi mini){
        super('N');
        this.nome = nome;
        this.mini = mini;
    }
}

class Vuota extends Stanza{

    public Vuota(){
        super('V');
    }
}

class Start extends Stanza{

    public Start(){
        super('S');
    }
}
