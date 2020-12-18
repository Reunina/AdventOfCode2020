package com.adventofcode.day05

import java.io.File
 
fun main() {
    val input: List<String> =
            File("src/main/resources/day05/puzzle_input.txt")
                    .readLines()
    val seatWithHigherID = SeatDecoder.findSeatWithHigherIdFrom(input)
    println("Day 05, part 1 :$seatWithHigherID")
}
 
