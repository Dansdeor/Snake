package gui;


import java.awt.*;
import java.util.ArrayList;

class Snake {
    public Direction dir;
    private ArrayList<Rect> body;
    private int old_x, old_y;

    public Snake() {
        body = new ArrayList();
        body.add(new Rect());
        dir = new Direction();
    }

    public void draw(Graphics g, Game game) {
        Rect Node = GetHead();
        old_x = Node.x;
        old_y = Node.y;
        Node.x += 10 * dir.x;
        Node.y += 10 * dir.y;
        Node.draw(g);
        for (int i = 1; i < body.size(); i++) {
            Node = body.get(i);
            int temp = Node.x;
            Node.x = old_x;
            old_x = temp;
            temp = Node.y;
            Node.y = old_y;
            old_y = temp;
            Node.draw(g);
        }
    }

    public void Grow() {
        body.add(new Rect(old_x, old_y));
    }

    public Rect GetHead() {
        return body.get(0);
    }
}

class Direction {
    public int x, y;

    public Direction() {
        y = 1;
        x = 0;
    }
}

class Rect {
    public int x, y;

    public Rect() {
        x = 0;
        y = 0;
    }

    public Rect(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, 10, 10);
    }
}