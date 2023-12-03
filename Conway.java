import java.awt.Color;
import java.util.ArrayList;

public class Conway extends Solid{

    public Conway(int x, int y) {
        super(x, y);
        c = new Color(200, 200, 200);
        rgb = c.getRGB();
        density = 0.8;
        element = Element.CONWAY;
        baseColor = c;
        invincible = false;
    }

    @Override
    public void update(Grid g){
        ArrayList <int[]> neighboringConways = g.getNeighborsOfElement(getX(), getY(), Element.CONWAY);
        int[] coords = {getX(), getY()};

        if(neighboringConways.size() <= 1){
            g.conwaysToRemove.add(coords);
        }
        else if(neighboringConways.size() >= 4){
            g.conwaysToRemove.add(coords);
        }
    }
}