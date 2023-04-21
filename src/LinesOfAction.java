import java.awt.*;
import java.util.*;

public class LinesOfAction {
    private Board board = new Board();
    /**
     * state
     * 0 selecting
     * 1 moving
     * 2 game over
     */
    private int state;
    private Pair selectedSquare;
    private Set<Pair> legalMovements;

    public static void main(String[] args) {
        (new LinesOfAction()).run();
    }

    public LinesOfAction() {
    }

    public void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(512, 576);
        StdDraw.setXscale(-0.5, 7.5);
        StdDraw.setYscale(-0.5, 8.5);
        this.draw();

        while(this.board.findWinner() == '.') {
            this.handleMouseClick();
            this.draw();
        }

    }

    public void handleMouseClick() {
        while(!StdDraw.isMousePressed()) {
        }

        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();

        while(StdDraw.isMousePressed()) {
        }

        int r = (int)Math.round(8.0 - y);
        int c = (int)Math.round(x);

        Pair here = new Pair(r, c);
        if (this.state == 0) {
            if (this.board.get(here) == this.board.getCurrentPlayer()) {
                this.legalMovements = this.board.findAllLegalMovesFrom(here);
                if (!this.legalMovements.isEmpty()) {
                    this.selectedSquare = here;
                    this.state = 1;
                }
            }
        } else if (this.state == 1) {
            if (this.legalMovements.contains(here)) {
                this.board.move(this.selectedSquare, here);
            }

            this.state = 0;
        }

        if (this.board.findWinner() != '.') {
            this.state = 2;
        }

    }

    public String colorToName(char color) {
        return color == 'O' ? "White" : "Black";
    }

    public void draw() {
        StdDraw.clear(StdDraw.BLACK);
        this.drawSquaresAndPieces();
        this.drawMessage();
        StdDraw.show();
    }

    public void drawMessage() {
        String message;
        /*
          state
          0 selecting
          1 moving
          2 game over
         */
        switch (this.state) {
            case 0 -> message = this.colorToName(this.board.getCurrentPlayer()) + ": select a piece to move";
            case 1 -> message = this.colorToName(this.board.getCurrentPlayer()) + ": select destination";
            case 2 -> {
                char winner = this.board.findWinner();
                if (winner == 't') {
                    message = "Tie!";
                } else {
                    String str = this.colorToName(winner);
                    message = str + " wins!";
                }
            }
            default -> message = "Invalid state!";
        }

        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(3.5, 0.0, message);
    }

    public void drawSquaresAndPieces() {
        for(int r = 0; r < 8; ++r) {
            for(int c = 0; c < 8; ++c) {
                // choose the color of the pen
                if ((r + c) % 2 == 0) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                } else {
                    StdDraw.setPenColor(StdDraw.GRAY);
                }

                // Find the color of the background of the square
                if (this.state == 1) {
                    Pair here = new Pair(r, c);
                    // selected square
                    if (this.selectedSquare.equals(here)) {
                        StdDraw.setPenColor(StdDraw.BLUE);
                    }
                    // legal movements squares
                    else if (this.legalMovements.contains(here)) {
                        StdDraw.setPenColor(StdDraw.GREEN);
                    }
                }

                // fill up the background color
                StdDraw.filledSquare((double)c, (double)(8 - r), 0.5);

                // draw the pair
                char piece = this.board.get(new Pair(r, c));
                if (piece == '#') {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledCircle((double)c, (double)(8 - r), 0.3);
                } else if (piece == 'O') {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledCircle((double)c, (double)(8 - r), 0.3);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.circle((double)c, (double)(8 - r), 0.3);
                }

            } // end for c
        }  // end for r
    }
}
