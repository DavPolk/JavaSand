import java.awt.Color;

public class Water extends Fluid{

    public Water(int x, int y) {
        super(x, y);
        c = new Color(20, 10, 230);
        isEmpty = false;
        rgb = c.getRGB();
        density = 1.5;
        element = Element.WATER;
    }
}
