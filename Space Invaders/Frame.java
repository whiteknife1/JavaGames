//Gavin Williams

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Frame extends JFrame implements KeyListener {
   private final int width;
   private final int height;
   private final Panel panel;
   private boolean isRunning;
   private boolean left;
   private boolean right;
   private boolean up;
   private boolean down;
   private boolean shooting;
   private boolean spaceShooting;
   private final MouseClass mouse;

   public Frame() {
      super("Space Invaders");
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      this.width = (int)screenSize.getWidth();
      this.height = (int)screenSize.getHeight();
      System.out.println(this.width + " " + this.height);
      this.panel = new Panel(this.width, this.height);
      this.setSize(this.width, this.height);
      this.add(this.panel);
      this.addKeyListener(this);
      this.mouse = new MouseClass();
      this.addMouseListener(this.mouse);
      this.setResizable(false);
      this.setUndecorated(true);
      this.setDefaultCloseOperation(3);
      this.setBackground(Color.BLACK);
      this.setVisible(true);
      this.isRunning = true;
      this.left = false;
      this.right = false;
      this.up = false;
      this.down = false;
      this.shooting = false;
   }

   public void updatePanel() {
      this.shooting = this.mouse.getShooting();
      if(this.shooting || this.spaceShooting) {
         this.panel.addLaser(true);
      }
   
      this.panel.modPlayerPos(this.left, this.right, this.up, this.down, this.isRunning);
      this.panel.revalidate();
      this.panel.repaint();
   }

   public boolean getIsRunning() {
      return this.isRunning;
   }

   public void keyTyped(KeyEvent e) {
   }

   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == 65) {
         this.left = true;
      }
   
      if(e.getKeyCode() == 68) {
         this.right = true;
      }
   
      if(e.getKeyCode() == 83) {
         this.down = true;
      }
   
      if(e.getKeyCode() == 87) {
         this.up = true;
      }
   
      if(e.getKeyCode() == 27) {
         this.setVisible(false);
         this.dispose();
         this.isRunning = false;
      }
   
      if(e.getKeyCode() == 32) {
         this.spaceShooting = true;
         if(!this.panel.getAlive()) {
            this.panel.reset();
         }
      }
   
      if(e.getKeyCode() == 48) {
         this.panel.changeInc(0);
      } 
      else if(e.getKeyCode() == 49) {
         this.panel.changeInc(1);
      } 
      else if(e.getKeyCode() == 50) {
         this.panel.changeInc(2);
      } 
      else if(e.getKeyCode() == 51) {
         this.panel.changeInc(3);
      } 
      else if(e.getKeyCode() == 52) {
         this.panel.changeInc(4);
      } 
      else if(e.getKeyCode() == 53) {
         this.panel.changeInc(5);
      } 
      else if(e.getKeyCode() == 54) {
         this.panel.changeInc(6);
      } 
      else if(e.getKeyCode() == 55) {
         this.panel.changeInc(7);
      } 
      else if(e.getKeyCode() == 56) {
         this.panel.changeInc(8);
      } 
      else if(e.getKeyCode() == 57) {
         this.panel.changeInc(99);
      }
   
   }

   public void keyReleased(KeyEvent e) {
      if(e.getKeyCode() == 65) {
         this.left = false;
      }
   
      if(e.getKeyCode() == 68) {
         this.right = false;
      }
   
      if(e.getKeyCode() == 83) {
         this.down = false;
      }
   
      if(e.getKeyCode() == 87) {
         this.up = false;
      }
   
      if(e.getKeyCode() == 32) {
         this.spaceShooting = false;
      }
   
   }
}