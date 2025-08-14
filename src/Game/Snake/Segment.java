package Game.Snake;

/**
 * Stores basic functions for segments of food or snake
 */
public abstract class Segment {
    private final int[] location = new int[2];

    /**
     * Returns the location of the item as an array
     * @return [x, y]
     */
    public int[] getLocation() {
        return location;
    }

    public int getX() {
        return location[0];
    }

    public int getY() {
        return location[1];
    }
}
