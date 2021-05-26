import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.sound.midi.SysexMessage;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class BouncingPanel extends JPanel {
    
    // center
    private int point1x;
    private int point1y;
    
    private int frameHeight;
    private int frameWidth;
    
    // ball = sun
    // player characteristics
    private double ballradius = 40;
    private double balldiameter = ballradius * 2;
    private double ball_center_x;
    private double ball_center_y;
    private double ball_upper_corner_x;
    private double ball_upper_corner_y;
    private int ball_upper_corner_integer_x;
    private int ball_upper_corner_integer_y;
    private double dx;
    private double dy;

    // Cat information
    // cat = earth
    private double cat_radius = 15;
    private double cat_diameter = cat_radius * 2;
    private double cat_center_x = 30;
    private double cat_center_y = 30;
    private double cat_upper_corner_x = 0.0;
    private double cat_upper_corner_y = 0.0;
    private int cat_upper_corner_integer_x = 0;
    private int cat_upper_corner_integer_y = 0;
    private double cat_dx;
    private double cat_dy;
    private double t = 0;
    private double mt = 0;
    private double moont = 0;

    // Mars information
    private double mars_radius = 14;
    private double mars_diameter = cat_radius * 2;
    private double mars_center_x = 30;
    private double mars_center_y = 30;
    private double mars_upper_corner_x = 0.0;
    private double mars_upper_corner_y = 0.0;
    private int mars_upper_corner_integer_x = 0;
    private int mars_upper_corner_integer_y = 0;

    // moon information
    private double moon_radius = 5;
    private double moon_diameter = moon_radius * 2;
    private double moon_center_x = 30;
    private double moon_center_y = 30;
    private double moon_upper_corner_x = 0.0;
    private double moon_upper_corner_y = 0.0;
    private int moon_upper_corner_integer_x = 0;
    private int moon_upper_corner_integer_y = 0;
    private double distance_between_moon_and_earth;

    // distance between the two things:
    private double distance_between_center;
    private double distance_between_edges;

    private boolean initialize = false;

    // mouse distance relative to borders
    private double dright;
    private double dleft;
    private double dtop;
    private double dbottom;

    // cat distance relative to borders
    // cat = sun
    private double cat_dright;
    private double cat_dleft;
    private double cat_dtop;
    private double cat_dbottom;

    private Color colorChoiceOne = Color.yellow; // sun
    private Color colorChoiceTwo = Color.blue; // earth
    private Color colorChoiceThree = Color.orange; // mars
    private Color colorChoiceFour = Color.gray; // moon

    private boolean entering = false;
    private boolean too_close = false;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        if (initialize) {
            //earth
            g2.setColor(colorChoiceTwo);
            g2.fillOval(cat_upper_corner_integer_x, cat_upper_corner_integer_y, (int) Math.round(cat_diameter),
                    (int) Math.round(cat_diameter));
            //sun
            g2.setColor(colorChoiceOne);
            g2.fillOval(ball_upper_corner_integer_x, ball_upper_corner_integer_y, (int) Math.round(balldiameter),
                    (int) Math.round(balldiameter));
            //mars
            g2.setColor(colorChoiceThree);
            g2.fillOval(mars_upper_corner_integer_x, mars_upper_corner_integer_y, (int) Math.round(mars_diameter),
                    (int) Math.round(mars_diameter));
            //moon
            g2.setColor(colorChoiceFour);
            g2.fillOval(moon_upper_corner_integer_x, moon_upper_corner_integer_y, (int) Math.round(moon_diameter),
                    (int) Math.round(moon_diameter));
        }
    }

    public boolean getInitialize() {
        return initialize;
    }

    public boolean tooClose() {
        return too_close;
    }

    public void setDifferentials(double sx, double sy) {
        dx = sx;
        dy = sy;
    }

    public void setCatDifferentials(double sx, double sy) {
        cat_dx = sx;
        cat_dy = sy;
    }

    public void setColorOne(Color newChoice) {
        colorChoiceOne = newChoice;
        repaint();
    }

    public void setColorTwo(Color newChoice) {
        colorChoiceTwo = newChoice;
        repaint();
    }

    public void moveEarth(double st, double distance_between_sun_and_earth) {
        cat_center_x = (double)frameWidth/2 + distance_between_sun_and_earth*Math.cos(t);
        cat_center_y = (double)frameHeight/2 + distance_between_sun_and_earth*Math.sin(t);
        cat_upper_corner_x = cat_center_x - cat_radius;
        cat_upper_corner_y = cat_center_y - cat_radius;
        cat_upper_corner_integer_x = (int)cat_upper_corner_x;
        cat_upper_corner_integer_y = (int)cat_upper_corner_y;
        t+=st;
    }
    public void moveMars(double st, double distance) {
        mars_center_x = (double)frameWidth/2 + distance*Math.cos(mt);
        mars_center_y = (double)frameHeight/2 + distance*Math.sin(mt);
        mars_upper_corner_x = mars_center_x - mars_radius;
        mars_upper_corner_y = mars_center_y - mars_radius;
        mars_upper_corner_integer_x = (int)mars_upper_corner_x;
        mars_upper_corner_integer_y = (int)mars_upper_corner_y;
        mt+=st;
    }

    public void moveMoon(double st) {
        moon_center_x = cat_center_x + distance_between_moon_and_earth*Math.cos(moont);
        moon_center_y = cat_center_y + distance_between_moon_and_earth*Math.sin(moont);
        moon_upper_corner_x = moon_center_x - moon_radius;
        moon_upper_corner_y = moon_center_y - moon_radius;
        moon_upper_corner_integer_x = (int)moon_upper_corner_x;
        moon_upper_corner_integer_y = (int)moon_upper_corner_y;
        moont+=st;
    }
    public double getEarthLocX() {
        return cat_center_x;
    }

    public double getEarthLocY() {
        return cat_center_y;
    }

    public void reset(int height, int width) {
        frameHeight = height;
        frameWidth = width;
        t = 0;
        mt = 0;
        moont = 0;
        // Sun info
        point1x = width / 2;
        point1y = height / 2;
        ball_center_x = (double) width / 2;
        ball_center_y = (double) height / 2;
        ball_upper_corner_integer_x = point1x - (int) ballradius;
        ball_upper_corner_integer_y = point1y - (int) ballradius;

        // earth info
        int distance_from_right_earth = width - 800;
        cat_center_x = (double) distance_from_right_earth;
        cat_center_y = ball_center_y;
        cat_upper_corner_integer_x = distance_from_right_earth - (int) cat_radius;
        cat_upper_corner_integer_y = (int) cat_center_y - (int) cat_radius;

        // mars info
        int distance_from_right_mars = width - 650;
        mars_center_x = (double) distance_from_right_mars;
        mars_center_y = ball_center_y;
        mars_upper_corner_integer_x = distance_from_right_mars - (int) mars_radius;
        mars_upper_corner_integer_y = (int) mars_center_y - (int) mars_radius;
        
        // moon info
        int distance_from_right_moon = width - 775;
        moon_center_x = (double) distance_from_right_moon;
        moon_center_y = ball_center_y;
        moon_upper_corner_integer_x = distance_from_right_moon - (int) moon_radius;
        moon_upper_corner_integer_y = (int) moon_center_y - (int) moon_radius;
        distance_between_moon_and_earth = (double)800 - 775;
        
        initialize = true;
        repaint();
    }
}
