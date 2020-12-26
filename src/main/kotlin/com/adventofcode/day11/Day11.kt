package com.adventofcode.day11

import java.io.File

fun main() {
    val input: List<CharArray> =
            File("src/main/resources/day11/puzzle_input.txt")
                    .readLines()
                    .map { it.toCharArray() }
    println("Day 11 -- Part 01,  How many seats end up occupied: " + SeatHandler.fromInput(input).runOccupationSeatsSimulationWithAdjacentSeats())
    println("Day 11-- Part 02,  How many seats end up occupied: " + SeatHandler.fromInput(input).runOccupationSeatsSimulationWithFirstVisibleSeats())

}
 
