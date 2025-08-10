package Snake;

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

    @Override
    public String toString() {
        return "Segment [x=" + x + ", y=" + y + "]";
    }


}