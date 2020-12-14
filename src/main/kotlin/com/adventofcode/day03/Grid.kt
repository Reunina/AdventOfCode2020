package com.adventofcode.day03

class Grid(val gridLines: List<String>) {

    companion object {
        fun fromInput(grisAsString: String): Grid {
            return Grid(grisAsString
                    .split("\n")
                    .toList())
        }
    }

    private var maxCharacterInPattern: Int = gridLines[0].length
    private var positionOnColumns: Int = 0
    private var positionOnLine: Int = 0

    fun moveRightBy(moveRightBy: Int) {
        val newIndex = positionOnColumns + moveRightBy
        positionOnColumns = newIndex.rem(maxCharacterInPattern)
    }

    fun moveDownBy(moveDowndBy: Int) {
        positionOnLine += moveDowndBy
    }

    fun getPosition(): Triple<Int, Int, Char> {
        val triple = Triple(positionOnLine, positionOnColumns, gridLines.getOrElse(positionOnLine){""}.getOrElse(positionOnColumns){' '})
        return triple
    }

    fun lastLine(): Int {
        return gridLines.size
    }


}
