package com.adventofcode.day12

interface Ferry {
    var facing: Orientation
    var positionNorthSouth: Long
    var positionEastWest: Long
    fun followInstruction(instruction: String): Ferry
    fun goForward(value: Long)
    fun turnRight(value: Long)
    fun turnLeft(value: Long)
    fun followInstructions(instructions: List<String>): Ferry
    fun distance(): Long
}