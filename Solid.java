import java.awt.Color;

public class Solid extends Particle{
    
    public Solid(int x, int y){
        super(x, y);
        c = new Color(100, 100, 100);
        isEmpty = false;
        rgb = c.getRGB();
        density = 999;
    }
}
