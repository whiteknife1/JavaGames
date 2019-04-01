import java.awt.*;

/**
 * Created by john_eberling on 4/3/17.
 */
public class Panel {

    private Point a1;
    private Point a2;
    private Point a3;
    private Point a4;
    private Point a5;
    private Point a6;
    private Polygon panel = new Polygon();
    private int center;
    private int y;
    private int panelLength;
    private int panelWidth;
    private double xVel;
    public int xLimit;

    public Panel(int center1, int y1){
        center = center1;
        y = y1-20;
        panelLength = 120;
        panelWidth = 20;

        a1 = new Point(center-panelLength/2, y+panelWidth);
        a2 = new Point(center+panelLength/2, y+panelWidth);
        a3 = new Point(center+panelLength/2-30, y);
        a4 = new Point(center-panelLength/2+30, y);
       // a5 = new Point();
        //a6 = new Point();

        panel.addPoint(a1.x, a1.y);
        panel.addPoint(a2.x, a2.y);
        panel.addPoint(a3.x, a3.y);
        panel.addPoint(a4.x, a4.y);
        xVel = 0;
        xLimit = center*2;
    }

    public  void updatePanel(String direction){
        if(direction.equals("left")){
            center -= 2;
            xVel = -1;
        }
        else if(direction.equals("right")){
            center += 2;
            xVel = 1;
        }
        else if(direction.equals("neutral")){
                xVel = 0;
        }
        if(center<panelLength/2){
            center = panelLength/2;
        }
        if(center+panelLength/2 == xLimit){
            center = xLimit-panelLength/2;
        }
        panel = new Polygon();
        a1 = new Point(center-panelLength/2, y+panelWidth);
        a2 = new Point(center+panelLength/2, y+panelWidth);
        a3 = new Point(center+panelLength/2-30, y);
        a4 = new Point(center-panelLength/2+30, y);
        // a5 = new Point();
        //a6 = new Point();

        panel.addPoint(a1.x, a1.y);
        panel.addPoint(a2.x, a2.y);
        panel.addPoint(a3.x, a3.y);
        panel.addPoint(a4.x, a4.y);
    }

    public double getVelocity(){ return xVel; }

    public Polygon getPanel(){ return panel; }

    public int getCenter(){
        return center;
    }

    public int getY(){ return y; }

    public int getLength(){ return panelLength; }

    public int getWidth(){ return panelWidth; }
}
