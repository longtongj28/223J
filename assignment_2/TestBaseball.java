//****************************************************************************************************************************
//Program name: "Baseball Animation".  This program will display a pause-able animation of a ball moving the four corners of a
//quadrilateral.
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
//File name: BaseballFrame.java
//Compile : javac BaseballFrame.java
//Purpose: This is the driver file that will be used to run the entire program.
import javax.swing.JFrame;

public class TestBaseball {
    public static void main(String[] args) {
        BaseballFrame frame = new BaseballFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
