package pac;

import java.util.Scanner;

public abstract class Minigiochi {
    public abstract void startGame();
    public abstract void play();
}

class Impiccato extends Minigiochi{
    private String secret;
    private StringBuilder guessed;
    private int remainingAttempts;
    private String difficoltà;

    public Impiccato(String secret, int maxAttempts, String difficoltà){
        this.secret = secret;
        this.remainingAttempts = maxAttempts;
        this.difficoltà = difficoltà;
        inizializza();
    }

    private void inizializza(){
        this.guessed = new StringBuilder(this.secret.length());
        if (this.difficoltà.equals("facile")){
            this.guessed.insert(0, this.secret.charAt(0));
            for (int i = 1; i < this.secret.length()-1; i++) this.guessed.append('_');
            this.guessed.insert(this.guessed.length(), this.secret.charAt(this.secret.length()-1));
        }else if(this.difficoltà.equals("media")){
            this.guessed.insert(0, this.secret.charAt(0));
            for (int i = 1; i < this.secret.length(); i++) this.guessed.append('_');
        }else for (int i = 0; i < this.secret.length(); i++) this.guessed.append('_');
    }

    @Override
    public void startGame(){
        System.out.println("--- Gioco dell'impiccato ---");
    }

    @Override
    public void play(){
        Scanner sca = new Scanner(System.in);
        String in;
        while (this.remainingAttempts > 0 && !this.secret.equalsIgnoreCase(guessed.toString())) {
            System.out.println("Parola da indovinare:");
            System.out.println(this.guessed);
            System.out.println("Hai a disposizione " + this.remainingAttempts + " tentativi");
            System.out.println("");
            in = sca.nextLine();
            if(in.length() > 1){
                if (in.equalsIgnoreCase(this.secret)){
                    System.out.println("PAROLA INDOVINATA !!!!");
                    this.guessed.setLength(0);
                    this.guessed.append(this.secret);
                }
                else this.remainingAttempts -= in.length();
            }else{
                in = in.toLowerCase();
                if(this.secret.contains(in)){
                    for(int i = 0; i < this.secret.length(); i++){
                        if(String.valueOf(this.secret.charAt(i)).equalsIgnoreCase(in)) this.guessed.setCharAt(i, in.charAt(0));
                    }
                }else this.remainingAttempts --;  
            }
        }if(this.remainingAttempts == 0) System.out.println("GAME OVER");
        sca.close();
    }

    public static void main(String[] args) {
        System.out.println("asd");
        Impiccato imp = new Impiccato("Giovanni", 10, "facile");
        imp.inizializza();
        imp.play();
    }
}
