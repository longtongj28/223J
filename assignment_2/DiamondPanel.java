//****************************************************************************************************************************
//Program name: "Baseball Animation".  This program will display a pause-able animation of a ball moving the four corners of a quadrilateral.
//  Copyright (C) 2021 Johnson Tong                                                                         *
//This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License  *
//version 3 as published by the Free Software Foundation.                                                                    *
//This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
//warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.     *
//A copy of the GNU General Public License v3 is available here:  <https://www.gnu.org/licenses/>.                           *
//****************************************************************************************************************************

//Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**

//Author information:
//Author: Johnson Tong
//Mail: jt28@csu.fullerton.edu

//Program information:
//Program name: Baseball Animation
//Programming language: Java
//Files: TestBaseball.java, BaseballFrame.java, DiamondPanel.java, run.sh
//Date project began: 2021 Feb 25
//Date of last update: 2021 Mar 07
//Status: Finished; testing completed.
//Purpose: This program displays the animation of a ball moving from the four corners of a quadrilateral and
//         demonstrates the mathematical calculation of animation.
//Base test system: Linux (Tuffix) system with Bash shell and openjdk-14-jdk

//This module
//File name: DiamondPanel.java
//Compile : javac DiamondPanel.java
//Purpose: This class defines class DiamondPanel, which inherits from JPanel to draw the graphics of the animation.
//This module (class) is called from the BaseballFrame class.
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class DiamondPanel extends JPanel {
    private int point1x = 300;
    private int point1y = 375;

    private int point2x = 75;
    private int point2y = 200;

    private int point3x = 275;
    private int point3y = 50;

    private int point4x = 525;
    private int point4y = 175;

    // size of bases
    private int baseSide = 32;
    private int adjust = baseSide / 2;

    // player characteristics
    private double balldiameter = 24;
    private double ballradius = balldiameter / 2;

    private double ball_center_x;
    private double ball_center_y;
    private double ball_upper_corner_x;
    private double ball_upper_corner_y;
    private int ball_upper_corner_integer_x = point1x - (int)ballradius;
    private int ball_upper_corner_integer_y = point1y - (int)ballradius;
    private double distance_center_of_ball_2_end_of_line_segment;
    private double distance_moved_in_one_tic;
    private boolean successfulmove;
    private double dx;
    private double dy;
    private double a1,b1,a2,b2; //double coordinates of end points of line segment.
    private int i1,j1,i2,j2;    //integer coordinates of end points of line segment.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(48, 52, 63));

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        // draw the lines to the bases
        g2.drawLine(point1x, point1y, point2x, point2y);
        g2.drawLine(point2x, point2y, point3x, point3y);
        g2.drawLine(point3x, point3y, point4x, point4y);
        g2.drawLine(point4x, point4y, point1x, point1y);

        // draw the bases and center them on the points
        g2.setColor(new Color(214, 229, 227));
        g2.fill3DRect(point1x - adjust, point1y - adjust, baseSide, baseSide, true);
        g2.setColor(new Color(246, 216, 174));
        g2.fill3DRect(point2x - adjust, point2y - adjust, baseSide, baseSide, true);
        g2.setColor(new Color(145, 77, 118));
        g2.fill3DRect(point3x - adjust, point3y - adjust, baseSide, baseSide, true);
        g2.setColor(new Color(117, 159, 188));
        g2.fill3DRect(point4x - adjust, point4y - adjust, baseSide, baseSide, true);

        // draw the player (starts at point 1)

        g2.setColor(new Color(218, 65, 103));
        g2.fillOval(ball_upper_corner_integer_x,ball_upper_corner_integer_y,
                                   (int)Math.round(balldiameter),(int)Math.round(balldiameter));
    }
    public void initialize(double startx, double starty, double endx, double endy, double sx, double sy) {
        a1 = startx;
        b1 = starty;
        a2 = endx;
        b2 = endy;
        i1 = (int)Math.round(startx);
        j1 = (int)Math.round(starty);
        i2 = (int)Math.round(endx);
        j2 = (int)Math.round(endy);
        dx = sx;
        dy = sy;
        distance_moved_in_one_tic = Math.sqrt(sx*sx + sy*sy);  //Hypotenuse in right triangle with legs sx and sy.
        ball_center_x = a1; //Ball receives its starting coordinates.
        ball_center_y = b1;
        ball_upper_corner_x = ball_center_x - ballradius;   //Translate from center to upper left corner.
        ball_upper_corner_y = ball_center_y - ballradius;   //Ditto
        ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);  //Round to nearest int
        ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);  //Ditto
        distance_center_of_ball_2_end_of_line_segment
                      = Math.sqrt(Math.pow(ball_center_x - a2,2) + Math.pow(ball_center_y - b2,2));
    }

    public void setInitialCoord(double p1x, double p1y,
                                double p2x, double p2y,
                                double p3x, double p3y,
                                double p4x, double p4y
                                )
    {
      point1x = (int) p1x;
      point1y = (int) p1y;

      point2x = (int) p2x;
      point2y = (int) p2y;

      point3x = (int) p3x;
      point3y = (int) p3y;

      point4x = (int) p4x;
      point4y = (int) p4y;
      ball_upper_corner_integer_x = point1x - (int) ballradius;
      ball_upper_corner_integer_y = point1y - (int) ballradius;
    }
    public boolean movePlayer() {
        successfulmove = true;
          if(distance_center_of_ball_2_end_of_line_segment > distance_moved_in_one_tic)
              {//This is the case where the destination is further away than a single step can accomplish.
               ball_center_x += dx;
               ball_center_y += dy;
              }
          else
              {//This is the case where the ball needs exactly one short hop to reach its destination.
               ball_center_x = a2;
               ball_center_y = b2;
               successfulmove = false;
              }
          ball_upper_corner_x = ball_center_x - ballradius;
          ball_upper_corner_y = ball_center_y - ballradius;
          ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);
          ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);
          distance_center_of_ball_2_end_of_line_segment = Math.sqrt(Math.pow(ball_center_x - i2,2) + Math.pow(ball_center_y - j2,2));
          return successfulmove;
    }
}
