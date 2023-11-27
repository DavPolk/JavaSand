import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;
import java.net.URL;
import javax.imageio.ImageIO;

class gridDrawer{
    public void draw(){
        BufferedImage bufferI = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);

        int[] rgb = {0, 0, 255};
        bufferI.setRGB(0, 0, 2, 2, rgb, 0, 1);


        /*
        Color myColor = new Color(0, 0, 255);
        int[] rgb = {0, 0, 255};
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                bufferI.setRGB(i*4, j*4, 4, 4, rgb, 0, 0);
            }
        }
        */

        final JLabel label = new JLabel(new ImageIcon(bufferI));

        ActionListener listener = new ActionListener() {

            Random random = new Random();

            public void actionPerformed(ActionEvent ae) {
                Graphics g = bufferI.getGraphics();

                g.drawImage(bufferI, 0, 0, null );
                g.dispose();

                label.repaint();
            }
        };
        JOptionPane.showMessageDialog(null, label);
    }
}
    

public class Main { 

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                gridDrawer g = new gridDrawer();
                g.draw();
            }
        });
    }
}