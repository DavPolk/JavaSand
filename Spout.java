import java.awt.Color;

public class Spout extends Solid{
    public Spout(int x, int y){
        super(x, y);
        c = new Color(100, 100, 255);
        isEmpty = false;
        rgb = c.getRGB();
        density = 999;
    }

    @Override
    public void update(Grid g){

        if(g.particleGrid[getX()][getY()-1].isEmpty){
            g.particleGrid[getX()][getY()-1] = new Fluid(getX(), getY()-1);
        }
    }
}
