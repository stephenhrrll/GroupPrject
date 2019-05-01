import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Serializable {

    String name;
    ArrayList<GameRecord> gamesPlayed;

    public Player(String name){
        this.name = name;
        this.gamesPlayed = new ArrayList<>() ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<GameRecord> getGamesPlayed() {
        return gamesPlayed;
    }

    public void addGamePlayed(GameRecord game){

        getGamesPlayed().add(game);

    }



}
