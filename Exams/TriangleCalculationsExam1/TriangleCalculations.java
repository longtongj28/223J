//Johnson Tong
//CPSC 223J Test 1

public class TriangleCalculations {
    public static double calculateHypotenuse(double a, double b) {
        return ( Math.sqrt( Math.pow(a,2) + Math.pow(b, 2) ) );
    }

    public static double calculateArea(double a, double b) {
        return (0.5*a*b);
    }

    public static boolean validInput(String testString) {
        boolean isValid = true;
        try {
            Double.parseDouble(testString);
        }
        catch(NumberFormatException e) {
            isValid = false;
        }
        if (testString.charAt(0) == '+' || testString.charAt(0) == '-') {
            isValid = false;
        }
        return isValid;
    }
}
