dayindex=$1

theDay="day$dayindex"

firstLetter=$(echo "${theDay::1}" | tr "[:lower:]" "[:upper:]" )
restOfTheWord=$(echo "${theDay:1}" | tr "[:upper:]" "[:lower:]")

theDayCapitalized="$firstLetter$restOfTheWord"

mkdir -p "./src/main/resources/${theDay}"
mkdir -p "./src/main/kotlin/com/adventofcode/${theDay}"
mkdir -p "./src/main/resources/${theDay}"
mkdir -p "./src/test/kotlin/com/adventofcode/${theDay}"

resourceFile="src/main/resources/${theDay}/puzzle_input.txt"
javaFile="./src/main/kotlin/com/adventofcode/${theDay}/${theDayCapitalized}.kt"

touch $resourceFile
touch $javaFile

echo "package com.adventofcode.${theDay}"  > ${javaFile}
echo "" >> ${javaFile}
echo "import java.io.File" >> ${javaFile}
echo " " >> ${javaFile}
echo "fun main() {" >> ${javaFile}
echo "    val input: List<Int> =" >> ${javaFile}
echo "            File(\"${resourceFile}\")" >> ${javaFile}
echo "                    .readLines()" >> ${javaFile}
echo "                    .map(String::toInt)" >> ${javaFile}
echo "    println(\"No code yet for this Day ! \")"  >> ${javaFile}
echo "}" >> ${javaFile}
echo " " >> ${javaFile}
