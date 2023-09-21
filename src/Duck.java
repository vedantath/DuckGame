import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;

public class Duck extends Thing{

    //Graphics d;
    //Image image;
    private String direction;
    public Duck()
    {
        color = Color.yellow;
        x=360;
        y=300;
        w = 50;
        h = 50;
        direction="";

        //uploading image for duck sprite
        /*try{
            image = ImageIO.read(getClass().getResource("Orange.png"));
        }
        catch (Exception e){ }*/
    }
    public void draw(Graphics g, GameEngine ge)
    {
        g.setColor(color);
        g.fillRect((int)x,(int)y,w,h);
        //g.drawImage(image,(int)x,(int)y,w,h,null);
        move(ge);
    }
    public void move(GameEngine ge)
    {
        if (ge.getInput().isKeyDown(KeyEvent.VK_A))
        {
            x-=2;
            direction="left";
        }
        if (ge.getInput().isKeyDown(KeyEvent.VK_D))
        {
            x+=2;
            direction="right";
        }
        if (ge.getInput().isKeyDown(KeyEvent.VK_W) && this.y>=600)
            //if(y>500)
            {
                this.y =400;
            }
            //jump
    }
    public void bounce()
    {

    }


    public void gravity() {
       // this.y -= 200;
        /*vertical_speed = 10;
        while(this.getY()<600)
            y += (10*2-4.9*4);*/
       // vertical_speed = 5;

        //fall
        this.vertical_speed = this.vertical_speed + GRAVITY;
        if (this.vertical_speed > TERMINAL_VELOCITY)
        {
            this.vertical_speed = TERMINAL_VELOCITY;
        }
        this.y = this.getY() + this.vertical_speed;
        while (this.y > 600)
        {
            vertical_speed = 0;
        }
        /*if (this.y < 0 )
        {
            vertical_speed=-vertical_speed;
        }*/
    }
    public double getY() {return y;}
    public double getX() {return x;}
    public String getDirection() {return direction;}


}
