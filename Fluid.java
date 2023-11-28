import java.awt.Color;

public class Fluid extends Particle{

    int maxFlow = 3;
    int nudgePercentage = 10;

    public Fluid(int x, int y){
        super(x, y);
        c = new Color(20, 230, 20);
        isEmpty = false;
        rgb = c.getRGB();
        density = 1.5;
    }

    
    @Override
    public void update(Grid g){
        fluidUpdate(g, maxFlow, nudgePercentage);
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
        fluidJiggle(g, 50);
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
