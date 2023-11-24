import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Floor fl = new Floor(0);
        //Questions que1 = new Questions(3,1);
        //Questions que2 = new Questions(3,2);

        String[] t = {"Logica","Matematica", "Informatica", "Lingua", "Conoscenze generali", "Storia", "Geografia", "Letteratura", "Prove interattive", "Storia della scoperta"};
        ArrayList<String> temi = new ArrayList<>();
        Collections.addAll(temi, t);
        Collections.shuffle(temi);

        int totalFloors = 2;
        for(int i=1; i<=totalFloors; i++){
            Floor fl = new Floor(i);
            fl.moveForward();
            if(i!=totalFloors)
                System.out.println("Hai finito il piano! Vai al prossimo!");
            else System.out.println("Hai finito l'ultimo piano! Congratulazioni!");
        }
        //Floor primo = new Floor(1);
        //primo.moveForward();
        //Floor secondo = new Floor(2);
    }
}