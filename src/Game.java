public class Game {
    private Piece[][] board = new Piece[8][8];

    public Game() {
        init_board();
    }
    public void init_board() {
        //White
        for (int i = 1; i < 7; i++) {
            this.board[0][i] = new Piece('w');
            this.board[7][i] = new Piece('w');
        }

        //Black
        for (int i = 1; i < 7; i++) {
            this.board[i][0] = new Piece('b');
            this.board[i][7] = new Piece('b');
        }
    }

    public Piece[][] getBoard() {
        return this.board;
    }
}
