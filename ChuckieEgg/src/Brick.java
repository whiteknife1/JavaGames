import java.awt.*;
import java.awt.geom.Rectangle2D;
/**
 * Created by John Eberling on 4/28/17.
 */
public class Brick {
    private Rectangle2D brick = new Rectangle2D.Double();

    public Brick(Graphics g, int x, int y, int length){
        drawBrick(g, x, y, length);
    }

    public void drawBrick(Graphics g, int x, int y, int length){
        g.setColor(Color.red);
        brick = new Rectangle2D.Double(x, y, length-1, 16);
        g.fill3DRect( x, y, (int)brick.getWidth(), (int)brick.getHeight(), false);
        length = length/16;
        for(int i=0; i<length; i++){
            //g.fill3DRect(x, y, 16, 16, false);
            g.setColor(new Color(250, 200, 120));
            g.drawLine(x+7, y, x+7, y+15);
            g.drawLine(x, y+7, x+15, y+7);
            x += 16;
        }
    }

    public Rectangle2D getBrick(){
        return brick;
    }
}
