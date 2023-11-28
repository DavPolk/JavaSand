import java.awt.Color;
import java.util.ArrayList;

public class Plant extends Solid {
    int spreadPercent = 15;

    public Plant(int x, int y) {
        super(x, y);
        c = new Color(10, 200, 10);
        rgb = c.getRGB();
    }

    @Override
    public void update(Grid g){
        
        if(Math.random() * 100 < spreadPercent){
            ArrayList<int[]> adjWaters = g.getNeighborsOfElement(getX(), getY(), Element.WATER);
            for(int i = 0; i < adjWaters.size(); i++){
                g.spawnElement(adjWaters.get(i)[0], adjWaters.get(i)[1], Element.PLANT);
            }
        }
    }
}
