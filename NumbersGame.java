import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class NumbersGame extends JFrame implements WindowListener {

    GridBagConstraints cst;
    GameData gameData;

    private Player player;
    private int whichGame;
    private TwoPlus gamePlay;
    private LevelOne gamePlay1;

    private GameRecord gameRecord;




    public NumbersGame(String title){
        super(title);

        gameData = new GameData();
        gamePlay = new TwoPlus("2");//just place holders
      
        //gamePlay1 = new LevelOne( "2")
  
        player = new Player("");//just place holders
        gameRecord = new GameRecord("", "","");

        //JFrame settings
        setSize(new Dimension(650, 900));
        setResizable(true);
        setLayout(new GridBagLayout());// 5 rows 1 column
        cst = new GridBagConstraints();
        setLocationRelativeTo(null);//opens in the center of the screen


        addWindowListener(this);//definitions for closing opening are in windowClosing() and windowOpened()
                                  //towards the end of the NumbersGame class
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

                //this variable is used as a switch between the levelone game and twoplus when it comes time to guess
                //int whichGame = 0;
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
                    output.setText("Hello " + player.getName()+"\n\n" + guiMessages.enterLevelInstructions());
                    input.setText("Enter Level");
                }else if (buttonText.matches("Submit")){
                    //the user has entered the level.

                    //start the game at that level

                    if(in.matches("1")){
                        //start level 1
                        whichGame = 0;
                        /*output.setText("Enter a maximum value");
                        input.setText("Enter a maximum value");
                        gamePlay1 = new LevelOne(in);*/


                        /*gameRecord = new GameRecord(gamePlay1.getNumberGenerated(), in);
                        player.addGamePlayed(gameRecord);*/
                        button.setText("Set Max");

                        output.setText("Enter a Max value to guess" + "\n" + guiMessages.levelOneInstructions());

                        input.setText("Enter a whole number");
                    }else {
                        gamePlay = new TwoPlus(in);
                        whichGame = 1;
                        //start the timer and start recording game data
                        gameRecord = new GameRecord(gamePlay.getNumberGenerated(), in, player.getName());
                        //add the record of this game to the player
                        player.addGamePlayed(gameRecord);


                        button.setText("Guess");
                        output.setText(String.format("Enter a %s digit number", in) + "\n" + guiMessages.higherLevelInstructions());
                        input.setText("Enter a whole number");
                    }
                }else if(buttonText.matches("Guess")){

                    //the user has entered their guess
                    if (whichGame == 1){//do level Twoplus studd here
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

                        }else{

                        output.setText(output.getText() + "\n" +
                                checkGuess + "\n");
                        }
                    }
                    if (whichGame == 0){
                        String checkGuess = gamePlay1.checkGuess(in);

                        System.out.println(gamePlay1.getNumberGenerated());
                        gameRecord.addTurn(in + "," + checkGuess);

                        if (checkGuess.contains("Win")) {
                            gameRecord.setEndTime();
                            gameRecord.setStatus("Won");

                            output.setText(guiMessages.winMessage());

                            button.setText("Submit");
                            input.setText("1");

                        }
                        else {

                            output.setText(output.getText() + "\n" +
                                    checkGuess + "\n");
                        }}

//                    if (whichGame == 0){
//                        String checkGuess = gamePlay1.checkGuess(in);
//
//                        System.out.println(gamePlay1.getNumberGenerated());
//                        gameRecord.addTurn(in + "," + checkGuess);
//
//                        if (checkGuess.contains("Win")){
//                            gameRecord.setEndTime();
//                            gameRecord.setStatus("Won");
//
//                            output.setText(guiMessages.winMessage());
//
//                            button.setText("Submit");
//                            input.setText("1");
//                        }
//
//                    }



                }else if(buttonText.matches("Set Max")){
                    //they have entered a max value
                    output.setText("Enter a maximum value");
                    input.setText("Enter a maximum value");
                    gamePlay1 = new LevelOne(in);
                


                    gameRecord = new GameRecord(gamePlay1.getNumberGenerated(), "1",player.getName());
                    player.addGamePlayed(gameRecord);

                    button.setText("Guess");
                    output.setText("Enter a whole number");
                    input.setText("Enter a whole number");

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

        ButtonGroup group = new ButtonGroup();//grouping the radio buttons ensures that others get unchecked

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
                output.setText("");

                if(time.isSelected()){
                    output.append("Games sorted by length of time\n");
                    output.append("\n");
                    ArrayList<String> out = gameData.byTime();

                    for(String l:out){
                        output.append(l);

                    }
                }else if (numberOfPlays.isSelected()){
                    output.append("Games sorted by number of turns\n");
                    output.append("\n");
                    ArrayList<String> out = gameData.byTurns();

                    for(String l:out){
                        output.append(l);

                    }

                }else if (topPlayer.isSelected()){
                    System.out.println("3");
                    output.append("Top Players ranked by average length of play per game\n");
                    output.append("\n");
                    ArrayList<String> out = gameData.topPlayers();

                    for(String l:out){
                        output.append(l);

                    }
                }else{//hardestGame

                    output.append("Hardest games by level\n");
                    output.append("\n");
                    ArrayList<String> out = gameData.hardestGames();

                    for(String l:out){
                        output.append(l);

                    }

                }

            }
        });

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 3;

        add(dispStats,cst);



    }

    @Override
    public void windowOpened(WindowEvent e) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("gameData.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            gameData = (GameData) ois.readObject();
            ois.close();

            //this needs to be further tested

            /*ArrayList<Player> data = gameData.getGameData();

            for(Player p:data){
                System.out.println(p.getName());
            }*/

        } catch (FileNotFoundException e1) {
            //nothing needs to happen  the file was deleted or
            //this is the first time its running
            //in either case the game will continue as if its the first time being played
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }


    }

    @Override
    public void windowClosing(WindowEvent e) {
        // saving gameData will occur here
        try {
            System.out.println("Closing");
            FileOutputStream fos = new FileOutputStream("gameData.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameData);
            oos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


    public static void main(String[] args) {
        new NumbersGame("Numbers Game").setVisible(true);
    }


}// End of NumbersGame class
