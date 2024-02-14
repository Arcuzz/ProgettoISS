package pac.stanze;

import java.util.Scanner;

import pac.Grafica;
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
        System.out.println(Grafica.Minigame);
        System.out.println("\n"+Grafica.sep+ "Sono "+this.nome+" risolvi il mio minigioco");
        System.out.println(Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
        scan.nextLine();

        this.mini.inizializza();
        this.mini.startGame();
        if(this.mini.play(scan))
            this.res = true;
    }
}