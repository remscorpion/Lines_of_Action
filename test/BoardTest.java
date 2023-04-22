/**
 * @author TOMOHIRO & ED
 * Still watting for ED's test functions
 * But the game is good to play
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void countPieces() {

    }

    @Test
    void findMoveInDirection() {
    }

    @Test
    void findAllLegalMovesFrom() {
    }

    @Test
    void testCountPieces() {
        Board b = new Board(".###....\n........\n........\n........\n........\n........\n........\n.OOO....\n");
        int w = b.countPieces('O');
        int bl = b.countPieces('#');
        assertEquals(w, bl);
    }

    @Test
    void hasWon() {
        Board white_win = new Board(".O......\n........\n........\n........\n........\n........\n........\n........\n");
        assertTrue(white_win.hasWon('O'));
        assertFalse(white_win.hasWon('#'));

        Board black_win = new Board(".#......\n........\n........\n........\n........\n........\n........\n........\n");
        assertTrue(black_win.hasWon('#'));
        assertFalse(black_win.hasWon('O'));

        Board white_win2 = new Board(".O......\n.O......\n.O......\n#.......\n........\n....#...\n......#\n#.......\n");
        assertTrue(white_win2.hasWon('O'));
        assertFalse(white_win2.hasWon('#'));
    }

    @Test
    void findWinner() {
        Board white_win = new Board(".O......\n........\n........\n........\n........\n........\n........\n........\n");
        assertEquals('O', white_win.findWinner());
        Board black_win = new Board(".#......\n........\n........\n........\n........\n........\n........\n........\n");
        assertEquals('#', black_win.findWinner());
        Board tie = new Board(".###....\n........\n........\n........\n........\n........\n........\n.OOO....\n");
        assertEquals('t', tie.findWinner());
    }
}