package pac.stanze;

import java.util.Scanner;

import pac.minigiochi.MinigiocoController;
import pac.minigiochi.MinigiocoModel;
import pac.minigiochi.MinigiocoView;

public  class Npc extends Stanza{
    public String nome;
    public MinigiocoModel model;
    public MinigiocoController controller;
    public MinigiocoView view;
    public boolean res;

    public Npc(String nome, MinigiocoModel model, MinigiocoView view, MinigiocoController controller){
        super('N');
        this.nome = nome;
        this.model = model;
        this.controller = controller;
        this.view = view;
        this.res = false;
    }

    public void idle(Scanner scan){
        this.model.inizializza();

        if(this.controller.play(scan, nome))
            this.res = true;
    }
}