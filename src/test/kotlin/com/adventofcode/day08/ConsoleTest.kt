package com.adventofcode.day08

import com.adventofcode.day08.ConsoleAssert.Companion.assertThatConsole
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ConsoleTest {

    @Test
    fun shouldHasAccumulatorInitializedAt0() {
        assertAll(
                {
                    assertThatConsole(Console()).hasAccumulatorAt(0)
                    assertThatConsole(Console().boot()).hasAccumulatorAt(0)
                }
        )
    }

    @Test
    fun shouldDoNothingWhenInstructionInNOP() {
        assertThatConsole(Console("nop +0").boot())
                .hasAccumulatorAt(0)
    }


    @Test
    fun shouldIncreasesWhenInstructionIsACCWithPositiveValue() {
        assertThatConsole(Console("acc +1").boot())
                .hasAccumulatorAt(1)
    }

    @Test
    fun shouldIncreasesWhenInstructionIsACCWithNegativeValue() {
        assertThatConsole(Console("acc -1").boot())
                .hasAccumulatorAt(-1)
    }


    @Test
    fun shouldJmpWhenInstructionIsJmpWithValue() {
        assertAll(
                {

                    assertThatConsole(Console("jmp +2", "acc +1", "nop +0").boot())
                            .`as`("jmp instruction with positive and greater than 1 should skip some next instructions")
                            .hasAccumulatorAt(0)
                            .hasAllInstructionsVisitedBut(1)

                    assertThatConsole(Console("jmp +2", "acc +1", "jmp -1").boot())
                            .`as`("jmp instruction with offset at 1 should do nothing")
                            .hasAccumulatorAt(1)
                            .hasAllInstructionsVisited()

                }
        )
    }


    @Test
    fun shouldMarkInstructionAsVisited() {
        assertAll(
                {

                    assertThatConsole(Console("jmp +1", "acc +1", "nop +0").boot())
                            .`as`("jmp instruction with offset at 1 should do nothing")
                            .hasAllInstructionsVisited()

                    assertThatConsole(Console("nop +0", "jmp +2", "nop +0").boot())
                            .`as`("jmp instruction with offset at 1 should do nothing")
                            .hasAllInstructionsVisitedBut(1)
                }
        )

    }


    @Test
    fun shouldStopWhenHittingAnInfiniteLoop() {
        assertThatConsole(Console(
                "nop +0",
                "acc +1",
                "jmp +4",
                "acc +3",
                "jmp -3",
                "acc -99",
                "acc +1",
                "jmp -4",
                "acc +6").boot())
                .hasAccumulatorAt(5)


    }

    @Test
    fun shouldFoundMutatedInstructionsThatComputeNormallyChangingJmpToNop() {

        val console = Console(
                "nop +0",
                "acc +1",
                "jmp +4",
                "acc +3",
                "jmp -3",
                "acc -99",
                "acc +1",
                "jmp -4",
                "acc +6")

        assertThatConsole(console.boot())
                .hasNotLastInstructionsVisited()
                .hasAccumulatorAt(5)

        assertThatConsole(console.smartBoot())
                .hasLastInstructionsVisited()
                .hasAccumulatorAt(8)

    }

}


class ConsoleAssert(value: Console?) : AbstractObjectAssert<ConsoleAssert, Console?>(value, ConsoleAssert::class.java) {
    fun hasAccumulatorAt(expectedValue: Int): ConsoleAssert {
        assertThat(actual?.accumulator).isEqualTo(expectedValue)
        return this
    }

    fun hasLastInstructionsVisited(): ConsoleAssert {
        assertThat(actual?.visitedInstructions).contains(actual?.instructions?.size!! - 1)
        return this
    }

    fun hasNotLastInstructionsVisited(): ConsoleAssert {
        assertThat(actual?.lastInstructionHasBeenVisited()).isFalse()
        return this
    }

    fun hasAllInstructionsVisited(): ConsoleAssert {
        assertThat(actual?.lastInstructionHasBeenVisited()).isTrue()
        return this
    }

    fun hasAllInstructionsVisitedBut(expectedNumber: Int): ConsoleAssert {
        assertThat(actual?.visitedInstructions).hasSize(actual?.instructions?.size!! - expectedNumber)
        return this
    }

    companion object {
        fun assertThatConsole(value: Console): ConsoleAssert {
            return ConsoleAssert(value)
        }
    }

}