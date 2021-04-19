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
