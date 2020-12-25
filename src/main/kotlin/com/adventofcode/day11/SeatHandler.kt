package com.adventofcode.day11


typealias Layout = List<Triple<Int, Int, Char>>

class SeatHandler(private val layout: Layout) {

    val FLOOR = '.'
    val EMPTY_SEAT = 'L'
    val OCCUPIED_SEAT = '#'

    companion object {

        fun fromInput(input: List<CharArray>): SeatHandler {
            val layout = input
                    .mapIndexed { x, values ->
                        values.mapIndexed { y, value -> Triple(x, y, value) }.toList()
                    }
                    .flatten()
                    .filterNot { it.third == '.' }
            return SeatHandler(layout)
        }
    }


    fun runOccupationSeatsSimulation(): Int {
        var previousRunningLayout = layout.toList()
        do {
            val previous = previousRunningLayout.count { it.third == OCCUPIED_SEAT }
            val actualRunningLayout = changeSeatsOf(previousRunningLayout)
            val actual = actualRunningLayout.count { it.third == OCCUPIED_SEAT }

            previousRunningLayout = actualRunningLayout

        } while (previous != actual)

        return previousRunningLayout.count { it.third == OCCUPIED_SEAT }
    }

    private fun changeSeatsOf(layout: Layout): Layout {
        return layout
                .map {
                    it to howManyAdjacentSeatsAreOccupied(layout, it.first, it.second)
                }
                .map {
                    Triple(it.first.first, it.first.second, newType(it.first.third, it.second))
                }
    }

    private fun newType(seatType: Char, actualOccupiedSeatsNearBy: Int): Char {
        if (seatType == EMPTY_SEAT && actualOccupiedSeatsNearBy == 0) return OCCUPIED_SEAT
        if (seatType == OCCUPIED_SEAT && actualOccupiedSeatsNearBy > 3) return EMPTY_SEAT
        return seatType
    }

    private fun howManyAdjacentSeatsAreOccupied(currentLayout: Layout, currentX: Int, currentY: Int): Int {
        return currentLayout
                .filter {
                    (it.first != currentX || it.second != currentY)
                            &&
                            ( it.first  > currentX - 2  && it.first <currentX + 2)
                            &&
                            ( it.second  > currentY - 2  && it.second <currentY + 2)
                }
                .count { it.third == OCCUPIED_SEAT }

    }
}
