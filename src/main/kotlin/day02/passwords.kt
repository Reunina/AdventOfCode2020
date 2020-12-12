package day02

import java.io.File

class Passwords(passwordsAndPolicy: List<String>) {

    private val passwordsAndPolicy = passwordsAndPolicy
            .map { it.split(":") }
            .map { Password(it[1].trim(), Policy().fromInput(it[0])) }
            .toList()

    fun findAllValid(): List<Password> {
        return passwordsAndPolicy
                .filter { it.isValid() }
                .toList()
    }

}


fun main(args: Array<String>) {
    val input: List<String> = File("src/main/resources/day02/passwords_and_associated_corporate_policy.txt")
            .readLines()
    val findAllValid = Passwords(input).findAllValid()
    println("Day 02, number of valid passwords found: " + findAllValid.size + "\nsee below:\n"+findAllValid )
}



