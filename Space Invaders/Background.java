//Gavin Williams
import java.awt.Color;
import java.awt.Graphics;

public class Background {
   private int posX;
   private int inPosY;
   private int posY;
   private final int sizeX;
   private final int sizeY;
   private int speed;
   private final int screenHeight;
   private final int screenWidth;
   private boolean bound;

   public Background(int screenX, int screenY) {
      this.screenHeight = screenY;
      this.screenWidth = screenX;
      this.sizeX = this.screenWidth / 341;
      this.sizeY = this.screenHeight / 153;
      this.posX = (int)(Math.random() * (double)this.screenWidth);
      this.posY = (int)(Math.random() * (double)this.screenHeight);
      this.speed = 1 + (int)(Math.random() * (double)(this.screenHeight / 51));
      this.bound = false;
   }

   public void increment() {
      if(this.posY < this.screenHeight + 2) {
         this.posY += this.speed;
      } 
      else {
         this.reposition();
      }
   
   }

   private void reposition() {
      this.posY = -this.sizeY;
      this.posX = (int)(Math.random() * (double)this.screenWidth);
      this.speed = 1 + (int)(Math.random() * (double)(this.screenHeight / 51));
   }

   public void drawBackground(Graphics g) {
      g.setColor(Color.white);
      g.fillOval(this.posX, this.posY, this.sizeX, this.sizeY);
   }
}
