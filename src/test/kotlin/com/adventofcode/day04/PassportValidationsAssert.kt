package com.adventofcode.day04

import org.assertj.core.api.AbstractObjectAssert

class PassportsValidationAssert(value: PassportsValidation) : AbstractObjectAssert<PassportsValidationAssert,
        PassportsValidation>(value, PassportsValidationAssert::class.java) {

    fun onlyContainsValidPassports(): PassportsValidationAssert {
        if ( ! actual.areAllPassportsValidOnes()) {
             failWithMessage("%nExpecting %n<%s>%nto have all passports valid", actual)

        }
        return this
    }

    fun onlyContainsInvalidPassports() : PassportsValidationAssert{
        if ( actual.areAllPassportsValidOnes()) {
            failWithMessage("%nExpecting %n<%s>%nto have all passports invalid", actual)

        }
        return this
    }

    companion object {
        fun assertThatPassportValidation(value: PassportsValidation): PassportsValidationAssert {
            return PassportsValidationAssert(value)
        }
    }

}