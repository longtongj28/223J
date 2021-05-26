import javax.swing.JFrame;

public class BouncingDriver {
    public static void main(String[] args) {
        BouncingFrame frame = new BouncingFrame();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
