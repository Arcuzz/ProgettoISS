package pac.minigiochi;

import pac.Grafica;

import java.util.Scanner;

public class Impiccato extends Minigiochi{
    public String secret;
    public StringBuilder guessed;
    public int remainingAttempts;

    public Impiccato(String secret, int maxAttempts, String difficolta){
        this.secret = secret;
        this.remainingAttempts = maxAttempts;
        this.difficolta = difficolta;
        inizializza();
    }

    @Override
    public void inizializza(){
        this.guessed = new StringBuilder(this.secret.length());
        if (this.rank==1){
            this.guessed.insert(0, this.secret.charAt(0));
            for (int i = 1; i < this.secret.length()-1; i++) this.guessed.append('_');
            this.guessed.insert(this.guessed.length(), this.secret.charAt(this.secret.length()-1));
        }else if(this.rank==2){
            this.guessed.insert(0, this.secret.charAt(0));
            for (int i = 1; i < this.secret.length(); i++) this.guessed.append('_');
        }else for (int i = 0; i < this.secret.length(); i++) this.guessed.append('_');
        this.punti = this.rank*100;
    }

    @Override
    public void startGame(){
        Grafica.clearConsole();
        System.out.println(Grafica.Minigame);
        System.out.println("\n\n" + Grafica.sep+"--- Gioco dell'impiccato ---");

    }

    @Override
    public boolean play(Scanner sca){
        System.out.println("\n"+ Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
        sca.nextLine();
        String in = "";

        while (this.remainingAttempts > 0 && !this.secret.equalsIgnoreCase(guessed.toString()) && !in.equals("exit")) {

            startGame();

            System.out.println("\n"+Grafica.sep+"Parola da indovinare:");
            System.out.println(Grafica.sep+this.guessed);
            System.out.println("\n" + Grafica.sep+"Hai a disposizione " + this.remainingAttempts + " tentativi");
            System.out.println();
            System.out.print(Grafica.sep+"#: ");
            in = sca.nextLine();
            if(in.length() > 1){
                if (in.equalsIgnoreCase(this.secret)){
                    System.out.println(Grafica.sep+"PAROLA INDOVINATA !!!!");
                    this.guessed.setLength(0);
                    this.guessed.append(this.secret);
                    System.out.println(Grafica.sep+"Premi invio per riprendere il gioco");
                    System.out.print(Grafica.sep+"+ ");
                    sca.nextLine();
                    return true;
                }else if (this.secret.contains(in.toLowerCase())){
                    int j = 0;
                    for(int i = 0; i < this.secret.length(); i++){
                        if ((this.secret.charAt(i)) == in.toLowerCase().charAt(j)){
                            this.guessed.setCharAt(i, in.charAt(j));
                            j++;
                        }
                    }if (this.secret.equalsIgnoreCase(this.guessed.toString())){
                        System.out.println(Grafica.sep+"PAROLA INDOVINATA !!!!");
                        System.out.println(Grafica.sep+"Premi invio per riprendere il gioco");
                        System.out.print(Grafica.sep+"+ ");
                        sca.nextLine();
                        return true;
                    }

                }else if(!in.equalsIgnoreCase("exit"))this.remainingAttempts -= in.length();
            }else{
                in = in.toLowerCase();
                if(this.secret.contains(in)){
                    for(int i = 0; i < this.secret.length(); i++){
                        if(String.valueOf(this.secret.charAt(i)).equalsIgnoreCase(in)) this.guessed.setCharAt(i, in.charAt(0));
                    }
                }else this.remainingAttempts --;  
            }
        }
        return false;
    }
}