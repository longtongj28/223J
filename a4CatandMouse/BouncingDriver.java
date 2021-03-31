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
  //File name: BouncingDriver.java
  //Purpose:  This file is the main driver of the program. The entire program frame is initialized from here.

import javax.swing.JFrame;

public class BouncingDriver {
    public static void main(String[] args) {
        BouncingFrame frame = new BouncingFrame();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
