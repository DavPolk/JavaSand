import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

    GameCanvas game;
    private boolean isHeld = false;

    public void loadGame(GameCanvas g){
        game = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // public Rectangle sandButton = new Rectangle(100, 495, 60, 25);
        // public Rectangle waterButton = new Rectangle(100, 525, 60, 25);
        // public Rectangle stoneButton = new Rectangle(100, 555, 60, 25);

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

        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}