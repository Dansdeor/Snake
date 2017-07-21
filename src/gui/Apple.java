package gui;

import java.awt.*;

class Apple extends Rect {
    private boolean eaten;

    public Apple() {
        eaten = true;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 10, 10);
    }
}
