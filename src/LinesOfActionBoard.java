import java.util.*;

public class LinesOfActionBoard {
    public static final List<Pair> directions = new ArrayList();
    static {
        directions.add(new Pair(-1,  1));
        directions.add(new Pair(-1,  0));
        directions.add(new Pair(-1, -1));
        directions.add(new Pair( 0,  1));
        directions.add(new Pair( 0, -1));
        directions.add(new Pair( 1,  1));
        directions.add(new Pair( 1,  0));
        directions.add(new Pair( 1, -1));
    }
    private char[][] board;
    private char currentPlayer;

    /**
     * O -> white
     * # -> black
     * . -> empty
     */


    public char opposite(char color) {
        return (char)(color == '#' ? 'O' : '#');
    }

    public LinesOfActionBoard(String diagram) {
        this.board = new char[8][8];
        int i = 0;

        for(int r = 0; r < 8; ++r) {
            for(int c = 0; c < 8; ++c) {
                this.board[r][c] = diagram.charAt(i);
                ++i;
            }

            ++i;
        }

        this.currentPlayer = 'O';
    }

    public LinesOfActionBoard() {
        this(".OOOOOO.\n#......#\n#......#\n#......#\n#......#\n#......#\n#......#\n.OOOOOO.\n");
    }

    public String toString() {
        String result = "";

        for(int r = 0; r < 8; ++r) {
            for(int c = 0; c < 8; ++c) {
                result = result + this.board[r][c];
            }

            result = result + "\n";
        }

        return result;
    }

    /**
     * Get the pair on the board as a char
     * @param p the pair
     * @return a char that represent the pair
     */
    public char get(Pair p) {
        return this.board[p.getRow()][p.getColumn()];
    }

    /**
     * count how many pairs are there for the given direction
     * @param location location of the current pair
     * @param direction the direction
     * @return
     */
    public int countPieces(Pair location, Pair direction) {
        int result = 0;

        Pair p;
        for(p = location; p.isOnBoard(); p = p.plus(direction)) {
            if (this.get(p) != '.') {
                ++result;
            }
        }

        // opposite way
        direction = direction.opposite();

        for(p = location.plus(direction); p.isOnBoard(); p = p.plus(direction)) {
            if (this.get(p) != '.') {
                ++result;
            }
        }

        return result;
    }

    public Pair findMoveInDirection(Pair location, Pair direction) {
        char friend = this.get(location);
        char enemy = this.opposite(friend);
        int distance = this.countPieces(location, direction);

        for(int i = 1; i <= distance; ++i) {
            location = location.plus(direction);
            if (!location.isOnBoard()) {
                return null;
            }

            if (this.get(location) == enemy) {
                if (i == distance) {
                    return location;
                }

                return null;
            }
        }

        if (this.get(location) == friend) {
            return null;
        } else {
            return location;
        }
    }

    public Set<Pair> findAllLegalMovesFrom(Pair location) {
        Set<Pair> result = new HashSet();
        Iterator i = directions.iterator();

        while(i.hasNext()) {
            Pair direction = (Pair)i.next();
            Pair there = this.findMoveInDirection(location, direction);
            if (there != null) {
                result.add(there);
            }
        }

        return result;
    }

    public void set(Pair location, char color) {
        this.board[location.getRow()][location.getColumn()] = color;
    }

    public void move(Pair source, Pair destination) {
        this.set(destination, this.get(source));
        this.set(source, '.');
        this.currentPlayer = this.opposite(this.currentPlayer);
    }

    public char getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int countPieces(char color) {
        int result = 0;

        for(int r = 0; r < 8; ++r) {
            for(int c = 0; c < 8; ++c) {
                if (this.board[r][c] == color) {
                    ++result;
                }
            }
        }

        return result;
    }

    public boolean hasWon(char color) {
        int all = this.countPieces(color);

        for(int r = 0; r < 8; ++r) {
            for(int c = 0; c < 8; ++c) {
                if (this.board[r][c] == color) {
                    int count = this.findGroupSize(new Pair(r, c), new boolean[8][8]);
                    if (count == all) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int findGroupSize(Pair here, boolean[][] visited) {
        int sum = 1;
        visited[here.getRow()][here.getColumn()] = true;
        char color = this.get(here);
        Iterator var5 = directions.iterator();

        while(var5.hasNext()) {
            Pair direction = (Pair)var5.next();
            Pair neighbor = here.plus(direction);
            if (neighbor.isOnBoard() && this.get(neighbor) == color && !visited[neighbor.getRow()][neighbor.getColumn()]) {
                sum += this.findGroupSize(neighbor, visited);
            }
        }

        return sum;
    }

    public char findWinner() {
        boolean w = this.hasWon('O');
        boolean b = this.hasWon('#');
        if (w) {
            return (char)(b ? 't' : 'O');
        } else {
            return (char)(b ? '#' : '.');
        }
    }
}
