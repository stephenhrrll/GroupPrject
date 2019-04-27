import java.util.ArrayList;

public class GameData {

    ArrayList<Player> players;

    public GameData(){
        players = new ArrayList<>();
    }

    public ArrayList<Player> getGameData() {
        return players;
    }

    public Player find(String name){
        for(Player p:players){
            if(p.getName().matches(name)){
                return p;
            }
        }
        return null;
    }

    public void add(Player p){
        players.add(p);
    }
}
