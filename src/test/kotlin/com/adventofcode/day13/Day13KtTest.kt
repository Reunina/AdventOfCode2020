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
}