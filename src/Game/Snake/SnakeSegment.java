package Game.Snake;

/**
 * Defines a single segment of the snake
 */
public class SnakeSegment extends Segment {

    /**
     * Creates a new segment with the specified x and y coordinates
     * @param x x-position of the segment
     * @param y y-position of the segment
     */
    public SnakeSegment(int x, int y) {
        super.setLocation(x,y);
    }

    /**
     * Creates a new segment with the specified x and y coordinates
     * @param coords [x, y] position of the segment
     */
    public SnakeSegment(Integer[] coords) { this(coords[0], coords[1]); }

    /**
     * Creates a new segment with the specified x and y coordinates
     * @param coords [x, y] position of the segment
     */
    public SnakeSegment(int[] coords) { this(coords[0], coords[1]); }

    /**
     * Moves the segment to the specified position
     * @param x x-position of the segment
     * @param y y-position of the segment
     */
    protected void move(int x, int y) {
        setLocation(x,y);
    }

    /**
     * Moves the segment to the specified position
     * @param coords Integer array storing the new [x, y] coordinates of the segment
     */
    protected void move(Integer[] coords) {
        move(coords[0], coords[1]);
    }

    /**
     * Gives the x and y position of the segment as a string
     * @return string representation of the segment's position
     */
    @Override
    public String toString() {
        return "Segment [x=" + location[0] + ", y=" + location[1] + "]";
    }


}