public class Location {
    private int x;
    private int y;

    public Location(int a, int b) {
        this.x = a;
        this.y = b;
    }

    public int[] getLocation() {
        return new int[] {this.x, this.y};
    }
}
