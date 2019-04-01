/**
 * Created by john_eberling on 5/1/17.
 */
public class Hen{
    private int x;
    private int y;

    public Hen(int x1, int y1){
        x = x1;
        y = y1;
    }

    public String getHen(String direction, int hen){
        if(direction.equals("up")){
            y--;
            switch(hen) {
                case 0:
                    return "backHen1.png";
                case 1:
                    return "backHen2.png";
                case 2:
                    return "backHen3.png";
            }
        }

        else if(direction.equals("down")){
            y++;
            switch(hen) {
                case 0:
                    return "frontHen1.png";
                case 1:
                    return "frontHen2.png";
                case 2:
                    return "frontHen3.png";
            }
        }
        else if(direction.equals("left")){
            x--;
            switch(hen) {
                case 0:
                    return "leftHen1.png";
                case 1:
                    return "leftHen2.png";
                case 2:
                    return "leftHen3.png";
            }
        }
        else if(direction.equals("right")){
            x++;
            switch(hen) {
                case 0:
                    return "rightHen1.png";
                case 1:
                    return "rightHen2.png";
                case 2:
                    return "rightHen3.png";
            }
        }
        return "";
    }
    

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
