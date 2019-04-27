public class Messages {
    /*The purpose of this class is just to serve various messages
        to the NumbersGame
    * */
    public Messages(){
        //dont need to do anything
    }

    public String welcomeMessage(){
        return "Welcome to Numbers Game!\n\n" +
                "This is a game where you guess the numbers I generate.\n\n" +
                "Ill tell you more after we get started.\n\n" +
                "Please enter your username in the text field above and click Start Game.";
    }

    public String enterLevelInstructions(){
        return "Explain what the levels are and how to proceed";
    }

    public String levelOneInstructions(){
        return "Level 1 instructions go here";
    }

    public String higherLevelInstructions(){
        return "higher level instructions go here";
    }


}

