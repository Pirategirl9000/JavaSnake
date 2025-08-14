import Game.Display;

/**
 * Driver class for the program
 */
public class Main {
    /**
     * Driver code for the program
     * @param args [Null]
     */
    public static void main(String[] args) {

        ///// GAME SETTINGS  /////
        int GAMEWIDTH = 800;
        int GAMEHEIGHT = 800;
        int SNAKESPEED = 10;
        int TICKSPERSECOND = 20;
        /////                /////

        if (GAMEWIDTH % SNAKESPEED > 0 || GAMEHEIGHT % SNAKESPEED > 0) {  // Checks whether your settings are valid
            throw new IllegalArgumentException("Snake's speed must be aligned within the size of the game frame");
        }

        Display display = new Display(GAMEWIDTH, GAMEHEIGHT, SNAKESPEED, TICKSPERSECOND);  // Boot up the game

        //TODO Make sure that Display.width / SPEED == 0 && Display.height / SPEED == 0

    }
}
