package com.adventofcode.day18

import java.io.File
 
fun main() {
    val input: List<String> =
            File("src/main/resources/day18/puzzle_input.txt")
                    .readLines()
    println("Day 18 part 01 : sum of resulting values is : "+Calculator(input).evaluate())
}
 
