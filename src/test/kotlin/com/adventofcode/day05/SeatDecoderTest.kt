package com.adventofcode.day05

import com.adventofcode.day05.SeatAssert.Companion.assertThatSeat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class SeatDecoderTest {

    @Test
    fun shouldBeAbleToDecodeSeatFromInputText() {
        assertAll({
            assertThatSeat(SeatDecoder.readSeat("FBFBBFFRLR"))
                    .isEqualToComparingFieldByFieldRecursively(Seat(row = 44, column = 5))

            assertThatSeat(SeatDecoder.readSeat("FFFBBBFRRR"))
                    .isEqualToComparingFieldByFieldRecursively(Seat(row = 14, column = 7))

            assertThatSeat(SeatDecoder.readSeat("BBFFBBFRLL"))
                    .isEqualToComparingFieldByFieldRecursively(Seat(row = 102, column = 4))
        })
    }

    @Test
    fun shouldBeAbleToFindWithHigherIdFrom() {
        val input = listOf("BFFFBBFRRR: row 70, column 7, seat ID 567",
                "FFFBBBFRRR: row 14, column 7, seat ID 119",
                "BBFFBBFRLL: row 102, column 4, seat ID 820"
        )
        assertThatSeat(
                SeatDecoder.findSeatWithHigherIdFrom(input)
        )
                .isEqualToComparingFieldByFieldRecursively(Seat(row = 102, column = 4))


    }

}