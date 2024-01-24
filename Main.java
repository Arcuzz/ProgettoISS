package pac;
import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();
        menu.display(scan);
        scan.close();
    }
}
