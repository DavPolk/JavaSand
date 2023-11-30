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
        baseColor = c;
        invincible = true;
    }

    public int spawnPercent = 20;
    public Element currentElement;
    @Override
    public void update(Grid g){

        if(currentElement == Element.FIRE){
            g.spawnElement(getX(), getY()+1, currentElement);
            g.spawnElement(getX(), getY()-1, currentElement);
        }
        else if(currentElement == Element.CONWAY){
            if(getX() != 0 && getX() < g.gridSizeX && getY() != 0 && getY() < g.gridSizeY){
                if(Math.random() * 3 >= 1){
                    g.spawnElement(getX(), getY()+1, currentElement);
                }
                if(Math.random() * 3 >= 1){
                    g.spawnElement(getX(), getY()-1, currentElement);
                }
                if(Math.random() * 3 >= 1){
                    g.spawnElement(getX()-1, getY(), currentElement);
                }
                if(Math.random() * 3 >= 1){
                    g.spawnElement(getX()+1, getY(), currentElement);
                }
            }
        }
        else if(Math.random()*100 <= spawnPercent){
            g.spawnElement(getX(), getY()-1, currentElement);
        }
    }
}
