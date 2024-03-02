package pac;

import java.io.Serializable;
import java.util.*;

import pac.minigiochi.*;
import pac.stanze.Npc;

public class TorreModel implements Serializable {

    private int livello;
    private ArrayList<String> temi;
    private DifficultyModel diff;
    private Piano pianoCurr;
    private ArrayList<Npc> npc = new ArrayList<>();
    private static TorreModel instance = null;
    private GameCaretakerModel caretaker;
    private long time;

    private TorreModel(ArrayList<String> temi, DifficultyModel diff, Piano piano, int livello, GameCaretakerModel caretaker, long time){
        this.temi = temi;
        this.diff = diff;
        this.livello = livello;
        this.pianoCurr = piano;
        this.caretaker = caretaker;
        this.time = time;
        initNPC();
    }
    public static TorreModel getDefaultInstance(ArrayList<String> temi, DifficultyModel diff, GameCaretakerModel caretaker){
        if(instance==null){
            instance = new TorreModel(temi, diff, null, 1, caretaker, 0);
            Collections.shuffle(instance.temi);
        }
        return instance;
    }

    public static TorreModel getSavedInstance(ArrayList<String> temi, DifficultyModel diff, Piano piano, GameCaretakerModel caretaker, long time){
        if(instance==null){
            instance = new TorreModel(temi, diff, piano, piano.livello, caretaker, time);
            Collections.shuffle(instance.temi);
        }
        return instance;
    }

    public void increaseLvl(){
        this.livello ++;
        this.time = 0;
    }

    public static void resetInstance(){
        instance = null;
    }

    public void addSnap(GameMemento memento){
        this.caretaker.addSnapshot(memento);
    }

    public void saveGame(){
        GameCaretakerController gameCaretakerController = new GameCaretakerController(caretaker, new GameCaretakerView());
        gameCaretakerController.saveGame("local/saves/save.ser");
    }

    public ArrayList<String> getTemi(){
        return this.temi;
    }

    public long getTime(){
        return this.time;
    }

    public DifficultyModel getDiff(){
        return this.diff;
    }

    public Piano getPiano(){
        return this.pianoCurr;
    }

    public int getLivello(){
        return this.livello;
    }

    public void setupTorre(ProtagonistaModel pro){
        ArrayList<Npc> floorNPC = new ArrayList<>(this.npc.subList(this.livello-1,this.livello));
        this.pianoCurr = new Piano(this.livello, diff.getDifficolta(), this.temi.get(this.livello), floorNPC);
        pro.attivaAiutante(this.pianoCurr.tema);
        ProtagonistaController pro_Controller = new ProtagonistaController(pro, new ProtagonistaView());
        pro_Controller.start(this.pianoCurr);
    }

    public void initNPC(){
        ImpiccatoModel imp_Model = new ImpiccatoModel("Giovanni", 10, this.diff.getDifficolta());
        ImpiccattoView imp_View = new ImpiccattoView();
        this.npc.add(new Npc("Aldo", imp_Model, imp_View, new ImpiccatoController(imp_Model, imp_View)));

        VersaLiquidoModel ver_Model = new VersaLiquidoModel(this.diff.getDifficolta());
        VersaLiquidoView ver_View = new VersaLiquidoView(); 
        this.npc.add(new Npc("Giovanni", ver_Model, ver_View, new VersaLiquidoController(ver_Model, ver_View)));

        BriscolaModel bri_Model = new BriscolaModel();
        BriscolaView bri_View = new BriscolaView();
        this.npc.add(new Npc("Giacomo", bri_Model, bri_View, new BriscolaController(bri_Model, bri_View)));

        MemoryModel mem_Model = new MemoryModel();
        MemoryView mem_View = new MemoryView();
        this.npc.add(new Npc("Memy", mem_Model, mem_View, new MemoryController(mem_Model, mem_View)));

        SassoCartaForbiciModel scf_Model = new SassoCartaForbiciModel();
        SassoCartaForbiciView scf_View = new SassoCartaForbiciView();
        this.npc.add(new Npc("SCF", scf_Model, scf_View, new SassoCartaForbiciController(scf_Model, scf_View)));

        Collections.shuffle(this.npc);
    }
}
