package com.adventofcode.day12

class SmartyPantsFerry(
        override var positionEastWest: Long = 0L,
        override var positionNorthSouth: Long = 0L) : Ferry {
    override var facing: Orientation = Orientation.E

    var wayPointEastWest = 10L
    var wayPointNorthSouth = 1L

    override fun followInstruction(instruction: String): Ferry {
        val action = instruction.take(1)
        val value = instruction.removeRange(0, 1).toLong()
        when (action) {
            "N" -> wayPointNorthSouth += value
            "S" -> wayPointNorthSouth += -value
            "E" -> wayPointEastWest += value
            "W" -> wayPointEastWest += -value
            "R" -> turnRight(value)
            "L" -> turnLeft(value)
            "F" -> goForward(value)
        }
        return this
    }

    override fun goForward(value: Long) {
        positionNorthSouth += wayPointNorthSouth.times(value)
        positionEastWest += wayPointEastWest.times(value)
    }

    override fun turnRight(value: Long) {
        val nbStep = Math.abs(value) / 90L

        val actualWayPoint = Pair(wayPointEastWest, wayPointNorthSouth)
        when (nbStep) {
            1L -> setWayPointTo(actualWayPoint.second, actualWayPoint.first.unaryMinus())
            2L -> setWayPointTo(actualWayPoint.first.unaryMinus(), actualWayPoint.second.unaryMinus())
            3L -> setWayPointTo(actualWayPoint.second.unaryMinus(), actualWayPoint.first)
        }
    }

    private fun setWayPointTo(positionEastWest: Long, positionNorthSouth: Long) {
        wayPointEastWest = positionEastWest
        wayPointNorthSouth = positionNorthSouth
    }

    override fun turnLeft(value: Long) {
        val nbStep = Math.abs(value) / 90L
        when (nbStep) {
            1L -> turnRight(270)
            2L -> turnRight(180)
            3L -> turnRight(90)
        }
    }

    override fun followInstructions(instructions: List<String>): Ferry {
        instructions.forEach { followInstruction(it) }
        return this
    }

    override fun distance(): Long =
            Math.abs(positionEastWest).plus(Math.abs(positionNorthSouth))

}