package pac.stanze;

import java.util.Scanner;

import pac.Aiutante;
import pac.Prova;

public class Domanda extends Stanza{
    public Prova prova;
    public boolean risposta;

    public Domanda(Prova prova){
        super('D');
        this.prova = prova;
        this.risposta = false;
    }

    public void idle(Scanner scan, Aiutante aiutante){
        this.prova.aiutante = aiutante;
        if(this.prova.faiDomanda(scan))
            this.risposta = true;
    }
}
