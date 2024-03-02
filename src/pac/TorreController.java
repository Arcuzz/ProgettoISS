package pac;

import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class TorreController implements Serializable {

    private TorreView view;
    private TorreModel model;
    
    public TorreController(TorreView view, TorreModel model){
        this.model = model;
        this.view = view;
    }

    public void game(Scanner scan, ProtagonistaModel pro) throws IOException{
        Instant start;
        long penalty;
        ProtagonistaView pro_View = new ProtagonistaView();
        ProtagonistaController pro_Controller = new ProtagonistaController(pro, pro_View);
        view.intro(pro.getNome());
        scan.nextLine();
        int out = 0;
        while (model.getLivello() <= model.getDiff().getNumPiani()) {
            start = Instant.now();
            out = 0;
            while (out != 1 && out != 3){
                out = pro_Controller.move(scan);
                if (out == 2){
                    GameMemento memento = new GameMemento(model.getDiff(), model.getTemi(), model.getPiano(),
                            pro.getNome(), new int[]{pro.getX(), pro.getY()}, pro.getVisited(), pro.getAiutante(),
                            new int[]{pro.getN_dom_risposte(),pro.getN_errori_dom(), pro.getPunti_dom()},
                            new int[]{pro.getN_mini_risolti(),pro.getPunti_mini()}, pro.getPunteggio_totale(), time_penalty(start, Instant.now(), false, pro));
                    model.addSnap(memento);
                    model.saveGame();
                    view.correctSave();
                    scan.nextLine();
                }
            }
            if(out == 1 && model.getLivello()+1 <= model.getDiff().getNumPiani()){
                penalty = time_penalty(start, Instant.now(), true, pro);
                view.fineLvl(model.getLivello(), penalty, out);
                model.increaseLvl();
                view.resumeGame();
                scan.nextLine();
                model.setupTorre(pro);
            }else{
                time_penalty(start, Instant.now(), true, pro);
                view.exit(pro.getPunteggio_totale());
                view.resumeGame();
                scan.nextLine();
                break;
            }
        }if (model.getLivello() == model.getDiff().getNumPiani() && out != 3){
            view.victory();
        }
        printClassifica(pro);
        view.crediti();
        view.gameContinue();
        scan.nextLine();
    }

    public long time_penalty(Instant start, Instant end, boolean end_plan, ProtagonistaModel pro){
        long tot = Duration.between(start, end).toSeconds() + model.getTime();
        if (end_plan) pro.setPunteggio_totale(pro.getPunteggio_totale() - (int) (tot/2));
        return tot;
    }

    public void printClassifica(ProtagonistaModel pro) throws IOException{
        ClassificaModel c_Model = new ClassificaModel();
        ClassificaView c_View = new ClassificaView();
        ClassificaController c_Controller = new ClassificaController(c_Model, c_View);
        view.printClassificaView(pro, c_Controller, c_Model);
    }

}
