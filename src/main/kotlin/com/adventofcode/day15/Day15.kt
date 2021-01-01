package com.adventofcode.day15

 
fun main() {
    val startingNumbers: List<Int> = listOf(18,8,0,5,4,1,20)
    println("Day 15, Part 01, the 2020th number spoken for $startingNumbers, is:"+ MemoryGame(startingNumbers).spokeNumberAtTurn(2020))
}
 
