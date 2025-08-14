package Game;

import Game.Snake.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Runnable;
import java.awt.event.KeyEvent;

/**
 * The GameController serves as the canvas and logic controller of the game. Handles features like the basic gameLoop, clock, input parsing, graphics, and basic logic
 */
public class GameController extends JPanel implements Runnable {
    /**
     * Snake object used for storing info about the snake's position and segments. Also handles the movement of the snake
     */
    @SuppressWarnings("FieldMayBeFinal")  // I know what I'm doing Mr. Compiler
    private Snake snake;

    /**
     * Ticks Per Second, how many game ticks are executed each second. Higher tick speed means faster gameplay and frame rate
     */
    private final int TPS = 20;

    /**
     * Time (ms) of a single frame, determined by the TPS
     */
    @SuppressWarnings("FieldCanBeLocal")  // Have you no faith in me IntelliJ?
    private final long frameDuration = 1000 / TPS;

    /**
     * The thread the game's clock performs on
     */
    private Thread gameThread;

    /**
     * Width of the game context
     */
    private final int WIDTH;

    /**
     * Width of the game context
     */
    private final int HEIGHT;

    /**
     * Creates a gameController to handle the logic and graphics of the game
     */
    public GameController(int width, int height) {
        this.setBackground(Color.BLACK);
        this.setSize(width, height);
        WIDTH = width;
        HEIGHT = height;
        snake = new Snake(WIDTH, HEIGHT, 20, 20, 20, 40, 40);
        startLoop();
    }

//    public void reset() {
//        killThread();
//        snake = new Snake(WIDTH, HEIGHT, 20, 20, 20, 40, 40);
//        isAlive = true;
//        startLoop();
//    }

    /**
     * Starts the game's loop on a new thread
     */
    private void startLoop() {
        gameThread = new Thread(this);  // Create a new thread reference
        gameThread.start();  // Begin the gameLoop
    }

    /**
     * Executes one game tick and repaints the canvas after
     */
    private void tick() {
        snake.move();
        repaint();
    }

    /**
     * Kills the game's thread safely for termination of game
     */
    protected void killThread() {
        snake.killSnake();
        gameThread.interrupt();  // Stops the gameThread where it is, so if it's sleeping we stop that
    }

    /**
     * Parses KeyEvents to directional input for the game
     * @param e KeyEvent object, accepted keys: [w, a, s, d]
     */
    protected void parseInput(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                snake.changeDirection("up");
                break;
            case 's':
                snake.changeDirection("down");
                break;
            case 'a':
                snake.changeDirection("left");
                break;
            case 'd':
                snake.changeDirection("right");
                break;
        }
    }

    /**
     * Paints the game canvas
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Clear previous frame

        g.setColor(snake.color);
        final int WIDTH = snake.getSegmentWidth();
        final int HEIGHT = snake.getSegmentHeight();

        // Draw the snake
        for (int i = 0; i < snake.segments.size(); i++) {
            SnakeSegment seg = snake.segments.get(i);
            g.fillRect(seg.getX(), seg.getY(), WIDTH, HEIGHT);
        }

        // Draw the food
        g.setColor(snake.getFoodColor());
        g.fillRect(snake.getFoodX(), snake.getFoodY(), snake.getSegmentWidth(), snake.getSegmentHeight());  // Height/width of snake are always the same as food
    }

    /**
     * The running thread of the gameLoop
     */
    @Override
    public void run() {
        while (snake.getAlive()) {
            long startTime = System.currentTimeMillis();  // Get the start time

            // Execute Game logic
            tick();

            long elapsedTime = System.currentTimeMillis() - startTime;  // Get the time it took to execute game logic

            // If the time it took to execute the logic was less than the time of one frame then we stop the thread till enough time
            // has passed for us to execute the next game tick
            if (elapsedTime < frameDuration) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(frameDuration-elapsedTime);  // Wait till we should generate the next frame
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Reset the interrupt flag on the thread - Good practice habit
                }
            }
        }
    }

    /**
     * Runs select debug commands - Developer tool
     * @param command command to execute
     */
    protected void debugCommand(String command) {
        switch (command) {
            //noinspection SpellCheckingInspection
            case "FORCEEAT":
                snake.eat();
                break;
            //noinspection SpellCheckingInspection
            case "FORCEKILL":
                snake.killSnake();
                break;
        }
    }
}