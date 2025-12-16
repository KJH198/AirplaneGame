package Source;

import java.awt.*;

//游戏物体根类
public class GameObject {
    Image img; //图片
    public int x,y; //坐标
    int width,height; //宽高
    int speed;

    public GameObject(){
//        super();
    }

    public GameObject(Image img) {
        this();
        this.img = img;
        if(img!=null) {
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);
        }
    }

    public GameObject(Image img, int x, int y) {
        this(img);
        this.x = x;
        this.y = y;
    }

    public GameObject(Image img, int x, int y, int speed) {
        this(img, x, y);
        this.speed = speed;
    }

    public GameObject(Image img, int x, int y, int width, int height, int speed) {
        this(img, x, y, speed);
        this.width = width;
        this.height = height;
    }

    //画自己
    public void drawObject(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }

    //返回物理边界
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
