//Harrison Greco and John Eberling
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.*;
import java.io.* ;
import javax.imageio.*;

public class Tile
{
   private int num;
   
   public Tile ()
   {
      num=-1;
   }//end Tile
   public void modNum(int change)
   {num=change;}
   public int getNum()
   {
      return num;}
   public void draw(Graphics g, int x, int y, int size,int n)
   {
      BufferedImage img = null;
      switch(num)
      {
         case -1: g.setColor(Color.gray);
            g.fill3DRect(x,y,size,size,true);
            g.setColor(Color.black);
            g.drawRect(x,y,size,size);
            break;
         case 9: 
            try{
               img = ImageIO.read(new File("minesweeperBomb.png"));
            }
            catch(IOException e){
            }
            g.drawImage(img,x,y,size,size,null);
            break;
         case 10:
            try{
               img = ImageIO.read(new File("minesweeperFlag.png"));
            }
            catch(IOException e){
            }
            g.drawImage(img,x,y,size,size,null);
            break;
         default: g.setColor(Color.white);
            g.fill3DRect(x,y,size,size,true);
            g.setColor(Color.black);
            g.drawRect(x,y,size,size);
            break;
      }
      
      if(num>-1 && num<9)
      {
      g.setFont(new Font("Monospaced",Font.BOLD,size/2));
      g.drawString(n+"",x+size/3,y+size/2);
      }//end if
   }
}//end Tile class