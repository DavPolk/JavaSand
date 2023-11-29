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
        for(int i = getX(); i < getX() + explosionRadius; i++){
            for(int j = getY()-4; j < getY() + explosionRadius; j++){
                if(i > 0 && i < g.gridSizeX - 1){
                    if(j > 0 && j < g.gridSizeY - 1){
                        if(g.particleGrid[i][j].isEmpty){
                            g.spawnElement(i, j, Element.FIRE);
                        }
                        if(g.particleGrid[i][j].density < 999){
                            // if (i < getX()){
                            //     System.out.println("trying to add velocity left");
                            //     g.particleGrid[i][j].velocity[0] -= explosionStrength;
                            // }
                            if (i > getX()){
                                g.particleGrid[i][j].velocity[0] += explosionStrength;
                                g.particleGrid[i][j].velocity[1] += 1;
                            }
                        }
                    }
                }
            }
        }

        for(int i = getX(); i > getX() - explosionRadius; i--){
            for(int j = getY(); j < getY() + explosionRadius; j++){
                if(i > 0 && i < g.gridSizeX - 1){
                    if(j > 0 && j < g.gridSizeY - 1){
                        if(g.particleGrid[i][j].isEmpty){
                            g.spawnElement(i, j, Element.FIRE);
                        }
                        if(g.particleGrid[i][j].density < 999){
                            if (i < getX()){
                            //     System.out.println("trying to add velocity left");
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

        g.spawnElement(getX(), getY(), Element.FIRE);
    }

}