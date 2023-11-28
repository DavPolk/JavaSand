import java.awt.Color;

public class ParticulateSpout extends Solid{
    public ParticulateSpout(int x, int y){
        super(x, y);
        c = new Color(100, 100, 255);
        isEmpty = false;
        rgb = c.getRGB();
        density = 999;
        currentElement = Element.SAND;
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
