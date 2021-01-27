import javax.swing.JFrame;

public class TestPayroll {
    public static void main(String[] args) {
        PayrollFrame payrollFrame = new PayrollFrame();
        payrollFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        payrollFrame.setSize(500, 600);
        payrollFrame.setVisible(true);
    }
}
