package pac;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {
    public static final ArrayList<String> temi = new ArrayList<String>();
    public GameCaretaker caretaker;
    private GameMemento memento;
    private static final String path = "saves/save.ser";
    public Menu(){

        this.caretaker = new GameCaretaker();
        temi.add("Geografia");
        temi.add("Storia");
        temi.add("Matematica");
        temi.add("Informatica");
        temi.add("Italiano");
        temi.add("Inglese");
    }
    public void display(Scanner scan) throws IOException{
        System.out.println("▄▄▄█████▓ ██░ ██ ▓█████    ▄▄▄█████▓ ▒█████   █     █░▓█████  ██▀███  \n" +
                "▓  ██▒ ▓▒▓██░ ██▒▓█   ▀    ▓  ██▒ ▓▒▒██▒  ██▒▓█░ █ ░█░▓█   ▀ ▓██ ▒ ██▒\n" +
                "▒ ▓██░ ▒░▒██▀▀██░▒███      ▒ ▓██░ ▒░▒██░  ██▒▒█░ █ ░█ ▒███   ▓██ ░▄█ ▒\n" +
                "░ ▓██▓ ░ ░▓█ ░██ ▒▓█  ▄    ░ ▓██▓ ░ ▒██   ██░░█░ █ ░█ ▒▓█  ▄ ▒██▀▀█▄  \n" +
                "  ▒██▒ ░ ░▓█▒░██▓░▒████▒     ▒██▒ ░ ░ ████▓▒░░░██▒██▓ ░▒████▒░██▓ ▒██▒\n" +
                "  ▒ ░░    ▒ ░░▒░▒░░ ▒░ ░     ▒ ░░   ░ ▒░▒░▒░ ░ ▓░▒ ▒  ░░ ▒░ ░░ ▒▓ ░▒▓░\n" +
                "    ░     ▒ ░▒░ ░ ░ ░  ░       ░      ░ ▒ ▒░   ▒ ░ ░   ░ ░  ░  ░▒ ░ ▒░\n" +
                "  ░       ░  ░░ ░   ░        ░      ░ ░ ░ ▒    ░   ░     ░     ░░   ░ \n" +
                "          ░  ░  ░   ░  ░                ░ ░      ░       ░  ░   ░     \n" +
                "                                                                      ");
        System.out.println("1) Nuova partita \n2) Carica partita \n3) Esci");
        int input = Integer.parseInt(scan.nextLine());

        if(input == 1){

            if (checkSave()) this.caretaker.loadGame(path);
            Difficulty diff = new Difficulty(scan);
            System.out.println("Dimmi il tuo nome: ");
            Protagonista pr = Protagonista.getDefaultInstance(scan.nextLine());
            pr.aiutante = diff.aiutante;

            Collections.shuffle(temi);
            Torre tor = Torre.getDefaultInstance(temi, diff, pr, this.caretaker);
            tor.setupTorre();
            tor.game(scan);

        } else if (input == 2) {

            if(checkSave()){
                this.caretaker.loadGame(path);
                this.caretaker.printPerSnapshots();
                System.out.println("Seleziona un personaggio: ");
                String nome = scan.nextLine();
                this.caretaker.printSnapshots(nome);
                System.out.println("Scegli da quale salvataggio vuoi caricare la partita");
                this.memento = caretaker.getSnapshot(nome,Integer.parseInt(scan.nextLine())-1);
                Protagonista pro = Protagonista.getSavedInstance(memento.getNome(), memento.getPiano(), memento.getPos(), memento.getVisited(), memento.getAiutante(), memento.getDom(), memento.getMini());
                Torre tor = Torre.getSavedInstance(memento.getTemi(), memento.getDiff(), pro, memento.getPiano(), this.caretaker);
                tor.game(scan);

            }else{

                System.out.println("Non è presente alcun salvataggio");

            }
        }
    }
    public boolean checkSave(){
        File save = new File(path);
        return save.isFile();
    }

}
