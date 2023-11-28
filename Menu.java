import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Menu{

    GameCanvas game;

    public void loadGame(GameCanvas g){
        game = g;
    }

    public Rectangle sandButton = new Rectangle(100, 495, 60, 25);
    public Rectangle waterButton = new Rectangle(100, 525, 60, 25);
    public Rectangle stoneButton = new Rectangle(100, 555, 60, 25);

    public Rectangle spoutButton = new Rectangle( 640, 495, 60, 25);
    public Rectangle resetButton = new Rectangle(640, 555, 60, 25);


    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font fnt = new Font("arial", Font.PLAIN, 15);
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString(game.selectedElement.toString(), 10, 590);
        if(game.selectedSpout)
            g.drawString("SPOUT OF", 10, 570);

        g.drawString("Sand", sandButton.x+12, sandButton.y+18);
        g2d.draw(sandButton);
        g.drawString("Water", waterButton.x+12, waterButton.y+18);
        g2d.draw(waterButton);
        g.drawString("Stone", stoneButton.x+12, stoneButton.y+18);
        g2d.draw(stoneButton);
        g.drawString("Reset",  resetButton.x+12, resetButton.y+18);
        g2d.draw(resetButton);
        g.drawString("Spout",  spoutButton.x+12, spoutButton.y+18);
        g2d.draw(spoutButton);
    }


}
