import java.awt.Color;
import java.util.*;

public class Particle {

    public Color c;
    public int rgb;

    private int gridX;
    private int gridY;
    public double density;

    public boolean isEmpty;
    public String type;

    public Particle(int x, int y){
        gridX = x;
        gridY = y;
        c = new Color(0, 0, 0);
        isEmpty = true;
        rgb = c.getRGB();
        density = 0.0;
        type = "Empty";
    }

    public void update(Grid g){
    }

    public void randomizeColor(){
        int r = (int) (Math.random()*255);
        int g = (int) (Math.random()*255);
        int b = (int) (Math.random()*255);
        c = new Color(r, g, b);
        rgb = c.getRGB();
    }

    public int getX(){
        return gridX;
    }

    public int getY(){
        return gridY;
    }

    public void setX(int x){
        gridX = x;
    }

    public void setY(int y){
        gridY = y;
    }

    public void replaceWithEmpty(Grid g){
        g.particleGrid[getX()][getY()] = new Particle(getX(), getY());
    }

    /*
    public boolean settleLeft(Grid g, int maxFlowDist){  
        if(getY() == 0){
                //sit on bottom for now. might delete fluids on touching bottom later
                return true;
        }

        for(int i = getX(); i >= 0; i--){
            if((g.particleGrid[i][getY()-1].isEmpty) && (getX()-i <= maxFlowDist)){
                g.swap(getX(), getY(), i, getY()-1);
                return true;
            }
        }
        return false;
    }
    */

    public boolean settleLeft(Grid g, int maxFlowDist){
        if(getY() == 0){
                //sit on bottom for now. might delete particles on touching bottom later
                return true;
        }

        ArrayList<Integer> potentialXValues = new ArrayList<Integer>();
        for(int i = 0; i <= maxFlowDist; i++){
            if((getX()-i) >= 0)
                potentialXValues.add(getX()-i);
        }
        Collections.shuffle(potentialXValues);
        for(int i = 0; i < potentialXValues.size(); i++)
            if((g.particleGrid[potentialXValues.get(i)][getY()-1].getDensity() < this.density) || g.particleGrid[potentialXValues.get(i)][getY()-1].isEmpty){
                g.swap(getX(), getY(),potentialXValues.get(i), getY()-1);
                return true;
            }

        return false;
    }


/*
    public boolean settleRight(Grid g, int maxFlowDist){
        for(int i = getX(); i < g.gridSizeX; i++){
            if(getY() == 0){
                //sit on bottom for now. might delete fluids on touching bottom later
                return true;
            }
            else if(g.particleGrid[i][getY()-1].isEmpty && (i-getX() <= maxFlowDist)){
                g.swap(getX(), getY(), i, getY()-1);
                return true;
            }
        }
        return false;
    }
    */

    public boolean settleRight(Grid g, int maxFlowDist){
        if(getY() == 0){
                //sit on bottom for now. might delete particles on touching bottom later
                return true;
        }

        ArrayList<Integer> potentialXValues = new ArrayList<Integer>();
        for(int i = 0; i <= maxFlowDist; i++){
            if((getX()+i) < g.gridSizeX)
                potentialXValues.add(getX()+i);
        }
        Collections.shuffle(potentialXValues);
        for(int i = 0; i < potentialXValues.size(); i++)
            if((g.particleGrid[potentialXValues.get(i)][getY()-1].getDensity() < this.density) || g.particleGrid[potentialXValues.get(i)][getY()-1].isEmpty){
                g.swap(getX(), getY(), (int) potentialXValues.get(i), getY()-1);
                return true;
            }

        return false;
    }

    public double getDensity(){
        return density;
    }

    public boolean settleDown(Grid g, int nudgePercentage){
        if(getY() == 0){
                //sit on bottom for now. might delete particles on touching bottom later
                return true;
        }
        if (Math.random()*100 < nudgePercentage)
            return false;
        if((g.particleGrid[getX()][getY()-1].isEmpty)){
            g.swap(getX(), getY(), getX(), getY()-1);
            return true;
        }
        if((g.particleGrid[getX()][getY()-1].getDensity() < this.density)){
            g.swap(getX(), getY(), getX(), getY()-1);
            return true;
        }
        return false;
    }

    public boolean fluidSettleRight(Grid g, int maxFlowDist){
        if(getY() == 0){
                //sit on bottom for now. might delete fluids on touching bottom later
                return true;
        }

        for(int i = getX(); i < g.gridSizeX; i++){
                if(g.particleGrid[i][getY()].getDensity() > this.density){
                    return false;
                }
                if((g.particleGrid[i][getY()-1].getDensity() < this.density) || g.particleGrid[i][getY()-1].isEmpty){
                    g.swap(getX(), getY(), i, getY()-1);
                    return true;
                }
            
        }
        return false;
    }

    public boolean fluidSettleLeft(Grid g, int maxFlowDist){
        if(getY() == 0){
                //sit on bottom for now. might delete fluids on touching bottom later
                return true;
        }

        for(int i = getX(); i >= 0; i--){
                if(g.particleGrid[i][getY()].getDensity() > this.density){
                    return false;
                }
                if((g.particleGrid[i][getY()-1].getDensity() < this.density) || g.particleGrid[i][getY()-1].isEmpty){
                    g.swap(getX(), getY(), i, getY()-1);
                    return true;
                }
        }
        return false;
    }

    public boolean fluidSettleDown(Grid g, int nudgePercentage, int maxFlow){
        if(getY() == 0){
                //sit on bottom for now. might delete fluids on touching bottom later
                return true;
        }
        if((g.particleGrid[getX()][getY()-1].isEmpty)){
            if (Math.random()*100 < nudgePercentage){
                if(Math.random()*2 >= 1){
                    if(getX() < g.gridSizeX-1){
                        if(g.particleGrid[getX()+1][getY()-1].isEmpty){
                            g.swap(getX(), getY(), getX()+1, getY()-1);
                            return true;
                        }
                    }
                }
                else{
                    if(getX() > 0){
                        if(g.particleGrid[getX()-1][getY()-1].isEmpty){
                            g.swap(getX(), getY(), getX()-1, getY()-1);
                            return true;
                        }
                    }
                }
            }
            g.swap(getX(), getY(), getX(), getY()-1);
            return true;
        }
        if((g.particleGrid[getX()][getY()-1].getDensity() < this.density)){
            g.swap(getX(), getY(), getX(), getY()-1);
            return true;
        }
        return false;
    }
}