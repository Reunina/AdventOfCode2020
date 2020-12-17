package com.adventofcode.day04

class PassportScanner(val passports: List<Passport>) {


    fun simplyValidateAllPassports(): PassportsValidator {
        return SimplePassportsValidator(passports)
    }

    fun fullyValidateAllPassports(): PassportsValidator {
        return FullPassportsValidator(passports)
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
