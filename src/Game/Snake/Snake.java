package Game.Snake;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the Snake in its entirety. Holds logic regarding how to move, eat, grow, and die.
 */
public class Snake {
    /**
     * Color of the Snake
     */
    public Color color = Color.RED;

    /**
     * Whether the snake is still alive, used for stopping the gameLoop thread
     */
    private boolean isAlive = true;

    /**
     * Speed in pixels/tick of the snake
     */
    private final int SPEED;

    /**
     * Tracks whether the snake has moved once on this direction. Prevents frame perfect 180's
     */
    private boolean hasMoved = false;

    /**
     * Height of a segment in pixels, should align on a 1920x1080 display
     */
    @SuppressWarnings("SpellCheckingInspection")  // Shhhhhhh
    private final int SEGMENTWIDTH;

    /**
     * Height of a segment in pixels, should align on a 1920x1080 display
     */
    @SuppressWarnings("SpellCheckingInspection")  // I said SHHHHHHHH!
    private final int SEGMENTHEIGHT;

    /**
     * Arraylist of all the segments of the snake<br>
     * segments[0] = head of snake
     */
    public ArrayList<SnakeSegment> segments = new ArrayList<>();
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
     * Stores whether the snake has eaten which is then used to increase the size of the snake on the next tick
     */
    private boolean hasEaten = false;

    /**
     * Width of the game area
     */
    private final int WIDTH;

    /**
     * Height of the game area
     */
    private final int HEIGHT;

    /**
     * Stores information regarding the food
     */
    private final Food food;

    /**
     * Creates a new snake with a set speed, width, height, and head position
     * @param width width of the game area
     * @param height height of the game area
     * @param speed speed in pixels/tick
     * @param segmentWidth width of segment in pixels
     * @param segmentHeight height of segment in pixels
     * @param x x-position of the head
     * @param y y-position of the head
     */
    public Snake(int width, int height, int speed, int segmentWidth, int segmentHeight, int x, int y) {
        WIDTH = width;
        HEIGHT = height;
        SPEED = speed;
        SEGMENTWIDTH = segmentWidth;
        SEGMENTHEIGHT = segmentHeight;
        segments.add(new SnakeSegment(x, y));
        positions.add(new Integer[]{x, y});
        food = new Food(WIDTH, HEIGHT, SEGMENTWIDTH, SEGMENTHEIGHT, segments);
    }

    /**
     * Creates a new snake with a set speed, width, and height with a default head position
     * @param width width of the game area
     * @param height height of the game area
     * @param speed speed in pixels/tick
     * @param segmentWidth width of segment in pixels
     * @param segmentHeight height of segment in pixels
     */
    public Snake(int width, int height, int speed, int segmentWidth, int segmentHeight) {
        this(width, height, speed, segmentWidth, segmentHeight, 40, 40);
    }

    /**
     * Creates a new snake with default speed, width, height, and head position
     * @param width width of the game area
     * @param height height of the game area
     */
    public Snake(int width, int height) {
        this(width, height, 20, 20, 20, 40, 40);
    }

    /**
     * Returns the height of a segment within the snake
     * @return height of segment
     */
    public int getSegmentHeight() { return SEGMENTHEIGHT; }

    /**
     * Returns the width of a segment within the snake
     * @return width of segment
     */
    public int getSegmentWidth() { return SEGMENTWIDTH; }

    /**
     * Return's the food's color
     * @return color of the food
     */
    public Color getFoodColor() { return food.getColor(); }

    /**
     * Returns whether the snake is still alive
     * @return boolean
     */
    public boolean getAlive() { return isAlive; }

    /**
     * Returns the food's position as an array
     * @return [x, y]
     */
    public int[] getFoodPosition() { return food.getLocation(); }

    /**
     * Returns the food's x-position
     * @return x
     */
    public int getFoodX() { return food.getX(); }

    /**
     * Returns the food's y-position
     * @return y
     */
    public int getFoodY() { return food.getY(); }


    /**
     * Moves the snake and it's segments forward one game tick in accordance with its current direction
     */
    public void move() {

        // Move the head
        SnakeSegment head = segments.get(0);
        int newX = head.getX() + xVel;
        int newY = head.getY() + yVel;
        head.move(newX, newY);

        // Move every segments
        for (int i = 1; i < segments.size(); i++) {
            segments.get(i).move(positions.get(i-1));
        }

        // Collision checks
        collisionCheck();

        // If the snake has eaten last frame then we increase it's size by 1, otherwise we pop the last position since we no longer need that position for movement calculations
        if (hasEaten) {
            hasEaten = false;
            addSegment();
        } else {
            positions.remove(positions.size()-1);  // Get rid of the last element
        }

        positions.add(0, new Integer[]{newX, newY});  // Add the new head to the positional array
        hasMoved = true;
    }

    /**
     * Adds a new segment to the snake at the back
     */
    private void addSegment() {
        segments.add(new SnakeSegment(positions.get(positions.size() - 1)));
    }

    /**
     * Checks for collision of snake with objects and handles them accordingly
     */
    private void collisionCheck() {
        SnakeSegment head = segments.get(0);

        // Food collision
        //noinspection EqualsBetweenInconvertibleTypes
        if (head.equals(food)) {
            eat();
        }

        // Body Collision
        for (int i = 1; i < segments.size(); i++) {
            if (head.equals(segments.get(i))) {
                killSnake();
            }
        }

        // Out of Bounds Collision
        if (head.getX() > WIDTH || head.getX() < 0 || head.getY() > HEIGHT || head.getY() < 0) {
            killSnake();
        }

    }

    /**
     * Sets the flag for hasEaten to true and generates a new piece of food, the snake will then be extended next tick
     */
    public void eat() {
        hasEaten = true;
        food.generateNewFood(segments);
    }

    /**
     * Sets the snake's isAlive status to false
     */
    public void killSnake() {
        isAlive = false;
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

        // Checks to see if they had just turned and haven't moved in that direction yet
        // This prevents the player from pressing two directional keys on the same frame to do a full 180
        // Perfect 180's should not be allowed in snake or in general since they technically force the head through its body
        if (!hasMoved) { return; }

        boolean isOneSegment = (segments.size() == 1);

        switch (direction.toLowerCase()) {
            case "up":
                xVel = 0;
                yVel = (isOneSegment || yVel != SPEED) ? -SPEED : yVel;  // If snake is 1 segment long, or it isn't facing down then it can go up
                break;
            case "down":
                xVel = 0;
                yVel = (isOneSegment || yVel != -SPEED) ? SPEED : yVel;  // If snake is 1 segment long, or it isn't facing up then it can go down
                break;
            case "left":
                xVel = (isOneSegment || xVel != SPEED) ? -SPEED : xVel;  // If snake is 1 segment long, or it isn't facing right then it can go left
                yVel = 0;
                break;
            case "right":
                xVel = (isOneSegment || xVel != -SPEED) ? SPEED : xVel;  // If snake is 1 segment long, or it isn't facing left then it can go right
                yVel = 0;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }

        hasMoved = false;  // Prevents 180 turns
    }
}
