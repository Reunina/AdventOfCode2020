package com.adventofcode.day13

import java.io.File
 
fun main() {
    val input: List<String> =
            File("src/main/resources/day13/puzzle_input.txt")
                    .readLines()
    println("Day 13, part 01:  the ID of the earliest bus I can take to the airport multiplied by the number of minutes I'll need to wait for that bus:"
    + ShuttleBusService.readFrom(input[0], input[1]).findEarliestIdByWaitingTime() )

    println("Day 13, part 01:  the ID of the earliest bus I can take to the airport multiplied by the number of minutes I'll need to wait for that bus:"
           + ShuttleBusServicePart02(*input[1].split(",").toTypedArray()).findEarliestTimeStampThatMatchesWithTheBusDeparturePosition() )

}
 
