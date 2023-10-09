import java.awt.*;
import java.io.IOException;
import java.io.File;
import java.awt.event.*;
import javax.imageio.*;

//Duck

public class Ball extends Thing
{
    //boolean running = false;
    int INITIAL_VELOCITY = 25;
    final int GRAVITY = 1;
    Image ball;
    private String direction;
    Color color = Color.yellow;
    double x=360;
    double y=300;
    //double y=150;
    int lives = 3;

    public Ball()
    {
        try
        {
            ball = ImageIO.read(getClass().getResource("suck.png"));
        }
        catch(Exception e){}

        reset();
        direction="";

    }
    public void draw(Graphics g, GameEngine ge) throws InterruptedException
    {
        g.setColor(color);
        g.drawImage(ball,(int)x, (int)y, w , h, null);

        move();
        gravity(ge);
        if (ge.getInput().isKeyDown(KeyEvent.VK_R))
            reset();
        if (ge.getInput().isKeyDown(KeyEvent.VK_W)&& y <= 650-h)
        {
            if(y==650-h)
                Sound.JUMP.play();
            // Will result in the player moving upwards.
            y -= INITIAL_VELOCITY; // Move the player on the y-axis based on the strength of the jump.
            INITIAL_VELOCITY -= GRAVITY; // Gradually decrease the strength of the jump by the player's weight.
            if (y > 650-h)
                y = 650-h;
        }
        if (ge.getInput().isKeyDown(KeyEvent.VK_A))
            if(x>0){
                x-=10;
                direction="left";
                //To make the duck face opposite direction
                /*if(w/1>0) {
                    w = -w;
                    this.x += Math.abs(w);
                }*/
            }
        if (ge.getInput().isKeyDown(KeyEvent.VK_D))
            if(x<960-w){
                x+=10;
                direction="right";
                if(w/1<0) {
                    w = -w;
                    this.x -= Math.abs(w);
                }
            }
        if (INITIAL_VELOCITY <= -25)
        {
            INITIAL_VELOCITY = 25;
            y = 650-h;
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
        y=650-h;

    }
    public void gravity(GameEngine ge)
    {
        if(ge.getInput().isKeyDown(KeyEvent.VK_W)==false && y < 650-h)
        {
            y-=INITIAL_VELOCITY;
            INITIAL_VELOCITY-=GRAVITY;
            if(y > 650-h)
                y = 650-h;
        }
    }

    public void jump() throws InterruptedException
    {

    }
    public double getY() {return y;}
    public double getX() {return x;}
    public String getDirection() {return direction;}
    public int getWidth() {return w;}
    public int getHeight() {return h;}
    public int getLives() {return lives;}
    public void setLives(int x) {lives = x;}
    public void removeLife() {
        lives -= 1;
    }

}