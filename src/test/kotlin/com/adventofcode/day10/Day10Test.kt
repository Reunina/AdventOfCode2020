package com.adventofcode.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class Day10Test {

    @Test
    fun shouldReturnDifferencesAsExpected() {

        val adapters = medium_input()
        assertThat(HomeMadeAdapter(adapters).joinAdapters())
               .containsEntry(JoltDifferences.ONE_JOLT_DIFF, 22)
               .containsEntry(JoltDifferences.THREE_JOLT_DIFF, 9)
    }

    private fun medium_input(): String {
        return "28\n" +
                "33\n" +
                "18\n" +
                "42\n" +
                "31\n" +
                "14\n" +
                "46\n" +
                "20\n" +
                "48\n" +
                "47\n" +
                "24\n" +
                "23\n" +
                "49\n" +
                "45\n" +
                "19\n" +
                "38\n" +
                "39\n" +
                "11\n" +
                "1\n" +
                "32\n" +
                "25\n" +
                "35\n" +
                "8\n" +
                "17\n" +
                "7\n" +
                "9\n" +
                "4\n" +
                "2\n" +
                "34\n" +
                "10\n" +
                "3"
    }

    @Test
    fun shouldReturnAllValidArrangementAsExpected() {

        val adapters = small_input()

       assertThat(HomeMadeAdapter(adapters).findAllValidArrangements())
       .isEqualTo(8)
    }

    @Test
    fun shouldReturnAllValidArrangementAsExpectsed() {

        assertThat(HomeMadeAdapter(medium_input()).findAllValidArrangements())
                .isEqualTo(19208)
    }

    @Test
    fun shouldReturnAllValidArrangementAsssExpected() {

        val adapters = small_input()

        assertThat(HomeMadeAdapter(adapters).findAllValidArrangements())
                .isEqualTo(8)
    }

    private fun small_input(): String {
        return "16\n" +
                "10\n" +
                "15\n" +
                "5\n" +
                "1\n" +
                "11\n" +
                "7\n" +
                "19\n" +
                "6\n" +
                "12\n" +
                "4"
    }

    @Test
    fun shouldReturnAllValidArrangementAsExpected2() {

        val adapters = full_input()

       assertThat(HomeMadeAdapter(adapters).findAllValidArrangements())
       .isEqualTo(21156911906816)
    }

    private fun full_input(): List<Int> {
        return File("src/main/resources/day10/puzzle_input.txt")
                .readLines()
                .map(String::toInt)
    }
}