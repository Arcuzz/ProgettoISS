package pac;

public class Aiutante implements Serializable {
    public int id;  //1 aiuta sempre, 2,3,4 aiuta per due materie ciascuno, 5 non aiuta
    public String nome, titolo;
    public boolean aiuto = false;
    public Aiutante(int id){
        this.id = id;
        switch (id) {
            case 1 -> {
                this.nome = "Galgith";
                this.titolo = " il Saggio";
            }
            case 2 -> {
                this.nome = "Boris";
                this.titolo = " il Cronistorico";
            }
            case 3 -> {
                this.nome = "Rendar";
                this.titolo = " il Linguista";
            }
            case 4 -> {
                this.nome = "Sanga";
                this.titolo = " il Matematico";
            }
        }
    }
    public void attivaAiuto(String tema){
        switch (tema){
            case "Storia","Geografia": if(this.id==2 || this.id==1) this.aiuto = true; break;
            case "Italiano","Inglese": if(this.id==3 || this.id==1) this.aiuto = true; break;
            case "Matematica","Informatica": if(this.id==4 || this.id==1) this.aiuto = true; break;
            default: this.aiuto = false; break;
        }
    }
}
