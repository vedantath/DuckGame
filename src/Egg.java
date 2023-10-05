import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;

public class Egg extends Thing{
    private double x;
    private double y;
    Image image;
    private final String d;

    public Egg(double x, double y, GameEngine ge, String direction) {
        this.x = x;
        this.y = y;
        w = 32*2;
        h = 22*2;
        color = Color.white;
        d = direction;

        try{
            image = ImageIO.read(getClass().getResource("Egg.png"));
        }
        catch (Exception e){ }
    }

    public void tick(){
        if(d.equals("left")) {x-=30;}
        if(d.equals("right")) {x+=30;}
    }
    public void render(Graphics g){
        g.setColor(color);
        g.drawImage(image,(int)x,(int)y,w,h,null);
    }
    public double getX(){return x;}
    public double getY(){return y;}
    public String getD(){return d;}
    public int getWidth() {return w;}
    public int getHeight() {return h;}
}