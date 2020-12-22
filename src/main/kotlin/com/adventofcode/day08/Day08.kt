package com.adventofcode.day08

import java.io.File

fun main() {
     val input: List<Instruction> =
             File("src/main/resources/day08/puzzle_input.txt")
                     .readLines()
    val console = Console(input)
    println("Day 08 - Part 01 , Accumulator just before infinite loop = ${console.boot().accumulator}")
    println("Day 08 - Part 02 , Accumulator when program terminates   = ${console.smartBoot().accumulator}")
}
 
