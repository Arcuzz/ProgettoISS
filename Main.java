package pac;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Protagonista pr = Protagonista.getIstance("Giovanni");
        String[] tem = {"Logica","Matematica", "Informatica", "Italiano", "Inglese"};
        Difficulty diff = new Difficulty(scan);
        Torre tor = new Torre(tem, diff, pr);
        tor.game(scan);
        scan.close();
    }
}
