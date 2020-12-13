package com.adventofcode.day02

class TobogganCorporatePolicy(positions: String, private val letter: String) : Policy {

    private val expectedPositions= positions.split("-").map { it.toInt() }.toList()
    private val firstPosition = expectedPositions.first()
    private val secondPosition = expectedPositions.last()

    companion object {
        fun fromInput(policyAsString: String): TobogganCorporatePolicy {
            val split = policyAsString.split(" ")
            return TobogganCorporatePolicy(split.first(), split.last())
        }
    }

    override fun toString(): String {
        return "Toboggan Corporate policy: -letter '$letter' must be at '$firstPosition' and not at '$secondPosition'-"
    }

    override fun checkValidity(password: String): Boolean {
        return letterIsAtFirstPositionAndNotAtSecondPosition(password)
                || letterISAtSecondPositionAndNotAtInFirstPosition(password)
            }

    private fun letterIsAtFirstPositionAndNotAtSecondPosition(password: String) =
            (password.getOrNull(firstPosition - 1).toString() == letter
                    && password.getOrNull(secondPosition - 1).toString() != letter)

    private fun letterISAtSecondPositionAndNotAtInFirstPosition(password: String) =
            (password.getOrNull(firstPosition - 1).toString() != letter
                    && password.getOrNull(secondPosition - 1).toString() == letter)

}
