package com.adventofcode.day10

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class JoltDifferencesTest {

    @Test
    fun shouldRetriedMemberAccordingToTheGivenDifferential() {

        assertThat(JoltDifferences.forDiff(1))
                .isEqualTo(JoltDifferences.ONE_JOLT_DIFF)

        assertThatThrownBy({ JoltDifferences.forDiff(-1) }
        ).isInstanceOf(NoSuchElementException::class.java)
                .hasMessageContaining("Array contains no element matching the predicate")

    }

}