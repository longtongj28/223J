
public class PayrollCalculations 
{
    public static double regularPay(double hours, double payRate) {
        if (hours <= 0) return 0;
        else if (hours <= 40) return hours * payRate;
        else return 40 * payRate;
    }

    public static double weeklyOvertimePay(double hours, double payRate) {
        if (hours <= 40) return 0;
        else return (hours-40)*0.5d*payRate;
    }

    public static double grossPay(double hours, double payRate) {
       return regularPay(hours, payRate) + weeklyOvertimePay(hours,payRate);
    }
}