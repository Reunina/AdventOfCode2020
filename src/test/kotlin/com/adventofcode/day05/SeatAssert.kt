package com.adventofcode.day05


import org.assertj.core.api.AbstractObjectAssert

class SeatAssert(value: Seat?) : AbstractObjectAssert<SeatAssert, Seat?>(value, SeatAssert::class.java) {


    fun hasRow(expectedRow: Int): SeatAssert {
        if (actual!!.row != expectedRow) {
            failWithMessage("%nExpecting row to be <%s> instead of<%s>", actual.row, expectedRow)
        }
        return this

    }

    fun hasColumn(expectedColumn: Int): SeatAssert {
        if (actual!!.column != expectedColumn) {
            failWithMessage("%nExpecting column to be <%s> instead of<%s>", actual.row, expectedColumn)
        }
        return this

    }

    fun hasId(expectedId: Int): SeatAssert {
        if (actual!!.id != expectedId) {
            failWithMessage("%nExpecting id to be <%s> instead of<%s>", actual.id, expectedId)
        }
        return this
    }


    companion object {
        fun assertThatSeat(value: Seat): SeatAssert {
            return SeatAssert(value)
        }
    }

}