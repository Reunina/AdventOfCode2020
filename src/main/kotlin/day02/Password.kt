package day02


class Password(private val password: String, private val policy: Policy) {

    companion object {
        fun fromInput(policy : (String) -> Policy, passwordAndPolicy: String) : Password {
            val split = passwordAndPolicy.split(":")
           return Password(split[1].trim(), policy.invoke(split[0]))
        }
    }

    fun isValid(): Boolean {
        return policy.checkValidity(password)
    }

    override fun toString(): String {
        return "Password '$password' with: $policy"
    }
}