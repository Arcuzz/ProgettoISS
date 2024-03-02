package pac;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MenuModel {
    private static final ArrayList<String> temi = new ArrayList<>();
    private GameCaretakerModel caretaker_Model;
    private static final String path = "local/saves/save.ser";

    public MenuModel(){

        this.caretaker_Model = new GameCaretakerModel();
        GameCaretakerController caretaker_Controller = new GameCaretakerController(caretaker_Model, new GameCaretakerView());

        temi.add("Geografia");
        temi.add("Storia");
        temi.add("Matematica");
        temi.add("Informatica");
        temi.add("Italiano");
        temi.add("Inglese");

        if (checkSaveFile()) caretaker_Controller.loadGameFile(path);
    }

    public GameCaretakerModel getGameCareTaker(){
        return this.caretaker_Model;
    }

    public void shuffleTemi(){
        Collections.shuffle(temi);
    }

    public String getPath(){
        return MenuModel.path;
    }

    public ArrayList<String> getTemi(){
        return MenuModel.temi;
    }

    public boolean checkSaveFile () {
        File save = new File(path);
        return save.isFile();
    }
}
