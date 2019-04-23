import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NumbersGame extends JFrame {

    /*JPanel start_n_input;// will contain startbutton and input area
    JPanel output_area;// will contain output area
    JPanel clear_n_stats;// will contain clear display button and stats options
    JPanel display_n_hardest;// will conatin display stats button and most difficult option*/
    GridBagConstraints cst;




    public NumbersGame(String title){
        super(title);

        //JFrame settings
        setSize(new Dimension(650, 900));
        setResizable(false);
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
        /****************************************************
        * *****************row 1*****************************
        * **************************************************/

        //*****************start game button******************

        JButton startGuess = new JButton("Start Game");

        startGuess.setFont(new Font(null, 0, 24));


        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.weightx = 0.5;
        cst.gridx = 0;//first column
        cst.gridy = 0;//first row




        startGuess.addActionListener(new ActionListener() {

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
                * this buttons functions go here
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

        add(startGuess,cst);

        //*****************input area***************************

        JTextField input = new JTextField("Input");
        input.setFont(new Font(null, 0, 24));

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
        * **********************row 2****************************
        * ******************************************************/

        // ****************output area**************************

        JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font(null,0,24));
        output.setText("OutPut / Instructions");

        //second row first column
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.weightx = 0.0;
        cst.gridwidth = 2;
        cst.ipady = 600;
        cst.gridx = 0;
        cst.gridy = 1;


        add(output, cst);

        /********************************************************
         * **********************row 3****************************
         * ******************************************************/

        // ****************clear display button********************

        JButton clearDisp = new JButton("Clear Display");
        clearDisp.setFont(new Font(null,0,24));
        clearDisp.addActionListener(new ActionListener() {
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
                 * clear display functions go here
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

        JCheckBox time = new JCheckBox("Time");
        time.setFont(new Font(null,0,24));

        time.addActionListener(new ActionListener() {
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
                 * clear other check boxes
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

        JCheckBox numberOfPlays = new JCheckBox("# of Plays");
        numberOfPlays.setFont(new Font(null,0,24));

        numberOfPlays.addActionListener(new ActionListener() {
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
                 * clear other check boxes
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

        JCheckBox topPlayer = new JCheckBox("Top Player");
        topPlayer.setFont(new Font(null,0,24));

        topPlayer.addActionListener(new ActionListener() {
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
                 * clear other check boxes
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

        JCheckBox hardestGame = new JCheckBox("Hardest Game");
        hardestGame.setFont(new Font(null,0,24));


        topPlayer.addActionListener(new ActionListener() {
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
                 * clear other check boxes
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
                 * */
            }
        });

        // set gridbox constraints for check boxes

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 0;

        stats.add(statsLabel,cst);

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
