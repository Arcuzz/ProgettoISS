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

        while (model.remainingAttempts > 0 && !model.secret.equalsIgnoreCase(model.guessed.toString()) && !in.equals("exit")) {

            view.startGame();

            view.showGame(model.guessed, model.remainingAttempts);
            in = sca.nextLine();
            
            if(in.length() > 1){
                if (in.equalsIgnoreCase(model.secret)){
                    view.completedGame();
                    model.guessed.setLength(0);
                    model.guessed.append(model.secret);
                    view.resumeGame();
                    sca.nextLine();
                    return true;
                }else if (model.secret.contains(in.toLowerCase())){
                    int j = 0;
                    for(int i = 0; i < model.secret.length(); i++){
                        if ((model.secret.charAt(i)) == in.toLowerCase().charAt(j)){
                            model.guessed.setCharAt(i, in.charAt(j));
                            j++;
                        }
                    }if (model.secret.equalsIgnoreCase(model.guessed.toString())){
                        view.completedGame();
                        view.resumeGame();
                        sca.nextLine();
                        return true;
                    }

                }else if(!in.equalsIgnoreCase("exit"))model.remainingAttempts -= in.length();
            }else{
                in = in.toLowerCase();
                if(model.secret.contains(in)){
                    for(int i = 0; i < model.secret.length(); i++){
                        if(String.valueOf(model.secret.charAt(i)).equalsIgnoreCase(in)) model.guessed.setCharAt(i, in.charAt(0));
                    }
                }else model.remainingAttempts --;  
            }
        }
        return false;
    }

}
