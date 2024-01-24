package pac;

import java.io.*;
import java.util.*;

public class Classifica {
    public File file = new File("C:\\Users\\ASUS\\Desktop\\Gioco\\src\\Classifica.txt");
    public ArrayList<RecordPersona> rp = new ArrayList<>();
    public int righe = 0;
    public Classifica() throws FileNotFoundException {
        Scanner scan = new Scanner(this.file);
        while(scan.hasNextLine() && this.righe<=10){
            String nome = scan.nextLine();
            int punteggio = Integer.parseInt(scan.nextLine());
            this.rp.add(new RecordPersona(nome,punteggio));
            this.righe+=2;
        }
        scan.close();
    }
    public void aggiornaRecord(String nome, int punteggio){
        this.rp.add(new RecordPersona(nome,punteggio));
        for(int i=this.rp.size()-1; i>0; i--){
            if(this.rp.get(i).punteggio>this.rp.get(i-1).punteggio)
                Collections.swap(this.rp,i,i-1);
            if(this.rp.get(i).punteggio==this.rp.get(i-1).punteggio)
                this.rp.get(i-1).nome = this.rp.get(i-1).nome + ", " + this.rp.get(i).nome;
        }
        if(this.rp.size()>5)
            this.rp.remove(this.rp.size()-1);
    }
    public void scriviClassifica() throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (RecordPersona recordPersona : this.rp)
            fw.write(recordPersona.nome + "\n" + recordPersona.punteggio + "\n");
        fw.close();
    }
    public void stampaClassifica(){
        System.out.println("Classifica: ");
        for(int i=0; i<this.rp.size(); i++)
            System.out.println((i+1)+"o: "+this.rp.get(i).nome+" Punti: "+this.rp.get(i).punteggio);
    }
}

class RecordPersona{
    public String nome;
    public int punteggio;
    public RecordPersona(String n, int p){
        this.nome = n;
        this.punteggio = p;
    }
}