package Snake;

import java.awt.Color;
import java.util.ArrayList;

public class Snake {
    /**
     * Color of the Snake
     */
    public Color color = Color.RED;

    private final int SPEED;

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
     * Creates a new snake and adds a head segment at Snake.segments[0]
     */
    public Snake(int speed) {
        SPEED = speed;
        segments.add(new Segment(40, 40));
    }

    public void move() {
        Segment head = segments.get(0);
        head.move(head.getX() + xVel, head.getY() + yVel);






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
                xVel = (isOneSegment || xVel != 1) ? -SPEED : xVel;  // If Snake is 1 segment long, or it isn't facing right then it can go left
                yVel = 0;
                break;
            case "right":
                xVel = (isOneSegment || xVel != -1) ? SPEED : xVel;  // If Snake is 1 segment long, or it isn't facing left then it can go right
                yVel = 0;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
}
