import java.util.*;

public class Grid {

    public int gridSizeX = 240;
    public int gridSizeY = 160;
    Particle[][] particleGrid;

    Grid(){
        particleGrid = new Particle[gridSizeX][gridSizeY];
        for(int i = 0; i < gridSizeX; i++){
            for(int j = 0; j < gridSizeY; j++){
                particleGrid[i][j] = new Particle(i, j);
            }
        }
    }

    public void reset(){
        for(int i = 0; i < gridSizeX; i++){
            for(int j = 0; j < gridSizeY; j++){
                particleGrid[i][j] = new Particle(i, j);
            }
        }
    }

    public int getSizeX(){
        return gridSizeX;
    }
    //swap the positions of two particles and update their internal coordinates
    public void swap(int x1, int y1, int x2, int y2){
        int tempx = x1;
        int tempy = y1;

        particleGrid[x1][y1].setX(particleGrid[x2][y2].getX());
        particleGrid[x1][y1].setY(particleGrid[x2][y2].getY());

        particleGrid[x2][y2].setX(tempx);
        particleGrid[x2][y2].setY(tempy);

        Particle tempP = particleGrid[x1][y1];
        particleGrid[x1][y1] = particleGrid[x2][y2];
        particleGrid[x2][y2] = tempP;
    }

    public void paintElement(int x, int y, Element e, int brushSize){
        for(int i = 0; i < brushSize; i++){
            if((i+x) <= gridSizeX){
                for(int j = 0; j < brushSize; j++){
                    if((i+j) <= gridSizeY){
                        spawnElement(x+i, y+j, e);
                    }
                }
            }
        }
    }

    public void spawnElement(int x, int y, Element e){
        if(e == Element.SAND)
            particleGrid[x][y] = new Sand(x, y);
        if(e == Element.WATER)
            particleGrid[x][y] = new Water(x, y);
        if(e == Element.STONE)
            particleGrid[x][y] = new Stone(x, y);
    }

    public void paintSpout(int x, int y, Element e, int brushSize){
        for(int i = 0; i < brushSize; i++){
            if((i+x) <= gridSizeX){
                for(int j = 0; j < brushSize; j++){
                    if((i+j) <= gridSizeY){
                        spawnSpout(x+i, y+j, e);
                    }
                }
            }
        }
    }
    
    public void spawnSpout(int x, int y, Element e){
            particleGrid[x][y] = new Spout(x, y, e);
    }
}