package com.adventofcode.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class Day16Test {


    val ticketScanning = TicketScanning.fromInput(File("src/main/resources/day16/puzzle_input.txt").readLines())

    @Test
    fun shouldBeAbleToFoundExpectedValueForPart01() {
    assertThat(ticketScanning.errorsInNearByTickets().sum())
            .isEqualTo(20091)
    }

    @Test
    fun shouldBeAbleToFoundExpectedValueForPart02() {
        assertThat(ticketScanning.multiplyDepartureFieldsTogether())
                .isEqualTo(2325343130651L)
    }
}

