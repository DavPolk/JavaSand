import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMotionInput extends MouseAdapter{

    GameCanvas game;

    public void loadGame(GameCanvas g){
        game = g;
    }

    //
    // Mouse element drawing
    //
    @Override
    public void mouseDragged(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        int topOfMenu = game.HEIGHT * game.SCALE - (game.SCALE * game.MENUSIZE);

        if (mx >= 0 && mx <= game.WIDTH * game.SCALE){
            if (my >= 0 && my <= game.HEIGHT * game.SCALE - (game.SCALE * game.MENUSIZE))
            {
                int gridX = (int) (mx / game.SCALE);
                int gridY = (int) (topOfMenu - my) / game.SCALE;

                if(game.selectedSpout){
                    game.grid.paintSpout(gridX, gridY, game.selectedElement, game.brushSize);
                }
                else{
                    game.grid.paintElement(gridX, gridY, game.selectedElement, game.brushSize);
                }
            }
        }
    }

    //
    //  Buttons
    //

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // public Rectangle sandButton = new Rectangle(100, 495, 60, 25);
        // public Rectangle waterButton = new Rectangle(100, 525, 60, 25);
        // public Rectangle stoneButton = new Rectangle(100, 555, 60, 25);

        // public Rectangle plantButton = new Rectangle(170, 495, 60, 25);
        // public Rectangle fireButton = new Rectangle(170, 525, 60, 25);
        // public Rectangle gpButton = new Rectangle(170, 555, 60, 25);

        // public Rectangle spoutButton = new Rectangle( 640, 495, 60, 25);
        // public Rectangle resetButton = new Rectangle(640, 555, 60, 25);

        //gunpowder button
        if (mx >= 170 && mx <= 230){
            if (my >= 555 && my <= 580){
                game.selectedElement = Element.GUNPOWDER;
            }
        }

        //fire button
        if (mx >= 170 && mx <= 230){
            if (my >= 525 && my <= 550){
                game.selectedElement = Element.FIRE;
            }
        }

        //plant button
        if (mx >= 170 && mx <= 230){
            if (my >= 495 && my <= 520){
                game.selectedElement = Element.PLANT;
            }
        }

        //spout button
        if (mx >= 640 && mx <= 700){
            if (my >= 495 && my <= 520){
                game.selectedSpout = !(game.selectedSpout);
            }
        }
        //sand button
        if (mx >= 100 && mx <= 160){
            if (my >= 495 && my <= 520){
                game.selectedElement = Element.SAND;
            }
        }

        //water button
        if (mx >= 100 && mx <= 160){
            if (my >= 525 && my <= 550){
                game.selectedElement = Element.WATER;
            }
        }

        //stone button
        if (mx >= 100 && mx <= 160){
            if (my >= 555 && my <= 580){
                game.selectedElement = Element.STONE;
            }
        }

        //reset button
        if (mx >= 640 && mx <= 700){
            if (my >= 555 && my <= 580){
                game.grid.reset();
            }
        }
    }
}
