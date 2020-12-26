package com.adventofcode.day11

import kotlin.reflect.KFunction3


typealias Layout = List<Triple<Int, Int, Char>>

class SeatHandler(private val layout: Layout) {

    private val empty = 'L'
    private val occupied = '#'

    companion object {

        fun fromInput(input: List<CharArray>): SeatHandler {
            val layout = input
                    .mapIndexed { x, values -> values.mapIndexed { y, value -> Triple(x, y, value) }.toList() }
                    .flatten()
                    .filterNot { it.third == '.' }
            return SeatHandler(layout)
        }
    }


    fun runOccupationSeatsSimulation(maxAdjacentToChange: Int, filter: KFunction3<Layout, @ParameterName(name = "currentX") Int, @ParameterName(name = "currentY") Int, Int>): Int {
        var previousRunningLayout = layout.toList()
        do {
            val previous = previousRunningLayout.count { it.third == occupied }
            val actualRunningLayout = changeSeatsOf(previousRunningLayout, filter, maxAdjacentToChange)
            val actual = actualRunningLayout.count { it.third == occupied }

            previousRunningLayout = actualRunningLayout

        } while (previous != actual)

        return previousRunningLayout.count { it.third == occupied }
    }

    fun runOccupationSeatsSimulationWithAdjacentSeats(): Int {
        return runOccupationSeatsSimulation(3, ::howManyAdjacentSeatsAreOccupied)
    }

    private fun changeSeatsOf(layout: Layout, filter: KFunction3<Layout, @ParameterName(name = "currentX") Int, @ParameterName(name = "currentY") Int, Int>, maxToChange: Int): Layout {
        return layout
                .map {
                    Triple(it.first, it.second,
                            newSeatType(it.third, filter.call(layout, it.first, it.second),
                                    maxToChange))
                }
    }

    private fun newSeatType(seatType: Char, actualOccupiedSeatsNearBy: Int, maxOccupied: Int): Char {
        if (seatType == empty && actualOccupiedSeatsNearBy == 0) return occupied
        if (seatType == occupied && actualOccupiedSeatsNearBy > maxOccupied) return empty
        return seatType
    }

    fun howManyAdjacentSeatsAreOccupied(currentLayout: Layout, currentX: Int, currentY: Int): Int {
        return currentLayout
                .filter { arePositionAdjacent(it.first, it.second, currentX, currentY) }
                .count { it.third == occupied }
    }

    fun runOccupationSeatsSimulationWithFirstVisibleSeats(): Long {
        return runOccupationSeatsSimulation(4, ::howManyVisibleSeatsAreOccupied).toLong()
    }


    fun howManyVisibleSeatsAreOccupied(currentLayout: Layout, currentX: Int, currentY: Int): Int {
        val onAllAxes = currentLayout
                .filter {
                    isNotCurrentPosition(it.first, it.second, currentX, currentY)
                            && (
                            isOnDiagonals(it, currentX, currentY) ||
                                    isOnHorizon(it, currentY) ||
                                    isOnVertical(it, currentX)
                            )
                }.toList()
        return listOfNotNull(
                onAllAxes.lastOrNull { it.first < currentX && it.second == currentY },
                onAllAxes.lastOrNull { it.first < currentX && it.second < currentY },
                onAllAxes.lastOrNull { it.first < currentX && it.second > currentY },
                onAllAxes.lastOrNull { it.first == currentX && it.second < currentY },
                onAllAxes.firstOrNull { it.first == currentX && it.second > currentY },
                onAllAxes.firstOrNull { it.first > currentX && it.second == currentY },
                onAllAxes.firstOrNull { it.first > currentX && it.second < currentY },
                onAllAxes.firstOrNull { it.first > currentX && it.second > currentY }
        )
                .filter { it.third == occupied }.count()


    }

    private fun arePositionAdjacent(otherX: Int, otherY: Int, currentX: Int, currentY: Int): Boolean {
        return (isNotCurrentPosition(otherX, otherY, currentX, currentY)
                &&
                (otherX > currentX - 2 && otherX < currentX + 2)
                &&
                (otherY > currentY - 2 && otherY < currentY + 2))
    }

    private fun isOnVertical(it: Triple<Int, Int, Char>, currentX: Int) =
            (it.first == currentX)

    private fun isOnHorizon(it: Triple<Int, Int, Char>, currentY: Int) =
            (it.second == currentY)

    private fun isOnDiagonals(it: Triple<Int, Int, Char>, currentX: Int, currentY: Int) =
            (it.second == it.first * -1 + (currentY + currentX)) //  y = (-1) * x + (currentY + currentX)
                    || (it.second == it.first * 1 + (currentY - currentX)) //  y = (1) * x + (currentY - currentX)

    private fun isNotCurrentPosition(otherX: Int, otherY: Int, currentX: Int, currentY: Int) =
            (otherX != currentX || otherY != currentY)


}
