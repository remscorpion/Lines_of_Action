import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    @org.junit.jupiter.api.Test
    void testEquals() {
        Pair a = new Pair(7,5);
        Pair b = new Pair(7,5);
        assertTrue(a.equals(b));
        Pair c = new Pair(3,1);
        Pair d = new Pair(5,2);
        assertFalse(c.equals(d));
    }

    @org.junit.jupiter.api.Test
    void getRow() {
        for (int i = 0; i < 7; i++) {
            int n = StdRandom.uniformInt(0,7);
            Pair p = new Pair(n,i);
            assertEquals(n,p.getRow());
        }
    }

    @org.junit.jupiter.api.Test
    void getColumn() {
        for (int i = 0; i < 7; i++) {
            int n = StdRandom.uniformInt(0,7);
            Pair p = new Pair(i,n);
            assertEquals(n,p.getColumn());
        }
    }

    @org.junit.jupiter.api.Test
    void plus() {
        for (int i = 0; i < 25; i++) {
            int a = StdRandom.uniformInt(0,100);
            int b = StdRandom.uniformInt(0,100);
            int c = StdRandom.uniformInt(0,100);
            int d = StdRandom.uniformInt(0,100);
            Pair p = new Pair(a,b);
            Pair px = new Pair(c,d);
            Pair pz;
            pz = p.plus(px);
            assertEquals(p.getRow()+px.getRow(),pz.getRow());
            assertEquals(p.getColumn()+px.getColumn(),pz.getColumn());

        }
    }

    @org.junit.jupiter.api.Test
    void isOnBoard() {
        int a = 3, b = 7;
        int c = 16, d = 2;
        Pair p = new Pair(a,b);
        Pair px = new Pair(c,d);
        assertTrue(p.isOnBoard());
        assertFalse(px.isOnBoard());
    }

    @org.junit.jupiter.api.Test
    void opposite() {
        for (int i = 0; i < 25; i++) {
            int a = StdRandom.uniformInt(0,8);
            int b = StdRandom.uniformInt(0,10);
            Pair p = new Pair(a,b);
            p = p.opposite();
            assertEquals(-a,p.getRow());
            assertEquals(-b,p.getColumn());
        }
    }
}