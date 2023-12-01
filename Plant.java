import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Plant extends Solid {
    int thirstiness = 4; // number of water particles needed to spawn a new plant
    int saturation = 0;

    public Plant(int x, int y) {
        super(x, y);
        c = new Color(10, 140, 10);
        rgb = c.getRGB();
        flammability = 10;
        baseColor = c;
    }

    @Override
    public void updateColor(){
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        if (green > 80){
            green = green-5;
            blue = blue + 5;
        }

        c = new Color(red, green, blue);
        rgb = c.getRGB();
    }

    @Override
    public void update(Grid g){


        if (saturation < thirstiness){
            ArrayList<int[]> adjWaters = g.getNeighborsOfElement(getX(), getY(), Element.WATER);
            for(int i = 0; i < adjWaters.size(); i++){
                g.spawnElement(adjWaters.get(i)[0], adjWaters.get(i)[1], Element.EMPTY);
                saturation++;
                updateColor();
            }
        }
        else{
            ArrayList<int[]> adjEmpties = g.getNeighborsOfElement(getX(), getY(), Element.EMPTY);
            Collections.shuffle(adjEmpties);
            g.spawnElement(adjEmpties.get(0)[0], adjEmpties.get(0)[1], Element.PLANT);
            saturation -= thirstiness;
        }
        
    }
}
