import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Fluid extends Particle{

    int maxFlow = 5;
    int nudgePercentage = 10;

    public Fluid(int x, int y){
        super(x, y);
        c = new Color(20, 10, 230);
        isEmpty = false;
        rgb = c.getRGB();
        density = 1.5;
        type = "Fluid";
    }

    
    @Override
    public void update(Grid g){
        
        if(!fluidSettleDown(g, nudgePercentage, maxFlow)){
            if(Math.random()*2 >= 1){
                if(!fluidSettleLeft(g, maxFlow))
                    fluidSettleRight(g, maxFlow);
            }
            else{
                if(!fluidSettleRight(g, maxFlow))
                    fluidSettleLeft(g, maxFlow);
            }
        }
    }
    

    
    
    /* 
    public boolean settleRight(Grid g, int maxFlowDist){
        if(getY() == 0){
                //sit on bottom for now. might delete fluids on touching bottom later
                return true;
        }

        ArrayList<Integer> potentialXValues = new ArrayList<Integer>();
        for(int i = 0; i <= maxFlowDist; i++){
            if((getX()+i) < g.gridSizeX)
                potentialXValues.add(getX()+i);
        }
        for(int i = 0; i < potentialXValues.size(); i++){
            if((g.particleGrid[potentialXValues.get(i)][getY()-1].getDensity() < this.density) || g.particleGrid[potentialXValues.get(i)][getY()-1].isEmpty){
                g.swap(getX(), getY(), (int) potentialXValues.get(i), getY()-1);
                return true;
            }
        }
        return false;
    }
    */
    
}
