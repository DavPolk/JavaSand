import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Fire extends Solid{

    public int douseChance = 10; //% chance to be deleted per adjacent water

    public Fire(int x, int y) {
        super(x, y);
        c = new Color(250, 140, 41);
        rgb = c.getRGB();
        health = 10;
        density = 0.4;
        element = Element.FIRE;
        baseColor = c;
    }

    @Override
    public void updateColor(){
        c = new Color(150+10*health, 20+10*health, 40+health);
        rgb = c.getRGB();
    }


    @Override
    public void update(Grid g){

        if(Math.random()*10 < 4){
            health-= 1;
        }

        if(Math.random()*20 < 1){
            if(getY() < g.gridSizeY-1){
                if (g.particleGrid[getX()][getY()+1].density <= 0){
                    g.swap(getX(), getY(), getX(), getY()+1);
                }
            }
        }

        fluidJiggle(g, 5);
    

    ArrayList<int[]> neighbors = g.getNeighbors(getX(), getY());
    Collections.shuffle(neighbors);

        for(int i = 0; i < neighbors.size(); i++){
            if(Math.random()*100 < g.particleGrid[neighbors.get(i)[0]][neighbors.get(i)[1]].flammability){
                g.spawnElement(neighbors.get(i)[0], neighbors.get(i)[1], Element.FIRE);
            }
            if(Math.random()*100 < douseChance){
                if(g.particleGrid[neighbors.get(i)[0]][neighbors.get(i)[1]].element == Element.WATER){
                    this.replaceWithEmpty(g);
                }
            }
        }
    }
}
