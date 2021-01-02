package com.adventofcode.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ValidRangesTest {

    private val rangesForFieldClass = ValidRanges("class", 1, 3, 5, 7)

    @Test
    fun shouldDisplayNicely() {

        assertThat(rangesForFieldClass)
                .hasToString("class: [1 to 3] or [5 to 7]")
    }

    @Test
    fun shouldAcceptValidField() {

        assertAll(
                {
                    assertThat(rangesForFieldClass.accept(1)).isTrue()
                    assertThat(rangesForFieldClass.accept(2)).isTrue()
                    assertThat(rangesForFieldClass.accept(3)).isTrue()
                    assertThat(rangesForFieldClass.accept(5)).isTrue()
                    assertThat(rangesForFieldClass.accept(6)).isTrue()
                    assertThat(rangesForFieldClass.accept(7)).isTrue()
                }
        )
    }

    @Test
    fun shouldNotAcceptInvalidField() {

        assertAll(
                {
                    assertThat(rangesForFieldClass.accept(0)).isFalse()
                    assertThat(rangesForFieldClass.accept(4)).isFalse()
                    assertThat(rangesForFieldClass.accept(8)).isFalse()
                }
        )
    }
}

