package pac;


import java.io.Serializable;

public class ClassificaController implements Serializable {

    private ClassificaModel model;
    private ClassificaView view;

    public ClassificaController(ClassificaModel model, ClassificaView view){
        this.model = model;
        this.view = view;
    }

    public void aggiornaRecord(String nome, int punteggio){
        model.addRecord(new RecordPersona(nome,punteggio));
        for(int i = model.getRp().size()-1; i>0; i--){
            if(model.getRp().get(i).punteggio > model.getRp().get(i-1).punteggio)
                model.swap(i, i-1);
            if(model.getRp().get(i).punteggio==model.getRp().get(i-1).punteggio){
                model.getRp().get(i-1).nome = model.getRp().get(i-1).nome + ", " + model.getRp().get(i).nome;
                model.removeRp(i);
            }
        }
        if(model.getRp().size()>5)
            model.removeRp(model.getRp().size()-1);
    }

    public void stampaClassifica(){
        view.stampaClassifica(model.getRp());
    }
}
