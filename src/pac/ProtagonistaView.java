package pac;

import pac.stanze.Stanza;

public class ProtagonistaView {

    public void riepilogo(String nome, String difficolta, int Punteggio_totale, int livello, String tema, int dom_r, int errori_dom, int mini_risolti, int Punti_mini, int Punti_dom){
        System.out.println("\n"+ Grafica.sep+"Menù riepilogo");
        System.out.println(Grafica.sep+"Nome: "+nome);
        System.out.println(Grafica.sep+"Punteggio: "+ Punteggio_totale);
        System.out.println(Grafica.sep+"Difficoltà: "+difficolta);
        System.out.println(Grafica.sep+"Piano corrente: "+livello);
        System.out.println(Grafica.sep+"Tema corrente: "+tema);
        System.out.println(Grafica.sep+"Domande risposte correttamente: "+dom_r);
        System.out.println(Grafica.sep+"Errori commessi: "+errori_dom);
        System.out.println(Grafica.sep+"Punti ottenuti dalle domande: "+Punti_dom);
        System.out.println(Grafica.sep+"Minigiochi risolti: "+mini_risolti);
        System.out.println(Grafica.sep+"Punti ottenuti dai minigiochi: "+ Punti_mini);
    }

    public void saveGame(){
        System.out.println(Grafica.sep+"Desideri salvare la partita? [s/n]");
        System.out.print("\n"+ Grafica.sep+">> ");
    }

    public void score(int p){
        System.out.println(Grafica.sep+"Hai ottenuto "+p+" punti!");
    }

    public void resumeGame(){
        System.out.println(Grafica.sep+"Premi invio per riprendere il gioco");
        System.out.print(Grafica.sep+"+ ");
    }

    public void startDom(){
        Grafica.clearConsole();
        System.out.println(Grafica.Dom);
    }

    public void nextFloor(){
        System.out.println("\n"+ Grafica.sep+"Hai finito le domande del piano, vuoi andare al succesivo? [s/n]");
        System.out.print("\n"+ Grafica.sep+">> ");
    }

    public void chooseDirection(){
        System.out.println("\n"+ Grafica.sep+"In che direzione vuoi andare?");
        System.out.println("\n"+ Grafica.sep+ Grafica.bold +"d"+ Grafica.resetText+" per destra, "+ Grafica.bold + "a"+ Grafica.resetText+" per sinistra, "+ Grafica.bold+"w"+ Grafica.resetText+" per sopra, "+ Grafica.bold+"s"+ Grafica.resetText+" per sotto, "+ Grafica.bold+"r"+ Grafica.resetText+" per riepilogo: ");
        System.out.print("\n"+ Grafica.sep+">> ");
    }

    public void invalidDirection(){
        System.out.println("\n"+ Grafica.sep+"Ouch! Hai sbattuto contro un muro, ti consiglio di utilizzare le porte!");
        System.out.println(Grafica.sep+"Premi invio per riprendere");
        System.out.print(Grafica.sep+"+ ");
    }

    public void helpHeader(){
        System.out.println(Grafica.sep+"--- SEZIONE DI AIUTO ---");
        System.out.println(Grafica.sep+"Digita 1 per una spiegazione delle meccaniche di gioco");
        System.out.println(Grafica.sep+"Digita 2 per andare immediatamente al prossimo piano");
        System.out.print(Grafica.sep+">> ");
    }

    public void invalidInput(){
        System.out.println(Grafica.sep + "Input sbagliato");
    }

    public void helpBody(){
        System.out.print("\n" + Grafica.sep+"Lo scopo del gioco è completare diversi livelli (da 3 a 5)\n" +
                Grafica.sep+"Ogni livello contiene un certo numero di domande a cui rispondere\n" +
                Grafica.sep+"Alcune domande sono a risposta chiusa, e dovrai digitare solo la lettera della risposta che credi corretta\n" +
                Grafica.sep+"Altre domande sono a risposta aperta, e ti verrà suggerito il formato della risposta se è più di una parola o un numero\n" +
                Grafica.sep+"Rispondere \"exit\" ti fa tornare al movimento senza rispondere alla domanda, che ti verrà riproposta al rientro in quella stanza\n" +
                Grafica.sep+"IMPORTANTE: rispondere \"help\" a una domanda ti darà la risposta esatta da inserire (viene detto solo qui)\n" +
                Grafica.sep+"Dopo aver risposto a tutte le domande ti verrà chiesto se vuoi andare al piano successivo\n" +
                Grafica.sep+"Se rispondi no, puoi comunque avanzare tornando alla stanza di partenza (\"S\")\n" +
                Grafica.sep+"Ogni livello contiene anche un minigioco, sfide più complesse e danno più punti, ma totalmente opzionali\n" +
                Grafica.sep+"I punti dati da domande e minigiochi non sono necessari, esistono per pura competizione (alla fine del gioco viene stampata una classifica)\n" +
                Grafica.sep+"Durante il movimento muoverti digitando W|A|S|D, stampare un riepilogo delle statistiche con R o questa sezione di aiuto con H\n\n");
    }

    public void vision(int livello, Stanza[][] visited, int x, int y) {
        Grafica.clearConsole();

        System.out.println(Grafica.ASCII_lvl(livello));
        int rows = visited.length;
        int cols = visited[0].length;

        // Prima riga

        System.out.println("\n");
        System.out.print(Grafica.sep+"\t╔═══");

        for (int i = 1; i < cols; i++) {
            System.out.print("╦═══");
        }

        System.out.println("╗");

        // Contenuto della mappa
        for (int j = 0; j < rows; j++) {
            System.out.print(Grafica.sep+"\t");
            for (int i = 0; i < cols; i++) {
                if (visited[j][i] != null && visited[j][i].id != '#') {
                    if (x == j && y == i)
                        System.out.print("║" + " " + Grafica.green + visited[j][i].id + " " + Grafica.resetText);
                    else
                        System.out.print("║" + " " + visited[j][i].id + " ");
                } else
                    System.out.print("║   ");
            }
            System.out.println("║");
            if (j < rows - 1) {
                System.out.print(Grafica.sep+"\t╠═══");
                for (int i = 0; i < cols - 1; i++) {
                    System.out.print("╬═══");
                }
                System.out.println("╣");
            }
        }

        // Ultima riga
        System.out.print(Grafica.sep+"\t╚═══");
        for (int i = 0; i < cols - 1; i++) {
            System.out.print("╩═══");
        }
        System.out.println("╝");
    }

}
