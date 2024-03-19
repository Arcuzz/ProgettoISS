package pac;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ClassificaModel implements Serializable{

    public File file = new File("local"+File.separator+"Classifica.txt");
    public ArrayList<RecordPersona> rp = new ArrayList<>();
    public int righe;
    
    public ClassificaModel() throws FileNotFoundException {

        try {
            Path filePath = Paths.get(file.toURI());
            Path parentDir = filePath.getParent();

            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.rp = new ArrayList<>();
        this.righe = 0;
        this.caricaClassifica();
    }

    private void caricaClassifica() throws FileNotFoundException {
        if (!this.file.exists()) return;

        Scanner scan = new Scanner(this.file);
        while (scan.hasNextLine() && this.righe <= 10) {
            String nome = scan.nextLine();
            int punteggio = Integer.parseInt(scan.nextLine());
            this.rp.add(new RecordPersona(nome, punteggio));
            this.righe += 2;
        }
        scan.close();
    }
    
    public void addRecord(RecordPersona rec){
        this.rp.add(rec);
    }

    public ArrayList<RecordPersona> getRp(){
        return this.rp;
    }

    public void swap(int i, int j){
        Collections.swap(this.rp,i,j);
    }

    public void scriviClassifica() throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (RecordPersona recordPersona : this.getRp())
            fw.write(recordPersona.nome + "\n" + recordPersona.punteggio + "\n");
        fw.close();
    }

    public void removeRp(int i){
        this.getRp().remove(i);
    }
}


