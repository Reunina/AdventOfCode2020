package com.adventofcode.day12

import com.adventofcode.day12.FerryAssert.Companion.assertThatFerry
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FerryTest {

    @Test
    fun shouldStartFacingEast() {
        val brandNewFerry = Ferry()
        assertThatFerry(brandNewFerry)
                .isPositionedAt( 0L,  0L)
                .face(Orientation.E)


    }

    @Test
    fun shouldFollowSimpleInstructions() {

        val brandNewFerry = Ferry()

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction("F10")
                .isPositionedAt( 10L, 0L)
                .face(Orientation.E)

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction("N3")
                .isPositionedAt( 10L, 3L)
                .face(Orientation.E)

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction( "F7")
                .isPositionedAt( 17L, 3L)
                .face(Orientation.E)

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction( "R90")
                .isPositionedAt( 17L, 3L)
                .face(Orientation.S)

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction( "F11")
                .isPositionedAt( 17L, -8L)
                .face(Orientation.S)

    }


}

class FerryAssert(value: Ferry) : AbstractObjectAssert<FerryAssert, Ferry>(value, FerryAssert::class.java) {


    fun isPositionedAt(eastWest: Long, northSouth: Long): FerryAssert {
        return hasPositionNothSouth(northSouth)
                .hasPositionEastWest(eastWest)

    }
    fun hasPositionNothSouth(expected: Long) : FerryAssert{
        assertThat(actual.positionNorthSouth).isEqualTo(expected)
        return this
    }
    fun hasPositionEastWest(expected: Long) : FerryAssert{
        assertThat(actual.positionEastWest).isEqualTo(expected)
        return this
    }

    fun face(expectedFacing: Orientation): FerryAssert {
        val actualFacing = actual.facing
        assertThat(actualFacing)
                .`as`("Expecting facing <%s> but foung facing <%s>", expectedFacing , actual.facing)
                .isEqualTo(expectedFacing)
        return this

    }


    fun afterFollowingInstruction(instruction: String): FerryAssert {
        actual.followInstruction(instruction)
        return this

    }

    companion object {
        fun assertThatFerry(value: Ferry): FerryAssert {
            return FerryAssert(value)
        }
    }

}