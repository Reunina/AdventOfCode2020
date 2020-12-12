package day02


class Password(private val password: String, private val policy: Policy) {

    constructor(password: String) : this(password, NeutralPolicy())

    companion object {
        fun fromInput(passwordAndPolicy: String) : Password {
            val split = passwordAndPolicy.split(":")
           return Password(split[1].trim(), SledRentalPolicy.fromInput(split[0]))
        }
    }

    fun withSledRentalPolicy(count: String, letter: String): Password {
        return Password(password, SledRentalPolicy(count, letter))
    }

    fun isValid(): Boolean {
        return policy.checkValidity(password)
    }

    override fun toString(): String {
        return "Password '$password' with: $policy"
    }
}