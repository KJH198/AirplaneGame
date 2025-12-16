package Source;

import java.awt.*;

public class Explode {
    int x,y;
    static Image[] imgs = new Image[16];
    int count = 0;
    boolean live = true;

    static {
        for (int i = 0; i < 16; i++) {
            imgs[i] = GameUtil.getImage("images/explode/e" + (i + 1) + ".gif");
            //避免懒加载
            imgs[i].getWidth(null);
        }
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawObject(Graphics g) {
        if(!live) return;
        if (count < 16) {
            g.drawImage(imgs[count], x, y, null);
            count++;
        }else {
            live = false;
        }
    }
}
