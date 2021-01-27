
echo remove old byte-code files
rm *.class

echo list source files
ls -l *.java

echo compile PayrollCalculations.java
javac PayrollCalculations.java

echo compile PayrollFrame.java
javac PayrollFrame.java

echo compile TestPayroll.java
javac TestPayroll.java

echo Execute program
java TestPayroll

echo finished