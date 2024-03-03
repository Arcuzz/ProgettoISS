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
        view.startGame();
        if (sca.nextLine().equalsIgnoreCase("n")) return false;

        while (model.remainingAttempts > 0 && !model.secret.equalsIgnoreCase(model.guessed.toString()) && !in.equals("exit")) {

            view.showGame(model.guessed, model.remainingAttempts);
            in = sca.nextLine();
            
            if(in.length() > 1){
                if (in.equalsIgnoreCase(model.secret)){
                    view.showGame(model.guessed, model.remainingAttempts);
                    view.completedGame();
                    model.guessed.setLength(0);
                    model.guessed.append(model.secret);
                    view.resumeGame();
                    sca.nextLine();
                    return true;
                }else if (model.secret.toLowerCase().contains(in.toLowerCase())){
                    int init_pos = model.secret.toLowerCase().indexOf(in.toLowerCase());
                    for(int i = init_pos; i < init_pos + in.length(); i++){
                        model.guessed.setCharAt(i, model.secret.charAt(i));
                    }if (model.secret.equalsIgnoreCase(model.guessed.toString())){
                        view.showGame(model.guessed, model.remainingAttempts);
                        view.completedGame();
                        view.resumeGame();
                        sca.nextLine();
                        return true;
                    }

                }else if(!in.equalsIgnoreCase("exit"))model.remainingAttempts -= in.length();
            }else{
                if(model.secret.contains(in.toLowerCase())){
                    for(int i = 0; i < model.secret.length(); i++){
                        if(String.valueOf(model.secret.charAt(i)).equalsIgnoreCase(in.toLowerCase())) model.guessed.setCharAt(i, in.charAt(0));
                    }
                    if (model.secret.equalsIgnoreCase(model.guessed.toString())){
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
