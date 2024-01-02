package pac;

import java.util.Scanner;

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

    public void idle(Scanner scan){
        if(this.prova.faiDomanda(scan))
            this.risposta = true;
    }
}

class Npc extends Stanza{
    public String nome;
    public Minigiochi mini;
    public boolean res;

    public Npc(String nome, Minigiochi mini){
        super('N');
        this.nome = nome;
        this.mini = mini;
        this.res = false;
    }

    public void idle(Scanner scan){
        System.out.println("Sono "+this.nome+" risolvi il mio minigioco");
        while(!this.res){
            this.mini.inizializza();
            this.mini.startGame();
            this.mini.play(scan);
            this.res = true;
        }
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
