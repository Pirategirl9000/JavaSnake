package Game;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;

/**
 * Display is a JFrame extended class that serves as the window for the program. Stores the GameController.
 */
public class Display extends JFrame {
    /**
     * GameController that handles the logic and graphics of the game
     */
    GameController game;

    /**
     * Creates a new display window with the Snake game
     * @param width width of the display and game
     * @param height height of the display and game
     */
    public Display(int width, int height) {
        game = new GameController(width, height);
        this.setTitle("Snake Game");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLACK);

        // Because of the Game thread we need to kill the window in our own way rather than
        // using JFrame.EXIT_ON_CLOSE
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                game.killThread();       // Kill the gameLoop
                Display.this.dispose();  // kill the JFrame display
            }
        });

        // Add a listener so you can control the snake
        this.addKeyListener(new KeyListener() {
            /**
             * Triggers on keyPressed. ~Ignored
             * @param e the event to be processed
             */
            @Override
            public void keyPressed(KeyEvent e) {
                return;
            }

            /**
             * Triggers on keyReleased. ~Ignored
             * @param e the event to be processed
             */
            @Override
            public void keyReleased(KeyEvent e) {
                return;
            }

            /**
             * Triggers when a key is pressed and released. Sends input to game for parsing
             * @param e the event to be processed
             */
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'f') {
                    toggleFullscreen();
                }
                game.parseInput(e);  // Send it to the game controller for reading
            }
        });

        this.add(game);  // Add the game to the display
        this.setVisible(true);
    }

    /**
     * Toggles fullscreen for the JFrame
     */
    private void toggleFullscreen() {
        int state = this.getExtendedState();  // Get the current JFrame's state

        this.dispose();  // Frame can't be displayable when manipulating these

        if (state == JFrame.NORMAL) {  // Set to fullscreen
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setUndecorated(true);
        } else {  // Disable fullscreen
            this.setExtendedState(JFrame.NORMAL);
            this.setUndecorated(false);
        }

        this.setVisible(true);  // Show it again
    }

    /**
     * Runs a debug command, used for dev testing
     * @param command the command to run,
     * @see GameController#debugCommand(String) view commands
     */
    public void debugCommand(String command) { game.debugCommand(command); }
}
