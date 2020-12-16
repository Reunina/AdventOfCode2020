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

    override val validData: Collection<Pair<Passport, Boolean>> = validationData.filter { it.second }

    override fun toString(): String {
        return asString()  }




}
