package Game.Snake;

/**
 * Stores basic functions for segments of food or snake
 */
public abstract class Segment {
    protected final int[] location = new int[2];

    /**
     * Creates a new Segment with setLocation(x, y)
     * @param x x position of Segment
     * @param y y position of Segment
     */
    public Segment(int x, int y) {
        setLocation(x, y);
    }

    /**
     * Sets the location of the segment
     * @param x x position of Segment
     * @param y y position of Segment
     */
    public void setLocation(int x, int y) {
        location[0] = x;
        location[1] = y;
    }

    /**
     * Returns the location of the item as an array
     * @return [x, y]
     */
    public int[] getLocation() {
        return location;
    }

    /**
     * Returns the x position of the item
     * @return x-position
     */
    public int getX() {
        return location[0];
    }

    /**
     * Returns the y position of the item
     * @return y-position
     */
    public int getY() {
        return location[1];
    }

    /**
     * Gives the x and y position of the item as a string
     * @return string representation of the item's position
     */
    @Override
    public String toString() {
        return "Segment [x=" + location[0] + ", y=" + location[1] + "]";
    }

    /**
     * Checks to see if two segment objects are overlapping
     * @param obj the object to compare it to
     * @return overlapping?
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Segment) {
            Segment item = (Segment) obj;

            return (this.getX() == item.getX()) && (this.getY() == item.getY());  // Checks if they have overlapping x and y coordinates
        }

        return false;
    }

}
