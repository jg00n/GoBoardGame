import components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WeiChi {
    private static JFrame frame;
    private static Board weiChiBoard;
    private static GameBoardGUI gameBoardGUI;
    private static char currentPlayerColor ='B';
    private static JLabel turnLabel =new JLabel();

    public static void main(String[] args){
        SwingUtilities.invokeLater(()-> createAndShowGUI());
    }

    private static void createAndShowGUI(){
        frame = new JFrame("Wei-Chi Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        weiChiBoard = new Board();
        gameBoardGUI = new GameBoardGUI(weiChiBoard, Board.SQUARE_SIZE, Board.START_POS, Board.START_POS);

        JButton resetButton = new JButton("Reset Board");
        resetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                weiChiBoard.resetBoard();
                gameBoardGUI.repaint();
                resetPlayerTurnLabel();
            }
        });
        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));

        //Set Layout
        FlowLayout flowLayout = new FlowLayout();
        frame.setLayout(flowLayout);
        System.out.println("GameGUI size: "+ gameBoardGUI.getSize());
        frame.getContentPane().add(gameBoardGUI, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.add(resetButton);
        frame.add(turnLabel);

        //initialize
        resetPlayerTurnLabel();

        gameBoardGUI.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int row = (e.getY() - gameBoardGUI.startY)/gameBoardGUI.squareSize;
                int col = (e.getX() - gameBoardGUI.startX)/gameBoardGUI.squareSize;

                if(weiChiBoard.getPiece(row, col) ==' '){
                    weiChiBoard.placePiece(row, col, currentPlayerColor);
                    updatePlayerTurnLabel();
                    gameBoardGUI.repaint();
                }
            }
        });

        frame.setVisible(true);
    }
   
    private static void updatePlayerTurnLabel(){
        //Queues up prompt for player turn 
        String playerTurnText = (currentPlayerColor =='B')? "Player 2's Turn": "Player 1's Turn";
        frame.setTitle("WeiChi Game - "+ playerTurnText);
        turnLabel.setText(playerTurnText);
        currentPlayerColor = (currentPlayerColor =='B') ?'W': 'B'; //Switch players.
    }
    private static void resetPlayerTurnLabel(){
        frame.setTitle("WeiChi Game - Player 1's Turn");
        turnLabel.setText("Player 1's Turn.");
        currentPlayerColor = 'B';
    }
}
