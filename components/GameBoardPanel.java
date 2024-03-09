package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel{
    private GameResetListener resetListener;

    private JLabel playerLabel;
    private JTextArea gameLog;
    private JButton resetButton;
    private JPanel scorePanel;

    private JLabel scoreLabel1, scoreLabel2;
    private JTextField scoreBoard1, scoreBoard2;

    private String currentPlayer = "Player 1";
    private int player1Score = 0;
    private int player2Score = 0;

    public GameBoardPanel(){
        initializeComponents();
    }

    private void initializeComponents(){
        playerLabel = new JLabel("Current Player:" + currentPlayer);
        gameLog = new JTextArea(20,15);
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
        gameLog.setWrapStyleWord(true);
        JScrollPane logScrollPane = new JScrollPane(gameLog);
        logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scoreLabel1 = new JLabel ("Player 1");
        scoreLabel2 = new JLabel ("Player 2");

        scoreBoard1 = new JTextField(Integer.toString(player1Score));
        scoreBoard1.setEditable(false);
        scoreBoard2 = new JTextField(Integer.toString(player2Score));
        scoreBoard2.setEditable(false);

        resetButton = new JButton("Reset Game");
        resetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                resetGame();
            }
        });

        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(2,3,10,10));
        scorePanel.add(scoreLabel1);
        scorePanel.add(new JLabel());
        scorePanel.add(scoreLabel2);

        scorePanel.add(scoreBoard1);
        scorePanel.add(new JLabel());
        scorePanel.add(scoreBoard2);

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());
        centralPanel.add(scorePanel, BorderLayout.NORTH);
        centralPanel.add(logScrollPane, BorderLayout.CENTER);
        centralPanel.setPreferredSize(new Dimension(175, centralPanel.getPreferredSize().height));


        setLayout(new BorderLayout());
        add(playerLabel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);


    }

    public void setResetButtonListener(ActionListener listener){
        resetButton.addActionListener(listener);
    }
    private void resetGame(){
        player1Score =0;
        player2Score =0;
        gameLog.setText("");

        //Notify listner (if set) that the game is about to reset.
        if(resetListener != null){
            resetListener.onResetGame();
        }
        currentPlayer = "Player 1";
        updateDisplay();
        log("Game Reset");
    }

    public void updateDisplay(){
        playerLabel.setText("Current Player: "+ currentPlayer);
        scoreBoard1.setText(Integer.toString(player1Score));
        scoreBoard2.setText(Integer.toString(player2Score));
    }
    public void log(String message){
        gameLog.append(message+"\n");
    }
    public void updateScore(String player, int points){
        if (player.equals("Player 1")){
            player1Score += points;
        }else if(player.equals("Player 2")){
            player2Score += points;
        }

        log(player + "scored" + points + " points.");
        updateDisplay();
    }
    public void switchPlayer(){
        currentPlayer = (currentPlayer.equals("Player 1"))? "Player 2": "Player 1";
        log("Turn switched to "+ currentPlayer);
        updateDisplay();
    }

}
