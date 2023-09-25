import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;


public class Platformer {

    public static void main(String[] args) {
        GameEngine.main(args);
    }
}
class GameEngine implements Runnable {

    /*Ball ball = new Ball();
Paddle left = new Paddle(1);
Paddle right = new Paddle(2);
*/
    long onStart = System.currentTimeMillis();
    Bball bball = new Bball();
    Ball duck = new Ball();
    //Egg egg = new Egg();
    Thing[] things = {duck,bball};
    //ArrayList<Egg> eggs = new ArrayList<Egg>();

    public static void main(String[] args) {
        GameEngine ge = new GameEngine();
        ge.start();
    }
    private int width=960,height=720;
    private float scale = 1f;
    private String title = "Platformer";
    private Window window;
    private Thread thread;
    private Input input;
    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60;

    private Controller c;
    public GameEngine()
    {
        //addKeyListener(new KeyInput(this));
    }
    public void start()
    {
        window = new Window(this);
        thread = new Thread(this);
        input = new Input(this);
        addKeyListener(new KeyInput(this));
        c= new Controller(this);
        thread.run();
    }
    public void stop()
    {

    }
    public void run()
    {
        running = true;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;
        boolean render = false;
        double frameTime = 0;
        int frames = 0;
        int fps= 0;
        //addKeyListener(new KeyInput(this));
        while(running)
        {
            firstTime = System.nanoTime()/1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            render = false;
            unprocessedTime += passedTime;
            frameTime += passedTime;
            while(unprocessedTime>=UPDATE_CAP)
            {
                unprocessedTime -= UPDATE_CAP;

                render = true;

                if(frameTime>=1.0)
                {
                    frameTime=0;
                    fps=frames;
                    frames=0;
                    //System.out.println("FPS : "+fps);
                }
                //update Game
            }
            if(render)
            {
                //render
                //c.render(g);

                try {
                    window.update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frames++;
            }
            else
            {
                try {Thread.sleep(1);}catch (Exception e){e.printStackTrace(); }
            }
        }

    }
    private void dispose()
    {

    }

    private void tick(){
        c.tick();
    }
    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public float getScale() {return scale;}
    public String getTitle() {return title;}
    public Window getWindow() {return window;}
    public Input getInput() {return input;}

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE) {
            if (duck.getDirection().equals("left")) { c.addEgg(new Egg(duck.getX(), duck.getY(), this, "right"));}
            if (duck.getDirection().equals("right")) { c.addEgg(new Egg(duck.getX(), duck.getY(), this, "left"));}
        }
    }
    public void keyReleased(KeyEvent e){

    }

    public void update(Graphics g) throws InterruptedException
    {
        long time = System.currentTimeMillis()-onStart;

        if (this.getInput().isKeyDown(KeyEvent.VK_SPACE) && time>=200)
        {
            KeyEvent e = new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_SPACE, '\0');
            KeyInput testobj = new KeyInput(this);
            onStart = System.currentTimeMillis();
            testobj.keyPressed(e);
        }
        c.render(g);
        c.tick();
       // duck.draw(g, this);
        g.setColor(Color.white);
        g.drawString("Eggs Left: "+c.getEggsLeft(), 50, 50);

        //System.out.println("duck: "+duck.getX()+"\t"+"ball: "+bball.getX());
        /*if(duck.getX() <= bball.getX()+bball.getWidth() && duck.getX()>= bball.getX())
        {
            System.out.println("HIT2");
        }*/

        /*for(Thing t : things) {
            for (Thing o : things) {
                if (t != o)
                    if (t.collide(o))
                        t.reset();
            }
            t.draw(g, this);*/
        for(Egg e: c.e) {
            if(e.collide(bball)) {
                bball.reset();
                c.removeEgg(e);
            }

        }

        for(Thing t : things)
        {
            for(Thing o : things)
                if(t!=o)
                    if(t.collide(o))
                        t.reset();
            t.draw(g,this);
        }
    }

}

