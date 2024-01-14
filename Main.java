package pac;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Difficulty diff = new Difficulty(scan);
        System.out.println("Dimmi il tuo nome: ");
        Protagonista pr = Protagonista.getInstance(scan.nextLine());
        pr.aiutante = diff.aiutante;
        String[] temi = {"Geografia","Storia","Matematica", "Informatica", "Italiano","Inglese"};
        Torre tor = Torre.getInstance(temi, diff, pr);
        tor.game(scan);
        scan.close();
    }
}
