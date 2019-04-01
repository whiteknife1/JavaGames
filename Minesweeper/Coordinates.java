//John Eberling and Harrison Grecco
//class of one by one coordinates for uncovering zeroes
public class Coordinates
   {
   private int[] coords;
   
   public Coordinates(int x, int y)
   {
   coords = new int[2];
   coords[0] = x;
   coords[1] = y;
   }//end constructor
   
   public int getX()
   {return coords[0];}
   
   public int getY()
   {return coords[1];}
   }//end Coordinates