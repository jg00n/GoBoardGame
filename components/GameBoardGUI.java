package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameBoardGUI extends JPanel {
    private Board board;
    private int borderSize = 20;
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
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String timestamp = dateFormat.format(now);
        Color mahogany = new Color(0x764332);
        System.out.printf("[%s] - Rendered Board%n", timestamp);
        super.paintComponent(g);
        g.setColor(mahogany);
        g.fillRect(startX - borderSize, startY - borderSize, Board.BOARD_DIMENSION.width + 2 * borderSize,
                Board.BOARD_DIMENSION.height + 2 * borderSize);

        board.renderBoard(g, squareSize, (startX - borderSize / 2), (startY - borderSize / 2));
    }
}