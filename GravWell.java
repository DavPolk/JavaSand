import java.awt.Color;

public class GravWell extends Solid{

    public int gravRange = 50;
    public int pullStrength = 80;

    public GravWell(int x, int y) {
        super(x, y);
        c = new Color(255, 255, 255);
        rgb = c.getRGB();
        baseColor = c;
    }


    @Override
    public void update(Grid g){


        //pull particles in the lower left
        for(int i = getX() - gravRange; i <= getX(); i++){
            if(i > 0){
                for(int j = getY() - gravRange; j < getY(); j++){
                    if(j > 0){
                       // g.particleGrid[i][j].ignoreNextGravity = true;
                        if(g.particleGrid[i][j].density < 10){
                            // if(!g.particleGrid[i][j].beenGravLeft){
                            //     if(g.particleGrid[i][j].density > g.particleGrid[i+1][j].density){
                            //         g.particleGrid[i][j].beenGravLeft = true;
                            //         g.swap(i, j, i+1, j);
                            //     }
                            // }
                            if(g.particleGrid[i][j].velocity[0] < 5)
                                g.particleGrid[i][j].velocity[0] += 1;
                            if(g.particleGrid[i][j].velocity[1] < 5)
                                g.particleGrid[i][j].velocity[1] += 2;
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
                        //g.particleGrid[i][j].ignoreNextGravity = true;
                        if(g.particleGrid[i][j].density < 10){
                            if(g.particleGrid[i][j].velocity[0] > -5)
                                g.particleGrid[i][j].velocity[0] -= 1;
                            if(g.particleGrid[i][j].velocity[1] < 5)
                                g.particleGrid[i][j].velocity[1] += 2;
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
                        //g.particleGrid[i][j].ignoreNextGravity = true;
                        if(g.particleGrid[i][j].density < 10){
                           if(g.particleGrid[i][j].velocity[0] > -5)
                                g.particleGrid[i][j].velocity[0] -= 1;
                            if(g.particleGrid[i][j].velocity[1] > -5)
                                g.particleGrid[i][j].velocity[1] -= 1;
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
                        //g.particleGrid[i][j].ignoreNextGravity = true;
                        if(g.particleGrid[i][j].density < 10){
                            if(g.particleGrid[i][j].velocity[0] < 5)
                                g.particleGrid[i][j].velocity[0] += 1;
                            if(g.particleGrid[i][j].velocity[1] > -5)
                                g.particleGrid[i][j].velocity[1] -= 1;
                        }
                    }
                }
            }
        }


        














        /* first attempt at gravwell update method

        for(int i = 0; i < g.gridSizeX; i++){
            for(int j = g.gridSizeY - 1; j > 0; j--){
                if(!(i == getX() && j == getY())){
                    int[] vect = g.getVector(getX(), getY(), i, j);

                    if(Math.abs(vect[0]) > 1 && Math.abs(vect[1]) > 1){
                        if(g.particleGrid[i][j].density < 10){
                            double oddsToMoveX = 1 + Math.abs(vect[0]) / (1 + Math.abs((vect[0]) +Math.abs(vect[1])));
                            if(Math.random() < oddsToMoveX ){
                                if(Math.abs(vect[0]) <= gravRange && Math.abs(vect[0]) > 1){
                                    if(vect[0] > 0){
                                        if(g.particleGrid[i+1][j].density < g.particleGrid[i][j].density){
                                            g.swap(i, j, i+1, j);
                                        }
                                    }
                                    if(vect[0] < 0){
                                        if(g.particleGrid[i-1][j].density < g.particleGrid[i][j].density){
                                            g.swap(i, j, i-1, j);
                                        }
                                    }
                                }
                            }
                            else{
                                if(Math.abs(vect[1]) <= gravRange && Math.abs(vect[1]) > 2){
                                    if(vect[1] > 0){
                                        if(g.particleGrid[i][j+2].density < g.particleGrid[i][j].density){
                                            g.swap(i, j, i, j+2);
                                        }
                                    }
                                    if(vect[1] < 0){
                                        if(g.particleGrid[i][j-1].density < g.particleGrid[i][j].density){
                                            g.swap(i, j, i, j-1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }*/
    }
}