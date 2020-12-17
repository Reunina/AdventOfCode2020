package com.adventofcode.day04

class FullPassportsValidator(passports: Collection<Passport>) : PassportsValidator {

    override var invalidityCause = ""
    override val validationData = validateAll(passports)

    override fun validatePassport(passport: Passport): Boolean {

        val invalidData = passport.data.filter { !it.key.validate(it.value) }.toMap()
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
        return asString()
    }
}
