//Harrison Greco and John Eberling

import java.awt.event.MouseListener;
    import java.awt.event.MouseEvent;
public class MouseClass implements MouseListener{
   
   private boolean clicked=false;
   private int x=0;
   private int y=0;
   private int num=-1;
   
   public MouseClass()
      {
      super();
      }
   
    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    //System.out.println("Down!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    //System.out.println("Up!");
    if(e.getButton() == MouseEvent.BUTTON1)
      {clicked=true;
      x=e.getX();
      y=e.getY();
      num=0;}
    if(e.getButton() == MouseEvent.BUTTON3)
      {clicked=true;
      x=e.getX();
      y=e.getY();
      num=10;}
    }
    
    public int getX()
      {return x;}
   public int getY()
      {return y;}
   public boolean getClick()
      {boolean click=clicked;
      clicked=false;
      return click;}
   public int getNum()
      {int r=num;
      num=-1;
      return r;}

}