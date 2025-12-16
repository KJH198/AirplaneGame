package Source;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

//工具包(不必实例化，仅调用静态方法)
public class GameUtil {
    //存静态变量
    public static int width=1000,height=800,pwidth=50,pheight=50,locX=100,locY=100;

    //构造器私有，防止创建实例
    private GameUtil() {}

    //静态方法，加载图片
    public static Image getImage(String path) {
        Image img = null;
        URL url = GameUtil.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }
}
