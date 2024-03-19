package pac;
import java.util.*;
import java.io.*;

public class Questions implements Serializable{
    public ArrayList<ProvaModel> domande = new ArrayList<>();
    public int piano, rank, numDomande;
    public String tema, difficolta;
    private final InputStream is;
    
    public Questions(int livello, String tema, String diff) throws FileNotFoundException {
        this.piano = livello;
        this.tema = tema;
        this.difficolta = diff;
        this.numDomande = livello+livello+1;
        setRank();
        //int totalQuestions = countTotalQuestions();
        // Path per jar
        String path = "resources/Subjects/" + this.tema + "/enigmi" + this.rank + ".txt";
        // Path per IntelliJ
        //String path = "resources"+ File.separator +"Subjects" + File.separator + this.tema + File.separator + "enigmi" + this.rank + ".txt";
        this.is = Questions.class.getClassLoader().getResourceAsStream(path);
        addQuestions();
    }

    public void addQuestions(){
        if (this.is != null){
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))){
                String dom;
                while ((dom = br.readLine()) != null){
                    String ris = br.readLine();
                    String hint = br.readLine();
                    if(dom.contains("\\n")) dom = dom.replace("\\n","\n"+ Grafica.sep);
                    domande.add(new ProvaModel(dom,ris,hint));
                }
                Collections.shuffle(this.domande);
                this.domande.removeIf(n -> (this.domande.indexOf(n) >= this.numDomande));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //non necessario

    public int countTotalQuestions() throws FileNotFoundException {

        int count = 0;
        if (this.is != null){
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))){
                while (br.readLine() != null) count++;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return count/3;
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
