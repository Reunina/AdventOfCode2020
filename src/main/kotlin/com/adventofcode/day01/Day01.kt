package com.adventofcode.day01

import java.io.File

fun main() {
    val input: List<Int> =
            File("src/main/resources/day01/puzzle_input_for_entry.txt")
                    .readLines()
                    .map(String::toInt)
    println("Code to enter Advent Of Code 2020:       " + EntryCode().findFromTwoEntriesOf(input))
    println("Code to continue on Advent Of Code 2020: " + EntryCode().findFromThreeEntriesOf(input))
}
