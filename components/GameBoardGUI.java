package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameBoardGUI extends JPanel {
    private Board board;
    public int squareSize;
    public int startX;
    public int startY;

    public GameBoardGUI(Board board, int squareSize, int startX, int startY) {
        this.board = board;
        this.squareSize = squareSize;
        this.startX = startX;
        this.startY = startY;

        // Set preferred size initially
        setPreferredSize(Board.BOARD_DIMENSION);

        // Add a component listenter to handle resizing.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Recalculate preferred size on resize
                setPreferredSize(Board.BOARD_DIMENSION);
                revalidate();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("Painting GameBoard GUI");
        super.paintComponent(g);
        board.renderBoard(g, squareSize, startX, startY);
    }
}