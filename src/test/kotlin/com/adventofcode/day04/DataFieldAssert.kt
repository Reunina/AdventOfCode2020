package com.adventofcode.day04

import org.assertj.core.api.AbstractObjectAssert

class DataFieldAssert(value: DataField) : AbstractObjectAssert<DataFieldAssert,
        DataField>(value, DataFieldAssert::class.java) {

    fun validates(input: String): DataFieldAssert {
        if ( ! actual.validate(input)) {
             failWithMessage("%nExpecting <%s> to validate:<%s>%n", actual, input)

        }
        return this
    }

    fun invalidates(input: String): DataFieldAssert {
        if (  actual.validate(input)) {
            failWithMessage("%nExpecting <%s> to invalidate:<%s>%n", actual, input)

        }
        return this
    }

    companion object {
        fun assertThatDataField(value: DataField): DataFieldAssert {
            return DataFieldAssert(value)
        }
    }

}