package pac;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {
    public static final ArrayList<String> temi = new ArrayList<>();
    public GameCaretaker caretaker;
    private static final String path = "local/saves/save.ser";
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


    public void menu_0(){
        Grafica.clearConsole();
        System.out.println("""
                \n \n
                """ + Grafica.sep + """
                ▄▄▄█████▓ ██░ ██ ▓█████    ▄▄▄█████▓ ▒█████   █     █░▓█████  ██▀███
                """ + Grafica.sep + """
                ▓  ██▒ ▓▒▓██░ ██▒▓█   ▀    ▓  ██▒ ▓▒▒██▒  ██▒▓█░ █ ░█░▓█   ▀ ▓██ ▒ ██▒
                """ + Grafica.sep + """
                ▒ ▓██░ ▒░▒██▀▀██░▒███      ▒ ▓██░ ▒░▒██░  ██▒▒█░ █ ░█ ▒███   ▓██ ░▄█ ▒
                """ + Grafica.sep + """
                ░ ▓██▓ ░ ░▓█ ░██ ▒▓█  ▄    ░ ▓██▓ ░ ▒██   ██░░█░ █ ░█ ▒▓█  ▄ ▒██▀▀█▄
                """ + Grafica.sep + """
                  ▒██▒ ░ ░▓█▒░██▓░▒████▒     ▒██▒ ░ ░ ████▓▒░░░██▒██▓ ░▒████▒░██▓ ▒██▒
                """ + Grafica.sep + """
                  ▒ ░░    ▒ ░░▒░▒░░ ▒░ ░     ▒ ░░   ░ ▒░▒░▒░ ░ ▓░▒ ▒  ░░ ▒░ ░░ ▒▓ ░▒▓░
                """ + Grafica.sep + """
                    ░     ▒ ░▒░ ░ ░ ░  ░       ░      ░ ▒ ▒░   ▒ ░ ░   ░ ░  ░  ░▒ ░ ▒░
                """ + Grafica.sep + """
                  ░       ░  ░░ ░   ░        ░      ░ ░ ░ ▒    ░   ░     ░     ░░   ░
                """ + Grafica.sep + """
                          ░  ░  ░   ░  ░                ░ ░      ░       ░  ░   ░
                """ + Grafica.sep + """
                                                                                     
                                                                                    """);

        System.out.println(Grafica.bold+"\n"+ Grafica.sep+"\t1) Nuova partita \t2) Carica partita \t3) Esci" + Grafica.resetText);
        System.out.print("\n"+ Grafica.sep+">> ");
    }

    public void display(Scanner scan) throws IOException {
        while (true) {
            menu_0();

            Protagonista.resetInstance();
            Torre.resetInstance();

            int input = Integer.parseInt(scan.nextLine());
            if (input == 1) {

                Difficulty diff = new Difficulty(scan);
                System.out.println("\n"+ Grafica.sep+"Dimmi il tuo nome: ");
                System.out.print(Grafica.sep+">> ");
                String nome = scan.nextLine();
                Protagonista pr;
                if (!this.caretaker.check_duplicate_name(nome)){
                    pr = Protagonista.getDefaultInstance(nome);
                    pr.aiutante = diff.aiutante;
                    Collections.shuffle(temi);
                    Torre tor = Torre.getDefaultInstance(temi, diff, pr, this.caretaker);
                    System.out.print("\n"+ Grafica.sep+"Premi invio per iniziare a giocare + ");
                    scan.nextLine();

                    tor.setupTorre();
                    tor.game(scan);
                }else{
                    System.out.println("\n"+ Grafica.sep+"Esiste già un personaggio con questo nome, scegliene uno non utilizzato");
                    System.out.print("\n"+ Grafica.sep+"+ ");
                    scan.nextLine();
                }

            } else if (input == 2) {

                if (checkSaveFile()) {
                    while (true) {
                        menu_1("");
                        System.out.println("\n"+ Grafica.sep+"Inserire il nome del personaggio per caricare la partita\n"+ Grafica.sep+"Inserire il carattere \""+ Grafica.bold+"d"+ Grafica.resetText+"\" per entrare in modalità eliminazione\n"+ Grafica.sep+"Inserire il carattere \""+ Grafica.bold+"b"+ Grafica.resetText+"\" per tornare indietro: ");
                        System.out.print(Grafica.sep+">> ");
                        String nome = scan.nextLine();

                        if (nome.equals("d")){
                            menu_1("");
                            System.out.println("\n"+ Grafica.sep+"Inserire il nome del personaggio di cui vuoi eliminare i salvataggi o \"b\" per tornare indietro: ");
                            System.out.print(Grafica.sep+">> ");
                            String to_del = scan.nextLine();
                            if (this.caretaker.check_save(to_del) && !to_del.equals("b")){
                                this.caretaker.removeSnapshot(to_del);
                                this.caretaker.saveGame(path);
                                System.out.println("\n"+ Grafica.sep+"Eliminazione completata, premi invio per continuare");
                                scan.nextLine();
                            }
                            else if (!to_del.equals("b")) {
                                System.out.println("\n"+ Grafica.sep+"Nome non valido, premi invio per continuare");
                                scan.nextLine();
                            }

                        } else if (nome.equals("b")) {
                            break;

                        }else{
                            if (this.caretaker.check_save(nome)) {
                                menu_1(nome);
                                System.out.println("\n"+ Grafica.sep+"Scegli da quale salvataggio vuoi caricare la partita, 0 per tornare indietro");
                                System.out.print(Grafica.sep+">> ");
                                String val = scan.nextLine();
                                if (!val.equals("0")) {
                                    GameMemento memento = caretaker.getSnapshot(nome, Integer.parseInt(val) - 1);
                                    if (memento != null) {
                                        Protagonista pro = Protagonista.getSavedInstance(memento.getNome(), memento.getPiano(), memento.getPos(), memento.getVisited(), memento.getAiutante(), memento.getDom(), memento.getMini(), memento.getTotal_points());
                                        Torre tor = Torre.getSavedInstance(memento.getTemi(), memento.getDiff(), pro, memento.getPiano(), this.caretaker, memento.getTime());
                                        tor.game(scan);
                                        break;
                                    } else {
                                        System.out.println("\n"+ Grafica.sep+"\tSeleziona un salvataggio valido, premi invio per continuare");
                                        System.out.print(Grafica.sep+">> ");
                                        scan.nextLine();
                                    }
                                }
                            } else {
                                System.out.println("\n"+ Grafica.sep+"Nome non valido, premi invio per continuare");
                                System.out.print(Grafica.sep+"+ ");
                                scan.nextLine();
                            }
                        }
                    }
                } else {
                    System.out.println("\n"+ Grafica.sep+"Non è presente alcun salvataggio");
                    System.out.print(Grafica.sep+"+ ");
                    scan.nextLine();
                }
            }else break;
        }
        Grafica.clearConsole();
    }

    public void menu_1(String nome){
        Grafica.clearConsole();
        System.out.println("""
                \n \n
                """ + Grafica.sep + """
                      :::        ::::::::      :::     :::::::::   \s
                """ + Grafica.sep + """
                     :+:       :+:    :+:   :+: :+:   :+:    :+:   \s
                """ + Grafica.sep + """     
                    +:+       +:+    +:+  +:+   +:+  +:+    +:+    \s
                """ + Grafica.sep + """    
                   +#+       +#+    +:+ +#++:++#++: +#+    +:+     \s
                """ + Grafica.sep + """   
                  +#+       +#+    +#+ +#+     +#+ +#+    +#+      \s
                """ + Grafica.sep + """  
                 #+#       #+#    #+# #+#     #+# #+#    #+#       \s
                """ + Grafica.sep + """ 
                ########## ########  ###     ### #########         \s
                """ + Grafica.sep + """
                      ::::::::      :::       :::   :::   ::::::::::
                """ + Grafica.sep + """      
                    :+:    :+:   :+: :+:    :+:+: :+:+:  :+:       \s
                """ + Grafica.sep + """    
                   +:+         +:+   +:+  +:+ +:+:+ +:+ +:+        \s
                """ + Grafica.sep + """   
                  :#:        +#++:++#++: +#+  +:+  +#+ +#++:++#    \s
                """ + Grafica.sep + """  
                 +#+   +#+# +#+     +#+ +#+       +#+ +#+          \s
                """ + Grafica.sep + """ 
                #+#    #+# #+#     #+# #+#       #+# #+#           \s
                """ + Grafica.sep + """
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
