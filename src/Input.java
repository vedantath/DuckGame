//import javax.swing.text.Position;
//import kotlin.reflect.jvm.internal.impl.incremental.components.Position;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
    private boolean mouseClicked;
    private boolean mousePressed;

    private boolean[] currentlyPressed;
    private boolean[] pressed;

    private boolean[] keys = new boolean[256];
    private boolean[] keysLast = new boolean[256];

    private boolean[] buttons = new boolean[5];
    private boolean[] buttonsLast = new boolean[5];

    private int mouseX,mouseY;
    private int scroll;

    private GameEngine ge;

    public Input(GameEngine ge)
    {
        this.ge = ge;
        mouseX=mouseY=scroll=0;

        ge.getWindow().getCanvas().addKeyListener(this);
        ge.getWindow().getCanvas().addMouseListener(this);
        ge.getWindow().getCanvas().addMouseMotionListener(this);
        ge.getWindow().getCanvas().addMouseWheelListener(this);

        pressed = new boolean[1000];
        currentlyPressed = new boolean[1000];
        //pointerPosition = new Position(0,0);
    }

    public void clearMouseClick()
    {
        mouseClicked = false;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public boolean isKey(int keyCode)
    {
        return keys[keyCode];
    }
    public boolean isKeyUp(int keyCode)
    {
        return keysLast[keyCode] && !keys[keyCode];
    }
    public boolean isKeyDown(int keyCode)
    {
        return !keysLast[keyCode] && keys[keyCode];
    }
    public boolean isButton(int buttonCode)
    {
        return buttons[buttonCode];
    }
    public boolean isButtonUp(int buttonCode)
    {
        return buttonsLast[buttonCode] && !buttons[buttonCode];
    }
    public boolean isButtonDown(int buttonCode)
    {
        return !buttonsLast[buttonCode] && buttons[buttonCode];
    }

    public void update()
    {
        scroll=0;
        for(int i=0;i<keys.length;i++)
            keysLast[i]=keys[i];
        for(int i=0;i<buttons.length;i++)
            buttonsLast[i]=buttons[i];
    }
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        scroll = e.getWheelRotation();
    }
    public void mouseDragged(MouseEvent e)
    {
        mouseX = (int)(e.getX() / ge.getScale());
        mouseY = (int)(e.getY() / ge.getScale());
    }
    public void mouseMoved(MouseEvent e)
    {
        mouseX = (int)(e.getX() / ge.getScale());
        mouseY = (int)(e.getY() / ge.getScale());
    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }
    public void mousePressed(MouseEvent e)
    {
        mousePressed = true;

        buttons[e.getButton()] = true;
    }
    public void mouseReleased(MouseEvent e)
    {
        mouseClicked = true;
        mousePressed = false;

        buttons[e.getButton()] = false;
    }
    public void mouseClicked(MouseEvent e)
    {

    }
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()]=true;
    }
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()]=false;
    }
    public void keyTyped(KeyEvent e)
    {

    }
    public int getMouseX(){return mouseX;}
    public int getMouseY(){return mouseY;}

}
