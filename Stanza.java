package pac;

public class Stanza {
    public char id;

    public Stanza(char id){
        this.id = id;
    }
}

class Domanda extends Stanza{
    public int indice_domanda;
    public int indice_risposta;

    public Domanda(int indice, int risposta){
        super('D');
        this.indice_domanda = indice;
        this.indice_risposta = risposta;
    }
}

class Npc extends Stanza{
    // public Personaggio npc;
    public String nome;

    public Npc(String nome){
        super('N');
        this.nome = nome;

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