//Gavin Williams

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Fleet {
   private final int screenX;
   private final int screenY;
   private final Random rand;
   private int round;
   private int incr;
   private boolean refresh;
   private boolean shift;
   private int dir;
   private ArrayList<Invader> invaders;
   private boolean dead;

   public Fleet(int screenHeight, int screenWidth) {
      this.screenX = screenWidth;
      this.screenY = screenHeight;
      this.rand = new Random();
      this.refresh = false;
      this.round =1;
      this.incr = 1;
      this.dir = 1;
      this.shift = false;
      this.dead = false;
      this.invaders = new ArrayList();
      this.fillFleet();
   }

   public int checkDeath(Laser laser, int score) {
      if(this.invaders.size() > 0) {
         for(int i = 0; i < this.invaders.size(); ++i) {
            if(laser.getX() > ((Invader)this.invaders.get(i)).getPosX() && laser.getX() + laser.getSizeX() < ((Invader)this.invaders.get(i)).getPosX() + ((Invader)this.invaders.get(i)).getSizeX() && laser.getY() > ((Invader)this.invaders.get(i)).getPosY() && laser.getY() < ((Invader)this.invaders.get(i)).getPosY() + ((Invader)this.invaders.get(i)).getSizeY()) {
               this.dead = true;
               ((Invader)this.invaders.get(i)).getHit();
               if(((Invader)this.invaders.get(i)).getHealth() <= 0) {
                  score += this.round;
                  ((Invader)this.invaders.get(i)).modDead();
               }
            }
         }
      }
   
      return score;
   }

   public boolean getShift(int change) {
      if(change == 1) {
         this.shift = false;
      }
   
      return this.shift;
   }

   public void fillFleet() {
      this.round += this.incr;
   
      for(int i = 0; i < 9; ++i) {
         this.invaders.add(new Invader(i * 20 + i * (this.screenX / 14), this.screenY / 10, this.screenX, this.screenY, this.round, this.rand.nextInt(800 / this.round + 30)));
      }
   
   }

   public void changeInc(int num) {
      this.incr = num;
   }

   public boolean getDead() {
      return this.dead;
   }

   public void modDead() {
      this.dead = false;
   }

   public int getRound() {
      return this.round;
   }

   public void drawInvaders(Graphics g, int imgSZ, ArrayList eLasers) {
      if(this.invaders.size() > 0) {
         if(((Invader)this.invaders.get(0)).getPosX() <= 0) {
            this.dir = 1;
         } 
         else if(((Invader)this.invaders.get(this.invaders.size() - 1)).getPosX() + ((Invader)this.invaders.get(this.invaders.size() - 1)).getSizeX() >= this.screenX) {
            this.dir = -1;
         }
      
         for(int i = 0; i < this.invaders.size(); ++i) {
            if(!((Invader)this.invaders.get(i)).getDead()) {
               if(((Invader)this.invaders.get(i)).getCooldown() > 0) {
                  ((Invader)this.invaders.get(i)).updateCooldown();
               } 
               else {
                  ((Invader)this.invaders.get(i)).modCooldown(this.rand.nextInt(800 / this.round + 30));
                  eLasers.add(new Laser(this.screenY, ((Invader)this.invaders.get(i)).getX(), ((Invader)this.invaders.get(i)).getY(), false));
               }
            
               ((Invader)this.invaders.get(i)).moveInvader(this.dir);
            }
         
            ((Invader)this.invaders.get(i)).drawInvader(g);
            if(((Invader)this.invaders.get(i)).getTimer() <= 0) {
               this.invaders.remove(i);
            }
         }
      } 
      else if(this.invaders.size() <= 0 && imgSZ <= 0) {
         this.fillFleet();
      } 
      else {
         this.shift = true;
      }
   
   }
}