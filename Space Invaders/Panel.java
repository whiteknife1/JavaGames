//Gavin Williams

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {
   private Player player;
   private final int screenX;
   private final int screenY;
   private int frameCount = 0;
   private int time;
   private BufferedImage image;
   private boolean imageTrue;
   private BufferedImage image2;
   private boolean imageTrue2;
   private int count;
   private ArrayList<Laser> lasers;
   private Background[] backgrounds;
   private Fleet fleet;
   private int score;
   private boolean playerAlive;
   private int imgSZ;
   private int img;
   private boolean shift;
   private Font name;
   private Font scr;
   private int coolDown;

   public Panel(int width, int height) {
      this.setDoubleBuffered(true);
      this.setLayout((LayoutManager)null);
      this.screenX = width;
      this.screenY = height;
      this.player = new Player(this.screenX, this.screenY);
      this.lasers = new ArrayList();
      this.backgrounds = new Background[200];
      this.score = 0;
      this.imgSZ = 0;
      this.img = 0;
      this.count = 0;
      this.shift = false;
      this.name = new Font("MONOSPACED", 1, 30);
      this.scr = new Font("SANS_SERIF", 0, 28);
      this.playerAlive = this.player.getAlive();
      this.fleet = new Fleet(this.screenY, this.screenX);
      this.coolDown = 0;
   
      for(int ex = 0; ex < this.backgrounds.length; ++ex) {
         this.backgrounds[ex] = new Background(this.screenX, this.screenY);
      }
   
      this.imageTrue = true;
   
      try {
         this.image = ImageIO.read(this.getClass().getResource("nagrand.jpg"));
      } 
      catch (Exception var5) {
         System.out.println(var5);
         this.imageTrue = false;
         this.image = null;
      }
   
      this.imageTrue2 = true;
   
      try {
         this.image2 = ImageIO.read(this.getClass().getResource("Aurelion.jpg"));
      } 
      catch (Exception var4) {
         System.out.println(var4);
         this.imageTrue2 = false;
         this.image2 = null;
      }
   
   }

   public void modPlayerPos(boolean left, boolean right, boolean up, boolean down, boolean running) {
      this.player.modPlayerPos(left, right, up, down, running);
   }

   public void paintComponent(Graphics g) {
      if(this.playerAlive) {
         ++this.frameCount;
         g.setColor(Color.BLACK);
         g.fillRect(-3, -3, this.screenX + 8, this.screenY + 8);
         this.shift = this.fleet.getShift(0);
         this.editBackgrounds(g);
      
         int i;
         for(i = 0; i < this.backgrounds.length; ++i) {
            this.backgrounds[i].increment();
            this.backgrounds[i].drawBackground(g);
         }
      
         if(this.lasers.size() > 0) {
            for(i = 0; i < this.lasers.size(); ++i) {
               ((Laser)this.lasers.get(i)).increment();
               ((Laser)this.lasers.get(i)).drawLazer(g);
               if(((Laser)this.lasers.get(i)).getWho()) {
                  this.score = this.fleet.checkDeath((Laser)this.lasers.get(i), this.score);
               } 
               else if(((Laser)this.lasers.get(i)).getX() > this.player.getPosX() && ((Laser)this.lasers.get(i)).getX() + ((Laser)this.lasers.get(i)).getSizeX() < this.player.getPosX() + this.player.getSizeX() && ((Laser)this.lasers.get(i)).getY() > this.player.getPosY() && ((Laser)this.lasers.get(i)).getY() < this.player.getPosY() + this.player.getSizeY()) {
                  this.playerAlive = false;
                  this.player.modAlive(this.playerAlive);
               }
            
               if(((Laser)this.lasers.get(i)).getBounds() || this.fleet.getDead()) {
                  this.lasers.remove(i);
                  this.fleet.modDead();
               }
            }
         
            this.lasers.trimToSize();
         }
      
         this.player.drawPlayer(g);
         this.coolDown();
         this.fleet.drawInvaders(g, this.imgSZ, this.lasers);
         g.setColor(Color.BLACK);
         g.fillRect(-1, -1, this.screenX + 5, this.screenY / 10);
         g.setColor(Color.red);
         g.setFont(this.name);
         g.drawString("Gavin Williams", this.screenX / 69, this.screenY / 35);
         g.setFont(this.scr);
         g.drawString("Score: " + this.score, this.screenX * 55 / 69, this.screenY / 35);
         g.drawString("Round: " + this.fleet.getRound(), this.screenX * 55 / 69, this.screenY / 20);
         g.drawString("Shot Count: " + this.count, this.screenX * 55 / 69, this.screenY / 13);
         if(this.frameCount / 15 % 60 > 30 && this.frameCount / 15 % 60 <= 0) {
            ++this.time;
         }
      
         g.drawString(this.time + ":" + this.frameCount / 15 % 60, this.screenX / 2, this.screenY / 35);
      } 
      else {
         g.setColor(Color.white);
         g.setFont(new Font("MONOSPACED", 1, 75));
         g.drawString("Press Space to Start", this.screenX / 6, this.screenY / 2);
      }
   
   }

   public void addLaser(boolean shot) {
      if(this.playerAlive && this.coolDown <= 0 && !this.shift) {
         this.lasers.add(new Laser(this.screenY, this.player.getX(), this.player.getY(), shot));
         this.coolDown = 20;
         ++this.count;
      }
   
   }

   public void coolDown() {
      if(this.coolDown > 0) {
         --this.coolDown;
      }
   
   }

   public void editBackgrounds(Graphics g) {
      if(this.shift) {
         this.imgSZ += 5;
         if(this.img == 0) {
            g.drawImage(this.image2, 0, 0, this.screenX, this.imgSZ, (ImageObserver)null);
            g.drawImage(this.image, 0, this.imgSZ, this.screenX, this.screenY - this.imgSZ, (ImageObserver)null);
         } 
         else if(this.img == 1) {
            g.drawImage(this.image, 0, 0, this.screenX, this.imgSZ, (ImageObserver)null);
            g.drawImage(this.image2, 0, this.imgSZ, this.screenX, this.screenY - this.imgSZ, (ImageObserver)null);
         }
      
         if(this.imgSZ >= this.screenY) {
            this.imgSZ = 0;
            if(this.img == 0) {
               this.img = 1;
            } 
            else if(this.img == 1) {
               this.img = 0;
            }
         
            this.shift = false;
            this.fleet.getShift(1);
         }
      } 
      else {
         if(this.img == 0) {
            g.drawImage(this.image, 0, 0, this.screenX, this.screenY, (ImageObserver)null);
         } 
         else if(this.img == 1) {
            g.drawImage(this.image2, 0, 0, this.screenX, this.screenY, (ImageObserver)null);
         }
      
         this.imgSZ = 1;
      }
   
   }

   public void modShift(boolean mod) {
      this.shift = mod;
   }

   public boolean getAlive() {
      return this.playerAlive;
   }

   public void reset() {
      this.player = new Player(this.screenX, this.screenY);
      this.playerAlive = this.player.getAlive();
      this.fleet = new Fleet(this.screenY, this.screenX);
      this.lasers = new ArrayList();
      this.score = 0;
      this.imgSZ = 0;
      this.img = 0;
      this.count = 0;
      this.shift = false;
   }

   public void changeInc(int num) {
      this.fleet.changeInc(num);
   }
}
