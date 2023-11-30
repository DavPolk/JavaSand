import java.awt.Color;

public class Stone extends Solid {

    public Stone(int x, int y) {
        super(x, y);
        c = new Color(150, 150, 150);
        rgb = c.getRGB();
        element = Element.STONE;
        density = 999;
        baseColor = c;
        maxHealth = 100;
        health = maxHealth;
    }
}
