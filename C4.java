import java.util.ArrayList;

public class C4 extends Solid{

    public int explosionRadius = 20;
    public int explosionStrength = 2;

    public C4(int x, int y) {
        super(x, y);
    }


    @Override
    public void update(Grid g){
        ArrayList<int[]> fireNeighbors = g.getNeighborsOfElement(getX(), getY(), Element.FIRE);
        if (fireNeighbors.size() > 0){
            explode(g);
        }
    }

    public void explode(Grid g){

        //handle particles to the top right
        for(int i = getX()+1; i < getX() + explosionRadius; i++){
            for(int j = getY() + 1; j < getY() + explosionRadius; j++){
                if(i > 0 && i < g.gridSizeX - 1){
                    if(j > 0 && j < g.gridSizeY - 1){
                        if(g.particleGrid[i][j].isEmpty){
                            g.spawnElement(i, j, Element.FIRE);
                        }
                        if(g.particleGrid[i][j].density < 999){
                            if (i > getX()){
                                g.particleGrid[i][j].velocity[0] += explosionStrength;
                                g.particleGrid[i][j].velocity[1] += 1;
                            }
                        }
                    }
                }
            }
        }

        //handle particles to the top left
        for(int i = getX() - explosionRadius; i < getX(); i++){
            for(int j = getY()+1; j < getY() + explosionRadius; j++){
                if(i > 0 && i < g.gridSizeX - 1){
                    if(j > 0 && j < g.gridSizeY - 1){
                        if(g.particleGrid[i][j].isEmpty){
                            g.spawnElement(i, j, Element.FIRE);
                        }
                        if(g.particleGrid[i][j].density < 999){
                            if (i < getX()){
                                //System.out.println("trying to add velocity left");
                                g.particleGrid[i][j].velocity[0] -= explosionStrength;
                                g.particleGrid[i][j].velocity[1] += 1;
                            }
                            // if (i > getX()){
                            //     g.particleGrid[i][j].velocity[0] += explosionStrength;
                            // }
                        }
                    }
                }
            }
        }

        //handle particles to the lower right
        for(int i = getX()+1; i < getX() + explosionRadius; i++){
            for(int j = getY() - explosionRadius; j < getY(); j++){
                if(i > 0 && i < g.gridSizeX - 1){
                    if(j > 0 && j < g.gridSizeY - 1){
                        if(g.particleGrid[i][j].isEmpty){
                            g.spawnElement(i, j, Element.FIRE);
                        }
                        if(g.particleGrid[i][j].density < 999){
                            if (i > getX()){
                                g.particleGrid[i][j].velocity[0] += explosionStrength;
                                g.particleGrid[i][j].velocity[1] -= explosionStrength;
                            }
                        }
                    }
                }
            }
        }

        //handle particles to the lower left
        for(int i = getX() - explosionRadius; i < getX(); i++){
            for(int j = getY() - explosionRadius; j < getY(); j++){
                if(i > 0 && i < g.gridSizeX - 1){
                    if(j > 0 && j < g.gridSizeY - 1){
                        if(g.particleGrid[i][j].isEmpty){
                            g.spawnElement(i, j, Element.FIRE);
                        }
                        if(g.particleGrid[i][j].density < 999){
                            if (i < getX()){
                                g.particleGrid[i][j].velocity[0] -= explosionStrength;
                                g.particleGrid[i][j].velocity[1] -= explosionStrength;
                            }
                        }
                    }
                }
            }
        }


        g.spawnElement(getX(), getY(), Element.FIRE);
    }

}