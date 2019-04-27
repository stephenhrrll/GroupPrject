import java.time.LocalTime;
import java.util.ArrayList;

public class GamePlayed {

    LocalTime startTime;

    LocalTime endTime;

    String numberToGuess;//the number the player is trying to guess

    ArrayList<String> turns;

    String status;//won or lost, losing means they quit the game.

    public GamePlayed(String numberToGuess){
        this.startTime = LocalTime.now();
        this.endTime = null;
        this.numberToGuess = numberToGuess;
        this.turns = new ArrayList<String>();
        this.status = null;
    }//end of constructor


    //Getters

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getNumberToGuess() {
        return numberToGuess;
    }

    public ArrayList<String> getTurns() {
        return turns;
    }

    public String getStatus() {
        return status;
    }

    //setters

    public LocalTime setEndTime(){
        this.endTime = LocalTime.now();
        return this.endTime;
    }

    public void addTurn(String turn){//turn should be in the form "Guess,hint"
            this.turns.add(turn);    //e.g. "123,01"

    }

    public void setStatus(String status){// "won" or "lost"
        this.status = status;
    }


}
