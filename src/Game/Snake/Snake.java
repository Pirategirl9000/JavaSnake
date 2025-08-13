package Game.Snake;

import java.awt.Color;
import java.util.ArrayList;

public class Snake {
    /**
     * Color of the Snake
     */
    public Color color = Color.RED;

    /**
     * Speed in pixels/tick of the snake
     */
    private final int SPEED;

    /**
     * Height of a segment in pixels, should align on a 1920x1080 display
     */
    private final int SEGMENTWIDTH;

    /**
     * Height of a segment in pixels, should align on a 1920x1080 display
     */
    private final int SEGMENTHEIGHT;

    /**
     * Arraylist of all the segments of the snake<br>
     * segments[0] = head of snake
     */
    public ArrayList<Segment> segments = new ArrayList<>();
    /**
     * Current x-velocity of the snake, has three states:<br>
     * [left] = -SPEED<br>
     * [Start of Game] = 0<br>
     * [right] = SPEED
     */
    private int xVel = 0;

    /**
     * Current y-velocity of the snake, has three states:<br>
     * [up] = -SPEED<br>
     * [Start of Game] = 0<br>
     * [down] = SPEED
     */
    private int yVel = 0;

    /**
     * Stores all previous positions of the head<br>
     * This is how the other segments know where to go next
     */
    public ArrayList<Integer[]> positions = new ArrayList<>();

    /**
     * Creates a new snake with a set speed, width, height, and head position
     * @param speed speed in pixels/tick
     * @param segmentWidth width of segment in pixels
     * @param segmentHeight height of segment in pixels
     * @param x x-position of the head
     * @param y y-position of the head
     */
    public Snake(int speed, int segmentWidth, int segmentHeight, int x, int y) {
        SPEED = speed;
        segments.add(new Segment(x, y));
        positions.add(new Integer[]{x, y});
        SEGMENTWIDTH = segmentWidth;
        SEGMENTHEIGHT = segmentHeight;
    }

    /**
     * Creates a new snake with a set speed, width, and height with a default head position
     * @param speed speed in pixels/tick
     * @param segmentWidth width of segment in pixels
     * @param segmentHeight height of segment in pixels
     */
    public Snake(int speed, int segmentWidth, int segmentHeight) {
        SPEED = speed;
        segments.add(new Segment(40, 40));
        positions.add(new Integer[] {40, 40});
        SEGMENTWIDTH = segmentWidth;
        SEGMENTHEIGHT = segmentHeight;
    }

    /**
     * Creates a new snake with default speed, width, height, and head position
     */
    public Snake() {
        SPEED = 20;
        segments.add(new Segment(40, 40));
        positions.add(new Integer[] {40, 40});
        SEGMENTWIDTH = 20;
        SEGMENTHEIGHT = 20;
    }

    /**
     * Returns the height of a segment within the snake
     * @return height of segment
     */
    public int getHeight() { return SEGMENTHEIGHT; }

    /**
     * Returns the width of a segment within the snake
     * @return width of segment
     */
    public int getWidth() { return SEGMENTWIDTH; }

    /**
     * Moves the snake and it's segments forward one game tick in accordance with its current direction
     */
    public void move() {
        Segment head = segments.get(0);
        int newX = head.getX() + xVel;
        int newY = head.getY() + yVel;
        head.move(newX, newY);

        for (int i = 1; i < segments.size(); i++) {
            Integer[] newPosition = positions.get(i-1);
            segments.get(i).move(newPosition[0], newPosition[1]);
        }

        // Collision logic should go here

        positions.add(new Integer[]{newX, newY});  // Add the new head position to the list of positions
        positions.remove(0);
    }


    /**
     * Changes the direction of the snake
     * <br>
     * Valid Directions:
     * <ul>
     *     <li>up</li>
     *     <li>down</li>
     *     <li>left</li>
     *     <li>right</li>
     * </ul>
     * @param direction New direction for the snake to travel
     * @throws IllegalArgumentException if direction is invalid
     */
    public void changeDirection(String direction) throws IllegalArgumentException {
        boolean isOneSegment = segments.size() == 1;

        switch (direction.toLowerCase()) {
            case "up":
                xVel = 0;
                yVel = (isOneSegment || yVel != 1) ? -SPEED : yVel;  // If snake is 1 segment long, or it isn't facing down then it can go up
                break;
            case "down":
                xVel = 0;
                yVel = (isOneSegment || yVel != -1) ? SPEED : yVel;  // If snake is 1 segment long, or it isn't facing up then it can go down
                break;
            case "left":
                xVel = (isOneSegment || xVel != 1) ? -SPEED : xVel;  // If Game.Snake is 1 segment long, or it isn't facing right then it can go left
                yVel = 0;
                break;
            case "right":
                xVel = (isOneSegment || xVel != -1) ? SPEED : xVel;  // If Game.Snake is 1 segment long, or it isn't facing left then it can go right
                yVel = 0;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
}
