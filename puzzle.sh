#!/bin/bash

JAVA=$(which java)
if [ $? -eq 1 ]; then
  echo "Cannot find the java command in your PATH."
  exit
fi

JAR="target/Puzzle.jar"
if [ ! -f ${JAR} ]; then
  echo "Cannot find ${JAR}."

  MVN=$(which mvn)
  if [ $? -eq 1 ]; then
    echo "Cannot find Maven on your system."
    exit
  fi
  echo "Packaging ${JAR}..."
  exec mvn package -P cli-dist
fi

exec ${JAVA} -cp ${JAR} com.caseyscarborough.puzzle.Puzzle $@
