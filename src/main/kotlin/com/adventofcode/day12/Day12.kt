package com.adventofcode.day12

import java.io.File

fun main() {
    val input: List<String> =
            File("src/main/resources/day12/puzzle_input.txt")
                    .readLines()
    val ferryPosition = Ferry().followInstructions(input)
    println("Day 12, Part 01: What is the Manhattan distance between that location and the ship's starting position: "
            + ferryPosition.distance())
}

