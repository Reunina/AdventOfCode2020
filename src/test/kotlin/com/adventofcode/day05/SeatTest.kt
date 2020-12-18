package com.adventofcode.day05

import com.adventofcode.day05.SeatAssert.Companion.assertThatSeat
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class SeatTest {

    @Test
    fun shouldDisplayNicely(){
        Assertions.assertThat(Seat(row = 44, column = 5).toString())
                .isEqualTo("Seat(row=44, column=5, id=357)")
    }

    @Test
    fun shouldHaveRowAndColumnWellSet(){
        assertThatSeat(Seat(row = 44, column = 5))
                .hasRow(44)
                .hasColumn(5)
    }

    @Test
    fun shouldHaveAnID(){
        assertThatSeat(Seat(row = 44, column = 5))
                .hasId(357)
    }
}