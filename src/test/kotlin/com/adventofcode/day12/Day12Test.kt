package com.adventofcode.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class Day12Test {

    @Test
    fun shouldComputePart01() {

        val ferry = Ferry().followInstructions(File("src/main/resources/day12/puzzle_input.txt")
                .readLines())

        assertThat(ferry.distance())
                .isEqualTo(2879L)
    }
}