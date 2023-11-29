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

                if(gridX < game.grid.gridSizeX-1 && gridY < game.grid.gridSizeY-1){
                    if(game.selectedSpout){
                        game.grid.paintSpout(gridX, gridY, game.selectedElement, game.brushSize);
                    }
                    else{
                        game.grid.paintElement(gridX, gridY, game.selectedElement, game.brushSize);
                    }
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


        // public Rectangle brushOne = new Rectangle(10, 495, 25, 25);
        // public Rectangle brushTwo = new Rectangle(40, 495, 25, 25);
        // public Rectangle brushFour = new Rectangle(10, 525, 25, 25);
        // public Rectangle brushEight = new Rectangle(40, 525, 25, 25);

        // public Rectangle sandButton = new Rectangle(100, 495, 60, 25);
        // public Rectangle waterButton = new Rectangle(100, 525, 60, 25);
        // public Rectangle stoneButton = new Rectangle(100, 555, 60, 25);

        // public Rectangle plantButton = new Rectangle(170, 495, 60, 25);
        // public Rectangle fireButton = new Rectangle(170, 525, 60, 25);
        // public Rectangle gpButton = new Rectangle(170, 555, 60, 25);

        // public Rectangle conwayButton = new Rectangle(240, 495, 60, 25);
        // public Rectangle gravWellButton = new Rectangle(240, 525, 60, 25);
        // public Rectangle blackHoleButton = new Rectangle(240, 555, 60, 25);

        // public Rectangle spoutButton = new Rectangle( 640, 495, 60, 25);
        // public Rectangle resetButton = new Rectangle(640, 555, 60, 25);

        //black hole button
        if (mx >= 240 && mx <= 300){
            if (my >= 555 && my <= 575){
                game.selectedElement = Element.BLACKHOLE;
            }
        }

        //Gravwell button
        if (mx >= 240 && mx <= 300){
            if (my >= 525 && my <= 550){
                game.selectedElement = Element.GRAVWELL;
            }
        }

        //Conway game of life button
        if (mx >= 240 && mx <= 300){
            if (my >= 495 && my <= 530){
                game.selectedElement = Element.CONWAY;
            }
        }


        //8x brush toggle
        if (mx >= 40 && mx <= 65){
            if (my >= 525 && my <= 550){
                game.brushSize = 8;
            }
        }

        //4x brush toggle
        if (mx >= 10 && mx <= 35){
            if (my >= 525 && my <= 550){
                game.brushSize = 4;
            }
        }

        //2x brush toggle
        if (mx >= 40 && mx <= 65){
            if (my >= 495 && my <= 520){
                game.brushSize = 2;
            }
        }

        //1x brush toggle
        if (mx >= 10 && mx <= 35){
            if (my >= 495 && my <= 520){
                game.brushSize = 1;
            }
        }

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
