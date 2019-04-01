import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.awt.Font;
import java.util.Random;

/**
 * Created by john_eberling on 3/28/17.
 */
public class Breaker extends JPanel implements ActionListener{
    private static JFrame window;
    private static int WIDTH = 900;
    private static  int HEIGHT = 650;
    private static int length = 40;
    private static int girth = 20;
    private BufferedImage img = null;
    private int y1 = 700, y2 = 1400;
    private boolean comrade = false, lose = false;
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private static ArrayList<Brick> bricks = new ArrayList<Brick>();
    private Ball ball = new Ball(-1, -1, HEIGHT-100, WIDTH/2, 20);
    private Panel panel = new Panel(WIDTH/2, HEIGHT-15);
    private static Key key = new Key();

    public void color(Graphics g) {
        g.setColor(Color.magenta);
        g.fillPolygon(panel.getPanel());
        g.setColor(Color.blue);
        g.fillOval(ball.getDx()-ball.getRadius(), ball.getDy()-ball.getRadius(), 2*ball.getRadius(), 2*ball.getRadius());

        if(balls.size() > 0){
            g.setColor(Color.green);
            for(int i=0; i<balls.size(); i++){
                g.fillOval(balls.get(i).getDx()-balls.get(i).getRadius(), balls.get(i).getDy()-balls.get(i).getRadius(),
                        2*balls.get(i).getRadius(), 2*balls.get(i).getRadius());
            }
        }

       for(int i=0; i<bricks.size(); i++){
           g.setColor(Color.red);
           if(bricks.get(i).speed()){
               g.setColor(Color.green);
           }
           else if(bricks.get(i).multi()){
               g.setColor(Color.magenta);
           }
           else if(bricks.get(i).soviet()) {
               g.setColor(Color.yellow);
           }
           g.fillRect(bricks.get(i).getLeftWall(), bricks.get(i).getUpperWall(), length, girth);

       }
       //comrade = true;
       if(comrade){
        if(y1 >= 0) {
            y1--;
            y2--;
        }
        g.setColor(Color.orange);
        g.setFont(new Font("Serif",Font.BOLD,75));
        g.drawString("IN SOVIET RUSSIA",25,100);
        g.drawString("BRICK BREAK YOU", 25, 210);
        try{
            img = ImageIO.read(new File("soviet.jpeg"));
        }
        catch(IOException e){
        }
        g.drawImage(img, 50, y1, 700, y2, null);
    }
    }

    public static void initializeBricks(){
        Random rand = new Random();
        int count = 0, speeder, multi, russian;
        for(int i=50; i<(HEIGHT-300); i+=girth+5){
            for(int k=60; k<(WIDTH-100); k+=length+5){
                bricks.add(new Brick(k, k+length, i, i+girth));
            }
        }

        while(count < 3) {
            speeder = rand.nextInt(bricks.size());
            multi = rand.nextInt(bricks.size());
            russian = rand.nextInt(bricks.size());
            if(!bricks.get(speeder).speed() && !bricks.get(multi).multi() && !bricks.get(russian).soviet()
                    && speeder != multi && multi != russian && speeder != russian) {
                bricks.get(speeder).changeSpeed();
                bricks.get(multi).changeMulti();
                bricks.get(russian).changeSoviet();
                count++;
            }
        }
    }

    public void addBalls(){
        balls.add(new Ball(-1, -1, 100, HEIGHT-100, 20));
        balls.add(new Ball(-1, -1, WIDTH/2, HEIGHT-100, 20));
        balls.add(new Ball(-1, -1, WIDTH-100, HEIGHT-100, 20));
    }

    public void checkBricks(Ball a){
        for(int i=0; i<bricks.size(); i++) {
            for(int k=bricks.get(i).getLeftWall(); k<bricks.get(i).getRightWall(); k++){
                if(Math.sqrt(Math.pow(bricks.get(i).getUpperWall()-a.getDy(), 2)+Math.pow(k-a.getDx(), 2)) == a.getRadius()){
                    if(bricks.get(i).speed()){
                        a.setVelX(2*a.getVelX());
                        a.setVelY(2*a.getVelY());
                    }
                    else if(bricks.get(i). soviet()){
                        comrade = true;
                    }
                    else if(bricks.get(i).multi()){
                        addBalls();
                    }
                    bricks.remove(i);
                    i--;
                    a.setVelY(-a.getVelY());
                }

                if(Math.sqrt(Math.pow(bricks.get(i).getLowerWall()-a.getDy(), 2)+Math.pow(k-a.getDx(), 2)) == a.getRadius()){
                    if(bricks.get(i).speed()){
                        a.setVelX(2*a.getVelX());
                        a.setVelY(2*a.getVelY());
                    }
                    else if(bricks.get(i). soviet()){
                        comrade = true;
                    }
                    else if(bricks.get(i).multi()){
                        addBalls();
                    }
                    bricks.remove(i);
                    i--;
                    a.setVelY(-a.getVelY());
                }

            }

            for(int k=bricks.get(i).getUpperWall(); k<bricks.get(i).getLowerWall(); k++){
                if(Math.sqrt(Math.pow(bricks.get(i).getLeftWall()-a.getDx(), 2)+Math.pow(k-a.getDy(), 2)) == a.getRadius()){
                    if(bricks.get(i).speed()){
                        a.setVelX(2*a.getVelX());
                        a.setVelY(2*a.getVelY());
                    }
                    else if(bricks.get(i). soviet()){
                        comrade = true;
                    }
                    else if(bricks.get(i).multi()){
                        addBalls();
                    }
                    bricks.remove(i);
                    i--;
                    a.setVelX(-a.getVelX());
                }

                if(Math.sqrt(Math.pow(bricks.get(i).getRightWall()-a.getDx(), 2)+Math.pow(k-a.getDy(), 2)) == a.getRadius()){
                    if(bricks.get(i).speed()){
                        a.setVelX(2*a.getVelX());
                        a.setVelY(2*a.getVelY());
                    }
                    else if(bricks.get(i). soviet()){
                        comrade = true;
                    }
                    else if(bricks.get(i).multi()){
                        addBalls();
                    }
                    bricks.remove(i);
                    i--;
                    a.setVelX(-a.getVelX());
                }

            }
        }
    }

    public void checkCircle(Ball a, int pos){
        int distance;
        int oldVelX = a.getVelX();
        int oldVelY = a.getVelY();
        //int collisionPointX, collisionPointY;
        for(int k=pos; k>=0; k--){
            if(a.getDx() + a.getRadius() + balls.get(k).getRadius() > balls.get(k).getDx()
                    && a.getDx() < balls.get(k).getDx() + a.getRadius() + balls.get(k).getRadius()//checks to see if boxes surrounding circles overlap
                    && a.getDy() + a.getRadius() + balls.get(k).getRadius() > balls.get(k).getDy()
                    && a.getDy() < balls.get(k).getDy() + a.getRadius() + balls.get(k).getRadius()) {
                //calculate distance between two circles using Pythagorean theorem with distance equaling c
                distance = (int) Math.sqrt(Math.pow(a.getDx() - balls.get(k).getDx(), 2) + Math.pow(a.getDy() - balls.get(k).getDy(), 2));
                if (distance <= a.getRadius() + balls.get(k).getRadius()) {//check to see if circles have collided
                    a.setVelX((a.getVelX() * (a.getMass() - balls.get(k).getMass()) +
                            (2 * balls.get(k).getMass() * balls.get(k).getVelX())) / (a.getMass() + balls.get(k).getMass()));
                    a.setVelY((a.getVelY() * (a.getMass() - balls.get(k).getMass()) +
                            (2 * balls.get(k).getMass() * balls.get(k).getVelY())) / (a.getMass() + balls.get(k).getMass()));
                    balls.get(k).setVelX((balls.get(k).getVelX() * (balls.get(k).getMass() - balls.get(k).getMass()) +
                            (2 * a.getMass() * oldVelX)) / (balls.get(k).getMass() + a.getMass()));
                    balls.get(k).setVelY((balls.get(k).getVelY() * (balls.get(k).getMass() - a.getMass()) +
                            (2 * a.getMass() * oldVelY)) / (balls.get(k).getMass() + a.getMass()));
                    // break;
                }
            }
        }
    }

    public void checkBorders(Ball a, boolean added, Graphics g){
        if(added){
            if (a.getDx() <= a.getRadius() || a.getDx() >= WIDTH - a.getRadius()) {
                a.setVelX(-a.getVelX());
            }
            if (a.getDy() <= a.getRadius()) {
                a.setVelY(-a.getVelY());
            }
            if(a.getDy() >= HEIGHT - a.getRadius()){
                balls.remove(a);
            }
        }
        else {
            if (a.getDx() <= a.getRadius() || a.getDx() >= WIDTH - a.getRadius()) {
                a.setVelX(-a.getVelX());
            }
            if (a.getDy() <= a.getRadius()){
                a.setVelY(-a.getVelY());
            }
            if(a.getDy() >= HEIGHT - a.getRadius() && !comrade){
                a.setVelY(0);
                a.setVelX(0);
                g.setColor(Color.magenta);
                g.setFont(new Font("Serif",Font.BOLD,100));
                g.drawString("YOU LOSE",200,100);
                lose = true;
            }

            //if(a.getDy()+a.getRadius()>panel.getY() && a.getDy()+a.getRadius()<panel.getY()+panel.getWidth()
             //       && a.getDx() > a.getCenter()){
        }
        int edge = panel.getCenter()-panel.getLength()/2;
        if(a.getDy()+a.getRadius() > panel.getY() && a.getDx()+a.getRadius()> edge  && a.getDx()-a.getRadius() < edge+panel.getLength()){
            for (int i = a.getDx(); i < panel.getCenter() + panel.getLength() / 2; i++) {
                if (i <= edge+30) {
                    a.setVelY(-a.getVelY());
                    if((panel.getVelocity() > 0 &&  a.getVelX() < 0) && (panel.getVelocity() < 0 &&  a.getVelX() > 0)) {
                        a.setVelX(-a.getVelX());
                    }
                } else if (i > edge+30 && i < panel.getCenter() + panel.getLength() / 2 - 30) {
                    a.setVelY(-a.getVelY());
                } else {
                    a.setVelY(-a.getVelY());
                    if((panel.getVelocity() > 0 &&  a.getVelX() < 0) && (panel.getVelocity() < 0 &&  a.getVelX() > 0)) {
                        a.setVelX(-a.getVelX());
                    }
                }
            }
            a.setDy(a.getDy()-7);
        }
    }

    public void changePanel(){
        if(key.getLeft()){
            panel.updatePanel("left");
        }

        else if(key.getRight()){
            panel.updatePanel("right");
        }
        else{
            panel.updatePanel("neutral");
        }
    }

    public static void main(String[] args) {
        window = new JFrame("Breaker");
        Breaker breaker = new Breaker();
        breaker.setBackground(Color.BLACK);
        window.setContentPane(breaker);
        breaker.setPreferredSize(new Dimension(WIDTH, HEIGHT));
       window.pack();
        window.setLocation(100,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setVisible(true);
        window.addKeyListener(key);
        window.setFocusable(true);
        initializeBricks();
        while(true){
            updateFrame();
        }
    } // end main

    public static void updateFrame(){
        try{
           Thread.sleep(4);
        }catch(Exception e){}
        window.revalidate();
        window.repaint();
    }

    public void actionPerformed(ActionEvent evt) {
        window.repaint();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        checkBricks(ball);
        changePanel();
        checkBorders(ball, false, g);
        checkCircle(ball, -1);
        if(balls.size()>0){
            for(int i=balls.size()-1; i>=0; i--){
                checkBricks(balls.get(i));
                checkCircle(balls.get(i), i);
                checkBorders(balls.get(i), true, g);
            }

            for(int i=0; i<balls.size(); i++){
                balls.get(i).setDx(balls.get(i).getDx()+balls.get(i).getVelX());
                balls.get(i).setDy(balls.get(i).getDy()+balls.get(i).getVelY());
            }
        }
        ball.setDx(ball.getDx()+ball.getVelX());
        ball.setDy(ball.getDy()+ball.getVelY());
        if(!lose)
        color(g);
    }

}