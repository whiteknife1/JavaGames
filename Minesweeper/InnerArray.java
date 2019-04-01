//Harrison Greco and John Eberling
//Inner array for minesweeper
import java.util.Random;

public class InnerArray
{
   private int[][] inner;
   private int size;
   
   public InnerArray(int nSize)
   {
      inner = new int[nSize][nSize];
      size=nSize;
      fillArray();
      //printTiles();
   }//end inner Array
   public int getSize()
   {return size;}
      
   public void fillArray()
   {
      int count = (size*size)/6;
      Random rand = new Random();
      while(count>0)
      {
         int x = rand.nextInt(size);
         int y = rand.nextInt(size); 
         if(inner[x][y] != 9)
         {
            count--;
            inner[x][y] = 9;
         }//end if
      }
      int bombCount=0;
      for(int row=0; row<inner.length; row++)
         for(int col=0; col<inner[0].length; col++)
         {
            bombCount=0;
         if(inner[row][col]<9)   
            {if(col-1>=0)
            {
               if(row-1>=0)
               {
                  if(inner[row-1][col-1]>8)
                  {bombCount++;}
               }
               if(inner[row][col-1]>8)
               {bombCount++;}
               if(row+1<inner.length)
               {
                  if(inner[row+1][col-1]>8)
                  {bombCount++;}
               }
            }
         
            if(col+1<inner.length)
            {
               if(row-1>=0)
               {
                  if(inner[row-1][col+1]>8)
                  {bombCount++;}
               }
               if(inner[row][col+1]>8)
               {bombCount++;}
               if(row+1<inner.length)
               {
                  if(inner[row+1][col+1]>8)
                  {bombCount++;}
               }
            }
         
            if(row-1>=0)
            {
               if(inner[row-1][col]>8)
               {bombCount++;}
            }
            if(row+1<inner.length)
            {
               if(inner[row+1][col]>8)
               {bombCount++;}
            }
         
            inner[row][col]=bombCount;
         }
         }//end for inner      
   }//end fillArray
   
   public void printTiles()
      {
      for(int row=0;row<inner.length;row++)
         {
         for(int col=0;col<inner[0].length;col++)
            System.out.print(inner[row][col]+" ");
         System.out.println();
         }
      }
   
   public int getCount(int r, int c){
      return inner[r][c];
   }//end getCount
   
}//end inner