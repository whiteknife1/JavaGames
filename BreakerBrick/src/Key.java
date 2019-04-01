import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Created by John Eberling on 3/28/17.
 */
public class Key implements KeyListener{
    private boolean left = false, right = false;

    public Key()
    {
        super();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
           left = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
        }
    }

    public boolean getLeft(){
        return left;
    }

    public boolean getRight(){
        return right;
    }
}
