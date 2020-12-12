package day02

private val neutralPolicy = Policy("0", "")

class Password(val password: String, val policy: Policy) {

    constructor(password: String) : this(password, neutralPolicy)
    constructor() : this("", neutralPolicy)

    fun withPolicy(count: String, letter: String): Password {
        return Password(password, Policy(count, letter))
    }

    fun isValid(): Boolean {
        val count = policy.countValidLetterOf(password)
        return policy.isLetterCountValid(count)
    }

    override fun toString(): String {
        return "Password '$password' with policy: $policy"
    }

    fun fromInput(input: String): Password {
        val split = input.split(":")
        return Password(split[1].trim(), Policy().fromInput(split[0]))
    }

}