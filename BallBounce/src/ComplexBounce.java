import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by John Eberling on 3/15/17.
 */
public class ComplexBounce extends JPanel implements ActionListener {
    private static JFrame frame;
    private static int HEIGHT = 700;
    private static int WIDTH = 1250;
    private static ball[] balls = new ball[40];
   // private ball a = new ball(1, 1, 10, 100, 100, 50);
    //private ball b = new ball(0, 0, 10, 300, 100, 50);

    public void drawBall(Graphics g){
        g.setColor(Color.red);
        for(int i=0; i<balls.length; i++) {
            g.fillOval(balls[i].getDx() - balls[i].getRadius(), balls[i].getDy() - balls[i].getRadius(),
                    2*balls[i].getRadius(), 2*balls[i].getRadius());
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
        int dxInc = WIDTH/(balls.length);
       // dxInc = dxInc/2;
        int dyInc = HEIGHT/(balls.length);
        //dyInc = dyInc/2;
       dx = dxInc;
        dy = dyInc;
        int maxRadius = dy/2;
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
            balls[i] = new ball(vx, vy, 10, dx+radius, dy+radius, radius);
            dx += 4*dxInc;
            if(dx+4*dxInc >= WIDTH){
                dy += 6*dyInc;
                dx = dxInc;
            }
        }
    }

    public void checkCircle(ball a, int pos){
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

    public static void main(String[] args){
        frame = new JFrame("ComplexBounce");
        ComplexBounce complex = new ComplexBounce();
        complex.setBackground(Color.BLACK);
        frame.setContentPane(complex);
        complex.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        complex.setLocation(100, 100);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        initializeBalls();
        while(true){
            updateFrame();
        }
    }

    public static void updateFrame(){
        try
        {Thread.sleep(10);}
        catch(Exception e){}
        frame.revalidate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent evt){
        repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0; i<balls.length; i++) {
            if (balls[i].getDx() == balls[i].getRadius() || balls[i].getDx() == WIDTH - balls[i].getRadius()) {
                balls[i].setVelX(-balls[i].getVelX());
            }
            if (balls[i].getDy() == balls[i].getRadius() || balls[i].getDy() == HEIGHT - balls[i].getRadius()) {
                balls[i].setVelY(-balls[i].getVelY());
            }
        }
        for(int i=0; i<balls.length-1; i++) {
            checkCircle(balls[i], i);
        }

        for(int i=0; i<balls.length; i++) {
            balls[i].setDx(balls[i].getDx() + balls[i].getVelX());
            balls[i].setDy(balls[i].getDy() + balls[i].getVelY());
        }
        drawBall(g);
    }
}

class ball{
    private int dx;
    private int dy;
    private int velX ;
    private int velY;
    private int mass;
    private int radius;

    public ball(int vx, int vy, int mass1, int x, int y, int rad){
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