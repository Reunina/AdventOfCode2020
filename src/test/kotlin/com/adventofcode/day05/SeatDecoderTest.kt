package com.adventofcode.day05

import com.adventofcode.day05.SeatAssert.Companion.assertThatSeat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class SeatDecoderTest {


    @Test
    fun shouldBeAbleToDecodeSeatFromInputText() {

        assertAll({
            assertThatSeat(SeatDecoder.readSeatFromInput("FBFBBFFRLR").seats[0])
                    .isEqualToComparingFieldByFieldRecursively(Seat(row = 44, column = 5))

            assertThatSeat(SeatDecoder.readSeatFromInput("FFFBBBFRRR").seats[0])
                    .isEqualToComparingFieldByFieldRecursively(Seat(row = 14, column = 7))

            assertThatSeat(SeatDecoder.readSeatFromInput("BBFFBBFRLL").seats[0])
                    .isEqualToComparingFieldByFieldRecursively(Seat(row = 102, column = 4))
        })
    }

    @Test
    fun shouldBeAbleToFindWithHigherIdFrom() {
        val seatDecoder = SeatDecoder.readFromInput(listOf("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"))
        assertThatSeat(
                seatDecoder.findSeatWithHigherIdFrom()!!
        )
                .isEqualToComparingFieldByFieldRecursively(Seat(row = 102, column = 4))


    }


    @Test
    fun shouldBeAbleToFindMissingSeats() {
        val seatDecoder = SeatDecoder(
                listOf(
                        Seat(0, 0),
                        Seat(0, 1),
                        Seat(0, 2),

                        Seat(1, 0),
                        // Seat(1,1), // missing one
                        Seat(1, 2),

                        Seat(2, 0),
                        Seat(2, 1),
                        Seat(2, 2)
                )
        )

        assertThat(seatDecoder.findMissingSeat()).isEqualTo(9)


    }
}