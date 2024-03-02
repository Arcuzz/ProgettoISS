package pac.minigiochi;

import java.io.Serializable;
import java.util.Scanner;


public class VersaLiquidoController extends MinigiocoController implements Serializable {

    private VersaLiquidoModel model;
    private VersaLiquidoView view;

    public VersaLiquidoController(VersaLiquidoModel model, VersaLiquidoView view){
        this.view = view;
        this.model = model;
    }

    @Override
    public boolean play(Scanner sca, String nome){
        view.initGraphic(nome);
        sca.nextLine();

        String scelta, dest;

        while(!(model.getBicchieri(0).livello == model.getObiettivo() && model.getBicchieri(0).livello == model.getBicchieri(1).livello)){
            view.startGame(model.getBicchieri(0).CAPACITA, model.getBicchieri(1).CAPACITA, model.getBicchieri(2).CAPACITA, model.getObiettivo(), model.getBicchieri(0).CAPACITA-1);
            view.stampaBicchieri(model.getBicchieri(0).livello, model.getBicchieri(1).livello, model.getBicchieri(2).livello, model.getBicchieri(0).CAPACITA, model.getBicchieri(1).CAPACITA, model.getBicchieri(2).CAPACITA, model.getObiettivo(), model.getBicchieri(0).CAPACITA-1, model.getMosse());
            view.prompt();
            scelta = sca.nextLine();

            while(!scelta.matches("[1-5]")){
                view.invalidInput();
                scelta = sca.nextLine();
            }

            int sceltaPos = Integer.parseInt(scelta)-1;
            if(scelta.equals("4")){
                model.reset();
                continue;
            }

            if(scelta.equals("5")){
                view.exit();
                sca.nextLine();
                model.reset();
                return false;
            }

            if(model.getBicchieri(sceltaPos).livello == 0){
                view.empty();
                sca.nextLine();
                continue;
            }

            view.choose();
            dest = sca.nextLine();

            while(!dest.matches("[1-3]")){
                view.invalidInput();
                dest = sca.nextLine();
            }

            int destPos = Integer.parseInt(dest)-1;
            if(model.getBicchieri(destPos).livello == model.getBicchieri(destPos).CAPACITA){
                view.full();
                sca.nextLine();
                continue;
            }

            if(!scelta.equals(dest)){
                model.travaso(sceltaPos, destPos);
            }
            
            else{
                view.bicchiereDiPartenza();
                sca.nextLine();
            }
        }

        if(model.getMosse() == model.getBicchieri(0).CAPACITA-1){
            model.updateScore();
            view.perfectVictory();
            sca.nextLine();
            return true;
        }
        else if(model.getMosse() > model.getBicchieri(0).CAPACITA-1){
            view.victory(model.getMosse());
            sca.nextLine();
            return true;
        }
        return false;
    }

}
