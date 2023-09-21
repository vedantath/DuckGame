import java.awt.*;
import java.awt.*;
import java.io.IOException;
import java.io.File;
import java.awt.event.*;
import javax.imageio.*;


public class Bball extends Thing
{
    Image bball;
    int INITIAL_VELOCITY = 25;
    final int GRAVITY = 1;

    public Bball()
    {
        try
        {
            //bball = ImageIO.read(getClass().getResource("bball.png"));
            bball = ImageIO.read(getClass().getResource("bball.png"));
        }
        catch(Exception e){}

        reset();

    }
    public void draw(Graphics g, GameEngine ge)
    {

        g.setColor(color);
        g.drawImage(bball,(int)x, (int)y, w , h, null);


        move();
        if (ge.getInput().isKeyDown(KeyEvent.VK_R))
            reset();
    }
    public void move()
    {
        x += dx;
        if(y>720-h||y<0+h)
            dy=-INITIAL_VELOCITY;
        /*if (x <-50)
        {
            reset();
        }*/
        if (x > 1010)
        {
            reset();
        }
        if (y <= 720-h)
        {
            // Will result in the player moving upwards.
            y -= INITIAL_VELOCITY; // Move the player on the y-axis based on the strength of the jump.
            INITIAL_VELOCITY -= GRAVITY; // Gradually decrease the strength of the jump by the player's weight.
            if (y > 720-h)
                y = 720-h;
        }
        if (INITIAL_VELOCITY <= -25)
        {
            INITIAL_VELOCITY = 25;
            y = 720-h;
        }
    }
    public void bounce()
    {
        dx=-dx*10;
    }
    public void reset()
    {
        dx=dy=0;
        while (dx==0)
            dx=4;

        w=h=150;
        x=0-w/2;
        y=720-h;
    }

}