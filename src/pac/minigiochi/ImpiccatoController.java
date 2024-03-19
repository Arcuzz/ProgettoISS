package pac.minigiochi;

import java.io.Serializable;
import java.util.Scanner;

public class ImpiccatoController extends MinigiocoController implements Serializable {

    private ImpiccatoModel model;
    private ImpiccattoView view;


    public ImpiccatoController(ImpiccatoModel model, ImpiccattoView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public boolean play(Scanner sca, String nome){
        view.initGraphic(nome);
        sca.nextLine();
        String in = "";
        view.startGame(model.remainingAttempts);
        if (sca.nextLine().equalsIgnoreCase("n")) return false;

        while (model.remainingAttempts > 0 && !model.getSecret().equalsIgnoreCase(model.guessed.toString()) && !in.equals("exit")) {

            view.showGame(model.guessed, model.remainingAttempts);
            in = sca.nextLine();
            
            if(in.length() > 1){
                if (in.equalsIgnoreCase(model.getSecret())){
                    model.guessed.setLength(0);
                    model.guessed.append(model.getSecret());
                    view.showGame(model.guessed, model.remainingAttempts);
                    view.completedGame();
                    view.resumeGame();
                    sca.nextLine();
                    return true;
                }else if (model.getSecret().toLowerCase().contains(in.toLowerCase())){
                    int init_pos = model.getSecret().toLowerCase().indexOf(in.toLowerCase());
                    for(int i = init_pos; i < init_pos + in.length(); i++){
                        model.guessed.setCharAt(i, model.getSecret().charAt(i));
                    }if (model.getSecret().equalsIgnoreCase(model.guessed.toString())){
                        view.showGame(model.guessed, model.remainingAttempts);
                        view.completedGame();
                        view.resumeGame();
                        sca.nextLine();
                        return true;
                    }

                }else if(!in.equalsIgnoreCase("exit"))model.remainingAttempts -= in.length();
            }else{
                if(model.getSecret().contains(in.toLowerCase())){
                    for(int i = 0; i < model.getSecret().length(); i++){
                        if(String.valueOf(model.getSecret().charAt(i)).equalsIgnoreCase(in.toLowerCase())) model.guessed.setCharAt(i, in.charAt(0));
                    }
                    if (model.getSecret().equalsIgnoreCase(model.guessed.toString())){
                        view.showGame(model.guessed, model.remainingAttempts);
                        view.completedGame();
                        view.resumeGame();
                        sca.nextLine();
                        return true;
                    }
                }else model.remainingAttempts --;  
            }
        }
        return false;
    }

}
