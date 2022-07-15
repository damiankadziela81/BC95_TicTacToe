import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton restartButton = new JButton("RESTART");
    boolean player1Turn;
    int step = 0;


    TicTacToe(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setResizable(false);

        textField.setBackground(new Color(20,20,20));
        textField.setForeground(Color.GREEN);
        textField.setFont(new Font(null,Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("TIC TAC TOE");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800,100);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(120,120,120));

        for (int i=0;i<9;i++){
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font(null,Font.BOLD,120));
            buttons[i].setBackground(Color.white);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setEnabled(false);
        }

        restartButton.setBounds(180,380,440,100);
        restartButton.setFont(new Font(null,Font.BOLD,80));
        restartButton.addActionListener(this);
        restartButton.setVisible(false);

        titlePanel.add(textField);
        frame.add(restartButton);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel);

        showTitle();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i=0;i<9;i++){
            if(e.getSource()==buttons[i]){
                if (player1Turn) {
                    if (buttons[i].getText().equals("")){
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("X");
                        player1Turn = false;
                        textField.setText("O turn");
                        check();
                    }
                }
                else {
                    if (buttons[i].getText().equals("")){
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }

        if (e.getSource()==restartButton){
            restart();
        }

    }

    public void showTitle(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        firstTurn();
    }

    public void firstTurn(){


        if (random.nextBoolean()) {
            player1Turn = true;
            textField.setText("X turn");
        }
        else {
            player1Turn = false;
            textField.setText("O turn");
        }
        for (int i=0;i<9;i++){
            buttons[i].setEnabled(true);
        }

    }

    public void check(){
        
        //X conditions
        if ((buttons[0].getText().equals("X")) && (buttons[1].getText().equals("X")) && (buttons[2].getText().equals("X"))) {
            xWins(0,1,2);
        }
        if ((buttons[3].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[5].getText().equals("X"))) {
            xWins(3,4,5);
        }
        if ((buttons[6].getText().equals("X")) && (buttons[7].getText().equals("X")) && (buttons[8].getText().equals("X"))) {
            xWins(6,7,8);
        }
        if ((buttons[0].getText().equals("X")) && (buttons[3].getText().equals("X")) && (buttons[6].getText().equals("X"))) {
            xWins(0,3,6);
        }
        if ((buttons[1].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[7].getText().equals("X"))) {
            xWins(1,4,7);
        }
        if ((buttons[2].getText().equals("X")) && (buttons[5].getText().equals("X")) && (buttons[8].getText().equals("X"))) {
            xWins(2,5,8);
        }
        if ((buttons[0].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[8].getText().equals("X"))) {
            xWins(0,4,8);
        }
        if ((buttons[2].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[6].getText().equals("X"))) {
            xWins(2,4,6);
        }
        //O conditions 
        if ((buttons[0].getText().equals("O")) && (buttons[1].getText().equals("O")) && (buttons[2].getText().equals("O"))) {
            oWins(0,1,2);
        }
        if ((buttons[3].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[5].getText().equals("O"))) {
            oWins(3,4,5);
        }
        if ((buttons[6].getText().equals("O")) && (buttons[7].getText().equals("O")) && (buttons[8].getText().equals("O"))) {
            oWins(6,7,8);
        }
        if ((buttons[0].getText().equals("O")) && (buttons[3].getText().equals("O")) && (buttons[6].getText().equals("O"))) {
            oWins(0,3,6);
        }
        if ((buttons[1].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[7].getText().equals("O"))) {
            oWins(1,4,7);
        }
        if ((buttons[2].getText().equals("O")) && (buttons[5].getText().equals("O")) && (buttons[8].getText().equals("O"))) {
            oWins(2,5,8);
        }
        if ((buttons[0].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[8].getText().equals("O"))) {
            oWins(0,4,8);
        }
        if ((buttons[2].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[6].getText().equals("O"))) {
            oWins(2,4,6);
        }
        //tie
        step++;
        if (step==9){
            nobodyWins();
        }

    }

    public void xWins(int a,int b,int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("X Wins!");
        restartButton.setVisible(true);
       // restart();
    }

    public void oWins(int a,int b,int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("O Wins!");
        restartButton.setVisible(true);
        //restart();

    }

    public void nobodyWins(){
        for (int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("It's a tie.");
        restartButton.setVisible(true);
       // restart();
    }

    public void restart(){
        restartButton.setVisible(false);
        step=0;
        for (int i=0;i<9;i++){
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(Color.white);
        }
        firstTurn();
    }

}
