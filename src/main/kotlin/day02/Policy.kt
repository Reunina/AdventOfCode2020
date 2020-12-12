package day02

class Policy(private val expectedCount: String, private val letter: String) {

    fun fromInput(policyAsString: String): Policy {
        val split = policyAsString.split(" ")
       return Policy(split.first(), split.last())
    }

    constructor() : this("0", "")


    override fun toString(): String {
        return "-letter '$letter' repeated '$expectedCount' times-"
    }

    fun countValidLetterOf(password: String): Int {
        return password.count{it == letter.toCharArray()[0]}
    }

    fun isLetterCountValid(count: Int): Boolean {
        val expectedCountSplit = expectedCount.split("-")
        return count in expectedCountSplit.first().toInt()..expectedCountSplit.last().toInt()
    }


}
