package com.adventofcode.day14

import java.io.File

fun main() {
    val input: List<String> =
            File("src/main/resources/day14/puzzle_input.txt")
                    .readLines()
    val memory = MaskPrograms(input)
            .getMemory()
    println("Day 14, part 01 , the the sum of all values left in memory after it completes is : $memory")
    val memoryV2 = MaskPrograms(input)
            .getMemoryV2()
    println("Day 14, part 02 , the the sum of all values left in memory after it completes is : $memoryV2")
}
