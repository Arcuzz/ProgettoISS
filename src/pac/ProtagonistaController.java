package pac;

import pac.stanze.Domanda;
import pac.stanze.Npc;

import java.io.Serializable;
import java.util.Scanner;

public class ProtagonistaController implements Serializable {

    private static final int[][] direzioni = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private ProtagonistaModel model;
    private ProtagonistaView view;

    public ProtagonistaController(ProtagonistaModel model, ProtagonistaView view){
        this.model = model;
        this.view = view;
    }

    public void start(Piano piano){
        model.start(piano);
        aggiungi_adj();
    }

    public int move(Scanner scan){
        String in;
        view.vision(model.getPiano().livello, model.getVisited(), model.getX(), model.getY());
        //System.out.println("\nRisolte: "+this.piano.dom_sup + " | Totali: " + this.piano.n_dom);
        view.chooseDirection();
        in = scan.nextLine();
        switch (in) {
            case "d","D":
                if(legal_coord(model.getX(), model.getY()+1) && legal_cell(model.getX(), model.getY()+1)) model.setY(model.getY() + 1);
                else view.invalidDirection();
                break;
            case "a","A":
                if(legal_coord(model.getX(), model.getY()-1) && legal_cell(model.getX(), model.getY()-1)) model.setY(model.getY() - 1);
                else view.invalidDirection();
                break;
            case "w","W":
                if(legal_coord(model.getX()-1, model.getY()) && legal_cell(model.getX()-1, model.getY())) model.setX(model.getX() - 1);
                else view.invalidDirection();
                break;
            case "s","S":
                if(legal_coord(model.getX()+1, model.getY()) && legal_cell(model.getX()+1, model.getY())) model.setX(model.getX() + 1);
                else view.invalidDirection();
                break;
            case "r","R":
                riepilogo();
                scan.nextLine();
                break;
            case "h","H":
                help(scan);
                scan.nextLine();
                break;
            case "exit": return 3;
        }
        if(checkRoom(scan) == '+') return 2;
        if(nextFloor(scan)) return 1; // si continua al prossimo piano
        aggiungi_adj();
        return 0;
    }

    public boolean nextFloor(Scanner scan){
        String in;
        if (model.getPiano().dom_sup == model.getPiano().n_dom && !last()){
            view.nextFloor();
            in = scan.nextLine();
            if(in.equalsIgnoreCase("s")) return true;
            model.setLast_d(model.getLast_d() + 1);
        }
        else if (model.getPiano().mat[model.getX()][model.getY()].id == 'S' && model.getPiano().dom_sup == model.getPiano().n_dom){
            view.nextFloor();
            in = scan.nextLine();
            return in.equalsIgnoreCase("s");
        }
        return false;
    }

    public char checkRoom(Scanner scan){
        if (model.getPiano().mat[model.getX()][model.getY()].id == 'D' && !((Domanda)model.getPiano().mat[model.getX()][model.getY()]).risposta){
            Domanda d = (Domanda)model.getPiano().mat[model.getX()][model.getY()];
            view.startDom();
            d.idle(scan,model.getAiutante());

            if(d.risposta){
                int p = model.getPiano().livello * (10-d.prova.getContaErrori());
                if(d.prova.getContaErrori()>2) p = 5*model.getPiano().livello;
                model.setPunteggio_totale(model.getPunteggio_totale() + p);
                model.setPunti_dom(model.getPunti_dom() + p);
                model.setN_dom_risposte(model.getN_errori_dom() + 1);
                model.setN_errori_dom(model.getN_errori_dom() + d.prova.getContaErrori());
                view.score(p);
                model.getPiano().dom_sup++;
                view.resumeGame();
                scan.nextLine();
            }
            return 'D';
        }
        else if (model.getPiano().mat[model.getX()][model.getY()].id == 'N' && !((Npc)model.getPiano().mat[model.getX()][model.getY()]).res){
            Npc n = (Npc)model.getPiano().mat[model.getX()][model.getY()];
            Grafica.clearConsole();

            n.idle(scan);
            if(n.res){
                model.setPunteggio_totale(model.getPunteggio_totale() + n.model.punti);
                model.setN_mini_risolti(model.getN_mini_risolti() + 1);
                model.setPunti_mini(model.getPunti_mini() + n.model.punti);
                view.score(n.model.punti);
                view.resumeGame();
                scan.nextLine();
            }
            return 'N';
        }
        else if (model.getPiano().mat[model.getX()][model.getY()].id == '+'){
            view.saveGame();
            if(scan.nextLine().equalsIgnoreCase("s")){
                return '+';
            }
        }
        return 'v';
    }

    public void help(Scanner scan){
        view.helpHeader();
        switch(Integer.parseInt(scan.nextLine())){
            case 1: view.helpBody();
                scan.nextLine(); break;
            case 2: model.getPiano().dom_sup = model.getPiano().n_dom; break;
        }
        view.resumeGame();
    }

    public void riepilogo(){
        view.riepilogo(model.getNome(), model.getPiano().difficolta, model.getPunteggio_totale(), model.getPiano().livello, model.getPiano().tema, model.getN_dom_risposte(), model.getN_dom_risposte(), model.getN_mini_risolti(), model.getPunti_mini(), model.getPunti_dom());
        view.resumeGame();
    }

    public void aggiungi_adj(){
        for(int[] direzione: direzioni){
            int n_rig = model.getX() + direzione[0];
            int n_col = model.getY() + direzione[1];
            if(legal_coord(n_rig, n_col) && model.getPiano().mat[n_rig][n_col] != null && model.getVisited()[n_rig][n_col] == null){
                model.getVisited()[n_rig][n_col] = model.getPiano().mat[n_rig][n_col];
            }
        }
    }

    public boolean legal_cell(int x, int y){
        return (model.getPiano().mat[x][y] != null && model.getPiano().mat[x][y].id != '#');
    }

    public boolean legal_coord(int x, int y){
        return (x >= 0 && x < model.getPiano().mat.length && y >= 0 && y < model.getPiano().mat.length);
    }

    public boolean last(){
        return model.getLast_d() > 0;
    }
}
