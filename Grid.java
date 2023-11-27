import java.util.*;

public class Grid {

    public int gridSizeX = 100;
    public int gridSizeY = 100;
    Particle[][] particleGrid;

    Grid(){
        particleGrid = new Particle[gridSizeX][gridSizeY];
        for(int i = 0; i < gridSizeX; i++){
            for(int j = 0; j < gridSizeY; j++){
                particleGrid[i][j] = new Particle(i, j);
            }
        }
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
}