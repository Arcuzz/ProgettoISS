package pac;
import java.util.*;
import java.io.*;
public class Questions {
    public ArrayList<Prova> domande = new ArrayList<>();
    public int piano, rank, numDomande;
    public String tema, difficolta;
    
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
            domande.add(new Prova(dom,ris,hint,this.rank));
        }
        scan.close();
        Collections.shuffle(this.domande);
        this.domande.removeIf(n -> (this.domande.indexOf(n) >= this.numDomande)); //solo con > ne mette uno in più
    }

    //non necessario
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
        for(Prova item : this.domande)
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
