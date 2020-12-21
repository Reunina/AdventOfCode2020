package com.adventofcode.day07

import java.io.File

fun main() {
    val input: List<Rule> =
            File("src/main/resources/day07/puzzle_input.txt")
                    .readLines().map(Rule.Companion::from)
    val nbOfColors = BagFinder(input).findNumberOfBagsThatCanContains("shiny gold")
    println("Day 07 Part 01, the number of bag colors that can eventually contain at least one shiny gold bag is $nbOfColors")

}
