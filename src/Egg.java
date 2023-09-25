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
        if(d.equals("left")) {x-=15;}
        if(d.equals("right")) {x+=15;}
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

























/*public class Egg extends Thing{
    public Egg()
    {
        color = Color.white;
        dx=5;
        w = 30;
        h = 30;
    }
    public void draw(Graphics g, GameEngine ge, int x1, int y1)
    {
        //x = Duck.getX();
        //y= Duck.getY();
        x=x1;
        y=y1;
        g.setColor(color);
        g.fillRect((int)x,(int)y,w,h);
        move(ge);

    }
    public void move(GameEngine ge)
    {
        //if (ge.getInput().isKeyDown(KeyEvent.VK_SPACE))
       // {
        //    //fire();
       // }
        //this.x+=5;

    }
    public void fire(Graphics g, GameEngine ge)
    {
        //this.draw(g, ge);
    }
    public boolean isSpacePressed(GameEngine ge)
    {
        if (ge.getInput().isKeyDown(KeyEvent.VK_SPACE))
            return true;
        return false;

    }

    public double getX() {return x;}
    public double getY() {return y;}
    public void setX(double x){this.x=x;}
    public void setY(double y){this.y=y;}
}*/
