import java.awt.Color;

public class Stone extends Solid {

    public Stone(int x, int y) {
        super(x, y);
        c = new Color(200, 200, 200);
        rgb = c.getRGB();
    }
}
