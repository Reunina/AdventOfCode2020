package com.adventofcode.day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

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

        assertThat(Calculator(listOf("1 + (2 * 3) + (4 * (5 + 6))", "1 + 2 * 3 + 4 * 5 + 6", "(4 * (5 + 6)) + 1 + (2 * 3)")).evaluate())
                .isEqualTo(173L)

    }


    @TestFactory
    fun shouldApplysAdditionAndMultiplicationWithDeclaredPrecedence() = listOf(

            "(2 * 3) + (4 * (5 + 6))" to 50L,
            "1 + (2 * 3) + (4 * (5 + 6))" to 51L,
            "2 * 3 + (4 * 5)" to 46L,
            "5 + (8 * 3 + 9 + 3 * 4 * 3)" to 1445L,
            "1 + 20 + 300 + 4000" to 4321L,
            "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))" to 669060L,
            "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2" to 23340L,
            "(9 * (4 + 4 * 5 * 2 * 2) * 9 * (9 * 9 + 3 * 9 + 4 * 3) + 8 * 6) + (2 * 6 * 5 * (3 + 2 + 8 * 8) + 2 * 5) * 7 + 2" to 2953611000L
    ).map { (expression, expectedValue) ->
        DynamicTest.dynamicTest("$expression should be evaluate to $expectedValue") {
            assertThat(Calculator(expression).evaluateWithPrecedence())
                    .isEqualTo(expectedValue)
        }
    }


}