package pac;
import pac.minigiochi.*;
import pac.stanze.Npc;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();
        menu.display(scan);
        scan.close();
    }

}
