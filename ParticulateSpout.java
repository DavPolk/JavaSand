import java.awt.Color;

public class ParticulateSpout extends Solid{
    public ParticulateSpout(int x, int y){
        super(x, y);
        c = new Color(100, 100, 255);
        isEmpty = false;
        rgb = c.getRGB();
        density = 999;
    }

    @Override
    public void update(Grid g){

        if(Math.random()*2 >= 1){
        if(g.particleGrid[getX()][getY()-1].isEmpty){
            g.particleGrid[getX()][getY()-1] = new Particulate(getX(), getY()-1);
        }
        }
    }
}
