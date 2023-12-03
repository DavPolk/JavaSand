import java.awt.Color;

public class Smoke extends Gas {

    public Smoke(int x, int y) {
        super(x, y);
        int colorMod = (int) (Math.random()*20);
        c = new Color(10+colorMod, 10+colorMod, 10+colorMod);
        isEmpty = false;
        rgb = c.getRGB();
        density = 0.2;
        element = Element.SMOKE;
        baseColor = c;
        invincible = false;
    }
    
}
