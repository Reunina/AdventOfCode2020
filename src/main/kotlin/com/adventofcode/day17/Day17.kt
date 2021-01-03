package com.adventofcode.day17

import java.io.File
 
fun main() {
    val input: List<String> =
            File("src/main/resources/day17/puzzle_input.txt")
                    .readLines()
    val cubeSimulator=CubesSimulator(input)
    println("Day 17 part 01, After the sixth cycle there are "+cubeSimulator.simulateCycles(6)+" active cubes")
    println("Day 17 part 02, After the sixth cycle there are "+cubeSimulator.simulateCyclesOn4thDim(6)+" active cubes")
}
 
