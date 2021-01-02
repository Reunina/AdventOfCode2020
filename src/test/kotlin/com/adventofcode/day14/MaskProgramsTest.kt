package com.adventofcode.day14

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class MaskProgramsTest {

    @Test
    fun shouldReturnTheSumOfMemoryValues() {
        val input: List<String> = listOf("mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1")
        val memory = MaskPrograms(input)
                .getMemory()

        Assertions.assertThat(memory).isEqualTo(51L)
    }
    @Test
    fun shouldReturnTheSumOfMemoryValuesForV2() {
        val input: List<String> = listOf("mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1")
        val memory = MaskPrograms(input)
                .getMemoryV2()

        Assertions.assertThat(memory).isEqualTo(208L)
    }
}

