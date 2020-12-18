package com.adventofcode.day05

import java.io.File
 
fun main() {
    val input: List<String> =
            File("src/main/resources/day05/puzzle_input.txt")
                    .readLines()
    val seatDecoder = SeatDecoder()
    val seatWithHigherID = seatDecoder.findSeatWithHigherIdFrom(input)
    val missingSeats = seatDecoder.findMissingSeats(input)
    println("Day 05, part 01 :$seatWithHigherID")
    println("Day 05, part 02 :$missingSeats")
}
 
