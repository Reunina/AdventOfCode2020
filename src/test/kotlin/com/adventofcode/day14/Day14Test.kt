package com.adventofcode.day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class Day14Test {

    @Test
    fun shouldFoundExpectedValueForPart01() {
        val input: List<String> =
                File("src/main/resources/day14/puzzle_input.txt")
                        .readLines()
        val memory = MaskPrograms(input)
                .getMemory()

        assertThat(memory).isEqualTo(7817357407588L)

    }
}