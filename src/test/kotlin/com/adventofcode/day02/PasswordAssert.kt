package com.adventofcode.day02

import org.assertj.core.api.AbstractObjectAssert

class PasswordAssert(value: Password) : AbstractObjectAssert<PasswordAssert, Password>(value, PasswordAssert::class.java) {
    fun isValid(): PasswordAssert {
        if (!actual.isValid()) {
            failWithMessage("Actual value <%s> is not a valid password", actual)
        }
        return this
    }

    fun isInValid(): PasswordAssert {
        if (actual.isValid()) {
            failWithMessage("Actual value <%s> is not an invalid password", actual)
        }
        return this
    }

    companion object {
        fun assertThatPassword(value: Password): PasswordAssert {
            return PasswordAssert(value)
        }
    }
}