import java.awt.*;

public class Thing {
    //private static Object y;
    double x,y,dx,dy;
    int w,h;
    Color color;
    final int GRAVITY = 1;
    final int TERMINAL_VELOCITY = 5;
    int vertical_speed;
    int horizontal_speed;

    //Image image;

    public Thing()
    {
        //color = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
    }
    /*public void draw(Graphics g, GameEngine ge)
    {

    }*/
    public void draw(Graphics g, GameEngine ge) throws InterruptedException
    {

    }
    public void move()
    {

    }
    public void bounce()
    {

    }
    public void gravity(){


    }
    public double getY()
    {
       return y;
    }
    public double getX()
    {
        return x;
    }
    public int getDy()
    {
        return (int)dy;
    }
    public void reset()
    {
    }

   public boolean collide(Thing other)
    {
        if (x < other.x+other.w &&
                y < other.y+other.h &&
                x+w > other.x &&
                y+h > other.y)
            return true;
        return false;
    }
}
