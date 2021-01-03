package com.adventofcode.day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    fun shouldApplysAdditionAndMultiplicationWithSamePrecedence() {

        assertThat(Calculator("1 + 2 * 3 + 4 * 5 + 6").evaluate())
                .isEqualTo(71L)

    }

    @Test
    fun shouldApplysAdditionAndMultiplicationWithSamePrecedenceButAccordingToParentheses() {

        assertThat(Calculator("1 + (2 * 3) + (4 * (5 + 6))").evaluate())
                .isEqualTo(51L)
        assertThat(Calculator("(4 * (5 + 6)) + 1 + (2 * 3)").evaluate())
                .isEqualTo(51L)

    }
    @Test
    fun shouldEvaluateFromMultipleExpressions() {

        assertThat(Calculator(listOf("1 + (2 * 3) + (4 * (5 + 6))", "1 + 2 * 3 + 4 * 5 + 6" ,"(4 * (5 + 6)) + 1 + (2 * 3)" )).evaluate())
                .isEqualTo(173L)

    }
}