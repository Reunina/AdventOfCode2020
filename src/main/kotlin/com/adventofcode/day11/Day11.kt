package com.adventofcode.day11

import java.io.File
 
fun main() {
    val input: List<String> =
            File("src/main/resources/day11/puzzle_input.txt")
                    .readLines()
    println("Day 11,  How many seats end up occupied: "+ SeatHandler.fromInput(input ).runOccupationSeatsSimulation()
    )
}
 
