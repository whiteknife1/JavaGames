//Gavin Williams

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

public class Player {
   private int posX;
   private int posY;
   private final int speedX;
   private final int speedY;
   private final int sizeX;
   private final int sizeY;
   private final int screenX;
   private final int screenY;
   private boolean alive;
   private BufferedImage image;
   private boolean imageTrue;
   static final double twoPI = 6.283185307179586D;
   static final double halfPI = 1.5707963267948966D;
   static final double quarterPI = 0.7853981633974483D;

   public Player(int newWidth, int newHeight) {
      this.screenX = newWidth;
      this.screenY = newHeight;
      this.posY = this.screenY;
      this.speedX = this.screenX / 230;
      this.speedY = this.screenY / 200;
      this.sizeX = this.screenX / 25;
      this.sizeY = this.screenY / 15;
      this.posX = this.screenX / 2 - this.sizeX / 2;
      this.imageTrue = true;
   
      try {
         this.image = ImageIO.read(this.getClass().getResource("/Images/Galaga_Fighter.png"));
      } 
      catch (Exception var4) {
         System.out.println(var4);
         this.imageTrue = false;
      }
   
      this.alive = true;
   }

   public void modPlayerPos(boolean left, boolean right, boolean up, boolean down, boolean running) {
      if(running && this.alive) {
         if(left) {
            if(this.posX - this.speedX > 0) {
               this.posX -= this.speedX;
            } 
            else {
               this.posX = 0;
            }
         }
      
         if(right) {
            if(this.posX + this.sizeX + this.speedX < this.screenX) {
               this.posX += this.speedX;
            } 
            else {
               this.posX = this.screenX - this.sizeX;
            }
         }
      
         if(up) {
            if(this.posY - this.sizeY - this.speedY > this.screenY / 2) {
               this.posY -= this.speedY;
            } 
            else {
               this.posY = this.sizeY + this.screenY / 2;
            }
         }
      
         if(down) {
            if(this.posY + this.speedY < this.screenY) {
               this.posY += this.speedY;
            } 
            else {
               this.posY = this.screenY;
            }
         }
      }
   
   }

   public void drawPlayer(Graphics g) {
      if(this.alive) {
         if(!this.imageTrue) {
            int pX = this.sizeX / 6;
            int pY = this.sizeY / 6;
            g.setColor(new Color(153, 153, 255));
            g.fillRect(this.posX, this.posY - pY * 4, pX, pY * 4);
            g.fillRect(this.posX + pX * 5, this.posY - pY * 4, pX, pY * 4);
            g.fillRect(this.posX + pX, this.posY - pY * 3, pX * 4, pY);
            g.fillRect(this.posX + pX * 2, this.posY - pY * 6, pX * 2, pY * 5);
            g.setColor(new Color(204, 0, 0));
            g.fillRect(this.posX + pX * 2 + pX / 4, this.posY - (pY * 6 - pX / 2), pX * 2 - pX / 2, pY / 2);
         } 
         else {
            g.drawImage(this.image, this.posX, this.posY - this.sizeY, this.sizeX, this.sizeY, (ImageObserver)null);
         }
      } 
      else {
         g.setColor(Color.red);
         drawStar(g, this.posX + this.sizeX / 2, this.posY - this.sizeY / 2, this.sizeY, 8);
         g.setColor(Color.orange);
         drawStar(g, this.posX + this.sizeX / 2, this.posY - this.sizeY / 2, this.sizeY / 2, 8);
      }
   
   }

   public void modAlive(boolean life) {
      this.alive = life;
   }

   public boolean getAlive() {
      return this.alive;
   }

   public int getPosX() {
      return this.posX;
   }

   public int getPosY() {
      return this.posY - this.sizeY;
   }

   public int getSizeX() {
      return this.sizeX;
   }

   public int getSizeY() {
      return this.sizeY;
   }

   public int getX() {
      return this.posX + this.sizeX / 12 * 6;
   }

   public int getY() {
      return this.posY - this.sizeY / 7 * 8;
   }

   public static void drawStar(Graphics g, int centerX, int centerY, int radius, int points) {
      int halfRadius = getHalfRadius(radius, points);
      int p = points;
      points *= 2;
      int[] xCoord = new int[points];
      int[] yCoord = new int[points];
   
      int x;
      for(x = 0; x < points; ++x) {
         int currentRadius;
         if(x % 2 == 0) {
            currentRadius = radius;
         } 
         else {
            currentRadius = halfRadius;
         }
      
         xCoord[x] = (int)Math.round(Math.cos(6.283185307179586D * (double)x / (double)points - 1.5707963267948966D) * (double)currentRadius) + centerX;
         yCoord[x] = (int)Math.round(Math.sin(6.283185307179586D * (double)x / (double)points - 1.5707963267948966D) * (double)currentRadius) + centerY;
      }
   
      x = (p - 5) / 2 + 1;
      if(p >= 5 && p <= 51) {
         switch(p % 4) {
            case 1:
               yCoord[x] = yCoord[x + 1] = yCoord[points - x - 1] = yCoord[points - x];
               break;
            case 2:
               yCoord[x] = yCoord[x + 1] = yCoord[points - x - 1] = yCoord[points - x];
               yCoord[x + 3] = yCoord[x + 4] = yCoord[points - x - 4] = yCoord[points - x - 3];
               break;
            case 3:
               yCoord[x + 2] = yCoord[x + 3] = yCoord[points - x - 3] = yCoord[points - x - 2];
         }
      }
   
      g.fillPolygon(xCoord, yCoord, points);
   }

   private static int getHalfRadius(int radius, int points) {
      int halfRadius;
      switch(points) {
         case 3:
            halfRadius = 140 * radius / 500;
            break;
         case 4:
            halfRadius = 170 * radius / 400;
            break;
         case 5:
            halfRadius = 192 * radius / 500;
            break;
         case 6:
            halfRadius = 233 * radius / 400;
            break;
         case 7:
            halfRadius = 179 * radius / 500;
            break;
         case 8:
            halfRadius = 215 * radius / 400;
            break;
         case 9:
            halfRadius = 173 * radius / 500;
            break;
         case 10:
            halfRadius = 212 * radius / 400;
            break;
         default:
            if(points < 52) {
               if(points % 2 == 1) {
                  halfRadius = (180 - points) * radius / 500;
               } 
               else {
                  halfRadius = (222 - points) * radius / 400;
               }
            } 
            else {
               halfRadius = radius / 2;
            }
      }
   
      return halfRadius;
   }
}
