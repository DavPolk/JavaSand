import java.awt.Color;

public class Spout extends Solid{
    public Spout(int x, int y){
        super(x, y);
        c = new Color(100, 100, 255);
        isEmpty = false;
        rgb = c.getRGB();
        density = 999;
        currentElement = Element.WATER;
    }

    public int spawnPercent = 50;
    public Element currentElement;
    @Override
    public void update(Grid g){
        if(Math.random()*100 <= spawnPercent){
            g.spawnElement(getX(), getY()-1, currentElement);
        }
    }
}
