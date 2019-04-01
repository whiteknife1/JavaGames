//Gavin Williams

import java.awt.Color;
import java.awt.Graphics;

public class Laser {
   private final int posX;
   private int posY;
   private final int speed;
   private final int sizeX;
   private final int sizeY;
   private final int screenY;
   private final boolean playerShot;
   private boolean bounds;

   public Laser(int newHeight, int newPosX, int newPosY, boolean shot) {
      this.screenY = newHeight;
      this.posX = newPosX;
      this.posY = newPosY;
      if(shot) {
         this.speed = this.screenY / 85;
      } 
      else {
         this.speed = this.screenY / 150;
      }
   
      this.sizeX = this.screenY / 153;
      this.sizeY = this.screenY / 50;
      this.bounds = false;
      this.playerShot = shot;
   }

   public void increment() {
      if(!this.bounds) {
         if(this.playerShot) {
            this.posY -= this.speed;
         } 
         else if(!this.playerShot) {
            this.posY += this.speed;
         }
      }
   
      if(this.posY - this.sizeY < 0) {
         this.bounds = true;
      }
   
   }

   public boolean getBounds() {
      return this.bounds;
   }

   public int getX() {
      return this.posX;
   }

   public int getY() {
      return this.posY;
   }

   public boolean getWho() {
      return this.playerShot;
   }

   public int getSizeX() {
      return this.sizeX;
   }

   public int getSizeY() {
      return this.sizeY;
   }

   public boolean getOwner() {
      return this.playerShot;
   }

   public void drawLazer(Graphics g) {
      if(this.playerShot) {
         g.setColor(Color.green);
      } 
      else {
         g.setColor(Color.red);
      }
   
      g.fillOval(this.posX, this.posY - this.sizeY, this.sizeX, this.sizeY);
   }
}
