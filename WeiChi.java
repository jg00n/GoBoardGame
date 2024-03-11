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
    private static GameBoardPanel gamePanel;
    private static char currentPlayerColor = 'B';
    

    public static void main(String[] args) {
        DebugSettings.setDebug(false);
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Wei-Chi Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weiChiBoard = new Board();
        gameBoardGUI = new GameBoardGUI(weiChiBoard, Board.SQUARE_SIZE, Board.START_POS, Board.START_POS);
        gamePanel = new GameBoardPanel();

        // Set Layout
        FlowLayout flowLayout = new FlowLayout();
        frame.setLayout(flowLayout);
        System.out.println("GameGUI size: " + gameBoardGUI.getSize());
        frame.getContentPane().add(gameBoardGUI, BorderLayout.CENTER);
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);

        // initialize
        resetPlayerTurnLabel();

        gamePanel.setResetButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        gameBoardGUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = (e.getY() - gameBoardGUI.startY) / gameBoardGUI.squareSize;
                int col = (e.getX() - gameBoardGUI.startX) / gameBoardGUI.squareSize;

                if (weiChiBoard.getPiece(row, col) == ' ') {
                    weiChiBoard.placePiece(row, col, currentPlayerColor);
                    char currentPlayer = (currentPlayerColor == 'B') ? '1' : '2';
                    gamePanel.log("Player " + currentPlayer + " placed Piece at [" + row + ',' + col + "]");
                    updatePlayerTurnLabel();
                    gameBoardGUI.repaint();
                }
            }
        });
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private static void resetGame() {
        weiChiBoard.resetBoard();
        gameBoardGUI.repaint();
        resetPlayerTurnLabel();
    }

    private static void updatePlayerTurnLabel() {
        // Queues up prompt for player turn
        String playerTurnText = (currentPlayerColor == 'B') ? "Player 2's Turn" : "Player 1's Turn";
        frame.setTitle("WeiChi Game - " + playerTurnText);
        gamePanel.switchPlayer();
        currentPlayerColor = (currentPlayerColor == 'B') ? 'W' : 'B'; // Switch players.
    }

    private static void resetPlayerTurnLabel() {
        frame.setTitle("WeiChi Game - Player 1's Turn");
        currentPlayerColor = 'B';
    }
}
