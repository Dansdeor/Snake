package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

class Game extends JPanel {
    Snake snake;
    Apple apple;
    Timer timer;

    public Game() {
        snake = new Snake();
        apple = new Apple();
        setBackground(new Color(255, 255, 255));
        this.timer = new Timer(100, (ActionEvent e) -> repaint());
    }

    public void Start() {
        timer.start();
    }

    public void Stop() {
        timer.stop();
    }

    public void SetDir(int x, int y) {
        snake.dir.x = x;
        snake.dir.y = y;
    }

    private void CreateApple(Graphics g) {
        if (apple.isEaten()) {
            Random rand = new Random();
            apple.x = rand.nextInt(getWidth() / 10) * 10;
            apple.y = rand.nextInt(getHeight() / 10) * 10;
        }
        apple.setEaten(false);
        apple.draw(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (timer.isRunning()) {
            Rect head = snake.GetHead();

            if (head.x + 10 * snake.dir.x == apple.x && head.y + 10 * snake.dir.y == apple.y) {
                apple.setEaten(true);
                snake.Grow();
            }

            snake.draw(g);

            if (head.y == -10 || head.y == getHeight() - 1 || head.x == -10 || head.x == getWidth() + 1)
                this.Stop();

            for (int i = 0; i < getWidth(); i += 10) {
                g.drawLine(i, 0, i, getHeight());
                g.drawLine(0, i, getWidth(), i);
            }
            CreateApple(g);
        }
    }
}

public class Main {

    private static int SIZE = 500;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        Game game = new Game();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE + 5, SIZE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(game);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W)
                    game.SetDir(0, -1);
                if (e.getKeyCode() == KeyEvent.VK_S)
                    game.SetDir(0, 1);
                if (e.getKeyCode() == KeyEvent.VK_D)
                    game.SetDir(1, 0);
                if (e.getKeyCode() == KeyEvent.VK_A)
                    game.SetDir(-1, 0);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        try {
            game.Start();
            //game.Stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}