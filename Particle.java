import java.awt.Color;
import java.util.*;

public class Particle {

    public Color c;
    public int rgb;
    private int gridX;
    private int gridY;
    public double density;
    public boolean isEmpty;
    public Element element;
    public int flammability;
    public boolean ignoreNextGravity = false; //tells particle to ignore next gravity update
    public boolean beenGravLeft = false; //has already been pulled left by a gravwell this tick
    public boolean beenGravRight = false;
    public boolean beenGravUp = false;
    public boolean beenGravDown = false;
    public boolean hasMovedX = false;
    public int[] velocity = {0, 0};

            //default particles act as empty cells for the grid
            //Element types extend Particle and override constructor and update functions

    public Particle(int x, int y){
        gridX = x;
        gridY = y;
        c = new Color(40, 40, 40);
        isEmpty = true;
        rgb = c.getRGB();
        density = 0.0;
        element = Element.EMPTY;
        flammability = 0;
    }


    public void update(Grid g){
        //empty particles check for neighboring conway particles
        //converts self to conway if exactly 3 are neighboring
        ArrayList <int[]> neighboringConways = g.getNeighborsOfElement(getX(), getY(), Element.CONWAY);
        if(neighboringConways.size() == 3){
            int[] coords = {getX(), getY()};
            g.conwaysToAdd.add(coords);
        }
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

    public double getDensity(){
    return density;
    }

    public void replaceWithEmpty(Grid g){
        g.particleGrid[getX()][getY()] = new Particle(getX(), getY());
    }

    public void tryVelocityUp(Grid g){
        if(velocity[1] > 0){
            this.ignoreNextGravity = true;
            if(getY() < g.gridSizeY - 1){
                if(g.particleGrid[getX()][getY()+1].density < this.density){
                    g.swap(getX(), getY(), getX(), getY()+1);
                }
                else if(g.particleGrid[getX()][getY()+1].density < 999){
                    g.particleGrid[getX()][getY()+1].velocity[1] += this.velocity[1];
                    this.velocity[1] = 0;
                }
                else{
                    this.velocity[1] = 0;
                }
            }
            else{
                this.velocity[1] = 0;
            }
            if(this.velocity[1] > 0)
                velocity[1] -= 1;
        }
    }

    public void tryVelocityDown(Grid g){
        if(velocity[1] < 0){
            this.ignoreNextGravity = true;
            if(getY() > 0){
                if(g.particleGrid[getX()][getY()-1].density < this.density){
                    g.swap(getX(), getY(), getX(), getY()-1);
                }
                else if(g.particleGrid[getX()][getY()-1].density < 999){
                    g.particleGrid[getX()][getY()-1].velocity[1] += this.velocity[1];
                    this.velocity[1] = 0;
                }
                else{
                    this.velocity[1] = 0;
                }
            }
            else{
                this.velocity[1] = 0;
            }
            if(this.velocity[1] < 0)
                velocity[1] += 1;
        }
    }
    

    public void tryVelocityRight(Grid g){
        if(velocity[0] > 0){
            if(getX() < g.gridSizeX-1){
                if(g.particleGrid[getX()+1][getY()].density < this.density){
                    g.swap(getX(), getY(), getX()+1, getY());
                }
                else if(g.particleGrid[getX()+1][getY()].density < 999){
                    g.particleGrid[getX()+1][getY()].velocity[0] += this.velocity[0];
                    this.velocity[0] = 0;
                }
                else{
                    this.velocity[0] = 0;
                }
            }
            else{
                this.velocity[0] = 0;
            }
            if(velocity[0] > 0)
                velocity[0] -= 1;
        }
    }

    public void tryVelocityLeft(Grid g){
         if (velocity[0] < 0){
            if(getX() > 0){
                if(g.particleGrid[getX()-1][getY()].density < this.density){
                    g.swap(getX(), getY(), getX()-1, getY());
                }
                else if(g.particleGrid[getX()-1][getY()].density < 999){
                    g.particleGrid[getX()-1][getY()].velocity[0] += this.velocity[0];
                    this.velocity[0] = 0;
                }
                else{
                    this.velocity[0] = 0;
                }
            }
            else{
                this.velocity[0] = 0;
            }
            if(velocity[0] < 0)
                velocity[0] += 1;
        }
    }

    //Gravity
    //tries to move the particle one cell down. If it fails, or is nudged, tries down-left and down-right in random order.
    //maxFlowDist = number of cells to the left and right to be scanned 

    //Particles move by swapping locations
    //Can move down into empty cells or Particles with lower densities
    public void tryGravity(Grid g, int maxFlowDist, int nudgePercentage){
        if(!ignoreNextGravity){
            if(!settleDown(g, nudgePercentage)){
                if(Math.random()*2 >= 1){
                    if(!settleLeft(g, maxFlowDist))
                        settleRight(g, maxFlowDist);
                }
                else{
                    if(!settleRight(g, maxFlowDist))
                        settleLeft(g, maxFlowDist);
                }
            }
        }
        else{
            ignoreNextGravity = false;
        }
    }
    
    //used for gravity
    //returns true if it moves the particle
    public boolean settleLeft(Grid g, int maxFlowDist){
        if(getY() == 0){
                //sit on bottom for now. might delete particles on touching bottom later
                this.replaceWithEmpty(g);
                return true;
        }

        //build list of possible x values
        ArrayList<Integer> potentialXValues = new ArrayList<Integer>();
        for(int i = 0; i <= maxFlowDist; i++){
            if((getX()-i) >= 0)
                potentialXValues.add(getX()-i);
        }

        //try cells in random order
        Collections.shuffle(potentialXValues);
        for(int i = 0; i < potentialXValues.size(); i++)
            if((g.particleGrid[potentialXValues.get(i)][getY()-1].getDensity() < this.density) || g.particleGrid[potentialXValues.get(i)][getY()-1].isEmpty){
                g.swap(getX(), getY(),potentialXValues.get(i), getY()-1);
                return true;
            }

        return false;
    }


    //used for gravity
    //returns true if it moves the particle
    public boolean settleRight(Grid g, int maxFlowDist){
        if(getY() == 0){
                //sit on bottom for now. might delete particles on touching bottom later
                this.replaceWithEmpty(g);
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


    //Move particle down, if possible. Chance to return false to allow particle to move to the sides while midair
    public boolean settleDown(Grid g, int nudgePercentage){
        if(getY() == 0){
            this.replaceWithEmpty(g);
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

    //used for fluid settling
    //will scan row below to the right until it finds a suitable cell
    //if a cell is found, move toward it maxFlowDist number of cells

    public boolean fluidSettleRight(Grid g, int maxFlowDist){
        if(getY() == 0){
                //sit on bottom for now. might delete fluids on touching bottom later
                this.replaceWithEmpty(g);
                return true;
        }

        for(int i = getX(); i < g.gridSizeX; i++){
                if(g.particleGrid[i][getY()].getDensity() > this.density){
                    return false;
                }
                if((g.particleGrid[i][getY()-1].getDensity() < this.density) || g.particleGrid[i][getY()-1].isEmpty){
                    if((i - getX()) > maxFlowDist){
                        g.swap(getX(), getY(), getX()+maxFlowDist, getY());
                        return true;
                    }
                    else{
                        g.swap(getX(), getY(), i, getY());
                        return true;
                    }
                }
            
        }
        return false;
    }

    public boolean fluidSettleLeft(Grid g, int maxFlowDist){
        if(getY() == 0){
                //sit on bottom for now. might delete fluids on touching bottom later
                this.replaceWithEmpty(g);
                return true;
        }

        for(int i = getX(); i >= 0; i--){
                if(g.particleGrid[i][getY()].getDensity() > this.density){
                    return false;
                }
                if((g.particleGrid[i][getY()-1].getDensity() < this.density) || g.particleGrid[i][getY()-1].isEmpty){
                    if((getX() -i ) > maxFlowDist){
                        g.swap(getX(), getY(), getX()-maxFlowDist, getY());
                        return true;
                    }
                    else{
                        g.swap(getX(), getY(), i, getY());
                        return true;
                    }
                }
        }
        return false;
    }

    //used for fluid settling
    //returns true if it moves the particle
    public boolean fluidSettleDown(Grid g, int nudgePercentage, int maxFlow){
        if(getY() == 0){
                //sit on bottom for now. might delete fluids on touching bottom later
                this.replaceWithEmpty(g);
                return true;
        }
        //attempt to nudge
       //if(g.particleGrid[getX()][getY()].isEmpty){
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
            
                //otherwise try to go straight down
                if(g.particleGrid[getX()][getY()].isEmpty){
                    g.swap(getX(), getY(), getX(), getY()-1);
                    return true;
                }
            }
        //}

        //check if particle below is less dense, if so swap
        if((g.particleGrid[getX()][getY()-1].getDensity() < this.density)){
            g.swap(getX(), getY(), getX(), getY()-1);
            return true;
        }
        return false;
    }

    public void fluidJiggle(Grid g, int percent){
        if(Math.random()*100 <= percent){
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
    }

    public void resetGravMarkers(){
        beenGravLeft = false; //has already been pulled left by a gravwell this tick
        beenGravRight = false;
        beenGravUp = false;
        beenGravDown = false;
    }

    public void updateExplosives(Grid g){
    }
}




        /* V1 gravity

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
        