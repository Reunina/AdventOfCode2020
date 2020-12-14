package com.adventofcode.day03

import java.io.File

fun main() {
    val input: List<String> =
            File("src/main/resources/day03/puzzle_input.txt")
                    .readLines()
                    .toList()

    val trajectoryWithTrees = TobogganTrajectory(Grid(input), CheaperSlopesModel())

    trajectoryWithTrees.downTheSlope()

    println("Day 03, Found "+trajectoryWithTrees.treesEncountered()+" trees down this slope usig Cheaper slopes model to move." )

}
 
