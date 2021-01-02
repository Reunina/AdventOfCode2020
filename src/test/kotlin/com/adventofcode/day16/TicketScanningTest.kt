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

    @Test
    fun shouldFindPossibleFields(){
        val notes = TicketScanning.fromInput(listOf(
                "class: 0-1 or 4-19",
                "row: 0-5 or 8-19",
                "seat: 0-13 or 16-19",
                "",
                "your ticket:",
                "11,12,13",
                "",
                "nearby tickets:",
                "3,9,18",
                "15,1,5",
                "5,14,9")
        )

        assertThat(notes.findPossibleFields())
                .hasSize(3)
                .containsEntry(0 , listOf( listOf("row", "seat"), listOf("class", "row"), listOf("class", "row", "seat")))
                .containsEntry(1 , listOf(listOf("class", "row", "seat"), listOf("class", "row", "seat"), listOf("class", "row")))
                .containsEntry(2 , listOf(listOf("class", "row", "seat"), listOf("class", "row", "seat"),  listOf("class", "row", "seat")))

    }



    @Test
    fun shouldFindWhichFieldIsWhich(){
        val smallNotes = TicketScanning.fromInput(listOf(
                "class: 0-1 or 4-19",
                "row: 0-5 or 8-19",
                "seat: 0-13 or 16-19",
                "",
                "your ticket:",
                "11,12,13",
                "",
                "nearby tickets:",
                "3,9,18",
                "15,1,5",
                "5,14,9")
        )

        assertThat(smallNotes.findWhichFieldIsWhich())
                .hasSize(3)
                .containsEntry(0 , "row")
                .containsEntry(1 , "class")
                .containsEntry(2 , "seat")


        assertThat(notes.findWhichFieldIsWhich())
                .hasSize(3)
                .containsEntry(0 , "row")
                .containsEntry(1 , "class")
                .containsEntry(2 , "seat")

    }


}