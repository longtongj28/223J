//****************************************************************************************************************************
//Program name: "Payroll Calculator"". The program's purpose is to allow the user to calculate payroll automatically in a
// visually appealing user interface.
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
  //Date of last update: 2021-Feb-02 
  //Status: Finished
  //Distribution: Users are invited to try to crash by the use of invalid inputs.
  //Purpose: Perform Payroll Calculations in a nice and simple UI.

//This module
  //File name: PayrollCalculations.java
  //Compile: PayrollCalculations.java
  //This module is invoked from the PayrollFrame class
  //Purpose: Calculate different payments like overtime pay, regular pay, and gross pay.

//Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**
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

    public static boolean validPay(String testString) {
        boolean isValid = true;
        for ( int i = 0; i < testString.length(); i++) {
            if (!Character.isDigit(testString.charAt(i)) && 
                testString.charAt(i) != '.') 
                { isValid = false; }
        }
        return isValid;
    }
}