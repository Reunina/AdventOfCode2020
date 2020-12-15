package com.adventofcode.day04

import java.io.File
 
fun main() {
    val input: String =
            File("src/main/resources/day04/puzzle_input.txt")
                    .readText()

    println("Day 04 , first input : ")

    println(PassportScanner.readPassportsFrom(input).validateAllPassports())

}
 
