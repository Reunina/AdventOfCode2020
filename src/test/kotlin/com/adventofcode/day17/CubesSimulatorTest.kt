package com.adventofcode.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.io.File

class CubesSimulatorTest {

    @Test
    fun shouldBeAbleToReadFromInput() {


        assertThat(CubesSimulator(File("src/main/resources/day17/puzzle_input.txt")
                .readLines()))
                .isEqualToComparingFieldByFieldRecursively(CubesSimulator(listOf(
                        "...#...#",
                        "..##.#.#",
                        "###..#..",
                        "........",
                        "...##.#.",
                        ".#.####.",
                        "...####.",
                        "..##...#"
                )))

    }

    private val withSimpleInitialData = CubesSimulator(listOf(
            ".#.",
            "..#",
            "###"
    ))


    @TestFactory
    fun shouldFoundActiveCubesAfterNthCycle() = listOf(
            0 to 5L,
            1 to 11L,
            2 to 21L,
            3 to 38L
            ).map { (n, activeCubes) ->
        DynamicTest.dynamicTest("After $n cycles, there mus be $activeCubes active cubes") {
            assertThat(withSimpleInitialData.simulateCycles(n))
                    .isEqualTo(activeCubes)
        }
    }

    @Test
    fun shouldFoundActiveCubesAfterNthCycleWhenWaries() {


        assertThat(withSimpleInitialData.simulateCyclesOn4thDim(nbCycles = 6 ))
                .isEqualTo(848)

    }


}