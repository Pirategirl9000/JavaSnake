package Game;

import Game.Snake.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Runnable;

public class GameController extends JPanel implements Runnable {
    private Snake snake;
    private boolean isAlive = true;
    private final int TPS = 20;
    private final long frameDuration = 1000 / TPS;
    private Thread gameThread;

    public GameController() {
        this.setBackground(Color.DARK_GRAY);
        snake = new Snake(20, 20, 20, 40, 40);
        startLoop();
    }

    private void startLoop() {
        gameThread = new Thread(this);  // Create a new thread reference
        gameThread.start();  // Begin the gameLoop
    }

    private void tick() {
        snake.move();
        repaint();
    }

    public void killThread() {
        isAlive = false;
        gameThread.interrupt();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Clear previous frame
        g.setColor(snake.color);
        final int WIDTH = snake.getWidth();
        final int HEIGHT = snake.getHeight();

        for (int i = 0; i < snake.segments.size(); i++) {
            Segment seg = snake.segments.get(i);
            g.fillRect(seg.getX(), seg.getY(), WIDTH, HEIGHT);
        }
    }

    @Override
    public void run() {
        while (isAlive) {
            long startTime = System.currentTimeMillis();

            tick();

            long elapsedTime = System.currentTimeMillis() - startTime;

            if (elapsedTime < frameDuration) {
                try {
                    Thread.sleep(frameDuration-elapsedTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Reset the interrupt flag on the thread
                }
            }
        }
    }
}