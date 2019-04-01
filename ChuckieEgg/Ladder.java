import java.awt.*;
import java.awt.geom.Rectangle2D;
/**
 * Created by John Eberling on 4/28/17.
 */
public class Ladder {
    private Rectangle2D ladder = new Rectangle2D.Double();

    public Ladder(Graphics g, int x1, int y1, int wrungs1){
        drawLadder(g, x1, y1, wrungs1);
    }
    public void drawLadder(Graphics g, int x, int y, int wrungs){
        int step = ((wrungs+2)*25)/(wrungs);
        ladder = new Rectangle2D.Double(x, y, 35, wrungs*step);
        g.setColor(Color.blue);
        g.fillRect((int)ladder.getX(), (int)ladder.getY(), (int)ladder.getWidth(), (int)ladder.getHeight());
        g.setColor(new Color(200, 10, 255));
        g.fill3DRect(x, y, 5, (wrungs+2)*25, false);
        g.fill3DRect(x+30, y, 5, (wrungs+2)*25, false);
        for(int i = 0; i<wrungs; i++){
            g.fill3DRect(x+5, y+step*i+step/2, 25, 5, true);
        }
    }

    public Rectangle2D getLadder(){
        return ladder;
    }
}
