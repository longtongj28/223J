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
    // three panels that will be used
    private JPanel programNamePanel;
    private JPanel diamondPanel;
    private JPanel buttonsPanel;

    //Frame formatting information
    private FlowLayout flowLayout;
    private int frameHeight = 650;
    private int frameWidth = 600;


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
        Font font1 = new Font("Liberation Sans", Font.BOLD, 25);
        Font font2 = new Font("Liberation Sans", Font.BOLD, 15);
        // program name panel
        programNamePanel = new JPanel();
        programNamePanel.setLayout(new GridLayout(3,1));
        programNamePanel.setPreferredSize(new Dimension(frameWidth, 75));
        programNamePanel.setBackground(new Color(251, 255, 241));

        programeNameLabel = new JLabel("Johnson's Diamond Animation");
        programeNameLabel.setHorizontalAlignment(JLabel.CENTER);
        programeNameLabel.setFont(font1);
        programNamePanel.add(programeNameLabel);

        instructions = new JLabel("Only use number inputs for the speed.");
        instructions.setHorizontalAlignment(JLabel.CENTER);
        programNamePanel.add(instructions);

        goodBye = new JLabel("");
        goodBye.setHorizontalAlignment(JLabel.CENTER);
        programNamePanel.add(goodBye);
        add(programNamePanel);

        // diamond animation panel
        diamondPanel = new DiamondPanel();
        diamondPanel.setPreferredSize(new Dimension(frameWidth, 425));
        diamondPanel.setBackground(new Color(197, 216, 109));
        add(diamondPanel);

        // buttons panel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setPreferredSize(new Dimension(frameWidth, 125));
        buttonsPanel.setBackground(new Color(249, 245, 227));

        Dimension sizeOfButton = new Dimension(85, 30);
        startButton = new JButton("Start");
        startButton.setFont(font2);
        startButton.setPreferredSize(sizeOfButton);

        speedSection = new JPanel();
        speedSection.setOpaque(true);
        speedSection.setBackground(new Color(0, 0, 0, 0));
        speedLabel = new JLabel("Speed: ");
        speedField = new JTextField(10);
        speedSection.add(speedLabel);
        speedSection.add(speedField);

        quitButton = new JButton("Quit");
        quitButton.setFont(font2);
        quitButton.setPreferredSize(sizeOfButton);
        buttonsPanel.add(quitButton);

        //managing the location of the buttons
        //first part at (0,1) coordinates.
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

        ButtonHandler myHandler = new ButtonHandler();
        quitButton.addActionListener(myHandler);
        startButton.addActionListener(myHandler);
        closeTimer = new Timer(timeToClose, myHandler);
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == quitButton) {
                startButton.setEnabled(false);
                quitButton.setEnabled(false);
                speedField.setEditable(false);
                goodBye.setText("Thanks for using!");
                closeTimer.start();
            }
            else if (event.getSource() == closeTimer) {
                System.exit(0);
            }
            if (event.getSource() == startButton) {
                if (!checkNum(speedField.getText())) {
                    instructions.setForeground(Color.RED);
                }
                else if (startButton.getText().equals("Pause")) {
                    startButton.setText("Start");
                }
                else {
                    startButton.setText("Pause");
                    speedField.setEditable(false);
                    instructions.setForeground(Color.BLACK);
                }
            }
        }
    }
    private static boolean checkNum(String testString) {
        boolean isValid = true;
        try {
          Double.parseDouble(testString);
        } 
        catch(NumberFormatException e) {
          isValid = false;
          return isValid;
        }
        if (testString.charAt(0) == '+' || testString.charAt(0) == '-') {
          isValid = false;
        }
        return isValid;
    }
}
