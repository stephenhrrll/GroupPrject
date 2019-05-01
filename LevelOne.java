import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class LevelOne {//extends TwoPlus {
    private String level = "1";
    private String numberGenerated;
    private String max;

    public LevelOne (String max){
        //this.level = level;
        this.max = max;
        this.numberGenerated = this.generate(max);


    }

    public String getMax(){
        return max;
    }
    public String getNumberGenerated() {
        return numberGenerated;
    }


    public String getLevel() {
        return level;
    }

    private String generate(String max){
        /*int randomNum = ThreadLocalRandom.current().nextInt(1, Integer.valueOf(max) + 1);
        String answer = Integer.toString(randomNum);
        return answer;*/

        Random r = new Random();
        return Integer.toString(r.nextInt((Integer.valueOf(max) + 1)));
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



        return null;



    }

}
