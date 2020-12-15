package com.adventofcode.day04

class PassportScanner(val passports: List<Passport>) {

    private val EXPECCTED_VALUES: Collection<DataField> = DataField.values().filter { it != DataField.CID }

    private fun validatePassport(passport: Passport): Boolean {
        return passport.containsAllDataField(EXPECCTED_VALUES)
    }

    fun validateAllPassports(): PassportsValidation {
        return PassportsValidation(passports.map { it to validatePassport(it) })
    }

    companion object {

        fun readPassportFrom(input: String): PassportScanner {
            return PassportScanner(listOf(Passport.fromInput(input)))
        }

        fun readPassportsFrom(input: String): PassportScanner {

            return PassportScanner(input.split("\n\n")
                    .map { Passport.fromInput(it) }
                    .toList())
        }
    }
}
