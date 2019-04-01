import java.awt.*;
import java.awt.geom.Ellipse2D;
/**
 * Created by John Eberling on 5/31/2017.
 */
public class Egg {
    private Ellipse2D egg = new Ellipse2D.Double();
    public Egg(int x, int y){
        egg = new Ellipse2D.Double(x, y, 15, 20);
    }

    public Ellipse2D getEgg(){
        return egg;
    }

    public void drawEgg(Graphics g){
        g.fillOval((int)egg.getX(), (int)egg.getY(), (int)egg.getWidth(), (int)egg.getHeight());
    }
}
