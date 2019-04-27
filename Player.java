import java.util.ArrayList;

public class Player {

    String name;
    ArrayList<GamePlayed> gamesPlayed;

    public Player(String name){
        this.name = name;
        this.gamesPlayed = new ArrayList<GamePlayed>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<GamePlayed> getGamesPlayed() {
        return gamesPlayed;
    }

    public void addGamePlayed(GamePlayed game){
        this.gamesPlayed.add(game);

    }

}
