package Game;

import Game.Snake.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class GameController extends JPanel {
    private Snake snake = new Snake(20, 40, 40);


    @Override
    public void paintComponent(Graphics g) {
        g.setColor(snake.color);

        for (int i = 0; i < snake.segments.size(); i++) {
            Segment seg = snake.segments.get(i);
            g.drawRect(seg.getX(), seg.getY(), 20, 20);
        }
    }
}
