package com.adventofcode.day05

import java.io.File

fun main() {
    val input: List<String> =
            File("src/main/resources/day05/puzzle_input.txt")
                    .readLines()
    val seatDecoder = SeatDecoder.readFromInput(input)
    val seatWithHigherID = seatDecoder.findSeatWithHigherIdFrom()
    val missingSeats = seatDecoder.findMissingSeat()
    println("Day 05, part 01 seat with higher ID: $seatWithHigherID")
    println("Day 05, part 02       missing seat : $missingSeats")

}
 
