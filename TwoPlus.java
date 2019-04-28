public class TwoPlus {

    private String numberGenerated;
    private String level;

    public TwoPlus(String level){

        this.level = level;
        this.numberGenerated = generate(level);

    }

    private String generate(String level){
        String number = "";
        for(int i = 0; i < Integer.valueOf(level);i++){
            number += (int)(Math.random() * 9);
        }
        return number;
    }

    public String getNumberGenerated() {
        return numberGenerated;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
        this.numberGenerated = this.generate(level);
    }
    private boolean validate(String guess){
        guess = guess.replaceAll("[\\D]","");//replace all
        int diff = Math.abs(guess.length() - numberGenerated.length());
        if(diff != 0){// too many or too few digits
            return false;
        }
        return true;//is correct length and has only numbers
    }

    private String countDigits(String guess){

        //check the number of correct digits
        int numOfCorrectDigits = 0;
        //for each digit in numberGenerated check to see if it is in the guess
        for(String s:guess.split("")){
            if(numberGenerated.contains(s)){
                numOfCorrectDigits ++;
            }
        }
        return Integer.toString(numOfCorrectDigits);

    }

    private String countSequence(String guess){
        String[] g = guess.split("");
        String[] c = numberGenerated.split("");
        int count = 0;
        for(int i = 0; i < guess.length() - 1; i++){
            if(g[i].matches(c[i])){
                count ++;
            }

        }
        return Integer.toString(count);
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
        guess = guess.replaceAll("[\\D]]","");//replace anything thats not a number with ""
        // check input
        boolean isValid = this.validate(guess);
        if(isValid){
            if(guess.matches(numberGenerated)){//they have guessed the right number
                    return "Win";
            }//they have not guessed correctly

            //check how many correct digits are present
            String correctDigits = countDigits(guess);

            //check how many are in the right place

            String correctPlace = countSequence(guess);

            return correctDigits + correctPlace;

        }

        return "Invald input, only numbers are allowed,try again";



    }
}
