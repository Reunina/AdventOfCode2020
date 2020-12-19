package com.adventofcode.day05

import java.io.Serializable

open class BinarySpace(val min: Int, val max: Int) : Serializable {

    override fun toString(): String {
        return "[$min,$max]"
    }

    fun decode(code: String): BinarySpace {
        var newSpace = this
        code.toCharArray()
                .forEach {
                    newSpace = newSpace.takeHalf(it)
                }
        return newSpace

    }

    fun takeLowerHalf(): BinarySpace {
        if (min == max) return this
        return BinarySpace(min, max - Math.floorDiv(getSpaceDelta(), 2))
    }

    fun takeUpperHalf(): BinarySpace {
        if (min == max) return this
        return BinarySpace(min + Math.floorDiv(getSpaceDelta(), 2), max)
    }

    private fun getSpaceDelta(): Int {
        val diff = max - min
        if (diff % 2 != 0) return diff + 2
        return diff
    }

    fun takeHalf(code: Char): BinarySpace {
        if (code == 'F' || code == 'L') return takeLowerHalf()
        return takeUpperHalf()
    }


}
