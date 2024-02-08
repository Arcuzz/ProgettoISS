package pac;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameCaretaker {

    private Map<String, List<GameMemento>> snapshots = new HashMap<>();
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

    public void removeSnapshot(String nome){
        this.snapshots.remove(nome);
    }
    public GameMemento getSnapshot(String nome, int index){

        if (this.snapshots.get(nome).size() > index) return this.snapshots.get(nome).get(index);
        return null;
    }
    public void printPerSnapshots(){
        System.out.println("Sono presenti salvataggi per i seguenti personaggi:");
        int i = 1;
        for (String nom: this.snapshots.keySet()) {
            System.out.println(i+") "+nom);
            i++;
        }
    }

    public void printSnapshots(String nome){
        if (snapshots.containsKey(nome)){
            System.out.println("Per il personaggio " + nome + " sono presenti i seguenti salvataggi");
            int i = 1;
            List<GameMemento> memList = snapshots.get(nome);
            for (GameMemento mem : memList){
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
                        "+                                                          +\n" +
                        "+   "+i+")    Nome: "+ nome+"          Data: "+ mem.getDate()+"     +\n" +
                        "+                                                          +\n" +
                        "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        }else{
            System.out.println("Non è presente alcun salavataggio");
        }
    }

    public boolean check_save(String nome){
        return snapshots.get(nome) != null;
    }
    public void saveGame(String fileName){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(snapshots);
            System.out.println("Game saved.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadGameFile(String fileName){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            snapshots = (Map<String, List<GameMemento>>) inputStream.readObject();
            System.out.println("Game file loaded");
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
