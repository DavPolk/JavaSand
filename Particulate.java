import java.awt.Color;
import java.util.*;

public class Particulate extends Particle{
    int maxFlow = 1;
    int nudgePercentage = 10;
    

    public Particulate(int x, int y){
        super(x, y);
        c = new Color(217, 184, 44);
        isEmpty = false;
        rgb = c.getRGB();
        density = 2.0;
        type = "particulate";
    }

    @Override
    public void update(Grid g){

        
        if(!settleDown(g, nudgePercentage)){
            if(Math.random()*2 >= 1){
                if(!settleLeft(g, maxFlow))
                    settleRight(g, maxFlow);
            }
            else{
                if(!settleRight(g, maxFlow))
                    settleLeft(g, maxFlow);
            }
        }
        

        /*
        //gravity
        if(getY() == 0){
            //sit on bottom for now, maybe delete particle later
        }
        //try immediately below
        else if(g.particleGrid[getX()][getY()-1].isEmpty == true){
            g.swap(getX(), getY(), getX(), getY()-1);
        }
        //then to the sides
        else if((getX() > 0) && (g.particleGrid[getX()-1][getY()-1].isEmpty == true)){
            g.swap(getX(), getY(), getX()-1, getY()-1);
        }
        else if((getX() < 99) && (g.particleGrid[getX()+1][getY()-1].isEmpty == true)){
            g.swap(getX(), getY(), getX()+1, getY()-1);
        }
        */
        
        
    }

}