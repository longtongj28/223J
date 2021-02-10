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



public class PayrollFrame extends JFrame {

    private JPanel companyNamePanel;

    private JLabel companyNameLabel;
    private JLabel companyNameLabel2;
    private JLabel dollarSigns;
    //////////////////////////////////
    private JPanel employeeInfoPanel;

    private JLabel employeeNameLabel;
    private JTextField employeeNameField;

    private JLabel hoursWorkedLabel;
    private JTextField hoursWorkedField;

    private JLabel hourlyRateLabel;
    private JTextField hourlyRateField;
    //////////////////////////////////

    private JPanel calculatedInfoPanel;
    private JLabel nameOfEmployeeLabel;
    private JLabel regularPayLabel;
    private JLabel overtimePayLabel;
    private JLabel grossPayLabel;
    private JLabel employeeNameValue;
    private JLabel regularPayValue;
    private JLabel overtimePayValue;
    private JLabel grossPayValue;

    //////////////////////////////////
    private JPanel buttonsPanel;
    private JButton clearButton;
    private JButton computeButton;
    private JButton quitButton;

    ////////////////////////////////
    // layouts
    private FlowLayout flowLayout;

    //Frame dimensions
    private int frameHeight = 625;
    private int frameWidth = 500;


    private PayrollCalculations payrollCalculator;
    private Timer closeTimer;
    private int timeToClose = 3000;
    // Overarching frame that will contain all of the panels.
    public PayrollFrame() {
        super("Payroll Frame");
        flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        setTitle("Johnson's Wholesale");
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setLayout(flowLayout);
        setLocationRelativeTo(null);
        Font font = new Font("Helvetica Neue", Font.BOLD, 25);
        Font font2 = new Font("Helvetica Neue", Font.PLAIN, 16);

        // company name and information panel
        companyNamePanel = new JPanel();
        Dimension panelOneSize = new Dimension(frameWidth, 125);
        companyNamePanel.setPreferredSize(panelOneSize);
        companyNamePanel.setBackground(new Color(56, 119, 128));
        companyNamePanel.setLayout(new GridLayout(3, 1));

        companyNameLabel = new JLabel("<html><u>~*Johnson's Wholesale*~<u></html>");
        companyNameLabel.setHorizontalAlignment(JLabel.CENTER);
        companyNameLabel.setForeground(Color.white);
        companyNameLabel.setFont(font);
        companyNamePanel.add(companyNameLabel);

        companyNameLabel2 = new JLabel("<html><u><i>~Employee Payroll System~<i><u></html>");
        companyNameLabel2.setHorizontalAlignment(JLabel.CENTER);
        companyNameLabel2.setForeground(Color.white);
        companyNameLabel2.setFont(font);
        companyNamePanel.add(companyNameLabel2);

        dollarSigns = new JLabel("<html><u><i>~~~~~Decimal inputs only~~~~~<i><u></html>");
        dollarSigns.setHorizontalAlignment(JLabel.CENTER);
        dollarSigns.setForeground(new Color(192,192,192));
        dollarSigns.setFont(font);
        companyNamePanel.add(dollarSigns);

        add(companyNamePanel);

        // employee info panel
        employeeInfoPanel = new JPanel();
        employeeInfoPanel.setLayout(new GridBagLayout());
        Dimension panelTwoSize = new Dimension(frameWidth, 175);
        employeeInfoPanel.setBackground(new Color(210, 204, 161));
        employeeInfoPanel.setPreferredSize(panelTwoSize);

        // Hours Worked Section
        employeeNameLabel = new JLabel("<html><u><i>Employee Name: <i><u></html>");
        employeeNameLabel.setFont(font2);
        employeeNameField = new JTextField(20);
        employeeNameField.setHorizontalAlignment(JTextField.CENTER);
        employeeNameField.setFont(font2);

        hoursWorkedLabel = new JLabel("<html><u><i>Hours Worked: <i><u></html>");
        hoursWorkedLabel.setFont(font2);
        hoursWorkedField = new JTextField(20);
        hoursWorkedField.setHorizontalAlignment(JTextField.CENTER);
        hoursWorkedField.setFont(font2);

        hourlyRateLabel = new JLabel("<html><u><i>Hourly Pay Rate: <i><u>$</html>");
        hourlyRateLabel.setFont(font2);
        hourlyRateField = new JTextField(20);
        hourlyRateField.setHorizontalAlignment(JTextField.CENTER);
        hourlyRateField.setFont(font2);

        GridBagConstraints location = new GridBagConstraints();
        location.insets = new Insets(9, 9, 9, 9);
        location.gridx = 0;
        location.gridy = 0;
        employeeInfoPanel.add(employeeNameLabel, location);
        location.gridx = 1;
        location.gridy = 0;
        employeeInfoPanel.add(employeeNameField, location);
        location.gridx = 0;
        location.gridy = 1;
        employeeInfoPanel.add(hoursWorkedLabel, location);
        location.gridx = 1;
        location.gridy = 1;
        employeeInfoPanel.add(hoursWorkedField, location);
        location.gridx = 0;
        location.gridy = 2;
        employeeInfoPanel.add(hourlyRateLabel, location);
        location.gridx = 1;
        location.gridy = 2;
        employeeInfoPanel.add(hourlyRateField, location);
        add(employeeInfoPanel);

        // Calculate Information Panel
        calculatedInfoPanel = new JPanel();
        Dimension panelThreeSize = new Dimension(frameWidth, 175);
        calculatedInfoPanel.setBackground(new Color(219, 212, 211));
        calculatedInfoPanel.setPreferredSize(panelThreeSize);
        calculatedInfoPanel.setLayout(new GridLayout(4, 2));
        int tab = 50;
        Border leftTab = BorderFactory.createEmptyBorder(0, tab, 0, 0);        
        nameOfEmployeeLabel = new JLabel("<html><u><i>Employee Name: <i><u></html>");

        nameOfEmployeeLabel.setBorder(leftTab);
        nameOfEmployeeLabel.setFont(font2);
        employeeNameValue = new JLabel(" ");
        employeeNameValue.setHorizontalAlignment(JLabel.CENTER);
        employeeNameValue.setFont(font2);

        regularPayLabel = new JLabel("<html><u><i>Regular Pay: <i><u></html>");
        regularPayLabel.setBorder(leftTab);
        regularPayLabel.setFont(font2);
        regularPayValue = new JLabel(" ");
        regularPayValue.setHorizontalAlignment(JLabel.CENTER);
        regularPayValue.setFont(font2);

        overtimePayLabel = new JLabel("<html><u><i>Overtime Pay (1.5x): <i><u></html>");
        overtimePayLabel.setBorder(leftTab);
        overtimePayLabel.setFont(font2);
        overtimePayValue = new JLabel(" ");
        overtimePayValue.setHorizontalAlignment(JLabel.CENTER);
        overtimePayValue.setFont(font2);

        grossPayLabel = new JLabel("<html><u><i>Gross Pay: <i><u></html>");
        grossPayLabel.setBorder(leftTab);
        grossPayLabel.setFont(font2);
        grossPayValue = new JLabel(" ");
        grossPayValue.setHorizontalAlignment(JLabel.CENTER);
        grossPayValue.setFont(font2);

        calculatedInfoPanel.add(nameOfEmployeeLabel);
        calculatedInfoPanel.add(employeeNameValue);
        calculatedInfoPanel.add(regularPayLabel);
        calculatedInfoPanel.add(regularPayValue);
        calculatedInfoPanel.add(overtimePayLabel);
        calculatedInfoPanel.add(overtimePayValue);
        calculatedInfoPanel.add(grossPayLabel);
        calculatedInfoPanel.add(grossPayValue);

        add(calculatedInfoPanel);

        // Buttons panel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(new Color(0,52,89));
        Color buttonBackground = new Color(0,52,89);
        Dimension panelFourSize = new Dimension(frameWidth, 125);
        buttonsPanel.setPreferredSize(panelFourSize);
        Dimension buttonSize = new Dimension(100, 35);

        clearButton = new JButton("CLEAR");
        clearButton.setBorder(BorderFactory.createBevelBorder( 1, Color.gray, Color.white));
        clearButton.setPreferredSize(buttonSize);
        clearButton.setBackground(buttonBackground);
        clearButton.setForeground(Color.white);
        clearButton.setFont(font2);

        computeButton = new JButton("COMPUTE");
        computeButton.setBorder(BorderFactory.createBevelBorder( 1, Color.gray, Color.white));
        computeButton.setPreferredSize(buttonSize);
        computeButton.setBackground(buttonBackground);
        computeButton.setForeground(Color.white);
        computeButton.setFont(font2);

        quitButton = new JButton("QUIT");
        quitButton.setBorder(BorderFactory.createBevelBorder( 1, Color.gray, Color.white));
        quitButton.setPreferredSize(buttonSize);
        quitButton.setBackground(buttonBackground);
        quitButton.setForeground(Color.white);
        quitButton.setFont(font2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonsPanel.add(clearButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonsPanel.add(computeButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonsPanel.add(quitButton, gbc);
        add(buttonsPanel);

        ButtonHandler myHandler = new ButtonHandler();
        clearButton.addActionListener(myHandler);
        computeButton.addActionListener(myHandler);
        quitButton.addActionListener(myHandler);
        payrollCalculator = new PayrollCalculations();
        setLocationRelativeTo(null);
        closeTimer = new Timer(timeToClose, myHandler);
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == clearButton) {
                dollarSigns.setText("<html><u>~~~~~~~~~~=*$.$.$.$.$*=~~~~~~~~~~<u></html>");
                employeeNameField.setText("");
                hoursWorkedField.setText("");
                hourlyRateField.setText("");
                employeeNameValue.setText("");
                regularPayValue.setText("");
                overtimePayValue.setText("");
                grossPayValue.setText("");
            }
            else if (event.getSource() == quitButton) {
                String closing = "........";
                dollarSigns.setText("<html><i>Closing application....<i></html>");
                employeeNameField.setText("Goodbye! Have a great day.");
                hoursWorkedField.setText(closing);
                hourlyRateField.setText(closing);

                employeeNameValue.setText("");
                regularPayValue.setText("");
                overtimePayValue.setText("");
                grossPayValue.setText("");

                employeeNameField.setEditable(false);
                hoursWorkedField.setEditable(false);
                hourlyRateField.setEditable(false);

                quitButton.setEnabled(false);
                clearButton.setEnabled(false);
                computeButton.setEnabled(false);
                closeTimer.start();
            }
            else if (event.getSource() == closeTimer) {
                System.exit(0);
            }
            else if (event.getSource() == computeButton) {
                employeeNameValue.setText(employeeNameField.getText());
                String hourlyPay = hourlyRateField.getText().replaceAll("\\s", "");
                String hoursWorked = hoursWorkedField.getText().replaceAll("\\s","");
                boolean isValid = payrollCalculator.validPay(hourlyPay) && payrollCalculator.validPay(hoursWorked);

                dollarSigns.setText("<html><u>~~~~~~~~~~=*$.$.$.$.$*=~~~~~~~~~~<u></html>");
                DecimalFormat df = new DecimalFormat("##.00");
               
                if (!isValid || Double.parseDouble(hoursWorked)>=168d ) {
                    dollarSigns.setText("<html><i>Error: invalid fields.<i></html>");
                    regularPayValue.setText("$0.00");
                    overtimePayValue.setText("$0.00");
                    grossPayValue.setText("$0.00");
                }
                else {
                    double parsedHourlyPay = Double.parseDouble(hourlyPay);
                    double parsedHoursWorked = Double.parseDouble(hoursWorked);
                    String calculatedRegPay = df.format(payrollCalculator.regularPay(parsedHoursWorked, parsedHourlyPay));
                    String calculatedOverPay = df.format(payrollCalculator.weeklyOvertimePay(parsedHoursWorked, parsedHourlyPay));
                    String calculatedGrossPay = df.format(payrollCalculator.grossPay(parsedHoursWorked, parsedHourlyPay));

                    if (calculatedRegPay.equals(".00")) calculatedRegPay = "0.00";
                    if (calculatedOverPay.equals(".00")) calculatedOverPay = "0.00";
                    if (calculatedGrossPay.equals(".00")) calculatedGrossPay = "0.00";

                    regularPayValue.setText("$" + calculatedRegPay);
                    overtimePayValue.setText("$" + calculatedOverPay);
                    grossPayValue.setText("$" + calculatedGrossPay);
                }
            }
        }
    }
  

}
