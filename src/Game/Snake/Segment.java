package Game.Snake;

/**
 * Defines a single segment of the snake
 */
public class Segment {
    /**
     * X-position of the segment
     */
    private int x;

    /**
     * Y-position of the segment
     */
    private int y;


    /**
     * Creates a new segment with the specified x and y coordinates
     * @param x x-position of the segment
     * @param y y-position of the segment
     */
    public Segment(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new segment with the specified x and y coordinates
     * @param coords [x, y] position of the segment
     */
    public Segment(Integer[] coords) { this(coords[0], coords[1]); }

    /**
     * Creates a new segment with the specified x and y coordinates
     * @param coords [x, y] position of the segment
     */
    public Segment(int[] coords) { this(coords[0], coords[1]); }

    /**
     * Gets the x-coordinate of the segment
     * @return x-position of the segment
     */
    public int getX() { return x; }

    /**
     * Gets the y-coordinate of the segment
     * @return y-position of the segment
     */
    public int getY() { return y; }

    /**
     * Moves the segment to the specified position
     * @param x x-position of the segment
     * @param y y-position of the segment
     */
    protected void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the segment to the specified position
     * @param coords Integer array storing the new [x, y] coordinates of the segment
     */
    protected void move(Integer[] coords) { move(coords[0], coords[1]); }

    /**
     * Gives the x and y position of the segment as a string
     * @return string representation of the segment's position
     */
    @Override
    public String toString() {
        return "Segment [x=" + x + ", y=" + y + "]";
    }
}