import java.awt.*;

/**
 * Created by john_eberling on 3/28/17.
 */
public class Ball {
    private int dx;
    private int dy;
    private int velX ;
    private int velY;
    private int mass;
    private int radius;

    public Ball(int vx, int vy, int x, int y, int rad){
        velX = vx;
        velY =vy;
        mass = 10;
        dx = x;
        dy = y;
        radius = rad;
    }

    public void setVelX(int x){
        velX = x;
    }

    public void setVelY(int y){
        velY = y;
    }

    public void setDx(int x) {dx = x; }

    public void setDy(int y) {dy = y; }

    public int getVelX(){
        return velX;
    }

    public int getVelY(){
        return velY;
    }

    public int getMass(){
        return mass;
    }

    public int getDx(){
        return dx;
    }

    public int getDy(){
        return dy;
    }

    public int getRadius() { return radius; }

}
