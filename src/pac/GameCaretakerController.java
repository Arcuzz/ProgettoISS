package pac;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class GameCaretakerController {

    private GameCaretakerModel model;
    private GameCaretakerView view;

    public GameCaretakerController(GameCaretakerModel model, GameCaretakerView view){
        this.model = model;
        this.view = view;
    }

    public void removeSnapshot(String nome){
        model.removeSnapshot(nome);
    }

    public void printCharSnapshot(){
        view.printCharSnapshot(model.getSnapshots());
    }

    public void printSnapshots(String nome){
        view.printSnapshots(nome, model.getSnapshots());
    }

    public void saveGame(String fileName){
        try {
            Path filePath = Paths.get(fileName);
            Path parentDir = filePath.getParent();

            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                outputStream.writeObject(model.getSnapshots());
                view.printGameSaved();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadGameFile(String fileName){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            model.setSnapshots((Map<String, List<GameMemento>>) inputStream.readObject());
            view.printGameLoaded();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
