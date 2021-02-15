import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import java.awt.Graphics;
public class DiamondPanel extends JPanel{
    private int point1x = 300;
    private int point1y = 375;

    private int point2x = 75;
    private int point2y = 200;

    private int point3x = 275;
    private int point3y = 50;

    private int point4x = 525;
    private int point4y = 175;

    //size of bases
    private int baseSide = 12;
    private int adjust = baseSide/2;

    //player characteristics
    private int playerSide = 20;
    private int playerAdjust = playerSide/2;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(48,52,63));

        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3));

        // draw the lines to the bases
        g2.drawLine(point1x, point1y, point2x, point2y);
        g2.drawLine(point2x, point2y, point3x, point3y);
        g2.drawLine(point3x, point3y, point4x, point4y);
        g2.drawLine(point4x, point4y, point1x, point1y);

        // draw the bases and center them on the points
        g2.setColor(new Color(214,229,227));
        g2.fill3DRect(point1x-adjust, point1y-adjust, baseSide, baseSide, true);
        g2.setColor(new Color(246,216,174));
        g2.fill3DRect(point2x-adjust, point2y-adjust, baseSide, baseSide, true);
        g2.setColor(new Color(145,77,118));
        g2.fill3DRect(point3x-adjust, point3y-adjust, baseSide, baseSide, true);
        g2.setColor(new Color(117,159,188));
        g2.fill3DRect(point4x-adjust, point4y-adjust, baseSide, baseSide, true);

        // draw the player (starts at point 1)
        g2.setColor(new Color(218,65,103));
        g2.fillOval(point1x-playerAdjust, point1y-playerAdjust, playerSide, playerSide);
        
    }
}
