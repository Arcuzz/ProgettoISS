package pac;

import java.util.ArrayList;

public class ClassificaView {

    public void stampaClassifica(ArrayList<RecordPersona> rp){
        System.out.println("\n" + Grafica.sep+"Classifica: ");
        for(int i=0; i<rp.size(); i++)
            System.out.println(Grafica.sep+(i+1)+"o: "+rp.get(i).nome+" Punti: "+rp.get(i).punteggio);
    }

}
