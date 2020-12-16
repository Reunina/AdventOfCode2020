package com.adventofcode.day04

class SimplePassportsValidator(passports: Collection<Passport>) : PassportsValidator {

    override var invalidityCause: String = "Missing some field"

    override val validationData: Collection<Pair<Passport,Boolean>> =  passports.map { it to  validatePassport(it) }

    override fun toString(): String {
        return "SimplePassportsValidator: ${validationData.count { it.second }} data(s) validated.\n\n${validationData.joinToString("\n")}"
    }


}
