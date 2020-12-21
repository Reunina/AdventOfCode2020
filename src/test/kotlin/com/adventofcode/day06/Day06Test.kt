package com.adventofcode.day06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day06Test {


    private val input = ("abc\n\n" +
            "a\n" +
            "b\n" +
            "c\n\n" +
            "ab\n" +
            "ac\n\n" +
            "a\n" +
            "a\n" +
            "a\n" +
            "a\n\n" +
            "b")

    @Test
    fun shouldFind11WithThisExampleInputWhenUsingAnyoneCountingMethod() {
        assertThat(input
                .split("\n\n")
                .map(Group.Companion::readFrom)
                .map { it.questionAtLeastOnePersonAnswered() }
                .sum()
        ).isEqualTo(11)
    }


    @Test
    fun shouldFind11WithThisExampleInputWhenUsingEveryOneCountingMethod() {
        assertThat(input
                .split("\n\n")
                .map(Group.Companion::readFrom)
                .map { it.questionEveryPersonAnswered() }
                .sum()
        ).isEqualTo(6)
    }

}