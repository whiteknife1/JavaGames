import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Created by John Eberling on 5/8/2017.
 */
public class Key implements KeyListener{
    private boolean left = false, right = false, up = false, down = false;

    public Key()
    {
        super();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            left = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_W){
            up = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            down = false;
        }
    }

    public boolean getLeft(){
        return left;
    }

    public boolean getRight(){
        return right;
    }

    public boolean getUp() { return up; }

    public boolean getDown(){ return down; }
}
