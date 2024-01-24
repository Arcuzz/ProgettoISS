import java.io.FileNotFoundException;

public class Floor {
    Questions que;
    public Floor(int currentF) throws FileNotFoundException {
        setCurrentFloor(currentF);
        printCurrentFloor();
        que = new Questions(2,currentF);
    }
    public int currentFloor;
    public int numOfQuestions;
    public void callDialogue(){};
    public void moveForward(){
        System.out.println("Ti muovi in avanti e trovi un nuovo enigma!");
        for(Prova item : que.domande) {
            item.faiDomanda();
        }
    }
    public void start(){
        printCurrentFloor();
    }
    public void setCurrentFloor(int x){
        this.currentFloor = x;
    }
    public int getCurrentFloor(){
        return this.currentFloor;
    }
    public void printCurrentFloor(){
        System.out.println("\nSei al piano " + getCurrentFloor());
    }
}
