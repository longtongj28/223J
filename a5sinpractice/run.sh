#!/bin/bash

#Program Name: Cat and Mouse
#Author: Johnson Tong
#Email: jt28@csu.fullerton.edu
#File Name: run.sh
#Execution: sh run.sh

echo remove old byte-code files
rm *.class

echo list source files
ls -l *.java

echo compile BouncingFrame.java
javac BouncingFrame.java

echo compile BouncingPanel.java
javac BouncingPanel.java

echo compile BouncingDriver.java
javac BouncingDriver.java

echo Execute program
java BouncingDriver

echo finished

echo clean up
rm *.class