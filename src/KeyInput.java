import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    GameEngine ge;
    public KeyInput(GameEngine ge){
        this.ge = ge;
    }

    public void keyPressed(KeyEvent e){
        ge.keyPressed(e);
    }
    public void keyReleased(KeyEvent e){
        ge.keyReleased(e);
    }
}
