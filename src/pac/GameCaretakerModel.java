package pac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameCaretakerModel {

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

    public void setSnapshots(Map<String, List<GameMemento>> snapshots){this.snapshots = snapshots;}
    public Map<String, List<GameMemento>> getSnapshots(){return this.snapshots;}

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

    public boolean check_save(String nome){
        return snapshots.get(nome) != null;
    }

}
