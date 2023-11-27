import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Fluid extends Particle{

    int maxFlow = 2;
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
        
        fluidUpdate(g, maxFlow, nudgePercentage);
        // if(!fluidSettleDown(g, nudgePercentage, maxFlow)){
        //     if(Math.random()*2 >= 1){
        //         if(!fluidSettleLeft(g, maxFlow))
        //             fluidSettleRight(g, maxFlow);
        //     }
        //     else{
        //         if(!fluidSettleRight(g, maxFlow))
        //             fluidSettleLeft(g, maxFlow);
        //     }
        // }
    }
    

    public void fluidUpdate(Grid g, int flow, int nudge){
        if(fluidSettleDown(g, nudge, flow))
            return;
        else{
            if(Math.random()*2 >= 1){
                if(fluidSettleLeft(g, flow))
                    return;
                else{
                    if(fluidSettleRight(g, flow))
                        return;
                }
            }
            else{
                if(fluidSettleRight(g, flow))
                    return;
                else{
                    if(fluidSettleLeft(g, flow))
                        return;
                }
            }
        }
        fluidJiggle(g);
    }

    public void fluidJiggle(Grid g){
        if((getX() != 0) && (getX() != g.gridSizeX-1)){
            if(Math.random()*2 >= 1){
                if(g.particleGrid[getX()+1][getY()].isEmpty)
                    g.swap(getX(), getY(), getX()+1, getY());
                else if(g.particleGrid[getX()-1][getY()].isEmpty)
                    g.swap(getX(), getY(), getX()-1, getY());
            }
            else{
                if(g.particleGrid[getX()-1][getY()].isEmpty)
                    g.swap(getX(), getY(), getX()-1, getY());
                else if(g.particleGrid[getX()+1][getY()].isEmpty)
                    g.swap(getX(), getY(), getX()+1, getY());
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
