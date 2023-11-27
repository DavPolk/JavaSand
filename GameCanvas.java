import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class GameCanvas extends Canvas implements Runnable{

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    public static final int SCALE = 6;
    public final String TITLE = "Java Sand";

    private boolean running = false;
    private Thread thread;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private Grid grid;

    public void run(){
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;  //max game updates per second
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        grid = new Grid();


        //
        //      Particle inputs  
        //

        /*
        for(int i = 20; i < 50; i++){
            grid.particleGrid[i][20] = new Solid(i, 20);
            grid.particleGrid[i][21] = new Solid(i, 21);
        }

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

        grid.particleGrid[70][80] = new Spout(70, 80);

        grid.particleGrid[30][80] = new ParticulateSpout(30, 80);      



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
        grid.particleGrid[90][90].randomizeColor();
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                grid.particleGrid[i][j].update(grid);
            }
        }
    }

    private void draw(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                int ParticleRGB = grid.particleGrid[i][j].rgb;
                image.setRGB(i, 99-j, ParticleRGB);
            }
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

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