import java.awt.*;
import java.io.IOException;
import java.io.File;
import java.awt.event.*;
import javax.imageio.*;

//Duck

public class Ball extends Thing
{
    boolean running = false;
    int INITIAL_VELOCITY = 25;
    //final int TERMINAL_VELOCITY = 10;
    final int GRAVITY = 1;
    Image ball;
    private String direction;
    //int INITIAL_GRAV=0;
    Color color = Color.yellow;
    double x=360;
    double y=300;
   // int w=50;
    //int h = 50;

    public Ball()
    {
        /*try
        {
            ball = ImageIO.read(getClass().getResource("hand.png"));
        }
        catch(Exception e){ }*/

        reset();
        direction="";

    }
    public void draw(Graphics g, GameEngine ge) throws InterruptedException
    {
        //g.setColor(color);
        //g.drawImage(ball,(int)x, (int)y, w , h, null);
        g.setColor(color);
        //System.out.println("w: "+w+" h: "+h);  (100,100)
        g.fillRect((int)x,(int)y,80,80);
        move();
        gravity(ge);
        if (ge.getInput().isKeyDown(KeyEvent.VK_R))
            reset();
        if (ge.getInput().isKeyDown(KeyEvent.VK_W)&& y <= 720-h)
        {
            // Will result in the player moving upwards.
            y -= INITIAL_VELOCITY; // Move the player on the y-axis based on the strength of the jump.
            INITIAL_VELOCITY -= GRAVITY; // Gradually decrease the strength of the jump by the player's weight.
            if (y > 720-h)
                y = 720-h;
        }
        if (ge.getInput().isKeyDown(KeyEvent.VK_A))
            if(x>0){
                x-=10;
                direction="left";
            }
        if (ge.getInput().isKeyDown(KeyEvent.VK_D))
            if(x<910){
                x+=10;
                direction="right";
            }
        if (INITIAL_VELOCITY <= -25)
        {
            INITIAL_VELOCITY = 25;
            y = 720-h;
        }
    }
    public void move()
    {

    }
    public void bounce()
    {

    }
    public void reset()
    {
        dx=dy=0;
        w=h=100;
        x=960/2-w/2;
        y=720-h;

    }
    public void gravity(GameEngine ge)
    {
        if(ge.getInput().isKeyDown(KeyEvent.VK_W)==false && y < 720-h)
        {
            y-=INITIAL_VELOCITY;
            INITIAL_VELOCITY-=GRAVITY;
            if(y > 720-h)
                y = 720-h;
        }
    }
    /*public void jump(float x)
    {
        y -= 670 - (INITIAL_VELOCITY*x)+((GRAVITY/2)*(Math.pow(x, 2)));
    }*/

    public void jump() throws InterruptedException
    {

    }
    public double getY() {return y;}
    public double getX() {return x;}
    public String getDirection() {return direction;}
    public int getWidth() {return w;}
    public int getHeight() {return h;}
}