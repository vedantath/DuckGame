import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

public class Controller {

    LinkedList<Egg> e = new LinkedList<Egg>();
    private Stack<Egg> ammo = new Stack<Egg>();
    private int eggLimit = 30;
    Egg temp;
    GameEngine ge;

    public Controller(GameEngine ge){
        this.ge = ge;
    }

    public void tick(){
        for(int i=0; i<e.size(); i++){
            temp = e.get(i);
            if(temp.getX()<-10 || temp.getX()>970)
                removeEgg(temp);
            temp.tick();
        }
    }

    public void render(Graphics g){
        for(int i=0; i<e.size(); i++){
            temp = e.get(i);
            temp.render(g);
        }
    }

    public void addEgg(Egg o){
        if(ammo.size()<eggLimit) {
            e.add(o);
            ammo.push(o);
        }
    }
    public void removeEgg(Egg o) {e.remove(o);}
    public boolean empty() {return e.isEmpty();}
    //public Egg getLast() {return e.getLast();}
    public int getEggsLeft() {
        if(!ammo.empty()){
            return eggLimit-ammo.size();
        }
        else if(ammo.size()==0)
            return eggLimit;
        return 0;
    }
    public void resetAmmo()
    {
        while(ammo.size()>0)
            ammo.pop();
    }
}
