package pac;

public class ProvaView {

    public void mostraDomanda(String domanda){
        System.out.println(Grafica.sep + domanda);
    }

    public void promptRisposta(){
        System.out.println("\n"+Grafica.sep+"Risposta: ");
        System.out.print(Grafica.sep+">> ");
    }

    public void rispostaEsatta(int errori){
        System.out.println(Grafica.sep+"Risposta esatta con "+errori+" errori! Vai avanti");
    }

    public void uscitaProva(){
        System.out.println(Grafica.sep+"Sei uscito senza rispondere correttamente, ritorno al movimento");
    }

    public void mostraHelp(String risposta){
        System.out.println(Grafica.sep+"LA RISPOSTA E': "+risposta);
    }

    public void rispostaSbagliata(){
        System.out.println(Grafica.sep+"Risposta sbagliata! Riprova o scrivi \"exit\" per uscire\n");
    }

    public void daiIndizio(String indizio){
        System.out.println(Grafica.sep+"INDIZIO: "+indizio);
    }

    // Questa viene chiamata da Questions.java
    public void summary(String domanda, String risposta, int ranking){
        System.out.println( "\nDomanda: " + domanda +
                "\nRisposta: " + risposta +
                "\nDifficoltà: " + ranking);
    }
}
