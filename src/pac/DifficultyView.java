package pac;

public class DifficultyView {

    public void promptStatica(){
        System.out.println("\n"+ Grafica.sep+"Scegli 1 per difficolta statica, 2 per crescente:");
        System.out.print(Grafica.sep+">> ");
    }

    public void wrongInput(){
        System.out.println("\n"+ Grafica.sep+"Input sbagliato! Riprova:");
        System.out.print(Grafica.sep+">> ");
    }

    public void sceltaSicura(){
        System.out.println("\n"+ Grafica.sep+"Sei sicuro della tua scelta? [s/n]:");
        System.out.print(Grafica.sep+">> ");
    }

    public void promptAiutante(){
        System.out.println("\n"+ Grafica.sep+"Scegli un aiutante per facilitare il tuo viaggio:");
        System.out.println(Grafica.sep+"Scrivi 0 per andare avanti da solo");
        System.out.println(Grafica.sep+"Scrivi 1 per Boris il Cronistorico");
        System.out.println(Grafica.sep+"Scrivi 2 per Rendar il Linguista");
        System.out.println(Grafica.sep+"Scrivi 3 per Sanga il Matematico");
        System.out.print(Grafica.sep+">> ");
    }

    public void aiutoFacile(){
        System.out.println("\n"+ Grafica.sep+"Galgith il Saggio chiede di accompagnarti. Accetti la sua proposta? [s/n]");
        System.out.print(Grafica.sep+">> ");
    }

    public void staticaLivello(){
        System.out.println("\n"+ Grafica.sep+"Scegli 1 per facile, 2 media, 3 difficile:");
        System.out.print(Grafica.sep+">> ");
    }

    public void printData(String difficolta, boolean statica, int numPiani){
        System.out.println("Statica: " + statica);
        System.out.println("difficolta: " + difficolta);
        System.out.println("Numero piani: " + numPiani);
    }
}
