import java.applet.Applet;
import java.applet.AudioClip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;


public class Platformer {

    public static void main(String[] args) {
        GameEngine.main(args);
    }
}
class GameEngine implements Runnable {

    long onStart = System.currentTimeMillis();
    Bball bball = new Bball();
    Ball duck = new Ball();

    Heart h1 = new Heart(20);
    Heart h2 = new Heart(70);
    Heart h3 = new Heart(120);
    ArrayList<Heart> hearts = new ArrayList<>();

    Thing[] things = {duck,bball};
    Background bg = new Background();

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
    private Canvas canvas;
    private int gameStart = 0;

    private Controller c;

    public GameEngine()
    {

    }
    public void start()
    {
        window = new Window(this);
        thread = new Thread(this);
        input = new Input(this);
        addKeyListener(new KeyInput(this));
        c= new Controller(this);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setFocusable(false);
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);
        hearts.add(h1); hearts.add(h2); hearts.add(h3);
        gameStart = 1;
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
                    System.out.println("FPS : "+fps);
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
        System.exit(0);
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
            Sound.SHOOT.play();

        }
    }
    public void keyReleased(KeyEvent e){

    }

    public void update(Graphics g) throws InterruptedException {

        //System.out.println("GS: "+gameStart);
        /*if(gameStart == 1)
        {
            bg.draw(g);
            //g.setColor(Color.GREEN);
            //g.drawImage(bg,(int)x, (int)y, w , h, null);
            //g.drawRect(0,0,960,720);
            System.out.println("BGG");
            gameStart = 2;
        }*/
        if (duck.getLives() > 0) {


            bg.draw(g);
            for(int i = 0; i<duck.getLives(); i++)
            {
                hearts.get(i).draw(g);
            }
            //bg.draw(g);

            long time = System.currentTimeMillis() - onStart;
            if (this.getInput().isKeyDown(KeyEvent.VK_SPACE) && time >= 200) {
                KeyEvent e = new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_SPACE, '\0');
                KeyInput testobj = new KeyInput(this);
                onStart = System.currentTimeMillis();
                testobj.keyPressed(e);
            }
            c.render(g);
            c.tick();

            g.setColor(Color.white);
            g.drawString("Eggs Left: " + c.getEggsLeft(), 50, 50);

            for (Egg e : c.e) {
                if (e.collide(bball)) {
                    bball.reset();
                    c.removeEgg(e);
                }

            }

            for (Thing t : things) {
                for (Thing o : things)
                    if (t != o)
                        if (t.collide(o)) {
                            t.reset();
                            duck.removeLife();
                        }
                t.draw(g, this);
            }
        }
        else {  //Game over screen & menu
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
            g.drawString("Game Over", width/2-120, height/2);

            Rectangle resBtn = new Rectangle(150,100,100,25);
            Rectangle exitBtn = new Rectangle(150,150,100,25);

            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.setColor(Color.WHITE);
            g.drawString("Duck Lite",125,75);
            if(!mouseHover(resBtn))
                g.setColor(Color.CYAN);
            else
                g.setColor(Color.PINK);
            g.fillRect(resBtn.x, resBtn.y, resBtn.width, resBtn.height);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.setColor(Color.GRAY);
            g.drawString("Reset", resBtn.x+20, resBtn.y+17);
            if(!mouseHover(exitBtn))
                g.setColor(Color.CYAN);
            else
                g.setColor(Color.PINK);
            g.fillRect(exitBtn.x, exitBtn.y, exitBtn.width, exitBtn.height);
            g.setColor(Color.GRAY);
            g.drawString("Exit", exitBtn.x+20, exitBtn.y+17);

            if(btnClicked(exitBtn))
            {
                System.out.println("EXIT");
                dispose();
                input.clearMouseClick();
            }

            if(btnClicked(resBtn))
            {
                System.out.println("RES");
                onStart = System.currentTimeMillis();
                duck.setLives(3);
                c.resetAmmo();
                input.clearMouseClick();
            }

        }
    }

    private boolean btnClicked(Rectangle btn) {

        if(mouseHover(btn))
        {
            if(input.isMouseClicked()) {
                input.clearMouseClick();
                return true;
            }
        }

        return false;
    }

    private boolean mouseHover(Rectangle btn)
    {
        if(input.getMouseX()>= btn.x && input.getMouseX()<= btn.x+btn.width &&
                input.getMouseY() >= btn.y && input.getMouseY()<=btn.y+btn.height)
        {
            return true;
        }
        return false;
    }

}

