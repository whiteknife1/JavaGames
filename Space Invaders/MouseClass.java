//Gavin Williams

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClass implements MouseListener {
   private boolean shooting = false;

   public MouseClass() {
   }

   public void mouseClicked(MouseEvent e) {
   }

   public void mouseEntered(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
   }

   public void mousePressed(MouseEvent e) {
      this.shooting = true;
   }

   public void mouseReleased(MouseEvent e) {
      this.shooting = false;
   }

   public boolean getShooting() {
      return this.shooting;
   }
}
