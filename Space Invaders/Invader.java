//Gavin Williams

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

public class Invader {
   private int posX;
   private int posY;
   private boolean dead = false;
   private int deathCount = 15;
   private final int sizeX;
   private final int sizeY;
   private final int screenX;
   private final int screenY;
   private final int speed;
   private int round;
   private int cooldown;
   private BufferedImage image;
   private boolean imageTrue;
   static final double twoPI = 6.283185307179586D;
   static final double halfPI = 1.5707963267948966D;
   static final double quarterPI = 0.7853981633974483D;

   public Invader(int newPosX, int newPosY, int screenWidth, int screenHeight, int rnd, int cd) {
      this.posX = newPosX;
      this.posY = newPosY;
      this.round = rnd;
      this.cooldown = cd;
      this.screenX = screenWidth;
      this.screenY = screenHeight;
      this.speed = this.screenX / 220;
      this.sizeX = this.screenX / 14;
      this.sizeY = this.screenY / 10;
      this.imageTrue = true;
   
      try {
         this.image = ImageIO.read(this.getClass().getResource("/Images/invader.png"));
      } 
      catch (Exception var8) {
         System.out.println(var8);
         this.imageTrue = false;
      }
   
   }

   public int getX() {
      return this.posX + this.sizeX / 2;
   }

   public int getY() {
      return this.posY + this.sizeY / 2;
   }

   public int getPosX() {
      return this.posX;
   }

   public int getPosY() {
      return this.posY;
   }

   public int getSizeX() {
      return this.sizeX;
   }

   public int getSizeY() {
      return this.sizeY;
   }

   public int getHealth() {
      return this.round;
   }

   public void getHit() {
      --this.round;
   }

   public void modDead() {
      this.dead = true;
   }

   public boolean getDead() {
      return this.dead;
   }

   public int getTimer() {
      return this.deathCount;
   }

   public void drawInvader(Graphics g) {
      if(!this.dead) {
         if(this.imageTrue) {
            g.drawImage(this.image, this.posX, this.posY, this.sizeX, this.sizeY, (ImageObserver)null);
         } 
         else {
            g.fillRect(this.posX, this.posY, this.sizeX, this.sizeY);
         }
      } 
      else {
         --this.deathCount;
         g.setColor(Color.red);
         drawStar(g, this.posX + this.sizeX / 2, this.posY + this.sizeY / 2, this.sizeY / 2, 8);
         g.setColor(Color.orange);
         drawStar(g, this.posX + this.sizeX / 2, this.posY + this.sizeY / 2, this.sizeY / 4, 8);
      }
   
   }

   public void moveInvader(int dir) {
      this.posX += dir * this.speed;
   }

   public void modCooldown(int cd) {
      this.cooldown = cd;
   }

   public int getCooldown() {
      return this.cooldown;
   }

   public void updateCooldown() {
      --this.cooldown;
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
