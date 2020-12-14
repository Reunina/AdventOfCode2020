package com.adventofcode.day03

import java.io.File

fun main() {
    val input: List<String> =
            File("src/main/resources/day03/puzzle_input.txt")
                    .readLines()
                    .toList()

    val cheaperSlopesModel = CheaperSlopesModel(3, 1)
    val trajectoryWithTrees = TobogganTrajectory(Grid(input), cheaperSlopesModel)

    trajectoryWithTrees.downTheSlope()

    println("Day 03 - Part 01, found " + trajectoryWithTrees.treesEncountered() + " trees down this slope using " + cheaperSlopesModel + " to move.")


    val weirdCalculus = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
            .map { CheaperSlopesModel(it.first, it.second) }
            .map { TobogganTrajectory(Grid(input), it) }
            .map { it.downTheSlope(); it.treesEncountered().toLong() }
            .reduce { res, element -> res.times(element) }


    println("Day 03 - Part 02, found " + weirdCalculus + " trees down theses slopes.")


}

 
