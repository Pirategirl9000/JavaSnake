package Game;

import Game.Snake.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Runnable;

public class GameController extends JPanel implements Runnable {
    /**
     * Snake object used for storing info about the snake's position and segments. Also handles the movement of the snake
     */
    private Snake snake;

    /**
     * Whether the player is still alive, used for stopping the gameLoop thread
     */
    private boolean isAlive;

    /**
     * Ticks Per Second, how many game ticks are executed each second. Higher tick speed means faster gameplay and frame rate
     */
    private final int TPS = 20;

    /**
     * Time (ms) of a single frame, determined by the TPS
     */
    private final long frameDuration = 1000 / TPS;

    /**
     * The thread the game's clock performs on
     */
    private Thread gameThread;

    /**
     * Creates a gameController to handle the logic and graphics of the game
     */
    public GameController() {
        this.setBackground(Color.DARK_GRAY);
        snake = new Snake(20, 20, 20, 40, 40);
        isAlive = true;
        startLoop();
    }

//    public void reset() {
//        killThread();
//        snake = new Snake(20, 20, 20, 40, 40);
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
        isAlive = false;
        gameThread.interrupt();  // Stops the gameThread where it is, so if it's sleeping we stop that
    }

    /**
     * Paints the game canvas
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Clear previous frame

        g.setColor(snake.color);
        final int WIDTH = snake.getWidth();
        final int HEIGHT = snake.getHeight();

        // Draw the snake
        for (int i = 0; i < snake.segments.size(); i++) {
            Segment seg = snake.segments.get(i);
            g.fillRect(seg.getX(), seg.getY(), WIDTH, HEIGHT);
        }
    }

    /**
     * The running thread of the gameLoop
     */
    @Override
    public void run() {
        while (isAlive) {
            long startTime = System.currentTimeMillis();  // Get the start time

            // Execute Game logic
            tick();

            long elapsedTime = System.currentTimeMillis() - startTime;  // Get the time it took to execute game logic

            // If the time it took to execute the logic was less than the time of one frame then we stop the thread till enough time
            // has passed for us to execute the next game tick
            if (elapsedTime < frameDuration) {
                try {
                    Thread.sleep(frameDuration-elapsedTime);  // Wait till we should generate the next frame
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Reset the interrupt flag on the thread - Good practice habit
                }
            }
        }
    }
}