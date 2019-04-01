import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by John Eberling on 3/6/17.
 */

public class SimpleBounce extends JPanel implements ActionListener {
    private static JFrame frame;
    private static int HEIGHT = 550;
    private static int WIDTH = 900;
    private int dx = 100;
    private int dy = 100;
    private int movex = -1;
    private int movey = -1;
    
    public void drawBall(Graphics g){
        g.setColor(Color.red);
        g.fillOval(dx, dy, 100, 100);
        g.setColor(Color.white);
        g.drawLine(0, 0, 0, HEIGHT);
        g.drawLine(0, 0, WIDTH, 0);
        g.drawLine(WIDTH, 0, WIDTH, HEIGHT);
        g.drawLine(0, HEIGHT, WIDTH, HEIGHT);
    }

    public static void main(String[] args){
        frame = new JFrame("SimpleBounce");
        SimpleBounce simple = new SimpleBounce();
        simple.setBackground(Color.BLACK);
        frame.setContentPane(simple);
        simple.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        simple.setLocation(100, 100);
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
        {Thread.sleep(20);}
        catch(Exception e){}
        frame.revalidate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent evt){
        repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(dx == 0 || dx == WIDTH-100){
            movex = -movex;
        }
        if(dy == 0 || dy == HEIGHT-100){
            movey = -movey;
        }
        dx += movex;
        dy += movey;
        drawBall(g);
    }
}
