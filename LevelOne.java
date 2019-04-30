import java.util.concurrent.ThreadLocalRandom;
public class LevelOne {//extends TwoPlus {
    private String level;
    private String numberGenerated;

    public LevelOne(String level){
        this.level = level;
        this.numberGenerated = this.generate();
        //this.level = level


    }
    public String getNumberGenerated() {
        return numberGenerated;
    }


    public String getLevel() {
        return level;
    }

    private String generate(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        String answer = Integer.toString(randomNum);
        return answer;
    }

    private boolean validate(String guess){
        //guess = guess.replaceAll("[^\\d]","");
        guess = guess.replaceAll("[\\D]","");//replace all
        int diff = Math.abs(guess.length() - numberGenerated.length());
        if(diff != 0){// too many or too few digits
            return false;
        }
        return true;//is correct length and has only numbers
    }




    public String checkGuess(String guess){
        /*
         * checkGuess validates the input, if it is invalid it will return
         *
         * the prompt to be displayed
         *
         * It also checks if the user has guessed correctly,
         * if so it returns a string containing the guess and Win,
         * e.g. 12, Win
         *
         * If they have not guess correctly it will return the hint
         *
         *
         * */
        guess = guess.replaceAll("[^\\d]","");//replace anything thats not a number with ""
        // check input
        boolean isValid = this.validate(guess);
        if(isValid){
            if(guess.matches(numberGenerated)){//they have guessed the right number
                return "Win";
            }
            int tempGuess = Integer.parseInt(guess);
            int tempGenerated = Integer.parseInt(numberGenerated);
            if (tempGuess > tempGenerated){
                return ("Too high");
                //System.out.println("Too high");//they have not guessed correctly
            }
            else if(tempGuess < tempGenerated){
                return ("Too low");
            }

            //check how many correct digits are present
            //String correctDigits = countDigits(guess);

            //check how many are in the right place

            //String correctPlace = countSequence(guess);

            //return correctDigits + correctPlace;

        }

        return "Invald input, only numbers are allowed,try again";



    }

}
