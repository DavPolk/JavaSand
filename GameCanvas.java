import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class GameCanvas extends Canvas implements Runnable{

    public static final int WIDTH = 240;
    public static final int HEIGHT = 200;
    public static final int MENUSIZE = 40;
    public static final int SCALE = 3;
    public final String TITLE = "Java Sand";
    public Element selectedElement = Element.SAND;
    public Element selectedSpoutElement = Element.WATER;
    public boolean selectedSpout = false;
    public int brushSize = 2;

    private boolean running = false;
    private Thread thread;
    private BufferedImage image;
    public Grid grid;
    private Menu menu = new Menu();

    public void run(){
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;  //max game updates per second
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        grid = new Grid();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        MouseMotionInput mm = new MouseMotionInput();
        mm.loadGame(this);
        this.addMouseListener(mm);
        this.addMouseMotionListener(mm);
        menu.loadGame(this);

        //
        //      Particle inputs  
        //

        
        // for(int i = 20; i < 50; i++){
        //     grid.particleGrid[i][20] = new Solid(i, 20);
        //     grid.particleGrid[i][21] = new Solid(i, 21);
        // }

        /*
        for(int i = 10; i < 30; i++){
            for(int j = 40; j < 70; j++){
                grid.particleGrid[i][j] = new Particulate(i, j);
            }
        }

        for(int i = 70; i < 90; i++){
            for(int j = 40; j < 70; j++){
                grid.particleGrid[i][j] = new Particulate(i, j);
            }
        }
        */


        //game loop
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            draw();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " TPS, FPS: " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running)
            return;
        
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    private void tick(){
        for(int i = 0; i < grid.gridSizeX; i++){
            for(int j = 0; j < grid.gridSizeY; j++){
                grid.particleGrid[i][j].update(grid);
                grid.particleGrid[i][j].resetGravMarkers();
            }
        }


        //handle conway updates
        //have to do this all at once instead of in individual particle updates or everything goes sideways
        for(int i = 0; i < grid.conwaysToAdd.size(); i++){
            grid.spawnElement(grid.conwaysToAdd.get(i)[0], grid.conwaysToAdd.get(i)[1], Element.CONWAY);
        }
        for(int i = 0; i < grid.conwaysToRemove.size(); i++){
            grid.spawnElement(grid.conwaysToRemove.get(i)[0], grid.conwaysToRemove.get(i)[1], Element.EMPTY);
        }
        grid.conwaysToAdd.clear();
        grid.conwaysToRemove.clear();
    }

    private void draw(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        for(int i = 0; i < grid.gridSizeX; i++){
            for(int j = 0; j < grid.gridSizeY; j++){
                int ParticleRGB = grid.particleGrid[i][j].rgb;
                image.setRGB(i, HEIGHT-j-MENUSIZE, ParticleRGB);
            }
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        menu.render(g);
        g.dispose();
        bs.show();
    }


    public static void main(String[] args){
        GameCanvas canvas = new GameCanvas();

        canvas.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        canvas.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        canvas.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(canvas.TITLE);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.start();
    }
}