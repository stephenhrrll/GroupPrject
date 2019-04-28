import java.util.ArrayList;

public class Player {

    String name;
    ArrayList<GameRecorder> gamesPlayed;

    public Player(String name){
        this.name = name;
        this.gamesPlayed = new ArrayList<GameRecorder>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<GameRecorder> getGamesPlayed() {
        return gamesPlayed;
    }

    public void addGamePlayed(GameRecorder game){
        this.gamesPlayed.add(game);

    }

}
