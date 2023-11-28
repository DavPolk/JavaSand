import java.awt.Color;

public class Solid extends Particle{
    
    public Solid(int x, int y){
        super(x, y);
        c = new Color(10, 200, 10);
        isEmpty = false;
        rgb = c.getRGB();
        density = 999;
    }

    @Override
    public void update(Grid g){
    }
}
