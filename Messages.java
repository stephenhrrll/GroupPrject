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
        return "Enter what level you want your game to be. 1 being the lowest.";
    }

    public String levelOneInstructions(){
        return "For level one, Enter a positive whole number which you want to be the maximum value of the range that a random number will be generated.\n" +
                "The minimum value will always be 1. Once the number is generated you will have to guess the number, after each guess a hint will be given such as 'Too high' or 'Too low'\n"
                + "Once you guess correctly, a 'Win' message will be displayed and the amount of guesses you made and the time you took will be recorded";
    }

    public String higherLevelInstructions(){
        return "Enter a whole number which will represent the level. The value of that whole number will represent how many numbers between 0 and 9 you will have to guess\n"
                + "  For example. If you choose level 3, the numbers 826 could be an example of the numbers generated. \n"
                + " a 2 digit response will be given, the first number representing how many numbers were guessed correctly\n"
                + " and the second representing how many were in the right position. For example. Guessing 806 will return 22 \n" +
                " and guessing 569 would return 1,0. Finally, guessing 826 would return a 'Win'";
    }

    public String winMessage(){
        return "You win!\n"+
                "To play again, enter a level or click Submit\n"+
                "To quit, exit the game";

    }
    public String notValid(){
        return "Please enter an input with only numeric characters and no spaces";
    }

}