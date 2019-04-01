//Harrison Greco and John Eberling
//updates graphics for minesweeper
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener
{
   private final Panel panel;
   private boolean isRunning;
   private final int width;
   private final int height;
   private MouseClass mouse;
   
   public Frame()
   {super("Practice");
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      width =(int) screenSize.getWidth();
      height =(int) screenSize.getHeight();
      System.out.println(width+" "+height);
      panel=new Panel(width, height);
      setSize(width, height);
      this.add(panel);
      mouse=new MouseClass();
      this.addMouseListener(mouse);
      setUndecorated(true);
      setExtendedState(Frame.MAXIMIZED_BOTH);
      this.addKeyListener((KeyListener) this);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      isRunning=true;
   }
   public void updatePanel()
   {
      try
      {Thread.sleep(15);}
      catch(Exception e)
      {System.out.println(e);}
      if(mouse.getClick())
      {
         panel.hitTile(mouse.getX(),mouse.getY(),mouse.getNum());
      }
      panel.revalidate();
      panel.repaint();
   }
   
   public boolean getRun()
   {
      if(!panel.getRunning())
      {
         setVisible(false);
         dispose();
         isRunning=false;
      }
      return isRunning;
   }
   
   @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
    public void keyPressed(KeyEvent e) 
   {
      if(e.getKeyCode() == KeyEvent.VK_ESCAPE)//ends frame
      {setVisible(false);
         dispose();
         isRunning=false;}
      if(e.getKeyCode() == KeyEvent.VK_SPACE)
      {
         panel.restart();
         
      }
   }//end keyPressed

   @Override
    public void keyReleased(KeyEvent e) 
   {
   }


}