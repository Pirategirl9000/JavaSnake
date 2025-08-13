package Game;

import javax.swing.*;

public class Display extends JFrame {
    public Display() {
        this.setTitle("Game.Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setLocationRelativeTo(null);

        GameController game = new GameController();

        this.add(game);


        this.setVisible(true);
    }
}
