package com.adventofcode.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TicketTest{

    val validRanges = listOf(
            ValidRanges("class", 1, 3, 5, 7),
            ValidRanges("row", 6, 11, 33, 44),
            ValidRanges("seat", 13, 40, 45, 50)
    )

    @Test
    fun shouldFindErrorsAccordingToValidRange(){

        assertThat(Ticket(7,3,47).findErrorsAccordingTo(validRanges))
                .hasSize(0)

        assertThat(Ticket(40,4,50).findErrorsAccordingTo(validRanges))
                .hasSize(1)
                .containsOnly(4)

    }
}