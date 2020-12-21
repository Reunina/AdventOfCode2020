package com.adventofcode.day06

import java.io.File
 
fun main() {
    val input: String =
            File("src/main/resources/day06/puzzle_input.txt")
                    .readText()

    val sumOfAnyoneCounts = input.split("\n\n")
            .map(Group.Companion::readFrom)
            .map { it.questionAtLeastOnePersonAnswered() }
            .sum()

    val sumOfEveryoneCounts = input.split("\n\n")
            .map(Group.Companion::readFrom)
            .map { it.questionEveryPersonAnswered() }
            .sum()

    println("Day 06 Part 01, sum of counts (anyone method)  : $sumOfAnyoneCounts")
    println("Day 06 Part 03, sum of counts (everyone method): $sumOfEveryoneCounts")
}
 
