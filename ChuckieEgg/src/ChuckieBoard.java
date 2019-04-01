import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Font;

/**
 * Created by John Eberling on 4/28/17.
 */
public class ChuckieBoard extends JPanel implements ActionListener {
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private ArrayList<Ladder> ladders = new ArrayList<Ladder>();
    private static  ArrayList<Egg> eggs = new ArrayList<Egg>();
    private Hen hen1 = new Hen(100, 10);
    private Hen hen2 = new Hen(1150, 635);
    private Hen hen3 = new Hen(1065, 160);
    private Hen hen4 = new Hen(150, 480);
    private Hen hen5 = new Hen(905, 35);
    private static Character gator = new Character(20, 640);
    private Rectangle2D gatorBox = new Rectangle2D.Double();
    private Rectangle2D henBox1 = new Rectangle2D.Double(100, 10, 50, 50);
    private Rectangle2D henBox2 = new Rectangle2D.Double(1150, 635, 50, 50);
    private Rectangle2D henBox3 = new Rectangle2D.Double(1065, 160, 50, 50);
    private Rectangle2D henBox4 = new Rectangle2D.Double(150, 480, 50, 50);
    private Rectangle2D henBox5 = new Rectangle2D.Double(905, 35, 50, 50);
    BufferedImage img, img2, img3, img4, img5;
    private static ArrayList<String> h1, h2, h3, h4, h5;
    private static ArrayList<Integer> hd1, hd2, hd3, hd4, hd5;
    private static ArrayList<Integer> shd1, shd2, shd3, shd4, shd5;
    private int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;
    private final static int WIDTH = 1250, HEIGHT = 700;
    private long nanoStart = System.nanoTime(), nanoEnd;
    private int seconds = 0;
    private int minutes = 0;
    private static Key key = gator.getKey();
    private static JFrame window;
    boolean lose = false, win = false;

        public void drawFrame(Graphics g, int frameNum) {
            if(win){
                g.setFont(new Font("Comic Sans", Font.BOLD, 55));
                g.setColor(Color.GREEN);
                g.drawString("You Win!", 550, 250);
                if(seconds < 10){
                    g.drawString("Your Time was "+minutes+":0"+seconds, 400, 350);
                }
                else {
                    g.drawString("Your Time was " + minutes + ":" + seconds, 400, 350);
                }
            }
            else if(lose) {
                g.setFont(new Font("Comic Sans", Font.BOLD, 55));
                g.setColor(Color.magenta);
                g.drawString("You Lose!", 550, 250);
            }

            else {
                nanoEnd = System.nanoTime()-nanoStart;
                if(nanoEnd >= 1000000000){
                    nanoStart = System.nanoTime();
                        seconds++;
                        if(seconds == 60) {
                            seconds = 0;
                            minutes++;
                        }
                }
                g.setFont(new Font("Comic Sans", Font.BOLD, 25));
                g.setColor(Color.GREEN);
                if(seconds<10){
                    g.drawString(minutes+":0"+seconds, 10, 20);
                }
                else {
                    g.drawString(minutes + ":" + seconds, 10, 20);
                }
                bricks.add(new Brick(g, 0, 675, 300));
                bricks.add(new Brick(g, 490, 675, 830));
                bricks.add(new Brick(g, 50, 520, 140));
                bricks.add(new Brick(g, 460, 520, 240));
                bricks.add(new Brick(g, 730, 520, 170));
                bricks.add(new Brick(g, 950, 478, 100));
                bricks.add(new Brick(g, 1050, 400, 200));
                bricks.add(new Brick(g, 200, 338, 400));
                bricks.add(new Brick(g, 800, 338, 200));
                bricks.add(new Brick(g, 0, 200, 100));
                bricks.add(new Brick(g, 250, 200, 100));
                bricks.add(new Brick(g, 450, 200, 50));
                bricks.add(new Brick(g, 530, 200, 50));
                bricks.add(new Brick(g, 730, 200, 100));
                bricks.add(new Brick(g, 945, 200, 150));
                bricks.add(new Brick(g, 125, 50, 400));
                bricks.add(new Brick(g, 730, 75, 200));

                ladders.add(new Ladder(g, 700, 50, 23));
                ladders.add(new Ladder(g, 300, 338, 13));
                ladders.add(new Ladder(g, 500, 50, 10));
                drawEggs(g);
                drawCharacter(g);
                drawHen(g);

                if (gatorBox.intersects(henBox1) || gatorBox.intersects(henBox2) || gatorBox.intersects(henBox3) || gatorBox.intersects(henBox4) || gatorBox.intersects(henBox5)) {
                    lose = true;
                }
                else if(gatorBox.getY() > 700){
                    lose = true;
                }
                else if(eggs.size()==0){
                    win = true;
                }
            }
        }

        public void drawEggs(Graphics g){
            for(int i = eggs.size()-1; i >= 0; i--) {
                    if (eggs.get(i).getEgg().intersects(gatorBox)) {
                        eggs.remove(i);
                    } else
                        eggs.get(i).drawEgg(g);
            }
        }

        public void drawHen(Graphics g){
            hen1.getHen(h1.get(p1), hd1.get(p1));
            hen2.getHen(h2.get(p2), hd2.get(p2));
            hen3.getHen(h3.get(p3), hd3.get(p3));
            hen4.getHen(h4.get(p4), hd4.get(p4));
            hen5.getHen(h5.get(p5), hd5.get(p5));

            hd1.set(p1, hd1.get(p1)-1);
            hd2.set(p2, hd2.get(p2)-1);
            hd3.set(p3, hd3.get(p3)-1);
            hd4.set(p4, hd4.get(p4)-1);
            hd5.set(p5, hd5.get(p5)-1);

            if(hd1.get(p1) == 0){
                hd1.set(p1, shd1.get(p1));
                if(p1 == h1.size()-1)
                    p1 = 0;
                else
                   p1++;
            }

            if(hd2.get(p2)==0){
                hd2.set(p2, shd2.get(p2));
                if(p2 == h2.size()-1){
                    p2 = 0;
                }
                else{
                    p2++;
                }
            }

            if(hd3.get(p3)==0){
                hd3.set(p3, shd3.get(p3));
                if(p3 == h3.size()-1){
                    p3 = 0;
                }
                else{
                    p3++;
                }
            }

            if(hd4.get(p4) == 0){
                hd4.set(p4, shd4.get(p4));
                if(p4 == h4.size()-1)
                    p4 = 0;
                else
                    p4++;
            }

            if(hd5.get(p5) == 0){
                hd5.set(p5, shd5.get(p5));
                if(p5 == h5.size()-1)
                    p5 = 0;
                else
                    p5++;
            }

            if (frameNum % 12 == 0) {
                try {
                    img = ImageIO.read(new File(hen1.getHen(h1.get(p1), 0)));
                    img2 = ImageIO.read(new File(hen2.getHen(h2.get(p2), 0)));
                    img3 = ImageIO.read(new File(hen3.getHen(h3.get(p3), 0)));
                    img4 = ImageIO.read(new File(hen4.getHen(h4.get(p4), 0)));
                    img5 = ImageIO.read(new File(hen5.getHen(h5.get(p5), 0)));
                } catch (IOException e) {
                }
            } else if (frameNum % 8 == 0) {
                try {
                    img = ImageIO.read(new File(hen1.getHen(h1.get(p1), 1)));
                    img2 = ImageIO.read(new File(hen2.getHen(h2.get(p2), 1)));
                    img3 = ImageIO.read(new File(hen3.getHen(h3.get(p3), 1)));
                    img4 = ImageIO.read(new File(hen4.getHen(h4.get(p4), 1)));
                    img5 = ImageIO.read(new File(hen5.getHen(h5.get(p5), 1)));
                } catch (IOException e) {
                }
            } else if (frameNum % 4 == 0) {
                try {
                    img = ImageIO.read(new File(hen1.getHen(h1.get(p1), 2)));
                    img2 = ImageIO.read(new File(hen2.getHen(h2.get(p2), 2)));
                    img3 = ImageIO.read(new File(hen3.getHen(h3.get(p3), 2)));
                    img4 = ImageIO.read(new File(hen4.getHen(h4.get(p4), 2)));
                    img5 = ImageIO.read(new File(hen5.getHen(h5.get(p5), 2)));
                } catch (IOException e) {
                }
            }
            g.drawImage(img, hen1.getX(), hen1.getY(), 50, 50, null);
            g.drawImage(img2, hen2.getX(), hen2.getY(), 50, 50, null);
            g.drawImage(img3, hen3.getX(), hen3.getY(), 50, 50, null);
            g.drawImage(img4, hen4.getX(), hen4.getY(), 50, 50, null);
            g.drawImage(img5, hen5.getX(), hen5.getY(), 50, 50, null);

            henBox1.setRect(hen1.getX(), hen1.getY(), 50, 50);
            henBox2.setRect(hen2.getX(), hen2.getY(), 50, 50);
            henBox3.setRect(hen3.getX(), hen3.getY(), 50, 50);
            henBox4.setRect(hen4.getX(), hen4.getY(), 50, 50);
            henBox5.setRect(hen5.getX(), hen5.getY(), 50, 50);
        }

        public void drawCharacter(Graphics g){
            if(gatorBox == null){
                 gatorBox = new Rectangle2D.Double(gator.getX(), gator.getY(), 125, 50);
            }
            if(!gator.isVertical()) {
                g.drawImage(gator.getImage(), gator.getX(), gator.getY(), 125, 50, null);
                g.setColor(Color.cyan);
                if(gator.getDirection().equals("left")) {
                    g.drawRect(gator.getX() + 32, gator.getY() + 17, 82, 25);
                    gatorBox.setRect(gator.getX() + 42, gator.getY() + 17, 82, 25);
                }
                else{
                    g.drawRect(gator.getX() + 22, gator.getY() + 17, 82, 25);
                    gatorBox.setRect(gator.getX() + 42, gator.getY() + 17, 82, 25);
                }

            }
            else if(gator.isVertical()){
                g.drawImage(gator.getImage(), gator.getX(), gator.getY(), 59, 95, null);
                g.setColor(Color.cyan);
                if(gator.getDirection().equals("down")) {
                    //g.drawRect(gator.getX() + 15, gator.getY() + 20, 30, 50);
                    gatorBox.setRect(gator.getX() + 15, gator.getY() + 20, 30, 50);
                }
                else{
                    g.drawRect(gator.getX() + 15, gator.getY() + 27, 30, 50);
                    gatorBox.setRect(gator.getX() + 15, gator.getY() + 27, 30, 50);
                }
            }
            checkGravity();
        }

        public void checkGravity(){
            gator.setGravity(true);
            for(int i=0; i<bricks.size(); i++){
                if(bricks.get(i).getBrick().intersects(gatorBox)){
                    gator.setGravity(false);
                }
            }
            for(int i=0; i<ladders.size(); i++){
                if(ladders.get(i).getLadder().intersects(gatorBox)){
                    gator.setGravity(false);
                }
            }
        }

        public static  void initializeHens(){
            h1 = new ArrayList<String>();
            hd1 = new ArrayList<Integer>();
            h1.add("right");
            h1.add("down");
            h1.add("left");
            h1.add("right");
            h1.add("up");
            h1.add("left");
            hd1.add(317);
            hd1.add(228);
            hd1.add(210);
            hd1.add(210);
            hd1.add(228);
            hd1.add(317);
            shd1 = new ArrayList<Integer>(hd1);

            h2 = new ArrayList<String>();
            hd2 = new ArrayList<Integer>();
            h2.add("left");
            h2.add("up");
            h2.add("left");
            h2.add("right");
            h2.add("up");
            h2.add("right");
            h2.add("left");
            h2.add("down");
            h2.add("left");
            h2.add("right");
            h2.add("down");
            h2.add("right");
            hd2.add(362);
            hd2.add(126);
            hd2.add(185);
            hd2.add(185);
            hd2.add(352);
            hd2.add(135);
            hd2.add(135);
            hd2.add(352);
            hd2.add(150);
            hd2.add(150);
            hd2.add(126);
            hd2.add(362);
            shd2 = new ArrayList<Integer>(hd2);

            h3 = new ArrayList<String>();
            hd3 = new ArrayList<Integer>();
            h3.add("left");
            h3.add("down");
            h3.add("right");
            h3.add("down");
            h3.add("right");
            h3.add("left");
            h3.add("up");
            hd3.add(135);
            hd3.add(110);
            hd3.add(95);
            hd3.add(50);
            hd3.add(110);
            hd3.add(70);
            hd3.add(160);
            shd3 = new ArrayList<Integer>(hd3);

            h4 = new ArrayList<String>();
            hd4 = new ArrayList<Integer>();
            h4.add("left");
            h4.add("right");
            hd4.add(100);
            hd4.add(100);
            shd4 = new ArrayList<Integer>(hd4);

            h5 = new ArrayList<String>();
            hd5 = new ArrayList<Integer>();
            h5.add("left");
            h5.add("down");
            h5.add("right");
            h5.add("left");
            h5.add("right");
            h5.add("up");
            h5.add("right");
            hd5.add(173);
            hd5.add(357);
            hd5.add(100);
            hd5.add(250);
            hd5.add(150);
            hd5.add(357);
            hd5.add(173);
            shd5 = new ArrayList<Integer>(hd5);
        }

        public static void initializeEggs(){
            eggs.add(new Egg(200, 655));
            eggs.add(new Egg(550, 655));
            eggs.add(new Egg(800, 655));
            eggs.add(new Egg(1090, 655));
            eggs.add(new Egg(120, 500));
            eggs.add(new Egg(460, 500));
            eggs.add(new Egg(590, 500));
            eggs.add(new Egg(850, 500));
            eggs.add(new Egg(990, 458));
            eggs.add(new Egg(1150, 380));
            eggs.add(new Egg(250, 318));
            eggs.add(new Egg(475, 318));
            eggs.add(new Egg(900, 318));
            eggs.add(new Egg(50, 180));
            eggs.add(new Egg(280, 180));
            eggs.add(new Egg(475, 180));
            eggs.add(new Egg(555, 180));
            eggs.add(new Egg(780, 180));
            eggs.add(new Egg(955, 180));
            eggs.add(new Egg(1055, 180));
            eggs.add(new Egg(145, 30));
            eggs.add(new Egg(295, 30));
            eggs.add(new Egg(760, 55));
            eggs.add(new Egg(840, 55));
        }

    public static void main(String[] args) {
            window = new JFrame("Chuckie");
            ChuckieBoard chuckie = new ChuckieBoard();
            chuckie.setBackground(Color.BLACK);
            window.setContentPane(chuckie);
            chuckie.setPreferredSize(new Dimension(1250, 700));
           window.pack();
            window.setLocation(10,10);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(true);
            Timer frameTimer = new Timer(0,chuckie);
            window.setVisible(true);
            window.addKeyListener(key);
            initializeHens();
            initializeEggs();
            frameTimer.start();
        } // end main

        private int frameNum = 0;

        public void actionPerformed(ActionEvent evt) {
            frameNum++;
            revalidate();
            repaint();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawFrame(g, frameNum);
        }


    }