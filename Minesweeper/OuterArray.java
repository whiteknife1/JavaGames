//Outer Array for Minesweeper project
//Created by Harrison Greco and John Eberling

import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class OuterArray
{
   private Tile[][] outer;
   private int size;
   private int score=0;
   private InnerArray iArray;
   private boolean alive=true;
   private ArrayList<Coordinates> coords;
   private int set=0;
   public OuterArray(int nSize)
   {
      this.set=set;
      coords = new ArrayList<Coordinates>();
      iArray=new InnerArray(nSize);
      outer = new Tile[nSize][nSize];
      size = nSize;
      for(int row=0;row<outer.length;row++)
         for(int col=0;col<outer.length;col++)
            outer[row][col]=new Tile();
      this.getStart();
   }//end OuterArray
   
   public int getSize()
   {
      return size;}
  
   public int getSet()
   {
      return set;} 
        
   public void hitTile(int row, int col, int num)
   {
      if(num>9)//flags tile
      {
         if(outer[row][col].getNum()<0)
            outer[row][col].modNum(num);
         else if(outer[row][col].getNum()>9)
            outer[row][col].modNum(-1);
      }
   if(outer[row][col].getNum() == -1)
      {if(num==0)//changes tile
      {
         if(outer[row][col].getNum()==-1 && iArray.getCount(row,col)<9)//updates clicked unkown tile
         {
            outer[row][col].modNum(iArray.getCount(row,col));
         
            if(outer[row][col].getNum()==0)
            {
               coords.add(new Coordinates(row,col));
               while(coords.size()>0)
               { 
                  outer[coords.get(0).getX()][coords.get(0).getY()].modNum(iArray.getCount(coords.get(0).getX(),coords.get(0).getY()));
                  this.checkZeroes(coords.get(0).getX(),coords.get(0).getY());
                  coords.remove(0);
               }//end while
            }//end if
         }//end if
      }
      }
         boolean win = didYouWin();
         if(outer[row][col].getNum()==-1 && iArray.getCount(row,col)==9)
         {
            set=1;
         }//end else if
         else if(win==true)
         {
            set=2;
         }//end else if
   }//end hit tile
   
   public void checkZeroes(int row, int col)
   {
      if(col-1>=0)
      {                 
         if(row-1>=0)
         {             
            if(iArray.getCount(row-1,col-1)==0 && outer[row-1][col-1].getNum() == -1)
            {
               outer[row-1][col-1].modNum(iArray.getCount(row-1,col-1));
               coords.add(new Coordinates(row-1,col-1));
            }
            
            else if(iArray.getCount(row-1,col-1)<9 && outer[row-1][col-1].getNum()==-1)
            {
               outer[row-1][col-1].modNum(iArray.getCount(row-1,col-1));
            }//end else if
            
         }//end row-1
         
         if(iArray.getCount(row,col-1)==0 && outer[row][col-1].getNum() == -1)
         {
            outer[row][col-1].modNum(iArray.getCount(row,col-1));
            coords.add(new Coordinates(row,col-1));
         }//end row,col-1
         
         else if(iArray.getCount(row,col-1)<9 && outer[row][col-1].getNum()==-1)
         {
            outer[row][col-1].modNum(iArray.getCount(row,col-1));
         }//end else if
             
         if(row+1<iArray.getSize())
         {            
            if(iArray.getCount(row+1,col-1)==0 && outer[row+1][col-1].getNum() == -1)
            {
               outer[row+1][col-1].modNum(iArray.getCount(row+1,col-1));
               coords.add(new Coordinates(row+1,col-1));
            }
            
            else if(iArray.getCount(row+1,col-1)<9 && outer[row+1][col-1].getNum()==-1)
            {
               outer[row+1][col-1].modNum(iArray.getCount(row+1,col-1));
            }//end else if  
         }//end row+1
      }//end col-1
         
      if(col+1<iArray.getSize())
      {         
         if(row-1>=0)
         {
            if(iArray.getCount(row-1,col+1)==0 && outer[row-1][col+1].getNum() == -1)
            {
               outer[row-1][col+1].modNum(iArray.getCount(row-1,col+1));
               coords.add(new Coordinates(row-1,col+1));
            }
            
            else if(iArray.getCount(row-1,col+1)<9 && outer[row-1][col+1].getNum()==-1)
            {
               outer[row-1][col+1].modNum(iArray.getCount(row-1,col+1));
            }//end else if
         }//end row-1
         
         if(iArray.getCount(row,col+1)==0 && outer[row][col+1].getNum() == -1)
         {
            outer[row][col+1].modNum(iArray.getCount(row,col+1));
            coords.add(new Coordinates(row,col+1));
         }//end row 
         
         else if(iArray.getCount(row,col+1)<9 && outer[row][col+1].getNum()==-1)
         {
            outer[row][col+1].modNum(iArray.getCount(row,col+1));
         }//end else if
      
         
         if(row+1<iArray.getSize())
         {
            if(iArray.getCount(row+1,col+1)==0 && outer[row+1][col+1].getNum() == -1)
            {
               outer[row+1][col+1].modNum(iArray.getCount(row+1,col+1));
               coords.add(new Coordinates(row+1,col+1));
            }
            
            else if(iArray.getCount(row+1,col+1)<9 && outer[row+1][col+1].getNum()==-1)
            {
               outer[row+1][col+1].modNum(iArray.getCount(row+1,col+1));
            }//end else if
         }//end row+1
      }//end col+1
         
      if(row-1>=0)
      {
         if(iArray.getCount(row-1,col)==0 && outer[row-1][col].getNum() == -1)
         {
            outer[row-1][col].modNum(iArray.getCount(row-1,col));
            coords.add(new Coordinates(row-1,col));
         }
         else if(iArray.getCount(row-1,col)<9 && outer[row-1][col].getNum()==-1)
         {
            outer[row-1][col].modNum(iArray.getCount(row-1,col));
         }//end else if
      }//end row-1
      
      if(row+1<iArray.getSize())
      {
         if(iArray.getCount(row+1,col)==0 && outer[row+1][col].getNum() == -1)
         {
            outer[row+1][col].modNum(iArray.getCount(row+1,col));
            coords.add(new Coordinates(row+1,col));
         }
         
         else if(iArray.getCount(row+1,col)<9 && outer[row+1][col].getNum()==-1)
         {
            outer[row+1][col].modNum(iArray.getCount(row+1,col));
         }//end else if
      }//end row+1
   
   }//end checkZeros
   
   private int checkTile(int row, int col)
   {
      if(iArray.getCount(row, col)>8)
      {alive=false;
         return -1;}
      return iArray.getCount(row, col);
   }
   public boolean getRunning()
   {
      return alive;}
      
   public void drawArray(Graphics g, int width, int height)
   {
      int size=height/outer.length;
      int add=width/12;
      for(int row=0;row<outer.length;row++)
         for(int col=0;col<outer[0].length;col++)
         {outer[row][col].draw(g,(row*size)+add, col*size, size,iArray.getCount(row,col));}
   }
   
    public void getStart() //Finds a group of zeros and reveals them at the beginning of the game.
   {
      boolean cont = true;
      Random rand = new Random();
      while(cont)
      {
         int x = rand.nextInt(outer.length);
         int y = rand.nextInt(outer.length);
         if(iArray.getCount(x,y) == 0)
         {
            cont = false;
            this.hitTile(x,y,0);
         }//end if
      }//end while  
   }//end getStart
   
   public void showArray()
   {
      for(int row=0; row<iArray.getSize(); row++)
         for(int col=0; col<iArray.getSize(); col++)
         {
            outer[row][col].modNum(iArray.getCount(row,col));
         }//end for inner
   }//end showArray
   
   public boolean didYouWin()
   {
      for(int row=0; row<iArray.getSize(); row++)
         for(int col=0; col<iArray.getSize(); col++)
         {
            if(outer[row][col].getNum()!=10 && iArray.getCount(row,col)==9)
               return false;
            if(outer[row][col].getNum()==10 && iArray.getCount(row,col)!=9)
               return false;
         }//end for inner
      return true;
   }//end didYouWin
}//end OuterArray class