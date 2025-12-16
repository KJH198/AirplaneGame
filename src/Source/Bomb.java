package Source;

import java.awt.*;

public class Bomb extends GameObject {

    private double degree;//炸弹旋转角度
    boolean live = true;


    public Bomb(){
        degree = Math.random()*Math.PI*2;
        x = 500;
        y = 400;
        width = 10;
        height = 10;
        speed = 10;
    }

    @Override
    public void drawObject(Graphics g) {
        if(!live) return;
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height);
        g.setColor(color);
        move();
    }

    public void move(){
        x += speed * (int)Math.cos(degree);
        y += speed * (int)Math.sin(degree);
        if (x < 0){
            x = 0;
            degree = Math.PI - degree;
        }
        else if (x > GameUtil.width - width) {
            x = GameUtil.width - width;
            degree = Math.PI - degree;
        }

        if (y < 30) {
            y = 30;
            degree = -degree;
        }
        else if (y > GameUtil.height - height) {
            y = GameUtil.height - height;
            degree = -degree;
        }
    }
}
