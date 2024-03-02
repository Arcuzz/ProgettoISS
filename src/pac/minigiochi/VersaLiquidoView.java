package pac.minigiochi;

import pac.Grafica;

import java.io.Serializable;

public class VersaLiquidoView extends MinigiocoView implements Serializable {

    @Override
    public void initGraphic(String nome){
        System.out.println(Grafica.Minigame);
        System.out.println("\n"+Grafica.sep+ "Sono "+nome+" risolvi il mio minigioco");
        System.out.println(Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
    }

    @Override
    public void startGame(int ...val) {
        Grafica.clearConsole();
        System.out.println(Grafica.Minigame);
        System.out.println("\n\n" + Grafica.sep+"--- Dosa i bicchieri ---");
        System.out.println(Grafica.sep+"Davanti a te vedi tre bicchieri di "+val[0]+", "+val[1]+", "+val[2]+" litri rispettivamente");
        System.out.println(Grafica.sep+"Il primo è pieno d'acqua e gli altri due sono vuoti");
        System.out.println(Grafica.sep+"Il tuo obiettivo è spostare l'acqua tra i bicchieri in modo che il primo e il secondo contengano "+val[3]+" litri");
        System.out.println(Grafica.sep+"Versare acqua più del limite di un bicchiere semplicemente lo riempie fino all'orlo, senza alcuna perdita");
        System.out.println(Grafica.sep+"Puoi fare tutti gli spostamenti che vuoi, ma per la soluzione più rapida ne servono solo "+val[4]);
        System.out.println(Grafica.sep+"Buona fortuna e divertiti!");
    }

    public void stampaBicchieri(int ...val){
        System.out.println("\n"+Grafica.sep+"|       |");
        System.out.println(Grafica.sep+"|       |       |       |");
        System.out.println(Grafica.sep+"|   "+val[0]+"\t|       |       |       |       |");
        System.out.println(Grafica.sep+"|       |       |   "+val[1]+"   |       |       |");
        System.out.println(Grafica.sep+"|       |       |       |       |   "+val[2]+"   |");
        System.out.println(Grafica.sep+"|-------|       |-------|       |-------|");
        System.out.println(Grafica.sep+"    "+val[3]+"L              "+val[4]+"L              "+val[5]+"L");
        System.out.println(Grafica.sep+"Spostamenti fatti: "+val[6]);
    }

    public void prompt(){
        System.out.println("\n"+Grafica.sep+"Cosa vuoi fare? Digita 1,2,3 per travasare dal rispettivo bicchiere, 4 per resettare o 5 per uscire: ");
        System.out.print(Grafica.sep+">> ");
    }

    
    public void invalidInput(){
        System.out.println(Grafica.sep+"Input sbagliato! Riprova");
        System.out.print(Grafica.sep+"#: ");
    }

    public void exit(){
        System.out.println("\n" + Grafica.sep+"Sei uscito senza risolvere il minigioco, ritorno al movimento");
        System.out.print(Grafica.sep+"#: ");
    }

    public void empty(){
        System.out.println(Grafica.sep+"Il bicchiere che hai scelto è vuoto! Riprova");
        System.out.print(Grafica.sep+"#: ");
    }

    public void full(){
        System.out.println(Grafica.sep+"Il bicchiere che hai scelto è pieno! Riprova");
        System.out.print(Grafica.sep+"#: ");
    }

    public void choose(){
        System.out.println(Grafica.sep+"Scegli il bicchiere da riempire con 1,2,3:");
        System.out.print(Grafica.sep+">> ");
    }

    public void bicchiereDiPartenza(){
        System.out.println(Grafica.sep+"Hai scelto il bicchiere di partenza! Riprova");
        System.out.print(Grafica.sep+"#: ");
    }

    public void perfectVictory(){
        System.out.println(Grafica.sep+"Complimenti! Hai finito nel numero minimo di spostamenti possibili!");
        System.out.println("\n"+ Grafica.sep+"Premi invio per uscire");
        System.out.print(Grafica.sep+"#: ");
    }

    public void victory(int mosse){
        System.out.println("\n"+Grafica.sep+"Complimenti! Hai finito correttamente con "+mosse+" spostamenti!");
        System.out.println("\n"+ Grafica.sep+"Premi invio per uscire");
        System.out.print(Grafica.sep+"#: ");
    }
}
