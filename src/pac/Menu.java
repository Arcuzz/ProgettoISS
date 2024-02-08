package pac;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {
    public static final ArrayList<String> temi = new ArrayList<>();
    public GameCaretaker caretaker;
    private static final String path = "../../../saves/save.ser";
    public Menu(){

        this.caretaker = new GameCaretaker();
        temi.add("Geografia");
        temi.add("Storia");
        temi.add("Matematica");
        temi.add("Informatica");
        temi.add("Italiano");
        temi.add("Inglese");

        if (checkSaveFile()) this.caretaker.loadGameFile(path);
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ignored) {}
    }

    public void menu_0(){
        clearConsole();
        System.out.println("""
                ▄▄▄█████▓ ██░ ██ ▓█████    ▄▄▄█████▓ ▒█████   █     █░▓█████  ██▀███ \s
                ▓  ██▒ ▓▒▓██░ ██▒▓█   ▀    ▓  ██▒ ▓▒▒██▒  ██▒▓█░ █ ░█░▓█   ▀ ▓██ ▒ ██▒
                ▒ ▓██░ ▒░▒██▀▀██░▒███      ▒ ▓██░ ▒░▒██░  ██▒▒█░ █ ░█ ▒███   ▓██ ░▄█ ▒
                ░ ▓██▓ ░ ░▓█ ░██ ▒▓█  ▄    ░ ▓██▓ ░ ▒██   ██░░█░ █ ░█ ▒▓█  ▄ ▒██▀▀█▄ \s
                  ▒██▒ ░ ░▓█▒░██▓░▒████▒     ▒██▒ ░ ░ ████▓▒░░░██▒██▓ ░▒████▒░██▓ ▒██▒
                  ▒ ░░    ▒ ░░▒░▒░░ ▒░ ░     ▒ ░░   ░ ▒░▒░▒░ ░ ▓░▒ ▒  ░░ ▒░ ░░ ▒▓ ░▒▓░
                    ░     ▒ ░▒░ ░ ░ ░  ░       ░      ░ ▒ ▒░   ▒ ░ ░   ░ ░  ░  ░▒ ░ ▒░
                  ░       ░  ░░ ░   ░        ░      ░ ░ ░ ▒    ░   ░     ░     ░░   ░\s
                          ░  ░  ░   ░  ░                ░ ░      ░       ░  ░   ░    \s
                                                                                     \s""");
        System.out.println("1) Nuova partita \n2) Carica partita \n3) Esci");
    }

    public void display(Scanner scan) throws IOException {
        while (true) {
            menu_0();
            int input = Integer.parseInt(scan.nextLine());
            if (input == 1) {

                Difficulty diff = new Difficulty(scan);
                System.out.println("Dimmi il tuo nome: ");
                Protagonista pr = Protagonista.getDefaultInstance(scan.nextLine());
                pr.aiutante = diff.aiutante;

                Collections.shuffle(temi);
                Torre tor = Torre.getDefaultInstance(temi, diff, pr, this.caretaker);
                tor.setupTorre();
                tor.game(scan);

            } else if (input == 2) {

                if (checkSaveFile()) {
                    while (true) {
                        menu_1("");
                        System.out.println("Inserire il nome del personaggio per caricare la partita\nInserire il carattere \"d\" per entrare in modalità eliminazione\nInserire il carattere \"b\" per tornare indietro: ");
                        String nome = scan.nextLine();

                        if (nome.equals("d")){
                            menu_1("");
                            System.out.println("Inserire il nome del personaggio di cui vuoi eliminare i salvataggi o \"b\" per tornare indietro: ");
                            String to_del = scan.nextLine();
                            if (this.caretaker.check_save(to_del) && !to_del.equals("b")){
                                this.caretaker.removeSnapshot(to_del);
                                this.caretaker.saveGame(path);
                                System.out.println("Eliminazione completata, premi invio per continuare");
                                scan.nextLine();
                            }
                            else {
                                System.out.println("Nome non valido, premi invio per continuare");
                                scan.nextLine();
                            }

                        } else if (nome.equals("b")) {
                            break;

                        }else{
                            if (this.caretaker.check_save(nome)) {
                                menu_1(nome);
                                System.out.println("Scegli da quale salvataggio vuoi caricare la partita, 0 per tornare indietro");
                                String val = scan.nextLine();
                                if (!val.equals("0")) {
                                    GameMemento memento = caretaker.getSnapshot(nome, Integer.parseInt(val) - 1);
                                    if (memento != null) {
                                        Protagonista pro = Protagonista.getSavedInstance(memento.getNome(), memento.getPiano(), memento.getPos(), memento.getVisited(), memento.getAiutante(), memento.getDom(), memento.getMini(), memento.getTotal_points());
                                        Torre tor = Torre.getSavedInstance(memento.getTemi(), memento.getDiff(), pro, memento.getPiano(), this.caretaker, memento.getTime());
                                        tor.game(scan);
                                    } else {
                                        System.out.println("Seleziona un salvataggio valido, premi invio per continuare");
                                        scan.nextLine();
                                    }
                                }
                            } else {
                                System.out.println("Nome non valido, premi invio per continuare");
                                scan.nextLine();
                            }
                        }
                    }
                } else {
                    System.out.println("Non è presente alcun salvataggio");
                    scan.nextLine();
                }
            }else break;
        }
    }

    public void menu_1(String nome){
        clearConsole();
        System.out.println("""
                      :::        ::::::::      :::     :::::::::   \s
                     :+:       :+:    :+:   :+: :+:   :+:    :+:   \s
                    +:+       +:+    +:+  +:+   +:+  +:+    +:+    \s
                   +#+       +#+    +:+ +#++:++#++: +#+    +:+     \s
                  +#+       +#+    +#+ +#+     +#+ +#+    +#+      \s
                 #+#       #+#    #+# #+#     #+# #+#    #+#       \s
                ########## ########  ###     ### #########         \s
                      ::::::::      :::       :::   :::   ::::::::::
                    :+:    :+:   :+: :+:    :+:+: :+:+:  :+:       \s
                   +:+         +:+   +:+  +:+ +:+:+ +:+ +:+        \s
                  :#:        +#++:++#++: +#+  +:+  +#+ +#++:++#    \s
                 +#+   +#+# +#+     +#+ +#+       +#+ +#+          \s
                #+#    #+# #+#     #+# #+#       #+# #+#           \s
                ########  ###     ### ###       ### ##########     \s

                """);
        if (nome.isEmpty()) this.caretaker.printPerSnapshots();
        else this.caretaker.printSnapshots(nome);
    }
    public boolean checkSaveFile () {
        File save = new File(path);
        return save.isFile();
    }
}
