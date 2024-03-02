package pac.stanze;

import java.util.Scanner;

import pac.Aiutante;
import pac.ProvaController;
import pac.ProvaModel;
import pac.ProvaView;

public class Domanda extends Stanza{
    public ProvaModel prova;
    public boolean risposta;


    public Domanda(ProvaModel prova){
        super('D');
        this.prova = prova;
        this.risposta = false;
    }

    public void idle(Scanner scan, Aiutante aiutante){
        this.prova.setAiutante(aiutante);
        if(new ProvaController(this.prova, new ProvaView()).faiDomanda(scan))
            this.risposta = true;
    }
}
