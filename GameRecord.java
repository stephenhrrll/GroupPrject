import java.time.LocalTime;
import java.util.ArrayList;

public class GameRecord {

    private LocalTime startTime;

    private LocalTime endTime;

    private String numberToGuess;//the number the player is trying to guess

    private ArrayList<String> turns;

    private String status;//won or lost, losing means they quit the game.

    private String level;

    public GameRecord(String numberToGuess, String level){
        this.startTime = LocalTime.now();
        this.endTime = null;
        this.numberToGuess = numberToGuess;
        this.turns = new ArrayList<String>();
        this.status = null;
        this.level = level;
    }//end of constructor


    //Getters


    public String getLevel() {
        return level;
    }

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
