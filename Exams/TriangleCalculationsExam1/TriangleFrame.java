
//Johnson Tong
//CPSC 223J Test 1
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class TriangleFrame extends JFrame {

    private JPanel infoPanel;
    private JPanel inputPanel;
    private JPanel buttonsPanel;

    // info panel stuff
    private JLabel welcomeLabel;
    private JLabel authorLabel;
    private JLabel rulesLabel;

    // input panel stuff
    private JLabel inputSideOneLabel;
    private JTextField inputSideOneField;
    private JLabel inputSideTwoLabel;
    private JTextField inputSideTwoField;
    private JLabel hypotenuseLabel;
    private JTextField hypotenuseField;
    private JLabel areaLabel;
    private JTextField areaField;

    //buttons panel stuff
    private JButton clearButton;
    private JButton computeButton;
    private JButton quitButton;

    // frame dimensions
    private int frameWidth = 500;
    private int frameHeight = 600;

    private FlowLayout flowLayout;

    private TriangleCalculations triangleCalculations;
    public TriangleFrame() {
        super("Payroll Frame");
        flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        setTitle("Johnson's Triangle Program");
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setLayout(flowLayout);
        setLocationRelativeTo(null);

        // fonts
        Font font1 = new Font("Liberation Sans", Font.BOLD, 25);
        Font font2 = new Font("Liberation Sans", Font.PLAIN, 16);

        // Info panel
        infoPanel = new JPanel();
        Dimension panelOneSize = new Dimension(frameWidth, 100);
        infoPanel.setPreferredSize(panelOneSize);
        infoPanel.setBackground(new Color(56, 119, 128));
        infoPanel.setLayout(new GridLayout(3, 1));

        welcomeLabel = new JLabel("Welcome to Triangle Computations");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setFont(font1);
        infoPanel.add(welcomeLabel);

        authorLabel = new JLabel("Programmed by Johnson Tong");
        authorLabel.setHorizontalAlignment(JLabel.CENTER);
        authorLabel.setForeground(Color.white);
        authorLabel.setFont(font1);
        infoPanel.add(authorLabel);

        rulesLabel = new JLabel("All triangles are right triangles");
        rulesLabel.setHorizontalAlignment(JLabel.CENTER);
        rulesLabel.setForeground(Color.white);
        rulesLabel.setFont(font1);
        infoPanel.add(rulesLabel);

        // Input panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        Dimension panelTwoSize = new Dimension(frameWidth, 350);
        inputPanel.setBackground(new Color(210, 204, 161));
        inputPanel.setPreferredSize(panelTwoSize);

        inputSideOneLabel = new JLabel("Input Side 1");
        inputSideOneLabel.setFont(font2);
        inputSideOneField = new JTextField(20);
        inputSideOneField.setHorizontalAlignment(JTextField.CENTER);
        inputSideOneField.setFont(font2);

        inputSideTwoLabel = new JLabel("Input Side 2");
        inputSideTwoLabel.setFont(font2);
        inputSideTwoField = new JTextField(20);
        inputSideTwoField.setHorizontalAlignment(JTextField.CENTER);
        inputSideTwoField.setFont(font2);

        hypotenuseLabel = new JLabel("Hypotenuse is: ");
        hypotenuseLabel.setFont(font2);
        hypotenuseField = new JTextField(20);
        hypotenuseField.setHorizontalAlignment(JTextField.CENTER);
        hypotenuseField.setFont(font2);

        areaLabel = new JLabel("Area is: ");
        areaLabel.setFont(font2);
        areaField = new JTextField(20);
        areaField.setHorizontalAlignment(JTextField.CENTER);
        areaField.setFont(font2);

        GridBagConstraints location = new GridBagConstraints();
        location.insets = new Insets(9, 9, 9, 9);
        location.gridx = 0;
        location.gridy = 0;
        inputPanel.add(inputSideOneLabel, location);
        location.gridx = 1;
        location.gridy = 0;
        inputPanel.add(inputSideOneField, location);
        location.gridx = 0;
        location.gridy = 1;
        inputPanel.add(inputSideTwoLabel, location);
        location.gridx = 1;
        location.gridy = 1;
        inputPanel.add(inputSideTwoField, location);
        location.gridx = 0;
        location.gridy = 2;
        inputPanel.add(hypotenuseLabel, location);
        location.gridx = 1;
        location.gridy = 2;
        inputPanel.add(hypotenuseField, location);
        location.gridx = 0;
        location.gridy = 3;
        inputPanel.add(areaLabel, location);
        location.gridx = 1;
        location.gridy = 3;
        inputPanel.add(areaField, location);

        // buttons panel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(new Color(0, 52, 89));
        Dimension panelFourSize = new Dimension(frameWidth, 125);
        buttonsPanel.setPreferredSize(panelFourSize);
        Dimension buttonSize = new Dimension(100, 35);
        Color buttonBackground = new Color(0, 52, 89);

        clearButton = new JButton("CLEAR");
        clearButton.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.white));
        clearButton.setPreferredSize(buttonSize);
        clearButton.setBackground(buttonBackground);
        clearButton.setForeground(Color.white);
        clearButton.setFont(font2);

        computeButton = new JButton("COMPUTE");
        computeButton.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.white));
        computeButton.setPreferredSize(buttonSize);
        computeButton.setBackground(buttonBackground);
        computeButton.setForeground(Color.white);
        computeButton.setFont(font2);

        quitButton = new JButton("QUIT");
        quitButton.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.white));
        quitButton.setPreferredSize(buttonSize);
        quitButton.setBackground(buttonBackground);
        quitButton.setForeground(Color.white);
        quitButton.setFont(font2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonsPanel.add(computeButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonsPanel.add(clearButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonsPanel.add(quitButton, gbc);

        add(infoPanel);
        add(inputPanel);
        add(buttonsPanel);

        ButtonHandler myHandler = new ButtonHandler();
        clearButton.addActionListener(myHandler);
        computeButton.addActionListener(myHandler);
        quitButton.addActionListener(myHandler);
        triangleCalculations = new TriangleCalculations();
        setLocationRelativeTo(null);
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == clearButton) {
                inputSideOneField.setText("");
                inputSideTwoField.setText("");
                hypotenuseField.setText("");
                areaField.setText("");
            }
            else if (event.getSource() == quitButton) {
                System.exit(0);
            }
            else if (event.getSource() == computeButton) {
                String inputOne = inputSideOneField.getText();
                String inputTwo = inputSideTwoField.getText();

                if( triangleCalculations.validInput(inputOne) &&
                    triangleCalculations.validInput(inputTwo) )
                {
                    DecimalFormat df = new DecimalFormat("#.000000");
                    double inputOneD = Double.parseDouble(inputOne);
                    double inputTwoD = Double.parseDouble(inputTwo);
                    hypotenuseField.setText(df.format(triangleCalculations.calculateHypotenuse(inputOneD, inputTwoD))); 
                    areaField.setText(df.format(triangleCalculations.calculateArea(inputOneD, inputTwoD)));
                }
                else {
                    hypotenuseField.setText("ERROR");
                    areaField.setText("ERROR");
                }
            }
        }
    }
}
