package com.adventofcode.day06

import java.io.File
 
fun main() {
    val input: String =
            File("src/main/resources/day06/puzzle_input.txt")
                    .readText()

    val sumOfCounts = input.split("\n\n")
            .map(Group.Companion::readFrom)
            .map { it.questionAnswered() }
            .sum()

    println("Day 06, sum of counts: $sumOfCounts")
}
 
