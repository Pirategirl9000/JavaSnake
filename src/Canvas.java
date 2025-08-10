import javax.swing.*;
import java.awt.Color;
import Snake.*;

public class Canvas extends JFrame {
    private final JPanel gamePanel = new JPanel() {{
        this.setSize(1000, 1000);
        this.setBackground(Color.BLACK);
    }};

    Canvas() {
        this.setTitle("Snake.Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setLocationRelativeTo(null);

        this.add(gamePanel);


        this.setVisible(true);
    }
}
