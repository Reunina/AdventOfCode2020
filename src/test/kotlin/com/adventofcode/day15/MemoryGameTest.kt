package com.adventofcode.day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory


internal class MemoryGameTest {


    private val memoryGame = MemoryGame(listOf(0, 3, 6))

    @TestFactory
    fun shouldFoundSpokenNumberAccordingTOPreviousUsageOfTheNumber() = listOf(
            1 to 0,
            2 to 3,
            3 to 6,
            4 to 0,
            5 to 3,
            6 to 3,
            7 to 1,
            8 to 0,
            9 to 4,
            10 to 0,
            2020 to 436
    ).map { (nthTurn, expectedSpokenNumber) ->
        DynamicTest.dynamicTest("At the turn N°${nthTurn} the nummber spoke should be ${expectedSpokenNumber}") {
            assertThat(memoryGame.spokeNumberAtTurn(nthTurn))
                    .isEqualTo(expectedSpokenNumber)
        }
    }

    @TestFactory
    fun shouldFoundThe2020thSpokenNumber() = listOf(
            listOf(1, 3, 2) to 1,
            listOf(2, 1, 3) to 10,
            listOf(1, 2, 3) to 27,
            listOf(2, 3, 1) to 78,
            listOf(3, 2, 1) to 438,
            listOf(3, 1, 2) to 1836
    ).map { (startingNumbers, expectedSpokenNumber) ->
        DynamicTest.dynamicTest("for ${startingNumbers} At the turn 2020th the nummber spoke should be ${expectedSpokenNumber}") {
            val memoryGame =MemoryGame(startingNumbers)
            assertThat(memoryGame.spokeNumberAtTurn(2020))
                    .isEqualTo(expectedSpokenNumber)
        }
    }
}