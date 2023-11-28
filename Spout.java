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

    public Spout(int x, int y, Element e){
        super(x, y);
        c = new Color(100, 100, 255);
        isEmpty = false;
        rgb = c.getRGB();
        density = 999;
        currentElement = e;
    }

    public int spawnPercent = 20;
    public Element currentElement;
    @Override
    public void update(Grid g){

        if(currentElement == Element.FIRE){
            g.spawnElement(getX(), getY()+1, currentElement);
        }
        else if(Math.random()*100 <= spawnPercent){
            g.spawnElement(getX(), getY()-1, currentElement);
        }
    }
}
