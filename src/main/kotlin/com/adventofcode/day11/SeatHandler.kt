package com.adventofcode.day11


import java.io.Serializable

enum class SpaceType(val type: Char) {
    FLOOR('.'), EMPTY_SEAT('L'), OCCUPIED_SEAT('#');

    companion object {
        fun valueFrom(value: Char): SpaceType {
            return values().find { it.type == value }!!
        }
    }
}

data class Coordinate(val c: Long) {
    companion object {
        fun of(value: Int): Coordinate {
            return Coordinate(value.toLong())
        }
    }

    fun isAtDistanceOf1To(other: Coordinate): Boolean {
        return c in (other.c - 1).rangeTo(other.c + 1)
    }

}

data class Position(val x: Coordinate, val y: Coordinate) : Serializable {
    fun isAdjacentTo(other: Position): Boolean {
        return x.isAtDistanceOf1To(other.x) && y.isAtDistanceOf1To(other.y)
    }
}

typealias Layout = MutableMap<Position, SpaceType>

class SeatHandler(private val layout: Layout) {

    companion object {
        fun fromInput(input: String): SeatHandler {
            return fromInput(input.split("\n"))
        }
        fun fromInput(input: List<String>): SeatHandler {
            val layout = input
                    .mapIndexed { line, values ->
                        values.toCharArray()
                                .mapIndexed { column, value ->
                                    Position(Coordinate.of(line), Coordinate.of(column)) to SpaceType.valueFrom(value)
                                }.toList()
                    }.flatten().toMap().toMutableMap()
            return SeatHandler(layout)
        }
    }

    fun runOccupationSeatsSimulation(): Long {

        var previousRunningLayout = layout.toMutableMap()
        do {
            val previous = previousRunningLayout.values.groupBy { it }
            val actualRunningLayout = changeSeatsOf(previousRunningLayout)
            val actual = actualRunningLayout.values.groupBy { it  }

            previousRunningLayout=actualRunningLayout.toMutableMap()

        }while ( previous != actual )

        return previousRunningLayout.filterValues { it == SpaceType.OCCUPIED_SEAT }.size.toLong()
    }

    private fun changeSeatsOf(layout: Layout): Layout {
        val newLayout = layout.toMutableMap()
        layout.map { it  to howManyAdjacentSeatsAreOccupied(layout,it.key)  }
                .map {
                    if(it.first.value == SpaceType.EMPTY_SEAT && it.second == 0L ) newLayout[it.first.key] = SpaceType.OCCUPIED_SEAT
                    if(it.first.value == SpaceType.OCCUPIED_SEAT && it.second > 3L ) newLayout[it.first.key] = SpaceType.EMPTY_SEAT
                }
        return newLayout
    }

    private fun howManyAdjacentSeatsAreOccupied(layout: Layout, currentPosition: Position): Long {
        return layout
                .filterKeys { it != currentPosition && it.isAdjacentTo(currentPosition) }
                .values.count { it == SpaceType.OCCUPIED_SEAT }.toLong()
    }
}
