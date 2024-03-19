package pac;

import pac.minigiochi.*;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        /*MemoryModel mini = new MemoryModel();
        mini.rank = 2;
        mini.inizializza();
        MemoryController co = new MemoryController(mini, new MemoryView());
        co.play(scan, "ASD");*/
        MenuModel menu = new MenuModel();
        MenuController menuController = new MenuController(menu, new MenuView());
        menuController.display(scan);
        scan.close();
    }

}
