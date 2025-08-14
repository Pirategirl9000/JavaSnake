package Game.Snake;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

/**
 * Holds information regarding the food that the snake eats to grow
 */
public class Food extends Segment {

    public Color color;

    /**
     * Width of the game area, used for generating location of food
     */
    private final int DISPLAYWIDTH;

    /**
     * Height of the game area, used for generating location of food
     */
    private final int DISPLAYHEIGHT;

    /**
     * Width of a food object
     */
    private final int FOODWIDTH;

    /**
     * Height of a food object
     */
    private final int FOODHEIGHT;

    /**
     * The random number generator for the food locations
     */
    private final Random rand = new Random();

    public Food(int displayWidth, int displayHeight, int foodWidth, int foodHeight, ArrayList<SnakeSegment> segments) {
        super(80, 80); // Default position to prevent null pointers
        rand.setSeed(System.currentTimeMillis());  // Generate seed based on current time
        color = Color.GREEN;
        DISPLAYWIDTH = displayWidth;
        DISPLAYHEIGHT = displayHeight;
        FOODWIDTH = foodWidth;
        FOODHEIGHT = foodHeight;
        generateNewFood(segments);
    }

    /**
     * Returns the width of a food object
     * @return width
     */
    public int getFoodWidth() { return FOODWIDTH; }

    /**
     * Returns the height of a food object
     * @return height
     */
    public int getFoodHeight() { return FOODHEIGHT; }

    public Color getColor() { return color; }

    /**
     * Spawns a new food square at a random location
     */
    protected void generateNewFood(ArrayList<SnakeSegment> segments) {
        int xRange = DISPLAYWIDTH / FOODWIDTH;  // We do it this way to prevent any need for rounding and grant an equal chance for every square to be selected
        int yRange = DISPLAYHEIGHT / FOODHEIGHT;

        int[] foodPos;

        do {  // RARE SIGHTING, A DO WHILE STATEMENT!!!
            foodPos = new int[] {rand.nextInt(xRange), rand.nextInt(yRange)};
            foodPos[0] *= FOODWIDTH;
            foodPos[1] *= FOODHEIGHT;
            this.setLocation(foodPos[0], foodPos[1]);
        } while (isOverlappingSnake(segments));  // God, I'm such a genius
    }

    private boolean isOverlappingSnake(ArrayList<SnakeSegment> segments) {
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < segments.size(); i++) {
            //noinspection EqualsBetweenInconvertibleTypes
            if (((segments.get(i))).equals(this)) {  // Checks if they are at the same coordinates
                return true;
            }
        }

        return false;
    }

    /**
     * Generates a new seed based on the current time
     */
    public void generateNewSeed() { rand.setSeed(System.currentTimeMillis()); }

    @Override
    public String toString() {
        return "Food [x=" + location[0] + ", y=" + location[1] + "]";
    }


}
