import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

/**
 * Created by John Eberling on 5/10/2017.
 */
public class Character {

    private static Key key = new Key();
    private BufferedImage person, left1, left2, left3, right1, right2, right3, up1, up2, up3, down1, down2,down3;
    private Image image = null;
    private int  y;
    private double x, cycle = 0;
    private boolean isVertical = false, down = false, left = false, gravity = false;
    private ChuckieBoard board = new ChuckieBoard();

    public Character(int x1, int y1) {
        x = x1;
        y = y1;
        try {
            person = ImageIO.read(new File("chuckieCharacter.png"));
        } catch (IOException e) {
        }
        down1 = person.getSubimage(42, 1, 47, 93);
        down2 = person.getSubimage(165, 1, 59, 93);
        down3 = person.getSubimage(291, 1, 49, 93);
        left1 = person.getSubimage(1, 96, 123, 47);
        left2 = person.getSubimage(131, 101, 120, 40);
        left3 = person.getSubimage(253, 99, 124, 42);
        right1 = person.getSubimage(1, 170, 123, 49);
        right2 = person.getSubimage(137, 178, 118, 39);
        right3 = person.getSubimage(264, 174, 115, 40);
        up1 = person.getSubimage(42, 226, 49, 95);//100
        up2 = person.getSubimage(171, 230, 48, 95);//96
        up3 = person.getSubimage(299, 231,  46, 95);//95
        image = right1;
    }

    public Image findImage(){
        if(gravity){
            y += 2;
        }
        Image tempImage = image;
        if(isVertical)
           cycle += 0.05;
        else{
            cycle += .1;
        }
        if(cycle > 2){
            cycle = 0;
        }
        if(key.getDown()){
            down = true;
            left = false;
            isVertical = true;
            tempImage = down1;
            y ++;
            switch((int) cycle){
                case 0: return down1;
                case 1: return down2;
                case 2: return down3;
            }
        }

        else if(key.getLeft()){
            down = false;
            left = true;
            tempImage = left1;
            isVertical = false;
            x -= 1.5;
            switch((int) cycle){
                case 0: return left1;
                case 1: return left2;
                case 2: return left3;
            }
        }

        else if(key.getRight()){
            down = false;
            left = false;
            tempImage = right1;
            isVertical = false;
            x += 1.5;
            switch((int) cycle){
                case 0: return right1;
                case 1: return right2;
                case 2: return right3;
            }
        }

        else if(key.getUp()){
            down = false;
            left = false;
            tempImage = up1;
            isVertical = true;
            y --;
            switch((int)cycle){
                case 0: return up1;
                case 1: return up2;
                case 2: return up3;
            }
        }

        return tempImage;
    }

    public Image getImage(){
        return image;
    }

    public int getX(){
        return (int) x;
    }

    public int getY(){
        return  y;
    }

    public String getDirection(){
        if(left) return "left";
        else if(down) return "down";
        else{
            return "";
        }
    }

    public boolean isVertical(){
        image = findImage();
        return isVertical;
    }

    public static Key getKey(){
        return key;
    }

    public void setGravity(boolean grav){
        gravity = grav;
    }
}
