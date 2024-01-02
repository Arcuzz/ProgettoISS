package pac;
import java.util.*;
import java.io.*;
public class Questions {
    public ArrayList<Prova> domande = new ArrayList<>();
    public int piano;
    public String tema;
    public String difficolta;
    public int rank;
    public int numDomande;
    public Questions(){}
    public Questions(int livello, String tema, String diff) throws FileNotFoundException {
        this.piano = livello;
        this.tema = tema;
        this.difficolta = diff;
        this.numDomande = livello+livello+1;
        setRank();
        int totalQuestions = countTotalQuestions();
        System.out.println("\nScelto " + this.numDomande + " domande su " + totalQuestions +" di "+tema+" per il piano " + livello);
        addQuestions();
        //printProve();
    }

    public void addQuestions() throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\IdeaProjects\\Gioco\\src\\"+this.tema+"\\enigmi"+this.rank+".txt");
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String dom = scan.nextLine();
            String ris = scan.nextLine();
            String hint = scan.nextLine();
            domande.add(new Prova(dom,ris,hint,0));
        }
        scan.close();
        Collections.shuffle(domande);
        domande.removeIf(n -> (domande.indexOf(n) >= numDomande)); //solo con > ne mette uno in più
    }

    public int countTotalQuestions() throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\IdeaProjects\\Gioco\\src\\"+this.tema+"\\enigmi"+this.rank+".txt");
        Scanner scan = new Scanner(file);
        int count = 0;
        while(scan.hasNextLine()) {
            count++;
            scan.nextLine();
        }
        scan.close();
        return count/3;
    }

    public void printProve(){
        for(Prova item : domande)
            item.summary();
    }
    public void setRank(){
        switch (this.difficolta) {
            case "facile" -> this.rank = 1;
            case "media" -> this.rank = 2;
            case "difficile" -> this.rank = 3;
            case "crescente" -> this.rank = this.piano;
        }
    }
}
