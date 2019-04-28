import java.util.ArrayList;

public class Player {

    String name;
    ArrayList<GameRecord> gamesPlayed;

    public Player(String name){
        this.name = name;
        this.gamesPlayed = new ArrayList<GameRecord>();
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
        this.gamesPlayed.add(game);

    }

}
