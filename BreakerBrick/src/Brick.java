import java.awt.*;

/**
 * Created by John Eberling on 3/28/17.
 */
public class Brick {
    private int leftWall;
    private int rightWall;
    private int upperWall;
    private int lowerWall;
    private boolean speedCount = false;
    private boolean multi = false;
    private boolean soviet = false;

    public Brick(int left, int right, int up, int down){
        leftWall = left;
        rightWall = right;
        upperWall = up;
        lowerWall = down;
    }

    public void changeSpeed(){
        speedCount = !speedCount;
    }

    public boolean speed(){ return speedCount; }

    public void changeMulti(){
        multi = !multi;
    }

    public boolean multi(){ return multi; }

    public void changeSoviet(){ soviet = !soviet; }

    public boolean soviet(){ return soviet; }

    public int getLeftWall(){ return leftWall; }

    public int getRightWall(){
        return rightWall;
    }

    public int getUpperWall(){
        return upperWall;
    }

    public int getLowerWall(){
        return lowerWall;
    }
}
