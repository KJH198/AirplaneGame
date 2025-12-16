package Source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static Source.GameUtil.*;
import java.awt.event.KeyEvent;
import java.util.Date;

public class GameFrame extends Frame {
    private final Image bg = GameUtil.getImage("Images/background.jpg");
    private final Image planeImg = GameUtil.getImage("Images/plane.png");
    private Image offScreenImage = null; //定义一个图片对象作为缓冲区

    private Plane plane = new Plane(planeImg,100,100,pwidth,pheight,10);
    private Bomb[] bombs = new Bomb[20];
    private int bombNum;
    private Explode explode;

    private Date beginTime,endTime;

    //启动游戏窗口和监听器
    public void launchGameFrame(int bombNum) {
        String title = "飞机大战";
        setTitle(title);
        setSize(width, height);
        setVisible(true);
        setLocation(locX, locY);

        //增加窗口监听器
        addWindowListener(/*匿名内部类*/new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); //退出程序
            }
        });

        //增加键盘监听
        addKeyListener(/*匿名内部类*/new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.addDirection(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.minsDirection(e);
            }
        });

        //启动窗口绘制线程
        new PaintThread().start();

        this.bombNum = bombNum;
        for(int i = 0;i < this.bombNum;i++){
            bombs[i]=new Bomb();
        }

        beginTime = new Date();
    }

    //画窗口
    @Override
    public void paint(Graphics g) {
        g.drawImage(bg, 0, 0, width,height,null);
        plane.drawObject(g);
        for(int i=0;i<bombNum;i++){
            bombs[i].drawObject(g);
            boolean peng = bombs[i].getRec().intersects(plane.getRec());
            if(peng && plane.live) {
                plane.live = false;
                bombs[i].live = false;
                explode = new Explode(plane.x,plane.y);
                endTime = new Date();
                break;
            }
        }
        if(explode!=null){
            explode.drawObject(g);

            int period = (int)((endTime.getTime() - beginTime.getTime())/1000);
            printInfo(g,"游戏结束",100,width/2-200,height/2,Color.RED);
            printInfo(g,"存活时间:"+period+"秒",50,width/2-150,height/2+100,Color.GREEN);
        }
    }

    public void printInfo(Graphics g,String str,int size,int x,int y,Color c){
        Color cpy = g.getColor();
        Font f = g.getFont();

        g.setColor(c);
        g.setFont(new Font("宋体",Font.BOLD,size));
        g.drawString(str,x,y);

        g.setColor(cpy);
        g.setFont(f);
    }

    //双缓冲技术解决屏闪问题
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(width,height);//创建一张和窗口一样大的图片
        Graphics goff = offScreenImage.getGraphics();       //拿到图片的画笔
        paint(goff);                                        //用图片的画笔画窗口
        g.drawImage(offScreenImage,0,0,null); //再一次性把完整图片画到窗口上
    }

    //内部类,负责重画窗口(循环)
    class PaintThread extends Thread {
        @Override
        public void run() {
            while(true) {
                repaint();  //重画窗口
                try {
                    Thread.sleep(20);  //20毫秒刷新一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
