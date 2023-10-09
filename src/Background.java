import javax.imageio.ImageIO;
import java.awt.*;

public class Background {

    Image bg;
    public Background()
    {
        try
        {
            bg = ImageIO.read(getClass().getResource("burton_crop.jpg"));
        }
        catch(Exception e){}
    }

    public void draw(Graphics g)
    {
        int w=960;
        int h=720;
        g.setColor(Color.PINK);
        g.drawImage(bg,0, 0, w , h, null);
        //g.fillRect(0,0,960,720);
        //System.out.println("BGG");
    }
}
