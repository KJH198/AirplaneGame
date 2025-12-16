package Source;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {
    boolean live = true;
    boolean left, up, right, down;

    public Plane(Image img, int x, int y, int width, int height, int speed) {
        super(img, x, y, width, height, speed);
    }

    @Override
    public void drawObject(Graphics g) {
        if(!live) return;
        super.drawObject(g);
        move();
    }

    public void addDirection(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
        }
    }

    public void minsDirection(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
    }

    private void move() {
        if(up) y-=speed;
        if(down) y+=speed;
        if(left) x-=speed;
        if(right) x+=speed;

        if (x < 10) x = 10;
        else if (x > GameUtil.width - width - 10) x = GameUtil.width - width - 10;

        if (y < 30) y = 30;
        else if (y > GameUtil.height - height - 10) y = GameUtil.height - height - 10;
    }

    public Rectangle getRec() {
        return new Rectangle(x, y, 30, 30);
    }
}
