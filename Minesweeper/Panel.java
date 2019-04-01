//Harrison Greco and John Eberling
//graphics for minesweeper
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Font;

public class Panel extends JPanel
{
   private int screenX;
   private int screenY;
   
   private OuterArray oArray;
   
   private boolean restart=false;
   
   public Panel(int sizeX, int sizeY)
   {
      super();
      screenX=sizeX;
      screenY=sizeY;
      
      oArray=new OuterArray(13);
   }

   public void hitTile(int x, int y, int num)
   {
      int add=screenX/12;
      int xSize=screenY/oArray.getSize();
      if(oArray.getSet() == 0)
         for(int row=0;row<oArray.getSize();row++)
            for(int col=0;col<oArray.getSize();col++)
            {
               if((x-add>row*xSize && x-add<(row+1)*xSize) && (y>col*xSize && y<(col+1)*xSize))
               {oArray.hitTile(row,col,num);}
            }
   }
   public boolean getRunning()
   {
      return oArray.getRunning();
   }
   
   @Override
   public void paintComponent(Graphics g)
   {
      g.setColor(Color.black);
      g.fillRect(-1,-1,screenX+1, screenY+1);
      
      oArray.drawArray(g,screenX, screenY);
      
      if(oArray.getSet()==1)
      {
         restart = true;
         g.setColor(Color.magenta);
         g.setFont(new Font("Serif",Font.BOLD,100));
         g.drawString("YOU LOSE!",3*screenX/11,screenY/4);
         g.setFont(new Font("Serif",Font.BOLD,75));
         g.drawString("Press Esc to Exit",7*screenX/25,screenY/3);
         g.drawString("Press Space to Play Again",screenX/6,2*screenY/5);
         oArray.showArray();
      } 
      else if(oArray.getSet() ==2)  
      {
         restart = true;
         g.setColor(Color.green);
         g.setFont(new Font("Serif",Font.BOLD,100));
         g.drawString(" YOU WIN!",3*screenX/11,screenY/4);
         g.setFont(new Font("Serif",Font.ITALIC,75));
         g.drawString("Press Esc to Exit",7*screenX/25,screenY/3);
         g.drawString("Press Space to Play Again",screenX/6,2*screenY/5);
      }//end else if     
   }
   
   public  void restart()
   {
      if(restart)
         {oArray = new OuterArray(13);}  
   }//end restart
}