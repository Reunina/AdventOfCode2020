package com.adventofcode.day15

 
fun main() {
    val startingNumbers: List<Int> = listOf(18,8,0,5,4,1,20)
    val memoryGame = MemoryGame(startingNumbers)
    println("Day 15, Part 01, the 2020th number spoken for $startingNumbers, is:"+ memoryGame.spokeNumberAtTurn(2020))
    println("Day 15, Part 02, the 30000000th number spoken for $startingNumbers, is:"+ memoryGame.spokeNumberAtTurn(30000000))
}
 
