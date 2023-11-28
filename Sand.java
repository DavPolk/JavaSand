import java.awt.Color;

public class Sand extends Particulate{

    public Sand(int x, int y) {
        super(x, y);
        c = new Color(217, 184, 44);
        isEmpty = false;
        rgb = c.getRGB();
        density = 2.0;
        type = "Particulate";
    }
    
}
