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
        Display display = new Display(1920, 1080);  // Boot up the game

        display.debugCommand("FORCEEAT");

        //TODO Make sure that Display.width / SPEED == 0 && Display.height / SPEED == 0

    }
}
