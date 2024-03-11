package components;

import java.awt.*;
import java.awt.geom.Point2D;

public class Board {
    // Size of board
    final static int DEFAULT_SIZE = 19;
    private int size;
    private Piece[][] gameBoard;
    // Size of rendered tiles.
    public final static int SQUARE_SIZE = 40;
    public final static int START_POS = 50;
    public final static int BOARD_WIDTH = (DEFAULT_SIZE * SQUARE_SIZE + START_POS);
    public final static Dimension BOARD_DIMENSION = new Dimension(
            BOARD_WIDTH,
            BOARD_WIDTH);

    public Board() {
        this.size = (DEFAULT_SIZE);
        initializeBoard();
    };

    public void resetBoard() {
        initializeBoard();
    };

    private void initializeBoard() {
        gameBoard = new Piece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameBoard[i][j] = new Piece(' ');
            }
        }
    }

    public void renderBoard(Graphics g, int square_size, int start_x, int start_y) {
        Color woodColor = new Color(204, 119, 34);
        // Draw the in game tiles.
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int x = start_x + col * square_size;
                int y = start_y + row * square_size;
                // determine position as N,S,E,W, and for corner pieces, 1,2,3,4, otherwise
                // denote C as center tile.
                char position = 'C';
                // If statment to determine the position of edge cases.
                if (row == 0) {
                    position = (col == 0) ? '1' : (col == size - 1) ? '3' : 'N';
                } else if (row == size - 1) {
                    position = (col == 0) ? '2' : (col == size - 1) ? '4' : 'S';
                } else if (col == 0) {
                    position = 'W';
                } else if (col == size - 1) {
                    position = 'E';
                } else {
                    position = 'C';// Default Case
                }

                renderTile(g, x, y, square_size, position, woodColor);

                // Draw the piece inside the square
                char piece = gameBoard[row][col].getPiece();
                if (piece != ' ') {
                    int pieceSize = (int) (square_size * 0.9);
                    int pieceAdjusted = pieceSize - (2 * square_size + 2);
                    int pieceX = x + (square_size - pieceSize) / 2;
                    int pieceY = y + (square_size - pieceSize) / 2;
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Draws piece Border.
                    g.setColor(piece == 'B' ? Color.BLACK : Color.WHITE);
                    g.fillOval(pieceX, pieceY, pieceSize, pieceSize);

                    // Draws the actual Piece.
                    g.setColor(piece == 'B' ? Color.WHITE : Color.BLACK);
                    g.fillOval(pieceX + square_size, pieceY + square_size, pieceAdjusted, pieceAdjusted);

                    // Draw gradient shadow effect
                    int shadowSize = pieceSize;
                    Point2D center = new Point2D.Float(pieceX + shadowSize / 2f, pieceY + shadowSize / 2f);
                    float[] dist = { 0.0f, 1.0f };
                    Color[] colors;
                    // Adjust the colors for the shadow based on piece color
                    if (piece == 'W') {
                        // White piece, gradient from transparent to black
                        colors = new Color[] { new Color(0, 0, 0, 0), new Color(0, 0, 0, 80) };
                    } else {
                        // Black piece, gradientfrom white to transparent
                        colors = new Color[] { new Color(255, 255, 255, 80), new Color(255, 255, 255, 0) };
                    }
                    // to black
                    RadialGradientPaint gradient = new RadialGradientPaint(center, shadowSize / 2f, dist, colors);

                    g2d.setPaint(gradient);
                    g2d.fillOval(pieceX, pieceY, shadowSize, shadowSize);
                }
            }
        }

    }

    private void renderTile(Graphics tile, int x, int y, int square_size, char direction, Color tileColor) {
        Graphics2D tile2D = (Graphics2D) tile;
        int middleX = x + square_size / 2;
        int middleY = y + square_size / 2;
        tile2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw the wood-colored square
        tile2D.setColor(tileColor);
        tile2D.fillRect(x, y, square_size, square_size);
        
        tile2D.setColor(Color.BLACK);
        tile2D.setStroke(new BasicStroke(2.0f));

        if (DebugSettings.isDebug()) {
            switch (direction) {
                case 'N':
                    tile.setColor(Color.RED);
                    break;
                case 'E':
                    tile.setColor(Color.YELLOW);
                    break;
                case 'S':
                    tile.setColor(Color.GREEN);
                    break;
                case 'W':
                    tile.setColor(Color.BLUE);
                    break;
                case '1':
                    tile.setColor(Color.CYAN);
                    break;
                case '2':
                    tile.setColor(Color.MAGENTA);
                    break;
                case '3':
                    tile.setColor(Color.LIGHT_GRAY);
                    break;
                case '4':
                    tile.setColor(Color.DARK_GRAY);
                    break;
                // Add cases for other directions as needed
                default: // Case for C
                    // No specific color for default case
                    break;
            }
        }
        // Determine edge case
        switch (direction) {
            case 'N':
                tile.drawLine(x, middleY, x + square_size, middleY);//horizontal
                tile.drawLine(middleX, middleY, middleX, y + square_size);//vertical
                break;
            case 'E':
                tile.drawLine(x, middleY, middleX, middleY);//horizontal
                tile.drawLine(middleX, y, middleX, y + square_size);//vertical
                break;
            case 'S':
                tile.drawLine(x, middleY, x + square_size, middleY);//horizontal
                tile.drawLine(middleX, y, middleX, middleY);//vertical
                break;
            case 'W':
                tile.drawLine(middleX, middleY, x + square_size, middleY);//horizontal
                tile.drawLine(middleX, y, middleX, y + square_size);//vertical
                break;
            case '1':
                tile.drawLine(middleX, middleY, x + square_size, middleY);//horizontal
                tile.drawLine(middleX, middleY, middleX, y + square_size);//vertical
                break;
            case '2':
                tile.drawLine(middleX, middleY, x + square_size, middleY);//horizontal
                tile.drawLine(middleX, y, middleX, middleY);//vertical
                break;
            case '3':
                tile.drawLine(x, middleY, middleX, middleY);//horizontal
                tile.drawLine(middleX, middleY, middleX, y + square_size);//vertical
                break;
            case '4':
                tile.drawLine(middleX, middleY, x, middleY);//horizontal
                tile.drawLine(middleX, y, middleX, middleY);//vertical
                break;
            default: // Case for C
                tile.drawLine(x, middleY, x + square_size, middleY); // Horizontal line
                tile.drawLine(middleX, y, middleX, y + square_size); // Vertical line
                break;
        }

    }

    public void placePiece(int row, int col, char pieceType) {
        if (isValidPosition(row, col)) {
            gameBoard[row][col].setPiece(pieceType);
        } else {
            System.out.printf("Invalid position:[%d,%d]", row, col);
        }
    }

    public char getPiece(int row, int col) {
        if (isValidPosition(row,col)){
            return gameBoard[row][col].getPiece();
        } else{
            //return null character. (Boilerplate style fix)
            return '\0';
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

}
