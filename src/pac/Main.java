package pac;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        MenuModel menu = new MenuModel();
        MenuController menuController = new MenuController(menu, new MenuView());
        menuController.display(scan);
        scan.close();
    }

}
