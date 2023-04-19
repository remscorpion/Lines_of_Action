import java.util.ArrayList;

public class Board {
    private int[][] board = new int[8][8]; // -1->white 1->black 0->nothing
    private boolean turn; // false: black player turn    true: white player turn

    /*  status of the game
        0  game not end
        1  white player win
        2  black player win
        -1 tie game
     */
    private int status;

    public Board() {
        init_board();
        this.status = 0;
        this.turn = false;
    }
    public void init_board() {
        //White
        for (int i = 1; i < 7; i++) {
            this.board[0][i] = 1;
            this.board[7][i] = 1;
        }

        //Black
        for (int i = 1; i < 7; i++) {
            this.board[i][0] = -1;
            this.board[i][7] = -1;
        }
    }

    public int[][] getBoard() {
        return this.board;
    }

    public boolean getTurn() {
        return this.turn;
    }

    public int getStatus() {return  this.status;}

    /**
     * Check all avail vertically movement
     * @return
     */
    public ArrayList<int[]> checkAvailVertically(int x, int y) {
        ArrayList<int[]> avail = new ArrayList<>();
        int cont = 0;
        for (int i = 7; i >= 0; i--) {
            if (board[i][x] != 0) cont++;
        }
        if (y + cont < 7) avail.add(new int[]{x, y + cont});
        if (y - cont >= 0) avail.add(new int[]{x, y - cont});

        return avail;
    }

    /**
     *
     * @return
     */
    public ArrayList<int[]> checkAvailHorizontally(int x, int y) {
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<int[]> checkAvailDiagonally(int x, int y) {
        return null;
    }
}
