package day02

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

    override fun checkValidity(password: String): Boolean {
        return letterFoundInFirstPositionAndNotInSecondPosition(password)
                || letterFoundInSecondPositionAndNotInFirstPosition(password)
            }

    private fun letterFoundInFirstPositionAndNotInSecondPosition(password: String) =
            (password.getOrNull(firstPosition - 1).toString() == letter
                    && password.getOrNull(secondPosition - 1).toString() != letter)

    private fun letterFoundInSecondPositionAndNotInFirstPosition(password: String) =
            (password.getOrNull(firstPosition - 1).toString() != letter
                    && password.getOrNull(secondPosition - 1).toString() == letter)

    override fun toString(): String {
        return "Toboggan Corporate policy: -letter '$letter' must be at '$firstPosition' and not at '$secondPosition'-"
    }
}

/*
1-3 a: abcde is valid: position 1 contains a and position 3 does not.
1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
 */
