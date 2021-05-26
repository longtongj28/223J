# Johnson Tong
# CPSC223J Test 1
# The program is run through this script file, run.sh
# use sh run.sh on a bash terminal to run the program.

echo remove old byte-code files
rm *.class

echo list source files
ls -l *.java

echo compile TriangleCalculations.java
javac TriangleCalculations.java

echo compile TriangleFrame.java
javac TriangleFrame.java

echo compile TriangleDriver.java
javac TriangleDriver.java

echo Execute program
java TriangleDriver

echo finished
rm *.class
