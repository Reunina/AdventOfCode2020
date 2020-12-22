package com.adventofcode.day10

import java.io.File
 
fun main() {
    val adapters: List<Int> =
            File("src/main/resources/day10/puzzle_input.txt")
                    .readLines()
                    .map(String::toInt)

    val joignedAdapters = HomeMadeAdapter(adapters).joinAdapters()
    val differencesCalculus = joignedAdapters.differences[JoltDifferences.ONE_JOLT_DIFF]!! * joignedAdapters.differences[JoltDifferences.THREE_JOLT_DIFF]!!

    println("Day 10 -- Part 01, the number of 1-jolt differences multiplied by the number of 3-jolt differences is: $differencesCalculus")
}
 
