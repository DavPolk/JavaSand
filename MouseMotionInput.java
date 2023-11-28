import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionInput implements MouseMotionListener {

    GameCanvas game;

    public void loadGame(GameCanvas g){
        game = g;
    }

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

                game.grid.paintElement(gridX, gridY, game.selectedElement, 2);
            }
        }

        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }
    
}
