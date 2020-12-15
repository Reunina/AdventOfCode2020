package com.adventofcode.day04

import org.assertj.core.api.AbstractObjectAssert

class PassportScannerAssert(value: PassportScanner) : AbstractObjectAssert<PassportScannerAssert,
        PassportScanner>(value, PassportScannerAssert::class.java) {

    fun hasReadThisPassport(expectedPassport: Passport): PassportScannerAssert {
        if ( ! actual.passports.contains(expectedPassport)) {
             failWithMessage("%nExpecting <%s> to have read:%n  <%s>%nbut was not found in:%n  <%s>", actual, expectedPassport, actual.passports)

        }
        return this
    }

    companion object {
        fun assertThatPassportScanner(value: PassportScanner): PassportScannerAssert {
            return PassportScannerAssert(value)
        }
    }

}