package com.adventofcode.day04

import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions.assertThat

class PassportsValidatorAssert(value: PassportsValidator) : AbstractObjectAssert<PassportsValidatorAssert,
        PassportsValidator>(value, PassportsValidatorAssert::class.java) {

    fun onlyContainsValidPassports(): PassportsValidatorAssert {
        if (!actual.areAllPassportsValidOnes()) {
            failWithMessage("%nExpecting %n<%s>%nto have all passports valid %nBut some are not:%n<%s>", actual.validationData, actual.invalidityCause)
        }
        return this
    }

    fun onlyContainsInvalidPassports(): PassportsValidatorAssert {
        if (actual.areAllPassportsValidOnes()) {
            failWithMessage("%nExpecting %n<%s>%nto have all passports invalid", actual)
        }
        return this
    }

    fun displaysNumberOfValidatedDataFoundAs(expectedStart: String): PassportsValidatorAssert {
        assertThat(actual.toString()).startsWith(expectedStart)
        return this
    }

    fun displayInvalidityCauseAs(expectedCause: String): PassportsValidatorAssert {
        assertThat(actual.invalidityCause).isEqualTo(expectedCause)
        return this
    }

    companion object {
        fun assertThatPassportValidator(value: PassportsValidator): PassportsValidatorAssert {
            return PassportsValidatorAssert(value)
        }
    }

}
