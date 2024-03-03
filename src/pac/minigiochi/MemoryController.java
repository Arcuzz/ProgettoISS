package pac.minigiochi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MemoryController extends MinigiocoController implements Serializable {

    private MemoryModel model;
    private MemoryView view;

    public MemoryController(MemoryModel model, MemoryView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public boolean play(Scanner sca, String nome){
        view.initGraphic(nome);
        sca.nextLine();
        view.startGame(model.getNumeri().size(), model.getRank());
        String in = sca.nextLine();
        if(in.equals("n")){
            reset();
            return false;
        }
        scopriTutto();
        this.move(sca);
        if(model.getInput().equalsIgnoreCase("exit")){
            view.exit();
            reset();
            return false;
        }
        if(model.getTentativi() == model.getCoppieScop()){
            view.perfectVictory();
            model.setPunti(model.getPunti());
        }
        else view.tentVictory(this.model.getTentativi());
        return true;
    }

    public void selezioneCarta(int check){
        //è stata scelta solo la prima carta
        if(check==0){
            model.setNumeriVisibili(model.getPos(), model.getCoppia().get(0));
            model.setPosPrimaCarta(model.getPos());
        }
        //le due carte scelte sono uguali
        else if(check==1){
            model.setNumeriVisibili(model.getNumeri().indexOf(model.getCoppia().get(0)), model.getCoppia().get(0));
            model.setNumeriVisibili(model.getNumeri().indexOf(model.getCoppia().get(0)), model.getCoppia().get(0));
            model.setNumeriVisibili(model.getNumeri().lastIndexOf(model.getCoppia().get(0)), model.getCoppia().get(0));
            model.resetCoppia();
            model.setCoppieScop(model.getCoppieScop() + 1);
            model.setTentativi(model.getTentativi() + 1);
            view.indovinato();
        }
        //le due carte scelte sono diverse
        else if(check==2){

            model.setNumeriVisibili(model.getPos(), model.getCoppia().get(1));
            printNumeri(model.getNumeriVisibili());
            view.differentCard();
            model.setNumeriVisibili(model.getPosPrimaCarta(), 0);
            model.setNumeriVisibili(model.getPos(), 0);
            Collections.shuffle(model.getNumeri());

            for(int i=0; i < model.getNumeriVisibili().size(); i++){
                int tmp = model.getNumeriVisibili().get(i);
                if(tmp!=0 && !model.getGiaVisto().get(tmp)){
                    Collections.swap(model.getNumeri(), model.getNumeriVisibili().indexOf(tmp), model.getNumeri().indexOf(tmp));
                    Collections.swap(model.getNumeri(), model.getNumeriVisibili().lastIndexOf(tmp), model.getNumeri().lastIndexOf(tmp));
                    model.setGiaVisto(tmp, true);
                }
            }

            scopriTutto();
            model.resetGiaVisto();
            model.resetCoppia();
            model.setTentativi(model.getTentativi() + 1);
        }
    }

    public int checkCoppia(int num){
        //aggiunge la prima carta nella coppia da confrontare
        if(model.getCoppia().size()<2) model.setCoppia(num);
        //se è stata scelta la seconda carta le confronta
        if(model.getCoppia().size()==2){
            if(model.getCoppia().get(0) == model.getCoppia().get(1)) return 1;
            else return 2;
        }
        return 0;
        //serve per il metodo selezioneCarta
    }

    public void move(Scanner sca){
        model.setInput("");
        while(!model.getInput().equalsIgnoreCase("exit") && model.getCoppieScop() != model.getNumeri().size()/2){

            view.startGame(model.getNumeri().size(), model.getRank());

            printNumeri(model.getNumeriVisibili());
            view.direction();
            model.setInput(sca.nextLine());

            switch (model.getInput()) {
                case "d","D" -> {
                    if (legalCoord(model.getPos() + 1) && model.getNumeri().get(model.getPos() + 1) != null) model.setPos(1);
                    else view.invalidDirection();
                }
                case "a","A" -> {
                    if (legalCoord(model.getPos() - 1) && model.getNumeri().get(model.getPos() - 1) != null) model.setPos(-1);
                    else view.invalidDirection();
                }
                case "w","W" -> {
                    if (legalCoord(model.getPos() - 5) && model.getNumeri().get(model.getPos() - 5) != null) model.setPos(-5);
                    else view.invalidDirection();
                }
                case "s","S" -> {
                    if (legalCoord(model.getPos() + 5) && model.getNumeri().get(model.getPos() + 5) != null) model.setPos(5);
                    else view.invalidDirection();
                }
                case "r","R" -> {reset(); Collections.shuffle(model.getNumeri());}
                case "h" -> printNumeri(model.getNumeri());
                case "x","X" -> {
                    if(model.getNumeriVisibili().get(model.getPos())!=0){
                        view.giaScoperta();
                        continue;
                    }
                    selezioneCarta(checkCoppia(model.getNumeri().get(model.getPos())));
                    if(model.getCoppieScop()==model.getNumeri().size()/2){
                        view.vicotory();
                    }
                }
            }
        }
    }

    public void reset(){
        model.setPos(0);
        model.setCoppieScop(0);
        model.setPosPrimaCarta(0);
        model.setTentativi(0);
        Collections.sort(model.getNumeri());
        model.resetNumeriVisibili();
        model.resetCoppia();
        model.resetGiaVisto();
    }

    public boolean legalCoord(int x){
        return x >= 0 && x < model.getNumeri().size();
    }

    public void printNumeri(ArrayList<Integer> arr){
        for (int i = 0; i < arr.size(); i++) {
            if(i%5==0)
                view.printNum1();
            if(arr.get(i)<10)
                view.printNum2();
            if(i==model.getPos()){
                view.printNum3(arr.get(i));
            }
            else view.printNum4(arr.get(i));
        }
        view.doubleNewLine();
    }

    public void scopriTutto(){
        view.memorize();
        printNumeri(model.getNumeri());
        attesa(5*model.getRank());
    }

    private void attesa(int t){
        view.newLine();
        for(;t>0;t--){
            view.waiting(t);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        view.newLine();
    }

}
