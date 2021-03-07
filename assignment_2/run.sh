#Program Name: Baseball Animation
#Author: Johnson Tong
#Email: jt28@csu.fullerton.edu
#File Name: run.sh
#Execution: sh run.sh
echo remove old byte-code files
rm *.class

echo list source files
ls -l *.java

echo compile BaseballFrame.java
javac BaseballFrame.java

echo compile TestBaseball.java
javac TestBaseball.java

echo Execute program
java TestBaseball

echo finished
