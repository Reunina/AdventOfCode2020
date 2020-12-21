package com.adventofcode.day08

import java.io.File

fun main() {
     val input: List<Instruction> =
             File("src/main/resources/day08/puzzle_input.txt")
                     .readLines()
    val accumulator = Console(input).boot()
    println("Day 08 - Part 01 , Accumulator juste before infinite loop= ${accumulator.accumulator}")
}
 
