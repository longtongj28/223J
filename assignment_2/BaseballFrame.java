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
//File name: BaseballFrame.java
//Compile : javac BaseballFrame.java
//Purpose: This class defines the overall user interface of the program.
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class BaseballFrame extends JFrame {
    // three panels that will be used
    private JPanel programNamePanel;
    private DiamondPanel diamondPanel;
    private JPanel buttonsPanel;

    // Frame formatting information
    private FlowLayout flowLayout;
    private int frameHeight = 900;
    private int frameWidth = 1000;

    // program name panel elements
    private JLabel programeNameLabel;
    private JLabel instructions;
    private JLabel goodBye;

    // program diamond panel elements

    // program buttons panel elements
    private JButton startButton;
    private JPanel speedSection;
    private JLabel speedLabel;
    private JTextField speedField;
    private JButton quitButton;
    private Timer closeTimer;
    private int timeToClose = 3000;

    // speed of player, animations
    private double ball_speed_pix_per_second = 100;
    private double ball_speed_pix_per_tic;

    private Timer refreshClock;
    private Timer motionClock;
    private ButtonHandler myButtonHandler;
    private ClockHandlerClass clockHandler;
    private final double refresh_clock_rate = 120; // Hz. This is often called frames per second.
    private int refresh_clock_delay_interval; // This value will be computed
    private final double motion_clock_rate = 99.873; // Hz. How many times the moving object will update its position in
                                                     // each second.
    private int motion_clock_delay_interval; // This number will be computed by the constructor
    private final double millisecondpersecond = 1000.0;
    private double dx; // Unit of incremental change in coordinates.
    private double dy; // Unit of incremental change in coordinates.
    private double u, v; // Two temporary variables for holding xy coordinates.

    private double length_line_segment;

    //location of the four points
    private double p1x = 500;
    private double p1y = 500;

    private double p2x = 875;
    private double p2y = 270;

    private double p3x = 450;
    private double p3y = 40;

    private double p4x = 80;
    private double p4y = 250;

    private int whatpoint = 0;
    public BaseballFrame() {
        super("Baseball Frame");
        flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        setTitle("Diamond Animation");
        setSize(frameWidth, frameHeight);
        getRootPane().setBorder(BorderFactory.createEmptyBorder());
        setResizable(false);
        setLayout(flowLayout);
        setLocationRelativeTo(null);
        // some fonts
        Font font1 = new Font("Liberation Sans", Font.BOLD, 35);
        Font font2 = new Font("Liberation Sans", Font.BOLD, 25);
        // program name panel
        programNamePanel = new JPanel();
        programNamePanel.setLayout(new GridLayout(3, 1));
        programNamePanel.setPreferredSize(new Dimension(frameWidth, 150));
        programNamePanel.setBackground(new Color(251, 255, 241));

        programeNameLabel = new JLabel("Johnson's Diamond Animation");
        programeNameLabel.setHorizontalAlignment(JLabel.CENTER);
        programeNameLabel.setFont(font1);
        programNamePanel.add(programeNameLabel);

        instructions = new JLabel("Only use number inputs for the speed.");
        instructions.setFont(font2);
        instructions.setHorizontalAlignment(JLabel.CENTER);
        programNamePanel.add(instructions);

        goodBye = new JLabel("");
        goodBye.setFont(font2);
        goodBye.setHorizontalAlignment(JLabel.CENTER);
        programNamePanel.add(goodBye);
        add(programNamePanel);

        // diamond animation panel
        diamondPanel = new DiamondPanel();
        diamondPanel.setPreferredSize(new Dimension(frameWidth, 550));
        diamondPanel.setBackground(new Color(197, 216, 109));
        diamondPanel.setInitialCoord(p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y);
        diamondPanel.repaint();
        add(diamondPanel);

        // buttons panel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setPreferredSize(new Dimension(frameWidth, 150));
        buttonsPanel.setBackground(new Color(249, 245, 227));


        Dimension sizeOfButton = new Dimension(150, 50);
        startButton = new JButton("Start");
        startButton.setFont(font2);
        startButton.setPreferredSize(sizeOfButton);

        speedSection = new JPanel();
        speedSection.setOpaque(true);
        speedSection.setBackground(new Color(0, 0, 0, 0));
        speedLabel = new JLabel("Speed: ");
        speedLabel.setFont(font2);
        speedField = new JTextField(10);
        speedField.setFont(font2);
        speedField.setText("100");
        speedSection.add(speedLabel);
        speedSection.add(speedField);

        quitButton = new JButton("Quit");
        quitButton.setFont(font2);
        quitButton.setPreferredSize(sizeOfButton);
        buttonsPanel.add(quitButton);

        // managing the location of the buttons
        // first part at (0,1) coordinates.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 30, 15, 30);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonsPanel.add(startButton, gbc);
        gbc.gridx++;
        buttonsPanel.add(speedSection, gbc);
        gbc.gridx++;
        buttonsPanel.add(quitButton, gbc);

        add(buttonsPanel);

        myButtonHandler = new ButtonHandler();
        quitButton.addActionListener(myButtonHandler);
        startButton.addActionListener(myButtonHandler);
        closeTimer = new Timer(timeToClose, myButtonHandler);

        // create handler for all clocks
        clockHandler = new ClockHandlerClass();

        //Create a refresh clock
        refresh_clock_delay_interval = (int)Math.round(millisecondpersecond/refresh_clock_rate);
        refreshClock = new Timer(refresh_clock_delay_interval,clockHandler);

        //Create a motion clock
        motion_clock_delay_interval = (int)Math.round(millisecondpersecond/motion_clock_rate);
        motionClock = new Timer(motion_clock_delay_interval,clockHandler);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == quitButton) {
                startButton.setEnabled(false);
                quitButton.setEnabled(false);
                speedField.setEditable(false);
                goodBye.setText("Thanks for using!");
                speedField.setText("Goodbye!");
                motionClock.stop();
                refreshClock.stop();
                closeTimer.start();
            } else if (event.getSource() == closeTimer) {
                System.exit(0);
            } else if (event.getSource() == startButton) {
                if (!checkNum(speedField.getText())) {
                    instructions.setForeground(Color.RED);
                } else if (startButton.getText().equals("Pause")) {
                    startButton.setText("Resume");
                    refreshClock.stop();
                    motionClock.stop();
                } else if (startButton.getText().equals("Start")) {
                    // converting speed of player from px/s to px/tic
                    ball_speed_pix_per_second = Double.parseDouble(speedField.getText());
                    ball_speed_pix_per_tic = ball_speed_pix_per_second/motion_clock_rate;
                    startButton.setText("Pause");
                    speedField.setEditable(false);
                    instructions.setForeground(Color.BLACK);
                    refreshClock.start();
                    motionClock.start();
                    calculateDifferentials(p1x, p1y, p2x, p2y);
                    diamondPanel.initialize(p1x, p1y, p2x, p2y, dx, dy);
                }
                else if (startButton.getText().equals("Resume")) {
                    refreshClock.start();
                    motionClock.start();
                    startButton.setText("Pause");
                }
                else {
                    // pass
                }
            }
        }
    }

    private class ClockHandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            boolean contAnimation = false;
            if (event.getSource() == refreshClock) {
                diamondPanel.repaint();
            } else if (event.getSource() == motionClock) {
                contAnimation = diamondPanel.movePlayer();
                if (!contAnimation) {
                    if (whatpoint == 0) {
                        whatpoint++;
                    }
                    else if (whatpoint == 1) {
                        calculateDifferentials(p2x, p2y, p3x, p3y);
                        diamondPanel.initialize(p2x, p2y, p3x, p3y, dx, dy);
                        whatpoint++;
                    }
                    else if (whatpoint == 2) {
                        calculateDifferentials(p3x, p3y, p4x, p4y);
                        diamondPanel.initialize(p3x, p3y, p4x, p4y, dx, dy);
                        whatpoint++;
                    }
                    else if (whatpoint == 3) {
                        calculateDifferentials(p4x, p4y, p1x, p1y);
                        diamondPanel.initialize(p4x, p4y, p1x, p1y, dx, dy);
                        whatpoint++;
                    }
                    else if (whatpoint == 4)
                    {
                        motionClock.stop();
                        refreshClock.stop();
                        speedField.setEditable(true);
                        startButton.setText("Start");
                        whatpoint = 0;
                        diamondPanel.repaint();
                    }
                }

            }
        }
    }

    // function to make sure that input to speed text field is valid
    private static boolean checkNum(String testString) {
        boolean isValid = true;
        try {
            Double.parseDouble(testString);
        } catch (NumberFormatException e) {
            isValid = false;
            return isValid;
        }
        if (testString.charAt(0) == '+' || testString.charAt(0) == '-') {
            isValid = false;
        }
        return isValid;
    }
    private void calculateDifferentials(double xStart, double yStart, double xEnd, double yEnd) {
        length_line_segment = Math.sqrt(Math.pow((xEnd-xStart),2) + Math.pow((yEnd-yStart),2));
        dx = ball_speed_pix_per_tic*(xEnd - xStart)/length_line_segment;
        dy = ball_speed_pix_per_tic*(yEnd - yStart)/length_line_segment;
    }
}
