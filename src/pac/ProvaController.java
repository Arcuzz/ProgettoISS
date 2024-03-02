package pac;

import java.io.Serializable;
import java.util.Scanner;

public class ProvaController implements Serializable {

    private ProvaModel model;
    private final ProvaView view;


    public ProvaController(ProvaModel model, ProvaView view){
        this.model = model;
        this.view = view;
    }

    public boolean faiDomanda(Scanner scan){
        String answer = "";

        while(!answer.equalsIgnoreCase(model.getRisposta())){

            view.mostraDomanda(model.getDomanda());
            if(model.getContaErrori()>2) daiIndizio();

            view.promptRisposta();
            answer = scan.nextLine();

            if(answer.equalsIgnoreCase(model.getRisposta())){
                view.rispostaEsatta(model.getContaErrori());
                return true;
            }
            else if(answer.equalsIgnoreCase("exit")){
                view.uscitaProva();
                break;
            }
            else if(answer.equalsIgnoreCase("help")){
                view.mostraHelp(model.getRisposta());
                continue;
            }
            model.setContaErrori(model.getContaErrori()+1);
            view.rispostaSbagliata();
        }
        return false;
    }

    public void daiIndizio(){
        if(model.getAiutante().aiuto){
            view.daiIndizio(model.getIndizioPrincipale());
        }
    }
}
