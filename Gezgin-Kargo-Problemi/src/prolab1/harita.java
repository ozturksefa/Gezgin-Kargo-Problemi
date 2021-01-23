package prolab1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;

import javax.swing.*;

public class harita extends JFrame {

    Image image;
    Image pinImage;
    Image image2;
    int x[];
    int y[];
    int gidis[];
    int donus[];
    int ids[];

    public harita(int x[], int y[], int gidis[], int donus[], int ids[]) {
        this.setTitle("En kısa yol");
        this.setSize(1015, 563);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.x = x;
        this.y = y;
        this.gidis = gidis;
        this.donus = donus;
        this.ids = ids;

    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        ImageIcon bg = new ImageIcon("harita3.png");
        image = bg.getImage();
        g.drawImage(image, 5, 35, null);
        
        

        int k = 0;
        int j = 0;
        while (gidis[k + 1] != 99) {
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(4f));
            g2.drawLine(x[gidis[k]], y[gidis[k]], x[gidis[k + 1]], y[gidis[k + 1]]);
            
            if (gidis[k + 1] == ids[j]) {
                ImageIcon pin = new ImageIcon("pin.png");
                pinImage = pin.getImage();
                g2.drawImage(pinImage, x[gidis[k + 1]]-8, y[gidis[k + 1]]-16, null);
                j++;
            }
            
            k++;
        }
        k = 0;
        float[] dashingPattern2 = {10f, 4f};
        Stroke stroke1 = new BasicStroke(2f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern2, 0.0f);

        g2.setStroke(stroke1);
        g2.setColor(Color.BLACK);
        if (ids.length != 1) {
            while (donus[k + 1] != 99) {
                g2.drawLine(x[donus[k]], y[donus[k]], x[donus[k + 1]], y[donus[k + 1]]);
                k++;
            }

        }
    }

}
