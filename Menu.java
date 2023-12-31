import java.awt.*;

public class Menu{

    GameCanvas game;

    public void loadGame(GameCanvas g){
        game = g;
    }

    public Rectangle brushOne = new Rectangle(10, 495, 25, 25);
    public Rectangle brushTwo = new Rectangle(40, 495, 25, 25);
    public Rectangle brushFour = new Rectangle(10, 525, 25, 25);
    public Rectangle brushEight = new Rectangle(40, 525, 25, 25);

    public Rectangle sandButton = new Rectangle(100, 495, 60, 25);
    public Rectangle waterButton = new Rectangle(100, 525, 60, 25);
    public Rectangle stoneButton = new Rectangle(100, 555, 60, 25);

    public Rectangle plantButton = new Rectangle(170, 495, 60, 25);
    public Rectangle fireButton = new Rectangle(170, 525, 60, 25);
    public Rectangle gpButton = new Rectangle(170, 555, 60, 25);

    public Rectangle conwayButton = new Rectangle(240, 495, 60, 25);
    public Rectangle gravWellButton = new Rectangle(240, 525, 60, 25);
    public Rectangle blackHoleButton = new Rectangle(240, 555, 60, 25);

    public Rectangle c4Button = new Rectangle(310, 495, 60, 25);
    public Rectangle nitroButton = new Rectangle(310, 525, 60, 25);
    public Rectangle methaneButton = new Rectangle(310, 555, 60, 25);

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

        g.drawString("1x", brushOne.x+4, brushOne.y+18);
        g2d.draw(brushOne);
        g.drawString("2x", brushTwo.x+4, brushTwo.y+18);
        g2d.draw(brushTwo);
        g.drawString("4x", brushFour.x+4, brushFour.y+18);
        g2d.draw(brushFour);
        g.drawString("8x", brushEight.x+4, brushEight.y+18);
        g2d.draw(brushEight);
        

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
        g.drawString("Plant",  plantButton.x+12, plantButton.y+18);
        g2d.draw(plantButton);
        g.drawString("Fire",  fireButton.x+12, fireButton.y+18);
        g2d.draw(fireButton);
        g.drawString("GunP",  gpButton.x+12, gpButton.y+18);
        g2d.draw(gpButton);
        g.drawString("Conway",  conwayButton.x+6, conwayButton.y+18);
        g2d.draw(conwayButton);
        g.drawString("GravWll",  gravWellButton.x+3, gravWellButton.y+18);
        g2d.draw(gravWellButton);
        g.drawString("BH",  blackHoleButton.x+15, blackHoleButton.y+18);
        g2d.draw(blackHoleButton);
        g.drawString("C4",  c4Button.x+15, c4Button.y+18);
        g2d.draw(c4Button);
        g.drawString("Nitro",  nitroButton.x+12, nitroButton.y+18);
        g2d.draw(nitroButton);
        g.drawString("Methane",  nitroButton.x+3, methaneButton.y+18);
        g2d.draw(methaneButton);
    }


}
