package day02


class Password(private val password: String, private val policy: Policy) {

    constructor(password: String) : this(password, NeutralPolicy())

    companion object {
        fun fromInput(policy : (String) -> Policy, passwordAndPolicy: String) : Password {
            val split = passwordAndPolicy.split(":")
           return Password(split[1].trim(), policy.invoke(split[0]))
        }
    }

    fun withPolicy(policy : (String) -> Policy , policyAsString: String): Password {
        return Password(password, policy.invoke(policyAsString))
    }

    fun isValid(): Boolean {
        return policy.checkValidity(password)
    }

    override fun toString(): String {
        return "Password '$password' with: $policy"
    }
}