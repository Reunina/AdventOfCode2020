package com.adventofcode.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class Day11Test {


    @Test
    fun shouldFound2270WithFullInput() {

        assertThat(SeatHandler.fromInput(File("src/main/resources/day11/puzzle_input.txt")
                .readLines()
                .map { it.toCharArray() }).runOccupationSeatsSimulationWithAdjacentSeats()
        ).isEqualTo(2270L)
    }

    @Test
    fun shouldComputeSimpleExampleOfSimulationWithAdjacentSeats() {
        val input = ("L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL").split("\n").map { it.toCharArray() }

        assertThat(SeatHandler.fromInput(input).runOccupationSeatsSimulationWithAdjacentSeats())
                .isEqualTo(37L)
    }

    @Test
    fun shouldFound2042WithFullInput() {

        assertThat(SeatHandler.fromInput(File("src/main/resources/day11/puzzle_input.txt")
                .readLines()
                .map { it.toCharArray() }).runOccupationSeatsSimulationWithFirstVisibleSeats()
        ).isEqualTo(2042L)
    }


}
