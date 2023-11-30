import java.awt.Color;

public class Gunpowder extends Particulate{

    public Gunpowder(int x, int y) {
        super(x, y);
        flammability = 50;
        c = new Color(122, 110, 104);
        rgb = c.getRGB();
        density = 2.5;
        baseColor = c;
    }
}