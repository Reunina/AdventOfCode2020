package com.adventofcode.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class TicketScanningTest {


    private val notes = TicketScanning.fromInput(File("src/main/resources/day16/small_input.txt")
            .readLines())

    @Test
    fun shouldBeAbleToReadNotesFromInput() {


        val expectedValidRanges = listOf(
                ValidRanges("class", 1, 3, 5, 7),
                ValidRanges("row", 6, 11, 33, 44),
                ValidRanges("seat", 13, 40, 45, 50)
        )
        val expectedTicket = Ticket(7, 1, 14)
        val expectedNearbyTickets = listOf(
                Ticket(7, 3, 47),
                Ticket(40, 4, 50),
                Ticket(55, 2, 20),
                Ticket(38, 6, 12)
        )
        assertThat(notes)
                .isEqualToComparingFieldByFieldRecursively(
                        TicketScanning(expectedValidRanges, expectedTicket, expectedNearbyTickets)
                )

    }
    @Test
    fun shouldBeAbleToFoundErrorsInNearbyTickets() {

        assertThat(notes.errorsInNearByTickets())
                .hasSize(3)
                .contains(4,55,12)

    }



}