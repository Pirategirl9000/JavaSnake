package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;

public class Display extends JFrame {
    public Display() {
        this.setTitle("Snake Game");
        this.setSize(1920, 1080);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.DARK_GRAY);
        GameController game = new GameController();

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
                game.parseInput(e);  // Send it to the game controller for reading
            }
        });

        this.add(game);  // Add the game to the display
        this.setVisible(true);
    }
}
