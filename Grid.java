import java.util.*;

public class Grid {

    public int gridSizeX = 240;
    public int gridSizeY = 160;
    Particle[][] particleGrid;
    ArrayList<int[]> conwaysToAdd = new ArrayList<int[]>();
    ArrayList<int[]> conwaysToRemove = new ArrayList<int[]>();

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
        
        // if(particleGrid[x1][y1].element == Element.FIRE){
        //     particleGrid[x1][y1].density = 0;
        // }
        // if(particleGrid[x2][y2].element == Element.FIRE){
        //     particleGrid[x2][y2].density = 0;
        // }

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
        if(e == Element.PLANT)
            particleGrid[x][y] = new Plant(x, y);
        if(e == Element.FIRE)
            particleGrid[x][y] = new Fire(x, y);
        if(e == Element.GUNPOWDER)
            particleGrid[x][y] = new Gunpowder(x, y);
        if(e == Element.CONWAY)
            particleGrid[x][y] = new Conway(x, y);
        if(e == Element.EMPTY)
            particleGrid[x][y] = new Particle(x, y);
        if(e == Element.GRAVWELL)
            particleGrid[x][y] = new GravWell(x, y);
        if(e == Element.BLACKHOLE)
            particleGrid[x][y] = new BlackHole(x, y);
        if(e == Element.C4)
            particleGrid[x][y] = new C4(x, y);
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

    //returns an arrayList of coordinate pairs of neighbor particles
    public ArrayList <int[]> getNeighbors(int x, int y){
        ArrayList <int[]> neighborList = new ArrayList<int[]>();

        int[][] neighbors = {{x-1, y-1}, {x, y-1}, {x+1, y-1}, {x-1, y}, {x+1, y}, {x-1, y+1}, {x, y+1}, {x+1, y+1}};

        for(int i = 0; i < 8; i++){
            if (neighbors[i][0] >= 0 && neighbors[i][0] < gridSizeX){
                if(neighbors[i][1] >= 0 && neighbors[i][1] < gridSizeY){
                    int[] coords = neighbors[i];
                    neighborList.add(coords);
                }
            }
        }
        return neighborList;
        
    }

    //returns an arrayList of coordinate pairs of neighbor particles of a specific elemennt
    public ArrayList <int[]> getNeighborsOfElement(int x, int y, Element e){ 
        ArrayList <int[]> neighbors = getNeighbors(x, y);
        ArrayList <int[]> result = new ArrayList<int[]>();
        for(int i = 0; i < neighbors.size(); i++){
            if(particleGrid[neighbors.get(i)[0]][neighbors.get(i)[1]].element == e){
                result.add(neighbors.get(i));
            }
        }
        return result;
    }

    public int[] getVector(int x1, int y1, int x2, int y2){
        int deltaX = x1-x2;
        int deltaY = y1-y2;
        int[] v = {deltaX, deltaY};
        return v;
    }

    public double distFromTo(int x1, int y1, int x2, int y2){
        double result = Math.sqrt((x1-x2)^2 + (y1-y2)^2);
        return result;
    }
}