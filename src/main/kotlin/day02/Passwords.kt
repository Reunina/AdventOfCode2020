package day02

class Passwords(private val passwordsAndPolicy: List<String>) {

    fun findAllValidForPolicy(policy: (String) -> Policy): List<Password> {
        return passwordsAndPolicy
                .map { it.split(":") }
                .map { Password(it[1].trim(), policy.invoke(it[0])) }
                .filter { it.isValid() }
                .toList()
    }

}



