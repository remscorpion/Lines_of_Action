

public class Main {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(512, 640);
        StdDraw.setXscale(0,9);
        StdDraw.setYscale(11,0);

        Board g = new Board();

        drawPiece(g.getBoard());
        drawNotification(g.getTurn(), g.getStatus());
    }

    /**
     * Draw the pieces
     * @param board A array that has all pieces' location
     */
    public static void drawPiece(int[][] board) {
        drawBackground();

        // draw the pieces
        // *** All coordinates need to increase by 1 to fit the canvas
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                if (board[y][x] != 0) {
                    if (board[y][x] == -1) {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.filledCircle(x+1, y+1, 0.3);
                    } else if (board[y][x] == 1) {
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledCircle(x+1, y+1, 0.3);
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.circle(x+1, y+1, 0.3);
                    }
                }
            }
        }

    }

    /**
     * Draw the game board
     */
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

    /**
     * Draw the hint board
     * @param turn A boolean tells which player's turn
     * @param status A int shows the status of the game
     */
    public static void drawNotification(boolean turn, int status) {
        double x = 4.5;
        double y = 10;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(x, y, x, 1);
        System.out.printf("Status is %d\nTurn is %b\n", status, turn);

        StdDraw.setPenColor(StdDraw.WHITE);
        switch (status) {
            case 0 -> {
                if (turn) {
                    StdDraw.text(x, y, "It's white player's turn");
                } else {
                    StdDraw.text(x, y, "It's black player's turn");
                }
            }
            case 1 -> StdDraw.text(x, y, "White player WIN!");
            case 2 -> StdDraw.text(x, y, "Black player WIN!");
            case -1 -> StdDraw.text(x, y, "TIE GAME");
        }

    }
}