package pac.minigiochi;
import pac.Grafica;

import java.util.*;

public class Briscola extends Minigiochi{
    private static final String[] mazzoFisso={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39"};
    private static final int[] value={11,0,10,0,0,0,0,2,3,4};
    private static int ind = 0; //indice scorrimento mazzo
    private static String briscola = "";
    private static String[] mazzo;
    public Briscola(){}
    public void inizializza(){
        this.punti = this.rank;
        mazzo = mescolaMazzo(mazzoFisso);
        briscola = mazzo[39];
    }
    public void startGame(){
        Grafica.clearConsole();
        System.out.println(Grafica.Minigame);
        System.out.println("\n\n" + Grafica.sep+"--------BRISCOLA--------");
        System.out.println(Grafica.sep+"Da un mazzo di 40 carte e 4 semi viene estratta una prima carta il cui seme è la briscola");
        System.out.println(Grafica.sep+"La mano del giocatore è composta da tre carte, e si estrae una nuova carta dal mazzo dopo ogni turno");
        System.out.println(Grafica.sep+"A ogni turno si sceglie una delle 3 carte, e chi ha la carta dal valore maggiore vince il turno e somma il valore delle due al proprio punteggio totale");
        System.out.println(Grafica.sep+"L'asso, il 3, l'8, il 9 e il 10 valgono rispettivamente 11, 10, 2, 3, 4 punti, tutte le altre 0");
        System.out.println(Grafica.sep+"Se uno dei due giocatori sceglie una briscola vince in automatico");
        System.out.println(Grafica.sep+"Se entrambi i giocatori scelgono una briscola, il turno procede normalmente");
        System.out.println(Grafica.sep+"Quando il mazzo viene esaurito vince chi ha totalizzato più di 60 punti\n");;
    }
    public boolean play(Scanner scan){
        System.out.println("\n"+ Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
        scan.nextLine();
        int mod=0;
        if(this.rank==3) mod = 1;
        int punteggioEsito = partita(mod, scan);
        this.punti *= punteggioEsito;
        return esitoPartita(punteggioEsito);
    }
    private void reset(){
        briscola = "";
        ind = 0;
        this.punti = this.rank;
    }
    private String[] mescolaMazzo(String[] mazzo){
        ArrayList<String> mazzo1 = new ArrayList<>(Arrays.asList(mazzo));
        Collections.shuffle(mazzo1);
        for(int i=0;i< mazzo.length;i++) mazzo[i] = mazzo1.get(i);
        return mazzo;
    }
    private ArrayList<String> riempiMano(ArrayList<String> mano){
        while(mano.size()<3){
            mano.add(mazzo[ind++]);
        }
        return mano;
    }
    private String printCarta(String carta){
        String out="";
        switch (carta.charAt(0)) {
            case '0' -> out = "_Coppe";
            case '1' -> out = "_Denari";
            case '2' -> out = "_Mazze";
            case '3' -> out = "_Spade";
            default -> System.out.println("Errore carta!");
        }
        return (((int)carta.charAt(1)-'0')+1)+out;
    }
    private void printMano(ArrayList<String> mano){
        System.out.print("\n"+Grafica.sep+"----------------------------------------------\n");
        System.out.print(Grafica.sep+"---> Mano: ");
        for(int i=0;i<mano.size();i++) System.out.print(" | "+printCarta(mano.get(i)));
        System.out.print(" |\n"+Grafica.sep+"----------------------------------------------");
    }
    private static int scelta(String txt){
        int ris=0;
        boolean ok = false;
        while(!ok){
            try{
                System.out.println(txt+"\n"+Grafica.sep+"Rispondi con 1=SI, 0=NO");
                System.out.print(Grafica.sep+"#: ");
                Scanner in = new Scanner(System.in);
                ris = in.nextInt();
                in.close();
                if(ris==1 || ris==0)    ok = true;
                else{
                    throw new InputMismatchException();
                }
            }
            catch(InputMismatchException e){
                System.out.println(Grafica.sep+"Errore! Digita \"1\"=SI o \"0\"=NO");
            }
        }
        return ris;
    }
    private int sceltaUser(ArrayList<String> mano_user, Scanner scan){
        int c=0;
        System.out.println("\n"+Grafica.sep+"Scegli la tua carta (1,2,3 rispettivamente) o 99 per uscire dal gioco:");
        try{
            do{
                System.out.print(Grafica.sep+"#: ");
                c = Integer.parseInt(scan.nextLine());
                if(c==99) return c;
                if(c>=1 && c<=mano_user.size())
                    break;
                else throw new InputMismatchException();
            }
            while(scan.hasNextInt());
                //if(c>=1 && c<=mano_user.size())    ok = true;
        }
        catch(InputMismatchException e){
            System.out.println(Grafica.sep+"Errore! Digita 1,2,3 per le carte");
        }
        return c-1;
    }
    private int sceltaPc(int mod,ArrayList<String> mano_pc){
        int n;
        if(mod==0) n = ((int)(Math.random()* mano_pc.size()));
        else{n=0;}
        return n;
    }   //se il pc va per primo
    private int sceltaPc(int mod,ArrayList<String> mano_pc,String cartaUser){
        int n, i;   //n scelta finale pc
        if(mod==0) n = ((int)(Math.random()* mano_pc.size()));
        else{
            n=0;
            if(cartaUser.charAt(0)==briscola.charAt(0)){    //se l'utente butta una briscola per primo
                for(i=0;i<mano_pc.size();i++){
                    if(mano_pc.get(i).charAt(0)!=briscola.charAt(0)){   //se il pc non ha briscola
                        if(value[(int)mano_pc.get(i).charAt(1)-'0']==0){    //butta il liscio se lo ha
                            n=i;
                        }
                        else{   //altrimenti butta il minimo
                            if(value[(int)mano_pc.get(i).charAt(1)-'0']<value[(int)mano_pc.get(i+1).charAt(1)-'0'])
                                n=i;
                            else n=i+1;
                        }
                    }
                    else{   //se il pc ha briscola
                        if(value[(int)mano_pc.get(i).charAt(1)-'0']>value[(int)cartaUser.charAt(1)-'0'])   //ed è briscola maggiore dell'utente
                            if(value[(int)mano_pc.get(i).charAt(1)-'0']<value[(int)mano_pc.get(i+1).charAt(1)-'0'])
                                n=i;        //butta la briscola maggiore minima
                            else n=i+1;
                        else{
                            if(value[(int)cartaUser.charAt(1)-'0']==0)  //butta il liscio briscola
                                n=i;
                            else{       //oppure butta la briscola minore minima
                                if(value[(int)mano_pc.get(i).charAt(1)-'0']<value[(int)mano_pc.get(i+1).charAt(1)-'0'])
                                    n=i;
                                else n=i+1;
                            }
                        }
                    }
                }
            }
            else{   //se l'utente butta una non briscola per primo
                for(i=0;i<mano_pc.size();i++){
                    if(mano_pc.get(i).charAt(0)==cartaUser.charAt(0)){  //se il pc ha carta dello stesso seme dell'utente
                        if(value[(int)mano_pc.get(i).charAt(1)-'0']>value[(int)cartaUser.charAt(1)-'0'] && value[(int)cartaUser.charAt(1)-'0']>0)    //e ha valore maggiore
                            if(value[(int)mano_pc.get(i).charAt(1)-'0']<value[(int)mano_pc.get(i+1).charAt(1)-'0']) //butta quella superiore più vicina
                                n=i;                                           //perché ha valore > cartauser ma la minore tra le sue
                            else n=i+1;
                        else if(value[(int)mano_pc.get(i).charAt(1)-'0']==0)    //e non ha valore
                            n=i;
                    }
                    else if(value[(int)cartaUser.charAt(1)-'0']>0 && mano_pc.get(i).charAt(0)==briscola.charAt(0)){ //se la cartauser ha valore e il pc ha briscola
                        if(value[(int)mano_pc.get(i).charAt(1)-'0']==0)
                            n=i;
                        else{
                            if(value[(int)mano_pc.get(i).charAt(1)-'0']<value[(int)mano_pc.get(i+1).charAt(1)-'0'])
                                n=i;
                            else n=i+1;
                        }
                    }
                    else{  //se il pc non ha briscola nè stesso seme
                        if(value[(int)mano_pc.get(i).charAt(1)-'0']==0) //liscio
                            n=i;
                        else{
                            if(value[(int)mano_pc.get(i).charAt(1)-'0']<value[(int)mano_pc.get(i+1).charAt(1)-'0'])
                                n=i;
                            else n=i+1;
                        }
                    }
                }
            }
        }
        return n;
    }   //se l'utente va per primo

    private int partita(int mod, Scanner scan){   //0 facile, 1 difficile
        ArrayList<String> user = new ArrayList<>();
        ArrayList<String> pc = new ArrayList<>();
        String[] carte = new String[2];
        int punteggio = 0, carta, n_turno=1;
        System.out.println(Grafica.sep+"***************************************");
        System.out.println(Grafica.sep+"*******\tBriscola: "+printCarta(briscola)+"\t*******");
        System.out.println(Grafica.sep+"***************************************");
        System.out.println(Grafica.sep+"Lanciando una moneta: testa parte l'utente, croce parte il pc");
        int primoGiocatore = (int)(Math.random() * 2);
        attesa(200);
        if(primoGiocatore==0) System.out.println(Grafica.sep+"Esce testa, inizia l'utente");
        else System.out.println(Grafica.sep+"Esce croce, parte il pc");
        System.out.println("\n"+ Grafica.sep+"Premi invio per iniziare");
        System.out.print(Grafica.sep+"#: ");
        scan.nextLine();

        do{

            startGame();

            System.out.println(Grafica.sep+"Lanciando una moneta: testa parte l'utente, croce parte il pc");
            System.out.println("\n"+ Grafica.sep+"Turno numero " + n_turno + ". Rimangono " + (20-n_turno) +" turni.");
            n_turno++;
            attesa(200);
            if(primoGiocatore==0){
                if(ind<40 && user.size()<3){
                    user = riempiMano(user);
                    pc = riempiMano(pc);
                }
                System.out.println(Grafica.sep+"BRISCOLA: " + printCarta(briscola)+"\n");
                printMano(user);
                attesa(200);
                carta = sceltaUser(user,scan);
                if(carta==99){
                    System.out.println(Grafica.sep+"Sei uscito senza completare il minigioco, ritorno al movimento");
                    reset();
                    return 0;
                }
                carte[0] = user.get(carta);
                System.out.println(Grafica.sep+"Hai scelto: " + printCarta(carte[0]));
                user.remove(carta);
                carta = sceltaPc(mod, pc, carte[0]);
                carte[1] = pc.get(carta);
                attesa(500);
                System.out.println("\n"+Grafica.sep+" - Pc ha scelto: " + printCarta(carte[1]));
                pc.remove(carta);
                punteggio += puntiTurno(carte, 0);
            }
            else{
                if(ind<40 && user.size()<3){
                    pc = riempiMano(pc);
                    user = riempiMano(user);
                }
                carta = sceltaPc(mod, pc);
                carte[0] = pc.get(carta);
                attesa(500);
                System.out.println(Grafica.sep+"Pc ha scelto: " + printCarta(carte[0]));
                pc.remove(carta);
                System.out.println(Grafica.sep+"BRISCOLA: " + printCarta(briscola)+"\n");
                printMano(user);
                attesa(200);
                carta = sceltaUser(user,scan);
                if(carta==99){
                    System.out.println(Grafica.sep+"Sei uscito senza completare il minigioco, ritorno al movimento");
                    reset();
                    return 0;
                }
                carte[1] = user.get(carta);
                System.out.println(Grafica.sep+"Hai scelto: " + printCarta(carte[1]));
                user.remove(carta);
                punteggio += puntiTurno(carte, 1);
            }

            System.out.println("\n"+ Grafica.sep+"Premi invio per continuare");
            System.out.print(Grafica.sep+"#: ");
            scan.nextLine();
        } while(user.size()>0);
        return punteggio;
    }
    private int puntiTurno(String[] carte, int user){
        int punti = 0;
        boolean vincitore = false;
        if(user==0){
            if(carte[0].charAt(0)==carte[1].charAt(0)) {
                if(value[carte[0].charAt(1)-'0']>value[carte[1].charAt(1)-'0'])
                    vincitore = true;
            }
            else{   //semi diversi
                if(carte[0].charAt(0)==briscola.charAt(0))
                    vincitore = true;
                else if(carte[1].charAt(0)!=briscola.charAt(0) && value[carte[0].charAt(1)-'0']>value[carte[1].charAt(1)-'0']){ //entrambi senza briscola
                    vincitore = true;
                }
            }
        }
        else{
            if(carte[0].charAt(0)==carte[1].charAt(0)) {
                if(value[carte[0].charAt(1)-'0']<value[carte[1].charAt(1)-'0'])
                    vincitore = true;
            }
            else{
                if(carte[1].charAt(0)==briscola.charAt(0))
                    vincitore = true;
                else if(carte[0].charAt(0)!=briscola.charAt(0) && value[carte[0].charAt(1)-'0']<value[carte[1].charAt(1)-'0']){ //entrambi senza briscola
                    vincitore = true;
                }
            }
        }
        if(vincitore)   punti = value[carte[0].charAt(1)-'0'] + value[carte[1].charAt(1)-'0'];
        return punti;
    }
    private boolean esitoPartita(int score){
        if(score==0){
            System.out.println(Grafica.sep+"Sei uscito senza completare la partita! Ritorno al movimento");
            return false;
        }
        if(score>60){
            System.out.print("\n"+ Grafica.sep+" -=-= Complimenti! Hai vinto con "+score+" Punti! =-=-\n");
            return true;
        }
        else{
            if(score<60){
                System.out.print(Grafica.sep+" -=-= Hai perso! Hai ottenuto "+score+" Punti! =-=-\n");
            }
            else{
                System.out.print(Grafica.sep+" -=-= Pareggio! Hai ottenuto "+score+" Punti! =-=-\n");
            }
            return false;
        }
    }
    private void attesa(int t){
        try {
            Thread.currentThread().sleep(t);
        }
        catch(Exception e){}
    }
}
