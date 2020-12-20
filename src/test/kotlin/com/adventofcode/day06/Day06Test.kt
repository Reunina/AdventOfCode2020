package com.adventofcode.day06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day06Test {


    @Test
    fun shouldFind11WithThisExampleInput() {
        assertThat(("abc\n\n" +
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
                .split("\n\n")
                .map(Group.Companion::readFrom)
                .map { it.questionAnswered() }
                .sum()
        ).isEqualTo(11)
    }
}