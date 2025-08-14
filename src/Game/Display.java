package Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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
    public Display(int width, int height, int speed, int tps) {
        game = new GameController(width, height, speed, tps);
        this.setTitle("Snake Game");
        this.setSize(1920, 1080);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.DARK_GRAY);

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


        /// The following segment was generated with AI since I suck at formatting

        // Create a wrapper around the game with a visible border
        JPanel gameWrapper = new JPanel(new BorderLayout());
        gameWrapper.setBackground(Color.DARK_GRAY);
        gameWrapper.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4)); // Optional visual border
        gameWrapper.add(game, BorderLayout.CENTER);

        // Create a fullscreen panel that centers the gameWrapper
        JPanel fullscreenPanel = new JPanel(new GridBagLayout());
        fullscreenPanel.setBackground(Color.DARK_GRAY);
        fullscreenPanel.add(gameWrapper); // Centered by default

        // Add title label separately at the top
        JLabel title = new JLabel("Violet's Snake Game");
        title.setForeground(new Color(131, 6, 131));
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(1920, 40));
        title.setBackground(Color.DARK_GRAY);
        title.setOpaque(true);

        // Use BorderLayout to stack title and game
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(Color.DARK_GRAY);
        rootPanel.add(title, BorderLayout.NORTH);
        rootPanel.add(fullscreenPanel, BorderLayout.CENTER);

        this.setContentPane(rootPanel);
        /// END OF AI GENERATED FORMATTING

        this.pack();
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

        this.pack();
        this.setVisible(true);  // Show it again
    }

    /**
     * Runs a debug command, used for dev testing
     * @param command the command to run,
     * @see GameController#debugCommand(String) view commands
     */
    public void debugCommand(String command) { game.debugCommand(command); }
}
