import java.util.*;
import java.io.*;
public class Questions {
    public ArrayList<Prova> domande = new ArrayList<>();
    public Questions(){}
    public Questions(int numOfQuestions, int floor) throws FileNotFoundException {
        int totalQuestions = countTotalQuestions(floor);
        System.out.println("\nScelto " + numOfQuestions + " domande su " + totalQuestions + " per il piano " + floor);
        addQuestions(numOfQuestions ,floor);
        //printProve();
    }

    public void addQuestions(int number, int floor) throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\IdeaProjects\\Gioco\\src\\Matematica\\enigmi"+String.valueOf(floor)+".txt");
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String dom = scan.nextLine();
            String ris = scan.nextLine();
            String hint = scan.nextLine();
            domande.add(new Prova(dom,ris,hint,0));
        }
        Collections.shuffle(domande);
        domande.removeIf(n -> (domande.indexOf(n) >= number)); //solo con > ne mette uno in più
    }

    public int countTotalQuestions(int floor) throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\IdeaProjects\\Gioco\\src\\Matematica\\enigmi"+String.valueOf(floor)+".txt");
        Scanner scan = new Scanner(file);
        int count = 0;
        while(scan.hasNextLine()) {
            count++;
            scan.nextLine();
        }
        return count/2;
    }

    public void printProve(){
        for(Prova item : domande)
            item.summary();
    }

}
