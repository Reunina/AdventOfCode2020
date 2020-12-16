package com.adventofcode.day04

class FullPassportsValidator(passports: Collection<Passport>) : PassportsValidator {
    override var invalidityCause: String = ""

    override val validationData: Collection<Pair<Passport, Boolean>> =
            passports.map { it to validatePassport(it) }

    override fun validatePassport(passport: Passport): Boolean {

        val invalidData = passport.data.filter { !it.key.validate(it.value) }
        val allValid = invalidData.isEmpty()
        return when {
            allValid -> super.validatePassport(passport)
            else -> {
                this.invalidityCause = "Found invalid data: $invalidData"
                allValid
            }
        }

    }

    override fun toString(): String {
        return "FullPassportsValidator: ${validationData.count { it.second }} passport(s) validated.\n\n${validationData.joinToString("\n")}"
    }


}
