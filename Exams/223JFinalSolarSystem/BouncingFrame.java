import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Color;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class BouncingFrame extends JFrame {
    // main_panel to prevent inclusion of window borders
    // all panels will be added to this one.
    private JPanel mainPanel;
    private int frameHeight = 1026;
    private int frameWidth = 1824;
    private Dimension frameDimensions;

    // panels of the interface
    private JPanel aboutPanel;
    private Dimension aboutDimensions;
    private int aboutWidth = frameWidth;
    private int aboutHeight = 175;
    private JLabel instructions1;
    private JLabel instructions2;
    private JLabel announcements;
    private String recommend = "Try changing the colors of the Earth and Sun!";

    private BouncingPanel graphicsPanel;
    private Dimension graphicsDimensions;
    private int graphicsHeight = 650;

    // This panel will contain the button row panel and input row panel
    private JPanel leftHalfPanel;
    // button panel stuff
    private JPanel buttonsPanel;
    private Dimension buttonsPanelDimensions;
    private int buttonsPanelHeight = frameHeight - aboutHeight - graphicsHeight;

    private JPanel inputRowPanel;
    private JLabel earthSpeedLabel;
    private JTextField earthSpeedField;
    private JLabel speedLabel;
    private JTextField speedField;
    private JLabel directionLabel;
    private JTextField directionField;

    private String[] colorStrings = { "yellow", "blue", "cyan", "gray", "green", "magenta", "pink", "red", "white",
            "orange" };
    private JPanel colorPanel;
    private JPanel colorPanelBottom;
    private JLabel colorLabel;
    private JLabel mouseLabel;
    private JLabel catLabel;
    private JComboBox colorChoiceOne;
    private JComboBox colorChoiceTwo;

    private JPanel buttonRowPanel;
    private Dimension buttonDimensions = new Dimension(165, 50);
    private JButton clearButton;
    private JButton quitButton;
    private JButton startButton;

    // ball location panel info
    private JPanel ballLocPanel;
    private int ballLocPanelWidth = 250;
    private int ballLocPanelHeight = 175;
    private JLabel ballLocLabel;

    private JPanel ballXPanel;
    private JLabel ballXLabel;
    private JTextField ballXField;

    private JPanel ballYPanel;
    private JLabel ballYLabel;
    private JTextField ballYField;
    // end button panel

    // Borders
    private TitledBorder titleBorder;
    private Border borderLine;
    // layouts
    private FlowLayout flowLayout;
    // Fonts
    private Font titleFont;
    private Font instructionFont;
    private Font ballLocationBoxFont;
    // Handlers
    private ButtonHandler myButtonHandler;
    private ClockHandlerClass clockHandler;
    // Timers
    private Timer closeTimer;
    private int timeToClose = 1200;
    private Timer colorTimer;
    private int colorChangeInterval = 500;
    // Decimal Format
    private DecimalFormat df = new DecimalFormat("0.000");
    // information needed for animation
    private boolean active = false; // is the animation running?
    private double ball_speed_pix_per_second;
    private double ball_speed_pix_per_tic;
    
    private Timer refreshClock;
    private Timer motionClock;
    private Timer catMotionClock;
    private double refresh_clock_rate = 60.0; // Hz. This is often called frames per second.
    private int refresh_clock_delay_interval; // This value will be computedeach second.
    private final double millisecondpersecond = 1000.0;
    
    // mouse animation information
    private final double motion_clock_rate = 99.9; // Hz. How many times the moving object will update its position in
    private int motion_clock_delay_interval; // This number will be computed by the constructor
    private double dx; // Unit of incremental change in coordinates for mouse.
    private double dy; // Unit of incremental change in coordinates for mouse.
    private double angle; // angle in degrees for the mouse direction.
    
    // cat animation information
    private int cat_motion_clock_delay_interval;
    private double cat_dx;
    private double cat_dy;
    private double cat_speed_pix_per_second;
    private double cat_speed_pix_per_tic;
    private double t = 0.0;
    private double dt;
    private double distance_between_sun_and_earth;
    private double distance_between_sun_and_mars;
    
    // position of sun
    private double currentPositionX = (double) frameWidth / 2;
    private double currentPositionY = (double) graphicsHeight / 2;
    // earth
    private double currentCatPositionX = (double) frameWidth/2;
    private double currentCatPositionY = (double) 200;
    //mars
    private double currentMarsPositionX = (double) frameWidth / 2;
    private double currentMarsPositionY = (double) 100;
    //moon
    private double currentMoonPositionX = (double) frameWidth / 2;
    private double currentMoonPositionY = (double) 175;
    // end info for animation

    public BouncingFrame() {
        // frame information
        super("Bouncing Frame");
        flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        setTitle("Cat and Mouse");
        setResizable(false);
        pack();
        // Fonts
        titleFont = new Font("Liberation Sans", Font.BOLD, 35);
        instructionFont = new Font("Liberation Sans", Font.ITALIC, 30);

        // add the main panel, the dimensions of this will not include the window border
        mainPanel = new JPanel();
        frameDimensions = new Dimension(frameWidth, frameHeight);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(frameDimensions);

        // about panel
        aboutPanel = new JPanel();
        aboutDimensions = new Dimension(aboutWidth, aboutHeight);
        aboutPanel.setPreferredSize(aboutDimensions);
        aboutPanel.setBackground(new Color(194, 175, 240));
        aboutPanel.setLayout(new GridLayout(3, 1));

        Color borderColor = new Color(100, 17, 63);
        borderLine = BorderFactory.createLineBorder(borderColor, 10);
        titleBorder = BorderFactory.createTitledBorder(borderLine, "Solar System for 3rd Graders - Johnson Tong");
        titleBorder.setTitleFont(titleFont);
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        aboutPanel.setBorder(titleBorder);

        instructions1 = new JLabel("~Input the Earth's speed in px/sec~");
        instructions1.setFont(instructionFont);
        instructions1.setHorizontalAlignment(JLabel.CENTER);
        instructions2 = new JLabel("First click \"clear\" to initialize, then input using numbers only!");
        instructions2.setHorizontalAlignment(JLabel.CENTER);
        instructions2.setFont(instructionFont);

        announcements = new JLabel(recommend);
        announcements.setFont(instructionFont);
        announcements.setHorizontalAlignment(JLabel.CENTER);

        aboutPanel.add(instructions1);
        aboutPanel.add(instructions2);
        aboutPanel.add(announcements);

        // graphics panel
        graphicsPanel = new BouncingPanel();
        graphicsPanel.setBackground(Color.black);
        graphicsDimensions = new Dimension(frameWidth, graphicsHeight);
        graphicsPanel.setPreferredSize(graphicsDimensions);

        // buttons panel
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(241, 232, 184));
        buttonsPanelDimensions = new Dimension(frameWidth, buttonsPanelHeight);
        buttonsPanel.setPreferredSize(buttonsPanelDimensions);
        // border
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
        buttonsPanel.setBorder(compound);
        // ball location info
        ballLocPanel = new JPanel();
        Dimension ballLocPanelSize = new Dimension(ballLocPanelWidth, ballLocPanelHeight);
        ballLocPanel.setPreferredSize(ballLocPanelSize);
        Border ballLocBorder = BorderFactory.createLineBorder(Color.black, 5);
        ballLocPanel.setBorder(ballLocBorder);

        ballLocationBoxFont = new Font("Liberation Sans", Font.BOLD, 18);
        ballLocLabel = new JLabel("Earth's Java Coordinates:");
        ballLocLabel.setFont(ballLocationBoxFont);

        ballXPanel = new JPanel();
        ballXField = new JTextField(7);
        ballXLabel = new JLabel("X: ");
        ballXLabel.setFont(ballLocationBoxFont);
        ballXField.setHorizontalAlignment(JTextField.CENTER);
        ballXField.setFont(ballLocationBoxFont);
        ballXField.setEditable(false);
        ballXPanel.add(ballXLabel);
        ballXPanel.add(ballXField);

        ballYPanel = new JPanel();
        ballYField = new JTextField(7);
        ballYLabel = new JLabel("Y: ");
        ballYLabel.setFont(ballLocationBoxFont);
        ballYField.setHorizontalAlignment(JTextField.CENTER);
        ballYField.setFont(ballLocationBoxFont);
        ballYField.setEditable(false);
        ballYPanel.add(ballYLabel);
        ballYPanel.add(ballYField);

        ballLocPanel.setLayout(new GridBagLayout());
        GridBagConstraints ballPanelGBC = new GridBagConstraints();
        ballPanelGBC.weightx = 1;
        ballPanelGBC.weighty = 1;
        ballPanelGBC.gridx = 0;
        ballPanelGBC.gridy = 0;
        ballLocPanel.add(ballLocLabel, ballPanelGBC);
        ballPanelGBC.gridy++;
        ballLocPanel.add(ballXPanel, ballPanelGBC);
        ballPanelGBC.gridy++;
        ballLocPanel.add(ballYPanel, ballPanelGBC);

        // end ball location panel info( in the lower third panel)

        // panel to house the input row and the button row
        leftHalfPanel = new JPanel();
        leftHalfPanel.setOpaque(false);
        leftHalfPanel.setLayout(new BorderLayout());
        // input row on the third panel
        inputRowPanel = new JPanel();
        inputRowPanel.setOpaque(false);
        earthSpeedLabel = new JLabel("Earth's Speed (px/s)");
        earthSpeedLabel.setFont(ballLocationBoxFont);
        earthSpeedField = new JTextField(10);
        earthSpeedField.setText("100.0");
        earthSpeedField.setHorizontalAlignment(JTextField.CENTER);
        earthSpeedField.setFont(ballLocationBoxFont);

        // color panel stuff here
        colorPanel = new JPanel();
        colorPanel.setLayout(new BorderLayout());
        colorLabel = new JLabel("Color Choices: ");
        colorLabel.setHorizontalAlignment(JLabel.CENTER);
        colorLabel.setFont(ballLocationBoxFont);

        colorPanelBottom = new JPanel();
        mouseLabel = new JLabel("Sun Color:");
        mouseLabel.setFont(ballLocationBoxFont);
        colorChoiceOne = new JComboBox<>(colorStrings);
        colorChoiceOne.setSelectedIndex(0);
        colorChoiceOne.setFont(ballLocationBoxFont);

        catLabel = new JLabel("Earth Color:");
        catLabel.setFont(ballLocationBoxFont);
        colorChoiceTwo = new JComboBox<>(colorStrings);
        colorChoiceTwo.setSelectedIndex(1);
        colorChoiceTwo.setFont(ballLocationBoxFont);

        colorPanelBottom.add(mouseLabel);
        colorPanelBottom.add(colorChoiceOne);
        colorPanelBottom.add(catLabel);
        colorPanelBottom.add(colorChoiceTwo);

        colorPanel.add(colorLabel, BorderLayout.NORTH);
        colorPanel.add(colorPanelBottom, BorderLayout.SOUTH);
        // end color panel stuff

        inputRowPanel.setLayout(new GridBagLayout());
        GridBagConstraints inputRowGBC = new GridBagConstraints();
        inputRowGBC.insets = new Insets(10, 30, 10, 30);
        inputRowGBC.gridx = 0;
        inputRowGBC.gridy = 0;
        inputRowPanel.add(earthSpeedLabel, inputRowGBC);
        inputRowGBC.gridx++;
        inputRowPanel.add(earthSpeedField, inputRowGBC);
        leftHalfPanel.add(inputRowPanel, BorderLayout.NORTH);
        // end input row panel

        // button row panel
        buttonRowPanel = new JPanel();
        buttonRowPanel.setOpaque(false);
        clearButton = new JButton("Clear");
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        clearButton.setFont(ballLocationBoxFont);
        startButton.setFont(ballLocationBoxFont);
        quitButton.setFont(ballLocationBoxFont);

        clearButton.setPreferredSize(buttonDimensions);
        startButton.setPreferredSize(buttonDimensions);
        quitButton.setPreferredSize(buttonDimensions);

        buttonRowPanel.setLayout(new GridBagLayout());
        GridBagConstraints buttonRowGBC = new GridBagConstraints();
        buttonRowGBC.insets = new Insets(20, 35, 20, 35);
        buttonRowGBC.gridx = 0;
        buttonRowGBC.gridy = 0;
        buttonRowPanel.add(clearButton, buttonRowGBC);
        buttonRowGBC.gridx++;
        buttonRowPanel.add(startButton, buttonRowGBC);
        buttonRowGBC.gridx++;
        buttonRowPanel.add(quitButton, buttonRowGBC);
        leftHalfPanel.add(buttonRowPanel, BorderLayout.SOUTH);

        leftHalfPanel.setBorder(compound);
        buttonsPanel.add(leftHalfPanel);
        buttonsPanel.add(ballLocPanel);
        buttonsPanel.add(colorPanel);

        mainPanel.add(aboutPanel, BorderLayout.NORTH);
        mainPanel.add(graphicsPanel);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // button handling/input handling
        myButtonHandler = new ButtonHandler();
        quitButton.addActionListener(myButtonHandler);
        startButton.addActionListener(myButtonHandler);
        clearButton.addActionListener(myButtonHandler);
        colorChoiceOne.addActionListener(myButtonHandler);
        colorChoiceTwo.addActionListener(myButtonHandler);

        closeTimer = new Timer(timeToClose, myButtonHandler);

        // create handler for all clocks
        clockHandler = new ClockHandlerClass();
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == quitButton) {
                startButton.setEnabled(false);
                quitButton.setEnabled(false);
                clearButton.setEnabled(false);
                colorChoiceOne.setEnabled(false);
                colorChoiceTwo.setEnabled(false);

                earthSpeedField.setEditable(false);
                if (active) {
                    activateClocks();
                }
                announcements.setText("Bye! I hope you enjoyed the animation!");
                announcements.setFont(titleFont);
                announcements.setForeground(Color.black);

                closeTimer.start();
            } else if (event.getSource() == closeTimer) {
                System.exit(0);
            } else if (event.getSource() == clearButton) {
                startButton.setEnabled(true);
                graphicsPanel.reset(graphicsHeight, frameWidth);
                startButton.setText("Start");
                announcements.setText(recommend);
                instructions2.setForeground(Color.black);

                announcements.setForeground(Color.black);
                earthSpeedField.setText("");

                earthSpeedField.setEditable(true);

                currentPositionX = (double) frameWidth / 2;
                currentPositionY = (double) graphicsHeight / 2;

                if (active) {
                    activateClocks();
                }
                active = false;
            } else if (event.getSource() == startButton) {
                if (startButton.getText().equals("Start")) {
                    if (!graphicsPanel.getInitialize()) { // ball hasn't been initialized
                        instructions2.setForeground(Color.red);
                    } else { // ball has already appeared on the center of the graphics panel
                        announcements.setText("");
                        boolean valid = checkNum(earthSpeedField.getText());
                        if (!valid) {
                            announcements.setForeground(Color.red);
                            announcements.setText("Please enter valid numbers!");
                        } else { // this is a successful start, after all conditionas are met.
                            setInitialAnimationVals();
                            announcements.setText(recommend);
                            announcements.setForeground(Color.black);
                            earthSpeedField.setEditable(false);
                            startButton.setText("Pause");
                            activateClocks();
                        }
                    }
                } else if (startButton.getText().equals("Pause")) {
                    startButton.setText("Resume");
                    activateClocks();
                } else if (startButton.getText().equals("Resume")) {
                    startButton.setText("Pause");
                    activateClocks();
                }
            } else if (event.getSource() == colorChoiceOne) {
                String chosenColor = colorChoiceOne.getSelectedItem().toString();
                Color newColor = convertStrToColor(chosenColor);
                graphicsPanel.setColorOne(newColor);
            } else if (event.getSource() == colorChoiceTwo) {
                String chosenColor = colorChoiceTwo.getSelectedItem().toString();
                Color newColor = convertStrToColor(chosenColor);
                graphicsPanel.setColorTwo(newColor);
            }
        }
    }

    private class ClockHandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == refreshClock) {
                graphicsPanel.repaint();
            } else if (event.getSource() == motionClock) {
                if (active) {
                    
                }
            } else if (event.getSource() == catMotionClock) {
                graphicsPanel.moveEarth(dt,distance_between_sun_and_earth);
                graphicsPanel.moveMars(dt/2,distance_between_sun_and_mars);
                graphicsPanel.moveMoon(dt/28);

                ballXField.setText(df.format(graphicsPanel.getEarthLocX()));
                ballYField.setText(df.format(graphicsPanel.getEarthLocY()));
            }
        }
    }

    private void activateClocks() {
        if (active) { // started, want to stop it now.
            refreshClock.stop();
            catMotionClock.stop();
        } else { // stopped, want to start it now.
            refreshClock.start();
            catMotionClock.start();
        }
        active = !active;
    }

    private void setInitialAnimationVals() {
        // actually the earth speeds
        cat_speed_pix_per_second = Double.parseDouble(earthSpeedField.getText());
        cat_speed_pix_per_tic = cat_speed_pix_per_second / motion_clock_rate;
        distance_between_sun_and_earth = (double)(frameWidth - 800) - ((double)frameWidth/2);
        distance_between_sun_and_mars = (double)(frameWidth - 650) - ((double)frameWidth/2);
        dt = -1 *cat_speed_pix_per_tic/distance_between_sun_and_earth;
        // mouse animation information. angle in radians
        // angle = Math.toRadians(Double.parseDouble(directionField.getText()));
        // dx = Math.cos(angle) * ball_speed_pix_per_tic;
        // dy = -1 * Math.sin(angle) * ball_speed_pix_per_tic;
        // graphicsPanel.setDifferentials(dx, dy);
        // // cat animation information.
        // cat_dx = ball_speed_pix_per_tic * (currentPositionX - currentCatPositionX) / distance_between;
        // cat_dy = ball_speed_pix_per_tic * (currentPositionY - currentCatPositionY) / distance_between;
        // graphicsPanel.setCatDifferentials(cat_dx, cat_dy);

        graphicsPanel.reset(graphicsHeight, frameWidth);
        // // Create a refresh clock
        refresh_clock_delay_interval = (int) Math.round(millisecondpersecond / refresh_clock_rate);
        refreshClock = new Timer(refresh_clock_delay_interval, clockHandler);
        // // Create a motion clock for the mouse
        // motion_clock_delay_interval = (int) Math.round(millisecondpersecond / motion_clock_rate);
        // motionClock = new Timer(motion_clock_delay_interval, clockHandler);
        // // Create a motion clock for the Cat
        cat_motion_clock_delay_interval = (int) Math.round(millisecondpersecond / motion_clock_rate);
        catMotionClock = new Timer(cat_motion_clock_delay_interval, clockHandler);
        // // Create clock for color alternation.
        // colorTimer = new Timer(colorChangeInterval, clockHandler);
    }

    private static boolean checkNum(String testString) { // validate input as numbers
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

    private static Color convertStrToColor(String colorString) {
        if (colorString.equals("yellow")) {
            return Color.yellow;
        } else if (colorString.equals("blue")) {
            return Color.blue;
        } else if (colorString.equals("cyan")) {
            return Color.cyan;
        } else if (colorString.equals("gray")) {
            return Color.gray;
        } else if (colorString.equals("green")) {
            return Color.green;
        } else if (colorString.equals("magenta")) {
            return Color.magenta;
        } else if (colorString.equals("pink")) {
            return Color.pink;
        } else if (colorString.equals("red")) {
            return Color.red;
        } else if (colorString.equals("orange")) {
            return Color.orange;
        } else {
            return Color.white;
        }
    }
}