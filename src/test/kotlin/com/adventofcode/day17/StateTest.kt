package com.adventofcode.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StateTest {

    @Test
    fun shouldCountActivesCubesInState() {
        assertThat(
                State(mapOf(Triple(0, 0, 0) to Cube('.'))
                ).countActives())
                .isEqualTo(0L)
        assertThat(
                State(mapOf(Triple(0, 0, 0)  to Cube('#'))
        ).countActives())
                .isEqualTo(1L)

        assertThat(State.fromInput(".#.",
                "..#",
                "###").countActives())
                .isEqualTo(5L)

    }
}

