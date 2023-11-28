import java.awt.Color;
import java.util.*;

public class Particulate extends Particle{
    int maxFlow = 1;
    int nudgePercentage = 10;
    

    public Particulate(int x, int y){
        super(x, y);
        c = new Color(20, 184, 44);
        isEmpty = false;
        rgb = c.getRGB();
        density = 2.0;
        type = "Particulate";
    }

    @Override
    public void update(Grid g){
        tryGravity(g, maxFlow, nudgePercentage);
    }
}