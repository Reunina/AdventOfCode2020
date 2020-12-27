package com.adventofcode.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class Day13KtTest {

    @Test
    fun shouldFindExpectedResultWithGivenInputForPart01() {
        val input: List<String> =
                File("src/main/resources/day13/puzzle_input.txt")
                        .readLines()
        assertThat(
                ShuttleBusService.readFrom(input[0], input[1]).findEarliestIdByWaitingTime()
        ).isEqualTo(370L)
    }

    @Test
    fun shouldFindExpectedResultWithGivenInputForPart02() {
        val input: List<String> =
                File("src/main/resources/day13/puzzle_input.txt")
                        .readLines()
        assertThat(
                ShuttleBusServicePart02(*input[1].split(",").toTypedArray()).findEarliestTimeStampThatMatchesWithTheBusDeparturePosition()
        ).isEqualTo(894954360381385L)
    }
}