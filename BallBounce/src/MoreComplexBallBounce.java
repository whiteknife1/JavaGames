import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * Created by john_eberling on 3/6/17.
 */
public class MoreComplexBallBounce extends JPanel implements ActionListener{

    private static JFrame frame;
    private static int WIDTH = 1000;
    private static int HEIGHT = 650;
    private static ball2[] balls = new ball2[6];
    private triangle tri = new triangle(WIDTH, HEIGHT);
    private Polygon poly = new Polygon();
    private Point[] line1 = new Point[2];
    private Point[] line2 = new Point[2];
    private int slope1, slope2, b1, b2;
    private boolean delay = false;
    private boolean color = false;

    public void drawBall(Graphics g){
        g.setColor(Color.green);
        if(color )g.setColor(Color.red);
        g.setColor(Color.red);

        for(int i=0; i<balls.length; i++) {
            g.fillOval(balls[i].getDx() - balls[i].getRadius(), balls[i].getDy() - balls[i].getRadius(),
                    2*balls[i].getRadius(), 2*balls[i].getRadius());
        }

        Graphics g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
       g2d.fillPolygon(poly);
        for(int  i=0; i < tri.getPoints().length; i++){
            g2d.fillRect(tri.getPoints()[i].x-1, tri.getPoints()[i].y-1, 3, 3);
            g2d.fillRect(tri.getPoints()[i].x-1, tri.getPoints()[i].y-1, 3, 3);

        }
        g.setColor(Color.white);
        g.drawLine(0, 0, 0, HEIGHT);
        g.drawLine(0, 0, WIDTH, 0);
        g.drawLine(WIDTH, 0, WIDTH, HEIGHT);
        g.drawLine(0, HEIGHT, WIDTH, HEIGHT);
    }

    public static void initializeBalls(){
        Random rand = new Random();
        int vx, vy, dx, dy, radius;
        int dxInc = WIDTH/2-400;
        // dxInc = dxInc/2;
        int dyInc = HEIGHT/2-300;
        //dyInc = dyInc/2;
        dx = dxInc;
        dy = dyInc;
        int maxRadius = dy/5;
        for(int i=0; i<balls.length; i++){
            if(i%2 == 0){
                vx = -1;
                vy = -1;
            }
            else{
                vx = 1;
                vy = 1;
            }
            radius = 20+rand.nextInt(maxRadius);
            dx = dx+rand.nextInt(dxInc);
            balls[i] = new ball2(vx, vy, 10, dx+radius, dy+radius, radius);
            dx += dxInc;
            if(dx+dxInc >= WIDTH){
                dy += 3*dyInc;
                dx = dxInc;
            }
        }
    }

    public void wallCollision(ball2 a){
        if(a.getDx() == a.getRadius() || a.getDx() == WIDTH-a.getRadius()){
            a.setVelX(-a.getVelX());
        }
        if(a.getDy() == a.getRadius() || a.getDy() == HEIGHT-a.getRadius()){
            a.setVelY(-a.getVelY());
        }
    }

    public void initializeTriangle(){
        tri.pivot();
        poly = tri.setPoly();
        tri.setToOriginal(WIDTH, HEIGHT);
        tri.setVel();
    }

    public void checkCircle(ball2 a, int pos){
        int distance;
        int oldVelX = a.getVelX();
        int oldVelY = a.getVelY();
        //int collisionPointX, collisionPointY;
        for(int k=pos+1; k<balls.length; k++){
            if(a.getDx() + a.getRadius() + balls[k].getRadius() > balls[k].getDx()
                    && a.getDx() < balls[k].getDx() + a.getRadius() + balls[k].getRadius()//checks to see if boxes surrounding circles overlap
                    && a.getDy() + a.getRadius() + balls[k].getRadius() > balls[k].getDy()
                    && a.getDy() < balls[k].getDy() + a.getRadius() + balls[k].getRadius()) {
                //calculate distance between two circles using Pythagorean theorem with distance equaling c
                distance = (int) Math.sqrt(Math.pow(a.getDx() - balls[k].getDx(), 2) + Math.pow(a.getDy() - balls[k].getDy(), 2));
                if (distance <= a.getRadius() + balls[k].getRadius()) {//check to see if circles have collided
                    a.setVelX((a.getVelX() * (a.getMass() - balls[k].getMass()) +
                            (2 * balls[k].getMass() * balls[k].getVelX())) / (a.getMass() + balls[k].getMass()));
                    a.setVelY((a.getVelY() * (a.getMass() - balls[k].getMass()) +
                            (2 * balls[k].getMass() * balls[k].getVelY())) / (a.getMass() + balls[k].getMass()));
                    balls[k].setVelX((balls[k].getVelX() * (balls[k].getMass() - balls[k].getMass()) +
                            (2 * a.getMass() * oldVelX)) / (balls[k].getMass() + a.getMass()));
                    balls[k].setVelY((balls[k].getVelY() * (balls[k].getMass() - a.getMass()) +
                            (2 * a.getMass() * oldVelY)) / (balls[k].getMass() + a.getMass()));
                    // break;
                }
            }
        }
    }

   public void checkTriangle(ball2 a){
       if(poly.contains(a.getDx()+a.getRadius(), a.getDy()-a.getRadius())
       ||poly.contains(a.getDx()-a.getRadius(), a.getDy()-a.getRadius())
       ||poly.contains(a.getDx()+a.getRadius(), a.getDy()+a.getRadius())
       ||poly.contains(a.getDx()-a.getRadius(), a.getDy()+a.getRadius())){
           if(!delay)
           updateVelocity(a);
           color = true;
       }
       else {
           color = false;
           delay = false;
       }

    }

    public void updateVelocity(ball2 a){
            a.setVelX((a.getVelX() * (a.getMass() - a.getMass()) +
                    (2 * a.getMass() * tri.getVelX())) / (a.getMass() + a.getMass()));
            a.setVelY((a.getVelY() * (a.getMass() - a.getMass()) +
                    (2 * a.getMass() * tri.getVelY())) / (a.getMass() + a.getMass()));
        if(((a.getVelX()>0 && tri.getVelX()>0) || (a.getVelX()<0 && tri.getVelX()<0) )
                && ((a.getVelY()>0 && tri.getVelY()>0) || (a.getVelY()<0 && tri.getVelY()<0))){
            a.setVelX(-a.getVelX());
            a.setVelY(-a.getVelY());
        }
        delay = true;
       // tri.changeAngle();
    }

    public static void main(String[] args){
        frame = new JFrame("MoreComplexBallBounce");
        MoreComplexBallBounce complex = new MoreComplexBallBounce();
        complex.setBackground(Color.BLACK);
        frame.setContentPane(complex);
        complex.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        complex.setLocation(100, 100);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        //Timer time = new Timer(20, complex);
        initializeBalls();
        while(true){
            updateFrame();
        }
    }

    public static void updateFrame(){
        try
        {Thread.sleep(5);}
        catch(Exception e){}
        frame.revalidate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent evt){
            repaint();
        }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        initializeTriangle();
        for(int i=0; i<balls.length; i++) {
            checkCircle(balls[i], i);
            checkTriangle(balls[i]);
            wallCollision(balls[i]);
        }

        for(int i=0; i<balls.length; i++) {
            balls[i].setDx(balls[i].getDx() + balls[i].getVelX());
            balls[i].setDy(balls[i].getDy() + balls[i].getVelY());
        }
        drawBall(g);
    }
}

class ball2{
    private int dx;
    private int dy;
    private int velX ;
    private int velY;
    private int mass;
    private int radius;

    public ball2(int vx, int vy, int mass1, int x, int y, int rad){
        velX = vx;
        velY =vy;
        mass = mass1;
        dx = x;
        dy = y;
        radius = rad;
    }

    public void setVelX(int x){
        velX = x;
    }

    public void setVelY(int y){
        velY = y;
    }

    public void setDx(int x) {dx = x; }

    public void setDy(int y) {dy = y; }

    public int getVelX(){
        return velX;
    }

    public int getVelY(){
        return velY;
    }

    public int getMass(){
        return mass;
    }

    public int getDx(){
        return dx;
    }

    public int getDy(){
        return dy;
    }

    public int getRadius() { return radius; }
}

class triangle{
    AffineTransform transform = new AffineTransform();
    Polygon poly = new Polygon();
    private Point[] points;
    private Point[] original = new Point[3];
    private Point center;
    private double angle;
    private int velX, velY;
    private int WIDTH, HEIGHT;

    public triangle(int width, int height){
        original[0] = new Point(width/2-100, height/2-100);
        original[1] = new Point(width/2+100, height/2-100);
        original[2] = new Point(width/2, height/2+50);
        points = original;
        center = new Point(width/2, height/2+50);
        angle = 1;
        WIDTH = width;
        HEIGHT  = height;
    }

    public void pivot(){
            AffineTransform.getRotateInstance
                    (Math.toRadians(angle), center.x, center.y)
                    .transform(original, 0, points, 0, 2);
            //angle=angle+.00001;

    }

    public void setToOriginal(int width, int height){
        points = original;
    }


    public Polygon setPoly(){
        Polygon temp = new Polygon();
        for(int  i=0; i < points.length; i++){
            temp.addPoint(points[i].x, points[i].y);
        }
        return temp;

    }

    public Polygon getPoly(){
        poly = setPoly();
        return poly;
    }

    public void setVel(){
            if (points[1].x >= WIDTH / 2 && points[1].y <= HEIGHT / 2) {
                velX = 1;
                velY = 1;
            } else if (points[1].x >= WIDTH / 2 && points[1].y > HEIGHT / 2) {
                velX = -1;
                velY = 1;
            } else if (points[1].x < WIDTH / 2 && points[1].y <= HEIGHT / 2) {
                velX = 1;
                velY = -1;
            } else {
                velX = -1;
                velY = -1;
            }

    }

    public int getVelX(){
            return velX;
    }

    public int getVelY(){
            return velY;
    }

    public void changeAngle(){
        angle = -angle;
    }

    public Point[] getPoints(){ return points; }

    public Point[] getOriginal(){ return original; }
}
