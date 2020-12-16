package com.adventofcode.day04

class SimplePassportsValidator(passports: Collection<Passport>) : PassportsValidator {

    override var invalidityCause: String = "Missing some field"

    override val validationData: Collection<Pair<Passport,Boolean>> =  passports.map { it to  validatePassport(it) }

    override val validData: Collection<Pair<Passport, Boolean>> = validationData.filter { it.second }

    override fun toString(): String {
        return asString()  }


}
