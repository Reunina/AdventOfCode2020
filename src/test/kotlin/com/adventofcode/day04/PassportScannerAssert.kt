package com.adventofcode.day04

import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions.assertThat

class PassportScannerAssert(value: PassportScanner) : AbstractObjectAssert<PassportScannerAssert,
        PassportScanner>(value, PassportScannerAssert::class.java) {

    fun hasReadThisPassport(expectedPassport: Passport): PassportScannerAssert {
        assertThat(actual.passports)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(expectedPassport)
        return this
    }

    fun hasReadThesePassports(expectedPassports: List<Passport>): PassportScannerAssert {
        assertThat(actual.passports)
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(expectedPassports)
        return this
    }

    companion object {
        fun assertThatPassportScanner(value: PassportScanner): PassportScannerAssert {
            return PassportScannerAssert(value)
        }
    }

}