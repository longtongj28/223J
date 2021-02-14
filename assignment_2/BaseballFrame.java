//****************************************************************************************************************************
//Program name: "Payroll Calculator"". The program's purpose is to allow the user to calculate payroll automatically in a
// visually appealing user interface. Copyright 2021 Johnson Tong.
//This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License  *
//version 3 as published by the Free Software Foundation.                                                                    *
//This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
//warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.     *
//A copy of the GNU General Public License v3 is available here:  <https://www.gnu.org/licenses/>.                           *
//****************************************************************************************************************************

//Author information:
//Author: Johnson Tong
//Mail: jt28@csu.fullerton.edu

//Program information:
//Program name: Payroll Calculator
//Programming language: Java
//Files: PayrollCalculations.java, PayrollFrame.java, TestPayroll.java, run.sh
//Date project began: 2021-Jan-21 
//Date of last update: 2021-Feb-03 
//Status: Finished
//Distribution: Users are invited to try to crash by the use of invalid inputs.
//Purpose: Perform Payroll Calculations in a nice and simple UI.

//This module
//File name: PayrollFrame.java
//Compile: PayrollFrame.java
//Purpose: provide the definition and user interface for the program. Invoked in the TestPayroll.java.

//Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class BaseballFrame extends JFrame {
    private JPanel programNamePanel;
    private JPanel diamondPanel;
    private JPanel buttonsPanel;

    private FlowLayout flowLayout;
    private int frameHeight = 650;
    private int frameWidth = 600;
    //program name panel elements
    private JLabel programeNameLabel;
    //program diamond panel elements
    //program buttons panel elements


    public BaseballFrame() {
        super("Baseball Frame");
        flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        setTitle("Diamond Animation");
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setLayout(flowLayout);
        setLocationRelativeTo(null);
        //some fonts
        Font font1 = new Font("Liberation Sans", Font.BOLD, 25);
        //program name panel
        programNamePanel = new JPanel();
        programNamePanel.setPreferredSize(new Dimension(frameWidth, 75));
        programNamePanel.setBackground(new Color(251, 255, 241));

        programeNameLabel = new JLabel("Johnson's Diamond Animation");
        programNamePanel.setFont(font1);
        programNamePanel.add(programeNameLabel);

        add(programNamePanel);
    }
}
