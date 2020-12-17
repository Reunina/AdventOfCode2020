package com.adventofcode.day04

class SimplePassportsValidator(passports: Collection<Passport>) : PassportsValidator {

    override var invalidityCause: String = "Missing some field"
    override val validationData: Collection<Pair<Passport, Boolean>> = validateAll(passports)

    override fun toString(): String {
        return asString()
    }

}
