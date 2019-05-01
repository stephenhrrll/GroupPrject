import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class GameRecord implements Serializable {

    private LocalTime startTime;

    private LocalTime endTime;

    private String totalTime;

    private String numberToGuess;//the number the player is trying to guess

    private ArrayList<String> turns;

    private String status;//won or lost, losing means they quit the game.

    private String level;

    private String playerName;

    public GameRecord(String numberToGuess, String level, String name){
        this.startTime = LocalTime.now();
        this.endTime = null;
        this.numberToGuess = numberToGuess;
        this.turns = new ArrayList<String>();
        this.status = "Lost";
        this.level = level;
        this.playerName = name;
        this.totalTime = "0";
    }//end of constructor


    //Getters


    public String getLevel() {
        return level;
    }

    public String getNumberToGuess() {
        return numberToGuess;
    }

    public String getTurns() {
        return Integer.toString(turns.size());
    }

    public String getStatus() {
        return status;
    }

    //setters

    public LocalTime setEndTime(){
        this.endTime = LocalTime.now();

        return this.endTime;
    }
    public void setTotalTime(){
        this.totalTime = getTotalTime();
    }

    public String getTotalTime(){
        Duration l = Duration.between(startTime, endTime);
        String length = Long.toString(l.toMillis());
        return length;
    }

    public void addTurn(String turn){//turn should be in the form "Guess,hint"
            this.turns.add(turn);    //e.g. "123,01"

    }

    public void setStatus(String status){// "won" or "lost"
        this.status = status;
    }

    public String toString(){
        String length = getTotalTime();

        String turns = Integer.toString(this.turns.size());

        return "Level: " + level + "\n" +
                "Number to guess: " + numberToGuess + "\n" +
                "Time in play: " + length + "\n" +
                "Number of turns : " + turns + "\n" +
                "Won or Lost: " + status + "\n";

    }

    public String getPlayerName(){
        return playerName;
    }



}
