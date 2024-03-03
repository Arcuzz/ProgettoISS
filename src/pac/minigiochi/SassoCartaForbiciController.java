package pac.minigiochi;

import java.io.Serializable;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import pac.Grafica;

public class SassoCartaForbiciController extends MinigiocoController implements Serializable {

    private SassoCartaForbiciModel model;
    private SassoCartaForbiciView view;

    public SassoCartaForbiciController(SassoCartaForbiciModel model, SassoCartaForbiciView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public boolean play(Scanner scan, String nome) {
        view.initGraphic(nome);
        scan.nextLine();

        int vittorieUser=0, vittoriePC=0;
        view.startGame(model.obiettivo);
        if (scan.nextLine().equalsIgnoreCase("n")) return false;
        while(vittorieUser<model.obiettivo && vittoriePC<model.obiettivo){

            view.mainGame(vittoriePC, vittorieUser);
            String input = scan.nextLine();

            if(input.equalsIgnoreCase("exit")){
                view.exit();
                return false;
            }

            view.sceltaUser(input);
            attesa(4);
            view.sceltaPC(model.sceltaPC.getFirst());

            int esito = esitoTurno(input, model.sceltaPC.getFirst());
            if(esito==1){
                view.roundVinto();
                vittorieUser++;
            }
            else if(esito==2){
                view.roundPerso();
                vittoriePC++;
            }

            Collections.shuffle(model.sceltaPC);
            view.resumeGame();
            scan.nextLine();
        }

        view.finalScore(vittorieUser, vittoriePC);

        if(vittoriePC==0){
            model.punti += model.punti;
            view.perfectVictory();
            return true;
        }
        else if(vittorieUser>vittoriePC){
            view.victory();
            return true;
        }
        else{
            view.lose();
            return false;
        }
    }

    public int esitoTurno(String sceltaUser, String sceltaPC){
        //ritorna true in caso di vittoria
        if(sceltaUser.equalsIgnoreCase(sceltaPC)){
            view.draw();
            return 0;
        }
        else switch (sceltaUser){
            case "s","S": if(sceltaPC.equalsIgnoreCase("f")) return 1; else return 2;
            case "c","C": if(sceltaPC.equalsIgnoreCase("s")) return 1; else return 2;
            case "f","F": if(sceltaPC.equalsIgnoreCase("c")) return 1; else return 2;
        }
        return 0;
    }
    
    private void attesa(int t){
        System.out.print(Grafica.sep);
        for(;t>0;t--){
            System.out.print(".  ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }

}
