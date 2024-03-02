package pac;

import java.io.IOException;
import java.util.Scanner;

public class MenuController {

    private MenuModel model;
    private MenuView view;

    public MenuController(MenuModel model, MenuView view){
        this.model = model;
        this.view = view;
    }

    public void display(Scanner scan) throws IOException {
        while (true) {
            view.menu_0();

            ProtagonistaModel.resetInstance();
            TorreModel.resetInstance();

            int input = Integer.parseInt(scan.nextLine());
            if (input == 1) {

                DifficultyModel diff_Model = new DifficultyModel();
                DifficultyController diff_Controller = new DifficultyController(new DifficultyView(), diff_Model);
                diff_Controller.createDifficulty(scan);

                view.getName();
                String nome = scan.nextLine();

                ProtagonistaModel pr;
                if (!model.getGameCareTaker().check_duplicate_name(nome)){
                    pr = ProtagonistaModel.getDefaultInstance(nome);
                    pr.setAiutante(diff_Model.getAiutante());
                    model.shuffleTemi();
                    TorreModel tor = TorreModel.getDefaultInstance(model.getTemi(), diff_Model, model.getGameCareTaker());
                    view.pressToStart();
                    scan.nextLine();

                    tor.setupTorre(pr);
                    TorreController tor_Controller = new TorreController(new TorreView(), tor);
                    tor_Controller.game(scan, pr);
                }else{
                    view.nomeEsistente();
                    scan.nextLine();
                }

            } else if (input == 2) {

                if (model.checkSaveFile()) {
                    while (true) {
                        view.menu_1();
                        GameCaretakerController gct_Controller = new GameCaretakerController(model.getGameCareTaker(), new GameCaretakerView());
                        gct_Controller.printCharSnapshot();
                        view.loadGameFile();
                        String nome = scan.nextLine();

                        if (nome.equals("d")){
                            view.menu_1();
                            view.deleteGameFile();
                            String to_del = scan.nextLine();
                            if (model.getGameCareTaker().check_save(to_del) && !to_del.equals("b")){
                                gct_Controller.removeSnapshot(to_del);
                                gct_Controller.saveGame(model.getPath());
                                view.deleteDone();
                                scan.nextLine();
                            }
                            else if (!to_del.equals("b")) {
                                view.invalidName();
                                scan.nextLine();
                            }

                        } else if (nome.equals("b")) {
                            break;

                        }else{
                            if (model.getGameCareTaker().check_save(nome)){
                                view.menu_1();
                                gct_Controller.printSnapshots(nome);
                                view.chooseSave();
                                String val = scan.nextLine();
                                
                                if (!val.equals("0")) {
                                    GameMemento memento = model.getGameCareTaker().getSnapshot(nome, Integer.parseInt(val) - 1);
                                    if (memento != null) {
                                        ProtagonistaModel pro = ProtagonistaModel.getSavedInstance(memento.getNome(), memento.getPiano(), memento.getPos(), memento.getVisited(), memento.getAiutante(), memento.getDom(), memento.getMini(), memento.getTotal_points());
                                        TorreModel tor = TorreModel.getSavedInstance(memento.getTemi(), memento.getDiff(), memento.getPiano(), model.getGameCareTaker(), memento.getTime());
                                        TorreController tor_Controller = new TorreController(new TorreView(), tor);
                                        tor_Controller.game(scan, pro);
                                        break;
                                    } else {
                                        view.invalidGameFile();
                                        scan.nextLine();
                                    }
                                }
                            } else {
                                view.invalidName();
                                scan.nextLine();
                            }
                        }
                    }
                } else {
                    view.emptyGameFile();
                    scan.nextLine();
                }
            }else break;
        }
        view.clear();
    }

}
