package pac;

public class MenuView {

    public void getName(){
        System.out.println("\n"+ Grafica.sep+"Dimmi il tuo nome: ");
        System.out.print(Grafica.sep+">> ");
    }

    public void pressToStart(){
        System.out.println("\n"+ Grafica.sep+"Premi invio per iniziare a giocare");
        System.out.print(Grafica.sep+"+ ");
    }

    public void nomeEsistente(){
        System.out.println("\n"+ Grafica.sep+"Esiste già un personaggio con questo nome, scegliene uno non utilizzato");
        System.out.print("\n"+ Grafica.sep+"+ ");
    }

    public void loadGameFile(){
        System.out.println("\n"+ Grafica.sep+"Inserire il nome del personaggio per caricare la partita\n"+ Grafica.sep+"Inserire il carattere \""+ Grafica.bold+"d"+ Grafica.resetText+"\" per entrare in modalità eliminazione\n"+ Grafica.sep+"Inserire il carattere \""+ Grafica.bold+"b"+ Grafica.resetText+"\" per tornare indietro: ");
        System.out.print(Grafica.sep+">> ");
    }

    public void chooseSave(){
        System.out.println("\n"+ Grafica.sep+"Scegli da quale salvataggio vuoi caricare la partita, 0 per tornare indietro");
        System.out.print(Grafica.sep+">> ");
    }

    public void emptyGameFile(){
        System.out.println("\n"+ Grafica.sep+"Non è presente alcun salvataggio");
        System.out.print(Grafica.sep+"+ ");
    }

    public void invalidGameFile(){
        System.out.println("\n"+ Grafica.sep+"\tSeleziona un salvataggio valido, premi invio per continuare");
        System.out.print(Grafica.sep+">> ");
    }

    public void deleteGameFile(){
        System.out.println("\n"+ Grafica.sep+"Inserire il nome del personaggio di cui vuoi eliminare i salvataggi o \"b\" per tornare indietro: ");
        System.out.print(Grafica.sep+">> ");
    }

    public void deleteDone(){
        System.out.println("\n"+ Grafica.sep+"Eliminazione completata, premi invio per continuare");
        System.out.print(Grafica.sep+"+ ");
    }

    public void invalidName(){
        System.out.println("\n"+ Grafica.sep+"Nome non valido, premi invio per continuare");
        System.out.print(Grafica.sep+"+ ");
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

    public void menu_1(){
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
    }

    public void clear(){
        Grafica.clearConsole();
    }

}
