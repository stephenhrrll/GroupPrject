import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

class NumbersGame extends JFrame {

    GridBagConstraints cst;
    GameData gameData;

    private Player player;

    private TwoPlus gamePlay;

    private GameRecord gameRecord;




    public NumbersGame(String title){
        super(title);

        gameData = new GameData();
        gamePlay = new TwoPlus("2");//just place holders
        player = new Player("");//just place holders
        gameRecord = new GameRecord("", "");

        //JFrame settings
        setSize(new Dimension(650, 900));
        setResizable(true);
        setLayout(new GridBagLayout());// 5 rows 1 column
        cst = new GridBagConstraints();
        setLocationRelativeTo(null);//opens in the center of the screen

        /*
        * Saving data when closing will have to start here
        *
        * need to look this up
        * */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        makeView();








    }//End of NumbersGame constructor

    public void makeView(){

        Messages guiMessages = new Messages();

        /********************************************************
         * **********************row 2****************************
         * ******************************************************/

        // ****************output area**************************

        JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font(null,0,24));
        output.setWrapStyleWord(true);
        output.setLineWrap(true);



        output.setText(guiMessages.welcomeMessage());

        //Scroll bar
        JScrollPane scroll = new JScrollPane (output);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //second row first column
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.weightx = 0.0;
        cst.gridwidth = 2;
        cst.ipady = 600;
        cst.gridx = 0;
        cst.gridy = 1;


        add(scroll, cst);
        /****************************************************
        * *****************row 1*****************************
        * **************************************************/

        //*****************input area***************************

        JTextField input = new JTextField("Input");
        input.setFont(new Font(null, 0, 24));
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        input.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e) {
                //removes placeholder text upon text area selection
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }

        });


        //*****************start game button******************

        JButton startGuess = new JButton("Start Game");

        startGuess.setFont(new Font(null, 0, 24));


        cst.fill = GridBagConstraints.HORIZONTAL;

        cst.gridwidth = 1;//reset
        cst.ipady = 0;//reset

        cst.weightx = 0.5;
        cst.gridx = 0;//first column
        cst.gridy = 0;//first row




        startGuess.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                *
                *
                * start button functions go here
                *
                * */

                JButton button = (JButton)e.getSource();
                String buttonText = button.getText();

                //get text from input
                String in = input.getText();
                in = in.replaceAll(" ", "");

                if(buttonText.matches("Start Game")){
                    //user has just entered their name

                    //check to see if that player exists in game data

                    player = gameData.find(in);

                    if(player == null) {
                        player = new Player(in);
                        //add that player to gameData

                        gameData.add(player);
                    }



                    button.setText("Submit");
                    output.setText("Hello " + player.getName()+"\n\n" + guiMessages.higherLevelInstructions());
                    input.setText("Enter Level");
                }else if (buttonText.matches("Submit")){
                    //the user has entered the level.

                    //start the game at that level

                    if(in.matches("1")){
                        //start level 1
                    }else{
                        gamePlay = new TwoPlus(in);
                    }
                    //start the timer and start recording game data
                    gameRecord = new GameRecord(gamePlay.getNumberGenerated(), in);
                    //add the record of this game to the player
                    player.addGamePlayed(gameRecord);


                    button.setText("Guess");
                    output.setText(String.format("Enter a %s digit number", in));
                    input.setText("Enter a whole number");

                }else if(buttonText.matches("Guess")){

                    //the user has entered their guess

                    String checkGuess = gamePlay.checkGuess(in);

                    System.out.println(gamePlay.getNumberGenerated());

                    gameRecord.addTurn(in +","+ checkGuess);

                    if(checkGuess.contains("Win")){
                        gameRecord.setEndTime();
                        gameRecord.setStatus("Won");
                        //display win message
                        output.setText(guiMessages.winMessage());

                        //let the player choose to play again

                        button.setText("Submit");
                        input.setText(gamePlay.getLevel());

                    }else {

                        output.setText(output.getText() + "\n" +
                                checkGuess + "\n");
                    }



                }

            }
        });

        add(startGuess,cst);

        // add input area

        //top row second column
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.weightx = 0.5;
        cst.gridx = 1;
        cst.gridy = 0;

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 * input area stuff goes here
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 * */
            }
        });


        add(input, cst);

        /********************************************************
         * **********************row 3****************************
         * ******************************************************/

        // ****************clear display button********************

        JButton clearDisp = new JButton("Clear Display");
        clearDisp.setFont(new Font(null,0,24));
        clearDisp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
            }
        });

        //third row first column
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.ipady = 0;//reset to defualt
        cst.gridwidth = 1;
        cst.gridx = 0;
        cst.gridy = 2;

        add(clearDisp, cst);

        //*******************stats*********************************

        JPanel stats = new JPanel(new GridBagLayout());

        // ****************checkboxes****************************

        JLabel statsLabel = new JLabel("Statistics Options");
        statsLabel.setFont(new Font(null,0,30));

        ButtonGroup group = new ButtonGroup();

        JRadioButton time = new JRadioButton("Time", true);
        time.setFont(new Font(null,0,24));


        JRadioButton numberOfPlays = new JRadioButton("# of Plays", false);
        numberOfPlays.setFont(new Font(null,0,24));



        JRadioButton topPlayer = new JRadioButton("Top Player", false);
        topPlayer.setFont(new Font(null,0,24));


        JRadioButton hardestGame = new JRadioButton("Hardest Game", false);
        hardestGame.setFont(new Font(null,0,24));

        group.add(time);
        group.add(numberOfPlays);
        group.add(topPlayer);
        group.add(hardestGame);




        // set gridbox constraints for check boxes

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 0;

        stats.add(statsLabel,cst);//add the label

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 1;

        stats.add(time,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 1;

        stats.add(numberOfPlays,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 2;

        stats.add(topPlayer,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 2;

        stats.add(hardestGame,cst);

        //set grid box constraints for stats JPanel

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 2;
        cst.gridheight = 2;

        add(stats,cst);

        /********************************************************
         * **********************row 4****************************
         * ******************************************************/

        // ****************Display stats********************

        JButton dispStats = new JButton("Display Stats");
        dispStats.setFont(new Font(null, 0, 24));

        dispStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //display stats
            }
        });

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 3;

        add(dispStats,cst);



    }




    public static void main(String[] args) {
        new NumbersGame("Numbers Game").setVisible(true);
    }

}// End of NumbersGame class
