package Source;

import java.awt.*;

public class Bomb extends GameObject {

    private double degree;//炸弹旋转角度
    boolean live = true;


    public Bomb(boolean create) {
        degree = Math.random()*Math.PI*2;
        if(create) y = 800;
        else y = 30;
        x = 1000;
        width = 10;
        height = 10;
        speed = 5;
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
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);
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
