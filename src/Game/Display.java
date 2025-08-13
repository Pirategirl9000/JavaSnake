package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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


        this.add(game);


        this.setVisible(true);
    }
}
