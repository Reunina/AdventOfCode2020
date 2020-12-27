package com.adventofcode.day12

import com.adventofcode.day12.Orientation.*


class BasicFerry (
        override var facing: Orientation = E,
        override var positionEastWest: Long = 0L,
        override var positionNorthSouth: Long = 0L) : Ferry {

    override fun followInstruction(instruction: String): BasicFerry {
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

    override fun goForward(value: Long) {
        when (facing) {
            E -> positionEastWest += value
            W -> positionEastWest -= value
            N -> positionNorthSouth += value
            S -> positionNorthSouth -= value
        }
    }

    override fun turnRight(value: Long) {
        val nbStep = Math.abs(value) / 90L
        when (nbStep) {
            1L -> facing = facing.nextClockWise()
            2L -> facing = facing.nextClockWise().nextClockWise()
            3L -> facing = facing.previousClockWise()
        }
    }

    override fun turnLeft(value: Long) {
        val nbStep = Math.abs(value) / 90L
        when (nbStep) {
            1L -> facing = facing.previousClockWise()
            2L -> facing = facing.nextClockWise().nextClockWise()
            3L -> facing = facing.nextClockWise()
        }
    }

    override fun followInstructions(instructions: List<String>): Ferry {
        instructions.forEach { followInstruction(it) }
        return this
    }

    override fun  distance(): Long =
        Math.abs(positionEastWest).plus(Math.abs(positionNorthSouth))

}

