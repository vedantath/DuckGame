import java.awt.*;

public class Thing {

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

    }
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
    public int getWidth() {return w;}
    public int getHeight() {return h;}
    public void reset()
    {
    }

    public boolean collide(Thing other)
    {
        if(this.getX() < other.getX()+other.getWidth()-30 &&
                this.getY() < other.getY()+other.getHeight()-30 &&
                this.getX()+this.getWidth()-30 > other.getX() &&
                this.getY()+this.getHeight()-30 >other.getY())
            return true;
        return false;
    }


}
