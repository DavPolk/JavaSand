import java.awt.Color;
import java.util.ArrayList;

public class BlackHole extends GravWell {

    public BlackHole(int x, int y) {
        super(x, y);
        density = 1000;
        c = new Color(0, 0, 0);
        rgb = c.getRGB();
    }
    

    @Override
    public void update(Grid g){


        //pull particles in the lower left
        for(int i = getX() - gravRange; i < getX(); i++){
            if(i > 0){
                for(int j = getY() - gravRange; j < getY(); j++){
                    if(j > 0){
                        g.particleGrid[i][j].ignoreNextGravity = true;
                        if(g.particleGrid[i][j].density < this.density){
                            if(!g.particleGrid[i][j].beenGravLeft){
                                if(g.particleGrid[i][j].density > g.particleGrid[i+1][j].density){
                                    g.particleGrid[i][j].beenGravLeft = true;
                                    g.swap(i, j, i+1, j);
                                }
                            }
                            if(!g.particleGrid[i][j].beenGravUp){
                                if(g.particleGrid[i][j].density > g.particleGrid[i][j+1].density){
                                    g.particleGrid[i][j].beenGravUp = true;
                                    g.swap(i, j, i, j+1);
                                }
                            }
                        }
                    }
                }
            }
        }

        //pull particles in the lower right
        for(int i = getX() + gravRange; i > getX(); i--){
            if (i < g.gridSizeX-1){
                for(int j = getY() - gravRange; j < getY(); j++){
                    if(j > 0){
                        g.particleGrid[i][j].ignoreNextGravity = true;
                        if(g.particleGrid[i][j].density < this.density){
                            if(!g.particleGrid[i][j].beenGravRight){
                                if(g.particleGrid[i][j].density > g.particleGrid[i-1][j].density){
                                    g.particleGrid[i][j].beenGravRight = true;
                                    g.swap(i, j, i-1, j);
                                }
                            }
                            if(!g.particleGrid[i][j].beenGravUp){
                                if(g.particleGrid[i][j].density > g.particleGrid[i][j+1].density){
                                    g.particleGrid[i][j].beenGravUp = true;
                                    g.swap(i, j, i, j+1);
                                }
                            }
                        }
                    }
                }
            }
        }

        //pull particles in the upper right
        for(int i = getX() + gravRange; i > getX(); i--){
            if(i < g.gridSizeX-1){
                for(int j = getY() + gravRange; j > getY(); j--){
                    if( j < g.gridSizeY-1){
                        g.particleGrid[i][j].ignoreNextGravity = true;
                        if(g.particleGrid[i][j].density < this.density){
                            if(!g.particleGrid[i][j].beenGravRight){
                                if(g.particleGrid[i][j].density > g.particleGrid[i-1][j].density){
                                    g.particleGrid[i][j].beenGravRight = true;
                                    g.swap(i, j, i-1, j);
                                }
                            }
                            if(!g.particleGrid[i][j].beenGravDown){
                                if(g.particleGrid[i][j].density > g.particleGrid[i][j-1].density){
                                    g.particleGrid[i][j].beenGravDown = true;
                                    g.swap(i, j, i, j-1);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        //pull particles in the upper left
        for(int i = getX() - gravRange; i < getX(); i++){
            if(i > 0){
                for(int j = getY() + gravRange; j > getY(); j--){
                    if(j < g.gridSizeY-1){
                        g.particleGrid[i][j].ignoreNextGravity = true;
                        if(g.particleGrid[i][j].density < this.density){
                            if(!g.particleGrid[i][j].beenGravLeft){
                                if(g.particleGrid[i][j].density > g.particleGrid[i+1][j].density){
                                    g.particleGrid[i][j].beenGravLeft = true;
                                    g.swap(i, j, i+1, j);
                                }
                            }
                            if(!g.particleGrid[i][j].beenGravDown){
                                if(g.particleGrid[i][j].density > g.particleGrid[i][j-1].density){
                                    g.particleGrid[i][j].beenGravDown = true;
                                    g.swap(i, j, i, j-1);
                                }
                            }
                        }
                    }
                }
            }
        }

        ArrayList<int[]> neighbors = g.getNeighbors(getX(), getY());
        for(int i = 0; i < neighbors.size(); i++){
            if(g.particleGrid[neighbors.get(i)[0]][neighbors.get(i)[1]].density < this.density){
                g.spawnElement(neighbors.get(i)[0], neighbors.get(i)[1], Element.EMPTY);
            }
        }
    }
}
