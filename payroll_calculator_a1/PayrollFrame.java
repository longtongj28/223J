import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.Timer;

public class PayrollFrame extends JFrame {
    private JLabel hoursWorkedLabel;
    private JTextField hoursWorkedField;

    private JLabel wageLabel;
    private JTextField wageField;

    private JLabel regularPayLabel;
    private JTextField regularPayField;

    private JLabel overtimePayLabel;
    private JTextField overtimePayField;

    private JLabel grossPayLabel;
    private JTextField grossPayField;

    private JButton calculatePayButton;
    private JButton exitButton;

    private int frameWidth = 500;
    private int frameHeight = 600;

    private int panelOneSize = 125;
    private int panelTwoSize = 150;
    private int panelThreeSize = 200;
    private int panelFourSize = 125;

    // First panel with name of company
    public JPanel PanelOne() {
        JPanel panel = new JPanel();
        panel.setForeground(Color.WHITE);
        panel.setBackground(new Color(51, 102, 0));
        panel.setBounds(0, 0, frameWidth, panelOneSize);
        return panel;
    }

    // Input employee information panel
    public JPanel PanelTwo() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 64, 255));
        panel.setBounds(0, panelOneSize, frameWidth, panelTwoSize);
        return panel;
    }

    // panel that displays calculated information
    public JPanel PanelThree() {
        JPanel panel = new JPanel();
        int location = panelOneSize + panelTwoSize;
        panel.setBackground(new Color(34,131,60));
        panel.setBounds(0, location, frameWidth, panelThreeSize);
        return panel;
    }

    // panel with the command buttons.
    public JPanel PanelFour() {
        JPanel panel = new JPanel();
        int location = panelOneSize + panelTwoSize + panelThreeSize;
        panel.setBounds(0, location, frameWidth, panelFourSize);
        panel.setBackground(new Color(0, 0, 0));
        return panel;
    }

    // Overarching frame that will contain all of the panels.
    public PayrollFrame() {
        super("Payroll Frame");
        add(PanelOne());
        add(PanelTwo());
        add(PanelThree());
        add(PanelFour());
    }

    // //Hours Worked Section
    // hoursWorkedLabel = new JLabel("Hours Worked:");
    // hoursWorkedLabel.setForeground(Color.CYAN);
    // add(hoursWorkedLabel);
    // hoursWorkedField = new JTextField(20);
    // add(hoursWorkedField);

    // //Wage Section
    // wageLabel = new JLabel("Wage:");
    // add(wageLabel);
    // wageField = new JTextField(20);
    // add(wageField);

    // //Regular section
    // regularPayLabel = new JLabel("Regular Pay: ");
    // add(regularPayLabel);
    // regularPayField = new JTextField(20);
    // add(regularPayField);

    // //Overtime section
    // overtimePayLabel = new JLabel("Overtime Pay: ");
    // add(overtimePayLabel);
    // overtimePayField = new JTextField(20);
    // add(overtimePayField);

    // //Gross section
    // grossPayLabel = new JLabel("Gross Pay: ");
    // add(grossPayLabel);
    // grossPayField = new JTextField(20);
    // add(grossPayField);

    // //Button group
    // calculatePayButton = new JButton("Calculate Pay");
    // add(calculatePayButton);
    // exitButton = new JButton("Exit");
    // add(exitButton);
    // JLabel instructions = new JLabel("Only use integer inputs.");
    // add(instructions);

}
