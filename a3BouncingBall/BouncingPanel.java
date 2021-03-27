//****************************************************************************************************************************
//Program name: "Bouncing Animation". The program showcases a ball that can be resized and have its colors changed during
// animation time. When the ball reaches a side of the panel, it will bounce in the opposite direction.
// Copyright 2021 Johnson Tong.
//                                                                                                                           * 
//This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License  *
//version 3 as published by the Free Software Foundation.  This program is distributed in the hope that it will be useful,   *
//but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   *
//the GNU General Public License for more details.  A copy of the GNU General Public License v3 is available here:           *
//<https://www.gnu.org/licenses/>.                                                                                           *
//****************************************************************************************************************************

//Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**

//Author: Johnson Tong
//Email: jt28@fullerton.edu

//Program information
  //Program name: Bouncing Animation
  //Programming language: Java
  //Files in this program (4): run.sh (bash script) BouncingDriver.java (Driver) BouncingFrame.java (main UI design) 
  //                       BouncingPanel.java (JPanel modified to show animations)
  //Date project began: March 12, 2021
  //Date of last update: March 27, 2021
  //Status: Ready for public posting.  The program was tested significantly and did very well.                    
  //Purpose: This program demonstrates a ball that can change direction when hitting a wall, and change colors during animation.
//
//This module
  //File name: BouncingPanel.java
  //Purpose:  This file modifies the JPanel class and utilizes the graphics object to draw a ball and give characteristics
  //           to allow the ball to bounce off of walls and change color.


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
    // player characteristics
    private double ballradius = 15;
    private double balldiameter = ballradius * 2;

    //center
    private int point1x;
    private int point1y;

    private int frameHeight;
    private int frameWidth;

    private double ball_center_x;
    private double ball_center_y;
    private double ball_upper_corner_x;
    private double ball_upper_corner_y;
    private int ball_upper_corner_integer_x;
    private int ball_upper_corner_integer_y;
    private double dx;
    private double dy;

    private boolean initialize = false;

    private double dright;
    private double dleft;
    private double dtop;
    private double dbottom;

    private boolean colorOne = true;
    private Color colorChoiceOne = Color.yellow;
    private Color colorChoiceTwo = Color.blue;
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        if (colorOne) {
            g2.setColor(colorChoiceOne);
        }
        else {
            g2.setColor(colorChoiceTwo);
        }

        if (initialize) {
            g2.fillOval(ball_upper_corner_integer_x, ball_upper_corner_integer_y, (int) Math.round(balldiameter),
                (int) Math.round(balldiameter));
        }
    }

    public boolean getInitialize() {
        return initialize;
    }

    public void setDifferentials(double sx, double sy){
        dx = sx;
        dy = sy;
    }

    public void changeColor() {
        colorOne = !colorOne;
    }

    public void increaseSize() {
        if (balldiameter < (frameHeight - 50)) {
            ballradius += 4.0;
            balldiameter = ballradius * 2.0;
            dright = frameWidth - ball_center_x - ballradius;
            dleft = 0 - ball_center_x + ballradius;
            dbottom = frameHeight - ball_center_y - ballradius;
            dtop = 0 - ball_center_y + ballradius;

            if (dbottom < 0) { // bigger than the bottom, get current bottom = bottom border
            ball_center_y = ball_center_y + dbottom;
            ball_upper_corner_y = ball_center_y - ballradius;
            ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);
            }
            else if (dtop > 0) { // bigger than top
                ball_center_y = ball_center_y + dtop;
                ball_upper_corner_y = ball_center_y - ballradius;
                ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);
            }
            else {
                ball_upper_corner_integer_y = (int)Math.round(ball_center_y - ballradius);
            }

            if (dright < 0) { //bigger than right side
                ball_center_x = ball_center_x + dright;
                ball_upper_corner_x = ball_center_x - ballradius;
                ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);
            }
            else if (dleft > 0) {
                ball_center_x = ball_center_x + dleft;
                ball_upper_corner_x = ball_center_x - ballradius;
                ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);
            }
            else {
                ball_upper_corner_integer_x = (int)Math.round(ball_center_x - ballradius);
            }
            repaint();
        }
    }

    public void decreaseSize(double currX, double currY) {
        if (ballradius > 5) {
            ballradius -= 4.0;
            balldiameter = ballradius * 2.0;
            ball_upper_corner_integer_x = (int)Math.round(currX - ballradius);
            ball_upper_corner_integer_y = (int)Math.round(currY - ballradius);
            repaint();
        }
    }

    public void setColorOne(Color newChoice) {
        colorChoiceOne = newChoice;
        repaint();
    }    
    public void setColorTwo(Color newChoice) {
        colorChoiceTwo = newChoice;
        repaint();
    }
    public void moveball(double posX, double posY) {
        dright = frameWidth - posX - ballradius;
        dleft = 0 - posX + ballradius;
        dbottom = frameHeight - posY - ballradius;
        dtop = 0 - posY + ballradius;
        // when the ball reaches a border
        if (dright < dx || dleft > dx) {
            if (dright < dx) { // going to overtake the right border
                ball_center_x = frameWidth - ballradius;
                ball_upper_corner_x = ball_center_x - ballradius;
                repaint();
            }
            else { // overtaking left side
                ball_center_x = 0 + ballradius;
                ball_upper_corner_x = ball_center_x - ballradius;
                repaint();
            }
            dx = -dx;
        }
        else {
            ball_center_x += dx;
            ball_upper_corner_x = ball_center_x - ballradius;
        }

        if (dbottom < dy || dtop > dy) {
            if(dbottom < dy) { // overtaking the bottom border
                ball_center_y = frameHeight - ballradius;
                ball_upper_corner_y = ball_center_y - ballradius;
                repaint();
            }
            else {  //overtaking the top border
                ball_center_y = 0 + ballradius;
                ball_upper_corner_y = ball_center_y - ballradius;
                repaint();
            }
            dy = -dy;
        }
        else {
            ball_center_y += dy;
            ball_upper_corner_y = ball_center_y - ballradius;
        }


        ball_upper_corner_integer_y = (int) Math.round(ball_upper_corner_y);
        ball_upper_corner_integer_x = (int) Math.round(ball_upper_corner_x);
    }
    public double getBallLocX() {
        return ball_center_x;
    }
    public double getBallLocY() {
        return ball_center_y;
    }

    public void reset(int height, int width) {
        frameHeight = height;
        frameWidth = width;

        point1x = width/2;
        point1y = height/2;
        ball_center_x = (double)width/2;
        ball_center_y = (double) height/2;
        ball_upper_corner_integer_x = point1x - (int) ballradius;
        ball_upper_corner_integer_y = point1y - (int) ballradius;
        initialize = true;
        repaint();
    }
}
