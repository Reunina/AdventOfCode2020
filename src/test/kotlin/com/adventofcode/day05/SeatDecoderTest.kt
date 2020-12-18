package com.adventofcode.day05

import com.adventofcode.day05.SeatAssert.Companion.assertThatSeat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class SeatDecoderTest {

    private val seatDecoder = SeatDecoder()

    @Test
    fun shouldBeAbleToDecodeSeatFromInputText() {
        assertAll({
            assertThatSeat(seatDecoder.readSeat("FBFBBFFRLR"))
                    .isEqualToComparingFieldByFieldRecursively(Seat(row = 44, column = 5))

            assertThatSeat(seatDecoder.readSeat("FFFBBBFRRR"))
                    .isEqualToComparingFieldByFieldRecursively(Seat(row = 14, column = 7))

            assertThatSeat(seatDecoder.readSeat("BBFFBBFRLL"))
                    .isEqualToComparingFieldByFieldRecursively(Seat(row = 102, column = 4))
        })
    }

    @Test
    fun shouldBeAbleToFindWithHigherIdFrom() {
        val input = listOf("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")
        assertThatSeat(
                seatDecoder.findSeatWithHigherIdFrom(input)!!
        )
                .isEqualToComparingFieldByFieldRecursively(Seat(row = 102, column = 4))


    }


    @Test
    fun shouldBeAbleToFindMissingSeats() {

        val input = listOf("XXXXXXXXXX", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")
        assertThat(
                seatDecoder.findMissingSeats(input)
        ).hasSize(1020)
                .doesNotContainAnyElementsOf(listOf(1023,
                        567,
                        119,
                        820 ))


    }
}