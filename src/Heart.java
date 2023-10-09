import javax.imageio.ImageIO;
import java.awt.*;

public class Heart {
    Image heart;
    Color color;
    int x;
    public Heart(int xpos) {
        try
        {
            heart = ImageIO.read(getClass().getResource("heart.png"));
        }
        catch(Exception e){}

        x=xpos;
    }
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.drawImage(heart,(int)x, (int)60, 50 , 50, null);
    }

}