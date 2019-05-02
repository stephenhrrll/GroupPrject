//Evan Holmberg
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class GameData implements Serializable {

    ArrayList<Player> players;


    public GameData(){
        players = new ArrayList<>();
    }

    private ArrayList<GameRecord> unpackGames(){
        ArrayList<GameRecord> allGames = new ArrayList<>();
        for (Player p:players) {
            for(GameRecord g:p.getGamesPlayed()){
                allGames.add(g);
            }
        }
        return allGames;
    }

    public ArrayList<String> byTime(){

        ArrayList<String> output = new ArrayList<>();
        //list all games from to shortest to longest


        //unpack all of the games
        ArrayList<GameRecord> allGames = unpackGames();

        //sortgamesbytime

        allGames.sort(new Comparator<GameRecord>() {
            @Override
            public int compare(GameRecord o1, GameRecord o2) {
                Long game1 = Long.valueOf(o1.getTotalTime());
                Long game2 = Long.valueOf(o2.getTotalTime());
                Long t = game1 -game2;

                return Integer.valueOf(t.toString());
            }
        });


        //Display all of the games

        for(GameRecord gr: allGames){
            String name = gr.getPlayerName();
            Long t = Long.valueOf(gr.getTotalTime());
            String time = Double.toString((double)t / 1000);
            String status = gr.getStatus();
            String guessing = gr.getNumberToGuess();

            output.add(name + ": " + time + " seconds, guessing "+ guessing + " " + status+"\n");
        }

        return output;


    }

    public ArrayList<String> byTurns(){
        ArrayList<String> output = new ArrayList<>();
        //list all games by number of turns

        //unpack all of the games
        ArrayList<GameRecord> allGames = unpackGames();

        //sortgamesbytime

        allGames.sort(new Comparator<GameRecord>() {
            @Override
            public int compare(GameRecord o1, GameRecord o2) {
                int t1 = Integer.valueOf(o1.getTurns());
                int t2 = Integer.valueOf(o2.getTurns());

                return t1 - t2;
            }
        });


        //Display all of the games

        for(GameRecord gr: allGames){
            String name = gr.getPlayerName();
            String turns = gr.getTurns();
            String status = gr.getStatus();
            String guessing = gr.getNumberToGuess();

            output.add(name + ": " + turns + " turns, guessing "+ guessing + " " + status+"\n");
        }
        return output;
    }


    class PassBackVals{
        HashMap<String, ArrayList<GameRecord>> levels;
        ArrayList<String> keys ;
        public PassBackVals(HashMap<String, ArrayList<GameRecord>> levels, ArrayList<String> keys){
            this.levels = levels;
            this.keys = keys;
        }
    }
    private PassBackVals unpackByLevel(){
        HashMap<String, ArrayList<GameRecord>> levels = new HashMap<>();

        ArrayList<String> keys = new ArrayList<>();

        //unpack all of the games organizing by level

        for(Player p: players){
            for(GameRecord g: p.getGamesPlayed()){

                //attempt to find the level in dictionary of levels
                ArrayList<GameRecord> l = levels.get(g.getLevel());

                if(l == null){//that level was found
                    //make a list
                    l = new ArrayList<>();
                    //add element to the list
                    l.add(g);
                    keys.add(g.getLevel());

                    //put the list in the dictionary
                    levels.put(g.getLevel(), l);
                }else{//it was found

                    //add element to the list
                    l.add(g);

                    //put the list back int dictionary
                    levels.put(g.getLevel(), l);
                }
            }
        }


        return new PassBackVals(levels, keys);
    }


    public ArrayList<String> topPlayers(){

        ArrayList<String> output = new ArrayList<>();
        //list games by level with average time being the deciding factor



        //sort each level

        PassBackVals r = unpackByLevel();
        HashMap<String, ArrayList<GameRecord>> levels = r.levels;
        ArrayList<String> levelKeys = r.keys;



        //for each level
        for(String key:levelKeys){
            output.add("Level " + key + "\n");
            //get the list
            ArrayList<GameRecord> ls = levels.get(key);

            HashMap<String, ArrayList<GameRecord>> plyrs = new HashMap<>();

            //names in this level

            ArrayList<String> names = new ArrayList<>();
            //for each game in that level
            for(GameRecord g:ls){
                //attempt to find the player  in plyrs

                ArrayList<GameRecord> games = plyrs.get(g.getPlayerName());

                if(games == null){
                    games = new ArrayList<>();
                    games.add(g);
                    plyrs.put(g.getPlayerName(),games);
                    //add the name to list of names
                    names.add(g.getPlayerName());

                }else{
                    games.add(g);
                    plyrs.put(g.getPlayerName(),games);

                }
            }//all players are in dict for curretn level

            //get average time for each player at that level

            ArrayList<String> st = new ArrayList<>();

            //for eah player at this level
            for(String k:names){
                //get the average play time
                Long sum = new Long(0);
                ArrayList<GameRecord> gms = plyrs.get(k);
                int count = 0;
                for(GameRecord g:gms){
                    sum += Long.valueOf(g.getTotalTime());
                    count++;
                }

                Long avg = sum / count;
                String personData = k + "," + avg;

                st.add(personData);

            }

            st.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String[] ob1 = o1.split(",");
                    String[] ob2 = o2.split(",");
                    Long ob1Val = Long.valueOf(ob1[1]);
                    Long ob2Val = Long.valueOf(ob2[1]);

                    return (int) (ob1Val - ob2Val);
                }
            });

            for(String s:st){
                String name = s.split(",")[0];
                Long time = Long.valueOf(s.split(",")[1]);
                double t = (double) time / 1000;
                output.add(name + " "  + t + " Seconds Avg.\n");
            }


        }
        return output;
    }

    public ArrayList<String> hardestGames(){
        ArrayList<String> ouput = new ArrayList<>();
        //list by level all of the hardest games.
        PassBackVals r = unpackByLevel();
        HashMap<String, ArrayList<GameRecord>> levels = r.levels;
        ArrayList<String> levelKeys = r.keys;

        //for each level
        for(String k:levelKeys){
            ouput.add("Level " + k + "\n");
            ArrayList<GameRecord> level = levels.get(k);
            GameRecord mostTurns = new GameRecord("","","");
            for(GameRecord g:level){
                int gTurns = Integer.valueOf(g.getTurns());
                int mTurns = Integer.valueOf(mostTurns.getTurns());
                if(gTurns > mTurns){
                    mostTurns = g;

                }
            }
            double time = (double)Long.valueOf(mostTurns.getTotalTime())/1000;

            ouput.add(mostTurns.getPlayerName() + " , " + mostTurns.getTurns() + " turns " + time + " seconds\n");



        }
        return ouput;

    }

    public void addGame(){

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
