package com.adventofcode.day16

class ValidRanges(val name: String, val firstLowerBound: Int, val firstHigherBound: Int, val secondLowerBound: Int, val secondHigherBound: Int) {

    private val firstRange = firstLowerBound.rangeTo(firstHigherBound)
    private val secondRange = secondLowerBound.rangeTo(secondHigherBound)

    override fun toString(): String {
        return "$name: [$firstLowerBound to $firstHigherBound] or [$secondLowerBound to $secondHigherBound]"
    }

    fun accept(field: Int): Boolean {
        return firstRange.contains(field) || secondRange.contains(field)
    }
}
