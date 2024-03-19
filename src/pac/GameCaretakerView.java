package pac;

import java.util.List;
import java.util.Map;

public class GameCaretakerView {

    public void printCharSnapshot(Map<String, List<GameMemento>> snapshots){
        System.out.print(Grafica.sep+"Sono presenti salvataggi per i seguenti personaggi:\n\n\t");
        for (String nom: snapshots.keySet()) {
            System.out.print(Grafica.sep+"# "+nom);
        }
        System.out.println("\n");
    }

    public void printGameSaved(){
        System.out.println(Grafica.sep + "Game saved.");
    }

    public void printGameLoaded(){
        System.out.println(Grafica.sep+"Game file loaded");
    }

    public void printSnapshots(String nome, Map<String, List<GameMemento>> snapshots){
        if (snapshots.containsKey(nome)){
            System.out.println(Grafica.sep+"Per il personaggio " + nome + " sono presenti i seguenti salvataggi");
            int i = 1;
            List<GameMemento> memList = snapshots.get(nome);
            for (GameMemento mem : memList){

                System.out.println(Grafica.sep+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(Grafica.sep+"+                                                          +");
                System.out.println(Grafica.sep+"+   "+i+")    Nome: "+nome+"          Data: "+mem.getDate()+"     +");
                System.out.println(Grafica.sep+"+                                                          +");
                System.out.println(Grafica.sep+"+    Piano: "+mem.getPiano().livello+"      Punti: "+mem.getTotal_points()+"           Tempo: "+mem.getTime()+"s          +");
                System.out.println(Grafica.sep+"+                                                          +");
                System.out.println(Grafica.sep+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                i++;
            }
        }else{
            System.out.println(Grafica.sep+"Non è presente alcun salavataggio");
        }
    }
}
