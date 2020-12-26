package com.adventofcode.day12

import com.adventofcode.day12.Orientation.*


typealias Position = Triple<Orientation, Longitude, Latitude>

typealias Longitude = Pair<Orientation, Long>
typealias Latitude = Pair<Orientation, Long>

class Ferry {

    var facing = E
    var positionNorthSouth = 0L
    var positionEastWest = 0L


    fun followInstruction(instruction: String): Ferry {
        val action = instruction.take(1)
        val value = instruction.removeRange(0, 1).toLong()
        when (action) {
            "N" -> positionNorthSouth += value
            "S" -> positionNorthSouth += -value
            "E" -> positionEastWest += value
            "W" -> positionEastWest += -value
            "R" -> turnRight(value)
            "L" -> turnLeft(value)
            "F" -> goForward(value)
        }

        return this
    }

    private fun goForward(value: Long) {
        when (facing) {
            E -> positionEastWest += value
            W -> positionEastWest -= value
            N -> positionNorthSouth += value
            S -> positionNorthSouth -= value
        }
    }

    private fun turnRight(value: Long) {
        val nbStep = Math.abs(value) / 90L
        when (nbStep) {
            1L -> facing = facing.nextClockWise()
            2L -> facing = facing.nextClockWise().nextClockWise()
            3L -> facing = facing.previousClockWise()
        }
    }

    private fun turnLeft(value: Long) {
        val nbStep = Math.abs(value) / 90L
        when (nbStep) {
            1L -> facing = facing.previousClockWise()
            2L -> facing = facing.nextClockWise().nextClockWise()
            3L -> facing = facing.nextClockWise()
        }
    }

    fun followInstructions(instructions: List<String>): Ferry {
        instructions.forEach { followInstruction(it) }
        return this
    }

    fun  distance(): Long =
        Math.abs(positionEastWest).plus(Math.abs(positionNorthSouth))

}

