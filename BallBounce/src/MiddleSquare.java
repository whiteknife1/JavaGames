import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by John Eberling on 3/15/17.
 */
public class MiddleSquare extends JPanel implements ActionListener {
    private static JFrame frame;
    private static int HEIGHT = 550;
    private static int WIDTH = 900;
    private ball1 a = new ball1(1, 1, 10, 100, 100, 50);
    int squareX = WIDTH/2-150;
    int squareY = HEIGHT/2-150;
    int squareWidth = 300;
    public void drawBall(Graphics g){
        g.setColor(Color.red);
        g.fillOval(a.getDx()-a.getRadius(), a.getDy()-a.getRadius(), 100, 100);
        g.setColor(Color.blue);
        g.fillRect(squareX, squareY, squareWidth, squareWidth);
        g.setColor(Color.white);
        g.drawLine(0, 0, 0, HEIGHT);
        g.drawLine(0, 0, WIDTH, 0);
        g.drawLine(WIDTH, 0, WIDTH, HEIGHT);
        g.drawLine(0, HEIGHT, WIDTH, HEIGHT);
    }

    public static void main(String[] args){
        frame = new JFrame("MiddleSquare");
        MiddleSquare middle = new MiddleSquare();
        middle.setBackground(Color.BLACK);
        frame.setContentPane(middle);
        middle.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        middle.setLocation(100, 100);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
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

        if(a.getDx() == a.getRadius() || a.getDx() == WIDTH-a.getRadius()){
            a.setVelX(-a.getVelX());
        }
        if(a.getDy() == a.getRadius() || a.getDy() == HEIGHT-a.getRadius()) {
            a.setVelY(-a.getVelY());
        }
         //checkSquare

            if(a.getVelX()>0 && a.getDx()+a.getRadius() == squareX
                    && (a.getDy()+a.getRadius() >= squareY && a.getDy()-a.getRadius() <= squareY+squareWidth)){
                a.setVelX(-a.getVelX());
            }
            else if(a.getVelX()<0 && a.getDx()-a.getRadius() == squareX+squareWidth
                    && (a.getDy() + a.getRadius() >= squareY && a.getDy()-a.getRadius() <= squareY+squareWidth)){
                a.setVelX(-a.getVelX());
            }
            if(a.getVelY()>0 && a.getDy()+a.getRadius() == squareY
                    && (a.getDx()+a.getRadius() >= squareX && a.getDx()-a.getRadius() <= squareX+squareWidth)){
                a.setVelY(-a.getVelY());
            }
            else if(a.getVelY()<0 && a.getDy()-a.getRadius() == squareY+squareWidth
                    && (a.getDx()+a.getRadius() >= squareX && a.getDx()-a.getRadius() <= squareX+squareWidth)){
                a.setVelY(-a.getVelY());
            }

        a.setDx(a.getDx()+a.getVelX());
        a.setDy(a.getDy()+a.getVelY());

        drawBall(g);
    }
}

class ball1{
    private int dx;
    private int dy;
    private int velX ;
    private int velY;
    private int mass;
    private int radius;

    public ball1(int vx, int vy, int mass1, int x, int y, int rad){
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