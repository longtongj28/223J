//****************************************************************************************************************************
//Program name: "Cat and Mouse". This program demonstrates a mouse (the small ball) being chased by a Cat (the bigger ball).
// The Cat must continuously change its differentials to match the position of the mouse and continue chasing it. The mouse
// has a special trait, where it can jump through the left and right side of the panel. Once the Cat gets too close, the
// animation ends and the Cat enjoys its dinner.
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
//Program name: Cat and Mouse
//Programming language: Java
//Files in this program (4): run.sh (bash script) BouncingDriver.java (Driver) BouncingFrame.java (main UI design) 
//                       BouncingPanel.java (JPanel modified to show animations)
//Date project began: April 1, 2021
//Date of last update: April 18, 2021
//Status: Ready for public posting.  The program was tested through many cases and works logically.           
//Purpose: Demonstrate some low level physics in graphical objects through a fun interface.
//
//This module
//File name: BouncingPanel.java
//Purpose:  This file modifies the JPanel class and utilizes the graphics object to create a cat and mouse, as well as define
//          the physics and mathematical components for them.

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

    // center
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

    // Cat information
    private double cat_radius = 30;
    private double cat_diameter = cat_radius * 2;
    private double cat_center_x = 30;
    private double cat_center_y = 30;
    private double cat_upper_corner_x = 0.0;
    private double cat_upper_corner_y = 0.0;
    private int cat_upper_corner_integer_x = 0;
    private int cat_upper_corner_integer_y = 0;
    private double cat_dx;
    private double cat_dy;

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
    private double cat_dright;
    private double cat_dleft;
    private double cat_dtop;
    private double cat_dbottom;

    private boolean colorOne = true;
    private Color colorChoiceOne = Color.cyan;
    private Color colorChoiceTwo = Color.green;

    private boolean entering  = false;
    private boolean too_close = false;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        if (initialize) {
            g2.setColor(colorChoiceTwo);
            g2.fillOval(cat_upper_corner_integer_x, cat_upper_corner_integer_y, (int) Math.round(cat_diameter),
                    (int) Math.round(cat_diameter));
            g2.setColor(colorChoiceOne);
            g2.fillOval(ball_upper_corner_integer_x, ball_upper_corner_integer_y, (int) Math.round(balldiameter),
                    (int) Math.round(balldiameter));

        }
    }

    public boolean getInitialize() {
        return initialize;
    }

    public double calcDistance() {
        distance_between_center = Math.sqrt(Math.pow(ball_center_x - cat_center_x, 2) + Math.pow(ball_center_y-cat_center_y, 2));
        return distance_between_center;
    }
    public double calcDistanceEdge() {
        distance_between_edges = distance_between_center - ballradius - cat_radius;
        return distance_between_edges;
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

    public void changeColor() {
        colorOne = !colorOne;
    }

    public void setColorOne(Color newChoice) {
        colorChoiceOne = newChoice;
        repaint();
    }

    public void setColorTwo(Color newChoice) {
        colorChoiceTwo = newChoice;
        repaint();
    }

    public void moveball(double posX, double posY, double distance_moved_in_one_tic) {
        dright = frameWidth - posX - ballradius;
        dleft = 0 - posX + ballradius;
        dbottom = frameHeight - posY - ballradius;
        dtop = 0 - posY + ballradius;

        distance_between_center = calcDistance();
        distance_between_edges = calcDistanceEdge();
        // when the ball reaches a border
        if(ball_upper_corner_x >= frameWidth){
            ball_center_x = 0 - ballradius;
            ball_upper_corner_x = 0 - balldiameter;
            entering = true;
        }
        else if (entering && ball_upper_corner_x >= 0) {
            entering = false;
        }
        else if (ball_upper_corner_x + ballradius < 0 && !entering) {
            ball_center_x = frameWidth - ballradius;
            ball_upper_corner_x = frameWidth - balldiameter;
        }
        else {
            ball_center_x += dx;
            ball_upper_corner_x = ball_center_x - ballradius;
        }

        if (dbottom < dy || dtop > dy) {
            if (dbottom < dy) { // overtaking the bottom border
                ball_center_y = frameHeight - ballradius;
                ball_upper_corner_y = ball_center_y - ballradius;
                repaint();
            } else { // overtaking the top border
                ball_center_y = 0 + ballradius;
                ball_upper_corner_y = ball_center_y - ballradius;
                repaint();
            }
            dy = -dy;
        } else {
            ball_center_y += dy;
            ball_upper_corner_y = ball_center_y - ballradius;
        }
        
        if(distance_moved_in_one_tic > distance_between_edges) {
            //undo our previous move, it's too close
            ball_center_x -= dx;
            ball_center_y -= dy;
            dx = ((cat_center_x - ball_center_x) * distance_between_edges)/distance_between_center;
            dy = ((cat_center_y - ball_center_y) * distance_between_edges)/distance_between_center;
            ball_center_x += dx;
            ball_center_y += dy;
            ball_upper_corner_x = ball_center_x - ballradius;
            ball_upper_corner_y = ball_center_y - ballradius;
            too_close = true;
            ball_upper_corner_integer_y = (int) Math.round(ball_upper_corner_y);
            ball_upper_corner_integer_x = (int) Math.round(ball_upper_corner_x);
            repaint();
        }

        ball_upper_corner_integer_y = (int) Math.round(ball_upper_corner_y);
        ball_upper_corner_integer_x = (int) Math.round(ball_upper_corner_x);
    }
    
    public void moveCat(double catSpeed) {
        distance_between_center = calcDistance();
        distance_between_edges = calcDistanceEdge();

        cat_dx = catSpeed*(ball_center_x - cat_center_x) / distance_between_center;
        cat_dy = catSpeed*(ball_center_y - cat_center_y) / distance_between_center;
        
        cat_dright = frameWidth - cat_center_x - cat_radius;
        cat_dleft = 0 - cat_center_x + cat_radius;
        cat_dbottom = frameHeight - cat_center_y - cat_radius;
        cat_dtop = 0 - cat_center_y + cat_radius;

        if (cat_dright < cat_dx || cat_dleft > cat_dx) {
            if (cat_dright < cat_dx) { // going to overtake the right border
                cat_center_x = frameWidth - cat_radius;
                cat_upper_corner_x = cat_center_x - cat_radius;
                repaint();
            } else { // overtaking left side
                cat_center_x = 0 + cat_radius;
                cat_upper_corner_x = cat_center_x - cat_radius;
                repaint();
            }
            cat_dx = -cat_dx;
        } else {
            cat_center_x += cat_dx;
            cat_upper_corner_x = cat_center_x - cat_radius;
        }

        if (cat_dbottom < cat_dy || cat_dtop > cat_dy) {
            if (cat_dbottom < cat_dy) { // overtaking the bottom border
                cat_center_y = frameHeight - cat_radius;
                cat_upper_corner_y = cat_center_y - cat_radius;
                repaint();
            } else { // overtaking the top border
                cat_center_y = 0 + cat_radius;
                cat_upper_corner_y = cat_center_y - cat_radius;
                repaint();
            }
            cat_dy = -cat_dy;
        } else {
            cat_center_y += cat_dy;
            cat_upper_corner_y = cat_center_y - cat_radius;
        }
        
        if(catSpeed > distance_between_edges) {
            //undo our previous move, it's too close
            cat_center_x -= cat_dx;
            cat_center_y -= cat_dy;
            cat_dx = ((ball_center_x - cat_center_x) * distance_between_edges)/distance_between_center;
            cat_dy = ((ball_center_y - cat_center_y) * distance_between_edges)/distance_between_center;
            cat_center_x += cat_dx;
            cat_center_y += cat_dy;
            cat_upper_corner_x = cat_center_x - cat_radius;
            cat_upper_corner_y = cat_center_y - cat_radius;
            too_close = true;
            cat_upper_corner_integer_y = (int) Math.round(cat_upper_corner_y);
            cat_upper_corner_integer_x = (int) Math.round(cat_upper_corner_x);
            repaint();
        }

        cat_upper_corner_integer_y = (int) Math.round(cat_upper_corner_y);
        cat_upper_corner_integer_x = (int) Math.round(cat_upper_corner_x);
    }
    public double getBallLocX() {
        return ball_center_x;
    }

    public double getBallLocY() {
        return ball_center_y;
    }

    public void reset(int height, int width, double catPosX, double catPosY ) {
        frameHeight = height;
        frameWidth = width;
        too_close = false;

        // mouse info
        point1x = width / 2;
        point1y = height / 2;
        ball_center_x = (double) width / 2;
        ball_center_y = (double) height / 2;
        ball_upper_corner_integer_x = point1x - (int) ballradius;
        ball_upper_corner_integer_y = point1y - (int) ballradius;

        // cat info
        cat_center_x = catPosX;
        cat_center_y = catPosY;
        cat_upper_corner_integer_x = 0;
        cat_upper_corner_integer_y = 0;

        initialize = true;
        repaint();
    }
}
