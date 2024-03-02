package pac;

import java.io.IOException;

public class TorreView {

    public void gameContinue(){
        System.out.println(Grafica.sep+"Premi invio per riprendere il gioco");
        System.out.print(Grafica.sep+"+ ");
    }

    public void intro(String nome){
        Grafica.clearConsole();
        System.out.println("\n" + Grafica.sep+"Una mattina come tante. Una sveglia suona. "+nome+", uno studente di informatica apre gli occhi.\n" +
                Grafica.sep+"Per lui oggi è un giorno importante: deve sostenere il suo ultimo esame.\n" +
                Grafica.sep+"Apre la bocca per sbadigliare ma si accorge di non potere emettere alcun suono.\n" +
                Grafica.sep+"Spaventato viene preso dal panico e per alcuni minuti prova in tutti i modi a parlare, a gridare, cantare ma senza risultato.\n" +
                Grafica.sep+"Poi torna lucido: si ricordò di aver sentito parlare di una \"Torre della Conoscenza\", in cima alla quale vi era un artefatto sacro in grado di esaudire un desiderio.\n" +
                Grafica.sep+"Con la speranza nel cuore e un aiutante al suo fianco, si mette alla ricerca della Torre e appena la trova, comincia la sua ascesa.\n");
        System.out.print(Grafica.sep+"Inserisci un carattere qualunque per andare avanti + ");
    }

    public void correctSave(){
        System.out.println(Grafica.sep + "Salvataggio avvenuto con successo");
        System.out.println(Grafica.sep + "Premi invio per riprendere il gioco");
        System.out.print(Grafica.sep + "+ ");
    }

    public void fineLvl(int livello, long penalty, int punteggio){
        System.out.println("\n" + Grafica.sep+"Hai finito il livello "+livello+" in "+ penalty +" secondi e con un punteggio complessivo di "+punteggio+" punti!");
    }

    public void resumeGame(){
        System.out.println(Grafica.sep+"Premi invio per riprendere il gioco");
        System.out.print(Grafica.sep+"+ ");
    }

    public void exit(int punteggio_totale){
        System.out.println("\n"+ Grafica.sep+"Sei uscito dal gioco con un punteggio di "+punteggio_totale);
    }

    public void victory(){
        Grafica.clearConsole();
        System.out.println(Grafica.Victory);
    }

    public void crediti(){
        System.out.println("\n"+ Grafica.sep+"Grazie per aver giocato a Tower of Trials!");
        System.out.println(Grafica.sep+"Sviluppato da: Arcuri Andrea, Palazzolo Gloria, Palmeri Giovanni");
    }

    public void printClassificaView(ProtagonistaModel pro, ClassificaController controller, ClassificaModel model) throws IOException{
        controller.aggiornaRecord(pro.getNome(), pro.getPunteggio_totale());
        controller.stampaClassifica();
        model.scriviClassifica();
    }
}
