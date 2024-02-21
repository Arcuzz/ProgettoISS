package pac;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class GameCaretaker {

    private Map<String, List<GameMemento>> snapshots = new HashMap<>();
    
    public Map<String, List<GameMemento>> getSnapshots() {
        return snapshots;
    }
    
    public void addSnapshot(GameMemento snapshot){
        if (snapshots.containsKey(snapshot.getNome())) {
            List<GameMemento> existingList = snapshots.get(snapshot.getNome());
            existingList.add(snapshot);
        } else {
            List<GameMemento> newList = new ArrayList<>();
            newList.add(snapshot);
            snapshots.put(snapshot.getNome(), newList);
        }
    }

    public boolean check_duplicate_name(String nome){
        return this.snapshots.containsKey(nome);
    }

    public void removeSnapshot(String nome){
        this.snapshots.remove(nome);
    }
    public GameMemento getSnapshot(String nome, int index){

        if (this.snapshots.get(nome).size() > index) return this.snapshots.get(nome).get(index);
        return null;
    }
    public void printPerSnapshots(){
        System.out.print(Grafica.sep+"Sono presenti salvataggi per i seguenti personaggi:\n\n\t");
        for (String nom: this.snapshots.keySet()) {
            System.out.print(Grafica.sep+"# "+nom);
        }
        System.out.println("\n");
    }

    public void printSnapshots(String nome){
        if (snapshots.containsKey(nome)){
            System.out.println(Grafica.sep+"Per il personaggio " + nome + " sono presenti i seguenti salvataggi");
            int i = 1;
            List<GameMemento> memList = snapshots.get(nome);
            for (GameMemento mem : memList){

                System.out.println(Grafica.sep+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(Grafica.sep+"+                                                          +");
                System.out.println(Grafica.sep+"+   \"+i+\")    Nome: \"+ nome+\"          Data: \"+ mem.getDate()+\"     +");
                System.out.println(Grafica.sep+"+                                                          +");
                System.out.println(Grafica.sep+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                i++;
            }
        }else{
            System.out.println(Grafica.sep+"Non è presente alcun salavataggio");
        }
    }


    public boolean check_save(String nome){
        return snapshots.get(nome) != null;
    }
    public void saveGame(String fileName){
        try {
            Path filePath = Paths.get(fileName);
            Path parentDir = filePath.getParent();

            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                outputStream.writeObject(snapshots);
                System.out.println(Grafica.sep + "Game saved.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadGameFile(String fileName){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            snapshots = (Map<String, List<GameMemento>>) inputStream.readObject();
            System.out.println(Grafica.sep+"Game file loaded");
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
