import java.util.*;

public class Pair {
    private int row;
    private int column;

    public Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Test if two pairs are the same
     * @param o the other pair
     * @return ture for equal, false for not equal
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Pair pair = (Pair)o;
            return this.row == pair.row && this.column == pair.column;
        } else {
            return false;
        }
    }

    /**
     * Create a hashcode for a pair
     * @return hashcode by int
     */
    public int hashCode() {
        return Objects.hash(new Object[]{this.row, this.column});
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    /**
     * Add two pair
     * @param that another pair
     * @return a new pair as the result
     */
    public Pair plus(Pair that) {
        return new Pair(this.row + that.row, this.column + that.column);
    }

    /**
     * Test is the pair out of board
     * @return true if it's on board, false if it's out of board
     */
    public boolean isOnBoard() {
        return this.row >= 0 && this.row < 8 && this.column >= 0 && this.column < 8;
    }

    /**
     * Get the opposite pair of current pair
     * @return a new pair as the opposite one
     */
    public Pair opposite() {
        return new Pair(-this.row, -this.column);
    }
}
