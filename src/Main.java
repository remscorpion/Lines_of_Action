import java.awt.*;

public class Main {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(512, 640);
        StdDraw.setXscale(0,9);
        StdDraw.setYscale(11,0);

        Game g = new Game();

        drawPiece(g.getBoard());

    }

    public static void drawPiece(Piece[][] board) {
        drawBackground();

        // draw the pieces
        // *** All coordinates need to increase by 1 to fit the canvas
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                if (board[y][x] != null) {
                    if (board[y][x].getColor() == 'b') {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.filledCircle(x+1, y+1, 0.3);
                    } else {
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledCircle(x+1, y+1, 0.3);
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.circle(x+1, y+1, 0.3);
                    }
                }
            }
        }
    }

    public static void drawBackground() {
        // background
        for (int i = 1 ; i < 9; i++) {
            // white start
            if (i%2 == 0) {
                for (int j = 1; j < 9; j++) {
                    if (j % 2 == 0) {
                        StdDraw.setPenColor(StdDraw.WHITE);
                    } else {
                        StdDraw.setPenColor(StdDraw.GRAY);
                    }
                    StdDraw.filledSquare(j, i, 0.5);
                } // end for j
            } else {
                for (int j = 1; j < 9; j++) {
                    if (j % 2 == 0) {
                        StdDraw.setPenColor(StdDraw.GRAY);
                    } else {
                        StdDraw.setPenColor(StdDraw.WHITE);
                    }
                    StdDraw.filledSquare(j, i, 0.5);
                }  // end for j
            }  // end if else
        }  // end for i

        // lines
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < 9; i++) {
            StdDraw.line(0.5, i+0.5, 8.5, i+0.5);
            StdDraw.line(i+0.5, 0.5, i+0.5, 8.5);
        }
    }
}