package day02

class SledRentalPolicy(private val expectedCount: String, private val letter: String) : Policy {

    companion object {
        fun fromInput(policyAsString: String): SledRentalPolicy {
            val split = policyAsString.split(" ")
            return SledRentalPolicy(split.first(), split.last())
        }
    }

    override fun toString(): String {
        return "Sled Rental policy: -letter '$letter' repeated '$expectedCount' times-"
    }

    override fun checkValidity(password: String): Boolean {
        return isLetterCountValid(countValidLetterOf(password))
    }

    private fun countValidLetterOf(password: String): Int {
        return password.count{it == letter.toCharArray()[0]}
    }

    private  fun isLetterCountValid(count: Int): Boolean {
        val expectedCountSplit = expectedCount.split("-")
        return count in expectedCountSplit.first().toInt()..expectedCountSplit.last().toInt()
    }


}
