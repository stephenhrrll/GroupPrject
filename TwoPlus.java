public class TwoPlus {

    private String numberGenerated;
    private String level;

    public TwoPlus(String level){

        this.level = level;
        this.numberGenerated = generate(level);

    }

    public String generate(String level){
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
        for(String s:numberGenerated.split("")){
            if(guess.contains(s)){
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
        guess = guess.replaceAll("[\\D]]","");//replace anything thats not a number with ""
        // check input
        boolean isValid = this.validate(guess);
        if(isValid){
            String correctDigits = countDigits(guess);

            //how many are in the right place

            String correctPlace = countSequence(guess);
            return correctDigits + correctPlace;

        }

        return "Invald input, only numbers are allowed,try again";



    }
}
