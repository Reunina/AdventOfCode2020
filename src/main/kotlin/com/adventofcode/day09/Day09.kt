package com.adventofcode.day09

import java.io.File

fun main() {
    val input: String =
            File("src/main/resources/day09/puzzle_input.txt")
                    .readText()

    val decoder = XmasCipherDecoder.readFrom(input, 25)

    println("Day 09 -- Part 01, the first number which is not the sum of two of the 25 numbers before it : ${decoder.findFirstInvalidCode()}")
}
