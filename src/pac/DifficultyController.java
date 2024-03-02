package pac;

import java.io.Serializable;
import java.util.Scanner;

public class DifficultyController implements Serializable {

    private DifficultyModel model;
    private DifficultyView view;

    public DifficultyController(DifficultyView view, DifficultyModel model){
        this.model = model;
        this.view = view;
    }

    public void createDifficulty(Scanner scan){
        int stat = staticaScelta(scan);
        if(stat==1){
            model.setStatica(true);
            int level = staticaLivello(scan);
            switch (level) {
                case 1:
                    model.setDiff("facile", 3);
                    aiutoFacile(scan);
                    break;
                case 2:
                    model.setDiff("media", 4);
                    sceltaAiutante(scan);
                    break;
                case 3:
                    model.setDiff("difficile", 5);
                    model.setAiutante(new Aiutante(5));
                    break;
                default:
                    break;
            }
        }
        else{
            model.setStatica(false);
            model.setDiff("crescente", 3);
            sceltaAiutante(scan);
        }
    }

    public int staticaScelta(Scanner scan){
        int scelta;
        do{
            view.promptStatica();
            scelta = Integer.parseInt(scan.nextLine());
            while(scelta!=1 && scelta!=2){
                view.wrongInput();
                scelta = Integer.parseInt(scan.nextLine());
            }
        }while(!sceltaSicura(scan));
        return scelta;
    }

    public boolean sceltaSicura(Scanner scan){
        view.sceltaSicura();
        String choice = scan.nextLine();
        while(!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n")){
            view.wrongInput();
            choice = scan.nextLine();
        }
        return choice.equalsIgnoreCase("s") || !choice.equalsIgnoreCase("n");
    }

    public void sceltaAiutante(Scanner scan){
        int scelta;
        do{
            view.promptAiutante();
            scelta = Integer.parseInt(scan.nextLine());
            while(scelta<0 || scelta>3){
                view.wrongInput();
                scelta = Integer.parseInt(scan.nextLine());
            }
        }while(!sceltaSicura(scan));
        model.setAiutante(new Aiutante(scelta+1));
    }
    
    public void aiutoFacile(Scanner scan){
        String choice;
        do{
            view.aiutoFacile();
            choice = scan.nextLine();
            while(!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n")){
                view.wrongInput();
                choice = scan.nextLine();
            }
        }while(!sceltaSicura(scan));
        if(choice.equalsIgnoreCase("s")) model.setAiutante(new Aiutante(1));
        else if(choice.equalsIgnoreCase("n")) model.setAiutante(new Aiutante(5));
    }

    public int staticaLivello(Scanner scan){
        int scelta;
        do{
            view.staticaLivello();
            scelta = Integer.parseInt(scan.nextLine());
            while(scelta!=1 && scelta!=2 && scelta!=3){
                view.wrongInput();
                scelta = Integer.parseInt(scan.nextLine());
            }
        }while(!sceltaSicura(scan));
        return scelta;
    }
}
