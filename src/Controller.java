import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

public class Controller {

    LinkedList<Egg> e = new LinkedList<Egg>();
    private Stack<Egg> ammo = new Stack<Egg>();
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
        if(ammo.size()<20) {
            e.add(o);
            ammo.push(o);
        }
    }
    public void removeEgg(Egg o) {e.remove(o);}
    public boolean empty() {return e.isEmpty();}
    public Egg getLast() {return e.getLast();}
    public int getEggsLeft() {
        //System.out.println(ammo.size());
        if(!ammo.empty()){
            return 20-ammo.size();
        }
        else if(ammo.size()==0)
            return 20;
        return 0;
    }
}
