package com.adventofcode.day10

import java.io.File
 
fun main() {
    val adapters: List<Int> =
            File("src/main/resources/day10/puzzle_input.txt")
                    .readLines()
                    .map(String::toInt)

    val homeMadeAdapter = HomeMadeAdapter(adapters)
    val joinedAdapters = homeMadeAdapter.joinAdapters()
    val differencesCalculus = joinedAdapters[JoltDifferences.ONE_JOLT_DIFF]!! * joinedAdapters[JoltDifferences.THREE_JOLT_DIFF]!!

  println("Day 10 -- Part 01, the number of 1-jolt differences multiplied by the number of 3-jolt differences is: $differencesCalculus")
  println("Day 10 -- Part 02, the total number of distinct ways I can arrange the adapters to connect the charging outlet to my device : ${homeMadeAdapter.findAllValidArrangements()}")
}
 
