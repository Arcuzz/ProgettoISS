package pac.stanze;

import java.util.Scanner;

import pac.minigiochi.Minigiochi;

public  class Npc extends Stanza{
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
        this.mini.inizializza();
        this.mini.startGame();
        if(this.mini.play(scan))
            this.res = true;
    }
}