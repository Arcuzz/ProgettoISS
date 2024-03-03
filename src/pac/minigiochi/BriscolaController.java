package pac.minigiochi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class BriscolaController extends MinigiocoController implements Serializable {

    private BriscolaModel model;
    private BriscolaView view;

    public BriscolaController(BriscolaModel model, BriscolaView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public boolean play(Scanner scan, String nome){
        view.initGraphic(nome);
        scan.nextLine();
        int mod=0;
        if(model.getRank() == 3) mod = 1;
        int punteggioEsito = partita(mod, scan);
        if(punteggioEsito == 0) return false;
        model.updateScore(punteggioEsito);
        return view.esitoPartita(punteggioEsito);
    }

    private int sceltaPc(int mod,ArrayList<String> mano_pc){
        int n;
        if(mod==0) n = ((int)(Math.random()* mano_pc.size()));
        else{n=0;}
        return n;
    }   //se il pc va per primo
    private int sceltaPc(int mod,ArrayList<String> mano_pc,String cartaUser){
        int n, i;   //n scelta finale pc
        if(mod==0) n = ((int)(Math.random()* mano_pc.size()));
        else{
            n=0;
            if(cartaUser.charAt(0) == model.getBriscola().charAt(0)){    //se l'utente butta una briscola per primo
                for(i=0;i<mano_pc.size();i++){
                    if(mano_pc.get(i).charAt(0) != model.getBriscola().charAt(0)){   //se il pc non ha briscola
                        if(model.getValue((int)mano_pc.get(i).charAt(1)-'0')==0){    //butta il liscio se lo ha
                            n=i;
                        }
                        else{   //altrimenti butta il minimo
                            if(model.getValue((int)mano_pc.get(i).charAt(1)-'0') < model.getValue((int)mano_pc.get(i+1).charAt(1)-'0'))
                                n=i;
                            else n=i+1;
                        }
                    }
                    else{   //se il pc ha briscola
                        if(model.getValue((int)mano_pc.get(i).charAt(1)-'0') > model.getValue((int)cartaUser.charAt(1)-'0'))   //ed è briscola maggiore dell'utente
                            if(model.getValue((int)mano_pc.get(i).charAt(1)-'0')<model.getValue((int)mano_pc.get(i+1).charAt(1)-'0'))
                                n=i;        //butta la briscola maggiore minima
                            else n=i+1;
                        else{
                            if(model.getValue((int)cartaUser.charAt(1)-'0')==0)  //butta il liscio briscola
                                n=i;
                            else{       //oppure butta la briscola minore minima
                                if(model.getValue((int)mano_pc.get(i).charAt(1)-'0')<model.getValue((int)mano_pc.get(i+1).charAt(1)-'0'))
                                    n=i;
                                else n=i+1;
                            }
                        }
                    }
                }
            }
            else{   //se l'utente butta una non briscola per primo
                for(i=0;i<mano_pc.size();i++){
                    if(mano_pc.get(i).charAt(0)==cartaUser.charAt(0)){  //se il pc ha carta dello stesso seme dell'utente
                        if(model.getValue((int)mano_pc.get(i).charAt(1)-'0') > model.getValue((int)cartaUser.charAt(1)-'0') && model.getValue((int)cartaUser.charAt(1)-'0')>0)    //e ha valore maggiore
                            if(model.getValue((int)mano_pc.get(i).charAt(1)-'0') < model.getValue((int)mano_pc.get(i+1).charAt(1)-'0')) //butta quella superiore più vicina
                                n=i;                                           //perché ha valore > cartauser ma la minore tra le sue
                            else n=i+1;
                        else if(model.getValue((int)mano_pc.get(i).charAt(1)-'0')==0)    //e non ha valore
                            n=i;
                    }
                    else if(model.getValue((int)cartaUser.charAt(1)-'0')>0 && mano_pc.get(i).charAt(0) == model.getBriscola().charAt(0)){ //se la cartauser ha valore e il pc ha briscola
                        if(model.getValue((int)mano_pc.get(i).charAt(1)-'0')==0)
                            n=i;
                        else{
                            if(model.getValue((int)mano_pc.get(i).charAt(1)-'0') < model.getValue((int)mano_pc.get(i+1).charAt(1)-'0'))
                                n=i;
                            else n=i+1;
                        }
                    }
                    else{  //se il pc non ha briscola nè stesso seme
                        if(model.getValue((int)mano_pc.get(i).charAt(1)-'0') == 0) //liscio
                            n=i;
                        else{
                            if(model.getValue((int)mano_pc.get(i).charAt(1)-'0') < model.getValue((int)mano_pc.get(i+1).charAt(1)-'0'))
                                n=i;
                            else n=i+1;
                        }
                    }
                }
            }
        }
        return n;
    }   //se l'utente va per primo
    
    private int sceltaUser(ArrayList<String> mano_user, Scanner scan){
        view.chooseCard();
        String c = scan.nextLine();
        while(!c.matches("[0-"+ mano_user.size() +"]")){
            view.chooseCardError();
            c = scan.nextLine();
        }
        int cNum = Integer.parseInt(c);;
        return cNum-1;
    }

    private int puntiTurno(String[] carte, int user){
        int punti = 0;
        boolean vincitore = false;
        if(user == 0){
            if(carte[0].charAt(0) == carte[1].charAt(0)) {
                if(model.getValue(carte[0].charAt(1)-'0') > model.getValue(carte[1].charAt(1)-'0'))
                    vincitore = true;
            }
            else{   //semi diversi
                if(carte[0].charAt(0) == model.getBriscola().charAt(0))
                    vincitore = true;
                else if(carte[1].charAt(0) != model.getBriscola().charAt(0) && model.getValue(carte[0].charAt(1)-'0') > model.getValue(carte[1].charAt(1)-'0')){ //entrambi senza briscola
                    vincitore = true;
                }
            }
        }
        else{
            if(carte[0].charAt(0) == carte[1].charAt(0)) {
                if(model.getValue(carte[0].charAt(1)-'0') < model.getValue(carte[1].charAt(1)-'0'))
                    vincitore = true;
            }
            else{
                if(carte[1].charAt(0) == model.getBriscola().charAt(0))
                    vincitore = true;
                else if(carte[0].charAt(0) != model.getBriscola().charAt(0) && model.getValue(carte[0].charAt(1)-'0') < model.getValue(carte[1].charAt(1)-'0')){ //entrambi senza briscola
                    vincitore = true;
                }
            }
        }
        if(vincitore) punti = model.getValue(carte[0].charAt(1)-'0') + model.getValue(carte[1].charAt(1)-'0');
        return punti;
    }

    private int partita(int mod, Scanner scan){   //0 facile, 1 difficile
        ArrayList<String> user = new ArrayList<>();
        ArrayList<String> pc = new ArrayList<>();
        String[] carte = new String[2];
        int punteggio = 0, carta, n_turno=1;
        view.startBriscola(model.getBriscola());
        int primoGiocatore = (int)(Math.random() * 2);

        attesa(200);

        if(primoGiocatore==0) view.testa();
        else view.croce();
        scan.nextLine();

        do{
            view.startGame();

            view.turno(n_turno);
            n_turno++;
            attesa(200);

            if(primoGiocatore==0){
                if(model.getIndice() < 40 && user.size() < 3){
                    user = model.riempiMano(user);
                    pc = model.riempiMano(pc);
                }

                view.printBirscola(model.getBriscola());
                view.printMano(user);
                attesa(200);
                carta = sceltaUser(user,scan);
                if(carta == -1){
                    view.exit();
                    model.reset();
                    return 0;
                }

                carte[0] = user.get(carta);
                view.playerCard(carte[0]);
                user.remove(carta);
                carta = sceltaPc(mod, pc, carte[0]);
                carte[1] = pc.get(carta);
                attesa(500);
                view.pcCard(carte[1]);
                pc.remove(carta);
                punteggio += puntiTurno(carte, 0);

            }else{
                if(model.getIndice() < 40 && user.size() < 3){
                    pc = model.riempiMano(pc);
                    user = model.riempiMano(user);
                }

                carta = sceltaPc(mod, pc);
                carte[0] = pc.get(carta);
                attesa(500);
                view.pcCard(carte[0]);
                pc.remove(carta);
                view.printBirscola(model.getBriscola());
                view.printMano(user);
                attesa(200);
                carta = sceltaUser(user,scan);

                if(carta == -1){
                    view.exit();
                    scan.nextLine();
                    model.reset();
                    return 0;
                }

                carte[1] = user.get(carta);
                view.playerCard(carte[1]);
                user.remove(carta);
                punteggio += puntiTurno(carte, 1);
            }
            view.continua();
            scan.nextLine();

        } while(user.size()>0);
        return punteggio;
    }

    private void attesa(int t){
        try {
            Thread.currentThread();
            Thread.sleep(t);
        }
        catch(Exception e){}
    }

}
